package com.bypay.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.CoreTransLogDao;
import com.bypay.domain.CoreTransLog;

@Repository("coreTransLogDao")
public class CoreTransLogDaoImpl implements CoreTransLogDao {
	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Map selectCoreTransLogCount(Map map) {
	    return (HashMap)sqlSessionTemplate.selectOne("com.bypay.dao.CoreTransLogDao.selectCoreTransLogCount", map);
	  }

	@Override
	public List<CoreTransLog> selectCoreTransLog(Map map) {
	    return (List<CoreTransLog>) sqlSessionTemplate.selectList("com.bypay.dao.CoreTransLogDao.selectCoreTransLog", map);
	    }

    @Override
    public CoreTransLog selectInfo(CoreTransLog coreTransLog) {
        return (CoreTransLog)sqlSessionTemplate.selectOne("com.bypay.dao.CoreTransLogDao.selectInfo", coreTransLog);
    }

    @Override
    public List<CoreTransLog> selectInfoList(CoreTransLog coreTransLog) {
        return (List<CoreTransLog>) sqlSessionTemplate.selectList("com.bypay.dao.CoreTransLogDao.selectInfoList", coreTransLog);
    }

}
