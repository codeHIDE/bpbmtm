package com.bypay.util;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONStringBuilder {
	/**
	 * 从json对象中获得字符串
	 * @param list
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAjaxString(List list) {
		JSONArray ja = JSONArray.fromObject(list);
		return ja.toString();
	}
	/**
	 * 从Map对象中获得json字符�?
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String getAjaxString(Map map) {
		JSONObject jo = JSONObject.fromObject(map);
		return jo.toString();
	}
	
	public static String getAjaxString(Object obj) {
		JSONObject jo = JSONObject.fromObject(obj);
		return jo.toString();
	}
	
	public static void main(String[] args) {
		//String str = "{\"userId\":171,\"roleId\":2,\"mobile\":\"13000000003\",\"bankCard\":\"6221884510001332217\",\"identityCard\":\"427156783093838387\",\"openingBank\":\"邮储银行\",\"userName\":\"陈海洋\",\"dPIStatus\":1,\"cardholder\":\"陈海洋\",\"menu\":[{\"mId\":1,\"mName\":\"应用中心\",\"functionKey\":null,\"children\":[{\"mId\":5,\"mName\":\"账务管理\",\"functionKey\":\"ACCOUNTMANAGER\",\"children\":[{\"mId\":6,\"mName\":\"余额查询\",\"functionKey\":\"QUERYBALANCE\",\"pmid\":5,\"status\":2,\"sonDisplayType\":0},{\"mId\":27,\"mName\":\"我要收款\",\"functionKey\":\"RECEIVABLES\",\"pmid\":5,\"status\":1,\"sonDisplayType\":0},{\"mId\":28,\"mName\":\"我要付款\",\"functionKey\":\"PAY\",\"pmid\":5,\"status\":1,\"sonDisplayType\":0},{\"mId\":37,\"mName\":\"卡卡转账\",\"functionKey\":\"CARD2CARDTRANSFER\",\"pmid\":5,\"status\":1,\"sonDisplayType\":0}],\"pmid\":1,\"status\":1,\"sonDisplayType\":0},{\"mId\":21,\"mName\":\"优惠促销\",\"functionKey\":\"SALE\",\"pmid\":1,\"status\":1,\"sonDisplayType\":0},{\"mId\":22,\"mName\":\"万吉商城\",\"functionKey\":\"VANJOYMALL\",\"pmid\":1,\"status\":1,\"sonDisplayType\":0},{\"mId\":34,\"mName\":\"便利缴费\",\"functionKey\":\"CONVENIENCEPAYMENTMANAGER\",\"children\":[{\"mId\":88,\"mName\":\"公共缴费\",\"functionKey\":\"PUBLICPAYMENT\",\"pmid\":34,\"status\":1,\"sonDisplayType\":0},{\"mId\":89,\"mName\":\"手机充值\",\"functionKey\":\"PHONERECHARGE\",\"pmid\":34,\"status\":1,\"sonDisplayType\":0},{\"mId\":90,\"mName\":\"信用卡还款\",\"functionKey\":\"CREDITCARDREPAYMENT\",\"pmid\":34,\"status\":1,\"sonDisplayType\":0},{\"mId\":91,\"mName\":\"房贷车贷\",\"functionKey\":\"MORTGAGECARLOAN\",\"pmid\":34,\"status\":1,\"sonDisplayType\":0},{\"mId\":92,\"mName\":\"保险车险\",\"functionKey\":\"INSURANCECARINSURANCE\",\"pmid\":34,\"status\":1,\"sonDisplayType\":0}],\"pmid\":1,\"status\":1,\"sonDisplayType\":0},{\"mId\":35,\"mName\":\"在线票务\",\"functionKey\":\"ONLINETICKETMANAGER\",\"pmid\":1,\"status\":1,\"sonDisplayType\":0},{\"mId\":36,\"mName\":\"彩票投注\",\"functionKey\":\"LOTTERYPLAY\",\"pmid\":1,\"status\":1,\"sonDisplayType\":0}],\"pmid\":0,\"status\":1,\"sonDisplayType\":0},{\"mId\":2,\"mName\":\"交易记录\",\"functionKey\":null,\"pmid\":0,\"status\":1,\"sonDisplayType\":0},{\"mId\":3,\"mName\":\"账户信息\",\"functionKey\":null,\"children\":[{\"mId\":23,\"mName\":\"收支明细\",\"functionKey\":null,\"pmid\":3,\"status\":1,\"sonDisplayType\":0},{\"mId\":24,\"mName\":\"修改密码\",\"functionKey\":null,\"pmid\":3,\"status\":1,\"sonDisplayType\":0},{\"mId\":25,\"mName\":\"绑定银行卡\",\"functionKey\":null,\"pmid\":3,\"status\":1,\"sonDisplayType\":0},{\"mId\":26,\"mName\":\"上传错误日志\",\"functionKey\":null,\"pmid\":3,\"status\":1,\"sonDisplayType\":0}],\"pmid\":0,\"status\":1,\"sonDisplayType\":0},{\"mId\":4,\"mName\":\"更多\",\"functionKey\":null,\"children\":[{\"mId\":7,\"mName\":\"用户反馈\",\"functionKey\":null,\"pmid\":4,\"status\":1,\"sonDisplayType\":0},{\"mId\":8,\"mName\":\"关于\",\"functionKey\":null,\"pmid\":4,\"status\":1,\"sonDisplayType\":0},{\"mId\":9,\"mName\":\"帮助\",\"functionKey\":null,\"pmid\":4,\"status\":1,\"sonDisplayType\":0},{\"mId\":11,\"mName\":\"切换账户\",\"functionKey\":null,\"pmid\":4,\"status\":1,\"sonDisplayType\":0},{\"mId\":12,\"mName\":\"退出\",\"functionKey\":null,\"pmid\":4,\"status\":1,\"sonDisplayType\":0}],\"pmid\":0,\"status\":1,\"sonDisplayType\":0}],\"feeRate\":0.01,\"discountRate\":1,\"branchesBank\":\"北京海淀区魏公村支行\",\"bBankProvince\":1,\"bBankCity\":108}";
		String str = "{\"action\":\"userAction_firstNextBnRegister\",\"clientType\":2,\"terminalTypeId\":1,\"roleId\":2,\"mobile\":\"13521856049\",\"activationCode\":\"ABCD\"}";//"{\"userId\":105,\"roleId\":3,\"mobile\":\"13000000000\",\"bankCard\":\"6228480018034116071\",\"identityCard\":\"436621358008754224\",\"openingBank\":\"农业银行\",\"userName\":\"包月飞\",\"dPIStatus\":1,\"cardholder\":\"包月飞\",\"feeRate\":0.01,\"discountRate\":1,\"branchesBank\":\"\",\"bBankProvince\":1,\"bBankCity\":101}";
		JSONObject js = JSONObject.fromObject(str);
		/*Users users = (Users) JSONObject.toBean(js,Users.class);
		if(users==null){
			System.out.println("this null");
		}else{
			System.out.println("not  null "+users.getBranchesBank());
		}*/
		System.out.println(js.getString("mobile"));
	}
}
