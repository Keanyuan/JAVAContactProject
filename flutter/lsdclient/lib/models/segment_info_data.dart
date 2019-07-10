import 'package:lsdclient/models/subsection_data.dart';

class SegmentInfoData {

  SegmentInfoData({
    this.sectionData,
    this.isOff : true,
    this.shipmentNo,
    this.shipmentState,
    this.transMode,
    this.clearCorp,
    this.orderSegTime,
    this.orderDispatchTime,
    this.scheduleTime,
    this.scheduleStatus,
    this.scheduleType,
    this.effectiveTime,
    this.planLoadingTime,
    this.transportToolStartTime,
    this.goodsStockOutTime,
  });

  final SubSectionData sectionData;      // 运段信息
  bool isOff = true;
  final String shipmentNo;               // 运单号
  final String shipmentState;            // 运单状态
  final String transMode;                // 运输方式
  final String clearCorp;                // 结算公司
  final String orderSegTime;             // 运单分段时间
  final String orderDispatchTime;        // 运单派单时间
  final String scheduleTime;             // 调度生效时间
  final String effectiveTime;            // 运段生效时间
  final String planLoadingTime;          // 计划装车时间
  final String transportToolStartTime;   // 运输工具道起运库时间
  final String goodsStockOutTime;        // 商品库出库时间
  final String scheduleStatus;           // 调度单状态
  final String scheduleType;             // 调度单类型

  SegmentInfoData.fromJson(Map<String, dynamic> json)
     : sectionData = SubSectionData(
          startAddress : json["shipmentSrcWhName"] ?? '',
          endAddress : json["shipmentDestWhName"] ?? '',
          shipmentStatus : json["shipmentState"] ?? '',
          transSupplierName : json['transCompany'] ?? '',
          planShipTime : json['loadLeavingTime'] ?? '暂无',
          actualShipTime : json['actualShipTime'] ?? '暂无',
          plannedArrivalTime : json['planArriveTime'] ?? '暂无',
          actualArrivalTime : json['actualDeliveryTime'] ?? '暂无',
          nodeAlertLevelName : json['nodeAlertLevelName'] ?? '',
        ),
        shipmentNo = json['shipmentNo'] ?? '',
        shipmentState = json['shipmentState'] ?? '',
        transMode = json['transMode'] ?? '',
        clearCorp = json['loadBalanceCorpName'] ?? '',
        effectiveTime = json['effectiveTime'] ?? '',
        planLoadingTime = json['planLoadTime'] ?? '',
        orderSegTime = json['shipmentCreateTime'] ?? '',
        orderDispatchTime = json['shipmentSubEndTime'] ?? '',
        transportToolStartTime = json['transBeginTime'] ?? '',
        goodsStockOutTime = json['loadOutTime'] ?? '',
        scheduleType = json['loadType'] ?? '',
        scheduleStatus = json['loadState'] ?? '',
        scheduleTime = json['loadTime'] ?? '';

  static List<SegmentInfoData> fromMapList(dynamic mapList) {
    List<SegmentInfoData> list = new List(mapList.length);
    for (int i = 0; i < mapList.length; i++) {
      list[i] = SegmentInfoData.fromJson(mapList[i]);
    }
    return list;
  }
}

