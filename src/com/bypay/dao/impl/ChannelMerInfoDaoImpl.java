package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.ChannelMerInfoDao;
import com.bypay.domain.ChannelMerInfo;

@Repository("channelMerInfoDao")
public class ChannelMerInfoDaoImpl implements ChannelMerInfoDao {
	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer selectChannelMerInfoCount(Map map) {
	    int num = 0;
	    List<Integer> l = sqlSessionTemplate.selectList("selectChannelMerInfoCount", map);
	    for (int i = 0; i < l.size(); i++) {
	      num = num + l.get(i);
	    }
	    return num;
	  }

	@Override
	public List<ChannelMerInfo> selectChannelMerInfo(Map map) {
	    return (List<ChannelMerInfo>) sqlSessionTemplate.selectList("selectChannelMerInfo", map);
	    }

	@Override
	public int insertChannelMerInfo(ChannelMerInfo channelMerInfo) {
		 return sqlSessionTemplate.insert("insertChannelMerInfo", channelMerInfo);
}

    @Override
    public ChannelMerInfo selectChannelMerInfoByMerId(ChannelMerInfo channelMerInfo) {
        return (ChannelMerInfo) sqlSessionTemplate.selectOne("selectChannelMerInfoByMerId", channelMerInfo);
    }
    
    @Override
    public ChannelMerInfo selectChannelMerInfoById(ChannelMerInfo channelMerInfo) {
        return (ChannelMerInfo) sqlSessionTemplate.selectOne("selectChannelMerInfoById", channelMerInfo);
    }

    @Override
    public int updateChannelMerInfo(ChannelMerInfo channelMerInfo) {
        return sqlSessionTemplate.update("updateChannelMerInfo", channelMerInfo);
    }

    @Override
    public List<ChannelMerInfo> selectChannelMerInfoByFee(Map map) {
        return (List<ChannelMerInfo>) sqlSessionTemplate.selectList("selectChannelMerInfoByFee", map);
    }

    @Override
    public int updateStatus(ChannelMerInfo channelMerInfo) {
        return sqlSessionTemplate.update("updateMerStat", channelMerInfo);
    }
	
}
