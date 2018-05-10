<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>添加终端生成批次统计</title>
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
    	$("#createNum").val("");
    	$("#merSysId").val("");
    	$("#factoryId").val("");
    }
    function che(){
     var createNum = $("#createNum").val();
     var factoryId = $("#factoryId").val();
     if(factoryId=="19"){
     	if(createNum > 3000){
	     	alert("单批次生成数量不能大于3000");
	     	return false;
     	}
     }else{
     	if(createNum > 5000){
	     	alert("单批次生成数量不能大于5000");
	     	return false;
     	}
     }
    if(len("createNum",10,"N","生成数量"))
    	return true;
    else
		return false;
    }
    function sub(){
    	if(che()){
    		if(confirm("是否生成？")){
    		xf();
    			$.post("<s:url value='/BatchConfig!insertTerminalBatchConfig.ac'/>",
    			{'terminalBatchConfig.createNum':$("#createNum").val(),
    			'terminalBatchConfig.merSysId':$("#merSysId").val(),
    			'terminalBatchConfig.factoryId':$("#factoryId").val(),
    			'terminalBatchConfig.reserved1':$("#reserved1").val()},
    			function(data){
    				if(data=='succ'){
    					alert("添加成功！");
    				}else
    					alert("添加失败");
    				c();
    				cle();
    			},"text");
    		}
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
  <div id="alert">
<h4><span>提示</span></h4>
<p>
  	终端生成中，请稍后。。。。
</p>
</div>
 		<div style="text-align: center;">
 		<b style="font-size:20px;">添加终端生成批次统计</b><br/><br/>
 		</div>
   	<table width="100%" style="text-align: center;" border="0">
   		<tr>
   			<td style="text-align: right;">生成数量:</td>
   			<td style="text-align: left;">
   			<input type="text" id="createNum" value="" style="width:165px"/>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">终端类型:</td>
   			<td style="text-align: left;">
   				APP
   				<input type="hidden" id="reserved1" value="04"/>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">机构号:</td>
   			<td style="text-align: left;">
   			<select id="merSysId" style="width:170px">
   				<s:iterator value="merInfoList" var="mil">
   					<option value="<s:property value="#mil.merSysId"/>">
   						<s:property value="#mil.merName"/>(<s:property value="#mil.merSysId"/>)
   					</option>
   				</s:iterator>
   			</select>
   			</td>
   		</tr>
   		<tr>
   			<td style="text-align: right;">厂商编号:</td>
   			<td style="text-align: left;">
   			<select id="factoryId" style="width:170px">
   				<s:iterator value="factoryCodeList" var="fc">
   					<option value="<s:property value="#fc.factoryNo"/>">
   						<s:property value="#fc.factoryNo"/>||<s:property value="#fc.factoryName"/>
   					</option>
   				</s:iterator>
   			</select>
   			</td>
   		</tr>
   	</table>
   	<br />
   	<div>
		<input type="button" value="提交" class="l-button" onclick="sub()"/> 
		&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; 
   		<input type="button" value="重置" class="l-button" onclick="cle()"/>
   	</div>
   	<script type="text/javascript"> 
var myAlert = document.getElementById("alert"); 
var reg = $("#xf"); 

function xf() 
{ 
	myAlert.style.display = "block"; 
	myAlert.style.position = "absolute"; 
	myAlert.style.top = "50%"; 
	myAlert.style.left = "50%"; 
	myAlert.style.marginTop = "-75px"; 
	myAlert.style.marginLeft = "-150px";
	mybg = document.createElement("div"); 
	mybg.setAttribute("id","mybg"); 
	mybg.style.background = "#000"; 
	mybg.style.width = "100%"; 
	mybg.style.height = "100%"; 
	mybg.style.position = "absolute"; 
	mybg.style.top = "0"; 
	mybg.style.left = "0"; 
	mybg.style.zIndex = "500"; 
	mybg.style.opacity = "0.3"; 
	mybg.style.filter = "Alpha(opacity=30)"; 
	document.body.appendChild(mybg);
	document.body.style.overflow = "hidden"; 
}

function c() {
	myAlert.style.display = "none"; 
    mybg.style.display = "none"; 
}
</script>
  </body>
</html>
