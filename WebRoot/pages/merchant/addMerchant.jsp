<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户管理平台添加机构商户</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascr" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<script src="<s:url value='/js/jqui/jquery-validation/jquery.validate.min.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js' />"></script>
		<!-- 选色器所需js和css
		<link rel="stylesheet" href="<s:url value='/css/colorpicker.css'/>" type="text/css" /> -->
       	<script type="text/javascript" src="<s:url value='/js/colorpicker/colorpicker.js'/>"></script>
		<!-- 选色器所需js和css -->
		<style type="text/css">
			tr{
				height:30px;
			}
			input{
				width: 220px;
			}
	        .l-table-edit {}
	        .l-table-edit-td{ padding:4px;}
	        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
	        .l-verify-tip{ left:230px; top:120px;}
		</style>
		<script type="text/javascript">
        $(function (){
          if(${requestScope.addMerchantInfo=='success'}){
					alert("添加机构商户成功！");
				}else if(${requestScope.addMerchantInfo=='fouse'}){
					alert("添加机构商户失败！");
				}
        });  
			function check(){
				var merKind=$('#merKind').val();
				var settAgency=$('#settAgency').val();
				var settAccType = $("#settAccType").val();
				var accessType = $("#accessType").val();
				if(settAccType==-1){
					alert("请选择结算机构类型");
					return false;
				}
				if(merKind==-1){
					alert("请选择机构商户性质");
					return false;
				}
				if(settAgency==-1){
					alert("请选择结算机构名称");
					return false;
				}
				if(accessType==-1){
					alert("请选择机构接入类别");
					return false;
				}
				if(	len('merName',60,'All','机构工商注册名') &&
					len('merRegno',20,'EN','机构工商注册号')	&&
					len('merTaxno',20,'EN','税务登记号')&&
					len('organizationCode',20,'EN','组织机构代码')&&
					len('merRegaddr',200,'All','工商注册地址') &&
					len('contactorAddr',200,'All','联系地址') &&
					len('merLegalPerson',60,'EandS','法人代表') &&
					len('merLegalIdcard',20,'EN','法人代表身份证号码') &&
					len('contracter',60,'EandS','联系人') &&
					len('contracterPhone',20,'N','联系人电话') &&
					len('settAccountNo',30,'N','结算账户号') &&
					//len('settAccountName',60,'EandS','结算账户名') &&
					len('settAccountName',60,'All','结算账户名') &&
					len('signPerson',30,'EandS','签约人') &&
					len('signDate',20,'All','签约日期') &&
					len('regCapital',10,'N','注册资本')
					){
						form1.submit();
						return true;
					}
					return false;
			}
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				<form action="<s:url value="merchantInfo!addMerchant.ac"/>" id="addMerchant" method="post" name="form1" enctype="multipart/form-data" >
					<table width="80%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="text-align: right;"> 
								机构工商注册名： 
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input  class="l-text-field" style="width:200px" type="text" name="merchantInfo.merName" type="text" id="merName" />
							</div>
							</td>
							<td style="text-align: right;">
								机构工商注册号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px" name="merchantInfo.merRegNo" id="merRegno" />
							</div>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.merTaxNo" id="merTaxno" />
							</div>
							</td>
							<td style="text-align: right;">
								组织机构代码：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.organizationCode" id="organizationCode" />
									</div>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.merRegAddr" id="merRegaddr" />
									</div>
							</td>
							<td width="120px" style="text-align: right;">
								机构商户性质：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<select name="merchantInfo.merKind" id="merKind"  class="l-text-field" style="width:200px">
									<option value="-1">
										-------------请选择--------------
									</option>
									<option value="国有企业">
										国有企业
									</option>
									<option value="集体企业">
										集体企业
									</option>
									<option value="联营企业">
										联营企业
									</option>
									<option value="股份合作制">
										股份合作制
									</option>
									<option value="股份有限公司">
										股份有限公司
									</option>
									<option value="有限责任公司">
										有限责任公司
									</option>
									<option value="合资企业">
										合资企业
									</option>
									<option value="个体户">
										个体户
									</option>
									<option value="私营企业">
										私营企业
									</option>
								</select>
								</div>
							</td>
							
						</tr>
						
						<tr>
							<td width="150px" style="text-align: right;">
								法人代表：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" maxlength="5" class="l-text-field" style="width:200px"
									name="merchantInfo.merLegalPerson" id="merLegalPerson" />
									</div>
							</td>
							<td width="150px" style="text-align: right;">
								法人代表身份证号码：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.merLegalIdcard" id="merLegalIdcard" />
									</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
								<input type="text"  class="l-text-field" style="width:200px"
									name="merchantInfo.contactor" id="contracter" />
									</div>
							</td>
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.contactorPhone" id="contracterPhone" />
									</div>
							</td>
						</tr>
						<tr><td style="text-align: right;">
								联系地址：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.contactorAddr" id="contactorAddr" />
									</div>
							</td>
							<td style="text-align: right;">
								联系邮箱：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px"
									name="merchantInfo.contactorEmail" id="contactorEmail" />
									</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								注册资本：
							</td>
							<td>
							<div class="l-text" style="width: 200px; float:left;">
								<input type="text" class="l-text-field" style="width:200px;"
									name="merchantInfo.regCapital" id="regCapital" /> 
									</div><div style="width:50px;float:left;">&nbsp;万元</div>
							</td>
							<td width="150px" style="text-align: right;">
								结算账户号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field" style="width:200px" name="merchantInfo.settAccountNo" id="settAccountNo" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								结算账户类型：
							</td>
							<td>
								<div class="l-text" style="width: 200px;">
									<select name="merchantInfo.settAccType" id="settAccType"  style="width: 200px"  class="l-text-field" >
												<option value="-1">
													-------------请选择--------------
												</option>
												<option value="1" <c:if test="${merchantInfo.settAccType==1}">selected="selected"</c:if>>
													 对私
												</option>
												<option value="2" <c:if test="${merchantInfo.settAccType==1}">selected="selected"</c:if>>
													对公
												</option>
									</select>
								</div>
							</td>
							<td style="text-align: right">
								结算周期：
							</td>
							<td>
							<div class="l-text" style="width: 200px;float:left;">
							<select name="merchantInfo.billCycle" class="l-text-field"
									style="width: 200px">
									<option value="01">
										T+1
									</option>
									<!-- <option value="02">
										D+1
									</option> -->
								</select>
									</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								结算机构名称：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<select name="merchantInfo.settAgency" id="settAgency"  style="width: 200px"  class="l-text-field" >
											<option value="-1">
												-------------请选择--------------
											</option>
											<s:iterator value="bankBehalfBranchList" var="bank">
											<option value="<s:property value="code"/>">
												 <s:property value="bankName"/>
											</option>
											</s:iterator>
									</select>
									</div>
							</td>
							<td width="150px" style="text-align: right;">
								结算账户名：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text"  class="l-text-field" style="width:200px" 
									name="merchantInfo.settAccountName" id="settAccountName" />
									</div>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								签约人：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text"  class="l-text-field" style="width:200px" 
								 name="merchantInfo.signPerson" id="signPerson" />
								</div>
							</td>
							<td width="150px" style="text-align: right;">
								签约日期：
							</td>
							<td>
									<div class="l-text" style="width: 200px;">
								<input type="text" name="merchantInfo.signDate" id="signDate"  style="width:200px" 
													onfocus="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1900-01-01 00:00:00',maxDate:'2099-12-31 23:59:59'})"
													class="inputext_style l-text-field" />
													</div>
							</td>
						</tr>
						<tr>
							
							<!-- <td width="150px" style="text-align: right;">
									显示名称：
							</td>
							<td>
								<div class="l-text" style="width: 200px;">
								<input type="text" name="merchantInfo.dispName" id="dispName"  style="width:200px" class="l-text-field" />
								</div>
							</td> -->
							<td width="150px" style="text-align: right;">
								机构接入类别：
							</td>
							<td>
								<div class="l-text" style="width: 200px;">
									<select name="merchantInfo.accessType" id="accessType" style="width:200px" class="l-text-field">
										<option value="-1">
											-------------请选择--------------
										</option>
										<option value="1" <c:if test="${merchantInfo.accessType=='1'}">selected="selected"</c:if>>
											 大商户接入
										</option>
										<option value="2" <c:if test="${merchantInfo.accessType=='2'}">selected="selected"</c:if>>
											 子商户逐一入网
										</option>
										<!-- <option value="3" <c:if test="${merchantInfo.accessType=='3'}">selected="selected"</c:if>>
											多费率路由
										</option>
										<option value="4" <c:if test="${merchantInfo.accessType=='4'}">selected="selected"</c:if>>
											 金额路由
										</option>
										<option value="5" <c:if test="${merchantInfo.accessType=='5'}">selected="selected"</c:if>>
											 借贷记路由
										</option> -->
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<td style="text-align:center" colspan="4">
								-------------------------以下选填-----------------------
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								色调：
							</td>
							
							<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
								<select name="merchantInfo.color" id="color"  style="width:200px" class="l-text-field">
									<option value="-1">-------------请选择--------------</option>
									<s:iterator value="cssConfigList">
											<option value="<s:property value="cssId"/>">
												 <s:property value="cssName"/>
											</option>
									</s:iterator>
								</select>
								</div>
							</td>
							<%-- <td width="120px" style="text-align: right;">
								所属平台方：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<select name="merchantInfo.platMerId" id="platMerId"  style="width:200px" class="l-text-field" >
											<option value="-1">
												-------------请选择--------------
											</option>
											<s:iterator value="platMerInfoList">
											<option value="<s:property value="platMerId"/>">
												 <s:property value="platMerName"/>
											</option>
											</s:iterator>
											
									</select>
									</div>
							</td> --%>
							<td width="120px" style="text-align: right;">
								备注：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" name="merchantInfo.remark" id="remark"  style="width:200px" class="l-text-field" />
								</div>
							</td>
						</tr>
						<tr>
							 
						</tr>
						<tr>
							<td style="text-align:center" colspan="4">
								<input type="button" value="确认申请" id="btn" onclick="check()" style="width:80px;"/>
							</td>
						</tr>
					</table>
				</form>
				</div>
			</div>
	</body>
</html>
