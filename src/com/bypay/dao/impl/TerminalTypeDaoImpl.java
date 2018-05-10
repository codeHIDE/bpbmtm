package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Repository;
import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.TerminalTypeDao;
import com.bypay.domain.TerminalType;

@Repository("terminalTypeDao")
public class TerminalTypeDaoImpl implements TerminalTypeDao{
	
	@Inject
	private SqlSessionTemplate sqlSessionTemplate;
	//添加对应表
	
	@Override
	public int insertTerminalType(TerminalType terminalType) {
		return sqlSessionTemplate.insert("insertTerminalType", terminalType);
	}

	//查询对应表列表
	@Override
	public List<TerminalType> selectTerminalTypeList(Map map) {
		return sqlSessionTemplate.selectList("selectTerminalTypeList", map);
	}

	//查询对应表列表 条数分页用
	@Override
	public int selectTerminalTypeListCount(TerminalType terminalType) {
		return (Integer) sqlSessionTemplate.selectOne("selectTerminalTypeListCount", terminalType);
	}

	//删除样式
	@Override
	public int deleteTerminalType(TerminalType terminalType) {
		return sqlSessionTemplate.delete("deleteTerminalType", terminalType);
	}

	@Override
	public List<TerminalType> selectTerminalTypeAll() {
		return sqlSessionTemplate.selectList("selectTerminalTypeAll");
	}
}
