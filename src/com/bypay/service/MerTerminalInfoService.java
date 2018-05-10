package com.bypay.service;

import java.util.List;
import java.util.Map;

import com.bypay.domain.MerTerminalInfo;

public interface MerTerminalInfoService {
	
	//添加终端配置信息
	int insertMerTerminalInfo(MerTerminalInfo merTerminalInfo);
	
	//终端查询 查询所有机构商户的终端信息
	List<MerTerminalInfo> selectMerTerminalInfoList(Map map);
	
	//查询机构终端的条数 分页用
	int selectMerTerminalInfoCount(MerTerminalInfo merTerminalInfo);

	/**
	 * 查询指定机构终端信息
	 * @param merTerminalInfo 查询条件
	 * @return  查询结果
	 */
	MerTerminalInfo selectMerTerminalInfoOne(MerTerminalInfo merTerminalInfo);
	
	/**
	 * 修改指定机构终端
	 * @param merTerminalInfo 需要修改的信息
	 * @return	修改结果
	 */
	int updateMerTerminalInfo(MerTerminalInfo merTerminalInfo);
}
