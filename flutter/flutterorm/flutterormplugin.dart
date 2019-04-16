import 'dart:math';

import 'package:flutter_orm_plugin/flutter_orm_plugin.dart';

//https://pub.dartlang.org/packages/flutter_orm_plugin
class FlutterOrmp {
  static orm() {
    String _student = 'Student';
//    studentId 在数据库中是Integer类型，主键，自增的。
//    name 在数据库中是Text类型
//    class 在数据库中是Text类型，是外键，关联的表是另一个表Class表
//    score 在数据库中是Real类型
    Map<String, Field> fields = new Map<String, Field>();
    fields["studentId"] =
        Field(FieldType.Integer, primaryKey: true, autoIncrement: true);
    fields["name"] = Field(FieldType.Text);
    fields["class"] =
        Field(FieldType.Text, foreignKey: true, to: "School_Class");
    fields["score"] = Field(FieldType.Real);
    FlutterOrmPlugin.createTable("School", "Student", fields);

//    //名字 班级 分数
//    Map m = {'name': 'winnde', 'class': 'class1', 'score': 96.5};
////    单条插入
//    FlutterOrmPlugin.saveOrm(_student, m);
//
////    批量插入
//    List orms = new List();
//    for(int i = 0; i< 10; i++){
//      int score = new Random().nextInt(100);
//      Map m = {'name':'name_$i', 'class': 'class', 'score': score};
//      orms.add(m);
//    }
//    FlutterOrmPlugin.batchSaveOrms(_student, orms);

//    全部查询
//    Query(_student).all().then((List students){
//      for(var student in students){
//        print(student);
//      }
//    });

    //查询第一条
//    Query(_student).first().then((Map m){
//      print(m);
//    });

    //根据主键查询
//    Query(_student).primaryKey([1,3,4]).all().then((List students){
//      for(var st in students){
//        print(st);
//      }
//    });

    ///where条件查询
    ///MORE_THEN >
    ///EQ_OR_LESS_THEN <=
    ///EQ_OR_MORE_THEN >=
//    Query(_student).whereByColumFilters([WhereCondiction('score', WhereCondictionType.LESS_THEN, 88)]).all().then((l){
//      for(var st in l){
//        print(st);
//      }
//    });

    //查询班级是 class class1 并且 分数高于90的
//    Query(_student).whereBySql('class in (?,?) and score > ?', ['class','class1', 90]).all().then((l){
//      if(l != null){
//        for(var st in l){
//          print(st);
//        }
//      } else{
//        print('kill');
//      }
//    });

//    where 查询排序  desc倒序
//    Query(_student).orderBy(['score desc']).all().then((l){
//        if(l != null) {
//          for (var st in l) {
//            print(st);
//          }
//        } else {
//          print('kill');
//        }
//    });

    //查询指定列
//    Query(_student).needColums(['studentId', 'name']).all().then((l) {
//          if (l != null) {
//            for (var st in l) {
//              print(st);
//            }
//          } else {
//            print('kill');
//          }
//        });

    //avg 平均值
    //group by 、having 查询
//    Query(_student)
//        .needColums(['class', 'name', 'score'])
//        .groupBy(['class'])
//        .havingByBindings("avg(score) > ?", [40])
//        .orderBy(["avg(score)"])
//        .all()
//        .then((l) {
//          if (l != null) {
//            for (var st in l) {
//              print(st);
//            }
//          } else {
//            print('kill');
//          }
//        });

    //全部更新
//    Query(_student).update({'name': 'test all update'});

    //根据主键更新
//    Query(_student).primaryKey([11]).update({'name': 'update all update'});

    //更新 studentId < 5 或者 score
    //根据特定条件更新
//    Query(_student).whereByColumFilters([
//      WhereCondiction('studentId', WhereCondictionType.LESS_THEN, 5),
//      WhereCondiction('score', WhereCondictionType.EQ_OR_MORE_THEN, 45)
//    ]).update({'score':100});

    //根据自定义where sql更新
//    Query(_student).whereBySql("studentId <= ? and score <= ?", [5,100]).update({"score":10});

    //根据主键删除
//    Query(_student).primaryKey([1,3,4]).delete();

    //根据条件删除 studentId 是 1，3，5的删除
//    Query(_student).whereByColumFilters([WhereCondiction('studentId', WhereCondictionType.IN, [1,3,5])]).delete();

    //根据自定义where sql删除
//    Query(_student).whereBySql("studentId in (?, ?, ?)", [2,6,7]).delete();
    //全部删除
//    Query(_student).delete();




//    JoinCondiction c = new JoinCondiction("Match");
//    c.type = JoinType.INNER;
//    c.matchColumns = {"studentId": "winnerId"};
//    Query("Student").join(c).all().then((List l) {
//      if (l != null) {
//        for (var st in l) {
//          print(st);
//        }
//      } else {
//        print('kill');
//      }
//    });

    FlutterOrmp.select();
  }

  static select() {
    String _student = 'Student';
    Query(_student).all().then((List l) {
      if (l != null) {
        for (var st in l) {
          print(st);
        }
      } else {
        print('kill');
      }
    });
  }
}
