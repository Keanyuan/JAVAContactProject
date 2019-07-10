import 'dart:async';
import 'dart:io';

import 'package:aj_flutter_auto_orientation/aj_flutter_auto_orientation.dart';
import 'package:aj_flutter_plugin/aj_flutter_plugin.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:lsdclient/common/aj_mixin.dart';
import 'package:lsdclient/common/common_utils.dart';
import 'package:lsdclient/common/keyboard_state.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/pages/home/screen_list_page.dart';
import 'package:lsdclient/pages/mine/mine_page.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/tools/app_update_utils.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:lsdclient/tools/object_utils.dart';
import 'package:lsdclient/widgets/loading_widget.dart';
import 'package:redux/redux.dart';
import 'package:lsdclient/pages/order/alarm_page.dart';
import 'package:webview_flutter/webview_flutter.dart';
import 'package:version/version.dart';

class HomePage extends StatefulWidget {
  static final String sName = "home";

  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends KeyboardState<HomePage> with AJMixin {
  String _bodyStr = "";
  String _url;
  final Completer<WebViewController> _controller =
      Completer<WebViewController>();
  bool _loading = true;
  int _firstRequestTime = 0;

  String _title = "安吉掌运";
  bool _needBackButton = false;
  bool _isLandscapeMode = false;

  @override
  void initState() {
    super.initState();
    _init();
    _url = "${Address.basewebHost}/";
    print(_url);
    new Future.delayed(new Duration(milliseconds: 2000), () {
      AppUpdateUtils.updateApp(context);
    });
  }

  _init() async {
    SystemChrome.setPreferredOrientations([
      DeviceOrientation.portraitUp,
    ]);
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
  }

  _setVersion(flutterWebViewPlugin) async {
    AjFlutterPlugin info = await AjFlutterPlugin.platformVersion();
    Version version = Version.parse(info.version);
    print(info.buildNumber);
    flutterWebViewPlugin.evaluateJavascript(
        "window.Native.GetVersion('${version.major}${version.minor}${version.patch}')");
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
      child: Scaffold(
        appBar: PreferredSize(
          child: AppBar(
            brightness: Brightness.light,
            leading: _needBackButton
                ? IconButton(
                    icon: Image.asset(
                      AJICons.ARROW_LEFT,
                      width: 18,
                    ),
                    onPressed: _popPage)
                : InkWell(
                    onTap: () {
                      NavigatorUtils.pushTO(context,
                          child: MinePage(
                            webController: _controller,
                          ));
                    },
                    child: Container(
                      height: 40,
                      width: 40,
                      alignment: Alignment.center,
                      child: ClipRRect(
                        borderRadius: BorderRadius.all(Radius.circular(40)),
                        child: Image.asset(
                          "assets/images/mine_header.png",
                          width: 40,
                        ),
                      ),
                    ),
                  ),
//            Row(
//                    children: <Widget>[
//                      Container(width: 10),
//                      InkWell(
//                        onTap: () {
//                          NavigatorUtils.pushTO(context, child: MinePage());
//                        },
//                        child: Container(
//                          height: 40,
//                          width: 40,
//                          alignment: Alignment.center,
//                          child: ClipRRect(
//                            borderRadius: BorderRadius.all(Radius.circular(40)),
//                            child: Image.asset(
//                              "assets/images/mine_header.png",
//                              width: 40,
//                            ),
//                          ),
//                        ),
//                      ),
//                    ],
//                  ),
            elevation: 1.0,
            title: Text(
              _title,
              style: TextStyle(fontSize: 18, color: AJColors.black),
            ),
            backgroundColor: AJColors.white,
            centerTitle: true,
            actions: <Widget>[
              _needBackButton
                  ? Container()
                  : InkWell(
                      onTap: () async {
                        NavigatorUtils.pushTO(context, child: ScreenListPage())
                            .then((v) async {
                          if (v != null) {
                            _controller.future
                                .then((flutterWebViewPlugin) async {
                              String _mapStr =
                                  await CommonUtils.getScreenStringMapInfo();
                              flutterWebViewPlugin.evaluateJavascript(
                                  "window.Native.GetParams($_mapStr)");
                            });
                          }
                        });
                      },
                      child: Container(
                        height: 40,
                        width: 60,
                        alignment: Alignment.center,
                        child: Image.asset(
                          AJICons.screening,
                          width: 24,
                        ),
                      ),
                    )
            ],
          ),
          preferredSize: Size.fromHeight(kToolbarHeight),
        ),
        body: SafeArea(child: _bodyWidget()),
      ),
      onWillPop: () {
        if (_needBackButton) {
          _popPage();
        } else {
          backTodesktop();
        }
      },
    );
  }

