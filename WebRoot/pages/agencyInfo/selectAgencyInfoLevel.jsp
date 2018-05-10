<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>二级代理商列表</title>
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
            $("#maingrid4").ligerGrid({
         	  columns: [
	            { 
	            	display: '代理商户号', name: 'agentId', width: '39%', align: 'center' 
	            },
           		{ 
           			display: '代理商户名', name: 'agentName', width: '39%', align: 'center' 
	            },
	            { 
	            	display: '操作', name: 'null', width: '15%', align: 'center' ,render: function (rowdata, rowindex, value)
	           	 	{
	                     var h = "";
	                  if (!rowdata._editing)
	                    {
	                    	if(rowdata.status=='0'){
	                    		h += "<a onclick='f_open3("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>上线</a> ";
	                    	}if(rowdata.status=='1'){
	                    		h += "<a onclick='f_open3("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>暂停</a> ";
	                    	}if(rowdata.status=='2'){
	                    		h += "<a onclick='f_open3("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>恢复</a> ";
	                    	}
	                    }
	                    return h;
	                }
                }
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
                url:'<s:url value="/agencyInfo!selectLevel2.ac"/>?agenctInfo.agentId=${param.id}'
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("select[name='rp']").css("display","none");
        });
        
        function f_open3(agentId)
        {
            $.ligerDialog.open({ url: '<s:url value="/agencyInfo!agencyDetailOnline.ac"/>?agenctInfo.agentId='+agentId,
            					 height:350,width: 650, isResize: false,title:'代理商详情'}); 
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0;">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
