package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.StatisticsDao;
import com.bypay.domain.StatisticsInfo;

@Repository("statisticsDao")
public class StatisticsDaoImpl implements StatisticsDao{

	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	/**
	 * 根据卡类型和订单状态统计
	 */
	@SuppressWarnings("unchecked")
	@Override
	public StatisticsInfo getOrderInfoByCardType(Map<String, String> map) {
		return (StatisticsInfo) sqlSessionTemplate.selectOne("getOrderInfoByCardType", map);
	}

	/**
	 * 错误码统计
	 */
	@Override
	public List<StatisticsInfo> getErrRespCodeStatistics(Map mapParameter) {
		return sqlSessionTemplate.selectList("getErrRespCodeStatistics", mapParameter);
	}

	/**
	 * 按每日统计成功交易的金额、手续费
	 */
	@Override
	public List<StatisticsInfo> getSucTransAmtFeeStatistics(Map mapParameter) {
		return sqlSessionTemplate.selectList("getSucTransAmtFeeStatistics", mapParameter);
	}

	
	/**
	 * 按每月统计成功交易的金额、手续费
	 */
	@Override
	public List<StatisticsInfo> getMonSucTransAmtFeeStatistics(Map mapParameter) {
		return sqlSessionTemplate.selectList("getMonSucTransAmtFeeStatistics", mapParameter);
	}

	/**
	 * 代理商利润数据
	 */
	@Override
	public List<StatisticsInfo> getAgentProfitStatistics(Map map) {
		return sqlSessionTemplate.selectList("getAgentProfitStatistics", map);
	}

	/**
	 * 代理商利润数据总条数
	 */
	@Override
	public Integer getAgentProfitStatisticsCount(Map mapParameter) {
		return (Integer) sqlSessionTemplate.selectOne("getAgentProfitStatisticsCount", mapParameter);
	}

	/**
	 * 代理商利润统计
	 */
	@Override
	public List<StatisticsInfo> agentProfitDayStatistics(Map mapParameter) {
		return sqlSessionTemplate.selectList("agentProfitDayStatistics", mapParameter);
	}
	
	/**
	 * 按每月统计成功交易的金额、手续费
	 */
	@Override
	public List<StatisticsInfo> getMerFeeStatistics(Map mapParameter) {
		return sqlSessionTemplate.selectList("getMerFeeStatistics", mapParameter);
	}

	
	/**
	 * 下载用查询信息 条数查询
	 * @param mapParameter
	 * @return
	 */
	@Override
	public Map getStatisticsInfoDowlondCount(Map map) {
		return (Map) sqlSessionTemplate.selectOne("getStatisticsInfoDowlondCount", map);
	}

	/**
	 * 下载用查询信息 下载信息查询
	 * @param mapParameter
	 * @return
	 */
	@Override
	public List<StatisticsInfo> selecrStatisticsInfoList(Map map) {
		return sqlSessionTemplate.selectList("selecrStatisticsInfoList", map);
	}

}
