package com.bypay.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.bypay.dao.OrderRiskDao;
import com.bypay.domain.OrderRisk;
import com.bypay.service.OrderRiskService;
import com.bypay.util.DateUtil;
@Service("orderRiskService")
public class OrderRiskServiceImpl implements OrderRiskService {
	
	@Inject
	private OrderRiskDao orderRiskDao;

	@Override
	public List<OrderRisk> getOrderRiskList(OrderRisk orderRisk,String startTime,String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", orderRisk.getPage());
		map.put("pageSize", orderRisk.getPageSize());
		map.put("isDownload", orderRisk.getIsDownload());
		//业务处理..........
		if(orderRisk!=null){
			if( StringUtils.isNotEmpty(orderRisk.getSubmerId())){
				map.put("submerId", orderRisk.getSubmerId());
			}else if(StringUtils.isNotEmpty(orderRisk.getOrderId())){
				map.put("orderId", orderRisk.getOrderId());
			}
		}
		if(StringUtils.isNotEmpty(startTime)){
			map.put("startTime", startTime);
		}else if(StringUtils.isNotEmpty(endTime)){					
			try {
				//在查询结束时间上加一天
				Date date = new SimpleDateFormat("yy-MM-dd").parse(endTime);
				map.put("endTime", DateUtil.getNextWeekDay(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return orderRiskDao.getOrderRiskList(map);
	}


	/**
	 * 查询异常交易记录的数据总条数
	 */
	@Override
	public Integer getOrderRiskListCount(OrderRisk orderRisk, String startTime,
			String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", orderRisk.getPage());
		map.put("pageSize", orderRisk.getPageSize());
		map.put("isDownload", orderRisk.getIsDownload());
		//业务处理..........
		if(orderRisk!=null){
			if( StringUtils.isNotEmpty(orderRisk.getSubmerId())){
				map.put("submerId", orderRisk.getSubmerId());
			}else if(StringUtils.isNotEmpty(orderRisk.getOrderId())){
				map.put("orderId", orderRisk.getOrderId());
			}
		}
		if(StringUtils.isNotEmpty(startTime)){
			map.put("startTime", startTime);
		}else if(StringUtils.isNotEmpty(endTime)){					
			try {
				//在查询结束时间上加一天
				Date date = new SimpleDateFormat("yy-MM-dd").parse(endTime);
				map.put("endTime", DateUtil.getNextWeekDay(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return orderRiskDao.getOrderRiskListCount(map);
	}
}
