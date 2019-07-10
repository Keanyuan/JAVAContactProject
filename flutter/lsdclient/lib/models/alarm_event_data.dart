import 'subsection_data.dart';

class AlarmEventData {
  AlarmEventData({
    this.orderStartAddress,
    this.orderEndAddress,
    this.orderNum,
    this.isConcerned: false,
    this.vinCode,
    this.earlyWarningSection,
    this.customer,
    this.dealer,
    this.orderState,
    this.transportState,
    this.effectiveTime,
    this.lockTime,
    this.unlockTime,
    this.delayTime,
    this.delayReason,
    this.planedArrivalTime,
    this.estimateArrivalTime,
    this.sectionData,
    this.nodeAlertName,
  });

  final String orderStartAddress; // 订单出发城市名称
  final String orderEndAddress; // 订单目的城市名称
  final String orderNum; // 订单号
  bool isConcerned; // 是否关注
  final String vinCode; // VIN码
  final String earlyWarningSection; // 预警运段
  final String customer; // 客户
  final String dealer; // 经销商
  final String orderState; // 订单状态
  final String transportState; // 运段状态
  final String effectiveTime; // 订单生效时间
  final String lockTime; // 锁定时间
  final String unlockTime; // 解锁时间
  final String delayTime; // 延误时间
  final String delayReason; // 延误原因
  final String planedArrivalTime; // 计划到达时间
  final String estimateArrivalTime; // 预计达到时间
  final SubSectionData sectionData; // 分段信息
  final String nodeAlertName;

  AlarmEventData.fromJson(Map<String, dynamic> json)
      : orderStartAddress = json["orderSrcWhName"] ?? '',
        orderEndAddress = json["orderDestWhName"] ?? '',
        orderNum = json["orderNo"] ?? '',
        orderState = json["orderState"] ?? '',
        isConcerned = json["isFollow"] ?? false,
        sectionData = SubSectionData.fromJson(json),
        vinCode = json["vin"] ?? '',
        earlyWarningSection = json["orderSrcWhCName"] ?? '',
        customer = json["customerName"] ?? '',
        dealer = json["retailerName"] ?? '',
        transportState = json["shipmentState"] ?? '',
        effectiveTime = json["orderAviabledTime"] ?? '',
        lockTime = json["orderSrcWhCName"] ?? '',
        unlockTime = json["orderSrcWhCName"] ?? '',
        delayTime =
            (json['delayTime'] != null && json['delayTime'].toString() != '0')
                ? json['delayTime'].toString() + "个小时"
                : '暂无',
        delayReason = json["orderSrcWhCName"] ?? '',
        planedArrivalTime = json["planDeliveryTime"] ?? '',
        estimateArrivalTime = json["orderSrcWhCName"] ?? '',
        nodeAlertName = json["nodeAlertName"] ?? '';

  static List<AlarmEventData> fromMapList(dynamic mapList) {
    List<AlarmEventData> list = new List(mapList.length);
    for (int i = 0; i < mapList.length; i++) {
      list[i] = AlarmEventData.fromJson(mapList[i]);
    }
    return list;
  }
}
