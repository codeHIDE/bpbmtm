<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>配置机构商户密钥</title>
    	<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<s:url value='/js/jquery/jquery-ui-1.7.1.custom.min.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function setKeys(){
				$.post("<s:url value='/merchantInfo!setKeys.ac'/>",
				{merSysId:$("#merSysId").val(),pubKey:$("#publicKey").val(),priKey:$("#privateKey").val(),secuKey:$("#sensitiveKey").val()},
				function (data){
					if(data == 'succ'){
						alert("更新密钥成功!");
						location.reload();
					}else{
						alert("更新密钥失败!");
					}
				},"text");
			}
		</script>
  </head>
  <body style="padding: 15px; overflow: hidden;">
  <input type="hidden" id="merSysId" value="<s:property value='merchantInfo.merSysId'/>"/>
    <div id="content">
			<div class="box_system">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td>当前机构商户：<s:property value="merchantInfo.merShortName"/>
						(<s:property value="merchantInfo.merSysId"/>)</td>
					</tr>
					<tr valign="top">
						<td>
							加密公钥：<textarea id="publicKey" cols="60" rows="4"><s:property value="merTransInfo.pubKey"/></textarea>
						</td>
					</tr>
					<tr valign="top">
						<td>
							加密私钥：<textarea id="privateKey" cols="60" rows="4"><s:property value="merTransInfo.priKey"/></textarea>
						</td>
					</tr>
					<tr valign="top">
						<td>
							敏感密钥：<textarea id="sensitiveKey" cols="60"><s:property value="merTransInfo.secuKey"/></textarea>
						</td>
					</tr>
					<tr align="center">
						<td>
							<input type="button" value="更新" id="btn" style="width:80px" onclick="setKeys()"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
  </body>
</html>
