import 'package:shared_preferences/shared_preferences.dart';

///SharedPreferences 本地存储
class LocalStorage {

  ///保存
  static save(String key, value) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setString(key, value);
  }

  ///保存
  static saveBool(String key, bool value) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setBool(key, value);
  }

  ///保存
  static saveInt(String key, int value) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setInt(key, value);
  }

  ///保存
  static saveDouble(String key, double value) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.setDouble(key, value);
  }


  ///获取
  static get(String key) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.get(key);
  }

  static getBool(String key) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getBool(key);
  }
  static getInt(String key) async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.getInt(key);
  }


  ///删除
  static remove(String key)async{
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.remove(key);
  }

}