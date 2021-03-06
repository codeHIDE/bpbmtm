package com.bypay.util;

import java.io.*;
import java.util.*;

import jxl.Sheet;
import jxl.Workbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bypay.domain.CdateConfig;


public class FileUtil {

	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 读取上传的对账文件
	 * 
	 * @param file
	 * @return
	 */
	public static List<CdateConfig> readExcelFile(File file) {
		try {
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			int columnsLength = sheet.getColumns();
			int rowsCount = sheet.getRows();

			List<CdateConfig> cdateConfigList = new ArrayList<CdateConfig>();

			for (int i = 2; i < rowsCount; i++) {// 忽略第一和第二行
				CdateConfig cdateConfig = new CdateConfig();
				for (int j = 1; j < columnsLength; j++) {
					switch (j) {
						case 1:
							cdateConfig.setCdate(sheet.getCell(j, i).getContents());
							continue;
						case 2:
							cdateConfig.setWeekdy(sheet.getCell(j, i).getContents());
							continue;
						case 3:
							cdateConfig.setIsWork(sheet.getCell(j, i).getContents());
							continue;
						case 4:
							cdateConfig.setIsXend(sheet.getCell(j, i).getContents());
							continue;
						case 5:
							cdateConfig.setIsMend(sheet.getCell(j, i).getContents());
							continue;
						case 6:
							cdateConfig.setIsQend(sheet.getCell(j, i).getContents());
							continue;
						case 7:
							cdateConfig.setIsHend(sheet.getCell(j, i).getContents());
							continue;
						case 8:
							cdateConfig.setIsYend(sheet.getCell(j, i).getContents());
							continue;
						default:
							break;
					}
				}
				cdateConfigList.add(cdateConfig);
			}
			return cdateConfigList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
	
	
	/**
	 * 
	 * @param path 生成文件路径  
	 * @param fileName  文件名称
	 * @param type   文件类型
	 * 
	 * 返回true 生成文件成功，返回false 文件生成失败
	 */
	public static boolean generateFile(String content, String path, String fileName, String type){
		boolean falg = false;
		File filePath = new File(path);
		if( !filePath.exists() ){
			logger.info("路径"+path+"不存在创建文件路径");
			filePath.mkdirs();
		}
		try {
			
		File file = new File(path+File.separator+fileName+"."+type);
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		osw.write(content);
		osw.flush();
		
		osw.close();
		fos.close();
		falg = true;
		} catch (Exception e) {
			System.out.println("生成文件异常");
			e.printStackTrace();
			
		}
		
		return falg;
	}
	
	public static boolean generateFileGBK(String content, String path, String fileName, String type){
		boolean falg = false;
		File filePath = new File(path);
		if( !filePath.exists() ){
			logger.info("路径"+path+"不存在创建文件路径");
			filePath.mkdirs();
		}
		try {
			
		File file = new File(path+File.separator+fileName+"."+type);
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
		osw.write(content);
		osw.flush();
		
		osw.close();
		fos.close();
		falg = true;
		} catch (Exception e) {
			System.out.println("生成文件异常");
			e.printStackTrace();
			
		}
		
		return falg;
	}
	
	public static void main(String[] args) {
		generateFile("测试文件", "D:/T+0/text", "test", "txt");
	}
}
