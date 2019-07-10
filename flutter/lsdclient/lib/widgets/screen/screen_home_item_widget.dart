
import 'package:flutter/material.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/models/screen_item_model.dart';

class ScreenHomeItemWidget extends StatelessWidget {
  final String imageAssetName;
  final String title;
  final String checkInfo;
  final GestureTapCallback onTap;
  final EdgeInsetsGeometry margin;
  final List<ScreenItemModel> selectList;

  const ScreenHomeItemWidget({Key key,
    this.imageAssetName = AJICons.customer_distribution_list,
    this.title = "",
    this.checkInfo = "",
    this.onTap,
    this.margin,
    this.selectList,})
      : super( key: key );

  @override
  Widget build(BuildContext context) {
    // TODO: implement build

    return InkWell(
      onTap: onTap,
      child: Container(
        margin: EdgeInsets.only(top: 10),
        decoration: BoxDecoration(color: AJColors.white, boxShadow: [BoxShadow(
          color: Color.fromRGBO(142, 155, 227, 0.2),
//          offset: Offset(5.0, 5.0),
          blurRadius: 6,
        )], borderRadius: BorderRadius.all(Radius.circular(4.0))),
        padding: EdgeInsets.symmetric( vertical: 18, horizontal: 17 ),
        child: Row(
          children: <Widget>[
            Image.asset(
              this.imageAssetName,
              width: 26.8,
            ),
            Container(
              padding: EdgeInsets.symmetric( horizontal: 14 ),
              child: Text(
                title,
                style: AJConstant.listTitleTextStyle,
              ),
            ),
            Expanded(
                child: Container(
                  padding: EdgeInsets.only( right: 14 ),
                  child: Text(
                    this.checkInfo == null || this.checkInfo.length == 0
                        ? "请选择"
                        : this.checkInfo,
                    textAlign: TextAlign.end,
                    maxLines: 1,
                    overflow: TextOverflow.ellipsis,
                    style: this.checkInfo == null ||
                        this.checkInfo.length == 0
                        ? AJConstant.listDescCheckTextStyle
                        : AJConstant.listTitleDescTextStyle,
                  ),
                ) ),
            Container(
              child: Image.asset(
                AJICons.arrow_right,
                width: 6,
              ),
            ),
          ],
        ),
      ),
    );

    return Card(
      margin: EdgeInsets.only( top: 10, left: 0, right: 0 ),
      child: InkWell(
        onTap: onTap,
        child: Container(
          padding: EdgeInsets.symmetric( vertical: 18, horizontal: 17 ),
          child: Row(
            children: <Widget>[
              Image.asset(
                this.imageAssetName,
                width: 26.8,
              ),
              Container(
                padding: EdgeInsets.symmetric( horizontal: 14 ),
                child: Text(
                  title,
                  style: AJConstant.listTitleTextStyle,
                ),
              ),
              Expanded(
                  child: Container(
                    padding: EdgeInsets.only( right: 14 ),
                    child: Text(
                      this.checkInfo == null || this.checkInfo.length == 0
                          ? "请选择"
                          : this.checkInfo,
                      textAlign: TextAlign.end,
                      maxLines: 1,
                      overflow: TextOverflow.ellipsis,
                      style: this.checkInfo == null ||
                          this.checkInfo.length == 0
                          ? AJConstant.listDescCheckTextStyle
                          : AJConstant.listTitleDescTextStyle,
                    ),
                  ) ),
              Container(
                child: Image.asset(
                  AJICons.arrow_right,
                  width: 6,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}