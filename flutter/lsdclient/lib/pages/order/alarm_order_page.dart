import 'package:flutter/material.dart';
import 'package:lsdclient/dao/order_dao.dart';
import 'package:lsdclient/models/aj_event_bus.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/pages/order/alarm_details_page.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/widgets/circular_progress_widget.dart';
import 'package:lsdclient/widgets/refresh/src/aj_refresh_widget.dart';
import 'alarm_order_item.dart';

class AlarmOrderPage extends StatefulWidget {
  _AlarmOrderPageState createState() => _AlarmOrderPageState();
}

class _AlarmOrderPageState extends State<AlarmOrderPage> {
  List<AlarmOrderData> alarmOrders;
  RefreshController _refreshController;
  int footIndex = 1;
  bool isLoading = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    alarmOrders = [];
    _refreshController = RefreshController();
    _initData().then((_) {
      if (!mounted) return;
      setState(() {
        isLoading = false;
      });
    });
    _listen();
  }

  Future<bool> _initData({bool isNeedLoading: true}) async {
    if (isNeedLoading) {
      if (mounted)
        setState(() {
          isLoading = true;
        });
    }

    var res = await OrderDao.getOrderInquiry(pageNo: footIndex, pageSize: 5);
    if (res != null && res.result) {
      alarmOrders.addAll(res.data);
      footIndex++;

      if (res.data.length < 5) {
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
      alarmOrders = [];
      footIndex = 1;
      _initData(isNeedLoading: false).then((value) {
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
      _initData().then((value) {
        if (mounted)
          setState(() {
          isLoading = false;
        });

        if (value) {
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
      if (event.index == 1) {
        alarmOrders = [];
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
    if (alarmOrders == null || alarmOrders.length == 0) {
      return isLoading
          ? CircularProgressWidget()
          : EmptyWidget(
              onPressed: () {
                _initData().then((_) {
                  if (mounted)
                  setState(() {
                    isLoading = false;
                  });
                });
              },
            );
    }

    return AJRefreshWidget(
      enablePullDown: true,
      //是否下拉刷新
      enablePullUp: true,
      //是否上拉加载
      onHeaderRefresh: _onHeaderRefresh,
      onFooterRefresh: _onFooterRefresh,
      controller: _refreshController,
      child: ListView.builder(
        itemBuilder: (BuildContext context, int index) {
          var alarmOrder = alarmOrders[index];
          return AlarmOrderItem(
            data: alarmOrder,
            onTap: () {
              Navigator.of(context).push(MaterialPageRoute(
                builder: (context) {
                  return AlarmDetailsPage(
                    orderNo: alarmOrder.orderNo,
                  );
                },
              )).then((value) {
                if (mounted)
                setState(() {
                  alarmOrder.isConcerned = value;
                });
              });
            },
            orderFollowOnTap: () {
              OrderDao.setConcerned(!alarmOrder.isConcerned, alarmOrder.orderNo)
                  .then((res) {
                if (mounted)
                setState(() {
                  if (res) {
                    alarmOrder.isConcerned = !alarmOrder.isConcerned;
                    if (alarmOrder.isConcerned) {
                      Code.errorHandle('"关注成功"可在关注订单中查看');
                    } else {
                      Code.errorHandle('取消关注成功');
                    }
                  }
                });
              });
            },
          );
        },
        itemCount: alarmOrders.length,
      ),
    );
  }
}
