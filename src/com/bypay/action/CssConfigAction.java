package com.bypay.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.bypay.common.BaseAction;
import com.bypay.dao.CdateConfigDao;
import com.bypay.dao.FactoryRiskDao;
import com.bypay.dao.MerModelDao;
import com.bypay.dao.PlatModelDao;
import com.bypay.domain.BusType;
import com.bypay.domain.CdateConfig;
import com.bypay.domain.CssConfig;
import com.bypay.domain.FactoryCode;
import com.bypay.domain.FactoryRisk;
import com.bypay.domain.MccCode;
import com.bypay.domain.MerModel;
import com.bypay.domain.PlatModel;
import com.bypay.domain.RegionCode;
import com.bypay.domain.TerminalType;
import com.bypay.service.BusTypeService;
import com.bypay.service.CssConfigService;
import com.bypay.service.FactoryCodeService;
import com.bypay.service.MccCodeService;
import com.bypay.service.RegionCodeService;
import com.bypay.service.TerminalTypeService;
import com.bypay.util.FileUtil;
import com.bypay.util.PageUtil;

public class CssConfigAction extends BaseAction{

	@Inject
	private CssConfigService cssConfigService;
	@Inject
	private MerModelDao merModelDao;
	@Inject
	private PlatModelDao platModelDao;
	@Inject
	private CdateConfigDao cdateConfigDao;
	@Inject
	private FactoryRiskDao factoryRiskDao;
	
	private MerModel merModel;
	private PlatModel platModel;
	private String merModelListJson;
	private String platModelListJson;
	private FactoryRisk factoryRisk;
	private List<MerModel> merModelList;
	private List<PlatModel> platModelList;
	private CssConfig cssConfig;
	private List<CssConfig> cssConfigList;
	private List<FactoryRisk> factoryRiskList;
	private String csstList;
	
	private String cssId;
	private String cssName;
	private String cssUrl;
	private String remark;
	private String id;
	private File excel;
	// ========分页
	private int page = 1;
	private int pageSize = 15;
	
	private PrintWriter print;
// ****************************************************************
	//MccCode
	@Inject
	private MccCodeService mccCodeService;
	private MccCode mccCode;
	private List<MccCode> mccCodeList;
	private String mccList;
	private String mcc;	
	private String desc;	
	private String profit;
	
