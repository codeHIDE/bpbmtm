package com.bypay.dao;
import java.util.List;
import java.util.Map;

import com.bypay.domain.BlackInfo;

public interface BlackInfoDao {
	/**
	 * 查询所有信息
	 * @param map	参数-分页、条件
	 * @return	信息集合
	 */
	List<BlackInfo> selectBlackInfoList(Map map);
	/**
	 * 查询所有信息数量
	 * @param map	参数-条件
	 * @return	数量
	 */
	Integer selectBlackInfoCount(Map map);
	/**
	 * 修改信息状态
	 * @param bi	条件、修改的值
	 * @return	执行结果
	 */
	boolean updateBlackInfoStatus(BlackInfo bi);
	
	
	boolean deleteBlackInfo(BlackInfo bi);
	/**
	 * 添加黑名单
	 * @param bi 要添加的信息
	 * @return 执行结果
	 */
	boolean insertBlackInfo(BlackInfo bi);
	
	BlackInfo selectBlackInfo(BlackInfo blackInfo);
	
	public BlackInfo selectBlackInfoById(BlackInfo blackInfo);
}
