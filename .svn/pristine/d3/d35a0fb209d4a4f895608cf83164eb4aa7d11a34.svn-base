<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>未上线子商户</title>
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
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerForm.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerComboBox.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerCheckBox.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerButton.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerRadio.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerSpinner.js'/>" type="text/javascript"></script>
	    <script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerTextBox.js'/>" type="text/javascript"></script>  
	    <script src="<s:url value='/js/jqui/CustomersData.js'/>"></script>	
	    <script src="<s:url value='/js/common.js'/>"></script>
		<style type="text/css">
			tr{
				height:30px;
			}
		</style>
		<script type="text/javascript">
       		 $(function ()
      		  {
			     $("#maingrid4").ligerGrid({
			     columns: [
	            { display: '子商户入网号', name: 'subMerId', align: 'left', width: '10%' ,align: 'center' },
	            { display: '所属机构商户', name: 'merName', align: 'center', width: '16%',render:function(rowdata,rowindex){
	            	return rowdata.merName+"("+rowdata.merSysId+")";
	            } },
	            { display: '子商户名称', name: 'subMerName', width: '8%', align: 'center' },
	            { display: '子商户状态', name: 'status', width: '5%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.status=='0'){return '未审批';}
	            	else if(rowdata.status=='1'){return '已审批';}
	            	else if(rowdata.status=='2'){return '开通服务';}
	            	else if(rowdata.status=='3'){return '暂停服务';}
	            	else if(rowdata.status=='4'){return '停止服务';}
	            	else if(rowdata.status=='5'){return '黑名单';}
	            } },   
	             { display: '实人认证状态', name: 'authStatus', width: '7%', align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.authStatus != null){
	            	if(rowdata.authStatus=='0'){return '认证中';}
	            	else if(rowdata.authStatus=='1'){return '实名认证成功';}
	            	else if(rowdata.authStatus=='2'){return '实人认证成功';}
	            	else if(rowdata.authStatus=='3'){return '成功开通收款';}
	            	else if(rowdata.authStatus=='4'){return '实人认证失败';}
	            	}else{
	            	return "";}
	            } },  
	            { display: '入网时间', name: 'createTime', width: '11%', align: 'center' },
	            { display: '结算账户名', name: 'settAccountName', width: '9%', align: 'center' }, 
	            { display: '结算账户号', name: 'settAccountNo', width: '11%', align: 'center' }, 
	            { display: '操作', name: 'null', width: '22%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['1001'] == '1001'}">
                    		h += "<a onclick='f_open3("+rowdata.subMerId+")' style='cursor:pointer;color:blue;'>上线</a> "; 
                    	</c:if>
                    	<c:if test="${purview['1002'] == '1002'}">
                    	  	h += "<a onclick='f_open6("+rowdata.subMerId+")' style='cursor:pointer;color:blue;'>终端号</a> ";
                    	</c:if>
                    	<c:if test="${purview['1003'] == '1003'}">
                    	 	h += "<a onclick='f_open7("+rowdata.subMerId+")' style='cursor:pointer;color:blue;'>商户照片</a> ";
                    	</c:if>
                    	<c:if test="${purview['1004'] == '1004'}">
                    	 if(rowdata.autoApprove=='1'){
                    	 	h += "<a onclick='f_open8("+rowdata.subMerId+")' style='cursor:pointer;color:blue;'>实人认证</a> ";
                    	 }
                    	</c:if>
                    	<c:if test="${purview['1005'] == '1005'}">
                    	 	h += "<a onclick='f_open9("+rowdata.subMerId+")' style='cursor:pointer;color:blue;'>修改结算信息</a> ";
                    	</c:if>
                    }		
                    return h;
                }}], 
               type: "POST",
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
                url:'<s:url value="/subMerInfo!selectSubMerchantNoOnline.ac"/>'
			            });
		            $('#maingrid4').ligerGrid().set('dataAction','server');
           		 	$("#pageloading").hide();
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
        
         function f_open3(subMerId)
	    {
	        $.ligerDialog.open({ url: '<s:url value="/subMerInfo!subMerchantTop.ac"/>?id='+subMerId+'&rateStatus=1',
            					 height:530,width:650, isResize: false,title:'子商户上线'});    
	    }
         function f_open4(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerInfo!getById.ac"/>?id='+subMerId,
            					 height:490,width:650, isResize: false,title:'子商户详情'});    
        }
         function f_open6(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/subMerchant/selectSubMerTerminal.jsp"/>?id='+subMerId,
            					 height:250,width: 580, isResize: false,title:'子商户终端号'});    
        }
          function f_open7(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerInfo!selectSubMerchantPicture.ac"/>?subMerInfo.subMerId='+subMerId+'&id=0',
            					 height:600,width:700, isResize: false,title:'商户照片'});    
        }
         function f_open8(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/pages/authInfo/authList.jsp"/>?subMerId='+id,
            					 height:600,width:950, isResize: false,title:'实人认证'});    
        }
           function f_open9(subMerId)
        {
            $.ligerDialog.open({ url: '<s:url value="/subMerInfo!passCheck.ac"/>?id='+subMerId,
            					 height:170,width:350, isResize: false,title:'密码验证'});  
        }
        if(data=='succ'){
			$("#merStatus").html("<font color='red'>已审批</font>");
		}
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div >
		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0">
		</div>
		<div style="display: none;">

		</div>
	</body>
</html>
