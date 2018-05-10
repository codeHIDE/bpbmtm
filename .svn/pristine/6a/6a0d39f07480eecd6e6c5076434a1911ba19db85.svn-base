<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>通道修改</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/CustomersData.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/common.js'/>"></script>

		<script type="text/javascript">
     		 
			function update(){
					if(length('merAmt',10,'NS','变更金额')){
						$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value='accountManage!selectUpdateAccountManageDetail.ac'/>',
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="1"){
		                      	  alert("修改成功！");
		                      } else if(data=="2") {
		                      	  alert("授权人ID或密码错误！");
		                      } 
		                       window.parent.$.ligerDialog.close();
	            				window.parent.$(".l-dialog,.l-window-mask").remove();
		                   }
		                });
						}
			}
			
			function cls(){
	            window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove(); 
			}
			
		</script>
		<style type="text/css">
tr {
	height: 30px;
}
</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">

		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
			<form id="form1" method="post">
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td style="text-align: right;">
							变更前金额：
						</td>
						<td>
						<s:set var="amts" value="accountManage.changeAmt" />
						<s:property value="#amts/100"/>
						&nbsp;元 
						</td>
					</tr>
					<tr>
						<td width="120px" style="text-align: right;">
							清算日期：
						</td>
						<td>
						<s:property value="accountManage.settleDate"/>
						<input  type="hidden" name="accountManage.settleDate"
								value="<s:property value="accountManage.settleDate"/>"></input>	
						</td>					
					</tr>
					<tr>
						<td width="150px" style="text-align: right;">
								金额：
							</td>
							<td>
							<div class="l-text" style="float:left;"> 
								<s:set var="amt" value="accountManage.merAmt" />
								<input type="text" class="l-text-field" id="merAmt"  name="accountManage.merAmt" style="width:80px"
									value="${amt/100}"></input>
								</div><div style="width:10px;float:left;">&nbsp;元</div> 
							</td>
					</tr>
					<tr>
						<td width="150px" style="text-align: right;">
								变更原因：
							</td>
							<td>
							<div class="l-text" style="float:left;"> 
								<input type="text" class="l-text-field" id="changeReason"  name="accountManage.changeReason" style="width:80px"
									value="<s:property value="accountManage.changeReason"/>"></input>
								</div> 
							</td>
					</tr>
					<tr>
						<td>
								<input type="hidden" class="l-text-field" id="merAmts"  name="accountManage.merAmts" style="width:80px"
									value="<s:property value="accountManage.merAmt"/>"></input>
									<input type="hidden" class="l-text-field" id="merSysId"  name="accountManage.merSysId" style="width:80px"
									value="<s:property value="accountManage.merSysId"/>"></input>
							</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input type="button" value="确认修改" id="btn" class="l-button" onclick="update()" />
						</td>
						<td colspan="2" align="center">
							<input type="button" value="关 闭" id="btn2" class="l-button" onclick="cls()" />
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
	</body>
</html>
