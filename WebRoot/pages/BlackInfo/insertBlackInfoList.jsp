﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>黑名单查询</title> 
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" 
			rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" 
			rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>"
			 rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" 
			type="text/javascript"></script>
			<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js' />"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
	/* 	if(${requestScope.black=="0"}){
			alert("子商户号输入不正确！");
		}if(${requestScope.black=="1"}){
			alert("添加成功！");
		}if(${requestScope.black=="2"}){
			alert("添加失败！");
		}if(${requestScope.black=="3"}){
			alert("TSN号输入不正确！");
		}  */
         function f_search()
        {
      		/* var subMerId=$("#subMerId").val();
	    	var number = /^[0-9]{1,20}$/;	//数字	
			if(subMerId==""||!number.test(subMerId)) {
				alert("子商户号不能为空且只能是数字！");
				$("#merSysId").focus();
				$("#merSysId").val(""); 
				return false;
			}var terminalId=$("#terminalId").val();
			if(terminalId==""){
				$("#terminalId").focus();
				$("#terminalId").val(""); 
				return false;
			}var phone=$("#phone").val();
			if(phone==""||!number.test(phone)||phone.length!=11){
				$("#phone").focus();
				$("#phone").val(""); 
				alert("手机号不能为空！且长度为11位,只能是数字");
				return false;
			}
	    	var idNum=$("#idNum").val();
			if(idNum==""||(idNum.length!=15&&idNum.length!=18)){
				$("#idNum").focus();
				$("#idNum").val(""); 
				alert("证件号不能为空！且长度为15-18位");
				return false;
			}var realName=$("#realName").val();
			if(realName==""){
				$("#realName").focus();
				$("#realName").val(""); 
				alert("姓名不能为空！");
				return false;
			}var status=$("#status").val();
			if(status==-1){
				alert("请选择状态！");
				$("#status").focus();
				$("#status").val("-1");
				return false;
			}
			var cardNo=$("#cardNo").val();
			if(cardNo==""||!number.test(cardNo)){
				$("#cardNo").focus();
				$("#cardNo").val(""); 
				alert("卡号不能为空且只能是数字！");
				return false;
			}
			else{
			  $("#form1").submit();
			} */
        	 $("#form1").submit();
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">	
			<form id="form1"action='<s:url value="/blackInfo!insertBlackInfoList.ac"/>' method="post" >
				<table width="100%" style="margin: 30px">
					<tr style="height: 50px">
						<td style="text-align: right;"> 
							子商户号： 
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<input type="text" name="blackInfo.subMerId" id="subMerId" style="width: 200px;" class="inputext_style l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							姓名：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<input type="text" name="blackInfo.realName" id="realName" style="width: 200px;" class="inputext_style l-text-field"/>
							</div>
						</td>
					</tr>
					<tr style="height: 50px">
						<td style="text-align: right;">
							TSN号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<input type="text" name="blackInfo.terminalId" id="terminalId" style="width: 200px;" class="inputext_style l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							证件号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<input type="text" name="blackInfo.idNum" id="idNum" style="width: 200px;" class="inputext_style l-text-field"/>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							卡号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<input name="blackInfo.cardNo" id="cardNo" style="width: 200px" class="inputext_style l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							手机号：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<input type="text" name="blackInfo.phone" id="phone" style="width: 200px;" class="inputext_style l-text-field"/>
							</div>
						</td>
					</tr>
					<tr style="height: 50px">
						<td style="text-align: right;">
							卡类型：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
							<select name="blackInfo.cardType" id="cardType" style="width: 200px" class="inputext_style l-text-field">
								<option value="1">借记卡</option>
								<option value="2">信用卡</option>
							</select>
							</div>
						</td>
						<td style="text-align: right;">
							状态：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px">
							<select name="blackInfo.status" id="status" style="width: 200px;border: 0px">
								<option value="1">标记</option>
							</select>
							</div>
						</td>
					</tr>
					<tr style="height: 50px">
						<td style="text-align: right;">
							黑名单类型：
						</td>
						<td>
							<div class="l-text" style="width: 200px;">
							<select name="blackInfo.blackType" id="blackType" style="width:200px;border: 0px">
							<option value="1">类型</option>
							</select>
							</div>
						</td>
						<td style="text-align: right;">
							黑名单级别：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px">
							<select name="blackInfo.level" id="level" style="width: 200px;border: 0px">
								<option value="7">级别</option>
							</select>
							</div>
						</td>
					</tr>
					<tr style="height: 50px">
						<td style="text-align: center;" colspan="2">
							<input id="btn" type="button" 
							value="添加" onclick="f_search()" class="l-button"/>
						</td>
					    <td style="text-align: center;" colspan="2">
					    	<input id="btn2" type="button" 
					   		value="重 置" onclick="f_clean()" class="l-button"/>
					   	</td>
					</tr>
				</table>
			</form>
		</div>
		</div>
	</body>
</html>
