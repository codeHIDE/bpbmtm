<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>POS对账</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>" type="text/javascript"></script>
    <script type="text/javascript">
     $(function (){
      $("#settleDate").ligerDateEditor({ showTime: true,width:"130",format: "yyyyMMdd"});
     });
     function doSubmit(){
     	var settleDate=$("#settleDate").val();
		var xlsFile=$("#xlsFile").val();
		if(settleDate==''){
    		alert('请选择日期');
    		return false;
    	}
    	if(xlsFile==''){
    		alert('请选择上传文件');
    		return false;
    	}
    	var form = document.getElementById("upload");
    	form.submit();
     }
     
     function downLoad(){
     	var settleDate=$("#settleDate").val();
     	var chName=$("#chName").val();
		if(settleDate==''){
    		alert('请选择日期');
    		return false;
    	}
    	var form = document.getElementById("upload");
        window.location.href="<s:url value='/MerSettleStatictis!downloadPosFile.ac'/>?settleDate="+settleDate+"&chName="+chName;
     }
     
     
    </script>
    <style type="text/css">
    tr{
		height:30px;
		}
    </style>
  </head>
  
  <body style="text-align: center;">

 		<div style="text-align: center;">
 		<b style="font-size:20px;">清算文件</b><br/><br/>
 		</div>
 	<form id="upload" action="<s:url value="/MerSettleStatictis!checkPos.ac" />" method="post" enctype="multipart/form-data">
   	<table width="100%" style="text-align: center;" border="0">
   		<tr>
   			<td style="text-align: right;">清算日期:</td>
   			<td style="text-align: left;" align="right">
				<input type="text" name="settleDate" id="settleDate" required="required" />
			</td>
	   		<td style="text-align: right;">交易渠道:</td>
   			<td>
   			<div class="l-text" style="width: 150px;">
   				<select name="chName" id="chName"  style="width: 150px"  class="l-text-field" >
   						<option value="1">
   							商联储
   						</option>
   						<option value="2">
   							德颐
   						</option>
   				</select>
   			</div>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">文件类型:</td>
   			<td>
   			<div class="l-text" style="width: 150px;">
   				<select name="fileType" id="fileType"  style="width: 150px"  class="l-text-field" >
   						<option value="1">
   							xls
   						</option>
   						<option value="2">
   							txt
   						</option>
   				</select>
   			</div>
   			</td>
   			<td style="text-align: right;">清算文件:</td>
   			<td style="text-align: left;">
   			<input type="file" id="xlsFile" name="xlsFile" value="" style="width:165px" accept="application/msexcel" />
   			</td>
   		</tr>
   		<tr>
   			<td colspan="2" align="center">
				<br />
				<b style="color: red;font-size: 15px;">
					注：清算需要大量资源,各渠道需要单独使用
				</b>
			</td>
   		</tr>
   	</table>
   	<br />
   	<div>
		<input type="button" value="上传" class="l-button" onclick="doSubmit()" /> 
		<input type="button" value="下载" class="l-button" onclick="downLoad()" /> 
   	</div>
   	</form>
   	<script type="text/javascript"> 
var myAlert = document.getElementById("alert"); 
var reg = $("#xf"); 


function c() {
	myAlert.style.display = "none"; 
    mybg.style.display = "none"; 
}
</script>
  </body>
</html>
