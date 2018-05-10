﻿﻿<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>代理商查询</title>
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
            { display: '代理商名称', name: 'agentName', width: '15%', align: 'center' ,render:function(rowdata,rowindex){
	            	return rowdata.agentName+"("+rowdata.agentId+")"; } 
            },    
            { display: '所属机构商户号', name: 'merSysId', width: '8%', align: 'center' }, 
            { display: '结算账号名', name: 'settAccountName', width: '7%', align: 'center' }, 
            { display: '结算账号', name: 'settAccountNo', width: '10%', align: 'center' }, 
            { display: '签约时间', name: 'createTime', width: '7%', align: 'center' }, 
            { display: '代理商状态', name: 'status', width: '7%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.status=='0'){return '未使用';}
            	else if(rowdata.status=='1'){return '正在使用';}
            	else if(rowdata.status=='2'){return '暂停';}
            } },   
           { display: '代理商级别', name: 'level', width: '6%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.level=='1'){return '一级代理商';}
            	else if(rowdata.level=='2'){return '二级代理商';}
            	else if(rowdata.level=='3'){return '三级代理商';}
            	else if(rowdata.level=='4'){return '四级代理商';}
            	else if(rowdata.level=='5'){return '五级代理商';}
            	else if(rowdata.level=='6'){return '六级代理商';}
            	else if(rowdata.level=='7'){return '七级代理商';}
            	else if(rowdata.level=='8'){return '八级代理商';}
            	else if(rowdata.level=='9'){return '九级代理商';}
            	else if(rowdata.level=='10'){return '十级代理商';}
            } },   
            { display: '一级代理商号', name: 'superAgentId', width: '8%', align: 'center' }, 
            { display: '操作', name: 'null', width: '31%', align: 'left' ,render: function (rowdata, rowindex, value)
           	 	{
                     var h = "";
                  if (!rowdata._editing)
                    {
                    	<c:if test="${purview['1062'] == '1062'}">
	                    	if(rowdata.status=='0'){
	                    		h += "<a onclick='f_open4("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>上线</a> ";
	                    	}if(rowdata.status=='1'){
	                    		h += "<a onclick='f_open4("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>暂停</a> ";
	                    	}if(rowdata.status=='2'){
	                    		h += "<a onclick='f_open4("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>启用</a> ";
	                    	}
                    	</c:if>
                    	<c:if test="${purview['1063'] == '1063'}">
                    		h += "<a onclick='f_open5("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>编辑</a> ";
                    	</c:if>
                    	<c:if test="${purview['1064'] == '1064'}">
                    		h += "<a onclick='f_open8("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>上传LOGO </a> ";
                    	</c:if>
                    	<c:if test="${purview['1065'] == '1065'}">
                    		h += "<a onclick='f_open9("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>修改结算信息 </a> "; 
                    	</c:if>
                    	h += "<a onclick='f_open6("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>修改费率信息</a> ";
                    	<c:if test="${purview['1061'] == '1061'}">
	                    	if(rowdata.level=='1'){
	                    		h += "<a onclick='f_open2("+rowdata.agentId+")' style='cursor:pointer;color:blue;'>二级代理列表</a> ";
	                    	}
                    	</c:if>
                    	
                    	h += "<a onclick='updatePassword("+rowdata.agentId+")' style='cursor:pointer;color:blue;'> 重置密码 </a> ";
                    }
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
                url:'<s:url value="/agencyInfo!selectAgenInfoList.ac"/>?agenctInfo.merSysId='+$("#merSysId").val()+
                '&agenctInfo.level='+$("#level").val()+'&agenctInfo.agentName='+encodeURI($("#agentName").val())
                +'&agenctInfo.agentId='+$("#agentId").val()
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
        });
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
        
         function f_search()
        {
           $("#form1").submit();
        }
        
          function f_clean(){
        	$("#merSysId").val("");
        	$("#agentName").val("");
        	$("#agentId").val("");
        	$("#level").val("-1");
        }
         function f_open2(agentId)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/agencyInfo/selectAgencyInfoLevel.jsp"/>?id='+agentId,
            					 height:500,width: 580, isResize: false,title:'二级代理商'});  
        }
         function f_open4(agentId)
        {
            $.ligerDialog.open({ url: '<s:url value="/agencyInfo!agencyDetailOnline.ac"/>?agenctInfo.agentId='+agentId,
            					 height:350,width: 650, isResize: false,title:'代理商详情'});    
        }
        function f_open5(agentId)
        {
            $.ligerDialog.open({ url: '<s:url value="/agencyInfo!updateAgency.ac"/>?agenctInfo.agentId='+agentId,
            					 height:350,width: 650, isResize: false,title:'代理商编辑'});    
        }
        function f_open6(agentId)
        {
            $.ligerDialog.open({ url: '<s:url value="/agencyInfo!updateAgencyRateOnline.ac"/>?agenctInfo.agentId='+agentId,
            					 height:350,width: 650, isResize: false,title:'代理商费率信息编辑'});    
        }
         function f_open8(agentId)
        {    
            $.ligerDialog.open({ url: '<s:url value="/agencyInfo!ageUploadLogo.ac"/>?agenctInfo.agentId='+agentId,
            					 height:150,width:340, isResize: false,title:'上传LOGO'});    
        }
           function f_open9(agentId)
        {
        	$.ligerDialog.open({ url: '<s:url value="/agencyInfo!passCheck.ac"/>?agenctInfo.agentId='+agentId,
            		 height:170,width:350, isResize: false,title:'密码验证'});
        }
           
           
           function updatePassword(agentId){
       		 $.ligerDialog.confirm('确认重置代理商密码?', function (yes) {
        	if(yes){
        		$.post('<s:url value="/agencyInfo!updatePassword.ac"/>',
				  {
				    agentId:agentId
				  },
				  function(data,status){
				    if(data=="succ"){
				    	$.ligerDialog.success('重置密码成功!新密码 为:123456');
				    }else{
				    	$.ligerDialog.error('重置密码失败!');
				 	   }
				  });
        	}
        });
        }
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/agencyInfo/selectAgencyInfoList.jsp" />" method="post">
				<table width="100%">
					<tr>
						<td style="text-align: right;"> 
							机构商户号： 
						</td>
						<td style="text-align: left;">
							<input type="text" name="merSysId" id="merSysId" value="${param.merSysId}" />
						</td>
						<td style="text-align: right;">
							代理商名称：
						</td>
						<td style="text-align: left;">
							<input type="text" name="agentName" id="agentName" maxlength="20" value="${param.agentName}" />
						</td>
						<td style="text-align: right;">
							代理商户号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="agentId" id="agentId" maxlength="20" value="${param.agentId}" />
						</td>
						<td style="text-align: right;">
							代理商级别：
						</td>
						<td style="text-align: left;">
							<select name="level" id="level" style="width: 120px;">
								<option value="-1" <c:if test="${param.level=='-1'}">selected="selected"</c:if>>请选择</option>
								<option value="1" <c:if test="${param.level=='1'}">selected="selected"</c:if>>一级代理商</option>
								<option value="2" <c:if test="${param.level=='2'}">selected="selected"</c:if>>二级代理商</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="4" style="text-align: center;">
							<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/>
						</td>
						<td colspan="4" style="text-align: center;">
							<input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button"/>
						</td>
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
