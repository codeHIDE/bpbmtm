﻿<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
            { display: '通道号', name: 'tractId', align: 'left', width: '6%' ,align: 'center' },
            { display: '通道名称', name: 'tractName', width: '10%', align: 'center' },
            { display: '费率类型', name: 'ratesType', width: '6%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.ratesType=='01'){return '扣率型';}
            	else if(rowdata.ratesType=='02'){return '封顶型';}
            	else if(rowdata.ratesType=='05'){return '积分型';}
            	else if(rowdata.ratesType=='10'){return '代付型';}
            	else if(rowdata.ratesType=='11'){return '会员消费';}
            	else if(rowdata.ratesType=='12'){return '新封顶';}
            	else if(rowdata.ratesType=='15'){return '急速到账';}
            	else if(rowdata.ratesType=='17'){return '通道直付';}
            	else if(rowdata.ratesType=='31'){return '香港(境外)';}
            	else if(rowdata.ratesType=='32'){return '英国(境外)';}
            	else if(rowdata.ratesType=='33'){return '美国(境外)';}
            	else if(rowdata.ratesType=='34'){return '法国(境外)';}
            	else if(rowdata.ratesType=='35'){return '日本(境外)';}
            	else if(rowdata.ratesType=='36'){return '新西兰(境外)';}
            	else if(rowdata.ratesType=='37'){return '马来西亚(境外)';}
            	else if(rowdata.ratesType=='38'){return '泰国(境外)';}
            } },
            { display: '支付商户号', name: 'transMerId', width: '10%', align: 'center' }, 
              { display: '通道机构', name: 'tractAgency', width: '10%', align: 'center' }, 
 			{ display: '支付通道', name: 'payTract', width: '6%', align: 'center' }, 
            { display: '入网时间', name: 'createTime', width: '8%', align: 'center' }, 
            { display: '通道状态', name: 'status', width: '6%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.status=='0'){return '未启用';}
            	else if(rowdata.status=='1'){return '已启用';}
            	else if(rowdata.status=='2'){return '暂停';}
            	else if(rowdata.status=='3'){return '暂停服务';}
            	else if(rowdata.status=='4'){return '停止服务';}
            	else if(rowdata.status=='5'){return '黑名单';}
            } },
               { display: '费率', name: 'tractRate', width: '8%', align: 'center' }, 
              { display: '封顶值', name: 'tractHighestFee', width: '8%', align: 'center' }, 
                { display: '通道负载', name: 'reserved', width: '6%', align: 'center',render:function(rowdata,rowindex){
            	if(rowdata.reserved=='0'){return '正常';}
            	else if(rowdata.reserved=='9'){return '已满';}
            } },
             { display: '操作', name: 'null', width: '15%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                    var h = "";
                    if (!rowdata._editing)
                    {
                    	<c:if test="${purview['5021'] == '5021'}">
	                    	var option = "启用 ";
	                    	if(rowdata.status=='1'){  
	                    		option = "暂停 ";
	                    		h += "<a onclick='f_open3("+rowdata.tractId+")' style='cursor:pointer;color:blue;'>"+option+"</a>"; 
	                    	}else if(rowdata.status=='0'||rowdata.status=='2'){
	                    		option = "启用 ";
		                    		h += "<a onclick='f_open3("+rowdata.tractId+")' style='cursor:pointer;color:blue;'>"+option+"</a>"; 
	                    	}
	                    </c:if>
	                    <c:if test="${purview['5022'] == '5022'}">
                   	   		h += "<a onclick='f_open2("+rowdata.tractId+")' style='cursor:pointer;color:blue;'>详情 </a>";
                   	   	</c:if>
                   	   	<c:if test="${purview['5023'] == '5023'}">
                    		h += "<a onclick='f_open4("+rowdata.tractId+")' style='cursor:pointer;color:blue;'>  修改  </a>";
                    	</c:if>
                    	<c:if test="${purview['5024'] == '5024'}">
                    	h += "<a onclick='insert(" + rowdata.tractId + ")' style='cursor:pointer;color:blue;'> 统计添加 </a> ";
                    	</c:if>
                    	<c:if test="${purview['5025'] == '5025'}">
                    		var options = "";
	                    	if(rowdata.reserved=='0'){  
	                    		options = "已满 ";
	                    		h += "<a onclick='update(" + rowdata.tractId + ")' style='cursor:pointer;color:blue;'> "+options+" </a> ";
	                    	}else if(rowdata.reserved=='9'){
	                    		options = "正常";
		                    	h += "<a onclick='update1(" + rowdata.tractId + ")' style='cursor:pointer;color:blue;'> "+options+" </a> "; 
	                    	}
	                    </c:if>
                        //h += "<a href='javascript:deleteRow(" + rowindex + ")'>删除</a> "; 
                        h += "<a onclick='f_qiandao("+rowdata.tractId+")' style='cursor:pointer;color:blue;'>平台签到 </a>";
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
                url:'<s:url value="/tractInfo!selectTractAllList.ac"/>?tractId='+$("#tractId").val()+
                "&tractName="+encodeURI($("#tractName").val())+"&transMerId="+$("#transMerId").val()+
                "&payTract="+$("#payTract").val()+ "&tractType="+$("#tractType").val()+"&status="+$("#status").val() +
                "&ratesType="+$("#ratesType").val()+"&integral="+$("#integral").val()+"&tractAgency="+$("#tractAgency").val()
            });
            $("#maingrid4").ligerGrid().set('dataAction','server');
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
        
        function f_clean(){
        	$("#tractId").val("");
        	$("#tractName").val("");
        	$("#transMerId").val("");
        	$("#payTract").val("-1");
        	$("#tractType").val("-1");
        	$("#status").val("-1");
        	$("#ratesType").val("-1");
        	$("#integral").val("-1");
        	$("#tractAgency").val("");
        }
           
        function f_open3(tractId)
        {
            $.ligerDialog.open({ url: '<s:url value="/tractInfo!tractDetail.ac"/>?tractId='+tractId,
            height:620,width:670, isResize: false,title:'通道审核'});    
        }
        
         function f_open2(tractId)
        {
            $.ligerDialog.open({ url: '<s:url value="/tractInfo!selecttractDetail.ac"/>?tractId='+tractId,
            height:600,width:670, isResize: false,title:'通道详情'});    
        }
        
        function f_open4(tractId)
        {
            $.ligerDialog.open({ url: '<s:url value="/tractInfo!updateTractInfoDetail.ac"/>?tractId='+tractId,
            					 height:720,width:670, isResize: false,title:'修改通道信息'});    
        }
        
       //	function serEncryptKeyInfo(tractId)
       // 	{
       //  		 $.ligerDialog.open({ url: '<s:url value="/tractInfo!tractDetail.ac"/>?tractId='+tractId,
       //      					 height:500,width:650, isResize: false,title:'密钥管理'});    
       //   }
         if(data=='succ'){
			$("#tractStatus").html("<font color='red'>已审批</font>");
		}
         
         function f_qiandao(tractId){
            $.ligerDialog.confirm('确认平台签到?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/tractStatictis!tractQiandao.ac'/>',
				  {
				    tractId:tractId
				  },
				  function(data,status){
				  		alert(data);
				  });
        	}
        });
         }
           function insert(tractId){
		        $.ligerDialog.confirm('确认添加通道统计?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/tractStatictis!addTractStatictis.ac'/>',
				  {
				    tractId:tractId
				  },
				  function(data,status){
				    if(data=="succ"){
				    	alert("添加通道统计成功！");
				    	//window.parent.$.ligerDialog.success('添加通道统计成功!');
				    	 window.f_search();
				    }else{
				    	alert("已有记录不可重复添加！");
				    	//window.parent.$.ligerDialog.error('添加通道统计失败!');
				    	//window.parent.$.ligerDialog.success('添加通道统计成功!');
				    	 window.f_search();
				    }
				  });
        	}
        });
        }
           
             function update(tractId){
       		 $.ligerDialog.confirm('确认更改通道状态?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/tractInfo!updateReserved.ac'/>',
				  {
				    tractId:tractId,
				    reserved:'9'
				  },
				  function(data,status){
				    if(data=="succ"){
				    //	window.parent.$.ligerDialog.success('更改通道状态成功!');
				    	alert("更改通道状态成功！");
				    	 window.f_search();
				    	 //	window.location.reload();
				    }else{
				    	window.parent.$.ligerDialog.error('更改通道状态失败!');
				    	alert("更改通道状态失败！");
				    	 window.f_search();
				    	 	//window.location.reload();
				    }
				  });
        	}
        });
        }
             
          function update1(tractId){
       		 $.ligerDialog.confirm('确认更改通道状态?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/tractInfo!updateReserved.ac'/>',
				  {
				    tractId:tractId,
				    reserved:'0'
				  },
				  function(data,status){
				    if(data=="succ"){
				    	alert("更改通道状态成功！");
				    	 window.f_search();
				    //	window.parent.$.ligerDialog.success('更改通道状态成功!');
				    	// 	window.location.reload();
				    }else{
				    	window.parent.$.ligerDialog.error('更改通道状态失败!');
				    	alert("更改通道状态失败！");
				    	 window.f_search();
				    	 //	window.location.reload();
				    }
				  });
        	}
        });
        }
        
       
    </script>
	</head>
	<body style="padding: 4px;">
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/tract/selectTractInfo.jsp"/>" method="post">
				<table width="80%">
					<thead></thead>
					<tr>
						<td style="text-align: right;">
							通道号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="tractId" id="tractId"
								value="${param.tractId}" />
								</div>
						</td>
						<td style="text-align: right;">
							通道名称：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="tractName" id="tractName"
								maxlength="20" value="${param.tractName}" />
								</div>
						</td>
						<td style="text-align: right;">
							支付商户号：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="transMerId" id="transMerId" value="${param.transMerId}" />
						</div>
						</td>
							<td style="text-align: right;">
							通道机构：
						</td>
						<td style="text-align: left;">
						<div class="l-text" style="float:left;">
							<input type="text" class="l-text-field" name="tractAgency" id="tractAgency" value="${param.tractAgency}" />
						</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							支付通道：
						</td>
						<td>
							<select name="payTract"  id="payTract">
								<option value="-1">
									--请选择--
								</option>
								<option value="CSTP" <c:if test="${param.payTract=='CSTP'}">selected="selected"</c:if>>
									CSTP
								</option>
								<option value="BJPOSP" <c:if test="${param.payTract=='BJPOSP'}">selected="selected"</c:if>>
									BJPOSP
								</option>
								<option value="UMSBJ" <c:if test="${param.payTract=='UMSBJ'}">selected="selected"</c:if>>
									UMSBJ
								</option>
								<option value="RUIYIN" <c:if test="${param.payTract=='RUIYIN'}">selected="selected"</c:if>>
									RUIYIN
								</option>
							</select>
							</td>
						<td style="text-align: right;">
							通道状态：
						</td>
						<td>
							<select name="status"  id="status">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.status=='0'}">selected="selected"</c:if>>
									未启用 
								</option>
								<option value="1" <c:if test="${param.status=='1'}">selected="selected"</c:if>>
									已启用
								</option>
								<option value="2" <c:if test="${param.status=='2'}">selected="selected"</c:if>>
									暂停
								</option>
							</select>
						</td>
						<td style="text-align: right;">
							是否积分：
						</td>
						<td>
							<select name="integral"  id="integral">
								<option value="-1">
									--请选择--
								</option>
								<option value="0" <c:if test="${param.integral=='0'}">selected="selected"</c:if>>
									否
								</option>
								<option value="1" <c:if test="${param.integral=='1'}">selected="selected"</c:if>>
									是
								</option>
							</select>
						</td>
						<td style="text-align: right;">
							手续费类型：
						</td>
						<td>
							<select name="ratesType"  id="ratesType">
								<option value="-1">
									--请选择--
								</option>
								<option value="01" <c:if test="${param.ratesType=='01'}">selected="selected"</c:if>>
									扣率型
								</option>
								<option value="02" <c:if test="${param.ratesType=='02'}">selected="selected"</c:if>>
									封顶型
								</option>
								<option value="05" <c:if test="${param.ratesType=='05'}">selected="selected"</c:if>>
									积分型
								</option>
								<option value="10" <c:if test="${param.ratesType=='10'}">selected="selected"</c:if>>
									代付型
								</option>
								<option value="11" <c:if test="${param.ratesType=='11'}">selected="selected"</c:if>>
									会员消费
								</option>
								<option value="12" <c:if test="${param.ratesType=='12'}">selected="selected"</c:if>>
									新封顶
								</option>
								<option value="15" <c:if test="${param.ratesType=='15'}">selected="selected"</c:if>>
									急速到账
								</option>
								<option value="17" <c:if test="${param.ratesType=='17'}">selected="selected"</c:if>>
									通道直付
								</option>
								<option value="31" <c:if test="${param.ratesType=='31'}">selected="selected"</c:if>>
									香港(境外)
								</option>
								<option value="32" <c:if test="${param.ratesType=='32'}">selected="selected"</c:if>>
									英国(境外)
								</option>
								<option value="33" <c:if test="${param.ratesType=='33'}">selected="selected"</c:if>>
									美国(境外)
								</option>
								<option value="34" <c:if test="${param.ratesType=='34'}">selected="selected"</c:if>>
									法国(境外)
								</option>
								<option value="35" <c:if test="${param.ratesType=='35'}">selected="selected"</c:if>>
									日本(境外)
								</option>
								<option value="36" <c:if test="${param.ratesType=='36'}">selected="selected"</c:if>>
									新西兰(境外)
								</option>
								<option value="37" <c:if test="${param.ratesType=='37'}">selected="selected"</c:if>>
									马来西亚(境外)
								</option>
								<option value="38" <c:if test="${param.ratesType=='38'}">selected="selected"</c:if>>
									泰国(境外)
								</option>
							</select>
						</td>
						<td style="text-align: left"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
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
