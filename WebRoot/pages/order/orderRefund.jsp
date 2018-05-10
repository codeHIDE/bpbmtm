<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/common.js'/>"> </script>
		<style type="text/css">
			tr {
				height: 30px;
			}
		</style>
		<script type="text/javascript">
			function checkPwd() {
				var levelPwd=$("#levelPwd").val();
				var loginName=$("#loginName").val();
				if(loginName == "") {
					alert("授权人ID不能为空！");
					$("#loginName").focus();
					return false;
				}
				if(levelPwd == "") {
					alert("授权密码不能为空！");
					$("#levelPwd").focus();
					return false;
				}
				window.parent.$.ligerDialog.confirm('是否要退款?', function (yes) {
				if(yes){
					$("#btn").hide();
					$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/orderInfo!orderRefund.ac"/>',
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="1"){
		                       	alert("退款失败!");
		                      } else if(data=="2") {
		                      	alert("授权人ID或密码错误！");
		                      } else {
		                      	alert(data);
		                      }
		                     // window.parent.$.ligerDialog.closeWaitting();
			                 // dialogClose();
			                 $("#btn").show();
			                 window.parent.location.reload();
		                   }
		                });
					}
				});
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;margin-top: 13px;">
		<div id="searchbar">
			<form id="form1" method="post">
				<table width="100%">
					<tr>
						<td style="text-align: right;">
							订单号：
						</td>
						<td style="text-align: left;">
							${param.orderId}
							<input type="hidden"  name="orderInfo.orderId" id="orderId" value="${param.orderId}" class="l-text-field" readonly="readonly"/>
						</td>
					</tr>
					<tr style="text-align: right;">
						<td>
							授权人ID：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 110px;">
								<input type="text" name="sysManager.loginName" id="loginName"  class="l-text-field"/>
							</div>
						</td>
					</tr>
					<tr style="text-align: right;">
						<td>
							授权密码：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 110px;">
								<input type="password" name="sysManager.levelPwd" id="levelPwd"  class="l-text-field"/>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: center;" colspan="2">
								<input type="button" value="确定" id="btn" onclick="checkPwd()" class="l-button"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
