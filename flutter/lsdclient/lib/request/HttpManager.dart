import 'dart:io';

import 'package:dio/dio.dart';
import 'package:connectivity/connectivity.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/request/SignConfig.dart';
import 'dart:collection';

import 'package:lsdclient/request/result_data.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:lsdclient/tools/object_utils.dart';

///http请求
class HttpManager {
  static const CONTENT_TYPE_JSON = "application/json";
  static const CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";

  ///发起网络请求
  ///[ url] 请求url
  ///[ param] 请求参数
  ///[ isNeedToken] 是否需要token 需要token  添加header ,   不需要token 不添加header
  ///[ optionMetod] 请求类型 post、get
  ///[ noTip] 是否需要返回错误信息 默认不需要
  ///[ needSign] 是否需要Sign校验  默认需要
  ///[ needError] 是否需要错误提示
  static requestData(
    url,
    param, {
    bool isNeedToken = true,
    String optionMetod = "post",
    pageNo,
    pageSize,
    noTip = false,
    needSign = true,
    needError = true,
    sendingSms = false,
  }) async {
//    Address.host = "http://10.108.14.223:8802";
//    Address.host = "http://10.108.14.229:8802";

    ///没有网络
    var connectivityResult = await (new Connectivity().checkConnectivity());
    if (connectivityResult == ConnectivityResult.none) {
      return new ResultData(
          Code.errorHandleFunction(Code.NETWORK_ERROR, "", noTip),
          false,
          Code.NETWORK_ERROR);
    }

    //获取token
    var mirrorToken = await getAuthorization();
    if (isNeedToken) {
      if (mirrorToken == "") {
        if (needError) {

          AJLogUtil.v("needError $url");
          return new ResultData(
              Code.errorHandleFunction(
                  Code.REQUEST_SHIBBOLETH, "未登录或登录已过期", noTip),
              false,
              Code.REQUEST_SHIBBOLETH);
        }
      }
    }

    //请求协议 post 、get
    RequestOptions option = new RequestOptions(method: optionMetod);
    //设置超时时间
    option.connectTimeout = 15000;
    var params;
    var time = new DateTime.now().millisecondsSinceEpoch;

    //是否需要token
    if (isNeedToken) {
      //获取 请求头  和  请求参数
      ReqModle reqModle = await SignConfig.signData(param, mirrorToken, time,
          pageSize: pageSize, pageNo: pageNo);
      option.headers = reqModle.header;
      params = reqModle.params;
    } else {
      params = {"time": time, "reqData": param};
    }

    if (Platform.isIOS) {
      option.headers[HttpHeaders.userAgentHeader] = "MOBILE;IPHONE";
    } else {
      option.headers[HttpHeaders.userAgentHeader] = "MOBILE;ANDROID";
    }

    AJLogUtil.v('请求url: ' + Address.host + url);
    AJLogUtil.v('请求头: ' + option.headers.toString());
    if (params != null) {
      AJLogUtil.v('请求参数: ' + params.toString());
    }

    ///初始化请求类
    Dio dio = new Dio();

    (dio.httpClientAdapter as DefaultHttpClientAdapter).onHttpClientCreate = (client){
      if(Address.needfindProxyHost){
        client.findProxy = (uri) {
          //proxy all request to localhost:8888
          return "PROXY ${Address.findProxyHost}:8888";
        };
      }
      client.badCertificateCallback=(X509Certificate cert, String host, int port){
//        if(cert.pem==PEM){ // Verify the certificate
//          return true;
//        }
        return true;
      };
    };
    Response response;
    try {
      ///开始请求
      response =
          await dio.request(Address.host + url, data: params, options: option);
    } on DioError catch (e) {
      Response errorResponse;
      if (e.response != null) {
        errorResponse = e.response;
      } else {
        errorResponse = new Response(statusCode: 666);
      }

      AJLogUtil.v('请求异常: ' + e.toString());
      AJLogUtil.v('请求异常url: ' + url);

      ///请求失败处理
      if (needError) {
        return new ResultData(
            Code.errorHandleFunction(Code.TOASTFORDEFAULT, e.message, noTip),
            false,
            Code.TOASTFORDEFAULT);
      }
    }

    AJLogUtil.v('header: \n' + response.headers.toString());

    if (response != null) {
      AJLogUtil.v('返回参数: ' + ObjectUtils.jsonFormat(response.data));
    }
    try {
      if (option.contentType == null) {
        var responseJson = response.data;
        if (response.statusCode == 200) {
          //保存token
          if (responseJson["repCode"] == "0000") {
            if (responseJson["repData"] != null) {
              if (responseJson["repData"] is Map) {
                if (responseJson["repData"]["token"] != null) {
                  await LocalStorage.save(
                      AJConfig.TOKEN_KEY, responseJson["repData"]["token"]);
                }
              }
            }
          }

          ///请求链接成功
          if (responseJson["repCode"] == "0000") {
            return new ResultData(responseJson["repData"], true, Code.SUCCESS,
                headers: response.headers);
          } else if (responseJson["repCode"] == "0104") {
            if (needError) {
              return new ResultData(
                  Code.errorHandleFunction(
                      Code.REQUEST_SHIBBOLETH, responseJson["repData"], noTip),
                  false,
                  Code.REQUEST_SHIBBOLETH);
            }
          } else {
            if(responseJson["repCode"] == "0230" && sendingSms){
              Code.errorHandle(responseJson["repMsg"]);
              return new ResultData(responseJson["repData"], true, Code.SUCCESS,
                  headers: response.headers);
            }
            if (needError) {
              return new ResultData(
                  Code.errorHandleFunction(
                      Code.TOASTFORDEFAULT, responseJson["repMsg"], noTip),
                  false,
                  Code.TOASTFORDEFAULT);
            }


          }
        }
      } else {
        AJLogUtil.v('请求异常--111');
        return new ResultData(response.data, true, Code.SUCCESS);
      }
    } catch (e) {
      AJLogUtil.v('请求异常11: ' + e.toString());
      if (needError) {
        return new ResultData(
            Code.errorHandleFunction(Code.TOASTFORDEFAULT, "请求失败", noTip),
            false,
            Code.TOASTFORDEFAULT);
      }
    }

    //其他错误
    if (needError) {
      return new ResultData(
          Code.errorHandleFunction(Code.TOASTFORDEFAULT, "请求失败", noTip),
          false,
          Code.TOASTFORDEFAULT);
    } else {
      return new ResultData(null, false, Code.TOASTFORDEFAULT);
    }
  }

  ///清除授权
  static clearAuthorization() async {
    await LocalStorage.remove(AJConfig.TOKEN_KEY);
  }

  ///获取授权token
  static getAuthorization() async {
    String token = await LocalStorage.get(AJConfig.TOKEN_KEY);
    if (token == null) {
      return "";
    } else {
      return token;
    }
  }
}
