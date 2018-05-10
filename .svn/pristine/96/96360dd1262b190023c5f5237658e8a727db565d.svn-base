package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.OrderProfitDao;
import com.bypay.domain.OrderProfit;

@Repository("orderProfitDao")
public class OrderProfitDaoImpl implements OrderProfitDao{
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public OrderProfit selectOrderProfitByOrderId(String orderId) {
		return (OrderProfit) sqlSessionTemplate.selectOne("selectOrderProfitByOrderId", orderId);
	}

	@Override
	public Integer insertOrderProfit(OrderProfit orderProfit) {
		return sqlSessionTemplate.insert("insertOrderProfit",orderProfit);
	}
}
