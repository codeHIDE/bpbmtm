<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>子商户终端号列表</title>
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
	            { display: 'TSN', name: 'tsn', align: 'left', width: '35%' ,align: 'center' },
	            { display: '子商户终端类型', name: 'category', width: '35%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.category=='04'){return '自有终端';}
	            	else if(rowdata.category=='08'){return '银联终端';}
	            } },
	            { display: '操作', name: 'null', width: '22%', align: 'center',render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                   		 h += "<a onclick=\"goto('"+rowdata.id+"')\"  style='cursor:pointer;color:blue;'> 密码重置 </a> ";
                   		 h += "<a onclick=\"updateTerminalSerialNumberStatus('"+rowdata.id+"')\"  style='cursor:pointer;color:blue;'> 状态重置 rowdata.terminalSerialNumber</a> ";
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
                url:'<s:url value="/subMerInfo!selectSubMerTerminal.ac"/>?id=${param.id}'
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("select[name='rp']").css("display","none");
        });
         function goto(terminalSerialNumber){
       		 window.parent.$.ligerDialog.confirm('是否要重置密码?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/subMerInfo!updateTerminalpePass.ac'/>',
				  {
				    id:terminalSerialNumber
				  },
				  function(data){
				    if(data=="succ"){
				    	window.parent.$.ligerDialog.success('密码重置成功    新密码为：123456');
				    }else{
				    	window.parent.$.ligerDialog.error('密码重置失败！');
				    }
				  });
        	}
        });
        }
        function updateTerminalSerialNumberStatus(terminalSerialNumber){
       		 window.parent.$.ligerDialog.confirm('是否要重置终端状态?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/subMerInfo!updateTerminalStatus.ac'/>',
				  {
				    id:terminalSerialNumber
				  },
				  function(data){
				    if(data=="succ"){
				    	window.parent.$.ligerDialog.success('重置终端成功！');
				    }else{
				    	window.parent.$.ligerDialog.error('重置状态失败！');
				    }
				  });
        	}
        });
        }
        
        
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
