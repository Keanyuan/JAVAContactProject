import 'package:flutter/material.dart';


class AJFlexButton extends StatelessWidget{
  //按钮文字
  final String text;
  //按钮背景颜色
  final Color color;
  //按钮字体颜色
  final Color textColor;

  //按钮事件
  final VoidCallback onPress;

  //按钮字体大小
  final double fontSize;

  //按钮字体行数
  final int maxLines;

  //字体对齐方式
  final MainAxisAlignment mainAxisAlignment;

  //按钮圆角
  final double radius;

  //按钮阴影
  final double elevation;

  final ShapeBorder shape;

  AJFlexButton({Key key, this.text, this.color, this.textColor, this.onPress,
    this.fontSize = 16.0, this.maxLines = 1, this.mainAxisAlignment = MainAxisAlignment.center, this.radius = 0.0, this.elevation = 0.0, this.shape});

  @override
  Widget build(BuildContext context) {
    return new Material(
      child: new RaisedButton(
        onPressed: (){
          this.onPress?.call();
        },
        padding: new EdgeInsets.only(left: 20.0, top: 10.0, right: 20.0, bottom: 10.0),
//        shape: const StadiumBorder(side: BorderSide(style: BorderStyle.solid)),
        shape:  this.radius == 0.0 ? null : StadiumBorder(),
        textColor: textColor,
        color: Color.fromRGBO(255, 255, 255, 0.0),
        elevation: 0.0,
        child: new Column(
          mainAxisAlignment: mainAxisAlignment,
          children: <Widget>[
            new Text(text, style: new TextStyle(fontSize: fontSize), maxLines: maxLines, overflow: TextOverflow.ellipsis)
          ],
        ),
      ),
      borderRadius: BorderRadius.circular(radius),
      color: color,
      elevation: elevation,
    );

  }


}