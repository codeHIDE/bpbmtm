/**
 * @ClassName:     BankCardCheckService.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         Eason Jiang
 * @version        V1.0  
 * @Date           2015-7-6 下午3:11:04 
 */
package com.bypay.service;

import java.util.Map;

/** 
 * @ClassName: BankCardCheckService 
 * @Description: TODO(3	银行卡鉴权接口) 
 * @author Eason Jiang 
 * @date 2015-7-6 下午3:11:04 
 *  
 */
public interface BankCardCheckService {
	
	public Map<String,String> checkBankCard(Map<String, String> param);
}
