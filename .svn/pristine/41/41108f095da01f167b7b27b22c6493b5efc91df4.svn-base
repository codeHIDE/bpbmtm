package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.WaresSpecInfoDao;
import com.bypay.domain.WaresSpecInfo;
@Repository("waresSpecInfoDao")
public class WaresSpecInfoDaoImpl implements WaresSpecInfoDao {

	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	

	@Override
	public Integer selectSpecCount(Map map) {
	    int num = 0;
	    List<Integer> l = sqlSessionTemplate.selectList("selectSpecCount", map);
	    for (int i = 0; i < l.size(); i++) {
	      num = num + l.get(i);
	    }
	    return num;
	  }

	@Override
	public List<WaresSpecInfo> selectSpec(Map map) {
	    return (List<WaresSpecInfo>) sqlSessionTemplate.selectList("selectSpec", map);
	    }

	@Override
	public int insertSpec(WaresSpecInfo waresSpecInfo) {
		 return sqlSessionTemplate.insert("insertSpec", waresSpecInfo);
	}


}
