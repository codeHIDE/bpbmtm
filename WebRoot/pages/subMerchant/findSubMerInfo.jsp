<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>子商户详情</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script type="text/javascript">
			function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
		function check(){
			if(len("subMerName","60","All","子商户入网名")&&
				len("taxNo","20","N","税务登记号")&&
				len("regAddr","60","All","工商注册地址")&&
				len("regNo","60","N","工商注册号码")&&
				len("legalPerson","60","EandS","法人代表")&&
				len("legalIdcard","60","N","法人证件号码")&&
				len("contactor","60","EandS","联系人")&&
				len("contactorPhone","60","N","联系人电话")&&
				len("dispMerId","15","N","打印商户号")&&
				len("dispMerName","60","All","打印商户名称")){
			return true;
			}return false;
		}
		
		</script>
		<style type="text/css">
		tr {
			height: 25px;
		}
		</style>
	</head>
	<body style="padding: 4px;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				
					<table width="90%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								子商户入网号：
							</td>
							<td>
								<s:property value="subMerInfo.subMerId" />
									</td>
							<td style="text-align: right;">
								子商户入网名：
							</td>
							<td>
							<s:property value="subMerInfo.subMerName" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商户状态：
							</td>
							<td id="Status">
								<s:if test="subMerInfo.status==0"> <span style="color: red">未审批</span> </s:if>
								<s:if test="subMerInfo.status==1"> <span style="color: green">已审批</span> </s:if>
								<s:if test="subMerInfo.status==2"><span style="color: green">开通服务</span></s:if>
								<s:if test="subMerInfo.status==3">暂停服务</s:if>
								<s:if test="subMerInfo.status==4">黑名单</s:if>
								<s:if test="subMerInfo.status==5">上线失败</s:if>
								<s:if test="subMerInfo.status==-3">待二级代理审批</s:if>
								<s:if test="subMerInfo.status==-2">待一级代理审批</s:if>
								<input type="hidden" name="subMerInfo.status"
									value="<s:property value="subMerInfo.status"/>"></input>
							</td>
							<td style="text-align: right;">
								所属商户号：
							</td>
							<td>
							<s:property value="subMerInfo.merSysId" /></td>
						</tr>
						<tr>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
							<s:property value="subMerInfo.taxNo" />
							</td>
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td>
							<s:property value="subMerInfo.regAddr" />
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								地区：
							</td>
							<td>
								<s:set var="sheng" value="subMerInfo.region.substring(0,2)"></s:set>
								<s:set var="shi" value="subMerInfo.region.substring(2,4)"></s:set>
								
								<s:iterator value="regionCodeList" var="reg">
									<s:if test="#reg.level==1">
										
										<s:if test="#reg.code == #sheng"><s:property value="#reg.name"/></s:if>
										
										
									</s:if>
								</s:iterator>
								
								
								<s:iterator value="regionCodeList" var="reg">
									<s:if test="#reg.level==2 && #reg.superCode==#sheng">
										
										<s:if test="#reg.code == #shi"><s:property value="#reg.name"/></s:if>
										
									</s:if>
								</s:iterator>
								
							</td>
							<td style="text-align: right;">
								工商注册号码：
							</td>
							<td>
							<s:property value="subMerInfo.regNo" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								入网时间：
							</td>
							<td>
								<s:property value="subMerInfo.createTime" />
							</td>
							<td width="120px" style="text-align: right;">
							行业类别： 
							</td>
							<td>
								
									<s:iterator value="mccCodeList" var="mcc">										
										<s:if test="#mcc.mcc == subMerInfo.mcc"><s:property value="#mcc.desc"/></s:if>										
									</s:iterator>
							
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								法人代表：
							</td>
							<td>
							<s:property value="subMerInfo.legalPerson" />
							</td>
							
							<td width="150px" style="text-align: right;">
								法人证件号码：
							</td>
							<td>
							<s:property value="subMerInfo.legalIdcard" />
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td>
							<s:property value="subMerInfo.contactor" />
							</td>
							<td style="text-align: right;">
								联系人电话：
							</td>
							<td>
							<s:property value="subMerInfo.contactorPhone" />
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								打印商户号：
							</td>
							<td>
							<s:property value="subMerTrans.dispMerId" />
							</td>
							<td style="text-align: right;">
								打印商户名称：
							</td>
							<td>
							<s:property value="subMerTrans.disMerName" />
							</td>
						</tr>
						<s:if test="merInfo.accessType == 2">
						<tr>
							<td width="150px" style="text-align: right;">
								通道：
							</td>
							<td style="text-align: left;">
								
									<s:iterator value="tractInfoList" var="tra">
										<s:if test="(subMerRate.rateType == '01' && tractId == subMerTrans.subMerTractR1) || (subMerRate.rateType == '02' && tractId == subMerTrans.subMerTractR2)"><s:property value="tractName"/></s:if>
											
									</s:iterator>
								
							</td>
						</tr>
						</s:if>
						<s:else>
						<tr>
							<td width="150px" style="text-align: right;">
								扣率通道：
							</td>
							<td style="text-align: left;">
								<s:iterator value="merTractList" var="tra">
										<s:if test="profitType == '01'">
											
											<s:if test="tractInfo.tractId == subMerTrans.subMerTractR1"><s:property value="tractInfo.tractName"/></s:if>
												
										</s:if>
									</s:iterator>
								
							</td>
						</tr>
						</s:else>
						<tr>
							<td width="150px" style="text-align: right;">
								POS渠道：
							</td>
							<td style="text-align: left;">
									<s:iterator value="channelAddInfoList" var="channelAddInfo">
										<s:if test="#channelAddInfo.chAddId == subMerTrans.subMerTractR2"> <s:property value="#channelAddInfo.chName"/></s:if>									
									</s:iterator>
							</td>	
							<td width="150px" style="text-align: right;">
								POS渠道商户：
							</td>
							<td style="text-align: left;">
								<s:property value="subMerTrans.subMerTractR3"/>
							</td>					
						</tr>
						<s:if test="subMerInfo.status == 5">
									<tr style="color: red;">
									<td style="text-align: right;color: red;" >
										审核失败原因：
									</td>
									<td colspan="3">
										<s:property value="subMerInfo.remark"/>
									</td>
									</tr>
						</s:if>
						<tr>
							<td width="150px" style="text-align: right;">银行卡号：</td>
							<td style="text-align: left;"> <s:property value="subMerInfo.settAccountNo"/> </td>
							<td width="150px" style="text-align: right;">开户行：</td>
							<td style="text-align: left;"> <s:property value="subMerInfo.openBank"/> (<s:property value="subMerInfo.lineNum"/>) </td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">手机归属省份：</td>
							<td style="text-align: left;"> <s:property value="subMerInfo.phoneProvince"/> </td>
							<td width="150px" style="text-align: right;">手机归属城市：</td>
							<td style="text-align: left;"> <s:property value="subMerInfo.phoneCity"/></td>
						</tr>
						<!-- 费率开始 -->
						<tr>
							<td colspan="4" style="text-align: center;">
							费率信息
							</td>
						</tr>
					</table>
	<table width="90%">
