
import 'package:event_bus/event_bus.dart';

import 'http_error_event.dart';

class Code {

  ///网络错误
  static const NETWORK_ERROR = -1;
  //口令校验失败
  static const REQUEST_SHIBBOLETH = 0104;
  //弹框在中间
  static const TOASTFORCENTER = 10001;
  //弹框在下边
  static const TOASTFORDEFAULT = 10000;
  //成功
  static const SUCCESS = 200;

  //toast event
  static final EventBus eventBus = new EventBus();

  ///code is request code
  /// message is you want show message
  /// notip default is false  you can control the tip show message
  static errorHandleFunction(code, message, noTip){
    if(noTip){
      return message;
    }
    //eventBus 注册
    eventBus.fire(new HttpErrorEvent(code, message));
    return message;

  }

  static errorHandle(message,{int code = Code.TOASTFORDEFAULT,bool noTip = false}){
    if(noTip){
      return message;
    }
    //eventBus 注册
    eventBus.fire(new HttpErrorEvent(code, message));
    return message;

  }
}


class PageSnapEvent {
  //是否跳转
  bool snap;

  PageSnapEvent(this.snap);
}


