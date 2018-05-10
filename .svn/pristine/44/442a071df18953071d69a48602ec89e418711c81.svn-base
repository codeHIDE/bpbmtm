<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>机构商户审批</title>
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
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script type="text/javascript">
			function update(merSysId,status){
				var url = "<s:url value='/merchantInfo!approveMerchant.ac'/>";
				if(confirm("是否确认操作？"))
				$.post(url,
				{merSysId:merSysId,status:status},
				function(data){
					if(data=='succ'){
						$("#merStatus").html("<font color='green'>已审批</font>");
						alert("已审批！");
						window.parent.location.reload();
					}else if(data=='fone'){
						alert("审批失败");
					}else if(data=='succOnline'){
						$("#merStatus").html("<font color='green'>开通服务</font>");
						alert("已上线！");
						window.parent.location.reload();
					}else if(data=='fonesOnline'){
							alert("交易配置信息不完整，无法上线!");
					}else if(data=='succTopY'){
						alert("已恢复");
						window.parent.location.reload();
					}else if(data=='succSuspended'){
						$("#merStatus").html("<font color='red'>暂停服务</font>");
						alert("已暂停！");
						window.parent.location.reload();
					}else if(data=='succTopN'){	
						alert("操作失败，请重新操作!");
					}else if(data=='fonesSuspended'){
						alert("暂停失败!");
					}else if(data=='merManagerFone'){
						alert("没有超级管理员，请添加!");
					}else if(data=='listFone'){
						alert("请配置机构通道或者应用通道!");
					}else if(data=='merRiskFone'){
						alert("请填写风控信息");
					}else if(data=='merTransFone'){
						alert("请填写交易配置\n(操作-交易配置-机构交易配置)");
					}else{	
						alert("操作失败，请重新操作!");
					}
				},"text");
			}
			
			
			
			function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
		</script>
		<style type="text/css">
			tr {
				height: 30px;
			}
			
			td{
					width: 1000px;
			}
		</style>
	</head>
	<body style="padding: 4px;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
						<table width="95%" border="0" cellpadding="0" cellspacing="0">
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
								工商注册号：
							</td>
							<td>
								<s:property value="merchantInfo.merRegNo"/>
							</td>
							
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td>
								<s:property value="merchantInfo.merRegAddr"/>
							</td>
						</tr>
						<tr>
						<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
								<s:property value="merchantInfo.merTaxNo"/>
							</td>
							<td style="text-align: right;">
								联系地址：
							</td>
							<td>
								<s:property value="merchantInfo.contactorAddr"/>
							</td>
							
						</tr>
						<tr>
						<td style="text-align: right;">
								对外经营名称：
							</td>
							<td>
								<s:property value="merchantInfo.dispName"/>
							</td>
							<td style="text-align: right;">
								法人代表：
							</td>
							<td style="text-align: left;">
								<s:property value="merchantInfo.merLegalPerson"/>
								<s:if test="merchantInfo.merLegalPerson==''">未填写</s:if>
							</td>
							
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								法人证件号码：
							</td>
							<td style="text-align: left;">
								<s:property value="merchantInfo.merLegalIdcard"/>
								<s:if test="merchantInfo.merLegalIdcard==''">未填写</s:if>
							</td>
							<td width="120px" style="text-align: right;">
								平台商户号：
							</td>
							<td style="text-align: left;">
								<input type="hidden" name="merchantInfo.platMerId" id="platMerId"  value="<s:property value="merchantInfo.platMerId"/>"/>
								<s:if test="merchantInfo.platMerId == -1">
									未设置
								</s:if>
								<s:else>
									<s:property value="merchantInfo.platMerId"/>
								</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								结算机构：
							</td>
							<td>
								<s:property value="merchantInfo.settAgency" />
								<s:if test="merchantInfo.settAgency==''">未填写</s:if>
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
								<s:property value="merchantInfo.contactor"/>
								<s:if test="merchantInfo.contactor==''">未填写</s:if>
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
								<s:property value="merchantInfo.contactorPhone"/>
								<s:if test="merchantInfo.contactorPhone==''">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								签约人：
							</td>
							<td>
								<s:property value="merchantInfo.contactorPhone"/>	
								<s:if test="merchantInfo.contactorPhone==''">未填写</s:if>
							</td>	
						</tr>
						<tr>
						
							<td width="150px" style="text-align: right;">
								签约日期：
							</td>
							<td>
								<s:property value="merchantInfo.signDate"/>
								<s:if test="merchantInfo.signDate==''">未填写</s:if>
								
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
								<s:if test="merchantInfo.status==3">
								<span style="color:red">暂停服务</span></s:if>
								<s:if test="merchantInfo.status==4">黑名单</s:if>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br />
								<s:if test="merchantInfo.status==0 ">
									<input type="button" value="确认审批"  id="btn1" class="l-button" onclick="update('<s:property value="merchantInfo.merSysId"/>','1')"/>
								</s:if>
								<s:if test="merchantInfo.status==1">
									<input type="button" value="确认上线"  id="btn2" class="l-button" onclick="update('<s:property value="merchantInfo.merSysId"/>','2')"/>
								</s:if>
								<s:elseif test="merchantInfo.status==2">
									<input type="button" value="暂 停"  id="btn3" class="l-button" onclick="update('<s:property value="merchantInfo.merSysId"/>','3')"/>
								</s:elseif>
								<s:elseif test="merchantInfo.status==3">
									<input type="button" value="恢 复"  id="btn4" class="l-button" onclick="update('<s:property value="merchantInfo.merSysId"/>','4')"/>
								</s:elseif>
							</td>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
							</td>
							
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
