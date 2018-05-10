<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>路由规则列表</title>
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
            { text: '增加', click: function (item)
               {
                   addWindow();
               }, icon:'add'},
              { text: '查询', click: function (item)
               {
                   f_search();
               }, icon: 'search2'}
           	 ] }
           	 
           	 );
           	 
            $("#maingrid4").ligerGrid({
                columns: [
           { display: 'ID', name: 'id', width: '12%' ,align: 'center' },
            { display: '起始时间', name: 'startTime', width: '12%', align: 'center' },
            { display: '结束时间', name: 'endTime', width: '12%', align: 'center' },
            { display: '封顶夜间通道', name: 'tractHight', width: '12%', align: 'center' },
            { display: '扣率夜间通道', name: 'tractDay', width: '12%', align: 'center' },
            { display: '状态', name: 'status', width: '8%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
           	 		var str = null;
              		 if(rowdata.status == '0'){
              			str = "暂停";
              		 }else{
              		 	str = "启用";
              		 }
                    return str;
                }}, 
            { display: '规则描述', name: 'reserved1', width: '12%', align: 'center' }, 
            { display: '保留域2', name: 'reserved2', width: '12%', align: 'center' }, 
            { display: '保留域3', name: 'reserved3', width: '12%', align: 'center' }, 
           { display: '操作', name: 'null', width: '10%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
           	 		 var str = null;
           	 		 var h = "";
           	 		 <c:if test="${purview['5061'] == '5061'}">
              			 h = "<a onclick='delectMccCode("+rowdata.id+")' style='cursor:pointer;color:blue;'>删除</a> ";
              		 </c:if> 
              		 <c:if test="${purview['5062'] == '5062'}">
	              		 if(rowdata.status == '0'){
	              			str = "启用";
	              		 }else{
	              		 	str = "暂停";
	              		 }
	              		 h+= "<a onclick='index("+rowdata.id+","+rowdata.status+")' style='cursor:pointer;color:blue;'>"+str+"</a> ";
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
                url:'<s:url value="/tractInfo!selectRulesAllList.ac"/>'                           
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
        
           function f_search()
        {
           $("#form1").submit();
        }
        
   
           function addWindow(){
            $.ligerDialog.open({ url: '<s:url value="/tractInfo!insertRulesAllInit.ac"/>',
            	 height:280,width: 620, isResize: false,title:'全局路由添加'});    
       		 }
        
        	function index(id,status){
        		var status1 = 0;
        		var str = null;
        		if(status == '0'){
        			status1 = 1;
        			str = "启用";
        		}else{
        			status1 = 0;
        			str = "暂停";
        		}
        		$.ligerDialog.confirm('是否要'+str+'?', function (yes) {
        			if(yes){
        				$.post('<s:url value='/tractInfo!indexRulesAll.ac'/>',
				  		{'rulesAll.id':id,'rulesAll.status':status1},
				  		function(data){
				    		if(data=="succ"){
			           			alert(str+'成功！');
				    			location.reload();
			                	dialogClose();
				    		}else{
				    	     	alert(str+'失败！');
				   				// window.parent.$.ligerDialog.closeWaitting(); 
			    				location.reload();
		                		dialogClose();
				    		}
				  		});
					}
	   			 });
	        	}
             function delectMccCode(id){
       		 $.ligerDialog.confirm('是否要删除?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/tractInfo!delectRulesAll.ac'/>',
				  {
				  		 'rulesAll.id':id
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
		<form id="form1" action="<s:url value="/pages/tract/rulesAllList.jsp"/>" method="post">
				<table width="80%">
				</table>
			</form>
			<div>
		  		<div id="toptoolbar"></div> 
  		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
