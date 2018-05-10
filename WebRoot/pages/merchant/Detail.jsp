<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>机构商户详情</title>
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
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function cls(){
	            window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove(); 
			}
		</script>
		
		<style type="text/css">
			tr {
				height: 30px;
			}
		</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">

		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
						<table width="100%" border="0" cellpadding="0" cellspacing="0" >
						<tr>
						<td style="text-align: right;" width="20%">
								机构商户号：
							</td>
							<td id="merSysId" width="30%">
								<s:property value="merchantInfo.merSysId"/>
							</td>
							<td  style="text-align: right;" width="20%">
								工商注册名：
							</td>
							<td style="text-align: left;" width="30%">
								<s:property value="merchantInfo.merName"/>
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
								机构商户状态：
							</td>
							<td id="merStatus">
								<s:if test="merchantInfo.status==0">
								<span style="color:red">未审批</span></s:if>
								<s:if test="merchantInfo.status==1">
								<span style="color:green">已审批</span></s:if> 
								<s:if test="merchantInfo.status==2">
								<span style="color:green">开通服务</span></s:if>
								<s:if test="merchantInfo.status==3">暂停服务</s:if>
								<s:if test="merchantInfo.status==4">停止服务</s:if>
								<s:if test="merchantInfo.status==5">黑名单</s:if>		
							</td>
						<td style="text-align: right;">
								工商注册号：
							</td>
							<td>
								<s:property value="merchantInfo.merRegno"/>
							</td>
						</tr>
						<tr>
						<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
								<s:property value="merchantInfo.merTaxno"/>
							</td>
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td>
								<s:property value="merchantInfo.merRegaddr"/>
							</td>
							
							
						</tr>
						<tr>
						<td style="text-align: right;">
								对外经营名称：
							</td>
							<td>
								<s:property value="merchantInfo.merShortName"/>
							</td>
							<td style="text-align: right;">
								联系地址：
							</td>
							<td>
								<s:property value="merchantInfo.merShortName"/>
							</td>
						</tr>
						<tr>
						<td width="120px" style="text-align: right;">
								收单机构名称：
							</td>
							<td>
								<s:property value="merchantInfo.acqAgencyName"/>
								<s:if test="merchantInfo.acqAgencyName==''">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								法人证件号码：
							</td>
							<td style="text-align: left;">
								<s:property value="merchantInfo.merLegalIdcard"/>
								<s:if test="merchantInfo.merLegalIdcard==''">未填写</s:if>
							</td>
							
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								结算机构：
							</td>
							<td>
								<s:iterator value="bankBehalfBranchList" var="bank">
									<s:if test="#bank.bankLineNumber==merchantInfo.settAgencyName">
										<s:property value="#bank.bankName" />
									</s:if>
								</s:iterator>
							</td>
							<td width="150px" style="text-align: right;">
								结算账户名：
							</td>
							<td>
								<s:property value="merchantInfo.settAccountName"/>
								<s:if test="merchantInfo.settAccountName==''">未填写</s:if>
							</td>
							
						</tr>
						<tr>
						<td width="120px" style="text-align: right;">
								业务联系人：
							</td>
							<td style="text-align: left;">
								<s:property value="merchantInfo.contracter"/>
								<s:if test="merchantInfo.contracter==''">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								结算账户号：
							</td>
							<td>
								<s:property value="merchantInfo.settAccountNo"/>
								<s:if test="merchantInfo.settAccountNo==''">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td>
								<s:property value="merchantInfo.contracterPhone"/>
								<s:if test="merchantInfo.contracterPhone==''">未填写</s:if>
							</td>
							<td style="text-align: right;">
								入网时间：
							</td>
							<td>
								<s:property value="merchantInfo.createTime"/>
								<s:if test="merchantInfo.createTime==null">未填写</s:if>
							</td>
						</tr>
						<tr>
						<td width="150px" style="text-align: right;">
								签约人：
							</td>
							<td>
								<s:property value="merchantInfo.signPerson"/>	
								<s:if test="merchantInfo.signPerson==''">未填写</s:if>
							</td>	
							<td width="120px" style="text-align: right;">
								签约日期：
							</td>
							<td>
								<s:property value="merchantInfo.signDate"/>
								<s:if test="merchantInfo.signDate==''">未填写</s:if>
								
							</td>
						</tr>
						<tr>
						<td style="text-align: right;">
								法人代表：
							</td>
							<td style="text-align: left;">
								<s:property value="merchantInfo.merLegalPerson"/>
								<s:if test="merchantInfo.merLegalPerson==''">未填写</s:if>
							</td>
					
							<td width="120px" style="text-align: right;">
								POS单商户名：
							</td>
							<td>
								<s:property value="merchantInfo.dispName"/>
								<s:if test="merchantInfo.dispName==''">未填写</s:if>
								
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
