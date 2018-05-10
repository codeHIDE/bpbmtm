package com.bypay.action;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bypay.common.BaseAction;

public class TestAction extends BaseAction {

	
	
	
	public static void main(String[] args) {
		String[] paths = new String[] {"classpath:com/bypay/config/applicationContext*.xml"};
		  //启动Spring，得到Spring环境上下文
		ApplicationContext ctx = new ClassPathXmlApplicationContext(paths);  
//		AirSupplyYxtxInterface airSupply = (AirSupplyYxtxInterface) ctx.getBean("airSupplyYxtxInterface");

	}
	
	

}
