﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>渠道费率管理</title>
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
            { display: '费率名称', name: 'feeTypeName', align: 'center', width: '8%'},
            { display: '费率类型', name: 'feeType', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.feeType==1){return '点数';}
            	else if(rowdata.feeType==2){return '封顶';}
            	} 
            },  
            { display: '费率点数', name: 'feeRate', width: '8%', align: 'center' },	
            { display: '封顶金额', name: 'topFee', width: '8%', align: 'center' },	
            { display: '开通日期', name: 'lastUpdTs', width: '8%', align: 'center' }
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
                url:'<s:url value="/channel!selectChannelFee.ac"/>?channelFeeType.feeTypeName='+$("#feeTypeName").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
        });
        

         function f_search()
        {
        /* 	if(len("merSysId",20,"N","机构商号")) */
				  $("#form1").submit();
			/* return false; */
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#merShortName").val("");
        	$("#signPerson").val("");
        	$("#signDate").val("");
        	$("#createTime").val("");
        	$("#status").val("-1");
        	/* $("#platMerId").val(""); */
        	$("#tractId").val("-1");
        	/* $("#level").val("1"); */
        }
        
         function f_add()
        {
        $.ligerDialog.open({ url: '<s:url value="/pages/channel/addChannelFee.jsp"/>',
	           	 height:400,width: 500, isResize: false,title:'渠道添加'});    
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/channel/selectChannelFee.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							费率名称：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="feeTypeName"  id="feeTypeName" value="${param.feeTypeName}" class="l-text-field" />
							</div>
						</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
    						<td style="text-align: left;"><input id="btn" type="button" value="添 加" onclick="f_add()" class="l-button"/></td>

							<td style="text-align: left;"><br /></td>
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
