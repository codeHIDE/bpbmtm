package com.bypay.dao;

import java.util.List;
import java.util.Map;

import com.bypay.domain.SubMerTerminal;
import com.bypay.domain.SubMerTerminalInfo;
import com.bypay.domain.clientTool.TerminalEnable;

public interface SubMerTerminalDao {
  // 查询属于同一个子商户的子商户设备信息
  List<SubMerTerminal> selectSubMerTerminalAll(SubMerTerminal smi);

  // 查询指定id的子商户设备信息
  SubMerTerminal selectSubMerTerminalOne(SubMerTerminal subMerTerminal);

  // 重置密码
  boolean updateTreminalPwd(String smi, String sm);

  // 重置状态
  boolean updateTreminalStatus(String smi, String sm);
  SubMerTerminal selectTerminalBySubMerId(String subMerId);
  /**
   * 修改子商户设备信息
   * 
   * @param subMerTerminal
   * @return
   */
  boolean updateSubMerTerminal(SubMerTerminal subMerTerminal);

  /**
   * 查询登陆信息
   */
  SubMerTerminal getSubMerTerminal(SubMerTerminal terminalId);

  /**
   * 查询终端信息
   */
  List<SubMerTerminal> selectSubMerTerminal(Map map);

  Integer selectSubMerTerminalCount(Map map);

  /**
   * 重置
   */
  Integer reset(SubMerTerminal subMerTerminal);

  /**
   * 修改终端状态
   */
  int updateStatus(TerminalEnable terminalId);

  /**
   * 根据终端号查询
   */
  SubMerTerminal selectSubMerTerminalByTerminalId(SubMerTerminal subMerTerminal);

  /**
   * 根据商户号、终端号获取子商户终端信息
   * 
   * @param terminal
   * @return
   */
  SubMerTerminal getSubMerTerInfo(SubMerTerminal terminal);
  SubMerTerminal getSubMerTerMer(SubMerTerminal terminal);

  /**
   * 修改子商户设备的版本信息
   * 
   * @param terminalId
   * @return
   */
  boolean updateSubMerTerminalVersion(SubMerTerminal terminal);

  // 根据 子商户号 终端号 手机 查询有没有此终端
  SubMerTerminal selectSubMerTerminalByUpdatePwd(SubMerTerminal subMerTerminal);

  // 更新终端密码
  int updatePwd(SubMerTerminal subMerTerminal);

  /**
   * 修改设备最近登录时间
   * 
   * @param subMerTerminal
   */
  void updateSubMerByLastLoginTime(SubMerTerminal subMerTerminal);

  /**
   * 添加子商户设备信息
   * 
   * @param smt
   * @return
   */
  int insertSubMerTerminal(SubMerTerminal smt);

  // 修改登录名
  int updateSubMerTerminalLoginName(SubMerTerminal subMerTerminal);

  /**
   * 重置机构
   */
  Integer resetMerSysIdByTsn(SubMerTerminal subMerTerminal);

  /**
   * 根据子商户号修改子商户设备状态
   * 
   * @param status
   * @param subMerId
   * @return
   */
  int updateTreminalStatusBySubMerId(String status, String subMerId);

  // 根据子商户号查询 TSN
  SubMerTerminal getSubMerTerminalTSN(String subMerId);

  /**
   * 根据登录名/手机号查询信息
   * 
   * @param mobileNum
   * @return
   */
  List<SubMerTerminal> selectSubMerTerminalByPhone(String mobileNum);
  /**
   * 
   * @Description:清空子商户 
   * @Auther: ljl
   * @param subMerTerminal
   * @return
   * @Date: 2014-10-31 上午10:40:55
   */
  Integer resetSubMerInfo(SubMerTerminal subMerTerminal);
  /**
   * 设备信息，清除商户号，登录账户号、密码
   * @param subMerTerminal
   * @return
   */
  Integer updateLoginInfoAndSubId(SubMerTerminal subMerTerminal);
  /**
   * 
  * @Title: queryCountMerPusineTer 
  * @Description: 根据机构商号返回 机构商号、统计下属终端数、终端开通数 syw 2015.08.06
  * @param @param merSysId
  * @param @return    设定文件 
  * @return List<SubMerTerminal>    返回类型 
  * @throws
   */
  List<SubMerTerminal> queryCountMerPusineTer(Map maps);
  
  /**
   * 克隆终端
   * @Title:        cloneTer 
   * @Description:  
   * @param:        @param smi
   * @param:        @return    
   * @return:       Boolean    
   * @throws 
   * @author        Eason Jiang
   * @Date          2015-7-13 上午11:04:47
   */
  Boolean cloneTer(SubMerTerminal smi);
  
  Integer cleanLogin(SubMerTerminal subMerTerminal);
  
  int insertBatchSubMerTerminal(List<SubMerTerminal> smts);
}
