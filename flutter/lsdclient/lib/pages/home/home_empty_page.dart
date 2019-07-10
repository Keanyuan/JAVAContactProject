import 'package:flutter/material.dart';
import 'package:lsdclient/pages/home/web_page_controller.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/tools/navigator_utils.dart';
import 'package:rxdart/rxdart.dart';

class HomeEmptyPage extends StatefulWidget {
  final String url;
  final String title;
  final bool isPortraitUp;

  const HomeEmptyPage({Key key, this.url, this.title, this.isPortraitUp})
      : super(key: key);

  @override
  _HomeEmptyPageState createState() => _HomeEmptyPageState();
}

class _HomeEmptyPageState extends State<HomeEmptyPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("hhh"),),
      body: Container(child: Center(child: Text("测试页面"),),),
      backgroundColor: Colors.white,
    );
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
//    Observable.just(1).delay(Duration(milliseconds: 1500)).listen((_) async {
//      NavigatorUtils.pushTO(context,
//          child: WebPageController(
//            url: "${Address.basewebHost}${widget.url}",
//            title: widget.title,
//            isPortraitUp: widget.isPortraitUp,
//          ));
//    });
  }
}
