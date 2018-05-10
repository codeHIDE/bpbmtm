package com.bypay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.TransLogInfoDao;
import com.bypay.domain.TransLogInfo;

@Repository("transLogInfoDao")
public class TransLogInfoDaoImpl implements TransLogInfoDao {
	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Map selectTransLogInfoCount(Map map) {
	    return (HashMap)sqlSessionTemplate.selectOne("selectTransLogInfoCount", map);
	  }

	@Override
	public List<TransLogInfo> selectTransLogInfo(Map map) {
	    return (List<TransLogInfo>) sqlSessionTemplate.selectList("selectTransLogInfo", map);
	    }

}
