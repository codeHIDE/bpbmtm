<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>规格列表</title>
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
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
  
        $(function (){
	        $("#toptoolbar").ligerToolBar({ items: [
	            {
	               text: '增加', click: function (item)
	               {
	                   addWindow('${param.id}');
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
	            { display: '规格名称', name: 'specName', align: 'left', width: '15%' ,align: 'center' },
	            { display: '规格价格', name: 'waresPrice', align: 'left', width: '15%' ,align: 'center' },
	            { display: '规格显示价格', name: 'waresShowPrice', align: 'left', width: '15%' ,align: 'center' },
	            { display: '规格关联用户级别', name: 'userType', align: 'left', width: '15%' ,align: 'center' },
	            { display: '创建时间', name: 'createTime', align: 'center', width: '15%'},
	            { display: '操作', name: 'null', width: '22%', align: 'center',render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    return h;
                } } 
                ], 
                pageSizeOptions: false , 
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 15,                        
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',    
                url:'<s:url value="/wares!selectSpec.ac"/>?waresSpecInfo.waresId=${param.id}'
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("select[name='rp']").css("display","none");
        });
        
        function addWindow(id){
	          $.ligerDialog.open({ url: '<s:url value="/wares!addspecPage.ac"/>?waresSpecInfo.waresId='+id,
	           	 height:400,width: 500, isResize: false,title:'商品添加'});    
         }
         
         
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<form id="form1" action="<s:url value="/pages/wares/selectSpec.jsp" />?id=${param.id}" method="post">
				<table width="80%">
				</table>
			</form>
		<div id="toptoolbar"></div> 
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<input type="hidden" name="waresSpecInfo.waresId" id="waresId"  value="${param.id}"/>
				<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
