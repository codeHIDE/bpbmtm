package com.bypay.service.impl.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.bypay.domain.CallBackTem;

@Component
public class StatisticsParser {
  // 日志
  Logger logger = Logger.getLogger(StatisticsParser.class);

  /**
   * 解析excel
   * 
   * @param file上传的excel文件
   * @return
   * @throws Exception
   */
  public Map<String, Object> parseExcel(File file) throws Exception {
    List<CallBackTem> list = new ArrayList<CallBackTem>();
    List<Sheet> sheetList = new ArrayList<Sheet>();
    InputStream inp = new FileInputStream(file);
    String fileName = file.getName();
    int index = fileName.lastIndexOf(".");
    String ext = fileName.substring(index);
    Workbook wb = null;
    if (".xls".equals(ext.toLowerCase())) {
      wb = new HSSFWorkbook(new POIFSFileSystem(inp));
      wb.getNumberOfSheets();
    } else if (".xlsx".equals(ext.toLowerCase())) {
      wb = new XSSFWorkbook(inp);
    }
    for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
      Sheet sheet = wb.getSheetAt(numSheet);
      if (sheet == null) {
        continue;
      }
      sheetList.add(wb.getSheetAt(numSheet));
    }
    for (Sheet sheet1 : sheetList) {
      Row row1 = sheet1.getRow(0);
      if (row1 == null)
        continue;
      for (Cell cell : row1) {
        if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
          break;
        }
      }
      for (Row row : sheet1) {
        if (row.getRowNum() > 0) {
          CallBackTem callBackTem = new CallBackTem();
          if (row.getCell(0) != null) {
            row.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setDate(row.getCell(0).getStringCellValue());
          }
          if (row.getCell(1) != null) {
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setName(row.getCell(1).getStringCellValue());
          }
          if (row.getCell(2) != null) {
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setAccountNo(row.getCell(2).getStringCellValue());
          }
          if (row.getCell(3) != null) {
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setAmt(row.getCell(3).getStringCellValue());
          }
          if (row.getCell(4) != null) {
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setStatus(row.getCell(4).getStringCellValue());
          }
          if (row.getCell(5) != null) {
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setTradeDesc(row.getCell(5).getStringCellValue());
          }
          if (row.getCell(6) != null) {
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setBankName(row.getCell(6).getStringCellValue());
          }
          if (row.getCell(7) != null) {
            row.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setUse(row.getCell(7).getStringCellValue());
          }
          if (row.getCell(8) != null) {
            row.getCell(8).setCellType(Cell.CELL_TYPE_STRING);
            callBackTem.setBatchId(row.getCell(8).getStringCellValue());
          }
          // for (Cell cell : row)
          // {
          // System.out.println(cell);
          // }
          list.add(callBackTem);
        }
      }
    }
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("list", list);
    return map;
  }

}
