package com.bypay.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.MerInfoDao;
import com.bypay.domain.MerInfo;
import com.bypay.service.MerInfoService;

@Service("merInfoService")
public class MerInfoServiceImpl implements MerInfoService{
	
	@Inject
	private MerInfoDao merInfoDao;

	//查询 所有终端号
	@Override
	public List<MerInfo> selectMerInfoByAllSysId(MerInfo merInfo) {
		return merInfoDao.selectMerInfoByAllSysId(merInfo);
	}

}
