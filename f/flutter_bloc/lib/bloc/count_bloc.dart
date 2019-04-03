
import 'dart:async';

class CountBLoC {
  int _count;
  StreamController<int> _countController;

  CountBLoC(){
    _count = 0;
    //初始化stream  改成广播流 可以多个监听
    _countController = StreamController<int>.broadcast();
  }

  Stream<int> get stream => _countController.stream;
  int get value => _count;

  dispose(){
    _countController.close();
  }



  ///事件
  increment(){
    //stream流处理
    _countController.sink.add(++_count);
  }

}