import 'package:flutter/material.dart';
import 'package:keyboard_visibility/keyboard_visibility.dart';

class KeyboardState<T extends StatefulWidget> extends State<T> {
  bool showTooBar = true;
  int keyboardIndex = -1;
  KeyboardVisibilityNotification notification;
  @override
  void initState() {
    super.initState();
    notification = new KeyboardVisibilityNotification();
    keyboardIndex = notification.addNewListener(
      onChange: (bool visible) {
        print("KeyboardVisibilityNotification " + visible.toString());
        setState(() {
          showTooBar = !showTooBar;
        });
      },
    );
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    notification.removeListener(keyboardIndex);
  }

  @override
  Widget build(BuildContext context) {
    return null;
  }

  getKeyboardWidget(Widget widget) {
    return !showTooBar ? Container() : widget;
  }

  getKeyboardOffstageWidget(Widget widget) {
    return Offstage(offstage: !showTooBar, child: widget,);
  }
}
