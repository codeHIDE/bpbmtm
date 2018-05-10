<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员添加</title>
<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
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
<script src="<s:url value='/js/jqui/jquery-1.3.2.min.js'/>" type="text/javascript"></script>
<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/jquery-1.3.2.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/jquery/jquery-ui-1.7.1.custom.min.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
<link href="<s:url value='/css/tree/ligerui-common.css'/>" rel="stylesheet" type="text/css" />
<link href="<s:url value='/css/tree/ligerui-tree.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<s:url value='/js/tree/ligerTree.js'/>" type="text/javascript"></script>
<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
<style type="">
tr {
	height: 30px;
}
</style>
<script type="text/javascript">
	
	function change(val){
	        	if(val=='1' || val=='2'){
	        		$(".td_show").css("visibility","visible");
	        	}else{
	        		$(".td_show").css("visibility","hidden");
	        	}
			}
	
	if(${requestScope.flag=='success'}){
			alert("添加管理员成功！");
	}else if(${requestScope.flag=='fail'}){
			alert("添加管理员失败！");
	}

	function initRight() {
		var getType = '';
		$.get("<s:url value='/sysManage!getRightList.ac'/>", {
			account : getType
		}, function(json) {
			strObj = eval(json);
			var data = [];
			$.each(strObj, function(i) {
				var isflag = false;
				data.push({
					id : strObj[i].modelNo,
					pid : strObj[i].superNo,
					text : strObj[i].modelName,
					ischecked : isflag
				});
			});

			$("#tree1").ligerTree({
				data : data,
				idFieldName : 'id',
				slide: false,
				parentIDFieldName : 'pid'//,
				//nodeWidth:150
			});
			manager = $("#tree1").ligerGetTreeManager();
			manager.collapseAll();
		}, "json");
	}

	function addRight() {
		var loginName = $("#loginName").val();
		var realName = $("#realName").val();
		var loginPwd = $("#loginPwd").val();
		var reloginPwd = $("#reloginPwd").val();
		var phone = $("#phone").val();
		var email = $("#email").val();
		var department = $("#department").val();
		var levelPwd = $("#levelPwd").val();
		var relevelPwd = $("#relevelPwd").val();
		var level = $("#level").val();
		
		manager = $("#tree1").ligerGetTreeManager();
		var notes = manager.getChecked();//复选框
		var rightStr = "";
		for (var i = 0; i < notes.length; i++) {
			rightStr += notes[i].data.id;
			if (i < notes.length - 1) {
				rightStr += ",";
			}
		}

		
		if(loginPwd!=reloginPwd){
			alert("密码输入不一致，请重新填写");
			return false;
		}
	
		if(	len('loginName',20,'EN','登录名') &&
		len('realName',30,'EandS','操作员姓名')	&&
		len('loginPwd',32,'EN','密码')     	&&
		len('phone',20,'N','联系电话') &&
		len('email',30,'All','邮箱') &&
		len('department',30,'EandS','所属部门') &&
		length('levelPwd',32,'N','授权密码')
		){
			if (rightStr.length < 1) {
				alert("请选择权限");
				return false;
			} 
			if(level=='-1'){
				alert("请选择通管理员级别！");
				return false;
			}
			if(level !='3'){
					if(levelPwd==null||levelPwd=='') {
						alert('授权密码不能为空！');
						return false;
					}
					 if(levelPwd!=relevelPwd){
					alert("确认授权密码与授权密码不一致，请重新填写");
					return false;
				}
			}
				document.getElementById("check").value = ',' + rightStr + ',';
				form1.submit();
				return true;
		}
		return false;
	}

</script>
<style type="text/css">
element.style {
	width: 400px;
}
</style>
</head>
<body style="padding: 4px;" onload="initRight();">
	<div id="content">
		<s:if test="message!=null">
			<span><s:property value="message" /> </span>
		</s:if>
		<div class="box_system">
			<form action="<s:url value="/sysManage!addManager.ac"/>" id="addMerchant" method="post" name="form1">
				<input type="hidden" value="" name="sysManager.purview"
					id="check" />
				<table width="60%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="120px" style="text-align: right;">登录名：</td>
						<td style="text-align: left;" width="120px"><input
							type="text" name="sysManager.loginName" id="loginName"
							value="<s:property value="sysManager.loginName"/>" /></td>
						<td width="120px" style="text-align: right;">操作员姓名：</td>
						<td style="text-align: left;" width="120px"><input
							type="text" name="sysManager.realName" id="realName"
							value="<s:property value="sysManager.realName"/>" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">登录密码：</td>
						<td>
							<input type="password" value="<s:property value="sysManager.loginPwd"/>" name="sysManager.loginPwd" id="loginPwd" />
						</td>
						<td style="text-align: right;">确认密码：</td>
						<td>
							<input type="password" value="" name="reloginPwd" id="reloginPwd" />
						</td>
					</tr>
					<tr>
						<td width="120px" style="text-align: right;">手机号：</td>
						<td style="text-align: left;" width="120px"><input
							type="text" name="sysManager.phone" id="phone"
							value="<s:property value="sysManager.phone"/>" /></td>
						<td style="text-align: right;">邮箱：</td>
						<td><input type="text"
							value="<s:property value="sysManager.email"/>"
							name="sysManager.email" id="email" /></td>
					</tr>
					<tr>
						<td style="text-align: right;">所属部门：</td>
						<td><input type="text"
							value="<s:property value="sysManager.department"/>"
							name="sysManager.department" id="department" /></td>
							<td style="text-align: right;">
								管理员级别：
							</td>
							<td>
								<select name="sysManager.level" id="level" onchange="change(this.value);">
									<option value="-1">
										---请选择---
									</option>
									<option value="1">
										1级
									</option>
									<option value="2">
										2级
									</option>
									<option value="3">
										3级
									</option>
								</select>
							</td>
							</tr>
						<tr>
						<td style="text-align: right;">报警邮箱：</td>
						<td>
							<input type="text" value="<s:property value="sysManager.reportEmail"/>" name="sysManager.reportEmail" id="reportEmail" />
						</td>
						<td style="text-align: right; visibility: hidden" class="td_show">
							授权密码：
						</td>
						<td  style="text-align: left; visibility: hidden" class="td_show">
						<input type="password" value="<s:property value="sysManager.levelPwd"/>"
							name="sysManager.levelPwd" id="levelPwd" /></td>
						<td style="text-align: right; visibility: hidden" class="td_show">
							确认授权密码：
						</td>
						<td   style="text-align: left; visibility: hidden" class="td_show">
							<input type="password" value="" name="relevelPwd" id="relevelPwd" />
						</td>
					</tr>
					<tr>
						<td width="120px" style="text-align: right;">添加权限：</td>
						<td style="text-align: left" colspan="2">
							<div style="float:left;position: relative; border:1px solid #ccc;overflow:auto;max-height: 340px;">
								<ul id="tree1"></ul>
							</div>
						</td>
						<td colspan="2" style="text-align: center">
							<input type="button" value="确认添加" id="bt" class="l-button l-button-submit" onclick="addRight()" />
						</td>
					</tr>
				</table>
				<div style="float: left;" >
					
				</div>
			</form>
		</div>


	</div>
</body>
</html>