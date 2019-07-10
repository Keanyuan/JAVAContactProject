
class OrderBasicData {

  const OrderBasicData({
    this.customer,
    this.vehicleType,
    this.orderType,
    this.departure,
    this.destProvinceOrCity,
    this.destination,
    this.dealer,
    this.orderState,
    this.lockState,
    this.lockReason,
    this.lockRemarks,
    this.orderCreatedTime,
    this.orderEffectiveTime,
    this.orderDispatchTime,
    this.plannedShipmentTime,
    this.actualShipmentTime,
    this.plannedSendToTime,
    this.actualSendToTime,
  });

  final String customer;               // 客户
  final String vehicleType;            // 车型
  final String orderType;              // 订单类型
  final String departure;              // 出发地
  final String destProvinceOrCity;     // 目的省市
  final String destination;            // 目的地
  final String dealer;                 // 经销商
  final String orderState;             // 订单状态
  final String lockState;              // 锁定状态
  final String lockReason;             // 锁定原因
  final String lockRemarks;            // 锁定备注
  final String orderCreatedTime;       // 订单创建时间
  final String orderEffectiveTime;     // 订单生效时间
  final String orderDispatchTime;      // 派单完成时间
  final String plannedShipmentTime;    // 计划出库时间
  final String actualShipmentTime;     // 实际出库时间
  final String plannedSendToTime;      // 计划送达时间
  final String actualSendToTime;       // 实际送达时间

  OrderBasicData.fromJson(Map<String, dynamic> json)
    : customer = json['customerName'] ?? '',
      vehicleType = json['vehicleType'] ?? '',
      orderType = json['orderType'] ?? '',
      departure = json['orderSrcWhName'] ?? '',
      destProvinceOrCity = json['destCity'] ?? '',
      destination = json['orderDestWhName'] ?? '',
      dealer = json['retailerName'] ?? '',
      orderState = json['orderState'] ?? '',
      lockState = json['lockStatus'] ?? '',
      lockReason = json['lockReason'] ?? '',
      lockRemarks = json['lockRemark'] ?? '',
      orderCreatedTime = json['orderCreateTime'] ?? '',
      orderEffectiveTime = json['orderAviabledTime'] ?? '',
      orderDispatchTime = json['orderFinishTime'] ?? '',
      plannedShipmentTime = json['planShipTime'] ?? '',
      actualShipmentTime = json['actualShipTime'] ?? '',
      plannedSendToTime = json['planDeliveryTime'] ?? '',
      actualSendToTime = json['actualDeliveryTime'] ?? '';

}