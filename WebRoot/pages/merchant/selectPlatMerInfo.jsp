<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>平台商户查询</title>
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
        $(function () {
            $("#maingrid4").ligerGrid({
            columns: [
             { display: '平台商户名称(平台商户号)', name: 'merName', align: 'center', width: '15%',render:function(rowdata,rowindex){
	            	return rowdata.platMerName+"("+rowdata.platMerId+")";
	            } },
            { display: '平台商户状态', name: 'status', width: '12%', align: 'center',render:function(rowdata,rowindex)
            	{
            	if(rowdata.status==0){return '未审核';}
            	else if(rowdata.status==1){return '正在使用';}
            	else if(rowdata.status==2){return '暂停服务';}
            	} 
            },  
            { display: '联系人', name: 'contactor', width: '12%', align: 'center' }, 
             { display: '联系电话', name: 'contactPhone', width: '12%', align: 'center' }, 
            { display: '操作', name: 'null', width: '30%', align: 'left' ,render: function (rowdata, rowindex, value)
            {
                    var h = "";
                    <c:if test="${purview['1031'] == '1031'}">
                    	if(rowdata.status==0){
	                    		h += "<a onclick='f_open3("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>审批</a> "; 
                    	} else if(rowdata.status=='1') {
	                    		h += "<a onclick='f_open3("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>暂停</a> "; 
                    	}else if(rowdata.status=='2'){
	                    		h += "<a onclick='f_open3("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>恢复 </a> "; 
                    	}
                     </c:if>
                     <c:if test="${purview['1032'] == '1032'}">
                   		h += "<a onclick='f_open5("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>编辑</a> ";
                   	 </c:if>
                   	 <c:if test="${purview['1033'] == '1033'}">
                    	h += "<a onclick='f_open7("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>修改权限</a> "; 
                     </c:if>
                     <c:if test="${purview['1034'] == '1034'}">
                    	h += "<a onclick='f_open8("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>上传LOGO </a> "; 
                     </c:if>
                     <c:if test="${purview['1035'] == '1035'}">
                   		h += "<a onclick='f_open9("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>机构列表 </a> ";
                   	 </c:if>
                   	 	h += "<a onclick='f_open10("+rowdata.platMerId+")' style='cursor:pointer;color:blue;'>添加操作员 </a> ";
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
                url:'<s:url value="/merchantInfo!selectPlatMerAll.ac"/>?platMerInfo.platMerId='+$("#platMerId").val()+'&platMerInfo.platMerName='+encodeURI($("#platMerName").val())+
                '&platMerInfo.createTime='+$("#createTime").val()+'&platMerInfo.status='+$("#status").val()
            }); 
            $('#maingrid4').ligerGrid().set('dataAction','server');   
            $("#pageloading").hide();
            $("#signDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            $("#createTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        

         function f_search()
        {
        
        		var merSysId=$("#platMerId").val();  
					var pattern = /^[\S]*$/; //不包含空格
			    	var number = /^[0-9]{1,20}$/;	//数字
			    	
					if(merSysId!=""&&!number.test(merSysId)) {
						alert("商户号只能是数字！");
						$("#merSysId").focus();
						return false;
					}else{
					  $("#form1").submit();
					}
         
        }
        
        function f_clean(){
        	$("#platMerId").val("");
        	$("#platMerName").val("");
        	$("#createTime").val("");
        	$("#status").val("-1");
        }
        
         function f_open3(pid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!platMerIndex.ac"/>?pid='+pid,
            					 height:420,width:650, isResize: false,title:'机构商户审核'});    
        }
        
          function f_open5(pid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!updatePlatMerInfoDetail.ac"/>?pid='+pid,
            					 height:350,width: 700, isResize: false,title:'编辑平台商户信息'});    
        }
        
        function f_open6(pid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!toAddPlatManager.ac"/>?pid='+pid,
            					 height:430,width: 550, isResize: false,title:'添加操作员'});    
        }
        
        
         function f_open7(pid)
        {    
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!toUpdateManager.ac"/>?pid='+pid,
            					 height:450,width:320, isResize: false,title:'分配权限'});    
        }
        
         function f_open8(pid)
        {    
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!platUploadLogo.ac"/>?pid='+pid,
            					 height:240,width:340, isResize: false,title:'上传LOGO'});    
        }
        
        
          function f_open9(pid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!doselectPlatList.ac"/>?pid='+pid,
            					 height:550,width: 900, isResize: false,title:'机构列表'});    
        }
        
        //通道规则
          function f_open10(pid)
        {
            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!toAddManager.ac"/>?pid='+pid,
            					 height:430,width: 550, isResize: false,title:'添加操作员'});    
        }
        
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/merchant/selectPlatMerInfo.jsp"/>" method="post" >
				<table width="80%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							平台商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="platMerId"  class="l-text-field"  id="platMerId" value="${param.platMerId}" /></div>
						</td>
						<td style="text-align: right;">
							平台商户名称：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="width: 130px;">
							<input type="text" name="platMerName" class="l-text-field"  id="platMerName" maxlength="20" value="${param.platMerName}" />
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							入网时间：
						</td>
						<td style="text-align: left;">
							<input type="text" name="createTime"  id="createTime" value="${param.createTime}"/>
						</td>
						<td style="text-align: right;">
							平台商户状态：
						</td>
						<td>
						<div class="l-text" style="width: 130px;">
							<select name="status" id="status"  class="l-text-field"  >
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status==0}">selected="selected"</c:if>>
									未审批 
								</option>
								<option value="1" <c:if test="${param.status==1}">selected="selected"</c:if>>
									正在使用
								</option>
								<option value="2" <c:if test="${param.status==2}">selected="selected"</c:if>>
									暂停服务
								</option>
							</select>
							</div>
							</td>
							<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
							<td style="text-align: left;"><input id="btn" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
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
