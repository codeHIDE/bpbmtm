<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商户操作员权限修改</title>
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
			alert("机构操作员修改成功！");
			window.parent.$.ligerDialog.close();
	        window.parent.$(".l-dialog,.l-window-mask").remove();
	}else if(${requestScope.flag=='fail'}){
			alert("机构操作员修改失败！");
			window.parent.$.ligerDialog.close();
            window.parent.$(".l-dialog,.l-window-mask").remove();
	}

	function initRight() {
		var getType = "mer";
		 var check=$("#purview").val();
		var num=check.split(',');
		$.post("<s:url value='/merchantInfo!getRightList.ac'/>", {
			type : getType
		}, function(json) {
			strObj = eval(json);
			var data = [];
			$.each(strObj, function(i) {
				var isflag = false;
				for ( var j = 0; j < num.length; j++) {
					if(strObj[i].modelNo==num[j]){
						isflag=true;
					}
				}
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
		manager = $("#tree1").ligerGetTreeManager();
		var notes = manager.getChecked();//复选框
		var rightStr = "";
		for (var i = 0; i < notes.length; i++) {
			rightStr += notes[i].data.id;
			if (i < notes.length - 1) {
				rightStr += ",";
			}
		}
		if (rightStr.length < 1) {
			alert("请选择权限");
		} else {
			document.getElementById("purview").value = ',' + rightStr + ',';
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
<body style="padding: 4px; text-align:center" onload="initRight();">
	<div id="content">
		<s:if test="message!=null">
			<span>没有超级管理员，请添加</span>
		</s:if>
		<s:else>
		<div class="box_system">
			<form action="<s:url value="/merchantInfo!updateManager.ac"/>"
				id="addMerchant" method="post" name="form1">
				<input type="hidden" value="<s:property value="merManager.purview"/>" name="merManager.purview"
					id="purview" />
					<input type="hidden" value="<s:property value="merManager.mid"/>" name="merManager.mid"
					id="mid" />
					<input type="hidden" value="mer" name="type"/>
				<table width="" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="120px" style="text-align: right;">登录名：</td>
						<td style="text-align: left;" width="120px">
						<s:property value="merManager.loginName"/></td>
						<td width="120px" style="text-align: right;">操作员姓名：</td>
						<td style="text-align: left;" width="120px">
							<s:property value="merManager.realName"/></td>
					</tr>
					<tr>
						<td width="120px" style="text-align: right;">权限：</td>
						<td style="text-align: left" colspan="3">
							<div style="float:left;position: relative; border:1px solid #ccc;overflow:auto;max-height: 340px;">
								<ul id="tree1"></ul>
							</div>
						</td>
					</tr>
				</table>
				<div style="text-align:center;padding-top:20px;">
					<input type="button" value="确认修改" id="bt"
						class="l-button l-button-submit" onclick="addRight()" />
				</div>
			</form>
		</div>

</s:else>
	</div>
</body>
</html>