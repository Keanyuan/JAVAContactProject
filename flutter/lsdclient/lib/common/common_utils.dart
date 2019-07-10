
import 'dart:convert';
import 'dart:io';
import 'dart:math';
import 'dart:ui';

import 'package:aj_flutter_plugin/aj_flutter_plugin.dart';
import 'package:common_utils/common_utils.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/models/screen_list_model.dart';
import 'package:lsdclient/reducer/theme_data_reducer.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:lsdclient/tools/aj_state.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:redux/redux.dart';


///通用逻辑
class CommonUtils {
  static double sStaticBarHeight = 0.0;

  //颜色列表
  static List<Color> getThemeListColor() {
    return [
      AJColors.primarySwatch,
      Colors.brown,
      Colors.blue,
      Colors.teal,
      Colors.amber,
      Colors.blueGrey,
      Colors.deepOrange,
    ];
  }

  //初始化状态栏高度
  static void initStatusBarHeight(context) async {
    double _statusBarHeight = MediaQuery.of(context).padding.top;
    _statusBarHeight = MediaQueryData.fromWindow(window).padding.top;
    sStaticBarHeight = _statusBarHeight;
//    sStaticBarHeight = await FlutterStatusbar.height / MediaQuery.of(context).devicePixelRatio;
  }


  /*改变主题*/
  static pushTheme(Store store, int index){
    ThemeData themeData;
    List<Color> colors = getThemeListColor();
    themeData = new ThemeData(primarySwatch: colors[index], platform: TargetPlatform.iOS);
    store.dispatch(new RefreshThemeDataAction(themeData));
  }

  static Future<bool> dialogExitLoginApp(BuildContext context){
    return showDialog(
        context: context,
        builder: (context) => new AlertDialog(
          content: new Text("确定要退出登录？"),
          actions: <Widget>[
            new FlatButton(
              onPressed: (){
                Navigator.of(context).pop(false);
              },
              child: new Text("取消"),
            ),
            new FlatButton(
                onPressed: (){
                  Navigator.of(context).pop();
                  ///防止多次进入
                  Store<AJState> store = StoreProvider.of(context);
                  UserDao.clearAll(store);
//                  NavigatorUtils.goLogin(context, result: true);
                },
                child: new Text("确定"))
          ],
        )
    );
  }

  static goLogin(BuildContext context, {result = false}) {
    ///防止多次进入
//    Store<AJState> store = StoreProvider.of(context);
//    UserDao.clearAll(store);
//    NavigatorUtils.goLogin(context, result: true);
  }

    ///大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数
  /// 此方法中前三位格式有：
  /// 13+任意数 * 15+除4的任意数 * 18+除1和4的任意数 * 17+除9的任意数 * 147
  static bool isChinaPhoneLegal(String str) {
    return new RegExp('^((13[0-9])|(15[^4])|(166)|(17[0-8])|(18[0-9])|(19[8-9])|(147,145))\\d{8}\$').hasMatch(str);
  }

  static bool isChineseCharacter(String str) {
    return new RegExp('^.*[\u4e00-\u9fa5]+.*\$').hasMatch(str);
  }

  ///包含英文和数字
  static bool isInnerEnglishAndNum(String str){
    return new RegExp('^(?=.*[a-zA-Z]+)(?=.*[0-9]+)[a-zA-Z0-9]+\$').hasMatch(str);
  }

//  var regEmail=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+(\.[a-zA-Z]{2,3})+$/;

  static bool isEmail(String str) {
    return new RegExp('^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}\$').hasMatch(str);
  }

  static String getCaptchaStr(int kCharCount){
    final changeList = ["0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
    var changeStr = "";
    final random = new Random();

    for (var x in changeList.sublist(0, kCharCount)) {
      var index = random.nextInt(changeList.length);
      changeStr = changeStr + changeList[index];
    }
    return changeStr;
  }

  static bool checkLogin(context){
    Store<AJState> store = StoreProvider.of(context);
    bool _isLogin = false;
    if (store.state.userInfo != null && store.state.userInfo.token != null) {
      if (store.state.userInfo.token.length > 0) {
        _isLogin = true;
      }
    }

    return _isLogin;
  }

  //h5 专用 string类型
  static Future<String> getScreenStringMapInfo() async{
    var _screen_info_model = await LocalStorage.get(AJConfig.screen_info_local_storage);
    if(_screen_info_model == null){
      ScreenInfoModel _screenLocalModel = ScreenInfoModel();
      _screen_info_model = json.encode(_screenLocalModel.toMap());
    }
    AJLogUtil.v(_screen_info_model);
    return _screen_info_model;
  }

  //native 专用 Map类型
  static Future<Map> getScreenMapInfo() async{
    var _screen_info_model = await LocalStorage.get(AJConfig.screen_info_local_storage);
    ScreenInfoModel _screenLocalModel = ScreenInfoModel();
    if(_screen_info_model == null){
      return _screenLocalModel.toMap();
    }
    Map _map = json.decode(_screen_info_model);
    return _map;
  }


  /// 提示修改密码链接发送到邮箱
  static Future<bool> showLuanchImgaeDialog(BuildContext context, {GestureTapCallback onTap, String name = "assets/images/home_01.png"}) {
    return showDialog(
        context: context,
        builder: (context) => new Material(
          color: Colors.transparent,
          child: WillPopScope(
            onWillPop: () async => false,
            child: GestureDetector(
              onTap: onTap,
              child: Image.asset(
                name,
                fit: BoxFit.fitHeight,
              ),
            ),
          ),
        ));
  }


  static void launchImage(String image, String localStorageInfo, BuildContext mycontext) async {
    bool isShow = await LocalStorage.getBool(localStorageInfo);
    if (ObjectUtil.isEmpty(isShow) || !isShow) {
      await LocalStorage.saveBool(localStorageInfo, true);
      CommonUtils.showLuanchImgaeDialog(mycontext, name: image, onTap: () {
        Navigator.of(mycontext).pop(false);
      });
    }
  }
}