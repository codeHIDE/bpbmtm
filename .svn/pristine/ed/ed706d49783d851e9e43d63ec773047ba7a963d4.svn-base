<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加渠道</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
			input{
				width: 220px;
			}
		</style>
		<script type="text/javascript">
		var flag = false;
		function saveInfo(){
			window.parent.$.ligerDialog.confirm('是否要保存信息?', function (yes) {
				if(yes){
					//window.parent.$.ligerDialog.waitting('正在保存中,请稍候...'); 
					$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/channel!addChannel.ac"/>',
		                   data: $('#addUserTypeForm').serialize(),
		                   success: function (data) {
		                      	   window.parent.$.ligerDialog.closeWaitting(); 
		                       	   alert(data);
			                       dialogClose();
		                   }
		                });
					}
				});
		}
		
		
		function dialogClose(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<div class="box_system">
				<form action="" id="addUserTypeForm" method="post"  >
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								渠道名称：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="text" name="channelAddInfo.chName" id="chName" />
								<span id="checkResult"></span>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								渠道简称：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="text" name="channelAddInfo.chShortName" id="chShortName" />
								<span id="checkResult"></span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								IP地址：
							</td>
							<td>
								<input type="text" name="channelAddInfo.chHost" id="chHost" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								端口：
							</td>
							<td>
								<input type="text" name="channelAddInfo.chPort" id="chPort" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								TPDU：
							</td>
							<td>
								<input type="text" name="channelAddInfo.chTPDU" id="chTPDU" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								HEADER：
							</td>
							<td>
								<input type="text" name="channelAddInfo.chHeader" id="chHeader" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								机构号：
							</td>
							<td>
								<input type="text" name="channelAddInfo.chOrgNo" id="chOrgNo" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								渠道秘钥：
							</td>
							<td>
								<input type="text" name="channelAddInfo.chOrgKey" id="chOrgKey" />
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<input type="button" value="保存" class="l-button"  id="btn" style="width:80px;" onclick="saveInfo()"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
