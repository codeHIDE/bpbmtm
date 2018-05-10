package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.FactoryCodeDao;
import com.bypay.domain.FactoryCode;
import com.bypay.service.FactoryCodeService;

@Service("factoryCodeService")
public class FactoryCodeServiceImpl implements FactoryCodeService{
	
	
	@Inject
	private FactoryCodeDao factoryCodeDao;

	@Override
	public int deleteFactoryCode(FactoryCode factoryCode) {
		return factoryCodeDao.deleteFactoryCode(factoryCode);
	}

	@Override
	public int insertFactoryCode(FactoryCode factoryCode) {
		return factoryCodeDao.insertFactoryCode(factoryCode);
	}

	@Override
	public List<FactoryCode> selectFactoryCodeList(Map map) {
		return factoryCodeDao.selectFactoryCodeList(map);
	}

	@Override
	public int selectFactoryCodeListCount(FactoryCode factoryCode) {
		return factoryCodeDao.selectFactoryCodeListCount(factoryCode);
	}

}
