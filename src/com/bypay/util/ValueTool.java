package com.bypay.util;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class ValueTool {
	
	//public static final String PATH = "E://bbxz//dingjieyuan.txt";
	//public static final String REPORT_PATH = "E://bbxz//dingjieyuan.txt";
	public static final String REPORT_PATH = "/usr/local/ProjectFiles/ReportFiles/";
//	public static final String REPORT_PATH ="D:\\bbcs\\"; 图片路径 
//	public static final String PICTURE_PATH = "D:/\\subPicture/\\";
	public static final String PICTURE_PATH = "D:\\test\\";
	
	public static final String busUrl = "http://210.51.61.174/lbcp/businessAction!doBusiness.ac";
	
	public static final String tmsUrl = "http://192.168.1.15/POSP/posGate.html";
//	public static final String tmsUrl = "http://192.168.1.187:8080/POSP/posGate.html";
	public static final String tmsPub = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChD3zYrL18G0svdjViNoS+OufPS0coB8gQ7lUFxxNa5cVTmXVcfbnXXeePicLwlblmi9/TbPSwdoOaqZlWfvIiy//d5hwvfSlT2ifUCOurK63p9h50SpnweRy3RphaCXrZc3YqLw/bkEb2pktIhAsfJMMUOGo/JMGTtrMni56z8QIDAQAB";

	//tms前置秘钥
	public static final String tmsDesKey="46d4284031c53c12362599cfdfe1304f"; 
	
	//身份认证相关参数
	public static String AUTH_MER_ID = "110000003734";
//	public static String AUTH_MER_ID = "110938583575";  //测试
	//实人签名秘钥
	public static final String AUTH_MER_SIGN_KEY = "65299c8e01932056cc3d57d7e9336a61";
//	public static final String AUTH_MER_SIGN_KEY = "67ce5927b4548e684c612ee9d81b2c09"; //测试
	//实人加密秘钥
	public static final String AUTH_MER_TRANS_KEY = "6b1c0ae344bcc4ff516c2862a6e6bd7e";
//	public static final String AUTH_MER_TRANS_KEY = "088371654929efa7a2d9af2263675877";  //测试
//	public static String AUTH_REQ_PATH = "http://ia.richerpay.com/orderGate.html";
//	public static String AUTH_REQ_PATH = "http://58.246.136.11:8024/IA/clientRequest!process.ac";  //测试
	public static String AUTH_REQ_PATH = "http://10.0.3.35:8280/orderGate.html";
	/**
	* 取得当前时间精确到毫�? 格式�?"yyyyMMddHHmmssSSS"，并联合5位随机数
	*/
	public synchronized static String createByTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
		String timeIndex = formatter.format(new java.util.Date());
		int t = new java.util.Random().nextInt(999999);  
		timeIndex+=Integer.valueOf(t).toString().substring(1);
		return timeIndex;
	}	
	//错误码表
	public static final String errorCode[][]={
		{"bm98","des解密出错"},
		{"bm97","application获取失败"},
		{"bm96","JAX解析失败"},
		{"bm95","获取空数据"},
		{"bm94","验证数据错"},
		{"bm01","注册接口参数错误"},
	};
	
	public static Map<String, String> SYS_CODE = new HashMap<String, String>();
	static{
		SYS_CODE.put("0000", "操作成功");
		SYS_CODE.put("9999", "未知错误");
		SYS_CODE.put("9998", "报文格式错误");
		SYS_CODE.put("9997", "解密出错");
		SYS_CODE.put("9996", "功能未开通");
		SYS_CODE.put("9995", "网络连接超时");
		SYS_CODE.put("9994", "可用功能列表未配置，请核实!"); 
		SYS_CODE.put("9993", "原业务信息长度不一致!");
		SYS_CODE.put("9001", "查无此终端");
		SYS_CODE.put("9002", "终端已初始化,未申请账户");
		SYS_CODE.put("9003", "此终端已初始化,账户已申请");
		SYS_CODE.put("9004", "终端已有注册商户");
		SYS_CODE.put("9005", "商户登录信息错误");
		SYS_CODE.put("9006", "商户不存在");
		SYS_CODE.put("9008", "非法操作");
		SYS_CODE.put("9009", "商户注册失败");
		SYS_CODE.put("9010", "未注册账户"); 
		SYS_CODE.put("9011", "此终端已暂停");
		SYS_CODE.put("9012", "重复交易");
		SYS_CODE.put("9013", "机构商不存在");
		SYS_CODE.put("9014", "银行信息获取失败");
		SYS_CODE.put("9015", "密码找回失败");
		SYS_CODE.put("9016", "结算信息不一致，请核实");
		SYS_CODE.put("9017", "账户或密码错误");
		SYS_CODE.put("9018", "尚未满足开通收款功能的要求");
		SYS_CODE.put("9019", "手机充值失败");
		SYS_CODE.put("9020", "终端验证失败");
		
		
		SYS_CODE.put("1001", "交易信息验证失败");
		SYS_CODE.put("1002", "报文MAC校验失败");
		SYS_CODE.put("1003", "敏感信息解密错误");
		SYS_CODE.put("1004", "原始交易不存在");
		SYS_CODE.put("1005", "余额查询通道错误");
		SYS_CODE.put("1006", "转账通道错误");
		SYS_CODE.put("1007", "信用卡还款通道错误");
		SYS_CODE.put("1008", "无此交易");
		SYS_CODE.put("1009", "消费通道错误");
		SYS_CODE.put("1010", "消费卡错误");
		
		SYS_CODE.put("2001", "身份认证失败,注册失败!");
		SYS_CODE.put("2002", "未查询到交易记录!");
		SYS_CODE.put("2003", "现在还不能进行该操作!");
		
		SYS_CODE.put("3001", "获取空数据");
		SYS_CODE.put("3002", "des解密出错");
		SYS_CODE.put("3003", "JAX解析失败");
		SYS_CODE.put("3004", "验证数据错");
		SYS_CODE.put("3005", "注册接口参数错误");
		SYS_CODE.put("3006", "application获取失败");
		SYS_CODE.put("3020", "信息处理失败");
		
		//
		SYS_CODE.put("3021", "修改子商户设备信息失败");
		SYS_CODE.put("3022", "添加子商户交易费率信息失败");
		SYS_CODE.put("3023", "添加子商户交易配置信息失败");
		SYS_CODE.put("3025", "终端正在使用中");
		SYS_CODE.put("3028", "收款功能开通失败");
		SYS_CODE.put("3029", "收款开通接口参数错误");
		SYS_CODE.put("3030", "添加实人认证信息失败");
		SYS_CODE.put("3031", "修改子商户状态失败");
		SYS_CODE.put("3032", "修改实人认证信息失败");
		SYS_CODE.put("3033", "实人认证失败");
		SYS_CODE.put("3034", "商户无此终端");
		SYS_CODE.put("3035", "收款功能已开通");
		SYS_CODE.put("3036", "修改子商户交易配置信息失败");
		SYS_CODE.put("3037", "终端启用接口参数错误");//YJH
		SYS_CODE.put("3038", "启动终端失败");//YJH
		SYS_CODE.put("3039", "获取机构交易配置信息失败");
		SYS_CODE.put("3040", "终端启用接口参数错误");//YJH
		SYS_CODE.put("3041", "密码修改接口参数错误");//YJH
		SYS_CODE.put("3042", "密码修改失败");//YJH
		SYS_CODE.put("3043", "账户信息查询接口参数错误");
		SYS_CODE.put("3044", "终端验证接口参数错误");
		SYS_CODE.put("3045", "获取子商户终端号失败");
		SYS_CODE.put("3046", "添加子商户终端信息失败");
		SYS_CODE.put("3047", "修改子商户认证状态失败");
		SYS_CODE.put("3048", "添加认证信息失败");
		SYS_CODE.put("3049", "添加子商户超级管理员失败");
		SYS_CODE.put("3050", "获取机构终端最新版本失败");
		SYS_CODE.put("3051", "获取机构终端版本信息失败");
		SYS_CODE.put("3052", "商户提现申请记录查询接口参数错误");
		SYS_CODE.put("3053", "查询余额接口参数错误");
		SYS_CODE.put("3054", "查询余额失败");
		SYS_CODE.put("3055", "此法人证件已注册过");	//
		SYS_CODE.put("3056", "终端版本未启用");
		SYS_CODE.put("3057", "此手机号已注册过");
		SYS_CODE.put("3058", "短信下发接口参数错误");
		SYS_CODE.put("3059", "验证码错误");
		SYS_CODE.put("3060", "验证码失效");
		SYS_CODE.put("3061", "短信发送失败");
		SYS_CODE.put("3062", "机构暂停进件");
		SYS_CODE.put("3063", "代理暂停进件");
		SYS_CODE.put("3064", "终端商户不一致");
		SYS_CODE.put("3065", "该商户已绑定过终端");
		SYS_CODE.put("3066", "绑定成功");
		SYS_CODE.put("3067", "余额不足");
	}
}
