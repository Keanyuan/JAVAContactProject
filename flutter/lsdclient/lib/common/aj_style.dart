import 'package:flutter/material.dart';
import 'package:lsdclient/common/aj_config.dart';

/// 颜色创建方法
/// - [colorString] 颜色值
/// - [alpha] 透明度(默认1，0-1)
///
/// 可以输入多种格式的颜色代码，如: 0x000000,0xff000000,#000000
AJColor(value, {double alpha = 1.0}) {
  String colorStr = value.toString();
  if (!colorStr.startsWith('0xff') && colorStr.length == 6) {// value未带0xff前缀并且长度为6，如000000
    colorStr = '0xff' + colorStr;
  }else if(colorStr.startsWith('0x') && colorStr.length == 8) {// value为8位，如0x000000
    colorStr = colorStr.replaceRange(0, 2, '0xff');
  }else if(colorStr.startsWith('#') && colorStr.length == 7) {// value为7位，如#000000
    colorStr = colorStr.replaceRange(0, 1, '0xff');
  }else if(colorStr.startsWith('0xff') && colorStr.length == 10) {// value为10位，如0xff000000
    colorStr = colorStr.replaceRange(0, 1, '0xff');
  }else {
    return Colors.black;
  }
  // 先分别获取色值的RGB通道
  Color color = Color(int.parse(colorStr));
  int red = color.red;
  int green = color.green;
  int blue = color.blue;
  // 通过fromRGBO返回带透明度和RGB值的颜色
  return Color.fromRGBO(red, green, blue, alpha);
}

class AJColors {
  //0xFF24292E  十六进制颜色值   0xFF 表示透明度100% 24292E表示对应颜色值  0xFF19234D
  static const int primaryValue = 0xFFffffff;

  static const Color white = Color(0xFFFFFFFF);
  static const Color black = Color(0xFF000000);
  static const Color transparent = Colors.transparent;
  static const Color app_line = Color(0xffEBEBEB);
  static const Color background_normal = Color(0xFFF9F9F9);
  static const Color screen_normal = Color(0xFF999999);
  static const Color screen_select_num_Color = Color(0xFF999999);
  static const int primaryLightValue = 0xFFe9e9e9;
  static const int primaryDarkValue = 0xFF121917;
  static const Color subTextColor = Color(0xff959595);

  static const Color buttonNormalColor = Color(0xff8E9BE3);
  static const Color listStartTitleColor = Color(0xff8292ED);
  static const Color listEndTitleColor = Color(0xffFCB859);
  static const Color listTitleColor = Color(0xff485465);
  static const Color listDescColor = Color(0xffA8B4C4);

  static const Color shawColor = Color(0x7fD9E2E9);

  static const Color letterColor = Color(0xff354BCD);
  static const Color letterbackgroundColor = Color(0x19354BCD);
  static const Color letterNormalbackgroundColor = Color(0x7fEFEFEF);

  static const AppBarColor = Color(0xffebebeb);
  static const DesTextColor = Color(0xff9e9e9e);
  static const DividerColor = Color(0xffEEEEEE);
  static const ViewDetailColor = Color(0xffEFF4FF);
  static const FullNormalTypeColor = Color(0xff00D2B9);
  static const AlarmTypeColor = Color(0xffFF7516);
  static const OverTimeTypeColor = Color(0xffFF0000);
  static const AddressTitleColor = Color(0xffFCB859);
  static const OrderNoTextColor = Color(0xff485465);
  static const OrderStateTextColor = Color(0xffFCB859);
  static const UnConcernTextColor = Color(0xff525462);
  static const ConcernTextColor = Color(0xffA8B4C4);
  static const SegAddressTextColor = Color(0xff333333);
  static const SegValueTextColor = Color(0xffA8B4C4);
  static const SegTimeValueTextColor = Color(0xff485465);
  static const OrderInfoItemTitleTextColor = Color(0xff485465);
  static const OrderInfoItemTextColor = Color(0xff485465);




  static const MaterialColor primarySwatch = const MaterialColor(
    primaryValue,
    const <int, Color>{
      50: const Color(primaryLightValue),
      100: const Color(primaryLightValue),
      200: const Color(primaryLightValue),
      300: const Color(primaryLightValue),
      400: const Color(primaryLightValue),
      500: const Color(primaryValue),
      600: const Color(primaryDarkValue),
      700: const Color(primaryDarkValue),
      800: const Color(primaryDarkValue),
      900: const Color(primaryDarkValue),
    },
  );
}

