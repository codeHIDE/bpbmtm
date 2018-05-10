<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户管理平台添加平台商户</title>
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
		<script type="text/javascript"><!--
        var eee;
        $(function (){
          if(${requestScope.addPlatMer=='success'}){
					alert("添加机构商户成功！");
				}else if(${requestScope.addPlatMer=='fouse'}){
					alert("添加机构商户失败！");
				}
            /*$.metadata.setType("attr", "validate");
            var v = $("form").validate({
                debug: true,
                errorPlacement: function (lable, element)
                {
                    if (element.hasClass("l-textarea"))
                    {
                        element.ligerTip({ content: lable.html(), target: element[0] }); 
                    }
                    else if (element.hasClass("l-text-field"))
                    {
                        element.parent().ligerTip({ content: lable.html(), target: element[0] });
                    }
                    else
                    {
                        lable.appendTo(element.parents("td:first").next("td"));
                    }
                },
                success: function (lable)
                {
                    lable.ligerHideTip();
                    lable.remove();
                },
                submitHandler: function ()
                {
                    $("form .l-text,.l-textarea").ligerHideTip();
                    alert("Submitted!")
                }
            });
            $("form").ligerForm();
            $(".l-button-test").click(function ()
            {
                alert(v.element($("#txtName")));
            });*/
        });  

			function check(){
				if(	len('platMerName',60,'All','平台商工商注册名') &&
					len('merRegNo',20,'N','工商注册号')	&&
					len('merTaxNo',20,'N','税务登记号')     	&&
					len('legalPerson',60,'EandS','法人姓名') &&
					len('legalIdcard',20,'N','法人身份证号码') &&
					len('contactor',60,'EandS','联系人') &&
					len('contactAddr',200,'All','联系地址') &&
					len('contactPhone',20,'N','联系人电话') 
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
				<form action="<s:url value="merchantInfo!addPlatMer.ac"/>" id="addPlatMer" method="post" name="form1" enctype="multipart/form-data" >
					<table width="80%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="120px" style="text-align: right;">
								平台商工商注册名：
							</td>
							<td width="120px" >
							<div class="l-text" style="width: 200px;">
								<input type="text" name="platMerInfo.platMerName" id="platMerName" style="width: 200px;" class="l-text-field" />
								</div>
							</td>
							<td style="text-align: right;">
								工商注册号：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" name="platMerInfo.merRegNo" id="merRegNo"  style="width: 200px;" class="l-text-field" />
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
									name="platMerInfo.merTaxNo" id="merTaxNo"  style="width: 200px;" class="l-text-field" />
									</div>
							</td>
						</tr>
						
						<tr>
							<td width="150px" style="text-align: right;">
								法人姓名：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" style="width: 200px;" class="l-text-field"
									name="platMerInfo.legalPerson" id="legalPerson" />
									</div>
							</td>
							<td width="150px" style="text-align: right;">
								法人身份证号码：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
								<input type="text"  style="width: 200px;" class="l-text-field"
									name="platMerInfo.legalIdcard" id="legalIdcard" />
									</div>
							</td>
						</tr>
						<tr>
							<td width="120px" style="text-align: right;">
								联系人：
							</td>
							<td style="text-align: left;">
							<div class="l-text" style="width: 200px;">
								<input type="text"  style="width: 200px;" class="l-text-field"
									name="platMerInfo.contactor" id="contactor" />
									</div>
							</td>
							<td width="150px" style="text-align: right;">
								联系人电话：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text"  style="width: 200px;" class="l-text-field"
									name="platMerInfo.contactPhone" id="contactPhone" />
									</div>
							</td>
						</tr>
						<tr><td style="text-align: right;">
								联系地址：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" style="width: 200px;" class="l-text-field"
									name="platMerInfo.contactAddr" id="contactAddr" />
									</div>
							</td>
							<td style="text-align: right;">
								联系邮箱：
							</td>
							<td><div class="l-text" style="width: 200px;">
								<input type="text" style="width: 200px;" class="l-text-field"
									name="platMerInfo.contactEmail" id="contactEmail" />
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
								备注：
							</td>
							<td>
							<div class="l-text" style="width: 200px;">
								<input type="text" name="merchantInfo.remark" style="width: 200px;" class="l-text-field" id="remark"  />
								</div>
							</td> 
							<td style="text-align: right;">色调：</td>
							<td style="text-align: right;">
							<div class="l-text" style="width: 200px;">
								<select name="platMerInfo.color" id="color"  style="width:200px" class="l-text-field">
									<option id="-1">--请选择--</option>
									<s:iterator value="cssConfigList">
											<option value="<s:property value="cssId"/>">
												 <s:property value="cssName"/>
											</option>
									</s:iterator>
								</select>
								</div>
							</td>
						</tr>
						<tr>
						<td></td>
						<td></td>
							<td style="text-align: center">
							<div style="width: 37%">
								<input type="button" value="确认申请" id="btn" onclick="check()" style="width:80px;"/>
							</div>
							</td>
						</tr>
					</table>
					
				</form>
			</div>
		</div>
	</body>
</html>
