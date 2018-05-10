package com.bypay.dao.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import com.bypay.common.ibatis3.SqlSessionTemplate;
import com.bypay.dao.ChannelFeeTypeDao;
import com.bypay.domain.ChannelFeeType;

@Repository("channelFeeTypeDao")
public class ChannelFeeTypeDaoImpl implements ChannelFeeTypeDao {
	@Inject
	  private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public Integer selectChannelFeeTypeCount(Map map) {
	    int num = 0;
	    List<Integer> l = sqlSessionTemplate.selectList("selectChannelFeeTypeCount", map);
	    for (int i = 0; i < l.size(); i++) {
	      num = num + l.get(i);
	    }
	    return num;
	  }

	@Override
	public List<ChannelFeeType> selectChannelFeeType(Map map) {
	    return (List<ChannelFeeType>) sqlSessionTemplate.selectList("selectChannelFeeType", map);
	    }

	@Override
	public int insertChannelFeeType(ChannelFeeType channelFeeType) {
		 return sqlSessionTemplate.insert("insertChannelFeeType", channelFeeType);
}
	
	@Override
	public List<ChannelFeeType> selectAllChannelFeeType() {
	    return (List<ChannelFeeType>) sqlSessionTemplate.selectList("selectAllChannelFeeType");
	    }
	
}
