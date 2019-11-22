import 'dart:async';
import 'dart:convert';
import 'dart:io';

//import 'package:auto_orientation/auto_orientation.dart';
import 'package:flutter/foundation.dart';
import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:lsdclient/common/aj_mixin.dart';
import 'package:lsdclient/common/common_utils.dart';
import 'package:lsdclient/common/keyboard_state.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:lsdclient/widgets/loading_widget.dart';
import 'package:redux/redux.dart';
import 'package:lsdclient/pages/order/alarm_page.dart';
import 'package:rxdart/rxdart.dart';
import 'package:webview_flutter/webview_flutter.dart';

class WebPageControllera extends StatefulWidget {
  @override
  _WebPageControlleraState createState() => _WebPageControlleraState();
}

class _WebPageControlleraState extends KeyboardState<WebPageControllera>
    with AJMixin {
  
  String _url = "https://www.baidu.com";
  bool _loading = true;

  final Completer<WebViewController> _controller =
  Completer<WebViewController>();
  
  @override
  void initState() {
    super.initState();

  }
  @override
  Widget build(BuildContext context) {
    return WillPopScope(child: Scaffold(
      appBar: AppBar(
        title: Text(
          "数字运营中心",
          style: TextStyle(fontSize: 18, color: AJColors.black),
        ),
        leading: IconButton(
            icon: Image.asset(
              AJICons.ARROW_LEFT,
              width: 18,
            ),
            onPressed: _popPage),
        centerTitle: true,
        elevation: 0,
      ),
      body: SafeArea(child: _bodyWidget()),
    ), onWillPop: (){
      _popPage();
    });
  }


  _popPage() async{
    _controller.future.then((v)async{
      if(await v.canGoBack()){
        await v.goBack();
      } else {
        Navigator.pop(context);
      }
    });
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
                    initialUrl:_url,
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
          print(message.message);
          if (message.message == "search") {
            //筛选条件
            _controller.future.then((flutterWebViewPlugin) async {
              String _mapStr = await CommonUtils.getScreenStringMapInfo();
              flutterWebViewPlugin
                  .evaluateJavascript("window.Native.GetParams($_mapStr)");
            });
          } else if (message.message == "token") {
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
              }
            });
          } else if (message.message == "warningList") {
            //预警列表
            NavigatorUtils.pushTO(context, child: AlarmPage());
          } else if (message.message == "login") {
            Code.errorHandle("口令失败", code: Code.REQUEST_SHIBBOLETH);
            return;
          }
        });
  }

  @override
  void didUpdateWidget(WebPageControllera oldWidget) {
    // TODO: implement didUpdateWidget
    super.didUpdateWidget(oldWidget);
    print("----web---- oldWidget ---- > $oldWidget");
    print("----web---- didChangeDependencies ---- ");
    double screen_w = ScreenUtil.getScreenW(context);
    double screen_h = ScreenUtil.getScreenH(context);
    print("----web---- screen_w  ${screen_w}  screen_h: ${screen_h}");
  }

  @override
  void didChangeDependencies() {
    // TODO: implement didChangeDependencies
    super.didChangeDependencies();
    print(" ----web---- didChangeDependencies ----");
  }

  @override
  void deactivate() {
    super.deactivate();
    print("----web----  deactivate");
  }

  @override
  void dispose() {
    _controller.future.then((c){

    });
    super.dispose();
  }
}