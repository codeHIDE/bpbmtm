<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>密码验证</title>
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
			function cols(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function check(){
				if(len("loginPwd",32,"EN","级别确认密码")&&len("loginName",32,"EN","授权人ID")){
					if(confirm("是否确认操作？")){
						$.post("<s:url value='/agencyInfo!selectSubMerInfoPass.ac'/>",
						{'sysManager.levelPwd':$("#loginPwd").val(),'sysManager.loginName':$("#loginName").val()},
						function(data){
							if(data=='succ'){
								alert("成功！");
								parent.$.ligerDialog.close();
								parent.$.ligerDialog.open({ url: '<s:url value="/agencyInfo!updatAgenctInfo.ac"/>?agenctInfo.agentId='+$("#subMerId").val(),
		            					 height:250,width:600, isResize: false,title:'结算信息修改'});
							}else{
								alert("失败！");
							}
						},"text");
					}
				}
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
			<div class="box_system" style="text-align: center;">
				<table width="100%">
					<tr>
						<td>授&nbsp;&nbsp;权&nbsp;人&nbsp;I&nbsp;D：</td>
						<td>
							<input name="sysManager.loginName" value="" type="text" id="loginName" style="width:150px"/>
							<input type="hidden" id="subMerId" value="<s:property value="agenctInfo.agentId" />" />
						</td>
					</tr>
					<tr>
						<td>级别确认密码：</td>
						<td>
						<input name="sysManager.levelPwd" value="" type="password" id="loginPwd" style="width:150px"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
							<input type="button" value="验证" onclick="check()" style="width:80px;height: 30px"/>
						</td>
					</tr>
				</table>
			</div>
		</div>

	</body>
</html>
