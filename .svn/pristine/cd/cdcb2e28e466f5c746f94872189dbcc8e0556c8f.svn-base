package com.bypay.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.domain.MerInfo;
import com.bypay.domain.MerTerminalInfo;
import com.bypay.domain.TractInfo;
import com.bypay.service.MerInfoService;
import com.bypay.service.MerTerminalInfoService;
import com.bypay.util.DateUtil;
import com.bypay.util.PageUtil;

public class MerTerminalInfoAction extends BaseAction{
	
	private MerTerminalInfo  merTerminalInfo;
	@Inject
	private MerTerminalInfoService merTerminalInfoService;	
	@Inject
	private MerInfoService merInfoService;
	private MerInfo merInfo;
	private List<MerInfo> merInfoList;
	private List<MerTerminalInfo> merTerminalInfoList;
	private String terminalInfoList;
	// ========分页
	private int page = 1;
	private int pageSize = 15;
	
	private String merSysId;
	private String category;
	
	//点击配置终端跳转的ACTION 查询机构表的机构号带到添加页面
	public String goAddMerTerminalInfo(){
		merInfoList=merInfoService.selectMerInfoByAllSysId(merInfo);
		return "goAddMerTerminalInfo";
	}
	
	//添加终端信息
	public String addMerTerminalInfo(){
		int insert = 0;
		if(merTerminalInfo==null){
			merTerminalInfo=new MerTerminalInfo();
		}
		merTerminalInfo.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss")); // 通道创建日期
		merTerminalInfo.setStatus("0");
		insert=merTerminalInfoService.insertMerTerminalInfo(merTerminalInfo);
		if (insert > 0) {
			getRequest().setAttribute("addMerTerminalInfo", "success");
			System.out.println("添加终端信息成功");
		} else {
			getRequest().setAttribute("addMerTerminalInfo", "fail");
			System.out.println("添加终端信息失败");
		}
		System.out.println(insert);
		merInfoList=merInfoService.selectMerInfoByAllSysId(merInfo);
		return "addMerTerminalInfo";
	}
		
	// 通道列表
	public void selectMerTerminalInfoAllList() {
		if (merTerminalInfo == null) {
			merTerminalInfo = new MerTerminalInfo();
			merTerminalInfo.setMerSysId(merSysId);
			merTerminalInfo.setCategory("-1".equals(category) ? null : category);
		}
		int count = merTerminalInfoService.selectMerTerminalInfoCount(merTerminalInfo);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("merTerminalInfo", merTerminalInfo);
		merTerminalInfoList = merTerminalInfoService.selectMerTerminalInfoList(map);
		JSONArray array = JSONArray.fromObject(merTerminalInfoList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		terminalInfoList = object.toString();
		System.out.println(terminalInfoList);
		try {
			getResponse().getWriter().write(terminalInfoList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询指定机构终端信息
	 * @return
	 */
	public String selectMerTerminalInfoOne(){
		System.out.println("指定信息ID："+merTerminalInfo.getId());
		try {
			merTerminalInfo = merTerminalInfoService.selectMerTerminalInfoOne(merTerminalInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectMerTerminalInfoOne";
	}
	/**
	 * 修改前查询信息
	 * @return
	 */
	public String selectMerTerminalInfoOneUpdate(){
		try {
			System.out.println("修改前查询机构终端");
			selectMerTerminalInfoOne();
			if(merTerminalInfo!=null){
				//处理文件名
				int index=merTerminalInfo.getFileName().indexOf(".");
				if(index>0){
					String fileType=merTerminalInfo.getFileName().substring(index);
					merTerminalInfo.setFileType(fileType);
					String fileName=merTerminalInfo.getFileName().substring(0,index);
					merTerminalInfo.setFileName(fileName);
					System.out.println("文件名L::::"+fileName);
					System.out.println("文件类型::::"+fileType);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "selectMerTerminalInfoOneUpdate";
	}
	/**
	 * 修改信息
	 */
	public void updateMerTerminalInfo(){
		try {
			System.out.println("修改信息开始：：：");
			if(merTerminalInfoService.updateMerTerminalInfo(merTerminalInfo)>0){
				getResponse().getWriter().write("succ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MerTerminalInfo getMerTerminalInfo() {
		return merTerminalInfo;
	}

	public void setMerTerminalInfo(MerTerminalInfo merTerminalInfo) {
		this.merTerminalInfo = merTerminalInfo;
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

	public List<MerTerminalInfo> getMerTerminalInfoList() {
		return merTerminalInfoList;
	}

	public void setMerTerminalInfoList(List<MerTerminalInfo> merTerminalInfoList) {
		this.merTerminalInfoList = merTerminalInfoList;
	}

	public String getTerminalInfoList() {
		return terminalInfoList;
	}

	public void setTerminalInfoList(String terminalInfoList) {
		this.terminalInfoList = terminalInfoList;
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

	public String getMerSysId() {
		return merSysId;
	}

	public void setMerSysId(String merSysId) {
		this.merSysId = merSysId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
