package com.bypay.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;

import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.dao.AgenctInfoDao;
import com.bypay.dao.AppTractInfoDao;
import com.bypay.dao.BankBehalfBranchDao;
import com.bypay.dao.CssConfigDao;
import com.bypay.dao.MccCodeDao;
import com.bypay.dao.MerAppTractDao;
import com.bypay.dao.MerInfoDao;
import com.bypay.dao.MerInfoUpdateLogDao;
import com.bypay.dao.MerManagerDao;
import com.bypay.dao.MerModelDao;
import com.bypay.dao.MerRiskDao;
import com.bypay.dao.MerSettleStatictisDao;
import com.bypay.dao.MerTractDao;
import com.bypay.dao.MerTransDao;
import com.bypay.dao.PayBankCodeDao;
import com.bypay.dao.PlatMerInfoDao;
import com.bypay.dao.PlatModelDao;
import com.bypay.dao.RegionCodeDao;
import com.bypay.dao.RulesMerAmtDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.dao.SubMerRateDao;
import com.bypay.dao.SysOpLogDao;
import com.bypay.dao.TractInfoDao;
import com.bypay.domain.AgenctInfo;
import com.bypay.domain.AppTractInfo;
import com.bypay.domain.BankBehalfBranch;
import com.bypay.domain.BusType;
import com.bypay.domain.CssConfig;
import com.bypay.domain.MccCode;
import com.bypay.domain.MerAppTract;
import com.bypay.domain.MerInfo;
import com.bypay.domain.MerInfoUpdate;
import com.bypay.domain.MerInfoUpdateLog;
import com.bypay.domain.MerManager;
import com.bypay.domain.MerModel;
import com.bypay.domain.MerRisk;
import com.bypay.domain.MerSettleStatictis;
import com.bypay.domain.MerTract;
import com.bypay.domain.MerTrans;
import com.bypay.domain.PayBankCode;
import com.bypay.domain.PlatMerInfo;
import com.bypay.domain.PlatModel;
import com.bypay.domain.RegionCode;
import com.bypay.domain.RulesMerAmt;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SubMerRate;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysOpLog;
import com.bypay.domain.TerminalType;
import com.bypay.domain.TractInfo;
import com.bypay.domain.clientTool.TransInfoReq;
import com.bypay.service.BusTypeService;
import com.bypay.service.MccCodeService;
import com.bypay.service.MerInfoUpdateService;
import com.bypay.service.RegionCodeService;
import com.bypay.service.SysManagerService;
import com.bypay.service.TerminalTypeService;
import com.bypay.util.AmountUtils;
import com.bypay.util.DateUtil;
import com.bypay.util.JsonUtil;
import com.bypay.util.Md5Util;
import com.bypay.util.PageUtil;
import com.bypay.util.PropertiesUtils;

public class MerchantInfoAction extends BaseAction {
  @Inject
  private TerminalTypeService terminalTypeService;
  @Inject
  private MccCodeService mccCodeService;
  @Inject
  private BusTypeService busTypeService;
  @Inject
  private MerInfoDao merInfoDao;
  @Inject
  private PlatMerInfoDao platMerInfoDao;
  @Inject
  private SubMerInfoDao subMerInfoDao;
  @Inject
  private AgenctInfoDao agenctInfoDao;
  @Inject
  private MerTractDao merTractDao;
  @Inject
  private TractInfoDao tractInfoDao;
  @Inject
  private MerRiskDao merRiskDao;
  @Inject
  private AppTractInfoDao appTractInfoDao;
  @Inject
  private MerAppTractDao merAppTractDao;
  @Inject
  private MerTransDao merTransDao;
  @Inject
  private BankBehalfBranchDao bankBehalfBranchDao;
  @Inject
  private MerManagerDao merManagerDao;
  @Inject
  private MerModelDao merModelDao;
  @Inject
  private PlatModelDao platModelDao;
  @Inject
  private CssConfigDao cssConfigDao;
  @Inject
  private SysManagerService sysManagerService;
  @Inject
  private SysOpLogDao sysOpLogDao;
  @Inject
  private MerInfoUpdateService merInfoUpdateService;
  @Inject
  private SubMerRateDao subMerRateDao;
  @Inject
  private MerSettleStatictisDao merSettleStatictisDao;
  @Inject
  private PayBankCodeDao payBankCodeDao;
  @Inject
  private MerInfoUpdateLogDao merInfoUpdateLogDao;
  @Inject
  private RegionCodeDao regionCodeDao;
  @Inject
  private MccCodeDao mccCodeDao;
  @Inject
  private RegionCodeService regionCodeService;
  @Inject
  private RulesMerAmtDao rulesMerAmtDao;
  private MerInfoUpdateLog merInfoUpdateLog;
  private MerInfo merchantInfo;// 机构商实体类
  private PlatMerInfo platMerInfo;// 平台商实体类
  private SubMerInfo subMerInfo;// 子商户实体类
  private AgenctInfo agenctInfo;// 代理商实体类
  private MerTract merTract;
  private MerTract merTract2;
  private TractInfo tractInfo;
  private MerRisk merRisk;
  private AppTractInfo appTractInfo;
  private MerAppTract merAppTract;
  private MerTrans merTrans;
  private MerManager merManager;
  private MerModel merModel;
  private SysManager sysManager;
  private MerInfoUpdate merInfoUpdate;
  private SubMerRate subMerRate;
  private SubMerRate subMerRate2;
  private TransInfoReq tir;
  private RegionCode regionCode;
   
  

  private List<PayBankCode> payBankCodeList;
  private List<MerInfoUpdate> subMerUpdateList;
  private List<TerminalType> terminalTypeList;
  private List<MccCode> mccCodeList;
  private List<BusType> busTypeList;
  private List<MerTract> merTractList;
  private List<MerInfo> merInfoList;
  private List<PlatMerInfo> platMerInfoList;
  private List<TractInfo> tractInfoList;
  private List<AppTractInfo> appTractInfoList;
  private List<MerAppTract> merAppTractList;
  private List<MerTrans> merTransList;
  private List<BankBehalfBranch> bankBehalfBranchList;
  private List<MerModel> merModelList;
  private List<CssConfig> cssConfigList;
  private List<SubMerRate> subMerRateList;
  private List<SubMerRate> subMerRateList2;
  private List<PlatModel> platModelList;
  private List<RegionCode> regionCodeList;
  // @Inject
  // private PlatModelService platModelService;
  // 分页开始
  private int page = 1;
  private int pageSize = 15;
  private int page1 = 1;
  private int pageSize1 = 15;

