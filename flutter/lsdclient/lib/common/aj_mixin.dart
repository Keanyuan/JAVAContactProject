import 'dart:io';

import 'package:aj_flutter_plugin/aj_flutter_plugin.dart';
import 'package:flutter/material.dart';
import 'package:lsdclient/tools/aj_code.dart';

@optionalTypeArgs
mixin AJMixin <T extends StatefulWidget> on State<T> {
  int firstBackPressTime = 0;

  backTodesktop() {
    if(Platform.isIOS)return;
    //Android 回到桌面
    int secondTime = new DateTime.now().millisecondsSinceEpoch;
    if (secondTime - firstBackPressTime > 2000) {
      // 如果两次按键时间间隔大于2秒，则不退出
      firstBackPressTime = secondTime; // 更新firstTime
      Code.errorHandle('再按一次回到桌面',code: Code.TOASTFORDEFAULT);
    } else {
      //退出到桌面
      exitApp();
    }
  }
}