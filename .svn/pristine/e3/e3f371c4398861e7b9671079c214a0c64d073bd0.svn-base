<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>修改费率类型</title>
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
		<script src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script type="text/javascript">
			function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function onchangeType(type){ 
				var transHighestFee;
				if(type=="f"){
			  		transHighestFee=$("#rateTypeF").val();//将用户修改后的类型存入transHighestFee中
			  	}else{
			  		transHighestFee=$("#rateTypeK").val();//将用户修改后的类型存入transHighestFee中
			  	}
	             $.post("<s:url value='/subMerInfo!onchangeRateType.ac'/>",
	             {'subMerInfo.subMerRate.rateType':transHighestFee,
	             'subMerInfo.subMerRate.subMerId':$("#id").val(),
	             'subMerInfo.subMerRate.status':$("#status1").val()},
	             function(result){
	             strObj=eval(result);
				if(result!=null && result!=""){
					$.each(strObj, function(i) {
						var sta=strObj[i].rateType;
						if(sta=="03" || sta=="01"){//扣率
							document.getElementById("teransRate4").innerHTML=strObj[i].teransRate+"%";
							document.getElementById("agentL2Rate4").innerHTML=strObj[i].agentL2Rate+"%";
							document.getElementById("agentL1Rate4").innerHTML=strObj[i].agentL1Rate+"%";
							document.getElementById("agentL2ProfitRate4").innerHTML=strObj[i].agentL2ProfitRate+"%";
							document.getElementById("agentL1ProfitRate4").innerHTML=strObj[i].agentL1ProfitRate+"%";
							document.getElementById("rateTypeK").value=strObj[i].rateType;
						}else if(sta=="02" || sta=="04"){//封顶
							document.getElementById("agentL2HighestFee2").innerHTML=strObj[i].agentL2HigestFee+"分";
							document.getElementById("agentL1HighestFee2").innerHTML=strObj[i].agentL1HighestFee+"分";
							document.getElementById("agentL2ProfitRate2").innerHTML=strObj[i].agentL2ProfitRate+"%";
							document.getElementById("agentL1ProfitRate2").innerHTML=strObj[i].agentL1ProfitRate+"%";
							document.getElementById("agentL2Rate2").innerHTML=strObj[i].agentL2Rate+"%";
							document.getElementById("agentL1Rate2").innerHTML=strObj[i].agentL1Rate+"%";
							document.getElementById("transHighestFee2").innerHTML=strObj[i].transHighestFee+"分";
							document.getElementById("teransRate2").innerHTML=strObj[i].teransRate+"%";
							document.getElementById("rateTypeF").value=strObj[i].rateType;
						}
					});
				}else{
					alert("失败！");
				}
			},"text");
		}
			function upd(){
				var s="?subMerInfo.subMerRate.rateType="+$("#rateTypeF").val();
				var str=s+"&subMerRate.rateType="+$("#rateTypeK").val();
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
				if(yes){
					$.ajax({
	                   type: "POST",
	                   dataType: "html",
	                   url: "<s:url value='/subMerInfo!updateSubMerchantInfoRateType.ac'/>"+str+'&id='+$("#id").val(),
	                   data: $('#form1').serialize(),
	                   success: function (data) {
	                      if(data=="2"){
	                   	    $.ligerDialog.success('申请修改费率成功！');
	                      }else{
	                    	$.ligerDialog.error('申请修改费率失败！');
		                  }
	                   },
	                   error: function(data) {
                  		 $.ligerDialog.error("错误提示:"+data.responseText);
                         window.parent.$.ligerDialog.closeWaitting(); 
		               }
					});
					}
				});
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;text-align: center;font-size: 13px">
		<b>修改费率类型</b>
		<table style="text-align: center;width: 100%" >
			<tr style="height: 30px">
				<td>
					子商户号：
				</td>
				<td width="25%">
					<s:property value="subMerInfo.subMerId" />
					<input type="hidden" id="id" value="<s:property value="subMerInfo.subMerId" />"/>
				</td>
				<td width="25%">
					子商户名：
				</td>
				<td width="25%">
					<s:property value="subMerInfo.subMerName" />
				</td>
			</tr>
			<s:iterator value="subMerRateList" var="smr">
				<s:if test="#smr.status==1">
				<tr style="height: 30px">
				<s:if test="#smr.rateType==02||#smr.rateType==04">
					<td colspan="2">
						费率类型：封顶
					</td>
					<td colspan="2">
					<select id="rateTypeF" style="width: 100px" onchange="onchangeType('f')">
						<s:iterator value="subMerRateList" var="subMerRate">
							<s:if test="#subMerRate.rateType==02">
							<option <s:if test="#subMerRate.rateType==02&&#subMerRate.status==1">selected="selected"</s:if> value="<s:property value="#subMerRate.rateType"/>">封顶</option>
							</s:if>
							<s:if test="#subMerRate.rateType==04">
							<option <s:if test="#subMerRate.rateType==04&&#subMerRate.status==1">selected="selected"</s:if> value="<s:property value="#subMerRate.rateType"/>">T+0封顶</option>
							</s:if>
						</s:iterator>
					</select>
					</td>
				</s:if>
				<s:if test="#smr.rateType==01||#smr.rateType==03">	
					<td colspan="2">
						费率类型：扣率
					</td>
					<td colspan="2">
					<select id="rateTypeK" style="width: 100px" onchange="onchangeType('k')">
						<s:iterator value="subMerRateList" var="subMerRate">
							<s:if test="#subMerRate.rateType==01">
							<option <s:if test="#subMerRate.rateType==01&&#subMerRate.status==1">selected="selected"</s:if> value="<s:property value="#subMerRate.rateType"/>">扣率</option>
							</s:if>
							<s:if test="#subMerRate.rateType==03">
							<option <s:if test="#subMerRate.rateType==03&&#subMerRate.status==1">selected="selected"</s:if> value="<s:property value="#subMerRate.rateType"/>">T+0扣率</option>
							</s:if>
						</s:iterator>
					</select>
					</td>
				</s:if>
				</tr>