  private String subMerUpdateJson;
  private String merchantListJson;
  private String platMerListJson;
  private String tractInfoListJson;
  private String appTractInfoListJson;
  private String configCodeJson;
  private String transNotifyActionList;
  private static final int BUFFER_SIZE = 16 * 1024;
  // 机构商LOGO文件域对象
  private File merfile;
  // 子商户LOGO文件域对象
  private File subfile;
  // 代理商LOGO文件域对象
  private File agefile;
  // 平台LOGO文件域对象
  private File platfile;
  // 上传文件名
  private String[] fileFileName;
  // 上传文件类型
  private String[] fileContentType;
  // 保存文件的目录路径(通过依赖注入)
  private String savePath;
  // 读取配置文件
  private String[] data;
  private String message;
  private String levelPwd;
  private String time;
  private String sumAmt;
  private String avgAmt;
  private String payBankId;
  private String backUrl;
  private String rate;
  ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault());

  public String aaa() {
    Map map = new HashMap();
    SubMerInfo subMerInfo = new SubMerInfo();
    subMerInfo.setMerSysId("173665683923263");
    map.put("subMerInfo", subMerInfo);
    map.put("page", 0);
    map.put("pageSize", 100);
    List<SubMerInfo> subMerInfoList = subMerInfoDao.findAll(map);
    Iterator<SubMerInfo> subMerInfoListIt = subMerInfoList.iterator();
    while (subMerInfoListIt.hasNext()) {
      SubMerInfo subMerInfo1 = (SubMerInfo) subMerInfoListIt.next();
      MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
      merSettleStatictis.setMid(subMerInfo1.getSubMerId());
      merSettleStatictis.setMerType("1");
      merSettleStatictis.setMerSysId(subMerInfo1.getMerSysId());
      merSettleStatictis.setMerName(subMerInfo1.getMerName());
      merSettleStatictis.setAccountNum(subMerInfo1.getSettAccountNo());
      merSettleStatictis.setAccountName(subMerInfo1.getSettAccountName());
      merSettleStatictis.setAccountAgencyId(subMerInfo1.getSettAgency());
      merSettleStatictis.setSettleDate("20140828");
      merSettleStatictis.setSumAmt("20000000");
      merSettleStatictis.setSumTransFee("0");
      merSettleStatictis.setClearAmt("20000000");
      merSettleStatictis.setCreateDate("20140828");
      merSettleStatictis.setCreateTime("184822");
      merSettleStatictis.setClearStatus("1");
      merSettleStatictisDao.insertMerSettleStatictis(merSettleStatictis);
    }
    return "";
  }

  /**
   * 通道规则信息查询
   * 
   * @return
   */
  public String tractRules() {
    try {
      String mid = getRequest().getParameter("mid");
      merTractList = merTractDao.selectmerTractByMerSysId(mid);
      JSONArray array = JSONArray.fromObject(merTractList);
      // JSONObject object = new JSONObject();
      tractInfoListJson = array.toString();
      System.out.println(tractInfoListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "tractRules";
  }

  // 查询结算机构，跳转添加机构商页面
  public String doAddMerchant() {
    try {
      cssConfigList = cssConfigDao.selectCssConfig();
      platMerInfoList = platMerInfoDao.selectPlatMerInfoList();
      bankBehalfBranchList = bankBehalfBranchDao.selectBankBehalfBranchList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "doAddMerchant";
  }

  // 添加机构商
  public String addMerchant() {
    if (merchantInfo == null) {
      getRequest().setAttribute("addMerchantInfo", "fouse");
      return "addMerchant";
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      merchantInfo.setCreateTime(sdf.format(new Date()));
      merchantInfo.setStatus("0");
      merchantInfo.setAuthStatus("0");
      merchantInfo.setPlatMerId("0");// 无平台方
      // if(merchantInfo.getBillCycle()!=null&&!"".equals(merchantInfo.getBillCycle())){
      // merchantInfo.setBillCycle(merchantInfo.getBillCycle().replace(", ",
      // "|"));
      // }
      int i = merInfoDao.insertMerInfo(merchantInfo);
      if (i != 1) {
        getRequest().setAttribute("addMerchantInfo", "fouse");
      } else {
        getRequest().setAttribute("addMerchantInfo", "success");
      }
    } catch (Exception e) {
      getRequest().setAttribute("addMerchantInfo", "fouse");
      e.printStackTrace();
    }
    return "addMerchant";
  }

  // 查询所有机构商
  public void selectMerchantAll() {
    try {
      if (merchantInfo == null) {
        merchantInfo = new MerInfo();
      }
      if (merchantInfo.getMerName() != null && !"".equals(merchantInfo.getMerName().trim())) {
        merchantInfo.setMerName(new String(merchantInfo.getMerName().getBytes("ISO-8859-1"),
            "UTF-8"));
      }
      if (merchantInfo.getSignPerson() != null && !"".equals(merchantInfo.getSignPerson().trim())) {
        merchantInfo.setSignPerson(new String(merchantInfo.getSignPerson().getBytes("ISO-8859-1"),
            "UTF-8"));
      }
      Map map = PageUtil.getPageMap(page, pageSize);
      map.put("merchantInfo", merchantInfo);
      String logName = (String) getSession().getAttribute("userName");
      //特殊用户只看外卡
      if(logName.equals("whf")){
          merchantInfo.setMerSysIds("'104','126','144','149','151'");
      }else{
          merchantInfo.setMerSysIds(null);
      }
    
      int count = merInfoDao.selectMerInfoCount(merchantInfo);
      merInfoList = merInfoDao.selectMerInfoAll(map);
      JSONArray array = JSONArray.fromObject(merInfoList);
      JSONObject str = JSONObject.fromObject(merInfoList.get(0));
      System.out.println(str.toString());
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      merchantListJson = object.toString();
      getResponse().getWriter().write(merchantListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 用于机构商户审批、上线、暂停、恢复
  public String merchantIndex() {
    try {
      merchantInfo = new MerInfo();
      merchantInfo.setMerSysId(getRequest().getParameter("mid"));
      merchantInfo = merInfoDao.selectMerInfoById(merchantInfo);
      if (merchantInfo.getStatus().equals("1")) {
        merchantInfo.setBackUrl(PropertiesUtils.getPropertiesValueInPath("backUrl"));
      }
      bankBehalfBranchList = bankBehalfBranchDao.selectBankBehalfBranchList();
      payBankCodeList = payBankCodeDao.selectPayBankCodeAll(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "merchantIndex";
  }

  // 机构商户审批、上线、暂停、恢复
  public void approveMerchant() {
    try {
      String status = getRequest().getParameter("status");
      merchantInfo = new MerInfo();
      merchantInfo.setMerSysId(getRequest().getParameter("merSysId"));
      merchantInfo.setStatus(status);
      int update = 0;
      if (status.equals("1")) {
        update = merInfoDao.approvalMerchantInfo(merchantInfo);
        if (update > 0) {
          getResponse().getWriter().write("succ");
        } else {
          getResponse().getWriter().write("fone");
        }
      }
      if (status.equals("4")) {
        merchantInfo.setStatus("2");
        update = merInfoDao.approvalMerchantInfo(merchantInfo);
        if (update > 0) {
          getResponse().getWriter().write("succTopY");
        } else {
          getResponse().getWriter().write("succTopN");
        }
      }
      if (status.equals("2")) {
        merAppTract = new MerAppTract();
        merManager = new MerManager();
        merManager.setMid(getRequest().getParameter("merSysId"));
        merManager.setRemark("1");
        merManager.setMerType("0");
        merAppTract.setMerSysId(getRequest().getParameter("merSysId"));
        merTrans = merTransDao.selectMerTransByMerSysId(getRequest().getParameter("merSysId"));
        merRisk = merRiskDao.selectmerRiskByMerSysId(getRequest().getParameter("merSysId"));
        merTractList = merTractDao.selectmerTractByMerSysId(getRequest().getParameter("merSysId"));
        merAppTractList = merAppTractDao.selectMerAppTractByMerSysId(merAppTract);
        merManager = merManagerDao.selectMerManagerRemarkByMid(merManager);
        if (merTrans == null) {// 如果没有配置交易配置
          getResponse().getWriter().write("merTransFone");
          return;
        }
        if (merRisk == null) {// 如果没有配置风控
          getResponse().getWriter().write("merRiskFone");
          return;
        }
        if (merTractList.size() < 1 && merAppTractList.size() < 1) {// 如果没有机构通道或者应用通道
          getResponse().getWriter().write("listFone");
          return;
        }
        if (merManager == null) {// 如果没有超级管理员
          getResponse().getWriter().write("merManagerFone");
          return;
        }
        update = merInfoDao.approvalMerchantInfo(merchantInfo);
        if (update > 0) {
          getResponse().getWriter().write("succOnline");
        } else {
          getResponse().getWriter().write("fonesOnline");
        }

      }
      if (status.equals("3")) {
        update = merInfoDao.approvalMerchantInfo(merchantInfo);
        if (update > 0) {
          getResponse().getWriter().write("succSuspended");
        } else {
          getResponse().getWriter().write("fonesSuspended");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return;
  }

  // 编辑商户信息
  public String updateMerchantDetail() {
    try {
      cssConfigList = cssConfigDao.selectCssConfig();
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
      bankBehalfBranchList = bankBehalfBranchDao.selectBankBehalfBranchList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "updateMerchantDetail";
  }

  // 修改商户信息
  public String updateMerchantInfo() {
    try {
      if (merchantInfo == null) {
        getResponse().getWriter().write("fone");
        return null;
      }
      if (null != merchantInfo && "-1".equals(merchantInfo.getColor()))
        merchantInfo.setColor("");
      int i = merInfoDao.updateMerchantInfo(merchantInfo);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // 修改机构商结算信息跳转
  public String selectSettleMerchant() {
    try {
      if (sysManager == null) {
        getRequest().setAttribute("mess", "fone");
        return "selectSettleMerchant";
      }
      SysOpLog sysOpLog = new SysOpLog();
      sysOpLog.setLoginName(sysManager.getLoginName());
      sysOpLog.setOpDesc("修改结算信息授权");
      sysOpLog.setOpUrl("/merchantInfo!selectSettleMerchant.ac");
      sysOpLog.setOpTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      sysManager.setLevelPwd(Md5Util.getMD5(sysManager.getLevelPwd()));
      if (sysManagerService.getSysManager(sysManager) == null) {
        getRequest().setAttribute("mess", "fone");
        sysOpLog.setRemark("授权失败，授权人ID或授权密码不正确");
        sysOpLogDao.insertSysOpLog(sysOpLog);
        return "selectSettleMerchant";
      }
      sysOpLog.setRemark("授权成功");
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
      bankBehalfBranchList = bankBehalfBranchDao.selectBankBehalfBranchList();
      regionCodeList = regionCodeService
              .selectRegionCodeList2(regionCode);
      sysOpLogDao.insertSysOpLog(sysOpLog);
    } catch (Exception e) {
      getRequest().setAttribute("mess", "fone");
      e.printStackTrace();
    }
    return "selectSettleMerchant";
  }

  // 修改结算机构
  public void updateSettleMerchantInfo() {
    try {
      if (merchantInfo == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      merchantInfo.setBillCycle(merchantInfo.getBillCycle().replace(" ", ""));
      String openBank = merchantInfo.getOpenBank();
      String region = merchantInfo.getRegion();
      String[] regions = region.split(",");
      String[] openBanks = openBank.split(",");
      openBank = openBanks[1];
      String lineNum = openBanks[0];
      String settAgency = merchantInfo.getSettAgency();

      merchantInfo.setRegion(regions[0].trim() + regions[2].trim());
      merchantInfo.setSettAgency(settAgency.split(",")[0]);
      merchantInfo.setOpenBank(openBank);
      merchantInfo.setLineNum(lineNum);
      int i = merInfoDao.updateSettleMerchantInfo(merchantInfo);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 跳转平台添加
  public String doAddPlatMer() {
    try {
      cssConfigList = cssConfigDao.selectCssConfig();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "doAddPlatMer";
  }

  // 添加平台商户
  public String addPlatMer() {
    if (platMerInfo == null) {
      getRequest().setAttribute("addPlatMer", "fouse");
      return "addPlatMer";
    }
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      platMerInfo.setCreateTime(sdf.format(new Date()));
      platMerInfo.setStatus("0");
      int i = platMerInfoDao.insertPlatMerInfo(platMerInfo);
      merManager = new MerManager();
      merManager.setStatus("1");
      merManager.setMerType("2");
      merManager.setLoginName("admin");
      merManager.setRemark("1");
      merManager.setLoginPwd(Md5Util.getMD5("admin"));
      merManager.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      merManager.setMid(platMerInfo.getPlatMerId());
      merManagerDao.insertMerManager(merManager);
      if (i != 1) {
        getRequest().setAttribute("addPlatMer", "fouse");
      } else {
        getRequest().setAttribute("addPlatMer", "success");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "addPlatMer";
  }

  // 查询所有平台商户
  public void selectPlatMerAll() {
    try {
      if (platMerInfo == null) {
        platMerInfo = new PlatMerInfo();
      }
      if (platMerInfo.getPlatMerName() != null && !"".equals(platMerInfo.getPlatMerName().trim())) {
        platMerInfo.setPlatMerName(new String(platMerInfo.getPlatMerName().getBytes("ISO-8859-1"),
            "UTF-8"));
      }
      Map map = PageUtil.getPageMap(page, pageSize);
      map.put("platMerInfo", platMerInfo);
      platMerInfoList = platMerInfoDao.selectPlatMerInfoAll(map);
      int count = platMerInfoDao.selectPlatMerInfoCount(platMerInfo);
      JSONArray array = JSONArray.fromObject(platMerInfoList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      platMerListJson = object.toString();
      getResponse().getWriter().write(platMerListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 用于平台商户审批、上线、暂停、恢复
  public String platMerIndex() {
    try {
      platMerInfo = getPlatMerInfoById(getRequest().getParameter("pid"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "platMerIndex";
  }

  // 平台商户审批、上线、暂停、恢复
  public String approvePlatMer() {
    try {
      String status = getRequest().getParameter("status");
      platMerInfo = new PlatMerInfo();
      platMerInfo.setPlatMerId(getRequest().getParameter("platMerId"));
      platMerInfo.setStatus(status);
      int update = platMerInfoDao.approvalPlatMerInfo(platMerInfo);
      if (status.equals("1"))
        if (update > 0) {
          getResponse().getWriter().write("succ");
        } else {
          getResponse().getWriter().write("fone");
        }
      if (status.equals("2"))
        if (update > 0) {
          getResponse().getWriter().write("succSuspended");
        } else {
          getResponse().getWriter().write("fonesSuspended");
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public String updatePlatMerInfoDetail() {
    try {
      cssConfigList = cssConfigDao.selectCssConfig();
      platMerInfo = getPlatMerInfoById(getRequest().getParameter("pid"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "updatePlatMerInfoDetail";
  }

  public String updatePlatMerInfo() {
    try {
      if (platMerInfo == null) {
        getResponse().getWriter().write("fone");
        return null;
      }
      int i = platMerInfoDao.updatePlatMerInfo(platMerInfo);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public PlatMerInfo getPlatMerInfoById(String platMerId) {
    platMerInfo = new PlatMerInfo();
    platMerInfo.setPlatMerId(platMerId);
    return platMerInfoDao.selectPlatMerInfoById(platMerInfo);
  }

  public MerInfo getMerInfo(String merSysId) {
    merchantInfo = new MerInfo();
    merchantInfo.setMerSysId(merSysId);
    return merInfoDao.selectMerInfoById(merchantInfo);
  }

  // 图片上传
  private static boolean uploadImgs(File src, File dst) {
    InputStream in = null;
    OutputStream out = null;
    try {
      in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
      out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
      byte[] buffer = new byte[BUFFER_SIZE];
      int len = 0;
      while ((len = in.read(buffer)) > 0) {
        out.write(buffer, 0, len);
      }
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    } finally {
      if (null != in) {
        try {
          in.close();
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      }
      if (null != out) {
        try {
          out.close();
        } catch (IOException e) {
          e.printStackTrace();
          return false;
        }
      }
    }
    return true;
  }

  public String merUploadLogo() {
    merchantInfo = new MerInfo();
    merchantInfo.setMerSysId(getRequest().getParameter("mid"));
    return "merUploadLogo";
  }

  public String platUploadLogo() {
    platMerInfo = new PlatMerInfo();
    platMerInfo.setPlatMerId(getRequest().getParameter("pid"));
    return "platUploadLogo";
  }

  // 上传LOGO
  public void toDoMerLogo() {
    try {
      boolean f = false;
      String dstPath = null;
      File dstFile = null;
      dstPath = rb.getString("ImagesUrl") + "/SubMerImages/" + "/LogoFiles";
      File uploadfile = new File(dstPath);
      if (!uploadfile.exists() && !uploadfile.isDirectory()) {
        // 不存在
        uploadfile.mkdirs();
      }
      if (merfile != null) {// 机构LOGO
        f = true;
        dstPath = dstPath + "/" + merchantInfo.getMerSysId() + ".png";
        dstFile = new File(dstPath);
        if (!uploadImgs(merfile, dstFile)) {
          getResponse().getWriter().write("merfileErr|");
          System.out.println("图片上传失败");
        } else {
          System.out.println("上传成功");
          // 上传图片成功后修改数据库机构LOGO字段地址
          MerInfo merInfo = new MerInfo();
          merInfo.setMerSysId(merchantInfo.getMerSysId());
          merInfo.setLogo(dstPath);
          merInfoDao.updateMerLogo(merInfo);
          getResponse().getWriter().write("merfileSucc|");
        }
      }
      // if(subfile!=null){
      // f=true;
      // dstPath = rb.getString("upload-path") + merchantInfo.getMerSysId()+"/"+
      // merchantInfo.getMerSysId()+"_Sub_Logo.png";
      // dstFile = new File(dstPath);
      // if(!uploadImgs(subfile, dstFile)){
      // getResponse().getWriter().write("subfileErr|");
      // System.out.println("图片上传失败");
      // }else{
      // System.out.println("上传成功");
      // getResponse().getWriter().write("subfileSucc|");
      // }
      // }
      // if(agefile!=null){
      // f=true;
      // dstPath = rb.getString("upload-path") + merchantInfo.getMerSysId()+"/"+
      // merchantInfo.getMerSysId()+"_Age_Logo.png";
      // dstFile = new File(dstPath);
      // if(!uploadImgs(agefile, dstFile)){
      // getResponse().getWriter().write("agefileErr|");
      // System.out.println("图片上传失败");
      // }else{
      // // AgenctInfo agenctInfo = new AgenctInfo();
      // // agenctInfo.setMerSysId(merchantInfo.getMerSysId());
      // // agenctInfo.setLogo(dstPath);
      // // agenctInfoDao.up
      // System.out.println("上传成功");
      // getResponse().getWriter().write("agefileSucc|");
      // }
      // }
      if (platfile != null) {// 平台LOGO
        f = true;
        dstPath = dstPath + "/" + platMerInfo.getPlatMerId() + ".png";
        dstFile = new File(dstPath);
        if (!uploadImgs(platfile, dstFile)) {
          getResponse().getWriter().write("platfileErr|");
          System.out.println("图片上传失败");
        } else {
          System.out.println("上传成功");
          getResponse().getWriter().write("platfileSucc|");
        }
      }
      if (!f) {
        getResponse().getWriter().write("notImage");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 机构交易配置跳转
  public String toSetTransInfo() {
    try {
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "toSetTransInfo";
  }

  // 设置通道以及费率
  public String toSetTract() {
    try {
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
      merTractList = merTractDao.selectmerTractByMerSysId(getRequest().getParameter("mid"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "toSetTract";
  }

  //
  public String toSetControl() {
    try {
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
      merRisk = merRiskDao.selectmerRiskByMerSysId(getRequest().getParameter("mid"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "toSetControl";
  }

  public String toSetAppTract() {
    try {
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
      merAppTract = new MerAppTract();
      merAppTract.setMerSysId(getRequest().getParameter("mid"));
      merAppTractList = merAppTractDao.selectMerAppTractByMerSysId(merAppTract);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "toSetAppTract";
  }

  public void getAllTractList() {
    try {
      if (tractInfo == null) {
        return;
      }
      if ("03".equals(tractInfo.getRatesType()))
        tractInfo.setRatesType("01");
      if ("04".equals(tractInfo.getRatesType()))
        tractInfo.setRatesType("02");
      tractInfoList = tractInfoDao.selectTractInfoByRatesType(tractInfo);
      JSONArray array = JSONArray.fromObject(tractInfoList);
      tractInfoListJson = array.toString();
      getResponse().getWriter().write(tractInfoListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getAllAppTractList() {
    try {
      if (appTractInfo == null) {
        return;
      }
      appTractInfoList = appTractInfoDao.selectAppTractInfoListByTractType(appTractInfo);
      JSONArray array = JSONArray.fromObject(appTractInfoList);
      appTractInfoListJson = array.toString();
      getResponse().getWriter().write(appTractInfoListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addMerTract() {
    try {
      if (merTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      if (merTractDao.selectmerTractByTractId(merTract).size() != 0) {
        getResponse().getWriter().write("fones");
        return;
      }
      merTrans = merTransDao.selectMerTransByMerSysId(merTract.getMerSysId());
      if (merTrans == null) {
        getResponse().getWriter().write("fones1");
        return;
      }
      // merTract.setMerRatio(merTrans.getMerProfitRate());
      // merTract.setLowestFee(merTrans.getMerLowestFee());
      // if(merTract.getProfitType().equals("01") ||
      // merTract.getProfitType().equals("03")){
      // merTract.setRate(merTrans.getMerRate1());
      // }else if(merTract.getProfitType().equals("02") ||
      // merTract.getProfitType().equals("04")){
      // merTract.setRate(merTrans.getMerRate2());
      // merTract.setLowestFee(merTrans.getMerLowestFee());
      // merTract.setHighestFee(merTrans.getMerHighestFee());
      // }
      int i = merTractDao.insertMerTract(merTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateMerTrackDefaultStatus() {
    try {
      if (merTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      merTractDao.updateAllMerTrackDefaultStatus(merTract);
      int i = merTractDao.updateMerTrackDefaultStatus(merTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateMerTractStatus() {
    try {
      if (merTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      int i = merTractDao.updateMerTractStatus(merTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getTractAndMerToUpdate() {
    try {
      merTract = merTractDao.selectmerTractByTractId(merTract).get(0);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "getTractAndMerToUpdate";
  }

  public void tractAndMerToUpdate() {
    try {
      if (merTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      merchantInfo = new MerInfo();
      merchantInfo.setMerSysId(merTract.getMerSysId());
      merchantInfo = merInfoDao.selectMerInfoByMerSysId(merchantInfo);
      MerInfoUpdateLog merInfoUpdateLog1 = new MerInfoUpdateLog();
      merInfoUpdateLog = new MerInfoUpdateLog();
      merInfoUpdateLog.setMerType("0");
      merInfoUpdateLog.setMerId(merTract.getMerSysId());
      merInfoUpdateLog.setUpdateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      String time = merInfoUpdateLogDao.selectOrgTime(merInfoUpdateLog).getUpdateTime();
      if (time != null)
        merInfoUpdateLog.setOrgTime(merchantInfo.getCreateTime());
      else
        merInfoUpdateLog.setOrgTime(time);
      merInfoUpdateLog.setOrgValue(JSONObject.fromObject(merTract).toString());
      merInfoUpdateLog.setNewValue(JSONObject.fromObject(merTract2).toString());
      merInfoUpdateLogDao.insertMerInfoUpdateLog(merInfoUpdateLog);
      int i = merTractDao.tractAndMerToUpdate(merTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
      try {
        getResponse().getWriter().write("fone");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

  public void setTrans() {
    try {
      if (merRisk == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      merRisk.setMerType("0");
      int i = 0;
      if (merRiskDao.selectmerRiskByMerSysId(merRisk.getMerSysId()) != null)
        i = merRiskDao.updateMerRisk(merRisk);
      else
        i = merRiskDao.inserMerRisk(merRisk);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addMerAppTract() {
    try {
      if (merAppTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      merAppTract.setCreateTime(new SimpleDateFormat().format(new Date()));
      merAppTract.setStatus("0");
      List<MerAppTract> appTracts = merAppTractDao.selectMerAppTractByMerSysId(merAppTract);
      if (null != appTracts) {
        for (int i = 0; i < appTracts.size(); i++) {
          // 判断应用通道号/通道类别/机构商号是否已经存在
          String appTractId = appTracts.get(i).getAppTractId();// 应用通道号
          String appTractType = appTracts.get(i).getAppTractType();// 通道类别
          String merSysId = appTracts.get(i).getMerSysId();// 机构商号
          if (appTractId.equals(merAppTract.getAppTractId())
              && appTractType.equals(merAppTract.getAppTractType())
              && merSysId.equals(merAppTract.getMerSysId())) {
            getResponse().getWriter().write("AllWrong");
            return;
          }
        }
      }
      int i = merAppTractDao.insertMerAppTract(merAppTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void updateMerAppTractStatus() {
    try {
      if (merAppTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      int i = merAppTractDao.updateMerAppTractStatus(merAppTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getTractAndMerAppToUpdate() {
    try {
      merAppTract = merAppTractDao.selectMerAppTractByTractId(merAppTract);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "getTractAndMerAppToUpdate";
  }

  public void tractAndMerAppToUpdate() {
    try {
      if (merAppTract == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      int i = merAppTractDao.updateMerAppTract(merAppTract);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String toSetMerTract() {
    try {
      merchantInfo = getMerInfo(getRequest().getParameter("mid"));
      merTrans = merTransDao.selectMerTransByMerSysId(getRequest().getParameter("mid"));
      if (merTrans != null) {
        if ("04".equals(merTrans.getTerminalType())) {
          if (StringUtils.isNotBlank(merTrans.getLowsetTransAmt())&&!"-1".equals(merTrans.getLowsetTransAmt())) {
            merTrans.setLowsetTransAmt(AmountUtils.changeF2Y(merTrans.getLowsetTransAmt()));
          }
          if (StringUtils.isNotBlank(merTrans.getMerHighestFee())&&!"-1".equals(merTrans.getMerHighestFee())) {// 封顶值
            merTrans.setMerHighestFee(AmountUtils.changeF2Y(merTrans.getMerHighestFee()));
          }
          if (StringUtils.isNotBlank(merTrans.getMerLowestFee())&&!"-1".equals(merTrans.getMerLowestFee())) {// 封底值
            merTrans.setMerLowestFee(AmountUtils.changeF2Y(merTrans.getMerLowestFee()));
          }
          if (StringUtils.isNotBlank(merTrans.getBasicHighestFee())&&!"-1".equals(merTrans.getBasicHighestFee())) {// 基础封顶费用
            merTrans.setBasicHighestFee(AmountUtils.changeF2Y(merTrans.getBasicHighestFee()));
          }
          if (StringUtils.isNotBlank(merTrans.getMerRateNoTop())&&!"-1".equals(merTrans.getMerRateNoTop())) {// 基础封顶费用
        	  merTrans.setMerRateNoTop(AmountUtils.changeF2Y(merTrans.getMerRateNoTop()));
          }
        }
      }
      // TODO Auto-generated catch block
      regionCode = new RegionCode();
      mccCodeList = mccCodeDao.selectMccCodeAll();
      regionCodeList = regionCodeDao.selectRegionCodeList2(regionCode);
      busTypeList = busTypeService.selectBusTypeAll();
      terminalTypeList = terminalTypeService.selectTerminalTypeAll();
      if (merTrans != null && merTrans.getActionList() != null
          && !"".equals(merTrans.getActionList())) {
        configCodeJson = merTrans.getActionList();
        transNotifyActionList = merTrans.getTransNotifyActionList();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "toSetMerTract";
  }

  /**
   * 机构交易配置
   */
  public void setMerTrans() {
    try {
      if (merTrans == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      if (merTrans.getActionList() == null || "".equals(merTrans.getActionList())) {
        merTrans.setActionList("01,04,05,06,07,08,09");
      }
      // String terminalType = "";
      // if(null != merTrans.getTerminalType() &&
      // !"".equals(merTrans.getTerminalType())){
      // terminalType = Combination(merTrans.getTerminalType().split(","));
      // }
      String busType = "";
      if (null != merTrans.getBusType() && !"".equals(merTrans.getBusType())) {
        busType = merTrans.getBusType().replace(", ", "|");
      }
      // if(null != merTrans.getTransNotifyActionList() &&
      // !"".equals(merTrans.getTransNotifyActionList())) {
      // transNotifyActionList =
      // Combination(merTrans.getTransNotifyActionList().split(","));
      // }
      // merTrans.setActionList(merTrans.getActionList().replace(";", ","));
      // merTrans.setTransNotifyActionList(merTrans.getTransNotifyActionList().replace(";",
      // ","));
      // merTrans.setTerminalType(merTrans.getTerminalType());
      // merTrans.setTerminalType(terminalType);
      merTrans.setBusType(busType);
      merTrans.setBasicRegion(merTrans.getBasicRegion().replace(", ", ""));
      // 这些最好还是写到service里
      if ("04".equals(merTrans.getTerminalType())) {
        if (StringUtils.isNotBlank(merTrans.getLowsetTransAmt())) {// 最低交易金额
          merTrans.setLowsetTransAmt(AmountUtils.changeY2F(merTrans.getLowsetTransAmt()));
        }
        if (StringUtils.isNotBlank(merTrans.getMerHighestFee())) {// 封顶值
          merTrans.setMerHighestFee(AmountUtils.changeY2F(merTrans.getMerHighestFee()));
        }
        if (StringUtils.isNotBlank(merTrans.getMerLowestFee())) {// 封底值
          merTrans.setMerLowestFee(AmountUtils.changeY2F(merTrans.getMerLowestFee()));
        }
        if (StringUtils.isNotBlank(merTrans.getBasicHighestFee())) {// 基础封顶费用
          merTrans.setBasicHighestFee(AmountUtils.changeY2F(merTrans.getBasicHighestFee()));
        }
        if( StringUtils.isNotBlank(merTrans.getMerRateNoTop()) ){ //积分扣率
        	merTrans.setMerRateNoTop(AmountUtils.changeY2F(merTrans.getMerRateNoTop()));
        }
      }
      merTrans.setAutoApprove("0");
      merTrans.setAuthStatus("0");
      merTrans.setT0Status("0");
      merTrans.setClearType("0");
      merTrans.setTransNotifyStatus("0");
      int i = 0;
      if (merTransDao.selectMerTransByMerSysId(merTrans.getMerSysId()) != null)
        i = merTransDao.updateMerTrans(merTrans);
      else
        i = merTransDao.insertMerTrans(merTrans);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
        getResponse().getWriter().write("succ");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getRightList() throws IOException {
    String type = getRequest().getParameter("type");
    JSONArray array = null;
    if (type != null && type.equals("mer")) {
      merModelList = merModelDao.selectMerModel();
      array = JSONArray.fromObject(merModelList);
    } else if (type != null && type.equals("plat")) {
      platModelList = platModelDao.selectPlatModel();
      array = JSONArray.fromObject(platModelList);
    }
    getResponse().getWriter().write(array.toString());
  }

  public String addManager() {
    String type = getRequest().getParameter("type");
    String result = "";
    if (type.equals("mer")) {
      result = "addMerManager";
    } else {
      result = "addPlatManager";
    }
    if (merManager == null) {
      getRequest().setAttribute("flag", "fail");
      return result;
    }
    if (type.equals("mer")) {
      merManager.setMerType("0");
    } else {
      merManager.setMerType("2");
    }
    // 查询操作员是否存在
    if (merManagerDao.selectMerManagerNameByMid(merManager) != 0) {
      getRequest().setAttribute("flag", "fail");
      return result;
    }
    merManager.setRemark("1");
    if (merManagerDao.selectMerManagerRemarkByMid(merManager) != null) {
      merManager.setRemark("2");
    } else {
      merManager.setRemark("1");
    }
    merManager.setStatus("1");
    merManager.setLoginPwd(Md5Util.getMD5(merManager.getLoginPwd()));
    merManager.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
    int i = merManagerDao.insertMerManager(merManager);
    if (i > 0) {
      getRequest().setAttribute("flag", "success");
      System.out.println("添加成功");
    } else {
      getRequest().setAttribute("flag", "fail");
      System.out.println("添加失败");
    }
    return result;
  }

  public String toAddManager() {
    String mid = getRequest().getParameter("mid");
    String pid = getRequest().getParameter("pid");
    merManager = new MerManager();
    if (mid != null && !"".equals(mid)) {
      merManager.setMid(mid);
      return "addMerManager";
    } else {
      merManager.setMid(pid);
      return "addPlatManager";
    }

  }

  public String toUpdateManager() {
    String mid = getRequest().getParameter("mid");
    String pid = getRequest().getParameter("pid");
    merManager = new MerManager();
    if (mid != null) {
      merManager.setMid(getRequest().getParameter("mid"));
      merManager.setMerType("0");
    }
    if (pid != null) {
      merManager.setMerType("2");
      merManager.setMid(getRequest().getParameter("pid"));
    }
    merManager.setRemark("1");
    merManager = merManagerDao.selectMerManagerRemarkByMid(merManager);
    if (merManager == null)
      message = "fone";
    if (mid != null) {
      return "toUpdateMerManager";
    } else {
      return "toUpdatePlatManager";
    }
  }

  public String updateManager() {
    String type = getRequest().getParameter("type");
    String result = null;
    if ("mer".equals(type)) {
      result = "toUpdateMerManager";
    } else {
      result = "toUpdatePlatManager";
    }
    if (merManager == null) {
      getRequest().setAttribute("flag", "fail");
      return result;
    }
    merManager.setRemark("1");
    if ("mer".equals(type)) {
      merManager.setMerType("0");
    } else {
      merManager.setMerType("2");
    }
    int i = merManagerDao.updateMerManager(merManager);
    if (i != 1) {
      getRequest().setAttribute("flag", "fail");
    } else {
      getRequest().setAttribute("flag", "success");
    }
    return result;
  }

  public String doselectPlatList() {
    platMerInfo = new PlatMerInfo();
    platMerInfo.setPlatMerId(getRequest().getParameter("pid"));
    return "doselectPlatList";
  }

  public void selectPlatList() {
    try {
      if (platMerInfo == null) {
        platMerInfo = new PlatMerInfo();
      }
      Map map = PageUtil.getPageMap(page1, pageSize1);
      map.put("platMerInfo", platMerInfo);
      int count = merInfoDao.selectMerInfoAllByPlatMerIdCount(map);
      merInfoList = merInfoDao.selectMerInfoAllByPlatMerId(map);
      JSONArray array = JSONArray.fromObject(merInfoList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      merchantListJson = object.toString();
      getResponse().getWriter().write(merchantListJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void selectTractInfo() {
    try {
      if (tractInfo == null || tractInfo.getTractId() == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      tractInfo = tractInfoDao.selectTractById(tractInfo.getTractId());
      JSONObject object = JSONObject.fromObject(tractInfo);
      getResponse().getWriter().write(object.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void selectAllChangeApList() {
    try {
      if (merInfoUpdate == null) {
        merInfoUpdate = new MerInfoUpdate();
      }
      Map map = PageUtil.getPageMap(page, pageSize);
      map.put("merInfoUpdate", merInfoUpdate);
      int count = merInfoUpdateService.selectMerInfoUpdateCount(map);
      subMerUpdateList = merInfoUpdateService.selectMerInfoUpdate(map);
      JSONArray array = JSONArray.fromObject(subMerUpdateList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      subMerUpdateJson = object.toString();
      getResponse().getWriter().write(subMerUpdateJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void checkStatus() {
    try {
      if (merInfoUpdate == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      merInfoUpdate.setStatus("1");
      int i = merInfoUpdateService.updateMerInfoUpdateStatus(merInfoUpdate);
      if (i != 1) {
        getResponse().getWriter().write("fone");
      } else {
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

  public String merInfoUpdateDetailed() {
    try {
      if (merInfoUpdate == null || merInfoUpdate.getId() == null) {
        return "merInfoUpdateDetailed";
      }
      subMerRate = new SubMerRate();
      merInfoUpdate = merInfoUpdateService.selectMerInfoUpdateById(merInfoUpdate.getId());
      String[] s = merInfoUpdate.getValue().split("\\|");
      subMerRateList2 = new ArrayList<SubMerRate>();
      for (int i = 0; i < s.length; i++) {
        if (!"".equals(s[i])) {
          JSONObject jsonObj = JSONObject.fromObject(s[i]);
          subMerRate2 = new SubMerRate();
          subMerRate2.setSubMerId(merInfoUpdate.getMid());
          subMerRate2.setRateType(jsonObj.getString("rateType"));
          subMerRate2.setTeransRate(jsonObj.getString("teransRate"));
          subMerRate2.setTransHighestFee(jsonObj.getString("transHighestFee"));
          subMerRate2.setAgentL1Rate(jsonObj.getString("agentL1Rate"));
          subMerRate2.setAgentL1ProfitRate(jsonObj.getString("agentL1ProfitRate"));
          subMerRate2.setAgentL1HighestFee(jsonObj.getString("agentL1HighestFee"));
          subMerRate2.setAgentL2HigestFee(jsonObj.getString("agentL2HigestFee"));
          subMerRate2.setAgentL2ProfitRate(jsonObj.getString("agentL2ProfitRate"));
          subMerRate2.setAgentL2Rate(jsonObj.getString("agentL2Rate"));
          subMerRateList2.add(subMerRate2);
        }
      }
      subMerRate.setSubMerId(merInfoUpdate.getMid());
      MerSettleStatictis merSettleStatictis = new MerSettleStatictis();
      merSettleStatictis.setMid(subMerRate.getSubMerId());
      merSettleStatictis.setMerType("1");
      Map map = merSettleStatictisDao.selectMerSettleStatictisByMid(merSettleStatictis);
      if (map != null) {
        time = jiSuan(map.get("maxTime").toString(), map.get("minTime").toString());
        sumAmt = map.get("sumAmt").toString();
        avgAmt = (Double.parseDouble(sumAmt) / Double.parseDouble(time)) + "";
      }
      subMerRateList = subMerRateDao.selectSubMerRateById(subMerRate);
      subMerRate.setSubMerName(subMerRateList.get(0).getSubMerName());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "merInfoUpdateDetailed";
  }

  public String jiSuan(String maxTime, String minTime) {
    String dateStart = maxTime;
    String dateStop = minTime;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    Date d1 = null;
    Date d2 = null;
    String diffDays = "";
    try {
      d1 = format.parse(dateStart);
      d2 = format.parse(dateStop);

      // 毫秒ms
      long diff = d1.getTime() - d2.getTime();

      diffDays = (diff / (24 * 60 * 60 * 1000)) + "";

      System.out.print("两个时间相差：");
      System.out.print(diffDays + " 天, ");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return diffDays;
  }

  public void insertRulesMerAmt() {
    String getData = getRequest().getParameter("tractRules");
    System.out.println(getData);
    JsonUtil ju = new JsonUtil();
    Map map = ju.getMap4Json(getData);
    String passenger = map.get("rules").toString();
    String array[] = ju.getStringArray4Json(passenger);
    StringBuffer getGz = new StringBuffer();
    try {
      int j = 0;
      RulesMerAmt rulesMerAmt = null;
      for (int i = 0; i < array.length; i++) {
        String passengerJsonStr = array[i];
        Map passengerJsonmap = ju.getMap4Json(passengerJsonStr);
        rulesMerAmt = new RulesMerAmt();
        rulesMerAmt.setEndAmt(passengerJsonmap.get("endAmt").toString());
        rulesMerAmt.setStartAmt(passengerJsonmap.get("beginAmt").toString());
        rulesMerAmt.setTractId(passengerJsonmap.get("tractId").toString());
        rulesMerAmt.setMerSysId(merTrans.getMerSysId());
        try {
          j = j + rulesMerAmtDao.insertRulesMerAmt(rulesMerAmt);
        } catch (Exception e) {
          System.out.println("添加异常");
        }
      }
      if (j == array.length) {
        getResponse().getWriter().write("添加成功");
      }
    } catch (Exception e) {
      try {
        getResponse().getWriter().write("添加失败");
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      System.out.println("添加异常");
      e.printStackTrace();
    }
  }

  // 通道规则添加
  public String insertRules() throws Exception {
    // 获取页面传值
    String getData = getRequest().getParameter("tractRules");
    System.out.println(getData);
    JsonUtil ju = new JsonUtil();
    Map map = ju.getMap4Json(getData);
    String passenger = map.get("rules").toString();
    String array[] = ju.getStringArray4Json(passenger);
    StringBuffer getGz = new StringBuffer();
    // getGz.append("package cn.bypay.drools\r\n");
    // getGz.append("import com.bypay.domain.business.Order;\r\n");
    try {
      for (int i = 0; i < array.length; i++) {
        String passengerJsonStr = array[i];
        Map passengerJsonmap = ju.getMap4Json(passengerJsonStr);
        // System.out.println("运算符:"+passengerJsonmap.get("field"));
        // System.out.println("金额:"+passengerJsonmap.get("amount"));
        System.out.println("卡类型:" + passengerJsonmap.get("cardType"));
        System.out.println("mcc:" + passengerJsonmap.get("mcc"));
        System.out.println("费率类型:" + passengerJsonmap.get("ratesType"));
        System.out.println("通道号:" + passengerJsonmap.get("tractId"));
        if (null == passengerJsonmap.get("tractId") || "".equals(passengerJsonmap.get("tractId"))
            || "-1".equals(passengerJsonmap.get("tractId"))) {
          System.out.println("添加失败,请选择通道!");
          getResponse().getWriter().write("添加失败,请选择通道!");
          return null;
        }
        String term = "";
        // if(passengerJsonmap.get("amount")!=null&&!"".equals(passengerJsonmap.get("amount"))
        // &&passengerJsonmap.get("field")!=null&&!"".equals(passengerJsonmap.get("field"))){
        // Long getAmt = Long.parseLong(passengerJsonmap.get("amount")+"")*100;
        // term = "amt"+passengerJsonmap.get("field")+getAmt;
        // }
        if (passengerJsonmap.get("cardType") != null
            && !"".equals(passengerJsonmap.get("cardType"))) {
          if (!"3".equals(passengerJsonmap.get("cardType"))) {
            if (!"".equals(term)) {
              term = term + ",";
            }
            term = term + "type==" + passengerJsonmap.get("cardType");
          }
        }
        if (passengerJsonmap.get("ratesType") != null
            && !"".equals(passengerJsonmap.get("ratesType"))
            && !"-1".equals(passengerJsonmap.get("ratesType"))) {
          if (!"".equals(term)) {
            term = term + ",";
          }
          term = term + "ratesType=='" + passengerJsonmap.get("ratesType") + "'";
        }
        if (passengerJsonmap.get("mcc") != null && !"".equals(passengerJsonmap.get("mcc"))
            && !"-1".equals(passengerJsonmap.get("mcc"))) {
          if (!"".equals(term)) {
            term = term + ",";
          }
          term = term + "mcc=='" + passengerJsonmap.get("mcc") + "'";
        }

        if ("".equals(term)) {
          System.out.println("添加失败,未选择条件!");
          getResponse().getWriter().write("添加失败,未选择条件!");
          return null;
        }
        String tractId = passengerJsonmap.get("tractId") + "";
        getGz.append("rule \"rule" + i + "\"\r\n");
        getGz.append("when\r\n");
        getGz.append("$order : Order( " + term + ")\r\n");
        getGz.append("then\r\n");
        getGz.append("System.out.println(\"this is a test\");\r\n");
        getGz.append("$order.setResult(\"" + tractId + "\");\r\n");
        getGz.append("end\r\n");
      }
      System.out.println(getGz.toString());
      merTrans.setRules(getGz.toString());
      System.out.println(getGz.length());
      int update = merTransDao.updateMerTransRules(merTrans);
      if (update > 0) {
        System.out.println("添加成功！");
        getResponse().getWriter().write("添加成功！");
        return null;
      } else {
        System.out.println("添加失败");
        getResponse().getWriter().write("添加失败");
        return null;
      }
    } catch (Exception e) {
      System.out.println("添加异常");
      getResponse().getWriter().write("添加异常");
      e.printStackTrace();
    }
    return null;
  }

  public String toAddPlatManager() {
    // merManager = new MerManager();
    // merManager.setMid(getRequest().getParameter("pid"));
    return "toAddPlatManager";
  }

  // 平台商户 显示管理员 权限用
  public void getPlatRightList() throws IOException {
    List<PlatModel> platModelLists = new ArrayList<PlatModel>();
    platModelLists = platModelDao.selectPlatModel();
    if (platModelLists != null) {
      JSONArray array = JSONArray.fromObject(platModelLists);
      getResponse().getWriter().write(array.toString());
    } else {
      System.out.println("空");
    }
  }

  public void getRegionCodeListBySuperCode() {
    try {
      if (regionCode == null) {
        return;
      }
      regionCode.setLevel("2");
      regionCodeList = regionCodeDao.selectRegionCodeList2(regionCode);
      JSONArray array = JSONArray.fromObject(regionCodeList);
      System.out.println(array.toString());
      getResponse().getWriter().write(array.toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // //添加平台操作员 功能暂时删除没有用到
  // public String addPlatManager() {
  // if(merManager == null){
  // getRequest().setAttribute("flag", "fail");
  // return "addManager";
  // }
  // // 查询操作员是否存在
  // if(merManagerDao.selectMerManagerNameByMid(merManager)!=0){
  // getRequest().setAttribute("flag", "fail");
  // return "addManager";
  // }
  // merManager.setRemark("1");
  // merManager.setStatus("1");
  // merManager.setMerType("0");
  // merManager.setLoginPwd(Md5Util.getMD5(merManager.getLoginPwd()));
  // merManager.setCreateTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
  // int i = merManagerDao.insertMerManager(merManager);
  // if (i > 0) {
  // getRequest().setAttribute("flag", "success");
  // System.out.println("添加成功");
  // } else {
  // getRequest().setAttribute("flag", "fail");
  // System.out.println("添加失败");
  // }
  // return "addManager";
  // }

  /**
   * 
   * @Description: 重置管理员密码
   * @Auther: YJH
   * @Date: 2014-12-18 下午17:12:56
   */
  public void updatePassword() {
    if (merManager == null) {
      merManager = new MerManager();
    }
    String mid = getRequest().getParameter("mid");
    merManager.setMid(mid);
    merManager.setLoginPwd(MD5.getHashString("123456"));
    Integer update = merManagerDao.updateMerManagerPassword(merManager);
    try {
      if (update > 0) {
        this.renderText("succ");
      } else {
        this.renderText("fones");
        log.info("重置密码失败");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  /**
   * 
   * @Description:更改代理商进件状态
   * @Auther: lijialiang
   * @Date: 2014-12-29 下午4:40:38
   */
  public void updateIsIntoPieces() {
    try {
      int i = merInfoDao.updateIsIntoPieces(merchantInfo);
      if (i != 1) {
        this.renderText("fone");
      } else {
        this.renderText("succ");
      }
    } catch (Exception e) {
    }
  }

  public MerInfoDao getMerInfoDao() {
    return merInfoDao;
  }

  public void setMerInfoDao(MerInfoDao merInfoDao) {
    this.merInfoDao = merInfoDao;
  }

  public MerInfo getMerchantInfo() {
    return merchantInfo;
  }

  public void setMerchantInfo(MerInfo merchantInfo) {
    this.merchantInfo = merchantInfo;
  }

  public List<MerInfo> getMerInfoList() {
    return merInfoList;
  }

  public void setMerInfoList(List<MerInfo> merInfoList) {
    this.merInfoList = merInfoList;
  }

  public PlatMerInfoDao getPlatMerInfoDao() {
    return platMerInfoDao;
  }

  public void setPlatMerInfoDao(PlatMerInfoDao platMerInfoDao) {
    this.platMerInfoDao = platMerInfoDao;
  }

  public PlatMerInfo getPlatMerInfo() {
    return platMerInfo;
  }

  public void setPlatMerInfo(PlatMerInfo platMerInfo) {
    this.platMerInfo = platMerInfo;
  }

  public List<PlatMerInfo> getPlatMerInfoList() {
    return platMerInfoList;
  }

  public void setPlatMerInfoList(List<PlatMerInfo> platMerInfoList) {
    this.platMerInfoList = platMerInfoList;
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

  public String getMerchantListJson() {
    return merchantListJson;
  }

  public void setMerchantListJson(String merchantListJson) {
    this.merchantListJson = merchantListJson;
  }

  public String getPlatMerListJson() {
    return platMerListJson;
  }

  public void setPlatMerListJson(String platMerListJson) {
    this.platMerListJson = platMerListJson;
  }

  public String getTractInfoListJson() {
    return tractInfoListJson;
  }

  public void setTractInfoListJson(String tractInfoListJson) {
    this.tractInfoListJson = tractInfoListJson;
  }

  public File getMerfile() {
    return merfile;
  }

  public void setMerfile(File merfile) {
    this.merfile = merfile;
  }

  public File getSubfile() {
    return subfile;
  }

  public void setSubfile(File subfile) {
    this.subfile = subfile;
  }

  public File getAgefile() {
    return agefile;
  }

  public void setAgefile(File agefile) {
    this.agefile = agefile;
  }

  public String[] getFileFileName() {
    return fileFileName;
  }

  public void setFileFileName(String[] fileFileName) {
    this.fileFileName = fileFileName;
  }

  public String[] getFileContentType() {
    return fileContentType;
  }

  public void setFileContentType(String[] fileContentType) {
    this.fileContentType = fileContentType;
  }

  public ResourceBundle getRb() {
    return rb;
  }

  public void setRb(ResourceBundle rb) {
    this.rb = rb;
  }

  public SubMerInfo getSubMerInfo() {
    return subMerInfo;
  }

  public void setSubMerInfo(SubMerInfo subMerInfo) {
    this.subMerInfo = subMerInfo;
  }

  public SubMerInfoDao getSubMerInfoDao() {
    return subMerInfoDao;
  }

  public void setSubMerInfoDao(SubMerInfoDao subMerInfoDao) {
    this.subMerInfoDao = subMerInfoDao;
  }

  public AgenctInfoDao getAgenctInfoDao() {
    return agenctInfoDao;
  }

  public void setAgenctInfoDao(AgenctInfoDao agenctInfoDao) {
    this.agenctInfoDao = agenctInfoDao;
  }

  public AgenctInfo getAgenctInfo() {
    return agenctInfo;
  }

  public void setAgenctInfo(AgenctInfo agenctInfo) {
    this.agenctInfo = agenctInfo;
  }

  public String[] getData() {
    return data;
  }

  public void setData(String[] data) {
    this.data = data;
  }

  public MerTract getMerTract() {
    return merTract;
  }

  public void setMerTract(MerTract merTract) {
    this.merTract = merTract;
  }

  public List<MerTract> getMerTractList() {
    return merTractList;
  }

  public void setMerTractList(List<MerTract> merTractList) {
    this.merTractList = merTractList;
  }

  public MerTractDao getMerTractDao() {
    return merTractDao;
  }

  public void setMerTractDao(MerTractDao merTractDao) {
    this.merTractDao = merTractDao;
  }

  public TractInfoDao getTractInfoDao() {
    return tractInfoDao;
  }

  public void setTractInfoDao(TractInfoDao tractInfoDao) {
    this.tractInfoDao = tractInfoDao;
  }

  public TractInfo getTractInfo() {
    return tractInfo;
  }

  public void setTractInfo(TractInfo tractInfo) {
    this.tractInfo = tractInfo;
  }

  public MerRiskDao getMerRiskDao() {
    return merRiskDao;
  }

  public void setMerRiskDao(MerRiskDao merRiskDao) {
    this.merRiskDao = merRiskDao;
  }

  public MerRisk getMerRisk() {
    return merRisk;
  }

  public void setMerRisk(MerRisk merRisk) {
    this.merRisk = merRisk;
  }

  public List<TractInfo> getTractInfoList() {
    return tractInfoList;
  }

  public void setTractInfoList(List<TractInfo> tractInfoList) {
    this.tractInfoList = tractInfoList;
  }

  public AppTractInfoDao getAppTractInfoDao() {
    return appTractInfoDao;
  }

  public void setAppTractInfoDao(AppTractInfoDao appTractInfoDao) {
    this.appTractInfoDao = appTractInfoDao;
  }

  public AppTractInfo getAppTractInfo() {
    return appTractInfo;
  }

  public void setAppTractInfo(AppTractInfo appTractInfo) {
    this.appTractInfo = appTractInfo;
  }

  public List<AppTractInfo> getAppTractInfoList() {
    return appTractInfoList;
  }

  public void setAppTractInfoList(List<AppTractInfo> appTractInfoList) {
    this.appTractInfoList = appTractInfoList;
  }

  public MerAppTractDao getMerAppTractDao() {
    return merAppTractDao;
  }

  public void setMerAppTractDao(MerAppTractDao merAppTractDao) {
    this.merAppTractDao = merAppTractDao;
  }

  public MerAppTract getMerAppTract() {
    return merAppTract;
  }

  public void setMerAppTract(MerAppTract merAppTract) {
    this.merAppTract = merAppTract;
  }

  public List<MerAppTract> getMerAppTractList() {
    return merAppTractList;
  }

  public void setMerAppTractList(List<MerAppTract> merAppTractList) {
    this.merAppTractList = merAppTractList;
  }

  public String getAppTractInfoListJson() {
    return appTractInfoListJson;
  }

  public void setAppTractInfoListJson(String appTractInfoListJson) {
    this.appTractInfoListJson = appTractInfoListJson;
  }

  public File getPlatfile() {
    return platfile;
  }

  public void setPlatfile(File platfile) {
    this.platfile = platfile;
  }

  public MerTransDao getMerTransDao() {
    return merTransDao;
  }

  public void setMerTransDao(MerTransDao merTransDao) {
    this.merTransDao = merTransDao;
  }

  public MerTrans getMerTrans() {
    return merTrans;
  }

  public void setMerTrans(MerTrans merTrans) {
    this.merTrans = merTrans;
  }

  public List<MerTrans> getMerTransList() {
    return merTransList;
  }

  public void setMerTransList(List<MerTrans> merTransList) {
    this.merTransList = merTransList;
  }

  public BankBehalfBranchDao getBankBehalfBranchDao() {
    return bankBehalfBranchDao;
  }

  public void setBankBehalfBranchDao(BankBehalfBranchDao bankBehalfBranchDao) {
    this.bankBehalfBranchDao = bankBehalfBranchDao;
  }

  public List<BankBehalfBranch> getBankBehalfBranchList() {
    return bankBehalfBranchList;
  }

  public void setBankBehalfBranchList(List<BankBehalfBranch> bankBehalfBranchList) {
    this.bankBehalfBranchList = bankBehalfBranchList;
  }

  public MerManagerDao getMerManagerDao() {
    return merManagerDao;
  }

  public void setMerManagerDao(MerManagerDao merManagerDao) {
    this.merManagerDao = merManagerDao;
  }

  public MerModelDao getMerModelDao() {
    return merModelDao;
  }

  public void setMerModelDao(MerModelDao merModelDao) {
    this.merModelDao = merModelDao;
  }

  public MerManager getMerManager() {
    return merManager;
  }

  public void setMerManager(MerManager merManager) {
    this.merManager = merManager;
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

  public CssConfigDao getCssConfigDao() {
    return cssConfigDao;
  }

  public void setCssConfigDao(CssConfigDao cssConfigDao) {
    this.cssConfigDao = cssConfigDao;
  }

  public List<CssConfig> getCssConfigList() {
    return cssConfigList;
  }

  public void setCssConfigList(List<CssConfig> cssConfigList) {
    this.cssConfigList = cssConfigList;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
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

  public List<MccCode> getMccCodeList() {
    return mccCodeList;
  }

  public void setMccCodeList(List<MccCode> mccCodeList) {
    this.mccCodeList = mccCodeList;
  }

  public List<BusType> getBusTypeList() {
    return busTypeList;
  }

  public String getTransNotifyActionList() {
    return transNotifyActionList;
  }

  public void setTransNotifyActionList(String transNotifyActionList) {
    this.transNotifyActionList = transNotifyActionList;
  }

  public void setBusTypeList(List<BusType> busTypeList) {
    this.busTypeList = busTypeList;
  }

  public TerminalTypeService getTerminalTypeService() {
    return terminalTypeService;
  }

  public void setTerminalTypeService(TerminalTypeService terminalTypeService) {
    this.terminalTypeService = terminalTypeService;
  }

  public List<TerminalType> getTerminalTypeList() {
    return terminalTypeList;
  }

  public void setTerminalTypeList(List<TerminalType> terminalTypeList) {
    this.terminalTypeList = terminalTypeList;
  }

  public String getConfigCodeJson() {
    return configCodeJson;
  }

  public void setConfigCodeJson(String configCodeJson) {
    this.configCodeJson = configCodeJson;
  }

  public String getLevelPwd() {
    return levelPwd;
  }

  public void setLevelPwd(String levelPwd) {
    this.levelPwd = levelPwd;
  }

  public String getSavePath() {
    return savePath;
  }

  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }

  public static int getBufferSize() {
    return BUFFER_SIZE;
  }

  public SysManagerService getSysManagerService() {
    return sysManagerService;
  }

  public void setSysManagerService(SysManagerService sysManagerService) {
    this.sysManagerService = sysManagerService;
  }

  public SysManager getSysManager() {
    return sysManager;
  }

  public void setSysManager(SysManager sysManager) {
    this.sysManager = sysManager;
  }

  public SysOpLogDao getSysOpLogDao() {
    return sysOpLogDao;
  }

  public void setSysOpLogDao(SysOpLogDao sysOpLogDao) {
    this.sysOpLogDao = sysOpLogDao;
  }

  public MerInfoUpdate getMerInfoUpdate() {
    return merInfoUpdate;
  }

  public void setMerInfoUpdate(MerInfoUpdate merInfoUpdate) {
    this.merInfoUpdate = merInfoUpdate;
  }

  public SubMerRate getSubMerRate2() {
    return subMerRate2;
  }

  public void setSubMerRate2(SubMerRate subMerRate2) {
    this.subMerRate2 = subMerRate2;
  }

  public MerInfoUpdateLogDao getMerInfoUpdateLogDao() {
    return merInfoUpdateLogDao;
  }

  public void setMerInfoUpdateLogDao(MerInfoUpdateLogDao merInfoUpdateLogDao) {
    this.merInfoUpdateLogDao = merInfoUpdateLogDao;
  }

  public MerInfoUpdateLog getMerInfoUpdateLog() {
    return merInfoUpdateLog;
  }

  public void setMerInfoUpdateLog(MerInfoUpdateLog merInfoUpdateLog) {
    this.merInfoUpdateLog = merInfoUpdateLog;
  }

  public MerTract getMerTract2() {
    return merTract2;
  }

  public void setMerTract2(MerTract merTract2) {
    this.merTract2 = merTract2;
  }

  public List<MerInfoUpdate> getSubMerUpdateList() {
    return subMerUpdateList;
  }

  public void setSubMerUpdateList(List<MerInfoUpdate> subMerUpdateList) {
    this.subMerUpdateList = subMerUpdateList;
  }

  public SubMerRateDao getSubMerRateDao() {
    return subMerRateDao;
  }

  public void setSubMerRateDao(SubMerRateDao subMerRateDao) {
    this.subMerRateDao = subMerRateDao;
  }

  public SubMerRate getSubMerRate() {
    return subMerRate;
  }

  public void setSubMerRate(SubMerRate subMerRate) {
    this.subMerRate = subMerRate;
  }

  public String getSubMerUpdateJson() {
    return subMerUpdateJson;
  }

  public void setSubMerUpdateJson(String subMerUpdateJson) {
    this.subMerUpdateJson = subMerUpdateJson;
  }

  public PlatModelDao getPlatModelDao() {
    return platModelDao;
  }

  public void setPlatModelDao(PlatModelDao platModelDao) {
    this.platModelDao = platModelDao;
  }

  public List<PlatModel> getPlatModelList() {
    return platModelList;
  }

  public void setPlatModelList(List<PlatModel> platModelList) {
    this.platModelList = platModelList;
  }

  public MerInfoUpdateService getMerInfoUpdateService() {
    return merInfoUpdateService;
  }

  public void setMerInfoUpdateService(MerInfoUpdateService merInfoUpdateService) {
    this.merInfoUpdateService = merInfoUpdateService;
  }

  public MerSettleStatictisDao getMerSettleStatictisDao() {
    return merSettleStatictisDao;
  }

  public void setMerSettleStatictisDao(MerSettleStatictisDao merSettleStatictisDao) {
    this.merSettleStatictisDao = merSettleStatictisDao;
  }

  public List<SubMerRate> getSubMerRateList() {
    return subMerRateList;
  }

  public void setSubMerRateList(List<SubMerRate> subMerRateList) {
    this.subMerRateList = subMerRateList;
  }

  public List<SubMerRate> getSubMerRateList2() {
    return subMerRateList2;
  }

  public void setSubMerRateList2(List<SubMerRate> subMerRateList2) {
    this.subMerRateList2 = subMerRateList2;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getSumAmt() {
    return sumAmt;
  }

  public void setSumAmt(String sumAmt) {
    this.sumAmt = sumAmt;
  }

  public String getAvgAmt() {
    return avgAmt;
  }

  public void setAvgAmt(String avgAmt) {
    this.avgAmt = avgAmt;
  }

  public String getPayBankId() {
    return payBankId;
  }

  public void setPayBankId(String payBankId) {
    this.payBankId = payBankId;
  }

  public String getBackUrl() {
    return backUrl;
  }

  public void setBackUrl(String backUrl) {
    this.backUrl = backUrl;
  }

  public String getRate() {
    return rate;
  }

  public void setRate(String rate) {
    this.rate = rate;
  }

  public PayBankCodeDao getPayBankCodeDao() {
    return payBankCodeDao;
  }

  public void setPayBankCodeDao(PayBankCodeDao payBankCodeDao) {
    this.payBankCodeDao = payBankCodeDao;
  }

  public TransInfoReq getTir() {
    return tir;
  }

  public void setTir(TransInfoReq tir) {
    this.tir = tir;
  }

  public List<PayBankCode> getPayBankCodeList() {
    return payBankCodeList;
  }

  public void setPayBankCodeList(List<PayBankCode> payBankCodeList) {
    this.payBankCodeList = payBankCodeList;
  }

  public RegionCodeDao getRegionCodeDao() {
    return regionCodeDao;
  }

  public void setRegionCodeDao(RegionCodeDao regionCodeDao) {
    this.regionCodeDao = regionCodeDao;
  }

  public MccCodeDao getMccCodeDao() {
    return mccCodeDao;
  }

  public void setMccCodeDao(MccCodeDao mccCodeDao) {
    this.mccCodeDao = mccCodeDao;
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

}
