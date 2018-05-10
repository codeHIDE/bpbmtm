package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.CssConfigDao;
import com.bypay.dao.MccCodeDao;
import com.bypay.domain.CssConfig;
import com.bypay.domain.MccCode;
import com.bypay.domain.TractInfo;
import com.bypay.service.CssConfigService;
import com.bypay.service.MccCodeService;


@Service("mccCodeService")
public class MccCodeServiceImpl implements MccCodeService {

	
	@Inject
	private MccCodeDao mccCodeDao;
	
	
	//添加对应表
	@Override
	public int insertMccCode(MccCode mccCode) {
		return mccCodeDao.insertMccCode(mccCode);
	}


	@Override
	public List<MccCode> selectMccCodeList(Map map) {
		return mccCodeDao.selectMccCodeList(map);
	}


	@Override
	public int selectMccCodeListCount(MccCode mccCode) {
		return mccCodeDao.selectMccCodeListCount(mccCode);
	}


	@Override
	public int deleteMccCode(MccCode mccCode) {
		return mccCodeDao.deleteMccCode(mccCode);
	}

	public List<MccCode> selectMccCodeAll(){
		return mccCodeDao.selectMccCodeAll();
	}


	@Override
	public List<MccCode> selectMccCodeByDesc(MccCode mccCode) {
		return mccCodeDao.selectMccCodeByDesc(mccCode);
	}
	

}
