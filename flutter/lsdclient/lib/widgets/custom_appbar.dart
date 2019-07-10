import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_style.dart';

class CustomAppBar {
  static const double appBarHeight = 48;

  static Widget getAppBar(
    BuildContext context,
    String title, {
    VoidCallback backPressed,
    bool displayBackButton = true,
    List<Widget> actions,
    Color backgroundColor = const Color(AJColors.primaryValue),
    Color titleColor = AJColors.black,
    double elevation = 1.0,
  }) {
    Widget backBtn = displayBackButton == true
        ? IconButton(
            icon: Image.asset(
              AJICons.ARROW_LEFT,
              width: 18,
            ),
            onPressed: backPressed ??  () {
              Navigator.of(context).pop();
            })
        : Container();
    return AppBar(
//      brightness: backgroundColor == const Color(AJColors.primaryValue)
//          ? Brightness.light
//          : Brightness.dark,
      leading: backBtn,
      elevation: elevation,
      actions: actions,
      title: Text(
        title,
        style: TextStyle(fontSize: 18, color: titleColor),
      ),
      backgroundColor: backgroundColor,
      centerTitle: true,
    );
  }

  static Widget getPageBody(Widget body, BuildContext context, String title,
      {bool displayBackButton = true,
      bool isShowBackground = true,
      Function keyBack}) {
    return Container(
        child: Column(children: <Widget>[
      CustomAppBar.getCutomAppBar(context, title,
          displayBackButton: displayBackButton,
          isShowBackground: isShowBackground,
          keyBack: keyBack),
      Expanded(
        child: body,
        flex: 1,
      )
    ]));
  }

  static Widget getCutomAppBar(BuildContext context, String title,
      {bool displayBackButton = true,
      List<Widget> actionWidgets,
      bool isShowBackground = true,
      Function keyBack}) {
    var _statusBarHeight = MediaQuery.of(context).padding.top;

    Widget backBtn = displayBackButton == true
        ? Padding(
            padding: EdgeInsets.only(top: _statusBarHeight),
            child: IconButton(
                highlightColor: Colors.transparent,
                splashColor: Colors.transparent,
                icon: Container(
                  width: 40,
                  height: 40,
                  alignment: Alignment.center,
                  child: Image.asset(
                    AJICons.ARROW_LEFT,
                    color: isShowBackground ? null : AJColors.white,
                    width: 30,
                    height: 30,
                  ),
                ),
                onPressed: () {
                  if (keyBack != null) {
                    keyBack();
                  } else {
                    Navigator.of(context).pop();
                  }
                }),
          )
        : Container();
    return Container(
      child: Stack(
        children: <Widget>[
          Image.asset(
            AJICons.aj_welcome,
            width: MediaQuery.of(context).size.width,
            height: CustomAppBar.appBarHeight + _statusBarHeight,
            fit: BoxFit.cover,
            color: isShowBackground ? null : Colors.transparent,
          ),
          Align(alignment: Alignment.centerLeft, child: backBtn),
          Padding(
            padding: EdgeInsets.only(top: _statusBarHeight),
            child: Align(
              alignment: Alignment.center,
              child: Text(
                title,
                style: TextStyle(fontSize: 18, color: Colors.white),
              ),
            ),
          ),
          getActionWidget(actionWidgets)
        ],
      ),
      height: CustomAppBar.appBarHeight + _statusBarHeight,
      width: MediaQuery.of(context).size.width,
    );
  }

  static Widget getActionWidget(List<Widget> actionWidgets) {
    List<Widget> widgts = [];
    if (actionWidgets != null) {
      widgts = actionWidgets.map((widget) {
        return Row(
          children: <Widget>[
            widget,
            SizedBox(
              width: 8,
            )
          ],
        );
      }).toList();
      Widget row = Row(children: widgts);
      return Align(
        alignment: Alignment.centerRight,
        child: row,
      );
    }
    return Container();
  }
}
