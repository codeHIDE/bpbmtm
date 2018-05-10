package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.ChannelFeeType;


public interface ChannelFeeTypeDao
{

	 Integer selectChannelFeeTypeCount(Map map);
	  
	 List<ChannelFeeType> selectChannelFeeType(Map map);
	 
	 int insertChannelFeeType(ChannelFeeType channelFeeType);
	 
	 List<ChannelFeeType> selectAllChannelFeeType();


}
