<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>子商户结算信息修改</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<script type="text/javascript">
		function check(){
				if(len("billCycle","3","N","清分周期")&&
					len("settAccountNo","30","N","结算账户号")&&
					len("settAccountName","60","All","结算账户名")){
				return true;
				}return false;
		}
		function update(){
			/* var b=document.getElementById("billCycle").value;
			var d=document.getElementById("billCycle2").value;
			var c=b+"|"+d; */
			if(check()){
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
				if(yes){
					$.ajax({
	                   type: "POST",
	                   dataType: "html",
	                   url: '<s:url value="/subMerInfo!updateSettSubMerchantInfo.ac"/>'/* ?subMerInfo.billCycle='+c */,
	                   data: $('#form1').serialize(),
	                   success: function (data) {
	                      if(data=="success"){
	                   	    $.ligerDialog.success('修改成功！');
	                     	//dialogClose();
	                      }
	                   },
	                   error: function(data) {
		                  		 $.ligerDialog.success("错误提示:"+data.responseText);
		                         window.parent.$.ligerDialog.closeWaitting(); 
		                 }
					});
					}else{
						dialogClose();
					}	
				});
			}
		}
		function t0Update(){
			/* var b=document.getElementById("billCycle").value;
			var d=document.getElementById("billCycle2").value;
			var c=b+"|"+d; */
			var t0Status = 0;
				if($("#t0Status").attr("checked")==true){
					t0Status=1
				}
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
				if(yes){
				$.post("<s:url value="/subMerInfo!updatet0Status.ac"/>",
				{'subMerTrans.t0Status':t0Status,'subMerTrans.subMerId':$("#subMerId").val()},
				function(data){
					if(data=="succ"){
                  	    $.ligerDialog.success('修改成功！');
                    	//dialogClose();
                     }else{
                     	$.ligerDialog.success('修改失败！');
                     }
				},"text");
				}
				})
	}
					
			function cls(){
				parent.location.reload();
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			//省市级联
			function sel(val){
				var val = val.split(',')[0];
				if(val != -1){
					$.post("<s:url value='/subMerInfo!getRegionCodeListBySuperCode.ac'/>",
					{'regionCode.superCode':val},
					function (data){
						strObj = eval(data);
						$("#region2").empty();
						$("#region2").append("<option value='-1'>--市--</option>");
						$.each(strObj, function(i) {
							if(strObj[i].superCode == val && strObj[i].level == 2)
							$("#region2").append("<option value='"+strObj[i].code+","+strObj[i].name+"'>"+strObj[i].name+"</option>");
						});
					},"json");
				}
			}
			//查询联行号
			function lineNum(){
				var region = $("#region1").val().split(',')[1];
				var city = $("#region2").val().split(',')[1];
				var bankName = $("#settAgency").val().split(',')[1];
				var  val = 1;
				if( region == "" || city == "" || bankName == ""){
					val = -1;
				}
				if( val != -1){
					$.post("<s:url value='/subMerInfo!getOpenBank.ac'/>",
					{'subMerInfo.region':region+','+city,
					 'subMerInfo.settAgency':bankName},
					 function(data){
						 strObj = eval(data);
						 $("#openBank").empty();
						 $("#openBank").append("<option value='-1'>--支行--</option>");
						 $.each(strObj, function(i){
							 $("#openBank").append("<option value='"+strObj[i].lineNum+","+strObj[i].openBank+"'>"+strObj[i].openBank+"</option>");
						 });
					 },"json");
				}
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
				<form id="form1" action="<s:url value='/subMerInfo!updateSettSubMerchantInfo.ac'/>" method="post">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								子商户入网号：
							</td>
							<td>
								<s:property value="subMerInfo.subMerId" />
								<input type="hidden" name="subMerInfo.subMerId" id="subMerId" value="<s:property value="subMerInfo.subMerId"/>"></input>
							</td>
							<td style="text-align: right;">
								子商户入网名：
							</td>
							<td>
							<s:property value="subMerInfo.subMerName" />
								<input type="hidden" name="subMerInfo.subMerName" value="<s:property value="subMerInfo.subMerName"/>"></input>
							</td>
						</tr>
						<tr>
							
							<td style="text-align: right;">
								商户状态：
							</td>
							<td id="Status">
								<s:if test="subMerInfo.status==0">
									<span style="color: red">未审批</span>
								</s:if>
								<s:if test="subMerInfo.status==1">
									<span style="color: green">已审批</span>
								</s:if>
								<s:if test="subMerInfo.status==2"><span style="color: green">开通服务</span></s:if>
								<s:if test="subMerInfo.status==3">暂停服务</s:if>
								<s:if test="subMerInfo.status==4">停止服务</s:if>
								<s:if test="subMerInfo.status==5">黑名单</s:if>
								<input type="hidden" name="subMerInfo.status"
									value="<s:property value="subMerInfo.status"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
								结算账户类型：
							</td>
							<td>
								<input type="radio" <s:if test="subMerInfo.settAccType eq 1">checked="checked"</s:if> name="subMerInfo.settAccType" id="settAccType1" value="1"  />对公
								<input type="radio" <s:if test="subMerInfo.settAccType eq 2">checked="checked"</s:if> name="subMerInfo.settAccType" id="settAccType2" value="2"  />对私
							</td>
						</tr>
						<tr>
						<!-- 
						<td width="120px" style="text-align: right;">
								结算周期： 
							</td>
							<td>
								<s:property value="subMerInfo.billCycle" />
								<input type="hidden" name="subMerInfo.billCycle"
									value="<s:property value="subMerInfo.billCycle"/>"></input>
							</td>
						 -->
						<td width="120px" style="text-align: right;">
							<c:set value="${fn:split(subMerInfo.billCycle,'|') }" var="bills"/>
							<c:set value="${bills[1] }" var="day"/>
							结算周期：
						</td>
						<td>
							<%-- <input type="text" size="1" id="billCycle" value="${bills[0] }" name="billCycle"/> --%>
							<select id="billCycle" name="subMerInfo.billCycle">
									<!--  <option value="00" <c:if test="${subMerInfo.billCycle=='00'}">selected="selected"</c:if>>停止结算</option> -->
									<option value="01" <c:if test="${subMerInfo.billCycle=='01'}">selected="selected"</c:if>>T+1</option>
									<%-- <option value="02" <c:if test="${subMerInfo.billCycle=='02'}">selected="selected"</c:if>>D+1</option> --%>
									<%-- <option value="03" <c:if test="${subMerInfo.billCycle=='03'}">selected="selected"</c:if>>D+1</option> --%>
							</select>
							</td>
							<td style="text-align: right;">
								结算账户名：
							</td>
							<td>
									<input type="text" name="subMerInfo.settAccountName" id="settAccountName"
									value="<s:property value="subMerInfo.settAccountName"/>"></input>
							</td>
						</tr>
						<tr>
							
							<td width="120px" style="text-align: right;">
								结算账户号：
							</td>
							<td>
								<input type="text" name="subMerInfo.settAccountNo" id="settAccountNo"
									value="<s:property value="subMerInfo.settAccountNo"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
									结算银行：
								</td>
								<td style="text-align: left;">
									<s:set var="sheng" value="subMerInfo.region.substring(0,2)"></s:set>
									<s:set var="shi" value="subMerInfo.region.substring(2,4)"></s:set>
									<select  id="region1" onchange="sel(this.value)" style="width:51px" class="inputext_style" name="subMerInfo.region" >
										<option value="-1">--省--</option>
											<s:iterator value="regionCodeList" >
												<s:if test="level == 1 && #sheng eq code">
													<option value="<s:property value="code"/>,<s:property value="name"/>" selected="selected" ><s:property value="name"/></option>
												</s:if>
												<s:if test="level == 1 && #sheng neq code">
													<option value="<s:property value="code"/>,<s:property value="name"/>" ><s:property value="name"/></option>
												</s:if>
											</s:iterator>
									</select>
									<select  id="region2" style="width:51px" class="inputext_style" name="subMerInfo.region">
										<s:iterator value="regionCodeList" >
											<s:if test="code eq #shi && level eq 2 && superCode eq #sheng">
												<option value="<s:property value="code"/>,<s:property value="name"/>" ><s:property value="name"/></option>
											</s:if>
										</s:iterator>
									</select>
<%--								</td>--%>
<%--								<td>--%>
<%--									<select name="subMerInfo.settAgency" id="settAgencyName"  style="width:75px">--%>
<%--										<s:iterator value="bankBehalfBranchList" var="bank">--%>
<%--													<s:if test="#bank.code==subMerInfo.settAgency">--%>
<%--													<option value="<s:property value="#bank.code" />" selected="selected">--%>
<%--														<s:property value="#bank.bankName" />--%>
<%--													</option>--%>
<%--													</s:if>--%>
<%--													<s:else>--%>
<%--												<option value="<s:property value="#bank.code" />">--%>
<%--													<s:property value="#bank.bankName" />--%>
<%--												</option>--%>
<%--												</s:else>--%>
<%--										</s:iterator>--%>
<%--								   </select>--%>
								   <select id="settAgency" class="inputext_style"
													style="width: 72px" name="subMerInfo.settAgency" onchange="lineNum()">
													<option value="-1" selected="selected">
														--请选择--
													</option>
													<s:iterator var="" value="bankBehalfBranchList">
														<option value="<s:property value="code" />,<s:property value="bankName" />" <s:if test="code eq subMerInfo.settAgency">selected="selected" </s:if> ><s:property value="bankName" />
														</option>
													</s:iterator>
												</select>
								   <select id="openBank" name="subMerInfo.openBank" class="inputext_style" style="width: 120px">
											<option value="<s:property value="subMerInfo.lineNum"/>,<s:property value="subMerInfo.openBank"/>"><s:property value="subMerInfo.openBank"/></option>
								   </select>
								   <span style="color: red">*</span>
								</td>
						</tr>
						 <tr>
							<td  style="text-align: right;">
								清算状态：
							</td>
							<td>
							<select id="billStatus" name="subMerInfo.billStatus">
									<option value="0" <c:if test="${subMerInfo.billStatus=='0'}">selected="selected"</c:if>>停止清算</option>
									<option value="1" <c:if test="${subMerInfo.billStatus=='1'}">selected="selected"</c:if>>正常清算</option>
									
							</select>
							</td>
						</tr> 
						<tr>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="确认修改" id="btn" class="l-button" onclick="update()" />
							</td>
							<td colspan="2" align="center">
								<br />
								<input type="button" value="关 闭" id="btn2" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
