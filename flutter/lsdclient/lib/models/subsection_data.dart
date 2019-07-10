class SubSectionData {
  SubSectionData({
    this.startAddress,
    this.endAddress,
    this.shipmentStatus,
    this.transSupplierName,
    this.planShipTime,
    this.actualShipTime,
    this.plannedArrivalTime,
    this.actualArrivalTime,
    this.nodeAlertLevelName,
  });

  SubSectionData.fromJson(Map<String, dynamic> json)
      : this.startAddress = json["shipmentSrcWhName"] ?? '',
        this.endAddress = json["shipmentDestWhName"] ?? '',
        this.shipmentStatus = json["shipmentState"] ?? '',
        this.transSupplierName = json["transSupplierName"] ?? '',
        this.planShipTime = json["planShipTime"] ?? '',
        this.actualShipTime = json["actualShipTime"] ?? '',
        this.plannedArrivalTime = json["planArriveTime"] ?? '',
        this.actualArrivalTime = json["actualArriveTime"] ?? '',
        this.nodeAlertLevelName = json["nodeAlertLevelName"] ?? '';

  final String startAddress;           // 运单出发仓库
  final String endAddress;             // 运单目的仓库
  final String shipmentStatus;         // 状态
  final String transSupplierName;      // 物流公司
  final String planShipTime;   // 计划发车时间
  final String actualShipTime;    // 实际发车时间
  final String plannedArrivalTime;     // 计划到达时间
  final String actualArrivalTime;      // 实际到达时间
  final String nodeAlertLevelName;          // 预警类型名称

}