<s:if test="#smr.rateType==02||#smr.rateType==04"><!-- 封顶 -->
				<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						子商户费率：
					</td>
					<td>
						<s:if test="#smr.teransRate== ''||#smr.teransRate== null ||#smr.teransRate==0 ||#smr.teransRate==-1" >
						无
						</s:if>
						<s:else>
							<label id="teransRate2"><s:property value="#smr.teransRate"/>%</label>
						</s:else>
					</td>
					<td width="120px" <s:if test="#smr.rateType == 01 || #smr.rateType == 03">style="display: none;text-align: right;"</s:if><s:else>style="text-align: right;"</s:else> >
						子商户封顶值：
					</td>
					<td <s:if test="#smr.rateType == 01 || #smr.rateType == 03">style="display: none;"</s:if>>
						<s:if test="#smr.teransRate== ''||#smr.teransRate== null ||#smr.teransRate==0 ||#smr.agentL1ProfitRate==-1" >
							无
						</s:if>
						<s:else>
							<label id="transHighestFee2"><s:property value="#smr.transHighestFee"/>分</label>
						</s:else>
					</td>
				</tr>
				<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						一级代理手续费： 
					</td>
					<td id="Y2">
						<s:if test="#smr.agentL1Rate== ''||#smr.agentL1Rate== null ||#smr.agentL1Rate==0 ||#smr.agentL1Rate==-1" >
						无
						</s:if>
						<s:else>
							<label id="agentL1Rate2"><s:property value="#smr.agentL1Rate"/>%</label>
						</s:else>
					</td>
					<td width="120px" style="text-align: right;">
						二级代理手续费：
					</td>
					<td id="Y3">
						<s:if test="#smr.agentL2Rate== ''||#smr.agentL2Rate== null ||#smr.agentL2Rate==0 ||#smr.agentL2Rate==-1" >
						无
						</s:if>
						<s:else>
							<label id="agentL2Rate2"><s:property value="#smr.agentL2Rate"/>%</label>
						</s:else>
					</td>
					</tr>
					<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						一级代理分润比：
					</td>
					<td id="Y4">
					<s:if test="#smr.agentL1ProfitRate== ''||#smr.agentL1ProfitRate== null ||#smr.agentL1ProfitRate==0 ||#smr.agentL1ProfitRate==-1" >
						无
					</s:if>
					<s:else>
						<label id="agentL1ProfitRate2"><s:property value="#smr.agentL1ProfitRate"/>%</label>
					</s:else>
					</td>
					<td width="120px" style="text-align: right;">
						二级代理分润比：
					</td>
					<td id="Y5">
					<s:if test="#smr.agentL2ProfitRate== ''||#smr.agentL2ProfitRate== null ||#smr.agentL2ProfitRate==0 ||#smr.agentL2ProfitRate==-1" >
					无
					</s:if>
					<s:else>
						<label id="agentL2ProfitRate2"><s:property value="#smr.agentL2ProfitRate"/> %</label>
					</s:else>
					</td>
				</tr>
				<tr style="height: 30px" id="trOne" >
					<td width="120px" style="text-align: right;">
						一级代理封顶值：
					</td>
					<td id="Y6">
					<s:if test="#smr.agentL1HighestFee== ''||#smr.agentL1HighestFee== null ||#smr.agentL1HighestFee==0 ||#smr.agentL1HighestFee==-1" >
					无
					</s:if>
					<s:else>
							<label id="agentL1HighestFee2"><s:property value="#smr.agentL1HighestFee"/>分</label>
					</s:else>
					</td>
					<td width="120px" style="text-align: right;">
						二级代理封顶值：
					</td>
					<td id="Y7">
					<s:if test="#smr.agentL2HigestFee== ''||#smr.agentL2HigestFee== null ||#smr.agentL2HigestFee==0 ||#smr.agentL2HigestFee==-1" >
					无
					</s:if>
					<s:else>
						<label id="agentL2HighestFee2"><s:property value="#smr.agentL2HigestFee"/>分</label>
					</s:else>
					</td>
				</tr>
