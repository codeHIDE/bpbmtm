package com.bypay.dao;

import java.util.List;

import com.bypay.domain.OrderStatictis;

public interface OrderStatictisDao {
	Integer insertOrderStatictis(OrderStatictis orderStatictis);
	
	Integer selectOrderStatictisOrderId(String orderId);
	
	OrderStatictis selectbyRight(String orderId);
	
	Integer updateOrderStatus(OrderStatictis orderStatictis);
	
	List<OrderStatictis> selectOrderByStatus(OrderStatictis orderStatictis);
	
	List<OrderStatictis> selectSingleOrder(OrderStatictis orderStatictis);
	boolean noLiquidation(String orderId);
}
