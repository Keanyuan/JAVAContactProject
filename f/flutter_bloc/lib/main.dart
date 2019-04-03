import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter_bloc/bloca/application_bloc.dart';
import 'package:flutter_bloc/bloca/bloc_provider.dart';
import 'package:flutter_bloc/bloca/counter_page.dart';
import 'package:flutter_bloc/bloca/increment_bloc.dart';
import 'package:flutter_bloc/demo/auto_input_page.dart';
import 'package:flutter_bloc/demo/input_chips_page.dart';
import 'package:flutter_bloc/demo/simple_login_page.dart';
//import 'package:flutter_bloc/bloc/bloc_provider.dart';
//import 'package:flutter_bloc/bloc/top_page.dart';
import 'package:flutter_bloc/scoped/count_model.dart';
import 'package:flutter_bloc/scoped/top_page.dart';
//import 'package:flutter_bloc/stream/counter_page.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:rxdart/rxdart.dart';

void main(){

  var command = 'NOW_OPEN';
  switch(command){
    case 'CLOSED':
      print('CLOSED');
      continue nowClosed;
    nowClosed:
    case 'NOW_OPEN':
      print('NOW_OPEN');
      break;
    case 'NOW_CLOSED':
      print('NOW_CLOSED');
      break;
  }

  //122 158
  //radix代表转换 x进制转换十进制
  print(int.parse('422', radix: 10));





//  RXDartDemo.rxdartOnservable();
  return runApp(
    BlocProvider<ApplicationBloc>(
        child: MyApp(),
        bloc: ApplicationBloc(),
    )
  );
}


class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: SimpleLoginPage(),
    );
  }
}

class MyApp2 extends StatelessWidget {
  CountModel countModel = CountModel();
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: BlocProvider<IncrementBloc>(
        bloc: IncrementBloc(),
        child: CounterPage(),
      ),
    );
  }
}


class MyApp1 extends StatelessWidget {
  CountModel countModel = CountModel();
  @override
  Widget build(BuildContext context) {
    return ScopedModel<CountModel>(
      model: countModel,
      child: MaterialApp(
        title: 'Flutter Demo',
        theme: ThemeData(
          primarySwatch: Colors.blue,
        ),
        home: TopPage(),
      ),
    );
  }
}

//class MyApp1 extends StatelessWidget {
//  // This widget is the root of your application.
//  @override
//  Widget build(BuildContext context) {
//    return BlocProvider(
//      child: MaterialApp(
//        title: 'Flutter Demo',
//        theme: ThemeData(
//          primarySwatch: Colors.blue,
//        ),
//        home: TopPage(),
//      ),
//    );
//  }
//}




class RXDartDemo {
  static rxdartOnservable(){
    /*
//    你可以把stream直接包装成Observable
    var obs = Observable(Stream.fromIterable([1,2,3,4,5]));

    obs.listen(print);


    //    通过Future创建：fromFuture
    var obs1 = Observable.fromFuture(new Future.value("hello"));
    obs1.listen(print);

//    通过Iterable创建：fromIterable
    var obs2 = Observable.fromIterable([1,2,3,4,5]);
    obs2.listen(print);

//    interval方法能够让流“吐出数据”后间隔一段时间再吐下一个数据。
    var obs3 = Observable(Stream.fromIterable([1,2,3,4,5]))
    .interval(new Duration(seconds: 1));
    obs3.listen(print);


//    map方法能够让我们迭代的处理每一个数据并返回一个新的数据
    var obs4 = Observable(Stream.fromIterable([1,2,3,4,5]))
        .map((item) => ++item);
    obs4.listen(print);


    //expand方法能够让我们把把每个item扩展至多个流
    var obs5 = Observable(Stream.fromIterable([1,2,3,4,5]))
        .expand((item) => [item, item.toDouble()]);
    obs5.listen(print);


//    merge方法能够让我们合并多个流,请注意输出。
    var obs6 = Observable.merge([
      Stream.fromIterable([1,2,3]),
      Stream.fromIterable([4,5,6]),
      Stream.fromIterable([7,8,9]),
    ]);
    obs6.listen(print);

//    concat方法能够让我们按照顺序执行一组流，当一组流执行完毕后，再开始执行下一组。
    var obs7 = Observable.concat([
      Stream.fromIterable([1,2,3]),
      Stream.fromIterable([4,5,6]),
      Stream.fromIterable([7,8,9]),
    ]);
    obs7.listen(print);


    //every会检查每个item是否符合要求，
    // 然后它将会返回一个能够被转化为
    // Observable 的 AsObservableFuture< bool>。
    var obs8 = Observable.fromIterable([1,2,3,4,5,12]);
    obs8.every((x) => x < 10).asObservable().listen(print);


    //使用工厂方法或者 asBroadcastStream 将其转化为多订阅流。
    var obs9 = Observable(Stream.fromIterable([1,2,3,4,5]))
        .asBroadcastStream();
    obs9.listen(print);
    obs9.listen(print);
    obs9.listen(print);


    var obs10 = Observable(Stream.fromIterable([1,2,3,4,5]))
        .asBroadcastStream();
    //第一个订阅者
    obs10.interval(Duration(seconds: 1))
        .map((item) => ++item)
        .listen((v) => print("第一个订阅者$v"));
    //第二个订阅者
    obs10.listen((v) => print("第二个订阅者$v"));

    //缓存最新一次事件的广播流控制器：BehaviorSubject
    final subject = new BehaviorSubject<int>();
    subject.add(1);
    subject.add(2);
    subject.add(3);
//    由于我们在add(3)之后才开始收听，所以将会收到最新的value。
    subject.stream.listen(print);
    subject.stream.listen(print);
    subject.stream.listen(print);
    subject.close();

    */

    //缓存更多事件的广播流控制器：ReplaySubject
    //maxSize控制缓存个数
    final subject = new ReplaySubject<int>(maxSize: 2);
    subject.add(1);
    subject.add(2);
    subject.add(3);
    subject.stream.listen(print);
    subject.stream.listen(print);
    subject.stream.listen(print);

    subject.close();




  }


}

class streamDemo{
  static streamSink(){
    //初始化 订阅一个 streamController
    final StreamController strl = StreamController();

    //初始化监听
    final StreamSubscription subscription = strl.stream.
    listen((data)=>print(data));



    //网stream添加数据
    strl.sink.add("my name ");
    strl.sink.add(1234);
    strl.sink.add({"a" : " element A", "b" : "element b"});
    strl.sink.add(13.45);


    strl.close();
  }

  static streamTransformer(){

    // 初始化一个int类型的广播Stream controller
    final StreamController<int> strl = StreamController<int>
    .broadcast();

    // 初始化一个监听，同时通过transform对数据进行简单处理
    final StreamSubscription subscription = strl.stream
        .where((value) => (value %2 == 0))
        .listen((value) => print("$value"));

    // 往Stream中添加数据
    for(int i = 0; i < 11; i ++){
      strl.sink.add(i);
    }

    // StreamController用完后需要释放
    strl.close();
  }
}