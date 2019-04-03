
import 'package:flutter/material.dart';
import 'dart:async';
import 'increment_bloc.dart';
import 'bloc_provider.dart';
import 'application_bloc.dart';

class CounterPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {

    final IncrementBloc bloc = BlocProvider.of<IncrementBloc>(context);;

    final ApplicationBloc appBloc = BlocProvider.of<ApplicationBloc>(context);


    var  url = "我是一个名字name";
    url = Uri.encodeComponent(url);
    print(url);
    url = Uri.decodeComponent(url);
    print(url);

    var uri =
    Uri.parse('http://example.org:8080/foo/bar#frag=1&name=cy');
    print(uri.scheme == 'http');
    print(uri.host == 'example.org');
    print(uri.path == '/foo/bar');
    print(uri.fragment == 'frag=1');
    print(uri.origin == 'http://example.org:8080');

    return Scaffold(
      appBar: AppBar(title: Text("Stream version of the Counter App"),),
      body: Center(
        child: StreamBuilder<int>(
          // 监听Stream，每次值改变的时候，更新Text中的内容
        stream: bloc.outCounter,
          initialData: bloc.value,
          builder: (BuildContext context, AsyncSnapshot<int> snapshot){
            return Text("You hit me: ${snapshot.data} times");
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: (){
          bloc.incrementCounter.add(null);
//          appBloc.sendAppEvent(type)
        },
        child: const Icon(Icons.add),
      ),
    );
  }
}
