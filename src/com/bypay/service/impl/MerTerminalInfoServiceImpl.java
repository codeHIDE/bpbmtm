package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.MerTerminalInfoDao;
import com.bypay.domain.MerTerminalInfo;
import com.bypay.domain.TractInfo;
import com.bypay.service.MerTerminalInfoService;


@Service("merTerminalInfoService")
public class MerTerminalInfoServiceImpl implements  MerTerminalInfoService{

	@Inject
	private MerTerminalInfoDao merTerminalInfoDao;
	//添加终端信息
	@Override
	public int insertMerTerminalInfo(MerTerminalInfo merTerminalInfo) {
		return merTerminalInfoDao.insertMerTerminalInfo(merTerminalInfo);
	}
	//查询所有机构终端
	@Override
	public List<MerTerminalInfo> selectMerTerminalInfoList(Map map) {
		List<MerTerminalInfo> list = null;
		try {
			list = merTerminalInfoDao.selectMerTerminalInfoList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//查询所有机构终端条数分页用
	@Override
	public int selectMerTerminalInfoCount(MerTerminalInfo merTerminalInfo) {
		return merTerminalInfoDao.selectMerTerminalInfoCount(merTerminalInfo);
	}
	@Override
	public MerTerminalInfo selectMerTerminalInfoOne(
			MerTerminalInfo merTerminalInfo) {
		return merTerminalInfoDao.selectMerTerminalInfoOne(merTerminalInfo);
	}
	@Override
	public int updateMerTerminalInfo(MerTerminalInfo merTerminalInfo) {
		return merTerminalInfoDao.updateMerTerminalInfo(merTerminalInfo);
	}

}
