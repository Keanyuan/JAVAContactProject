
import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';
import 'under_page.dart';
import 'count_model.dart';

class UnderScreen extends StatefulWidget {
  @override
  _UnderScreenState createState() => _UnderScreenState();
}

class _UnderScreenState extends State<UnderScreen> {
  GlobalObjectKey<ScaffoldState> _scaffoldKey = new GlobalObjectKey<ScaffoldState>("aadc");

  @override
  Widget build(BuildContext context) {

    return ScopedModelDescendant<CountModel>(
      builder: (context, child, model){
        return Scaffold(
          appBar: AppBar(title: Text("Under Screen"),),
          body: Center(
            child: Text(
              "You have pushed the button this many times:\n ${model.count}",
              style: TextStyle(fontSize: 48.0),
            ),
          ),
          floatingActionButton: FloatingActionButton(
            key: _scaffoldKey,
            onPressed: ()=> model.increment() ,
            child: Icon(Icons.add),
          ),
        );
      },
    );
  }
}
