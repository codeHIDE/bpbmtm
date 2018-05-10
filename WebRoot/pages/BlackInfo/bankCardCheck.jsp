<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>银行卡鉴权</title>
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
    	$("#userName").val("");
    	$("#idNum").val("");
    	$("#cardNo").val("");
    	$("#cellNo").val("");
    }
    function checkVal(){
    	var userName = $("#userName").val();
    	var idNum = $("#idNum").val();
    	var cardNo = $("#cardNo").val();
    	var cellNo = $("#cellNo").val();
    	if(userName==''){
    		alert('请填写姓名');
    		return false;
    	}
    	if(idNum==''){
    		alert('请填写身份证');
    		return false;
    	}
    	if(cardNo==''){
    		alert('银行卡号');
    		return false;
    	}
    	if(cellNo==''){
    		alert('请填写手机号码');
    		return false;
    	}
    	return true;
    }
    
    function sub(){
    		if(checkVal()){
    		var formData=
				{
					userName : $("#userName").val(),
					idNum :$("#idNum").val(),
					cardNo :$("#cardNo").val(),
					cellNo :$("#cellNo").val()
				};
			var url = "<s:url value='/blackInfo!checkBankCard.ac'/>";
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
 		<b style="font-size:20px;">银行卡鉴权</b><br/><br/>
 		</div>
   	<table width="100%" style="text-align: center;" border="0">
   		<tr>
   			<td style="text-align: right;">姓名:</td>
   			<td style="text-align: left;">
   			<input type="text" id="userName" value="" style="width:165px"/>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">身份证号码:</td>
   			<td style="text-align: left;">
   			<input type="text" id="idNum" value="" style="width:165px"/>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">银行卡号:</td>
   			<td style="text-align: left;">
   			<input type="text" id="cardNo" value="" style="width:165px"/>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">银行预留手机号:</td>
   			<td style="text-align: left;">
   			<input type="text" id="cellNo" value="" style="width:165px"/>
   			</td>
   		</tr>
   	</table>
   	<br />
   	<div>
		<input type="button" value="鉴权" class="l-button" onclick="sub()"/> 
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
