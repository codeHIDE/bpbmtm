package com.bypay.service;

import java.util.List;
import java.util.Map;

import com.bypay.domain.StatisticsInfo;

public interface StatisticsService {

	/**
	 * 根据卡类型（1借记卡 2信用卡 3贷记卡 4储蓄卡）和订单状态（0未支付 1支付成功 2支付失败 3处理中 4冻结）统计订单信息
	 * @param map
	 * @return
	 */
	StatisticsInfo getOrderInfoByCardType(Map<String, String> map);

	/**
	 * 错误码统计
	 * @param mapParameter
	 * @return
	 */
	List<StatisticsInfo> getErrRespCodeStatistics(Map mapParameter);

	/**
	 * 按每日统计成功交易的金额、手续费
	 * @param mapParameter
	 * @return
	 */
	List<StatisticsInfo> getSucTransAmtFeeStatistics(Map mapParameter);
	
	
	/**
	 * 按每月统计成功交易的金额、手续费 YJH
	 * @param mapParameter
	 * @return
	 */
	List<StatisticsInfo> getMonSucTransAmtFeeStatistics(Map mapParameter);

	/**
	 * 代理商利润数据
	 * @param map
	 * @return
	 */
	List<StatisticsInfo> getAgentProfitStatistics(Map map);

	/**
	 * 代理商利润数据总条数
	 * @param mapParameter
	 * @return
	 */
	Integer getAgentProfitStatisticsCount(Map mapParameter);

	/**
	 * 代理商利润统计
	 * @param mapParameter
	 * @return
	 */
	List<StatisticsInfo> agentProfitDayStatistics(Map mapParameter);
	
	/**
	 * 按每月的当日统计机构利润
	 * @param mapParameter
	 * @return
	 */
	List<StatisticsInfo> getMerFeeStatistics(Map mapParameter);
	
	/**
	 * 下载用查询信息 条数查询
	 * @param mapParameter
	 * @return
	 */
	 Map getStatisticsInfoDowlondCount(Map map);
	 
	 /**
		 * 下载用查询信息 下载信息查询
		 * @param mapParameter
		 * @return
		*/
	List<StatisticsInfo> selecrStatisticsInfoList(Map map);
}
