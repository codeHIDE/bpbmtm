<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>子商户商户修改</title>
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
		function check(){
			if($("#legalIdcard").val().trim() == "" || $("#legalIdcard").val() == null){
				alert("法人证件号码不能为空！");
				return false;
			}
			
			if(len("subMerName","60","All","子商户入网名")&&
				len("legalPerson","60","EandS","法人代表")&&
				//len("legalIdcard","60","ID_CARD","法人证件号码")&&
				len("contactor","60","EandS","联系人")&&
				len("contactorPhone","60","N","联系人电话")&&
				len("dispMerId","15","N","打印商户号")&&
				len("dispMerName","60","All","打印商户名称")){
				return true;
			}
			
			return false;
		}
		
		function updateInfo(){
			if(check()){
				$.ligerDialog.confirm('确定修改信息?', function (yes) {
					if(yes){ 
					$.ajax({
	                   type: "POST",
	                   dataType: "html",
	                   url: "<s:url value='/subMerInfo!updateFindById.ac'/>",
	                   data: $('#form1').serialize(),
	                   success: function (data) {
	                      if(data=="2"){
	                      alert('基本信息修改成功！');
	                   	    history.go(0) 
	                      }else if(data=="5"){
	                    	  alert('通道类型不匹配！');
	                    	  history.go(0) 
	                      }
	                   },
	                   error: function(data) {
		                  		 $.ligerDialog.success("错误提示:"+data.responseText);
		                         window.parent.$.ligerDialog.closeWaitting(); 
		                 }
					})
					}
				});}}
				
				function feilv(){
					parent.$.ligerDialog.close();
					parent.$.ligerDialog.open({ url: "<s:url value='/pages/subMerchant/updateInformationCenter.jsp'/>?id="+$("#id").val(),
            			height:170,width:350, isResize: false,title:'修改费率'});
				}
				
				function sel(val){
					if(val != -1){
						$.post("<s:url value='/merchantInfo!getRegionCodeListBySuperCode.ac'/>",
						{'regionCode.superCode':val},
						function (data){
							strObj = eval(data);
							$("#basicRegion2").empty();
							$("#basicRegion2").append("<option value='-1'>--市--</option>");
							$.each(strObj, function(i) {
								$("#basicRegion2").append("<option value='"+strObj[i].code+"'>"+strObj[i].name+"</option>");
							});
						},"json");
					}else{
						$("#basicRegion2").empty();
						$("#basicRegion2").append("<option value='-1'>-请选择-</option>");
					}
			}
			
			
			function changeChannelMerList(val){
					if(val != -1){
						var chAddId = $("#subMerTractR2").val();
						var t0Rate = $("#t0Rate").val();
						$.post("<s:url value='/subMerInfo!getChannelMerCode.ac'/>",
						{'channelMerInfo.t0Rate':t0Rate,
						'channelMerInfo.chAddId':chAddId},
						function(data){
						 strObj = eval(data);
						 $("#subMerTractR3").empty();
						 $("#subMerTractR3").append("<option value='-1'>--请选择--</option>");
						 $.each(strObj, function(i){
							 $("#subMerTractR3").append("<option value='"+strObj[i].chId+"'>"+strObj[i].chName+"|"+strObj[i].t0Fee+"</option>");
						 });
					 },"json");
				}else{
						$("#subMerTractR3").empty();
						$("#subMerTractR3").append("<option value='-1'>-请选择-</option>");
					}
			}
			
			
		</script>
		<style type="text/css">
		tr {
			height: 25px;
		}
		</style>
	</head>
	<body style="padding: 4px;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				<form id="form1" action="<s:url value='/subMerInfo!updateSubMerchantInfo.ac' />" method="post">
				<input  type="hidden" id="accessType" name="merInfo.accessType" value="<s:property value="merInfo.accessType" />"/>
				<input  type="hidden" id="rateType" name="subMerRate.rateType" value="<s:property value="subMerRate.rateType" />"/>
					<table width="90%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								子商户入网号：
							</td>
							<td>
								<s:property value="subMerInfo.subMerId" />
								<input type="hidden" name="subMerInfo.subMerId" value="<s:property value="subMerInfo.subMerId"/>"></input>
								<input type="hidden" id="id" value="<s:property value="subMerInfo.subMerId"/>"></input>
							</td>
							<td style="text-align: right;">
								子商户入网名：
							</td>
							<td>
							<input type="text" name="subMerInfo.subMerName" id="subMerName"
									value="<s:property value="subMerInfo.subMerName"/>"></input>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								商户状态：
							</td>
							<td id="Status">
								<s:if test="subMerInfo.status==0"> <span style="color: red">未审批</span> </s:if>
								<s:if test="subMerInfo.status==1"> <span style="color: green">已审批</span> </s:if>
								<s:if test="subMerInfo.status==2"><span style="color: green">开通服务</span></s:if>
								<s:if test="subMerInfo.status==3">暂停服务</s:if>
								<s:if test="subMerInfo.status==4">停止服务</s:if>
								<s:if test="subMerInfo.status==5">黑名单</s:if>
								<s:if test="subMerInfo.status==-3">待二级代理审批</s:if>
								<s:if test="subMerInfo.status==-2">待一级代理审批</s:if>
								<input type="hidden" name="subMerInfo.status"
									value="<s:property value="subMerInfo.status"/>"></input>
							</td>
							<td style="text-align: right;">
								所属商户号：
							</td>
							<td>
							<s:property value="subMerInfo.merSysId" />
								<input type="hidden" name="subMerInfo.merSysId"
									value="<s:property value="subMerInfo.merSysId"/>"></input>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
								<input type="text" name="subMerInfo.taxNo" id="taxNo"value="<s:property value="subMerInfo.taxNo"/>"></input>
							</td>
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td>
								<input type="text" name="subMerInfo.regAddr" id="regAddr"
									value="<s:property value="subMerInfo.regAddr"/>"></input>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								地区：
							</td>
							<td>
								<s:set var="sheng" value="subMerInfo.region.substring(0,2)"></s:set>
								<s:set var="shi" value="subMerInfo.region.substring(2,4)"></s:set>
								<select id="basicRegion1" style="width: 65px" name="subMerInfo.region" onchange="sel(this.value)">
									<option value="-1">
									-请选择-
									</option>
								<s:iterator value="regionCodeList" var="reg">
									<s:if test="#reg.level==1">
										<option value="<s:property value="#reg.code"/>" 
										<s:if test="#reg.code == #sheng">selected="selected"</s:if>>
										<s:property value="#reg.name"/>
										</option>
									</s:if>
								</s:iterator>
								</select>
								<select id="basicRegion2" name="subMerInfo.city" style="width: 85px">
									<option value="-1">
									-请选择-
									</option>
								<s:iterator value="regionCodeList" var="reg">
									<s:if test="#reg.level==2 && #reg.superCode==#sheng">
										<option value="<s:property value="#reg.code"/>" 
										<s:if test="#reg.code == #shi">selected="selected"</s:if>>
										<s:property value="#reg.name"/>
										</option>
									</s:if>
								</s:iterator>
								</select>
							</td>
							<td style="text-align: right;">
								工商注册号码：
							</td>
							<td>
								<input type="text" name="subMerInfo.regNo" id="regNo"
									value="<s:property value="subMerInfo.regNo"/>"></input>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								入网时间：
							</td>
							<td>
								<s:property value="subMerInfo.createTime" />
								<input type="hidden" name="subMerInfo.createTime" value="<s:property value="subMerInfo.createTime"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
							行业类别： 
							</td>
							<td>
								<select style="width: 130px" name="subMerInfo.mcc">
									<s:iterator value="mccCodeList" var="mcc">
										<option value="<s:property value="#mcc.mcc"/>" 
										<s:if test="#mcc.mcc == subMerInfo.mcc">selected="selected"</s:if>>
												<s:property value="#mcc.desc"/>
												（<s:property value="#mcc.profit"/>）
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								法人代表：
							</td>
							<td>
								<input type="text" name="subMerInfo.legalPerson" id="legalPerson"
									value="<s:property value="subMerInfo.legalPerson"/>"></input>
							</td>
							
							<td width="150px" style="text-align: right;">
								法人证件号码：
							</td>
							<td>
								<input type="text" name="subMerInfo.legalIdcard" id="legalIdcard"
									value="<s:property value="subMerInfo.legalIdcard"/>"></input>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td>
							<input type="text" name="subMerInfo.contactor" id="contactor"
									value="<s:property value="subMerInfo.contactor"/>"></input>
							</td>
							<td style="text-align: right;">
								联系人电话：
							</td>
							<td>
							<input type="text" name="subMerInfo.contactorPhone" id="contactorPhone"
									value="<s:property value="subMerInfo.contactorPhone"/>"></input>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								打印商户号：
							</td>
							<td>
							<input type="hidden" name="subMerInfo.subMerTrans.subMerId" value="<s:property value="subMerTrans.subMerId"/>"></input>
						
							<input type="text" name="subMerInfo.subMerTrans.dispMerId" id="dispMerId"
									value="<s:property value="subMerTrans.dispMerId"/>"></input>
							</td>
							<td style="text-align: right;">
								打印商户名称：
							</td>
							<td>
							<input type="text" name="subMerInfo.subMerTrans.disMerName" id="dispMerName"
									value="<s:property value="subMerTrans.disMerName"/>"></input>
							</td>
						</tr>
						<s:if test="merInfo.accessType == 2">
						<tr>
							<td width="150px" style="text-align: right;">
								通道：
							</td>
							<td style="text-align: left;">
								<select name="subMerTrans.subMerTractR1" id="subMerTractR1">
										<option value="-1">
												请选择
										</option>
									<s:iterator value="tractInfoList" var="tra">
										<option value="<s:property value="tractId"/>"
										<s:if test="(subMerRate.rateType == '01' && tractId == subMerTrans.subMerTractR1) || (subMerRate.rateType == '02' && tractId == subMerTrans.subMerTractR2)">selected</s:if>>
											<s:property value="tractName"/>
										</option>
									</s:iterator>
								</select>
							</td>
						</tr>
						</s:if>
						<s:else>
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
											<option value="<s:property value="tractInfo.tractId"/>"
											<s:if test="tractInfo.tractId == subMerTrans.subMerTractR1">selected</s:if>>
												<s:property value="tractInfo.tractName"/>
											</option>
										</s:if>
									</s:iterator>
								</select> --%>
								<input name = "subMerTrans.subMerTractR1" id="subMerTractR1" value="<s:property value="subMerTrans.subMerTractR1"/>"  />
							</td>
							<td width="150px" style="text-align: right;">
								POS渠道：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 150px;">
								<select name="subMerTrans.subMerTractR2" id="subMerTractR2"  style="width: 150px"  class="l-text-field"  
								<s:if test="subMerTrans.t0Status == 1">
								onchange="changeChannelMerList(this.value)"
								</s:if>>
											<option value="-1">
											----请选择----
											</option>
											<s:iterator value="channelAddInfoList" var="channelAddInfo">
												<option value="<s:property value="#channelAddInfo.chAddId"/>" 
												<s:if test="#channelAddInfo.chAddId == subMerTrans.subMerTractR2">selected="selected"</s:if>>
														 <s:property value="#channelAddInfo.chName"/>
												</option>
											</s:iterator>
									</select>
								</div>
							</td>
						</tr>
						<tr>
						<td width="150px" style="text-align: right;">
								POS渠道商户：
							</td>
							<td style="text-align: left;">
								<s:if test="subMerTrans.t0Status == 1">
								<select name="subMerTrans.subMerTractR3" id="subMerTractR3" >
									<option value="-1">
												请选择
									</option>
									<s:iterator value="channelMerInfoList" var="channelMerInfo">
												<option value="<s:property value="#channelMerInfo.chId"/>" 
												<s:if test="#channelMerInfo.chId == subMerTrans.subMerTractR3">selected="selected"</s:if>>
														 <s:property value="#channelMerInfo.chName"/>|<s:property value="#channelMerInfo.t0Fee"/>
												</option>
											</s:iterator>
								</select>
								</s:if>
								<s:else>
									<input name="subMerTrans.subMerTractR3" id="subMerTractR3" value="<s:property value="subMerTrans.subMerTractR3"/>" />
								</s:else>
							</td>
							<td style="text-align: right;">
								扫码商户号：
							</td>
							<td>
							<input type="text" name="subMerInfo.scanMerId" id="scanMerId"
									value="<s:property value="subMerInfo.scanMerId"/>"></input>
							</td>
						</tr>
						<s:if test="subMerInfo.status == 5">
							<tr style="color: red;">
							<td style="text-align: right;color: red;" >
								审核失败原因：
							</td>
							<td colspan="3">
								<s:property value="subMerInfo.remark"/>
							</td>
							</tr>
						</s:if>
						</s:else>
						<tr>
							<td colspan="4" align="center"><br />
								<c:if test="${purview['10521'] == '10521'}">
									<input type="button" style="width: 100px" value="基本信息修改" id="btn" class="l-button" onclick="updateInfo()"/>
								</c:if>
							</td>
						</tr>
						<!-- 费率开始 -->
						<tr>
							<td colspan="4" style="text-align: center;">
							费率信息
							</td>
						</tr>
					</table>
	<table width="90%">
