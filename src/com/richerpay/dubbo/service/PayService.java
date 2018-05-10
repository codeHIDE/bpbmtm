package com.richerpay.dubbo.service;

import com.richerpay.core.model.CoreTransInfo;
/**
 * 消费接口
 * @author Lynx
 *
 */
public interface PayService {
	/**
	 * 消费动作
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doPay(CoreTransInfo coreTransInfo);
	/**
	 * 代付交易
	 * @Title:        doEntrustPay 
	 * @Description:  
	 * @param:        @param coreTransInfo
	 * @param:        @return    
	 * @return:       CoreTransInfo    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-7-1 上午10:21:40
	 */
	CoreTransInfo doEntrustPay(CoreTransInfo coreTransInfo);
	/**
	 * 代付验证
	 * @Title:        doEntrustPayVerify 
	 * @Description:  
	 * @param:        @param coreTransInfo
	 * @param:        @return    
	 * @return:       CoreTransInfo    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-7-1 下午3:09:42
	 */
	CoreTransInfo doEntrustPayVerify(CoreTransInfo coreTransInfo);
	
	/**
	 * 冲正交易
	 * @Title:        doPayReverse 
	 * @Description:  
	 * @param:        @param coreTransInfo
	 * @param:        @return    
	 * @return:       CoreTransInfo    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-7-2 上午10:42:03
	 */
	CoreTransInfo doPayReverse(CoreTransInfo coreTransInfo);
	/**
	 * 代付确认
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doEntrustPayCheck(CoreTransInfo coreTransInfo);

}
