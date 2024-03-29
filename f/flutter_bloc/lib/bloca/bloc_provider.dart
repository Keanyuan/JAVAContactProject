import 'package:flutter/material.dart';


//所有 BLoCd的通用接口
abstract class BlocBase {
  void dispose();
}

// 通用 BLoC provider
class BlocProvider<T extends BlocBase> extends StatefulWidget{

  BlocProvider({
    Key key,
    @required this.child,
    @required this.bloc,
  }) : super(key: key);
  final T bloc;
  final Widget child;


  @override
  _BlocProviderState<T> createState() => _BlocProviderState<T>();

  static T of<T extends BlocBase>(BuildContext context){
    final type = _typeOf<BlocProvider<T>>();
    BlocProvider<T> provider = context.ancestorWidgetOfExactType(type);
    return provider.bloc;
  }

  static Type _typeOf<T>() => T;
}

class _BlocProviderState<T> extends State<BlocProvider<BlocBase>>{
  @override
  Widget build(BuildContext context) {
    return widget.child;
  }

  @override
  void dispose() {
    widget.bloc.dispose();
    super.dispose();
  }

}