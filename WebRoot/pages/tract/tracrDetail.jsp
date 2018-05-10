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
			function update(tractId){
				var status = $("#status").val();
				if(status!=""&&status=="1"){
					alert("已经启用！");
					return ;
				}else{
					$.post("<s:url value='tractInfo!approveTractInfo.ac'/>",{tractId:tractId},
					function(data){
						if(data=='succ'){
							$("#tractStatus").html("<font color='green'>已启用</font>");
								alert("启用成功！");
								 parent.window.f_search();
								//window.parent.location.reload();
								//window.opener.f_search();
						}else if(data=='fone'){
							$("#tractStatus").html("<font color='red'>未启用</font>");
						}
						//window.parent.location.reload();
						parent.window.f_search();
					},"text");
				}		
			}
		</script>
		
		<script type="text/javascript">
			function updateOFF(tractId){
				var status = $("#status").val();
				if(status!=""&&status=="2"){
					alert("已经暂停！");
					//window.parent.location.reload();
					return ;
				}else{
					$.post("<s:url value='tractInfo!approveTractInfoOFF.ac'/>",
					{tractId:tractId},
					function(data){
						if(data=='succOFF'){
							$("#tractStatus").html("<font color='red'>已暂停</font>");
								alert("已暂停！");
								parent.window.f_search();
						}else if(data=='foneOFF'){
							$("#tractStatus").html("<font color='red'>未启用</font>");	
						}
						//window.parent.location.reload();
						parent.window.f_search();
					},"text");
				}		
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
							<table width="98%" border="0" cellpadding="0" cellspacing="0">
							<tr>
							<td width="120px" style="text-align: right;">
								通道名：
							</td>
							<td style="text-align: left;" width="120px">
								<s:property value="tractInfo.tractName"/>
							</td>
							
							<td  width="120px" style="text-align: right; ">
								通道号：
							</td>
							<td id="tractId" style="text-align: left;"  width="120px">
								<s:property value="tractInfo.tractId"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								支付商户号：
							</td>
							<td>
								<s:property value="tractInfo.transMerId"/>
							</td>
							<td width="150px" style="text-align: right;">
								收单机构：
							</td>
							<td>
								<s:property value="tractInfo.acqAgencyName"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								支付通道：
							</td>
							<td>
								<s:property value="tractInfo.payTract"/>
							</td>
							<td style="text-align: right;">
								通道名称：
							</td>
							<td >
								<s:property value="tractInfo.payTract"/>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								通道状态：
							</td>
							<td id="tractStatus">
								<s:if test="tractInfo.status==0">
								<span style="color:red">未启用</span></s:if>
								<s:if test="tractInfo.status==1">
								<span style="color:green">已启用</span></s:if> 
								<s:if test="tractInfo.status==2">
								<span style="color:red">暂停</span></s:if>
								<s:if test="tractInfo.status==3">暂停服务</s:if>
								<s:if test="tractInfo.status==4">停止服务</s:if>
								<s:if test="tractInfo.status==5">黑名单</s:if>	
								<input type="hidden" id="status" value="<s:property value="tractInfo.status"/>" />	
							</td>
								<td width="150px" style="text-align: right;">
								是否支持积分：
							</td>
							<td >
								<s:if test="tractInfo.integral==0"> 否</s:if>
								<s:if test="tractInfo.integral==1"> 是</s:if> 
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								收单机构代码：
							</td>
							<td style="text-align: left;">
								<s:property value="tractInfo.acqAgencyId"/>
								<s:if test="tractInfo.acqAgencyId==''">未填写</s:if>
							</td>
							<td width="120px" style="text-align: right;">
								创建日期：
							</td>
							<td>
								<s:property value="tractInfo.createTime"/>
								<s:if test="tractInfo.createTime==null">未填写</s:if>
							</td>	
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								行业类别：
							</td>
							<td>
								<s:property value="tractInfo.mcc"/>
								<s:if test="tractInfo.mcc==null">未填写</s:if>
							</td>
							<td width="120px" style="text-align: right;">
								通道标识：
							</td>
							<td>
								<s:if test="tractInfo.tractFlag==1">POS商户通道</s:if>
								<s:if test="tractInfo.tractFlag==0">普通商户通道</s:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								终端号：
							</td>
							<td style="text-align: left;">
								<s:property value="tractInfo.terminalId"/>
								<s:if test="tractInfo.terminalId==null">未填写</s:if>
							</td>
							<td width="120px" style="text-align: right;">
								SPID：
							</td>
							<td>
								<s:if test="tractInfo.spId=='0000'">0000</s:if>
								<s:if test="tractInfo.spId=='0013'">上海</s:if> 
								<s:if test="tractInfo.spId=='0359'">四川</s:if>
								<s:if test="tractInfo.spId=='0358'">安徽</s:if>
								<s:if test="tractInfo.spId=='0297'">深圳</s:if>
								<s:if test="tractInfo.spId=='0331'">北京</s:if>
								<s:if test="tractInfo.spId=='0365'">河北</s:if>
								<s:if test="tractInfo.spId==null">未填写</s:if>
							</td>	
						</tr>
						<tr>
							<td style="text-align: right;">
								支持卡类型：
							</td>
							<td style="text-align: left;">
								<s:if test="tractInfo.cardFlag == 1">
									借记卡
								</s:if>
								<s:elseif test="tractInfo.cardFlag == 2">
									贷记卡
								</s:elseif>
								<s:else>
									全部
								</s:else>
								<s:if test="tractInfo.cardFlag==null">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								通道类型：
							</td>
							<td width="150px" style="text-align: right;">
								<select name="tractInfo.tractType" style="float:left;width:135px ">
									<option value="1" <s:if test="tractInfo.tractType == 1">selected</s:if>>轮循通道</option>
									<option value="2" <s:if test="tractInfo.tractType == 2">selected</s:if>>点对点通道</option>
								</select>
							</td>
						</tr>
							<tr>
							<td style="text-align:center" colspan="6">
								------------------------------费率信息-----------------------------
							</td>
						</tr>
							
						<tr>
					<td width="150px" style="text-align: right;">
								手续费类型：
							</td>
							<td >
								<s:if test="tractInfo.ratesType==01"> 扣率型</s:if>
								<s:if test="tractInfo.ratesType==02"> 封顶型</s:if> 
								<s:if test="tractInfo.ratesType==05"> 积分型</s:if> 
								<s:if test="tractInfo.ratesType==10"> 代付型</s:if> 
								<s:if test="tractInfo.ratesType==11"> 会员消费</s:if> 
								<s:if test="tractInfo.ratesType==12"> 新封顶</s:if> 
								<s:if test="tractInfo.ratesType==15"> 急速到账</s:if> 
								<s:if test="tractInfo.ratesType==17"> 通道直付</s:if> 
								<s:if test="tractInfo.ratesType==31"> 香港(境外)</s:if> 
								<s:if test="tractInfo.ratesType==32"> 英国(境外)</s:if> 
								<s:if test="tractInfo.ratesType==33"> 美国(境外)</s:if> 
								<s:if test="tractInfo.ratesType==34"> 法国(境外)</s:if> 
								<s:if test="tractInfo.ratesType==35"> 日本(境外)</s:if> 
								<s:if test="tractInfo.ratesType==36"> 新西兰(境外)</s:if> 
								<s:if test="tractInfo.ratesType==37"> 马来西亚(境外)</s:if> 
								<s:if test="tractInfo.ratesType==38"> 泰国(境外)</s:if> 
								<s:if test="tractInfo.ratesType==''">未填写</s:if>
							</td>
							<td width="120px" style="text-align: right;">
								通道费率：
							</td>
							<td>
								<s:if test="tractInfo.tractRate==''">未填写</s:if>
								<s:else><s:property value="tractInfo.tractRate"/> %</s:else>
							</td>
						</tr>
						<tr>
							
							<td width="120px" style="text-align: right;">
								借记卡成本：
							</td>
							<td>
								<s:if test="tractInfo.debitRateCost==''">未填写</s:if>
								<s:else><s:property value="tractInfo.debitRateCost"/> %</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								信用卡成本：
							</td>
							<td style="text-align: left;">
								
								<s:if test="tractInfo.creditRateCost==''">未填写</s:if>
								<s:else><s:property value="tractInfo.creditRateCost"/> %</s:else>
							</td>
						</tr>
					
						<tr>
							<td width="120px" style="text-align: right;">
								最小成本值：
							</td>
							<td>
								<s:if test="tractInfo.lowestCost==''">未填写</s:if>
								<s:else><s:property value="tractInfo.lowestCost/100"/> 元</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								平台分润：
							</td>
							<td>
								<s:if test="tractInfo.bypayProfit==''">未填写</s:if>
								<s:else><s:property value="tractInfo.bypayProfit/100"/> 元</s:else>
							</td>
						</tr>
						<tr>
						<td width="120px" style="text-align: right;">
								信用卡封顶成本：
							</td>
							<td>
								<s:if test="tractInfo.creditHighestCost==''">未填写</s:if>
								<s:else><s:property value="tractInfo.creditHighestCost/100"/> 元</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								借记卡封顶成本：
							</td>
							<td>
								<s:if test="tractInfo.debitHighestCost==''">未填写</s:if>
								<s:else><s:property value="tractInfo.debitHighestCost/100"/> 元</s:else>
							</td>
						</tr>
							<tr>
						<td width="120px" style="text-align: right;">
								最高封顶成本：
							</td>
							<td>
								<s:if test="tractInfo.tractHighestFee==''">未填写</s:if>
								<s:else><s:property value="tractInfo.tractHighestFee/100"/> 元</s:else>
							</td>
						</tr>
							<tr>
							<td style="text-align:center" colspan="4">
								------------------------------风控信息-----------------------------
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								单卡单笔限额：
							</td>
							<td>
								<s:if test="tractInfo.perCardQuota==''">未填写</s:if>
								<s:else><s:property value="tractInfo.perCardQuota/100"/> 元</s:else>
							</td>
							<td style="text-align: right;">
								单日单卡累计次数：
							</td>
							<td>
								<s:property value="tractInfo.cardCount"/>
								<s:if test="tractInfo.cardCount==null">未填写</s:if>
							</td>	
						</tr>
							<tr>
							<td width="120px" style="text-align: right;">
								通道限额：
							</td>
							<td>
							<s:if test="tractInfo.tractQuota==''">未填写</s:if>
								<s:else><s:property value="tractInfo.tractQuota/100"/> 元</s:else>
							</td>
								<td width="120px"" style="text-align: right;">
								单日单卡累计限额：
							</td>
							<td>
								
								<s:if test="tractInfo.cardQuota==''">未填写</s:if>
								<s:else><s:property value="tractInfo.cardQuota/100"/> 元</s:else>
							</td>
							
					</tr>
						<tr>
							<td colspan="2" align="center">
								<br />
								<s:if test="tractInfo.status==0 || tractInfo.status==2">
									<input type="button" value="确认启用"  id="btn" class="l-button" onclick="update('<s:property value="tractInfo.tractId"/>')"/>
								</s:if>
								<s:elseif test="tractInfo.status==1">
								
									<input type="button" value="暂 停"  id="btn" class="l-button" onclick="updateOFF('<s:property value="tractInfo.tractId"/>')"/>
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
