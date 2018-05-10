package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.StatisticsInfo;

public interface BypayProfitStatictisDao {

	/**
	 * 获取系统日分润统计数据总条数
	 * @param mapParameter
	 * @return
	 */
	Map getBypayProfitStatictisCount(Map mapParameter);

	/**
	 * 获取系统日分润统计LIST
	 * @param map
	 * @return
	 */
	List<StatisticsInfo> getBypayProfitStatictisList(Map map);

}
