<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>清分信息</title>
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
			tr{
				height:30px;
			}
		</style>
		<style> 
* {margin:0;padding:0;font-size:12px;} 
html,body {height:100%;width:100%;} 
#content {background:#f8f8f8;padding:30px;height:100%;} 
#content a {font-size:30px;color:#369;font-weight:700;} 
#alert {border:1px solid #369;width:300px;height:150px;background:#e2ecf5;z-index:1000;position:absolute;display:none;} 
#alert h4 {height:20px;background:#369;color:#fff;padding:5px 0 0 5px;} 
#alert h4 span {float:left;} 
#alert h4 span#close {margin-left:210px;font-weight:500;cursor:pointer;} 
#alert p {padding:12px 0 0 30px;} 
#alert p input {width:120px;margin-left:20px;} 
#alert p input.myinp {border:1px solid #ccc;height:16px;} 
#alert p input.sub {width:60px;margin-left:30px;} 
</style>
		<script type="text/javascript">
    	var CustomersData = "{Rows:"+"<s:property value='merSettleStatictisListJson'/>".replace(/&quot;/g,"\"")+",Total:91}";
		    var aa = eval('('+CustomersData+')'); 
        $(function (){
            $("#maingrid4").ligerGrid({
           	 checkbox: true,
                columns: [
                { display: '商户名(商户号)', name: 'mid', align: 'left', width: '14%' ,align: 'center',
                	render:function(rowdata,rowindex){
		            	return rowdata.subMerName+"("+rowdata.mid+")";
	            	}
                },
         	 	{ display: '机构名(机构号)', name: 'merSysId', align: 'left', width: '18%' ,align: 'center',
         	 		render:function(rowdata,rowindex){
		            	return rowdata.merName+"("+rowdata.merSysId+")";
	            	}	
	            },
	            { display: '结算机构', name: 'bankName', align: 'left', width: '15%' ,align: 'center'},
	            { display: '结算账户名', name: 'accountName', align: 'left', width: '5%' ,align: 'center'},
	            { display: '结算账号', name: 'accountNum', align: 'left', width: '13%' ,align: 'center'},
	            { display: '清算日期', name: 'settleDate', width: '6%', align: 'center'},
	            { display: '清分状态', name: 'clearStatus', width: '6%', align: 'center',
	            	render:function(rowdata,rowindex){
	            		if(rowdata.clearStatus == '0'){
		            		return '未清分';
		            	}else if(rowdata.clearStatus == '1'){
		            		return '待清分';
		            	}else if(rowdata.clearStatus == '2'){
		            		return '<font style="color:Orange">清分成功<font>';
		            	}else if(rowdata.clearStatus == '3'){
		            		return '<font style="color:red">清分失败<font>';
		            	}else if(rowdata.clearStatus == '4'){
		            		return '<font style="color:Orange">清分给机构<font>';
		            	}else if(rowdata.clearStatus == '5'){
		            		return '<font style="color:Orange">财务打款<font>';
		            	}else if(rowdata.clearStatus == '6'){
		            		return '<font style="color:Orange">部分处理成功<font>';
		            	}else if(rowdata.clearStatus == '9'){
		            		return '<font style="color:Orange">处理中<font>';
		            	}else{
		            		return "";
		            	}
	            	}
             	},     
	            { display: '失败金额', name: 'faileAmt', width: '7%', align: 'center',type:'currency' }/*,
	            { display: '失败原因', name: 'clearDesc', width: '12%', align: 'center' }
	            ,
		 		{ display: '操作', name: 'null', width: '12%', align: 'center' ,render: function (rowdata, rowindex, value)
           	 	{
                     var h = "";
                  if (!rowdata._editing)
                    {
                    	 <c:if test="${purview['4021'] == '4021'}">
                    	 	h += "<a onclick='downloadReport("+rowdata.merSysId+")' style='cursor:pointer;color:blue;'>报表下载</a> ";
                    	 </c:if>
                    }
                    return h;
                }}*/
                ], 
                totalRender: f_totalRender,
                data: $.extend(true,{},aa),
                rownumbers:true,
                width: '100%', 
                height: '100%', 
                pageSizeOptions: [10,20,30,40,50], //由于逻辑问题，这里最大为50
                pageSize: 50
               // isChecked: f_isChecked, onCheckRow: f_onCheckRow, onCheckAllRow: f_onCheckAllRow
            });  
            $('#maingrid4').ligerGrid().set('dataAction','server');
            $("#pageloading").hide();
             $("#settleDate").ligerDateEditor({ showTime: true,format: "yyyy-MM-dd"});
        });
      
        function f_totalRender(data){
        	return "清分总金额："+$("#clearAmt").val()+"&nbsp;&nbsp;&nbsp;&nbsp;清分失败总金额："+$("#faileAmt").val();
        }
         function f_search()
        {
       		var merSysId=$("#merSysId").val();  
				var pattern = /^[\S]*$/; //不包含空格
		    	var number = /^[0-9]{1,20}$/;	//数字	
				if(merSysId!=""&&!number.test(merSysId)) {
					alert("机构商户号只能是数字！");
					$("#merSysId").focus();
					return false;
				}else{
					$("#form1").attr("action", '<s:url value="/MerSettleStatictis!merSettleStatictisFailureList.ac"/>?merSettleStatictis.clearStatus='+$("#clearStatus").val());
				  	$("#form1").attr("target", '_self');
				  	$("#form1").submit();
				}
        }
        
        
        function f_clean(){
        	$("#merSysId").val("");
        	$("#settleDate").val("");
        	
        }
        
			var checkedCustomer = [];
		 function f_open1()
        {	
			 $.ligerDialog.open({ url: '<s:url value="/pages/theReport/importFailureExcel.jsp"/>',
				 height:300,width: 580, isResize: false,title:'导入'});
/*         	var settleDate = $("#settleDate").val();
        	var ids = [];
        	var merSysIds = [];
        	for(var i = 0;i<checkedCustomer.length;i++){
        		var val = (checkedCustomer[i]).split(',');
        		ids[i] = val[0];
        		merSysIds[i] = val[1];
        	}
        	if(confirm("确认清分？")){
				xf();
				$.post("<s:url value='/clearing!clearingTruefone.ac'/>",
				{ids:$.ligerui.toJSON(ids),merSysIds:$.ligerui.toJSON(merSysIds),settleDate:settleDate},
				 function(data){
       				alert(data);
        			c();
        			window.parent.form1.submit();
					window.parent.$.ligerDialog.close();
					window.parent.$(".l-dialog,.l-window-mask").remove();
		        });
			} */
        }
        function f_Excel(){
        	var count = $("#count").val();
        	if(count<=0){
        		alert("日期："+$("#settleDate").val()+"，没有数据！");
        		return false;
        	}
        	 $("#form1").attr("action", '<s:url value="/MerSettleStatictis!merSettleStatictisdownloadExcel.ac" />');
			 $("#form1").attr("target", '_blank');
			 $( "#form1").submit();
		}        
		function f_ExcelBQ(){
			var settleDate = $("#settleDate").val();
      			var url="<s:url value="/MerSettleStatictis!checkFile.ac" />";
      			 $.post(url,{settleDate2:settleDate,merType:2},function(data){
       			if(data=='fone'){
     				alert("日期："+settleDate+"无补清记录文件");
       			}else{
       				window.location.href="<s:url value='/MerSettleStatictis!downloadFile.ac'/>?settleDate2="+settleDate+"&merType=2";
       			}
	        });
		}
		 function f_onCheckAllRow(checked)
        {
            for (var rowid in this.records)
            {
            	var val = this.records[rowid]['id']+","+this.records[rowid]['merSysId'];
                if(checked)
                    addCheckedCustomer(val);
                else
                    removeCheckedCustomer(val);
            }
        }
		 /*
        该例子实现 表单分页多选
        即利用onCheckRow将选中的行记忆下来，并利用isChecked将记忆下来的行初始化选中
        */
        
        function findCheckedCustomer(CustomerID)
        {
            for(var i =0;i<checkedCustomer.length;i++)
            {
                if(checkedCustomer[i] == CustomerID) return i;
            }
            return -1;
        }
        function addCheckedCustomer(CustomerID)
        {
            if(findCheckedCustomer(CustomerID) == -1)
                checkedCustomer.push(CustomerID);
        }
        function removeCheckedCustomer(CustomerID)
        {
            var i = findCheckedCustomer(CustomerID);
            if(i==-1) return;
            checkedCustomer.splice(i,1);
        }
        function f_isChecked(rowdata)
        {
            if (findCheckedCustomer(rowdata.CustomerID) == -1)
                return false;
            return true;
        }
        function f_onCheckRow(checked, data)
        {
            if (checked) addCheckedCustomer(data.id+","+data.merSysId);
            else removeCheckedCustomer(data.id+","+data.merSysId);
        }
    </script>
	</head>
	<body style="padding: 4px; overflow: hidden;">
	<div id="alert">
