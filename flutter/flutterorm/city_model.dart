
class CityInfo {
  String name;
  bool isSelect;

  CityInfo({
    this.name,
    this.isSelect = false
  });

  CityInfo.fromJson(Map<String, dynamic> json)
      : name = json['name'] == null ? "" : json['name'];

  Map<String, dynamic> toJson() => {
        'name': name,
        'isSelect': isSelect
      };

  @override
  String toString() =>
      """
      name : $name,
      isSelect : $isSelect
      """;
}