<s:iterator value="subMerRateList" var="subMerRate">
	<tr style="height:35px">
		<td colspan="8" align="center">
			费率类型:
			<s:if test="#subMerRate.rateType==01">扣率</s:if>
			<s:if test="#subMerRate.rateType==02">封顶</s:if>
			<s:if test="#subMerRate.rateType==03">封顶-扣率</s:if>
			<s:if test="#subMerRate.rateType==04">T+0扣率</s:if>
			<s:if test="#subMerRate.rateType==05">T+0封顶</s:if>
		</td>
	</tr>
	<tr>
	<s:if test="#subMerRate.rateType==01||#subMerRate.rateType==03">
		<td style="text-align: right;">
			交易费率:
		</td>
		<td align="left">
			<s:property value="#subMerRate.teransRate"/> %
		</td>
	</s:if>
	<s:if test="#subMerRate.rateType==02||#subMerRate.rateType==03">
		<td style="text-align: right;">
			封顶费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.transHighestFee==null || #subMerRate.transHighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.transRateH"/> %
			</s:else>
		</td>
		<td width="120px" style="text-align: right;">
			封顶值:
		</td>
		<td align="left">
			<s:if test="#subMerRate.transHighestFee==null || #subMerRate.transHighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.transHighestFee"/> 分
			</s:else>
		</td>
	</s:if>
	</tr>
	<!-- 如果子商户的一级代理商不为空，页面显示所有一级代理费率信息 -->
	<s:if test="subMerInfo.agentIdL1 != null && subMerInfo.agentIdL1 != '-1'">
	<tr style="height:35px">
	<s:if test="#subMerRate.rateType==01||#subMerRate.rateType==03">
		<td align="right">
		一级代理费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1Rate==null || #subMerRate.agentL1Rate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1Rate"/> 分
			</s:else>
		</td>
	</s:if>
	<s:if test="#subMerRate.rateType==02||#subMerRate.rateType==03">
		<td align="right">
		一级代理封顶费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1RateH==null || #subMerRate.agentL1RateH=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1RateH"/> 分
			</s:else>
		</td>
		<td align="right">
		一级代理封顶值:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1HighestFee==null||#subMerRate.agentL1HighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1HighestFee"/> 分
			</s:else>
		</td>
	</s:if>
		<td align="right">
			一级代理分润占:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1ProfitRate==null || #subMerRate.agentL1ProfitRate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1ProfitRate"/> 分
			</s:else>
		</td>
	</tr>
	</s:if>
	<!-- 如果子商户的二级代理商不为空，页面显示所有二级代理商费率信息 -->
	<s:if test="subMerInfo.agentIdL2 != null && subMerInfo.agentIdL2 != -1">
	<tr style="height:35px">
	<s:if test="#subMerRate.rateType==01||#subMerRate.rateType==03">
		<td align="right">
		二级代理费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2Rate==null || #subMerRate.agentL2Rate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2Rate"/> 分
			</s:else>
		</td>
	</s:if>
	<s:if test="#subMerRate.rateType==02||#subMerRate.rateType==03">
		<td align="right">
		二级代理封顶费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2RateH==null || #subMerRate.agentL2RateH=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2RateH"/> 分
			</s:else>
		</td>
		<td align="right">
		二级代理封顶值:<s:property value="#subMerRate.agentL2HighestFee"/>
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2HighestFee==null||#subMerRate.agentL2HighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2HigestFee"/> 分
			</s:else>
		</td>
		</s:if>
		<td align="right">
		二级代理分润占:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2ProfitRate==null || #subMerRate.agentL2ProfitRate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2ProfitRate"/> 分
			</s:else>
		</td>
	</tr>
	</s:if>
</s:iterator>
	<tr>
		
		<td colspan="4"  align="center">
		<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()"/>
		</td>
	</tr>
</table>
				
			</div>
		</div>
	</body>
</html>
