package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.BypayProfitStatictisDao;
import com.bypay.dao.MerManagerDao;
import com.bypay.domain.StatisticsInfo;
import com.bypay.service.BypayProfitStatictisService;

@Service("bypayProfitStatictisService")
public class BypayProfitStatictisServiceImpl implements BypayProfitStatictisService{
	@Inject
	private BypayProfitStatictisDao bypayProfitStatictisDao;
	
	/**
	 * 获取系统日分润统计数据总条数
	 */
	@Override
	public Map getBypayProfitStatictisCount(Map mapParameter) {
		return bypayProfitStatictisDao.getBypayProfitStatictisCount(mapParameter);
	}

	/**
	 * 获取系统日分润统计LIST
	 */
	@Override
	public List<StatisticsInfo> getBypayProfitStatictisList(Map map) {
		return bypayProfitStatictisDao.getBypayProfitStatictisList(map);
	}

}
