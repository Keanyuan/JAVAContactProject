import 'dart:convert';
import 'dart:io';

import 'package:flutter/services.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/common_utils.dart';
import 'package:lsdclient/common/data_result.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/models/User.dart';
import 'package:lsdclient/models/app_version.dart';
import 'package:lsdclient/models/screen_item_model.dart';
import 'package:lsdclient/models/screen_list_model.dart';
import 'package:lsdclient/reducer/user_reducer.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/request/HttpManager.dart';
import 'package:lsdclient/request/result_data.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:redux/redux.dart';
import 'package:version/version.dart';

class UserDao {
  //更新第一次启动
  static updateFirstLaunch(bool firstLaunch) async {
    LocalStorage.saveBool(AJConfig.isFirstLaunch, firstLaunch);
  }

  //获取第一次启动
  static getFirstLaunch() async {
    return await LocalStorage.getBool(AJConfig.isFirstLaunch);
  }

  //初始化用户信息
  static initUserInfo(Store store) async {
    var token = await LocalStorage.get(AJConfig.TOKEN_KEY);

    //获取本地用户信息
    var res = await getUserInfoLocal();

    //如果用户信息不为空保存到reduce中
    if (res != null && res.result && token != null) {
      store.dispatch(UpdateUserAction(res.data));
    }

    ///读取主题颜色
    String themeIndex = await LocalStorage.get(AJConfig.THEME_COLOR);
    if (themeIndex != null && themeIndex.length != 0) {
      CommonUtils.pushTheme(store, int.parse(themeIndex));
    }
    return new DataResult(res.data, (res.result && (token != null)));
  }

  ///退出登录
  static clearAll(Store store) async {
    HttpManager.clearAuthorization();
    await LocalStorage.remove(AJConfig.USER_INFO);
    store.dispatch(new UpdateUserAction(User.empty()));
  }

  ///获取本地登录用户信息
  static getUserInfoLocal() async {
    //JSON 字符串
    var userText = await LocalStorage.get(AJConfig.USER_INFO);
    if (userText != null) {
      var userMap = json.decode(userText);
      User user = User.fromJson(userMap);
      return new DataResult(user, true);
    } else {
      return new DataResult(null, false);
    }
  }

  //otdNodeAlert/list
  static getOtdNodeAlertList(String typeCode) async {
    var params = {
    };
    var res = await HttpManager.requestData(Address.otdNodeAlertList, params, pageNo: 1, pageSize: 10);
    var resultData;
    if(res != null && res.result){

    }
    return new DataResult(resultData, res.result);
  }

  //筛选条件
  static getScreenListInfo(String typeCode) async {

    var resultLocalData =  await LocalStorage.get(
        "${AJConfig.screen_local_storagelist}${typeCode}");
    var res = null;
    AJLogUtil.v("resultLocalData   ---   $resultLocalData");
    var params = {
      "conditionType" : typeCode
    };
    if(resultLocalData == null){
      res = await HttpManager.requestData(Address.screenListInfo, params);
      if(res != null && res.result){
        int nowTimeMillisecond = new DateTime.now().millisecondsSinceEpoch;
        await LocalStorage.saveInt("${AJConfig.screen_local_time}", nowTimeMillisecond);
        await LocalStorage.save("${AJConfig.screen_local_storagelist}${typeCode}", json.encode(res.data));
      }
    } else {
      int nowTimeMillisecond = new DateTime.now().millisecondsSinceEpoch;
      int oldTimeMillisecond = await LocalStorage.getInt("${AJConfig.screen_local_time}");
      if(oldTimeMillisecond == null){
        oldTimeMillisecond = 0;
      };
      AJLogUtil.v("nowTimeMillisecond333  $nowTimeMillisecond  -  $oldTimeMillisecond");
      //如果 时间差 大于12个小时   1000 * 60秒 * 60分 * 12小时
      if((nowTimeMillisecond - oldTimeMillisecond) > 1000 * 60 * 60 * 12){
        await NavigatorUtils.clearScreenData();
        res = await HttpManager.requestData(Address.screenListInfo, params);
        if(res != null && res.result){
          await LocalStorage.saveInt("${AJConfig.screen_local_time}", nowTimeMillisecond);
          await LocalStorage.save("${AJConfig.screen_local_storagelist}${typeCode}", json.encode(res.data));
        }
      } else {
        var info = json.decode(resultLocalData);
        if(info is Map){
          var result = DataResult(info, true);
          res = result;
        }

      }
    }

    var resultData;
    if(res != null && res.result){
      List<ScreenItemModel> _list = ScreenItemModel.fromMapList(res.data["conditionsList"]);
      ScreenListRepModel screenListRepModel = ScreenListRepModel();
      screenListRepModel.screenList = _list;
      screenListRepModel.showFirstLetter = res.data["showFirstLetter"] ?? false;
      screenListRepModel.isMultiSelect = res.data["isMultiSelect"] ?? false;

      resultData = screenListRepModel;
    }
    return new DataResult(resultData, res.result);
  }

