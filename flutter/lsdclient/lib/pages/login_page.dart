import 'dart:async';

import 'package:aj_flutter_plugin/aj_flutter_plugin.dart';
import 'package:aj_flutter_update/aj_flutter_update.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_redux/flutter_redux.dart';
import 'package:lsdclient/common/aj_mixin.dart';
import 'package:lsdclient/common/dialog_utils.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/tools/app_update_utils.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:lsdclient/tools/object_utils.dart';
import 'package:lsdclient/widgets/aj_input_widget.dart';
import 'package:lsdclient/widgets/gradient_button.dart';
import 'package:redux/redux.dart';
import 'package:lsdclient/pages/login/host_config/host_config_page.dart';

class LoginPage extends StatefulWidget {
  static final String sName = "login";

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage>
    with AJMixin, AjFlutterUpdateMixin {
  ///是否是用户名登录
  bool isUserLogin = false;
  String _verificationCodeToken = "";
  String _userName = "";
  String _password = "";
  String _phoneNum = "";
  String _verificationCode = "";
  bool _codeVerifiEnable = true;
  Timer _countdownTimer;
  String _codeCountdownStr = '获取动态密码';
  int _countdownNum = 59;

  FocusNode _loginFocusNode = new FocusNode();
  FocusNode _passwordFocusNode = new FocusNode();
  FocusNode _phoneNumFocusNode = new FocusNode();
  FocusNode _verificationCodeFocusNode = new FocusNode();

  final TextEditingController _userController = new TextEditingController();
  final TextEditingController _pwController = new TextEditingController();
  final TextEditingController _phoneNumController = new TextEditingController();
  final TextEditingController _verificationCodeController =
      new TextEditingController();
  bool _obscure = true;

  @override
  void initState() {
    super.initState();
    initParams();
    new Future.delayed(new Duration(milliseconds: 2000), () {
      AppUpdateUtils.updateApp(context);
    });
  }

  initParams() async {
    _userController.value = new TextEditingValue(text: _userName ?? "");
    _pwController.value = new TextEditingValue(text: _password ?? "");
  }

  @override
  Widget build(BuildContext context) {
    final heightScreen = ScreenUtil.getScreenW(context);
    final widthSrcreen = ScreenUtil.getScreenW(context);
    final SystemUiOverlayStyle overlayStyle = SystemUiOverlayStyle.dark;
    return Scaffold(
      backgroundColor: AJColors.white,
      body: Semantics(
        container: true,
        child: AnnotatedRegion<SystemUiOverlayStyle>(
            child:  new GestureDetector(
              //冲击测试行为: 半透明的
              behavior: HitTestBehavior.translucent,
              onTap: () {
                //隐藏键盘
                FocusScope.of(context).requestFocus(new FocusNode());
              },
              child: WillPopScope(
                child: buildPageWidget(
                    context, widthSrcreen, heightScreen, heightScreen, widthSrcreen),
                onWillPop: () {
                  backTodesktop();
                },
              ),
            ),
            value: overlayStyle),
      ),
    );

  }

  Widget buildPageWidget(context, widthSrcreen, heightScreen, h1, h2) {
    return new CustomScrollView(
      slivers: <Widget>[
        SliverList(
          delegate: SliverChildListDelegate([
            Stack(
              children: <Widget>[
                Container(
                  width: ScreenUtil.getScreenW(context),
                  height: ScreenUtil.getScreenH(context) * 0.4,
                  alignment: Alignment.center,
                  child: Image.asset(
                    AJICons.DEFAULT_USER_BG_ICON,
                    width: 120,
                  ),
                ),
                Positioned(
                    right: 0,
                    top: 30,
                    child: InkWell(
                      splashColor: AJColors.transparent,
                      highlightColor: AJColors.transparent,
                      child: Container(
                        height: 80.0,
                        width: 100.0,
                        color: AJColors.transparent,
                      ),
                      onLongPress: () {
                        NavigatorUtils.pushTO(context, child: HostConfigPage());
                      },
                    )),
              ],
            ),
            _switchTextField(),
            new Padding(
              padding: new EdgeInsets.only(left: 20.0, right: 20.0, top: 20.0),
              child: GradientContainer(
                colors: [
                  AJColors.buttonNormalColor,
                  AJColors.buttonNormalColor,
                  AJColors.buttonNormalColor
                ],
                onTap: isUserLogin ? _logoin : _phoneLogin,
                height: 40,
                radius: 40,
                child: Center(
                  child: Text(
                    "登录",
                    style: AJConstant.loginWhiteStyle,
                  ),
                ),
              ),
            ),
            Padding(
              padding: EdgeInsets.only(left: 20.0, right: 20.0, top: 20.0),
              child: RawMaterialButton(
                onPressed: _switchLogin,
                materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
//                    fillColor: Colors.red,
                constraints:
                    const BoxConstraints(minWidth: 0.0, minHeight: 30.0),
                child: new Text(
                  isUserLogin ? "手机号快捷登录" : "用户名和密码登录",
                  style: TextStyle(color: Color(0xFF919191), fontSize: 13),
                ),
              ),
            ),
          ]),
        ),
      ],
    );
  }

  Widget _switchTextField() {
    ///用户名登录
    if (isUserLogin) {
      return Column(
        children: <Widget>[
          new AJInputWidget(
            margin: EdgeInsets.symmetric(horizontal: 20.0),
            hintText: "请输入用户名",
            textInputAction: TextInputAction.next,
            focusNode: _loginFocusNode,
            isUnderline: true,
            onSubmitted: (value) {
              _loginFocusNode.unfocus();
              FocusScope.of(context).requestFocus(_passwordFocusNode);
            },
            onChanged: (String value) {
              _userName = value;
            },
            controller: _userController,
            fontSize: AJFont.textSize16,
          ),
          new Padding(padding: new EdgeInsets.all(10.0)),
          new AJInputWidget(
            isUnderline: true,
            margin: EdgeInsets.symmetric(horizontal: 20.0),
            hintText: "请输入密码",
            obscureText: _obscure,
            suffix: Container(
              margin: EdgeInsets.only(right: 20),
              child: Center(
                child: RawMaterialButton(
                  splashColor: AJColors.transparent,
                  highlightColor: AJColors.transparent,
                  onPressed: () {
                    setState(() {
                      _obscure = !_obscure;
                    });
                  },
                  materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
                  constraints:
                      const BoxConstraints(minWidth: 0.0, minHeight: 30.0),
                  child: _obscure
                      ? Icon(AJICons.PASSWORD_EYE_CLOSE)
                      : Icon(AJICons.PASSWORD_EYE),
                ),
              ),
              width: 60,
              height: 30,
            ),
            enableInteractiveSelection: !_obscure,
            textInputAction: TextInputAction.done,
            focusNode: _passwordFocusNode,
            onSubmitted: (value) {
              FocusScope.of(context).requestFocus(new FocusNode());
              _logoin();
            },
            onChanged: (String value) {
              _password = value;
            },
            controller: _pwController,
            fontSize: AJFont.textSize16,
          ),
        ],
      );
    }

    ///手机登录
    return Column(
      children: <Widget>[
        new AJInputWidget(
          margin: EdgeInsets.symmetric(
            horizontal: 20.0,
          ),
          hintText: "请输入手机号",
//              iconData: AJICons.LOGIN_USER,
          focusNode: _phoneNumFocusNode,
          textInputAction: TextInputAction.next,
          isUnderline: true,
          onSubmitted: (value) {
            _phoneNumFocusNode.unfocus();
            FocusScope.of(context).requestFocus(_verificationCodeFocusNode);
          },
          onChanged: (String value) {
            _phoneNum = value;
          },
          controller: _phoneNumController,
          fontSize: AJFont.textSize16,
        ),
        new Padding(padding: new EdgeInsets.all(10.0)),
        new AJInputWidget(
          margin: EdgeInsets.symmetric(horizontal: 20.0),
          isUnderline: true,
          hintText: "请输入动态密码",
          suffix: RawMaterialButton(
            onPressed: _codeVerifiEnable ? _reGetCountdown : null,
            child: Container(
              child: Center(
                child: new Text(
                  _codeCountdownStr,
                  style: TextStyle(
                      color: _codeVerifiEnable
                          ? AJColors.black
                          : AJColors.app_line,
                      decoration: TextDecoration.underline),
                ),
              ),
              width: 120,
              height: 30,
            ),
          ),
          textInputAction: TextInputAction.done,
          focusNode: _verificationCodeFocusNode,
          onSubmitted: (value) {
            FocusScope.of(context).requestFocus(new FocusNode());
            _phoneLogin();
          },
          onChanged: (String value) {
            _verificationCode = value;
          },
          controller: _verificationCodeController,
          fontSize: AJFont.textSize16,
        ),
      ],
    );
  }

  ///手机号登录
  _phoneLogin() {
    Store<AJState> store = StoreProvider.of(context);
    if (ObjectUtils.isEmpty(_phoneNum)) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入手机号', false);
      return;
    }

    if (!RegexUtil.isMobileSimple(_phoneNum)) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入正确的手机号', false);
      return;
    }