<s:iterator value="subMerRateList" var="subMerRate">
	<tr style="height:35px">
		<td colspan="8" align="center">
			费率类型:
			<s:if test="#subMerRate.rateType==01">扣率</s:if>
			<s:if test="#subMerRate.rateType==02">封顶</s:if>
			<s:if test="#subMerRate.rateType==03">封顶-扣率</s:if>
			<s:if test="#subMerRate.rateType==04">T+0扣率</s:if>
			<s:if test="#subMerRate.rateType==05">T+0封顶</s:if>
		</td>
	</tr>
	<tr>
	<s:if test="#subMerRate.rateType==01||#subMerRate.rateType==03">
		<td style="text-align: right;">
		<input type="hidden" name="subMerInfo.subMerRate.rateType" size="4"
				value="<s:property value="#subMerRate.rateType"/>"/>
		<input type="hidden" name="subMerRate.rateType" 
			value="<s:property value="#subMerRate.rateType"/>"/>
			交易费率:
		</td>
		<td align="left">
			<s:property value="#subMerRate.teransRate"/> %
			<input  type="hidden"   name="t0Rate" id="t0Rate"  value="<s:property value="#subMerRate.teransRate"/>"/>
		</td>
	</s:if>
	<s:if test="#subMerRate.rateType==02||#subMerRate.rateType==03">
		<td style="text-align: right;">
			封顶费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.transHighestFee==null || #subMerRate.transHighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.transRateH"/> %
			</s:else>
		</td>
		<td width="100px" style="text-align: right;">
			封顶值:
		</td>
		<td align="left">
			<s:if test="#subMerRate.transHighestFee==null || #subMerRate.transHighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.transHighestFee"/> 分
			</s:else>
		</td>
		<td width="100px" style="text-align: right;">
			积分扣率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.transRateNoTop==null || #subMerRate.transRateNoTop=='-1'">
				无
				</s:if><s:else>
				<s:property value="#subMerRate.transRateNoTop"/> %
			</s:else>
		</td>
	</s:if>
	</tr>
	<!-- 如果子商户的一级代理商不为空，页面显示所有一级代理费率信息 -->
	<s:if test="subMerInfo.agentIdL1 != null && subMerInfo.agentIdL1 != '-1'">
	<tr style="height:35px">
	<s:if test="#subMerRate.rateType==01||#subMerRate.rateType==03">
		<td align="right">
		一级代理费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1Rate==null || #subMerRate.agentL1Rate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1Rate"/> 分
			</s:else>
		</td>
	</s:if>
	<s:if test="#subMerRate.rateType==02||#subMerRate.rateType==03">
		<td align="right">
		一级代理封顶费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1RateH==null || #subMerRate.agentL1RateH=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1RateH"/> 分
			</s:else>
		</td>
		<td align="right">
		一级代理封顶值:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1HighestFee==null||#subMerRate.agentL1HighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1HighestFee"/> 分
			</s:else>
		</td>
	</s:if>
		<td align="right">
			一级代理分润占:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL1ProfitRate==null || #subMerRate.agentL1ProfitRate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL1ProfitRate"/> 分
			</s:else>
		</td>
	</tr>
	</s:if>
	<!-- 如果子商户的二级代理商不为空，页面显示所有二级代理商费率信息 -->
	<s:if test="subMerInfo.agentIdL2 != null && subMerInfo.agentIdL2 != -1">
	<tr style="height:35px">
	<s:if test="#subMerRate.rateType==01||#subMerRate.rateType==03">
		<td align="right">
		二级代理费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2Rate==null || #subMerRate.agentL2Rate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2Rate"/> 分
			</s:else>
		</td>
	</s:if>
	<s:if test="#subMerRate.rateType==02||#subMerRate.rateType==03">
		<td align="right">
		二级代理封顶费率:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2RateH==null || #subMerRate.agentL2RateH=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2RateH"/> 分
			</s:else>
		</td>
		<td align="right">
		二级代理封顶值:<s:property value="#subMerRate.agentL2HighestFee"/>
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2HighestFee==null||#subMerRate.agentL2HighestFee=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2HigestFee"/> 分
			</s:else>
		</td>
		</s:if>
		<td align="right">
		二级代理分润占:
		</td>
		<td align="left">
			<s:if test="#subMerRate.agentL2ProfitRate==null || #subMerRate.agentL2ProfitRate=='-1'">
				无
			</s:if><s:else>
				<s:property value="#subMerRate.agentL2ProfitRate"/> 分
			</s:else>
		</td>
	</tr>
	</s:if>
</s:iterator>
	<tr>
		<td colspan="4" align="center">
		<c:if test="${purview['10522'] == '10522'}">
		<input type="button" value="费率修改" id="btn" class="l-button" onclick="feilv()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</c:if>
		</td>
		<td colspan="4"  align="center">
		<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()"/>
		</td>
	</tr>
</table>
				</form>
			</div>
		</div>
	</body>
</html>
