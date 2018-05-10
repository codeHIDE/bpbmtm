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
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
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
        function f_open()
        {
       	 	if(	 len("loginName",20,"EN","授权人ID") && len("levelPwd",20,"EN","级别确认密码")){
					$.ajax({
		                   type: "POST",
		                   dataType: "html",
		                   url: '<s:url value="/accountManage!checkInfo.ac"/>',
		                   data: $('#form1').serialize(),
		                   success: function (data) {
		                      if(data=="1"){
		                    	    window.parent.$.ligerDialog.close();
		                      	   parent.$.ligerDialog.open({ 
		                      	   	url: '<s:url value="/accountManage!toUpdateAccountManageDetail.ac"/>?merSysId='+$("#mid").val()
						           +'&settleDate='+$("#settleDate").val(),
						           height:250,width: 400, isResize: false,title:'修改信息'});      
		                      } else if(data=="2") {
		                      	  alert("授权人ID或密码错误！");
		                      } 
		                     // window.parent.$.ligerDialog.closeWaitting();
			                 // dialogClose();
			          //       $("#btn").show();
			              //   window.parent.location.reload();
		                   }
		                });
			}
        }
		</script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="content">
			<div class="box_system">
			<form id="form1" method="post">
				<input type="hidden" id="mid" value="${param.mid}"/>
				<input type="hidden" id="settleDate" value="${param.settleDate}"/>
				<div style="float:left;padding-left:52px;">授权人ID：</div>
				<div class="l-text" style="width: 150px;float:left;" >
				<input id="loginName" name="loginName" style="width:150px;" class="l-text-field"/>
				</div>
				<div style="float:left;padding-left:30px;padding-top:15px">级别确认密码：</div>
				<div class="l-text" style="width: 150px;float:left;margin-top:15px" >
				<input id="levelPwd" name="levelPwd" type="password" style="width:150px;" class="l-text-field"/>
				</div>
				<input type="button" value="确定" onclick="f_open()" style="margin-left:100px;margin-top:10px" class="l-button"/>
			</form>
			</div>
		</div>
	</body>
</html>
