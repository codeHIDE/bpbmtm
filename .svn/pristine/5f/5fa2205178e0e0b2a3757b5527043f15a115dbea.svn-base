<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户修改结算信息</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script type="text/javascript">
		$(function (){
		          if(${requestScope.mess=='fone'}){
					alert("级别密码确认失败！");
					window.parent.$.ligerDialog.close();
					window.parent.$(".l-dialog,.l-window-mask").remove();
					}
     		 });  
		function update(){
			if(validata())
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
					if(yes){
						$.ajax({
		                   type: "POST",
		                   dataType: "text",
		                   url: "<s:url value='/merchantInfo!updateSettleMerchantInfo.ac'/>",
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="succ"){
		                   	    $.ligerDialog.success('修改成功！');
		                   	    
		                      }else if (data=="fone"){
		                   	    $.ligerDialog.success('修改失败！');
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
					
				function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
				}
				function validata(){settAccType
					var settAccType = $("#settAccType").val();
					if(settAccType == -1){
						alert("请选择结算类型");
						return false;
					}
					if(	len('settAccountNo',60,'N','结算账户号') &&
						len('settAgency',30,'All','结算机构名称') &&
						len('settAccountName',60,'All','结算账户名'))
						return true;
						return false;
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
				<form id="form1" action="<s:url value='/merchantInfo!updateSettleMerchantInfo.ac'/>" method="post">
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;">
								机构商户号：
							</td>
							<td>
								<s:property value="merchantInfo.merSysId" />
								<input type="hidden" name="merchantInfo.merSysId" value="<s:property value="merchantInfo.merSysId"/>"></input>
							</td>
							<td style="text-align: right;">
								工商注册名：
							</td>
							<td >
								<s:property value="merchantInfo.merName" />
								<input type="hidden" name="merchantInfo.merName" value="<s:property value="merchantInfo.merName"/>"></input>
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
									机构商户状态：
								</td>
								<td id="merStatus">
									<s:if test="merchantInfo.status==0">
										<span style="color: red">未审批</span>
									</s:if>
									<s:if test="merchantInfo.status==1">
										<span style="color: green">已审批</span>
									</s:if>
									<s:if test="merchantInfo.status==2"><span style="color: green">已上线</span></s:if>
									<s:if test="merchantInfo.status==3">暂停</s:if>
									<s:if test="merchantInfo.status==4">黑名单</s:if>
									<input type="hidden" name="merchantInfo.status"
										value="<s:property value="merchantInfo.status"/>"></input>
								</td>
							</tr>
							<tr>
								<td style="text-align: right;">
								结算账户名：
							</td>
							<td>
								<input type="text" name="merchantInfo.settAccountName" id="settAccountName" value="<s:property value="merchantInfo.settAccountName"/>"></input>
							</td>
								<td width="135px" style="text-align: right;">
									结算账户号：
								</td>
								 <td>
									<input type="text" name="merchantInfo.settAccountNo" id="settAccountNo" value="<s:property value="merchantInfo.settAccountNo"/>"></input>
								</td>
								</tr>
								<tr>
								<td width="120px" style="text-align: right;">
									结算银行：
								</td>
								<td style="text-align: left;">
									<s:set var="sheng" value="merchantInfo.region.substring(0,2)"></s:set>
									<s:set var="shi" value="merchantInfo.region.substring(2,4)"></s:set>
									<select  id="region1" onchange="sel(this.value)" style="width:51px" class="inputext_style" name="merchantInfo.region" >
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
									<select  id="region2" style="width:51px" class="inputext_style" name="merchantInfo.region">
										<s:iterator value="regionCodeList" >
											<s:if test="code eq #shi && level eq 2 && superCode eq #sheng">
												<option value="<s:property value="code"/>,<s:property value="name"/>" ><s:property value="name"/></option>
											</s:if>
										</s:iterator>
									</select>
								   <select id="settAgency" class="inputext_style"
													style="width: 72px" name="merchantInfo.settAgency" onchange="lineNum()">
													<option value="-1" selected="selected">
														--请选择--
													</option>
													<s:iterator var="" value="bankBehalfBranchList">
														<option value="<s:property value="code" />,<s:property value="bankName" />" <s:if test="code eq merchantInfo.settAgency">selected="selected" </s:if> ><s:property value="bankName" />
														</option>
													</s:iterator>
												</select>
								   <select id="openBank" name="merchantInfo.openBank" class="inputext_style" style="width: 120px">
											<option value="<s:property value="merchantInfo.lineNum"/>,<s:property value="merchantInfo.openBank"/>"><s:property value="merchantInfo.openBank"/></option>
								   </select>
								   <span style="color: red">*</span>
								</td>
						</tr>
						<tr>
							<td  style="text-align: right;">
									结算账类型：
							</td>
							 <td>
								<select name="merchantInfo.settAccType" id="settAccType" style="width: 135px">
									<option value="-1">请选择</option>
									<option value="1" <s:if test="merchantInfo.settAccType == 1">selected</s:if>>对公</option>
									<option value="2" <s:if test="merchantInfo.settAccType == 2">selected</s:if>>对私</option>
								</select>
							</td>
							<td style="text-align: right;">
								清分周期：
							</td>
							<td style="text-align: left;">
								<select name="merchantInfo.billCycle" id="" class="inputext_style"
									style="width: 135px">
									<option value="01" <s:if test="merchantInfo.billCycle==01">selected</s:if>>
										T+1
									</option>
									<!-- <option value="02" <s:if test="merchantInfo.billCycle==02">selected</s:if>>
										D+1
									</option> -->
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
