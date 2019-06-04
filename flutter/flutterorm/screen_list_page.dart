import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_bloc/demo/city_model.dart';

class ScreenListPage extends StatefulWidget {
  final List<CityInfo> citySlectList;

  final List<CityInfo> cityList;

  const ScreenListPage({Key key, this.citySlectList, this.cityList}) : super(key: key);

  @override
  _ScreenListPageState createState() => _ScreenListPageState();
}

class _ScreenListPageState extends State<ScreenListPage> {
  List<CityInfo> _cityList = List();
  List<CityInfo> _citySlectList = List();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _citySlectList = widget.citySlectList;
    _loadData();
  }

  _loadData() {
    _cityList = List();
    widget.cityList.map((v){
      CityInfo _cityInfo = CityInfo(name: v.name, isSelect: v.isSelect);
      widget.citySlectList.map((s){
        if(_cityInfo.name == s.name){
          _cityInfo.isSelect = s.isSelect;
        }
      }).toList();
      _cityList.add(_cityInfo);
    }).toList();
    setState(() {
    });

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("列表"),
        leading: IconButton(icon: Icon(Icons.arrow_back_ios), onPressed: (){
          Navigator.of(context).pop(_citySlectList);
        }),
      ),
      body: ListView.builder(
        itemBuilder: (BuildContext context, int index) {
          return Card(
            child: ListTile(
              title: Text('${_cityList[index].name}'),
              selected: true,
              contentPadding: EdgeInsets.all(10),
              trailing: _cityList[index].isSelect ? Icon(Icons.assistant_photo) : Container(width: 10, height: 10,),
              onTap: () {
                _cityList[index].isSelect = !_cityList[index].isSelect;
                print(_cityList[index].hashCode);

                if(_cityList[index].isSelect){
                  _citySlectList.add(_cityList[index]);
                } else {
                  _cityList[index].isSelect = true;
                  _citySlectList.remove(_cityList[index]);
                  _cityList[index].isSelect = false;
                }
                setState(() {
                });
              },
            ),
          );
        },
        itemCount: _cityList?.length ?? 0 ,
      ),
    );
  }
}
