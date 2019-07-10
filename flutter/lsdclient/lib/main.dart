import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/aj_crash_collect.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/models/User.dart';
import 'package:lsdclient/pages/home/home_page.dart';
import 'package:lsdclient/pages/login_page.dart';
import 'package:lsdclient/pages/launch_page.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/tools/aj_state.dart';
import 'package:lsdclient/tools/http_error_event.dart';
import 'package:lsdclient/tools/localizations/Localizations.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:redux/redux.dart';
import 'package:oktoast/oktoast.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:flutter_localizations/flutter_localizations.dart';

void main() {
  AJConfig.initConfig();
  AJConfig.initUmengConfig();

  AJCrashCollect.init((){
    runApp(new MyApp());
  });

}

class MyApp extends StatelessWidget {
  List<Locale> an = [
    const Locale('zh', 'CH'),
    const Locale('en', 'US'),
  ];
  List<Locale> ios = [
    const Locale('en', 'US'),
    const Locale('zh', 'CH'),
  ];

  final store = new Store<AJState>(appReducer,
      //初始化redux
      initialState: new AJState(
          userInfo: User.empty(),
          themeData: new ThemeData(
              primarySwatch: AJColors.primarySwatch,
              platform: TargetPlatform.iOS //滑动返回
          )));

  @override
  Widget build(BuildContext context) {
    //在Redux中最核心的自然是组件，以及组件相关的事件与数据流方式
    return OKToast(
      radius: 4.0,
      backgroundColor: Color.fromRGBO(0, 0, 0, 0.75),
      textPadding: EdgeInsets.all(10),
      child: new StoreProvider(
        store: store,
        child: new StoreBuilder<AJState>(
          builder: (context, store) {
            return new MaterialApp(
              debugShowCheckedModeBanner: false,
              supportedLocales: Platform.isIOS ? ios : an,
              localizationsDelegates: [
                GlobalMaterialLocalizations.delegate,
                GlobalWidgetsLocalizations.delegate,
                ChineseCupertinoLocalizations.delegate,
              ],
              theme: store.state.themeData,
              home: LaunchPage(),
              routes: {
                HomePage.sName: (context) {
                  ///通过 Localizations.override 包裹一层，
                  return new AJLocalizations(
                    child: new HomePage(),
                  );
                },
                LoginPage.sName: (context) {
                  return new AJLocalizations(
                    child: new LoginPage(),
                  );
                },
              },
            );
          },
        ),
      ),
//      backgroundColor: AJColors.app_normal_red_color,
    );
  }
}




class AJLocalizations extends StatefulWidget {
  final Widget child;

  AJLocalizations({Key key, this.child}) : super(key: key);

  @override
  State<AJLocalizations> createState() {
    return new _AJLocalizations();
  }
}

class _AJLocalizations extends State<AJLocalizations> {
  StreamSubscription stream;

  @override
  Widget build(BuildContext context) {
    //通过 StoreBuilder 和 Localizations 实现实时多语言切换  为多语言做准备
    return new StoreBuilder<AJState>(builder: (context, store) {
      return new Localizations.override(
        context: context,
        child: widget.child,
      );
    });
  }

  @override
  void initState() {
    super.initState();
    //eventBus监听
    stream = Code.eventBus.on<HttpErrorEvent>().listen((event) {
      errorHandleFunction(event.code, event.message);
    });
  }

  @override
  void dispose() {
    super.dispose();
    if (stream != null) {
      stream.cancel();
      stream = null;
    }
  }

  errorHandleFunction(int code, message) {
    switch (code) {
      case Code.NETWORK_ERROR: //网络错误 -1
        showToast("网络错误,请检查网络");
        break;
      case Code.REQUEST_SHIBBOLETH: //0104
        showToast(message ?? "用户口令已失效，请重新登录", position: ToastPosition.center);
        Store<AJState> store = StoreProvider.of(context);
        UserDao.clearAll(store);
        NavigatorUtils.goLogin(context, result: true);
        break;
      case Code.TOASTFORCENTER: //toast在中间 10001
        showToast(message, position: ToastPosition.center);
        break;
      default: //toast在下部 10000
        showToast(message, position: ToastPosition.center);
        break;
    }
  }
}
