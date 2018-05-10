package com.bypay.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MerInfoUpdateLogDao;
import com.bypay.domain.MerInfoUpdateLog;
@Repository("MerInfoUpdateLogDao")
public class MerInfoUpdateLogDaoImpl implements MerInfoUpdateLogDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public boolean insertMerInfoUpdateLog(MerInfoUpdateLog miul) {
		if( sqlSessionTemplate.insert("insertMerInfoUpdateLog",miul)>0){
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public MerInfoUpdateLog selectOrgTime(MerInfoUpdateLog miul) {
		List<MerInfoUpdateLog> lm=sqlSessionTemplate.selectList("selectOrgTime", miul);
		if(lm.size()>0&&lm!=null)
			return lm.get(0);
		else
			return null;
	}
}
