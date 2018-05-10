package com.bypay.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;


public class FTPUtil {
	
	private static Logger log = Logger.getLogger(FTPUtil.class);
	
	/**
	* Description: 向FTP服务器上传文件
	* @Version1.0  20141105 9:57:09 PM by zhangwei 创建
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param path FTP服务器保存目录
	* @param filename 上传到FTP服务器上的文件名
	* @param input 输入流
	* @return 成功返回true，否则返回false
	*/ 
	public static boolean uploadFile(String ip,int port,String userName, String passWord, String path, String fileName,String remotePath) { 
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(ip, port);//连接FTP服务器  如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        log.info("登录FTP,用户名："+userName+" 密码："+passWord);
	        ftp.login(userName, passWord);//登录 
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        reply = ftp.getReplyCode(); 
	        log.info("登录返回值为："+reply);
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	        	log.info("登录FTP服务失败!");
	            ftp.disconnect(); 
	            return success; 
	        } 
	        FileInputStream in=new FileInputStream(new File(path+File.separator+fileName)); 
//	        if(!"".equals(remotePath)&&null!=remotePath){
//	        	ftp.mkd(remotePath);
//	        }
	        log.info("将文件上传至："+remotePath+fileName+"目录");
	        ftp.changeWorkingDirectory(remotePath);
	        ftp.enterLocalPassiveMode();
	        ftp.setControlEncoding("UTF-8");
	        ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); //设置文件传输流模式
	        success = ftp.storeFile(fileName, in);
	        System.out.println(success);
	        in.close(); 
	        ftp.logout(); 
	        success = true; 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return success; 
	}
	
	public static boolean uploadFileMS(String ip,int port,String userName, String passWord, String path, String fileName,String remotePath) { 
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        ftp.connect(ip, port);//连接FTP服务器  如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
	        log.info("登录FTP,用户名："+userName+" 密码："+passWord);
	        ftp.login(userName, passWord);//登录 
	        ftp.setFileType(FTP.BINARY_FILE_TYPE);
	        reply = ftp.getReplyCode(); 
	        log.info("登录返回值为："+reply);
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	        	log.info("登录FTP服务失败!");
	            ftp.disconnect(); 
	            return success; 
	        } 
	        FileInputStream in=new FileInputStream(new File(path+File.separator+fileName)); 
	        Boolean dir = ftp.changeWorkingDirectory(remotePath);//切换工作目录
	        System.out.println("change="+dir);
	        String dateDirectory = new SimpleDateFormat("yyyyMMdd").format(new Date());
	        if(!ftp.changeWorkingDirectory(dateDirectory)){
	        	dir = ftp.makeDirectory(dateDirectory);
	 	        System.out.println("make="+dir);
	 	        dir = ftp.changeWorkingDirectory(dateDirectory);
	 	        System.out.println("change="+dir);
	        }
	        
	        log.info("将文件上传至："+remotePath+"/"+dateDirectory+"/"+fileName+"目录");
	        ftp.enterLocalPassiveMode();
	        ftp.setControlEncoding("GBK");
	        ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); //设置文件传输流模式
	        success = ftp.storeFile(fileName, in);
	        System.out.println(success);
	        in.close(); 
	        ftp.logout(); 
	        success = true; 
	    } catch (IOException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) { 
	            } 
	        } 
	    } 
	    return success; 
	}
	
	/**
	 * 上传文件并且改名
	 * @Title:        uploadFileRename 
	 * @Description:  
	 * @param:        @param ip
	 * @param:        @param port
	 * @param:        @param userName
	 * @param:        @param passWord
	 * @param:        @param path
	 * @param:        @param fileName
	 * @param:        @param remotePath
	 * @param:        @return    
	 * @return:       boolean    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-8-5 下午5:48:53
	 */
	public static boolean uploadFileRename(String ip,int port,String userName, String passWord, String path, String fileName,String remotePath) { 
		boolean success = false; 
		FTPClient ftp = new FTPClient(); 
		try { 
			int reply; 
			ftp.connect(ip, port);//连接FTP服务器  如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器 
			log.info("登录FTP,用户名："+userName+" 密码："+passWord);
			ftp.login(userName, passWord);//登录 
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			reply = ftp.getReplyCode(); 
			log.info("登录返回值为："+reply);
			if (!FTPReply.isPositiveCompletion(reply)) { 
				log.info("登录FTP服务失败!");
				ftp.disconnect(); 
				return success; 
			} 
			FileInputStream in=new FileInputStream(new File(path+File.separator+fileName)); 
//	        if(!"".equals(remotePath)&&null!=remotePath){
//	        	ftp.mkd(remotePath);
//	        }
			log.info("将文件上传至："+remotePath+fileName+"目录");
			ftp.changeWorkingDirectory(remotePath);
			ftp.enterLocalPassiveMode();
			ftp.setControlEncoding("UTF-8");
			ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE); //设置文件传输流模式
			success = ftp.storeFile(fileName, in);
			System.out.println(success);
			if(success){
				success = ftp.rename(fileName, fileName.replace("_t", ""));
			}
			in.close(); 
			ftp.logout(); 
			success = true; 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} finally { 
			if (ftp.isConnected()) { 
				try { 
					ftp.disconnect(); 
				} catch (IOException ioe) { 
				} 
			} 
		} 
		return success; 
	}
	
	
	
	/**
	* Description: 从FTP服务器下载文件
	* @Version1.0  20141105 10:20:09 PM by zhangwei 创建
	* @param url FTP服务器hostname
	* @param port FTP服务器端口
	* @param username FTP登录账号
	* @param password FTP登录密码
	* @param remotePath FTP服务器上的相对路径
	* @param fileName 要下载的文件名
	* @param localPath 下载后保存到本地的路径
	* @return
	*/  
	public static boolean downFile(String ip, int port,String userName, String passWord, String remotePath,String fileName,String localPath) { 
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        if(port==0){
	        	log.info("采用默认端口连接FTP服务地址："+ip);
	        	ftp.connect(ip);
	        }else{
	        	log.info("连接FTP服务地址："+ip+" 端口："+port);
	        	ftp.connect(ip, port); 	        	
	        }
	        log.info("登录FTP,用户名："+userName+" 密码："+passWord);
	        ftp.login(userName, passWord);	//登录 
	        reply = ftp.getReplyCode(); 
	        log.info("登录返回值为："+reply);
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	        	log.info("登录FTP服务失败!");
	            ftp.disconnect(); 
	            return success; 
	        } 
	        
	       
	        
	        log.info("访问FTP服务器目录:"+File.separator+remotePath);
	        ftp.changeWorkingDirectory(File.separator+remotePath);//转移到FTP服务器目录 
	        
	        ftp.enterLocalPassiveMode();//被动模式
	        
	        FTPFile[] fs = ftp.listFiles(); 
	        log.info("成功访问FTP服务器"+File.separator+remotePath+"目录");
	        
	        log.info(remotePath+"目录下有"+fs.length+"个文件");
	        for(FTPFile ff:fs){ 
	        	if(ff.getName().equals(fileName)){ 
	            	log.info("将ftp服务器文件下载到本地目录："+localPath);
	                File localFile = new File(localPath+File.separator+ff.getName()); 
	                log.info("创建本地目录");
	                OutputStream is = new FileOutputStream(localFile);
	                log.info("将服务器文件写入本地目录");
	                ftp.retrieveFile(ff.getName(), is); 
	                is.close(); 
	            } 
	        } 
	         
	        ftp.logout(); 
	        success = true; 
	        log.info("获取FTP服务器文件成功！");
	    } catch (Exception e) { 
	        e.printStackTrace();
	        log.error("访问ftp服务异常："+e.getMessage());
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) {
	            	
	            } 
	        } 
	    } 
	    return success; 
	}
	/**
	 * 从服务器上下载所有文件
	 * @Title:        downFileMS 
	 * @Description:  
	 * @param:        @param ip
	 * @param:        @param port
	 * @param:        @param userName
	 * @param:        @param passWord
	 * @param:        @param remotePath
	 * @param:        @param fileName
	 * @param:        @param localPath
	 * @param:        @return    
	 * @return:       boolean    
	 * @throws 
	 * @author        Eason Jiang
	 * @Date          2015-8-28 下午2:05:42
	 */
	public static boolean downFileMS(String ip, int port,String userName, String passWord, String remotePath,String localPath) { 
	    boolean success = false; 
	    FTPClient ftp = new FTPClient(); 
	    try { 
	        int reply; 
	        if(port==0){
	        	log.info("采用默认端口连接FTP服务地址："+ip);
	        	ftp.connect(ip);
	        }else{
	        	log.info("连接FTP服务地址："+ip+" 端口："+port);
	        	ftp.connect(ip, port); 	        	
	        }
	        log.info("登录FTP,用户名："+userName+" 密码："+passWord);
	        ftp.login(userName, passWord);	//登录 
	        reply = ftp.getReplyCode(); 
	        log.info("登录返回值为："+reply);
	        if (!FTPReply.isPositiveCompletion(reply)) { 
	        	log.info("登录FTP服务失败!");
	            ftp.disconnect(); 
	            return success; 
	        } 
	        
	        String dateDirectory = new SimpleDateFormat("yyyyMMdd").format(new Date());
	        
	        log.info("访问FTP服务器目录:"+remotePath);
	        ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录 
	        if(!ftp.changeWorkingDirectory(dateDirectory)){
	        	return false;
	        }
	        File localFile = new File(localPath);
	        log.info("创建本地目录");
            if(!localFile.exists()){
            	localFile.mkdirs();
            }
	        ftp.enterLocalPassiveMode();//被动模式
	        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);		//设置为二进制
	        FTPFile[] fs = ftp.listFiles(); 
	        log.info("成功访问FTP服务器"+"/"+dateDirectory+"目录");
	        log.info(remotePath+"目录下有"+fs.length+"个文件");
	        for(FTPFile ff:fs){ 
	            	log.info("将ftp服务器文件下载到本地目录："+localPath);
	                File file = new File(localPath+File.separator+ff.getName()); 
	                file = new File(localPath+File.separator+ff.getName()); 
	                OutputStream is = new FileOutputStream(file);
	                log.info("将服务器文件写入本地目录");
	                ftp.retrieveFile(ff.getName(), is); 
	                is.close(); 
	        } 
	         
	        ftp.logout(); 
	        success = true; 
	        log.info("获取FTP服务器文件成功！");
	    } catch (Exception e) { 
	        e.printStackTrace();
	        log.error("访问ftp服务异常："+e.getMessage());
	    } finally { 
	        if (ftp.isConnected()) { 
	            try { 
	                ftp.disconnect(); 
	            } catch (IOException ioe) {
	            	
	            } 
	        } 
	    } 
	    return success; 
	}
	
	
	public static void main(String[] args) {
		uploadFile("222.92.117.85", 8070, "YsePay", "20150526Yse#!", "D:/", "msg.txt", "/T0/");
	}

}
