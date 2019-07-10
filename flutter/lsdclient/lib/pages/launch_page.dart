import 'dart:convert';

import 'package:connectivity/connectivity.dart';
import 'package:flutter/material.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/pages/guide_page.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:redux/redux.dart';
import 'package:rxdart/rxdart.dart';

class LaunchPage extends StatefulWidget {
  @override
  _LaunchPageState createState() => _LaunchPageState();
}

class _LaunchPageState extends State<LaunchPage> {



  @override
  void initState() {
    super.initState();
    _initAsync();
  }

  _initAsync(){
    Observable.just(1).delay(Duration(milliseconds: 2800)).listen((_)async{
      await (new Connectivity().checkConnectivity());

      var base_host = await LocalStorage.get(AJConfig.BASEHOST_LOCAL_STORAGE);
      var web_host = await LocalStorage.get(AJConfig.WEBHOST_LOCAL_STORAGE);
      print(base_host);
      print(web_host);
      if(base_host == null){
        await LocalStorage.save(AJConfig.BASEHOST_LOCAL_STORAGE, Address.host);
      }else{
        Address.host = base_host;
      }
      if(web_host == null){
        await LocalStorage.save(AJConfig.WEBHOST_LOCAL_STORAGE, Address.basewebHost);
      } else {
        Address.basewebHost = web_host;
      }

      double textScaleFactor = MediaQuery.of(context).textScaleFactor;
      AJConfig.textScaleFactor = textScaleFactor;
      ///防止多次进入
      Store<AJState> store = StoreProvider.of(context);

      UserDao.getFirstLaunch().then((isFirstLaunch){
        if (isFirstLaunch == null || isFirstLaunch == true){
          NavigatorUtils.pushNotAnimationTO(context, child: WelcomeWidget());
        } else {
          UserDao.initUserInfo(store).then((res) async{
            if (res != null && res.result) {
              NavigatorUtils.goHome(context);

            } else {
              NavigatorUtils.goLogin(context);
            }
          });
        }
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    final widthSrcreen = MediaQuery.of(context).size.width;
    final heightScreen = MediaQuery.of(context).size.height;
    return new Container(
      width: widthSrcreen,
      height: heightScreen,
      color: AJColors.white,
      child: new Center(
        child: Image.asset(
          AJICons.aj_welcome_gif,
          width: widthSrcreen,
          height: heightScreen,
        ),
      ),
    );
  }
}
