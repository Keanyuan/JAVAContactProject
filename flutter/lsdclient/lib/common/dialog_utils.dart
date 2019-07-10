import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:oktoast/oktoast.dart';

class DialogUtil {
  static showLongToast(String tostMessage) {
    showToast(tostMessage, duration: Duration(milliseconds: 2000));
  }

  static showShortToast(String tostMessage) {
    showToast(tostMessage);
  }

  //加载样式
  static Future<Null> showSpinKitLoadingDialog(BuildContext context) {
    return showDialog(
        context: context,
//        barrierDismissible: false,
        builder: (BuildContext context) {
          return new Material(
              color: Colors.transparent,
              child: WillPopScope(
                onWillPop: () => new Future.value(false),
                child: Center(
                  child: new Container(
                    width: 200.0,
                    height: 200.0,
                    padding: new EdgeInsets.all(4.0),
                    decoration: new BoxDecoration(
                      color: Colors.transparent,
                      //用一个BoxDecoration装饰器提供背景图片
                      borderRadius: BorderRadius.all(Radius.circular(4.0)),
                    ),
                    child: new Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: <Widget>[
                        new Container(
                            child: SpinKitFadingGrid(color: Color(0xffffffff))),
                        new Container(height: 10.0),
                        new Container(
                            child: new Text("努力加载中···",
                                style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 18,
                                ))),
                      ],
                    ),
                  ),
                ),
              ));
        });
  }

  /// 等待页
  static Future<T> showLoadingDialog<T>(
      BuildContext context, Future<T> futureTask) {
    // 是被future pop的还是按返回键pop的
    bool popByFuture = true;

    showDialog(
      context: context,
      builder: (context) {
        return Material(
          color: Colors.transparent,
          child: WillPopScope(
            onWillPop: () async => false,
            child: Center(
              child: Container(
                width: 200.0,
                height: 200.0,
                padding: new EdgeInsets.all(4.0),
                decoration: new BoxDecoration(
                  color: Colors.transparent,
                  //用一个BoxDecoration装饰器提供背景图片
                  borderRadius: BorderRadius.all(Radius.circular(4.0)),
                ),
                child: new Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    new Container(
                        child: SpinKitFadingGrid(color: Color(0xffffffff))),
                    new Container(height: 10.0),
                    new Container(
                        child: new Text("努力加载中···",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 18,
                            ))),
                  ],
                ),
              ),
            ),
          ),
        );
      },
      barrierDismissible: false,
    ).whenComplete(() {
      // 1. 如果是返回键pop的, 那么设置成true, 这样future完成时就不会pop了
      // 2. 如果是future完成导致的pop, 那么这一行是没用任何作用的
      popByFuture = false;
    });
    return futureTask.whenComplete(() {
      // 由于showDialog会强制使用rootNavigator, 所以这里pop的时候也要用rootNavigator
      if (popByFuture) {
        Navigator.of(context, rootNavigator: true).pop(context);
      }
    });
  }

  /// 单击提示退出
  static Future<bool> dialogConfigApp(BuildContext context, String title,
      {VoidCallback confirmTap}) {
    double _btnH = 50.0;
    double _h = 130.0;
    double _w = 260.0;

    return showDialog(
      context: context,
      builder: (context) {
        return Material(
          color: Colors.transparent,
          child: WillPopScope(
            onWillPop: () async => false,
            child: Center(
              child: Container(
                width: _w,
                height: _h,
                decoration: new BoxDecoration(
                  color: Colors.white,
                  //用一个BoxDecoration装饰器提供背景图片
                  borderRadius: BorderRadius.all(Radius.circular(4.0)),
                ),
                child: new Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    new Container(
                      child: Text(
                        title,
                        style: AJConstant.dialogTitleTextStyle,
                      ),
                      margin:
                          EdgeInsets.only(top: 30, left: 10, right: 10),
                    ),
                    Expanded(child: Container()),
                    Row(
                      children: <Widget>[
                        Expanded(
                            child: new InkWell(
                          onTap: () {
                            Navigator.of(context).pop();
                          },
                          child: Container(
                            decoration: BoxDecoration(
                              border: Border(
                                top: BorderSide(color: AJColors.app_line),
                                right: BorderSide(color: AJColors.app_line),
                              ),
                            ),
                            height: _btnH,
                            alignment: Alignment.center,
                            child: new Text(
                              "取消",
                              style: AJConstant.dialogTitleTextStyle,
                            ),
                          ),
                        )),
                        Expanded(
                            child: new InkWell(
                              onTap: () {
                                if (confirmTap != null) {
                                  confirmTap();
                                }
                                Navigator.of(context).pop();
                                Navigator.of(context).pop();
                              },
                              child: Container(
                                decoration: BoxDecoration(
                                  border: Border(
                                    left: BorderSide(color: AJColors.app_line),
                                    top: BorderSide(color: AJColors.app_line),
                                  ),
                                ),
                                height: _btnH,
                                alignment: Alignment.center,
                                child: new Text(
                                  "确定",
                                  style: AJConstant.dialogTitleTextStyle,
                                ),
                              ),
                            ))
                      ],
                    ),
                  ],
                ),
              ),
            ),
          ),
        );
      },
      barrierDismissible: false,
    );
  }
}
