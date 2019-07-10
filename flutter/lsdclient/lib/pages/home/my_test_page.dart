import 'package:flutter/material.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:lsdclient/widgets/aj_list_title.dart';
import 'package:lsdclient/widgets/refresh/src/aj_refresh_widget.dart';

class MyTestPage extends StatefulWidget {
  @override
  _MyTestPageState createState() => _MyTestPageState();
}

class _MyTestPageState extends State<MyTestPage> {
  RefreshController _refreshController;

  initState() {
    super.initState();
    _refreshController = RefreshController();
    // 是否需要刷新
    // _refreshController.requestRefresh();
  }

  @override
  Widget build(BuildContext context) {
//    return Material(
//      child: Drawer(
//        child: Scaffold(
//          appBar: CustomAppBar.getAppBar(context, "测试"),
//          body: AJRefreshWidget(
//            enablePullDown: true, //是否下拉刷新
//            enablePullUp:true, //是否上拉加载
//            onHeaderRefresh: _onHeaderRefresh,
//            onFooterRefresh: _onFooterRefresh,
//            controller: _refreshController,
//            child: ListView.builder(
//              itemBuilder: (BuildContext context, int index) {
//                return AJListTitle(
//                  onTap: (){
//                    NavigatorUtils.pushTO(context, child: Drawer(child: MyTestPage(),));
//                  },
//                  title: Text("标题"),
//                  subtitle: Text("这一段描述"),
//                );
//              },
//              itemCount: 20,
//            ),
//          ),
//        ),
//      ),
//    );

    return Scaffold(
      appBar: CustomAppBar.getAppBar(context, "测试"),
      body: AJRefreshWidget(
        enablePullDown: true,
        //是否下拉刷新
        enablePullUp: true,
        //是否上拉加载
        onHeaderRefresh: _onHeaderRefresh,
        onFooterRefresh: _onFooterRefresh,
        controller: _refreshController,
        child: ListView.builder(
          itemBuilder: (BuildContext context, int index) {
            return AJListTitle(
              title: Text("标题"),
              subtitle: Text("这一段描述"),
            );
          },
          itemCount: 20,
        ),
      ),
    );
  }

  //下拉刷新
  _onHeaderRefresh() {
    new Future.delayed(new Duration(milliseconds: 1000), () {
      _refreshController.refreshCompleted();
//       _refreshController.refreshFailed();
    });
  }

  //上拉加载
  _onFooterRefresh() {
    new Future.delayed(new Duration(milliseconds: 1000), () {
      _refreshController.loadComplete();
//      _refreshController.loadNoData();
    });
  }

  @override
  void dispose() {
    // TODO: implement dispose
    _refreshController.dispose();
    super.dispose();
  }
}




/*
*  "reqData": {
    "platformType": 1 //1：iOS 2：Android 3：其他
  },
  "responseData" : { //响应数据
    "platformType" : 1,//1：iOS 2：Android 3：其他
    "versionName" : "1.0.0", //最新版本
    "versionCode" : 1, //最新版本号  数字
    "downloadUrl" : "xxxx", //iOS： App Store url ； Android ：服务器下载地址
    "releaseLog" : "xxx", //更新信息
    "isMustUpdate" : 1 //1:true   0：false
  },*/