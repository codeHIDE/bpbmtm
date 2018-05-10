package com.richerpay.dubbo.service;

import com.richerpay.core.model.CoreTransInfo;
/**
 * 管理业务类
 * @author Lynx
 *
 */
public interface JManageService {
	/**
	 * 渠道签到
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doChannelSignIn(CoreTransInfo coreTransInfo);
	/**
	 * 渠道主密钥下载
	 * @param coreTransInfo
	 * @return
	 */
	CoreTransInfo doChannelTMKDownload(CoreTransInfo coreTransInfo);
}
