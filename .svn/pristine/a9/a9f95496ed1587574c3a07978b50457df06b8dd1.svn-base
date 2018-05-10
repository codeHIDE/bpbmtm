package com.bypay.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bypay.dao.SubMerInfoDao;
import com.bypay.domain.SubMerInfo;
import com.bypay.service.SubMerInfoService;

@Service
public class SubMerInfoServiceImpl implements SubMerInfoService {
  @Autowired
  private SubMerInfoDao subMerInfoDao;

  @Override
  public SubMerInfo getSubMerInfoById(String subMerId) {
    return subMerInfoDao.getSubMerInfoById(subMerId);
  };

  @Override
  public boolean update(SubMerInfo smi) {
    return subMerInfoDao.update(smi);
  };
  
  @Override
  public int updateCheckSub(String subMerId){
	  return subMerInfoDao.updateCheckSub(subMerId);
  }
}
