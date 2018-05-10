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
		var flag = false;
		$(function(){
			$("#levelPwd").blur(function(){
							var levelPwd=$("#levelPwd").val();
							if(levelPwd!=""){
								var url="<s:url value='/sysManage!selectLevelPass.ac'/>";
								$.post(url,{levelPwd:levelPwd},function(data){
				       				if(data=='success'){
					       				$("#sysSpan").html("密码正确");
				       				}else{
				       					$("#sysSpan").html("密码错误");
				       				}
				        		});
							}
					});
				});
		
		function saveInfo(){
			var levelPwd=$("#levelPwd").val();  
			var levelPwd1=$("#levelPwd1").val();  
			var levelPwd2=$("#levelPwd2").val();
			var pattern = /^[\S]*$/; //不包含空格
	    	if(levelPwd==null||levelPwd=='') {
				window.parent.$.ligerDialog.error('【原密码】不能为空');
				$("#levelPwd").focus();
				return;
			} 
			if(levelPwd1==null||levelPwd1=='') {
				window.parent.$.ligerDialog.error('【新密码】不能为空');
				$("#levelPwd1").focus();
				return;
			} 
			if(levelPwd1!=levelPwd2) {
				window.parent.$.ligerDialog.error('【2次密码输入不一致,请重新输入】');
				$("#levelPwd2").val("");
				return;
			} 
			
				window.parent.$.ligerDialog.confirm('是否要修改管理员密码?', function (yes) {
					if(yes){
						//window.parent.$.ligerDialog.waitting('密码修改中,请稍候...'); 
						$.ajax({
		                    type: "POST",
		                    dataType: "html",
		                    url: '<s:url value="/sysManage!updateLevelPassWord.ac"/>', 
		                    data: $('#updateLevelPassWord').serialize(),
		                    success: function (data) {
		                       if(data=="1"){
		                      	    window.parent.$.ligerDialog.closeWaitting(); 
		                       		alert("密码修改成功!");
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
				})
			
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
				<form action="" id="updateLevelPassWord" method="post"  onsubmit="return addC()">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								原授权密码：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="password" style="width: 120px;" name="levelPwd" id="levelPwd"  />
								<span  id="sysSpan" style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								新授权密码：
							</td>
							<td>
								<input type="password" style="width: 120px;" name="levelPwd1" id="levelPwd1" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								再次输入新授权密码：
							</td>
							<td>
								<input type="password" style="width: 120px;" name="levelPwd2" id="levelPwd2" />
								<span style="color: red">*</span>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<input type="button" value="修改" class="l-button"  id="btn" style="width:80px;" onclick="saveInfo()"/>
								<input type="button" value="关闭" class="l-button"  id="btn" style="width:80px;" onclick="dialogClose()"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>
</html>
