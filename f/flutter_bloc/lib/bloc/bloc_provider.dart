
import 'package:flutter/material.dart';
import 'count_bloc.dart';

class BlocProvider extends InheritedWidget{

  CountBLoC bLoC = CountBLoC();


  BlocProvider({Key key, Widget child}) : super(key: key, child: child);




  //更新通知
  //这里updateShouldNotify需要传入一个InheritedWidget oldWidget，
  // 但是我们强制返回true，所以传一个“_”占位。
  @override
  bool updateShouldNotify(_) => true;


  static CountBLoC of(BuildContext context) =>
      (context.inheritFromWidgetOfExactType(BlocProvider) as BlocProvider).bLoC;

}