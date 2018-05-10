<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>分享到朋友圈</title>
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript"
			src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
		<script type="text/javascript">
			var appId = "";
			var nonceStr = "";
			var signature = "";
			var timestamp = "";
			var invitationCode = $("#invitationCode").val();//邀请码
			var url = encodeURIComponent(location.href.split('#')[0]);
			$.ajax({
				cache : true,
				type : "POST",
				url: "<s:url value='/member!sign.ac'/>?url="+url,
				async : false,
				error : function(request) {
					alert("<span>连接异常</span>");
				},
				success : function(data) {
					if (data.code != "true") {
						alert("获取签名失败");
					} else {
						appId = data.appId;
						nonceStr = data.nonceStr;
						signature = data.signature;
						timestamp = data.timestamp;
					}

				}
			});

			/*    
			 * 注意：    
			 * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。    
			 * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。    
			 * 3. 完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html    
			 *    
			 * 如有问题请通过以下渠道反馈：    
			 * 邮箱地址：weixin-open@qq.com    
			 * 邮件主题：【微信JS-SDK反馈】具体问题    
			 * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。    
			 */
		wx.config({
		    debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
		    appId: 'wxe910a620c661fe92', // 必填，公众号的唯一标识
		    timestamp : timestamp,
			nonceStr : nonceStr,
			signature : signature,
		    jsApiList: [ 'checkJsApi', 'onMenuShareTimeline',
						'onMenuShareAppMessage', 'onMenuShareQQ',
						'onMenuShareWeibo', 'hideMenuItems', 'showMenuItems',
						'hideAllNonBaseMenuItem', 'showAllNonBaseMenuItem',
						'translateVoice', 'startRecord', 'stopRecord',
						'onRecordEnd', 'playVoice', 'pauseVoice', 'stopVoice',
						'uploadVoice', 'downloadVoice', 'chooseImage',
						'previewImage', 'uploadImage', 'downloadImage',
						'getNetworkType', 'openLocation', 'getLocation',
						'hideOptionMenu', 'showOptionMenu', 'closeWindow',
						'scanQRCode', 'chooseWXPay', 'openProductSpecificView',
						'addCard', 'chooseCard', 'openCard' ]
});
/*
			 * 注意：
			 * 1. 所有的JS接口只能在公众号绑定的域名下调用，公众号开发者需要先登录微信公众平台进入“公众号设置”的“功能设置”里填写“JS接口安全域名”。
			 * 2. 如果发现在 Android 不能分享自定义内容，请到官网下载最新的包覆盖安装，Android 自定义分享接口需升级至 6.0.2.58 版本及以上。
			 * 3. 完整 JS-SDK 文档地址：http://mp.weixin.qq.com/wiki/7/aaa137b55fb2e0456bf8dd9148dd613f.html
			 *
			 * 如有问题请通过以下渠道反馈：
			 * 邮箱地址：weixin-open@qq.com
			 * 邮件主题：【微信JS-SDK反馈】具体问题
			 * 邮件内容说明：用简明的语言描述问题所在，并交代清楚遇到该问题的场景，可附上截屏图片，微信团队会尽快处理你的反馈。
			 */
			wx
					.ready(function() {
						var invitationCode = $("#invitationCode").val();
						// 1 判断当前版本是否支持指定 JS 接口，支持批量判断
						document.querySelector('#checkJsApi').onclick = function() {
							wx.checkJsApi({
								jsApiList : [ 'onMenuShareAppMessage',
										'onMenuShareTimeline' ],
								success : function(res) {
									//alert("这个是success"+JSON.stringify(res));
								}
							});
						};

						wx
								.onMenuShareAppMessage({
									title : '微微夫人',
									desc : '您的好友“'
											+ nickname
											+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员抢好货！即刻起注册既有好礼送！',
									link : 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0006948e0effed72&redirect_uri=http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
											+ invitationCode
											+ '&requestFrom=memberShare'
											+ '&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect',
									imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
									trigger : function(res) {
										//alert('用户点击发送给朋友');
									},
									success : function(res) {
										//alert('已分享');
										//alert(invitationCode);
									},
									cancel : function(res) {
										//alert('已取消');
									},
									fail : function(res) {
										//alert(JSON.stringify(res));
									}
								});

						wx
								.onMenuShareTimeline({
									title : '您的好友“'
											+ nickname
											+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员抢好货！即刻起注册既有好礼送！',
									link : 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0006948e0effed72&redirect_uri=http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
											+ invitationCode
											+ '&requestFrom=memberShare&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect',
									imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
									trigger : function(res) {
										//alert('用户点击分享到朋友圈');
									},
									success : function(res) {
										//alert('已分享');
									},
									cancel : function(res) {
										//alert('已取消');
									},
									fail : function(res) {
										//alert(JSON.stringify(res));
									}
								});
						// alert('已注册获取“分享到朋友圈”状态事件');
						wx
								.onMenuShareQQ({
									title : '微微夫人',
									desc : '您的好友“'
											+ nickname
											+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员抢好货！即刻起注册既有好礼送！',
									link : 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0006948e0effed72&redirect_uri=http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
											+ invitationCode
											+ '&requestFrom=memberShare&scope=snsapi_userinfo&state=STATE#wechat_redirect',
									imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
									trigger : function(res) {
										//alert('用户点击分享到QQ');
									},
									complete : function(res) {
										//alert(JSON.stringify(res));
									},
									success : function(res) {
										//alert('已分享');
									},
									cancel : function(res) {
										//alert('已取消');
									},
									fail : function(res) {
										// alert(JSON.stringify(res));
									}
								});

						// 2. 分享接口
						// 2.1 监听“分享给朋友”，按钮点击、自定义分享内容及分享结果接口
						document.querySelector('#onMenuShareAppMessage').onclick = function() {
							wx
									.onMenuShareAppMessage({
										title : '微微夫人',
										desc : '您的好友“'
												+ nickname
												+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员抢好货！即刻起注册既有好礼送！',
										link : 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx0006948e0effed72&redirect_uri=http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
												+ invitationCode
												+ '&requestFrom=memberShare&scope=snsapi_userinfo&state=STATE#wechat_redirect',
										imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
										trigger : function(res) {
											// alert('用户点击发送给朋友');
										},
										success : function(res) {
											//alert('已分享');
											//alert(invitationCode);
										},
										cancel : function(res) {
											//alert('已取消');
										},
										fail : function(res) {
											//alert(JSON.stringify(res));
										}
									});

							click();
						};
						// 2.2 监听“分享到朋友圈”按钮点击、自定义分享内容及分享结果接口
						document.querySelector('#onMenuShareTimeline').onclick = function() {
							wx
									.onMenuShareTimeline({
										title : '您的好友“'
												+ nickname
												+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员抢好货！即刻起注册既有好礼送！',
										link : 'http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
												+ invitationCode,
										imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
										trigger : function(res) {
											//alert('用户点击分享到朋友圈');
										},
										success : function(res) {
											//alert('已分享');
										},
										cancel : function(res) {
											//alert('已取消');
										},
										fail : function(res) {
											//alert(JSON.stringify(res));
										}
									});
							// alert('已注册获取“分享到朋友圈”状态事件');
							click();
						};
						// 2.3 监听“分享到QQ”按钮点击、自定义分享内容及分享结果接口
						document.querySelector('#onMenuShareQQ').onclick = function() {
							wx
									.onMenuShareQQ({
										title : '微微夫人',
										desc : '您的好友“'
												+ nickname
												+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员抢好货！即刻起注册既有好礼送！',
										link : 'http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
												+ invitationCode,
										imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
										trigger : function(res) {
											//alert('用户点击分享到QQ');
										},
										complete : function(res) {
											//alert(JSON.stringify(res));
										},
										success : function(res) {
											//alert('已分享');
										},
										cancel : function(res) {
											//alert('已取消');
										},
										fail : function(res) {
											// alert(JSON.stringify(res));
										}
									});
							click();
							//alert('已注册获取“分享到 QQ”状态事件');
						};

						// 2.4 监听“分享到微博”按钮点击、自定义分享内容及分享结果接口
						document.querySelector('#onMenuShareWeibo').onclick = function() {
							wx
									.onMenuShareWeibo({
										title : '微微夫人',
										desc : '您的好友“'
												+ nickname
												+ '”在微微夫人买了神秘好礼，正在邀请您一起加入会员！即刻起注册既有好礼送！',
										link : 'http://manager.yishangmeng.cn/RyWeiXinApp/weixinApp/registerLogin.do?mark='
												+ invitationCode,
										imgUrl : 'http://manager.yishangmeng.cn/RyWeiXinApp/resources/assets/images/yishangquanlog.png',
										trigger : function(res) {
											//alert('用户点击分享到微博');
										},
										complete : function(res) {
											//alert(JSON.stringify(res));
										},
										success : function(res) {
											//alert('已分享');
										},
										cancel : function(res) {
											//alert('已取消');
										},
										fail : function(res) {
											//alert(JSON.stringify(res));
										}
									});
							//alert('已注册获取“分享到微博”状态事件');
						};
					});
			wx.error(function(res) {
				alert(res);
				// alert("这个是"+res.errMsg);
			});
	</script>
</head>
<body>

</body>
</html>