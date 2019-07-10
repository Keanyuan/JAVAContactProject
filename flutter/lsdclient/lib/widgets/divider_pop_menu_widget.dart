import 'package:flutter/material.dart';
import 'package:lsdclient/common/out_put.dart';

class DividerPopMenuWidget extends StatelessWidget {

  final PopupMenuItemSelected<int> onSelected;


  const DividerPopMenuWidget({Key key, this.onSelected}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    double _top = ScreenUtil.getStatusBarH(context) + 70;
    return new PopupMenuButton<int>(
        child: Container(
          height: 40,
          alignment: Alignment.center,
          child: Row(children: <Widget>[
            Text("预警列表"),
            Icon(Icons.arrow_drop_down),
          ],),
        ),
        offset: Offset(10.0, _top),
        itemBuilder: (BuildContext context) => <PopupMenuEntry<int>>[
          new PopupMenuItem<int>(value: 0, child: new Text('预警事件')),
          new PopupMenuDivider(height: 1.0),
          new PopupMenuItem<int>(value: 1, child: new Text('预警订单')),
          new PopupMenuDivider(height: 1.0),
          new PopupMenuItem<int>(value: 2, child: new Text('关注订单')),
        ],
        onSelected: onSelected);
  }
}
