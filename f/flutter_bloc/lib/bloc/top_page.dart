
import 'package:flutter/material.dart';
import 'under_page.dart';
import 'bloc_provider.dart';

class  TopPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    final bloc = BlocProvider.of(context);
    GlobalObjectKey<ScaffoldState> _scaffoldKey = new GlobalObjectKey<ScaffoldState>("aad");
    return Scaffold(
      appBar: AppBar(title: Text("Top page"),),
      body: Center(
        child: StreamBuilder<int>(
          //我们这里监听的是countBloc的value（它是一个stream）
          stream: bloc.stream,
          //因为当这个控件首次渲染的时候，还未与用户产生交互，
          // 也就不会有事件从流中流出。所以需要给首次渲染一个初始值
          initialData: bloc.value,
          builder: (BuildContext context, AsyncSnapshot<int>snapshot){
            //snapshot就是这个流输出的数据的一个快照。
            // 我们可以通过snapshot.data访问快照中的数据。
            // 也可以通过snapshot.hasError判断是否有异常，
            // 并通过snapshot.error获取这个异常
            return Text(
              'You hit me: ${snapshot.data} times',
              style: Theme.of(context).textTheme.display1,
            );
          },),
      ),
        floatingActionButton: FloatingActionButton(
          key: _scaffoldKey,
            child: Icon(Icons.navigate_next),
            onPressed: () =>
                Navigator.of(context).
                push(MaterialPageRoute(builder: (context) => UnderPage())))
    );
  }
}
