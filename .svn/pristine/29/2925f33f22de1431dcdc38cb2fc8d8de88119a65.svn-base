<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加厂商风控</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
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
				if(check())
				window.parent.$.ligerDialog.confirm('是否要保存信息?', function (yes) {
					if(yes){
						//window.parent.$.ligerDialog.waitting('正在保存中,请稍候...'); 
						$.ajax({
		                    type: "POST",
		                    dataType: "html",
		                    url: '<s:url value="/factoryRisk!updateFactoryRisk.ac"/>',
		                    data: $('#addFactoryRiskForm').serialize(),
		                    success: function (data) {
		                       if(data=="succ"){
		                      	    window.parent.$.ligerDialog.closeWaitting(); 
		                       		alert("信息保存成功!");
		                       		window.parent.location.reload();
			                       	dialogClose();
		                       }else{
		                       		alert("信息保存失败!");
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
		function check(){
			if(	/* len('factoryNo',2,'N','厂商号') && */
				len('terminalCardQuota',10,'NS','单终端单日限额') &&
				len('perCardQuota',10,'NS','单卡单笔限额') &&
				len('lowsetMentionAmt',10,'NS','最低提现金额') &&
				len('highestMentionAmt',10,'NS','最大提现金')&&
				len('reserved1',10,'NS','单卡单月消费总金额')){
				return true;
			}
			return false;
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
			<form action="" id="addFactoryRiskForm" method="post"  >
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
									厂商号：
							</td>
							<td style="text-align: left;" width="120px">
								<input type="hidden" name="factoryRisk.id"
								value="<s:property value="factoryRisk.id"/>"/>
								<s:property value="factoryRisk.factoryNo"/>
								<span id="checkResult"></span>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								单终端单日限额：
							</td>
							<td>
								<fmt:parseNumber var="terminalCardQuota" value="${factoryRisk.terminalCardQuota/100}" type="number"/>
								<input type="text" name="factoryRisk.terminalCardQuota" id="terminalCardQuota" 
								value="${terminalCardQuota }"/> 元
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								单卡单笔限额：
							</td>
							<td>
							<fmt:parseNumber var="perCardQuota" value="${factoryRisk.perCardQuota/100}" type="number"/>
								<input type="text" name="factoryRisk.perCardQuota" id="perCardQuota" 
								value="${perCardQuota }"/> 元
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								最低提现金额：
							</td>
							<td>
								<fmt:parseNumber var="lowsetMentionAmt" value="${factoryRisk.lowsetMentionAmt/100}" type="number"/>
								<input type="text" name="factoryRisk.lowsetMentionAmt" id="lowsetMentionAmt" 
								value="${lowsetMentionAmt }"/> 元
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								最大提现金：
							</td>
							<td>
								<fmt:parseNumber var="highestMentionAmt" value="${factoryRisk.highestMentionAmt/100}" type="number"/>
								<input type="text" name="factoryRisk.highestMentionAmt" id="highestMentionAmt" 
								value="${highestMentionAmt }"/> 元
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								单卡单月消费总金额：
							</td>
							<td>
								<fmt:parseNumber var="reserved1" value="${factoryRisk.reserved1/100}" type="number"/>
								<input type="text" name="factoryRisk.reserved1" id="reserved1" 
								value="${reserved1 }"/> 元
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
