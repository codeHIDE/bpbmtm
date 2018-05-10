﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
            { display: '终端号', name: 'terminalId', align: 'center', width: '8%'},
            { display: '子商户号', name: 'subMerId', width: '12%', align: 'center'},
            { display: '登录名', name: 'loginName', width: '8%', align: 'center' },	
            { display: '状态', name: 'status', width: '5%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.status==0){return '手机注册';}
            	else if(rowdata.status==1){return '实名认证';}
            	else if(rowdata.status==2){return '绑定机具';}
            	} 
            },     
            { display: '注册时间', name: 'createTime', width: '8%', align: 'center' },	
            { display: '操作', name: 'null', width: '28%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                   	h += "<a onclick='f_open6("+rowdata.id+")' style='cursor:pointer;color:blue;'>重置密码 </a> "; 
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
                url:'<s:url value="/subMerTerminal!selectMobileUser.ac"/>?mobileUser.terminalId='+$("#terminalId").val()+
                '&mobileUser.subMerId='+$("#subMerId").val()+ '&mobileUser.loginName='+$("#loginName").val()
                /* +'&subMerTerminal.agentIdL1='+$("#agentId").val() */+'&mobileUser.status='+$("#status").val()/* +"&subMerTerminal.level="+$("#level").val() */
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
        
          function f_open5(mid)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerTerminal!selectSubMerTerminalById.ac"/>?subMerTerminal.id='+mid,
            					 height:470,width: 650, isResize: false,title:'终端详情'});    
        }
        
        function f_open6(mid)
        {
        	var url = "<s:url value='/subMerTerminal!resetMobilePwd.ac'/>?mobileUser.id="+mid;
        	if(confirm("是否确认操作？"))
				$.post(url,
				function(data){
					if(data == "succ"){
					{
						alert("密码重置成功,新密码为123456");
					}
					window.location.reload();
					}else if(data == "fone"){
						alert("重置失败");
					}
				},"text");
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/subMerTerminal/selectMobileUser.jsp"/>" method="post" >
				<table width="100%" border="0">
					<thead></thead>
					<tr>
						<td style="text-align: right;width:100px;">
							终端号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input style="width: 130px" type="text" name="terminalId" id="terminalId" value="${param.terminalId}" class="l-text-field" />
							</div>
						</td>
						<td style="text-align: right;width:100px;">
							子商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="subMerId" id="subMerId"  class="l-text-field" value="${param.subMerId}" />
								</div>
						</td>
						<td style="text-align: right;width:100px;">
							登录名：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="loginName"  class="l-text-field"  id="loginName" value="${param.loginName}" />
							</div>
						</td> 
						<td></td>
						<td></td>
					</tr>
					<tr>
						<%-- <td style="text-align: right;width:100px;">
							代理商号：
						</td>
						<td style="text-align: left;">
							<select id="level" name="level" style="width: 50px;">
								<option value="1" <c:if test="${param.level==1}">selected</c:if>>一级</option>
								<option value="2" <c:if test="${param.level==2}">selected</c:if>>二级</option>
							</select>
							<input type="text" name="agentId" id="agentId" value="${param.agentId}" style="width: 75px;"/>
						</td> --%>
						<td style="text-align: right;width:100px;">
							状态：
						</td>
						<td><div class="l-text" style="width: 130px;">
							<select name="status" id="status"  class="l-text-field" style="width: 130px;">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>
									手机注册
								</option>
								<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>
									实名认证
								</option>
								<option value="2" <c:if test="${param.status==2}">selected="selected"</c:if>>
									绑定机具
								</option>
							</select></div>
							</td>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
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
