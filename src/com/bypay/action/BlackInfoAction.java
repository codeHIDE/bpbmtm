package com.bypay.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bypay.common.BaseAction;
import com.bypay.dao.BlackInfoDao;
import com.bypay.dao.IdCardInfoDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.dao.SubMerTerminalDao;
import com.bypay.domain.BlackInfo;
import com.bypay.domain.IdCardInfo;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SubMerTerminal;
import com.bypay.domain.clientTool.TerminalEnable;
import com.bypay.service.BankCardCheckService;
import com.bypay.util.JAUtil;
import com.bypay.util.PageUtil;
import com.bypay.util.YMInterfaceUtil;
/**
 * 黑名单ACTION
 */
public class BlackInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = LoggerFactory.getLogger(BlackInfoAction.class);  
	private BlackInfo blackInfo;
	@Inject
	private BlackInfoDao blackInfoDao;
	@Inject
	private SubMerInfoDao subMerInfoDao;
	@Inject
	private SubMerTerminalDao subMerTerminalDao;
	@Inject
	private IdCardInfoDao idCardInfoDao;
	@Inject
	private BankCardCheckService bankCardCheckService;
	private List<BlackInfo> blcakInfoList;
	private int page=1;
	private int pageSize=15;
//	@Autowired
//	private NewPayService payService;
	
	private IdCardInfo idCardInfo;
	static ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault());
	///////////////////////////////业务////////////////////////////////
	/**
	 * 查询所有黑名单信息
	 */
	public String subMerId;
	public String alertType;
	@SuppressWarnings("unchecked")
	public void selectBlackInfoList(){
		try {
			if(blackInfo==null){
				blackInfo=new BlackInfo();
			}
			if(blackInfo.getRealName()!=null&&!"".equals(blackInfo.getRealName())){
				//blackInfo.setRealName("%"+new String(blackInfo.getRealName().getBytes("ISO-8859-1"),"UTF-8")+"%");
				blackInfo.setRealName("%"+blackInfo.getRealName()+"%");
			}
			logger.info("blackInfo.getRealName="+blackInfo.getRealName());
			Map<String,Object> map = PageUtil.getPageMap(page,pageSize);
			map.put("blackInfo", blackInfo);
			int count=blackInfoDao.selectBlackInfoCount(map);
			blcakInfoList = blackInfoDao.selectBlackInfoList(map);
			JSONArray array = JSONArray.fromObject(blcakInfoList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			String subMerList=object.toString();
			getResponse().getWriter().write(subMerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 修改指定黑名单信息的状态
	 */
	public void updateBlackInfoStatus(){
		try {
			BlackInfo black = new BlackInfo();
			log.info("update Black="+blackInfo.getId());
			if(StringUtils.isEmpty(blackInfo.getId())){//如果实体类对象中没有子商户号则结束操作
				return;
			}else{
				black = blackInfoDao.selectBlackInfoById(blackInfo);
			}
			if(blackInfo.getStatus().equals("0")){
				//如果是移除黑名单修改终端状态
				TerminalEnable terminalEnable = new TerminalEnable();
				terminalEnable.setTerminalId(black.getTerminalId());
				subMerTerminalDao.updateStatus(terminalEnable);
				blackInfoDao.deleteBlackInfo(black);
			}else{
				blackInfoDao.deleteBlackInfo(black);
			}
			getResponse().getWriter().write("succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 添加黑名单
	 * @return
	 */
	public String insertBlackInfoList(){
		if(blackInfo!=null){
			//验证用户填写的子商户号
			SubMerInfo smi=new SubMerInfo();
			smi.setSubMerId(blackInfo.getSubMerId());
			smi=subMerInfoDao.findById(smi);
			if(smi==null){//验证未通过,不做处理
				//getRequest().setAttribute("black", "0");
				//return "insert";
			}else{//将子商户状态改为黑名单
				if(!subMerInfoDao.updateStatus(smi)){
					getRequest().setAttribute("black", "0");
					return "insert";
				}
			}
			//验证用户填写的终端TSN
			SubMerTerminal smt=new SubMerTerminal();
			smt.setTsn(blackInfo.getTerminalId());
			smt.setSubMerId(blackInfo.getSubMerId());
			SubMerTerminal smtL=subMerTerminalDao.selectSubMerTerminalByTerminalId(smt);
			if(smtL!=null){//验证未通过
				if(!subMerTerminalDao.updateTreminalStatus("3", smtL.getId()+"")){
					getRequest().setAttribute("black", "3");
					return "insert";
				}
			}
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			blackInfo.setCreateTime(sdf.format(new Date()));//创建时间
			if (blackInfoDao.insertBlackInfo(blackInfo)) {
				getRequest().setAttribute("black", "1");
			} else {
				getRequest().setAttribute("black", "2");
			}
		}
		return "insert";
	}
	
	/**
	 * 银行卡鉴权
	 * @Title:        checkBankCard 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-7-9 上午10:16:13
	 */
	public void checkBankCard(){
		String userName = getRequest().getParameter("userName");
		String idNum = getRequest().getParameter("idNum");
		String cardNo = getRequest().getParameter("cardNo");
		String cellNo = getRequest().getParameter("cellNo");
		Map<String,String> paraMap = new HashMap<String,String>();
		paraMap.put("userName", userName);
		paraMap.put("idNum", idNum);
		paraMap.put("cardNo", cardNo);
		paraMap.put("cellNo", cellNo);
		Map<String,String> resultMap = bankCardCheckService.checkBankCard(paraMap);
		String respCode = resultMap.get("respCode");
		String respMsg = resultMap.get("respMsg");
		logger.info("respCode="+respCode);
		logger.info("respMsg="+respMsg);
		try {
			getResponse().getWriter().write(respMsg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 银行卡/信用卡 验证
	 * @Title:        checkCreditCard 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-7-10 下午12:17:16
	 */
	public void checkCreditCard(){
		String userName = getRequest().getParameter("userName");
		String cardNo = getRequest().getParameter("cardNo");
		Boolean flag = false;
		 try{
			 JSONObject json = checkYMCredit(userName,cardNo);
			 if(json!=null && json.getString("stat").equals("1")){
				 flag = true;
			 }
			 getResponse().getWriter().write(flag.toString());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
	
	
	public JSONObject checkYMCredit(String userName,String cardNo){
		JSONObject tokenJson = YMInterfaceUtil.getToken2();
		JSONObject json = null;
		if(tokenJson!=null){
			String token = tokenJson.getString("access_token");
			json = YMInterfaceUtil.checkBankCard2(userName, cardNo, token);
		}
		return json;
	}
	/**
	 * 身份证验证
	 * @Title:        checkIdCard 
	 * @Description:  
	 * @param:            
	 * @return:       void    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2016-6-13 上午11:32:55
	 */
	public void checkIdCard(){
		String userName = getRequest().getParameter("userName");
		String cardNo = getRequest().getParameter("cardNo");
		logger.info("收到姓名:"+userName);
		logger.info("收到身份证:"+cardNo);
		IdCardInfo idcardInfo = new IdCardInfo();
		idcardInfo.setIdNum(cardNo);
		idcardInfo.setUserName(userName);
		idcardInfo = idCardInfoDao.selectOne(idcardInfo);
		String picPath = rb.getString("idPicPath");
		try {
			if(idcardInfo!=null){
				getResponse().getWriter().write("库存里含有该信息");
				return;
			}
			Map<String, String> param = new HashMap<String, String>();
		    param.put("userName", userName);
		    param.put("idNum", cardNo);
		    JSONObject json = JAUtil.checkId(param);
		    log.info("json = "+json);
            if(json!=null && json.optString("respCode").replaceAll("\\[\"|\"\\]", "").equals("000")){
                String jsonStr = json.optString("jsonStr");
                jsonStr = jsonStr.substring(1,jsonStr.length()-1);
                log.info("jsonStr"+jsonStr);
                String OUTPUT1 = JSONObject.fromObject(jsonStr).optString("OUTPUT1");
                log.info("picBuff="+OUTPUT1);
              //保存记录
                idcardInfo = new IdCardInfo();
                idcardInfo.setUserName(userName);
                idcardInfo.setIdNum(cardNo);
                idcardInfo.setPicPath(picPath+cardNo+".jpg");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                idcardInfo.setCreateTime(sdf.format(new Date()));
                idCardInfoDao.insertIdCardInfo(idcardInfo);
                YMInterfaceUtil.makePic(OUTPUT1, picPath+cardNo+".jpg");
                getResponse().getWriter().write("验证成功");
            }else{
                getResponse().getWriter().write("验证失败");  
            }
		} catch (Exception e) {
			e.printStackTrace();
			try {
				getResponse().getWriter().write("获取接口超时");	
			} catch (Exception e2) {
				// TODO: handle exception
			}
			
		}
	}
	
	public void alertSubMer() {
		String subMerPath ="";
		if(alertType.equals("1")){
			 subMerPath = rb.getString("alertSub");
		}else if(alertType.equals("2")){
			subMerPath = rb.getString("alertAddr");
		}else{
			try {
				getResponse().getWriter().write("请选择提示方式");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(StringUtils.isNotEmpty(subMerId)){
			 try {
				FileWriter fileWriter=new FileWriter(subMerPath,true);
				fileWriter.write(subMerId);
				fileWriter.write("\r\n");
				fileWriter.flush();
				fileWriter.close();
				 getResponse().getWriter().write("保存成功");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					getResponse().getWriter().write("保存失败");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}else{
			try {
				getResponse().getWriter().write("没有数据");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	        	
	public void selectIdCard() {
		  try {
		      if (idCardInfo == null) {
		    	  idCardInfo = new IdCardInfo();
		      }
		      Map map = PageUtil.getPageMap(page, pageSize);
		      map.put("idCardInfo", idCardInfo);
		      int count = 0;
		      count = idCardInfoDao.selectIdCardInfoCount(map);
			  List<IdCardInfo> idCardInfoList = idCardInfoDao.selectIdCardInfo(map);
		      JSONArray array = JSONArray.fromObject(idCardInfoList);
		      JSONObject object = new JSONObject();
		      object.put("Rows", array.toString());
		      object.put("Total", count);
		      String  subMerTerminalListJson = object.toString();
		      getResponse().getWriter().write(subMerTerminalListJson);
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
	  }
	
	public void downlIdPic() {
	    FileInputStream fis = null;
	    OutputStream out = null;
	    try {
	        String idNum = getRequest().getParameter("idNum");
	        System.out.println("下载身份证照片"+idNum);
	        String picPath = rb.getString("idPicPath");
	        File file = new File(picPath+idNum+".jpg");
	        System.out.println("路径为"+picPath+idNum+".jpg");
	      HttpServletResponse response = getResponse();
	      if(file.exists()){
	          response.setContentType("application/x-xls;charset=GBK");
	          response.setHeader("Content-Disposition", "attachment; filename="+file.getName());
	            response.addHeader("Content-Length", file.length()+"");
	            fis = new FileInputStream(file);
	          out = response.getOutputStream();
	          byte[] b = new byte[1024];
	          int len = 0;
	          while ((len=fis.read(b))!=-1) {
	              out.write(b,0,len);
	          }              
	        }else {
	          System.out.println("文件不存在");
	        }     
	    } catch (Exception e) {
	        e.printStackTrace();
	    }finally{
	        try {
	            if(fis!=null){
	                fis.close();
	            }
	            if(out!=null)
	                out.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
	
	
	
	////////////////////////////GET  SET/////////////////////////
	
	public BlackInfo getBlackInfo() {
		return blackInfo;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getSubMerId() {
		return subMerId;
	}
	public void setSubMerId(String subMerId) {
		this.subMerId = subMerId;
	}
	public BankCardCheckService getBankCardCheckService() {
		return bankCardCheckService;
	}
	public void setBankCardCheckService(BankCardCheckService bankCardCheckService) {
		this.bankCardCheckService = bankCardCheckService;
	}
	public void setBlackInfo(BlackInfo blcakInfo) {
		this.blackInfo = blcakInfo;
	}
	public BlackInfoDao getBlackInfoDao() {
		return blackInfoDao;
	}
	public void setBlackInfoDao(BlackInfoDao blcakInfoDao) {
		this.blackInfoDao = blcakInfoDao;
	}
	public List<BlackInfo> getBlcakInfoList() {
		return blcakInfoList;
	}
	public void setBlcakInfoList(List<BlackInfo> blcakInfoList) {
		this.blcakInfoList = blcakInfoList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public SubMerInfoDao getSubMerInfoDao() {
		return subMerInfoDao;
	}
	public void setSubMerInfoDao(SubMerInfoDao subMerInfoDao) {
		this.subMerInfoDao = subMerInfoDao;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public SubMerTerminalDao getSubMerTerminalDao() {
		return subMerTerminalDao;
	}
	public void setSubMerTerminalDao(SubMerTerminalDao subMerTerminalDao) {
		this.subMerTerminalDao = subMerTerminalDao;
	}
	public IdCardInfo getIdCardInfo() {
		return idCardInfo;
	}
	public void setIdCardInfo(IdCardInfo idCardInfo) {
		this.idCardInfo = idCardInfo;
	}
	
	
}