class AJICons {
  static const String ARROW_LEFT = 'assets/images/arrow_left.png';
  static const String aj_welcome = 'assets/images/aj_welcome.png';
  static const String aj_welcome_gif = 'assets/images/aj_welcom.gif';
  static const String timg_gif = 'assets/images/timggif.gif';
  static const String DEFAULT_USER_BG_ICON = 'assets/images/logo.png';
  static const String screening = 'assets/images/screening.png';
  static const String customer_distribution_list = 'assets/images/customer_distribution_list.png';
  static const String logicarea = 'assets/images/logicarea.png';
  static const String sendsrc = 'assets/images/sendsrc.png';
  static const String alarm_his = 'assets/images/alarm_his.png';
  static const String delaytime = 'assets/images/delaytime.png';
  static const String ordertype = 'assets/images/ordertype.png';
  static const String retailer = 'assets/images/retailer.png';
  static const String transportation_way_list = 'assets/images/transportation_way_list.png';
  static const String transportation_supply_list = 'assets/images/transportation_supply_list.png';
  static const String arrow_right = 'assets/images/arrow_right.png';
  static const String cha = 'assets/images/cha.png';
  static const String AJ_FONT_FAMILY = 'ajIconFont';
  static const String view_detail = 'assets/images/viewdetails.png';
  static const String empty = 'assets/images/empty.png';
  static const String concerned = 'assets/images/concerned.png';
  static const String unconcerned = 'assets/images/concern.png';
  static const String ARROW_UP = 'assets/images/arrow_up.png';
  static const String ARROW_DOWN = 'assets/images/arrow_down.png';

  //用户名
  static const IconData LOGIN_USER = const IconData(0xe604, fontFamily: AJICons.AJ_FONT_FAMILY);
  //密码
  static const IconData LOGIN_PW = const IconData(0xe620, fontFamily: AJICons.AJ_FONT_FAMILY);
  //导航收藏按钮
  static const IconData REPORT_COLLECTION = const IconData(0xe66f, fontFamily: AJICons.AJ_FONT_FAMILY);
  //睁眼
  static const IconData PASSWORD_EYE = const IconData(0xe603, fontFamily: AJICons.AJ_FONT_FAMILY);

  //闭眼
  static const IconData PASSWORD_EYE_CLOSE = const IconData(0xe602, fontFamily: AJICons.AJ_FONT_FAMILY);


  //手机号
  static const IconData REGIST_PHONE_NUM = Icons.phone_android;
  //邮箱
  static const IconData REGIST_EMAIL = Icons.email;
  //更多按钮
  static const IconData MORE = Icons.more_horiz;

}

class AJFont {
  //输入框字体大小
//项目名称字体
  static const textSize8 = 8.0;
  static const textSize10 = 10.0;
  static const textSize12 = 12.0;
  static const textSize13 = 13.0;
  static const textSize14 = 14.0;
  static const textSize15 = 15.0;
  static const textSize16 = 16.0;
  static const textSize17 = 18.0;
  static const textSize18 = 18.0;
  static const textSize20 = 20.0;
  static const textSize24 = 24.0;
  static const textSize36 = 36.0;


}

///文本样式
class AJConstant {

  static const letterTitleSelectColorStyle =
  TextStyle(color: AJColors.letterColor, fontSize: AJFont.textSize12);
  static const letterHeaderColorStyle =
  TextStyle(color: Color(0x66354BCD), fontSize: AJFont.textSize12, fontWeight: FontWeight.w300);
  static const loginWhiteStyle =
  TextStyle(color: AJColors.white, fontSize: AJFont.textSize16);

  static const scerrnSelectNumStyle =
  TextStyle(color: AJColors.screen_select_num_Color, fontSize: AJFont.textSize14);

  static const scerrnButtonWhiteStyle =
  TextStyle(color: AJColors.white, fontSize: AJFont.textSize14);

  static const scerrnNormalButtonStyle =
  TextStyle(color: AJColors.screen_normal, fontSize: AJFont.textSize14);

  static const letterNormalButtonStyle =
  TextStyle(color: AJColors.screen_normal, fontSize: AJFont.textSize12);

  static const makesureRedTextStyle =
      TextStyle(color: AJColors.black, fontSize: AJFont.textSize10);

  static const changePasswordTextStyle = TextStyle(
    color: AJColors.subTextColor,
    fontSize: AJFont.textSize14,
  );

  static const dialogTitleTextStyle = TextStyle(
    color: AJColors.listTitleColor,
    fontSize: AJFont.textSize16,
  );

  static const listTitleTextStyle = TextStyle(
    color: AJColors.listTitleColor,
    fontSize: AJFont.textSize15,
  );

