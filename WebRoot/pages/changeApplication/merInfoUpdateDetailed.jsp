<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>通道详情</title>
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
		
			function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove(); 
			}

			
			function conmit(){
				//当点击确定时 返回 true 
				if(confirm("确定审核?"))
				$.ajax({
                    type: "POST",
                    dataType: "html",
                    url: 'merchantInfo!checkStatus.ac?merInfoUpdate.id='+$("#id").val(),
                    data:'',
                    success: function (data) {
                       if(data=="succ"){
                      	    window.parent.$.ligerDialog.closeWaitting(); 
                       		alert("审核通过");
                       		window.parent.location.reload();
                       }else if(data=="fone"){
                      		alert("审核失败");		
                    		dialogClose();
                       }
                    },
                    error: function(data) {
                    	$.ligerDialog.closeWaitting(); 
                        alert("error:"+data.responseText);
                     }
                });
				}

		</script>
		
		<style type="text/css">
			tr {
				height: 30px;
			}	
		</style>
		
	</head>
	<body style="padding: 4px; ">
	
	   <form  id="form1" action="changeApplication!checkStates.ac" method="post">
	        <input id="id" type="hidden" name="merInfoUpdate.id" value='<s:property value="merInfoUpdate.id"/>'></input>
	         
	   </form>
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
						<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								子商户号：
							</td>
							<td style="text-align: left;" width="120px">
								<s:property value="subMerRate.subMerId"/>
							</td>
							
							<td style="text-align: right;">
								子商户名称：
							</td>
							<td id="tractId">
								<s:property value="subMerRate.subMerName"/>
							</td>
						</tr>
						<s:iterator value="subMerRateList" var="subMerRate">
						<tr>
							<td style="text-align: right;">
								费率类型：
							</td>
							<td>
							    <s:if test="#subMerRate.rateType=='01'"> <span style="color:red">扣率型</span></s:if>
							    <s:if test="#subMerRate.rateType=='02'"> <span style="color:red">封顶型</span></s:if>
							    <s:if test="#subMerRate.rateType=='03'"> <span style="color:red">T+0扣率</span></s:if>
							    <s:if test="#subMerRate.rateType=='04'"> <span style="color:red">T+0封顶</span></s:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								一级代理手续费：
							</td>
							<td>
							    <s:if test="#subMerRate.agentL1Rate==-1 || #subMerRate.agentL1Rate==null || #subMerRate.agentL1Rate=='' ">无</s:if>
							    <s:else><s:property value="#subMerRate.agentL1Rate"/>%</s:else>
							</td>
							<td style="text-align: right;">
								一级代理分润比：
							</td>
							<td >
							    <s:if test="#subMerRate.agentL1ProfitRate==null || #subMerRate.agentL1ProfitRate==-1 || #subMerRate.agentL1ProfitRate==''">无</s:if>
							    <s:else><s:property value="#subMerRate.agentL1ProfitRate"/> %</s:else>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								一级代理封顶手续费：
							</td>
							<td style="text-align: left;">
							   <s:if test="#subMerRate.agentL1HighestFee==null || #subMerRate.agentL1HighestFee==-1">无</s:if>
							   <s:else><s:property value="#subMerRate.agentL1HighestFee"/>分</s:else>
							</td>
							<td width="150px" style="text-align: right;">
								二级代理手续费：
							</td>
							<td >
							    <s:if test="#subMerRate.agentL2Rate==null || #subMerRate.agentL2Rate==-1 || #subMerRate.agentL2Rate==''">无</s:if>
							    <s:else><s:property value="#subMerRate.agentL1Rate"/>分</s:else>
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
								二级代理分润比：
							</td>
							<td style="text-align: left;">
							    <s:if test="#subMerRate.agentL2ProfitRate==null || #subMerRate.agentL2ProfitRate==-1 || #subMerRate.agentL2ProfitRate==''">无</s:if>
							    <s:else><s:property value="#subMerRate.agentL2ProfitRate"/> %</s:else>
							</td>
							<td width="150px" style="text-align: right;">
								二级代理封顶手续费：
							</td>
							<td style="text-align: left;">
								<s:if test="#subMerRate.agentL2HighestFee==null ||#subMerRate.agentL2HighestFee=='' || #subMerRate.agentL2HighestFee==-1">无</s:if>
								<s:else><s:property value="#subMerRate.agentL2HighestFee"/>分</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								子商户手续费：
							</td>
							<td>
							    <s:if test="#subMerRate.teransRate==null || #subMerRate.teransRate==-1 || #subMerRate.teransRate==''">无</s:if>
							    <s:else><s:property value="#subMerRate.teransRate"/>分</s:else>
							</td>
							<td width="120px" style="text-align: right;">
								子商户封顶手续费：
							</td>
							<td>
							    <s:if test="#subMerRate.transHighestFee==null || #subMerRate.transHighestFee ==-1 || #subMerRate.transHighestFee==''">无</s:if>
							    <s:else><s:property value="#subMerRate.transHighestFee"/>分</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								创建时间：
							</td>
							<td>
								<s:property value="#subMerRate.createTime"/> 
							</td>
							<td width="150px" style="text-align: right;">
								使用状态：
							</td>
							<td>
								<s:if test="#subMerRate.status==0"> <span style="color:red">未使用</span></s:if>
								<s:if test="#subMerRate.status==1"> <span style="color:red">正在使用</span></s:if>
							</td>
						</tr>
						</s:iterator>
						
						<tr>
							<td width="120px" style="text-align: right;">
								   -----审核依据----
							</td>
							<td>
								
							</td>
							<td width="150px" style="text-align: right;">
								
							</td>
							<td>
								
							</td>
						</tr>
						
						
						<tr>
							<td width="120px" style="text-align: right;">
								<span style="color:red">交易时长</span>：
							</td>
							<td>
							    <s:if test="time==0 || time==null || time==''">无（新商户）</s:if>
							    <s:else><s:property value="time"/> 天</s:else>
							</td>
							<td width="150px" style="text-align: right;">
								<span style="color:red">交易总金额</span>：
							</td>
							<td>
							   <s:if test="sumAmt ==null || sumAmt=='' || sumAmt==0">无（新商户）</s:if>
							   <s:else><s:property value="sumAmt"/> 元</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								<span style="color:red">日平均交易额</span>：
							</td>
							<td>
							    <s:if test="avgAmt==null || avgAmt==0 || avgAmt==''">无（新商户）</s:if>
							    <s:else><s:property value="avgAmt"/> 元</s:else>
							</td>
							
							<s:if test="subMerRate2.lineCreditStatus!=null">
							<td width="150px" style="text-align: right;">
							     授信额度：
							</td>
							  
							  <s:if test="subMerRate2.lineCreditStatus==2"> 
							  <td>
								   <s:property value="subMerRate2.lineCredit"/>
							  </td>
							  </s:if>
							  <s:if test="subMerRate2.lineCreditStatus==1"> 
							  <td>
								   <input type="text" id="lineCreditNew" name="lineCreditNew" value="<s:property value='subMerRate.lineCredit'/> "/>
							  </td>
							  </s:if>
							  
								
							</s:if>
							
						</tr>
						
					     <tr>
							<td width="120px" style="text-align: right;">
								   -----变更项----
							</td>
							<td>
								
							</td>
							<td width="150px" style="text-align: right;">
								
							</td>
							<td>
								
							</td>
						</tr>
						<s:iterator value="subMerRateList2">
						<s:if test="merInfoUpdate.modifyType == '01'">
						<tr>
								<td style="text-align: right;">
									费率类型：
								</td>
								<td>
								    <s:if test="rateType=='01'"> <span style="color:red">扣率型</span></s:if>
								    <s:if test="rateType=='02'"> <span style="color:red">封顶型</span></s:if>
								    <s:if test="rateType=='03'"> <span style="color:red">T+0扣率</span></s:if>
								    <s:if test="rateType=='04'"> <span style="color:red">T+0封顶</span></s:if>
								</td>
							</tr>
						<s:if test="(agentL1Rate != null && agentL1Rate != -1 && agentL1Rate != '') || (agentL1ProfitRate !=null && agentL1ProfitRate !=-1 &&agentL1ProfitRate !='')">
							<tr>
							  <s:if test="agentL1Rate != null && agentL1Rate != -1 && agentL1Rate != ''">
								    <td width="120px" style="text-align: right;">
										<span style="color:red">一级代理手续费</span>：
									</td>
									<td>
									    <s:property value="agentL1Rate"/> 元
									</td>
							 </s:if>
						     <s:if test="agentL1ProfitRate !=null && agentL1ProfitRate !=-1 &&agentL1ProfitRate !=''">
								<td width="150px" style="text-align: right;">
								    <span style="color:red">一级代理分润比</span>：
								</td>
								<td>
									<s:property value="agentL1ProfitRate"/> %
								</td>
							 </s:if>
							</tr>
						</s:if>
						
						<s:if test="(agentL1HighestFee != null && agentL1HighestFee != -1 && agentL1HighestFee != '') || (agentL2Rate != null && agentL2Rate != -1 && agentL2Rate != '')">
						<tr>
						    <s:if test="agentL1HighestFee != null && agentL1HighestFee != -1 && agentL1HighestFee != ''">
							<td width="120px" style="text-align: right;">
								<span style="color:red">一级代理封顶手续费</span>：
							</td>
							<td>
							    <s:property value="agentL1HighestFee"/> 元
							</td>
						   </s:if>
						  	<s:if test="agentL2Rate != null && agentL2Rate != -1 && agentL2Rate != ''">
							<td width="150px" style="text-align: right;">
							    <span style="color:red">二级代理手续费</span>：
							</td>
							<td>
								<s:property value="agentL2Rate"/> 元
							</td>
							</s:if>
						</tr>
						</s:if>
						
						
						<s:if test="(agentL2ProfitRate != null && agentL2ProfitRate != -1 && agentL2ProfitRate != '') || (agentL2HigestFee != null && agentL2HigestFee != -1 && agentL2HigestFee != '')">
						<tr>
						   <s:if test="agentL2ProfitRate != null && agentL2ProfitRate != -1 && agentL2ProfitRate != ''">
							<td width="120px" style="text-align: right;">
								<span style="color:red">二级代理分润比</span>：
							</td>
							<td>
							    <s:property value="agentL2ProfitRate"/> %
							</td>
						   </s:if>
						   <s:if test="agentL2HigestFee != null && agentL2HigestFee != -1 && agentL2HigestFee != ''">
							<td width="150px" style="text-align: right;">
							    <span style="color:red">二级代理封顶手续费</span>：
							</td>
							<td>
								<s:property value="agentL2HigestFee"/> 元
							</td>
						   </s:if>
						</tr>
						</s:if>
						
						<s:if test="(teransRate != null && teransRate != -1 && teransRate != '')">
						<tr>
						   <s:if test="teransRate != null && teransRate != -1 && teransRate != ''">
							<td width="120px" style="text-align: right;">
								<span style="color:red">子商户手续费</span>：
							</td>
							<td>
							    <s:property value="teransRate"/> 元
							</td>
						    </s:if>
						    <s:if test="transHighestFee != null && transHighestFee != -1 && transHighestFee != ''">
						       <td width="150px" style="text-align: right;">
							    <span style="color:red">子商户封顶手续费</span>：
							</td>
							<td>
								<s:property value="transHighestFee"/> 元
							</td>
						    </s:if>
							
						</tr>
						</s:if>
						</s:if>
						<s:if test="merInfoUpdate.modifyType=='03'">
						<tr>
						  <s:if test="rateType != null && rateType != -1 && rateType != ''">
							<td width="120px" style="text-align: right;">
								<span style="color:red">费率类型</span>：
							</td>
							<td>
							   <s:if test="rateType==01">
							         扣率型
							   </s:if>
							   <s:elseif test="rateType==02">
							           封顶型
							   </s:elseif>
							   <s:elseif test="rateType==03">
							    T+0扣率
							   </s:elseif>
							   <s:elseif test="rateType==04">
							    T+0封顶
							   </s:elseif>
							   <s:else>
							        未知
							   </s:else>
							</td>
							</s:if>
						    <td width="150px" style="text-align: right;">
						                 
							</td>
							<td width="150px" style="text-align: right;">
							</td>
						</tr>
						</s:if>
						</s:iterator>
						
						
						<tr>
							<td colspan="5" align="center">
							   <s:if test="merInfoUpdate.status==0">
							         <input type="button" value="审核"  id="btn2" class="l-button" onclick="conmit()" />
							   </s:if>
							    <input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
