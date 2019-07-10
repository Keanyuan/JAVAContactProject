import 'dart:async';

import 'package:flutter/material.dart';
import 'package:lsdclient/common/common_utils.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/pages/login/forget_ps_page/reset_password_page.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:lsdclient/widgets/aj_input_widget.dart';
import 'package:lsdclient/widgets/gradient_button.dart';



class ForgetPasswordPage extends StatefulWidget {
  final int index;

  ForgetPasswordPage(this.index);

  @override
  State<StatefulWidget> createState() {
    return new _ForgetPasswordPageState();
  }
}

//用于使用到了一点点的动画效果，因此加入了SingleTickerProviderStateMixin
class _ForgetPasswordPageState extends State<ForgetPasswordPage>
    with SingleTickerProviderStateMixin, AutomaticKeepAliveClientMixin {
  var _num = "";
  var _password = "";
  var _errorEmailNumText = null;

  bool _codeVerifiEnable = true;
  Timer _countdownTimer;
  String _codeCountdownStr = '获取验证码';
  int _countdownNum = 59;


  _ForgetPasswordPageState() : super();

  final TextEditingController _numController = new TextEditingController();
  final TextEditingController _pwController = new TextEditingController();

  @override
  void initState() {
    super.initState();
    initParams();
  }

  initParams() async {
    _numController.value = new TextEditingValue(text: _num ?? "");
    _pwController.value = new TextEditingValue(text: _password ?? "");
  }

  _focusScopeCheck() {
    if (_num.length != 0) {
      if (!CommonUtils.isEmail(_num)) {
        _errorEmailNumText = "邮箱格式有误";
      } else {
        _errorEmailNumText = null;
      }
    } else {
      _errorEmailNumText = null;
    }
    setState(() {});
  }

  _focusScopeCheckPhoneNum(){
    if (_num.length != 0) {
      if (!RegexUtil.isMobileSimple(_num)) {
        _errorEmailNumText = "手机格式有误";
      } else {
        _errorEmailNumText = null;
      }
    } else {
      _errorEmailNumText = null;
    }
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    final heightScreen = MediaQuery.of(context).size.height;
    return Scaffold(
      backgroundColor: AJColors.white,
      body: GestureDetector(
        onTap: () {
          FocusScope.of(context).requestFocus(new FocusNode());
          if( widget.index == 0)_focusScopeCheck();
          else _focusScopeCheckPhoneNum;
        },
        child: Container(
          color: AJColors.white,
          height: heightScreen,
          margin: EdgeInsets.only(left: 10.0, right: 10.0),
          child: ListView(
            children: <Widget>[
              new SizedBox(height: 30.0),
//              new Image(image: new AssetImage(AJICons.DEFAULT_REGISTER_ICON), height: 60.0,),
//              new Padding(padding: new EdgeInsets.all(10.0)),
              new AJInputWidget(
                hintText: widget.index == 0 ? "请输入邮箱" : "请输入手机号",
                errorText: _errorEmailNumText,
//                iconData: AJICons.REGIST_EMAIL,
                onChanged: (String value) {
                  _num = value;
                  if(widget.index == 0) _focusScopeCheck();
                  else _focusScopeCheckPhoneNum();

                },
                onSubmitted: (v) {
                  if(widget.index == 0) _focusScopeCheck();
                  else _focusScopeCheckPhoneNum();
                  },
                controller: _numController,
                fontSize: AJFont.textSize14,
              ),
              new Padding(padding: new EdgeInsets.all(10.0)),
              Row(
//              mainAxisAlignment: MainAxisAlignment.start,
                children: <Widget>[
                  Expanded(
                      flex: 2,
                      child: new AJInputWidget(
                        margin: const EdgeInsets.only(left: 20.0, right: 5.0),
                        hintText: widget.index == 0 ? "请输入邮箱验证码" : "请输入手机验证码",
//                    iconData: AJICons.LOGIN_PW,
                        onChanged: (String value) {
                          _password = value;
                        },
                        onSubmitted: (value) {
                          FocusScope.of(context).requestFocus(new FocusNode());
                        },
                        controller: _pwController,
                        fontSize: AJFont.textSize14,
                      )),
                  Container(
                    width: 110,
                    child: OutlineButton(
                      textColor: AJColors.subTextColor,
                      disabledBorderColor: AJColors.app_line,
                      color: AJColors.subTextColor,
                      disabledTextColor: AJColors.app_line,
                      child: Text(_codeCountdownStr, style: TextStyle(fontSize: 14)),
                      shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(20.0)),
                      onPressed: _codeVerifiEnable ? _reGetCountdown : null,
                    ),
                  ),
                  Container(width: 20,)
                ],
              ),
              new Padding(padding: new EdgeInsets.all(30.0)),
              GradientContainer(
                colors: [Color(0xff0000ff), Colors.blueAccent, Colors.lightBlueAccent],
                onTap: _nextAction,
                height: 40,
                radius: 40,
                child: Center(child: Text(
                  "下一步",
                  style: AJConstant.loginWhiteStyle,
                ),),
              ),
            ],
          ),
        ),
      ),
    );
  }



  ///下一步
  _nextAction(){

    if(widget.index == 1){
      if(!RegexUtil.isMobileSimple(_num)){
        Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入手机号', false);
        return;
      }
    } else {
      if(!RegexUtil.isEmail(_num)){
        Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入正确邮箱', false);
        return;
      }
    }

    if(_password.length == 0){
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入验证码', false);

      return;
    }

    if(widget.index == 1){
      NavigatorUtils.pushTO(context, child: ResetPasswordPage());

//      CommonUtils.showLoadingDialog(context);
//      UserDao.getCheckSmsVerificationCode(phone: _num.trim(), islogin: false, inputCode: _password.trim())
//          .then((res) {
//        Navigator.pop(context);
//        if (res != null && res.result) {
//          User user = res.data;
//          //TODO 数据请求下一步
//          NavigatorUtils.gotoResetPasswordPage(context, "${user.userId}");
//        }
//      });
    } else {
      NavigatorUtils.pushTO(context, child: ResetPasswordPage());
//      CommonUtils.showLoadingDialog(context);
//      UserDao.getCheckEmailVerificationCode(emailAddress: _num.trim(), inputCode: _password.trim())
//          .then((res) {
//        Navigator.pop(context);
//        if (res != null && res.result) {
//          User user = res.data;
//          //TODO 数据请求下一步
//          NavigatorUtils.gotoResetPasswordPage(context, "${user.userId}");
//        }
//      });
    }


  }

  ///获取动态验证码
  void _reGetCountdown() {

    if(widget.index == 1){
      if(!RegexUtil.isMobileSimple(_num)){
        Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入手机号', false);
        return;
      }
    } else {
      if(!RegexUtil.isEmail(_num)){
        Code.errorHandleFunction(Code.TOASTFORCENTER, '请输入正确邮箱', false);
        return;
      }
    }

    if (_countdownTimer != null) {
      return;
    }
    _setStateTimer();


    if(widget.index == 1){
      //TODO 操作获取验证码
//      CommonUtils.showLoadingDialog(context);
//      UserDao.getSendSmsVerificationCode(phone: _num, islogin: false)
//          .then((res) {
//        Navigator.pop(context);
//        if (res != null && res.result) {
//          _setStateTimer();
//        }
//      });
    } else {
//      CommonUtils.showLoadingDialog(context);
//      UserDao.getSendEmailVerificationCode(emailAddress: _num)
//          .then((res) {
//        Navigator.pop(context);
//        if (res != null && res.result) {
//          _setStateTimer();
//        }
//      });
    }
    
  }
  
  _setStateTimer(){
    setState(() {
      // Timer的第一秒倒计时是有一点延迟的，为了立刻显示效果可以添加下一行。
      _codeCountdownStr = '${_countdownNum--}重新获取';
      _countdownTimer =
      new Timer.periodic(new Duration(seconds: 1), (timer) {
        setState(() {
          if (_countdownNum > 0) {
            _codeVerifiEnable = false;
            _codeCountdownStr = '${_countdownNum--}重新获取';
          } else {
            _codeVerifiEnable = true;
            _codeCountdownStr = '获取验证码';
            _countdownNum = 59;
            _countdownTimer.cancel();
            _countdownTimer = null;
          }
        });
      });
    });
  }


  @override
  void dispose() {
    _numController.dispose();
    _pwController.dispose();
    _countdownTimer?.cancel();
    _countdownTimer = null;
    super.dispose();
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;

}
