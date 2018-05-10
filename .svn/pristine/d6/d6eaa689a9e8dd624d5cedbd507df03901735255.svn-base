<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>代理商模块添加</title>
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
		                    url: '<s:url value="/agentModel!addAgencyModel.ac"/>',
		                    data: $('#addBusTypeForm').serialize(),
		                    success: function (data) {
		                       if(data=="1"){
		                      	    window.parent.$.ligerDialog.closeWaitting(); 
		                       		alert("信息保存成功!");
		                       		window.parent.location.reload();
			                       	dialogClose();
		                       }
		                    },
		                    error: function(data) {
		                    	$.ligerDialog.closeWaitting(); 
		                        alert("error:"+data.responseText);
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
			<form action="" id="addBusTypeForm" method="post"  >
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;">
								模块名称：
							</td>
							<td>
								<input type="text" name="agenctModel.modelName" id="modelName" value="<s:property value="agenctModel.modelName"/>" />
							</td>
							<td style="text-align: right;">
								模块编号：
							</td>
							<td>
								<input type="text" name="agenctModel.modelNo"id="modelNo" value="<s:property value="agenctModel.modelNo"/>" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								访问路径：
							</td>
							<td>
								<input type="text" name="agenctModel.path"id="path" value="<s:property value="agenctModel.path"/>"/>
							</td>
							<td style="text-align: right;">
								描述：
							</td>
							<td>
								<input type="text" name="agenctModel.desc"id="desc" value="<s:property value="agenctModel.desc"/>" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								备注：
							</td>
							<td>
								<input type="text" name="agenctModel.remark"id="remark" value="<s:property value="agenctModel.remark"/>"/>
							</td>
							<td style="text-align: right;">
								上层编号：
							</td>
							<td>
								<input type="text" name="agenctModel.superNo"id="superNo" value="<s:property value="agenctModel.superNo"/>" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								扩展：
							</td>
							<td>
								<input type="text" name="agenctModel.reserved"id="reserved" value="<s:property value="agenctModel.reserved"/>" />
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
