package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.MerProfitStatictisDao;
import com.bypay.domain.MerProfitStatictis;
import com.bypay.service.MerProfitStatictisService;

@Service("merProfitStatictisService")
public class MerProfitStatictisServiceImpl implements MerProfitStatictisService{
	
	@Inject
	private MerProfitStatictisDao merProfitStatictisDao;

	@Override
	public List<MerProfitStatictis> dayProfitStatistics(MerProfitStatictis merProfitStatictis) {
		return merProfitStatictisDao.dayProfitStatistics(merProfitStatictis);
	}

	@Override
	public List<MerProfitStatictis> getSucTransAmtFeeStatistics(Map mapParameter) {
		return merProfitStatictisDao.getSucTransAmtFeeStatistics(mapParameter);
	}
}
