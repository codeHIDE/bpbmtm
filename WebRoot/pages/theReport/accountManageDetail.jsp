<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>通道详情</title>
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
							<td width="120px" style="text-align: right;">
								机构号：
							</td>
							<td style="text-align: left;" width="120px">
								<s:property value="accountManage.merSysId"/>
							</td>
							<td width="150px" style="text-align: right;">
								操作状态：
							</td>
							<td id="tractStatus">
								<s:if test="accountManage.status==0">
								<span style="color:red">未操作</span></s:if>
								<s:if test="accountManage.status==1">
								<span style="color:green">上账成功</span></s:if> 
								<s:if test="accountManage.status==2">
								<span style="color:red">上账失败</span></s:if>
								<input type="hidden" id="status" value="<s:property value="accountManage.status"/>" />	
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								手续费：
							</td>
							<td>
								<s:set var="feeAmt" value="accountManage.feeAmt" />
								<s:property value="#feeAmt/100"/>
								&nbsp;元 
							</td>
							<td  width="120px" style="text-align: right; ">
								金额：
							</td>
							<td id="tractId" style="text-align: left;"  width="120px">
								<s:set var="amt" value="accountManage.merAmt" />
								<s:property value="#amt/100"/>
								&nbsp;元 
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								是否有变更：
							</td>
							<td id="changeStatus">
									<s:if test="accountManage.changeStatus==0">
									<span style="color:red">无变更</span></s:if>
									<s:if test="accountManage.changeStatus==1">
									<span style="color:green">有变更</span></s:if> 
									<input type="hidden" id="status" value="<s:property value="accountManage.changeStatus"/>" />	
								</td>
								<td width="150px" style="text-align: right;">
								变更前金额：
							</td>
							<td >
								<s:set var="amts" value="accountManage.changeAmt" />
								<s:property value="#amts/100"/>
								&nbsp;元 
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								变更操作员：
							</td>
							<td style="text-align: left;">
								<s:property value="accountManage.changeOpenId"/>
								<s:if test="accountManage.changeOpenId==''">未填写</s:if>
							</td>
							<td width="120px" style="text-align: right;">
								提交操作员：
							</td>
							<td>
								<s:property value="accountManage.updateOpenId"/>
								<s:if test="accountManage.updateOpenId==null">未填写</s:if>
							</td>	
						</tr>
						<tr>
							<td style="text-align: right;">
								清算日期：
							</td>
							<td>
								<s:property value="accountManage.settleDate"/>
							</td>
							<td width="120px" style="text-align: right;">
								变更原因：
							</td>
							<td>
								<s:property value="accountManage.changeReason"/>
								<s:if test="accountManage.changeReason==null">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								创建日期：
							</td>
							<td>
								<s:property value="accountManage.createTime"/>
							</td>
						</tr>
						<tr>
							<td colspan="5" align="center">
							<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
