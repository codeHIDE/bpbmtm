<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>平台商户审批</title>
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
		<script src="<s:url value='/js/jquery/jquery-ui-1.7.1.custom.min.js'/>"></script>
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function update(platMerId,status){
				if(confirm("是否确认操作？"))
				$.post("<s:url value='/merchantInfo!approvePlatMer.ac'/>",
				{platMerId:platMerId,status:status},
				function(data){
					if(data=='succ'){
						$("#merStatus").html("<font color='green'>正在使用</font>");
							alert("审批通过！");
					}else if(data=='fone'){
						$("#merStatus").html("<font color='red'>未审批</font>");
					}else if(data=='succSuspended'){
							$("#merStatus").html("<font color='red'>暂停服务</font>");
								alert("已暂停！");
					}else if(data=='fonesSuspended'){
							alert("暂停失败!");
					}else{
							alert("操作失败，请重新操作!");
						}
					
					window.parent.$.ligerDialog.close();
	          	  	window.parent.$(".l-dialog,.l-window-mask").remove();
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
	<body style="padding: 4px; overflow: hidden;">

		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
						<table width="95%" border="0" cellpadding="0" cellspacing="0">
						<tr>
						<td style="text-align: right;" width="20%">
								平台商户号：
							</td>
							<td id="merSysId" width="30%">
								<s:property value="platMerInfo.platMerId"/>
							</td>
							<td  style="text-align: right;" width="20%">
								工商注册名：
							</td>
							<td style="text-align: left;" width="30%">
								<s:property value="platMerInfo.platMerName"/>
							</td>
							
						</tr>
						<tr>
						<td style="text-align: right;">
								工商注册号：
							</td>
							<td>
								<s:property value="platMerInfo.merRegNo"/>
							</td>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
								<s:property value="platMerInfo.merTaxNo"/>
							</td>
						</tr>
						<tr>
						
							<td style="text-align: right;">
								联系地址：
							</td>
							<td>
								<s:property value="platMerInfo.contactAddr"/>
							</td>
							<td style="text-align: right;">
								法人代表：
							</td>
							<td style="text-align: left;">
								<s:property value="platMerInfo.legalPerson"/>
								<s:if test="platMerInfo.legalPerson==''">未填写</s:if>
							</td>
						</tr>
						<tr><td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td style="text-align: left;">
								<s:property value="platMerInfo.contactor"/>
								<s:if test="platMerInfo.contactor==''">未填写</s:if>
							</td>
							
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td>
								<s:property value="platMerInfo.contactPhone"/>
								<s:if test="platMerInfo.contactPhone==''">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								法人证件号码：
							</td>
							<td style="text-align: left;">
								<s:property value="platMerInfo.legalIdcard"/>
								<s:if test="platMerInfo.legalIdcard==''">未填写</s:if>
							</td>
							<td style="text-align: right;">
								入网时间：
							</td>
							<td>
								<s:property value="platMerInfo.createTime"/>
								<s:if test="platMerInfo.createTime==null">未填写</s:if>
							</td>
							
						</tr>
						<tr>
							
							<td style="text-align: right;">
								平台商户状态：
							</td>
							<td id="merStatus">
								<s:if test="platMerInfo.status==0">
								<span style="color:red">未审核</span></s:if>
								<s:if test="platMerInfo.status==1">
								<span style="color:green">正在使用</span></s:if> 
								<s:if test="platMerInfo.status==2">
								<span style="color:red">暂停服务</span></s:if>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br />
								<s:if test="platMerInfo.status==0 ">
									<input type="button" value="确认审批"  id="btn1" class="l-button" onclick="update('<s:property value="platMerInfo.platMerId"/>','1')"/>
								</s:if>
								<s:elseif test="platMerInfo.status==1">
									<input type="button" value="暂 停"  id="btn3" class="l-button" onclick="update('<s:property value="platMerInfo.platMerId"/>','2')"/>
								</s:elseif>
								<s:elseif test="platMerInfo.status==2">
									<input type="button" value="恢 复"  id="btn4" class="l-button" onclick="update('<s:property value="platMerInfo.platMerId"/>','1')"/>
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
