import 'package:lsdclient/models/alarm_event_data.dart';
import 'package:lsdclient/models/alarm_order_data.dart';
import 'package:lsdclient/request/Address.dart';
import 'package:lsdclient/request/HttpManager.dart';
import 'package:lsdclient/common/data_result.dart';
import 'package:lsdclient/common/common_utils.dart';

class OrderDao {

  // 预警事件列表
  static Future<DataResult> getEventOrderInquiry({int pageNo, int pageSize}) async {
    Map _mapStr = await CommonUtils.getScreenMapInfo();
    List<AlarmEventData> resultData;
    var res = await HttpManager.requestData(Address.eventOrderInquiry, _mapStr, pageNo: pageNo, pageSize: pageSize);
    if (res != null && res.result) {
      resultData = AlarmEventData.fromMapList(res.data["records"]);
    }
    return DataResult(resultData, res.result);
  }

  //预警订单列表
  static Future<DataResult> getOrderInquiry({int pageNo, int pageSize}) async{
    Map _mapStr = await CommonUtils.getScreenMapInfo();
    List<AlarmOrderData> resultData;
    var res = await HttpManager.requestData(Address.orderInquiry, _mapStr, pageNo: pageNo, pageSize: pageSize);
    if (res != null && res.result) {
      resultData = AlarmOrderData.fromMapList(res.data["records"]);
    }
    return DataResult(resultData, res.result);
  }

  // 关注订单列表
  static Future<DataResult> getFollowOrders({int pageNo, int pageSize}) async {
    Map _mapStr = await CommonUtils.getScreenMapInfo();
    List<AlarmOrderData> resultData;
    var res = await HttpManager.requestData(Address.followOrders, _mapStr, pageNo: pageNo, pageSize: pageSize);
    if (res != null && res.result) {
      resultData = AlarmOrderData.fromMapList(res.data["records"]);
    }
    return DataResult(resultData, res.result);
  }

  // 设置是否关注订单
  static Future<DataResult> setFollowed(bool isFollow, orderNo) async {
    var params = {
      "isFollow": isFollow,
      "orderNo" : orderNo,
    };
    var res = await HttpManager.requestData(Address.isFollowed, params);
    return new DataResult(res.data, res.result);
  }

  static Future<bool> setConcerned(bool isFollow, String orderNo) async {
    var res = await OrderDao.setFollowed(isFollow, orderNo);
    return res != null && res.result;
  }

  /// /event/fullOrders/fullOrders/v1 全量订单
  static Future<DataResult> getFullOrders(String orderNoOrVinSuffix) async{
    var params = {
      "orderNoOrVinSuffix": orderNoOrVinSuffix
    };
    List<AlarmOrderData> resultData;
    var res = await HttpManager.requestData(Address.fullOrders, params);
    if (res != null && res.result) {
      resultData = AlarmOrderData.fromMapList(res.data["records"]);
    }
    return DataResult(resultData, res.result);
  }

  // 获取订单详情
  static Future<DataResult> getOrderDetail(String orderNo, {bool fullOrder = false}) async{
    var params = {
      "orderNo": orderNo
    };
    var res = await HttpManager.requestData(fullOrder ? Address.fullOrderDetail : Address.alarmOrderDetail, params);
    return new DataResult(res.data, res.result);
  }
}
