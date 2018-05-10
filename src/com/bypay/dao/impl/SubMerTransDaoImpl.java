package com.bypay.dao.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SubMerTransDao;
import com.bypay.domain.SubMerTrans;

@Repository("subMerTransDao")
public class SubMerTransDaoImpl implements SubMerTransDao {
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public SubMerTrans selectSubMerTransById(SubMerTrans smt) {
		return (SubMerTrans) sqlSessionTemplate.selectOne("selectSubMerTransById", smt);
	}
	
	/**
	 * 添加子商户交易配置信息
	 */
	@Override
	public int insertSubMerTransInfo(SubMerTrans subMerTrans) {
		return sqlSessionTemplate.insert("insertSubMerTransInfo", subMerTrans);
		
	}
	/**
	 * 修改子商户交易配置信息
	 */
	@Override
	public int updateSubMerTransInfo(SubMerTrans subMerTrans) {
		return sqlSessionTemplate.update("updateSubMerTransInfo",subMerTrans);		
	}
	
	@Override
	public int deleteSubMerTransInfo(SubMerTrans subMerTrans) {
		return sqlSessionTemplate.update("deleteSubMerTransInfo",subMerTrans);		
	}

}
