import 'package:event_bus/event_bus.dart';

//Bus初始化
EventBus eventBus = EventBus();

class TabViewOnRefresh {
  int index;
  TabViewOnRefresh(int index){
    this.index = index;
  }
}