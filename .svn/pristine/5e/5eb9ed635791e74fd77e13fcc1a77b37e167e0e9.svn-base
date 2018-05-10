package com.bypay.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.bypay.domain.SubMerInfo;
import com.bypay.service.impl.util.TmallYfDataPackage;


public class DYQRCodeUtil {
//    public final static String PUBKEYPATH = "/home/rsa/qrcode_public_key.pem";
    public final static String PUBKEYPATH = "D:/qrcode_public_key.pem";
//    public final static String PRIVATEPATH = "/home/rsa/qrcode_private_key_pkcs8.pem";
//    public final static String PRIVATEPATH = "D:/qrcode_private_key_pkcs8.pem";
//    public final static String PRIVATEPATH = "D:/rsa_private_key.pem";
    public final static String PRIVATEPATH = "D:/rsa_private_key_pkcs8.pem";
    public final static String ORGNO = "9558161206135547";
    public final static String ORGVERSION = "1.0";
    public final static String BRANCHID = "WL000000000003";
    public final static String ENCRYPTTYPE  = "RSA";
    public final static String URL  = "http://101.231.126.114:80/payQRCode/yjpayGateway.htm";            //测试
//    public final static String URL  = "https://weixin.yjpal.com:19881/payQRCode/yjpayGateway.htm";            //生产
    
    /**
     * 商户入驻
     * @Title:        merchantCheckIn 
     * @Description:  
     * @param:            type =1 商户入驻  type=2银行卡绑定
     * @return:       void    
     * @throws UnsupportedEncodingException 
     * @throws AlipayApiException 
     * @throws 
     * @author        Eason Jiang
     * @Date          2016-12-8 下午3:21:44
     */
    public static void merchantCheckIn(SubMerInfo subMerInfo,int type) throws Exception, AlipayApiException{
        Map<String, String> map = getMap();
        map.put("TRADE_CODE", "PASM05");
        map.put("TX_EXTERNAL_ID", subMerInfo.getSubMerId());
        if(type==1){
            map.put("TX_PAY_TYPE", "PA001");
            map.put("TX_NAME", subMerInfo.getSubMerName());
            map.put("TX_ALIAS_NAME", subMerInfo.getShortName());
            map.put("TX_SERVICE_PHONE", subMerInfo.getContactorPhone());
            map.put("TX_CONTACT_NAME", subMerInfo.getLegalPerson());
            map.put("TX_CONTACT_MOBILE", subMerInfo.getContactorPhone());
//            map.put("TX_CONTACT_EMAIL", "");
            map.put("TX_CATEGORY_ID", getCategoryCode(subMerInfo.getMcc()));      
            map.put("TX_MEMO", "至尊宝");
        }else if(type==2){
            map.put("TX_PAY_TYPE", "PA006");
            map.put("TX_SUB_MERCHANT_ADDR", subMerInfo.getRegAddr());
            map.put("TX_BANK_CRAD_ NO", subMerInfo.getSettAccountNo());
            map.put("TX_CARD_HOLDER", subMerInfo.getSettAccountName());
            map.put("TX_BANK_NO", subMerInfo.getLineNum());
            map.put("TX_BANK_NAME", getBankNameByCode(subMerInfo.getSettAgency()));
        }
        
        //key根据ascii排序
        Collection<String> keyset= map.keySet();
        List<String> list=new ArrayList<String>(keyset);  
        Collections.sort(list);
        //加密
        String sign = "";
        for(String key:list){
            //空的字段不要加密 也不用传
            if(StringUtils.isEmpty(map.get(key))){
                map.remove(key);
                continue;
            }
            sign +=key+"="+map.get(key)+"&";
        }
        sign = sign.substring(0,sign.length()-1);           //去掉最后一个&
        System.out.println(sign);
        RSAUtils cipher = new RSAUtils();
//         加载公钥私钥
        cipher.initKey(PRIVATEPATH, PUBKEYPATH);
        
        BufferedReader br = new BufferedReader(new FileReader(PRIVATEPATH));
        String s = br.readLine();
        StringBuffer privatekey = new StringBuffer();
        s = br.readLine();
        while (s.charAt(0) != '-') {
            privatekey.append(s + "\r");
            s = br.readLine();
        }
        String signBase64Str = AlipaySignature.rsaSign(sign, privatekey.toString(), "utf-8");
//        String signBase64Str = cipher.sign(sign);
        map.put("EXT_ORG_SIGN", signBase64Str);
        System.out.println(JSONObject.fromObject(map));
        //发送
        sendTo(map);
    }
    
    /**
     * 扫唯一二维码交易接口
     * @Title:        qrTrade 
     * @Description:  
     * @param:        1微信 2支付宝
     * @return:       void    
     * @throws 
     * @author        Eason Jiang
     * @Date          2016-12-14 上午11:33:11
     */
    public static void qrTrade(SubMerInfo subMerInfo,int type){
        Map<String, String> map = getMap();
        map.put("TRADE_CODE", "PASM04");
        if(type==1){
            map.put("TX_PAY_TYPE", "PA007");
        }else if(type==2){
            map.put("TX_PAY_TYPE", "PA011");
        }
        map.put("TX_MER_ID", subMerInfo.getSubMerId());
        map.put("TX_MER_NAME", subMerInfo.getSubMerName());
        map.put("TX_SUB_ORDERID", subMerInfo.getSubMerName());
        map.put("TX_MOBILE_NO", subMerInfo.getContactorPhone());
        map.put("TX_ORDER_AMOUNT", subMerInfo.getContactorPhone());         //交易金额
        map.put("TX_TRANS_DESC", subMerInfo.getContactorPhone());         //商品详情
        map.put("TX_BAG_THEME", subMerInfo.getContactorPhone());         //商品名称

        
    }
    
