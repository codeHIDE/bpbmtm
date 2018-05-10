<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
	//" no-cache:强制缓存从服务器上获取新的页面
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	//告诉代理服务器它的缓存页面何时将过期
	response.setDateHeader("Expires", -10);
%>
<!--top start -->
<div id="topmain">
	<div id="top">
		<div style="height: 80px;">
			<a href="#"><img src="<s:url value="/images/logo2.png"/>"
					border="0" class="logo" /> </a>
		</div>

		<ul class="nav">
			<li>
				<a href="<s:url value="/merchantManage!serachMerchantDetail.ac" />"
					class="hover">商户管理</a>
			</li>	
				<li>
					<a href="<s:url value="/BorderInfo!BorderIn.ac" />"
						<c:if test="${ currentModule == 40}">class="hover"</c:if>>交易管理</a>
				</li>
			<c:if test="${modelNo_3=='3'}">
				<li>
					<a href="<s:url value="/merManager!initMerManager.ac" />"
						<c:if test="${ currentModule ==20}">class="hover"</c:if>>系统管理</a>
				</li>
			</c:if>
			<c:if test="${modelNo_4=='4'}">
				<li>
					<a href="<s:url value="/proxyInfo!initProxyInfo.ac" />"
						<c:if test="${ currentModule == 50}">class="hover"</c:if>>代理商管理</a>
				</li>
			</c:if>
			<c:if test="${modelNo_5=='5'}">
				<li>
					<a href="<s:url value="/orderInfo!toDownReport.ac" />"
						<c:if test="${ currentModule == 60}">class="hover"</c:if>>报表下载</a>
				</li>
			</c:if>
			<li>
				<a href="<s:url value="/exit!exit.ac" />">退出系统</a>
			</li>
		</ul>
		<ul class="sub">
			<!-- 商户管理 -->
			<c:if test="${currentModule == 10}">	
					<li>
						<a
							href="<s:url value="/BmerInfoIndex!BselectMerchantInfoIndex.ac" />">商户详情</a><span>丨</span>
					</li>
				
			</c:if>
			<!-- 交易管理 --> 
			<c:if test="${currentModule == 40}">
					<li>
						<a href="<s:url value="/orderInfo!initCurrentDeal.ac" />">当天交易查询</a><span>丨</span>
					</li>
				
					<li>
						<a href="<s:url value="/orderInfo!initHistoryDeal.ac" />">历史交易查询</a>
					</li>
			</c:if>
			<!-- 系统管理 -->
			<c:if test="${currentModule ==20}">
				<li>
					<a href="<s:url value="/merManager!initMerManager.ac" />">添加管理员</a>
				</li>
			</c:if>
			<!-- 代理商管理 -->
			<c:if test="${currentModule == 50}">
				<li>
					<a href="<s:url value="/proxyInfo!initProxyInfo.ac" />">代理商信息</a><span>丨</span>
				</li>
			</c:if>
			<!-- 报表下载 -->
			<c:if test="${currentModule == 60}">
				<li>
					<a href="<s:url value="/orderInfo!toDownReport.ac" />">报表下载</a>
				</li>
			</c:if>
			<!-- 系统设置 -->
			<!-- 
			<c:if test="${currentModule ==30}">
				<li>
					<a href="<s:url value="/busInfo!selectBusInfoList.ac" />">添加业务</a><span>丨</span>
				</li>
				<li>
					<a href="<s:url value="/busProduct!selectBusProductList.ac"/>">添加产品</a>
				</li>
			</c:if>
			 -->
		</ul>
	</div>
</div>
<!--top end -->

