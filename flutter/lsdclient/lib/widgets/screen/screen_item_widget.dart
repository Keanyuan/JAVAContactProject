import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/models/screen_item_model.dart';

class ScreenItemWidget extends StatelessWidget {
  final ScreenItemModel screenItemModel;
  final GestureTapCallback onTap;

  ScreenItemWidget({this.screenItemModel, this.onTap});

  @override
  Widget build(BuildContext context) {
    return InkWell(
      onTap: onTap,
      child: Container(
        padding: EdgeInsets.all(4),
        alignment: Alignment.center,
        decoration: BoxDecoration(
            borderRadius: BorderRadius.all(Radius.circular(4)),
            color: screenItemModel.isSelect
                ? AJColors.letterbackgroundColor
                : AJColors.letterNormalbackgroundColor,
            border: Border.all(
                color: screenItemModel.isSelect
                    ? AJColors.letterColor
                    : AJColors.transparent,
                width: 0.4,
                style: BorderStyle.solid)),
        child: Text(
          "${screenItemModel.name}",
          maxLines: 2,
          overflow: TextOverflow.ellipsis,
          style: screenItemModel.isSelect
              ? AJConstant.letterTitleSelectColorStyle
              : AJConstant.letterNormalButtonStyle,
        ),
      ),
    );
  }
}