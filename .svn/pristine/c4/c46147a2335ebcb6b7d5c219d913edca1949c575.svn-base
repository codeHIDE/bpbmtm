<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title>
</title>
    <link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" /> 
    <link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css" rel="stylesheet'/>" type="text/css" /> 
    <script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js" type="text/javascript'/>"></script>
     <script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerForm.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerComboBox.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerCheckBox.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerButton.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerRadio.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerSpinner.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerTextBox.js'/>" type="text/javascript"></script> 
    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerTip.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/jquery-validation/jquery.validate.min.js'/>" type="text/javascript"></script> 
    <script src="<s:url value='/js/jqui/jquery-validation/jquery.metadata.js'/>" type="text/javascript"></script>
    <script src="<s:url value='/js/jqui/jquery-validation/messages_cn.js'/>" type="text/javascript"></script>

    <script type="text/javascript">
        var eee;
        $(function ()
        {
            $.metadata.setType("attr", "validate");
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
            });
        });  
    </script>
    <style type="text/css">
           body{ font-size:12px;}
        .l-table-edit {}
        .l-table-edit-td{ padding:4px;}
        .l-button-submit,.l-button-test{width:80px; float:left; margin-left:10px; padding-bottom:2px;}
        .l-verify-tip{ left:230px; top:120px;}
    </style>

</head>

<body style="padding:10px">
	<s:if test="message!=null">
		<span><s:property value="message" /> </span>
	</s:if>
    <form name="form1" method="post" action="<s:url value="merchantInfo!addMerchant.ac"/>" id="addMerchant" enctype="multipart/form-data">
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
            <tr>
                <td align="right" class="l-table-edit-td">机构工商注册名:</td>
                <td align="left" class="l-table-edit-td"><input name="merchantInfo.merName" type="text" id="merName" ltype="text" validate="{required:true,minlength:3,maxlength:10}" /></td>
                <td align="left" style="width:180px"></td>
                <td align="right" class="l-table-edit-td" valign="top">机构工商注册号:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="merchantInfo.merRegno" type="text" id="merRegno" ltype="text" validate="{minlength:3,maxlength:10}" />
                </td><td align="left"></td>
            </tr>   
             <tr>
             	<td align="right" class="l-table-edit-td" valign="top">组织机构代码号:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="merchantInfo.orgCodeCer" type="text" id="orgCodeCer" ltype="text" validate="{minlength:3,maxlength:10}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">税务登记号:</td>
                <td align="left" class="l-table-edit-td"><input name="merchantInfo.merTaxno" type="text" id="merTaxno" ltype="text" validate="{minlength:3,maxlength:10}" /></td>
                <td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">法人代表:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.merLegalPerson" type="text" id="merLegalPerson" ltype="text" validate="{required:true,minlength:3,maxlength:20}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">法人身份证号码:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.merLegalIdcard" type="text" id="merLegalIdcard" ltype="text" validate="{required:true,minlength:3,maxlength:20}" />
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td" valign="top">工商注册地址:</td>
                <td align="left" class="l-table-edit-td" colspan="3">
                     <textarea cols="100" rows="1" name="merchantInfo.merRegaddr" class="l-textarea" id="merRegaddr" style="width:400px" validate="{required:true}"></textarea>      
                </td><td align="left"></td>
            </tr>  
                 
            <tr>
                <td align="right" class="l-table-edit-td">联系地址:</td>
                <td align="left" class="l-table-edit-td" colspan="3">
                	<textarea cols="100" rows="1" name="merchantInfo.contracterAddr" class="l-textarea" id="contracterAddr" style="width:400px"></textarea>
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">对外经营名称:</td>
                <td align="left" class="l-table-edit-td">
                    <input name="merchantInfo.merShortName" type="text" id="merShortName" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">机构商户性质:</td>
                <td align="left" class="l-table-edit-td">
                <select name="merchantInfo.merKind" id="merKind" ltype="select" validate="{required:true}">
					<option value="">---请选择---</option>
					<option value="国有企业">国有企业</option>
					<option value="集体企业">集体企业</option>
					<option value="联营企业">联营企业</option>
					<option value="股份合作制">股份合作制</option>
					<option value="股份有限公司">股份有限公司</option>
					<option value="有限责任公司">有限责任公司</option>
					<option value="合资企业">合资企业</option>
					<option value="个体户">个体户</option>
					<option value="私营企业">私营企业</option>
				</select>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">业务联系人:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.contracter" type="text" id="contracter" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">联系人电话:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.contracterPhone" type="text" id="contracterPhone" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td><td align="left"></td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">收单机构名称:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.acqAgencyName" type="text" id="acqAgencyName" ltype="text" validate="{minlength:3,maxlength:10}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">结算机构名称:</td>
                <td align="left" class="l-table-edit-td">
                	<select name="merchantInfo.settAgencyName" id="settAgencyName" ltype="select">
							<option value="-1">---请选择---</option>
							<s:iterator value="bankBehalfBranchList" var="bank">
								<option value="<s:property value="#bank.bankLineNumber" />"><s:property value="#bank.bankName" /></option>
							</s:iterator>
					</select>
                </td>
            </tr>
            <tr>
                <td align="right" class="l-table-edit-td">结算账户名:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.settAccountName" type="text" id="settAccountName" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">结算账户号:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.settAccountNo" type="text" id="settAccountNo" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td><td align="left"></td>
            </tr>
            
            <tr>
                <td align="right" class="l-table-edit-td">签约人:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.signPerson" type="text" id="signPerson" ltype="text" validate="{required:true,minlength:3,maxlength:10}" />
                </td><td align="left"></td>
                <td align="right" class="l-table-edit-td">签约日期:</td>
                <td align="left" class="l-table-edit-td"> 
                <input name="merchantInfo.signDate" type="text" id="signDate" ltype="date" validate="{required:true}" />
                </td><td align="left"></td>
            </tr>
        </table>
 <br />
<input type="submit" value="确认申请" id="Button1" class="l-button l-button-submit" /> 
    </form>
    <div style="display:none">
    <!--  数据统计代码 --></div>

    
</body>
</html>