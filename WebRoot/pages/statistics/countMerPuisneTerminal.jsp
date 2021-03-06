<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>系统管理平台-机构商号下属终端统计</title>
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
		<script src="<s:url value='/js/common.js'/>" type="text/javascript"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
		var grid;
        $(function (){
           grid= $("#maingrid4").ligerGrid({
            columns: [
        	 { display: '机构商号', name: 'merSysId', align: 'center', width: '20%' },
             { display: '终端总数', name: 'countTsn', align: 'center', width: '20%'},
             { display: '已开通数', name: 'countValid', width: '20%', align: 'center'}
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
                parms:{
                	merSysId:$("#merSysId").val()
                },
                url:'<s:url value="/statistics!getCountMerPusineTer.ac"/>'
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
        });
        
        function f_search()
        {
			$("#form1").attr("target", '_self');
			$("#form1").attr("action", '<s:url value="/pages/statistics/countMerPuisneTerminal.jsp"/>');
			$("#form1").submit();        	 
        }
    </script>
	</head>
	<body style="padding: 4px;">
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
		<form id="form1" action="<s:url value="/pages/statistics/countMerPuisneTerminal.jsp" />" method="post">
				<table width="80%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							机构商号：
						</td>
						<td style="text-align: left;"> 
							<div style="width: 130px;float: left;"> 
								<input type="text" name="merSysId" id="merSysId"  value="${param.merSysId}"/>
							</div>
						</td>
						<td style="text-align: right;">
							<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0;margin-top: 10px"></div>
		<div style="display: none;">
		</div>
	</body>
</html>
