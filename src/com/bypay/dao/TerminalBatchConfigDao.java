package com.bypay.dao;

import java.util.*;

import com.bypay.domain.TerminalBatchConfig;

public interface TerminalBatchConfigDao {
	/**
	 * 添加一条终端生成批次统计信息
	 */
	boolean insertTerminalBatchConfig(TerminalBatchConfig terminalBatchConfig);
	/**
	 * 修改一条终端生成批次统计信息
	 */
	Integer updateTerminalBatchConfig(TerminalBatchConfig terminalBatchConfig);
	/**
	 * 查询所有
	 */
	@SuppressWarnings("unchecked")
	List<TerminalBatchConfig> selectTerminalBatchConfig(Map terminalBatchConfig);
	/**
	 * 查询所有信息数量
	 */
	@SuppressWarnings("unchecked")
	Integer selectCountTerminalBatchConfig(Map terminalBatchConfig);
}
