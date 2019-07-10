import 'package:flutter/material.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/widgets/kaiyan_indictor.dart';

import 'forgrt_psss_word_page.dart';


class ForgetPsHomePage extends StatefulWidget {
  @override
  _ForgetPsHomePageState createState() => _ForgetPsHomePageState();
}

class _ForgetPsHomePageState extends State<ForgetPsHomePage>
    with SingleTickerProviderStateMixin{
  TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
  }
  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("忘记密码"),
        elevation: 0,
        bottom: _buildTopBar(context),
      ),
      body: _bottomWidget(context),
    );
  }

  Widget _buildTopBar(BuildContext context) {
    return PreferredSize(
      child: Container(
        decoration: BoxDecoration(
          border: Border(bottom: BorderSide(color: AJColors.app_line)),
          color: AJColors.white,
        ),
        height: 50.0,
        child: new TabBar(
          labelColor: AJColors.black,
          unselectedLabelColor: AJColors.app_line,
          indicator: KaiyanIndictor(w: 80.0),
          tabs: <Tab>[
            new Tab(
              child: Container(
                child: Center(
                    child: Text(
                  "邮件",
                  style: TextStyle(fontSize: 18.0),
                )),
              ),
            ),
            new Tab(
              child: Container(
                child: Center(
                    child: Text(
                  "短信",
                  style: TextStyle(fontSize: 18.0),
                )),
              ),
            ),
          ],
          controller: _tabController,
        ),
      ),
      preferredSize: Size(double.infinity, 50.0),
    );
  }

  Widget _bottomWidget(BuildContext context) {
    return TabBarView(
      children: <Widget>[new ForgetPasswordPage(0), new ForgetPasswordPage(1)],
      controller: _tabController,
    );
  }

}
