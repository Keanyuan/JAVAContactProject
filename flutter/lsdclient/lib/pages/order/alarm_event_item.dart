import 'package:flutter/material.dart';
import 'package:lsdclient/models/alarm_event_data.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/tools/aj_log_util.dart';
import 'package:lsdclient/tools/object_utils.dart';
import 'package:lsdclient/tools/screen_util.dart';

typedef AlarmEventCallback = void Function();

class AlarmEventItem extends StatelessWidget {
  AlarmEventItem({
    this.data,
    this.onTap,
    this.eventFollowOnTap,
  }) : assert(data != null) {
    others = {
      '预警运段': data.earlyWarningSection,
      '客户': data.customer,
      '经销商': data.dealer,
      '订单状态': data.orderState,
      '运段状态': data.transportState,
      '订单生效时间': data.effectiveTime,
      '锁定时间': data.lockTime,
      '解锁时间': data.unlockTime,
      '计划到达': data.planedArrivalTime,
      '预计到达': data.estimateArrivalTime,
      '延误时间': data.delayTime,
      '延误原因': data.delayReason,
    };
  }

  final AlarmEventData data;
  final AlarmEventCallback onTap;
  final AlarmEventCallback eventFollowOnTap;
  Map<String, String> others;
  bool isOff = true;

