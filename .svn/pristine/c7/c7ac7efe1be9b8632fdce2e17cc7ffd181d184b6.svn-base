package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.OrderRiskDao;
import com.bypay.domain.OrderRisk;

@Repository("orderRiskDao")
public class OrderRiskDaoImpl implements OrderRiskDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<OrderRisk> getOrderRiskList(Map<String, Object> map) {
		return sqlSessionTemplate.selectList("getOrderRiskList",map);
	}
	
	@Override
    public Integer insertOrderRisk(OrderRisk orderRisk) {
        return sqlSessionTemplate.insert("insertOrderRisk",orderRisk);
    }
	
	/**
	 * 查询异常交易记录的数据总条数
	 */
	@Override
	public Integer getOrderRiskListCount(Map<String, Object> map) {
		return (Integer) sqlSessionTemplate.selectOne("getOrderRiskListCount",map);
	}
	
	

}
