<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
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
             { display: '机构商户名称(机构商户号)', name: 'merName', align: 'center', width: '30%',render:function(rowdata,rowindex){
	            	 return rowdata.merName+"("+rowdata.merSysId+")";
	            } },
            { display: '机构商户状态', name: 'status', width: '12%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.status==0){return '未审批';}
            	else if(rowdata.status==1){return '已审批';}
            	else if(rowdata.status==2){return '已上线';}
            	else if(rowdata.status==3){return '暂停';}
            	else if(rowdata.status==4){return '黑名单';}
            	} 
            },     
            { display: '入网时间', name: 'createTime', width: '20%', align: 'center' },
            { display: '联系人', name: 'contactor', width: '18%', align: 'center' }, 
            { display: '联系电话', name: 'contactorPhone', width: '18%', align: 'center' }
                ], 
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 10,
                 pageSizeOptions: [10],                         
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page1',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize1',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',    
                url:'<s:url value="/merchantInfo!selectPlatList.ac"/>?platMerInfo.platMerId='+$("#platMerId").val()
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">	
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
		<input type="hidden" name="platMerId" value="<s:property value="platMerInfo.platMerId"/>" id="platMerId"/>
	</body>
</html>