  Widget _buildLine1Widget() {
    return Container(
      padding: EdgeInsets.only(
        bottom: 10.0,
      ),
      margin: EdgeInsets.only(
        bottom: 10.0,
      ),
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
            !ObjectUtils.isEmpty(data.orderStartAddress)
                ? data.orderStartAddress
                : '暂无',
            style: AJConstant.addressTitleTextStyle,
          ),
          Container(
            margin: EdgeInsets.symmetric(
              horizontal: 10.0,
            ),
            width: 30.0,
            decoration: BoxDecoration(
              border: Border(
                bottom: BorderSide(
                  color: AJColors.AddressTitleColor,
                  width: AJSize.dividerWidth * 1.3,
                ),
              ),
            ),
          ),
          Text(
            !ObjectUtils.isEmpty(data.orderEndAddress)
                ? data.orderEndAddress
                : '暂无',
            style: AJConstant.addressTitleTextStyle,
          ),
        ],
      ),
    );
  }

  Widget _buildLine2Widget() {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 10.0),
      child: Row(
        children: <Widget>[
          Text(
            "订单号：${!ObjectUtils.isEmpty(data.orderNum) ? data.orderNum : '暂无'}",
            style: AJConstant.orderNoTextStyle,
          ),
          Container(
            margin: EdgeInsets.only(left: 10.0),
            width: AJSize.orderStateBoxWidth,
            height: AJSize.orderStateBoxHeight,
            decoration: BoxDecoration(
              color: Color(0xffFEF1DE),
              borderRadius: BorderRadius.all(Radius.circular(10)),
            ),
            child: Center(
              child: Text(
                "${!ObjectUtils.isEmpty(data.orderState) ? data.orderState : '暂无'}",
                style: AJConstant.orderStateTextStyle,
              ),
            ),
          ),
          Expanded(child: Container()),
          Container(
            margin: EdgeInsets.only(left: 10.0),
            width: AJSize.concernedBoxWidth,
            height: AJSize.concernedBoxHeight,
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.all(Radius.circular(4)),
              border: Border.all(
                color: data.isConcerned
                    ? AJColors.ConcernTextColor
                    : AJColors.UnConcernTextColor,
                width: 0.5,
              ),
            ),
            child: InkWell(
              child: Center(
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  crossAxisAlignment: CrossAxisAlignment.center,
                  children: <Widget>[
                    Image.asset(
                      data.isConcerned
                          ? AJICons.concerned
                          : AJICons.unconcerned,
                      width: AJSize.concernedImageSize,
                      height: AJSize.concernedImageSize,
                      color: data.isConcerned
                          ? AJColors.ConcernTextColor
                          : AJColors.UnConcernTextColor,
                    ),
                    SizedBox(
                      width: 5,
                    ),
                    Text(
                      data.isConcerned ? '已关注' : '关注',
                      style: data.isConcerned
                          ? AJConstant.concernedTextStyle
                          : AJConstant.unConcernedTextStyle,
                    ),
                  ],
                ),
              ),
              onTap: () {
                if (eventFollowOnTap != null) {
                  eventFollowOnTap();
                }
              },
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildLine3Widget() {
    var sectionData = data.sectionData;
    Widget topWidget;
    Widget bottomWidget;

    topWidget = !ObjectUtils.isEmpty(sectionData.transSupplierName)
        ? Container(
            child: Text(
              !ObjectUtils.isEmpty(sectionData.shipmentStatus)
                  ? sectionData.shipmentStatus
                  : '暂无',
              style: AJConstant.segValueTextStyle,
              textAlign: TextAlign.center,
            ),
          )
        : Container(
            child: Text(
              !ObjectUtils.isEmpty(sectionData.shipmentStatus)
                  ? sectionData.shipmentStatus
                  : '暂无',
              style: AJConstant.segValueTextStyle,
              textAlign: TextAlign.center,
            ),
            decoration: BoxDecoration(
              border: Border(
                bottom: BorderSide(
                  color: AJColors.DividerColor,
                  width: AJSize.dividerWidth,
                ),
              ),
            ),
          );

    if (sectionData.transSupplierName.isEmpty) {
      bottomWidget = Container(
        child: Text(
          '暂无',
          style: AJConstant.segValueTextStyle,
          textAlign: TextAlign.center,
        ),
      );
    } else {
      bottomWidget = Container(
        decoration: BoxDecoration(
          border: Border(
            top: BorderSide(
              color: AJColors.DividerColor,
              width: AJSize.dividerWidth,
            ),
          ),
        ),
        child: Text(
          sectionData.transSupplierName,
          style: AJConstant.segValueTextStyle,
          textAlign: TextAlign.center,
        ),
      );
    }

    var statusWidget = Column(
      children: <Widget>[
        topWidget,
        bottomWidget,
      ],
    );

    return Container(
      padding: const EdgeInsets.symmetric(horizontal: 5.0, vertical: 10.0),
      decoration: BoxDecoration(
        border: Border(
          bottom: BorderSide(
              color: AJColors.DividerColor, width: AJSize.dividerWidth),
        ),
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Column(
            children: <Widget>[
              Container(
                width: AJSize.segAddressMaxWidth,
                child: Text(
                  !ObjectUtils.isEmpty(sectionData.startAddress)
                      ? sectionData.startAddress
                      : '暂无',
                  style: AJConstant.segAddressTextStyle,
                  textAlign: TextAlign.center,
                ),
              ),
            ],
          ),
          SizedBox(width: AJSize.segAddressSpace),
          Container(
            width: AJSize.segAddressMaxWidth,
            child: statusWidget,
          ),
          SizedBox(width: AJSize.segAddressSpace),
          Column(
            children: <Widget>[
              Container(
                width: AJSize.segAddressMaxWidth,
                child: Text(
                  !ObjectUtils.isEmpty(sectionData.endAddress)
                      ? sectionData.endAddress
                      : '暂无',
                  style: AJConstant.segAddressTextStyle,
                  textAlign: TextAlign.center,
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }

  Widget _buildLine4Widget(BuildContext context) {
    var sectionData = data.sectionData;
    return Container(
      padding:
          const EdgeInsets.only(top: 10.0, left: 0.0, right: 0.0, bottom: 10.0),
      decoration: BoxDecoration(
          border: Border(
              bottom: BorderSide(
                  color: AJColors.DividerColor, width: AJSize.dividerWidth))),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Expanded(
              child: Column(
            crossAxisAlignment: CrossAxisAlignment.end,
            children: <Widget>[
              Text(
                "预警类型",
                style: AJConstant.nodeAlertNameTextStyle,
                textAlign: TextAlign.right,
              ),
              SizedBox(
                height: 8.0,
              ),
              Text(
                '计划开始时间',
                style: AJConstant.segTimeKeyTextStyle,
              ),
              SizedBox(
                height: 4.0,
              ),
              Container(
                //width: 65,
                child: Text(
                  !ObjectUtils.isEmpty(sectionData.planShipTime)
                      ? sectionData.planShipTime
                      : "暂无",
                  style: AJConstant.segTimeValueTextStyle,
                  textAlign: TextAlign.right,
                ),
              ),
              SizedBox(
                height: 8.0,
              ),
              Text(
                '实际开始时间',
                style: AJConstant.segTimeKeyTextStyle,
              ),
              SizedBox(
                height: 4.0,
              ),
              Container(
                //width: 65,
                child: Text(
                  !ObjectUtils.isEmpty(sectionData.actualShipTime)
                      ? sectionData.actualShipTime
                      : "暂无",
                  style: AJConstant.segTimeValueTextStyle,
                  textAlign: TextAlign.right,
                ),
              ),
            ],
          )),
          SizedBox(
            width: 15.0,
          ),
          Stack(
            children: <Widget>[
              CircleAvatar(
                backgroundColor:
                    ObjectUtils.isEmpty(sectionData.nodeAlertLevelName)
                        ? Colors.grey
                        : ((sectionData.nodeAlertLevelName == '正常')
                            ? AJColors.FullNormalTypeColor
                            : ((sectionData.nodeAlertLevelName == '预警')
                                ? AJColors.AlarmTypeColor
                                : AJColors.OverTimeTypeColor)),
                radius: AJSize.circleAvatarRadius,
                child: Container(
                  padding: EdgeInsets.all(10.0),
                  alignment: Alignment.center,
                  child: Text(
                    sectionData.nodeAlertLevelName,
                    textAlign: TextAlign.center,
                    style: AJConstant.nodeAlertLevelNameTextStyle,
                  ),
                ),
              ),
            ],
          ),
          SizedBox(
            width: 10.0,
          ),
          Expanded(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: <Widget>[
                Text(
                  data.nodeAlertName,
                  style: AJConstant.nodeAlertNameTextStyle,
                  textAlign: TextAlign.left,
                ),
                SizedBox(
                  height: 8.0,
                ),
                Text(
                  '计划完成时间',
                  style: AJConstant.segTimeKeyTextStyle,
                ),
                SizedBox(
                  height: 4.0,
                ),
                Container(
                  //width: 65,
                  child: Text(
                    !ObjectUtils.isEmpty(sectionData.plannedArrivalTime)
                        ? sectionData.plannedArrivalTime
                        : "暂无",
                    style: AJConstant.segTimeValueTextStyle,
                    textAlign: TextAlign.left,
                  ),
                ),
                SizedBox(
                  height: 8.0,
                ),
                Text(
                  '实际完成时间',
                  style: AJConstant.segTimeKeyTextStyle,
                ),
                SizedBox(
                  height: 4.0,
                ),
                Container(
                  //width: 65,
                  child: Text(
                    !ObjectUtils.isEmpty(sectionData.actualArrivalTime)
                        ? sectionData.actualArrivalTime
                        : "暂无",
                    style: AJConstant.segTimeValueTextStyle,
                    textAlign: TextAlign.left,
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildOthersWidget(String name, String value) {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 10.0),
      child: Row(
        children: <Widget>[
          Text(
            name,
            style: AJConstant.attrKeyValueTextStyle,
          ),
          Expanded(
            child: Text(
              !ObjectUtils.isEmpty(value) ? value : "",
              style: AJConstant.attrKeyValueTextStyle,
              textAlign: TextAlign.right,
            ),
          ),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    var contentWidget = Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        _buildLine2Widget(),
        _buildLine3Widget(),
        _buildLine4Widget(context),
        SizedBox(
          height: 7.0,
        ),
        _buildOthersWidget('VIN码', data.vinCode),
      ],
    );

    contentWidget.children.add(
      Offstage(
        offstage: isOff,
        child: Column(
          children: <Widget>[
            Column(
              children: others.keys.map((item) {
                return _buildOthersWidget(item, others[item]);
              }).toList(),
            ),
          ],
        ),
      ),
    );
    contentWidget.children.insert(0, _buildLine1Widget());

    var body = Stack(
      overflow: Overflow.visible,
      children: <Widget>[
        InkWell(
          child: Container(
            margin: const EdgeInsets.only(left: 15.0, right: 15.0, top: 15.0),
            padding:
                const EdgeInsets.symmetric(horizontal: 5.0, vertical: 13.0),
            decoration: BoxDecoration(
              color: AJColors.white,
              boxShadow: [
                BoxShadow(
                  color: AJColors.shawColor,
                  offset: Offset(0.0, 5.0),
                  blurRadius: 10,
                ),
                BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
              ],
              borderRadius: BorderRadius.all(Radius.circular(3)),
            ),
            child: contentWidget,
          ),
          onTap: () {
            if (onTap != null) {
              onTap();
            }
          },
        ),
      ],
    );

    return body;
  }
}