    if (_verificationCode == null || _verificationCode.length == 0) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入验证码', false);
      return;
    }

    DialogUtil.showLoadingDialog(
            context,
            UserDao.getUserMobileLogin(
                _phoneNum, _verificationCode, _verificationCodeToken, store))
        .then((res) {
      if (res != null && res.result) {
        NavigatorUtils.goHome(context);
      }
    });
  }

  ///用户名登录
  _logoin() {
    Store<AJState> store = StoreProvider.of(context);

    if (_userName == null || _userName.length == 0) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入用户名', false);
      return;
    }
    if (_password == null || _password.length == 0) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入密码', false);
      return;
    }
    DialogUtil.showLoadingDialog(
            context, UserDao.getUserLogin(_userName, _password, store))
        .then((res) {
      if (res != null && res.result) {
        NavigatorUtils.goHome(context);
      }
    });
  }

  ///切换登录
  _switchLogin() {
    FocusScope.of(context).requestFocus(new FocusNode());
    setState(() {
      _userName = "";
      _password = "";
      _phoneNum = "";
      _verificationCode = "";
      _userController.value = new TextEditingValue(text: _userName ?? "");
      _pwController.value = new TextEditingValue(text: _password ?? "");
      _phoneNumController.value = new TextEditingValue(text: _phoneNum ?? "");
      _verificationCodeController.value =
          new TextEditingValue(text: _verificationCode ?? "");
      isUserLogin = !isUserLogin;
    });
  }

  ///获取动态验证码
  void _reGetCountdown() {
    if (ObjectUtils.isEmpty(_phoneNum)) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入手机号', false);
      return;
    }
    if (!RegexUtil.isMobileSimple(_phoneNum)) {
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入正确的手机号', false);
      return;
    }
    if (_countdownTimer != null) {
      return;
    }

    DialogUtil.showLoadingDialog(context, UserDao.getSendingSms(_phoneNum))
        .then((res) {
      if (res != null && res.result) {
        AJLogUtil.v("res.data ---- ${res.data}");

        _verificationCodeToken = res.data["verificationCodeToken"];
        AJLogUtil.v(res.data["verificationCodeToken"]);

        _setStateTimer();
      }
    });
  }

  _setStateTimer() {
    setState(() {
      // Timer的第一秒倒计时是有一点延迟的，为了立刻显示效果可以添加下一行。
      _codeCountdownStr = '${_countdownNum--}秒后重新获取';
      _countdownTimer = new Timer.periodic(new Duration(seconds: 1), (timer) {
        if (_countdownNum > 0) {
          _codeVerifiEnable = false;
          _codeCountdownStr = '${_countdownNum--}秒后重新获取';
        } else {
          _codeVerifiEnable = true;
          _codeCountdownStr = '获取动态密码';
          _countdownNum = 59;
          _countdownTimer.cancel();
          _countdownTimer = null;
        }
        setState(() {});
      });
    });
  }

  @override
  void dispose() {
    _userController.dispose();
    _pwController.dispose();
    _verificationCodeController.dispose();
    _phoneNumController.dispose();
    _countdownTimer?.cancel();
    _countdownTimer = null;
    super.dispose();
  }
}
