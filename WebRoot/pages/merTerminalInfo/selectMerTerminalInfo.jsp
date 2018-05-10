<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>通道列表</title>
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
		<style type="">
			tr{
				height:30px;
			}
		</style>
	<script type="text/javascript">
    	$(function(){
            $("#maingrid4").ligerGrid({
                columns: [
            { display: '机构号', name: 'merSysId', align: 'left', width: '9%' ,align: 'center' },
            { display: '设备代码', name: 'terminalCode', width: '10%', align: 'center' },
            { display: '设备类别', name: 'category', width: '5%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.category=='04'){return '刷卡';}
            	else if(rowdata.category=='08'){return 'POS';}
            } },
            { display: '设备系统信息', name: 'terminalSysterm', width: '10%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.terminalSysterm=='01'){return 'ANDROID';}
            	else if(rowdata.terminalSysterm=='02'){return 'IOS';}
            	else if(rowdata.terminalSysterm=='03'){return 'WIN';}
			  }},
            { display: '更新类型', name: 'updateType', width: '6%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.updateType=='00'){return '不更新';}
            	else if(rowdata.updateType=='01'){return '强制更新';}
            	else if(rowdata.updateType=='02'){return '非强制更新';}
            } },
 			{ display: '版本号', name: 'version', width: '6%', align: 'center' }, 
              { display: '版本信息描述', name: 'versionDesc', width: '10%', align: 'center' }, 
              { display: '更新地址', name: 'updatePath', width: '13%', align: 'center' },
              { display: '状态', name: 'status', width: '4%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.status=='0'){return '不可用';}
            	else if(rowdata.status=='1'){return '可用';}
			  }},
			  { display: '文件名称', name: 'fileName', width: '10%', align: 'center' }, 
			  { display: '入网时间', name: 'createTime', width: '7%', align: 'center' }, 
			  { display: '操作', name: 'null', width: '7%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
	                    	if(rowdata.status=='0'){
	                    		h += "<a onclick='f_open3("+rowdata.id+")' style='cursor:pointer;color:blue;'>启用</a> ";
	                    	}if(rowdata.status=='1'){
	                    		h += "<a onclick='f_open3("+rowdata.id+")' style='cursor:pointer;color:blue;'>暂停</a> ";
	                    	}
           				    h += "<a onclick='f_open4("+rowdata.id+")' style='cursor:pointer;color:blue;'>编辑</a> ";
                    }		
                    return h;
                } },
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
                url:'<s:url value="/merTerminalInfo!selectMerTerminalInfoAllList.ac"/>?merSysId='+$("#merSysId").val()+
                "&category="+$("#category").val()
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
        });
        
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
   
   		function f_open3(id){
   			$.ligerDialog.open({ url: '<s:url value="/merTerminalInfo!selectMerTerminalInfoOne.ac"/>?merTerminalInfo.id='+id,
            					 height:300,width: 580, isResize: false,title:'机构终端详情'});
   		}
   		
   		function f_open4(id){
   			$.ligerDialog.open({ url: '<s:url value="/merTerminalInfo!selectMerTerminalInfoOneUpdate.ac"/>?merTerminalInfo.id='+id,
            					 height:350,width: 580, isResize: false,title:'机构终端编辑'});
   		}
   		
        function f_search()
        {
           $("#form1").submit();
        }
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#category").val("-1");
        }
       
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/merTerminalInfo/selectMerTerminalInfo.jsp"/>" method="post">
				<table width="80%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							机构号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="merSysId" id="merSysId"
								value="${param.merSysId}" />
								</div>
						</td>
						<td style="text-align: right;">
							设备类别：
						</td>
						<td>
							<select name="category"  id="category">
								<option value="-1">
									--请选择--
								</option>
								<option value="04" <c:if test="${param.category=='04'}">selected="selected"</c:if>>
									刷卡器
								</option>
									<option value="08" <c:if test="${param.category=='08'}">selected="selected"</c:if>>
									POS机
								</option>
							</select>
							</td>
						<td style="text-align: left"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
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
