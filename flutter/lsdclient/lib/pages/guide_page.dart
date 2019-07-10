import 'dart:ui';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_page_indicator/flutter_page_indicator.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:lsdclient/widgets/gradient_button.dart';
import 'package:transformer_page_view/transformer_page_view.dart';

class WelcomeWidget extends StatefulWidget {
  @override
  _WelcomePageState createState() => new _WelcomePageState();
}

class _WelcomePageState extends State<WelcomeWidget> {
  final List<String> images = [
    "assets/images/guide_a.webp",
    "assets/images/guide_b.webp",
    "assets/images/guide_c.webp",
  ];

  final List<Color> backgroundColors = [
    Colors.white,
    Colors.white,
    Colors.white,
  ];

  bool loop = false;
  double size = 8.0;
  double activeSize = 12.0;

  PageController controller;
  PageIndicatorLayout layout = PageIndicatorLayout.WARM;

  @override
  void initState() {
    super.initState();
    updateFirstLaunch();

    controller = new TransformerPageController(
        itemCount: backgroundColors.length, loop: loop);
  }

  updateFirstLaunch() async {
    UserDao.updateFirstLaunch(false);
  }

  Widget getBtn(BuildContext context) {
    //
    return InkWell(
      child: Image.asset(
        "assets/images/welcom_btn.png",
        width: 158.0,
      ),
      onTap: _login,
    );
  }

  Widget getLastPage(TransformInfo info, BuildContext context) {
    return new Stack(children: <Widget>[
      new ParallaxColor(
        colors: backgroundColors,
        info: info,
        child: new Column(
          children: <Widget>[
            new Expanded(
                child: new ParallaxContainer(
              child: new Image.asset(images[info.index]),
              position: info.position,
              opacityFactor: 1.0,
              translationFactor: 50.0,
            )),
          ],
        ),
      ),
      new Align(
          alignment: Alignment.bottomCenter,
          child: Padding(
              padding: EdgeInsets.only(bottom: 80), child: getBtn(context)))
    ]);
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      body: Stack(
        children: <Widget>[
          new Column(
            children: <Widget>[
              new Expanded(
                child: Stack(children: <Widget>[
                  new TransformerPageView(
                      index: 0,
                      pageController: controller,
                      transformer: new PageTransformerBuilder(
                          builder: (Widget child, TransformInfo info) {
                        //如果是最后一页，加入‘立即体验’按钮
                        if (info.index == images.length - 1) {
                          return getLastPage(info, context);
                        }
                        return new ParallaxColor(
                          colors: backgroundColors,
                          info: info,
                          child: new Column(
                            children: <Widget>[
                              new Expanded(
                                  child: new ParallaxContainer(
                                child: new Image.asset(images[info.index]),
                                position: info.position,
                                opacityFactor: 1.0,
                                translationFactor: 50.0,
                              )),
                            ],
                          ),
                        );
                      }),
                      itemCount: images.length),
                  new Align(
                    alignment: Alignment.bottomCenter,
                    child: new Padding(
                      padding: new EdgeInsets.only(bottom: 60.0),
                      child: new PageIndicator(
                        layout: layout,
                        size: size,
                        activeSize: activeSize,
                        controller: controller,
                        activeColor: Color(0XFF3F87EF),
                        color: Color(0XFFDEE8F7),
                        space: 10.0,
                        count: images.length,
                      ),
                    ),
                  )
                ]),
              ),
            ],
          ),
          Container(
            child: InkWell(
              child: Container(
                alignment: Alignment.center,
                width: 40,
                height: 40,
                child: Text(
                  '跳过',
                  style: TextStyle(
                    color: Colors.black,
                    fontSize: 14,
                  ),
                ),
              ),
              onTap: _login,
            ),
            margin: EdgeInsets.only(
                top: MediaQuery.of(context).padding.top, right: 20),
            alignment: Alignment.topRight,
          )
        ],
      ),
    );
  }

  _login(){
    NavigatorUtils.goLogin(context);
  }
}
