import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/widgets/button_factory.dart';

class CircularProgressWidget extends StatelessWidget {
  final Color color;
  final Color spinKitCircleColor;

  CircularProgressWidget(
      {this.color = AJColors.listDescColor,
      this.spinKitCircleColor = AJColors.listDescColor});

  @override
  Widget build(BuildContext context) {
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
                      child: SpinKitCircle(
                    size: 45,
                    color: this.spinKitCircleColor,
                  )),
                  new Container(height: 15.0),
                  new Container(
                      child: new Text("努力加载中···",
                          style: TextStyle(
                            color: this.color,
                            fontSize: AJFont.textSize16,
                          ))),
                ],
              ),
            ),
          ),
        ));
  }
}

class EmptyWidget extends StatelessWidget {
  final String desc;
  final String descColor;
  final String imageAsset;
  final VoidCallback onPressed;

  EmptyWidget(
      {this.desc = "暂无数据,点击",
        this.descColor = "重新加载",
      this.imageAsset = AJICons.empty,
      this.onPressed});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return InkWell(
      onTap: onPressed,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.start,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Expanded(
              child: Container(
            padding: EdgeInsets.symmetric(horizontal: 80),
            child: Image.asset(
              "$imageAsset",
            ),
          )),
          SizedBox(
            height: 20,
          ),
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Container(
                child: Text(
                  '$desc',
                  style: TextStyle(
                      color: AJColors.screen_normal,
                      fontSize: AJFont.textSize16,
                      fontWeight: FontWeight.w300),
                ),
              ),
              Container(
                child: Text(
                  '$descColor',
                  style: TextStyle(
                      color: AJColors.buttonNormalColor,
                      fontSize: AJFont.textSize16,
                      fontWeight: FontWeight.w300),
                ),
              ),
            ],
          ),
          Expanded(child: Container()),
        ],
      ),
    );
  }
}
