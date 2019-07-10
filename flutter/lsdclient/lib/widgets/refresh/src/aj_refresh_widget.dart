import 'package:flutter/material.dart';
import 'package:flutter_spinkit/flutter_spinkit.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/widgets/refresh/src/indicator/waterdrop_header.dart';
import 'package:lsdclient/widgets/refresh/src/smart_refresher.dart';

export  'package:lsdclient/widgets/refresh/pull_to_refresh.dart';


class AJRefreshWidget extends StatefulWidget {
  AJRefreshWidget({
    Key key,
    @required this.controller,
    this.enablePullDown: true,
    this.enablePullUp: true,
    this.onHeaderRefresh,
    this.onFooterRefresh,
    this.child,
    this.isLoading = false,
    this.isNestWrapped: false,
  }) : super(key: key);

  final RefreshController controller;

  final Widget child;

  //是否下拉刷新
  final bool enablePullDown;

  //是否上拉加载
  final bool enablePullUp;

  //下拉刷新处理事件
  final Function onHeaderRefresh;
  //上拉加载处理事件
  final Function onFooterRefresh;

  //是否添加加载样式 : true  显示，  false ： 不显示
  final bool isLoading;

  final bool isNestWrapped;

  @override
  _AJRefreshWidgetState createState() => _AJRefreshWidgetState();
}

class _AJRefreshWidgetState extends State<AJRefreshWidget> {
  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      body: Stack(
        children: <Widget>[
          SmartRefresher(
            controller: widget.controller,
            enablePullDown: widget.enablePullDown,
            enablePullUp: widget.enablePullUp,
            onRefresh: widget.onHeaderRefresh,
            onLoading: widget.onFooterRefresh,
            header: WaterDropHeader(),
            child: widget.child,
            isNestWrapped: widget.isNestWrapped,
          ),
          new Offstage(
            offstage: !(widget.isLoading), //offstage: true 不显示  false 显示
            child: Container(
              alignment: Alignment.center,
              child: SpinKitCircle(
                color: AJColors.listDescColor,
                size: 45.0,
              ),
            ),
          )
        ],
      ),
    );
  }

  @override
  // TODO: implement wantKeepAlive
  bool get wantKeepAlive => true;
}
