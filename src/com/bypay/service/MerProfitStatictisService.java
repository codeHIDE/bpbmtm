package com.bypay.service;

import java.util.List;
import java.util.Map;

import com.bypay.domain.MerProfitStatictis;

public interface MerProfitStatictisService {

	/**
	 * 查询某月的商户分润统计
	 * @param merProfitStatictis
	 * @return
	 */
	List<MerProfitStatictis> dayProfitStatistics(MerProfitStatictis merProfitStatictis);
	
	
	/**
	 * 按每日统计成功交易的金额、手续费YJH
	 * @param mapParameter
	 * @return
	 */
	List<MerProfitStatictis> getSucTransAmtFeeStatistics(Map mapParameter);

}
