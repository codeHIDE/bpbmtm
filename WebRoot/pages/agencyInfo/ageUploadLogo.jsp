<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户管理平台添加机构商户</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascr" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script type="text/javascript" src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script type="text/javascript" src="<s:url value='/js/common.js'/>"></script>
		<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>"></script>
		<script type="text/javascript">
		function merLogo(){
			$.ajaxFileUpload({
                 url:'agencyInfo!toDoMerLogo.ac?agenctInfo.agentId='+$("#agentId").val(),             //需要链接到服务器地址
                 secureuri:false,
                 fileElementId:['agelogo','sublogo'],                         //文件选择框的id属性
                 dataType: 'text',                                     //服务器返回的格式，可以是json
                 success: function (data, status)             //相当于java中try语句块的用法
                 {   
                 	if(data==""||data==null){
                 		alert('上传图片失败!');
                 	}else{
                 		//不同的浏览器返回的数据会有头文件，将其截取掉
	                 	var start = data.indexOf(">");
                      	if (start != -1) {
                          	var end = data.indexOf("<", start + 1);
                          	if (end != -1) {
                             	 data = data.substring(start + 1, end);
                         	}
                     	 }
                     	 if(data == "agesucc" ){
                     	 	window.parent.$.ligerDialog.success('代理商户LOGO上传成功!');
                     	 }else if(data == "ageerr"){
                     	 	window.parent.$.ligerDialog.error('代理商户LOGO上传失败！');
                     	 }else if(data == "subsucc" ){
                     	 	window.parent.$.ligerDialog.success('子商户LOGO上传成功!');
                     	 }else if(data == "suberr"){
                     	 	window.parent.$.ligerDialog.error('子商户LOGO上传失败！');
                     	 }else if(data == "agesuccsubsucc"){//同时上传两张图片，Action会向data叠加第二次值
                     	 	window.parent.$.ligerDialog.success('LOGO上传成功！');
                     	 }else{
                     		window.parent.$.ligerDialog.error('LOGO上传失败！');
                     	 }
	                    
                 	}
                 	
                 },
                 error: function (data, status, e)             //相当于java中catch语句块的用法
                 {
                    alert('上传图片失败!');
                 }
               }
             );
		}
		
		
			function cls(){
	            window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove(); 
			}
			</script>
		
	</head>
	<body style="padding: 4px; overflow: hidden;">
			<div >
				<form action="<s:url value="/merchantInfo!toDoMerLogo.ac"/>" id="merLogo" method="post"   enctype="multipart/form-data" >
					<table width="98%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td style="width: 200px; text-align: right" >
								代理商LOGO：
							</td>
							<td style="width: 200px;">
								<input type="file" name="agefile" id="agelogo" />
								<input type="hidden" name="agenctInfo.agentId"  id="agentId" value="<s:property value="agenctInfo.agentId"/>"/>
							</td>
						</tr>
						<!-- 
						<tr>
							<td style="width: 200px; text-align: right" >
								子商户LOGO：
							</td>
							<td>
								<input type="file" name="subfile" id="sublogo" />
							</td>
						</tr>
						 -->
						<tr>
							<td align="center" >
								<input type="button" value="确认上传" id="btn" class="l-button" style="width:120px;" onclick = "merLogo()"/>
							</td>
							<td  align="center">
								<input type="button" value="关 闭"  id="btn2" style="width:120px;" class="l-button" onclick="cls()" />
							</td>
						</tr>
					</table>
				</form>
			</div>
	</body>
</html>
