<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.net.*" %>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
%>

<%@ page import="java.io.UnsupportedEncodingException,java.net.URLEncoder" %>
<%!
// Copyright 2009 Google Inc. All Rights Reserved.
private static final String GA_ACCOUNT = "MO-15436829-1";
private static final String GA_PIXEL = "/common/ga.jsp";

private String googleAnalyticsGetImageUrl(
    HttpServletRequest request) throws UnsupportedEncodingException {
  StringBuilder url = new StringBuilder();
  url.append(GA_PIXEL + "?");
  url.append("utmac=").append(GA_ACCOUNT);
  url.append("&utmn=").append(Integer.toString((int) (Math.random() * 0x7fffffff)));
  String referer = request.getHeader("referer");
  String query = request.getQueryString();
  String path = request.getRequestURI();
  if (referer == null || "".equals(referer)) {
    referer = "-";
  }
  url.append("&utmr=").append(URLEncoder.encode(referer, "UTF-8"));
  if (path != null) {
    if (query != null) {
      path += "?" + query;
    }
    url.append("&utmp=").append(URLEncoder.encode(path, "UTF-8"));
  }
  url.append("&guid=ON");
  return url.toString().replace("&", "&amp;");
}
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<META HTTP-EQUIV="Pragma" CONTENT="no-cache" /> 
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" /> 
<META HTTP-EQUIV="Expires" CONTENT="0" /> 
<meta name="description" content="richerpay.com" />
<meta name="keywords" content="瑞银金融，银联" />
<meta name="author" content="richerpay.com" />

<meta http-equiv="Content-Type" content="application/xhtml+xml; charset=UTF-8"/>
<link type="image/x-icon" href="<c:url value='/img/favicon.ico' />" rel="shortcut icon" />
<link type="image/x-icon" href="<c:url value='/img/favicon.ico' />" rel="icon" />
<!-- <META name=viewport content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"> 
<META name=viewport content="width=device-width">-->

