<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户管理平台添加机构商户</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascr" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/jqui/jquery-validation/jquery.validate.min.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js' />"></script>
		<!-- 选色器所需js和css
		<link rel="stylesheet" href="<s:url value='/css/colorpicker.css'/>" type="text/css" /> -->
       	<script type="text/javascript" src="<s:url value='/js/colorpicker/colorpicker.js'/>"></script>
		<!-- 选色器所需js和css -->
		<style type="text/css">
			tr{
				height:30px;
			}
			input{
				width: 220px;
			}
	        .l-table-edit {}
	        .l-table-edit-td{ padding:4px;}
	        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
	        .l-verify-tip{ left:230px; top:120px;}
		</style>
		<script type="text/javascript">
        $(function (){
          if(${requestScope.addMerchantInfo=='success'}){
					alert("调账成功！");
				}else if(${requestScope.addMerchantInfo=='fouse'}){
					alert("调账失败！");
				}
        });  
			function check(){
				if(	len('subMerId',60,'All','商户号') &&
					len('merAmt',20,'NS','调帐金额')
					){
        				$.post('<s:url value="/subMerInfo!addAccounts.ac"/>',
				 		 {
				  		 	subMerId:$("#subMerId").val(),merAmt:$("#merAmt").val()
		  				},
				  		function(data){
						  	alert(data);
	 			 		});
					}
					return false;
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				<form action="<s:url value="subMerInfo!addAccounts.ac"/>" id="addMerchant" method="post" name="form1" enctype="multipart/form-data" >
					<table width="80%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;"> 
								商户号： 
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input  class="l-text-field" style="width:200px" type="text" name="subMerId" type="text" id="subMerId" />
							</div>
							</td>
							<td style="text-align: right;">
								调帐金额：
							</td>
							<td>
							<div class="l-text" style="width: 200px;float:left">
								<input type="text" class="l-text-field" style="width:200px" name="merAmt" id="merAmt" />
							</div>
							<div style="float:left">元</div>
							</td>
						</tr>
						<tr>
							<td style="text-align:center" colspan="4">
								<input type="button" value="确认" id="btn" onclick="check()" style="width:80px;"/>
							</td>
						</tr>
					</table>
				</form>
				</div>
			</div>
	</body>
</html>
