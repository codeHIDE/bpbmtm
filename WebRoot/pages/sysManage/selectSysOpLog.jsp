<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>机构商户查询</title>
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
		<script src="<s:url value='/js/CheckLength.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
        $(function () {
            $("#maingrid4").ligerGrid({
            columns: [
           
             { display: '管理员ID', name: 'loginName', align: 'center', width: '15%' },
	        /*{ display: '平台商户名称(平台商户号)', name: 'null', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	if(rowdata.platMerInfo==null){
	            		return "";
	            	}
	            	return rowdata.platMerInfo.platMerName+"("+rowdata.platMerInfo.platMerId+")";
	            } },*/
            { display: '操作描述', name: 'opDesc', width: '12%', align: 'center' },     
            { display: '操作地址', name: 'opUrl', width: '12%', align: 'center' },
            { display: '操作时的IP', name: 'ip', width: '12%', align: 'center' }, 
            { display: '操作时的UA信息', name: 'ua', width: '12%', align: 'center' }, 
            { display: '操作时间', name: 'opTime', width: '12%', align: 'center' }, 
            { display: '备注', name: 'remark', width: '12%', align: 'center' }
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
		        pagesizeParmName:'pageSize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',
                url:'<s:url value="/sysManage!selectSysOpLog.ac"/>?loginName='+$("#loginName").val()+'&firstOpTime='+$("#firstOpTime").val()
                +'&lastOpTime='+$("#lastOpTime").val()+'&functionName='+$("#functionName").val()+'&op='+$("#op").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
            $("#lastOpTime").ligerDateEditor({ showTime: true});
            $("#firstOpTime").ligerDateEditor({ showTime: true});
        });
        

         function f_search()
        {
       		var firstOpTime=$("#firstOpTime").val();  
			if(!len("firstOpTime",20,"All","操作起始时间"))
				return false;
			form1.submit();
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#merShortName").val("");
        	$("#signPerson").val("");
        	$("#signDate").val("");
        	$("#createTime").val("");
        	$("#status").val("-1");
        	$("#platMerId").val("");
        	$("#tractId").val("-1");
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<input type="hidden" value="${param.op}" id="op"/>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/sysManage/selectSysOpLog.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							管理员ID：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="loginName" id="loginName" value="${param.loginName}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							操作起始时间：
						</td>
						<td style="text-align: left;">
							<input type="text" name="firstOpTime" id="firstOpTime" value="${param.firstOpTime}" />
						</td>
						<td style="text-align: right;width:100px;">
							操作结束时间：
						</td>
						<td style="text-align: left;">
							<input type="text" name="lastOpTime" id="lastOpTime" value="${param.lastOpTime}"/>
						</td>
						<td style="text-align: right;width:100px;">
							方法名：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="functionName" id="functionName" value="${param.functionName}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
