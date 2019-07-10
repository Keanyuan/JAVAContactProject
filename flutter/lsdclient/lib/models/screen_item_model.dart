class ScreenItemModel {
  String name;
  bool isSelect;
  String code;
  String firstLetter;
  String pinyin;

  ScreenItemModel(
      {this.firstLetter, this.name, this.code, this.pinyin, this.isSelect});

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map['code'] = code;
    map['firstLetter'] = firstLetter;
    map['name'] = name;
    map['pinyin'] = pinyin;
    map['isSelect'] = isSelect ? "1" : "0";
    return map;
  }

  static ScreenItemModel fromMap(Map<String, dynamic> map) {
    ScreenItemModel screenItemModel = new ScreenItemModel();
    screenItemModel.code = "${map["code"]}";
    screenItemModel.firstLetter = map["firstLetter"];
    screenItemModel.pinyin = map["pinyin"];
    screenItemModel.name = map["name"];
    screenItemModel.isSelect = "${map["isSelect"] ?? ""}" == "1" ? true : false;
    return screenItemModel;
  }

  static List<Map<String, dynamic>> toMapList(List<ScreenItemModel> modelList) {
    List<Map<String, dynamic>> list = new List(modelList.length);
    for (int i = 0; i < modelList.length; i++) {
      list[i] = modelList[i].toMap();
    }
    return list;
  }

  static List<ScreenItemModel> fromMapList(dynamic mapList) {
    List<ScreenItemModel> list = new List(mapList.length);
    for (int i = 0; i < mapList.length; i++) {
      list[i] = fromMap(mapList[i]);
    }

//    sortListBySuspensionTag(list);
    return list;
  }

  @override
  String toString() {
    // TODO: implement toString
    return """
    {
      code : $code,
      firstLetter : $firstLetter,
      name : $name,
      isSelect : $isSelect,
      pinyin: $pinyin
    },
    """;
  }

  static void sortListBySuspensionTag(List<ScreenItemModel> list) {
    if (list == null || list.isEmpty) return;
    list.sort((a, b) => a.firstLetter.compareTo(b.firstLetter));
  }
}
