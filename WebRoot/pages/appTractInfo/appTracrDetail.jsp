<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>通道启用</title>
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
			function update(appTractId){
				var status = $("#status").val();
				if(status!=""&&status=="1"){
					alert("已经启用！");
					return ;
				}else{
					$.post("<s:url value='appTractInfo!approveAppTractInfo.ac'/>",{appTractId:appTractId},
					function(data){
						if(data=='succ'){
							$("#tractStatus").html("<font color='green'>已启用</font>");
								alert("启用成功！");
								window.parent.location.reload();
						}else if(data=='fone'){
							$("#tractStatus").html("<font color='red'>未启用</font>");
						}
						window.parent.location.reload();
					},"text");
				}		
			}
		</script>
		
		<script type="text/javascript">
			function updateOFF(appTractId){
				var status = $("#status").val();
				if(status!=""&&status=="2"){
					alert("已经暂停！");
					window.parent.location.reload();
					return ;
				}else{
					$.post("<s:url value='appTractInfo!approveAppTractInfoOFF.ac'/>",
					{appTractId:appTractId},
					function(data){
						if(data=='succOFF'){
							$("#tractStatus").html("<font color='red'>已暂停</font>");
								alert("已暂停！");
						}else if(data=='foneOFF'){
							$("#tractStatus").html("<font color='red'>未启用</font>");	
						}
						window.parent.location.reload();
					},"text");
				}		
			}
			
			function cls(){
				parent.location.reload();   
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
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="150px" style="text-align: right;">
								通道名：
							</td>
							<td style="text-align: left;" width="150px">
								<s:property value="appTractInfo.appTractName"/>
							</td>
							
							<td style="text-align: right;">
								通道号：
							</td>
							<td id="appTractId">
								<s:property value="appTractInfo.appTractId"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								支付商户号：
							</td>
							<td>
								<s:property value="appTractInfo.transMerId"/>
							</td>
							<td style="text-align: right;">
								支付通道：
							</td>
							<td>
								<s:property value="appTractInfo.payTract"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								通道状态：
							</td>
							<td id="tractStatus">
								<s:if test="appTractInfo.status==0">
								<span style="color:red">未启用</span></s:if>
								<s:if test="appTractInfo.status==1">
								<span style="color:green">已启用</span></s:if> 
								<s:if test="appTractInfo.status==2">
								<span style="color:red">暂停</span></s:if>
								<s:if test="appTractInfo.status==3">暂停服务</s:if>
								<s:if test="appTractInfo.status==4">停止服务</s:if>
								<s:if test="appTractInfo.status==5">黑名单</s:if>	
								<input type="hidden" id="status" value="<s:property value="appTractInfo.status"/>" />	
							</td>
							<td width="120px" style="text-align: right;">
								创建日期：
							</td>
							<td>
								<s:property value="appTractInfo.createTime"/>
								<s:if test="appTractInfo.createTime==null">未填写</s:if>
							</td>	
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								交易终端号：
							</td>
							<td style="text-align: left;" width="150px">
								<s:property value="appTractInfo.terminalId"/>
							</td>
							<td style="text-align: right;">
								SPID：
							</td>
							<td id="appTractId">
								<s:property value="appTractInfo.spid"/>
							</td>
						</tr>
						<tr>
							
							<td width="120px" style="text-align: right;">
								扣率成本：
							</td>
							<td>
								<s:if test="appTractInfo.transRateCost==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.transRateCost"/> %</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								最低成本：
							</td>
							<td style="text-align: left;">
								<s:if test="appTractInfo.transLowestCost==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.transLowestCost"/> 元</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								最高成本：
							</td>
							<td>
								<s:if test="appTractInfo.transHighestCost==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.transHighestCost"/> 元</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								平台分润：
							</td>
							<td>
								<s:if test="appTractInfo.bypayProfit==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.bypayProfit"/> %</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								单卡单笔限额：
							</td>
							<td>
								<s:if test="appTractInfo.perCardQuota==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.perCardQuota"/> 元</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								单卡单日限额：
							</td>
							<td>
								<s:if test="appTractInfo.cardQuota==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.cardQuota"/> 元</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								单卡单日次数：
							</td>
							<td>
								<s:if test="appTractInfo.cardCount==''">未填写</s:if>
								<s:else><s:property value="appTractInfo.cardCount"/> </s:else>
							</td>
							<td  style="text-align: right;">
								通道类型：
							</td>
							<td>
								<s:property value="appTractInfo.tractType.replace('|', ',').replace('01', '还款')
								.replace('02', '转账').replace('03', '余额查询').replace('04', '手机充值')" /> 	
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<br />
								<s:if test="appTractInfo.status==0 || appTractInfo.status==2">
									<input type="button" value="确认启用"  id="btn" class="l-button" onclick="update('<s:property value="appTractInfo.appTractId"/>')"/>
								</s:if>
								<s:elseif test="appTractInfo.status==1">
								
									<input type="button" value="暂 停"  id="btn" class="l-button" onclick="updateOFF('<s:property value="appTractInfo.appTractId"/>')"/>
								</s:elseif>
							</td>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
