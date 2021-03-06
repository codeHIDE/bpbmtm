package com.bypay.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.inject.Inject;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.Ostermiller.util.MD5;
import com.bypay.common.BaseAction;
import com.bypay.dao.AgenctInfoDao;
import com.bypay.dao.AgentManagerDao;
import com.bypay.dao.BankBehalfBranchDao;
import com.bypay.dao.BusTypeDao;
import com.bypay.dao.CssConfigDao;
import com.bypay.dao.MerInfoUpdateLogDao;
import com.bypay.dao.MerTractDao;
import com.bypay.dao.MerTransDao;
import com.bypay.dao.SubMerInfoDao;
import com.bypay.dao.SysOpLogDao;
import com.bypay.dao.TerminalTypeDao;
import com.bypay.domain.AgenctInfo;
import com.bypay.domain.AgentManager;
import com.bypay.domain.BankBehalfBranch;
import com.bypay.domain.BusType;
import com.bypay.domain.CssConfig;
import com.bypay.domain.MerTrans;
import com.bypay.domain.SubMerInfo;
import com.bypay.domain.SysManager;
import com.bypay.domain.SysOpLog;
import com.bypay.domain.TerminalType;
import com.bypay.service.SubMerInfoService;
import com.bypay.service.SysManagerService;
import com.bypay.util.DateUtil;
import com.bypay.util.Md5Util;
import com.bypay.util.PageUtil;
import com.bypay.util.UploadPicUtil;
import com.google.common.collect.Maps;

public class AgenctInfoAction extends BaseAction {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  @Inject
  private SysOpLogDao sysOpLogDao;
  @Inject
  private SubMerInfoDao subMerInfoDao;
  @Inject
  private SubMerInfoService subMerInfoService;

  private SubMerInfo subMerInfo;
  private AgenctInfo agenctInfo;
  private AgentManager agentManager;
  @Inject
  private AgenctInfoDao agenctInfoDao;
  @Inject
  private MerTractDao merTractDao;
  @Inject
  private MerInfoUpdateLogDao merInfoUpdateLogDao;
  @Inject
  private AgentManagerDao agentManagerDao;
  private List<AgenctInfo> agenctInfoList;
  private String merSysId;
  private String agencyName;
  private String level;
  private File agefile;
  private File subfile;
  private String agentId;

  @Inject
  private BankBehalfBranchDao bankBehalfBranchDao; // 银行 Dao层对象
  private List<BankBehalfBranch> bankBehalfBranchList;// 用于存储查询到的银行
  @Inject
  private BusTypeDao busTypeDao; // 业务类别Dao层队形
  private List<BusType> busTypeList; // 用于存储查询到的业务类别
  @Inject
  private TerminalTypeDao terminalTypeDao; // 产品类别Dao层队形
  private List<TerminalType> terminalTypeList; // 用于存储查询到的产品类别
  @Inject
  private CssConfigDao cssConfigDao; // 色调Dao层队形
  private List<CssConfig> cssConfigList; // 用于存储查询到的色调
  @Inject
  private SysManagerService sysManagerService; // 系统管理员service层对象
  private SysManager sysManager; // 系统管理员 对象
  @Inject
  private MerTransDao merTransDao;
  // 分页开始
  private int page = 1;
  private int pageSize = 15;
  // 记录页面现实内容
  private int view;
  ResourceBundle rb = ResourceBundle.getBundle("com/bypay/config/bmtmUtil", Locale.getDefault());

  // /////////////////////////////////// 业务↓
  // /////////////////////////////////////////

