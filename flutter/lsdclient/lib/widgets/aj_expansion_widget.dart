import 'package:flutter/material.dart';
import 'package:lsdclient/models/aj_expansion_data.dart';
import 'package:lsdclient/common/aj_style.dart';

typedef ExpansionPanelCallback = void Function(AjExpansionData data);

class AjExpansionWidget extends StatelessWidget {
  AjExpansionWidget({
    this.head,
    this.body,
    this.model,
    this.expansionCallback,
  });

  final Widget head;
  final Widget body;
  AjExpansionData model;

  final ExpansionPanelCallback expansionCallback;

  Widget _buildHeadWidget() {
    return InkWell(
      child: Row(
        children: <Widget>[
          head,
          Expanded(child: Container()),
          IconButton(
              splashColor: Colors.transparent,
              highlightColor: Colors.transparent,
              icon: model.isExpanded
                  ? Image.asset(
                      AJICons.ARROW_UP,
                      width: 13,
                      fit: BoxFit.fitWidth,
//                  height: 13,
                    )
                  : Image.asset(
                      AJICons.ARROW_DOWN,
                      width: 13,
                      fit: BoxFit.fitWidth,

//                  height: 13,
                    ),
              onPressed: () {
                if (expansionCallback != null) {
                  expansionCallback(model);
                }
              }),
        ],
      ),
      onTap: () {
        if (expansionCallback != null) {
          expansionCallback(model);
        }
      },
    );
  }

  Widget _buildBodyWidget() {
    return Offstage(
      offstage: !model.isExpanded,
      child: body,
    );
  }

  @override
  Widget build(BuildContext context) {
    Container container;
    if (model.isLast) {
      container = Container(
        margin: const EdgeInsets.all(15.0),
        padding: const EdgeInsets.all(0.0),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(3)),
          boxShadow: [
            BoxShadow(
              color: AJColors.shawColor,
              offset: Offset(0.0, 5.0),
              blurRadius: 10,
            ),
            BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
          ],
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            _buildHeadWidget(),
            _buildBodyWidget(),
          ],
        ),
      );
    } else {
      container = Container(
        margin: const EdgeInsets.only(left: 15.0, right: 15.0, top: 15.0),
        padding: const EdgeInsets.all(0.0),
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.all(Radius.circular(3)),
          boxShadow: [
            BoxShadow(
              color: AJColors.shawColor,
              offset: Offset(0.0, 5.0),
              blurRadius: 10,
            ),
            BoxShadow(color: AJColors.white, offset: Offset(1.0, 1.0)),
          ],
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            _buildHeadWidget(),
            _buildBodyWidget(),
          ],
        ),
      );
    }
    return container;
  }
}
