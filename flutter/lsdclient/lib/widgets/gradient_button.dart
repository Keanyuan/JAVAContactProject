
import 'package:flutter/material.dart';
import 'package:lsdclient/common/out_put.dart';

class GradientButton extends StatelessWidget {
  GradientButton({
    this.colors = const [Color(0xffFD300D), Color(0xffFE1C38), Color(0xffFF0072)],
    this.width,
    this.height,
    this.onTap,
    this.boxShadow = const [BoxShadow(
      color: AJColors.buttonNormalColor,
      offset: Offset(0.0, 5.0),
      blurRadius: 10,
    )],
    this.radius = 0.0,
    @required this.child,
  });

  // 渐变色数组
  final List<Color> colors;

  // 按钮宽高
  final double width;
  final double height;

  final Widget child;

  final double radius;
  final  List<BoxShadow> boxShadow;

  //点击回调
  final GestureTapCallback onTap;

  @override
  Widget build(BuildContext context) {
    ThemeData theme = Theme.of(context);

    return DecoratedBox(
      decoration: BoxDecoration(
          gradient: LinearGradient(colors: colors),
          borderRadius: BorderRadius.all(Radius.circular(radius))
      ),
      child: Material(
        type: MaterialType.transparency,
        child: InkWell( ////INK可以实现装饰容器，实现矩形  设置背景色
          splashColor: colors.last,
          borderRadius: BorderRadius.all(Radius.circular(radius)),
          highlightColor: Colors.transparent,
          onTap: onTap,
          child: ConstrainedBox(
            constraints: BoxConstraints.tightFor(height: height, width: width),
            child: Center(
              child: Padding(
                padding: const EdgeInsets.all(8.0),
                child: DefaultTextStyle(
                    style: TextStyle(fontWeight: FontWeight.bold),
                    child: child),
              ),
            ),
          ),
        ),
      ),
    );
  }
}



class GradientContainer extends StatelessWidget {
  GradientContainer({
    this.colors = const [Color(0xffFD300D), Color(0xffFE1C38), Color(0xffFF0072)],
    this.width,
    this.height,
    this.onTap,
    this.radius = 0.0,
    @required this.child,
  });

  // 渐变色数组
  final List<Color> colors;

  // 按钮宽高
  final double width;
  final double height;

  final Widget child;

  final double radius;


  //点击回调
  final GestureTapCallback onTap;

  @override
  Widget build(BuildContext context) {

    return DecoratedBox(
      decoration: BoxDecoration(
          gradient: LinearGradient(colors: colors),
          boxShadow: [BoxShadow(
            color: Color(0xB28E9BE3),
//            offset: Offset(0.0, 5.0),
            blurRadius: 6,
          )],
          borderRadius: BorderRadius.all(Radius.circular(radius))
      ),
      child: Material(
        type: MaterialType.transparency,
        child: InkWell( ////INK可以实现装饰容器，实现矩形  设置背景色
          splashColor: colors.last,
          borderRadius: BorderRadius.all(Radius.circular(radius)),
          highlightColor: Colors.transparent,
          onTap: onTap,
          child: ConstrainedBox(
            constraints: BoxConstraints.tightFor(height: height, width: width),
            child: child,
          ),
        ),
      ),
    );
  }
}

