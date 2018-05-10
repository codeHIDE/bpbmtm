package com.bypay.service.task;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bypay.dao.MerInfoDao;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.dao.RegionCodeDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.RegionCode;
import com.bypay.domain.SubMerInfo;
import com.bypay.util.DateUtil;
import com.google.common.collect.Maps;

/**
 * 
 * @Description:
 * @Author: ljl
 * @Date: 2014-9-19 下午1:53:45
 */
@Component("statictisExcelComponent")
public class StatictisExcelTask {
  @Autowired
  private MerSettleStatictisDao merSettleStatictisDao;
  @Autowired
  private MerInfoDao merInfoDao;
  @Autowired
  private SubMerInfoDao subMerInfoDao;
  @Autowired
  private RegionCodeDao regionCodeDao;

  ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault()); // 存放路径

  /**
   * 
   * @Description:
   * @Auther: ljl
   * @Date: 2014-9-19 下午3:17:12
   */
  public void execute() {
    Map<String, Object> map = Maps.newHashMap();
    MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
    merSettleStatictis.setSettleDate(DateUtil.getDate(DateUtil.getPreviousWeekDay(new Date()),
        "yyyyMMdd"));
    merSettleStatictis.setMerType("0");
    map.put("merSettleStatictis", merSettleStatictis);
    List<MerSettleStatictis> merSettleStatictiss = merSettleStatictisDao
        .selectMerSettleStatictisListNotPage(map);
    for (MerSettleStatictis merSettleStatictis2 : merSettleStatictiss) {
      createExcel(merSettleStatictis2);
    }
  }

  /**
   * 
   * @Description:
   * @Auther: ljl
   * @param id
   * @Date: 2014-9-19 下午3:17:17
   */
  private void createExcel(MerSettleStatictis merSettleStatictis) {

    List<MerSettleStatictis> mapList = new ArrayList<MerSettleStatictis>();
    // 获取要存放excel表的路径
    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
    Date oldDate = new Date();
    String date = df.format(oldDate);
    // 先创建Excel文件夹
    // String input = PropUtil.getVlaue("Route");
    String input = "E://Excel";
    File temFile = new File(input);
    if (!temFile.exists()) {
      temFile.mkdir();
    }
    String inputpath = "D:\\" + date;
    // inputpath = PropUtil.getVlaue("Route") + "/" + date;
    File temFile1 = new File(inputpath);
    if (!temFile1.exists()) {
      temFile1.mkdir();
    }
    // 第一步，创建一个webbook，对应一个Excel文件
    HSSFWorkbook wb = new HSSFWorkbook();
    // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
    HSSFSheet sheet = wb.createSheet("清算统计表");
    // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
    HSSFRow row0 = sheet.createRow((int) 0);
    HSSFCell cell0 = row0.createCell((short) 0);
    cell0.setCellValue("V1.1");
    // 第四步，在sheet中添加表头第1行,注意老版本poi对Excel的行数列数有限制short
    HSSFRow row = sheet.createRow((int) 1);
    // 第五步，创建单元格，并设置值表头 设置表头居中
    HSSFFont font = wb.createFont();
    // font.setColor(HSSFColor.BLACK.index);// HSSFColor.VIOLET.index //字体颜色
    font.setFontHeightInPoints((short) 13);
    // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    HSSFCellStyle style = wb.createCellStyle();
    style.setFont(font);
    style.setFillBackgroundColor(HSSFColor.YELLOW.index);
    // style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
    // 写入代理商统计信息
    String[] fubiao = {
        "姓名", "银行名", "银行账号", "代发人手机", "证件类型", "证件号", "交易金额(单位:元)", "代发类型", "代发省份名", "代发地区名",
        "代发支行名", "代发用途", "入账子账户", "联行号" };
    HSSFCell cell = row.createCell((short) 1);
    cell.setCellStyle(style);
    for (int i = 0; i < fubiao.length; i++) {
      cell = row.createCell((short) i);
      cell.setCellValue(fubiao[i]);
      cell.setCellStyle(style);
    }
    // 第六步，写入子商户数据
    int y = 2;

    Map<String, Object> map = Maps.newHashMap();
    MerSettleStatictis mss = new MerSettleStatictis();
    mss.setMerSysId(merSettleStatictis.getMid());
    mss.setSettleDate(DateUtil.getDate(DateUtil.getPreviousWeekDay(new Date()), "yyyyMMdd"));
    mss.setMerType("1");
    map.put("merSettleStatictis", merSettleStatictis);
    List<MerSettleStatictis> merSettleStatictiss = merSettleStatictisDao
        .selectMerSettleStatictisListNotPage(map);
    for (MerSettleStatictis merSettleStatictis2 : merSettleStatictiss) {
      row = sheet.createRow((int) y);
      SubMerInfo subMerInfo = subMerInfoDao.getSubMerInfoById(merSettleStatictis2.getMid());

      row.createCell((short) 0).setCellValue(subMerInfo.getSettAccountName());
      row.createCell((short) 1).setCellValue(subMerInfo.getSettAgency());// //待定
      row.createCell((short) 2).setCellValue(subMerInfo.getSettAccountNo());
      row.createCell((short) 3).setCellValue("13888888888");
      row.createCell((short) 4).setCellValue("00");
      row.createCell((short) 5).setCellValue(subMerInfo.getLegalIdcard());
      row.createCell((short) 6).setCellValue(merSettleStatictis2.getClearAmt());
      row.createCell((short) 7).setCellValue(subMerInfo.getSettAccType() == "1" ? "C" : "P");// 1==公==C
      // 省份
      RegionCode rc = new RegionCode();
      rc.setId(subMerInfo.getRegion());
      List<RegionCode> regionCodes = regionCodeDao.selectRegionCodeList2(rc);
      if (null != regionCodes && !regionCodes.isEmpty()) {
        row.createCell((short) 8).setCellValue(regionCodes.get(0).getName());
      }
      // 地区
      rc = new RegionCode();
      rc.setId(regionCodes.get(0).getSuperCode());
      List<RegionCode> regionCodes2 = regionCodeDao.selectRegionCodeList2(rc);
      if (null != regionCodes2 && !regionCodes2.isEmpty()) {
        row.createCell((short) 9).setCellValue(regionCodes2.get(0).getName());
      }
      row.createCell((short) 10).setCellValue("支行待定");// ///待定
      row.createCell((short) 11).setCellValue("报销代发1");
      row.createCell((short) 12).setCellValue(subMerInfo.getSubMerId());
      row.createCell((short) 13).setCellValue("联行号");// ////待定

      y++;
    }
    // 第六步，将文件存到指定位置
    try {

      String paths = inputpath + "/" + merSettleStatictis.getMid() + ".xls";
      FileOutputStream fout = new FileOutputStream(paths);
      wb.write(fout);
      fout.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
