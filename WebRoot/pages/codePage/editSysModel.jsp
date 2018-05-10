<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户管理平台</title>
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
		function editInfo(){
			var modelName = $("#modelName").val();  
			var modelNo = $("#modelNo").val();
			
			var pattern = /^[\S]*$/; //不包含空格
	    	var number = /^[0-9]{1,20}$/;	//数字
	    	if(modelNo == null|| modelNo == '') {
				window.parent.$.ligerDialog.error('【模块编号】不能为空');
				$("#modelNo").focus();
				return;
			} 
			if(modelName == null || modelName == '') {
				window.parent.$.ligerDialog.error('【模块名】不能为空');
				$("#cssName").focus();
				return;
			} 
			window.parent.$.ligerDialog.confirm('是否要修改信息?', function (yes) {
				if(yes){
					//window.parent.$.ligerDialog.waitting('正在保存中,请稍候...'); 
					$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/sysModel!editSysModel.ac"/>',
		                   data: $('#addSysModelForm').serialize(),
		                   success: function (data) {
		                      if(data=="1"){
		                      	   window.parent.$.ligerDialog.closeWaitting(); 
		                       	   alert("修改成功!");
		                       	   window.parent.location.reload();
			                       dialogClose();
		                      }else {
		                      	alert("修改失败！");
		                      }
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
				<s:form action="" id="addSysModelForm" method="post" theme="simple">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								模块名：
							</td>
							<td style="text-align: left;" width="120px">
								<s:hidden name="sysModel.id"></s:hidden>
								<s:textfield name="sysModel.modelName" id="modelName"></s:textfield>
								<span id="checkResult"></span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								模块编号：
							</td>
							<td>
								<s:textfield name="sysModel.modelNo" id="modelNo"></s:textfield>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								访问路径：
							</td>
							<td>
								<s:textfield name="sysModel.path" id="path"></s:textfield>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								描述：
							</td>
							<td>
								<s:textfield name="sysModel.desc" id="desc"></s:textfield>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								备注：
							</td>
							<td>
								<s:textfield name="sysModel.remark" id="remark"></s:textfield>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								上级编号：
							</td>
							<td>
								<s:textfield name="sysModel.superNo" id="superNo"></s:textfield>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<input type="button" value="修改" class="l-button"  id="btn" style="width:80px;" onclick="editInfo()"/>
							</td>
						</tr>
					</table>
				</s:form>
			</div>
		</div>
	</body>
</html>
