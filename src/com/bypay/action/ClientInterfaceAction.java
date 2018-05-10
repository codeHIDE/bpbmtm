package com.bypay.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.bypay.common.BaseAction;
import com.bypay.service.impl.util.BusProcessing;
import com.bypay.service.impl.util.DataProcessing;
import com.bypay.service.impl.util.TmallYfDataPackage;
import com.bypay.util.AESCoder;
import com.bypay.util.BASE64Util;
import com.bypay.util.DesUtil;
import com.bypay.util.ValueTool;
import com.bypay.util.XmlUtilnew;

@SuppressWarnings("serial")
public class ClientInterfaceAction extends BaseAction{
	
	@Inject
	private DataProcessing dataProcessing;
	@Inject
	private BusProcessing busProcessing;
	@Inject
	private XmlUtilnew xmlUtilnew;
	
	/**
	 * 客户端请求入口
	 * @throws UnsupportedEncodingException 
	 */
	public void inlet() throws UnsupportedEncodingException{
		//接收数据
		String requestContent = "";
		String outXml = "";
		try {
			requestContent = getRequestContent();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("获取数据::"+requestContent);
		//解密 合法性判断
		Object in = dataProcessing.hostMethod(requestContent);
		Object out = new Object();
		//业务处理
		System.out.println(dataProcessing.getCheckCode());
		try{
			if(dataProcessing.getCheckCode().equals("0000")){
				out = busProcessing.toBus(in);
				//正确返回组包
				outXml = xmlUtilnew.ObjToXml(out);
				System.out.println("返回数据::::::"+outXml);
				//加密
				outXml = "1|"+
				 BASE64Util.encodeBySun(DesUtil.encrypt(outXml.getBytes("utf-8"), 
						 ValueTool.tmsDesKey.getBytes()));
				
			}else{
				//错误包组包
				outXml = "0|"+dataProcessing.getCheckCode()+"|"+
				 BASE64Util.encodeBySun(ValueTool.SYS_CODE.get(dataProcessing.getCheckCode()).getBytes("UTF-8"));
			}
		} catch (JAXBException e) {
			outXml = "0|"+"9999"+"|"+
			BASE64Util.encodeBySun(ValueTool.SYS_CODE.get("9999").getBytes("UTF-8"));
			e.printStackTrace();
		} catch (Exception e) {
			outXml = "0|"+"3020"+"|"+
			BASE64Util.encodeBySun(ValueTool.SYS_CODE.get("3020").getBytes("UTF-8"));
			e.printStackTrace();
		}
		try
		{
			System.out.println("**************返回日志**************");
			System.out.println(outXml);
			System.out.println("**************返回日志**************");
			PrintWriter writerXml = getResponse().getWriter();
			writerXml.write(outXml);
			writerXml.flush();
			writerXml.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private String getRequestContent() throws IOException {
		String reqXml = "";
		InputStream inputStream = getRequest().getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				inputStream));
		String tempStr = "";
		while ((tempStr = reader.readLine()) != null) {
			reqXml += tempStr;
		}
		return reqXml;
	}
	
	public void addOto(){
		HttpServletRequest request = getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String message = request.getParameter("message");
		log.info("receive msg from oto:"+message);
		try {
			JSONObject json = JSONObject.fromObject(message);
			JSONObject result = busProcessing.addOTOMer(json);
			getResponse().getWriter().write(result.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 新借口
	 * @Title:        newInter 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-9-1 上午10:02:24
	 */
	public void newInter(){
		HttpServletRequest request = getRequest();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String message = request.getParameter("message");
		
		log.info("receive msg:"+message);
		try {
			JSONObject json = JSONObject.fromObject(message);
			if(!checkJson(json)){
				Map<String, String> map = new HashMap<String, String>();
				map.put("respCode", "9998");
				map.put("respMsg", ValueTool.SYS_CODE.get("9998"));
				String jsonString = TmallYfDataPackage.convertObjectToJson(map);
				json=JSONObject.fromObject(jsonString);
			}else{
				String transType =json.optString("transType"); 
				if(transType.equals("mobileRegist")){
					json = busProcessing.mobileRegist(json);
				}else if(transType.equals("invite")){
					json = busProcessing.invite(json);
				}else if(transType.equals("waresList")){
					json = busProcessing.waresList(json);
				}else if(transType.equals("specList")){
					json = busProcessing.specList(json);
				}else if(transType.equals("buyWares")){
//					json = busProcessing.waresList(json);
				}else if(transType.equals("showInvite")){
					json = busProcessing.showInvite(json);
				}else if(transType.equals("sendMsgCode")){
                    json = busProcessing.sendMsg(json);
                }else if(transType.equals("deyiPay")){
                    json = busProcessing.payToDy(json);
                }else if(transType.equals("addMerchant")){
                    json = busProcessing.addMerchant(json);
                }else if(transType.equals("deyiPayQuery")){
                    json = busProcessing.deyiPayQuery(json);
                }else if(transType.equals("purchase")){         //模仿卡头消费  取消放到前置走
                    json = busProcessing.purchase(json);
                }else if(transType.equals("scanPay")){      //扫码付   下单支付
                    json = busProcessing.scanPay(json);
                }else if(transType.equals("pradePay")){      //扫码付   预下单
                    json = busProcessing.pradePay(json);
                }else if(transType.equals("pradePayList")){         //扫码付查询
                    json = busProcessing.pradePayList(json);
                }else if(transType.equals("yinlianPay")){      //银联快捷支付
                    json = busProcessing.yinlianPay(json);
                }else if(transType.equals("driverBussiness")){      //绑定驾驶证
                    json = busProcessing.driverBussiness(json);
                }else if(transType.equals("scanRegist")){      //无卡激活码
                    json = busProcessing.scanRegist(json);
                }else if(transType.equals("getScanMer")){      //无卡报备
                    json = busProcessing.getScanMer(json);
                }
			}
			log.info("back Json:"+json.toString());
			getResponse().getWriter().write(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
			Map<String, String> map = new HashMap<String, String>();
			map.put("respCode", "9995");
			map.put("respMsg", ValueTool.SYS_CODE.get("9995"));
			String jsonString = TmallYfDataPackage.convertObjectToJson(map);
			JSONObject	json=JSONObject.fromObject(jsonString);
			try {
				getResponse().getWriter().write(json.toString());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 扫码付 预下单 回调
	 */
	public void pradePayBack(){
        HttpServletRequest request = getRequest();
        InputStream instream;
        StringBuffer sb = new StringBuffer();  
        try {
            instream = request.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
            String data = null;  
            while((data = in.readLine())!=null){  
                sb.append(data);  
            }  
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        
        Enumeration pNames=request.getParameterNames();
        String name = "";
        String value = "";
        while(pNames.hasMoreElements()){
            name=(String)pNames.nextElement();
            value=request.getParameter(name);
        }  
        JSONObject backJson = JSONObject.fromObject(name+value);
        log.info("pradePayBack="+backJson.toString());
//        String orderNum = request.getParameter("orgOrderNo");
//        String respcd = request.getParameter("respCode");
//        String errorDetail = request.getParameter("respMsg");
        String orderNum = backJson.optString("orgOrderNo");
        String respcd = backJson.optString("respCode");
        String errorDetail = backJson.optString("respMsg");
        log.info("receive orderNum:"+orderNum);
        log.info("receive respcd:"+respcd);
        JSONObject json = new JSONObject();
        json.put("orderNum", orderNum);
        json.put("respcd", respcd);
        json.put("errorDetail", errorDetail);
        busProcessing.pradePayBack(json);
        try {
            getResponse().getWriter().write("{\"code\":\"0000\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void yinLianPayBack(){
	    log.info("银联快捷回调");
        HttpServletRequest request = getRequest();
//        try {
//            request.setCharacterEncoding("UTF-8");
//        } catch (UnsupportedEncodingException e1) {
//            e1.printStackTrace();
//        }
        String businessContext = request.getParameter("businessContext");
        String aesKey="0t797Yw1RjWBUHVvoBAG+w==";
        JSONObject receiveJson;
        try {
         receiveJson = JSONObject.fromObject(AESCoder.decryptDataByAES(businessContext,aesKey,"UTF-8"));
         log.info("收到报文:"+receiveJson.toString());
        String orderNum = receiveJson.optString("transactionId");
        String respcd = receiveJson.optString("retCode");
        String errorDetail = receiveJson.optString("retRemark");
        log.info("receive orderNum:"+orderNum);
        log.info("receive respcd:"+respcd);
        
        JSONObject json = new JSONObject();
        json.put("orderNum", orderNum);
        json.put("respcd", respcd);
        json.put("errorDetail", errorDetail);
        busProcessing.pradePayBack(json);
            getResponse().getWriter().write("SUCCESS");
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
	public String getIpAddress(HttpServletRequest request) { 
	    String ip = request.getHeader("x-forwarded-for"); 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("WL-Proxy-Client-IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_CLIENT_IP"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getHeader("HTTP_X_FORWARDED_FOR"); 
	    } 
	    if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
	      ip = request.getRemoteAddr(); 
	    } 
	    return ip; 
	  } 
	
	public static void main(String[] args) {
	    Map<String, String> map = new HashMap<String, String>();
        map.put("transType", "deyiPay");
        map.put("orgNo", "100");
        map.put("intxnDt", "20170120");
        map.put("referenceNo", "170120140348");
        map.put("merId", "136090058120132");
        map.put("cardNo", "4367421273610498563");
        map.put("userName", "吴海泉");
        map.put("lineNum", "105306300017");
        map.put("bankName", "建行如东县支行");
        map.put("transAmt", "100");
        
        String jsonString = TmallYfDataPackage.convertObjectToJson(map);
        System.out.println(jsonString);
    }
	public boolean checkJson(JSONObject json){
		String transType =json.getString("transType"); 
		if(StringUtils.isEmpty(transType)){
			return false;
		}
		else if(transType.equals("mobileRegist")){
			String loginName = json.optString("loginName");
			String password = json.optString("password");
			if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)){
				return false;
			}
			return true;
		}
		else if(transType.equals("invite")){
			String loginName = json.optString("loginName");
			String password = json.optString("password");
			String inviteName = json.optString("inviteName");
			if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(inviteName)){
				return false;
			}
		}
		else if(transType.equals("waresList")){
			String page = json.optString("page");
			String pageSize = json.optString("pageSize");
			if(StringUtils.isEmpty(page) || StringUtils.isEmpty(pageSize) ){
				return false;
			}
		}
		else if(transType.equals("specList")){
			String waresId = json.optString("waresId");
			String page = json.optString("page");
			String pageSize = json.optString("pageSize");
			if(StringUtils.isEmpty(page) || StringUtils.isEmpty(pageSize) || StringUtils.isEmpty(waresId)){
				return false;
			}
		}
		else if(transType.equals("sendMsgCode")){
		    String loginName = json.optString("loginName");
            if(StringUtils.isEmpty(loginName)){
                return false;
            }
		}
		else if(transType.equals("showInvite")){
			String loginName = json.optString("loginName");
			if(StringUtils.isEmpty(loginName)){
				return false;
			}
		}
		else if(transType.equals("deyiPay")){
		    if(StringUtils.isEmpty(json.optString("cardNo"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("userName"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("lineNum"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("bankName"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("transAmt"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("merId"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("referenceNo"))){
		        return false;
		    }
		    if(StringUtils.isEmpty(json.optString("inTxnDt"))){
		        return false;
		    }
		}
		else if(transType.equals("deyiPayQuery")){
            if(StringUtils.isEmpty(json.optString("tran_dt_tm"))){
                return false;
            }
            if(StringUtils.isEmpty(json.optString("id"))){
                return false;
            }
        }
		return true;
	}
	
//	public void searchOrder(){
//		HttpServletRequest request = getRequest();
//		try {
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		String message = request.getParameter("message");
//		try {
//			JSONObject json = JSONObject.fromObject(message);
//			JSONObject result = busProcessing.searchOrder(json);
//			getResponse().getWriter().write(result.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
}