  static const listTitleDescTextStyle = TextStyle(
    color: AJColors.listTitleColor,
    fontSize: AJFont.textSize14,
  );
  static const listDescCheckTextStyle = TextStyle(
    color: AJColors.listDescColor,
    fontSize: AJFont.textSize16,
  );
  //app_line
  static const listDescBigTextStyle = TextStyle(
    color: AJColors.listDescColor,
    fontSize: AJFont.textSize15,
  );
  static const listDescTextStyle = TextStyle(
    color: AJColors.listDescColor,
    fontSize: AJFont.textSize14,
  );
  static var addressTextStyle = TextStyle(
    color: Colors.orange,
    fontSize: AJFont.textSize14 / AJConfig.textScaleFactor,
  );
  static var addressTitleTextStyle = TextStyle(
    color:AJColors.AddressTitleColor,
    fontSize: AJFont.textSize14 / AJConfig.textScaleFactor,
  );
  static var orderNoTextStyle = TextStyle(
    color: AJColors.OrderNoTextColor,
    fontSize: AJFont.textSize14 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );
  static var orderStateTextStyle = TextStyle(
    color: AJColors.OrderStateTextColor,
    fontSize: AJFont.textSize12 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var concernedTextStyle = TextStyle(
    color: AJColors.ConcernTextColor,
    fontSize: AJFont.textSize13 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var unConcernedTextStyle = TextStyle(
    color: AJColors.UnConcernTextColor,
    fontSize: AJFont.textSize13 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var segAddressTextStyle = TextStyle(
    color: AJColors.SegAddressTextColor,
    fontSize: AJFont.textSize17  / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCMedium,
  );

  static var segValueTextStyle = TextStyle(
    color: AJColors.SegValueTextColor,
    fontSize: AJFont.textSize15 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var orderInfoItemTitleTextStyle = TextStyle(
    color: AJColors.OrderInfoItemTitleTextColor,
    fontSize: AJFont.textSize16 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var orderInfoItemTextStyle = TextStyle(
    color: AJColors.OrderInfoItemTextColor,
    fontSize: AJFont.textSize14 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var address4TextStyle = TextStyle(
    color: Color(0xff333344),
    fontSize: AJFont.textSize18 / AJConfig.textScaleFactor,
  );

  static var segTimeKeyTextStyle = TextStyle(
    color: AJColors.SegValueTextColor,
    fontSize: AJFont.textSize13 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var segTimeValueTextStyle = TextStyle(
    color: AJColors.SegTimeValueTextColor,
    fontSize: AJFont.textSize12 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var attrKeyValueTextStyle = TextStyle(
    color: AJColors.SegValueTextColor,
    fontSize: AJFont.textSize14 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );
  static const ViewDetailsTextStyle = TextStyle(
    color: Color(0xff5F88F1),
  );

  static var nodeAlertLevelNameTextStyle = TextStyle(
    color: Colors.white,
    fontSize: AJFont.textSize17/ AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
  );

  static var nodeAlertNameTextStyle = TextStyle(
    color: AJColors.OrderNoTextColor,
    fontSize: AJFont.textSize14 / AJConfig.textScaleFactor,
    fontFamily: AJFontFamily.PingFangSCRegular,
    fontWeight: FontWeight.bold
  );

}

class AJSize {
  static var searchIconSize = 22.0 / AJConfig.textScaleFactor;
  static var fullWidthIconButtonIconSize = 12.0 / AJConfig.textScaleFactor;
  static var dividerWidth = 1.0 / AJConfig.textScaleFactor;
  static var infoTitleIconSize = 23.0 / AJConfig.textScaleFactor;
  static var timeLine1IconSize = 15.0 / AJConfig.textScaleFactor;
  static var timeLine2IconSize = 40.0 / AJConfig.textScaleFactor;
  static var segAddressSpace = 20.0 / AJConfig.textScaleFactor;
  static var circleAvatarRadius = 35.0 / AJConfig.textScaleFactor;
  static var segTimeVerticalSpace = 5.0 / AJConfig.textScaleFactor;
  static var concernedBoxWidth = 68.0 / AJConfig.textScaleFactor;
  static var concernedBoxHeight = 28.0 / AJConfig.textScaleFactor;
  static var concernedImageSize = 11.0 / AJConfig.textScaleFactor;
  static var orderStateBoxWidth = 50.0 / AJConfig.textScaleFactor;
  static var orderStateBoxHeight = 22.0 / AJConfig.textScaleFactor;
  static var segAddressMaxWidth = 90.0 /  AJConfig.textScaleFactor;
}

class AJFontFamily {
  static const PingFangSCMedium = 'PingFangSC-Medium';
  static const PingFangSCRegular = 'PingFangSC-Regular';
  static const PingFangSCSemibold = 'PingFangSC-Semibold';
}
