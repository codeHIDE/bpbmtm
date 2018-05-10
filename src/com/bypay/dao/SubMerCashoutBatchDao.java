package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SubMerCashoutBatch;

/**
 * 
 * @Description:
 * @Author: ljl
 * @Date: 2014-9-22 下午5:28:45
 */
public interface SubMerCashoutBatchDao {

  List<SubMerCashoutBatch> selectSubMerCashoutBatch();

  String insertSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch);

  List<SubMerCashoutBatch> selectSubMerCashoutBatchList(Map<String, Object> map);

  Integer selectSubMerCashoutBatchCount(Map<String, Object> map);

  /*
   * 删除
   */
  Integer deleteSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch);

  Integer updateSubMerCashoutBatch(SubMerCashoutBatch subMerCashoutBatch);
}