<h4><span>提示</span></h4>
<p>
  	清分处理中，请稍后。。。。
</p>
</div>
	<%@include file="/js/common.jsp" %>
		<div id="searchbar">
			<form id="form1" action="<s:url value="/pages/theReport/settleStatictisListFailure.jsp" />" method="post">
			<input type="hidden" id="count" value="<s:property value="count"/>"/>
			<input type="hidden" id="clearAmt" value="<s:property value="clearAmt"/>"/>
			<input type="hidden" id="faileAmt" value="<s:property value="faileAmt"/>"/>
				<table width="100%">
					<tr>
						<td style="text-align: right;">
							机构商户号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="merSettleStatictis.merSysId" id="merSysId" value="<s:property value="merSettleStatictis.merSysId"/>" />
						</td>
						<td style="text-align: right;">
							子商户号：
						</td>
						<td style="text-align: left;">
							<input type="text" name="merSettleStatictis.mid"  id="mid" value="<s:property value="merSettleStatictis.mid"/>" />
						</td>
						<td style="text-align: right;">
							交易日期：
						</td>
						<td style="text-align: left;">
							<input type="text" name="settleDate2" id="settleDate" value="<%=request.getParameter("settleDate2")==null?request.getAttribute("settleDate2"):request.getParameter("settleDate2")%>" />
						</td>
						<td style="text-align: right;">
							清分状态：
						</td>
						<td style="text-align: left;">
							<select id="clearStatus" name="clearStatus">
								<option value="-1" <c:if test="${param.clearStatus == -1}">selected</c:if>>请选择</option>
								<option value="0" <c:if test="${param.clearStatus == 0}">selected</c:if>>未清分</option>
								<option value="1" <c:if test="${param.clearStatus == 1}">selected</c:if>>待清分</option>
								<option value="2" <c:if test="${param.clearStatus == 2}">selected</c:if>>已清分</option>
								<option value="3" <c:if test="${param.clearStatus == 3}">selected</c:if>>清分失败</option>
								<option value="4" <c:if test="${param.clearStatus == 4}">selected</c:if>>清分给机构</option>
								<option value="5" <c:if test="${param.clearStatus == 5}">selected</c:if>>财务打款</option>
								<option value="6" <c:if test="${param.clearStatus == 6}">selected</c:if>>部分处理成功</option>
								<option value="9" <c:if test="${param.clearStatus == 9}">selected</c:if>>处理中</option>
							</select>
						</td>
						<td style="text-align: left;"><input id="btn" type="button" value="查 询" onclick="f_search()" class="l-button"/></td>
						<td style="text-align: left;"><input id="btn" type="button" value="重 置" onclick="f_clean()" class="l-button"/></td>
						<c:if test="${purview['4022'] == '4022'}">
							<td style="text-align: left;"><input id="btn" type="button" value="导入" onclick="f_open1()" class="l-button"/></td>
						</c:if>
						<c:if test="${purview['4023'] == '4023'}">
							<td style="text-align: left;"><input id="btn" type="button" value="导出" onclick="f_Excel()" class="l-button"/></td>
						</c:if>
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
		<script type="text/javascript"> 
var myAlert = document.getElementById("alert"); 
var reg = $("#xf"); 

function xf() 
{ 
	myAlert.style.display = "block"; 
	myAlert.style.position = "absolute"; 
	myAlert.style.top = "50%"; 
	myAlert.style.left = "50%"; 
	myAlert.style.marginTop = "-75px"; 
	myAlert.style.marginLeft = "-150px";
	mybg = document.createElement("div"); 
	mybg.setAttribute("id","mybg"); 
	mybg.style.background = "#000"; 
	mybg.style.width = "100%"; 
	mybg.style.height = "100%"; 
	mybg.style.position = "absolute"; 
	mybg.style.top = "0"; 
	mybg.style.left = "0"; 
	mybg.style.zIndex = "500"; 
	mybg.style.opacity = "0.3"; 
	mybg.style.filter = "Alpha(opacity=30)"; 
	document.body.appendChild(mybg);
	document.body.style.overflow = "hidden"; 
}

function c() {
	myAlert.style.display = "none"; 
    mybg.style.display = "none"; 
}
</script>
	</body>
</html>
