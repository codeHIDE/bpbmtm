package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.MccCodeDao;
import com.bypay.domain.MccCode;
@Repository("mccCodeDao")
public class MccCodeDaoImpl implements MccCodeDao {
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	
	//添加对应表
	@Override
	public int insertMccCode(MccCode mccCode) {
		return sqlSessionTemplate.insert("insertMccCode", mccCode);
	}

	//查询对应表列表
	@Override
	public List<MccCode> selectMccCodeList(Map map) {
		return sqlSessionTemplate.selectList("selectMccCodeList", map);
	}

	//查询对应表列表 条数分页用
	@Override
	public int selectMccCodeListCount(MccCode mccCode) {
		return (Integer) sqlSessionTemplate.selectOne("selectMccCodeListCount", mccCode);
	}

	//删除样式
	@Override
	public int deleteMccCode(MccCode mccCode) {
		return sqlSessionTemplate.delete("deleteMccCode", mccCode);
	}

	@Override
	public List<MccCode> selectMccCodeAll() {
		return sqlSessionTemplate.selectList("selectMccCodeAll");
	}

	
	//mcc表添加通道时候用
	@Override
	public List<MccCode> selectMccCodeByDesc(MccCode mccCode) {
		return sqlSessionTemplate.selectList("selectMccCodeByDesc", mccCode);
	}


}
