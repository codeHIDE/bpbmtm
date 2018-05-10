<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>选择修改内容</title>
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
			function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function Information(){
				parent.$.ligerDialog.close();
				parent.$.ligerDialog.open({ url: '<s:url value="/subMerInfo!rateInformationOrType.ac"/>?subMerRate.subMerId='+$("#id").val(),
            					 height:500,width:850, isResize: false,title:'修改费率信息'});
			}
			function updateType(){
				parent.$.ligerDialog.close();
				parent.$.ligerDialog.open({ url: '<s:url value="/subMerInfo!rateInformationOrType.ac"/>?subMerRate.subMerId='+$("#id").val()+'&id=type',
								height:500,width:600, isResize: false,title:'修改费率类型'});
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;font-size: 13px;text-align: center;" >
		<input type="hidden" id="id" value="${param.id }" />
		<b>请选择修改内容</b>
		<table style="text-align: center;width: 100%">
			<tr>
				<td width="50%">
				<br />
					<input type="button" class="l-button" value="修改费率基本信息" onclick="Information()" style="width:120px"/>
				</td>
				<!-- <td width="50%">
				<br />
					<input type="button" class="l-button" value="修改费率类型" onclick="updateType()" style="width:120px"/>
				</td> -->
			</tr>
			<tr>
				<td colspan="2">
				<br />
					<input type="button" class="l-button" value="关闭" onclick="cls()"/>
				</td>
			</tr>
		</table>
	</body>
</html>
