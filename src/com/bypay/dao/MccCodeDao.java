package com.bypay.dao;

import java.util.List;
import java.util.Map;
import com.bypay.domain.MccCode;

public interface MccCodeDao {
	List<MccCode> selectMccCodeAll();
	
	//添加对应表
	int insertMccCode(MccCode mccCode);
	
	
	//查询对应码表
	List<MccCode> selectMccCodeList(Map map);
	
	
	//查询对应码表的条数 分页用
	int selectMccCodeListCount( MccCode mccCode);
	
	//删除样式
	int deleteMccCode(MccCode mccCode);
	
	//mcc表添加通道时候用
	List<MccCode> selectMccCodeByDesc(MccCode mccCode);
}
