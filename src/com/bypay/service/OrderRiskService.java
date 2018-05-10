package com.bypay.service;

import java.util.List;

import com.bypay.domain.OrderRisk;

public interface OrderRiskService {

  /**
   * 查询所有的异常交易记录
   * 
   * @param orderRisk
   *          查询参数
   * @return 异常交易记录
   */
  public List<OrderRisk> getOrderRiskList(OrderRisk orderRisk,String startTime,String endTime);

  /**
   * 查询异常交易记录的数据总条数
   * @param orderRisk
   * @param startTime
   * @param endTime
   * @return
   */
  public Integer getOrderRiskListCount(OrderRisk orderRisk, String startTime,
		String endTime);
}
