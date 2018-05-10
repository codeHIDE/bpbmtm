package com.bypay.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bypay.common.BaseAction;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.clientTool.ProxyPay;
import com.bypay.domain.clientTool.TransInfoReq;
import com.bypay.util.RSACoder;
import com.bypay.util.RemoteAccessor;
import com.bypay.util.XmlUtil;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class NotifyGateWayAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7337327217873228509L;
	private Logger logger = LoggerFactory.getLogger(NotifyGateWayAction.class);
	@Inject
	private MerSettleStatictisDao merSettleStatictisDao;
	public static void main(String[] args) {
		System.out.println("201409011550115101".substring(14,"201409011550115101".length()-1));
		String fee="merchantOrderAmt=123&merchantOrderId=20140325155016";
//		System.out.println(Sha1Util.getSha1SignMsg(fee, "UTF-8"));
		String data = "MTczNjY1NjgzOTIzMjYz|Pak04jYut45nMPilLmkC55hOUv4vahaq9P/mUeA6pBunT9lefyO6ysPtXNyxfObP3g4Aj0khuGTJTtptKNKhHf9twSVW8l5nSneQLhtHwENlyMYVt1VOqpA3HFPC3nyB0uOk3uZEcvcJJwwYvAx5dqieWF7Pcnj+Qd/wBJdPlJQ=|H2jnuLYundkl4t1LjGq55ypIpiUJgw7yA4IjReRqUZwEx8Orm9+j1hr5Lzww46YO5agaPJjFC8f2KBs+vBUTPEqGiiB71BxPxlul54r6WCO1OtLVTDn/tR3NZPla5h7tUdNxli6WEpBksCNGkLgAq+5ReP0yIXJ/QCk8ffEpaosRJTZkHwqhyUApPH3xKWqLmcXewLFDO2pMFLhLSZfYypynUR0pCzHDhVoBKcIvh02kCJHcg6UcOjWwvgd8ibncLZuXQAnrd45QzpVySkhneiNRPPY9TcgmExiaiGDMbcDHqVkDUASlIAdua7/jAueYD9S+Csq8ypX1afhtyjNhAr0AWKOH9mQGyz6yLHg4P1zFtMIK7WA/4EMmXRYtSM+57gz/cuhs/ycZ7qShJjJ4/lXSZK69eMDndk8EwNQOt6Vow9tVrFbBT4ZIiHo1Gv3OQyZdFi1Iz7n5cKt8Qg63+EVrJV6TVNhlQyZdFi1Iz7lGZz5LHIyGtqQIkdyDpRw6/m+UEEmqv9vhzIXvVi/dYQJHZPJGddjzpGZymJGtHdck40+g6BKJwYcF07nrrM68sjmDQFsXTvdLzzlsKwkbQVIYD5yBl+ebFLh0iiGsMTCG+8n84BerEzBTFtEr9zyhUKVwIdeUv0IGpmPW+Zi5Yk94xv3NB8wjZJARd9OjJSnumYx57/Z7UHz3QydCxu0BX+Q/0rGOxU8X6uic3Sblq/ac8PiugM5IWpi/8cLfiZOY5TQvG+EZ2nPKCRjD98grcLuZhQ1uBSjoKZ1RfBIIxFv4B8dUn0Do/7HXdw/cvblq1Na+viJvrNwxBCK5J48NfoPkTqIyoSYfRMOI3IY1qGF0YxAI80a6qoEZkCLjo9sX6uic3Sblq//ywbS5hv8r+XivXIGaMFcOqANfWno5Nd3Ln8FAnc4q8WJze0IOTkjcMQQiuSePDagcGQ1Gogs0mi5FI4rxnledcsYYBLpaWcxcL6qdaZ8PfPdDJ0LG7QFsfBqaUs4x0bW0rVxdwp9LJjeHHgxMjCJpwrSstCnIHNwxBCK5J48N9WIjly/WovFc9HrKeDnaGFENRV9EwJ5/+UsO8VVr6WjcMQQiuSePDSFhjHBbs41uHC+1W7P17F+8gqVkFPTtgMPI4fvScKa7F+ronN0m5au+LtFL+pUDBKGrAtAz1aibpdCT9rgK+mhX3v+TZfFM95G4Ysgzw7QuRXXNAARO8I+ci8CNnna1R7aAL+tZmF+8agPB0AfQkozky+h44OqJADtRHr7pbvWd+hNLlitQ8V4IhBUk8bNV0cJkNnuBGucv4RKkkotovQgkWKZzwXOJk+UrUqEeuh/DbWeQgbDKLQMKxJcNdnvyeA==";
		RemoteAccessor remoteAccessor = new RemoteAccessor();
		try {
			remoteAccessor.getResponseByStream("http://localhost:8080/bmtm1.0/notifyGateWay!notifyIssued.ac", "utf-8", data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 下发异步处理
	 * 
	 * @return
	 */
	public String notifyIssued() {

		HttpServletResponse response = null;
		PrintWriter pw = null;

		try {
			response = getResponse();
			pw = response.getWriter();
			// 1.0得到数据
			String msg = receivedMsg(getRequest());
			logger.info("MESSAGE:" + msg);
			if (msg == null || "".equals(msg)) {
				// 无法获取到报文
				pw.write("000001");
				pw.flush();
				return null;
			}

			// 1验证数据合法性
//			String publicKey = RSACoder.getPublic(PropertiesUtils
//					.getPropertiesValueInPath("notifyCerPath"));
			String publicKey = RSACoder.KEY_FTB_PUBLICKEY;
			// BASE64(商户号)|BASE64(RSA(密钥))|BASE64(3DES报文) --3DES中包含的sign为md5校验值
			String[] message = msg.split("\\|");
			if (message.length < 3) {
				// 报文格式不正确
				pw.write("000002");
				pw.flush();
				return null;
			}
			String busMerId = new String(Base64.decodeBase64(message[0]),
					"UTF-8"); // 解密出商户号
			logger.info("接收到异常通知商户号:" + busMerId);
			String dks = RSACoder.decryptByPublicKey(message[1], publicKey); // 解密出密钥
			logger.info("接收到异常通知密钥:" + dks);
			String report = new String(RSACoder.decryptDesc(
					Base64.decodeBase64(message[2]), dks.getBytes()), "UTF-8");
			System.out.println(msg);
			logger.info("接收到异常通知的报文:" + report);
			// 2解密数据,处理后可以得到我们需要的数据
			TransInfoReq tir = (TransInfoReq) XmlUtil.XmlToObj(report,
					TransInfoReq.class);
			if (tir == null || busMerId == null
					|| !busMerId.equals(tir.getMerchantId())) {
				// 报文不合法,商户号不一致
				pw.write("000003");
				pw.flush();
				return null;
			}
			//循环子列表
			//更新清分统计表
			Map map = new HashMap();
			for (ProxyPay proxyPay : tir.getLists()){
				String [] str = proxyPay.getPreDetailId().split("-");
				if(str.length == 1){
					MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
					merSettleStatictis.setId(str[0]);
					if(proxyPay.getTranstatus().equals("2")){
						merSettleStatictis.setFaileAmt(proxyPay.getAmt());
						merSettleStatictis.setSumAmt("0");
					}else if(proxyPay.getTranstatus().equals("1")){
						merSettleStatictis.setSumAmt(proxyPay.getAmt());
						merSettleStatictis.setFaileAmt("0");
					}
					map.put(str[0], merSettleStatictis);
				}else if(str.length == 2){
					if(map.get(str[0])==null){
						MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
						merSettleStatictis.setId(str[0]);
						if(proxyPay.getTranstatus().equals("2")){ 
							merSettleStatictis.setFaileAmt(proxyPay.getAmt());
							merSettleStatictis.setSumAmt("0");
						}else if(proxyPay.getTranstatus().equals("1")){
							merSettleStatictis.setSumAmt(proxyPay.getAmt());
							merSettleStatictis.setFaileAmt("0");
						}
						map.put(str[0], merSettleStatictis);
					}else if(map.get(str[0])!=null){
						MerSettleStatictis merSettleStatictis = (MerSettleStatictis) map.get(str[0]);
						if(proxyPay.getTranstatus().equals("2")){
							merSettleStatictis.setFaileAmt(Long.parseLong(merSettleStatictis.getFaileAmt())+
									Long.parseLong(proxyPay.getAmt())+"");
						}else if(proxyPay.getTranstatus().equals("1")){
							merSettleStatictis.setSumAmt(Long.parseLong(merSettleStatictis.getSumAmt())+
									Long.parseLong(proxyPay.getAmt())+"");
						}
						map.put(str[0], merSettleStatictis);
					}
				}
			}
			List<MerSettleStatictis> list = coverWoMapString(map);
			Iterator<MerSettleStatictis> its = list.iterator();
			while (its.hasNext()) {
				MerSettleStatictis merSettleStatictis = (MerSettleStatictis) its.next();
				if(merSettleStatictis.getSumAmt() == null || merSettleStatictis.getSumAmt().equals("0")){
					merSettleStatictis.setClearStatus("3");
				}else if(merSettleStatictis.getFaileAmt() == null || merSettleStatictis.getFaileAmt().equals("0")){
					merSettleStatictis.setClearStatus("2");
				}else if(!merSettleStatictis.getFaileAmt().equals("0") && !merSettleStatictis.getSumAmt().equals("0")){
					merSettleStatictis.setClearStatus("6");
				}
				merSettleStatictis.setBpSerialNumber(tir.getBpSerialNum());
				System.out.println("ID:"+merSettleStatictis.getId()+"   状态："+merSettleStatictis.getClearStatus()+"  成功金额："+merSettleStatictis.getSumAmt()+"  失败金额："+merSettleStatictis.getFaileAmt());
				try{
					merSettleStatictisDao.updateMerSettleStatictisById(merSettleStatictis);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
			MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
			merSettleStatictis.setId(tir.getMerchantOrderId().substring(14,tir.getMerchantOrderId().length()));
			String time=merSettleStatictisDao.selectMerSettleStatictisByIdNew(merSettleStatictis).getSettleDate();
			merSettleStatictis.setSettleDate(time);
			merSettleStatictis.setMerSysId(tir.getMerchantId());
			merSettleStatictis.setClearStatus("1");
			List<MerSettleStatictis> settleStatictisList=merSettleStatictisDao.selectMerSettleStatictisStatusByMerSysIdNew(merSettleStatictis);
			if(settleStatictisList != null && settleStatictisList.size()==0){
//				merSettleStatictis.setClearStatus("9");
				merSettleStatictis = new MerSettleStatictis();
				merSettleStatictis.setSettleDate(time);
				merSettleStatictis.setMerSysId(tir.getMerchantId());
				List<MerSettleStatictis> merSettleStatictisList1=merSettleStatictisDao.selectMerSettleStatictisStatusByMerSysIdNew(merSettleStatictis);
				boolean status9 = false;
				boolean status3 = false;
				boolean status2 = false;
				boolean status6 = false;
				Iterator<MerSettleStatictis> it = merSettleStatictisList1.iterator();
				while (it.hasNext()) {
					MerSettleStatictis merSettleStatictiss = (MerSettleStatictis) it.next();
					if(merSettleStatictiss.getClearStatus().equals("9")){
						status9 = true;
					}else if(merSettleStatictiss.getClearStatus().equals("2")){
						status2 = true;
					}else if(merSettleStatictiss.getClearStatus().equals("3")){
						status3 = true;
					}else if(merSettleStatictiss.getClearStatus().equals("6")){
						status6 = true;
					}
				}
				merSettleStatictis = new MerSettleStatictis();
				merSettleStatictis.setId(tir.getMerchantOrderId().substring(14,tir.getMerchantOrderId().length()));
				if(status9){
					merSettleStatictis.setClearStatus("9");
				}else if(!status6&&status3){
					merSettleStatictis.setClearStatus("3");
				}else if(status6||status3){
					merSettleStatictis.setClearStatus("6");
				}else if(status2){
					merSettleStatictis.setClearStatus("2");
				}
				System.out.println("机构更新状态："+merSettleStatictis.getClearStatus());
				merSettleStatictisDao.updateMerSettleStatictisStatusById(merSettleStatictis);
			}
			
			logger.info("下发处理成功!"); 
			// 4告知处理结果
			// 4.1成功
			pw.write("000000");
			pw.flush();

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("下发处理失败!");
			// 4.2失败
			pw.write("fail");
			pw.flush();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}

		return null;
	}

	private String receivedMsg(HttpServletRequest request) {
		// TODO Auto-generated method stub
		InputStream is = null;
		ByteOutputStream bos = null;
		String msg = null;
		try {
			is = request.getInputStream();
			bos = new ByteOutputStream();
			byte[] buffer = new byte[2048];
			int length = 0;

			while ((length = is.read(buffer)) != -1) {
				bos.write(buffer, 0, length);
			}
			bos.flush();
			msg = bos.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = null;
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bos != null) {
				bos.close();
			}
		}

		return msg;
	}
	
	public static List coverWoMapString(Map<String, String> data) {
		TreeMap tree = new TreeMap();
		Iterator it = data.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry en = (Map.Entry) it.next();
			tree.put(en.getKey(), en.getValue());
		}
		it = tree.entrySet().iterator();
		List dataList = new ArrayList();
		while (it.hasNext()) {
			Map.Entry en = (Map.Entry) it.next();
			dataList.add(en.getValue());
		}
		return dataList;
	}
	
}
