import 'package:flutter/material.dart';
import 'package:lsdclient/models/User.dart';
import 'package:lsdclient/reducer/theme_data_reducer.dart';
import 'package:lsdclient/reducer/user_reducer.dart';

class AJState {
  ///用户信息
  User userInfo;

  ///主题数据
  ThemeData themeData;

  AJState({this.userInfo, this.themeData});

}


AJState appReducer(AJState state, action){
  ///通过 UserReducer 将 GSYState 内的 userInfo 和 action 关联在一起
  return AJState(
    userInfo: userReducer(state.userInfo, action),
    themeData: themeDataReducer(state.themeData, action),
  );
}