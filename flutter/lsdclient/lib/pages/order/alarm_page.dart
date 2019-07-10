import 'package:flutter/material.dart';
import 'package:lsdclient/models/aj_event_bus.dart';
import 'package:lsdclient/widgets/full_width_button.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:lsdclient/pages/order/alarm_event_page.dart';
import 'package:lsdclient/pages/order/alarm_order_page.dart';
import 'package:lsdclient/pages/order/concern_order_page.dart';
import 'package:lsdclient/pages/order/full_query_page.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/pages/home/screen_list_page.dart';
import 'package:lsdclient/tools/navigator_utils.dart';


class AlarmPage extends StatefulWidget {
  const AlarmPage({
    this.index : 0
  });
  final int index;

  _AlarmPageState createState() => _AlarmPageState();
}

class _AlarmPageState extends State<AlarmPage>
    with SingleTickerProviderStateMixin {

  TabController _tabController;
  List<Tab> _tabs;
  List<Widget> _pages;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _tabs = <Tab>[
      new Tab(text: '预警事件'),
      new Tab(text: '预警订单'),
      new Tab(text: '关注订单'),
    ];
    _pages = [
      AlarmEventPage(),
      AlarmOrderPage(),
      ConcernOrderPage(),
    ];

    _tabController = new TabController(
        vsync: this,
        length: _tabs.length,
        initialIndex: (widget.index > (_tabs.length - 1) ? (_tabs.length - 1) : widget.index)
    );
  }


  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
      appBar: new AppBar(
        elevation: 0.0,
        leading: Container(
          margin: EdgeInsets.only(left: 10.0),
          child: IconButton(
            alignment: Alignment.centerLeft,
            icon: Icon(Icons.arrow_back),
            color: Colors.black45,
            tooltip: '返回',
            onPressed: () {
              AJLogUtil.v('点击了返回');
              Navigator.of(context).pop();
            },
          ),
        ),
        actions: <Widget>[
          Container(
            margin: EdgeInsets.only(right: 10.0),
            child: IconButton(
              alignment: Alignment.centerRight,
              icon: ImageIcon(AssetImage(AJICons.screening)),
              color: Colors.black45,
              tooltip: '过滤',
              onPressed: () {
                NavigatorUtils.pushTO(context, child: ScreenListPage()).then((v) async{
                  if(v != null){
                    print("当前选项页：${_tabController.index}");
                    eventBus.fire(TabViewOnRefresh(_tabController.index));
                  }
                });
              },
            ),
          ),
        ],
        centerTitle: true,
        title: Row(
          children: <Widget>[
            Expanded(
              child: FullWidthButton(
                iconPath: 'assets/images/input_search.png',
                title: '请输入VIN码后6位或订单号',
                color: Colors.grey,
                intervalDistance : 5.0,
                onPressed:() {
                  Navigator.of(context).push(
                    MaterialPageRoute(builder: (context) {
                        return FullQueryPage();
                      },
                    )
                  ).then((data) {
                    //AJLogUtil.v('点击了返回 ');
                    //Navigator.of(context).pop();
                  });
                },
              ),
            ),
          ],
        ),
        bottom: new TabBar(
          tabs: _tabs,
          controller: _tabController,
          unselectedLabelColor: Colors.black,
          indicatorColor: Color(0xfffcba5e),
          labelColor: Color(0xfffcba5e),
          indicatorPadding: EdgeInsets.symmetric(horizontal: 50.0),
        ),
      ),
      body: new TabBarView(
        controller: _tabController,
        children: _pages,
      ),
    );
  }

}

