import 'package:aj_flutter_plugin/aj_flutter_plugin.dart';
import 'package:aj_flutter_update/aj_flutter_update.dart';
import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/models/app_version.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:version/version.dart';

class AppUpdateUtils  {

//  #版本对比
//  version: ^1.0.3
  static Future<AppVersion> checkAppVersion() async{
    var res = await UserDao.getAppUpdate();
    if(res != null && res.result){
      AppVersion appVersion = res.data;
      AJLogUtil.v(appVersion);
      AjFlutterPlugin info = await AjFlutterPlugin.platformVersion();
      Version currentVersion = Version.parse(info.version);
      Version lastVersion = Version.parse(appVersion.appVersion);
      if(lastVersion > currentVersion){

        if(!appVersion.mandatoryUpgrade){
          int nowTimeMillisecond = new DateTime.now().millisecondsSinceEpoch;
          int oldTimeMillisecond =
          await LocalStorage.getInt("${AJConfig.screen_local_update_time}");
          if (oldTimeMillisecond == null) {
            oldTimeMillisecond = 0;
            await LocalStorage.saveInt(
                "${AJConfig.screen_local_update_time}", nowTimeMillisecond);
          }
          AJLogUtil.v(
              "updateTimeMillisecond  $nowTimeMillisecond  -  $oldTimeMillisecond");
          //如果 时间差 小于12个小时   1000 * 60秒 * 60分 * 12小时
          if ((nowTimeMillisecond - oldTimeMillisecond) < 1000 * 60 * 60 * 12) {
            return null;
          }
        }
        return appVersion;
      }
    }
    return null;
  }

  static updateApp(BuildContext context){
    AppUpdateUtils.checkAppVersion().then((appversion){
      if(appversion != null){

        AjFlutterUpdateMixin.versionUpdate(
          context: context,
          downloadUrl: appversion.fileUrl,
          updateLog: appversion.remark,
          mustUpdate: appversion.mandatoryUpgrade,
          titleColor: Color( 0xFFFFA033 ),
          buttonColor: AJColors.buttonNormalColor,
        );
      }
    });
  }
}