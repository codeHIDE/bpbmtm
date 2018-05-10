package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.WaresInfoDao;
import com.bypay.domain.UserType;
import com.bypay.domain.WaresInfo;
import com.bypay.domain.WaresSpecInfo;
@Repository("waresInfoDao")
public class WaresInfoDaoImpl implements WaresInfoDao {

	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Integer selectWaresCount(Map map) {
	    int num = 0;
	    List<Integer> l = sqlSessionTemplate.selectList("selectWaresInfoCount", map);
	    for (int i = 0; i < l.size(); i++) {
	      num = num + l.get(i);
	    }
	    return num;
	  }

	@Override
	public List<WaresInfo> selectWares(Map map) {
	    return (List<WaresInfo>) sqlSessionTemplate.selectList("selectWaresInfo", map);
	    }

	@Override
	public int insertWares(WaresInfo waresInfo) {
		 return sqlSessionTemplate.insert("insertWares", waresInfo);
	}



}
