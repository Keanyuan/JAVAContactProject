import 'package:flutter/material.dart';
import 'package:lsdclient/models/segment_info_data.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/pages/order/alarm_common_item.dart';
import 'package:lsdclient/tools/object_utils.dart';

typedef SegmentCallback = void Function();

class SegmentInfoItem extends StatelessWidget {
  SegmentInfoItem({
    this.data,
    this.segmentOnTap,
    this.segmentFollowOnTap,
    this.isBottom : false,
  });

  final SegmentInfoData data;
  final SegmentCallback segmentOnTap;
  final SegmentCallback segmentFollowOnTap;
  final bool isBottom;

  Widget _buildFirstWidget() {
    return AlarmCommonItem(
      data: data.sectionData,
    );
  }

  Widget _buildSecondWidget() {
    var head = Container(
      padding: EdgeInsets.symmetric(vertical: 10.0),
      margin: EdgeInsets.only(bottom: 10.0,right: 0.0),
      decoration: BoxDecoration(
        border: Border(
          bottom: BorderSide(
            color: AJColors.DividerColor,
            width: AJSize.dividerWidth,
          ),
        ),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Text(
            !ObjectUtils.isEmpty(data.sectionData.startAddress) ? data.sectionData.startAddress : '暂无',
            style: AJConstant.segAddressTextStyle,
          ),
          Container(
            margin: EdgeInsets.symmetric(horizontal: 10.0,),
            width: 20.0,
            decoration: BoxDecoration(
              border: Border(
                bottom: BorderSide(
                  color: AJColors.SegAddressTextColor,
                  width: AJSize.dividerWidth * 1.3,
                ),
              ),
            ),
          ),
          Text(
            !ObjectUtils.isEmpty(data.sectionData.endAddress) ? data.sectionData.endAddress : '暂无',
            style: AJConstant.segAddressTextStyle,
          ),
        ],
      ),
    );

    return Column(
      children: <Widget>[
        head,
        _buildItemWidget('运单号',data.shipmentNo),
        _buildItemWidget('运单状态',data.shipmentState),
        _buildItemWidget('运输方式',data.transMode),
        _buildItemWidget('结算公司',data.clearCorp),
        _buildItemWidget('运单分段时间',data.orderSegTime),
        _buildItemWidget('运单派单时间',data.orderDispatchTime),
        _buildItemWidget('运单生效时间',data.effectiveTime),
        _buildItemWidget('计划装车时间',data.planLoadingTime),
        _buildItemWidget('起运工具至起运库时间',data.transportToolStartTime),
        _buildItemWidget('商品库出库时间',data.goodsStockOutTime),
        _buildItemWidget('调度单类型',data.scheduleType),
        _buildItemWidget('调度状态',data.scheduleStatus),
        _buildItemWidget('调度生效时间',data.scheduleTime),
        _buildItemWidget('预计到达时间',data.sectionData.plannedArrivalTime),

      ],
    );
  }

  Widget _buildItemWidget(String name, String value){
    return Container(
      margin: const EdgeInsets.only(bottom: 10.0),
      padding: const EdgeInsets.symmetric(horizontal: 15.0),
      child: Row(
        children: <Widget>[
          Text(name ?? '', style: AJConstant.orderInfoItemTextStyle,),
          Expanded(
            child: Container(
              padding: EdgeInsets.symmetric(vertical: 13.0),
            ),
          ),
          Text(value ?? '', style: AJConstant.orderInfoItemTextStyle,),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    Widget tempWidget;
    if(!isBottom) {
      tempWidget = Container(
        margin: const EdgeInsets.only(left: 0.0, right: 0.0, top: 0.0, bottom: 10.0),
        decoration: BoxDecoration(
          color: Colors.white,
          border: Border(
            top: BorderSide(
              color: Color(0xffF4F6F7),
              width: 1.0,
            ),
          ),
          //border: Border.all(width: 0.0, color: Color(0xffF4F6F7)),
          //borderRadius: BorderRadius.all(Radius.circular(3)),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Offstage(
              offstage: !data.isOff,
              child: _buildFirstWidget(),
            ),
            Offstage(
              offstage: data.isOff,
              child: _buildSecondWidget(),
            ),
          ],
        ),
      );
    } else {
      tempWidget = Container(
        margin: const EdgeInsets.all(0.0),
        decoration: BoxDecoration(
          color: Colors.white,
          border: Border(
            top: BorderSide(
              color: Color(0xffF4F6F7),
              width: 1.0,
            ),
          ),
          //border: Border.all(width: 1.0, color: Color(0xffF4F6F7)),
          //borderRadius: BorderRadius.all(Radius.circular(3)),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Offstage(
              offstage: !data.isOff,
              child: _buildFirstWidget(),
            ),
            Offstage(
              offstage: data.isOff,
              child: _buildSecondWidget(),
            ),
          ],
        ),
      );
    }

    return InkWell(
      child: tempWidget,
      onTap: () {
        if(segmentOnTap != null) {
          segmentOnTap();
        }
      },
    );
  }

}
