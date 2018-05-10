package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerInfoDao;
import com.bypay.domain.MerInfo;
import com.bypay.domain.PlatMerInfo;

@Repository("MerInfoDao")
public class MerInfoDaoImpl implements MerInfoDao {
  @Inject
  private SqlSessionTemplate sqlSessionTemplate;

  public SqlSessionTemplate getSqlSessionTemplate() {
    return sqlSessionTemplate;
  }

  public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
    this.sqlSessionTemplate = sqlSessionTemplate;
  }

  @Override
  public Integer insertMerInfo(MerInfo merInfo) {
    return sqlSessionTemplate.insert("insertMerInfo", merInfo);
  }

  @Override
  public List<MerInfo> selectMerInfoAll(Map map) {
    return sqlSessionTemplate.selectList("selectMerInfoAll", map);
  }

  @Override
  public MerInfo selectMerInfoById(MerInfo merInfo) {
    return (MerInfo) sqlSessionTemplate.selectOne("selectMerInfoById", merInfo);
  }

  @Override
  public Integer approvalMerchantInfo(MerInfo merInfo) {
    return sqlSessionTemplate.update("approvalMerchantInfo", merInfo);
  }

  @Override
  public Integer updateMerchantInfo(MerInfo merInfo) {
    return sqlSessionTemplate.update("updateMerchantInfo", merInfo);
  }

  @Override
  public Integer selectMerInfoCount(MerInfo merInfo) {
    return (Integer) sqlSessionTemplate.selectOne("selectMerInfoCount", merInfo);
  }

  @Override
  public Integer updateSettleMerchantInfo(MerInfo merInfo) {
    return sqlSessionTemplate.update("updateSettleMerchantInfo", merInfo);
  }

  @Override
  public List<MerInfo> selectMerInfoAllByPlatMerId(Map map) {
    return sqlSessionTemplate.selectList("selectMerInfoAllByPlatMerId", map);
  }

  @Override
  public Integer selectMerInfoAllByPlatMerIdCount(Map map) {
    return (Integer) sqlSessionTemplate.selectOne("selectMerInfoAllByPlatMerIdCount", map);
  }

  /**
   * 传图片成功后修改数据库机构LOGO字段地址
   * 
   * @return
   */
  @Override
  public int updateMerLogo(MerInfo merInfo) {
    return sqlSessionTemplate.update("updateMerLogo", merInfo);
  }

  // 查询所有机构商户号
  @Override
  public List<MerInfo> selectMerInfoByAllSysId(MerInfo merInfo) {
    return sqlSessionTemplate.selectList("selectMerInfoByAllSysId", merInfo);
  }

  @Override
  public MerInfo selectMerInfoByMerSysId(MerInfo merInfo) {
    return (MerInfo) sqlSessionTemplate.selectOne("selectMerInfoByMerSysId", merInfo);
  }

  @Override
  public Integer updateIsIntoPieces(MerInfo merInfo) {
    return sqlSessionTemplate.update("updateIsIntoPieces", merInfo);
  }
}
