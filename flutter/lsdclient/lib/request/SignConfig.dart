import 'dart:convert';
import 'dart:io';

import 'package:convert/convert.dart';
import 'package:crypto/crypto.dart';
import 'package:lsdclient/tools/aj_log_util.dart';

class SignConfig {

  static String generateMd5(String data){
    var content = new Utf8Encoder().convert(data);
    var digest = md5.convert(content);
    return hex.encode(digest.bytes);
  }

  /*
  * {
  "header": {
    "sign":"xxx",
    "time": "xxx",
    "token":"xxx"
  },
  "pageSize":1,
  "pageNo":10,
  "time": "xxx",
  "reqData": {
  }
}*/
  static Future<ReqModle> signData( Object params, tokenStr, time, {pageNo,pageSize,}) async{
    String token = tokenStr;
    Map<String , dynamic> reqData = new Map();
    Map<String , dynamic> headerData = new Map();
    Map<dynamic , dynamic> paramsObj = new Map();
    paramsObj = params;
    var arr = [];
    //将字典转成数组
    if(paramsObj != null){
      paramsObj.forEach((key,value)=>  arr.add(key));
    }
    //进行签名校验
    Map cr = new Map();
    if(pageNo != null){
      cr['pageNo'] = pageNo;
    }
    if(pageSize != null){
      cr['pageSize'] = pageSize;
    }
    if(paramsObj != null){
      cr['reqData'] = json.encode(paramsObj);
    }
    AJLogUtil.v(time.toString());
    cr['time'] = time.toString();
    AJLogUtil.v("cr: $cr");
    var array = [];
    cr.forEach((key,value) => array.add(key));
    array.sort();

    var str = '';
    for (var i = 0; i < array.length; i++) {
      var key = array[i];
      var value = cr["$key"];
      str += "$key" + "$value";
    }

    str += "ANJIPLUS20170824";

    AJLogUtil.v("array: $array");
    AJLogUtil.v("sign: $str");
    if(pageNo != null){
      reqData['pageNo'] = pageNo;
    }
    if(pageSize != null){
      reqData['pageSize'] = pageSize;
    }
    reqData['reqData'] = params;
    reqData["time"] = time;
    headerData[HttpHeaders.contentTypeHeader] = "application/json;charset=UTF-8";
    headerData["time"] = time;
    headerData["sign"] = generateMd5(str);
    headerData["token"] = token;
    AJLogUtil.v("headerData $headerData");
    ReqModle reqModle = ReqModle(header: headerData, params: reqData);
    return reqModle;
  }
}

class ReqModle {
  Map<String , dynamic> header = new Map();
  Map<String , dynamic> params = new Map();
  ReqModle({this.header, this.params});
}


/*
* //    print(object);
//    if(paramsObj != null){
////      if(paramsObj.containsKey("pageNo")){
//////        cr['pageNo'] = "10";
////      }
//
//      cr['pageSize'] = "1";
//    }*/