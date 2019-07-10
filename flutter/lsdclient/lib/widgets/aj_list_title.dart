import 'package:flutter/material.dart';

class AJListTitle extends StatelessWidget {
  final Widget leading;

  final Widget title;

  final Widget subtitle;

  final Widget trailing;

  //背景
  final Color backagroundColor;

  //是否可以交互
  final bool enabled;

  final bool selected;
  final GestureTapCallback onTap;

  //标题和描述间距
  final double columnMargin;

  //行间距
  final double rowMargin;

  final GestureLongPressCallback onLongPress;

  //整体margin
  final EdgeInsetsGeometry margin;
  final EdgeInsetsGeometry padding;

  final Color borderBottomColor;
  final Color highlightColor;
  final double borderWidth;
  AJListTitle({
    Key key,
    this.highlightColor,
    this.leading,
    this.title,
    this.subtitle,
    this.trailing,
    this.backagroundColor,
    this.enabled = true,
    this.selected,
    this.onTap,
    this.columnMargin = 5.0,
    this.rowMargin = 10.0,
    this.onLongPress,
    this.margin,
    this.padding,
    this.borderBottomColor = const Color(0xffE6E6E6),
    this.borderWidth = 1.0,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return InkWell(
      onLongPress: enabled ? onLongPress : null,
      onTap: enabled ? onTap : null,
      highlightColor: this.highlightColor,
      splashColor: this.highlightColor,
      child: Semantics(
        selected: selected,
        enabled: enabled,
        child: Container(
          margin: margin ??  EdgeInsets.only(left: 24, right: 24),
          padding:padding ?? EdgeInsets.symmetric(vertical: 10),
          decoration: BoxDecoration(
            border: Border(
              bottom: BorderSide(color: borderBottomColor, width: borderWidth),
            ),
          ),
          child: Row(
            children: <Widget>[
              leading == null ? Container() : leading,
              Container(width: title == null ? 0 : rowMargin),
              Expanded(child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  title == null ? Container() : title,
                  Container(
                    height: subtitle == null ? 0 : columnMargin,
                  ),
                  subtitle == null ? Container() : subtitle,
                ],
              )),
              trailing == null ? Container() : trailing,
            ],
          ),
        ),
      ),
    );
  }
}