package com.bypay.dao.impl;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.BlackInfoDao;
import com.bypay.domain.BlackInfo;

@Repository("blackInfoDao")
public class BlackInfoDaoImpl implements BlackInfoDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;

	@SuppressWarnings("unchecked")
	public List<BlackInfo> selectBlackInfoList(Map map) {
		return sqlSessionTemplate.selectList("selectBlackInfoList", map);
	}
	
	@SuppressWarnings("unchecked")
	public BlackInfo selectBlackInfo(BlackInfo blackInfo){
		return (BlackInfo) sqlSessionTemplate.selectOne("selectBlackInfo", blackInfo);
	}
	
	@SuppressWarnings("unchecked")
	public BlackInfo selectBlackInfoById(BlackInfo blackInfo){
		return (BlackInfo) sqlSessionTemplate.selectOne("selectBlackInfoById", blackInfo);
	}

	@SuppressWarnings("unchecked")
	public Integer selectBlackInfoCount(Map map) {
		return (Integer) sqlSessionTemplate.selectOne("selectBlackInfoCount", map);
	}

	@Override
	public boolean updateBlackInfoStatus(BlackInfo bi) {
		if(sqlSessionTemplate.update("updateBlackInfoStatus", bi)>0)
			return true;
		else
			return false;
	}
	
	@Override
	public boolean deleteBlackInfo(BlackInfo bi) {
		if(sqlSessionTemplate.delete("deleteBlackInfo", bi)>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean insertBlackInfo(BlackInfo bi) {
		if(sqlSessionTemplate.insert("insertBlackInfo", bi)>0)
			return true;
		else
			return false;
	}

}
