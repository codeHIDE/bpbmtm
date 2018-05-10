package com.bypay.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.bypay.common.BaseAction;
import com.bypay.dao.CoreTransLogDao;
import com.bypay.dao.OrderInfoDao;
import com.bypay.dao.OrderStatictisDao;
import com.bypay.dao.RegionCodeDao;
import com.bypay.dao.SendFileDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.domain.CallBackTem;
import com.bypay.domain.CoreTransLog;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.OrderInfo;
import com.bypay.domain.OrderStatictis;
import com.bypay.domain.SendFile;
import com.bypay.domain.SubMerCashout;
import com.bypay.domain.SubMerCashoutBatch;
import com.bypay.domain.SubMerInfo;
import com.bypay.service.MerSettleStatictisService;
import com.bypay.service.RegionCodeService;
import com.bypay.service.SubMerCashoutBatchService;
import com.bypay.service.SubMerCashoutService;
import com.bypay.service.SubMerInfoService;
import com.bypay.service.impl.util.StatisticsParser;
import com.bypay.util.AmountUtils;
import com.bypay.util.DateUtil;
import com.bypay.util.ImportExcelFile;
import com.bypay.util.PageUtil;
import com.bypay.util.ZipUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class MerSettleStatictisAction extends BaseAction {

  private static final long serialVersionUID = 8237821849312954183L;

  @Autowired
  private MerSettleStatictisService merSettleStatictisService;

  private MerSettleStatictis merSettleStatictis;

  private List<MerSettleStatictis> merSettleStatictisList;
  @Inject
  private SubMerCashoutService subMerCashoutService;
  @Inject
  private SubMerCashoutBatchService subMerCashoutBatchService;
  @Inject
  private SubMerInfoService subMerInfoService;
  @Inject
  private RegionCodeService regionCodeService;
  @Inject
  private StatisticsParser statisticsParser;
  @Inject
  private SendFileDao sendFileDao;
  @Inject
  private OrderStatictisDao orderStatictisDao;
  @Inject
  private OrderInfoDao orderInfoDao;
  @Inject
  private SubMerInfoDao subMerInfoDao;
  @Inject
  private RegionCodeDao regionCodeDao;
  @Inject
  private CoreTransLogDao coreTransLogDao;
  private String startTime;
  private String endTime;
  private String merType;
  private String settleDate2;
  private String merSettleStatictisListJson;
  private String mid;
  private String clearStatus;
  private String id;
  private String merAmt;
  private String transFee;
  private String faileAmt;// 清分失败金额
  private String clearSuccAmt;// 清分成功金额
  private String wqfAmt;// 未清分金额
  private String clearAmt;
  private String sumAmt;
  private String d1TransFee;//D1手续费
  private String t0SuccSumAmt;//T0成功金额
  private String t0SuccTransFee;//T0成功手续费
  private String t0ErrorSumAmt;//T0失败金额
  private String t0ErrorTransFee;//T0失败手续费
  private String settleDate1;
  private String createDate2;
  private File callBackFile;
  private String dowloadT1OrD1FileName = null;
  private String callBackFileFileName;
  ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault());
  // 分页开始
  private int page = 1;
  private int pageSize = 15;
  private int page1 = 1;
  private int pageSize1 = 10;
  private int count = 0;
  
  private File xlsFile;
  private String settleDate;
  private String chName;
  private String fileType;
  
  public String getSettDate() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_MONTH, -1);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    settleDate2 = formatter.format(c.getTime());
    // settleDate2 = "2014-08-28";
    getRequest().setAttribute("settleDate2", settleDate2);
    // 以上是获取前一天时间 格式是 20131126
    return "getSettDate";
  }

  public String getdownloadDf() {
    String ret = getRequest().getParameter("ret");
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_MONTH, -1);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    settleDate2 = formatter.format(c.getTime());
    // settleDate2 = "2014-08-28";
    getRequest().setAttribute("settleDate2", settleDate2);
    // 以上是获取前一天时间 格式是 20131126
    return ret;
  }

  public void dfList() {
    try {
      if (merSettleStatictis == null) {
        merSettleStatictis = new MerSettleStatictis();
      }
      String settleDate = getRequest().getParameter("settleDate");
      String settleType = getRequest().getParameter("settleType");
      if (settleDate != null) {
        settleDate = settleDate.replace("-", "");
      }
      SendFile sendFile = new SendFile();
      sendFile.setSettleDate(settleDate);
      sendFile.setSettleType(settleType);
      List<SendFile> sendFileList = sendFileDao.selectSendFileList(sendFile);
      JSONArray array = JSONArray.fromObject(sendFileList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      merSettleStatictisListJson = object.toString();
      System.out.println(merSettleStatictisListJson);
      getResponse().getWriter().write(merSettleStatictisListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * T1报盘文件查询
   * @Title:        checkList 
   * @Description:  
   * @param:            
   * @return:       void    
   * @throws 
   * @author        Eason Jiang
   * @Date          2016-4-6 上午9:59:17
   */
  public void checkList() {
	    try {
	      if (merSettleStatictis == null) {
	        merSettleStatictis = new MerSettleStatictis();
	      }
	      String settleDate = getRequest().getParameter("settleDate");
	      if (StringUtils.isNotEmpty(settleDate)) {
	        settleDate = settleDate.replace("-", "");
	      }else{
	    	  return;
	      }
	      //报盘文件根目录
	      String filePath = rb.getString("ExcelUrl") + "/issuingFile/"+settleDate+"/";
	      File dirFile = new File(filePath);
	      String[] names = dirFile.list(new FilenameFilter(){
	             public boolean accept(File f , String name){
		            	if(name.indexOf(".txt")!=-1){
		            		return true;
		            	}else{
		            		return false;
		            	}
	              }    
	          });
	      List<Map<String,String>> sendFileList = new ArrayList<Map<String,String>>();
	      for(String fileName:names){
	    	  Map<String,String> fileMap = new HashMap<String,String>();
	    	  fileMap.put("fileName", fileName);
	    	  fileMap.put("settleDate", settleDate);
	    	  sendFileList.add(fileMap);
	      }
	      JSONArray array = JSONArray.fromObject(sendFileList);
	      JSONObject object = new JSONObject();
	      object.put("Rows", array.toString());
	      merSettleStatictisListJson = object.toString();
	      System.out.println(merSettleStatictisListJson);
	      getResponse().getWriter().write(merSettleStatictisListJson);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
  
  public void downloadDf(){
    FileInputStream fis = null;
    OutputStream out = null;
    try {
        String settleDate = getRequest().getParameter("settleDate");
        String settleType = getRequest().getParameter("settleType");
        String filePath = rb.getString("ExcelUrl") + "/issuingFile/" + settleDate;
//      String filePath = "F://test";
//      String fileName = settleDate.replace("-", "");
        if("1".equals(settleType)){
//          fileName += "_T1.xls";
          dowloadT1OrD1FileName = "T1";
        }
        if("0".equals(settleType)){
//          fileName += "_D1.xls";
          dowloadT1OrD1FileName = "D1";
        }
        File file = new File(filePath);
        FilenameFilter filter = new FilenameFilter() {
          @Override
          public boolean accept(File dir, String name) {                
              return name.indexOf(dowloadT1OrD1FileName) != -1;
          }
      };
      File[] files = file.listFiles(filter);
      ServletOutputStream sos = null;    
      HttpServletResponse response = getResponse();
      if(files.length>0){
        if(files.length == 1){   
          response.setContentType("application/x-xls;charset=GBK");
          response.setHeader("Content-Disposition", "attachment; filename="+files[0].getName());
            response.addHeader("Content-Length", files[0].length()+"");
            fis = new FileInputStream(files[0]);
          out = response.getOutputStream();
          byte[] b = new byte[1024];
          int len = 0;
          while ((len=fis.read(b))!=-1) {
              out.write(b,0,len);
          }              
        }else {
          response.setContentType("application/zip");// 设置response内容的类型
          response.setHeader("Content-Disposition",
                  "attachment;  filename =" + settleDate+".zip");
          sos = response.getOutputStream();
          zipFiles(files, sos);
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

  /**
   * 下载报盘文件
   * @Title:        downloadCheck 
   * @Description:  
   * @param:            
   * @return:       void    
   * @throws 
   * @author        Eason Jiang
   * @Date          2016-4-6 上午11:50:10
   */
  public void downloadCheck(){
	  System.out.println("下载报盘文件");
	    FileInputStream fis = null;
	    OutputStream out = null;
	    try {
	        String fileName = getRequest().getParameter("fileName");
	        String settleDate = getRequest().getParameter("settleDate");
	        String filePath = rb.getString("ExcelUrl") + "/issuingFile/"+settleDate+"/";
//	      String filePath = "F://test";
//	      String fileName = settleDate.replace("-", "");
	        File file = new File(filePath+fileName);
	        System.out.println("文件为"+filePath+fileName);
	      HttpServletResponse response = getResponse();
	        if(file.exists()){   
	        	System.out.println("文件为存在");
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
  
/**
 * 功能:压缩多个文件成一个zip文件
 * <p>作者 陈亚标 Jul 16, 2010 10:59:40 AM
 * @param srcfile：源文件列表
 * @param zipfile：压缩后的文件
 */
public static void zipFiles(File[] srcfile,ServletOutputStream sos){
    byte[] buf=new byte[1024];
    try {
        //ZipOutputStream类：完成文件或文件夹的压缩
        ZipOutputStream out=new ZipOutputStream(sos);
        for(int i=0;i<srcfile.length;i++){
            FileInputStream in=new FileInputStream(srcfile[i]);
            out.putNextEntry(new ZipEntry(srcfile[i].getName()));
            int len;
            while((len=in.read(buf))>0){
                out.write(buf,0,len);
            }
            out.closeEntry();
            in.close();
        }
        out.close();
        System.out.println("压缩完成.");
    } catch (Exception e) {
        e.printStackTrace();
    }
}

  public void checkDf() {
    try {
      String settleDate = getRequest().getParameter("settleDate");
      String settleType = getRequest().getParameter("settleType");
      String filePath = rb.getString("ExcelUrl") + "/issuingFile/" + settleDate;
      String fileName = settleDate.replace("-", "");
      if ("1".equals(settleType)) {
        fileName += "_T1.xls";
      } else if ("0".equals(settleType))
        fileName += "_D1.xls";
      File file = new File(filePath + "/" + fileName);
      System.out.println(filePath + "/" + fileName);
      if (!file.exists()) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
      return;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 获得前一天没清算的 交易
  public String merSettleStatictisList() {
    try {
      if ("1".equals(id)) {
        getSettDate();
      }
      if (merSettleStatictis == null) {
        merSettleStatictis = new MerSettleStatictis();
      }
      merSettleStatictis.setMerType("0");
      getRequest().setAttribute("settleDate2", settleDate2);
      merSettleStatictis.setSettleDate(settleDate2.replaceAll("-", ""));
      Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
      map.put("merSettleStatictis", merSettleStatictis);
      // count =
      // merSettleStatictisService.selectMerSettleStatictisListCount(map);
      merSettleStatictisList = merSettleStatictisService.selectMerSettleStatictisList(map);
      List<MerSettleStatictis> merSettleStatictisList1 = merSettleStatictisService
          .selectMerSettleStatictisListAmt(merSettleStatictis);
      Iterator<MerSettleStatictis> merSettleStatictisIt = merSettleStatictisList1.iterator();
      SubMerCashout subMerCashout = new SubMerCashout();
      subMerCashout.setCreateTime(settleDate2.replace("-",""));
      subMerCashout.setMerSysId(merSettleStatictis.getMerSysId());
      map = new HashMap<String, Object>();
      map.put("subMerCashout",subMerCashout);
      List<SubMerCashout> subMerCashoutList = subMerCashoutService.selectSubMerCashoutAmt(map);
      Iterator<SubMerCashout> subMerCashoutIt = subMerCashoutList.iterator();
      Double faileAmt = 0D;// 清分失败金额
      Double clearSuccAmt = 0D;// 清分成功金额
      Double wqfAmt = 0D;// 未清分金额
      Double clearAmt = 0D;//清分总金额
      Double sumAmt = 0D;//交易总金额
      Double transFee = 0D;//手续费
      Double d1TransFee = 0D;//D1手续费
      Double t0SuccSumAmt = 0D;//T0成功金额
      Double t0SuccTransFee = 0D;//T0成功手续费
      Double t0ErrorSumAmt = 0D;//T0失败金额
      Double t0ErrorTransFee = 0D;//T0失败手续费
      while (subMerCashoutIt.hasNext()) {
    	  subMerCashout = (SubMerCashout) subMerCashoutIt.next();
    	  Double orderAmt = subMerCashout.getOrderAmt() == null || "".equals(subMerCashout.getOrderAmt())
    	  					? 0 : Double.parseDouble(subMerCashout.getOrderAmt())/100D;
    	  Double t0transFee = subMerCashout.getTransFee() == null || "".equals(subMerCashout.getTransFee())
			? 0 : Double.parseDouble(subMerCashout.getTransFee())/100D;
		switch (Integer.parseInt(subMerCashout.getOrderStatus())) {
			case 1:
				t0SuccSumAmt += orderAmt;
				t0SuccTransFee += t0transFee;
				break;
			case 2:
				t0SuccSumAmt += orderAmt;
				t0SuccTransFee += t0transFee;
				break;
			case 3:
				t0ErrorSumAmt += orderAmt;
				t0ErrorTransFee += t0transFee;
				break;
			case 9:
				t0SuccSumAmt += orderAmt;
				t0SuccTransFee += t0transFee;
				break;
			default:
				break;
		}
      }
      while (merSettleStatictisIt.hasNext()) {
        MerSettleStatictis merSettleStatictis = (MerSettleStatictis) merSettleStatictisIt.next();
        Double sumAmts = merSettleStatictis.getSumAmt() == null || "".equals(merSettleStatictis.getSumAmt())
		 ? 0 : (Double.parseDouble(merSettleStatictis.getSumAmt()) / 100);
        Double clearAmts = merSettleStatictis.getClearAmt() == null || "".equals(merSettleStatictis.getClearAmt())
		 ? 0 : (Double.parseDouble(merSettleStatictis.getClearAmt()) / 100);
        Double sumTransFee = merSettleStatictis.getSumTransFee() == null || "".equals(merSettleStatictis.getSumTransFee())
		 ? 0 : (Double.parseDouble(merSettleStatictis.getSumTransFee()) / 100);
        Double countFee = merSettleStatictis.getCountFee() == null || "".equals(merSettleStatictis.getCountFee())
		 ? 0 : (Double.parseDouble(merSettleStatictis.getCountFee()) / 100);
        sumAmt += sumAmts;
        clearAmt += clearAmts;
        transFee += sumTransFee;
        d1TransFee += countFee;
        switch (Integer.parseInt(merSettleStatictis.getClearStatus())) {
        case 0:
          if (merSettleStatictis.getClearAmt() != null)
            wqfAmt += (Double.parseDouble(merSettleStatictis.getClearAmt()) / 100);
          break;
        case 2:
          if (merSettleStatictis.getClearAmt() != null)
            clearSuccAmt += (Double.parseDouble(merSettleStatictis.getClearAmt()) / 100);
          break;
        case 3:
          if (merSettleStatictis.getFaileAmt() != null)
            faileAmt += (Double.parseDouble(merSettleStatictis.getFaileAmt()) / 100);
          break;
        default:
          break;
        }
      }
      DecimalFormat df = new DecimalFormat("#0.00");
      JSONArray array = JSONArray.fromObject(merSettleStatictisList);
      this.faileAmt = df.format(faileAmt);
      this.clearSuccAmt = df.format(clearSuccAmt);
      this.wqfAmt = df.format(wqfAmt);
      this.clearAmt = df.format(clearAmt);
      this.sumAmt = df.format(sumAmt);
      this.transFee = df.format(transFee);
      this.d1TransFee = df.format(d1TransFee);
      this.t0SuccSumAmt = df.format(t0SuccSumAmt);
      this.t0SuccTransFee = df.format(t0SuccTransFee);
      this.t0ErrorSumAmt = df.format(t0ErrorSumAmt);
      this.t0ErrorTransFee = df.format(t0ErrorTransFee);
      merSettleStatictisListJson = array.toString();
      System.out.println(merSettleStatictisListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "merSettleStatictisList";
  }

  public String getClear() {
    merSettleStatictis.setSettleDate(merSettleStatictis.getSettleDate().replaceAll("-", ""));
    return "getClearTodo";
  }

  public String selectReoprt() {
    try {
      String merSysId = getRequest().getParameter("merSysId");
      String settleDate1 = getRequest().getParameter("settleDate1");
      String settleDate2 = getRequest().getParameter("settleDate2");
      String settleType = getRequest().getParameter("settleType");
      String clearStatus = getRequest().getParameter("clearStatus");
      Map<String, String> statusMap = new HashMap<String, String>();// T+1
      // D+1状态
      statusMap.put("0", "未清分");
      statusMap.put("1", "待清分");
      statusMap.put("2", "已清分");
      statusMap.put("3", "失败");
      statusMap.put("4", "清分给机构");
      statusMap.put("5", "财务打款");
      statusMap.put("6", "部分成功");
      statusMap.put("7", "停止清算");
      statusMap.put("9", "处理中");
      Map<String, String> statusMap1 = new HashMap<String, String>();// T+0状态
      statusMap1.put("0", "申请");
      statusMap1.put("1", "生成代发文件等待代发");
      statusMap1.put("2", "代发成功");
      statusMap1.put("3", "代发失败");
      statusMap1.put("4", "次日代发失败");
      statusMap1.put("5", "已T+1清分");
      statusMap1.put("6", "钱包消费");
      statusMap1.put("9", "代发处理中");
      Map<String, Object> map = PageUtil.getPageMap(page, pageSize);// 条件MAP
      map.put("merSysId", merSysId);
      map.put("settleDate1", settleDate1);
      map.put("settleDate2", settleDate2);
      map.put("settleType", settleType);
      map.put("clearStatus", clearStatus);
      Map<String, String> mapfile = new LinkedHashMap<String, String>();// 报表列
      mapfile.put("settleDate", "清算日期"); // 对应实体字段
      mapfile.put("settleTime", "清算时间");
      mapfile.put("reserved1", "结算日期");
      mapfile.put("reserved1Time", "结算时间");
      mapfile.put("mid", "商户号");
      mapfile.put("merSysId", "代理商号");
      mapfile.put("id", "结算流水号");
      mapfile.put("clearAmt", "结算金额");
      mapfile.put("settleType", "结算类型");
      mapfile.put("clearStatus", "结算状态");
      mapfile.put("clearDesc", "状态描述");
      mapfile.put("bankName", "银行");
      mapfile.put("accountNum", "卡号");
      mapfile.put("sumAmt", "卡类型");
      mapfile.put("t0MerRate", "结算费率");
      mapfile.put("reserved", "结算手续费");
      mapfile.put("merProfitRate", "代理商分润比");
      mapfile.put("t0MerGain", "代理商分润");

      List<MerSettleStatictis> merSettleStatictisList1 = new ArrayList<MerSettleStatictis>();
      if ("02".equals(settleType)) {// 如果只查T+0
        List<SubMerCashout> subMerCashoutList = subMerCashoutService.selectSubMerCashoutReport(map);
        // count = subMerCashoutService
        // .selectSubMerCashoutReportCount(map);
        Iterator<SubMerCashout> subMerCashoutIt = subMerCashoutList.iterator();
        MerSettleStatictis merSettleStatictis = null;
        while (subMerCashoutIt.hasNext()) {
          SubMerCashout subMerCashout = (SubMerCashout) subMerCashoutIt.next();
          merSettleStatictis = new MerSettleStatictis();
          merSettleStatictis.setMid(subMerCashout.getSubMerId());// 商户号
          String settleDate = null;
          String settleTime = null;
          String reserved1Date = null;
          String reserved1Time = null;
          if (subMerCashout.getFinishTime() != null && subMerCashout.getFinishTime().length() == 14) {
            settleDate = subMerCashout.getFinishTime().substring(0, 8);
            settleTime = subMerCashout.getFinishTime().substring(8, 14);
          }
          if (subMerCashout.getCreateTime() != null && subMerCashout.getCreateTime().length() == 14) {
            reserved1Date = subMerCashout.getCreateTime().substring(0, 8);
            reserved1Time = subMerCashout.getCreateTime().substring(8, 14);
          }
          merSettleStatictis.setSettleDate(settleDate);// 清算时间
          merSettleStatictis.setSettleTime(settleTime);
          merSettleStatictis.setReserved1(reserved1Date);// 结算时间
          merSettleStatictis.setReserved1Time(reserved1Time);// 结算时间
          merSettleStatictis.setId(subMerCashout.getId());// 交易流水号
          merSettleStatictis.setMerSysId(subMerCashout.getMerSysId());// 代理商号（机构号）
          merSettleStatictis.setClearAmt(subMerCashout.getOrderAmt());// 结算金额
          merSettleStatictis.setClearStatus(subMerCashout.getOrderStatus());// 结算状态
          merSettleStatictis.setClearDesc(statusMap1.get(subMerCashout.getOrderStatus()));
          merSettleStatictis.setBankName(subMerCashout.getBankName());// 银行
          merSettleStatictis.setAccountNum(subMerCashout.getSettAccountNo());// 卡号
          merSettleStatictis.setT0MerRate(subMerCashout.getT0MerRate());// 结算费率
          merSettleStatictis.setReserved(subMerCashout.getTransFee());// 结算手续费
          merSettleStatictis.setT0MerGain(subMerCashout.getT0MerGain());// 代理商分润
          merSettleStatictis.setMerProfitRate(subMerCashout.getMerProfitRate());// 代理商分润比
          merSettleStatictis.setSettleType("T0");// 结算类型
          merSettleStatictisList1.add(merSettleStatictis);
        }
      } else if ("01".equals(settleType)) {// 如果只差D+1
        List<MerSettleStatictis> merSettleStatictisList = merSettleStatictisService
            .seleteMerSettleStatictisReport(map);
        // count = merSettleStatictisService
        // .seleteMerSettleStatictisReportCount(map);
        Iterator<MerSettleStatictis> merSettleStatictisIt = merSettleStatictisList.iterator();
        while (merSettleStatictisIt.hasNext()) {
          MerSettleStatictis merSettleStatictis1 = (MerSettleStatictis) merSettleStatictisIt.next();
          String reserved1Date = null;
          String reserved1Time = null;
          String settleDate = null;
          String settleTime = null;
          if (merSettleStatictis1.getSettleDate() != null
              && merSettleStatictis1.getSettleDate().length() == 8) {
            settleDate = merSettleStatictis1.getSettleDate();
            settleTime = null;
          }
          if (merSettleStatictis1.getReserved1() != null
              && merSettleStatictis1.getReserved1().length() == 14) {
            reserved1Date = merSettleStatictis1.getReserved1().substring(0, 8);
            reserved1Time = merSettleStatictis1.getReserved1().substring(8, 14);
          }
          merSettleStatictis1.setSettleDate(settleDate);// 清算时间
          merSettleStatictis1.setSettleTime(settleTime);
          merSettleStatictis1.setReserved1(reserved1Date);// 结算时间
          merSettleStatictis1.setReserved1Time(reserved1Time);// 结算时间
          merSettleStatictis1.setClearDesc(statusMap.get(merSettleStatictis1.getClearStatus()));
          merSettleStatictis1.setT0MerRate(merSettleStatictis1.getD1MerRate());// 结算费率
          merSettleStatictis1.setReserved(merSettleStatictis1.getCountFee());// 结算手续费
          merSettleStatictis1.setT0MerGain(merSettleStatictis1.getD1MerGain());// 代理商分润
          merSettleStatictis1.setSettleType("D1");// 结算类型
          merSettleStatictisList1.add(merSettleStatictis1);
        }
      } else if ("-1".equals(settleType)) {// 如果只查T+1
        List<MerSettleStatictis> merSettleStatictisList = merSettleStatictisService
            .seleteMerSettleStatictisReport(map);
        Iterator<MerSettleStatictis> merSettleStatictisIt = merSettleStatictisList.iterator();
        while (merSettleStatictisIt.hasNext()) {
          MerSettleStatictis merSettleStatictis1 = (MerSettleStatictis) merSettleStatictisIt.next();
          String reserved1Date = null;
          String reserved1Time = null;
          String settleDate = null;
          String settleTime = null;
          if (merSettleStatictis1.getSettleDate() != null
              && merSettleStatictis1.getSettleDate().length() == 8) {
            settleDate = merSettleStatictis1.getSettleDate();
            settleTime = null;
          }
          if (merSettleStatictis1.getReserved1() != null
              && merSettleStatictis1.getReserved1().length() == 14) {
            reserved1Date = merSettleStatictis1.getReserved1().substring(0, 8);
            reserved1Time = merSettleStatictis1.getReserved1().substring(8, 14);
          }
          merSettleStatictis1.setClearDesc(statusMap.get(merSettleStatictis1.getClearStatus()));
          merSettleStatictis1.setSettleDate(settleDate);// 清算时间
          merSettleStatictis1.setSettleTime(settleTime);
          merSettleStatictis1.setReserved1(reserved1Date);// 结算时间
          merSettleStatictis1.setReserved1Time(reserved1Time);// 结算时间
          merSettleStatictis1.setSettleType("T1");// 结算类型
          merSettleStatictisList1.add(merSettleStatictis1);
        }
      } else {// 查询所有
        List<SubMerCashout> subMerCashoutList = subMerCashoutService.selectSubMerCashoutReport(map);
        // count +=
        // merSettleStatictisService.seleteMerSettleStatictisReportCount(map);
        Iterator<SubMerCashout> subMerCashoutIt = subMerCashoutList.iterator();
        MerSettleStatictis merSettleStatictis = null;
        while (subMerCashoutIt.hasNext()) {
          SubMerCashout subMerCashout = (SubMerCashout) subMerCashoutIt.next();
          merSettleStatictis = new MerSettleStatictis();
          merSettleStatictis.setMid(subMerCashout.getSubMerId());// 商户号
          String settleDate = null;
          String settleTime = null;
          String reserved1Date = null;
          String reserved1Time = null;
          if (subMerCashout.getFinishTime() != null && subMerCashout.getFinishTime().length() == 14) {
            settleDate = subMerCashout.getFinishTime().substring(0, 8);
            settleTime = subMerCashout.getFinishTime().substring(8, 14);
          }
          if (subMerCashout.getCreateTime() != null && subMerCashout.getCreateTime().length() == 14) {
            reserved1Date = subMerCashout.getCreateTime().substring(0, 8);
            reserved1Time = subMerCashout.getCreateTime().substring(8, 14);
          }
          merSettleStatictis.setSettleDate(settleDate);// 清算时间
          merSettleStatictis.setSettleTime(settleTime);
          merSettleStatictis.setReserved1(reserved1Date);// 结算时间
          merSettleStatictis.setReserved1Time(reserved1Time);// 结算时间
          merSettleStatictis.setId(subMerCashout.getId());// 交易流水号
          merSettleStatictis.setMerSysId(subMerCashout.getMerSysId());// 代理商号（机构号）
          merSettleStatictis.setClearAmt(subMerCashout.getOrderAmt());// 结算金额
          merSettleStatictis.setClearStatus(subMerCashout.getOrderStatus());// 结算状态
          merSettleStatictis.setClearDesc(statusMap1.get(subMerCashout.getOrderStatus()));
          merSettleStatictis.setBankName(subMerCashout.getBankName());// 银行
          merSettleStatictis.setAccountNum(subMerCashout.getSettAccountNo());// 卡号
          merSettleStatictis.setT0MerRate(subMerCashout.getT0MerRate());// 结算费率
          merSettleStatictis.setReserved(subMerCashout.getTransFee());// 结算手续费
          merSettleStatictis.setT0MerGain(subMerCashout.getT0MerGain());// 代理商分润
          merSettleStatictis.setMerProfitRate(subMerCashout.getMerProfitRate());// 代理商分润比
          merSettleStatictis.setSettleType("T0");// 结算类型
          merSettleStatictisList1.add(merSettleStatictis);
        }
        List<MerSettleStatictis> merSettleStatictisList = merSettleStatictisService
            .seleteMerSettleStatictisReport(map);
        // count +=
        // merSettleStatictisService.seleteMerSettleStatictisReportCount(map);
        Iterator<MerSettleStatictis> merSettleStatictisIt = merSettleStatictisList.iterator();
        while (merSettleStatictisIt.hasNext()) {
          MerSettleStatictis merSettleStatictis1 = (MerSettleStatictis) merSettleStatictisIt.next();
          String reserved1Date = null;
          String reserved1Time = null;
          String settleDate = null;
          String settleTime = null;
          if (merSettleStatictis1.getSettleDate() != null
              && merSettleStatictis1.getSettleDate().length() == 8) {
            settleDate = merSettleStatictis1.getSettleDate();
            settleTime = null;
          }
          if (merSettleStatictis1.getReserved1() != null
              && merSettleStatictis1.getReserved1().length() == 14) {
            reserved1Date = merSettleStatictis1.getReserved1().substring(0, 8);
            reserved1Time = merSettleStatictis1.getReserved1().substring(8, 14);
          }
          merSettleStatictis1.setClearDesc(statusMap.get(merSettleStatictis1.getClearStatus()));
          merSettleStatictis1.setSettleDate(settleDate);// 清算时间
          merSettleStatictis1.setSettleTime(settleTime);
          merSettleStatictis1.setReserved1(reserved1Date);// 结算时间
          merSettleStatictis1.setReserved1Time(reserved1Time);// 结算时间
          // 如果不为T+0且不为D+1
          if ((merSettleStatictis1.getCountFee() == null || "0".equals(merSettleStatictis1
              .getCountFee()))
              && (merSettleStatictis1.getReserved() == null || "0".equals(merSettleStatictis1
                  .getReserved()))) {
            merSettleStatictis1.setSettleType("T1");// 结算类型
            merSettleStatictisList1.add(merSettleStatictis1);
            // 如果为D+1
          } else if ((merSettleStatictis1.getCountFee() == null || "0".equals(merSettleStatictis1
              .getCountFee()))
              && (merSettleStatictis1.getReserved() != null && !"0".equals(merSettleStatictis1
                  .getReserved()))) {
            merSettleStatictis1.setSettleType("D1");// 结算类型
            merSettleStatictis1.setT0MerRate(merSettleStatictis1.getD1MerRate());// 结算费率
            merSettleStatictis1.setReserved(merSettleStatictis1.getCountFee());// 结算手续费
            merSettleStatictis1.setT0MerGain(merSettleStatictis1.getD1MerGain());// 代理商分润
            merSettleStatictisList1.add(merSettleStatictis1);
            // 如果为T+0
          } else if ((merSettleStatictis1.getCountFee() != null && !"0".equals(merSettleStatictis1
              .getCountFee()))
              && (merSettleStatictis1.getReserved() == null || "0".equals(merSettleStatictis1
                  .getReserved()))) {
            merSettleStatictis1.setSettleType("T0");// 结算类型
            merSettleStatictisList1.add(merSettleStatictis1);
          }
          // 如果为T+0 + D+1
          else if (merSettleStatictis1.getCountFee() != null
              && !"0".equals(merSettleStatictis1.getCountFee())
              && merSettleStatictis1.getReserved() != null
              && !"0".equals(merSettleStatictis1.getReserved())) {
            merSettleStatictis1.setSettleType("T0");// 结算类型
            merSettleStatictisList1.add(merSettleStatictis1);
            merSettleStatictis1.setSettleType("D1");// 结算类型
            merSettleStatictis1.setT0MerRate(merSettleStatictis1.getD1MerRate());// 结算费率
            merSettleStatictis1.setReserved(merSettleStatictis1.getCountFee());// 结算手续费
            merSettleStatictis1.setT0MerGain(merSettleStatictis1.getD1MerGain());// 代理商分润
            merSettleStatictisList1.add(merSettleStatictis1);
            // count+=1;
          }
        }
      }
      if (merSettleStatictisList1.size() != 0) {
        HttpServletResponse response = getResponse();
        response.reset();// 清除缓存
        // 设置下载类型
        response.setContentType("application/x-download");
        try {
          response.setHeader("Content-Disposition",
              "attachment;filename=" + URLEncoder.encode("结算报表", "UTF-8") + ".xls");
          ImportExcelFile.ImportExcel(merSettleStatictisList1, response.getOutputStream(), mapfile,
              "结算报表", null);
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        getRequest().setAttribute("succ", "fone");
      }
      // JSONArray array = JSONArray.fromObject(merSettleStatictisList1);
      // JSONObject object = new JSONObject();
      // object.put("Rows", array.toString());
      // object.put("Total", count);
      // merSettleStatictisListJson = object.toString();
      // System.out.println(merSettleStatictisListJson);
      // getResponse().getWriter().write(merSettleStatictisListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "selectReoprt";
  }

  public String checkXls(){
	  System.out.println(settleDate);
	  if(settleDate==null){
		  return "checkXls";
	  }
	  String date = settleDate.substring(0,10);
	  log.info("对账日期为:"+date);
//	  String uploadPath = rb.getString("ExcelUrl")+"/checkStatic/";
	  //上传文件
//	  uploadTxt(date,uploadPath);
	  String uploadPath = rb.getString("ExcelUrl")+"/pos/check/"+date+"/";
      //上传文件
	  File uploadfile = new File(uploadPath);
      if (!uploadfile.exists() && !uploadfile.isDirectory()) {
          // 不存在
          uploadfile.mkdirs();
      }
      String suffix=".txt";
          try {
            FileUtils.copyFile(xlsFile, new File(uploadPath+date+suffix));
        } catch (IOException e) {
            e.printStackTrace();
        }
//   uploadXls(date,uploadPath);
	  //写入txt
//	  writeTxt(date,uploadPath);
	  //读取txt
//	  readTxt(date);
	  readNewTxt(date);
//	  String uploadPath = rb.getString("ExcelUrl")+"/checkStatic/";
//	  File txt = new File(uploadPath+setDate+"check.txt");
//	  try {
//		  // 以流的形式下载文件。
//	      InputStream fis = new BufferedInputStream(new FileInputStream(txt));
//	      byte[] buffer = new byte[fis.available()];
//	      fis.read(buffer);
//	      fis.close();
//	      // 清空response
//		  HttpServletResponse response = getResponse();
//		  // 清空response
//	      response.reset();
//	      // 设置response的Header
//	      response.addHeader("Content-Disposition", "attachment;filename=" + new String(txt.getName().getBytes()));
//	      response.addHeader("Content-Length", "" + txt.length());
//	      OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//	      response.setContentType("text/plain");
////	      response.setContentType("text/html");
//	      toClient.write(buffer);
//	      toClient.flush();
//	      toClient.close();
//	} catch (Exception e) {
//		e.printStackTrace();
//	}
	  return "checkXls";
  }
  
  
  public void downloadPosFile(){
      System.out.println(settleDate);
      String name="";
      switch (Integer.parseInt(chName)) {
          case 1:
              name="ShangLianChu";
          case 2:
              name="DeYi";
          default:
              break;
      }
      String realPath =rb.getString("ExcelUrl")+"/pos/"+settleDate+"/";
      File txt = new File(realPath+name+settleDate+".xlsx");
      try {
          // 以流的形式下载文件。
          InputStream fis = new BufferedInputStream(new FileInputStream(txt));
          byte[] buffer = new byte[fis.available()];
          fis.read(buffer);
          fis.close();
          // 清空response
          HttpServletResponse response = getResponse();
          // 清空response
          response.reset();
          // 设置response的Header
          response.addHeader("Content-Disposition", "attachment;filename=" + new String(txt.getName().getBytes()));
          response.addHeader("Content-Length", "" + txt.length());
          OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
          response.setContentType("application/x-xls");
//        response.setContentType("text/html");
          toClient.write(buffer);
          toClient.flush();
          toClient.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  
  //Pos对账
  public String checkPos(){
      System.out.println(settleDate);
      if(settleDate==null){
          return "checkPos";
      }
      String date = settleDate;
      log.info("对账日期为:"+date);
      String uploadPath = rb.getString("ExcelUrl")+"/pos/check/"+date+"/";
      //上传文件
//   uploadXls(date,uploadPath);
      uploadFile(date, uploadPath, fileType);
      //写入txt
//      writeTxt(date,uploadPath);
      //读取txt
      switch (Integer.parseInt(chName)) {
        case 1:
            readSLC(date);
        case 2:
            readDY(date);
        default:
            break;
    }
      
//      readTxt(date);
      return "checkPos";
  }
  
  //商联储对账
  public void readSLC(String date){
      String uploadPath =rb.getString("ExcelUrl")+"/pos/check/"+date+"/";
      File txt = new File(uploadPath+date+".txt");
      String setDate = date.replaceAll("-", "");
      String encoding="GBK";
      //对平集合
      Map<String,List<SubMerInfo>> checkMap = new HashMap<String,List<SubMerInfo>>();
      //对平集合
      List<SubMerInfo> okCheckList = new ArrayList<SubMerInfo>();
      //单边集合
      List<SubMerInfo> singleCheckList = new ArrayList<SubMerInfo>();
      //对账不平集合
      List<SubMerInfo> unCheckList = new ArrayList<SubMerInfo>();
      Map paramMap = new HashMap();
      paramMap.put("transMerId","802%");
      paramMap.put("settleDate",date.substring(4));
      paramMap.put("intxnDt",date.substring(0,4)+"%");
      paramMap.put("tradeCode","000000");
      List<SubMerInfo> subMerInfoList = subMerInfoDao.selectCoreTransLogByCh(paramMap);
      //先根据chtrackingNo分开 第三方流水对账
      Map<String,SubMerInfo> subMap = new HashMap<String,SubMerInfo>();
      for(SubMerInfo sub:subMerInfoList){
          subMap.put(sub.getChTrackingNo(), sub);
      }
      try{
          if(txt.isFile() && txt.exists()){ //判断文件是否存在
              InputStreamReader read = new InputStreamReader(
              new FileInputStream(txt),encoding);//考虑到编码格式
              BufferedReader bufferedReader = new BufferedReader(read);
              String lineTxt = null;
              StringBuffer sb = new StringBuffer();
              int i=0;
              SubMerInfo nowSub;
              while((lineTxt = bufferedReader.readLine()) != null){
                  if(i==0){     //第一行不取
                      i=1;
                      continue;
                  }
                  if(lineTxt.trim().equals("")){
                      continue;
                  }
                  System.out.println(lineTxt);
                  sb = new StringBuffer(lineTxt);
                  String merId=sb.substring(0, sb.indexOf("|")).trim();
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String chTrackingNo = sb.substring(0, sb.indexOf("|")).trim();            //渠道流水
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String transDate = sb.substring(0, sb.indexOf("|")).trim();
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String transTime = sb.substring(0, sb.indexOf("|")).trim();
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String transType = sb.substring(0, sb.indexOf("|")).trim();
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String transAmt = sb.substring(0, sb.indexOf("|")).trim();        //交易金额单位元
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String cardNo = sb.substring(0, sb.indexOf("|")).trim();      
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  String chMerId = sb.substring(0, sb.indexOf("|")).trim();      
                  sb = sb.delete(0, sb.indexOf("|")+1);
                  nowSub = subMap.get(chTrackingNo);
                  //如果找不到订单属于单边
                  if(nowSub==null){
                     nowSub = new SubMerInfo();
                        nowSub.setChTrackingNo(chTrackingNo);
                        nowSub.setChMerId(chMerId);
                        nowSub.setSettAccountNo(cardNo);
                        nowSub.setTransAmt(transAmt);
                        singleCheckList.add(nowSub);
                      continue;
                  }
                  //有则对账
                  if(nowSub.getTransAmt().equals(transAmt)){
                      List<SubMerInfo> subList = checkMap.get(nowSub.getSubMerId());
                      if(subList==null){
                          subList = new ArrayList<SubMerInfo>();
                          subList.add(nowSub);
                          checkMap.put(nowSub.getSubMerId(), subList);
                      }else{
                          subList.add(nowSub);
                          checkMap.put(nowSub.getSubMerId(), subList);
                      }
                  }else{
                      //处理对不平
                      unCheckList.add(nowSub);
                  }
                  
              }
              read.close();
          }
          //生成对账文件
//          createCheckXls("ShangLianChu",date,checkMap,singleCheckList,unCheckList);
      }catch(Exception e){
          e.printStackTrace();
      }
  }

  public void readDY(String date){
      String uploadPath =rb.getString("ExcelUrl")+"/pos/check/"+date+"/";
      File txt = new File(uploadPath+date+".txt");
      String encoding="GBK";
      //打款集合
      Map<String,List<SubMerInfo>> checkMap = new HashMap<String,List<SubMerInfo>>();
      //对平集合
      List<SubMerInfo> okCheckList = new ArrayList<SubMerInfo>();
      //单边集合
      List<SubMerInfo> singleCheckList = new ArrayList<SubMerInfo>();
      //对账不平集合
      List<SubMerInfo> unCheckList = new ArrayList<SubMerInfo>();
      Map paramMap = new HashMap();
      paramMap.put("transMerId","850%");
      paramMap.put("settleDate",date.substring(4));
      paramMap.put("intxnDt",date.substring(0,4)+"%");
      paramMap.put("tradeCode","'000000','200000'");
      List<SubMerInfo> subMerInfoList = subMerInfoDao.selectCoreTransLogByCh(paramMap);
      //先根据chtrackingNo分开 第三方流水对账
      Map<String,SubMerInfo> subMap = new HashMap<String,SubMerInfo>();
      for(SubMerInfo sub:subMerInfoList){
          subMap.put(sub.getChMerId()+sub.getReferenceNo(), sub);
      }
      try{
          if(txt.isFile() && txt.exists()){ //判断文件是否存在
              InputStreamReader read = new InputStreamReader(
              new FileInputStream(txt),encoding);//考虑到编码格式
              BufferedReader bufferedReader = new BufferedReader(read);
              String lineTxt = null;
              int i=0;
              SubMerInfo nowSub;
              while((lineTxt = bufferedReader.readLine()) != null){
                  if(i==0){     //第一行不取
                      i=1;
                      continue;
                  }
                  if(lineTxt.trim().equals("")){
                      continue;
                  }
                  System.out.println(lineTxt);
                  String line[] = lineTxt.split("\\,");
                  String chMerId=line[0].trim();
                  String transDt=line[1].trim();
                  String transTm=line[2].trim();
                  String settleDt=line[3].trim();
                  String termId=line[4].trim();
                  String transType = line[5].trim();
                  String chTrackingNo = line[6].trim(); //渠道流水
                  String cardNo = line[7].trim(); //卡号
                  String cardType = line[8].trim(); 
                  String transAmt = line[9].trim(); 
                  String referenceNo = line[14].trim(); 
                  nowSub = subMap.get(chMerId+referenceNo);
                  //如果找不到订单属于单边
                  if(nowSub==null ){
                      nowSub = new SubMerInfo();
                      nowSub.setChTrackingNo(chTrackingNo);
                      nowSub.setReferenceNo(referenceNo);
                      nowSub.setChMerId(chMerId);
                      nowSub.setSettAccountNo(cardNo);
                      if(transType.equals("消费")){
                          nowSub.setTradeCode("000000");
                      }else if(transType.equals("消费撤销")){
                          nowSub.setTradeCode("200000");
                      }
                      nowSub.setTransAmt(transAmt);
                      singleCheckList.add(nowSub);
                      continue;
                  }
                  //有则对账
                  if(transType.equals("消费")){
                      if(new BigDecimal(nowSub.getTransAmt()).compareTo(new BigDecimal(transAmt))==0 && nowSub.getTradeCode().equals("000000")){
                          okCheckList.add(nowSub);
                      }else{
                          //处理对不平
                          unCheckList.add(nowSub);
                      }
                  }else if(transType.equals("消费撤销")){
                      //消费撤销 金额是 负的 取绝对值比较
                      transAmt = transAmt.replace("-", "");
                      if(new BigDecimal(nowSub.getTransAmt()).compareTo(new BigDecimal(transAmt))==0 && nowSub.getTradeCode().equals("200000")){
                          okCheckList.add(nowSub);
                      }else{
                          //处理对不平
                          unCheckList.add(nowSub);
                      }
                  }
                  
              }
              read.close();
          }
          //将对账平转换为打款文件
          for(SubMerInfo sub :okCheckList){
              List<SubMerInfo> list = checkMap.get(sub.getSubMerId());
              if(list==null){
                  list = new ArrayList<SubMerInfo>();
                  list.add(sub);
                  checkMap.put(sub.getSubMerId(), list);
              }else{
                  list.add(sub);
              }
          }
          //生成对账文件
          createCheckXls("DeYi",date,checkMap,okCheckList,singleCheckList,unCheckList);
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  
  /**
   * 
   * @Title:        createCheckXls 
   * @Description:  
   * @param:        @param chName   渠道名
   * @param:        @param date         日期
   * @param:        @param checkMap 对平
   * @param:        @param SingleCheckList  单边
   * @param:        @param unCheckList      不平
   * @return:       void    
   * @throws 
   * @author        Eason Jiang
   * @Date          2016-11-16 上午11:00:40
   */
  public void createCheckXls(String chName,String date,Map<String,List<SubMerInfo>> checkMap,List<SubMerInfo> okCheckList,List<SubMerInfo> singleCheckList,List<SubMerInfo> unCheckList   ){
//      List<RegionCode> regionCodeList = regionCodeDao.selectRegionCodeListAll();
      XSSFWorkbook wb = new XSSFWorkbook();
      XSSFFont font = wb.createFont();// excel字体
      XSSFRichTextString value = new XSSFRichTextString("");
      value.applyFont(font);
      XSSFCellStyle style = wb.createCellStyle();// 单元样式
      style.setBorderBottom(XSSFCellStyle.BORDER_THIN);// bottom的样式
      style.setBorderTop(XSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
      style.setBorderRight(XSSFCellStyle.BORDER_THIN);
      style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
      style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
      // 颜色
      // http://xiaohewoai.iteye.com/blog/1300817
      style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
      style.setFillPattern(CellStyle.SOLID_FOREGROUND);

      // font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
      font.setFontName("宋体");
      font.setFontHeightInPoints((short) 11);// 设置字体大小
      //对账平SHEET
      XSSFSheet okSheet = wb.createSheet("对账平");// 创建excel sheet
      XSSFRow row = okSheet.createRow(0);// excel行
      String[] columNames = {"渠道流水","渠道编号","消费类型","银行账号","金额","检索参考号"};
      XSSFCell cell = row.createCell(0);
      value.applyFont(font);
      for( int i = 0; i < columNames.length; i++ ){
          okSheet.setColumnWidth(i, (15 * 256));
          value = new XSSFRichTextString(columNames[i]);
          value.applyFont(font);
          cell = row.createCell(i);
          cell.setCellValue(value);
          cell.setCellStyle(style);
      }
      int line = 1;
      for(SubMerInfo okSub:okCheckList){
          row = okSheet.createRow(line++);// excel行
          row.createCell(0).setCellValue(new XSSFRichTextString(okSub.getChTrackingNo()));
          row.createCell(1).setCellValue(new XSSFRichTextString(okSub.getChMerId()));
          row.createCell(2).setCellValue(new XSSFRichTextString(okSub.getTradeCode()));
          row.createCell(3).setCellValue(new XSSFRichTextString(okSub.getCardNo()));
          row.createCell(4).setCellValue(new XSSFRichTextString(okSub.getTransAmt()));
          row.createCell(5).setCellValue(new XSSFRichTextString(okSub.getReferenceNo()));
      }
      //对账不平SHEET
      XSSFSheet unSheet = wb.createSheet("对账不平");// 创建excel sheet
      row = unSheet.createRow(0);// excel行
      cell = row.createCell(0);
      value.applyFont(font);
      for( int i = 0; i < columNames.length; i++ ){
          unSheet.setColumnWidth(i, (15 * 256));
          value = new XSSFRichTextString(columNames[i]);
          value.applyFont(font);
          cell = row.createCell(i);
          cell.setCellValue(value);
          cell.setCellStyle(style);
      }
      line = 1;
      for(SubMerInfo unSub:unCheckList){
          row = unSheet.createRow(line++);// excel行
          row.createCell(0).setCellValue(new XSSFRichTextString(unSub.getChTrackingNo()));
          row.createCell(1).setCellValue(new XSSFRichTextString(unSub.getChMerId()));
          row.createCell(2).setCellValue(new XSSFRichTextString(unSub.getTradeCode()));
          row.createCell(3).setCellValue(new XSSFRichTextString(unSub.getSettAccountNo()));
          row.createCell(4).setCellValue(new XSSFRichTextString(unSub.getTransAmt()));
          row.createCell(5).setCellValue(new XSSFRichTextString(unSub.getReferenceNo()));

      }
      //对账单边SHEET
      XSSFSheet singleSheet = wb.createSheet("对账单边");// 创建excel sheet
      row = singleSheet.createRow(0);// excel行
      cell = row.createCell(0);
      value.applyFont(font);
      for( int i = 0; i < columNames.length; i++ ){
          singleSheet.setColumnWidth(i, (15 * 256));
          value = new XSSFRichTextString(columNames[i]);
          value.applyFont(font);
          cell = row.createCell(i);
          cell.setCellValue(value);
          cell.setCellStyle(style);
      }
      line = 1;
      for(SubMerInfo singleSub:singleCheckList){
          row = singleSheet.createRow(line++);// excel行
          row.createCell(0).setCellValue(new XSSFRichTextString(singleSub.getChTrackingNo()));
          row.createCell(1).setCellValue(new XSSFRichTextString(singleSub.getChMerId()));
          row.createCell(2).setCellValue(new XSSFRichTextString(singleSub.getTradeCode()));
          row.createCell(3).setCellValue(new XSSFRichTextString(singleSub.getSettAccountNo()));
          row.createCell(4).setCellValue(new XSSFRichTextString(singleSub.getTransAmt()));
          row.createCell(5).setCellValue(new XSSFRichTextString(singleSub.getReferenceNo()));
      }
      //打款文件
      XSSFSheet checkSheet = wb.createSheet("打款文件");// 创建excel sheet
      row = checkSheet.createRow(0);// excel行
      String[] checkColumn = {"明细编号","收款方客户账号","收款方账户名称","金额(分)","收款方开户行名称","收款方联行号","出款通道","用途","商户号","笔数","消费金额","撤销金额"};
      cell = row.createCell(0);
      value.applyFont(font);
      for( int i = 0; i < checkColumn.length; i++ ){
          checkSheet.setColumnWidth(i, (15 * 256));
          value = new XSSFRichTextString(checkColumn[i]);
          value.applyFont(font);
          cell = row.createCell(i);
          cell.setCellValue(value);
          cell.setCellStyle(style);
      }
      line = 1;
      Set keySet = checkMap.keySet();
      Iterator<String> iterator = keySet.iterator();
      int linNum=1;     //编号
      SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
      String num = "ZZB"+sdf.format(new Date());
      sdf = new SimpleDateFormat("yyyyMMdd");
      String ymd = sdf.format(new Date());
      sdf = new SimpleDateFormat("HHmm");
      String time = sdf.format(new Date());
      while(iterator.hasNext()){
          row = checkSheet.createRow(line++);// excel行
          String subMerId = iterator.next();
          BigDecimal money = new BigDecimal(0);             //交易金额
          BigDecimal clearMoney = new BigDecimal(0);        //结算金额
          BigDecimal cancelMoney = new BigDecimal(0);        //撤销金额
          int transCount = 0;
          List<SubMerInfo> subList = checkMap.get(subMerId);
          Map<String,BigDecimal> transMap = new HashMap<String,BigDecimal>();            //消费结算金额集合
          for(SubMerInfo sub:subList){
              if(sub.getTradeCode().equals("000000")){
                  money =   money.add(new BigDecimal(sub.getTransAmt()));
                  transMap.put(sub.getTrackingNo(), new BigDecimal(sub.getMerchantSettle()));           //消费成功 添加结算金额 以备撤销
                  clearMoney = clearMoney.add(new BigDecimal(sub.getMerchantSettle()));
              }else if(sub.getTradeCode().equals("200000")){
                  //撤销需要减少金额
                  cancelMoney =   cancelMoney.add(new BigDecimal(sub.getTransAmt()));
              }
              transCount++;
          }
          row.createCell(0).setCellValue(new XSSFRichTextString(num+leftAddZero(linNum++, 5)));        //编号
          row.createCell(1).setCellValue(new XSSFRichTextString(subList.get(0).getSettAccountNo()));        //收款方客户账号
          row.createCell(2).setCellValue(new XSSFRichTextString(subList.get(0).getSettAccountName()));        //收款方账户名称
          row.createCell(3).setCellValue(new XSSFRichTextString(clearMoney.multiply(new BigDecimal(100)).setScale(0).toString()));        //打款金额(分)
          String bankName="";
          //如果没有逗号
          if(subList.get(0).getSettAgency().indexOf(",")==-1){
              bankName=getBankNameByCode(subList.get(0).getSettAgency());
          }else{
              bankName=subList.get(0).getSettAgency();
          }
          row.createCell(4).setCellValue(new XSSFRichTextString(bankName+subList.get(0).getOpenBank()));
          row.createCell(5).setCellValue(new XSSFRichTextString(subList.get(0).getLineNum()));
          row.createCell(6).setCellValue(new XSSFRichTextString("MSDF"));
          row.createCell(7).setCellValue(new XSSFRichTextString(ymd+"至尊宝代付"+time));           //用途
          row.createCell(8).setCellValue(new XSSFRichTextString(subMerId));           //商户号
          row.createCell(9).setCellValue(new XSSFRichTextString(String.valueOf(transCount)));        
          row.createCell(10).setCellValue(new XSSFRichTextString(money.multiply(new BigDecimal(100)).setScale(0).toString()));          
          row.createCell(11).setCellValue(new XSSFRichTextString(cancelMoney.multiply(new BigDecimal(100)).setScale(0).toString()));          
      }
      FileOutputStream outputStream = null;
      try{
          //生成文件
          String realPath =rb.getString("ExcelUrl")+"/pos/"+date+"/";
          File uploadfile = new File(realPath);
          if (!uploadfile.exists() && !uploadfile.isDirectory()) {
              // 不存在
              uploadfile.mkdirs();
          }
          outputStream = new FileOutputStream(realPath+chName+date+".xlsx");
          wb.write(outputStream);
          outputStream.flush();
          outputStream.close();
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  
  /**
   * 左补零
   * @Title:        leftAddZero 
   * @Description:  
   * @param:        @return    
   * @return:       String    
   * @throws 
   * @author        Eason Jiang
   * @Date          2016-12-6 上午11:52:42
   */
  public String leftAddZero(int value,int length){
      String v = String.valueOf(value);
      String msg="";
      for(int i =0;i<length-v.length();i++){
          msg+="0";
      }
      return msg+value;
  }
  
  private String getBankNameByCode(String bankCode){
      
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
  
  public void downloadCheckXls(){
	  System.out.println(settleDate);
	  String date = settleDate.substring(0,10);
	  String setDate = date.replaceAll("-", "");
	  String uploadPath = rb.getString("ExcelUrl")+"/checkStatic/";
	  File txt = new File(uploadPath+setDate+"check.txt");
	  try {
		  // 以流的形式下载文件。
	      InputStream fis = new BufferedInputStream(new FileInputStream(txt));
	      byte[] buffer = new byte[fis.available()];
	      fis.read(buffer);
	      fis.close();
	      // 清空response
		  HttpServletResponse response = getResponse();
		  // 清空response
	      response.reset();
	      // 设置response的Header
	      response.addHeader("Content-Disposition", "attachment;filename=" + new String(txt.getName().getBytes()));
	      response.addHeader("Content-Length", "" + txt.length());
	      OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
	      response.setContentType("text/plain");
//	      response.setContentType("text/html");
	      toClient.write(buffer);
	      toClient.flush();
	      toClient.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
  }
  
  public void uploadXls(String date,String uploadPath){
		
		// 判断文件夹是否在，不存在就创建
			File uploadfile = new File(uploadPath);
			if (!uploadfile.exists() && !uploadfile.isDirectory()) {
				// 不存在
				uploadfile.mkdirs();
			}
			InputStream is;
			try {
				is = new FileInputStream(xlsFile);
	        OutputStream os = new FileOutputStream(new File(uploadPath+date+".xls"));
	        byte[] buffer = new byte[1024];
	        int length = 0;
	        while(-1 != (length = is.read(buffer, 0, buffer.length)))
	        {
	            os.write(buffer);
	        }
	        os.close();
	        is.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
  }
  
  public void uploadTxt(String date,String uploadPath){
      
      // 判断文件夹是否在，不存在就创建
          File uploadfile = new File(uploadPath);
          if (!uploadfile.exists() && !uploadfile.isDirectory()) {
              // 不存在
              uploadfile.mkdirs();
          }
          InputStream is;
          try {
              is = new FileInputStream(xlsFile);
          OutputStream os = new FileOutputStream(new File(uploadPath+date+".txt"));
          byte[] buffer = new byte[8192];
          int length = 0;
          while(-1 != (length = is.read(buffer, 0, buffer.length)))
          {
              os.write(buffer);
          }
          os.close();
          is.close();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }catch (Exception e) {
              e.printStackTrace();
          }
}
  
  public void uploadFile(String date,String uploadPath,String fileType){
      
      // 判断文件夹是否在，不存在就创建
          File uploadfile = new File(uploadPath);
          if (!uploadfile.exists() && !uploadfile.isDirectory()) {
              // 不存在
              uploadfile.mkdirs();
          }
          String suffix="";
          if("1".equals(fileType)){
              suffix=".xls";
          }else if("2".equals(fileType)){
              suffix=".txt";
          }
//          InputStream is;
          try {
              FileUtils.copyFile(xlsFile, new File(uploadPath+date+suffix));
//              is = new FileInputStream(xlsFile);
//          OutputStream os = new FileOutputStream(new File(uploadPath+date+suffix));
//          byte[] buffer = new byte[1024];
//          int length = 0;
//          while(-1 != (length = is.read(buffer, 0, buffer.length)))
//          {
//              os.write(buffer);
//          }
//          os.close();
//          is.close();
          } catch (FileNotFoundException e) {
              e.printStackTrace();
          }catch (Exception e) {
              e.printStackTrace();
          }
          if("1".equals(fileType)){
                 //xls文件需要转换TXT
              writeTxt(date,uploadPath);
          }
}
  
  public void writeTxt(String date,String uploadPath){
	  File txt = new File(uploadPath+date+".txt");
	  StringBuffer sb = readFile(uploadPath+date+".xls");
	  //将文件写入txt以便于逐行读取
	  try{
			FileWriter fw = new FileWriter(txt);
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(sb.toString());
			bw.flush();
			fw.flush();
			bw.close();
			fw.close();
			}catch(Exception e){
				e.printStackTrace();
			}
  }
  
  public void readTxt(String date){
	  String uploadPath = rb.getString("ExcelUrl")+"/checkStatic/";
	  File txt = new File(uploadPath+date+".txt");
	  String setDate = date.replaceAll("-", "");
	  String encoding="GBK";
	  String unChecked = "";	//对账不平
	  try{
	      if(txt.isFile() && txt.exists()){ //判断文件是否存在
	          InputStreamReader read = new InputStreamReader(
	          new FileInputStream(txt),encoding);//考虑到编码格式
	          BufferedReader bufferedReader = new BufferedReader(read);
	          String lineTxt = null;
	          StringBuffer sb = new StringBuffer();
	          int i=0;
	          while((lineTxt = bufferedReader.readLine()) != null){
	        	  if(i==0){		//第一行不取
	        		  i=1;
	        		  continue;
	        	  }
	        	  if(lineTxt.trim().equals("")){
	        		  continue;
	        	  }
	              System.out.println(lineTxt);
	              sb = new StringBuffer(lineTxt);
	              String transCode=sb.substring(0, sb.indexOf("|")).trim();
				  sb = sb.delete(0, sb.indexOf("|")+1);
				  String terminalOrderNo = sb.substring(0, sb.indexOf("|")).trim();
				  sb = sb.delete(0, sb.indexOf("|")+1);
				  String transMerNo = sb.substring(0, sb.indexOf("|")).trim();
				  sb = sb.delete(0, sb.indexOf("|")+1);
				  String transTime = sb.substring(0, sb.indexOf("|")).trim();
				  sb = sb.delete(0, sb.indexOf("|")+1);
				  String cardNo = sb.substring(0, sb.indexOf("|")).trim();
				  sb = sb.delete(0, sb.indexOf("|")+1);
				  //去掉小数点
				  String merAmt = sb.substring(0, sb.indexOf("|")).trim().replace(".", "");
				  sb = sb.delete(0, sb.indexOf("|")+1);
				  OrderStatictis orderStatictis = orderStatictisDao.selectbyRight(terminalOrderNo);
				  if(orderStatictis==null){
					  log.info("找打不到清算:"+terminalOrderNo);
					  OrderInfo orderInfo = orderInfoDao.selectbyRight(terminalOrderNo);
					  if(orderInfo==null){
						  log.info("找打不到交易:"+terminalOrderNo);
						  unChecked+=lineTxt;
						  unChecked+="\r\n";
					  }else if(!orderInfo.getMerAmt().equals(merAmt)){
						  log.info("对账不平:"+terminalOrderNo);
						  log.info("本地金额:"+orderInfo.getMerAmt());
						  log.info("文件金额:"+merAmt);
						  unChecked+=lineTxt;
						  unChecked+="\r\n";
					  }else if(!orderInfo.getRespCode().equals("00")){
						  log.info("本地失败:"+terminalOrderNo);
						  log.info("本地金额:"+orderInfo.getMerAmt());
						  log.info("文件金额:"+merAmt);
						  unChecked+=lineTxt;
						  unChecked+="\r\n";
					  }
				  }else{
					  if(orderStatictis.getMerAmt().equals(merAmt)){
						  orderStatictis.setCheckStatus("1");		//平
					  }else{
						  log.info("对账不平:"+terminalOrderNo);
						  log.info("本地金额:"+orderStatictis.getMerAmt());
						  log.info("文件金额:"+merAmt);
						  orderStatictis.setCheckStatus("2");		//不平
					  }
				  }
				  orderStatictisDao.updateOrderStatus(orderStatictis);
	          }
	          read.close();
	      }
	      //生成对账文件
	      writeCheckTxt(setDate,unChecked);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }
  
  public void readNewTxt(String date){
//      String uploadPath = rb.getString("ExcelUrl")+"/checkStatic/";
      String uploadPath = rb.getString("ExcelUrl")+"/pos/check/"+date+"/";
      File txt = new File(uploadPath+date+".txt");
//      File txt = xlsFile;
      String encoding="GBK";
      XSSFWorkbook wb = new XSSFWorkbook();
      XSSFFont font = wb.createFont();// excel字体
      XSSFRichTextString value = new XSSFRichTextString("");
      value.applyFont(font);
      XSSFCellStyle style = wb.createCellStyle();// 单元样式
      style.setBorderBottom(XSSFCellStyle.BORDER_THIN);// bottom的样式
      style.setBorderTop(XSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
      style.setBorderRight(XSSFCellStyle.BORDER_THIN);
      style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
      style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);// 垂直
      // 颜色
      // http://xiaohewoai.iteye.com/blog/1300817
      style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
      style.setFillPattern(CellStyle.SOLID_FOREGROUND);

      // font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
      font.setFontName("宋体");
      font.setFontHeightInPoints((short) 11);// 设置字体大小
      String[] columNames = {"商户号","终端号","渠道流水","渠道商户号","渠道终端号","消费类型","银行账号","金额","交易日期","交易时间","结算日期","检索参考号","卡类型","卡手续费"};
      
      
      try{
          if(txt.isFile() && txt.exists()){ //判断文件是否存在
              InputStreamReader read = new InputStreamReader(
              new FileInputStream(txt),encoding);//考虑到编码格式
              BufferedReader bufferedReader = new BufferedReader(read);
              String lineTxt = null;
              int txtLineNum=0;
              CoreTransLog coreTransLog = new CoreTransLog();
              Map<String,CoreTransLog> allMap = getList(date);
              //记录单边
              StringBuffer singBuffer = new StringBuffer();
              XSSFRow row;
              while((lineTxt = bufferedReader.readLine()) != null){
                  if(txtLineNum==0){     //第一行不取
                      txtLineNum=1;
                      continue;
                  }
                  if(lineTxt.trim().equals("")){
                      continue;
                  }
                  System.out.println(lineTxt);
                  String[] text = lineTxt.split(",");
                  String chMerId = text[0];
                  String settleDt = text[3].substring(4);
                  String chTermId = text[4];
                  String transType = text[5];
                  String transId = text[6];
                  String cardNo = text[7];
                  String cardType = text[8];
                  String transAmt = text[9];
                  String referenceNo = text[14];
                  
                  coreTransLog = allMap.get(chMerId+chTermId+referenceNo);
//                  coreTransLog.setChMerId(chMerId);
//                  coreTransLog.setChTermId(chTermId);
//                  coreTransLog.setReferenceNo(referenceNo);
//                  coreTransLog.setSettleDt(settleDt);
//                  coreTransLog = coreTransLogDao.selectInfo(coreTransLog);
                  if(coreTransLog==null){
                      singBuffer.append(lineTxt);
                      singBuffer.append("\r\n");
                      continue;
                  }
                  log.info("core="+JSONObject.fromObject(coreTransLog));
                  String transSource = coreTransLog.getTransSource();
                  XSSFSheet sheet = wb.getSheet(transSource);
                  if(sheet==null){
                      sheet = wb.createSheet(transSource);// 创建excel sheet
                      row = sheet.createRow(0);// excel行
                      XSSFCell cell = row.createCell(0);
                      value.applyFont(font);
                      for( int i = 0; i < columNames.length; i++ ){
                          sheet.setColumnWidth(i, (15 * 256));
                          value = new XSSFRichTextString(columNames[i]);
                          value.applyFont(font);
                          cell = row.createCell(i);
                          cell.setCellValue(value);
                          cell.setCellStyle(style);
                      }
                  }
                  //开始写入
                  row = sheet.createRow(sheet.getLastRowNum()+1);
                  row.createCell(0).setCellValue(new XSSFRichTextString(coreTransLog.getMerId()));         
                  row.createCell(1).setCellValue(new XSSFRichTextString(coreTransLog.getTermId()));         
                  row.createCell(2).setCellValue(new XSSFRichTextString(transId));         
                  row.createCell(3).setCellValue(new XSSFRichTextString(chMerId));         
                  row.createCell(4).setCellValue(new XSSFRichTextString(chTermId));         
                  row.createCell(5).setCellValue(new XSSFRichTextString(transType));         
                  row.createCell(6).setCellValue(new XSSFRichTextString(cardNo));         
                  row.createCell(7).setCellValue(new XSSFRichTextString(transAmt));         
                  row.createCell(8).setCellValue(new XSSFRichTextString(coreTransLog.getIntTxnDt()));         
                  row.createCell(9).setCellValue(new XSSFRichTextString(coreTransLog.getIntTxnTm()));         
                  row.createCell(10).setCellValue(new XSSFRichTextString(coreTransLog.getSettleDt()));         
                  row.createCell(11).setCellValue(new XSSFRichTextString(referenceNo));         
                  row.createCell(12).setCellValue(new XSSFRichTextString(cardType));         
                  /*吴海泉  15:40:45
                  卡类型
                  贷记卡、准贷记卡   交易金额*0.51%   保留两位小数
                  借记卡  云闪付借记卡  交易金额*0.42%  大于20，取20 ，小于20  取交易金额*0.42%
                  加一列手续费*/
                  BigDecimal amount = new BigDecimal(transAmt);
                  BigDecimal transFee = new BigDecimal(0);
                  String fee = "";
                  if(cardType.indexOf("贷")!=-1){        //贷记卡
                      transFee = new BigDecimal(0.0051);
                      fee = String.valueOf(mul(amount.doubleValue(), transFee.doubleValue()));
                  }else{
                      transFee = new BigDecimal(0.0042);
                      fee = String.valueOf(mul(amount.doubleValue(), transFee.doubleValue()));
                      if(new BigDecimal(fee).compareTo(new BigDecimal(20))>0){
                          fee="20";
                      }
                  }
                  row.createCell(13).setCellValue(new XSSFRichTextString(fee));    
                  
              }
              log.info("完成写入文件");
              read.close();
              log.info("不平="+singBuffer.toString());
          }
          FileOutputStream outputStream = null;
          try{
              //生成文件
              String realPath =rb.getString("ExcelUrl")+"/pos/"+date+"/";
              File uploadfile = new File(realPath);
              if (!uploadfile.exists() && !uploadfile.isDirectory()) {
                  // 不存在
                  uploadfile.mkdirs();
              }
              outputStream = new FileOutputStream(realPath+"check"+date+".xlsx");
              log.info("文件生成为:"+realPath+"check"+date+".xlsx");
              wb.write(outputStream);
              outputStream.flush();
              outputStream.close();
          }catch(Exception e){
              e.printStackTrace();
          }
      }catch(Exception e){
          e.printStackTrace();
      }
  }
  
  public static double mul(double v1, double v2)
  {
      BigDecimal b1 = new BigDecimal(Double.toString(v1));
      BigDecimal b2 = new BigDecimal(Double.toString(v2));
      BigDecimal result = b1.multiply(b2);
      // return Double.valueOf(result.toString());
      return roundFee(result.doubleValue(), 2);

  }
  /**
   * 计算手续费的时候向上取整
   * @param v
   * @param scale
   * @return
   */
  public static double roundFee(double v, int scale)
  {
      if (scale < 0)
      {
          throw new IllegalArgumentException("The scale must be a positive integer or zero");
      }
      BigDecimal b = new BigDecimal(Double.toString(v));
      BigDecimal one = new BigDecimal("1");
      return b.divide(one, scale, BigDecimal.ROUND_UP).doubleValue();
  }
  
  public Map<String,CoreTransLog> getList(String date){
      CoreTransLog coreTransLog = new CoreTransLog();
      coreTransLog.setSettleDt(date.substring(5,10).replaceAll("-", ""));
      List<CoreTransLog>coreTransLogList = coreTransLogDao.selectInfoList(coreTransLog);
      Map<String,CoreTransLog> map = new HashMap<String,CoreTransLog>();
      for(CoreTransLog core : coreTransLogList){
          map.put(core.getChMerId()+core.getChTermId()+core.getReferenceNo(), core);
      }
      return map;
  }
  
  public void writeCheckTxt(String date,String unChecked){
	  OrderStatictis orderStatictis = new OrderStatictis();
	  orderStatictis.setCheckStatus("2");		//不平账
	  orderStatictis.setSettleDate(date);
	  List<OrderStatictis> orderStatictisList = orderStatictisDao.selectOrderByStatus(orderStatictis);
	  String uploadPath = rb.getString("ExcelUrl")+"/checkStatic/";
	  File txt = new File(uploadPath+date+"check.txt");
	  try {
		  FileWriter fw = new FileWriter(txt);
		  BufferedWriter bw=new BufferedWriter(fw);
		  bw.write(date+"对账不平信息");
		  bw.write("\r\n");
		  for(OrderStatictis error:orderStatictisList){
			  bw.write(error.getOrderId());
			  bw.write("||");
			  bw.write(error.getSubMerId());
			  bw.write("||");
			  bw.write(error.getMerAmt());
			  bw.write("||");
			  bw.write(error.getSettleDate());
			  bw.write("||");
			  bw.write("\r\n");
		  }
		  bw.write(date+"文件有系统无");
		  bw.write("\r\n");
		  bw.write(unChecked);
		  bw.write(date+"系统有文件无");
		  bw.write("\r\n");
		  List<OrderStatictis> noList = orderStatictisDao.selectOrderByStatus(orderStatictis);
		  for(OrderStatictis none:noList){
			  bw.write(none.getOrderId());
			  bw.write("||");
			  bw.write(none.getSubMerId());
			  bw.write("||");
			  bw.write(none.getMerAmt());
			  bw.write("||");
			  bw.write(none.getSettleDate());
			  bw.write("||");
			  bw.write("\r\n");
		  }
		  bw.flush();
		  fw.flush();
		  bw.close();
		  fw.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
	  
  }
  
  public StringBuffer readFile(String url){
		StringBuffer sb = new StringBuffer();
		String value = "";
		try
		{
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(url));
		HSSFSheet sheet =workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		for (int r = 0; r < rows; r++)
		{
		HSSFRow row = sheet.getRow(r);
		if (row != null)
		{
		int cells = row.getPhysicalNumberOfCells();
		for (short c = 0; c < cells; c++) 
		{
		HSSFCell cell = row.getCell(c);
		if (cell != null)
		{
		switch (cell.getCellType())
		{
		case HSSFCell.CELL_TYPE_FORMULA :
//		strCell = String.valueOf(aCell.getNumericCellValue());
//		returnstr+=strCell+" ";
		break;
		case HSSFCell.CELL_TYPE_NUMERIC:
		value += (long)cell.getNumericCellValue()+"|";
		break;
		case HSSFCell.CELL_TYPE_STRING:
		value += cell.getStringCellValue()+"|";
		break;
		case HSSFCell.CELL_TYPE_BLANK://blank
//		strCell = aCell.getStringCellValue();
//		returnstr+=strCell+" ";
		break;

		default:
		value +="|";

		}
		}
		}
//		下面可以将查找到的行内容用SQL语句INSERT到oracle
		
		

		//
		}
		value +="\r\n";
		}
		sb= new StringBuffer(value);
		return sb;
		}catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		

	}
  
  public void checkFile() {
    try {
      settleDate2 = settleDate2.replace("-", "");
      String filePath = null;
      String fileName = merSettleStatictis.getMerSysId() + "_" + settleDate2;
      if ("0".equals(merType)) {
        fileName += "_MerSettleStatictis.xls";
        filePath = rb.getString("ExcelUrl") + "/MerSettleStatictis/" + settleDate2 + "/";
      } else if ("1".equals(merType)) {
        filePath = rb.getString("ExcelUrl") + "/OrderSettleStatictis/" + settleDate2 + "/";
        fileName += "_OrderSettleStatictis.xls";
      }
      File file = new File(filePath + fileName);
      if (!file.exists()) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public boolean downLoadFile(String filePath, HttpServletResponse response, String fileName,
      String fileType) throws Exception {
    File file = new File(filePath); // 根据文件路径获得File文件
    if (!file.exists()) {
      return false;
    }
    // 设置文件类型(这样设置就不止是下Excel文件了，一举多得)
    if ("pdf".equals(fileType)) {
      response.setContentType("application/pdf;charset=GBK");
    } else if ("xls".equals(fileType)) {
      response.setContentType("application/x-xls;charset=GBK");
    } else if ("doc".equals(fileType)) {
      response.setContentType("application/msword;charset=GBK");
    }
    // 文件名
    response.setHeader("Content-Disposition",
        "attachment;filename=\"" + new String(fileName.getBytes(), "ISO8859-1") + "\"");
    byte[] buffer = new byte[4096];// 缓冲区
    BufferedOutputStream output = null;
    BufferedInputStream input = null;
    try {
      output = new BufferedOutputStream(response.getOutputStream());
      input = new BufferedInputStream(new FileInputStream(file));
      int n = -1;
      // 遍历，开始下载
      while ((n = input.read(buffer, 0, 4096)) > -1) {
        output.write(buffer, 0, n);
      }
      output.flush(); // 不可少
      response.flushBuffer();// 不可少
    } catch (Exception e) {
      // 异常自己捕捉
    } finally {
      // 关闭流，不可少
      if (input != null)
        input.close();
      if (output != null)
        output.close();
    }
    return false;
  }

  /**
   * 
   * @Description: 下载代发文件
   * @Auther: 柏江波
   * @Date: 2014-9-23 下午14:30:20
   */
  public void downloadIssuingFile() {
    try {
      if (merSettleStatictis == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      settleDate2 = settleDate2.replace("-", "");
      // String filePath = rb.getString("ExcelUrl") +
      // "/MerSettleStatictis/" +
      // settleDate2 + "/"
      // + merSettleStatictis.getMerSysId() + "/";
      // String filePath = "D:\\"+ settleDate2 + "\\";
      // 本地测试数据
      String filePath = rb.getString("ExcelUrl") + "/issuingFile/" + settleDate2 + "/";
      String fileName = merSettleStatictis.getMerSysId() + "_" + settleDate2 + ".xls";
      downLoadFile(filePath + fileName, getResponse(), fileName, "xls");
    } catch (Exception e) {
      try {
        getResponse().getWriter().write("fone");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      e.printStackTrace();
    }
  }

  /**
   * 
   * @Description: 代发回传excel解析
   * @Auther: 柏江波
   * @Date: 2014-9-23 下午20:17:49
   */
  public void importIssuingFile() {
    try {
      if (null == callBackFile) {
        this.renderText("请上传文件!");
        return;
      }
      String directory = "/upload";
      String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
      // 生成上传的文件对象
      File target = new File(targetDirectory, callBackFileFileName);
      // 如果文件已经存在，则删除原有文件
      if (target.exists()) {
        target.delete();
      }
      // 复制file对象，实现上传
      try {
        FileUtils.copyFile(callBackFile, target);
      } catch (IOException e) {
        e.printStackTrace();
      }
      Map<String, Object> map = statisticsParser.parseExcel(target);
      @SuppressWarnings("unchecked")
      List<CallBackTem> callBackTems = (List<CallBackTem>) map.get("list");
      if (null != callBackTems) {
        for (CallBackTem callBackTem : callBackTems) {
          String status = "";
          if ("成功".equals(callBackTem.getStatus())) {
            status = "2";
          }
          if ("失败".equals(callBackTem.getStatus())) {
            status = "3";
          }
          if ("处理中".equals(callBackTem.getStatus())) {
            status = "9";
          }
          MerSettleStatictis statictis = new MerSettleStatictis();
          statictis.setId(callBackTem.getUse());
          statictis.setClearStatus(status);
          statictis.setReserved1(DateUtil.getDate(new Date(), "yyyyMMddHHmmss"));
          statictis.setReserved2(callBackTem.getTradeDesc());
          merSettleStatictisService.updateMerSettleStatictisStatusById(statictis);
        }
      }
      this.renderText("<script>alert('成功!');window.parent.$.ligerDialog.close();window.parent.$('.l-dialog,.l-window-mask').remove();</script>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getMerchantList() {
    try {
      if (merSettleStatictis == null) {
        merSettleStatictis = new MerSettleStatictis();
      }
      merSettleStatictis.setClearStatus(clearStatus);
      merSettleStatictis.setMid(mid);
      merSettleStatictis.setMerType("1");
      merSettleStatictis.setSettleDate(merSettleStatictis.getSettleDate().replaceAll("-", ""));
      Map<String, Object> map = PageUtil.getPageMap(page1, pageSize1);
      map.put("merSettleStatictis", merSettleStatictis);
      count = merSettleStatictisService.selectMerSettleStatictisListCount(map);
      // 查询机构下的所有子商户清分信息
      merSettleStatictisList = merSettleStatictisService.selectMerSettleStatictisList(map);
      getClear();
      Long sum = 0L;// 清算总金额
      Long sum2 = 0L;// 总交易额
      Long sum1 = 0L;// 交易总手续费
      for (MerSettleStatictis m : merSettleStatictisList) {
        if (m.getSumAmt() != null && !"".equals(m.getSumAmt()) && m.getSumTransFee() != null
            && !"".equals(m.getSumTransFee()))
          sum += (Long.parseLong(m.getSumAmt()) - Long.parseLong(m.getSumTransFee()));
        if (m.getSumTransFee() != null && !"".equals(m.getSumTransFee()))
          sum1 += Long.parseLong(m.getSumTransFee());
        if (m.getSumAmt() != null && !"".equals(m.getSumAmt()))
          sum2 += Long.parseLong(m.getSumAmt());
      }
      JSONArray array = JSONArray.fromObject(merSettleStatictisList);
      DecimalFormat df = new DecimalFormat("#0.00");
      transFee = df.format(sum1 / 100D);
      merAmt = df.format(sum2 / 100D);
      clearAmt = df.format(sum / 100D);
      merSettleStatictisListJson = array.toString().replace("\"", "\\\"");
      System.out.println(merSettleStatictisListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "getMerchantList";
  }

  public String getSettDateFailure() {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DAY_OF_MONTH, -1);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    settleDate2 = formatter.format(c.getTime());
    getRequest().setAttribute("settleDate", settleDate2);
    // 以上是获取前一天时间 格式是 20131126
    return "getSettDateFailure";
  }

  public String merSettleStatictisFailureList() {
    try {
      if ("1".equals(id)) {
        getSettDateFailure();
      }
      if (merSettleStatictis == null) {
        merSettleStatictis = new MerSettleStatictis();
      }
      merSettleStatictis.setMerType("1");
      if (settleDate2 != null && !"".equals(settleDate2)) {
        merSettleStatictis.setSettleDate(settleDate2.replaceAll("-", ""));
      }
      // 查询机构下的所有子商户清分信息
      merSettleStatictisList = merSettleStatictisService.getClearFaile(merSettleStatictis);
      Iterator<MerSettleStatictis> merSettleStatictisIt = merSettleStatictisList.iterator();
      if (merSettleStatictisList != null)
        count = merSettleStatictisList.size();
      Double clearAmt = 0D;
      Double faileAmt = 0D;
      while (merSettleStatictisIt.hasNext()) {
        MerSettleStatictis merSettleStatictis = (MerSettleStatictis) merSettleStatictisIt.next();
        if (merSettleStatictis.getClearAmt() != null)
          clearAmt += Double.parseDouble(merSettleStatictis.getClearAmt()) / 100L;
        if (merSettleStatictis.getFaileAmt() != null)
          faileAmt += Double.parseDouble(merSettleStatictis.getFaileAmt()) / 100L;
      }
      DecimalFormat df = new DecimalFormat("#0.00");
      this.clearAmt = df.format(clearAmt);
      this.faileAmt = df.format(faileAmt);
      JSONArray array = JSONArray.fromObject(merSettleStatictisList);
      merSettleStatictisListJson = array.toString();
      System.out.println(merSettleStatictisListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "merSettleStatictisFailureList";
  }

  public void merSettleStatictisdownloadExcel() throws IOException {
    if (merSettleStatictis == null) {
      merSettleStatictis = new MerSettleStatictis();
    }
    merSettleStatictis.setMerType("1");
    merSettleStatictis.setClearStatus("3");
    if (settleDate2 != null && !"".equals(settleDate2)) {
      merSettleStatictis.setSettleDate(settleDate2.replaceAll("-", ""));
    }
    merSettleStatictisList = merSettleStatictisService
        .selectMerSettleStatictisListNew(merSettleStatictis);
    if (merSettleStatictisList.size() > 1000) {
      this.renderText("<script>alert('导出条数大于1000，请添加限制条件');window.close();</script>");
      return;
    } else if (merSettleStatictisList.size() == 0) {
      this.renderText("<script>alert('导出条数不能为0');window.close();</script>");
      return;
    }
    try {
      String[] columNames = {
          "姓名", "银行名", "银行账号", "代发人手机", "证件类型", "证件号", "交易金额(单位:元)", "代发类型", "代发省份名", "代发地区名",
          "代发支行名", "代发用途", "入账子账户", "联行号" };
      HttpServletResponse response = getResponse();
      OutputStream os;
      try {
        os = response.getOutputStream();
        String sheetName = "待清分记录";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename="
            + new String(("失败" + sheetName).getBytes(), "ISO8859-1") + ".xls");
        response.setBufferSize(1024);

        WritableWorkbook book = Workbook.createWorkbook(os);
        WritableSheet sheet = book.createSheet(sheetName, 0);
        // 第一列 赋值
        sheet.addCell(new Label(0, 0, "V1.1"));
        // 第二列 赋值
        for (int i = 0; i < columNames.length; i++) {
          sheet.addCell(new Label(i, 1, columNames[i]));
        }

        for (int j = 0, row = 2; j < merSettleStatictisList.size(); j++, row++) {
          SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(merSettleStatictisList.get(j)
              .getMid());
          sheet.addCell(new Label(0, row, subMerInfo.getSettAccountName()));
          sheet.addCell(new Label(1, row, subMerInfo.getSettAgency()));
          sheet.addCell(new Label(2, row, subMerInfo.getSettAccountNo()));
          sheet.addCell(new Label(3, row, subMerInfo.getContactorPhone()));
          sheet.addCell(new Label(4, row, "00"));
          sheet.addCell(new Label(5, row, subMerInfo.getLegalIdcard()));
          sheet.addCell(new Label(6, row, AmountUtils.changeF2Y(merSettleStatictisList.get(j)
              .getClearAmt())));
          sheet.addCell(new Label(7, row, subMerInfo.getSettAccType() == "1" ? "C" : "P"));
          // 省份
          // RegionCode rc = new RegionCode();
          // rc.setId(subMerInfo.getRegion());
          // List<RegionCode> regionCodes =
          // regionCodeService.selectRegionCodeList2(rc);
          // if (null != regionCodes && !regionCodes.isEmpty()) {
          sheet.addCell(new Label(8, row, ""));
          // }
          // 地区
          // rc = new RegionCode();
          // rc.setId(regionCodes.get(0).getSuperCode());
          // List<RegionCode> regionCodes2 =
          // regionCodeService.selectRegionCodeList2(rc);
          // if (null != regionCodes2 && !regionCodes2.isEmpty()) {
          sheet.addCell(new Label(9, row, ""));
          // }
          sheet.addCell(new Label(10, row, "支行待定"));
          sheet.addCell(new Label(11, row, merSettleStatictisList.get(j).getId())); // T+0/ID/用作回传关联
          sheet.addCell(new Label(12, row, /*
                                            * merSettleStatictisList.get
                                            * (j).getMid()
                                            */""));
          sheet.addCell(new Label(13, row, "联行号"));
        }
        book.write();
        book.close();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (RowsExceededException e) {
        e.printStackTrace();
      } catch (WriteException e) {
        e.printStackTrace();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 
   * @Description: 回传excel解析
   * @Auther: ljl
   * @Date: 2014-9-23 下午8:17:49
   */
  public void importFailureFile() {
    try {
      if (null == callBackFile) {
        this.renderText("请上传文件!");
        return;
      }
      String directory = "/upload";
      String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
      // 生成上传的文件对象
      File target = new File(targetDirectory, callBackFileFileName);
      // 如果文件已经存在，则删除原有文件
      if (target.exists()) {
        target.delete();
      }
      // 复制file对象，实现上传
      try {
        FileUtils.copyFile(callBackFile, target);
      } catch (IOException e) {
        e.printStackTrace();
      }
      Map<String, Object> map = statisticsParser.parseExcel(target);
      @SuppressWarnings("unchecked")
      List<CallBackTem> callBackTems = (List<CallBackTem>) map.get("list");
      if (null != callBackTems) {
        for (CallBackTem callBackTem : callBackTems) {
          String status = "";
          if ("成功".equals(callBackTem.getStatus())) {
            status = "2";
          }
          if ("失败".equals(callBackTem.getStatus())) {
            status = "3";
          }
          if ("处理中".equals(callBackTem.getStatus())) {
            status = "9";
          }
          MerSettleStatictis statictis = new MerSettleStatictis();
          statictis.setId(callBackTem.getUse());
          statictis.setClearStatus(status);
          statictis.setReserved1(DateUtil.getDate(new Date(), "yyyyMMddHHmmss"));
          statictis.setReserved2(callBackTem.getTradeDesc());
          merSettleStatictisService.updateMerSettleStatictisStatusById(statictis);
        }
      }
      this.renderText("<script>alert('成功!');window.parent.$.ligerDialog.close();window.parent.$('.l-dialog,.l-window-mask').remove();</script>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // public void downloadExcel(OutputStream os, String sheetName, String[]
  // columNames,
  // List<? extends BaseExcel> data) throws RowsExceededException,
  // WriteException, IOException {
  // WritableWorkbook book = Workbook.createWorkbook(os);
  // WritableSheet sheet = book.createSheet(sheetName, 0);
  // // 第一列 赋值
  // for (int i = 0; i < columNames.length; i++) {
  // sheet.addCell(new Label(i, 0, columNames[i]));
  // }
  //
  // for (int j = 0, row = 1; j < data.size(); j++, row++) {
  // for (int i = 0; i < columNames.length; i++) {
  // sheet.addCell(new Label(i, row, data.get(j).getCellValue(i)));
  // }
  // }
  // book.write();
  // book.close();
  // }

  public String getTodayStatistics() {
    createDate2 = DateUtil.getDate(new Date(), "yyyy-MM-dd");
    getRequest().setAttribute("createDate2", createDate2);
    // 以上是获取前一天时间 格式是 20131126
    return "getTodayStatistics";
  }
  
  public String getTodaySubMerCashOut() {
	    createDate2 = DateUtil.getDate(new Date(), "yyyy-MM-dd");
	    getRequest().setAttribute("createDate2", createDate2);
	    // 以上是获取前一天时间 格式是 20131126
	    return "getTodaySubMerCashOut";
	  }
  
  public void subMerExcel(){
	  	SubMerCashout subMerCashout = new SubMerCashout();
	    subMerCashout.setCreateTime(createDate2.replaceAll("-", ""));
	    getRequest().setAttribute("createTime", createDate2.replaceAll("-", ""));
	    Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
	    map.put("subMerCashout", subMerCashout);
	    map.put("page", "-1");
	    map.put("pageSize", "-1"); 
	    map.put("createTime", createDate2.replaceAll("-", ""));
	    // 查询机构下的所有子商户清分信息
	    List<SubMerCashout> subMerCashouts = subMerCashoutService
	        .selectSubMerCashoutList3(map);
	    try{
	    HttpServletResponse response = getResponse();
		response.setContentType("application/x-xls;charset=GBK");
		response.setHeader("Content-disposition",
				"attachment;success=true;filename ="
						+ URLEncoder.encode(new Date().toString()+"T0记录.xls", "utf-8"));
		OutputStream fos = response.getOutputStream();
		subMerFile(subMerCashouts,fos);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
  }
  /**
	 * 生成代发文件
	 */
	public void subMerFile(List<SubMerCashout> subMerCashoutList,OutputStream os)
			throws Exception {
		
		// 写入代理商统计信息
		String[] fubiao = { "批次号", "子商户号", "提现金额(分)", "申请时间", "完成时间", "手续费",
				"提现状态" };
		WritableWorkbook book = Workbook.createWorkbook(os);
		WritableSheet wrsheet = book
				.createSheet("T+0提现记录", 0);
		wrsheet.setColumnView(0, 10);
		wrsheet.setColumnView(1, 20);
		wrsheet.setColumnView(2, 25);
		wrsheet.setColumnView(3, 12);
		wrsheet.setColumnView(4, 7);
		wrsheet.setColumnView(5, 20);
		wrsheet.setColumnView(6, 10);
		wrsheet.setColumnView(7, 7);
		wrsheet.setColumnView(8, 15);
		wrsheet.setColumnView(9, 15);
		wrsheet.setColumnView(10, 15);
		wrsheet.setColumnView(11, 10);
		wrsheet.setColumnView(12, 20);
		wrsheet.setColumnView(13, 15);
		// 设置字体种类和格式
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 28,
				WritableFont.BOLD);
		WritableCellFormat wcfFormat = new WritableCellFormat(bold);
		wcfFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		// 设置头部字体格式
		WritableFont font = new WritableFont(WritableFont.TIMES, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		// 应用字体
		WritableCellFormat wcfh = new WritableCellFormat(font);
		// 设置其他样式
		wcfh.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfh.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
		wcfh.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
		// wcfh.setBackground(Colour.YELLOW);// 背景色
		wcfh.setWrap(true);// 不自动换行
		wrsheet.addCell(new Label(0, 0, "T0记录", wcfh));
		// 第二列 赋值
		for (int i = 0; i < fubiao.length; i++) {
			wrsheet.addCell(new Label(i, 1, fubiao[i], wcfh));
		}
		Iterator<SubMerCashout> merSettleStatictisIt = subMerCashoutList
				.iterator();
		// RegionCode rc = null;
		int y = 2;
		while (merSettleStatictisIt.hasNext()) {
			SubMerCashout merSettleStatictis1 = (SubMerCashout) merSettleStatictisIt
					.next();
			SubMerInfo info = subMerInfoService
					.getSubMerInfoById(merSettleStatictis1.getSubMerId());
			wrsheet.addCell(new Label(0, y, merSettleStatictis1.getBatchId(), wcfh));
			wrsheet.addCell(new Label(1, y, merSettleStatictis1.getSubMerId(), wcfh));
			wrsheet.addCell(new Label(2, y, merSettleStatictis1.getOrderAmt(), wcfh));
			wrsheet.addCell(new Label(3, y, merSettleStatictis1.getCreateTime(), wcfh));
			wrsheet.addCell(new Label(4, y, merSettleStatictis1.getFinishTime(), wcfh));
			wrsheet.addCell(new Label(5, y, merSettleStatictis1.getTransFee(), wcfh));
			String status="";
			if(merSettleStatictis1.getOrderStatus().equals("0")){
				status="申请";
			}else if(merSettleStatictis1.getOrderStatus().equals("1")){
				status="等待代发";
			}else if(merSettleStatictis1.getOrderStatus().equals("2")){
				status="代发成功";
			}else if(merSettleStatictis1.getOrderStatus().equals("3")){
				status="当日代发失败";
			}else if(merSettleStatictis1.getOrderStatus().equals("4")){
				status="次日代发失败";
			}else if(merSettleStatictis1.getOrderStatus().equals("5")){
				status="已T+1清分";
			}else if(merSettleStatictis1.getOrderStatus().equals("6")){
				status="钱包消费";
			}else if(merSettleStatictis1.getOrderStatus().equals("9")){
				status="代发处理中";
			}
			wrsheet.addCell(new Label(6, y, status, wcfh));
			y++;
		}

		book.write();
		book.close();
		os.flush();
		os.close();
	}
  /**
   * 
   * @Description: T+0数据
   * @Auther: ljl
   * @Date: 2014-9-22 下午7:24:33
   */
  public void getTodayStatisticsData() {
    // String createDate = this.getParameterForString("createDate");
    SubMerCashoutBatch subMerCashoutBatch = new SubMerCashoutBatch();
    subMerCashoutBatch.setCreateDate(createDate2);
    getRequest().setAttribute("createDate2", createDate2);
    Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
    map.put("subMerCashoutBatch", subMerCashoutBatch);
    count = subMerCashoutBatchService.selectSubMerCashoutBatchCount(map);
    // 查询机构下的所有子商户清分信息
    List<SubMerCashoutBatch> subMerCashoutBatchs = subMerCashoutBatchService
        .selectSubMerCashoutBatchList(map);
    Map<String, Object> object = Maps.newHashMap();
    object.put("Rows", subMerCashoutBatchs);
    object.put("Total", count);
    try {
      this.renderJSON(object);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void getSubMerCashOutData() {
	  	SubMerCashout subMerCashout = new SubMerCashout();
	    subMerCashout.setCreateTime(createDate2.replaceAll("-", ""));
	    getRequest().setAttribute("createTime", createDate2.replaceAll("-", ""));
	    Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
	    map.put("subMerCashout", subMerCashout);
	    map.put("createTime", createDate2.replaceAll("-", ""));
	    count = subMerCashoutService.selectSubMerCashoutListCount3(map);
	    // 查询机构下的所有子商户清分信息
	    List<SubMerCashout> subMerCashouts = subMerCashoutService
	        .selectSubMerCashoutList3(map);
	    Map<String, Object> object = Maps.newHashMap();
	    object.put("Rows", subMerCashouts);
	    object.put("Total", count);
	    try {
	      this.renderJSON(object);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }

	  }
  
  public String getTodayStatisticsDetail() {
    this.setAttribute("batchId", this.getParameterForString("batchId"));
    return "getTodayStatisticsDetail";
  }

  /**
   * 
   * @Description: T+0详情数据
   * @Auther: ljl
   * @Date: 2014-9-22 下午7:24:33
   */
  public void getTodayStatisticsDetailData() {
    SubMerCashout subMerCashout = new SubMerCashout();
    subMerCashout.setBatchId(this.getParameterForString("batchId"));
    subMerCashout.setSubMerId(this.getParameterForString("subMerId"));
    Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
    map.put("subMerCashout", subMerCashout);
    count = subMerCashoutService.selectSubMerCashoutListCount(map);
    // 查询机构下的所有子商户清分信息
    List<SubMerCashout> subMerCashouts = subMerCashoutService.selectSubMerCashoutList(map);
    Map<String, Object> object = Maps.newHashMap();
    object.put("Rows", subMerCashouts);
    object.put("Total", count);
    try {
      this.renderJSON(object);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
  public HttpServletResponse download(String path, HttpServletResponse response) {
      try {
          // path是指欲下载的文件的路径。
          File file = new File(path);
          // 取得文件名。
          String filename = file.getName();
          // 取得文件的后缀名。
          String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

          // 以流的形式下载文件。
          InputStream fis = new BufferedInputStream(new FileInputStream(path));
          byte[] buffer = new byte[fis.available()];
          fis.read(buffer);
          fis.close();
          // 清空response
          response.reset();
          // 设置response的Header
          response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
          response.addHeader("Content-Length", "" + file.length());
          OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
          response.setContentType("application/octet-stream");
          toClient.write(buffer);
          toClient.flush();
          toClient.close();
          
        if (file.exists()) {
        	file.delete();
	    }
      } catch (IOException ex) {
          ex.printStackTrace();
      }
      return response;
  }

  public static void doDownLoad(String path, String name, HttpServletResponse response) {
    try {
      response.reset();
      response.setHeader("Content-disposition",
          "attachment;success=true;filename =" + URLEncoder.encode(name, "utf-8"));
      BufferedInputStream bis = null;
      BufferedOutputStream bos = null;
      OutputStream fos = null;
      InputStream fis = null;
      File uploadFile = new File(path);
      fis = new FileInputStream(uploadFile);
      bis = new BufferedInputStream(fis);
      fos = response.getOutputStream();
      bos = new BufferedOutputStream(fos);
      // 弹出下载对话框
      int bytesRead = 0;
      byte[] buffer = new byte[8192];
      while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
        bos.write(buffer, 0, bytesRead);
      }
      bos.flush();
      fis.close();
      bis.close();
      fos.close();
      bos.close();
      if (uploadFile.exists()) {
        uploadFile.delete();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

	public void downloadT0(){
		try {
			String createTime = getRequest().getParameter("createDate");
			SubMerCashout subMerCashout = new SubMerCashout();
			subMerCashout.setCreateTime(createTime.replace("-",""));
			subMerCashout.setOrderStatus("3");
			List<SubMerCashout> subMerCashoutList = subMerCashoutService.seleSubMerCashoutReport(subMerCashout);
			HttpServletResponse response = getResponse();
			response.setContentType("application/x-xls;charset=GBK");
			response.setHeader("Content-disposition",
					"attachment;success=true;filename ="
							+ URLEncoder.encode(createTime+"T0回盘失败记录.xls", "utf-8"));
			OutputStream fos = response.getOutputStream();
			issuingFile(subMerCashoutList,fos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成代发文件
	 */
	public void issuingFile(List<SubMerCashout> subMerCashoutList,OutputStream os)
			throws Exception {
		
		// 写入代理商统计信息
		String[] fubiao = { "姓名", "银行名", "银行账号", "代发人手机", "证件类型", "证件号",
				"交易金额(单位:元)", "代发类型", "代发省份名", "代发地区名", "代发支行名", "代发用途",
				"入账子账户", "联行号", "空", "空", "空", "空", "空", "商户号" };
		WritableWorkbook book = Workbook.createWorkbook(os);
		WritableSheet wrsheet = book
				.createSheet("代发失败记录", 0);
		wrsheet.setColumnView(0, 10);
		wrsheet.setColumnView(1, 20);
		wrsheet.setColumnView(2, 25);
		wrsheet.setColumnView(3, 12);
		wrsheet.setColumnView(4, 7);
		wrsheet.setColumnView(5, 20);
		wrsheet.setColumnView(6, 10);
		wrsheet.setColumnView(7, 7);
		wrsheet.setColumnView(8, 15);
		wrsheet.setColumnView(9, 15);
		wrsheet.setColumnView(10, 15);
		wrsheet.setColumnView(11, 10);
		wrsheet.setColumnView(12, 20);
		wrsheet.setColumnView(13, 15);
		// 设置字体种类和格式
		WritableFont bold = new WritableFont(WritableFont.ARIAL, 28,
				WritableFont.BOLD);
		WritableCellFormat wcfFormat = new WritableCellFormat(bold);
		wcfFormat.setAlignment(jxl.format.Alignment.CENTRE);// 单元格中的内容水平方向居中
		// 设置头部字体格式
		WritableFont font = new WritableFont(WritableFont.TIMES, 10,
				WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				Colour.BLACK);
		// 应用字体
		WritableCellFormat wcfh = new WritableCellFormat(font);
		// 设置其他样式
		wcfh.setAlignment(Alignment.CENTRE);// 水平对齐
		wcfh.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
		wcfh.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
		// wcfh.setBackground(Colour.YELLOW);// 背景色
		wcfh.setWrap(true);// 不自动换行
		wrsheet.addCell(new Label(0, 0, "V1.1", wcfh));
		// 第二列 赋值
		for (int i = 0; i < fubiao.length; i++) {
			wrsheet.addCell(new Label(i, 1, fubiao[i], wcfh));
		}
		Iterator<SubMerCashout> merSettleStatictisIt = subMerCashoutList
				.iterator();
		// RegionCode rc = null;
		int y = 2;
		while (merSettleStatictisIt.hasNext()) {
			SubMerCashout merSettleStatictis1 = (SubMerCashout) merSettleStatictisIt
					.next();
			SubMerInfo info = subMerInfoService
					.getSubMerInfoById(merSettleStatictis1.getSubMerId());
			wrsheet.addCell(new Label(0, y, info.getSettAccountName(), wcfh));
			wrsheet.addCell(new Label(1, y, info.getSettAgency() == null
					|| "".equals(info.getSettAgency()) ? "未知" : info
					.getSettAgency(), wcfh));
			wrsheet.addCell(new Label(2, y, info.getSettAccountNo(), wcfh));
			wrsheet.addCell(new Label(3, y, info.getContactorPhone(), wcfh));
			wrsheet.addCell(new Label(4, y, "00", wcfh));
			wrsheet.addCell(new Label(5, y, info
					.getLegalIdcard(), wcfh));
			wrsheet.addCell(new Label(6, y, (Float
					.parseFloat(merSettleStatictis1.getOrderAmt()) / 100)
					+ "", wcfh));
			wrsheet.addCell(new Label(7, y, info
					.getSettAccType() == "1" ? "C" : "P", wcfh));
			wrsheet.addCell(new Label(8, y, "", wcfh));
			wrsheet.addCell(new Label(9, y, "", wcfh));
			wrsheet.addCell(new Label(10, y, "支行待定", wcfh)); // 待定
			wrsheet
					.addCell(new Label(11, y, merSettleStatictis1.getId(), wcfh));
			wrsheet.addCell(new Label(12, y, null, wcfh));
			wrsheet.addCell(new Label(13, y, "联行号", wcfh));// 待定
			wrsheet
					.addCell(new Label(19, y, merSettleStatictis1.getSubMerId(),
							wcfh));// 待定
			y++;
		}

		book.write();
		book.close();
		os.flush();
		os.close();
	}
	
	public void checkT0(){
		try {
			String createTime = getRequest().getParameter("createDate");
			SubMerCashout subMerCashout = new SubMerCashout();
			subMerCashout.setCreateTime(createTime.replace("-",""));
			subMerCashout.setOrderStatus("3");
			Map map = new HashMap();
			map.put("subMerCashout", subMerCashout);
			int i = subMerCashoutService.selectSubMerCashoutListNotPageCount(map);
			if(i == 0){
				getResponse().getWriter().write("fone");
			}else{
				getResponse().getWriter().write("succ");
			}
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("fone");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
  public void todayStatisticsDetailExcel() {
		try {
			String[] columNames = { "姓名", "银行名", "银行账号", "代发人手机", "证件类型",
					"证件号", "交易金额(单位:元)", "代发类型", "代发省份名", "代发地区名", "代发支行名",
					"代发用途", "入账子账户", "联行号", "", "", "", "", "", "商户号" };
			String batchId = this.getParameterForString("batchId");
			if (StringUtils.isBlank(batchId)) {
				this.renderText("不存在!");
				return;
			}
			Map<String, Object> map = Maps.newHashMap();
			SubMerCashout subMerCashout = new SubMerCashout();
			subMerCashout.setBatchId(batchId);

			map.put("subMerCashout", subMerCashout);

			Integer resCount = subMerCashoutService
					.selectSubMerCashoutListNotPageCount(map);

			if (null == resCount || resCount < 1) {
				this.renderText("没有需要导出的数据!");
				return;
			} else {
				String dates = DateUtil.getDate("yyyyMMddHHmmss");
				
				File uploadfile = new File(rb.getString("ExcelUrl") + "/"+dates+"/");
				if (!uploadfile.exists() && !uploadfile.isDirectory()) {
					uploadfile.mkdirs();
				}
				int initNo = 5000;

				int n = resCount / initNo;
				int x = resCount % initNo;
				if (x > 0) {
					n = n + 1;
				}
				int pages = 0;
				int pageSizes = initNo;
				
				int fileNum = 0;
				for (int p = 0; p < n; p++) {
					fileNum ++;
					map.put("page", pages);
					map.put("pageSize", pageSizes);
					List<SubMerCashout> subMerCashouts = subMerCashoutService.selectSubMerCashoutListNotPage(map);

					String sheetName = "待清分记录";
					
					File fileWrite = new File(rb.getString("ExcelUrl") + "/"+dates+"/t0Write" + p + "_"+batchId+".xls");
					fileWrite.createNewFile();
					OutputStream oss = new FileOutputStream(fileWrite);

					WritableWorkbook book = Workbook.createWorkbook(oss);
					WritableSheet sheet = book.createSheet(sheetName, 0);
					// 第一列 赋值
					sheet.addCell(new Label(0, 0, "V1.1"));
					// 第二列 赋值
					for (int i = 0; i < columNames.length; i++) {
						sheet.addCell(new Label(i, 1, columNames[i]));
					}

					for (int j = 0, row = 2; j < subMerCashouts.size(); j++, row++) {

						SubMerInfo subMerInfo = subMerInfoService.getSubMerInfoById(subMerCashouts.get(j)
										.getSubMerId());
						Long amt = Long.parseLong(subMerCashouts.get(j)
								.getOrderAmt())
								- Long.parseLong(subMerCashouts.get(j)
										.getTransFee());
						sheet.addCell(new Label(0, row, subMerInfo
								.getSettAccountName()));
						sheet.addCell(new Label(1, row, subMerInfo
								.getSettAgency()));
						sheet.addCell(new Label(2, row, subMerInfo
								.getSettAccountNo()));
						sheet.addCell(new Label(3, row, subMerInfo
								.getContactorPhone()));
						sheet.addCell(new Label(4, row, "00"));
						sheet.addCell(new Label(5, row, subMerInfo
								.getLegalIdcard()));
						sheet.addCell(new Label(6, row, AmountUtils
								.changeF2Y(amt)));
						sheet.addCell(new Label(7, row, subMerInfo
								.getSettAccType() == "1" ? "C" : "P"));
						// 省份
						// RegionCode rc = new RegionCode();
						// rc.setId(subMerInfo.getRegion());
						// List<RegionCode> regionCodes =
						// regionCodeService.selectRegionCodeList2(rc);
						// if (null != regionCodes && !regionCodes.isEmpty()) {
						sheet.addCell(new Label(8, row, ""));
						// }
						// 地区
						// rc = new RegionCode();
						// rc.setId(regionCodes.get(0).getSuperCode());
						// List<RegionCode> regionCodes2 =
						// regionCodeService.selectRegionCodeList2(rc);
						// if (null != regionCodes2 && !regionCodes2.isEmpty())
						// {
						sheet.addCell(new Label(9, row, ""));
						// }
						sheet.addCell(new Label(10, row, subMerInfo.getOpenBank()));
						sheet.addCell(new Label(11, row, subMerCashouts.get(j)
								.getId())); // T+0/ID/用作回传关联
						sheet.addCell(new Label(12, row, /*
														 * subMerCashouts.get(j).
														 * getSubMerId()
														 */""));
						sheet.addCell(new Label(13, row, subMerInfo.getLineNum()));
						sheet.addCell(new Label(19, row, subMerInfo
								.getSubMerId()));
					}
					pages = pages + initNo;
					book.write();
					book.close();
					oss.flush();
					oss.close();
				}
				
				String inputFileName = rb.getString("ExcelUrl") + "/"+dates+"/"; // 你要压缩的文件夹
				String zipFileName = rb.getString("ExcelUrl") + "/t0_"+batchId+".zip"; // 压缩后的zip文件
		
				if(fileNum == 1) {
					download(inputFileName+"/t0Write0_"+batchId+".xls", getResponse());
				}else {
					ZipUtil zipUtil = new ZipUtil();
					try {
						zipUtil.zip(inputFileName, zipFileName);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					download(zipFileName, getResponse());
				}
				deleteDir(new File(inputFileName));//删除文件
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

  private static boolean deleteDir(File dir) {
    if (dir.isDirectory()) {
      String[] children = dir.list();
      // 递归删除目录中的子目录下
      for (int i = 0; i < children.length; i++) {
        boolean success = deleteDir(new File(dir, children[i]));
        if (!success) {
          return false;
        }
      }
    }
    // 目录此时为空，可以删除
    return dir.delete();
  }

  /**
   * 
   * @Description: 回传excel解析
   * @Auther: ljl
   * @Date: 2014-9-23 下午8:17:49
   */
  public void importCallBackFile() {
    try {
      if (null == callBackFile) {
        this.renderText("请上传文件!");
        return;
      }
      String directory = "/upload";
      String targetDirectory = ServletActionContext.getServletContext().getRealPath(directory);
      // 生成上传的文件对象
      File target = new File(targetDirectory, callBackFileFileName);
      // 如果文件已经存在，则删除原有文件
      if (target.exists()) {
        target.delete();
      }
      // 复制file对象，实现上传
      try {
        FileUtils.copyFile(callBackFile, target);
      } catch (IOException e) {
        e.printStackTrace();
      }
      Map<String, Object> map = statisticsParser.parseExcel(target);
      @SuppressWarnings("unchecked")
      List<CallBackTem> callBackTems = (List<CallBackTem>) map.get("list");
      if (null != callBackTems) {
        Set<String> set = Sets.newHashSet(); // 批次id
        for (CallBackTem callBackTem : callBackTems) {
          SubMerCashout smc = subMerCashoutService.selectSubMerCashoutById(callBackTem.getUse());
          // String oldStatus = "9";
          // if (null != smc) {
          // oldStatus = smc.getOrderStatus();
          // }
          String status = "";
          if ("成功".equals(callBackTem.getStatus())) {
            status = "2";
          }
          if ("失败".equals(callBackTem.getStatus())) {
            status = "3";
          }
          if ("处理中".equals(callBackTem.getStatus())) {
            status = "9";
          }

          if (null != smc) {
            String batchId = new String(smc.getBatchId());
            set.add(batchId);
          }
          // 失败为子商户金账户++;
          // if (null != smc && "3".equals(callBackTem.getStatus())) {
          // SubMerInfo subMerInfo =
          // subMerInfoService.getSubMerInfoById(smc.getSubMerId());
          // if ("9".equals(oldStatus)) {// 从未回盘过
          // if (DateUtil.getDate(new Date(), "yyyyMMdd").equals(
          // smc.getCreateTime().substring(0, 8))) {
          // subMerInfo.setgAccStatus(String.valueOf(Long.parseLong(subMerInfo.getgAccStatus())
          // + Long.parseLong(smc.getOrderAmt())));
          // subMerInfoService.update(subMerInfo);
          // } else {// 次日回盘失败
          // status = "4";
          // }
          // }
          // }
          SubMerCashout subMerCashout = new SubMerCashout();
          subMerCashout.setId(callBackTem.getUse());
          subMerCashout.setOrderStatus(status);
          subMerCashout.setReserved1(callBackTem.getTradeDesc());
          subMerCashout.setFinishTime(DateUtil.getDate(new Date(), "yyyyMMddHHmmss"));
          subMerCashoutService.updateSubMerCashout(subMerCashout);
        }
        for (String string : set) {
          SubMerCashoutBatch subMerCashoutBatch = new SubMerCashoutBatch();
          subMerCashoutBatch.setBatchId(string);
          subMerCashoutBatch.setFinishTime(DateUtil.getDate(new Date(), "yyyyMMddHHmmss"));
          subMerCashoutBatch.setStatus("2");
          subMerCashoutBatchService.updateSubMerCashoutBatch(subMerCashoutBatch);
        }

      }
      this.renderText("<script>alert('成功!');window.parent.$.ligerDialog.close();window.parent.$('.l-dialog,.l-window-mask').remove();</script>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void downloadFile() {
    try {
      if (merSettleStatictis == null) {
        return;
      }
      settleDate2 = settleDate2.replace("-", "");
      String filePath = null;
      String fileName = merSettleStatictis.getMerSysId() + "_" + settleDate2;
      if ("0".equals(merType)) {
        fileName += "_MerSettleStatictis.xls";
        filePath = rb.getString("ExcelUrl") + "/MerSettleStatictis/" + settleDate2 + "/";
      } else if ("1".equals(merType)) {
        filePath = rb.getString("ExcelUrl") + "/OrderSettleStatictis/" + settleDate2 + "/";
        fileName += "_OrderSettleStatictis.xls";
      }
      downLoadFile(filePath + fileName, getResponse(), fileName, "xls");
      System.out.println(filePath + fileName);
    } catch (Exception e) {
      e.printStackTrace();
    }
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

  public void setMerSettleStatictisList(List<MerSettleStatictis> merSettleStatictisList) {
    this.merSettleStatictisList = merSettleStatictisList;
  }

  public String getSettleDate2() {
    return settleDate2;
  }

  public void setSettleDate2(String settleDate2) {
    this.settleDate2 = settleDate2;
  }

  public String getMerSettleStatictisListJson() {
    return merSettleStatictisListJson;
  }

  public void setMerSettleStatictisListJson(String merSettleStatictisListJson) {
    this.merSettleStatictisListJson = merSettleStatictisListJson;
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

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public int getPage1() {
    return page1;
  }

  public void setPage1(int page1) {
    this.page1 = page1;
  }

  public int getPageSize1() {
    return pageSize1;
  }

  public void setPageSize1(int pageSize1) {
    this.pageSize1 = pageSize1;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getClearStatus() {
    return clearStatus;
  }

  public void setClearStatus(String clearStatus) {
    this.clearStatus = clearStatus;
  }

  public String getCreateDate2() {
    return createDate2;
  }

  public void setCreateDate2(String createDate2) {
    this.createDate2 = createDate2;
  }

  public File getCallBackFile() {
    return callBackFile;
  }

  public void setCallBackFile(File callBackFile) {
    this.callBackFile = callBackFile;
  }

  public String getCallBackFileFileName() {
    return callBackFileFileName;
  }

  public void setCallBackFileFileName(String callBackFileFileName) {
    this.callBackFileFileName = callBackFileFileName;
  }

  public MerSettleStatictisService getMerSettleStatictisService() {
    return merSettleStatictisService;
  }

  public void setMerSettleStatictisService(MerSettleStatictisService merSettleStatictisService) {
    this.merSettleStatictisService = merSettleStatictisService;
  }

  public SubMerCashoutService getSubMerCashoutService() {
    return subMerCashoutService;
  }

  public void setSubMerCashoutService(SubMerCashoutService subMerCashoutService) {
    this.subMerCashoutService = subMerCashoutService;
  }

  public SubMerCashoutBatchService getSubMerCashoutBatchService() {
    return subMerCashoutBatchService;
  }

  public void setSubMerCashoutBatchService(SubMerCashoutBatchService subMerCashoutBatchService) {
    this.subMerCashoutBatchService = subMerCashoutBatchService;
  }

  public SubMerInfoService getSubMerInfoService() {
    return subMerInfoService;
  }

  public void setSubMerInfoService(SubMerInfoService subMerInfoService) {
    this.subMerInfoService = subMerInfoService;
  }

  public RegionCodeService getRegionCodeService() {
    return regionCodeService;
  }

  public void setRegionCodeService(RegionCodeService regionCodeService) {
    this.regionCodeService = regionCodeService;
  }

  public StatisticsParser getStatisticsParser() {
    return statisticsParser;
  }

  public void setStatisticsParser(StatisticsParser statisticsParser) {
    this.statisticsParser = statisticsParser;
  }

  public String getMerType() {
    return merType;
  }

  public void setMerType(String merType) {
    this.merType = merType;
  }

  public ResourceBundle getRb() {
    return rb;
  }

  public void setRb(ResourceBundle rb) {
    this.rb = rb;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getSettleDate1() {
    return settleDate1;
  }

  public void setSettleDate1(String settleDate1) {
    this.settleDate1 = settleDate1;
  }

  public SendFileDao getSendFileDao() {
    return sendFileDao;
  }

  public void setSendFileDao(SendFileDao sendFileDao) {
    this.sendFileDao = sendFileDao;
  }

  public String getMid() {
    return mid;
  }

  public void setMid(String mid) {
    this.mid = mid;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMerAmt() {
    return merAmt;
  }

  public void setMerAmt(String merAmt) {
    this.merAmt = merAmt;
  }

  public String getTransFee() {
    return transFee;
  }

  public void setTransFee(String transFee) {
    this.transFee = transFee;
  }

  public String getFaileAmt() {
    return faileAmt;
  }

  public void setFaileAmt(String faileAmt) {
    this.faileAmt = faileAmt;
  }

  public String getClearSuccAmt() {
    return clearSuccAmt;
  }

  public void setClearSuccAmt(String clearSuccAmt) {
    this.clearSuccAmt = clearSuccAmt;
  }


  public String getD1TransFee() {
	return d1TransFee;
}

public void setD1TransFee(String d1TransFee) {
	this.d1TransFee = d1TransFee;
}


public String getT0SuccSumAmt() {
	return t0SuccSumAmt;
}

public void setT0SuccSumAmt(String t0SuccSumAmt) {
	this.t0SuccSumAmt = t0SuccSumAmt;
}

public String getT0SuccTransFee() {
	return t0SuccTransFee;
}

public void setT0SuccTransFee(String t0SuccTransFee) {
	this.t0SuccTransFee = t0SuccTransFee;
}

public String getT0ErrorSumAmt() {
	return t0ErrorSumAmt;
}

public void setT0ErrorSumAmt(String t0ErrorSumAmt) {
	this.t0ErrorSumAmt = t0ErrorSumAmt;
}

public String getT0ErrorTransFee() {
	return t0ErrorTransFee;
}

public void setT0ErrorTransFee(String t0ErrorTransFee) {
	this.t0ErrorTransFee = t0ErrorTransFee;
}

public String getDowloadT1OrD1FileName() {
	return dowloadT1OrD1FileName;
}

public void setDowloadT1OrD1FileName(String dowloadT1OrD1FileName) {
	this.dowloadT1OrD1FileName = dowloadT1OrD1FileName;
}

public String getWqfAmt() {
    return wqfAmt;
  }

  public void setWqfAmt(String wqfAmt) {
    this.wqfAmt = wqfAmt;
  }

  public String getClearAmt() {
    return clearAmt;
  }

  public void setClearAmt(String clearAmt) {
    this.clearAmt = clearAmt;
  }

  public String getSumAmt() {
    return sumAmt;
  }

  public void setSumAmt(String sumAmt) {
    this.sumAmt = sumAmt;
  }

  public File getXlsFile() {
	return xlsFile;
  }
	
  public void setXlsFile(File xlsFile) {
	this.xlsFile = xlsFile;
  }

  public String getSettleDate() {
	return settleDate;
  }
	
  public void setSettleDate(String settleDate) {
	this.settleDate = settleDate; 
  }

public String getChName() {
    return chName;
}

public void setChName(String chName) {
    this.chName = chName;
}

public String getFileType() {
    return fileType;
}

public void setFileType(String fileType) {
    this.fileType = fileType;
}
  
  
}
