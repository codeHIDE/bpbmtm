package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bypay.dao.MerInfoUpdateDao;
import com.bypay.domain.MerInfoUpdate;
import com.bypay.service.MerInfoUpdateService;
@Service("MerInfoUpdateService")
public class MerInfoUpdateServiceImpl implements MerInfoUpdateService {
	@Inject
	private MerInfoUpdateDao merInfoUpdateDao;
	@Override
	public List<MerInfoUpdate> selectMerInfoUpdate(Map map) {
		return merInfoUpdateDao.selectMerInfoUpdate(map);
	}

	@Override 
	public MerInfoUpdate selectMerInfoUpdateById(String id) {
		return merInfoUpdateDao.selectMerInfoUpdateById(id);
	}

	@Override
	public Integer selectMerInfoUpdateCount(Map map) {
		return merInfoUpdateDao.selectMerInfoUpdateCount(map);
	}

	@Override
	public Integer updateMerInfoUpdateStatus(MerInfoUpdate merInfoUpdate) {
		return merInfoUpdateDao.updateMerInfoUpdateStatus(merInfoUpdate);
	}

	@Override
	public List<MerInfoUpdate> selectMerInfoUpdateStatus() {
		return merInfoUpdateDao.selectMerInfoUpdateStatus();
	}

}
