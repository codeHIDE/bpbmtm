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
		<script src="<s:url value='/js/jquery/jquery-ui-1.7.1.custom.min.js'/>"></script>
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
		
			function update(){
					if(
						length('loginName',10,'All','登录名')
						){
							//$("#form1").submit();
							if(confirm("确定修改？"))
							form1.submit();
							return true;
						}
						return false;
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
		</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
			<form id="form1" action="<s:url value='subMerTerminal!updatetractInfo.ac'/>" method="post">
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td style="text-align: right;">
							终端编码：
						</td>
						<td width="160px">
						<s:property value="subMerTerminal.tsn"/>
							<input  type="hidden" name="subMerTerminal.tsn"
									value="<s:property value="subMerTerminal.tsn"/>"></input>
						</td>
						<td style="text-align: right;">
							厂商：
						</td>
						<td id="tractId">
						<s:property value="subMerTerminal.factory"/>
						<input  type="hidden" name="subMerTerminal.factory"
									value="<s:property value="subMerTerminal.factory"/>"></input>
						</td>			
					</tr>
					<tr>
						<td style="text-align: right;">
							状态：
						</td>
						<td>
								<s:if test="subMerTerminal.status==0">
								<span style="color:red">未使用</span></s:if>
								<s:if test="subMerTerminal.status==1">
								<span style="color:green">正在使用</span></s:if> 
								<s:if test="subMerTerminal.status==2">
								<span style="color:green">暂停使用</span></s:if>
								<s:if test="subMerTerminal.status==3">
								<span style="color:red">黑名单</span></s:if>
								<input  type="hidden" name="subMerTerminal.status" value="<s:property value="subMerTerminal.status"/>"></input>	
						</td>
						<td style="text-align: right;">
							子商户号：
						</td>
						<td id="tractId">
						<s:property value="subMerTerminal.subMerId"/>
						<input  type="hidden" name="subMerTerminal.subMerId"
									value="<s:property value="subMerTerminal.subMerId"/>"></input>
							</td>
					</tr>


					<tr>
						<td width="120px" style="text-align: right;">
							机构商号：
						</td>
						<td>
						<s:property value="subMerTerminal.merSysId"/>
						<input  type="hidden" name="subMerTerminal.merSysId"
									value="<s:property value="subMerTerminal.merSysId"/>"></input>
								
						</td>					
						<td width="120px" style="text-align: right;">
							终端信息：
						</td>
						<td>
						<s:property value="subMerTerminal.terminalInfo"/>
						<input  type="hidden" name="subMerTerminal.terminalInfo"
									value="<s:property value="subMerTerminal.terminalInfo"/>"></input>
						</td>
					</tr>					
					<tr>
						<td style="text-align: right;">
							平台商号：
						</td>
						<td > 
						 <s:property value="subMerTerminal.platMerId"/>	
							<input  type="hidden" name="subMerTerminal.platMerId"
									value="<s:property value="subMerTerminal.platMerId"/>"></input>
						</td>
						<td width="150px" style="text-align: right;">
							当前版本号：
						</td>
						<td>
						 <s:property value="subMerTerminal.version"/>	
							<input  type="hidden" name="subMerTerminal.version"
								value="<s:property value="subMerTerminal.version"/>"></input>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							类别：
						</td>
						<td>
							<s:if test="subMerTerminal.category=='04'"> 刷卡器</s:if>
							<s:if test="subMerTerminal.category=='08'"> POS机</s:if>
							<input  type="hidden" name="subMerTerminal.category" value="<s:property value="subMerTerminal.category"/>"></input>
						</td>
						
							<td style="text-align: right;">
								创建时间：
							</td>
							<td style="text-align: left;">
							<s:property value="subMerTerminal.createTime"/>	
								<input  type="hidden" name="subMerTerminal.createTime"
								value="<s:property value="subMerTerminal.createTime"/>"></input>
							</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							激活时间：
						</td>
						<td>
							<s:property value="subMerTerminal.activeTime"/>
								<input  type="hidden" name="subMerTerminal.activeTime"
								value="<s:property value="subMerTerminal.activeTime"/>"></input>
						</td>
						<td width="150px" style="text-align: right;">
							最近一次更新时间：
						</td>
						<td>
							<s:property value="subMerTerminal.lastUpdateTime"/>
								<input  type="hidden" name="subMerTerminal.lastUpdateTime"
								value="<s:property value="subMerTerminal.lastUpdateTime"/>"></input>
						</td>
				</tr>
				<tr>
					<td style="text-align: right;">
							一级代理商号：
						</td>
						<td>
							<s:property value="subMerTerminal.agentIdL1"/>
								<input  type="hidden" name="subMerTerminal.agentIdL1"
								value="<s:property value="subMerTerminal.agentIdL1"/>"></input>
						</td>
						<td width="150px" style="text-align: right;">
								二级代理商号：
							</td>
							<td>
							<s:property value="subMerTerminal.agentIdL2"/>
								<input  type="hidden" name="subMerTerminal.agentIdL2"
								value="<s:property value="subMerTerminal.agentIdL2"/>"></input>
							</td>
					</tr>
					<tr>
						<td style="text-align: right;" width="120px">
							最近一次登录时间：
						</td>
						<td style="text-align: left;" width="120px">
							<s:property value="subMerTerminal.lastLoginTime"/>	
								<s:if test="subMerTerminal.lastLoginTime==''">未填写</s:if>
								<input  type="hidden" name="subMerTerminal.lastLoginTime"
								value="<s:property value="subMerTerminal.lastLoginTime"/>"></input>
						</td>
							<td width="150px" style="text-align: right;">
							最近一次登录信息：
						</td>
						<td style="text-align: left;">
							<s:property value="subMerTerminal.lastLoginInfo"/>
								<s:if test="subMerTerminal.lastLoginInfo==''">未填写</s:if>
								<input  type="hidden" name="subMerTerminal.lastLoginInfo"
							value="<s:property value="subMerTerminal.lastLoginInfo"/>"></input>
						</td>
					</tr>
					<tr>
						<td width="150px" style="text-align: right;">
							登录名：
						</td>
						<td>
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="loginName"  name="subMerTerminal.loginName" 
								value="<s:property value="subMerTerminal.loginName"/>"></input>
								<s:property value="subMerTerminal.loginName"/>
							</div>
						</td>
						
					</tr>					
					<tr>
						<td colspan="2" align="center">
							<br />
							<input type="submit" value="确认修改" id="btn" class="l-button" onclick="update()" />
						</td>
						<td colspan="2" align="center">
							<br />
							<input type="button" value="关 闭" id="btn2" class="l-button"
								onclick="cls()" />
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		<s:if test="#request.updatetractInfo == 'success'">
			<script>
				alert("修改成功!");
			</script>
		</s:if>
	</body>
</html>