	//*************************************************************
	public void selectFactoryRisk(){
		int count = factoryRiskDao.selectFactoryRiskCount();
		Map map=PageUtil.getPageMap(page, pageSize);
		factoryRiskList = factoryRiskDao.selectFactoryRiskList(map);
		JSONArray array = JSONArray.fromObject(factoryRiskList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String updateFactoryRiskInit(){
		try {
			factoryRisk = factoryRiskDao.selectFactoryRiskById(factoryRisk);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updateFactoryRiskInit";
	}
	
	public void updateFactoryRisk(){
		try {
			if(factoryRisk == null){
				getResponse().getWriter().write("fone");
				return;
			}
			int i = factoryRiskDao.updateFactoryRisk(factoryRisk);
			if(i!=1){
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
	
	public void deleteFactoryRisk(){
		try {
			if(id == null){
			  this.renderText("fone");
				return;
			}
			int i = factoryRiskDao.deleteFactoryRisk(id);
			if(i != 1){
			  this.renderText("fone");
			}else{
			  this.renderText("succ");
			}
		} catch (Exception e) {
			try {
			  this.renderText("fone");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public void insertFactoryRisk(){
		try {
			if(factoryRisk == null){
				getResponse().getWriter().write("fone");
				return;
			}
			FactoryRisk factoryRisk = factoryRiskDao.selectFactoryRiskById(this.factoryRisk);
			if(factoryRisk != null){
				getResponse().getWriter().write("error");
				return;
			}
			int i = factoryRiskDao.insertFactoryRisk(this.factoryRisk);
			if(i!=1){
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
	public String addCdateConfig(){
		try {
			List<CdateConfig> cdateConfigList = FileUtil.readExcelFile(excel);
			if(cdateConfigList == null || cdateConfigList.size() == 0){
				getRequest().setAttribute("succ", "fone");
				return "addCdateConfig";
			}
			Iterator<CdateConfig> cdateConfigIt = cdateConfigList.iterator();
			while (cdateConfigIt.hasNext()) {
				CdateConfig cdateConfig = (CdateConfig) cdateConfigIt.next();
				int i = cdateConfigDao.insertCdateConfig(cdateConfig);
				if(i != 1){
					cdateConfigDao.deleteCdateConfig();
					getRequest().setAttribute("succ", "fones");
					return "addCdateConfig";
				}
			}
			getRequest().setAttribute("succ", "succ");
		} catch (Exception e) {
			getRequest().setAttribute("succ", "fone");
			e.printStackTrace();
		}
		return "addCdateConfig";
	}
	public void deleteCdateConfig(){
		try {
			cdateConfigDao.deleteCdateConfig();
			getResponse().getWriter().write("succ");
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("fone");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	// 点击添加跳转的 ACTION
	public String goToInsertCssConfig(){
		return "goToInsertCssConfig";
	}
	
	// 通道列表
	public void selectCssConfigList() {
		if (cssConfig == null) {
			cssConfig = new CssConfig();
		}
		int count = cssConfigService.selectCssConfigListCount(cssConfig);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("cssConfig", cssConfig);
		cssConfigList = cssConfigService.selectCssConfigList(map);
		JSONArray array = JSONArray.fromObject(cssConfigList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加样式码表
	public String addCssConfig(){
		if (cssConfig != null) {
//			cssConfig = new CssConfig();
//			cssConfig.setCssId(cssId);
//			cssConfig.setCssName(cssName);
//			cssConfig.setCssUrl(cssUrl);
//			cssConfig.setRemark(remark);
			try {
				print = getResponse().getWriter();
				int insert=cssConfigService.insertCssConfig(cssConfig);
				if(insert>0){
					System.out.println("添加成功！");
					print.write("1");
				}else{
					System.out.println("添加失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//删除样式
	public void deleceCss(){
		if (cssConfig == null) {
			System.out.println(id);
			cssConfig = new CssConfig();
			cssConfig.setId(id);
			try {
				print = getResponse().getWriter();
				int delect=cssConfigService.deleteCssConfig(cssConfig);
				if(delect>0){
					System.out.println("删除成功！");
					print.write("succ");
				}else{
					System.out.println("删除失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//*****************************MccCode************************************************
	
	// 点击添加跳转的 ACTION
	public String goToInsertMccCode(){
		return "goToInsertMccCode";
	}
	
	// 通道列表
	public void selectMccCodeList() {
		if (mccCode == null) {
			mccCode = new MccCode();
		}
		int count = mccCodeService.selectMccCodeListCount(mccCode);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("mccCode", mccCode);
		mccCodeList = mccCodeService.selectMccCodeList(map);
		JSONArray array = JSONArray.fromObject(mccCodeList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加样式码表
	public String addMccCode(){
		if (mccCode != null) {
//			mccCode = new MccCode();
//			mccCode.setCssId(cssId);
//			mccCode.setCssName(cssName);
//			mccCode.setCssUrl(cssUrl);
//			mccCode.setRemark(remark);
			try {
				print = getResponse().getWriter();
				int insert=mccCodeService.insertMccCode(mccCode);
				if(insert>0){
					System.out.println("添加成功！");
					print.write("1");
				}else{
					System.out.println("添加失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//删除样式
	public void delectMcc(){
		if (mccCode == null) {
			System.out.println(id);
			mccCode = new MccCode();
			mccCode.setId(id);
			try {
				print = getResponse().getWriter();
				int delect=mccCodeService.deleteMccCode(mccCode);
				if(delect>0){
					System.out.println("删除成功！");
					print.write("succ");
				}else{
					System.out.println("删除失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//************************BUS_TYPE*******************************************
	
	@Inject
	private BusTypeService busTypeService;
	private BusType busType;
	private List<BusType> busTypeList;
	private String busList;
	private String busId;
	private String busName;	
	private String busDesc;	
	private String status;	
	
	
	// 点击添加跳转的 ACTION
	public String goToInsertBusType(){
		return "goToInsertBusType";
	}
	
	// 通道列表
	public void selectBusTypeList() {
		if (busType == null) {
			busType = new BusType();
		}
		int count = busTypeService.selectBusTypeListCount(busType);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("busType", busType);
		busTypeList = busTypeService.selectBusTypeList(map);
		JSONArray array = JSONArray.fromObject(busTypeList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加样式码表
	public String addBusType(){
		if (busType != null) {
//			busType = new BusType();
//			busType.setCssId(cssId);
//			busType.setCssName(cssName);
//			busType.setCssUrl(cssUrl);
//			busType.setRemark(remark);
			busType.setStatus("1");
			try {
				print = getResponse().getWriter();
				int insert=busTypeService.insertBusType(busType);
				if(insert>0){
					System.out.println("添加成功！");
					print.write("1");
				}else{
					System.out.println("添加失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//删除样式
	public void delectBus(){
		if (busType == null) {
			System.out.println(id);
			busType = new BusType();
			busType.setBusId(busId);
			try {
				print = getResponse().getWriter();
				int delect=busTypeService.deleteBusType(busType);
				if(delect>0){
					System.out.println("删除成功！");
					print.write("succ");
				}else{
					System.out.println("删除失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
//	********************************TERMINAL_TYPE****************************************
	@Inject
	private TerminalTypeService terminalTypeService;
	private TerminalType terminalType;
	private List<TerminalType> terminalTypeList;
	private String terminalList;
	private String productId;
	private String productNmae;	
	private String productDesc;	
	
	
	// 点击添加跳转的 ACTION
	public String goToInsertTerminalType(){
		return "goToInsertTerminalType";
	}
	
	// 通道列表
	public void selectTerminalTypeList() {
		if (terminalType == null) {
			terminalType = new TerminalType();
		}
		int count = terminalTypeService.selectTerminalTypeListCount(terminalType);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("terminalType", terminalType);
		terminalTypeList = terminalTypeService.selectTerminalTypeList(map);
		JSONArray array = JSONArray.fromObject(terminalTypeList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加样式码表
	public String addTerminalType(){
		if (terminalType != null) {
//			terminalType = new TerminalType();
//			terminalType.setCssId(cssId);
//			terminalType.setCssName(cssName);
//			terminalType.setCssUrl(cssUrl);
//			terminalType.setRemark(remark);
			terminalType.setStatus("1");
			try {
				print = getResponse().getWriter();
				int insert=terminalTypeService.insertTerminalType(terminalType);
				if(insert>0){
					System.out.println("添加成功！");
					print.write("1");
				}else{
					System.out.println("添加失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//删除样式
	public void delectTerminal(){
		if (terminalType == null) {
			System.out.println(productId);
			terminalType = new TerminalType();
			terminalType.setProductId(productId);
			try {
				print = getResponse().getWriter();
				int delect=terminalTypeService.deleteTerminalType(terminalType);
				if(delect>0){
					System.out.println("删除成功！");
					print.write("succ");
				}else{
					System.out.println("删除失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
//*****************************RegionCode************************************************
	
	@Inject
	private RegionCodeService regionCodeService;
	private RegionCode regionCode;
	private List<RegionCode> regionCodeList;
	private String regionList;
	
	// 点击添加跳转的 ACTION
	public String goToInsertRegionCode(){
		return "goToInsertRegionCode";
	}
	
	// 通道列表
	public void selectRegionCodeList() {
		if (regionCode == null) {
			regionCode = new RegionCode();
		}
		int count = regionCodeService.selectRegionCodeListCount(regionCode);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("regionCode", regionCode);
		regionCodeList = regionCodeService.selectRegionCodeList(map);
		JSONArray array = JSONArray.fromObject(regionCodeList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加样式码表
	public String addRegionCode(){
		if (regionCode != null) {
//			regionCode = new RegionCode();
//			regionCode.setCssId(cssId);
//			regionCode.setCssName(cssName);
//			regionCode.setCssUrl(cssUrl);
//			regionCode.setRemark(remark);
			try {
				print = getResponse().getWriter();
				int insert=regionCodeService.insertRegionCode(regionCode);
				if(insert>0){
					System.out.println("添加成功！");
					print.write("1");
				}else{
					System.out.println("添加失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//删除样式
	public void delectRegion(){
		if (regionCode == null) {
			System.out.println(id);
			regionCode = new RegionCode();
			regionCode.setId(id);
			try {
				print = getResponse().getWriter();
				int delect=regionCodeService.deleteRegionCode(regionCode);
				if(delect>0){
					System.out.println("删除成功！");
					print.write("succ");
				}else{
					System.out.println("删除失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//*************************************平台权限码表**********************************
	public void selectPlatModelList(){
		try {
			int count = platModelDao.selectPlatModelListCount(null);
			Map map=PageUtil.getPageMap(page, pageSize);
			platModelList = platModelDao.selectPlatModelList(map);
			JSONArray array = JSONArray.fromObject(platModelList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			platModelListJson = object.toString();
			System.out.println(platModelListJson);
			try {
				getResponse().getWriter().write(platModelListJson);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletePlatModel(){
		try {
			if (platModel == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = platModelDao.deletePlatModel(platModel.getId());
			if(i != 1)
				getResponse().getWriter().write("fone");
			else
				getResponse().getWriter().write("succ");
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("fone");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void insertPlatModel(){
		try {
			if (platModel == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = platModelDao.insertPlatModel(platModel);
			if(i != 1)
				getResponse().getWriter().write("fone");
			else
				getResponse().getWriter().write("succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String updatePlatModelInit(){
		try {
			platModel = platModelDao.selectPlatModelById(platModel.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updatePlatModelInit";
	}
	
	public void updatePlatModel(){
		try {
			if (platModel == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = platModelDao.updatePlatModel(platModel);
			if(i != 1)
				getResponse().getWriter().write("fone");
			else
				getResponse().getWriter().write("succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//===============================MER_MODEL===============================
	public void selectMerModelList(){
		try {
			int count = merModelDao.selectMerModelListCount(null);
			Map map=PageUtil.getPageMap(page, pageSize);
			merModelList = merModelDao.selectMerModelList(map);
			JSONArray array = JSONArray.fromObject(merModelList);
			JSONObject object = new JSONObject();
			object.put("Rows", array.toString());
			object.put("Total", count);
			merModelListJson = object.toString();
			System.out.println(merModelListJson);
			try {
				getResponse().getWriter().write(merModelListJson);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteMerModel(){
		try {
			if (merModel == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = merModelDao.deleteMerModel(merModel.getId());
			if(i != 1)
				getResponse().getWriter().write("fone");
			else
				getResponse().getWriter().write("succ");
		} catch (Exception e) {
			try {
				getResponse().getWriter().write("fone");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void insertMerModel(){
		try {
			if (merModel == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = merModelDao.insertMerModel(merModel);
			if(i != 1)
				getResponse().getWriter().write("fone");
			else
				getResponse().getWriter().write("succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String updateMerModelInit(){
		try {
			merModel = merModelDao.selectMerModelById(merModel.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updateMerModelInit";
	}
	
	public void updateMerModel(){
		try {
			if (merModel == null) {
				getResponse().getWriter().write("fone");
				return;
			}
			int i = merModelDao.updateMerModel(merModel);
			if(i != 1)
				getResponse().getWriter().write("fone");
			else
				getResponse().getWriter().write("succ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//*****************************FactoryCode************************************************
	
	@Inject
	private FactoryCodeService factoryCodeService;
	private FactoryCode factory;
	private List<FactoryCode> factoryCodeList;
	private String factoryList;
	
	private String factoryNo;
	private String factoryName;
	

	// 点击添加跳转的 ACTION
	public String goToInsertFactoryCode(){
		return "goToInsertFactoryCode";
	}
	
	// 通道列表
	public void selectFactoryCodeList() {
		if (factory == null) {
			factory = new FactoryCode();
		}
		int count = factoryCodeService.selectFactoryCodeListCount(factory);
		Map map=PageUtil.getPageMap(page, pageSize);
		map.put("factoryCode", factory);
		factoryCodeList = factoryCodeService.selectFactoryCodeList(map);
		JSONArray array = JSONArray.fromObject(factoryCodeList);
		JSONObject object = new JSONObject();
		object.put("Rows", array.toString());
		object.put("Total", count);
		csstList = object.toString();
		System.out.println(csstList);
		try {
			getResponse().getWriter().write(csstList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//添加样式码表
	public String addFactoryCode(){
		if (factory != null) {
//			factory = new FactoryCode();
//			factoryCode.setFactoryNo(factoryNo);
//			factoryCode.setFactoryName(factoryName);
			try {
				print = getResponse().getWriter();
				int insert=factoryCodeService.insertFactoryCode(factory);
				if(insert>0){
					System.out.println("添加成功！");
					print.write("1");
				}else{
					System.out.println("添加失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//删除样式
	public void delectFactory(){
		if (factory == null) {
			System.out.println(id);
			factory = new FactoryCode();
			factory.setId(id);
			try {
				print = getResponse().getWriter();
				int delect=factoryCodeService.deleteFactoryCode(factory);
				if(delect>0){
					System.out.println("删除成功！");
					print.write("succ");
				}else{
					System.out.println("删除失败！");
					print.write("0");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	//**************************************************************************************************************
	
	public String getFactoryNo() {
		return factoryNo;
	}

	public void setFactoryNo(String factoryNo) {
		this.factoryNo = factoryNo;
	}

	public String getFactoryName() {
		return factoryName;
	}

	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	
	public CssConfig getCssConfig() {
		return cssConfig;
	}

	public void setCssConfig(CssConfig cssConfig) {
		this.cssConfig = cssConfig;
	}

	public List<CssConfig> getCssConfigList() {
		return cssConfigList;
	}

	public void setCssConfigList(List<CssConfig> cssConfigList) {
		this.cssConfigList = cssConfigList;
	}

	public String getCsstList() {
		return csstList;
	}

	public void setCsstList(String csstList) {
		this.csstList = csstList;
	}

	public String getCssId() {
		return cssId;
	}

	public void setCssId(String cssId) {
		this.cssId = cssId;
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

	public String getCssName() {
		return cssName;
	}

	public void setCssName(String cssName) {
		this.cssName = cssName;
	}

	public String getCssUrl() {
		return cssUrl;
	}

	public void setCssUrl(String cssUrl) {
		this.cssUrl = cssUrl;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public PrintWriter getPrint() {
		return print;
	}

	public void setPrint(PrintWriter print) {
		this.print = print;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MccCode getMccCode() {
		return mccCode;
	}

	public void setMccCode(MccCode mccCode) {
		this.mccCode = mccCode;
	}

	public List<MccCode> getMccCodeList() {
		return mccCodeList;
	}

	public void setMccCodeList(List<MccCode> mccCodeList) {
		this.mccCodeList = mccCodeList;
	}

	public String getMccList() {
		return mccList;
	}

	public void setMccList(String mccList) {
		this.mccList = mccList;
	}

	public String getMcc() {
		return mcc;
	}

	public void setMcc(String mcc) {
		this.mcc = mcc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getProfit() {
		return profit;
	}

	public void setProfit(String profit) {
		this.profit = profit;
	}

	public BusType getBusType() {
		return busType;
	}

	public void setBusType(BusType busType) {
		this.busType = busType;
	}


	public String getBusList() {
		return busList;
	}

	public void setBusList(String busList) {
		this.busList = busList;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public String getBusDesc() {
		return busDesc;
	}

	public void setBusDesc(String busDesc) {
		this.busDesc = busDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<BusType> getBusTypeList() {
		return busTypeList;
	}

	public void setBusTypeList(List<BusType> busTypeList) {
		this.busTypeList = busTypeList;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public TerminalType getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(TerminalType terminalType) {
		this.terminalType = terminalType;
	}

	public List<TerminalType> getTerminalTypeList() {
		return terminalTypeList;
	}

	public void setTerminalTypeList(List<TerminalType> terminalTypeList) {
		this.terminalTypeList = terminalTypeList;
	}

	public String getTerminalList() {
		return terminalList;
	}

	public void setTerminalList(String terminalList) {
		this.terminalList = terminalList;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductNmae() {
		return productNmae;
	}

	public void setProductNmae(String productNmae) {
		this.productNmae = productNmae;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public RegionCode getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(RegionCode regionCode) {
		this.regionCode = regionCode;
	}

	public List<RegionCode> getRegionCodeList() {
		return regionCodeList;
	}

	public void setRegionCodeList(List<RegionCode> regionCodeList) {
		this.regionCodeList = regionCodeList;
	}

	public String getRegionList() {
		return regionList;
	}

	public void setRegionList(String regionList) {
		this.regionList = regionList;
	}

	public CssConfigService getCssConfigService() {
		return cssConfigService;
	}

	public void setCssConfigService(CssConfigService cssConfigService) {
		this.cssConfigService = cssConfigService;
	}

	public MerModelDao getMerModelDao() {
		return merModelDao;
	}

	public void setMerModelDao(MerModelDao merModelDao) {
		this.merModelDao = merModelDao;
	}

	public MerModel getMerModel() {
		return merModel;
	}

	public void setMerModel(MerModel merModel) {
		this.merModel = merModel;
	}

	public List<MerModel> getMerModelList() {
		return merModelList;
	}

	public void setMerModelList(List<MerModel> merModelList) {
		this.merModelList = merModelList;
	}

	public MccCodeService getMccCodeService() {
		return mccCodeService;
	}

	public void setMccCodeService(MccCodeService mccCodeService) {
		this.mccCodeService = mccCodeService;
	}

	public BusTypeService getBusTypeService() {
		return busTypeService;
	}

	public void setBusTypeService(BusTypeService busTypeService) {
		this.busTypeService = busTypeService;
	}

	public TerminalTypeService getTerminalTypeService() {
		return terminalTypeService;
	}

	public void setTerminalTypeService(TerminalTypeService terminalTypeService) {
		this.terminalTypeService = terminalTypeService;
	}

	public RegionCodeService getRegionCodeService() {
		return regionCodeService;
	}

	public void setRegionCodeService(RegionCodeService regionCodeService) {
		this.regionCodeService = regionCodeService;
	}

	public PlatModelDao getPlatModelDao() {
		return platModelDao;
	}

	public void setPlatModelDao(PlatModelDao platModelDao) {
		this.platModelDao = platModelDao;
	}

	public PlatModel getPlatModel() {
		return platModel;
	}

	public void setPlatModel(PlatModel platModel) {
		this.platModel = platModel;
	}

	public String getMerModelListJson() {
		return merModelListJson;
	}

	public void setMerModelListJson(String merModelListJson) {
		this.merModelListJson = merModelListJson;
	}

	public List<PlatModel> getPlatModelList() {
		return platModelList;
	}

	public void setPlatModelList(List<PlatModel> platModelList) {
		this.platModelList = platModelList;
	}

	public String getPlatModelListJson() {
		return platModelListJson;
	}

	public void setPlatModelListJson(String platModelListJson) {
		this.platModelListJson = platModelListJson;
	}

	public List<FactoryCode> getFactoryCodeList() {
		return factoryCodeList;
	}

	public void setFactoryCodeList(List<FactoryCode> factoryCodeList) {
		this.factoryCodeList = factoryCodeList;
	}

	public String getFactoryList() {
		return factoryList;
	}

	public void setFactoryList(String factoryList) {
		this.factoryList = factoryList;
	}

	public FactoryCode getFactory() {
		return factory;
	}

	public void setFactory(FactoryCode factory) {
		this.factory = factory;
	}
	public CdateConfigDao getCdateConfigDao() {
		return cdateConfigDao;
	}
	public void setCdateConfigDao(CdateConfigDao cdateConfigDao) {
		this.cdateConfigDao = cdateConfigDao;
	}
	public File getExcel() {
		return excel;
	}
	public void setExcel(File excel) {
		this.excel = excel;
	}
	public FactoryCodeService getFactoryCodeService() {
		return factoryCodeService;
	}
	public void setFactoryCodeService(FactoryCodeService factoryCodeService) {
		this.factoryCodeService = factoryCodeService;
	}
	public FactoryRiskDao getFactoryRiskDao() {
		return factoryRiskDao;
	}
	public void setFactoryRiskDao(FactoryRiskDao factoryRiskDao) {
		this.factoryRiskDao = factoryRiskDao;
	}
	public FactoryRisk getFactoryRisk() {
		return factoryRisk;
	}
	public void setFactoryRisk(FactoryRisk factoryRisk) {
		this.factoryRisk = factoryRisk;
	}
	public List<FactoryRisk> getFactoryRiskList() {
		return factoryRiskList;
	}
	public void setFactoryRiskList(List<FactoryRisk> factoryRiskList) {
		this.factoryRiskList = factoryRiskList;
	}
	
}
