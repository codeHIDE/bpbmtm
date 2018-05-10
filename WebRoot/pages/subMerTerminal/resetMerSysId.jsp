<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script type="text/javascript">
			/* if(${requestScope.tsn=='no'}){
				alert("修改子商户的机构号时失败！");
			}else if(${requestScope.tsn=='ok'}){
				alert("重置成功！");
			}else if(${requestScope.tsn!=""}){
				alert("没有该TSN："+requestScope.tsn);
			} */
		</script>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>重置机构商</title>
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
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
			function submitTsn(){
				var merSysId=document.getElementById("merSysId");
				if(merSysId.value == "-1"){
					$.ligerDialog.warn("请选择机构商！");
					merSysId.focus();
					return false;
				}
				var tsn=document.getElementById("tsn");
				if(tsn.value == "" || tsn.value == null){
					$.ligerDialog.warn("请填写TSN！\n"+" 终端号按每行一条排列即可，无需任何符号切割");
					tsn.focus();
					return false;
				}
				$.ligerDialog.confirm('是否重置?', function (yes) { 
					if(yes){
						$.ligerDialog.waitting('正在保存中,请稍候...');
						$.ajax({
		                    type: "POST",
		                    dataType: "json",
		                    url: '<s:url value="/subMerTerminal!resetMerSysId.ac"/>',
		                    data: $('#form1').serialize(),
		                    success: function (data) {
		                    	var gridData ="";
		                    	strObj = eval(data.result);
		                    	$.each(strObj, function(i) {
		                    		gridData += "<tr align='center'><td> "+strObj[i].tsn+"</td><td>"+strObj[i].status+"</td><td>"+strObj[i].desc+"</td></tr>";
		    					});
		                    	var param = "<table align='center'><tr align='center'><td width=120>TSN</td><td width=120>状态</td><td width=120>描述</td></tr><tr><td colspan=3><hr/></td></tr>" + gridData +"</table>";
		                    	$.ligerDialog.open({
		                            height:400,
		                            width: 550,
		                            title : '重置结果',
		                            content: param,
		                            showMax: false,
		                            showToggle: true,
		                            showMin: false,
		                            isResize: true,
		                            slide: false
		                        });
		                      	$.ligerDialog.closeWaitting(); 
		                    },
		                    error: function(data) {
		                    	$.ligerDialog.closeWaitting(); 
		                        alert("error:"+data.responseText);
		                     }
		                });
					}
				});
			}
			function getAgencyLv1(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"merSysId":$("#merSysId").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL1").empty();
					$("#agentIdL1").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL1").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			function getAgencyLv2(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL1":$("#agentIdL1").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL2").empty();
					$("#agentIdL2").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL2").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			function getAgencyLv3(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL2":$("#agentIdL2").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL3").empty();
					$("#agentIdL3").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL3").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			function getAgencyLv4(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL3":$("#agentIdL3").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL4").empty();
					$("#agentIdL4").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL4").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			
			function getAgencyLv5(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL4":$("#agentIdL4").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL5").empty();
					$("#agentIdL5").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL5").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			
			function getAgencyLv6(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL5":$("#agentIdL5").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL6").empty();
					$("#agentIdL6").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL6").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			
			function getAgencyLv7(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL6":$("#agentIdL6").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL7").empty();
					$("#agentIdL7").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL7").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			
			function getAgencyLv8(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL7":$("#agentIdL7").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL8").empty();
					$("#agentIdL8").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL8").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			
			function getAgencyLv9(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL8":$("#agentIdL8").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL9").empty();
					$("#agentIdL9").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL9").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
			
			function getAgencyLv10(){
				$.post("<s:url value='/subMerTerminal!getAgency.ac'/>",{"agentIdL9":$("#agentIdL9").val()},
				function (data){
					strObj = eval(data.list);
					$("#agentIdL10").empty();
					$("#agentIdL10").append("<option value='-1'>--请选择--</option>");
					$.each(strObj, function(i) {
						$("#agentIdL10").append("<option value='"+strObj[i].agentId+"'>"+strObj[i].agentName+"</option>");
					});
				},"json");
			};
		</script>
		<style type="text/css">
			tr {
				height: 30px;
			}
		</style>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
		<h1 align="center">重置机构商</h1>
		<div class="box_system">
		<form id="form1">
		<table width="90%" style="margin: 50px">
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				机构商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.merSysId" id="merSysId" style="width: 225px;height: 30px;" onchange="getAgencyLv1()">
					<option value="-1">--请选择--</option>
					<s:iterator value="merInfoList" var="mer">
					<option value="<s:property value="#mer.merSysId"/>" >
						<s:property value="#mer.merName"/>（<s:property value="#mer.merSysId"/>）
					</option>
					</s:iterator>
				</select>
				<span>&nbsp;&nbsp;</span>
				<input type="button" value="重置机构商" class="l-button" onclick="submitTsn()"/>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				一级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL1" id="agentIdL1" style="width: 225px;height: 30px;" onchange="getAgencyLv2()">
					<option value="-1">--请选择--</option>

				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				二级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL2" id="agentIdL2" style="width: 225px;height: 30px;" onchange="getAgencyLv3()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				三级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL3" id="agentIdL3" style="width: 225px;height: 30px;" onchange="getAgencyLv4()">
					<option value="-1">--请选择--</option>

				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				四级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL4" id="agentIdL4" style="width: 225px;height: 30px;" onchange="getAgencyLv5()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				五级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL5" id="agentIdL5" style="width: 225px;height: 30px;" onchange="getAgencyLv6()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				六级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL6" id="agentIdL6" style="width: 225px;height: 30px;" onchange="getAgencyLv7()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				七级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL7" id="agentIdL7" style="width: 225px;height: 30px;" onchange="getAgencyLv8()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				八级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL8" id="agentIdL8" style="width: 225px;height: 30px;" onchange="getAgencyLv9()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				九级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL9" id="agentIdL9" style="width: 225px;height: 30px;" onchange="getAgencyLv10()">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td align="right" width="35%" style="font-size: 15px;">
				十级代理商：
			</td>
			<td align="left" width="58%">
				<select name="subMerTerminal.agentIdL10" id="agentIdL10" style="width: 225px;height: 30px;">
					<option value="-1">--请选择--</option>
					
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<br />
				<textarea id="tsn" name="subMerTerminal.tsn" rows="20" cols="60"></textarea>
				<br />
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<br />
				<b style="color: red;font-size: 15px;">
					注：终端号按每行一条排列即可，无需任何符号切割(最多2000条数据)
				</b>
			</td>
		</tr>
		</table>
		</form>
		</div>
		</div>
	</body>
</html>
