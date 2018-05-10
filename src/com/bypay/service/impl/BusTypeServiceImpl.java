package com.bypay.service.impl;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Service;
import com.bypay.dao.BusTypeDao;
import com.bypay.domain.BusType;
import com.bypay.service.BusTypeService;


@Service("busTypeService")
public class BusTypeServiceImpl implements BusTypeService{
	
	@Inject
	private BusTypeDao busTypeDao;

	//添加对应表
	@Override
	public int insertBusType(BusType busType) {
		return busTypeDao.insertBusType(busType);
	}


	@Override
	public List<BusType> selectBusTypeList(Map map) {
		return busTypeDao.selectBusTypeList(map);
	}


	@Override
	public int selectBusTypeListCount(BusType busType) {
		return busTypeDao.selectBusTypeListCount(busType);
	}


	@Override
	public int deleteBusType(BusType busType) {
		return busTypeDao.deleteBusType(busType);
	}


	public List<BusType> selectBusTypeAll() {
		return busTypeDao.selectBusTypeAll();
	}

}
