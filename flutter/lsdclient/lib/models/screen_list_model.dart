
import 'package:lsdclient/models/screen_item_model.dart';
class ScreenListRepModel {
  List<ScreenItemModel> screenList;
  bool showFirstLetter;
  bool isMultiSelect;
  ScreenListRepModel({this.screenList, this.showFirstLetter});


}
class ScreenListModel {
  List<ScreenItemModel> transportlist001; //客户 多选
  List<ScreenItemModel> selectList001;
  String selectStr001;
  bool showFirstLetter001;
  bool isMultiSelect001; //是否多选
  List<ScreenItemModel> transportlist002; //运输方式
  List<ScreenItemModel> selectList002;
  String selectStr002;
  bool showFirstLetter002;
  bool isMultiSelect002; //是否多选
  List<ScreenItemModel> transportlist003; //发货仓库 多选
  List<ScreenItemModel> selectList003;
  String selectStr003;
  bool showFirstLetter003;
  bool isMultiSelect003; //是否多选
  List<ScreenItemModel> transportlist004; //运输供方 多选
  List<ScreenItemModel> selectList004;
  String selectStr004;
  bool showFirstLetter004;
  bool isMultiSelect004; //是否多选
//  List<ScreenItemModel> transportlist005; //预警类型
//  List<ScreenItemModel> selectList005;
//  String selectStr005;
//  bool showFirstLetter005;
//  bool isMultiSelect005; //是否多选
  List<ScreenItemModel> transportlist006; //延期天数
  List<ScreenItemModel> selectList006;
  String selectStr006;
  bool showFirstLetter006;
  bool isMultiSelect006; //是否多选
  List<ScreenItemModel> transportlist007; //经销商
  List<ScreenItemModel> selectList007;
  String selectStr007;
  bool showFirstLetter007;
  bool isMultiSelect007; //是否多选
  List<ScreenItemModel> transportlist008; //分区
  List<ScreenItemModel> selectList008;
  String selectStr008;
  bool showFirstLetter008;
  bool isMultiSelect008; //是否多选
  List<ScreenItemModel> transportlist009; //订单类型
  List<ScreenItemModel> selectList009;
  String selectStr009;
  bool showFirstLetter009;
  bool isMultiSelect009; //是否多选

  List<ScreenItemModel> transportlist0010; //预警类型
  List<ScreenItemModel> selectList0010;
  String selectStr0010;
  bool showFirstLetter0010;
  bool isMultiSelect0010; //是否多选

  ScreenListModel({
    this.transportlist001,
    this.selectList001,
    this.selectStr001,
    this.transportlist002,
    this.selectList002,
    this.selectStr002,
    this.transportlist003,
    this.selectList003,
    this.selectStr003,
    this.transportlist004,
    this.selectList004,
    this.selectStr004,
//    this.transportlist005,
//    this.selectList005,
//    this.selectStr005,
    this.transportlist006,
    this.selectList006,
    this.selectStr006,
    this.transportlist007,
    this.selectList007,
    this.selectStr007,
    this.transportlist008,
    this.selectList008,
    this.selectStr008,
    this.transportlist009,
    this.selectList009,
    this.selectStr009,
    this.transportlist0010,
    this.selectList0010,
    this.selectStr0010,

  });
}



class ScreenInfoModel {
  List<dynamic> customerIdList; //客户ID
  List<dynamic> transCodeList; //运输方式
  List<dynamic> orderSrcWhCodeList; //发货仓库
  List<dynamic> transSupplierCodeList; //运输供方
//  List<dynamic> alarmTypeCodeList; //预警类型
  List<dynamic> delayTimeList; //延期天数
  List<dynamic> orderTypeList; //订单类型
  List<dynamic> logicAreaCodeList; //运作分区
  List<dynamic> retailerIdList; //经销商
  List<dynamic> nodeAlertLevelList; //预警类型级别


  ScreenInfoModel({this.customerIdList=const[], this.transCodeList=const[],
    this.orderSrcWhCodeList=const[], this.transSupplierCodeList=const[],
    this.nodeAlertLevelList=const[], this.delayTimeList=const[], this.orderTypeList=const[],
    this.logicAreaCodeList=const[], this.retailerIdList=const[]});

  Map<String, dynamic> toMap() {
    var map = new Map<String, dynamic>();
    map['customerIdList'] = customerIdList;
    map['transCodeList'] = transCodeList;
    map['orderSrcWhCodeList'] = orderSrcWhCodeList;
    map['transSupplierCodeList'] = transSupplierCodeList;
//    map['alarmTypeCodeList'] = alarmTypeCodeList;
    map['orderTypeList'] = orderTypeList;
    map['logicAreaCodeList'] = logicAreaCodeList;
    map['retailerIdList'] = retailerIdList;
    map['delayTimeList'] = delayTimeList;
    map['nodeAlertLevelList'] = nodeAlertLevelList;
    return map;
  }



  static ScreenInfoModel fromMap(Map<String, dynamic> map) {
    ScreenInfoModel screenInfoModel = new ScreenInfoModel();
    screenInfoModel.customerIdList = map["customerIdList"];
    screenInfoModel.transCodeList = map["transCodeList"];
    screenInfoModel.orderSrcWhCodeList = map["orderSrcWhCodeList"];
    screenInfoModel.transSupplierCodeList = map["transSupplierCodeList"];
//    screenInfoModel.alarmTypeCodeList = map["alarmTypeCodeList"];
    screenInfoModel.orderTypeList = map["orderTypeList"];
    screenInfoModel.logicAreaCodeList = map["logicAreaCodeList"];
    screenInfoModel.retailerIdList = map["retailerIdList"];
    screenInfoModel.delayTimeList = map["delayTimeList"];
    screenInfoModel.nodeAlertLevelList = map["nodeAlertLevelList"];

    return screenInfoModel;
  }

  @override
  String toString() {
    return
      """
      "customerIdList" : $customerIdList,
      "transCodeList" : $transCodeList,
      "orderSrcWhCodeList" : $orderSrcWhCodeList,
      "transSupplierCodeList" : $transSupplierCodeList,
      "nodeAlertLevelList" : $nodeAlertLevelList,
      "orderTypeList" : $orderTypeList,
      "logicAreaCodeList" : $logicAreaCodeList,
      "retailerIdList" : $retailerIdList,
      "delayTimeList" : $delayTimeList
      """;
  }
}
