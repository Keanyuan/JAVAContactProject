import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_style.dart';
import 'package:lsdclient/models/subsection_data.dart';
import 'package:lsdclient/tools/object_utils.dart';

typedef OnTapCallback = void Function();

class AlarmCommonItem extends StatelessWidget {
  AlarmCommonItem({
    this.data,
    this.onTapCallback,
  });

  final SubSectionData data;
  final OnTapCallback onTapCallback;

  Widget _buildLine1Widget() {
    Widget topWidget;
    Widget bottomWidget;
    if (ObjectUtils.isEmpty(data.transSupplierName)) {
      topWidget = Container(
        decoration: BoxDecoration(
          border: Border(
            bottom: BorderSide(
              color: AJColors.DividerColor,
              width: AJSize.dividerWidth,
            ),
          ),
        ),
        child: Text(
          !ObjectUtils.isEmpty(data.shipmentStatus)
              ? data.shipmentStatus
              : '暂无',
          style: AJConstant.segValueTextStyle,
        ),
      );

      bottomWidget = Container(
        child: Text(
          !ObjectUtils.isEmpty(data.transSupplierName)
              ? data.transSupplierName
              : '暂无',
          style: AJConstant.segValueTextStyle,
        ),
      );
    } else {
      topWidget = Container(
        child: Text(
          !ObjectUtils.isEmpty(data.shipmentStatus)
              ? data.shipmentStatus
              : '暂无',
          style: AJConstant.segValueTextStyle,
        ),
      );

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
          !ObjectUtils.isEmpty(data.transSupplierName)
              ? data.transSupplierName
              : '暂无',
          style: AJConstant.segValueTextStyle,
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
      padding: const EdgeInsets.all(10.0),
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
                  !ObjectUtils.isEmpty(data.startAddress)
                      ? data.startAddress
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
                  !ObjectUtils.isEmpty(data.endAddress)
                      ? data.endAddress
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

  Widget _buildLine2Widget() {
    return Container(
      padding:
          const EdgeInsets.only(top: 10.0, left: 0.0, right: 0.0, bottom: 0.0),
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
              Text(
                !ObjectUtils.isEmpty(data.planShipTime)
                    ? data.planShipTime
                    : "暂无",
                style: AJConstant.segTimeValueTextStyle,
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
              Text(
                !ObjectUtils.isEmpty(data.actualShipTime)
                    ? data.actualShipTime
                    : "暂无",
                style: AJConstant.segTimeValueTextStyle,
              ),
            ],
          )),
          SizedBox(
            width: 10.0,
          ),
          Stack(
            children: <Widget>[
              CircleAvatar(
                backgroundColor: ObjectUtils.isEmpty(data.nodeAlertLevelName)
                    ? Colors.grey
                    : ((data.nodeAlertLevelName == '正常')
                        ? AJColors.FullNormalTypeColor
                        : ((data.nodeAlertLevelName == '预警')
                            ? AJColors.AlarmTypeColor
                            : AJColors.OverTimeTypeColor)),
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
                  style: AJConstant.attrKeyValueTextStyle,
                ),
                SizedBox(
                  height: 4.0,
                ),
                Text(
                  !ObjectUtils.isEmpty(data.plannedArrivalTime)
                      ? data.plannedArrivalTime
                      : "暂无",
                  overflow: TextOverflow.ellipsis,
                  style: AJConstant.segTimeValueTextStyle,
                ),
                SizedBox(
                  height: 8.0,
                ),
                Text(
                  '实际完成时间',
                  style: AJConstant.attrKeyValueTextStyle,
                ),
                SizedBox(
                  height: 4.0,
                ),
                Text(
                  !ObjectUtils.isEmpty(data.actualArrivalTime)
                      ? data.actualArrivalTime
                      : "暂无",
                  style: AJConstant.segTimeValueTextStyle,
                ),
              ],
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
      ],
    );

    // TODO: implement build
    return Container(
      padding: EdgeInsets.only(
        bottom: 8.0,
      ),
      child: contentWidget,
    );
  }
}
