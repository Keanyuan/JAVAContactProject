import 'package:flutter/material.dart';
import 'package:lsdclient/common/common_utils.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/widgets/aj_input_widget.dart';
import 'package:lsdclient/widgets/gradient_button.dart';


class ResetPasswordPage extends StatefulWidget {
  final String userId;
  ResetPasswordPage({this.userId});
  @override
  _ResetPasswordPageState createState() => _ResetPasswordPageState( );

}

class _ResetPasswordPageState extends State<ResetPasswordPage> {
  var _password = "";
  var _password1 = "";
  bool _obscure = true;


  final TextEditingController _pwController = new TextEditingController( );
  final TextEditingController _pwController1 = new TextEditingController( );

  @override
  void initState() {
    super.initState( );
    _pwController.value = new TextEditingValue( text: _password ?? "" );
    _pwController1.value = new TextEditingValue( text: _password1 ?? "" );
  }

  @override
  void dispose() {
    _pwController.dispose( );
    _pwController1.dispose( );
    super.dispose( );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        elevation: 1,
        title: Text( "修改密码" ),
      ),
      body: ListView(
        padding: EdgeInsets.only( left: 10.0, right: 10.0 ),
        children: <Widget>[
          Padding( padding: EdgeInsets.all( 20 ) ),
          AJInputWidget(
            hintText: "请输入新密码",
            obscureText: _obscure,
            prefixIcon: Icon(AJICons.LOGIN_PW),
//            suffix:Icon(Icons.remove_red_eye),
            suffix: Container(
              child: Center(
                child: RawMaterialButton(
                  onPressed: () {
                    setState(() {
                      _obscure = !_obscure;
                    });
                  },
                  materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
//                    fillColor: Colors.red,
                  constraints:
                  const BoxConstraints(minWidth: 0.0, minHeight: 30.0),
                  child: _obscure ? Icon(AJICons.PASSWORD_EYE_CLOSE) : Icon(AJICons.PASSWORD_EYE),
                ),
              ),
              width: 60,
              height: 30,
            ),
            enableInteractiveSelection: !_obscure,
            onChanged: (v){
              _password = v;
            },
            onSubmitted: (v) {
              _password = v;
            },
            controller: _pwController,
            fontSize: AJFont.textSize14,
          ),
          Padding( padding: EdgeInsets.all( 20 ) ),
          AJInputWidget(
            hintText: "请输入新密码",
            prefixIcon: Icon(AJICons.LOGIN_PW),
            suffix:Container(
              child: Center(
                child: RawMaterialButton(
                  onPressed: () {
                    setState(() {
                      _obscure = !_obscure;
                    });
                  },
                  materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
//                    fillColor: Colors.red,
                  constraints:
                  const BoxConstraints(minWidth: 0.0, minHeight: 30.0),
                  child: _obscure ? Icon(AJICons.PASSWORD_EYE_CLOSE) : Icon(AJICons.PASSWORD_EYE),
                ),
              ),
              width: 60,
              height: 30,
            ), //visibility_off
            obscureText: _obscure,
            enableInteractiveSelection: !_obscure,
            onChanged: (v){
              _password1 = v;
            },
            onSubmitted: (v) {
              _password1 = v;
            },
            controller: _pwController1,
            fontSize: AJFont.textSize14,
          ),
          Padding(
            padding: EdgeInsets.only(left: 20, right: 20, top: 10),
            child: Text("密码需由6-20位数字、英文两种组合", textAlign: TextAlign.center, style: AJConstant.changePasswordTextStyle,),
          ),
          Padding(
            padding: EdgeInsets.all(20),
            child:GradientContainer(
              colors: [Color(0xff0000ff), Colors.blueAccent, Colors.lightBlueAccent],
              onTap: _submitAction,
              height: 40,
              radius: 40,
              child: Center(child: Text(
                "提交",
                style: AJConstant.loginWhiteStyle,
              ),),
            ),
          ),
        ],
      ),
    );
  }

  _submitAction() {
    //隐藏键盘
    FocusScope.of(context).requestFocus(new FocusNode());

    if(!CommonUtils.isInnerEnglishAndNum(_password)
        || !CommonUtils.isInnerEnglishAndNum(_password1)
        || _password.length < 6
        || _password.length > 20
        || _password1.length < 6
        || _password1.length > 21
    ){
      Code.errorHandleFunction(Code.TOASTFORCENTER, '请按提示设置新密码', false);
      return;
    }
    if(_password != _password1){
      Code.errorHandleFunction(Code.TOASTFORCENTER, '两次密码输入不一致', false);
      return;
    }


//    CommonUtils.showLoadingDialog(context);
//    UserDao.getChangeNewPassword(widget.userId, _password.trim(), isNeedToken: false)
//        .then((res) {
//      Navigator.pop(context);
//      if (res != null && res.result) {
//        new Future.delayed(const Duration(milliseconds: 300), () {
//          LocalStorage.save(AJConfig.USER_NAME_KEY, "");
//          LocalStorage.save(AJConfig.PW_KEY, "");
//          CommonUtils.goLogin(context);
//          return true;
//        });
//      }
//    });

  }
}
