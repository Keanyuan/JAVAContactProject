import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/aj_style.dart';

class FullWidthButton extends StatelessWidget {
  static const ICON_SIZE = 24.0;
  static const HORIZONTAL_PADDING = 10.0;
  static const VERTICAL_PADDING = 16.0;


  const FullWidthButton({
    @required this.iconPath,
    @required this.title,
    this.showDivider: false,
    this.intervalDistance : HORIZONTAL_PADDING,
    this.color,
    @required this.onPressed,
  }) : assert(iconPath != null),
       assert(title != null),
       assert(onPressed != null);
  final String iconPath;
  final String title;
  final bool showDivider;
  final double intervalDistance;
  final Color color;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context){
    Image icon = Image.asset(
      iconPath,
      width: AJSize.fullWidthIconButtonIconSize,
      height: AJSize.fullWidthIconButtonIconSize,
    );

    Text titleText = Text(title);
    if(color != null){
      titleText = Text(
        title,
        style: TextStyle(color: color,fontSize: AJFont.textSize12 / AJConfig.textScaleFactor),
        textAlign: TextAlign.center,
      );
      icon = Image.asset(
        iconPath,
        width: AJSize.fullWidthIconButtonIconSize,
        height: AJSize.fullWidthIconButtonIconSize,
        color: color,
      );
    }

    final button = Row(
      mainAxisAlignment: MainAxisAlignment.center,
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Container(
          padding: EdgeInsets.only(top: 3.0),
          child: icon,
        ),
        SizedBox(width: intervalDistance,),
        titleText,
      ],
    );

    return OutlineButton (
      onPressed: onPressed,
      color: Colors.white,
      child: button,
      shape: const RoundedRectangleBorder(
        borderRadius: BorderRadius.all(Radius.circular(5.0))),
    );

  }
}