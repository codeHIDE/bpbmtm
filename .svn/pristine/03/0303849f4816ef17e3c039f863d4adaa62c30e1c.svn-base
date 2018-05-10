<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>业务列表</title>
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
    	
    	//topMenuBar
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
           
           	 ]}
           	 );
           	 
           	    function f_search()
        {
           $("#form1").submit();
        }
           	 
            $("#maingrid4").ligerGrid({
                columns: [
            { display: '业务ID', name: 'busId', align: 'left', width: '8%' ,align: 'center' },
           { display: '业务名称', name: 'busName', width: '12%' ,align: 'center' },
            { display: '业务描述', name: 'busDesc', width: '12%', align: 'center' },
            { display: '备注', name: 'remark', width: '12%', align: 'center' }, 
            { display: '类型', name: 'type', width: '12%', align: 'center' }, 
           { display: '操作', name: 'null', width: '10%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
           	 		var h = "";
           	 		<c:if test="${purview['8071'] == '8071'}">
           	 			h = "<a onclick='delectBusType("+rowdata.busId+")' style='cursor:pointer;color:blue;'>删除</a> ";
           	 		</c:if>
                    return h;
                }}
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
                url:'<s:url value="/busType!selectBusTypeList.ac"/>'                           
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
        
        
        
   
           function addWindow(){
            $.ligerDialog.open({ url: '<s:url value="/busType!goToInsertBusType.ac"/>',
            	 height:220,width: 420, isResize: false,title:'业务添加'});    
        }
        
             function delectBusType(busId){
       		 $.ligerDialog.confirm('是否要删除?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/busType!delectBus.ac'/>',
				  {
				  		 busId:busId
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
				   // window.parent.$.ligerDialog.closeWaitting(); 
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
	<form id="form1" action="<s:url value="/pages/codePage/busTypeList.jsp"/>" method="post">
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