  _bodyWidget() {
    double screen_w = ScreenUtil.getScreenW(context);
    double screen_h = ScreenUtil.getScreenH(context);
    double bottom = MediaQuery.of(context).padding.bottom;
    double top = MediaQuery.of(context).padding.top;
    double content_h = screen_h - top - kToolbarHeight - bottom;
    return Stack(
      children: <Widget>[
        ListView(
          shrinkWrap: true,
          physics: NeverScrollableScrollPhysics(),
          children: <Widget>[
            Container(
              width: screen_w,
              height: content_h,
              child: AJStackLoading(
                  child: WebView(
                    initialUrl: _url,
                    javascriptMode: JavascriptMode.unrestricted,
//            withLocalUrl: false,
                    gestureRecognizers: <Factory<OneSequenceGestureRecognizer>>[
                      Factory<OneSequenceGestureRecognizer>(
                          () => EagerGestureRecognizer())
                    ].toSet(),
                    onWebViewCreated: (WebViewController webViewController) {
                      _controller.complete(webViewController);
                    },
                    javascriptChannels: <JavascriptChannel>[
                      _toasterJavascriptChannel(context),
                    ].toSet(),
                    navigationDelegate: (NavigationRequest request) {
                      if (Platform.isIOS) {
                        _javascriptModeH5ToNative(request.url);
                      }
                      return NavigationDecision.navigate;
                    },
                    onPageFinished: (String url) {
                      setState(() {
                        _loading = false;
                      });

                      _pushLaunchImage01();

                      if (Platform.isAndroid) {
                        _javascriptModeH5ToNative(url);
                      }
                    },
                  ),
                  isLoading: _loading),
            )
          ],
        ),
      ],
    );
  }

  _javascriptModeH5ToNative(String url) async {
    AJLogUtil.v(url);
  }

  JavascriptChannel _toasterJavascriptChannel(BuildContext context) {
    return JavascriptChannel(
        name: 'NativeJavascriptChannel',
        onMessageReceived: (JavascriptMessage message) {
          AJLogUtil.v(message.message);
          if (message.message == "search") { //TODO 索取筛选信息
            //筛选条件
            _controller.future.then((flutterWebViewPlugin) async {
              String _mapStr = await CommonUtils.getScreenStringMapInfo();
              flutterWebViewPlugin
                  .evaluateJavascript("window.Native.GetParams($_mapStr)");
            });
          } else if (message.message == "token") { //TODO 获取token
            //获取token
            _controller.future.then((flutterWebViewPlugin) async {
              Store<AJState> store = StoreProvider.of(context);
              var _token = store.state.userInfo.token;
              if (_token == null) {
                Code.errorHandle("口令失败", code: Code.REQUEST_SHIBBOLETH);
                return;
              } else {
                flutterWebViewPlugin
                    .evaluateJavascript("window.Native.token('$_token')");
                _setVersion(flutterWebViewPlugin);
              }
            });
          } else if (message.message == "warningList") { //TODO 预警列表
            //预警列表
            NavigatorUtils.pushTO(context, child: AlarmPage());
          } else if (message.message == "login") { //TODO 口令失败跳转登录
            Code.errorHandle("口令失败", code: Code.REQUEST_SHIBBOLETH);
            return;
          } else if (message.message.contains("chartsList")) { //TODO 图表列表
            _gotoChartsDetail(message: message, isLandscapeMode: false);
          } else if (message.message.contains("chartsDetail")) {  //TODO 图表横屏
            _gotoChartsDetail(message: message, isLandscapeMode: true);
          } else if (message.message.contains("tapMiddleAction")) {// TODO 点击中间按钮
            CommonUtils.launchImage("assets/images/home_02.webp", "home_page_home_02", context);
          } else if (message.message.contains("getScreenInfo")) { // TODO 获取筛选信息
            NavigatorUtils.pushTO(context, child: ScreenListPage())
                .then((v) async {
              if (v != null) {
                _controller.future.then((flutterWebViewPlugin) async {
                  String _mapStr = await CommonUtils.getScreenStringMapInfo();
                  flutterWebViewPlugin
                      .evaluateJavascript("window.Native.GetParams($_mapStr)");
                });
              }
            });
          } else if (message.message.contains("getTitle")) { // TODO 获取筛选信息
            List<String> u1 = message.message.split(",");
            if(u1.length > 0){
              if(!mounted)return;
              setState(() {
                _title = u1[1] ?? "安吉掌运";
              });
            }
          }
        });
  }

