import 'package:flutter/material.dart';
import 'package:lsdclient/models/alarm_events_his_data.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/models/order_Locus_data.dart';
import 'package:lsdclient/pages/order/alarm_common_item.dart';
import 'package:lsdclient/pages/order/alarm_event_his_item.dart';
import 'package:lsdclient/pages/order/alarm_order_item.dart';
import 'package:lsdclient/tools/aj_code.dart';
import 'package:lsdclient/tools/object_utils.dart';
import 'package:lsdclient/widgets/circular_progress_widget.dart';
import 'package:lsdclient/widgets/custom_appbar.dart';
import 'package:lsdclient/dao/order_dao.dart';
import 'package:lsdclient/widgets/aj_expansion_widget.dart';
import 'package:lsdclient/models/aj_expansion_data.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/models/order_basic_data.dart';
import 'package:lsdclient/pages/order/segment_info_item.dart';
import 'package:lsdclient/models/segment_info_data.dart';

class AlarmDetailsPage extends StatefulWidget {
  AlarmDetailsPage({
    this.orderNo,
    this.fullOrder : false,
  });
  final String orderNo;
  final bool fullOrder;

  _AlarmDetailsPageState createState() => _AlarmDetailsPageState();
}

class _AlarmDetailsPageState extends State<AlarmDetailsPage> {
  AlarmOrderData alarmOrderData;               // 订单详情信息
  OrderBasicData orderBasicData;               // 订单基本信息
  List<SegmentInfoData> segmentInfoDatas = [];      // 运段信息
  List<AlarmEventHisData> alarmEventHisDatas = [];  // 预警历史
  //List<OrderLocusData> orderLocusDatas = [];        // 文字物流轨迹

  List<AjExpansionData> ajExpansionDataList;
  Map<String,String> orderBasicInfos;
  bool isLoading = false;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    ajExpansionDataList = <AjExpansionData>[
      AjExpansionData(
        iconPath: 'assets/images/order_info_basic.png',
        title: '订单基本信息',
        isExpanded: false,
      ),
      AjExpansionData(
        iconPath: 'assets/images/transportation_way_list.png',
        title: '运段信息',
        isExpanded: false,
      ),
      AjExpansionData(
        iconPath: 'assets/images/alarm_his.png',
        title: '预警历史',
        isExpanded: false,
        isLast: true,
      ),
      AjExpansionData(
        iconPath: 'assets/images/text_logistic_trail.png',
        title: '文字物流轨迹',
        isExpanded: false,
      ),
    ];

