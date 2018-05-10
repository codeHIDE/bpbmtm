<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<script src="<s:url value='/js/jqui/jquery/jquery-1.5.2.min.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/core/base.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerToolBar.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDialog.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerGrid.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerFilter.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDrag.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerResizable.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerGrid.showFilter.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/jqui/ligerUI/js/plugins/ligerDateEditor.js'/>" type="text/javascript"> </script>
		<script src="<s:url value='/js/common.js'/>"> </script>
		<style type="text/css">
			tr {
				height: 30px;
			}
		</style>
		<script type="text/javascript">
			$(function() {
				$("#maingrid4")
						.ligerGrid(
								{
								columns : [
								{ display : '管理员编号', name : 'id', align : 'left', width : '14%', align : 'center' },
								{ display : '管理员登录名', name : 'loginName', align : 'left', width : '14%', align : 'center' },
								{ display : '管理员姓名', name : 'realName', width : '14%', align : 'center' },
								{ display : '管理员状态', name : 'status', width : '14%', align : 'center', render : function(rowdata, rowindex) {
										if (rowdata.status == '0') {
											return '未使用';
										} else if (rowdata.status == '1') {
											return '正在使用';
										} else if (rowdata.status == '2') {
											return '暂停';
										}
									}
								},
								{ display : '所属部门', name : 'department', width : '14%', align : 'center' },
								{ display : '管理员电话', name : 'phone', width : '10%', align : 'center' },
								{ display : '操作', name : 'null', width : '20%', align : 'center', render : function(rowdata, rowindex, value) {
										var h = "";
										if (!rowdata._editing) {
												h += "<a onclick='f_open6(" + rowdata.id + ")' style='cursor:pointer;color:blue;'> 编辑 </a> ";
											<c:if test="${purview['9021'] == '9021'}">
												var option = "";
												if (rowdata.status == '0') {
													option = "开通 ";
													h += "<a onclick='updateStatus(" + rowdata.id + ")' style='cursor:pointer;color:blue;'>" + option + "</a> ";
												} else if (rowdata.status == '1') {
													option = "暂停 ";
													h += "<a onclick='updateStatusStop(" + rowdata.id + ")' style='cursor:pointer;color:blue;'>" + option + "</a> ";
												} else if (rowdata.status == '2') {
													option = "恢复 ";
													h += "<a onclick='updateStatus(" + rowdata.id + ")' style='cursor:pointer;color:blue;'>" + option + "</a> ";
												}
											</c:if>
											<c:if test="${purview['9022'] == '9022'}">
												h += "<a onclick='f_open5(" + rowdata.id + ")' style='cursor:pointer;color:blue;'> 分配权限   </a> ";
											</c:if>
											<c:if test="${purview['9023'] == '9023'}">
												h += "<a onclick='goto(" + rowdata.id + ")'  style='cursor:pointer;color:blue;'> 密码重置 </a> ";
											</c:if>
										}
										return h;
									}
								} ],
						rownumbers : true,
						width : '100%',
						height : '100%',
						checkbox : false,
						pageSize : 15,
						sortName : null,
						sortOrder : null,
						root : 'Rows', //数据源字段名
						record : 'Total', //数据源记录数字段名
						pageParmName : 'page', //页索引参数名，(提交给服务器)
						pagesizeParmName : 'pagesize', //页记录数参数名，(提交给服务器)
						dataAciton : 'server',
						url : '<s:url value="/sysManage!selectSysManagerAllList.ac"/>?loginName='+$("#loginName").val() +'&realName='+$("#realName").val()
								+'&department='+$("#department").val()+'&status='+$("#status").val()
					});
			$('#maingrid4').ligerGrid().set('dataAction', 'server');

			$("#pageloading").hide();
			$("#txt1").ligerDateEditor( {
			showTime : true,
			format : "yyyy-MM-dd"
		});
		});function goto(smId){
       		 window.parent.$.ligerDialog.confirm('是否要重置密码?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/sysManage!updateSmPass.ac'/>',
				  {
				    id:smId
				  },
				  function(data,status){
				    if(data=="succ"){
				    	window.parent.$.ligerDialog.success('密码重置成功    新密码为：123456');
				    }else{
				    	window.parent.$.ligerDialog.error('密码重置失败！');
				    }
				  });
        	}
        });
        }
        
        
          function updateStatus(smId){
       		 window.parent.$.ligerDialog.confirm('确认开通管理员服务?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/sysManage!updateSysManageStatus.ac'/>',
				  {
				    id:smId,
				    status:1
				  },
				  function(data,status){
				    if(data=="succ"){
				     window.parent.$.ligerDialog.confirm('开通管理员成功', function (yes) {
        				 if(yes){
				    		window.location.reload();
				    	 }
				    });
				   }else{
				    	window.parent.$.ligerDialog.error('开通管理员失败！');
				    }
				  });
        	}
        });
        }
        
        function updateStatusStop(smId){
       		 window.parent.$.ligerDialog.confirm('确认停止管理员服务?', function (yes) {
        	if(yes){
        		$.post('<s:url value='/sysManage!updateSysManageStatus.ac'/>',
				  {
				    id:smId,
				    status:2
				  },
				  function(data,status){
				    if(data=="succ"){
				    	window.parent.$.ligerDialog.confirm('暂停管理员成功', function (yes) {
        				 if(yes){
				    		window.location.reload();
				    	 }
				    });
				    }else{
				    	window.parent.$.ligerDialog.error('暂停失败！');
				    }
				  });
        	}
        });
        }
        
        
        
        
        
        
        function itemclick()
        {
            g.options.data = $.extend(true,{}, CustomersData);
            g.showFilter();
        }
        
         function f_search()
        {
           $("#form1").submit();
        }
        
         function f_open5(smId)
        {    
            $.ligerDialog.open({ url: '<s:url value="/sysManage!selectModelListAll.ac"/>?id='+smId,
            					 height:450,width:320, isResize: false,title:'分配权限'});    
        }
        
        function f_open6(smId)
        {
            $.ligerDialog.open({ url: '<s:url value="/sysManage!selectSysManager.ac"/>?id='+smId,
            					 height:220,width:500, isResize: false,title:'修改管理员信息'});    
        }
        
        function f_search()
        {
			 $("#form1").submit();
        }
        
        function f_clean(){
        	$("#loginName").val("");
        	$("#realName").val("");
        	$("#department").val("");
        	$("#status").val("-1");
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
		<div id="searchbar">
			<form id="form1"
				action="<s:url value="/pages/sysManage/selectSysManagerList.jsp" />"
				method="post">
				<table width="80%">
					<tr>
						<td style="text-align: right;">
							管理员登录名：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="loginName" id="loginName" value="${param.loginName}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: right;">
							管理员姓名：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="realName" id="realName"  value="${param.realName}"  class="l-text-field"/>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;">
							管理员状态：
						</td>
						<td style="text-align: left;">
							<!-- <input type="hidden" value="${param.status}" id="status" /> -->
							<select name="status" id="status">
								<option value="-1">
									--请选择--
								</option>
								<option value="1"
									<c:if test="${param.status==1}">selected="selected"</c:if>>
									正在使用
								</option>
								<option value="2"
									<c:if test="${param.status==2}">selected="selected"</c:if>>
									暂停
								</option>
							</select>
						</td>
						<td style="text-align: right;">
							所属部门：
						</td>
						<td style="text-align: left;">
							<div class="l-text" style="width: 130px;">
								<input type="text" name="department" id="department" value="${param.department}" class="l-text-field"/>
							</div>
						</td>
						<td style="text-align: left;">
							<input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button" />
						</td>
						<td style="text-align: left;">
							<input id="btn2" type="button" value="重 置" onclick="f_clean()" class="l-button" />
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
