class OrderLocusData {
  OrderLocusData({
    this.transState,
    this.transTime,
    this.transStateDesc,
    this.imgPath1,
    this.imgPath2,
  });

  OrderLocusData.fromJson(Map<String, dynamic> json)
      : this.transState = json["transState"] ?? '',
        this.transTime = json["transTime"] ?? '',
        this.transStateDesc = json["transStateDesc"] ?? '',
        this.imgPath1 = 'assets/images/in_transit_logo.png',
        this.imgPath2 = 'assets/images/timeline.png';

  final String transState;         // 状态
  final String transTime;          // 时间
  final String transStateDesc;     // 描述
  final String imgPath1;
  final String imgPath2;

  static List<OrderLocusData> fromMapList(dynamic mapList) {
    List<OrderLocusData> list = new List(mapList.length);
    for (int i = 0; i < mapList.length; i++) {
      list[i] = OrderLocusData.fromJson(mapList[i]);
    }
    return list;
  }

}