import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/common/dialog_utils.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:lsdclient/widgets/aj_input_widget.dart';

import 'aj_flex_button.dart';

class HostConfigPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return new _RegistPagerState();
  }
}

//用于使用到了一点点的动画效果，因此加入了SingleTickerProviderStateMixin
class _RegistPagerState extends State<HostConfigPage>
    with SingleTickerProviderStateMixin {
  final TextEditingController _basewebHostController =
      new TextEditingController();
  final TextEditingController _hostController = new TextEditingController();

  var _basewebHost = "";
  var _host = "";

  @override
  void initState() {
    super.initState();

    _init();
  }

  _init()async{
    var base_host = await LocalStorage.get(AJConfig.BASEHOST_LOCAL_STORAGE);
    var web_host = await LocalStorage.get(AJConfig.WEBHOST_LOCAL_STORAGE);
    if(base_host == null){
      base_host = Address.host;
    }

    if(web_host == null){
      web_host = Address.basewebHost;
    }

   setState(() {
     _host = base_host;
     _basewebHost = web_host;
     _hostController.value = new TextEditingValue(text: _host ?? "");
     _basewebHostController.value =
     new TextEditingValue(text: _basewebHost ?? "");
   });

  }

  @override
  void dispose() {
    _basewebHostController.dispose();
    _hostController.dispose();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
//      backgroundColor: AJColors.app_line,
      appBar: new AppBar(
        title: Text("服务配置"),
        actions: <Widget>[],
      ),
      body: ListView(
        children: <Widget>[
          Card(
            child: ListTile(
              contentPadding: EdgeInsets.all(10),
              title: Text("当前服务器地址: $_host"),
              subtitle: Text("当前网页地址: $_basewebHost \n抓包地址：${Address.findProxyHost} 是否开启抓包：${Address.needfindProxyHost}"),
            ),
          ),
          new Padding(padding: new EdgeInsets.all(10.0)),
          _childWidget("生产", "${Address.pro_host}",
              "${Address.pro_basewebHost}"),
          _childWidget(
              "预发", "${Address.pretest_host}", "${Address.pretest_basewebHost}"),
          _childWidget(
              "开发", "${Address.dev_host}", "${Address.dev_basewebHost}"),
          _childWidget(
              "测试", "${Address.test_host}", "${Address.test_basewebHost}"),
          _childWidget(
              "本地", "${Address.local_host}", "${Address.local_basewebHost}"),
          _childFindProxyWidget(),
          new Padding(padding: new EdgeInsets.all(10.0)),
          Container(
            color: AJColors.white,
            child: ExpansionTile(
                title: Text(
                  "添加新地址",
                  style: AJConstant.letterNormalButtonStyle,
                ),
                backgroundColor: Color(AJColors.primaryLightValue),
                children: <Widget>[
                  Card(
                    child: Column(
                      children: <Widget>[
                        new AJInputWidget(
                          hintText: "输入新的服务器地址",
                          onChanged: (value) {
                            _host = value;
                          },
                          controller: _hostController,
                          fontSize: AJFont.textSize14,
                        ),
                        new AJInputWidget(
                          hintText: "输入新的网页地址",
                          onChanged: (value) {
                            _basewebHost = value;
                          },
                          controller: _basewebHostController,
                          fontSize: AJFont.textSize14,
                        ),
                        new Padding(padding: new EdgeInsets.all(10.0)),
                        new AJFlexButton(
                          text: "使用",
                          color: AJColors.buttonNormalColor,
                          textColor: AJColors.white,
                          radius: 20.0,
                          onPress: () async {
                            if (_basewebHost.length > 0 && _host.length > 0) {
                              DialogUtil.dialogConfigApp(
                                  context, "确定要使用此地址？", confirmTap: () async{
                                Address.basewebHost = _basewebHost;
                                Address.host = _host;
                                await LocalStorage.save(AJConfig.BASEHOST_LOCAL_STORAGE, _host);
                                await LocalStorage.save(AJConfig.WEBHOST_LOCAL_STORAGE, _basewebHost);
                              });
                            } else if (_basewebHost.length > 0 &&
                                _host.length == 0) {
                              DialogUtil.dialogConfigApp(
                                  context, "确定要使用此地址？", confirmTap: () async{
                                Address.basewebHost = _basewebHost;
                                await LocalStorage.save(AJConfig.WEBHOST_LOCAL_STORAGE, _basewebHost);

                              });
                            } else if (_host.length > 0 &&
                                _basewebHost.length == 0) {
                              DialogUtil.dialogConfigApp(
                                  context, "确定要使用此地址？", confirmTap: ()async {
                                Address.host = _host;
                                await LocalStorage.save(AJConfig.BASEHOST_LOCAL_STORAGE, _host);
                              });
                            } else {
                              Code.errorHandleFunction(
                                  Code.TOASTFORCENTER, '如果需要新地址请输入对应地址', false);
                            }
                          },
                        ),
                        new Padding(padding: new EdgeInsets.all(10.0)),
                      ],
                    ),
                  )
                ]),
          ),
        ],
      ),
    );
  }

  Widget _childWidget(
      String environmentStr, String tpmBaseHost, String tpmBasewebHost) {
    var _tpmBaseHost = tpmBaseHost;
    var _tpmBasewebHost = tpmBasewebHost;
    return Container(
      margin: EdgeInsets.all(5),
      color: AJColors.white,
      child: ExpansionTile(
          title: Text(
            "环境： $environmentStr",
            style: AJConstant.letterNormalButtonStyle,
          ),
          backgroundColor: AJColors.app_line,
          children: <Widget>[
            Card(
              child: Column(
                children: <Widget>[
                  new Padding(padding: new EdgeInsets.all(10.0)),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Text(
                        "服务器地址：",
                        style: AJConstant.letterNormalButtonStyle,
                      ),
                      InkWell(
                        child: Container(
                          margin: EdgeInsets.only(right: 20),
                          padding: EdgeInsets.all(5.0),
                          width: 60,
                          decoration: new BoxDecoration(
                            color: AJColors.buttonNormalColor,
                            borderRadius:
                                new BorderRadius.all(new Radius.circular(30.0)),
                          ),
                          child: Text(
                            "使用",
                            style: AJConstant.loginWhiteStyle,
                            textAlign: TextAlign.center,
                          ),
                        ),
                        onTap: () async {
                          if (_tpmBaseHost.length == 0 ||
                              _tpmBasewebHost.length == 0) {
                            Code.errorHandleFunction(
                                Code.TOASTFORCENTER, '请输入完整地址', false);
                            return;
                          }
                          DialogUtil.dialogConfigApp(context, "确定要使用此地址？",confirmTap: ()async {
                            Address.host = _tpmBaseHost;
                            Address.basewebHost = _tpmBasewebHost;
                            await LocalStorage.save(AJConfig.BASEHOST_LOCAL_STORAGE, _tpmBaseHost);
                            await LocalStorage.save(AJConfig.WEBHOST_LOCAL_STORAGE, _tpmBasewebHost);
                          });
                        },
                      )
                    ],
                  ),
                  new AJInputWidget(
                    hintText: "输入新的服务器地址",
                    onChanged: (v) {
                      _tpmBaseHost = v;
                    },
                    controller: TextEditingController()
                      ..value = TextEditingValue(
                          text: _tpmBaseHost,
                          selection: TextSelection.fromPosition(TextPosition(
                              affinity: TextAffinity.downstream,
                              offset: _tpmBaseHost.length))),
                    fontSize: AJFont.textSize14,
                  ),
                  new Padding(padding: new EdgeInsets.all(10.0)),
                  Row(
                    children: <Widget>[
                      Text(
                        "网页地址：",
                        style: AJConstant.letterNormalButtonStyle,
                      )
                    ],
                  ),
                  new AJInputWidget(
                    hintText: "输入新的网页地址",
                    onChanged: (v) {
                      _tpmBasewebHost = v;
                    },
                    controller: TextEditingController()
                      ..value = TextEditingValue(
                        text: _tpmBasewebHost,
                          selection: TextSelection.fromPosition(TextPosition(affinity: TextAffinity.downstream, offset: _tpmBasewebHost.length))
                      ),
                    fontSize: AJFont.textSize14,
                  ),
                  new Padding(padding: new EdgeInsets.all(10.0)),
                ],
              ),
            )
          ]),
    );
  }


  Widget _childFindProxyWidget() {
    var _tpmBaseHost = Address.findProxyHost;

    return Container(
      margin: EdgeInsets.all(5),
      color: AJColors.white,
      child: ExpansionTile(
          title: Text(
            "抓包配置",
            style: AJConstant.letterNormalButtonStyle,
          ),
          backgroundColor: AJColors.app_line,
          children: <Widget>[
            Card(
              child: Column(
                children: <Widget>[
                  new Padding(padding: new EdgeInsets.all(10.0)),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: <Widget>[
                      Text(
                        "抓包地址：",
                        style: AJConstant.letterNormalButtonStyle,
                      ),
                      InkWell(
                        child: Container(
                          margin: EdgeInsets.only(right: 20),
                          padding: EdgeInsets.all(5.0),
                          width: 60,
                          decoration: new BoxDecoration(
                            color: AJColors.buttonNormalColor,
                            borderRadius:
                            new BorderRadius.all(new Radius.circular(30.0)),
                          ),
                          child: Text(
                            "使用",
                            style: AJConstant.loginWhiteStyle,
                            textAlign: TextAlign.center,
                          ),
                        ),
                        onTap: () async {
                          if (_tpmBaseHost.length == 0) {
                            Code.errorHandleFunction(
                                Code.TOASTFORCENTER, '请输入完整地址', false);
                            return;
                          }
                          DialogUtil.dialogConfigApp(context, "确定要使用此地址？",confirmTap: ()async {
                            Address.findProxyHost = _tpmBaseHost;
                            Address.needfindProxyHost = true;
                          });
                        },
                      )
                    ],
                  ),
                  new AJInputWidget(
                    hintText: "输入新的服务器地址",
                    onChanged: (v) {
                      _tpmBaseHost = v;
                    },
                    controller: TextEditingController()
                      ..value = TextEditingValue(
                          text: _tpmBaseHost,
                          selection: TextSelection.fromPosition(TextPosition(
                              affinity: TextAffinity.downstream,
                              offset: _tpmBaseHost.length))),
                    fontSize: AJFont.textSize14,
                  ),
                  new Padding(padding: new EdgeInsets.all(10.0)),
                ],
              ),
            )
          ]),
    );
  }


}
