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
		function saveInfo(){
			window.parent.$.ligerDialog.confirm('是否要保存信息?', function (yes) {
				if(yes){
					//window.parent.$.ligerDialog.waitting('正在保存中,请稍候...'); 
					$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/wares!addWares.ac"/>',
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
								商品名称：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="text" name="waresInfo.waresName" id="waresName" />
								<span id="checkResult"></span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商户号：
							</td>
							<td>
								<input type="text" name="waresInfo.merchantId" id="merchantId" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商品金额：
							</td>
							<td>
								<input type="text" name="waresInfo.waresPrice" id="waresPrice" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								显示价格：
							</td>
							<td>
								<input type="text" name="waresInfo.waresShowPrice" id="waresShowPrice" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商品库存：
							</td>
							<td>
								<input type="text" name="waresInfo.waresStock" id="waresStock" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商品类型：
							</td>
							<td align="center">
							<select name="waresInfo.waresType" id="waresType"  class="l-text-field" style="width: 100px;">
								<option value="1" >
									系统商品
								</option>
							</select>
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
