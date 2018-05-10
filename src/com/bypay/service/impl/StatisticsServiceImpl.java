package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.StatisticsDao;
import com.bypay.domain.StatisticsInfo;
import com.bypay.service.StatisticsService;

@Service("statisticsService")
public class StatisticsServiceImpl implements StatisticsService{
	
	@Inject
	private StatisticsDao statisticsDao;

	/**
	 * 根据卡类型和订单状态统计
	 */
	@Override
	public StatisticsInfo getOrderInfoByCardType(Map<String, String> map) {
		return statisticsDao.getOrderInfoByCardType(map);
	}

	/**
	 * 错误码统计
	 */
	@Override
	public List<StatisticsInfo> getErrRespCodeStatistics(Map mapParameter) {
		return statisticsDao.getErrRespCodeStatistics(mapParameter);
	}

	/**
	 * 按每日统计成功交易的金额、手续费
	 */
	@Override
	public List<StatisticsInfo> getSucTransAmtFeeStatistics(Map mapParameter) {
		return statisticsDao.getSucTransAmtFeeStatistics(mapParameter);
	}

	
	/**
	 * 按每月统计成功交易的金额、手续费 YJH
	 */
	@Override
	public List<StatisticsInfo> getMonSucTransAmtFeeStatistics(Map mapParameter) {
		return statisticsDao.getMonSucTransAmtFeeStatistics(mapParameter);
	}

	/**
	 * 代理商利润数据
	 */
	@Override
	public List<StatisticsInfo> getAgentProfitStatistics(Map map) {
		return statisticsDao.getAgentProfitStatistics(map);
	}

	/**
	 * 代理商利润数据总条数
	 */
	@Override
	public Integer getAgentProfitStatisticsCount(Map mapParameter) {
		return statisticsDao.getAgentProfitStatisticsCount(mapParameter);
	}

	/**
	 * 代理商利润统计
	 */
	@Override
	public List<StatisticsInfo> agentProfitDayStatistics(Map mapParameter) {
		return statisticsDao.agentProfitDayStatistics(mapParameter);
	}
	
	/**
	 * 按每月统计成功交易的金额、手续费YJH
	 */
	@Override
	public List<StatisticsInfo> getMerFeeStatistics(Map mapParameter) {
		return statisticsDao.getMerFeeStatistics(mapParameter);
	}

	@Override
	public Map getStatisticsInfoDowlondCount(Map map) {
		return statisticsDao.getStatisticsInfoDowlondCount(map);
	}

	@Override
	public List<StatisticsInfo> selecrStatisticsInfoList(Map map) {
		return statisticsDao.selecrStatisticsInfoList(map);
	}
}
