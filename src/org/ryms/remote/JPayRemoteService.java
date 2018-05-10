package org.ryms.remote;

import org.ryms.model.CoreTransInfoDubbo;

/**
 * 
 * @ClassName: JPayRemoteService
 * @Description: 作为dubbo公布服务接口
 * @author A18ccms a18ccms_gmail_com
 * @date 2016年4月14日 上午9:15:01
 */
public interface JPayRemoteService {
	/**
	 * 代付T0
	 * 
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfoDubbo doPay(CoreTransInfoDubbo coreTransInfo);

	/**
	 * 平台签到
	 * 
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfoDubbo doChannelSignIn(CoreTransInfoDubbo coreTransInfo);

	/**
	 * pinBlock加密
	 * 
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfoDubbo doPinBlock(CoreTransInfoDubbo coreTransInfo);

}
