import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/pages/home/home_page.dart';
import 'package:lsdclient/pages/login_page.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:lsdclient/tools/local_storage.dart';

import '../main.dart';

class NavigatorUtils {
  static List<String> typeCodeList = [
    "customer", //客 户
    "transfertype", //运输方式
    "sendsrc", //发货仓库
    "transsupplier", //运输供方
//    "alarmtype", //预警类型
    "alarmlevel", //预警类型级别
    "delaytime", //延期天数
    "retailer", //经销商
    "logicarea", //分区
    'ordertype', //订单类型
  ];

  static Future<bool> clearScreenData() async {
    for (var typeCode in NavigatorUtils.typeCodeList) {
      AJLogUtil.v("${AJConfig.screen_local_storagelist}${typeCode}");
      await LocalStorage.save(
          "${AJConfig.screen_local_storagelist}${typeCode}", null);
      await LocalStorage.save(
          "${AJConfig.screen_local_storage}${typeCode}", null);

      var resultLocalData = await LocalStorage.get(
          "${AJConfig.screen_local_storagelist}${typeCode}");
      var screen_local_storage =
          await LocalStorage.get("${AJConfig.screen_local_storage}${typeCode}");
      AJLogUtil.v("resultLocalData   ---   $resultLocalData");
      AJLogUtil.v("screen_local_storage ----- $screen_local_storage");
    }
    await LocalStorage.save(AJConfig.screen_info_local_storage, null);
    return true;
  }

  //首页
  static goHome(BuildContext context) async {
    int nowTimeMillisecond = new DateTime.now().millisecondsSinceEpoch;
    int oldTimeMillisecond =
        await LocalStorage.getInt("${AJConfig.screen_local_time}");
    if (oldTimeMillisecond == null) {
      oldTimeMillisecond = 0;
      await LocalStorage.saveInt(
          "${AJConfig.screen_local_time}", nowTimeMillisecond);
    }
    AJLogUtil.v(
        "nowTimeMillisecond222  $nowTimeMillisecond  -  $oldTimeMillisecond");

    //如果 时间差 大于12个小时   1000 * 60秒 * 60分 * 12小时
    if ((nowTimeMillisecond - oldTimeMillisecond) > 1000 * 60 * 60 * 12) {
      NavigatorUtils.clearScreenData().then((cle) {
        Navigator.pushReplacementNamed(context, HomePage.sName);
      });
    } else {
      Navigator.pushReplacementNamed(context, HomePage.sName);
    }
  }

  //登录
  static goLogin(BuildContext context,
      {result = false, needClear = false}) async {
    int nowTimeMillisecond = new DateTime.now().millisecondsSinceEpoch;
    int oldTimeMillisecond =
        await LocalStorage.getInt("${AJConfig.screen_local_time}");
    if (oldTimeMillisecond == null) {
      oldTimeMillisecond = 0;
    }
    ;
    AJLogUtil.v(
        "nowTimeMillisecond11  $nowTimeMillisecond  -  $oldTimeMillisecond");
    await LocalStorage.saveInt(
        "${AJConfig.screen_local_time}", nowTimeMillisecond);
    if (needClear) {
      NavigatorUtils.clearScreenData().then((cle) {
        _gotoLogin(context, result: result);
      });
    } else {
      //如果 时间差 大于12个小时   1000 * 60秒 * 60分 * 12小时
      if ((nowTimeMillisecond - oldTimeMillisecond) > 1000 * 60 * 60 * 12) {
        NavigatorUtils.clearScreenData().then((cle) {
          _gotoLogin(context, result: result);
        });
      } else {
        _gotoLogin(context, result: result);
      }
    }
  }

  static _gotoLogin(BuildContext context, {result = false}) {
    if (result) {
      Navigator.pushAndRemoveUntil(
          context,
          new PageRouteBuilder(
            opaque: false,
            pageBuilder: (BuildContext context, _, __) {
              return AJLocalizations(child: LoginPage());
            },
          ),
          (Route<dynamic> route) => false);
    } else {
      Navigator.pushReplacementNamed(context, LoginPage.sName, result: result);
    }
  }

  /// push  child 要跳转的页面     needTransition是否需要动画
  static pushTO(BuildContext context,
      {Widget child, bool needTransition = false}) {
    return _push(context, child: child, needTransition: needTransition);
  }

  static pushNotAnimationTO(BuildContext context,
      {Widget child}) {
    return Navigator.push(
      context,
      PageRouteBuilder(
        opaque: true,
        pageBuilder: (BuildContext context, _, __) {
          return child;
        },
      ),
    );
  }

  //base push 基类
  static _push(BuildContext context,
      {Widget child, bool needTransition = false}) {
    if (needTransition) {
      return Navigator.push(
        context,
        new PageRouteBuilder(
            pageBuilder: (BuildContext context, Animation<double> animation,
                Animation<double> secondaryAnimation) {
              return child;
            },
            transitionDuration: const Duration(milliseconds: 200),
            transitionsBuilder: (BuildContext context,
                Animation<double> animation,
                Animation<double> secondaryAnimation,
                Widget child) {
              // 添加一个平移动画
              return createTransition(animation, child);
            }),
      );
    } else {
      return Navigator.push(
        context,
        new MaterialPageRoute(builder: (context) => child),
      );
      ;
    }
  }

  static SlideTransition createTransition(
      Animation<double> animation, Widget child,
      {bool reverse = false}) {
    double offset = reverse ? -1.0 : 1.0;
    return new SlideTransition(
      position: new Tween<Offset>(
        begin: Offset(offset, 0.0),
        end: Offset(0.0, 0.0),
      ).animate(animation),
      child: child, // child is the value returned by pageBuilder
    );
  }
}
