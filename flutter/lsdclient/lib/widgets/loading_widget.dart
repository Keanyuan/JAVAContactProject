import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:lsdclient/common/out_put.dart';

class AJStackLoading extends StatelessWidget {
  /// ProgressIndicator的padding，决定loading的大小
  final EdgeInsets padding = EdgeInsets.all(30.0);

  /// 文字顶部距菊花的底部的距离
  final double margin = 10.0;

  /// 圆角
  final double cornerRadius = 10.0;

  final Widget _child;
  final bool _isLoading;
  final double opacity;
  final Color color;
  final String text;

  AJStackLoading({
    Key key,
    @required child,
    @required isLoading,
    this.text,
    this.opacity = 0.3,
    this.color = Colors.white,
  })  : assert(child != null),
        assert(isLoading != null),
        _child = child,
        _isLoading = isLoading,
        super(key: key);

  @override
  Widget build(BuildContext context) {
    List<Widget> widgetList = List<Widget>();
    widgetList.add(_child);
    if (_isLoading) {
      final loading = [
        Opacity(
          opacity: opacity,
          child: ModalBarrier(dismissible: false, color: color),
        ),
        _buildProgressIndicator()
      ];
      widgetList.addAll(loading);
    }
    return Stack(
      children: widgetList,
    );
  }

  Widget _buildProgressIndicator() {
    return Center(
      child: new Container(
        width: 200.0,
        height: 200.0,
        padding: new EdgeInsets.all(4.0),
        decoration: new BoxDecoration(
          color: Colors.transparent,
          //用一个BoxDecoration装饰器提供背景图片
          borderRadius: BorderRadius.all(Radius.circular(4.0)),
        ),
        child:new Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            new Container(
                child: SpinKitCircle(
                  color: AJColors.listDescColor,
                  size: 45,
                )),
            new Container(height: 10.0),
            new Container(
                child: new Text("努力加载中···",
                    style: TextStyle(
                      color: AJColors.listDescColor,
                      fontSize: AJFont.textSize16,
                    ))),
          ],
        ),
      ),
    );
  }
}
