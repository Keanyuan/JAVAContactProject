import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/dao/user_dao.dart';
import 'package:lsdclient/models/screen_item_model.dart';
import 'package:lsdclient/models/screen_list_model.dart';
import 'package:lsdclient/pages/home/screen_list_detail_page.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:lsdclient/tools/object_utils.dart';
import 'package:lsdclient/widgets/circular_progress_widget.dart';
import 'package:lsdclient/widgets/gradient_button.dart';
import 'package:lsdclient/widgets/screen/screen_home_item_widget.dart';
import 'package:rxdart/rxdart.dart';

class ScreenListPage extends StatefulWidget {
  @override
  _ScreenListPageState createState() => _ScreenListPageState();
}

class _ScreenListPageState extends State<ScreenListPage> {
  ScreenListModel _screenListModel = ScreenListModel();
  ScreenInfoModel _screenInfoModel = ScreenInfoModel();

  int _reqCount = 0;
  bool _isOperation = false;

  @override
  void initState() {
    super.initState();
    _initData();
  }

  _initData() async {
    _screenInfoModel = ScreenInfoModel();
    _reqCount = 0;
    NavigatorUtils.typeCodeList.map((typeCode) async {
      var res = await UserDao.getScreenListInfo(typeCode);
      _reqCount++;
      if (res != null && res.result) {
        ScreenListRepModel _screenListRepModel;
        if (res.data is ScreenListRepModel) {
          _screenListRepModel = res.data;
        }

        AJLogUtil.v("------>>   ${_screenListRepModel.screenList.length}");
        var info = await LocalStorage.get(
            "${AJConfig.screen_local_storage}${typeCode}");

        List<dynamic> _listMap = [];
        if (info != null) {
          _listMap = json.decode(info);
          await LocalStorage.save(
              "${AJConfig.screen_local_storage}${typeCode}temp",
              json.encode(_listMap));
        }

        switch (typeCode) {
          case "customer":
            _screenListModel.transportlist001 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter001 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect001 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList001 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr001 =
                  _getSelectNameStr(_screenListModel.selectList001);
            }
            break;
          case "transfertype":
            _screenListModel.transportlist002 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter002 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect002 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList002 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr002 =
                  _getSelectNameStr(_screenListModel.selectList002);
            }
            break;
          case "sendsrc":
            _screenListModel.transportlist003 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter003 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect003 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList003 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr003 =
                  _getSelectNameStr(_screenListModel.selectList003);
            }
            break;
          case "transsupplier":
            _screenListModel.transportlist004 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter004 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect004 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList004 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr004 =
                  _getSelectNameStr(_screenListModel.selectList004);
            }
            break;
          case "alarmlevel":
            _screenListModel.transportlist0010 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter0010 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect0010 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList0010 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr0010 =
                  _getSelectNameStr(_screenListModel.selectList0010);
            }
            break;
          case "delaytime":
            _screenListModel.transportlist006 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter006 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect006 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList006 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr006 =
                  _getSelectNameStr(_screenListModel.selectList006);
            }
            break;
          case "retailer":
            _screenListModel.transportlist007 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter007 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect007 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList007 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr007 =
                  _getSelectNameStr(_screenListModel.selectList007);
            }
            break;
          case "logicarea":
            _screenListModel.transportlist008 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter008 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect008 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList008 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr008 =
                  _getSelectNameStr(_screenListModel.selectList008);
            }
            break;
          case "ordertype":
            _screenListModel.transportlist009 = _screenListRepModel.screenList;
            _screenListModel.showFirstLetter009 =
                _screenListRepModel.showFirstLetter;
            _screenListModel.isMultiSelect009 =
                _screenListRepModel.isMultiSelect;
            if (info != null) {
              _screenListModel.selectList009 =
                  ScreenItemModel.fromMapList(_listMap);
              _screenListModel.selectStr009 =
                  _getSelectNameStr(_screenListModel.selectList009);
            }
            break;
          default:
            break;
        }
      }
      if (!mounted) return;
      setState(() {});
    }).toList();
    if (!mounted) return;
    setState(() {});
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        child: Scaffold(
          appBar: CustomAppBar.getAppBar(context, "筛选",
              backPressed: _backPress,
              actions: [
                Container(
                  width: 60,
                  child: InkWell(
                    onTap: () {
                      if (_reqCount != NavigatorUtils.typeCodeList.length)
                        return;
                      _resetAllInfo();
                    },
                    child: Container(
                      alignment: Alignment.center,
                      child: Text(
                        "重置",
                        style: AJConstant.listDescTextStyle,
                      ),
                    ),
                  ),
                ),
              ]),
          body: SafeArea(child: _body()),
        ),
        onWillPop: () {
          _backPress();
        });
  }

  _backPress() {
    if (_isOperation) {
      DialogUtil.dialogConfigApp(context, "是否放弃选择？");
    } else {
      Navigator.pop(context);
    }
  }

  _body() {
    if (_reqCount != NavigatorUtils.typeCodeList.length) {
      return CircularProgressWidget();
    }

    return Stack(
      children: <Widget>[
        ListView(
          padding: EdgeInsets.only(
              left: 17,
              right: 17,
              top: 10,
              bottom: 52 + 20.0 + ScreenUtil.getBottomBarH(context)),
          children: <Widget>[
            ScreenHomeItemWidget(
              imageAssetName: AJICons.customer_distribution_list,
              title: "客户",
              checkInfo: _screenListModel.selectStr001,
              onTap: () {
                if (_screenListModel.transportlist001 == null ||
                    _screenListModel.transportlist001.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "客户",
                          isRadio: !_screenListModel.isMultiSelect001,
                          screenItemModelList:
                              _screenListModel.transportlist001,
                          selectItemModelList: _screenListModel.selectList001,
                          showFirstLetter: _screenListModel.showFirstLetter001,
                          typeCode: "customer",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "customer");
                  }
                });
                setState(() {});
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.transportation_way_list,
              title: "运输方式",
              checkInfo: _screenListModel.selectStr002,
              onTap: () {
                if (_screenListModel.transportlist002 == null ||
                    _screenListModel.transportlist002.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "运输方式",
                          isRadio: !_screenListModel.isMultiSelect002,
                          screenItemModelList:
                              _screenListModel.transportlist002,
                          selectItemModelList: _screenListModel.selectList002,
                          showFirstLetter: _screenListModel.showFirstLetter002,
                          typeCode: "transfertype",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "transfertype");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.logicarea,
              title: "运作分区",
              checkInfo: _screenListModel.selectStr008,
              onTap: () {
                if (_screenListModel.transportlist008 == null ||
                    _screenListModel.transportlist008.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "运作分区",
                          isRadio: !_screenListModel.isMultiSelect008,
                          screenItemModelList:
                              _screenListModel.transportlist008,
                          selectItemModelList: _screenListModel.selectList008,
                          showFirstLetter: _screenListModel.showFirstLetter008,
                          typeCode: "logicarea",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "logicarea");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.sendsrc,
              title: "发货仓库",
              checkInfo: _screenListModel.selectStr003,
              onTap: () {
                if (_screenListModel.transportlist003 == null ||
                    _screenListModel.transportlist003.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "发货仓库",
                          isRadio: !_screenListModel.isMultiSelect003,
                          screenItemModelList:
                              _screenListModel.transportlist003,
                          selectItemModelList: _screenListModel.selectList003,
                          showFirstLetter: _screenListModel.showFirstLetter003,
                          typeCode: "sendsrc",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "sendsrc");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.transportation_supply_list,
              title: "运输供方",
              checkInfo: _screenListModel.selectStr004,
              onTap: () {
                if (_screenListModel.transportlist004 == null ||
                    _screenListModel.transportlist004.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "运输供方",
                          isRadio: !_screenListModel.isMultiSelect004,
                          screenItemModelList:
                              _screenListModel.transportlist004,
                          selectItemModelList: _screenListModel.selectList004,
                          showFirstLetter: _screenListModel.showFirstLetter004,
                          typeCode: "transsupplier",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "transsupplier");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.alarm_his,
              title: "预警级别",
              checkInfo: _screenListModel.selectStr0010,
              onTap: () {
                if (_screenListModel.transportlist0010 == null ||
                    _screenListModel.transportlist0010.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "预警级别",
                          isRadio: !_screenListModel.isMultiSelect0010,
                          screenItemModelList:
                              _screenListModel.transportlist0010,
                          selectItemModelList: _screenListModel.selectList0010,
                          showFirstLetter: _screenListModel.showFirstLetter0010,
                          typeCode: "alarmlevel",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "alarmlevel");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.delaytime,
              title: "延期天数",
              checkInfo: _screenListModel.selectStr006,
              onTap: () {
                if (_screenListModel.transportlist006 == null ||
                    _screenListModel.transportlist006.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "延期天数",
                          isRadio: !_screenListModel.isMultiSelect006,
                          screenItemModelList:
                              _screenListModel.transportlist006,
                          selectItemModelList: _screenListModel.selectList006,
                          showFirstLetter: _screenListModel.showFirstLetter006,
                          typeCode: "delaytime",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "delaytime");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.ordertype,
              title: "订单类型",
              checkInfo: _screenListModel.selectStr009,
              onTap: () {
                if (_screenListModel.transportlist009 == null ||
                    _screenListModel.transportlist009.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "订单类型",
                          isRadio: !_screenListModel.isMultiSelect009,
                          screenItemModelList:
                              _screenListModel.transportlist009,
                          selectItemModelList: _screenListModel.selectList009,
                          showFirstLetter: _screenListModel.showFirstLetter009,
                          typeCode: "ordertype",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "ordertype");
                  }
                });
              },
            ),
            ScreenHomeItemWidget(
              imageAssetName: AJICons.retailer,
              title: "经销商",
              checkInfo: _screenListModel.selectStr007,
              onTap: () {
                if (_screenListModel.transportlist007 == null ||
                    _screenListModel.transportlist007.length == 0) {
                  Code.errorHandle("暂无数据");
                  return;
                }
                NavigatorUtils.pushTO(context,
                        child: ScreenListDetailPage(
                          title: "经销商",
                          isRadio: !_screenListModel.isMultiSelect007,
                          screenItemModelList:
                              _screenListModel.transportlist007,
                          selectItemModelList: _screenListModel.selectList007,
                          showFirstLetter: _screenListModel.showFirstLetter007,
                          typeCode: "retailer",
                        ),
                        needTransition: true)
                    .then((v) {
                  if (v != null) {
                    _changeSelectInfo(v, "retailer");
                  }
                });
              },
            ),
          ],
        ),
        Positioned(
            bottom: 10,
            left: 20,
            right: 20,
            child: Container(
              child: GradientContainer(
                colors: [
                  AJColors.buttonNormalColor,
                  AJColors.buttonNormalColor,
                  AJColors.buttonNormalColor
                ],
                onTap: _submitAction,
                height: 52,
                radius: 52,
                child: Center(
                  child: Text(
                    "确认",
                    style: AJConstant.loginWhiteStyle,
                  ),
                ),
              ),
            ))
      ],
    );
  }

  _changeSelectInfo(v, String typeCode) {
    _isOperation = true;

    String _nameList = "";
    List _codeList = [];

    List<ScreenItemModel> _selectList = [];
    _selectList = v;
    _selectList.map((model) {
      _nameList += "${model.name},";
      _codeList.add(model.code);
    }).toList();
    if (_nameList.length > 0) {
      _nameList = _nameList.substring(0, _nameList.length - 1);
    }

    switch (typeCode) {
      case "customer":
        _screenListModel.selectList001 = _selectList;
        _screenListModel.selectStr001 = _nameList;
        _screenInfoModel.customerIdList = _codeList;
        break;
      case "transfertype":
        _screenListModel.selectList002 = _selectList;
        _screenListModel.selectStr002 = _nameList;
        _screenInfoModel.transCodeList = _codeList;
        break;
      case "sendsrc":
        _screenListModel.selectList003 = _selectList;
        _screenListModel.selectStr003 = _nameList;
        _screenInfoModel.orderSrcWhCodeList = _codeList;
        break;
      case "transsupplier":
        _screenListModel.selectList004 = _selectList;
        _screenListModel.selectStr004 = _nameList;
        _screenInfoModel.transSupplierCodeList = _codeList;
        break;
      case "alarmlevel":
        _screenListModel.selectList0010 = _selectList;
        _screenListModel.selectStr0010 = _nameList;
        _screenInfoModel.nodeAlertLevelList = _codeList;
        break;
      case "delaytime":
        _screenListModel.selectList006 = _selectList;
        _screenListModel.selectStr006 = _nameList;
        _screenInfoModel.delayTimeList = _codeList;
        break;
      case "retailer":
        _screenListModel.selectList007 = _selectList;
        _screenListModel.selectStr007 = _nameList;
        _screenInfoModel.retailerIdList = _codeList;
        break;
      case "logicarea":
        _screenListModel.selectList008 = _selectList;
        _screenListModel.selectStr008 = _nameList;
        _screenInfoModel.logicAreaCodeList = _codeList;
        break;
      case "ordertype":
        _screenListModel.selectList009 = _selectList;
        _screenListModel.selectStr009 = _nameList;
        _screenInfoModel.orderTypeList = _codeList;
        break;
      default:
        break;
    }

    setState(() {});
  }

  String _getSelectNameStr(selectList) {
    String _nameList = "";
    List<ScreenItemModel> _selectList = [];
    _selectList = selectList;
    _selectList.map((model) {
      _nameList += "${model.name},";
    }).toList();
    if (_nameList.length > 0) {
      _nameList = _nameList.substring(0, _nameList.length - 1);
    }
    return _nameList;
  }

  List _getSelectCodeStr(selectList) {
    List _codeList = [];
    List<ScreenItemModel> _selectList = [];
    _selectList = selectList;
    if (!ObjectUtils.isEmpty(_selectList)) {
      _selectList.map((model) {
        _codeList.add(model.code);
      }).toList();
    }

    return _codeList;
  }

  _resetAllInfo() {
    _isOperation = true;

    NavigatorUtils.typeCodeList.map((typeCode) async {
      List<Map<String, dynamic>> _listMap = [];
      _screenInfoModel = ScreenInfoModel();
      await LocalStorage.save(
          "${AJConfig.screen_local_storage}${typeCode}", json.encode(_listMap));
      await LocalStorage.save("${AJConfig.screen_local_storage}${typeCode}temp",
          json.encode(_listMap));
      await LocalStorage.save(AJConfig.screen_info_local_storage,
          json.encode(_screenInfoModel.toMap()));
    }).toList();
    Code.errorHandle("重置成功");
    _initData();
  }

  _submitAction() {
    int _tmpCount = 0;
    NavigatorUtils.typeCodeList.map((typeCode) async {
      _tmpCount++;
      List<dynamic> _listMap = [];
      List<dynamic> _templistMap = [];
      var info = await LocalStorage.get(
          "${AJConfig.screen_local_storage}${typeCode}temp");
      if (info != null) {
        _listMap = json.decode(info);
      }
      switch (typeCode) {
        case "customer":
          _screenInfoModel.customerIdList =
              _getSelectCodeStr(_screenListModel.selectList001);
          break;
        case "transfertype":
          _screenInfoModel.transCodeList =
              _getSelectCodeStr(_screenListModel.selectList002);
          break;
        case "sendsrc":
          _screenInfoModel.orderSrcWhCodeList =
              _getSelectCodeStr(_screenListModel.selectList003);
          break;
        case "transsupplier":
          _screenInfoModel.transSupplierCodeList =
              _getSelectCodeStr(_screenListModel.selectList004);
          break;
        case "alarmlevel":
          _screenInfoModel.nodeAlertLevelList =
              _getSelectCodeStr(_screenListModel.selectList0010);
          break;
        case "delaytime":
          _screenInfoModel.delayTimeList =
              _getSelectCodeStr(_screenListModel.selectList006);
          break;
        case "retailer":
          _screenInfoModel.retailerIdList =
              _getSelectCodeStr(_screenListModel.selectList007);
          break;
        case "logicarea":
          _screenInfoModel.logicAreaCodeList =
              _getSelectCodeStr(_screenListModel.selectList008);
          break;
        case "ordertype":
          _screenInfoModel.orderTypeList =
              _getSelectCodeStr(_screenListModel.selectList009);
          break;
        default:
          break;
      }
      await LocalStorage.save(
          "${AJConfig.screen_local_storage}${typeCode}", json.encode(_listMap));
      await LocalStorage.save("${AJConfig.screen_local_storage}${typeCode}temp",
          json.encode(_templistMap));
      await LocalStorage.save(AJConfig.screen_info_local_storage,
          json.encode(_screenInfoModel.toMap()));
    }).toList();

    Observable.just(1).delay(Duration(milliseconds: 500)).listen((_) async {
      Navigator.pop(context, "a");
    });
  }
}
