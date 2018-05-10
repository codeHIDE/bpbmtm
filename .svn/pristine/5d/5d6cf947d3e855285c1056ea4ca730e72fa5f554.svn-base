package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.SubMerRateDao;
import com.bypay.domain.SubMerRate;
@Repository("subMerRateDao")
public class SubMerRateDaoImpl implements SubMerRateDao{
	@Inject
	private SqlSessionTemplate sqlSessionTempalte;
	public SqlSessionTemplate getSqlSessionTempalte() {
		return sqlSessionTempalte;
	}
	public void setSqlSessionTempalte(SqlSessionTemplate sqlSessionTempalte) {
		this.sqlSessionTempalte = sqlSessionTempalte;
	}
	@Override//查询指定的子商户交易费率，用于子商户的详情显示
	public SubMerRate selectSubMerRateGetById(SubMerRate sr) {
		return  (SubMerRate) sqlSessionTempalte.selectOne("selectSubMerRateGetById", sr);
	}
	/**
	 * 添加子商户费率信息
	 */
	@Override
	public int insertSubMerRateInfo(Map<Object, Object> map) {
		return sqlSessionTempalte.insert("insertSubMerRateInfo", map);
	}
	@Override
	public List<SubMerRate> selectListSubMerRateGetById(SubMerRate sr) {
		return sqlSessionTempalte.selectList("selectListSubMerRateGetById",sr);
	}
	@Override
	public List<SubMerRate> selectSubMerRateById(SubMerRate subMerRate) {
		return (List<SubMerRate>) sqlSessionTempalte.selectList("selectSubMerRateById",subMerRate);
	}
	@Override
	public Integer updateSubMerRate(SubMerRate subMerRate) {
		return sqlSessionTempalte.update("updateSubMerRate",subMerRate);
	}
	@Override
	public Integer deleteSubMerRate(SubMerRate subMerRate) {
		return sqlSessionTempalte.update("deleteSubMerRate",subMerRate);
	}
	@Override
	public Integer updateSubMerRateRateType(SubMerRate subMerRate) {
		return sqlSessionTempalte.update("updateSubMerRateRateType",subMerRate);
	}
	@Override
	public Integer updateSubMerRateSubMerTract(SubMerRate subMerRate) {
		return sqlSessionTempalte.update("updateSubMerRateSubMerTract",subMerRate);
	}
	@Override//查询指定的子商户交易费率，用于子商户的详情显示
    public SubMerRate getSubMerRateBySubMerId(SubMerRate sr) {
        return  (SubMerRate) sqlSessionTempalte.selectOne("getSubMerRateBySubMerId", sr);
    }
}
