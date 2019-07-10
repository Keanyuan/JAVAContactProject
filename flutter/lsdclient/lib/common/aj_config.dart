
import 'dart:io';

import 'package:flutter/services.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:flutter_umeng_analytics/flutter_umeng_analytics.dart';

class AJConfig {
  static const PAGE_SIZE = 20;
  //是否是debuge版本  上线前必须设置为false
  static bool ISPRODUCT = false;

  //UMeng iOS生产环境appkey
  static const UMENG_IOS_PRODUCT = '5d1314d70cafb272f9000536';


  //UMeng Android测试环境appkey
  static const UMENG_ANDROID_PRODUCT = '5d1313f04ca35799db00005b';

  /// //////////////////////////////////////常量////////////////////////////////////// ///
  static const TOKEN_KEY = "token";
  static const USER_NAME_KEY = "user-name";
  static const PW_KEY = "user-pw";
  //用户信息
  static const USER_INFO = "user-info";
  static const APP_VERSION= "app-version";
  static const String isFirstLaunch = "flutter.app/isFirstLaunch";
  static const APP_ISFIRST_CHECKED= "app-isfirst-checked";
  //主题颜色
  static const THEME_COLOR = "theme-color";

  static const BASEHOST_LOCAL_STORAGE = "basehost_local_storage.";
  static const WEBHOST_LOCAL_STORAGE = "webhost_local_storage.";



  //刷新条件
  static const screen_local_storage = "screen_local_storage.";

  //缓存时间戳
  static const screen_local_time = "screen_local_time";

  //缓存更新提示时间戳
  static const screen_local_update_time = "screen_local_update_time";


  //筛选条件列表
  static const screen_local_storagelist = "screen_local_storage_list.";

  //筛选结果
  static const screen_info_local_storage = "screen_info_local_storage.";

  static double textScaleFactor = 1.0;


  static void initConfig() async{

    ISPRODUCT = getCurrentEnvironment();
    AJLogUtil.init(isDebug: !ISPRODUCT, tag: "lsdclient log");
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
      DeviceOrientation.landscapeRight
    ]);
  }


  static void initUmengConfig(){
    if (Platform.isIOS) {
      UMengAnalytics.init(
          AJConfig.UMENG_IOS_PRODUCT,
          policy: Policy.BATCH, //启动发送
          encrypt: false, //设置是否对日志信息进行加密, 默认NO(不加密)
          reportCrash: true, //是否启动崩溃报告 default: YES
          logEnable: false);//设置是否打印sdk的log信息, 默认NO(不打印log)
    } else {
      UMengAnalytics.init(
          AJConfig.UMENG_ANDROID_PRODUCT,
          policy: Policy.BATCH,
          encrypt: false,
          reportCrash: true,
          logEnable: false);
    }
  }

  static bool getCurrentEnvironment(){
    return bool.fromEnvironment("dart.vm.product");
  }

}