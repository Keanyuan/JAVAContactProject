class AlarmEventHisData {
  AlarmEventHisData({
    this.shipmentSrcWhName,
    this.shipmentDestWhName,
    this.shipmentState,
    this.transSupplierName,
    this.planShipTime,
    this.actualShipTime,
    this.plannedArrivalTime,
    this.actualArrivalTime,
    this.nodeAlertLevelName,
    this.nodeAlertName,
  });

  AlarmEventHisData.fromJson(Map<String, dynamic> json)
      : this.shipmentSrcWhName = json["shipmentSrcWhName"] ?? '',
        this.shipmentDestWhName = json["shipmentDestWhName"] ?? '',
        this.shipmentState = json["shipmentState"] ?? '',
        this.transSupplierName = json["transSupplierName"] ?? '',
        this.planShipTime = json["planShipTime"] ?? '',
        this.actualShipTime = json["actualShipTime"] ?? '',
        this.plannedArrivalTime = json["planArriveTime"] ?? '',
        this.actualArrivalTime = json["actualArriveTime"] ?? '',
        this.nodeAlertLevelName = json["nodeAlertLevelName"] ?? '',
        this.nodeAlertName = json["nodeAlertName"] ?? '';

  final String shipmentSrcWhName;           // 运单出发仓库
  final String shipmentDestWhName;             // 运单目的仓库
  final String shipmentState;         // 状态
  final String transSupplierName;      // 物流公司
  final String planShipTime;   // 计划发车时间
  final String actualShipTime;    // 实际发车时间
  final String plannedArrivalTime;     // 计划到达时间
  final String actualArrivalTime;      // 实际到达时间
  final String nodeAlertLevelName;          // 预警类型名称
  final String nodeAlertName;

  static List<AlarmEventHisData> fromMapList(dynamic mapList) {
    List<AlarmEventHisData> list = new List(mapList.length);
    for (int i = 0; i < mapList.length; i++) {
      list[i] = AlarmEventHisData.fromJson(mapList[i]);
    }
    return list;
  }

}