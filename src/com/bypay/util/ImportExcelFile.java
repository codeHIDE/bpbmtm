package com.bypay.util;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Alignment;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ImportExcelFile {
	/**
     * 导出Excel
     * @param list：结果集合
     * @param filePath：指定的路径名
     * @param out：输出流对象 通过response.getOutputStream()传入
     * @param mapFields：导出字段 key:对应实体类字段    value：对应导出表中显示的中文名
     * @param sheetName：工作表名称
     */
    public static void createExcel(List list,String filePath,OutputStream out,Map<String, String> mapFields,String sheetName,
    		Map<String, String> mapAmountTo){
        sheetName = sheetName!=null && !sheetName.equals("")?sheetName:"sheet1";
        WritableWorkbook wook = null;//可写的工作薄对象
        Object objClass = null;
        try {
            if(filePath!=null && !filePath.equals("")){
                wook = Workbook.createWorkbook(new File(filePath));//指定导出的目录和文件名 如：D:\\test.xls
            }else{
                wook = Workbook.createWorkbook(out);//jsp页面导出用
            }
                                                                            
            //设置头部字体格式
            WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, 
                    false, UnderlineStyle.NO_UNDERLINE, Colour.RED);
            //应用字体表头
            WritableCellFormat wcfh = new WritableCellFormat(font);
            //设置其他样式
            wcfh.setAlignment(Alignment.CENTRE);//水平对齐
            wcfh.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直对齐
            wcfh.setBorder(Border.ALL, BorderLineStyle.THIN);//边框
            wcfh.setBackground(Colour.YELLOW);//背景色
            wcfh.setWrap(true);//不自动换行
                                                                            
            //设置内容字体格式
            WritableFont font1 = new WritableFont(WritableFont.createFont("微软雅黑"), 8 ,WritableFont.NO_BOLD);
            WritableCellFormat wcfc = new WritableCellFormat(font1);
            wcfc.setAlignment(Alignment.CENTRE);
            wcfc.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直对齐
            wcfc.setBorder(Border.ALL, BorderLineStyle.THIN);//边框
            wcfc.setWrap(false);//不自动换行
            
            //设置标题格式
            WritableFont font3 = new WritableFont(WritableFont.ARIAL, 18 ,WritableFont.BOLD);
            WritableCellFormat wcf3 = new WritableCellFormat(font3);
            wcf3.setAlignment(Alignment.CENTRE);//水平对齐
            wcf3.setVerticalAlignment(VerticalAlignment.CENTRE);//垂直对齐
                                                                            
            //创建工作表
            WritableSheet sheet = wook.createSheet(sheetName, 0);
            
            SheetSettings setting = sheet.getSettings();
            setting.setVerticalFreeze(3);//冻结窗口头部
            
            sheet.addCell(new Label(0, 0, sheetName, wcf3));
            sheet.mergeCells(0, 0, mapFields.size() -1, 1);//参数格式（开始列，开始行，结束列，结束行）
            
            int columnIndex = 0;  //列索引
            List<String> methodNameList = new ArrayList<String>();
            if(mapFields!=null){
                String key  = "";
                Map<String,Method> getMap = null;
                Method method = null;
                //开始导出表格头部
                for (Iterator<String> i = mapFields.keySet().iterator();i.hasNext();) {
                    key = i.next();
                    // 应用wcfh样式创建单元格
                    sheet.addCell(new Label(columnIndex, 2, mapFields.get(key), wcfh));
                   //设置单元格宽度，以字符为单位
                    sheet.setColumnView(columnIndex, mapFields.get(key).length() + 10);
                    //记录字段的顺序，以便于导出的内容与字段不出现偏移
                    methodNameList.add(key);
                    columnIndex++;
                }
                int inde = 0;
                if(list!=null && list.size()>0){
                    //导出表格内容
                    for (int i = 0,len = list.size(); i < len; i++) {
                    	inde = i+4;
                    	
                        objClass = list.get(i);
                        getMap = getAllMethod(objClass);//获得对象所有的get方法
                        //按保存的字段顺序导出内容
                        for (int j = 0; j < methodNameList.size(); j++) {
                            //根据key获取对应方法
                            method = getMap.get("GET"+methodNameList.get(j).toString().toUpperCase());
                            if(method!=null){
                                //从对应的get方法得到返回值
                                String value = "";
                                if(null != method.invoke(objClass, null) && !"".equals(method.invoke(objClass, null))){
                                	value = method.invoke(objClass, null).toString();
                                }
//	                            if(value.matches("\\d+\\.?\\d*")){
//	                            	Type monthType = method.getGenericReturnType();
//	                            	DecimalFormat   df   =   new   DecimalFormat( "#,##0.00 "); 
//
//	                            	if("double".equals(monthType.toString())) {
//	                            		double res = Double.parseDouble(value);
//	                            		jxl.write.Number val = new jxl.write.Number(j, i+3, res, wcfc);
////	                                    sheet.addCell(val);
//	                                    sheet.addCell(new Label(j, i+3, df.format(res), wcfc));
//	                            	}else {
//	                            		sheet.addCell(new Label(j, i+3, value, wcfc));
//	                            	}
//	                             }else {
//	                                sheet.addCell(new Label(j, i+3, value, wcfc));
//	                             }
                                sheet.addCell(new Label(j, i+3, value, wcfc));
                              }else{
                                   //如果没有对应的get方法，则默认将内容设为""
                                   sheet.addCell(new Label(j, i+3, "", wcfc));
                              }
                    
                        }
                    }
                }

                if(null != mapAmountTo && mapAmountTo.size() > 0) {
                	List listKey = new ArrayList();
        	        List listValue = new ArrayList();
        	        Iterator it = mapAmountTo.keySet().iterator();
        	        while (it.hasNext()) {
        	            String keys = it.next().toString();
        	            listKey.add(keys);
        	            listValue.add(mapAmountTo.get(keys));
        	        }
                    for (int i = 0,len = listKey.size(); i < len; i++) {
                        //按保存的字段顺序导出内容
                    	for (int j = 0; j < mapFields.size(); j++) {
               	          if(j == 0) {
               	        	 String str = (String) listKey.get(i);
                             sheet.addCell(new Label(j, i+inde, str, wcfc));
                          } else {
                        	 String str = (String) listValue.get(i);
                             sheet.addCell(new Label(j, i+inde, str, wcf3));
                             sheet.mergeCells(j, i+inde, mapFields.size() -1, i+inde);
                          }
                    	}
                    }
                }
                wook.write();
                System.out.println("导出Excel成功！");
            }else{
                throw new Exception("传入参数不合法");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            try {
                if(wook!=null){
                    wook.close();
                }
                if(out!=null){
                    out.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
                                                                    
    /**
     * 获取类的所有get方法
     * @param cls
     * @return
     */
    public static HashMap<String,Method> getAllMethod(Object obj) throws Exception{
        HashMap<String,Method> map = new HashMap<String,Method>();
        Method[] methods = obj.getClass().getMethods();//得到所有方法
        String methodName = "";
        for (int i = 0; i < methods.length; i++) {
            methodName = methods[i].getName().toUpperCase();//方法名
            if(methodName.startsWith("GET")){
                map.put(methodName, methods[i]);//添加get方法至map中
            }
        }
        return map;
    }
                                                                    
    /**
     * 根据指定路径导出Excel
     * @param list
     * @param filePath
     * @param mapFields
     * @param sheetName
     */
    public static void ImportExcel(List list,String filePath,Map<String, String> mapFields,String sheetName){
        createExcel(list,filePath,null,mapFields,sheetName,null);
    }
                                                                    
    /**
     * 从Jsp页面导出Excel
     * @param list
     * @param filePath
     * @param out
     * @param mapFields
     * @param sheetName
     */
    public static void ImportExcel(List list,OutputStream out,Map<String, String> mapFields,String sheetName,Map<String, String> mapAmountTo){
        createExcel(list,null,out,mapFields,sheetName,mapAmountTo);
    }
                                                                    
}
