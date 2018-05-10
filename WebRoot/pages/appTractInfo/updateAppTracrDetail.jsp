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
				var payTract = $("#payTract").val();
				if(payTract == 'CSTP'){
					var spid = $("#spid").val();
					if(spid == '0000'){
						alert("请选择SPID");
						return false;
					}
				}
					//alert("kkk");
					if(length('terminalId',10,'EN','交易终端号') &&
					length('transRateCost',10,'NS','扣率成本') &&
					length('transLowestCost',10,'NS','最低成本') &&
					length('transHighestCost',10,'NS','最高成本') &&
					length('bypayProfit',10,'NS','平台分润') &&
					length('perCardQuota',10,'NS','单卡单笔限额') &&
					length('cardQuota',10,'NS','单卡单日限额') &&
					length('cardCount',2,'N','单卡单日次数') 
					){
						//$("#form1").submit();
						if(confirm("确定修改？"))
							form1.submit();
						return true;
					}
					return false;
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
			<form id="form1" action="<s:url value='appTractInfo!updateAppTractInfo.ac'/>" method="post">
				<input type="hidden" id="" name="appTractId" value="<s:property value="appTractId"/>"/>
				<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
					<td  width="120px" style="text-align: right;">
							通道名称：
						</td>
						<td width="160px" >
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="" name="appTractInfo.appTractName" style="width:120px"
								value="<s:property value="appTractInfo.appTractName"/>"></input>
								</div>
						</td>
						
						<td style="text-align: right;">
							通道号：
						</td>
						<td id="appTractId">
						<s:property value="appTractInfo.appTractId"/>
						<input type="hidden" name="appTractInfo.appTractId"
								value="<s:property value="appTractInfo.appTractId"/>"></input>	
						</td>			
					</tr>
					<tr>
						<td width="120px" style="text-align: right;">
							支付商户号：
						</td>
						<td >
						<s:property value="appTractInfo.transMerId"/>
						<input  type="hidden" name="appTractInfo.transMerId"
								value="<s:property value="appTractInfo.transMerId"/>"></input>	
						</td>					
						<td width="120px" style="text-align: right;">
							支付通道：
						</td>
						<td>
						<s:property value="appTractInfo.payTract"/>
						<input  type="hidden" name="appTractInfo.payTract" id="payTract"
								value="<s:property value="appTractInfo.payTract"/>"></input>	
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							通道状态：
						</td>
						<td>
								<s:if test="appTractInfo.status==0">
								<span style="color:red">未启用</span></s:if>
								<s:if test="appTractInfo.status==1">
								<span style="color:green">已启用</span></s:if> 
								<s:if test="appTractInfo.status==2">
								<span style="color:red">暂停</span></s:if>
								<s:if test="appTractInfo.status==3">暂停服务</s:if>
								<s:if test="appTractInfo.status==4">停止服务</s:if>
								<s:if test="appTractInfo.status==5">黑名单</s:if>		
							<input  type="hidden" name="appTractInfo.status"
								value="<s:property value="appTractInfo.status"/>"></input>	
						</td>
						<td width="150px" style="text-align: right;">
								通道类型：
						</td>
							<td>
								<s:checkboxlist id="appTractInfo.tractType" theme="simple" name="appTractInfo.tractType" list="#{'01':'还款','02':'转账','03':'余额查询','04':'手机冲值'}" value="%{sysy}"></s:checkboxlist>
							</td>
					</tr>
					<tr>
							<td width="150px" style="text-align: right;">
								交易终端号：
							</td>
							<td style="text-align: left;" width="150px">
								<div class="l-text" style="float:left;">
									<input type="text" class="l-text-field" id="terminalId" size="10" name="appTractInfo.terminalId" 
									value="<s:property value="appTractInfo.terminalId"/>"/>
								</div>
							</td>
							<td style="text-align: right;">
								SPID：
							</td>
							<td id="appTractId">
									<select name="appTractInfo.spid" id="spid" style="width:135px">
										<option value="0000" <s:if test="appTractInfo.spid == '0000'">selected</s:if>>请选择</option>
										<option value="0013" <s:if test="appTractInfo.spid == '0013'">selected</s:if>>上海</option>
										<option value="0359" <s:if test="appTractInfo.spid == '0359'">selected</s:if>>四川</option>
										<option value="0358" <s:if test="appTractInfo.spid == '0358'">selected</s:if>>安徽</option>
										<option value="0297" <s:if test="appTractInfo.spid == '0297'">selected</s:if>>深圳</option>
									</select>
							</td>
						</tr>
					<tr>
						<td width="150px" style="text-align: right;">
							扣率成本：
						</td>
						<td>
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="transRateCost" size="6" name="appTractInfo.transRateCost" 
								value="<s:property value="appTractInfo.transRateCost"/>"></input>
								</div><div style="width:10px;float:left;">&nbsp;%</div>
						</td>
						<td width="150px" style="text-align: right;">
							最低成本：
						</td>
						<td>
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="transLowestCost" size="6" name="appTractInfo.transLowestCost" 
								value="<s:property value="appTractInfo.transLowestCost"/>"></input>
								</div><div style="width:10px;float:left;">&nbsp;元</div> 
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" width="120px">
							最高成本：
						</td>
						<td style="text-align: left;" width="120px">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="transHighestCost"  name="appTractInfo.transHighestCost" style="width:80px"
								value="<s:property value="appTractInfo.transHighestCost"/>"></input>
						</div><div style="width:10px;float:left;">&nbsp;元</div> 
						</td>
					<td width="150px" style="text-align: right;">
							平台分润：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="bypayProfit" name="appTractInfo.bypayProfit" style="width:80px"
								value="<s:property value="appTractInfo.bypayProfit"/>"></input> 
							</div><div style="width:10px;float:left;">&nbsp;%</div>
								
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" width="120px">
							单卡单笔限额：
						</td>
						<td style="text-align: left;" width="120px">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="perCardQuota"  name="appTractInfo.perCardQuota" style="width:80px"
								value="<s:property value="appTractInfo.perCardQuota"/>"></input>
						</div><div style="width:10px;float:left;">&nbsp;元</div> 
						</td>
					<td width="150px" style="text-align: right;">
							单卡单日限额：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="cardQuota" name="appTractInfo.cardQuota" style="width:80px"
								value="<s:property value="appTractInfo.cardQuota"/>"></input> 
							</div><div style="width:10px;float:left;">&nbsp;元</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" width="120px">
							单卡单日次数：
						</td>
						<td style="text-align: left;" width="120px">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" id="cardCount"  name="appTractInfo.cardCount" style="width:80px"
								value="<s:property value="appTractInfo.cardCount"/>"></input>
						</div>
						</td>
						<td style="text-align: right;">
							创建时间：
						</td>
						<td id="tractId">
						<s:property value="appTractInfo.createTime"/>
						<input  type="hidden" name="appTractInfo.createTime"
								value="<s:property value="appTractInfo.createTime"/>"></input>	
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							信息验证域：
						</td>
						<td>
							<s:checkboxlist id="reserved" theme="simple" name="appTractInfo.reserved" list="#{'1':'身份证','2':'姓名','3':'手机号'}" value="%{reservedList}"></s:checkboxlist>	
						</td>
					</tr>					
					<tr>
						<td colspan="2" align="center">
							<br />
							<input type="button" value="确认修改" id="btn" class="l-button" onclick="update()" />
						</td>
						<td colspan="2" align="center">
							<br />
							<input type="button" value="关 闭" id="btn2" class="l-button"
								onclick="cls()" />
						</td>
					</tr>
				</table>
			</form>
			</div>
		</div>
		<s:if test="#request.updateAppTractInfo == 'success'">
			<script>
				alert("修改成功!");
			</script>
		</s:if>
	</body>
</html>
