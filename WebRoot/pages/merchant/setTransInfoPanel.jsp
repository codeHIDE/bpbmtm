<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		 <link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
	    <script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerTab.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
        $(function ()
        {
            $("#navtab1").ligerTab(); 
        });
    </script>
	</head>
	<body style="padding: 10px">
		<div id="navtab1"
			style="width: 800px; overflow: hidden; border: 1px solid #A3C0E8;">
			<div tabid="home" title="设置通道及费率" lselected="true"
				style="height: 420px;">
				<iframe frameborder="0" name="showmessage"
					src="<s:url value="/merchantInfo!toSetTract.ac"/>?mid=<s:property value="merchantInfo.merSysId"/>"></iframe>
			</div>
			<div title="设置风控信息">
				<div id="Div1" style="height: 420px;">
					<iframe frameborder="0" name="showmessage"
						src="<s:url value="/merchantInfo!toSetControl.ac"/>?mid=<s:property value="merchantInfo.merSysId"/>"></iframe>
				</div>
			</div>
			<div title="设置应用通道及费率">
				<div id="Div2" style="height: 420px;">
					<iframe frameborder="0" name="showmessage"
						src="<s:url value="/merchantInfo!toSetAppTract.ac"/>?mid=<s:property value="merchantInfo.merSysId"/>"></iframe>
				</div>
			</div>
			<div title="机构交易配置">
				<div id="Div3" style="height: 420px;">
					<iframe frameborder="0" name="showmessage"
						src="<s:url value="/merchantInfo!toSetMerTract.ac"/>?mid=<s:property value="merchantInfo.merSysId"/>"></iframe>
				</div>
			</div>
		</div>
	</body>
</html>