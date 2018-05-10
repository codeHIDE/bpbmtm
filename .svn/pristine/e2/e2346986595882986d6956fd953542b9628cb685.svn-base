package com.bypay.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bypay.dao.SubMerCashoutBatchDao;
import com.bypay.domain.SubMerCashoutBatch;
import com.bypay.service.SubMerCashoutBatchService;

@Service
public class SubMerCashoutBatchServiceImpl implements SubMerCashoutBatchService {
  @Autowired
  private SubMerCashoutBatchDao subMerCashoutBatchDao;

  @Override
  public List<SubMerCashoutBatch> selectSubMerCashoutBatch() {
    return subMerCashoutBatchDao.selectSubMerCashoutBatch();
  }

  @Override
  public String insertSubMerCashoutBatch(SubMerCashoutBatch subMerCashout) {
    return subMerCashoutBatchDao.insertSubMerCashoutBatch(subMerCashout);
  }

  @Override
  public List<SubMerCashoutBatch> selectSubMerCashoutBatchList(Map<String, Object> map) {
    return subMerCashoutBatchDao.selectSubMerCashoutBatchList(map);
  }

  @Override
  public Integer selectSubMerCashoutBatchCount(Map<String, Object> map) {
    return (Integer) subMerCashoutBatchDao.selectSubMerCashoutBatchCount(map);
  }

  @Override
  public Integer deleteSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch) {
    return subMerCashoutBatchDao.deleteSubMerCashoutBatch(subMerCashoutBatch);
  }

  @Override
  public Integer updateSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch) {
    return subMerCashoutBatchDao.updateSubMerCashoutBatch(subMerCashoutBatch);
  }
}
