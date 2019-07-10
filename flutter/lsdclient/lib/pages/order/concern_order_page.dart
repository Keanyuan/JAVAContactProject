import 'package:flutter/material.dart';
import 'package:lsdclient/dao/order_dao.dart';
import 'package:lsdclient/models/aj_event_bus.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/pages/order/alarm_order_item.dart';
import 'package:lsdclient/pages/order/alarm_details_page.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/widgets/circular_progress_widget.dart';
import 'package:lsdclient/widgets/refresh/src/aj_refresh_widget.dart';

class ConcernOrderPage extends StatefulWidget {
  _ConcernOrderPageState createState() => _ConcernOrderPageState();
}

class _ConcernOrderPageState extends State<ConcernOrderPage> {

  List<AlarmOrderData> concernOrders;
  RefreshController _refreshController;
  int footIndex = 1;
  bool isLoading = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    concernOrders = [];
    _refreshController = RefreshController();
    _initData().then((_){
      if (mounted)
      setState(() {
        isLoading = false;
      });
    });
    _listen();
  }

  Future<bool> _initData({bool isNeedLoading : true}) async {
    if(isNeedLoading) {
      if (mounted)
      setState(() {
        isLoading = true;
      });
    }
    var res = await OrderDao.getFollowOrders(pageNo : footIndex, pageSize : 5);
    if (res != null && res.result){
      concernOrders.addAll(res.data);
      footIndex ++;

      if(res.data.length < 5){
        return false;
      }
      return true;
    }
    return false;

  }
  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    _refreshController.dispose();
  }

  //下拉刷新
  _onHeaderRefresh() {
    new Future.delayed(new Duration(milliseconds: 500), () {
      concernOrders = [];
      footIndex = 1;
      _initData(isNeedLoading : false).then((value){
        if (mounted)
        setState(() {
          isLoading = false;
        });
          _refreshController.refreshCompleted();
      });

    });
  }

  //上拉加载
  _onFooterRefresh() {
    new Future.delayed(new Duration(milliseconds: 500), () {
      _initData().then((value){
        if (mounted)
        setState(() {
          isLoading = false;
        });

        if(value) {
          _refreshController.loadComplete();
        } else {
          _refreshController.loadNoData();
        }
      });

    });
  }

  //监听Bus events
  void _listen() {
    eventBus.on<TabViewOnRefresh>().listen((event) {
      if(event.index == 2) {
        concernOrders = [];
        footIndex = 1;
        _initData().then((_) {
          if (mounted)
          setState(() {
            isLoading = false;
          });
        });
      }
    });
  }

  @override
  Widget build(BuildContext context) {

    if(concernOrders == null || concernOrders.length == 0) {
      return isLoading ? CircularProgressWidget() : EmptyWidget(
        onPressed : () {
          _initData().then((_){
            if (mounted)
            setState(() {
              isLoading = false;
            });
          });
        }
      );
    }

    return AJRefreshWidget(
      enablePullDown: true, //是否下拉刷新
      enablePullUp:true, //是否上拉加载
      onHeaderRefresh: _onHeaderRefresh,
      onFooterRefresh: _onFooterRefresh,
      controller: _refreshController,
      child: ListView.builder(
        itemBuilder: (BuildContext context, int index) {
          var concernOrder = concernOrders[index];
          return AlarmOrderItem(
            data: concernOrder,
            onTap: () {
              Navigator.of(context).push(
                  MaterialPageRoute(builder: (context) {
                    return AlarmDetailsPage(orderNo: concernOrder.orderNo,);
                  },)
              ).then((value){
                if (mounted)
                setState(() {
                  concernOrder.isConcerned = value;
                });
              });
            },
            orderFollowOnTap: () {
              OrderDao.setConcerned(!concernOrder.isConcerned, concernOrder.orderNo).then((res){
                if (mounted)
                  setState(() {
                  if(res) {
                    concernOrders.remove(concernOrder);
                    Code.errorHandle('取消关注成功');
                  }
                });
              });
            },

          );
        },
        itemCount: concernOrders.length,
      ),
    );

  }

}