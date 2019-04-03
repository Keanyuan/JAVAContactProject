
import 'package:flutter_bloc/bloca/bloc_provider.dart';
import 'dart:async';

class IncrementBloc implements BlocBase{
  int _counter;
  // 处理counter的stream
  StreamController<int> _counterController = StreamController<int>();
  StreamSink<int> get _inAdd => _counterController.sink;
  Stream<int> get outCounter => _counterController.stream;

  // 处理业务逻辑的stream
  StreamController _actionController = StreamController();
  StreamSink get incrementCounter => _actionController.sink;

  // 构造器
  IncrementBloc(){
    _counter = 0;
    //监听
    _actionController.stream.listen(_handleLogic);
  }

  get value => _counter;

  //添加
  void _handleLogic(data){
    _counter = _counter + 1;
    print("add");
    _inAdd.add(_counter);
  }
  @override
  void dispose() {
    _actionController.close();
    _counterController.close();
  }

}