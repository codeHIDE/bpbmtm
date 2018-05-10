package com.richerpay.dubbo.service;

import com.richerpay.core.model.CoreTransInfo;
/**
 * 消费接口
 * @author Lynx
 *
 */
public interface NewPayService {
	/**
	 * 消费动作
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doPay(CoreTransInfo coreTransInfo);
	/**
	 * 消费撤销
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doPayCancel(CoreTransInfo coreTransInfo);
	/**
	 * 消费冲正
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doPayReverse(CoreTransInfo coreTransInfo);
	/**
	 * 代付动作
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doEntrustPay(CoreTransInfo coreTransInfo);
	/**
	 * 代付验证
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doEntrustPayVerify(CoreTransInfo coreTransInfo);
	/**
	 * 代付确认
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doEntrustPayCheck(CoreTransInfo coreTransInfo);
}
