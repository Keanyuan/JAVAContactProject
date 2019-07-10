import 'package:redux/redux.dart';
import 'package:flutter/material.dart';

final themeDataReducer = combineReducers<ThemeData>([
  TypedReducer<ThemeData, RefreshThemeDataAction>(_refresh)
]);

ThemeData _refresh(ThemeData themeData, action){
  themeData = action.themeData;
  return themeData;
}

class RefreshThemeDataAction {
  final ThemeData themeData;
  RefreshThemeDataAction(this.themeData);
}