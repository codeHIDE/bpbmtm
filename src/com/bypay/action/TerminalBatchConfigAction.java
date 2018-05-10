package com.bypay.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import net.sf.json.*;

import com.bypay.common.BaseAction;
import com.bypay.dao.*;
import com.bypay.domain.*;
import com.bypay.domain.clientTool.GenerateTsm;
import com.bypay.util.*;

@SuppressWarnings("serial")
public class TerminalBatchConfigAction extends BaseAction{
	@Inject
	private MerInfoDao merInfoDao;
	private MerInfo merInfo;
	private FactoryCodeDao factoryCodeDao;
	private List<FactoryCode> factoryCodeList;
	private List<MerInfo> merInfoList;
	@Inject
	private TerminalBatchConfigDao terminalBatchConfigDao;
	@Inject
	private SubMerTerminalDao subMerTerminalDao;
	@Inject
	private XmlUtilnew xmlUtilnew;
	private TerminalBatchConfig terminalBatchConfig;
	private int page=1;//分页
	private int pageSize=15;
	private List<TerminalBatchConfig> terminalBatchConfigList;
	ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault());
	ResourceBundle rb2 = ResourceBundle.getBundle("com/bypay/config/path", Locale.getDefault());
	@SuppressWarnings("unchecked")
	public String addTerminalBatchConfigTopSelectMerSysId(){
		merInfoList=merInfoDao.selectMerInfoByAllSysId(merInfo);	//机构查询
		Map map=new HashMap();
		map.put("page", "-1");
		map.put("pageSize", "-1");
		factoryCodeList=factoryCodeDao.selectFactoryCodeList(map);
		return "OK";
	}
	//添加
	public void insertTerminalBatchConfig() throws UnsupportedEncodingException{
		try {
			System.out.println("1");
			if(terminalBatchConfig!=null){
				GenerateTsm generateTsm = new GenerateTsm();
				generateTsm.setApplication("GenerateTsm.Req");
				generateTsm.setVersion("1.0.0");
				generateTsm.setSendTime(DateUtil.getDate("yyyyMMddHHmmss"));
				generateTsm.setSendSeqId("346747478");
				generateTsm.setFactoryId(terminalBatchConfig.getFactoryId());
				terminalBatchConfig.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
				terminalBatchConfig.setStatus("0");
				terminalBatchConfigDao.insertTerminalBatchConfig(terminalBatchConfig);
				generateTsm.setFilePath(terminalBatchConfig.getMerSysId()+"_"+terminalBatchConfig.getBatchId()+".txt");
				generateTsm.setTerminalType(terminalBatchConfig.getReserved1());
				generateTsm.setNumber(terminalBatchConfig.getCreateNum());
				String data = xmlUtilnew.ObjToXml(generateTsm);
				System.out.println(data);
				RefundUtil refundUtil = new RefundUtil();
				try {
					data = refundUtil.encrypt(data, "11");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				RemoteAccessor remoteAccessor = new RemoteAccessor();
				try {
					String ddd = remoteAccessor.getResponseByStream(rb2.getString("TerminalUrl"), "utf-8", data);
					System.out.println("====="+ddd);
					String str[]= ddd.split("\\|");
					TerminalBatchConfig terminalBatchConfig1 = new TerminalBatchConfig();
					terminalBatchConfig1.setBatchId(terminalBatchConfig.getBatchId());
					if("0".equals(str[0])){
						BASE64Decoder decoder = new BASE64Decoder();
						System.out.println(new String(decoder.decodeBuffer(str[2]),"UTF-8"));
						terminalBatchConfig1.setStatus("2");
						terminalBatchConfig1.setReserved2(new String(decoder.decodeBuffer(str[2]),"UTF-8"));
						getResponse().getWriter().write("fone");
					}else{
						terminalBatchConfig1.setStatus("1");
						terminalBatchConfig1.setFileName(terminalBatchConfig.getMerSysId()+"_"+terminalBatchConfig.getBatchId()+".txt");
						getResponse().getWriter().write("succ");
					}
					terminalBatchConfigDao.updateTerminalBatchConfig(terminalBatchConfig1);
				} catch (Exception e) {
					getResponse().getWriter().write("fone");
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//查询所有
	@SuppressWarnings("unchecked")
	public void selectTerminalBatchConfig(){
		try {
			if(terminalBatchConfig==null){
				terminalBatchConfig=new TerminalBatchConfig();
			}
			Map map= PageUtil.getPageMap(page,pageSize);
			map.put("terminalBatchConfig", terminalBatchConfig);
			int count = terminalBatchConfigDao.selectCountTerminalBatchConfig(map);
			terminalBatchConfigList = terminalBatchConfigDao.selectTerminalBatchConfig(map);
			JSONArray array = JSONArray.fromObject(terminalBatchConfigList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			String subMerList=object.toString();
			getResponse().getWriter().write(subMerList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//下载
	@SuppressWarnings("unchecked")
	public String download(){
		try {
			getRequest().removeAttribute("mess");
			if(terminalBatchConfig!=null){
				Map map=new HashMap();
				map.put("terminalBatchConfig", terminalBatchConfig);
				terminalBatchConfigList = terminalBatchConfigDao.selectTerminalBatchConfig(map);
				// 下载本地文件名
				String fileName=terminalBatchConfigList.get(0).getFileName();
//				String fileName="123.TXT";
				System.out.println(fileName);
				if(fileName!=null){
					//为文件名加上路径
					fileName=rb.getString("FileUrl")+"/TerminalInfo/"+fileName;
					HttpServletResponse response=getResponse();
					// fileName是指欲下载的文件的路径。
		            File file = new File(fileName);
//		            // 取得文件名。
		            String filename = file.getName();
		            // 取得文件的后缀名。
		            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
		            System.out.println(ext);
		            // 以流的形式下载文件。
		            FileInputStream fs = new FileInputStream(file);
		            response.setContentType("APPLICATION/OCTET-STREAM"); 
		            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		            //写出流信息
		            int b = 0;
		            try {
		            	PrintWriter out = response.getWriter();
			            while((b=fs.read())!=-1) {
			            	out.write(b);
			            }
			            fs.close();
			            out.close();
		            }
			           catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}catch (Exception e) {
			getRequest().setAttribute("mess", "2");
			e.printStackTrace();
		}
		return "selectTerminalBatchConfigAll";
	}
	//生成终端
	public String buildFile(){
		//存储重复的TSN
		String [] tsnValue=new String[5000];
		//文件路径和文件名
		String fileName="";
		if(terminalBatchConfig!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("terminalBatchConfig", terminalBatchConfig);
			terminalBatchConfigList = terminalBatchConfigDao.selectTerminalBatchConfig(map);
			TerminalBatchConfig tbc=terminalBatchConfigList.get(0);
			// 下载本地文件名
			fileName=tbc.getFileName();
			terminalBatchConfig=tbc;
			System.out.println(fileName);
			if(fileName!=null){
				//为文件名加上路径
				fileName=rb.getString("FileUrl")+"/TerminalInfo/"+fileName;
				// 判断文件夹是否在
				File uploadfile = new File(fileName);
				BufferedReader reader = null;
				if (!uploadfile.exists()) {
					// 不存在
					getRequest().setAttribute("mess", "4");
					return "selectTerminalBatchConfigAll";
				}
			try {
				System.out.println("以行为单位读取文件内容，一次读一整行：");
	            reader = new BufferedReader(new FileReader(uploadfile));
	            String tempString = null;
	            int line = 1;
	            int b=0;
	            // 一次读入一行，直到读入null为文件结束
	            while ((tempString = reader.readLine()) != null) {
	            	tempString = tempString.trim().replace(" ", "");
	                // 显示行号和行内容
	                System.out.println("line " + line + ": " + tempString);
	                System.out.println("line " + line + ": " + tempString.length());
	                SubMerTerminal smt=new SubMerTerminal();
	                smt.setTsn(tempString);
	                smt.setFactory(tbc.getFactoryId());
	            	System.out.println((smt.getTsn()==null)+"：：：：：："+(smt.getTsn()=="")+"：：：：：："+("".equals(smt.getTsn())));
	            	if(smt.getTsn()==null||smt.getTsn()==""||"".equals(smt.getTsn()))
	                	break;
	            	SubMerTerminal smt1=subMerTerminalDao.selectSubMerTerminalByTerminalId(smt);
	                if(smt1==null){//判断将要添加的TSN是否已存在
		                //将终端存入数据库
		                if(tempString!=null&&tempString!=""){
		                	smt=new SubMerTerminal();
			                smt.setTsn(tempString);						//TSN
			                smt.setMerSysId(tbc.getMerSysId());			//机构商号
			                smt.setFactory(tbc.getFactoryId());			//厂商
			                smt.setCategory(tbc.getReserved1());		//类别
			                if("19".equals(tbc.getFactoryId()))			//状态
			                	smt.setStatus("1");	
			                else 
			                	smt.setStatus("0");						
			                smt.setSubMerId("-1");						//子商户
			                smt.setAgentIdL1("-1");						//一级代理商
			                smt.setAgentIdL2("-1");						//二级代理商
			                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			                smt.setCreateTime(df.format(new Date()));	//创建时间
			                smt.setLastUpdateTime(df.format(new Date()));//最后一次修改时间
			                int number=0;
			                try {
								number=subMerTerminalDao.insertSubMerTerminal(smt);
							} catch (Exception e) {
								e.printStackTrace();
								tsnValue[b]=tempString;
								b++;
								continue;
							}
			                if(number==0){//增加失败
			                	tsnValue[b]=tempString;
								b++;
								continue;
			                }
		                }
	                }else{
	                	System.out.println("重复的TSN::"+smt.getTsn());
	                	tsnValue[b]=tempString;
	                	b++;
	                }
	                //下一行
	                line++;
	            }
	            reader.close();//关闭BufferedReader
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
	            if (reader != null) {
	                try {
	                    reader.close();
	                } catch (IOException e1) {
	                	getRequest().setAttribute("mess", "6");
	                	e1.printStackTrace();
	                }
	             }
	        }
			if(tsnValue[0] != null){//有重复的TSN
				if(Generation(tsnValue,tbc.getFileName())>0){
					getRequest().setAttribute("mess", "8");
				}else
					getRequest().setAttribute("mess", "5");
				tbc.setStatus("2");
				tbc.setReserved2(null);
				tbc.setFileName(null);
				terminalBatchConfigDao.updateTerminalBatchConfig(tbc);
			}else{//没重复的TSN  修改终端生成批次信息的状态
				tbc.setStatus("3");
				tbc.setReserved2(null);
				tbc.setFileName(null);
				if(terminalBatchConfigDao.updateTerminalBatchConfig(tbc)>0){
					getRequest().setAttribute("mess", "7");//终端生成批次状态修改成功
				}else{
					getRequest().setAttribute("mess", "9");//终端生成批次状态修改失败
				}
			}
			}
		}
		return "selectTerminalBatchConfigAll";
	}
	/**
	 * public static void main(String[] args) {
		TerminalBatchConfigAction tbca=new TerminalBatchConfigAction();
		String[] filein={"1","2","3","4","5","6","7","8"};
		String url="D:\\TSN\\173665683923264_116.txt";
		tbca.Generation(filein, url);
	   }
	 * 将重复的tsn生成txt文本文档
	 * @param filein   tsn数组
	 * @param url    文件路径
	 */
	public int Generation(String [] filein,String fileName){  
		String url=rb.getString("FileUrl")+"/TerminalInfo/"+"ERROR_"+fileName;
		File file = new File(url);    
		RandomAccessFile mm = null;  
		try {
			if(!file.exists() && !file.isDirectory())
			new File("D://Repeat_TSN").mkdirs();
			mm = new RandomAccessFile(file,"rw"); 
			mm.setLength(mm.length()+filein.length);
			for (int i = 0; i < filein.length; i++) {
				if(filein[i]!=null)
					mm.writeBytes(filein[i]+"\r\n");
			}
		} catch (IOException e1) {   
			// TODO 自动生成 catch 块   
			e1.printStackTrace();  
			return 0;
		} finally{   
			if(mm!=null){    
				try {     
					mm.close();    
				} catch (IOException e2) {
					// TODO 自动生成 catch 块    
					e2.printStackTrace(); 
					return 0;
				}
			}  
		}
		return 1;
	}
	
	//下载Key
	@SuppressWarnings("unchecked")
	public String downloadKey(){
		try {
			getRequest().removeAttribute("mess");
			if(terminalBatchConfig!=null){
				Map map=new HashMap();
				map.put("terminalBatchConfig", terminalBatchConfig);
				terminalBatchConfigList = terminalBatchConfigDao.selectTerminalBatchConfig(map);
				// 下载文件名
				String fileName=terminalBatchConfigList.get(0).getFileName();
				fileName=fileName.substring(0,fileName.indexOf("."));
				fileName=fileName+"_Key.txt";
//				String fileName="123.TXT";
				System.out.println(fileName);
				if(fileName!=null){
					//为文件名加上路径
					fileName=rb.getString("FileUrl")+"/TerminalInfo/"+fileName;
//					fileName="D://usr//"+fileName;
					HttpServletResponse response=getResponse();
					// fileName是指欲下载的文件的路径。
		            File file = new File(fileName);
//		            // 取得文件名。
		            String filename = file.getName();
		            // 取得文件的后缀名。
		            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
		            System.out.println(ext);
		            // 以流的形式下载文件。
		            FileInputStream fs = new FileInputStream(file);
		            response.setContentType("APPLICATION/OCTET-STREAM"); 
		            response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
		            //写出流信息
		            int b = 0;
		            try {
		            	PrintWriter out = response.getWriter();
			            while((b=fs.read())!=-1) {
			            	out.write(b);
			            }
			            fs.close();
			            out.close();
		            }
			           catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}catch (Exception e) {
			getRequest().setAttribute("mess", "2");
			e.printStackTrace();
		}
		return "selectTerminalBatchConfigAll";
	}
	
	public TerminalBatchConfig getTerminalBatchConfig() {
		return terminalBatchConfig;
	}
	public FactoryCodeDao getFactoryCodeDao() {
		return factoryCodeDao;
	}
	public void setFactoryCodeDao(FactoryCodeDao factoryCodeDao) {
		this.factoryCodeDao = factoryCodeDao;
	}
	public List<FactoryCode> getFactoryCodeList() {
		return factoryCodeList;
	}
	public void setFactoryCodeList(List<FactoryCode> factoryCodeList) {
		this.factoryCodeList = factoryCodeList;
	}
	public void setTerminalBatchConfig(TerminalBatchConfig terminalBatchConfig) {
		this.terminalBatchConfig = terminalBatchConfig;
	}
	public TerminalBatchConfigDao getTerminalBatchConfigDao() {
		return terminalBatchConfigDao;
	}
	public void setTerminalBatchConfigDao(
			TerminalBatchConfigDao terminalBatchConfigDao) {
		this.terminalBatchConfigDao = terminalBatchConfigDao;
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
	public List<TerminalBatchConfig> getTerminalBatchConfigList() {
		return terminalBatchConfigList;
	}
	public void setTerminalBatchConfigList(
			List<TerminalBatchConfig> terminalBatchConfigList) {
		this.terminalBatchConfigList = terminalBatchConfigList;
	}
	public MerInfoDao getMerInfoDao() {
		return merInfoDao;
	}
	public void setMerInfoDao(MerInfoDao merInfoDao) {
		this.merInfoDao = merInfoDao;
	}
	public MerInfo getMerInfo() {
		return merInfo;
	}
	public void setMerInfo(MerInfo merInfo) {
		this.merInfo = merInfo;
	}
	public List<MerInfo> getMerInfoList() {
		return merInfoList;
	}
	public void setMerInfoList(List<MerInfo> merInfoList) {
		this.merInfoList = merInfoList;
	}
	public SubMerTerminalDao getSubMerTerminalDao() {
		return subMerTerminalDao;
	}
	public void setSubMerTerminalDao(SubMerTerminalDao subMerTerminalDao) {
		this.subMerTerminalDao = subMerTerminalDao;
	}
	public ResourceBundle getRb() {
		return rb;
	}
	public void setRb(ResourceBundle rb) {
		this.rb = rb;
	}
}
