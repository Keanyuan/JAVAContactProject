import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:redux/redux.dart';
import 'text_field_updated_action.dart';

typedef ChangeCallback = void Function(String);

class ReduxTextField<T> extends StatefulWidget {
  final Store<T> store;
  final String Function(T) converter;
  final TextFieldUpdatedAction action;
  final ChangeCallback onControllerChange;

  final FocusNode focusNode;
  final InputDecoration decoration;
  final TextInputType keyboardType;
  final TextInputAction textInputAction;
  final TextCapitalization textCapitalization;
  final TextStyle style;
  final TextAlign textAlign;
  final TextDirection textDirection;
  final bool autofocus;
  final bool obscureText;
  final bool autocorrect;
  final int maxLines;
  final int maxLength;
  final bool maxLengthEnforced;
  final ValueChanged<String> onChanged;
  final VoidCallback onEditingComplete;
  final ValueChanged<String> onSubmitted;
  final List<TextInputFormatter> inputFormatters;
  final bool enabled;
  final double cursorWidth;
  final Radius cursorRadius;
  final Color cursorColor;
  final Brightness keyboardAppearance;
  final EdgeInsets scrollPadding;
  final bool enableInteractiveSelection;
  final GestureTapCallback onTap;

  const ReduxTextField({
    Key key,
    @required this.store,
    @required this.converter,
    @required this.action,
    this.onControllerChange,
    this.focusNode,
    this.decoration = const InputDecoration(),
    TextInputType keyboardType,
    this.textInputAction,
    this.textCapitalization = TextCapitalization.none,
    this.style,
    this.textAlign = TextAlign.start,
    this.textDirection,
    this.autofocus = false,
    this.obscureText = false,
    this.autocorrect = true,
    this.maxLines = 1,
    this.maxLength,
    this.maxLengthEnforced = true,
    this.onChanged,
    this.onEditingComplete,
    this.onSubmitted,
    this.inputFormatters,
    this.enabled,
    this.cursorWidth = 2.0,
    this.cursorRadius,
    this.cursorColor,
    this.keyboardAppearance,
    this.scrollPadding = const EdgeInsets.all(20.0),
    this.enableInteractiveSelection,
    this.onTap,
  })  : assert(store != null),
        assert(converter != null),
        assert(action != null),
        assert(textAlign != null),
        assert(autofocus != null),
        assert(obscureText != null),
        assert(autocorrect != null),
        assert(maxLengthEnforced != null),
        assert(scrollPadding != null),
        assert(maxLines == null || maxLines > 0),
        assert(maxLength == null ||
            maxLength == TextField.noMaxLength ||
            maxLength > 0),
        keyboardType = keyboardType ??
            (maxLines == 1 ? TextInputType.text : TextInputType.multiline),
        super(key: key);

  @override
  _ReduxTextFieldState<T> createState() => _ReduxTextFieldState<T>();
}

class _ReduxTextFieldState<T> extends State<ReduxTextField> {
  final _controller = TextEditingController();
  StreamSubscription<T> _storeSubscription;
  String lastText = '';

  @override
  void initState() {
    super.initState();

    _controller.addListener(_controllerListener);
    _storeSubscription = widget.store.onChange.listen(_storeListener);
  }

  void _controllerListener() {
    if (lastText == _controller.text ||
        widget.converter(widget.store.state as T) == _controller.text) {
      return;
    }

    widget.action.setNewValue(_controller.text);
    widget.store.dispatch(widget.action);

    if (widget.onControllerChange != null) {
      widget.onControllerChange(_controller.text);
    }

    lastText = _controller.text;
  }

  void _storeListener(state) {
    final text = widget.converter(state as T);

    if (_controller.text == text) {
      return;
    }

    setState(() {
      _controller.removeListener(_controllerListener);
      _controller.text = text;
      _controller.addListener(_controllerListener);
    });
  }

  @override
  void dispose() {
    _controller.dispose();
    _storeSubscription.cancel();

    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return TextField(
      controller: _controller,
      focusNode: widget.focusNode,
      decoration: widget.decoration,
      keyboardType: widget.keyboardType,
      textInputAction: widget.textInputAction,
      textCapitalization: widget.textCapitalization,
      style: widget.style,
      textAlign: widget.textAlign,
      textDirection: widget.textDirection,
      autofocus: widget.autofocus,
      obscureText: widget.obscureText,
      autocorrect: widget.autocorrect,
      maxLines: widget.maxLines,
      maxLength: widget.maxLength,
      maxLengthEnforced: widget.maxLengthEnforced,
      onChanged: widget.onChanged,
      onEditingComplete: widget.onEditingComplete,
      onSubmitted: widget.onSubmitted,
      inputFormatters: widget.inputFormatters,
      enabled: widget.enabled,
      cursorWidth: widget.cursorWidth,
      cursorRadius: widget.cursorRadius,
      cursorColor: widget.cursorColor,
      keyboardAppearance: widget.keyboardAppearance,
      scrollPadding: widget.scrollPadding,
      enableInteractiveSelection: widget.enableInteractiveSelection,
      onTap: widget.onTap,
    );
  }
}