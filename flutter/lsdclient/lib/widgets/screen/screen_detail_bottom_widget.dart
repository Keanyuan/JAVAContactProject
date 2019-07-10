import 'package:flutter/material.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/models/screen_item_model.dart';

typedef void ScreenDetailTap(ScreenItemModel screenItemModel);

class ScreenDetailBottomWidget extends StatelessWidget {
  final List<ScreenItemModel> selectList;

  final GestureTapCallback onResetTap;
  final GestureTapCallback onSubmitTap;
  final ScreenDetailTap onDeleteTap;

  const ScreenDetailBottomWidget(
      {Key key,
      this.selectList = const [],
      this.onDeleteTap,
      this.onResetTap,
      this.onSubmitTap})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Positioned(
      left: 0,
      bottom: 0,
      right: 0,
      child: Stack(
        children: <Widget>[
          Container(
            height: 48.0 + 45.0,
            child: Column(
              children: <Widget>[
                Container(
                  height: 45.0,
                  decoration: BoxDecoration(color: AJColors.white, boxShadow: [
                    BoxShadow(
                      color: AJColors.shawColor,
                      offset: Offset(0.0, 5.0),
                      blurRadius: 10,
                    ),
                    BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
                  ]),
                  child: ListView.builder(
                    padding: EdgeInsets.only(left: 77),
                    itemBuilder: (BuildContext context, int index) {
//                      if (index == 0) {
//                        return Container(
//                          width: 77,
//                          height: 48,
//                          alignment: Alignment.center,
////                          child: Text(
////                            "已选(${selectList.length})",
////                            style: AJConstant.scerrnSelectNumStyle,
////                          ),
//                        );
//                      }
                      ScreenItemModel _screenItemModel = selectList[index];

                      return Container(
                        margin: EdgeInsets.only(right: 10),
                        alignment: Alignment.center,
                        child: Container(
                          constraints:
                          BoxConstraints(minWidth: 100, minHeight: 45),
                          alignment: Alignment.center,
                          decoration: BoxDecoration(
                            borderRadius: BorderRadius.all(Radius.circular(4)),
                            color: AJColors.letterbackgroundColor,
                            border: Border.all(
                                color: AJColors.letterColor,
                                width: 0.4,
                                style: BorderStyle.solid),
                          ),
                          child: Row(
                            children: <Widget>[
                              Container(
                                margin: EdgeInsets.only(left: 10),
                                child: Text(
                                  _screenItemModel.name,
                                  maxLines: 1,
                                  overflow: TextOverflow.ellipsis,
                                  style: AJConstant.letterTitleSelectColorStyle,
                                ),
                              ),
                              InkWell(
                                onTap: () {
                                  onDeleteTap(_screenItemModel);
                                },
                                child: Container(
                                  width: 25,
                                  height: 40,
                                  alignment: Alignment.center,
                                  child: Image.asset(
                                    AJICons.cha,
                                    width: 15,
                                    color: AJColors.letterColor,
                                  ),
                                ),
                              )
                            ],
                          ),
                        ),
                      );
                    },
                    scrollDirection: Axis.horizontal,
                    itemCount: selectList.length,
                  ),
                ),
                Container(
                  height: 48,
                  decoration: BoxDecoration(
                    color: AJColors.white,
                    border: Border(
                      top: BorderSide(color: AJColors.app_line),
                    ),
                  ),
                  child: Row(
                    children: <Widget>[
                      Expanded(
                          flex: 2,
                          child: InkWell(
                            onTap: onResetTap,
                            child: Container(
                              alignment: Alignment.center,
                              child: Text("重置",
                                  style: AJConstant.scerrnNormalButtonStyle),
                            ),
                          )),
                      Expanded(
                          flex: 3,
                          child: InkWell(
                            onTap: onSubmitTap,
                            child: Container(
                              color: AJColors.buttonNormalColor,
                              alignment: Alignment.center,
                              child: Text(
                                "确认",
                                style: AJConstant.scerrnButtonWhiteStyle,
                              ),
                            ),
                          )),
                    ],
                  ),
                )
              ],
            ),
          ),
          Positioned(
            left: 0,
            top: 0,
            child: Container(
              width: 77,
              height: 45,
              color: AJColors.white,
              alignment: Alignment.center,
              child: Text(
                "已选(${selectList.length})",
                style: AJConstant.scerrnSelectNumStyle,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
