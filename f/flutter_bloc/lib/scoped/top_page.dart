
import 'package:flutter/material.dart';
import 'package:scoped_model/scoped_model.dart';
import 'under_page.dart';
import 'count_model.dart';

class TopPage extends StatefulWidget {
  @override
  _TopPageState createState() => _TopPageState();
}

class _TopPageState extends State<TopPage> {

  GlobalObjectKey<ScaffoldState> _scaffoldKey = new GlobalObjectKey<ScaffoldState>("aa");

  Model getModel(BuildContext context){
    //直接使用of
    final countModel = ScopedModel.of<CountModel>(context);

    //使用CountModel中重写的of
    final countModel2 = CountModel().of(context);

    countModel.increment();
    countModel2.increment();

    return countModel;
  }
  
  
  
  @override
  Widget build(BuildContext context) {
    
    




    return ScopedModelDescendant<CountModel>(
      builder: (context, child, model){
        return Scaffold(
          appBar: AppBar(title: Text("Top Screen"),),
          body: Center(
            child: Text(
              model.count.toString(),
              style: TextStyle(fontSize: 48.0),
            ),
          ),
          floatingActionButton: FloatingActionButton(
            key: _scaffoldKey,
            onPressed: (){
              Navigator.of(context).push(MaterialPageRoute(builder: (BuildContext context){
                return UnderScreen();
              }));
            },
            child: Icon(Icons.forward),
          ),
        );
      },
    );
  }
}
