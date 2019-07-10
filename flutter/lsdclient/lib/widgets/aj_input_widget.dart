import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class AJInputWidget extends StatefulWidget {
  static const defaultMargin = const EdgeInsets.only( left: 20.0, right: 20.0 );

  //密码隐藏不可见
  final bool obscureText;

  //是否可编辑
  final bool enabled;

  //是否可以选择
  final bool enableInteractiveSelection;

  final TextInputAction textInputAction;

  //占位符
  final String hintText;

  final Color primarycolor;

  //前缀icon
  final Widget iconWidget;

  //带边框的前缀icon
  final Widget prefixIcon;

  //后缀
  final Widget suffix;

  final String errorText;
  final String helperText;

  //内容变化
  final ValueChanged<String> onChanged;

  //提交事件
  final ValueChanged<String> onSubmitted;

  //关注节点
  final FocusNode focusNode;

  //样式
  final TextStyle textStyle;

  //控制器
  final TextEditingController controller;

  //字体
  final FontWeight fontWeight;

  //字体大小
  final double fontSize;

  //字体颜色
  final Color labelColor;

  final bool autofocus;

  //占位符字体颜色
  final Color hintTextColor;
  final Color focusedBorderColor;
  final Color enabledBorderColor;
  final Color backgroundColor; //背景色

  //光标颜色
  final Color cursorColor;

  final EdgeInsetsGeometry margin;
  final EdgeInsetsGeometry padding;

//  final InputBorder inputBorder;
//  final InputBorder enabledBorder;

  //是否是底部线
  final bool isUnderline;

  //外部边线
  final Decoration decoration;

  //是否显示边框
  final bool lineShow;

  //边距
  final EdgeInsetsGeometry contentPadding;

  //输入框现在规则
  List<TextInputFormatter> inputFormatters;

  //文字默认位置
  final TextAlign textAlign;

  final double outlineBorderRadius;

  AJInputWidget({Key key,
    this.obscureText = false,
    this.isUnderline = false,
    this.hintText,
    this.iconWidget,
    this.onChanged,
    this.textStyle,
    this.controller,
    this.prefixIcon,
    this.fontWeight,
    this.fontSize = 14,
    this.hintTextColor = const Color( 0xffd8d8d8 ),
    this.labelColor,
    this.margin = defaultMargin,
    this.padding,
    this.enabled = true,
    this.enableInteractiveSelection = true,
    this.textInputAction = TextInputAction.done,
    this.onSubmitted,
    this.focusNode,
    this.errorText,
    this.helperText,
    this.autofocus = false,
    this.suffix,
    this.primarycolor = const Color( 0xff8E9BE3 ),
    this.cursorColor = const Color( 0xff8E9BE3 ),
    this.contentPadding =
    const EdgeInsets.only( left: 15, right: 5, top: 10, bottom: 10 ),
    this.decoration,
    this.focusedBorderColor = const Color( 0xff8E9BE3 ),
    this.enabledBorderColor = const Color( 0xffE6E6E6 ),
    this.lineShow = true,
    this.backgroundColor,
    this.inputFormatters,
    this.textAlign = TextAlign.start,
    this.outlineBorderRadius = 30.0})
      : super( key: key );

  @override
  State<StatefulWidget> createState() {
    return _AJInputWidgetState( );
  }
}

class _AJInputWidgetState extends State<AJInputWidget> {
  _AJInputWidgetState() : super( );

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: widget.decoration,
      color: widget.backgroundColor,
      padding: widget.padding,
      margin: widget.margin,
      child: AccentColorOverride(
        color: widget.primarycolor,
        child: new TextField(
          enabled: widget.enabled,
          keyboardAppearance: Brightness.light,
          controller: widget.controller,
          focusNode: widget.focusNode,
          onChanged: widget.onChanged,
          onSubmitted: widget.onSubmitted,
          obscureText: widget.obscureText,
          enableInteractiveSelection: widget.enableInteractiveSelection,
          autocorrect: false,
          textInputAction: widget.textInputAction,
          cursorColor: widget.cursorColor,
          textAlign: widget.textAlign,
          autofocus: widget.autofocus,
          decoration: new InputDecoration(
              hintText: widget.hintText,
              errorText: widget.errorText,
              helperText: widget.helperText,
              contentPadding: widget.contentPadding,
              icon: widget.iconWidget,
              prefixIcon: widget.prefixIcon,
              suffixIcon: widget.suffix,
              disabledBorder: _getBorderLine(
                isUnderline: widget.isUnderline,
                lineShow: widget.lineShow,
                labelColor: widget.enabledBorderColor,
                width: 0.5,
                radius: widget.outlineBorderRadius,
              ),
              border: _getBorderLine(
                isUnderline: widget.isUnderline,
                lineShow: widget.lineShow,
                labelColor: widget.enabledBorderColor,
                width: 0.5,
                radius: widget.outlineBorderRadius,
              ),
              enabledBorder: _getBorderLine(
                isUnderline: widget.isUnderline,
                lineShow: widget.lineShow,
                labelColor: widget.enabledBorderColor,
                width: 0.5,
                radius: widget.outlineBorderRadius,
              ),
              focusedBorder: _getBorderLine(
                isUnderline: widget.isUnderline,
                lineShow: widget.lineShow,
                labelColor: widget.focusedBorderColor,
                width: 0.5,
                radius: widget.outlineBorderRadius,
              ),
              labelStyle: TextStyle(
                  fontWeight:
                  widget.fontWeight == null ? null : widget.fontWeight,
                  fontSize: widget.fontSize == null ? null : widget.fontSize,
                  color: widget.labelColor == null ? null : widget.labelColor ),
              hintStyle: TextStyle(
                  fontSize: widget.fontSize == null ? null : widget.fontSize,
                  color: widget.hintTextColor == null
                      ? null
                      : widget.hintTextColor ) ),
          inputFormatters: widget.inputFormatters,
        ),
      ),
    );
  }

  static _getBorderLine({bool isUnderline,
    bool lineShow,
    Color labelColor,
    double width = 0.5,
    double radius = 30.0}) {
    return isUnderline
        ? UnderlineInputBorder(
        borderSide: BorderSide(
            color: lineShow ? labelColor : Colors.transparent,
            width: width ) )
        : OutlineInputBorder(
        borderRadius: BorderRadius.all( Radius.circular( radius ) ),
        borderSide: BorderSide(
            color: lineShow ? labelColor : Colors.transparent,
            width: width ) );
  }
}

class AccentColorOverride extends StatelessWidget {
  const AccentColorOverride({Key key, this.color, this.child})
      : super( key: key );

  final Color color;
  final Widget child;

  @override
  Widget build(BuildContext context) {
    return Theme(
      child: child,
      data: Theme.of( context ).copyWith(
        primaryColor: color,
      ),
    );
  }
}
