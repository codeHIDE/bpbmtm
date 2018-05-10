<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户管理平台</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>"></script>
		<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
			input{
				width: 220px;
			}
		</style>
		<script type="text/javascript">
		$(function(){
			$("#sysPass").blur(function(){
				var sysPass=$("#sysPass").val();
				if(sysPass!=""){
					var url="<s:url value='/sysManage!validatePass.ac'/>";
					$.post(url,{smPwd:sysPass},function(data){
	       				if(data=='success'){
		       				$("#sysSpan").html("密码正确");
	       				}else{
	       					$("#sysSpan").html("密码错误");
	       				}
	        		});
				}
			});
			
			$("#cmPass").mouseout(function(){
				var smPass=$("#smPass").val();
				var cmPass=$("#cmPass").val();
				if(smPass!=""&&cmPass!=""){
					if(smPass==cmPass){
						$("#cmSpan").html("输入密码一致");
					}else{
						$("#cmSpan").html("输入密码不一致");
					}
				}
			});
			
			$("#updateForm").submit(function(){
				var sysPass=$("#sysPass").val();
				var smPass=$("#smPass").val();
				var cmPass=$("#cmPass").val();
				
				if(sysPass==null || sysPass=="") {
	        		alert('管理员密码不能为空');
	        		$("#sysPass").focus();
	        		return false;
	        	}
				if(smPass==null || smPass=="") {
	        		alert('新密码不能为空');
	        		$("#smPass").focus();
	        		return false;
	        	}
	        	if(cmPass==null || cmPass=="") {
	        		alert('确认密码不能为空');
	        		$("#cmPass").focus();
	        		return false;
	        	}
	        	
				var sysSpan=$("#sysSpan").html();
				var cmSpan=$("#cmSpan").html();
				if(sysSpan=="密码错误" || cmSpan=="输入密码不一致"){
					return false;
				}else if(sysSpan=="密码正确" && cmSpan=="输入密码一致"){
					return true;
				}
			});
		});
		
			function dialogClose(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			} 
			
		</script>
	</head>
	<body>
		<div id="content">
			<div class="box_system">
				<form action="<s:url value='/sysManage!updatePass.ac'/>"
					id="updateForm" method="post">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								管理员密码：
							</td>
								<td style="text-align: left;" width="120px">
								<input type="password"  style="width: 120px;"  id="sysPass" maxlength="20" />
								<span id="sysSpan" style="color: red"></span>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								修改密码：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="password" style="width: 120px;"  name="smPass" id="smPass" />
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								确认密码：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="password" style="width: 120px;"  id="cmPass" />
								<span id="cmSpan"></span>
							</td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;">
								<input type="submit" value="确认修改" id="updateBtn" class="l-button" style="width:80px;" />
								<input type="button" value="关闭" class="l-button"  id="btn" style="width:80px;" onclick="dialogClose()"/>
							</td>
						</tr>
					</table>
					</form>
			</div>
		</div>
	</body>
</html>
