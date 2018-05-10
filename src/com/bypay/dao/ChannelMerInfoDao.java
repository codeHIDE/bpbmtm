package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.ChannelMerInfo;

public interface ChannelMerInfoDao
{	 
	
	Integer selectChannelMerInfoCount(Map map);
	
	List<ChannelMerInfo> selectChannelMerInfo(Map map);
	
	int insertChannelMerInfo(ChannelMerInfo channelMerInfo);
	
	ChannelMerInfo selectChannelMerInfoByMerId(ChannelMerInfo channelMerInfo);
	
	ChannelMerInfo selectChannelMerInfoById(ChannelMerInfo channelMerInfo);
	
	int updateChannelMerInfo(ChannelMerInfo channelMerInfo);
	
	 List<ChannelMerInfo> selectChannelMerInfoByFee(Map map);
	 
	  int updateStatus(ChannelMerInfo channelMerInfo);



}
