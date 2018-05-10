package com.bypay.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.common.ibatis3.transaction.SimpleTransactionSupport;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.domain.MerSettleStatictis;

@Repository("merSettleStatictisDao")
public class MerSettleStatictisDaoImpl implements MerSettleStatictisDao {
  @Inject
  private SqlSessionTemplate sqlSessionTemplate;
  @Inject
  private SimpleTransactionSupport simpleTransactionSupport;

  public SqlSessionTemplate getSqlSessionTemplate() {
    return sqlSessionTemplate;
  }

  public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    this.sqlSessionTemplate = sqlSessionTemplate;
  }

  @Override
  public MerSettleStatictis selectMerSettleStatictisById(Map<String, Object> map) {
    return (MerSettleStatictis) sqlSessionTemplate.selectOne("selectMerSettleStatictisById", map);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisList(Map<String, Object> map) {
    return sqlSessionTemplate.selectList("selectMerSettleStatictisList", map);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisListNotPage(Map<String, Object> map) {
    return sqlSessionTemplate.selectList("selectMerSettleStatictisListNotPage", map);
  }

  @Override
  public Integer selectMerSettleStatictisListCount(Map<String, Object> map) {
    return (Integer) sqlSessionTemplate.selectOne("selectMerSettleStatictisListCount", map);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisListNew(
      MerSettleStatictis merSettleStatictis) {
    return sqlSessionTemplate.selectList("selectMerSettleStatictisListNew", merSettleStatictis);
  }

  @SuppressWarnings("unchecked")
  @Override
  public Map<String, Object> selectMerSettleStatictisByMid(MerSettleStatictis merSettleStatictis) {
    return (Map<String, Object>) sqlSessionTemplate.selectOne("selectMerSettleStatictisByMid", merSettleStatictis);
  }

  @Override
  public Integer updateMerSettleStatictisStatusById(String[] str, String status)
      throws SQLException {
    int i = 0;
    try {
      simpleTransactionSupport.start();
      MerSettleStatictis merSettleStatictis = null;
      // 验证通过，把所有记录只为处理中
      for (String str1 : str) {
        merSettleStatictis = new MerSettleStatictis();
        merSettleStatictis.setId(str1);
        merSettleStatictis.setClearStatus(status);
        i += sqlSessionTemplate.update("updateMerSettleStatictisStatusById", merSettleStatictis);
      }
      simpleTransactionSupport.commit();
    } catch (Exception e) {
      e.printStackTrace();
      simpleTransactionSupport.rollback();
    } finally {
      simpleTransactionSupport.end();
    }
    return i;
  }

  @Override
  public MerSettleStatictis selectMerSettleStatictisByIdNew(MerSettleStatictis merSettleStatictis) {
    return (MerSettleStatictis) sqlSessionTemplate.selectOne("selectMerSettleStatictisByIdNew",
        merSettleStatictis);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisByMerSysIdNew(
      MerSettleStatictis merSettleStatictis) {
    return sqlSessionTemplate.selectList("selectMerSettleStatictisByMerSysIdNew",
        merSettleStatictis);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisStatusByMerSysIdNew(
      MerSettleStatictis merSettleStatictis) {
    return sqlSessionTemplate.selectList("selectMerSettleStatictisStatusByMerSysIdNew",
        merSettleStatictis);
  }

  @Override
  public Integer updateMerSettleStatictisById(MerSettleStatictis merSettleStatictis) {
    return sqlSessionTemplate.update("updateMerSettleStatictisById", merSettleStatictis);
  }

  @Override
  public Integer updateMerSettleStatictisStatusById(MerSettleStatictis merSettleStatictis) {
    return sqlSessionTemplate.update("updateMerSettleStatictisStatusById", merSettleStatictis);
  }

  /**
   * 修改清分状态（根据MID，MER_TYPE,SETTLE_DATE）
   */
  @Override
  public Integer updateClearStatus(MerSettleStatictis merSettleStatictis) throws Exception {
    return sqlSessionTemplate.update("updateClearStatus", merSettleStatictis);
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> getSucAmtStatistics(Map<String, Object> mapParameter) {
    return sqlSessionTemplate.selectList("getSucAmtStatistics", mapParameter);
  }

  @Override
  public Integer insertMerSettleStatictis(MerSettleStatictis merSettleStatictis) {
    return sqlSessionTemplate.insert("insertMerSettleStatictis", merSettleStatictis);
  }

  @Override
  public MerSettleStatictis selectSubMerInfoLeftMerSettleStatictis(
      MerSettleStatictis merSettleStatictis) {
    return (MerSettleStatictis) sqlSessionTemplate.selectOne(
        "selectSubMerInfoLeftMerSettleStatictis", merSettleStatictis);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<MerSettleStatictis> selectMerSettleStatictisListAmt(
      MerSettleStatictis merSettleStatictis) {
    return (List<MerSettleStatictis>) sqlSessionTemplate.selectList(
        "selectMerSettleStatictisListAmt", merSettleStatictis);
  }
  @Override
  public List<MerSettleStatictis> dayMerAmtFee(Map mapParameter) {
	return sqlSessionTemplate.selectList("dayMerAmtFee", mapParameter);
  }

  @Override
  public List<MerSettleStatictis> seleteMerSettleStatictisReport(Map map) {
  	return sqlSessionTemplate.selectList("seleteMerSettleStatictisReport",map);
  }
  @Override
  public Integer seleteMerSettleStatictisReportCount(Map map) {
  	return (Integer) sqlSessionTemplate.selectOne("seleteMerSettleStatictisReportCount",map);
  }

@Override
public List<MerSettleStatictis> getClearFaile(
		MerSettleStatictis merSettleStatictis) {
	return sqlSessionTemplate.selectList("getClearFaile",merSettleStatictis);
}
}
