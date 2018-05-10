package com.bypay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.BypayProfitStatictisDao;
import com.bypay.domain.StatisticsInfo;

@Repository("bypayProfitStatictisDao")
public class BypayProfitStatictisDaoImpl implements BypayProfitStatictisDao{

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	/**
	 * 获取系统日分润统计数据总条数
	 */
	@Override
	public Map getBypayProfitStatictisCount(Map map) {
		return (HashMap)sqlSessionTemplate.selectOne("getBypayProfitStatictisCount", map);
	}
	
	/**
	 * 获取系统日分润统计LIST
	 */
	@Override
	public List<StatisticsInfo> getBypayProfitStatictisList(Map map) {
		return sqlSessionTemplate.selectList("getBypayProfitStatictisList", map);
	}
	
}
