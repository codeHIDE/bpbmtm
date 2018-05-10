<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
			/* if(${requestScope.tsn=='no'}){
				alert("修改子商户的机构号时失败！");
			}else if(${requestScope.tsn=='ok'}){
				alert("重置成功！");
			}else if(${requestScope.tsn!=""}){
				alert("没有该TSN："+requestScope.tsn);
			} */
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>t+1打款</title>
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
			function submitTsn(){
				var tsn=document.getElementById("subMerId");
				if(tsn.value == "" || tsn.value == null){
					$.ligerDialog.warn("请填写商户号！\n"+" 按每行一条排列即可，无需任何符号切割");
					tsn.focus();
					return false;
				}
				$.ligerDialog.confirm('是否确认?', function (yes) { 
					if(yes){
						var formData=
				{
					subMerId : $("#subMerId").val()
				};
				var url = "<s:url value='/orderInfo!payTojz.ac'/>";
   				$.post(url,formData,function(data){
   					alert(data);
    			},"text");
					}
				});
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
		<h1 align="center">T+1打款</h1>
		<div class="box_system">
		<form id="form1">
		<table width="90%" style="margin: 50px">
		<tr>
			<td colspan="2" align="center">
				<br />
				<textarea id="subMerId" name="subMerId" rows="20" cols="150"></textarea>
				<input type="button" value="矩阵打款" class="l-button" onclick="submitTsn()"/>
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<br />
				<b style="color: red;font-size: 15px;">
					注：每行一条排列即可，无需任何符号切割
				</b>
			</td>
		</tr>
		</table>
		</form>
		</div>
		</div>
	</body>
</html>