  // 查询所有
  public void selectAgenInfoList() {
    try {
      Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
      ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
      ServletActionContext.getResponse().setCharacterEncoding("utf-8");
      if (agenctInfo.getAgentName() != null) {
        agenctInfo.setAgentName(new String(agenctInfo.getAgentName().getBytes("ISO-8859-1"),
            "UTF-8"));
      }
      String logName = (String) getSession().getAttribute("userName");
      //特殊用户只看外卡 
      if(logName.equals("whf")){
          map.put("merSysIds", "'104','126','144','149','151'");
      }else{
          map.put("merSysIds", null);
      }
      map.put("agenctInfo", agenctInfo);
      int count = agenctInfoDao.selectAgenctInfoAllCount(map);
      agenctInfoList = agenctInfoDao.selectAgenctInfoAll(map);
      JSONArray array = JSONArray.fromObject(agenctInfoList);
      JSONObject object = new JSONObject();
      object.put("Rows", array.toString());
      object.put("Total", count);
      merSysId = object.toString();
      getResponse().getWriter().write(merSysId);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 查询指定id的代理商户
  public String agencyDetailOnline() {
    try {
      terminalTypeList = terminalTypeDao.selectTerminalTypeAll();
      busTypeList = busTypeDao.selectBusTypeAll();
      agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
      if (agenctInfo != null || agenctInfo.equals("")) {
        return "agencyDetailOnlineYes";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "agencyDetailOnlineNo";
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
      agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
      if (agefile != null) {// 上传代理商户LOGO
        f = true;
        dstPath = dstPath + "/" + agenctInfo.getAgentId() + ".png";
        dstFile = new File(dstPath);
        if (!UploadPicUtil.uploadImgs(agefile, dstFile)) {
          getResponse().getWriter().write("ageerr");
          System.out.println("代理商户LOGO图片上传失败");
        } else {
          System.out.println("代理商户LOGO上传成功");
          // 代理商户LOGO上传成功后修改代理数据库中LOGO字段地址
          AgenctInfo info = new AgenctInfo();
          info.setAgentId(agenctInfo.getAgentId());
          info.setLogo(dstPath);
          agenctInfoDao.updateAgeLogo(info);
          getResponse().getWriter().write("agesucc");
        }
      }
      // if(subfile!=null){//上传子商户LOGO
      // f=true;
      // dstPath = rb.getString("upload-path") +
      // agenctInfo.getAgentId()+"_sub_logo.png";
      // dstFile = new File(dstPath);
      // if(!UploadPicUtil.uploadImgs(subfile, dstFile)){
      // getResponse().getWriter().write("suberr");
      // System.out.println("子商户LOGO图片上传失败");
      // }else{
      // System.out.println("子商户LOGO上传成功");
      // getResponse().getWriter().write("subsucc");
      // }
      // }
      if (!f) {
        System.out.println("未接收到图片");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 查询代理商户LOGO信息
  public String ageUploadLogo() {
    try {
      agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "ageUploadLogo";
  }

  public String updateAgency() {
    try {
      bankBehalfBranchList = bankBehalfBranchDao.selectBankBehalfBranchList();
      terminalTypeList = terminalTypeDao.selectTerminalTypeAll();
      busTypeList = busTypeDao.selectBusTypeAll();
      agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
      cssConfigList = cssConfigDao.selectCssConfig();
      if (agenctInfo != null || agenctInfo.equals("")) {
        return "agencyUpdateYes";
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "agencyDetailOnlineNo";
  }

  // 修改代理商户
  public void agencyUpdate() {
    try {
      if (agenctInfo == null) {
        getRequest().setAttribute("res", false);
      } else {
        if (agenctInfo.getTerminalType() != null) {// 调整产品类别的值（将‘04，08’变为‘04|08’）
          String[] are = agenctInfo.getTerminalType().split(",");
          String s = "|";
          for (int i = 0; i < are.length; i++) {
            if (i + 1 == are.length) {
              System.out.println(are[i].length());
              s = s + are[i];
              break;
            }
            s = s + are[i] + "|";
          }
          s = s.replace(" ", "");
          agenctInfo.setTerminalType(s);
          if ("-1".equals(agenctInfo.getColor())) {
            agenctInfo.setColor("");
          }
        }
        boolean f = agenctInfoDao.updateStatus(agenctInfo);
        if (f)
          getResponse().getWriter().write("succ");
        else
          getResponse().getWriter().write("fone");
        getRequest().setAttribute("id", agenctInfo.getAgentId());
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 修改代理商户状态
  public void agencyDetail() {
    try {
      if (agenctInfo == null) {
        getResponse().getWriter().write("fone");
        return;
      }
      boolean f = agenctInfoDao.updateStatus(agenctInfo);
      if (!f) {
        if (f) {
          getResponse().getWriter().write("fone");
        } else {
          getResponse().getWriter().write("fonesSuspended");
        }
      } else {
        subMerInfo = new SubMerInfo();
        subMerInfo.setAgentIdL1(agenctInfo.getAgentId());
        subMerInfo.setAgentIdL2(agenctInfo.getAgentId());
        if (agenctInfo.getStatus().equals("1")) { // 判断代理商执行的是暂停还是启用
          getResponse().getWriter().write("succ");
          subMerInfo.setStatus("2"); // 1：启用
        } else {
          subMerInfo.setStatus("3"); // 2：暂停
          getResponse().getWriter().write("succSuspended");
        }
        subMerInfoDao.updateSubMerInfoSTATUS(subMerInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 验证密码的页面跳转方法
  public String passCheck() {
    return "checkPass";
  }

  // 点击修改结算信息前进行密码验证
  public void selectSubMerInfoPass() {
    try {
      int num = 0;
      sysManager.setLevelPwd(Md5Util.getMD5(sysManager.getLevelPwd()));
      SysManager sm = sysManagerService.getSysManager(sysManager);
      if (sm != null) {
        getResponse().getWriter().write("succ");
        num = 1;
      } else {
        getResponse().getWriter().write("fone");
      }
      SysOpLog sysOpLog = new SysOpLog();
      sysOpLog.setLoginName(sm.getLoginName()); // 登录名
      if (num == 1) // 描述
        sysOpLog.setOpDesc("成功");
      else
        sysOpLog.setOpDesc("失败");
      sysOpLog.setOpTime(DateUtil.getDate("yyyy-MM-dd HH:mm:ss"));// 操作时间
      sysOpLog.setOpUrl("subMerInfo!selectSubMerInfoPass.ac"); // 路径
      sysOpLogDao.insertSysOpLog(sysOpLog);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 查询指定代理商户和所有的结算机构， 用于修改结算信息
  public String updatAgenctInfo() {
    try {
      bankBehalfBranchList = bankBehalfBranchDao.selectBankBehalfBranchList();
      if (agenctInfo.getAgentId() != null || agenctInfo.getAgentId() != "") {
        agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "updatAgemcuInfo";
  }

  // 修改结算信息
  public void updateSettAgenctInfo() {
    try {
      agenctInfo.setBillCycle(agenctInfo.getBillCycle().replace(" ", ""));
      if (agenctInfoDao.updateStatus(agenctInfo))
        getResponse().getWriter().write("success");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 同一个一级代理商下面的所有二级代理商
  public void selectLevel2() {
    try {
      agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
      String agentid = agenctInfo.getAgentId();
      if (agenctInfo.getLevel() == null || "".equals(agenctInfo.getLevel())
          || agenctInfo.getLevel() == "-1" || agenctInfo.getLevel() == "2") {
        getResponse().getWriter().write("LevelNULL");
      } else {
        agenctInfo = new AgenctInfo();
        agenctInfo.setSuperAgentId(agentid);
        agenctInfo.setLevel("2");
        Map<String, Object> map = PageUtil.getPageMap(page, pageSize);
        map.put("agenctInfo", agenctInfo);
        agenctInfoList = agenctInfoDao.selectAgenctInfoAll(map);
        JSONArray array = JSONArray.fromObject(agenctInfoList);
        JSONObject object = new JSONObject();
        object.put("Rows", array.toString());
        object.put("Total", agenctInfoList.size());
        merSysId = object.toString();
        getResponse().getWriter().write(merSysId);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // 修改代理商费率信息前查询代理商费率
  public String updateAgencyRateOnline() {
    agenctInfo = agenctInfoDao.selectAgenctInfoById(agenctInfo);
    /*
     * //如果要修改一级代理商费率信息 if("1".equals(agenctInfo.getLevel())){ this.setView(3);
     * }//如果要修改二级代理商费率信息 else if("2".equals(agenctInfo.getLevel())){
     * //查询所属一级代理商的信息 AgenctInfo ai=new AgenctInfo();
     * ai.setAgentId(agenctInfo.getSuperAgentId());
     * ai=agenctInfoDao.selectAgenctInfoById(ai); //如果扣率和封顶都有值 标记q
     * if(ai.getAgentRate1()!=null && ai.getAgentRate2()!=null){
     * this.setView(3); }//判断一级代理商的封顶是否有值 else if(ai.getAgentRate2()!=null){
     * //如果有就标记f this.setView(2); }//判断一级代理商的扣率是否有值 else
     * if(ai.getAgentRate2()!=null){ //如果有就标记k this.setView(1); } }
     */
    return "updateAgencyRateOnline";
  }

  // 修改代理商费率信息
  public String updateAgencyRate() {
    /*AgenctInfo ai = new AgenctInfo();
    ai = agenctInfoDao.selectAgenctInfoById(agenctInfo);
    if ("1".equals(ai.getLevel())) {// 1级
      MerTrans merTrans = merTransDao.getMerTransInfo(ai.getMerSysId());
      if (null != merTrans) {
        if (Double.parseDouble(merTrans.getMerRate1()) > Double.parseDouble(agenctInfo
            .getAgentRate1())) {
          this.setAttribute("updateAgencyRate", "merError");
          return updateAgencyRateOnline();
        }
//        if (Double.parseDouble(merTrans.getD1MerProfit()) < Double.parseDouble(agenctInfo
//            .getAgentD1Rate())) {
//          this.setAttribute("updateAgencyRate", "merError");
//          return updateAgencyRateOnline();
//        }
        if (Double.parseDouble(merTrans.getT0MerProfit()) < Double.parseDouble(agenctInfo
            .getAgentT0Rate())) {
          this.setAttribute("updateAgencyRate", "merError");
          return updateAgencyRateOnline();
        }
        Map<String, Object> AgentWhere = Maps.newHashMap();
        AgentWhere.put("superAgentId", ai.getAgentId());
        AgenctInfo agentResult = new AgenctInfo();
        // 扣率
        AgentWhere.put("orderby", "AGENT_RATE_1");
        AgentWhere.put("desc", "false");
        agentResult = agenctInfoDao.getAgentInfoByRateInfo(AgentWhere);
        if (null != agentResult) {
          if (StringUtils.isNotBlank(agentResult.getAgentRate1())) {
            if (Double.parseDouble(agenctInfo.getAgentRate1()) > Double.parseDouble(agentResult
                .getAgentRate1())) {
              this.setAttribute("updateAgencyRate", "merError");
              return updateAgencyRateOnline();
            }
          }
        }
        // D1费率
        AgentWhere.put("orderby", "AGENT_D1_RATE");
        AgentWhere.put("desc", "true");
        agentResult = agenctInfoDao.getAgentInfoByRateInfo(AgentWhere);
        if (null != agentResult) {
          if (StringUtils.isNotBlank(agentResult.getAgentD1Rate()) && StringUtils.isNotBlank(agenctInfo.getAgentD1Rate())) {
            if (Double.parseDouble(agenctInfo.getAgentD1Rate()) < Double.parseDouble(agentResult.getAgentD1Rate())) {
              this.setAttribute("updateAgencyRate", "merError");
              return updateAgencyRateOnline();
            }
          }
        }
        // T0费率
        AgentWhere.put("orderby", "AGENT_T0_RATE");
        AgentWhere.put("desc", "true");
        agentResult = agenctInfoDao.getAgentInfoByRateInfo(AgentWhere);
        if (null != agentResult) {
          if (StringUtils.isNotBlank(agentResult.getAgentT0Rate())) {
            if (Double.parseDouble(agenctInfo.getAgentT0Rate()) < Double.parseDouble(agentResult
                .getAgentT0Rate())) {
              this.setAttribute("updateAgencyRate", "merError");
              return updateAgencyRateOnline();
            }
          }
        }
      }
    } else {// 2级代理商
      ai = agenctInfoDao.getAgentInfo(ai.getSuperAgentId());
      if (null != agenctInfo) {
        if (Double.parseDouble(ai.getAgentRate1()) > Double.parseDouble(agenctInfo.getAgentRate1())) {
          // this.renderText("扣率费率应该大于等于"+ai.getAgentRate1());
          this.setAttribute("updateAgencyRate", "merError");
          return updateAgencyRateOnline();
        }
        if( StringUtils.isNotBlank(agenctInfo.getAgentD1Rate()) && StringUtils.isNotBlank(ai.getAgentD1Rate())){
        if (Double.parseDouble(ai.getAgentD1Rate()) < Double.parseDouble(agenctInfo
            .getAgentD1Rate())) {
          // this.renderText("D1费率应该小于等于"+ai.getAgentD1Rate());
          this.setAttribute("updateAgencyRate", "merError");
          return updateAgencyRateOnline();
        }
        }
        if (Double.parseDouble(ai.getAgentT0Rate()) < Double.parseDouble(agenctInfo
            .getAgentT0Rate())) {
          // this.renderText("T0费率应该小于等于"+ai.getAgentT0Rate());
          this.setAttribute("updateAgencyRate", "merError");
          return updateAgencyRateOnline();
        }
      }
    }
    if (agenctInfoDao.updateStatus(agenctInfo)) {// 如果修改代理商费率成功
      // 修改该代理商下所有子商户的费率信息
      // agenctInfoDao.updateSubMerInfoInAgenctInfo();
      // MerInfoUpdateLog miul=new MerInfoUpdateLog();
      // miul.setMerId(agenctInfo.getAgentId()); //代理商号
      // 查询修改的代理商信息，判断商户级别.
      // if(ai.getLevel()=="1"||"1".equals(ai.getLevel()))
      // miul.setMerType("2");
      // else
      // miul.setMerType("3");
      // JSONObject jo=JSONObject.fromObject(ai);
      // miul.setOrgValue(jo.toString());
      // agenctInfo=agenctInfoDao.selectAgenctInfoById(agenctInfo);
      // jo=JSONObject.fromObject(agenctInfo);
      // miul.setNewValue(jo.toString());
      // if(null==merInfoUpdateLogDao.selectOrgTime(miul))
      // miul.setOrgTime(ai.getCreateTime());
      // else
      // if(merInfoUpdateLogDao.selectOrgTime(miul).getUpdateTime()!=null)
      // miul.setOrgTime(merInfoUpdateLogDao.selectOrgTime(miul).getUpdateTime());
      // SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
      // miul.setUpdateTime(df.format(new Date()));
      // merInfoUpdateLogDao.insertMerInfoUpdateLog(miul);
      getRequest().setAttribute("updateAgencyRate", "succ");
    } else
      getRequest().setAttribute("updateAgencyRate", "fone");
    return updateAgencyRateOnline();*/
    
    if (agenctInfoDao.updateStatus(agenctInfo)) {// 如果修改代理商费率成功
        getRequest().setAttribute("updateAgencyRate", "succ");
      } else
        getRequest().setAttribute("updateAgencyRate", "fone");
      return updateAgencyRateOnline();
  }
  /**
   * 
   * @Description: 重置管理员密码
   * @Auther: lijialiang
   * @Date: 2014-12-12 上午10:56:56
   */
  public void updatePassword() {
    if (agentManager == null) {
      agentManager = new AgentManager();
    }
    agentManager.setAgentId(agentId);
    agentManager.setLoginPwd(MD5.getHashString("123456"));
    Integer update = agentManagerDao.updateAgenctInfoPassword(agentManager);
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

  // /////////////////////////////////////////GET
  // SET///////////////////////////////////////////

  public AgentManager getAgentManager() {
    return agentManager;
  }

  public void setAgentManager(AgentManager agentManager) {
    this.agentManager = agentManager;
  }

  public String getAgentId() {
    return agentId;
  }

  public void setAgentId(String agentId) {
    this.agentId = agentId;
  }

  public AgenctInfo getAgenctInfo() {
    return agenctInfo;
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

  public void setAgenctInfo(AgenctInfo agenctInfo) {
    this.agenctInfo = agenctInfo;
  }

  public AgenctInfoDao getAgenctInfoDao() {
    return agenctInfoDao;
  }

  public void setAgenctInfoDao(AgenctInfoDao agenctInfoDao) {
    this.agenctInfoDao = agenctInfoDao;
  }

  public String getMerSysId() {
    return merSysId;
  }

  public void setMerSysId(String merSysId) {
    this.merSysId = merSysId;
  }

  public String getAgencyName() {
    return agencyName;
  }

  public void setAgencyName(String agencyName) {
    this.agencyName = agencyName;
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

  public List<AgenctInfo> getAgenctInfoList() {
    return agenctInfoList;
  }

  public void setAgenctInfoList(List<AgenctInfo> agenctInfoList) {
    this.agenctInfoList = agenctInfoList;
  }

  public File getAgefile() {
    return agefile;
  }

  public void setAgefile(File agefile) {
    this.agefile = agefile;
  }

  public File getSubfile() {
    return subfile;
  }

  public void setSubfile(File subfile) {
    this.subfile = subfile;
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

  public ResourceBundle getRb() {
    return rb;
  }

  public void setRb(ResourceBundle rb) {
    this.rb = rb;
  }

  public SubMerInfoDao getSubMerInfoDao() {
    return subMerInfoDao;
  }

  public void setSubMerInfoDao(SubMerInfoDao subMerInfoDao) {
    this.subMerInfoDao = subMerInfoDao;
  }

  public SubMerInfo getSubMerInfo() {
    return subMerInfo;
  }

  public void setSubMerInfo(SubMerInfo subMerInfo) {
    this.subMerInfo = subMerInfo;
  }

  public BusTypeDao getBusTypeDao() {
    return busTypeDao;
  }

  public void setBusTypeDao(BusTypeDao busTypeDao) {
    this.busTypeDao = busTypeDao;
  }

  public List<BusType> getBusTypeList() {
    return busTypeList;
  }

  public void setBusTypeList(List<BusType> busType) {
    this.busTypeList = busType;
  }

  public TerminalTypeDao getTerminalTypeDao() {
    return terminalTypeDao;
  }

  public void setTerminalTypeDao(TerminalTypeDao terminalTypeDao) {
    this.terminalTypeDao = terminalTypeDao;
  }

  public List<TerminalType> getTerminalTypeList() {
    return terminalTypeList;
  }

  public void setTerminalTypeList(List<TerminalType> terminalTypeList) {
    this.terminalTypeList = terminalTypeList;
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

  public MerInfoUpdateLogDao getMerInfoUpdateLogDao() {
    return merInfoUpdateLogDao;
  }

  public void setMerInfoUpdateLogDao(MerInfoUpdateLogDao merInfoUpdateLogDao) {
    this.merInfoUpdateLogDao = merInfoUpdateLogDao;
  }

  public void setSysOpLogDao(SysOpLogDao sysOpLogDao) {
    this.sysOpLogDao = sysOpLogDao;
  }

  public MerTractDao getMerTractDao() {
    return merTractDao;
  }

  public void setMerTractDao(MerTractDao merTractDao) {
    this.merTractDao = merTractDao;
  }

  public int getView() {
    return view;
  }

  public void setView(int view) {
    this.view = view;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }
}
