import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:lsdclient/common/aj_config.dart';
import 'package:lsdclient/common/out_put.dart';
import 'package:lsdclient/models/screen_item_model.dart';
import 'package:lsdclient/tools/local_storage.dart';
import 'package:lsdclient/tools/object_utils.dart';
import 'package:lsdclient/widgets/aj_input_widget.dart';
import 'package:lsdclient/widgets/circular_progress_widget.dart';
import 'package:lsdclient/widgets/screen/screen_detail_bottom_widget.dart';
import 'package:lsdclient/widgets/screen/screen_item_widget.dart';

class ScreenListDetailPage extends StatefulWidget {
  final String title;
  final bool isRadio; //是否单选
  final List<ScreenItemModel> screenItemModelList;
  final List<ScreenItemModel> selectItemModelList;
  final String typeCode;
  final bool showFirstLetter;

  const ScreenListDetailPage(
      {Key key,
      this.title,
      this.isRadio = true,
      this.screenItemModelList,
      this.selectItemModelList,
      this.typeCode,
      this.showFirstLetter})
      : super(key: key);

  @override
  _ScreenListDetailPageState createState() => _ScreenListDetailPageState();
}

class _ScreenListDetailPageState extends State<ScreenListDetailPage> {
  final TextEditingController _searchController = new TextEditingController();
  String _searchValue = "";

//  List<List<ScreenItemModel>> _listAll = [];
  List<ScreenItemModel> _listAll = [];

  List<ScreenItemModel> _selectList = [];

  List<ScreenItemModel> _normalList = [];
  bool _isOperation = false;

  bool _isfocus = false;

  @override
  void initState() {
    super.initState();
    _searchController.value = TextEditingValue(text: _searchValue ?? "");
    _initStateData();
  }

  //搜索数据
  _searchStateData() {
    if (_searchValue.isEmpty) {
      setState(() {
        _isfocus = false;
      });
    } else {
      setState(() {
        _isfocus = true;
      });
    }
    List<ScreenItemModel> _tempList = [];
    _normalList.map((model) {
      if (model.name.contains(_searchValue)) {
        _tempList.add(model);
      }
    }).toList();
    _listAll = [];
    if (_tempList.length > 0) {
      _tempList.map((item) {
        item.isSelect = true;
        if (_selectList.contains(item)) {
          item.isSelect = true;
        } else {
          item.isSelect = false;
        }
        _listAll.add(item);
      }).toList();
    }
    AJLogUtil.v(_listAll.length);
    setState(() {});
  }

  ///深拷贝
  List<ScreenItemModel> _getItemsList(List<ScreenItemModel> list) {
    List<ScreenItemModel> copyList = [];
    list.map((item) {
      ScreenItemModel betContests = new ScreenItemModel(
          code: item.code,
          name: item.name,
          firstLetter: item.firstLetter,
          isSelect: item.isSelect);
      copyList.add(betContests);
    }).toList();
    return copyList;
  }

  //初始化
  _initStateData() async {
    if (widget.screenItemModelList == null ||
        widget.screenItemModelList.isEmpty) {
      setState(() {});
      return;
    }
    List<ScreenItemModel> _list = _getItemsList(widget.screenItemModelList);
    if (_list == null || _list.isEmpty) {
      setState(() {});
      return;
    }
    _normalList.addAll(_list);
    _selectList = [];

    AJLogUtil.v(widget.selectItemModelList,
        tag: "widget.selectItemModelList.length");

    if (widget.selectItemModelList == null ||
        widget.selectItemModelList.isEmpty) {
    } else {
      widget.selectItemModelList.map((item) {
        ScreenItemModel _screenTItemModel = ScreenItemModel(
            isSelect: item.isSelect,
            firstLetter: item.firstLetter,
            pinyin: item.pinyin,
            code: item.code,
            name: item.name);
        _selectList.add(_screenTItemModel);
      }).toList();
    }
    AJLogUtil.v(_selectList, tag: "_selectList");
    AJLogUtil.v(_selectList.length, tag: "_selectList.length");
    AJLogUtil.v(_normalList.length, tag: "_normalList.length");
    List<ScreenItemModel> _selectTmpList = [];
    _listAll = [];
    _normalList.map((item) {
      item.isSelect = false;
      _selectList.map((selectItem) {
        if (!ObjectUtils.isEmpty(selectItem.code) &&
            !ObjectUtils.isEmpty(item.code)) {
          if (selectItem.code == item.code && selectItem.isSelect) {
            item.isSelect = true;
            _selectTmpList.add(item);
          }
        }
      }).toList();
      _listAll.add(item);
    }).toList();

    _selectList = _selectTmpList;

    setState(() {});
  }

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        child: Scaffold(
          backgroundColor: AJColors.white,
          appBar: AppBar(
            brightness: Brightness.light,
            leading: IconButton(
                icon: Image.asset(
                  AJICons.ARROW_LEFT,
                  width: 18,
                ),
                onPressed: _backPress),
            elevation: 0,
            title: Text(
              widget.title,
              style: TextStyle(fontSize: 18, color: AJColors.black),
            ),
            centerTitle: true,
            bottom: PreferredSize(
                child: _headerSearchWidget(),
                preferredSize: Size(ScreenUtil.getScreenW(context), 50)),
          ),
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
    return Stack(
      children: <Widget>[
        _contentWidget(),
        _bottomWidget(),
      ],
    );
  }

