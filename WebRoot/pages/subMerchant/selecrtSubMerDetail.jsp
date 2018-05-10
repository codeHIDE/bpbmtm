<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>子商户商户详情</title>
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
		<script type="text/javascript">
			function cols(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function update(status,terminalType){
				var transHighestFee;
				if(confirm("是否确认操作？"))
				$.post("<s:url value='/subMerInfo!subMerDetail.ac'/>",
				{'subMerInfo.subMerId':$("#subMerId").val(),'subMerInfo.status':status,
				'subMerTrans.subMerTractR1':$("#subMerTractR1").val(),'subMerTrans.subMerTractR2':$("#subMerTractR2").val(),'subMerTrans.subMerTractR3':$("#subMerTractR3").val(),
				'subMerInfo.contactorPhone':$("#contactorPhone").val(),'subMerInfo.subMerName':$("#subMerName").val(), 
				'subMerInfo.merSysId':$("#merSysID").val() },
				function(data){
					if(data=='succ'){
						$("#merStatus").html("<font color='green'>正在使用</font>");
							alert("上线成功！");
					}else if(data=='fone'){
						$("#merStatus").html("<font color='red'>未审批</font>");
					}else if(data=='succSuspended'){
							$("#merStatus").html("<font color='red'>暂停服务</font>");
								alert("已暂停！");
					}else if(data=='fonesSuspended'){
							alert("暂停失败!");
					}else{
							alert("操作失败，请重新操作!");
					}
					if(${param.i}=="1"){
						parent.location.reload();
						window.parent.$.ligerDialog.close();
			            window.parent.$(".l-dialog,.l-window-mask").remove();
					}
					history.go(0);
				},"text");
			}
			function look(subMerId){
				if(confirm("是否确认操作？")){
					parent.$.ligerDialog.close();
					parent.$.ligerDialog.open({ url: '<s:url value="/pages/subMerchant/FailureReason.jsp"/>?id='+subMerId,
            					 height:200,width:400, isResize: false,title:'审核失败因素'});
				}
			}
		</script>
		<style type="text/css">
			tr {
				height: 30px;
			}
			td{
					width: 1000px;
			}
		</style>
	</head>
	<body style="padding: 4px;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								子商户入网号：
							</td>
							<td style="text-align: left;" width="120px">
								<s:property  value="subMerInfo.subMerId"/>
								<input type="hidden" name="subMerInfo.subMerId" id="subMerId" value=<s:property  value="subMerInfo.subMerId"/> />
								<s:if test="subMerInfo.subMerId==''">未填写</s:if>
								<s:if test="subMerInfo.subMerId==null">未填写</s:if>
							</td>
							<td style="text-align: right;">
								子商户入网名：
							</td>
							<td>
							<input type="hidden" name="subMerInfo.subMerName" id="subMerName" value=<s:property  value="subMerInfo.subMerName"/> />
								<s:property value="subMerInfo.subMerName"/>
								<s:if test="subMerInfo.subMerName==''">未填写</s:if>
								<s:if test="subMerInfo.subMerName==null">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
									商户状态：
								</td>
								<td id="subStatus">
									<s:if test="subMerInfo.status==0">
									<span style="color:red">未审批</span></s:if>
									<s:if test="subMerInfo.status==1">
									<span style="color:green">已审批</span></s:if> 
									<s:if test="subMerInfo.status==2">
									<span style="color:green">开通服务</span></s:if>
									<s:if test="subMerInfo.status==3">暂停服务</s:if>
									<s:if test="subMerInfo.status==4">停止服务</s:if>
									<s:if test="subMerInfo.status==5">黑名单</s:if>		
							</td>
							<td style="text-align: right;">
								所属商户号：
							</td>
							<td id="merSysId">
								<s:property value="subMerInfo.merSysId"/>
								<s:if test="subMerInfo.merSysId==''">未填写</s:if>
								<s:if test="subMerInfo.merSysId==null">未填写</s:if>
								<input type="hidden" id="merSysID" value="<s:property value="subMerInfo.merSysId"/>" />
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
								工商注册号码：
							</td>
							<td>
								<s:property value="subMerInfo.regNo"/>
								<s:if test="subMerInfo.regNo==''">未填写</s:if>
								<s:if test="subMerInfo.regNo==null">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td>
								<s:property value="subMerInfo.regAddr"/>
								<s:if test="subMerInfo.regAddr==''">未填写</s:if>
								<s:if test="subMerInfo.regAddr==null">未填写</s:if>
							</td>
							
						</tr>
						<tr>
						<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
								<s:property value="subMerInfo.taxNo"/>
								<s:if test="subMerInfo.taxNo==''">未填写</s:if>
								<s:if test="subMerInfo.taxNo==null">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								公司性质：
							</td>
							<td>
								<s:property value="subMerInfo.merKind"/>
								<s:if test="subMerInfo.merKind==null">未填写</s:if>	
								<s:if test="subMerInfo.merKind==''">未填写</s:if>	
							</td>	
						</tr>
						<tr>
							<td style="text-align: right;">
								法人代表：
							</td>
							<td style="text-align: left;">
								<s:property value="subMerInfo.legalPerson"/>
								<s:if test="subMerInfo.legalPerson==''">未填写</s:if>
								<s:if test="subMerInfo.legalPerson==null">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								法人证件号码：
							</td>
							<td style="text-align: left;">
								<s:property value="subMerInfo.legalIdcard"/>
								<s:if test="subMerInfo.legalIdcard==''">未填写</s:if>
								<s:if test="subMerInfo.legalIdcard==null">未填写</s:if>
							</td>
						</tr>
						<tr>
						<td width="120px" style="text-align: right;">
								结算账号：
							</td>
							<td>
								<s:property value="subMerInfo.settAccountNo"/>   
							</td>
							<td width="120px" style="text-align: right;">
								结算账户名：
							</td>
							<td>
								<s:property value="subMerInfo.settAccountName"/>
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
								入网时间：
							</td>
							<td>
								<s:property value="subMerInfo.createTime"/>
								<s:if test="subMerInfo.createTime==null">未填写</s:if>
								<s:if test="subMerInfo.createTime==''">未填写</s:if>	
							</td>
							<td width="120px" style="text-align: right;">
								<c:set value="${fn:split(subMerInfo.billCycle,'|') }" var="bills"/>
								<c:set value="${bills[1] }" var="day"/>
								结算周期： 
							</td>
							<td>
								<s:if
											test="subMerInfo.billCycle == null or subMerInfo.billCycle ==''">
											<span style="color: red;">未设置</span>
										</s:if>
										<s:else>
											<s:elseif test="subMerInfo.billCycle=='00'">
												停止结算
											</s:elseif>
											<s:if test="subMerInfo.billCycle=='01'">
												 T+1
											</s:if>
											<%-- <s:elseif test="subMerInfo.billCycle=='02'">
												D+1
											</s:elseif> --%>

											<s:else>
												<span style="color: red;"> <s:if
														test="subMerInfo.billCycle!=null">
												未知
											</s:if> <s:else>
												未设置
											</s:else> </span>
											</s:else>
										</s:else>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
							行业类别： 
							</td>
							<td>
								<s:if test="subMerInfo.mcc==null">未填写</s:if>
								<s:if test="subMerInfo.mcc==''">未填写</s:if>
								<s:iterator value="mccCodeList" var="mcc">
									<s:if test="#mcc.mcc == subMerInfo.mcc">
									<s:property value="#mcc.desc"/>
									（<s:property value="#mcc.profit"/>）
									</s:if>
								</s:iterator>
							</td>
							<td width="120px" style="text-align: right;">
								地区：
							</td>
							<td>
								<s:set var="sheng" value="subMerInfo.region.substring(0,2)"></s:set>
								<s:set var="shi" value="subMerInfo.region.substring(2,4)"></s:set>
								<s:iterator value="regionCodeList" var="reg">
									<s:if test="#reg.level==1 && #reg.code == #sheng">
										<s:property value="#reg.name"/>
									</s:if>
								</s:iterator>（省）
								<s:iterator value="regionCodeList" var="reg">
									<s:if test="#reg.level==2 && #reg.superCode==#sheng && #reg.code == #shi">
										<s:property value="#reg.name"/>
									</s:if>
								</s:iterator>（市）
							</td>
						</tr> 
						<tr>
							<td style="text-align: right;">
								联系人：
							</td>
							<td style="text-align: left;">
								<s:property value="subMerInfo.contactor"/>
								<s:if test="subMerInfo.contactor==''">未填写</s:if>
								<s:if test="subMerInfo.contactor==null">未填写</s:if>
							</td>
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td style="text-align: left;">
								<input type="hidden" name="subMerInfo.contactorPhone"  id="contactorPhone" value=<s:property  value="subMerInfo.contactorPhone" /> />
								<s:property value="subMerInfo.contactorPhone"/>
								<s:if test="subMerInfo.contactorPhone==''">未填写</s:if>
								<s:if test="subMerInfo.contactorPhone==null">未填写</s:if>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								已冻结金额：
							</td>
							<td>
							<s:property value="subMerInfo.frozenSum/100"/>&nbsp;&nbsp;元
							</td>
							<td width="150px" style="text-align: right;">
								需冻结限额：
							</td>
							<td>
							<s:property value="subMerInfo.frozenSumMax/100"/>&nbsp;&nbsp;元
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								扣率通道：
							</td>
							<td style="text-align: left;">
								<%-- <select name="subMerTrans.subMerTractR1" id="subMerTractR1">
										<option value="-1">
												请选择
										</option>
									<s:iterator value="merTractList" var="tra">
										<s:if test="profitType == '01' && tractInfo.status==1 && tractInfo.tractType==2">
											<option value="<s:property value="tractInfo.tractId"/>">
												<s:property value="tractInfo.tractName"/>
											</option>
										</s:if>
									</s:iterator>
								</select> --%>
								<input name="subMerTrans.subMerTractR1" id="subMerTractR1" value="<s:property value="subMerTrans.subMerTractR1"/>"  />
							</td>
							<td width="150px" style="text-align: right;">
								POS渠道：
							</td>
							<td style="text-align: left;">
								<%-- <select name="subMerTrans.subMerTractR2" id="subMerTractR2">
									<option value="-1">
												请选择
									</option>
									<s:iterator value="merTractList" var="tra">
										<s:if test="profitType == '02' && tractInfo.status==1 && tractInfo.tractType==2">
											<option value="<s:property value="tractInfo.tractId"/>">
												<s:property value="tractInfo.tractName"/>
											</option>
										</s:if>
									</s:iterator>
								</select> --%>
								<input name="subMerTrans.subMerTractR2" id="subMerTractR2" value="<s:property value="subMerTrans.subMerTractR2"/>"  />
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								POS渠道商户号：
							</td>
							<td style="text-align: left;">
								<%-- <select name="subMerTrans.subMerTractR3" id="subMerTractR3">
									<option value="-1">
												请选择
									</option>
									<s:iterator value="merTractList" var="tra">
										<s:if test="profitType == '05' && tractInfo.status==1 && tractInfo.tractType==2">
											<option value="<s:property value="tractInfo.tractId"/>">
												<s:property value="tractInfo.tractName"/>
											</option>
										</s:if>
									</s:iterator>
								</select> --%>
								<input name="subMerInfo.scanMerId" id="scanMerId" value="<s:property value="subMerInfo.scanMerId"/>"  />
							</td>
						<td width="150px" style="text-align: right;">
								扫码商户号：
							</td>
							<td style="text-align: left;">
								<%-- <select name="subMerTrans.subMerTractR3" id="subMerTractR3">
									<option value="-1">
												请选择
									</option>
									<s:iterator value="merTractList" var="tra">
										<s:if test="profitType == '05' && tractInfo.status==1 && tractInfo.tractType==2">
											<option value="<s:property value="tractInfo.tractId"/>">
												<s:property value="tractInfo.tractName"/>
											</option>
										</s:if>
									</s:iterator>
								</select> --%>
								<input name="subMerTrans.subMerTractR3" id="subMerTractR3" value="<s:property value="subMerTrans.subMerTractR3"/>"  />
							</td>
						</tr>
						<!-- 费率开始 -->
					<tr>
						<td colspan="4" style="text-align: center;">
						费率信息
						</td>
					</tr>	
		<s:iterator value="subMerRateList" var="smr">
				<tr style="height: 30px">
				<td></td>
					<td colspan="2" style="text-align: center;">
						费率类型：扣率+封顶+积分
					</td>
				</tr>
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
						<input  type="hidden"   name="t0Rate" id="t0Rate"  value="<s:property value="#subMerRate.teransRate"/>"/>
					</td>
					<td width="120px" style="text-align: right;">
						子商户封顶值：
					</td>
					<td>
						<s:if test="#smr.transHighestFee== ''||#smr.transHighestFee== null ||#smr.transHighestFee==0 ||#smr.transHighestFee==-1" >
							无
						</s:if>
						<s:else>
							<label id="transHighestFee2"><s:property value="#smr.transHighestFee"/>分</label>
						</s:else>
					</td>
				</tr>
				<tr style="height: 30px">
					<td width="120px" style="text-align: right;">
						子商户积分扣率：
					</td>
					<td>
						<s:if test="#smr.transRateNoTop== ''||#smr.transRateNoTop== null ||#smr.transRateNoTop==0 ||#smr.transRateNoTop==-1" >
							无
						</s:if>
						<s:else>
							<label id="transRateNoTop"><s:property value="#smr.transRateNoTop"/>%</label>
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
				<tr>
					<td width="120px" style="text-align: right;">
						一级代理封顶费率：
					</td>
					<td>
						<s:if test="#smr.agentL1RateH== ''||#smr.agentL1RateH== null ||#smr.agentL1RateH==0 ||#smr.agentL1RateH==-1" >
						无
						</s:if>
						<s:else>
							<label id="teransRate2"><s:property value="#smr.agentL1RateH"/>%</label>
						</s:else>
					</td>
					<td width="120px" style="text-align: right;">
						二级代理封顶费率：
					</td>
					<td>
						<s:if test="#smr.agentL2RateH== ''||#smr.agentL2RateH== null ||#smr.agentL2RateH==0 ||#smr.agentL2RateH==-1" >
						无
						</s:if>
						<s:else>
							<label id="teransRate2"><s:property value="#smr.agentL2RateH"/>%</label>
						</s:else>
					</td>
				</tr>
			</s:iterator>
						<tr>
							<td colspan="4" align="center">
								<s:if test="subMerInfo.status==1 ||subMerInfo.status==0">
									<input class="l-button" type="button" value="上线" id="btn2" onclick="javascript:update(2,'<s:property value="merTrans.terminalType"/>')" />
								</s:if>
								<s:if test="subMerInfo.status==2">
									<input class="l-button" type="button" value="暂停" id="btn2" onclick="javascript:update(3,null)" />
								</s:if>
								<s:if test="subMerInfo.status==3||subMerInfo.status==4">
									<input class="l-button" type="button" value="恢复" id="btn2" onclick="javascript:update(2,null)" />
								</s:if>&nbsp;&nbsp;
								<input type="button" value="审核失败"  id="btn2" class="l-button" onclick="look(<s:property value="subMerInfo.subMerId"/>)" />&nbsp;&nbsp;
								<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cols()" />
							</td>
						</tr>
					</table>
			</div>
		</div>

	</body>
</html>
