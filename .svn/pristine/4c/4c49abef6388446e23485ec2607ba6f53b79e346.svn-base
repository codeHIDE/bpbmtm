package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.TerminalTypeDao;
import com.bypay.dao.TerminalTypeDao;
import com.bypay.domain.TerminalType;
import com.bypay.service.TerminalTypeService;

@Service("terminalTypeService")
public class TerminalTypeServiceImpl implements TerminalTypeService{
	
	@Inject
	private TerminalTypeDao terminalTypeDao;

	//添加对应表
	@Override
	public int insertTerminalType(TerminalType terminalType) {
		return terminalTypeDao.insertTerminalType(terminalType);
	}


	@Override
	public List<TerminalType> selectTerminalTypeList(Map map) {
		return terminalTypeDao.selectTerminalTypeList(map);
	}


	@Override
	public int selectTerminalTypeListCount(TerminalType terminalType) {
		return terminalTypeDao.selectTerminalTypeListCount(terminalType);
	}


	@Override
	public int deleteTerminalType(TerminalType terminalType) {
		return terminalTypeDao.deleteTerminalType(terminalType);
	}


	@Override
	public List<TerminalType> selectTerminalTypeAll() {
		return terminalTypeDao.selectTerminalTypeAll();
	}


}