    _initData().then((_){
      setState(() {
        isLoading = false;
        orderBasicInfos = {
          '客户': orderBasicData.customer,
          '车型': orderBasicData.vehicleType,
          '订单类型': orderBasicData.orderType,
          '出发地': orderBasicData.departure,
          '目的省市': orderBasicData.destProvinceOrCity,
          '目的地': orderBasicData.destination,
          '经销商': orderBasicData.dealer,
          '订单状态': orderBasicData.orderState,
          '订单创建时间' : orderBasicData.orderCreatedTime,
          '订单生效时间' : orderBasicData.orderEffectiveTime,
          '派单完成时间' : orderBasicData.orderDispatchTime,
          '计划出库时间': orderBasicData.plannedShipmentTime,
          '实际出库时间': orderBasicData.actualShipmentTime,
          '计划送达时间': orderBasicData.plannedSendToTime,
          '实际送达时间': orderBasicData.actualSendToTime,
          '锁定状态': orderBasicData.lockState,
          '锁定备注': orderBasicData.lockRemarks,
          '锁定原因': orderBasicData.lockReason,
        };

      });
    });
  }

  Future<Null> _initData() async {
    setState(() {
      isLoading = true;
    });

    var res = await OrderDao.getOrderDetail(widget.orderNo, fullOrder: widget.fullOrder);
    if (res != null && res.result){
      if(widget.fullOrder) {
        if(res.data['fullOrdersVo'] != null) {
          alarmOrderData = AlarmOrderData.fromJson(res.data['fullOrdersVo']);
          orderBasicData = OrderBasicData.fromJson(res.data['fullOrdersVo']);
        } else {
          alarmOrderData = AlarmOrderData();
          orderBasicData = OrderBasicData();
        }
      } else {
        if(res.data['alarmOrdersVo'] != null) {
          alarmOrderData = AlarmOrderData.fromJson(res.data['alarmOrdersVo']);
          orderBasicData = OrderBasicData.fromJson(res.data['alarmOrdersVo']);
        } else {
          alarmOrderData = AlarmOrderData();
          orderBasicData = OrderBasicData();
        }
      }

      if(res.data['fullShipments'] != null) {
        segmentInfoDatas.addAll(
            SegmentInfoData.fromMapList(res.data['fullShipments']));
      }
      if(res.data['alarmEventsHis'] != null) {
        alarmEventHisDatas.addAll(
            AlarmEventHisData.fromMapList(res.data['alarmEventsHis']));
      }
//      if(res.data['orderLocus'] != null) {
//        orderLocusDatas.addAll(OrderLocusData.fromMapList(res.data['orderLocus']));
//      }

    }
    setState(() {});
  }

  Widget _buildInfoTitleWidget(AjExpansionData data){
    return Container(
      height: 60,
      child : Row(
        children: <Widget>[
          Image.asset(
            data.iconPath,
            width: AJSize.infoTitleIconSize,
            height: AJSize.infoTitleIconSize,
          ),
          SizedBox(width: 10.0,),
          Text(data.title, style: AJConstant.orderInfoItemTitleTextStyle,),
        ],
      ),
    );
  }

  Widget _buildInfoWidget(AjExpansionData data, Widget bodyWidget){
    return AjExpansionWidget(
      head: Container(
        padding: EdgeInsets.only(left: 15.0),
        child: _buildInfoTitleWidget(data),
      ),
      body: bodyWidget,
      model: data,
      expansionCallback: (AjExpansionData data) {
        setState(() {
          ajExpansionDataList.where((item)=> item != data).forEach((item)=>item.isExpanded = false);
          data.isExpanded =  !data.isExpanded;
        });
      },
    );
  }

  Widget _buildOrderInfoItemWidget(String name, String value){
    return Row(
      children: <Widget>[
        Text(name ?? '', style: AJConstant.orderInfoItemTextStyle,),
        Expanded(
          child: Container(
            padding: EdgeInsets.symmetric(vertical: 15.0),
          ),
        ),
        Container(
          width: 200.0,
          child: Text(
            value ?? '',
            style: AJConstant.orderInfoItemTextStyle,
            textAlign: TextAlign.right,
          ),
        ),

      ],
    );
  }

  Widget _buildTimeLine(OrderLocusData data) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      mainAxisAlignment: MainAxisAlignment.start,
      children: <Widget>[
         Padding(
           padding: const EdgeInsets.only(left: 10.0),
           child: Container(
             padding: EdgeInsets.symmetric(horizontal: 5.0, vertical: 3.0),
             width: 85,
             child: Center(
               child: Text(
                 data.transTime,
                 style: AJConstant.attrKeyValueTextStyle,
                 textAlign: TextAlign.center,
               ),
             ),
           ),
         ),
         Column(
           children: <Widget>[
             Image.asset(
               data.imgPath1,
               width: AJSize.fullWidthIconButtonIconSize,
               height: AJSize.timeLine1IconSize,
             ),
             Image.asset(
               data.imgPath2,
               width: AJSize.timeLine1IconSize,
               height: AJSize.timeLine2IconSize,
             ),
           ],
         ),
         Column(
           crossAxisAlignment: CrossAxisAlignment.start,
           children: <Widget>[
             Container(
               padding: EdgeInsets.symmetric(horizontal: 9.0, vertical: 2.0),
               child: Text(
                 data.transState,
                 style: AJConstant.attrKeyValueTextStyle,
               ),
             ),
             Container(
               padding: EdgeInsets.symmetric(horizontal: 9.0, vertical: 0.0),
               child: Text(
                 data.transStateDesc,
                 style: AJConstant.attrKeyValueTextStyle,
               ),
             ),
           ],
         ),

      ],
    );

  }

  @override
  Widget build(BuildContext context) {

    return WillPopScope(
        child: Scaffold(
          appBar: CustomAppBar.getAppBar(
            context,
            '订单详情',
            elevation: 0.0,
            backPressed: () {
              bool _isConcerned = false;
              if(!ObjectUtils.isEmpty(alarmOrderData)){
                _isConcerned = alarmOrderData.isConcerned;
              }
              Navigator.of(context).pop(_isConcerned);
            },
          ),
          body: _getBody(),
        ),
        onWillPop: () {
          bool _isConcerned = false;
          if(!ObjectUtils.isEmpty(alarmOrderData)){
            _isConcerned = alarmOrderData.isConcerned;
          }
          Navigator.of(context).pop(_isConcerned);
        });
  }

  Widget _getBody() {

    if(alarmOrderData == null ||
        orderBasicInfos == null ||
        orderBasicInfos.keys.length == 0) {
      return isLoading ? CircularProgressWidget() : EmptyWidget(
          onPressed : () {
            _initData().then((_){
              setState(() {
                isLoading = false;
              });
            });
          }
      );
    }

    return SingleChildScrollView(

      child: Column(
        children: <Widget>[
          AlarmOrderItem(
            data: alarmOrderData,
            orderFollowOnTap: () {
              OrderDao.setConcerned(!alarmOrderData.isConcerned, alarmOrderData.orderNo).then((res){
                setState(() {
                  if(res) {
                    alarmOrderData.isConcerned = !alarmOrderData.isConcerned;
                    if(alarmOrderData.isConcerned){
                      Code.errorHandle('"关注成功"可在关注订单中查看');
                    } else {
                      Code.errorHandle('取消关注成功');
                    }
                  }
                });
              });
            },
          ),
          _buildInfoWidget(
            ajExpansionDataList[0],
            Container(
              padding: EdgeInsets.symmetric(horizontal: 15.0),
              child: Column(
                children: orderBasicInfos.keys.map((item) {
                  return _buildOrderInfoItemWidget(item,orderBasicInfos[item]);
                }).toList(),
              ),
            ),
          ),
          _buildInfoWidget(
            ajExpansionDataList[1],
            Container(
              color: Color(0xffF4F6F8),
              padding: EdgeInsets.symmetric(horizontal: 0.0),
              child: Column(
                children: segmentInfoDatas.map((item) {
                  int index = segmentInfoDatas.indexOf(item);
                  return SegmentInfoItem(
                    data: item,
                    isBottom: (index == (segmentInfoDatas.length - 1)),
                    segmentOnTap: () {
                      setState(() {
                        item.isOff = ! item.isOff;
                      });
                    },
                  );
                }).toList(),
              ),
            ),
          ),
          _buildInfoWidget(
            ajExpansionDataList[2],
            Container(
              color: Color(0xffF4F6F8),
              padding: EdgeInsets.symmetric(horizontal: 0.0),
              child: Column(
                children: alarmEventHisDatas.map((item) {
                  int index = alarmEventHisDatas.indexOf(item);
                  return AlarmEventHisItem(
                    data: item,
                    isBottom: (index == (alarmEventHisDatas.length - 1)),
                  );
                }).toList(),
              ),
            ),
          ),
//          _buildInfoWidget(
//            ajExpansionDataList[3],
//            Container(
//              child: Column(
//                children: orderLocusDatas.map((item) {
//                  return _buildTimeLine(item);
//                }).toList(),
//              ),
//            ),
//          ),
        ],
      ),
    );
  }
}