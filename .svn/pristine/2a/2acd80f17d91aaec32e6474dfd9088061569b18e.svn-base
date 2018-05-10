package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SubMerTerminalInfoDao;
import com.bypay.domain.SubMerTerminalInfo;

@Repository("subMerTerminalInfoDao")
public class SubMerTerminalInfoDaoImpl implements SubMerTerminalInfoDao {
  @Inject
  private SqlSessionTemplate sqlSessionTemplate;

  /**
   * 添加子商户终端信息
   */
  @Override
  public int insertSubMerTerminalInfo(SubMerTerminalInfo subMerTerminalInfo) throws Exception {
    return sqlSessionTemplate.insert("insertSubMerTerminalInfo", subMerTerminalInfo);
  }

  /**
   * 获取子商户设备明细
   */
  @Override
  public SubMerTerminalInfo selectSubMerTerminalInfo(SubMerTerminalInfo subMerTerminalInfo) {
    return (SubMerTerminalInfo) sqlSessionTemplate.selectOne("selectSubMerTerminalInfo",
        subMerTerminalInfo);
  }

  /**
   * 删除子商户设备明细表信息
   */
  @Override
  public Integer deleteSubMerTInfo(SubMerTerminalInfo subMerTerminalInfo) {
    return sqlSessionTemplate.delete("deleteSubMerTInfo", subMerTerminalInfo);
  }

  /**
   * 删除设备明细信息
   */
  @Override
  public Integer deleteTerminalInfo(SubMerTerminalInfo subMerTerminalInfo) {
    return sqlSessionTemplate.delete("deleteTerminalInfo", subMerTerminalInfo);
  }
  
  @Override
  public Integer updateTerminalInfoTsn(SubMerTerminalInfo subMerTerminalInfo) {
    return sqlSessionTemplate.delete("updateTerminalInfoTsn", subMerTerminalInfo);
  }
}
