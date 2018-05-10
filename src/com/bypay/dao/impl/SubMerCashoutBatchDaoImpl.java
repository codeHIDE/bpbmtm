package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SubMerCashoutBatchDao;
import com.bypay.domain.SubMerCashoutBatch;

@Repository("subMerCashoutBatchDao")
public class SubMerCashoutBatchDaoImpl implements SubMerCashoutBatchDao {

  @Inject
  private SqlSessionTemplate sqlSessionTemplate;

  @SuppressWarnings("unchecked")
  @Override
  public List<SubMerCashoutBatch> selectSubMerCashoutBatch() {
    return sqlSessionTemplate.selectList("selectSubMerCashoutBatch");
  }

  @Override
  public String insertSubMerCashoutBatch(SubMerCashoutBatch subMerCashout) {
    sqlSessionTemplate.insert("insertSubMerCashoutBatch", subMerCashout);
    return subMerCashout.getBatchId();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<SubMerCashoutBatch> selectSubMerCashoutBatchList(Map<String, Object> map) {
    return sqlSessionTemplate.selectList("selectSubMerCashoutBatchList", map);
  }

  @Override
  public Integer selectSubMerCashoutBatchCount(Map<String, Object> map) {
    return (Integer) sqlSessionTemplate.selectOne("selectSubMerCashoutBatchCount", map);
  }

  @Override
  public Integer deleteSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch) {
    return sqlSessionTemplate.delete("deleteSubMerCashoutBatch", subMerCashoutBatch);
  }

  @Override
  public Integer updateSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch) {
    return sqlSessionTemplate.update("updateSubMerCashoutBatch", subMerCashoutBatch);
  }

}
