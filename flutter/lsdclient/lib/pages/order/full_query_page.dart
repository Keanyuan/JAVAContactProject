import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/dao/order_dao.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/pages/order/alarm_details_page.dart';
import 'package:lsdclient/pages/order/alarm_order_item.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/widgets/aj_input_widget.dart';
import 'package:lsdclient/widgets/circular_progress_widget.dart';
import 'package:lsdclient/tools/screen_util.dart';

class FullQueryPage extends StatefulWidget {
  _FullQueryPageState createState() => _FullQueryPageState();
}

class _FullQueryPageState extends State<FullQueryPage> {
  final TextEditingController _searchController = new TextEditingController();
  String _searchValue = '';
  List<AlarmOrderData> fullOrders;
  bool isLoading = false;
  String searchIcon = "assets/images/input_search.png";
  String searchSuffixIcon = "";

  //搜索数据
  Future<Null> _searchData() async {
    fullOrders = null;
    if (_searchValue.isNotEmpty) {
      setState(() {
        searchSuffixIcon = "assets/images/input_delete.webp";
        isLoading = true;
      });

      var res = await OrderDao.getFullOrders(_searchValue);
      if (res != null && res.result) {
        fullOrders = res.data;
      }
    } else {
      searchSuffixIcon = "";
    }
    setState(() {});
  }

  Widget _headerSearchWidget() {
    return Container(
      height: 60,
      decoration: BoxDecoration(color: AJColors.white, boxShadow: [
        BoxShadow(
          color: AJColors.shawColor,
          offset: Offset(0.0, 5.0),
          blurRadius: 10,
        ),
        BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
      ]),
      child: AJInputWidget(
        margin: EdgeInsets.only(left: 20, right: 20, top: 0, bottom: 5),
        hintText: "请输入VIN码后6位或订单号",
        isUnderline: false,
        controller: _searchController,
        textInputAction: TextInputAction.search,
        outlineBorderRadius: 5.0,
        prefixIcon: Container(
          //color: Colors.white,
          child: Center(
            child: RawMaterialButton(
              onPressed: () {},
              materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
              constraints:
                  const BoxConstraints(minWidth: 20.0, minHeight: 20.0),
              child: searchIcon.isNotEmpty
                  ? Image.asset(
                      searchIcon,
//                color: const Color(0xffE6E6E6),
                      width: 16,
                      height: 16,
                    )
                  : Container(),
            ),
          ),
          width: 50,
          height: 20,
        ),
        suffix: Container(
          //color: Colors.white,
          child: Center(
            child: RawMaterialButton(
              onPressed: () {
                _searchValue = "";
                _searchController.value =
                    new TextEditingValue(text: _searchValue ?? "");
                _searchData().then((_) {
                  setState(() {
                    isLoading = false;
                  });
                });
              },
              materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
              constraints:
                  const BoxConstraints(minWidth: 20.0, minHeight: 20.0),
              child: searchSuffixIcon.isNotEmpty
                  ? Image.asset(
                      searchSuffixIcon,
//                color: const Color(0xffE6E6E6),
                      width: 16,
                      height: 16,
                    )
                  : Container(),
            ),
          ),
          width: 60,
          height: 20,
        ),
        onChanged: (v) {
          _searchValue = v;
          //触发查询
          _searchData().then((_) {
            setState(() {
              isLoading = false;
            });
          });
        },
      ),
    );
  }

  Widget _bodySearchWidget() {
    if (_searchValue.isEmpty) {
      return Container();
    } else {
      if (fullOrders == null || fullOrders.length == 0) {
        return isLoading
            ? CircularProgressWidget()
            : EmptyWidget(
                onPressed: () {
                  _searchData().then((_) {
                    setState(() {
                      isLoading = false;
                    });
                  });
                },
              );
      }
    }

    return ListView.builder(
      itemBuilder: (BuildContext context, int index) {
        var alarmOrder = fullOrders[index];
        return AlarmOrderItem(
          data: alarmOrder,
          onTap: () {
            Navigator.of(context).push(MaterialPageRoute(
              builder: (context) {
                return AlarmDetailsPage(
                  orderNo: alarmOrder.orderNo,
                  fullOrder: true,
                );
              },
            )).then((value) {
              setState(() {
                alarmOrder.isConcerned = value;
              });
            });
          },
          orderFollowOnTap: () {
            OrderDao.setConcerned(!alarmOrder.isConcerned, alarmOrder.orderNo)
                .then((res) {
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
      itemCount: fullOrders.length,
    );
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      backgroundColor: AJColors.white,
      resizeToAvoidBottomPadding: false,
      appBar: AppBar(
        brightness: Brightness.light,
        leading: IconButton(
            icon: Image.asset(
              AJICons.ARROW_LEFT,
              width: 18,
            ),
            onPressed: () {
              Navigator.of(context).pop();
            }),
        elevation: 0,
        title: Text(
          '搜索',
          style: TextStyle(fontSize: 18, color: AJColors.black),
        ),
        centerTitle: true,
        bottom: PreferredSize(
            child: _headerSearchWidget(),
            preferredSize: Size(ScreenUtil.getScreenW(context), 50)),
      ),
      body: Container(
        color: Color(0xffF3F3F8),
        child: _bodySearchWidget(),
      ),
    );
  }
}
