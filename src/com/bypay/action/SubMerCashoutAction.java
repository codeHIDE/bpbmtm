package com.bypay.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.bypay.common.BaseAction;
import com.bypay.domain.CallBackTem;
import com.bypay.domain.RegionCode;
import com.bypay.domain.SubMerCashout;
import com.bypay.domain.SubMerInfo;
import com.bypay.service.RegionCodeService;
import com.bypay.service.SubMerCashoutService;
import com.bypay.service.SubMerInfoService;
import com.bypay.service.impl.util.StatisticsParser;
import com.bypay.util.AmountUtils;
import com.bypay.util.DateUtil;
import com.bypay.util.PageUtil;
import com.google.common.collect.Maps;

public class SubMerCashoutAction extends BaseAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Inject
  private SubMerCashoutService subMerCashoutService;
  private SubMerCashout subMerCashout;
  private List<SubMerCashout> subMerCashoutList;
  private String CashoutList;
  private String orderStatus;
  // ========分页
  private int page = 1;
  private int pageSize = 15;
  private File callBackFile;
  private String callBackFileFileName;
  @Inject
  private SubMerInfoService subMerInfoService;
  @Inject
  private RegionCodeService regionCodeService;
  @Inject
  private StatisticsParser statisticsParser;

  // SubMerCashout通道列表
  public void selectSubMerCashoutList() {
    if (subMerCashout == null) {
      subMerCashout = new SubMerCashout();
      subMerCashout.setOrderStatus("-1".equals(orderStatus) ? null : orderStatus);
    }
    System.out.println(subMerCashout.getOrderStatus());
    int count = subMerCashoutService.getSubMerCashoutListCount(subMerCashout);
    Map map = PageUtil.getPageMap(page, pageSize);
    map.put("subMerCashout", subMerCashout);
    subMerCashoutList = subMerCashoutService.getSubMerCashoutList(map);
    JSONArray array = JSONArray.fromObject(subMerCashoutList);
    JSONObject object = new JSONObject();
    object.put("Rows", array.toString());
    object.put("Total", count);
    CashoutList = object.toString();
    System.out.println(CashoutList);
    try {
      getResponse().getWriter().write(CashoutList);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void todayStatisticsDetailExcel() {
    try {
      String[] columNames = {
          "姓名", "银行名", "银行账号", "代发人手机", "证件类型", "证件号", "交易金额(单位:元)", "代发类型", "代发省份名", "代发地区名",
          "代发支行名", "代发用途", "入账子账户", "联行号" };
      String orderStatus = this.getParameterForString("orderStatus");
      if (StringUtils.isBlank(orderStatus)) {
        this.renderText("不存在!");
        return;
      }
      Map<String, Object> map = Maps.newHashMap();
      SubMerCashout subMerCashout = new SubMerCashout();
      subMerCashout.setOrderStatus(orderStatus);
      map.put("subMerCashout", subMerCashout);
      List<SubMerCashout> subMerCashouts = subMerCashoutService.selectSubMerCashoutListNotPage(map);
      if (subMerCashouts == null || subMerCashouts.size() == 0) {
        this.renderText("没有需要导出的数据!");
        return;
      }
      HttpServletResponse response = getResponse();
      OutputStream os;
      try {
        os = response.getOutputStream();
        String sheetName = "待清分记录";
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition",
            "attachment;filename=" + new String((sheetName).getBytes(), "ISO8859-1") + ".xls");
        response.setBufferSize(1024);

        WritableWorkbook book = Workbook.createWorkbook(os);
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
          Long amt = Long.parseLong(subMerCashouts.get(j).getOrderAmt())-Long.parseLong(subMerCashouts.get(j).getTransFee());
          sheet.addCell(new Label(0, row, subMerInfo.getSettAccountName()));
          sheet.addCell(new Label(1, row, subMerInfo.getSettAgency()));
          sheet.addCell(new Label(2, row, subMerInfo.getSettAccountNo()));
          sheet.addCell(new Label(3, row, subMerInfo.getContactorPhone()));
          sheet.addCell(new Label(4, row, "00"));
          sheet.addCell(new Label(5, row, subMerInfo.getLegalIdcard()));
          sheet.addCell(new Label(6, row, AmountUtils
              .changeF2Y(amt)));
          sheet.addCell(new Label(7, row, subMerInfo.getSettAccType() == "1" ? "C" : "P"));
          // 省份
//          RegionCode rc = new RegionCode();
//          rc.setId(subMerInfo.getRegion());
//          List<RegionCode> regionCodes = regionCodeService.selectRegionCodeList2(rc);
//          if (null != regionCodes && !regionCodes.isEmpty()) {
            sheet.addCell(new Label(8, row, ""));
//          }
          // 地区
//          rc = new RegionCode();
//          rc.setId(regionCodes.get(0).getSuperCode());
//          List<RegionCode> regionCodes2 = regionCodeService.selectRegionCodeList2(rc);
//          if (null != regionCodes2 && !regionCodes2.isEmpty()) {
            sheet.addCell(new Label(9, row, ""));
//          }
          sheet.addCell(new Label(10, row, "支行待定"));
          sheet.addCell(new Label(11, row, subMerCashouts.get(j).getId())); // T+0/ID/用作回传关联
          sheet.addCell(new Label(12, row, /*subMerCashouts.get(j).getSubMerId()*/""));
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
          SubMerCashout subMerCashout = new SubMerCashout();
          subMerCashout.setId(callBackTem.getUse());
          subMerCashout.setOrderStatus(status);
          subMerCashout.setReserved1(callBackTem.getTradeDesc());
          subMerCashout.setFinishTime(DateUtil.getDate(new Date(), "yyyyMMddHHmmss"));
          subMerCashoutService.updateSubMerCashout(subMerCashout);
        }

      }
      this.renderText("<script>alert('成功!');window.parent.$.ligerDialog.close();window.parent.$('.l-dialog,.l-window-mask').remove();</script>");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public SubMerCashout getSubMerCashout() {
    return subMerCashout;
  }

  public void setSubMerCashout(SubMerCashout subMerCashout) {
    this.subMerCashout = subMerCashout;
  }

  public List<SubMerCashout> getSubMerCashoutList() {
    return subMerCashoutList;
  }

  public void setSubMerCashoutList(List<SubMerCashout> subMerCashoutList) {
    this.subMerCashoutList = subMerCashoutList;
  }

  public String getCashoutList() {
    return CashoutList;
  }

  public void setCashoutList(String cashoutList) {
    CashoutList = cashoutList;
  }

  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
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

}
