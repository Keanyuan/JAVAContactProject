
class Address {

//  static String host = "https://uat.anji-datahome.com/api";//预发环境
//  static String basewebHost = "https://uat.anji-datahome.com/plot"; //预发环境
  static String host = "https://www.anji-datahome.com/api";//生产环境
  static String basewebHost = "https://www.anji-datahome.com/plot"; //生产环境

  static String findProxyHost = "localhost"; //抓包环境
  static bool needfindProxyHost = false; //是否抓包

  //pro 生产
  static String pro_host = "https://www.anji-datahome.com/api";//生产环境
  static String pro_basewebHost = "https://www.anji-datahome.com/plot"; //生产环境

  //pretest 预发
  static String pretest_host = "https://uat.anji-datahome.com/api";//预发环境
  static String pretest_basewebHost = "https://uat.anji-datahome.com/plot"; //预发环境
  //dev 开发
  static String dev_host = "http://10.108.2.181:8802";//开发环境
  static String dev_basewebHost = "http://10.108.2.182/plot"; //开发环境

  //test 测试
  static String test_host = "http://10.108.2.183:8802"; //测试环境
  static String test_basewebHost = "http://10.108.2.184/plot"; // 测试环境

  //local 本地
  static String local_host = "http://10.108.141.48/api"; //本地环境
  static String local_basewebHost = "http://10.108.141.48/plot"; // 本地环境


  static String userLogin = "/base/user/noauth/login";   //登录
  static String mobileLogin = "/base/user/noauth/mobileLogin/v1";   //登录
  static String userLogout = "/base/user/noauth/logout";   //退出登录
  static String sendingSms = "/base/user/v1/noauth/sendingSms";   //发送验证码
  static String app_update = "/base/app/appversion";   //发送验证码

  static String screenListInfo = "/event/screeningconditions/v1";   //筛选列表

  static String fullOrders = "/event/fullOrders/fullOrders/v1";           // 全量订单
  static String orderInquiry = "/event/alarmOrders/orderInquiry/v1";      // 预警订单
  static String eventOrderInquiry = "/event/alarmEvents/orderInquiry/v1"; // 预警事件列表
  static String followOrders = "/event/fullOrders/followOrders/v1";      // 关注订单
  static String alarmOrderDetail = "/event/alarmOrders/orderDetails/v1";  // 预警订单详情
  static String isFollowed = "/event/followedOrders/isFollow/v1";         // 是否关注
  static String fullOrderDetail = "/event/fullOrders/fullOrderDetails/v1";      // 全量订单详情


  static String otdNodeAlertList = "/base/otdNodeAlert/list";   //预警类型 列表查询

//alarmEvents/orderInquiry/v1  //预警事件列表  //map
//orderInquiry   //预警订单列表   map
///followedOrders/list //关注列表   map
///followedOrders/isFollow/v1   //   是否关注   订单号
///fullOrders   //订单编号或者vin码的后8位匹配
///alarmOrders/orderDetails/v1   //订单编号

}