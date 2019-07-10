import 'package:flutter/material.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/tools/object_utils.dart';

typedef AlarmOrderCallback = void Function();

class AlarmOrderItem extends StatelessWidget {
  AlarmOrderItem({
    this.data,
    this.onTap,
    this.orderFollowOnTap,
  });

  final AlarmOrderData data;
  final AlarmOrderCallback onTap;
  final AlarmOrderCallback orderFollowOnTap;

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
            "订单号：${!ObjectUtils.isEmpty(data.orderNo) ? data.orderNo : '暂无'}",
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
            child: Center(
              child: InkWell(
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
                onTap: () {
                  if (orderFollowOnTap != null) {
                    orderFollowOnTap();
                  }
                },
              ),
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildLine3Widget() {
    Widget topWidget;
    Widget bottomWidget;

    topWidget = ObjectUtils.isEmpty(data.transSupplierName)
        ? Container(
            child: Text(
              !ObjectUtils.isEmpty(data.curShipmentStatus)
                  ? data.curShipmentStatus
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
          )
        : Container(
            child: Text(
              !ObjectUtils.isEmpty(data.curShipmentStatus)
                  ? data.curShipmentStatus
                  : '暂无',
              style: AJConstant.segValueTextStyle,
              textAlign: TextAlign.center,
            ),
          );

    if (ObjectUtils.isEmpty(data.transSupplierName)) {
      bottomWidget = Container(
        child: Text(
          '暂无',
          style: AJConstant.segValueTextStyle,
          textAlign: TextAlign.center,
        ),
      );
    } else {
      bottomWidget = Container(
        child: Text(
          !ObjectUtils.isEmpty(data.transSupplierName)
              ? data.transSupplierName
              : '暂无',
          style: AJConstant.segValueTextStyle,
          textAlign: TextAlign.center,
        ),
        decoration: BoxDecoration(
          border: Border(
            top: BorderSide(
              color: AJColors.DividerColor,
              width: AJSize.dividerWidth,
            ),
          ),
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
                  !ObjectUtils.isEmpty(data.curStartWarehouse)
                      ? data.curStartWarehouse
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
                  !ObjectUtils.isEmpty(data.curEndWarehouse)
                      ? data.curEndWarehouse
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

  Widget _buildLine4Widget() {
    Color color = ObjectUtils.isEmpty(data.nodeAlertLevelName)
        ? Colors.grey
        : ((data.nodeAlertLevelName == '正常')
            ? AJColors.FullNormalTypeColor
            : ((data.nodeAlertLevelName == '预警')
                ? AJColors.AlarmTypeColor
                : AJColors.OverTimeTypeColor));
    return Container(
      padding: const EdgeInsets.symmetric(vertical: 10.0, horizontal: 0.0),
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
                '计划开始时间',
                style: AJConstant.segTimeKeyTextStyle,
              ),
              SizedBox(
                height: 4.0,
              ),
              Container(
                //width: 65.0,
                child: Text(
                  !ObjectUtils.isEmpty(data.planShipTime)
                      ? data.planShipTime
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
                //width: 65.0,
                child: Text(
                  !ObjectUtils.isEmpty(data.actualShipTime)
                      ? data.actualShipTime
                      : "暂无",
                  style: AJConstant.segTimeValueTextStyle,
                  textAlign: TextAlign.right,
                ),
              ),
            ],
          )),
          SizedBox(
            width: 10.0,
          ),
          Stack(
            children: <Widget>[
              CircleAvatar(
                backgroundColor: color,
                radius: AJSize.circleAvatarRadius,
                child: Container(
                  padding: EdgeInsets.all(10.0),
                  alignment: Alignment.center,
                  child: Text(
                    !ObjectUtils.isEmpty(data.nodeAlertLevelName)
                        ? data.nodeAlertLevelName
                        : '暂无',
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
                  '计划完成时间',
                  style: AJConstant.segTimeKeyTextStyle,
                ),
                SizedBox(
                  height: 4.0,
                ),
                Container(
                  //width: 65.0,
                  child: Text(
                    !ObjectUtils.isEmpty(data.planDeliveryTime)
                        ? data.planDeliveryTime
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
                  //width: 65.0,
                  child: Text(
                    !ObjectUtils.isEmpty(data.actualDeliveryTime)
                        ? data.actualDeliveryTime
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
        _buildLine1Widget(),
        _buildLine2Widget(),
        _buildLine3Widget(),
        _buildLine4Widget(),
        SizedBox(
          height: 7.0,
        ),
        _buildOthersWidget('VIN码', data.vinCode),
      ],
    );

    var body = Stack(
      overflow: Overflow.visible,
      children: <Widget>[
        InkWell(
          child: Container(
            margin: const EdgeInsets.only(left: 15.0, right: 15.0, top: 15.0),
            padding:
                const EdgeInsets.symmetric(horizontal: 5.0, vertical: 13.0),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.all(Radius.circular(3)),
              boxShadow: [
                BoxShadow(
                  color: AJColors.shawColor,
                  offset: Offset(0.0, 5.0),
                  blurRadius: 10,
                ),
                BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
              ],
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
