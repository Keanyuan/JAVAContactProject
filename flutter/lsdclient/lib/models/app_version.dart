import 'package:lsdclient/tools/object_utils.dart';

class AppVersion {
  String appVersion;
  String fileUrl;
  bool mandatoryUpgrade; //是否强制升级 0 否  1 是
  String remark;
  int appType; //APP平台类别 0 Android平台 1 IOS平台


  AppVersion({this.appVersion, this.fileUrl, this.mandatoryUpgrade, this.remark});


  static AppVersion fromJson(Map<String, dynamic> json) {
    AppVersion appVersion = new AppVersion();
    appVersion.appVersion = json["appVersion"];
    appVersion.fileUrl = json["fileUrl"];
    appVersion.mandatoryUpgrade = json["mandatoryUpgrade"] == 0 ? false : true ;
    appVersion.remark = json["remark"] ;
    appVersion.appType = json["appType"] ;
    return appVersion;
  }

  Map<String, dynamic> toJson() {
    var map = new Map<String, dynamic>();
    map['appVersion'] = appVersion;
    map['fileUrl'] = fileUrl;
    map['mandatoryUpgrade'] = mandatoryUpgrade;
    map['remark'] = remark;
    map['appType'] = appType;
    return map;
  }


  @override
  String toString() {
    // TODO: implement toString
    return ObjectUtils.jsonFormat(toJson());
  }

}