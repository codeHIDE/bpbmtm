<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>行业列表</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"></script>
		
		
		<style type="">
			tr{
				height:30px;
			}
		</style>
	<script type="text/javascript">
    	$(function(){
    		if(${requestScope.succ == 'succ'}){
    			alert("导入成功");
    		}else if(${requestScope.succ == 'fone'}){
    			alert("导入失败");
    		}else if(${requestScope.succ == 'fones'}){
    			alert("导入失败，请检查数据");
    		}
        });
           	 
        
           function sub()
        {
        	var excel = $("#excel").val();
        	if(excel == null || excel == ''){
        		alert("请选择文件");
        		return;
        	}
           $("#form1").submit();
        }
        
   
        
             function deleteC(){
       		 $.ligerDialog.confirm('是否要删除?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/mccCode!deleteCdateConfig.ac'/>',
				  {},
				  function(data){
				    if(data=="succ"){
			           alert('删除成功！');
				    }else{
			    	     alert('删除失败！');
				    }
				  });
		        	}
		        });
		        }
		        
    </script>
	</head>
	<body style="padding: 0px; overflow: hidden;"> 
		<form id="form1" action="<s:url value="/mccCode!addCdateConfig.ac"/>" method="post"  enctype="multipart/form-data">
			
		
			<div>
		  		<div id="toptoolbar"></div> 
  		</div>
  		<input type="file" name="excel" id="excel" />
			<input type="button" value="确认上传" id="btn" class="l-button" style="width:80px;" onclick = "sub()"/>
			<input type="button" value="删除" id="btn" class="l-button" style="width:80px;" onclick = "deleteC()"/>
  		</form>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
