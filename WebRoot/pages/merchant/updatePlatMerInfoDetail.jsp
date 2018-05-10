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
	                   url: "<s:url value='/merchantInfo!updatePlatMerInfo.ac'/>",
	                   data: $('#form1').serialize(),
	                   success: function (data) {
	                      if(data=="succ"){
	                   	    $.ligerDialog.success('修改成功！');
	                      }else if(data=="fone"){
	                   	    $.ligerDialog.success('修改成功！');
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
				if(	len('platMerName',60,'All','平台商工商注册名') &&
					len('merRegNo',20,'N','工商注册号')	&&
					len('merTaxNo',20,'N','税务登记号')     	&&
					len('contactAddr',200,'All','联系地址') &&
					len('legalPerson',60,'EandS','法人代表') &&
					len('legalIdcard',20,'N','法人代表身份证号码') &&
					len('contactor',60,'EandS','联系人') &&
					len('contactPhone',20,'N','联系人电话')
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
	<body style="padding: 4px;">

		<div id="content">
			<s:if test="message!=null">
				<span><s:property value="message" /> </span>
			</s:if>
			<div class="box_system">
				<form id="form1" action="<s:url value='/merchantInfo!updateMerchantInfo.ac'/>" method="post"  enctype="multipart/form-data" >
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								平台商户号：
							</td>
							<td width="120px" class="l-table-edit-td">
								<input type="hidden" name="platMerInfo.platMerId" id="platMerId" value="<s:property value="platMerInfo.platMerId"/>"/>
								<s:property value="platMerInfo.platMerId"/>
							</td>
							<td width="120px" style="text-align: right;">
								平台商工商注册名：
							</td>
							<td width="120px" class="l-table-edit-td">
							<div class="l-text" style="width: 200px;">
								<input type="text" name="platMerInfo.platMerName" class="l-text-field" id="platMerName" value="<s:property value="platMerInfo.platMerName"/>"/>
							</div>	
							</td>
							
						</tr>
						<tr>
							<td style="text-align: right;">
								税务登记号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text"
									name="platMerInfo.merTaxNo" class="l-text-field"  id="merTaxNo" value="<s:property value="platMerInfo.merTaxNo"/>"/>
								</div>	
							</td>
							<td style="text-align: right;">
								工商注册号：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field"  name="platMerInfo.merRegNo" id="merRegNo" value="<s:property value="platMerInfo.merRegNo"/>"/>
							</div></td>
						</tr>
						
						<tr>
							<td width="150px" style="text-align: right;">
								法人姓名：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text" maxlength="5"  class="l-text-field"
									name="platMerInfo.legalPerson" id="legalPerson" value="<s:property value="platMerInfo.legalPerson"/>"/>
							</div></td>
							<td width="150px" style="text-align: right;">
								法人身份证号码：
							</td>
							<td style="text-align: left;"><div class="l-text" style="width: 200px;">
								<input type="text"  class="l-text-field"
									name="platMerInfo.legalIdcard" id="legalIdcard" value="<s:property value="platMerInfo.legalIdcard"/>"/>
							</div></td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td style="text-align: left;"><div class="l-text" style="width: 200px;">
								<input type="text"  maxlength="5" class="l-text-field"
									name="platMerInfo.contactor" id="contactor" value="<s:property value="platMerInfo.contactor"/>"/>
							</div></td>
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field"
									name="platMerInfo.contactPhone" id="contactPhone" value="<s:property value="platMerInfo.contactPhone"/>"/>
								</div></td>
						</tr>
						<tr><td style="text-align: right;">
								联系地址：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field"
									name="platMerInfo.contactAddr" id="contactAddr" value="<s:property value="platMerInfo.contactAddr"/>"/>
							</div></td>
							<td style="text-align: right;">
								联系邮箱：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text" class="l-text-field"
									name="platMerInfo.contactEmail" id="contactEmail" value="<s:property value="platMerInfo.contactEmail"/>"/>
							</div></td>
						</tr>
						<tr>
						<td style="text-align: right;">
								平台商户状态：
							</td>
							<td id="merStatus">
								<s:if test="platMerInfo.status==0">
									<span style="color: red">未审批</span>
								</s:if>
								<s:if test="platMerInfo.status==1">
									<span style="color: green">正在使用</span>
								</s:if>
								<s:if test="platMerInfo.status==2">暂停服务</s:if>
								<input type="hidden" name="platMerInfo.status"
									value="<s:property value="platMerInfo.status"/>"></input>
							</td>
							<td width="120px" style="text-align: right;">
								备注：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input class="l-text-field"  type="text" name="platMerInfo.remark" id="remark"  value="<s:property value="platMerInfo.remark"/>"/>
							</div></td> 
						</tr>
						<tr>
						<td width="120px" style="text-align: right;">色调：</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
								<select name="platMerInfo.color" id="color"  style="width:200px" class="l-text-field">
									<option id="-1">--请选择--</option>
									<s:iterator value="cssConfigList">
									<s:if test="cssId == platMerInfo.color">
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
							<td colspan="2" align="center">
								<input type="button" value="确认修改" id="btn" class="l-button"
									onclick="update()" />
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

	</body>
</html>
