package com.bypay.dao;
import java.util.List;
import java.util.Map;
import com.bypay.domain.BusType;


public interface BusTypeDao {
	List<BusType> selectBusTypeAll();
	//添加对应表
	int insertBusType(BusType busType);
	
	
	//查询对应码表
	List<BusType> selectBusTypeList(Map map);
	
	
	//查询对应码表的条数 分页用
	int selectBusTypeListCount( BusType busType);
	
	//删除样式
	int deleteBusType(BusType busType);
	

}