  //user/noauth/logout
  static getUserlogout() async{
    var res = await HttpManager.requestData(Address.userLogout, null,  optionMetod: "get", needError: false);
    return new DataResult(null, res.result);
  }


  //user/v1/noauth/sendingSms
  static getSendingSms(mobile) async {
    var params = {
      "mobile": mobile,
    };
    var res = await HttpManager.requestData(Address.sendingSms, params,
        isNeedToken: false, sendingSms: true);
    print(res.data);
    print(res.result);
    var resultData;
    if (res != null && res.result) {
      resultData = res.data;
    }
    return new DataResult(resultData, res.result);
  }

  //app/update
  static getAppUpdate() async {
    var params = {
      "appType": Platform.isIOS ? 1 : 0,//APP平台类别 0 Android平台 1 IOS平台
    };
    var res = await HttpManager.requestData(Address.app_update, params,
        isNeedToken: false, needError: false);
    print(res.data);
    print(res.result);
    var resultData;
    if (res != null && res.result) {
      resultData = res.data;
      AppVersion appVersion = AppVersion.fromJson(resultData);
      resultData  = appVersion;
    }
    return new DataResult(resultData, res.result);
  }

  //user/noauth/mobileLogin/v1
  static getUserMobileLogin(mobile, smsCode, verificationCodeToken, store) async {
    await HttpManager.clearAuthorization();
    var params = {
      "mobile": mobile,
      "smsCode": smsCode,
      "verificationCodeToken": verificationCodeToken
    };
    AJLogUtil.v(params);
    var res = await HttpManager.requestData(Address.mobileLogin, params,
        isNeedToken: false);
    var resultData;
    if (res != null && res.result) {
      resultData = res.data;
      var resultDataTemp = await getUserInfo(resultData);
      resultData = resultDataTemp;
      store.dispatch(new UpdateUserAction(resultData.data));
    }
    return new DataResult(resultData, res.result);
  }

  /// 登录 二期 accessUser/login
  static getUserLogin(userName, password, store) async {
    await HttpManager.clearAuthorization();
    var params = {
      "operName": userName,
      "operPassword": password,
    };
    var res = await HttpManager.requestData(Address.userLogin, params,
        isNeedToken: false);
    var resultData;
    if (res != null && res.result) {
      resultData = res.data;
      var resultDataTemp = await getUserInfo(resultData);
      resultData = resultDataTemp;
      store.dispatch(new UpdateUserAction(resultData.data));
    }
    return new DataResult(resultData, res.result);
  }

  static getUserInfo(res) async {
    User user = User.fromJson(res);
    if (user.token != null) {
      await LocalStorage.save(
          AJConfig.USER_INFO, json.encode(user.toJson(user)));
      return new DataResult(user, true);
    } else {
      return new DataResult(res, false);
    }
  }
}
