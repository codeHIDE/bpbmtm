package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.ChannelAddInfoDao;
import com.bypay.domain.ChannelAddInfo;

@Repository("channelAddInfoDao")
public class ChannelAddInfoDaoImpl implements ChannelAddInfoDao {
	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer selectChannelAddInfoCount(Map map) {
	    int num = 0;
	    List<Integer> l = sqlSessionTemplate.selectList("selectChannelAddInfoCount", map);
	    for (int i = 0; i < l.size(); i++) {
	      num = num + l.get(i);
	    }
	    return num;
	  }

	@Override
	public List<ChannelAddInfo> selectChannelAddInfo(Map map) {
	    return (List<ChannelAddInfo>) sqlSessionTemplate.selectList("selectChannelAddInfo", map);
	    }

	@Override
	public int insertChannelAddInfo(ChannelAddInfo channelAddInfo) {
		 return sqlSessionTemplate.insert("insertChannelAddInfo", channelAddInfo);
}
	
   @Override
    public List<ChannelAddInfo> selectAllChannelAddInfo() {
        return (List<ChannelAddInfo>) sqlSessionTemplate.selectList("selectAllChannelAddInfo");
        }
	   
}
