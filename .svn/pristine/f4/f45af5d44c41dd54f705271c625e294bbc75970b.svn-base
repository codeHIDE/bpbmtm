package com.bypay.dao;

import java.util.List;
import java.util.Map;
import com.bypay.domain.TractStatictis;

public interface TractStatictisDao {
	
	//通道查询 查询所有通道信息
	List<TractStatictis> selectTractStatictisList(Map map);
	
	//查询通道的条数 分页用
	 Map<String,Object> selectTractStatictisCount(TractStatictis tractStatictis);
	
	//跑批插入
	int insertTractStatictisCount(TractStatictis tractStatictis);

    Integer updateTractStatictis(TractStatictis tractStatictis);
    
    TractStatictis getTractStatictis(TractStatictis tractStatictis);
    
    //通道启动更新
  	int updateTractStatictisReserved(TractStatictis tractStatictis);

}
