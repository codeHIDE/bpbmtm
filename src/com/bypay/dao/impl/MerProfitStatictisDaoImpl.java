package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerProfitStatictisDao;
import com.bypay.domain.MerProfitStatictis;

@Repository("merProfitStatictisDao")
public class MerProfitStatictisDaoImpl implements MerProfitStatictisDao{

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<MerProfitStatictis> dayProfitStatistics(MerProfitStatictis merProfitStatictis) {
		return sqlSessionTemplate.selectList("dayProfitStatistics", merProfitStatictis);
	}

	
	@Override
	public List<MerProfitStatictis> getSucTransAmtFeeStatistics(Map mapParameter) {
		return sqlSessionTemplate.selectList("getSucTransAmt", mapParameter);
	}
}
