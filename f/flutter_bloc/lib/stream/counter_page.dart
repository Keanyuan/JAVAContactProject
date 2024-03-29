
import 'package:flutter/material.dart';
import 'dart:async';


class CounterPage extends StatefulWidget {
  @override
  _CounterPageState createState() => _CounterPageState();
}

class _CounterPageState extends State<CounterPage> {

  int _counter = 0;
  final StreamController<int> _streamController = StreamController<int>();

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(title: Text("Stream version of the Counter App"),),
      body: Center(
        child: StreamBuilder<int>(
          // 监听Stream，每次值改变的时候，更新Text中的内容
        stream: _streamController.stream,
          initialData: _counter,
          builder: (BuildContext context, AsyncSnapshot<int> snapshot){
            return Text("You hit me: ${snapshot.data} times");
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          // 每次点击按钮，更加_counter的值，同时通过Sink将它发送给Stream；
          // 每注入一个值，都会引起StreamBuilder的监听，StreamBuilder重建并刷新counter
          _streamController.sink.add(++_counter);
        },
        child: const Icon(Icons.add),
      ),
    );
  }
  
  @override
  void dispose() {
    _streamController.close();
    super.dispose();
  }
}
