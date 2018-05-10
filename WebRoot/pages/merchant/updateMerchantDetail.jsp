<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户修改</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>" type="text/javascript"></script> 
		<script src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript">
		function update(){
			if(check()){
				$.ligerDialog.confirm('确定修改信息?', function (yes) { 
				if(yes){
					$.ajax({
	                   type: "POST",
	                   dataType: "text",
	                   url: "<s:url value='/merchantInfo!updateMerchantInfo.ac'/>",
	                   data: $('#form1').serialize(),
	                   success: function (data) {
	                      if(data=="succ"){
	                   	    $.ligerDialog.success('修改成功！');
	                      }else if(data=="fone"){
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
		}
					
				function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function check(){
				if(	len('merName',60,'All','机构工商注册名') &&
					len('merRegNo',20,'EN','机构工商注册号')	&&
					len('merTaxNo',20,'EN','税务登记号')&&
					len('organizationCode',20,'EN','组织机构代码')&&
					len('merRegAddr',200,'All','工商注册地址') &&
					len('contactorAddr',200,'All','联系地址') &&
					len('merLegalPerson',60,'EandS','法人代表') &&
					len('merLegalIdcard',20,'EN','法人代表身份证号码') &&
					len('contactor',60,'EandS','联系人') &&
					len('contactorPhone',20,'N','联系人电话') &&
					len('dispName',60,'All','显示名称') &&
					len('regCapital',10,'N','注册资本')	
					){
						return true;
					}
					return false;
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
				<form id="form1" action="<s:url value='/merchantInfo!updateMerchantInfo.ac'/>" method="post"  enctype="multipart/form-data" >
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
					<tr>
							<td width="120px" style="text-align: right;">
								机构商户号：
							</td>
							<td width="200px" class="l-table-edit-td">
								<s:property value="merchantInfo.merSysId"/>
								<input type="hidden" name="merchantInfo.merSysId" value="<s:property value="merchantInfo.merSysId"/>"></input>
							</td>
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
								<s:if test="merchantInfo.status==2">开通服务</s:if>
								<s:if test="merchantInfo.status==3">暂停服务</s:if>
								<s:if test="merchantInfo.status==4">黑名单</s:if>
								<input type="hidden" name="merchantInfo.status"
									value="<s:property value="merchantInfo.status"/>"></input>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								机构工商注册名：
							</td>
							<td width="120px" class="l-table-edit-td">
								<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" name="merchantInfo.merName" class="l-text-field" type="text" id="merName" value="<s:property value="merchantInfo.merName"/>"/>
								</div>
							</td>
							<td style="text-align: right;">
								机构工商注册号：
							</td>
							<td>
								<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" name="merchantInfo.merRegNo" class="l-text-field" id="merRegNo" value="<s:property value="merchantInfo.merRegNo"/>"/>
								</div>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td><div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text"
									name="merchantInfo.merTaxNo" class="l-text-field" id="merTaxNo" value="<s:property value="merchantInfo.merTaxNo"/>"/>
								</div>
							</td>
							<td width="150px" style="text-align: right;">
								组织机构代码：
							</td>
							<td>
							<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text"
									name="merchantInfo.organizationCode" class="l-text-field" id="organizationCode" value="<s:property value="merchantInfo.organizationCode"/>"/>
							</div>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								工商注册地址：
							</td>
							<td><div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text"  class="l-text-field" 
									name="merchantInfo.merRegAddr" id="merRegAddr" value="<s:property value="merchantInfo.merRegAddr"/>"/>
							</div>
							</td>
							<td width="120px" style="text-align: right;">
								机构商户性质：
							</td>
							<td>
								<s:property value="merchantInfo.merKind"/>
								<input style="width: 150px;" type="hidden"
									name="merchantInfo.merKind" id="merRegaddr" value="<s:property value="merchantInfo.merKind"/>"/>
							</td>
						</tr>
						<tr>
							<td width="150px" style="text-align: right;">
								法人代表：
							</td>
							<td><div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" maxlength="5"
									name="merchantInfo.merLegalPerson" class="l-text-field" id="merLegalPerson" value="<s:property value="merchantInfo.merLegalPerson"/>"/>
							</div>
							</td>
							<td width="150px" style="text-align: right;">
								法人身份证号码：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" class="l-text-field"
									name="merchantInfo.merLegalIdcard" id="merLegalIdcard" value="<s:property value="merchantInfo.merLegalIdcard"/>"/>
							</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text"  class="l-text-field"
									name="merchantInfo.contactor" id="contactor" value="<s:property value="merchantInfo.contactor"/>"/>
								</div>
							</td>
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td>
							<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" class="l-text-field"
									name="merchantInfo.contactorPhone" id="contactorPhone" value="<s:property value="merchantInfo.contactorPhone"/>"/>
							</div>
							</td>
						</tr>
						<tr><td style="text-align: right;">
								联系地址：
							</td>
							<td><div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" class="l-text-field"
									name="merchantInfo.contactorAddr" id="contactorAddr" value="<s:property value="merchantInfo.contactorAddr"/>"/>
							</div>
							</td>
							<td style="text-align: right;">
								联系邮箱：
							</td>
							<td><div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" class="l-text-field"
									name="merchantInfo.contactorEmail" id="contactorEmail" value="<s:property value="merchantInfo.contactorEmail"/>"/>
							</div>
							</td>
						</tr>
						<tr>
							<td width="50px" style="text-align: right;">
								注册资本：
							</td>
							<td>
							<div class="l-text" style="width: 150px;float:left">
								<input style="width: 150px;" type="text" class="l-text-field"
									name="merchantInfo.regCapital" id="regCapital" value="<s:property value="merchantInfo.regCapital"/>"/>
							</div>
							<div style="width:30px;float:left">万元</div>
							</td>
							<td width="50px" style="text-align: right;">
								所属平台方：
							</td>
							<td>
								<s:if test="merchantInfo.platMerId == -1">
									未设置
								</s:if>
								<s:else>
									<s:property value="merchantInfo.platMerId"/>
								</s:else>
								<input type="hidden" name="merchantInfo.platMerId" value="<s:property value="merchantInfo.platMerId"/>"></input>
							</td>
							</tr>
						<tr>
							
							<td width="150px" style="text-align: right;">
								创建时间：
							</td>
							<td>
								<s:property value="merchantInfo.createTime"/>
								<input type="hidden" name="merchantInfo.createTime" value="<s:property value="merchantInfo.createTime"/>"></input>
							</td>
							<td width="150px" style="text-align: right;">
								实人认证状态：
							</td>
							<td>
							<s:if test="merchantInfo.authStatus=='0'">未认证</s:if>
							<s:elseif test="merchantInfo.authStatus=='1'">认证成功</s:elseif>
							<s:elseif test="merchantInfo.authStatus=='2'">认证失败</s:elseif>
							</td>
						</tr>
						<tr>
							
							<td width="150px" style="text-align: right;">
								签约人：
							</td>
							<td>
								<s:property value="merchantInfo.signPerson"/>
								<input type="hidden" name="merchantInfo.signPerson" value="<s:property value="merchantInfo.signPerson"/>"></input>
							</td>
							<td width="150px" style="text-align: right;">
								签约日期：
							</td>
							<td>
								<s:property value="merchantInfo.signDate"/>
								<input type="hidden" name="merchantInfo.signPerson" value="<s:property value="merchantInfo.signPerson"/>"></input>
							</td>
						</tr>
						<tr>
							
							<td width="150px" style="text-align: right;">
									显示名称：
							</td>
							<td>
								<div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" name="merchantInfo.dispName"  class="l-text-field" id="dispName"  value="<s:property value="merchantInfo.dispName"/>"/>
								</div>
							</td>
							<td width="120px" style="text-align: right;">
								色调：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 150px;">
								<select name="merchantInfo.color" id="color"  style="width:150px" class="l-text-field">
									<option value="-1">--请选择--</option>
									<s:iterator value="cssConfigList">
									<s:if test="cssId == merchantInfo.color">
									<option  value="<s:property value="cssId"/>" selected>
												 <s:property value="cssName"/>
											</option>
									</s:if>
									<s:else>
											<option value="<s:property value="cssId"/>">
												 <s:property value="cssName"/>
											</option>
											</s:else>
									</s:iterator>
								</select>
								</div>
							</td>
						</tr>
						<tr>
							
							<td width="120px" style="text-align: right;">
								备注：
							</td>
							<td><div class="l-text" style="width: 150px;">
								<input style="width: 150px;" type="text" name="merchantInfo.remark" class="l-text-field" id="remark"  value="<s:property value="merchantInfo.remark"/>" />
							</div>
							</td> 
						</tr>
						<tr>
							<td colspan="2" align="center">
								<input type="button" value="确认修改" id="btn" class="l-button"
									onclick="update()" />
							</td>
							<td colspan="2" align="center">
								<input type="button" value="关 闭" id="btn2" class="l-button"
									onclick="cls()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>
</html>
