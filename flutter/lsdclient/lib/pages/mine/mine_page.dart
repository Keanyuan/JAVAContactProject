import 'dart:async';

import 'package:aj_flutter_plugin/aj_flutter_plugin.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:lsdclient/common/common_utils.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/widgets/aj_list_title.dart';
import 'package:lsdclient/widgets/gradient_button.dart';
import 'package:redux/redux.dart';
import 'package:webview_flutter/webview_flutter.dart';

class MinePage extends StatefulWidget {
  final Completer<WebViewController> webController;

  const MinePage({Key key, this.webController}) : super(key: key);
  @override
  _MinePageState createState() => _MinePageState();
}

class _MinePageState extends State<MinePage> {

  String _version = "1.0.0";
  @override
  Widget build(BuildContext context) {
    return StoreBuilder<AJState>(builder: (context, store) {
      bool isLogin = CommonUtils.checkLogin(context);
      double bgH = ScreenUtil.getScreenW(context) * 222 / 375.0;
      final SystemUiOverlayStyle overlayStyle = SystemUiOverlayStyle.light;
      return Scaffold(
        body: Semantics(
          container: true,
          child: AnnotatedRegion<SystemUiOverlayStyle>(
              child: ListView(
                padding: EdgeInsets.only(top: 0),
                children: <Widget>[
                  Stack(
                    children: <Widget>[
                      Image.asset(
                        "assets/images/mine_header_bg.png",
                        fit: BoxFit.fitWidth,
                        height: bgH,
                      ),
                      Column(
                        children: <Widget>[
                          SizedBox(
                            height: ScreenUtil.getStatusBarH(context),
                          ),
                          Row(
                            children: <Widget>[
                              IconButton(
                                  icon: Container(
                                    child: Image.asset(
                                      AJICons.ARROW_LEFT,
                                      width: 20,
                                      color: AJColors.white,
                                    ),
                                  ),
                                  onPressed: () {
                                    Navigator.of(context).pop();
                                  }),
                              Expanded(child: Container()),
                            ],
                          ),
                          Image.asset(
                            "assets/images/mine_header.png",
                            fit: BoxFit.fitWidth,
                            width: 80,
                          ),
                          Container(
                            margin: EdgeInsets.only(top: 20),
                            child: Text(
                              store.state.userInfo.userName ?? "",
                              style: TextStyle(
                                  color: AJColors.white,
                                  fontSize: AJFont.textSize18),
                            ),
                          )
                        ],
                      )
                    ],
                  ),
                  _listWidget(title: "真实姓名", desc: store.state.userInfo.realName ?? ""),
                  _listWidget(title: "电话", desc: store.state.userInfo.mobile ?? ""),
                  _listWidget(title: "清除缓存", desc: "", onTap: () async {
                    widget.webController.future.then((v){
                      v.clearCache();
                    });
                    DialogUtil.showLoadingDialog(context, NavigatorUtils.clearScreenData()).then((v){
                      Code.errorHandle("筛选缓存已清除");
                    });
                  }),
                  _listWidget(title: "版本号", desc: _version),
                  Container(
                    padding: EdgeInsets.only(left: 58, right: 58, top: 30),
                    child: GradientContainer(
                      colors: [
                        AJColors.buttonNormalColor,
                        AJColors.buttonNormalColor,
                        AJColors.buttonNormalColor
                      ],
                      onTap: _logoutAction,
                      height: 40,
                      radius: 40,
                      child: Center(
                        child: Text(
                          isLogin ? "退出登录" : "登录",
                          style: AJConstant.loginWhiteStyle,
                        ),
                      ),
                    ),
                  )
                ],
              ),
              value: overlayStyle),
        ),
      );
    });
  }

  Widget _listWidget({String title, String desc, GestureTapCallback onTap}) {
    return AJListTitle(
      onTap: onTap,
      margin: EdgeInsets.all(0),
      padding: EdgeInsets.symmetric(vertical: 26),
      leading: Container(
        margin: EdgeInsets.only(left: 26),
        child: Text(
          title,
          style:
              TextStyle(color: Color(0xff696969), fontSize: AJFont.textSize13),
        ),
      ),
      trailing: onTap != null ? Container(margin: EdgeInsets.only(right: 20),child: Image.asset(AJICons.arrow_right, width: 7,),) : Container(
        margin: EdgeInsets.only(right: 14),
        child: Text(
          desc,
          style:
          TextStyle(color: Color(0xff333333), fontSize: AJFont.textSize13),
        ),
      ),
    );
  }

  //登录
  _logoutAction(){
    Future.delayed(Duration(milliseconds: 400)).then((_){
      Store<AJState> store = StoreProvider.of(context);
      UserDao.clearAll(store);
      NavigatorUtils.goLogin(context, result: true, needClear: true);
    });


  }


  @override
  void initState() {
    super.initState();
    _init();

  }

  _init()async{
    AjFlutterPlugin info = await AjFlutterPlugin.platformVersion();
    setState(() {
      _version = info.version;
    });

  }
}
