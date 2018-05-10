package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.OrderFrozenDao;
import com.bypay.domain.OrderFrozen;

@Repository("OrderFrozenDao")
public class OrderFrozenDaoImpl implements OrderFrozenDao{
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer insertOrderFrozen(OrderFrozen orderFrozen) {
		return sqlSessionTemplate.insert("insertOrderFrozen",orderFrozen);
	}

	@Override
	public OrderFrozen selectOrderFrozenByOrderId(String orderId) {
		return (OrderFrozen) sqlSessionTemplate.selectOne("selectOrderFrozenByOrderId",orderId);
	}

	@Override
	public Integer updateOrderFrozen(OrderFrozen orderFrozen) {
		return sqlSessionTemplate.update("updateOrderFrozen",orderFrozen);
	}

	@Override
	public List<OrderFrozen> selectOrderFrozen(Map map) {
		return sqlSessionTemplate.selectList("selectOrderFrozen",map);
	}

	@Override
	public Integer selectOrderFrozenCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectOrderFrozenCount",map);
	}

}
