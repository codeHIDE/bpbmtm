package com.bypay.service;

import java.util.List;
import java.util.Map;

import com.bypay.domain.OrderInfo;
import com.bypay.domain.clientTool.TRAN_REQ;
import com.bypay.domain.clientTool.TRAN_RESP;

public interface OrderInfoService {
  /**
   * 查询当日交易的条数分页用
   */
  Map selectOrderTheDateCount(Map map);

  /**
   * 查询当天交易信息
   */
  List<OrderInfo> selectOrderTheDate(Map map);

  /**
   * 查询历史交易的条数分页用
   */
  Map selectOrderHistoryCount(Map map);

  /**
   * 查询当天交易信息代付
   */
  List<OrderInfo> selectOrderTheDateRate(Map map);

  /**
   * 查询历史交易的条数分页用代付
   */
  Map selectOrderTheDateCountRate(Map map);
  
  
  /**
   * 查询所有 OrderInfo 历史交易查询
   */
  List<OrderInfo> selecrOrderHistoryList(Map map);

  /**
   * 查询转账信息的条数
   */
  Map selectTransferCount(Map map);

  /**
   * 转账信息查询
   * 
   * @param map
   * @return
   */
  List<OrderInfo> selectTransfer(Map map);

  /**
   * 应用交易查询的条数
   * 
   * @param orderInfo
   * @return
   */
  Map selectApplyCount(Map map);

  /**
   * 应用交易查询
   * 
   * @param map
   * @return
   */
  List<OrderInfo> selectApplyList(Map map);

  /**
   * 应用详情
   * 
   * @param orderId
   * @return
   */
  OrderInfo selectOrderDetail(Map map);

  /**
   * 历史订单退款
   * 
   * @param refund
   * @return
   */
  String orderRefund(OrderInfo orderInfo);

  /**
   * 根据订单ID获取订单信息
   * 
   * @param orderId
   * @return
   */
  OrderInfo getOrderInfo(String orderId);
  /**
   * 
   * @Description: 
   * @Auther: ljl
   * @Date: 2014-6-10 下午6:10:43
   */
  Boolean markOrderException(String orderId);
  
  Map<String, Object> selectTotalTractCost(Map<String, Object> map);
  
  int updateOrderSettle(String orderId);
  
  void updateOrder(OrderInfo OrderInfo);
  
  public TRAN_RESP payToMs(TRAN_REQ tranReq);
  
}
