<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商户操作员添加</title>
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
	
	if(${requestScope.flag=='success'}){
			alert("机构操作员添加成功！");
			window.parent.$.ligerDialog.close();
            window.parent.$(".l-dialog,.l-window-mask").remove();
	}else if(${requestScope.flag=='fail'}){
			alert("机构操作员添加失败！");
			window.parent.$.ligerDialog.close();
            window.parent.$(".l-dialog,.l-window-mask").remove();
	}

	function initRight() {
		var getType = 'mer';
		$.get("<s:url value='/merchantInfo!getRightList.ac'/>", {
			type : getType
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
		len('email',30,'All','邮箱')
		){
			if (rightStr.length < 1) {
				alert("请选择权限");
			} else {
				document.getElementById("check").value = ',' + rightStr + ',';
				form1.submit();
				return true;
			}
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
<body style="padding: 4px; " onload="initRight();">
	<div id="content">
		<s:if test="message!=null">
			<span><s:property value="message" /> </span>
		</s:if>
		<div class="box_system">
			<form action="<s:url value="/merchantInfo!addManager.ac"/>"
				id="addMerchant" method="post" name="form1">
				<input type="hidden" value="" name="merManager.purview"
					id="check" />
					<input type="hidden" value="<s:property value="merManager.mid"/>" name="merManager.mid"
					id="check" />
					<input type="hidden" value="mer" name="type"/>
				<table  style="text-align: center;" width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="120px">登录名：</td>
						<td style="text-align: left;" width="120px"><input
							type="text" name="merManager.loginName" id="loginName"
							value="<s:property value="merManager.loginName"/>" /></td>
						<td width="120px">操作员姓名：</td>
						<td style="text-align: left;" width="120px"><input
							type="text" name="merManager.realName" id="realName"
							value="<s:property value="merManager.realName"/>" /></td>
					</tr>
					<tr>
						<td>登录密码：</td>
						<td><input type="password"
							value="<s:property value="merManager.loginPwd"/>"
							name="merManager.loginPwd" id="loginPwd" /></td>
						<td>确认密码：</td>
						<td><input type="password"
							value=""
							name="reloginPwd" id="reloginPwd" /></td>
						
					</tr>
					<tr>
						<td width="120px">手机号：</td>
						<td style="text-align: left;" width="120px"><input
							type="text" name="merManager.phone" id="phone"
							value="<s:property value="merManager.phone"/>" /></td>
						<td>邮箱：</td>
						<td><input type="text"
							value="<s:property value="merManager.email"/>"
							name="merManager.email" id="email" /></td>
					</tr>
					<tr>
						<td width="120px">添加权限：</td>
						<td style="text-align: left" colspan="3">
							<div style="float:left;position: relative; border:1px solid #ccc;overflow:auto;max-height: 340px;">
								<ul id="tree1"></ul>
							</div>
						</td>
					</tr>
				</table>
				<div style="width: 37%">
					<input type="button" value="确认添加" id="bt"
						class="l-button l-button-submit" onclick="addRight()" />
				</div>
			</form>
		</div>


	</div>
</body>
</html>