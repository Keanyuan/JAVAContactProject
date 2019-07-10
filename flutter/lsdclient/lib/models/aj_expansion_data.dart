
class AjExpansionData {
  AjExpansionData({
    this.iconPath,
    this.title,
    this.isExpanded,
    this.isLast : false,
  });

  String iconPath;
  String title;
  bool isExpanded;
  bool isLast;
}