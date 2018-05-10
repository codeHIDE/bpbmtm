<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>商户照片</title>
		<link href="<s:url value='/js/jqui/ligerUI/skins/Aqua/css/ligerui-all.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/ligerui-icons.css'/>" rel="stylesheet" type="text/css" />
		<link href="<s:url value='/js/jqui/ligerUI/skins/Gray/css/all.css'/>" rel="stylesheet" type="text/css" />
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
		<script src="<s:url value='/js/jquery/jquery-ui-1.7.1.custom.min.js'/>"></script>
		<script src="<s:url value='/js/My97DatePicker_1/WdatePicker.js'/>"></script>
		<script src="<s:url value='/js/common.js'/>"></script>
		<script>
			//滚动条位置
			function GetPageScroll() 
			{ 
				var x, y; 
				if(window.pageYOffset) 
				{    // all except IE    
					y = window.pageYOffset;    
					x = window.pageXOffset; 
				} else if(document.documentElement && document.documentElement.scrollTop) 
				{    // IE 6 Strict    
					y = document.documentElement.scrollTop;    
					x = document.documentElement.scrollLeft; 
				} else if(document.body) {    // all other IE    
					y = document.body.scrollTop;    
					x = document.body.scrollLeft;   
				} 
				return {X:x, Y:y};
			}
		</script>
		<script language="javascript" type="text/javascript">
			$(
				function(){
					if(requestScope.mess=='Exception'){
						alert("出错");
					}else if(requestScope.mess=='FondImage'){
						alert("没有找到此图片");
					}else if(requestScope.mess=='Succ'){
						alert("下载成功");
					}
				}
			)
			function cls(){
				window.parent.$.ligerDialog.close();
	            window.parent.$(".l-dialog,.l-window-mask").remove();
			}
			function renZheng(){
				if(confirm("是否确认操作？")){
					$.post("<s:url value='/subMerInfo!manual.ac'/>",
					{'id':$("#subMerId").val()},
					function(data){
						if(data=='Y'){
							parent.location.reload();
							window.parent.$.ligerDialog.close();
							window.parent.$(".l-dialog,.l-window-mask").remove();
							alert("成功！");
						}else{
							alert("失败！");
						}
					},"text");
				}
			}
			
			function maxImg(url,title){
				$.post("<s:url value='/subMerInfo!getMaxImgageUrl.ac'/>",
					{'subMerInfo.subMerId':$("#subMerId").val(),'id':'1',
					'imgRealPath':url},
					function(data){
						if(data=='N'){
							alert("成功！");
						}else{
							var image=data.split("|");
							var tiger="<img title='"+title+"' width='"+(image[1]-30)+"px'" ;
							tiger=tiger+"height='"+(image[2]-30)+"px'" 
							tiger=tiger+"src='<s:url value="/subMerInfo!getImgIO.ac" />?";
							tiger=tiger+"imgRealPath="+image[0]+"'/>"
							tiger=tiger+"<br/>"
							var odiv=document.getElementById(url);  
							var strip=GetPageScroll();
							document.getElementById("show").style.display ="block";
							document.getElementById("show").style.width=(image[1]-30)+'px';
							document.getElementById("show").style.height=(image[2]-30)+'px';
							//alert(odiv.getBoundingClientRect().top-image[2]);
							if(odiv.getBoundingClientRect().left<200){
								//如果小图片距离页面底部太近则调整div的显示高度
								document.getElementById("show").style.left=odiv.getBoundingClientRect().left+'px';
							}else if((odiv.getBoundingClientRect().left+image[1])<600){
								//如果小图片距离页面底部不远，页面显示大图片不完全则调整div高度。
								document.getElementById("show").style.left=(odiv.getBoundingClientRect().left-image[1]/2)+'px';
							}else{
								document.getElementById("show").style.left=(odiv.getBoundingClientRect().left-image[1]/2)+'px';
							}
							document.getElementById("show").style.top=(odiv.getBoundingClientRect().top+60)+'px';
							document.getElementById("show").innerHTML=tiger;
						}
					},"text");
			}
			function hidediv() {
				document.getElementById("show").style.display ='none';
			}
			function dwn(name){
				window.location.href="<s:url value='/subMerInfo!downloadImg.ac'/>?id="+$("#subMerId").val()+"&imgType="+name;
			}
		</script>
		<style type="text/css">
			#show{display: none; position: absolute; top: 10%; left: 10%; width: 75%; height: 80%; padding:2px; border: 5px solid #E8E9F7; background-color: white; z-index:1002; overflow: auto;}
		</style>
 </head>
 <body style="padding: 4px;">
	<div id="content">
			<div style="text-align: center;">
				<input type="button" value="关 闭"  id="btn2" class="l-button" onclick="cls()" />
				<hr />
			</div>
	<input type="hidden" id="subMerId" name="id" value="${subMerInfo.subMerId }" />
		<s:if test="message!=null">
			<span><s:property value="message" /> </span>
		</s:if>
		<div class="box_system">
			<s:if test="imgUrlList.size==0||imgUrlList==null">
			<div style="color: red;text-align: center;">
				<span>没有相关图片信息</span>
			</div>
			</s:if>
			<s:else>
			<table>
			<tr>
			<s:iterator var="url" value="imgUrlList" status="st">
			<s:if test="#st.index==6">
				</tr><tr>
			</s:if>
			<td height="110px" width="110px">
			<div style="text-align: center;">
				<img title="<s:property value="#url[3]" />" id="<s:property value="#url[0]" />"
					width="<s:property value="#url[1]" />" 
					height="<s:property value="#url[2]" />" 
					src='<s:url value="/subMerInfo!getImgIO.ac" />?
					imgRealPath=<s:property value="#url[0]" />'
					onmouseover="maxImg('<s:property value="#url[0]" />',
					'<s:property value="#url[3]" />')" onmouseout="hidediv()"/><br/>
				<b><s:property value="#url[3]" /></b>
				<br/>
				<input id="rz" class="l-button" style="display: <s:property value="#url[4]" />" 
							type="button" value="手动认证" onclick="renZheng()"/>
				<input type="button" class="l-button" value="下载" onclick="dwn('<s:property value="#url[5]" />')" />
			</div>
			</td>
			</s:iterator>
			</tr>
			</table>
			<div ></div>
			</s:else>
			<div style="text-align: center;">
			<br /><br />
				<b id="mes" style="color: red;font-size: 15px;"></b>
					<script language="JavaScript" type="text/javascript">
					if (!!window.ActiveXObject || "ActiveXObject" in window) {
						document.getElementById("mes").innerHTML="您当前使用IE浏览器，"+
								"如果更新了图片。请刷新子商户列表！";
					}
				</script>
			</div>
		</div>
	</div>
	<div id="show">
	</div>
 </body>
</html>
