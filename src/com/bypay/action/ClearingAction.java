package com.bypay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.codec.binary.Base64;

import com.bypay.common.BaseAction;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.dao.ProcedureDao;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.clientTool.BankUtils;
import com.bypay.domain.clientTool.ProxyPay;
import com.bypay.domain.clientTool.TransInfoReq;
import com.bypay.util.DateUtil;
import com.bypay.util.JsonUtil;
import com.bypay.util.PropertiesUtils;
import com.bypay.util.RSACoder;
import com.bypay.util.RemoteAccessor;
import com.bypay.util.XmlUtil;

public class ClearingAction extends BaseAction{
	@Inject
	private MerSettleStatictisDao merSettleStatictisDao;
	@Inject
	private ProcedureDao procedureDao;
	private MerSettleStatictis merSettleStatictis;
	private String ids;
	private String merSysIds;
	private List<MerSettleStatictis> merSettleStatictisList;
	
	private String merSysId; 
	private String settleDate;
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("1111", "1111");
		map.put("4444", "4444");
		map.put("3333222", "3333222");
		map.put("2222", "2222");
		Set<String> set = map.keySet();
		Collection<String> c =  map.values();
		Iterator<String> it = set.iterator();
		Iterator<String> itc = c.iterator();
		while (it.hasNext()) {
			String a = (String) it.next();
			System.out.println(a);
		}
		while (itc.hasNext()) {
			String a = (String) itc.next();
			System.out.println(a);
		}
		System.out.println("1");
	}


	public void clearingTruefone(){
		settleDate = settleDate.replace("-", "");
		String ids1[] = JsonUtil.getStringArray4Json(ids);
		String merSysIds1[] = JsonUtil.getStringArray4Json(merSysIds);
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < merSysIds1.length; i++) {
			if(map.get(merSysIds1[i])==null){
//				merSettleStatictis = new MerSettleStatictis();
//				merSettleStatictis.setMerSysId(merSysIds1[i]);
//				merSettleStatictisList.add(merSettleStatictis);
				map.put(merSysIds1[i],ids1[i]);
			}else{
				String a = map.get(merSysIds1[i]);
				a=a+","+ids1[i];
				map.put(merSysIds1[i],a);
			}
		}
		try {
			merSettleStatictisList = new ArrayList<MerSettleStatictis>();
			Collection<String> conllection =  map.values();
			Iterator<String> itc = conllection.iterator();
			Integer j = 1;
			List<List<ProxyPay>> proxyPayLists = new ArrayList<List<ProxyPay>>();
			ProxyPay proxyPay = null ;
			
			Map<Integer, List<MerSettleStatictis>> map1 = new HashMap<Integer, List<MerSettleStatictis>>();
			String []merSysId= new String[map.size()];
			while (itc.hasNext()) {
				List<ProxyPay> proxyPayList = new ArrayList<ProxyPay>();
				String id = itc.next();
				String[]str = id.split(",");
				for (int i = 0; i < str.length; i++) {
					proxyPay = new ProxyPay();
					merSettleStatictis = new MerSettleStatictis();
					merSettleStatictis.setId(str[i]);
					merSettleStatictis = merSettleStatictisDao.selectSubMerInfoLeftMerSettleStatictis(merSettleStatictis);
					proxyPay.setAcccount2(merSettleStatictis.getAccountNum());
					proxyPay.setCode(merSettleStatictis.getAccountAgencyId());
					proxyPay.setAmt(merSettleStatictis.getClearAmt());
					proxyPay.setPostScript(settleDate);
					proxyPay.setPreDetailId(merSettleStatictis.getId());
					proxyPay.setTransRate(merSettleStatictis.getSumTransFee());
					proxyPay.setUserName(merSettleStatictis.getAccountName());
					merSettleStatictisList.add(merSettleStatictis);
					proxyPayList.add(proxyPay);
					merSysId[j-1] = merSettleStatictis.getMerSysId();
				}
				proxyPayLists.add(proxyPayList);
				map1.put(j, merSettleStatictisList);
				j++;
			}
			//调用代发接口
			int l = 1;
			int i = 1;
			String msg = "";
			for (List<ProxyPay> sett : proxyPayLists) {
				Map map2 = new HashMap();
				merSettleStatictis = new MerSettleStatictis();
				merSettleStatictis.setMerSysId(merSysId[i-1]);
				merSettleStatictis.setSettleDate(settleDate);
				map2.put("merSettleStatictis", merSettleStatictis);
				merSettleStatictis = merSettleStatictisDao.selectMerSettleStatictisById(map2);
				// 下发准备
				String[] requestMsg = null;
				try {
					//修改要发的批次的所有商户状态为9
					requestMsg = BankUtils
							.preBehalfToBusinessSub(sett, String.valueOf(merSysId[i-1]),
									RSACoder.KEY_FTB_PUBLICKEY, DateUtil.getDate("yyyyMMddHHmmss")+merSettleStatictis.getId(),"0");
					if (requestMsg == null) {
						l++;
						msg="下发报文不合法。";
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					l++;
					msg="下发报文加密失败。";
					continue;
				}
				// System.out.println(requestMsg[1]);
				// 异步处理
				String responseMsg = null;
				try {
					System.err.println(PropertiesUtils.getPropertiesValueInPath("busUrl"));
					responseMsg = new RemoteAccessor().getResponseByPost(
							PropertiesUtils.getPropertiesValueInPath("busUrl"),
							"UTF-8", new String[] { "msg", requestMsg[1] });
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (responseMsg == null) {
					l++;
					msg="下发申请失败,无法响应客户端的请求!";
					continue;
				}
				
				// 回复报文解析
				String[] msgs = responseMsg.split("\\|");
				if ("0".equals(msgs[0])) {
					// 错误
					// 错误响应格式：0|错误码|BASE64(错误描述)
					try {
						String reason = new String(Base64.decodeBase64(msgs[2]),
								"UTF-8");
						msg = "下发申请失败:" + reason;
						l++;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						msg = "不支持UTF-8编码";
					}
					continue;
				} else if ("1".equals(msgs[0])) {
					// 正常响应格式：1|BASE64(3DES(报文))|BASE64(MD5(报文))
					byte[] data;
					TransInfoReq tir;
					try {
						List<MerSettleStatictis> list = map1.get(l);
						Iterator<MerSettleStatictis> it2 = list.iterator();
						while (it2.hasNext()) {
							MerSettleStatictis merSettleStatictis = (MerSettleStatictis) it2.next();
							merSettleStatictis.setClearStatus("9");
							merSettleStatictisDao.updateMerSettleStatictisStatusById(merSettleStatictis);
						}
						data = RSACoder.decryptDesc(Base64.decodeBase64(msgs[1]),
								requestMsg[0].getBytes());
						tir = (TransInfoReq) XmlUtil.XmlToObj(
								new String(data, "UTF-8"), TransInfoReq.class);
						// 更新订单
						System.out.println(tir.getMerchantOrderId());
						msg = "清分受理成功!";

					} catch (Exception e) {
						e.printStackTrace();
						msg = "下发申请成功，处理失败,报文解密失败!";
//						merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i));
					}
				} else {
					msg = "未知异常!";
//					merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i));
					l++;
					continue;
				}
			}
			try {
				getResponse().getWriter().write(msg);
				getResponse().getWriter().flush();
				getResponse().getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("请联系管理员");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
	public void clearingTrue(){
//		Date date = new Date();
//		while(true) {
//			Date date2 = new Date();
//			int i =date2.getSeconds()-date.getSeconds();
//			System.out.println(i);
//			if(i>=3){
//				try {
//					getResponse().getWriter().write("succ");
//					break;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
		
		try {
			merSettleStatictis = new MerSettleStatictis();
			merSettleStatictis.setMerSysId(merSysId);
			merSettleStatictis.setMerType("1");
			merSettleStatictis.setSettleDate(settleDate);
			//查询所有需要清分的子商户
			merSettleStatictisList = merSettleStatictisDao.selectMerSettleStatictisListNew(merSettleStatictis);
			//查询清分机构
			merSettleStatictis.setMerType("0");
			merSettleStatictis = merSettleStatictisDao.selectMerSettleStatictisListNew(merSettleStatictis).get(0);
//			while(listIt.hasNext()){
//				MerSettleStatictis merSettleStatictis = listIt.next();
//				
//				System.out.println("ID:"+merSettleStatictis.getId()+"  金额"+merSettleStatictis.getSumAmt());
//			}
//			if(clearAmt>sumAmt){
//				getResponse().getWriter().write("清分金额不能大于"+sumAmt/100);
//				return;
//			} 
			//sumAmt为单批次限额
			Long sumAmt=250000000L;
			//clearAmt用来计算单批次金额
			Long clearAmt = 0L;
			//map是用来修改子商户状态
			Map<Integer,String[]> map = new HashMap<Integer, String[]>();
			List<List<ProxyPay>> proxyPayLists = new ArrayList<List<ProxyPay>>();
			int k = merSettleStatictisList.size();
			int o = 1;
			String[] str = new String[k];
			List<ProxyPay> proxyPayList = new ArrayList<ProxyPay>();
			for (int i = 1,j = 1; i <= k; i++,j++) {
				//获取List中的第一条
				MerSettleStatictis merSettleStatictis = merSettleStatictisList.get(0);
				if(merSettleStatictis.getAccountName() == null || "".equals(merSettleStatictis.getAccountName()) ||
					merSettleStatictis.getAccountAgencyId() == null || "".equals(merSettleStatictis.getAccountAgencyId()) ||
					merSettleStatictis.getAccountNum() == null || "".equals(merSettleStatictis.getAccountNum())){
					getResponse().getWriter().write("商户："+merSettleStatictis.getMid()+"结算信息不完整");
					return;
				}
				//累加清分金额
				clearAmt+=Long.parseLong(merSettleStatictis.getClearAmt());
				//如果清分金额大于单批次金额则走else
				if(clearAmt<sumAmt){
					ProxyPay pp = new ProxyPay();
					pp.setAcccount2(merSettleStatictis.getAccountNum());
					pp.setAmt(merSettleStatictis.getClearAmt());
					pp.setCode(merSettleStatictis.getAccountAgencyId());
					pp.setPostScript(settleDate);
					pp.setUserName(merSettleStatictis.getAccountName());
					pp.setPreDetailId(merSettleStatictis.getId() + "");
					proxyPayList.add(pp);
					str[i-1] = merSettleStatictis.getId();
					merSettleStatictisList.remove(0);
					//如果j是第50次，或者是该机构商的最后一个商户
					if(j%50==0 || i == k){
						j = 1;
						proxyPayLists.add(proxyPayList);
						proxyPayList = new ArrayList<ProxyPay>();
						map.put(o, str);
						str = new String[k];
						o++;
					}
				}else{
					//不删除该子商户的记录，将前面所累加的所有信息存入List和map
					//重置j
					j =1;
					//i-1是补回循环次数
					i-=1;
					//重置清分金额
					clearAmt = 0L;
					proxyPayLists.add(proxyPayList);
					proxyPayList = new ArrayList<ProxyPay>();
					map.put(o, str);
					str = new String[k];
					o++;
				}
			}
//			List<MerSettleStatictis> merSettleStatictisList1 = new ArrayList<MerSettleStatictis>();
//			merSettleStatictisList1.add(merSettleStatictis);
//			i = merSettleStatictisDao.updateMerSettleStatictisStatusById(merSettleStatictisList1);
//			if(i != merSettleStatictisList1.size()){
//				getResponse().getWriter().write("系统出错");
//				return;
//			}
			//调用代发接口
			int l = 0;
			//修改该机构商的状态为处理中
			merSettleStatictis.setClearStatus("9");
			Integer i = merSettleStatictisDao.updateMerSettleStatictisStatusById(merSettleStatictis);
			if(i != 1){
				getResponse().getWriter().write("系统出错");
				return;
			}
			i = 0;
			String msg = "";
			Long amt = Long.parseLong(merSettleStatictis.getSumAmt())-Long.parseLong(merSettleStatictis.getSumTransFee());
			for (List<ProxyPay> sett : proxyPayLists) {
				// 下发准备
				String[] requestMsg = null;
				try {
					//修改要发的批次的所有商户状态为9
					merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(++i),"9");
					requestMsg = BankUtils
							.preBehalfToBusinessSub(sett, String.valueOf(merSysId),
									RSACoder.KEY_FTB_PUBLICKEY, DateUtil.getDate("yyyyMMddHHmmss")+merSettleStatictis.getId(),amt.toString());
					if (requestMsg == null) {
						merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i),"3");
						l++;
						msg="下发报文不合法。";
						continue;
					}
				} catch (Exception e) {
					e.printStackTrace();
					l++;
					merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i),"3");
					msg="下发报文加密失败。";
					continue;
				}
				// System.out.println(requestMsg[1]);
				// 异步处理
				String responseMsg = null;
				try {
					System.err.println(PropertiesUtils.getPropertiesValueInPath("busUrl"));
					responseMsg = new RemoteAccessor().getResponseByPost(
							PropertiesUtils.getPropertiesValueInPath("busUrl"),
							"UTF-8", new String[] { "msg", requestMsg[1] });
				} catch (Exception e) {
					e.printStackTrace();
				}

				if (responseMsg == null) {
					l++;
					merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i),"3");
					msg="下发申请失败,无法响应客户端的请求!";
					continue;
				}
				
				// 回复报文解析
				String[] msgs = responseMsg.split("\\|");
				if ("0".equals(msgs[0])) {
					// 错误
					// 错误响应格式：0|错误码|BASE64(错误描述)
					try {
						String reason = new String(Base64.decodeBase64(msgs[2]),
								"UTF-8");
						msg = "下发申请失败:" + reason;
						merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i),"3");
						l++;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						msg = "不支持UTF-8编码";
					}
					continue;
				} else if ("1".equals(msgs[0])) {
					// 正常响应格式：1|BASE64(3DES(报文))|BASE64(MD5(报文))
					byte[] data;
					TransInfoReq tir;
					try {
						data = RSACoder.decryptDesc(Base64.decodeBase64(msgs[1]),
								requestMsg[0].getBytes());
						tir = (TransInfoReq) XmlUtil.XmlToObj(
								new String(data, "UTF-8"), TransInfoReq.class);
						// 更新订单
						System.out.println(tir.getMerchantOrderId());
						msg = "清分受理成功!";

					} catch (Exception e) {
						e.printStackTrace();
						msg = "下发申请成功，处理失败,报文解密失败!";
//						merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i));
					}
				} else {
					msg = "未知异常!";
//					merSettleStatictisDao.updateMerSettleStatictisStatusById(map.get(i));
					l++;
					continue;
				}
			}
			if(l == map.size()){
				merSettleStatictis.setClearStatus("3");
				merSettleStatictisDao.updateMerSettleStatictisStatusById(merSettleStatictis);
			}
			try {
				getResponse().getWriter().write(msg);
				getResponse().getWriter().flush();
				getResponse().getWriter().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("请联系管理员");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	

	private List<MerSettleStatictis> getList(List<MerSettleStatictis> merSettleStatictisList){
		List<MerSettleStatictis> list = new ArrayList<MerSettleStatictis>();
		Long money = 1000000L;
		Iterator<MerSettleStatictis> merSettleStatictisIt = merSettleStatictisList.iterator();
		MerSettleStatictis data = new MerSettleStatictis();
		while(merSettleStatictisIt.hasNext()){
			MerSettleStatictis merSettleStatictis = merSettleStatictisIt.next();
			Long num = Long.parseLong(merSettleStatictis.getSumAmt())-Long.parseLong(merSettleStatictis.getSumTransFee());
			String id = merSettleStatictis.getId();
			for (Integer i = 1; i <= num/money+2; i++){
				data = new MerSettleStatictis();
				if(i == num/money+1 && num % money != 0){
					data.setSumAmt((num % money)+"");
					data.setId(id+"-"+i.toString());
					list.add(data);
					break;
				}else if(num/money==1){
					data.setSumAmt((money)+"");
					data.setId(id+"-"+i.toString());
					list.add(data);
					continue;
				}
				data.setSumAmt(money.toString());
				data.setId(id+"-"+i.toString());
				list.add(data);
			}
		}
		return list;
	}
//	public static void main(String[] args) {
//		Long a = 60000L;
//		int b = 50000;
//		System.out.println(a/b);
//	}
	public MerSettleStatictisDao getMerSettleStatictisDao() {
		return merSettleStatictisDao;
	}
	public void setMerSettleStatictisDao(MerSettleStatictisDao merSettleStatictisDao) {
		this.merSettleStatictisDao = merSettleStatictisDao;
	}
	public MerSettleStatictis getMerSettleStatictis() {
		return merSettleStatictis;
	}
	public void setMerSettleStatictis(MerSettleStatictis merSettleStatictis) {
		this.merSettleStatictis = merSettleStatictis;
	}
	public List<MerSettleStatictis> getMerSettleStatictisList() {
		return merSettleStatictisList;
	}
	public void setMerSettleStatictisList(
			List<MerSettleStatictis> merSettleStatictisList) {
		this.merSettleStatictisList = merSettleStatictisList;
	}
	public String getMerSysId() {
		return merSysId;
	}
	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}
	public String getSettleDate() {
		return settleDate;
	}
	public void setSettleDate(String settleDate) {
		this.settleDate = settleDate;
	}

	public ProcedureDao getProcedureDao() {
		return procedureDao;
	}

	public void setProcedureDao(ProcedureDao procedureDao) {
		this.procedureDao = procedureDao;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMerSysIds() {
		return merSysIds;
	}

	public void setMerSysIds(String merSysIds) {
		this.merSysIds = merSysIds;
	}
	
	
}
