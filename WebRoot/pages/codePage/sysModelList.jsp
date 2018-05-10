<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统权限列表</title>
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
        	$("#toptoolbar").ligerToolBar({ items: [
            {
               text: '增加', click: function (item)
               {
                   addWindow();
               }, icon:'add'},
                 { text: '查询', click: function (item)
               {
                   f_search();
               }, icon: 'search2'}
           	 ]
           	 });
           	 
           	 	   function f_search()
        {
           $("#form1").submit();
        }
           	 
            $("#maingrid4").ligerGrid({
                columns: [
            		{ display: 'ID', name: 'id', align: 'left', width: '8%' ,align: 'center' },
          		    { display: '模块名', name: 'modelName', width: '10%' ,align: 'center' },
                    { display: '模块编号', name: 'modelNo', width: '8%', align: 'center' },
                    { display: '访问地址', name: 'path', width: '20%', align: 'center' }, 
	   		        { display: '描述', name: 'desc', width: '20%', align: 'center' }, 
                    { display: '备注', name: 'remark', width: '10%', align: 'center' }, 
                    { display: '上级编号', name: 'superNo', width: '10%', align: 'center' }, 
                    { display: '操作', name: 'null', width: '10%', align: 'center' ,render: function (rowdata, rowindex, value)
	           	 		{
	           	 			var h = "";
		           	 		<c:if test="${purview['8051'] == '8051'}">
		              		 	h = "<a onclick='delectSysModel("+rowdata.id+")' style='cursor:pointer;color:blue;'>删除</a> ";
		              		</c:if>
		              		<c:if test="${purview['8052'] == '8052'}">
	              		 		h+= "<a onclick='uptSysModel("+rowdata.id+")' style='cursor:pointer;color:blue;'>修改</a>"
	              		 	</c:if>
	                    	return h;
	                	}  
	                }
               ],  
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 15,    
                pageSizeOptions: [15],                    
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',    
                url:'<s:url value="/sysModel!getSysModelList.ac"/>'                           
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
         function addWindow(){
	           $.ligerDialog.open({ url: '<s:url value="/pages/codePage/addSysModel.jsp"/>',
	           	 height:300,width: 420, isResize: false,title:'系统模块添加'});    
         }
         function uptSysModel(id){
	           $.ligerDialog.open({ url: '<s:url value="/sysModel!getSysModelById.ac"/>?id='+id,
	           	 height:300,width: 420, isResize: false,title:'系统模块修改'});    
         }
         function delectSysModel(id){
       		 	$.ligerDialog.confirm('是否要删除?', function (yes) {
        			if(yes){
        				$.post('<s:url value='/sysModel!delSysModel.ac'/>',
				 		 {
				  			 'sysModel.id':id
				 		 },
				 		 function(data){
				   			 if(data=="succ"){
			  			  		//   $.ligerDialog.success('删除成功！');
			         			  alert('删除成功！');
				 				  // window.parent.$.ligerDialog.closeWaitting(); 
				    			location.reload();
			                	dialogClose();
				  		  }else{
				    	     alert('删除失败！');
				    		 location.reload();
			                 dialogClose();
				   		 }
				  });
        	}
        });
        }
        
    </script>
	</head>
	<body style="padding: 0px; overflow: hidden;"> 
	<form id="form1" action="<s:url value="/pages/codePage/sysModelList.jsp"/>" method="post">
				<table width="80%">
				</table>
			</form>
  		<div id="toptoolbar"></div> 
  		
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
