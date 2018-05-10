 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>黑名单查询</title> 
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" 
			rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" 
			rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>"
			 rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" 
			type="text/javascript"></script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" 
			type="text/javascript"></script>
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
	            { display:'子商户号',name:'subMerId',align:'left',width:'10%',align:'center'},
	            { display: '子商户名称', name: 'subMerName', width: '10%', align: 'center' },
	            {display:'终端号码',name:'terminalId',align:'left',width:'14%',align:'center'},
	            { display: '状态', name: 'status', width: '3%', align: 'center',
	            render:function(rowdata,rowindex){
	            	if(rowdata.status=='0'){return '移除';}
	            	else if(rowdata.status=='1'){return '标记';}
	            } }, 
	            {display:'创建时间',name:'createTime',align:'left',width:'7%',align:'center'},
				{ display:'姓名',name:'realName',align:'left',width:'6%',align:'center'},
				{ display:'证件号	',name:'idNum',align:'left',width:'12%',align:'center'},
				{ display:'手机号',name:'phone',align:'left',width:'8%',align:'center'},
	            { display: '卡类型', name: 'cardType', width: '4%', align: 'center',
	            render:function(rowdata,rowindex){
	            	if(rowdata.cardType=='0'){return '借记卡';}
	            	else if(rowdata.cardType=='1'){return '信用卡';}
	            } }, 
	            { display:'卡号',name:'cardNo',align:'left',width:'12%',align:'center'},
	             { display: '黑名单类型', name: 'blackType', width: '6%', 
	             align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.blackType != null){
	            	if(rowdata.blackType=='0'){return '认证中';}
	            	else if(rowdata.blackType=='1'){return '实名认证成功';}
	            	else if(rowdata.blackType=='2'){return '实人认证成功';}
	            	else if(rowdata.blackType=='3'){return '成功开通收款';}
	            	else if(rowdata.blackType=='4'){return '实人认证失败';}
	            	}else{
	            	return "";}
	            } },  
	            { display: '黑名单级别', name: 'level', width: '6%', 
	             align: 'center',render:function(rowdata,rowindex){
	            	if(rowdata.level != null){
	            	if(rowdata.level=='0'){return '认证中';}
	            	else if(rowdata.level=='1'){return '实名认证成功';}
	            	else if(rowdata.level=='2'){return '实人认证成功';}
	            	else if(rowdata.level=='3'){return '成功开通收款';}
	            	else if(rowdata.level=='4'){return '实人认证失败';}
	            	}else{
	            	return "";}
	            } },  
	            { display: '操作', name: 'null', width: '5%', align: 'center' ,render: 
	            function (rowdata, rowindex, value){
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['7011'] == '7011'}">
                    	var option = "标记";
	                    	if(rowdata.status=='0'){  
	                    		option = "标记 ";
	                    		h += "<a onclick='f_open("+rowdata.id+","+rowdata.status+
	                    	")' style='cursor:pointer;color:blue;'>"+option+"</a>"; 
	                    	}else if(rowdata.status=='1'){
	                    		option = "移除";
	                    		h += "<a onclick='f_open("+rowdata.id+","+rowdata.status+")' style='cursor:pointer;color:blue;'>"+option+"</a>"; 
	                    	}
                    	</c:if>
                    }		
                    return h;
                }}
                ], 
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
                url:'<s:url value="/blackInfo!selectBlackInfoList.ac"/>?blackInfo.subMerId='
        +$("#subMerId").val()+'&blackInfo.realName='+encodeURI($("#realName").val())+
        '&blackInfo.phone='+$("#phone").val()+'&blackInfo.cardNo='+$("#cardNo").val()+'&blackInfo.idNum='+$("#idNum").val()+
        '&blackInfo.status='+$("#status").val()+'&blackInfo.level='+$("#level").val()
        +'&blackInfo.blackType='+$("#blackType").val()+'&blackInfo.terminalId='+$("#terminalId").val()
            });
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
            $("#createTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
            $("#maxTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
         function f_search()
        {
      		var subMerId=$("#subMerId").val();
			var pattern = /^[\S]*$/; //不包含空格
	    	var number = /^[0-9]{1,20}$/;	//数字	
			if(subMerId!=""&&!number.test(subMerId)) {
				alert("子商户号只能是数字！");
				$("#merSysId").focus();
				$("#merSysId").val(""); 
				return false;
			}else{
			  $("#form1").submit();
			}
        }
        function f_clean(){
        	$("#subMerId").val("");
        	$("#realName").val(""); 
        	$("#status").val("-1");  
        	$("#level").val("-1"); 
        	$("#blackType").val("-1");
        }
	
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">			
			<form id="form1" method="post" 
				action="<s:url value="/pages/BlackInfo/selectBlackInfoList.jsp"/>">
				<table width="100%">
					<thead></thead>
					<tr>
						<td style="text-align: right;"> 
							子商户号： 
						</td>
						<td style="text-align: left;">
							<input type="text" name="subMerId" id="subMerId" 
								value="${param.subMerId}" />
						</td>
						<td style="text-align: right;"> 
							TSN号： 
						</td>
						<td style="text-align: left;">
							<input type="text" name="terminalId" id="terminalId" 
								value="${param.terminalId}" />
						</td>
						<td style="text-align: right;"> 
							卡号： 
						</td>
						<td style="text-align: left;">
							<input type="text" name="cardNo" id="cardNo" 
								value="${param.cardNo}" />
						</td>
						<td style="text-align: right;">
							姓名：
						</td>
						<td style="text-align: left;">
							<input type="text" name="realName" id="realName"  
								value="${param.realName}" maxlength="20"/>
						</td>
						<td style="text-align: right;">
							手机号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="phone" id="phone"  
								value="${param.phone}" maxlength="20"/>
						</td>
						<td style="text-align: right;">
							证件号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="idNum" id="idNum"  
								value="${param.idNum}" maxlength="20"/>
						</td>
						<td style="text-align: right;">
							状态：
						</td>
						<td style="text-align: left;">
							<select name="status" id="status" style="width: 130px">
								<option value="-1">请选择</option>
							</select>
						</td>
						<td style="text-align: right;">
							黑名单级别：
						</td>
						<td style="text-align: left;">
							<select name="level" id="level" style="width: 130px">
								<option value="-1">请选择</option>
								<option value="0">移除</option>
								<option value="1">标记</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							黑名单类型：
						</td>
						<td>
							<select name="blackType" id="blackType" style="width:130px">
							<option value="-1">请选择</option>
							</select>
						</td>
						<td style="text-align: left;"><input id="btn" type="button" 
							value="查 询" onclick="f_search()" class="l-button"/>
						</td>
					    <td style="text-align: left;"><input id="btn2" type="button" 
					   		value="重 置" onclick="f_clean()" class="l-button"/>
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
		<script>
			function f_open(bId,status){
			if(status=="1"){
				status="0";
			}else if(status=="0"){
				status="1";
			}else if(status==null || status==""){
				status="1";
			}
			if(confirm("是否确认操作？"))
				$.post("<s:url value='/blackInfo!updateBlackInfoStatus.ac'/>",						
				{'blackInfo.id':bId,'blackInfo.status':status},
				function(data){
					if(data=='succ'){
						alert("状态修改成功！");
						f_clean();
						f_search();
					}else{
						alert("操作失败，请重新操作!");
					}
				},"text");
		}
		</script>
	</body>
</html>