</s:if><s:if test="#smr.rateType==01||#smr.rateType==03"><!-- 扣率 -->
				<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						子商户费率：
					</td>
					<td id="Y0">
						<s:if test="#smr.teransRate== ''||#smr.teransRate== null ||#smr.teransRate==0 ||#smr.teransRate==-1" >
						无
						</s:if>
						<s:else>
							<label id="teransRate4"><s:property value="#smr.teransRate"/>%</label>
						</s:else>
					</td>
				</tr>
				<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						一级代理手续费： 
					</td>
					<td id="Y2">
						<s:if test="#smr.agentL1Rate== ''||#smr.agentL1Rate== null ||#smr.agentL1Rate==0 ||#smr.agentL1Rate==-1" >
						无
						</s:if>
						<s:else>
							<label id="agentL1Rate4"><s:property value="#smr.agentL1Rate"/>%</label>
						</s:else>
					</td>
					<td width="120px" style="text-align: right;">
						二级代理手续费：
					</td>
					<td id="Y3">
						<s:if test="#smr.agentL2Rate== ''||#smr.agentL2Rate== null ||#smr.agentL2Rate==0 ||#smr.agentL2Rate==-1" >
						无
						</s:if>
						<s:else>
							<label id="agentL2Rate4"><s:property value="#smr.agentL2Rate"/>%</label>
						</s:else>
					</td>
					</tr>
					<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						一级代理分润比：
					</td>
					<td id="Y4">
					<s:if test="#smr.agentL1ProfitRate== ''||#smr.agentL1ProfitRate== null ||#smr.agentL1ProfitRate==0 ||#smr.agentL1ProfitRate==-1" >
						无
					</s:if>
					<s:else>
						<label id="agentL1ProfitRate4"><s:property value="#smr.agentL1ProfitRate"/>%</label>
					</s:else>
					</td>
					<td width="120px" style="text-align: right;">
						二级代理分润比：
					</td>
					<td id="Y5">
					<s:if test="#smr.agentL2ProfitRate== ''||#smr.agentL2ProfitRate== null ||#smr.agentL2ProfitRate==0 ||#smr.agentL2ProfitRate==-1" >
					无
					</s:if>
					<s:else>
						<label id="agentL2ProfitRate4"><s:property value="#smr.agentL2ProfitRate"/> %</label>
					</s:else>
					</td>
				</tr>
</s:if>
			</s:if>
			</s:iterator>
			<tr style="height: 30px">
				<td colspan="2">
				<br />
					<input type="button" class="l-button" value="修改" onclick="upd()"/>
				</td>
				<td colspan="2">
				<br />
					<input type="button" class="l-button" value="关闭" onclick="cls()"/>
				</td>
			</tr>
		</table>
	</body>
</html>
