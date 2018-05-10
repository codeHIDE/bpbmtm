<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>会员等级设置</title>
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
            { display: '级别编号', name: 'userTypeId', align: 'center', width: '10%'},
            { display: '级别名称', name: 'typeName', align: 'center', width: '10%'},
            { display: '交易类型', name: 'transType', align: 'center', width: '8%'},
	        { display: '交易手续费', name: 'transFee', align: 'center', width: '8%'},
	        { display: '成本费率', name: 'costFee', align: 'center', width: '8%'},
            { display: '操作', name: 'null', width: '28%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
               
               		/* <c:if test="${purview['6015'] == '6015'}">
               			h += "<a onclick='f_open6("+rowdata.id+","+3+")' style='cursor:pointer;color:blue;'>清空一级代理 </a> ";
                    </c:if>
                    <c:if test="${purview['6017'] == '6017'}">
               			h += "<a onclick='f_open6("+rowdata.id+","+4+")' style='cursor:pointer;color:blue;'>清空二级代理 </a> ";
                    </c:if> */
               
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
                url:'<s:url value="/member!selectUserType.ac"/>?userType.userTypeId='+$("#userTypeId").val()
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
        
         function f_add()
        {
        $.ligerDialog.open({ url: '<s:url value="/pages/member/addUserType.jsp"/>',
	           	 height:300,width: 420, isResize: false,title:'级别添加'});    
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/member/selectUserType.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							级别编号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="userTypeId" id="userTypeId" value="${param.userTypeId}" class="l-text-field" />
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
