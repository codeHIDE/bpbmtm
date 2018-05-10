<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>替换机具</title>
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
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
    <script type="text/javascript">
    function cle(){
    	$("#oldTsn").val("");
    	$("#newTsn").val("");
    }
    function checkVal(){
    	var oldTsn = $("#oldTsn").val();
    	var newTsn = $("#newTsn").val();
    	if(oldTsn==''){
    		alert('请填写原终端');
    		return false;
    	}
    	if(newTsn==''){
    		alert('请填写替换终端');
    		return false;
    	}
    	return true;
    }
    
    function clone(){
    		if(checkVal()){
    		var formData=
				{
					oldTsn : $("#oldTsn").val(),
					newTsn :$("#newTsn").val()
				};
			var url = "<s:url value='/subMerTerminal!cloneTerminal.ac'/>";
   			$.post(url,formData,function(data){
   					alert(data);
    				cle();
    			},"text");
    		}
    	}
    </script>
    <style type="text/css">
    td{
    	padding: 10px;
    	width:135px;
    }
    </style>
    <style> 
* {margin:0;padding:0;font-size:12px;} 
html,body {height:100%;width:100%;} 
#content {background:#f8f8f8;padding:30px;height:100%;} 
#content a {font-size:30px;color:#369;font-weight:700;} 
#alert {border:1px solid #369;width:300px;height:150px;background:#e2ecf5;z-index:1000;position:absolute;display:none;} 
#alert h4 {height:20px;background:#369;color:#fff;padding:5px 0 0 5px;} 
#alert h4 span {float:left;} 
#alert h4 span#close {margin-left:210px;font-weight:500;cursor:pointer;} 
#alert p {padding:12px 0 0 30px;} 
#alert p input {width:120px;margin-left:20px;} 
#alert p input.myinp {border:1px solid #ccc;height:16px;} 
#alert p input.sub {width:60px;margin-left:30px;} 
</style>
  </head>
  
  <body style="text-align: center;">

 		<div style="text-align: center;">
 		<b style="font-size:20px;">替换机具</b><br/><br/>
 		</div>
   	<table width="100%" style="text-align: center;" border="0">
   		<tr>
   			<td style="text-align: right;">原终端TSN:</td>
   			<td style="text-align: left;">
   			<input type="text" id="oldTsn" value="" style="width:165px"/>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">替换终端TSN:</td>
   			<td style="text-align: left;">
   			<input type="text" id="newTsn" value="" style="width:165px"/>
   			</td>
   		</tr>
   	</table>
   	<br />
   	<div>
		<input type="button" value="替换终端" class="l-button" onclick="clone()"/> 
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; 
   		<input type="button" value="重置" class="l-button" onclick="cle()"/>
   	</div>
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