  //中间部件
  _contentWidget() {
    return Positioned(
      left: 0,
      top: 0,
      bottom: 48.0 + 45.0,
      right: 0,
      child: _contentBuild(),
    );
  }

  _contentBuild() {
    if (_listAll.length == 0) {
      return EmptyWidget(desc: "暂无数据",descColor: "",);
    }

    return GridView.builder(
      gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
        //横轴元素个数
        crossAxisCount: 3,
        //纵轴间距
        mainAxisSpacing: 8,
        //横轴间距
        crossAxisSpacing: 4,
        //子组件宽高长度比例
        childAspectRatio: 9 / 4.0,
      ),
      itemBuilder: (BuildContext context, int index) {
        return ScreenItemWidget(
          screenItemModel: _listAll[index],
          onTap: () {
            _isOperation = true;
            ScreenItemModel _item = _listAll[index];
            if (widget.isRadio) {
              if (_selectList.length > 0 && _item.isSelect) {
//                _listAll.map((model) {
//                  if (model.isSelect) {
//                    model.isSelect = false;
//                  }
//                }).toList();
//                _selectList.clear();
//                _item.isSelect = false;
              } else {
                _listAll.map((model) {
                  if (model.isSelect) {
                    model.isSelect = false;
                  }
                }).toList();
                _selectList.clear();
                _item.isSelect = true;
                _selectList.add(_item);
              }
              setState(() {
                DialogUtil.showLoadingDialog(context, _onRadioSubmitTap())
                    .then((v) {
                  Navigator.pop(context, v);
                });
              });
            } else {
              _item.isSelect = !_item.isSelect;
              if (_item.isSelect) {
                _selectList.add(_item);
              } else {
                if (_selectList.contains(_item)) {
                  _selectList.remove(_item);
                }
              }
            }
            setState(() {});
          },
        );
      },
      itemCount: _listAll.length,
      padding: EdgeInsets.only(top: 10, right: 9, left: 9),
    );
  }

  //底部部件
  _bottomWidget() {
    return ScreenDetailBottomWidget(
      selectList: _selectList,
      onDeleteTap: (deleteModel) {
        _isOperation = true;
        _listAll.map((model) {
          if (deleteModel == model) {
            if (model.isSelect) {
              model.isSelect = false;
            }
          }
          _selectList.remove(deleteModel);
        }).toList();
        setState(() {});
      },
      onResetTap: () {
        _isOperation = true;
        _listAll.map((model) {
          if (model.isSelect) {
            model.isSelect = false;
          }
        }).toList();

        _selectList.clear();

        setState(() {});
        Code.errorHandle("重置成功");
      },
      onSubmitTap: _onSubmitTap,
    );
  }

  Future<List<ScreenItemModel>> _onRadioSubmitTap() async {
    List<Map<String, dynamic>> _listMap =
        ScreenItemModel.toMapList(_selectList);
    await LocalStorage.save(
        "${AJConfig.screen_local_storage}${widget.typeCode}temp",
        json.encode(_listMap));
    return _selectList;
  }

  _onSubmitTap() async {
    List<Map<String, dynamic>> _listMap =
        ScreenItemModel.toMapList(_selectList);
    await LocalStorage.save(
        "${AJConfig.screen_local_storage}${widget.typeCode}temp",
        json.encode(_listMap));
    Navigator.pop(context, _selectList);
  }

  _headerSearchWidget() {
    return Container(
      height: 60,
      decoration: BoxDecoration(color: AJColors.white, boxShadow: [
        BoxShadow(
          color: AJColors.shawColor,
          offset: Offset(0.0, 5.0),
          blurRadius: 10,
        ),
        BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
      ]),
      child: AJInputWidget(
        margin: EdgeInsets.only(left: 20, right: 20, top: 5, bottom: 10),
        hintText: "请输入查询的${widget.title}",
        isUnderline: false,
        controller: _searchController,
        outlineBorderRadius: 5,
        textInputAction: TextInputAction.search,
        prefixIcon: Container(
          child: Center(
            child: RawMaterialButton(
              materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
              constraints:
                  const BoxConstraints(minWidth: 30.0, minHeight: 30.0),
              child: Image.asset(
                "assets/images/input_search.png",
//              color: const Color(0xffE6E6E6),
                width: 16,
                height: 16,
              ),
            ),
          ),
          width: 60,
          height: 30,
        ),
        suffix: Container(
          child: Center(
            child: RawMaterialButton(
              highlightColor: AJColors.transparent,
              splashColor: AJColors.transparent,
              onPressed: _isfocus
                  ? () {
                      _searchValue = "";
                      _searchController.value =
                          new TextEditingValue(text: _searchValue ?? "");
                      _searchStateData();
                    }
                  : null,
              materialTapTargetSize: MaterialTapTargetSize.shrinkWrap,
              constraints:
                  const BoxConstraints(minWidth: 30.0, minHeight: 30.0),
              child: _isfocus
                  ? Image.asset(
                      "assets/images/input_delete.webp",
//              color: const Color(0xffE6E6E6),
                      width: 16,
                      height: 16,
                    )
                  : Container(),
            ),
          ),
          width: 60,
          height: 30,
        ),
        onChanged: (v) {
          _searchValue = v;
          //触发查询
          _searchStateData();
        },
        onSubmitted: (v) {
          _searchValue = v;

          _searchStateData();
        },
      ),
    );
  }
}
