<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>管理员信息修改</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value=' /js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/common.js'/>"></script>

		<script type="text/javascript">
		
				function update(){
					if(
						length('realName',10,'All','真实姓名')
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
			<form id="form1" action="<s:url value='sysManage!updateSysManagerDetail.ac'/>" method="post">
				<input type="hidden" id="" name="tractId" value="<s:property value="tractId"/>"/>
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td style="text-align: right;">
							管理员名称：
						</td>
						<td width="160px">
						<div class="l-text" style="float:left;"> 
							<s:property value="sysManager.loginName"/>
							<input type="hidden" id="" name="sysManager.loginName" 
									value="<s:property value="sysManager.loginName"/>"></input>
								<input type="hidden" id="" name="sysManager.id" 
									value="<s:property value="sysManager.id"/>"></input>
							</div>
							
						</td>
						
						<td style="text-align: right;">
							所属部门：
						</td>
						<td id="tractId">
						<input  type="text" name="sysManager.department"
								value="<s:property value="sysManager.department"/>"></input>	
						</td>			
					</tr>
					<tr>
						<td width="120px" style="text-align: right;">
							真实姓名：
						</td>
						<td>
						<input  type="text" name="sysManager.realName" id="realName"
								value="<s:property value="sysManager.realName"/>"></input>	
						</td>					
						<td width="120px" style="text-align: right;">
							联系电话：
						</td>
						<td>
						<input  type="text" name="sysManager.phone"
								value="<s:property value="sysManager.phone"/>"></input>	
						</td>
					</tr>
					
					<tr>
						<td style="text-align: right;">
							邮箱：
						</td>
						<td > 
							<input  type="text" name="sysManager.email" id="email"
								value="<s:property value="sysManager.email"/>"></input>	
						</td>
						<td width="150px" style="text-align: right;">
							报警邮箱：
						</td>
						<td>
						<div class="l-text" style="float:left;"> 
							<input type="text" class="l-text-field" id="reportEmail"  name="sysManager.reportEmail" 
								value="<s:property value="sysManager.reportEmail"/>"></input>
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