    public static void main(String[] args) throws Exception, AlipayApiException {
        SubMerInfo subMerInfo = new SubMerInfo();
        subMerInfo.setSubMerId("104030158120001");
        subMerInfo.setSubMerName("吴海泉");
        subMerInfo.setShortName("吴海泉");
        subMerInfo.setContactorPhone("18626269633");
        subMerInfo.setLegalPerson("吴海泉");
        subMerInfo.setContactorPhone("18626269633");
        subMerInfo.setRegAddr("逸仙路23号203");
        subMerInfo.setSettAccountName("吴海泉");
        subMerInfo.setSettAccountNo("4367421273610498563");
        subMerInfo.setLineNum("105306300017");
        subMerInfo.setSettAgency("CCB");
        
//        subMerInfo.setSubMerId("104010055410145");
//        subMerInfo.setSubMerName("顾张聪");
//        subMerInfo.setShortName("顾张聪");
//        subMerInfo.setContactorPhone("18961468673");
//        subMerInfo.setLegalPerson("顾张聪");
//        subMerInfo.setContactorPhone("18961468673");
//        subMerInfo.setRegAddr("逸仙路23号203");
//        subMerInfo.setSettAccountName("顾张聪");
//        subMerInfo.setSettAccountNo("6228480412309089719");
//        subMerInfo.setLineNum("103304060439");
//        subMerInfo.setSettAgency("ABC");
        merchantCheckIn(subMerInfo,2);
    }
    
    private static String getBankNameByCode(String bankCode){
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("ICBC", "工商银行");
        map.put("CMBC", "民生银行");
        map.put("ABC", "农业银行");
        map.put("CMB", "招商银行");
        map.put("CCB", "建设银行");
        map.put("BCCB", "北京银行");
        map.put("BOC", "中国银行");
        map.put("BOCOM", "交通银行");
        map.put("CBHB", "渤海银行");
        map.put("CEB", "光大银行");
        map.put("CIB", "兴业银行");
        map.put("CITIC", "中信银行");
        map.put("CZB", "浙商银行");
        map.put("GDB", "广发银行");
        map.put("HXB", "华夏银行");
        map.put("PINGAN", "平安银行");
        map.put("SRCB", "上海农村商业银行");
        map.put("FRCU", "沙县农商行");
        map.put("PSBC", "中国邮政储蓄银行有限责任公司");
        return map.get(bankCode);
            
    }
    
private static String getCategoryCode(String bankCode){
        
        Map<String, String> map = new HashMap<String, String>();
        map.put("8398", "2016062900190385");
        map.put("7832", "2016062900190388");
        map.put("5712", "2015090700045519");
        map.put("4814", "2016062900190218");
        map.put("7299", "2015063000028051");
        map.put("4900", "2016062900190231");
        map.put("5411", "2016062900190236");
        map.put("7295", "2016062900190154");
        map.put("4816", "2016062900190247");
        map.put("7295", "2016062900190161");
        map.put("7261", "2016062900190168");
        map.put("4733", "2016062900190169");
        map.put("7995", "2016062900190171");
        map.put("7995", "2016062900190171");
        map.put("7995", "2016062900190171");
        map.put("5812", "2015050700000010");
        map.put("5812", "2015050700000010");
        map.put("5812", "2015050700000010");
        map.put("5812", "2015050700000010");
        return map.get(bankCode);
            
    }

    public static void sendTo(Map<String,String> map){
        DefaultHttpClient httpClient = new DefaultHttpClient();  
        HttpPost request = new HttpPost(URL);  
        try {
                //迭代MAP
                Set<String> keySet = map.keySet();
                Iterator<String> iterator = keySet.iterator();
            
                //配置参数
                List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();  
                while(iterator.hasNext()){
                    String key = iterator.next();
                    nvps.add(new BasicNameValuePair(key,map.get(key))); // 参数
                }
                UrlEncodedFormEntity params =new UrlEncodedFormEntity(nvps, HTTP.UTF_8);
                request.setEntity(params);
                //发送请求
                HttpResponse response = httpClient.execute(request);  
                HttpEntity entity = response.getEntity();  
                InputStream instream = entity.getContent();  
                BufferedReader in = new BufferedReader(new InputStreamReader(instream,"UTF-8"));  
                StringBuffer sb = new StringBuffer();  
                String data = null;  
                while((data = in.readLine())!=null){  
                    sb.append(data);  
                }  
                if(in != null)  
                    in.close(); 
                System.out.println("return="+sb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }
    
    
    public static Map getMap(){
        Map<String, String> map = new HashMap<String,String>();
        map.put("EXT_ORG_NO", ORGNO);
        map.put("EXT_ORG_VERSION", ORGVERSION);
        map.put("EXT_ORG_BRANCHID", BRANCHID);
        map.put("EXT_ORG_ENCRYPT_TYPE", ENCRYPTTYPE);
        return map;
    }
}