  _gotoChartsDetail(
      {JavascriptMessage message, bool isLandscapeMode = false}) async {
    _isLandscapeMode = isLandscapeMode;
    // isLandscapeMode 是否强制横屏
    AJLogUtil.v("message.message ${message.message}");
    List<String> u1 = message.message.split(",");
    AJLogUtil.v(u1);
    AJLogUtil.v(u1.length);

    double _ws = ScreenUtil.getScreenW(context);
    double _hs = ScreenUtil.getScreenH(context);

    bool isPortraitUp = _ws < _hs;

    if (!ObjectUtils.isListEmpty(u1)) {
      if (u1.length >= 2) {
        _title = u1.length > 2 ? u1[2] : "安吉掌运";
        print("gg");

        if (isLandscapeMode) {
          if(!mounted)return;
          setState(() {
            _needBackButton = true;
          });
          SystemChrome.setPreferredOrientations([
            DeviceOrientation.landscapeRight,
            DeviceOrientation.landscapeLeft,
          ]);
          await AjFlutterAutoOrientation.landscapeRightMode();
        } else {
          if(!mounted)return;
          setState(() {
            _needBackButton = true;
          });
        }

        if(_title == "预警类型分布"){
          CommonUtils.launchImage("assets/images/home_03.webp", "home_page_home_03", context);
        }

//        if(Platform.isIOS){
//          if(isLandscapeMode){
//            SystemChrome.setPreferredOrientations([
//              DeviceOrientation.landscapeRight,
//            ]);
//            await AjFlutterAutoOrientation.landscapeRightMode();
//            Observable.just(1).delay(Duration(milliseconds: 200)).listen((_)async{
//              _controller.future.then((vc) {
//                vc.loadUrl("${Address.basewebHost}${u1[1]}").then((v) {
//                  setState(() {
//                    _needBackButton = true;
//                  });
//                });
//              });
//            });
//          } else {
//            _controller.future.then((vc) {
//              vc.loadUrl("${Address.basewebHost}${u1[1]}").then((v) {
//                setState(() {
//                  _needBackButton = true;
//                });
//              });
//            });
//          }
//        } else {
//          NavigatorUtils.pushTO(context,
//              child: WebPageController(
//                url: "${Address.basewebHost}${u1[1]}",
//                title: u1.length > 2 ? u1[2] : null,
//                isPortraitUp: isPortraitUp,
//                isLandscapeMode: isLandscapeMode,
//              )).then((re){
//            if(re){
//              Observable.just(1).delay(Duration(milliseconds: 200)).listen((_)async{
//                SystemChrome.setPreferredOrientations([
//                  DeviceOrientation.portraitUp,
//                ]);
//                await AjFlutterAutoOrientation.portraitUpMode();
//              });
//            }
//          });
//        }

      }
    } else {
      Code.errorHandle("信息有误");
    }
  }

  _popPage() {
    _title = "数字运营中心";
    _controller.future.then((vc) async {
      if (_needBackButton) {
        vc.evaluateJavascript("window.Native.GoBack()").then((v) {
          if(!mounted)return;
          setState(() {
            _isLandscapeMode = false;
            _needBackButton = false;
          });
        });
        if (_isLandscapeMode) {
          SystemChrome.setPreferredOrientations([
            DeviceOrientation.portraitUp,
          ]);
          await AjFlutterAutoOrientation.portraitUpMode();
        } else {
          vc.evaluateJavascript("window.Native.GoBack()").then((v) {
            if(!mounted)return;
            setState(() {
              _isLandscapeMode = false;
              _needBackButton = false;
            });
          });
        }
      } else {
        if(!mounted)return;
        setState(() {
          _needBackButton = false;
        });
      }
    });
  }

  int firstRequestTime1 = 0;
  bool isLaunchFirst = true;

  void _pushLaunchImage01() async {
    int secondTime = new DateTime.now().millisecondsSinceEpoch;
    if (secondTime - firstRequestTime1 > 2000) {
      firstRequestTime1 = secondTime;
      if (isLaunchFirst) {
        if(!mounted)return;
        setState(() {
          isLaunchFirst = false;
        });
        CommonUtils.launchImage("assets/images/home_01.webp", "home_page_home_01", context);
      }
    }
  }

}
