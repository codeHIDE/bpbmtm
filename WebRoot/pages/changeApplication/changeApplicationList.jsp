<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>变更申请</title>
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
			tr {
				height: 30px;
			}
		</style>
   		<script type="text/javascript">

           function f_open1(id)
	        {
	            $.ligerDialog.open({ url: '<s:url value="/merchantInfo!merInfoUpdateDetailed.ac"/>?merInfoUpdate.id='+id, height:520,width:690, isResize: false,title:'详情'});  
	        }
	        

	       function f_open2(){
	    	   $.ligerDialog.open({ url: '<s:url value="/test!merInfoUpdateHandle.ac"/>', height:520,width:690, isResize: false,title:'详情'});  
		       }

	       function f_open3(){
	    	   $.ligerDialog.open({ url: '<s:url value="/test!updateUseCardit.ac"/>', height:520,width:690, isResize: false,title:'详情'}); 
		       }

        </script>
		<script type="text/javascript">

        $(function (){
            $("#maingrid4").ligerGrid({
            columns: [
	            { display: '商户号', name: 'mid', width: '15%' , align: 'center'},
	            { display: '机构类型', name: 'merType', align: 'center', width: '10%' , render:function(rowdata,rowindex){
	            	if(rowdata.merType==0){return '机构';}
	            	else if(rowdata.merType==1){return '子商户';}
	            	else return '未知';
	            }},
	            { display: '更新类型', name: 'modifyType', width: '17%', align: 'center', render:function(rowdata,rowindex){
	            	if(rowdata.modifyType=='01'){return '费率变更';}
	            	else if(rowdata.modifyType=='02'){return '支付通道变更';}
	            	else if(rowdata.modifyType=='03'){return '清分类型变更';}
	            	else return '未知';
	            } },
	            { display: '变更状态', name: 'status', width: '6%', align: 'center' , render:function(rowdata,rowindex){
	            	if(rowdata.status==0){return '申请';}
	            	else if(rowdata.status==1){return '允许';}
	            	else if(rowdata.status==2){return '已变更';}
	            	else if(rowdata.status==3){return '不允许';}
	            	else return '未知';
	            }},
	            { display: '创建时间', name: 'createTime', width: '10%', align: 'right'}, 
	            { display: '变更时间', name: 'updateTime', width: '10%', align: 'center' },
	            { display: '备注', name: 'remark', width: '10%', align: 'center' }, 
	            { display: '操作', name: 'null', width: '18%', align: 'center',render: function (rowdata, rowindex, value)
	           	 	{
	                    var h = "";
	                    if (!rowdata._editing)
	                    {
	                      <c:if test="${purview['1071'] == '1071'}">
	                   		 h += "<a onclick=f_open1('"+rowdata.id+"')  style='cursor:pointer;color:blue;'> 操作 </a> ";
	                 	  </c:if>
	                 	  //h += "<a href='<s:url value="/changeApplication!doBatch.ac"/>'> 跑批</a> ";
	                    }
	                    
	                    return h;
	                } } 
                ],
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                checkbox: false,
                pageSize: 15,
		        sortName : null,
		        sortOrder:null,      
		        root :'Rows',                       //数据源字段名
		        record:'Total',                     //数据源记录数字段名
		        pageParmName :'page',               //页索引参数名，(提交给服务器)
		        pagesizeParmName:'pagesize',        //页记录数参数名，(提交给服务器)
                dataAciton : 'server',
                url:'<s:url value="/merchantInfo!selectAllChangeApList.ac"/>?merInfoUpdate.merType='+$("#merType").val()+'&merInfoUpdate.mid='+$("#mid").val()+'&merInfoUpdate.modifyType='+ $("#modifyType").val()+'&merInfoUpdate.status='+ $("#status").val()
                
            });
            $("#pageloading").hide();
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#merOrderTime").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }

        /*
         function f_search()
        {
          	  var mid=$("#mId").val();  
			 
					var pattern = /^[\S]*$/; //不包含空格
			    	var number = /^[0-9]{1,20}$/;	//数字	
					if(!number.test(mid)) {
						alert("机构/商户号只能是数字！");
						$("#mId").val("");  
						$("#mId").focus();
						return false;
					}
					alert($("#mId").val());
					 $("#form1").submit();
        }
        */
        
           function f_clean(){
        	$("#merType").val("2");
        	$("#mid").val("");
        	$("#modifyType").val("04");
        	$("#status").val("-1");
        	 
        }
        
        
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<%@include file="/js/common.jsp"%>
		<div id="searchbar">
			<form id="form1"
				action="<s:url value='/pages/changeApplication/changeApplicationList.jsp' />"
				method="post">
				<table width="95%" border="0">
					<tr>
						<td style="text-align:center;">
							商户号/机构号：
							<select name="merType" id="merType">
								<option value="-1"
									<c:if test="${param.merType==-1}">selected="selected"</c:if>>
									请选择
								</option>
								<option value="1"
									<c:if test="${param.merType==1}">selected="selected"</c:if>>
									商户号
								</option>
								<option value="0"
									<c:if test="${param.merType==0}">selected="selected"</c:if>>
									机构号
								</option>
							</select>
							&nbsp;
							<input type="text" name="mid" id="mid" value="${param.mid}" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							变更状态：
							<select name="status" id="status">
								<option value="-1"
									<c:if test="${param.status==-1}">selected="selected"</c:if>>
									--请选择--
								</option>
								<option value="0"
									<c:if test="${param.status==0}">selected="selected"</c:if>>
									申请
								</option>
								<option value="1"
									<c:if test="${param.status==1}">selected="selected"</c:if>>
									允许
								</option>
								<option value="2"
									<c:if test="${param.status==2}">selected="selected"</c:if>>
									已变更
								</option>
								<option value="3"
									<c:if test="${param.status==3}">selected="selected"</c:if>>
									不允许
								</option>
							</select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							更新类型：
							<select name="modifyType" id="modifyType">
								<option value="-1"
									<c:if test="${param.modifyType=='04'}">selected="selected"</c:if>>
									--请选择--
								</option>
								<option value="01"
									<c:if test="${param.modifyType=='01'}">selected="selected"</c:if>>
									费率变更
								</option>
								<option value="02"
									<c:if test="${param.modifyType=='02'}">selected="selected"</c:if>>
									支付通道变更
								</option>
								<option value="03"
									<c:if test="${param.modifyType=='03'}">selected="selected"</c:if>>
									清分类型变更
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: left; text-align: center">
							<input id="btn" type="submit" value="查 询" class="l-button" />
							<input id="btn2" type="button" value="重 置" onclick="f_clean()"
								class="l-button" />
						</td>
					</tr>

				</table>
			</form>

		</div>
		<div class="l-loading" style="display: block" id="pageloading">
		</div>
		<div id="maingrid4" style="margin: 0; padding: 0; margin-top: 10px"></div>
		<div style="display: none;">
		</div>
	</body>
</html>
