class AlarmOrderData {
  AlarmOrderData(
      {this.orderNo,
      this.orderState,
      this.isConcerned: false,
      this.isFullOrder: false,
      this.vinCode,
      this.orderStartAddress,
      this.orderEndAddress,
      this.curStartWarehouse,
      this.curEndWarehouse,
      this.curShipmentStatus,
      this.transSupplierName,
      this.planShipTime,
      this.actualShipTime,
      this.planDeliveryTime,
      this.actualDeliveryTime,
      this.nodeAlertLevelName,
      this.nodeAlertName});

  final String orderNo; // 订单号
  final String orderState; // 订单状态
  bool isConcerned; // 是否关注
  bool isFullOrder; // 是否全量
  final String vinCode; // VIN码
  final String orderStartAddress; // 订单出发城市名称
  final String orderEndAddress; // 订单目的城市名称
  final String curStartWarehouse; // 当前出发仓库
  final String curEndWarehouse; // 当前到达仓库
  final String curShipmentStatus; // 当前运段状态
  final String transSupplierName; // 物流公司
  final String planShipTime; // 计划发车时间
  final String actualShipTime; // 实际发车时间
  final String planDeliveryTime; // 计划到达时间
  final String actualDeliveryTime; // 实际到达时间
  final String nodeAlertLevelName; // 预警类型名称
  final String nodeAlertName;

  AlarmOrderData.fromJson(Map<String, dynamic> json)
      : orderNo = json['orderNo'] ?? '',
        orderState = json['orderState'] ?? '',
        isConcerned = json["isFollow"] ?? false,
        isFullOrder = false,
        vinCode = json["vin"] ?? '',
        orderStartAddress = json["orderSrcWhName"] ?? '',
        orderEndAddress = json["orderDestWhName"] ?? '',
        curStartWarehouse = json["belongedWhName"] ?? '',
        curEndWarehouse = json["curDestWhName"] ?? '',
        curShipmentStatus = json["curStatus"] ?? '',
        transSupplierName = json["transSupplierName"] ?? '',
        planShipTime = json["planShipTime"] ?? '',
        actualShipTime = json["actualShipTime"] ?? '',
        planDeliveryTime = json["planDeliveryTime"] ?? '',
        actualDeliveryTime = json["actualDeliveryTime"] ?? '',
        nodeAlertLevelName = json["nodeAlertLevelName"] ?? '',
        nodeAlertName = json["nodeAlertName"] ?? '';

  AlarmOrderData.fromFullOrderJson(Map<String, dynamic> json)
      : orderNo = json['orderNo'] ?? '',
        orderState = json['orderState'] ?? '',
        isConcerned = json["isFollow"] ?? false,
        isFullOrder = true,
        vinCode = json["vin"] ?? '',
        orderStartAddress = json["orderSrcWhName"] ?? '',
        orderEndAddress = json["orderDestWhName"] ?? '',
        curStartWarehouse = json["belongedWhName"] ?? '',
        curEndWarehouse = json["curDestWhName"] ?? '',
        curShipmentStatus = json["curStatus"] ?? '',
        transSupplierName = json["transSupplierName"] ?? '',
        planShipTime = json["planShipTime"] ?? '',
        actualShipTime = json["actualShipTime"] ?? '',
        planDeliveryTime = json["planDeliveryTime"] ?? '',
        actualDeliveryTime = json["actualDeliveryTime"] ?? '',
        nodeAlertLevelName = json["nodeAlertLevelName"] ?? '',
        nodeAlertName = json["nodeAlertName"] ?? '';

  static List<AlarmOrderData> fromMapList(dynamic mapList) {
    List<AlarmOrderData> list = new List(mapList.length);
    for (int i = 0; i < mapList.length; i++) {
      list[i] = AlarmOrderData.fromJson(mapList[i]);
    }
    return list;
  }
}
