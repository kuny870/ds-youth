<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
<fmt:formatDate var="nowDate" value="${now}" pattern="yyyy-MM-dd HH:mm:ss" />
<c:set var="nowTime" value="${now.time}" scope="request" />

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--IE최신브라우저로 변경-->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/images/dsyouth_icon.png" />	<!-- andriod 홈 아이콘 (128 * 128) -->
<link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/dsyouth_icon.png" />	<!-- andriod 홈 아이콘 (128 * 128) -->
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/assets/images/dsyouth_icon.png" />	<!-- apple 홈 아이콘 (114 * 114)-->

<%-- <link rel="apple-touch-startup-image" href="${pageContext.request.contextPath}/resources/assets/images/dsyouth_theme.png">	<!-- 웹앱 띄울때 startup 화면 (720 * 1280) -->
<!-- SPA (Single Page Application & Routing) 적용시 적용가능 -->
<meta name="apple-mobile-web-app-capable" content="yes" />	<!-- url주소창 숨기기 -->
<meta name="apple-mobile-web-app-status-bar-style" content="blue" />	<!-- 상태바 색상 --> --%>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700&amp;subset=korean" rel="stylesheet">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/common/common.js?${nowTime}"></script>

<html><head><title>DSYouth</title></head></html>
