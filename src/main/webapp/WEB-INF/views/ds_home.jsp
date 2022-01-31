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
<link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/assets/images/logo/ds_logo.png?${nowTime}" />	<!-- andriod 홈 아이콘 (128 * 128) -->
<link rel="icon" href="${pageContext.request.contextPath}/resources/assets/images/logo/ds_logo.png?${nowTime}" />	<!-- andriod 홈 아이콘 (128 * 128) -->
<link rel="apple-touch-icon" href="${pageContext.request.contextPath}/resources/assets/images/logo/ds_logo.png?${nowTime}" />	<!-- apple 홈 아이콘 (114 * 114)-->

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
<script src="${pageContext.request.contextPath}/resources/assets/js/sweetalert2.js"></script>
<script src="${pageContext.request.contextPath}/resources/assets/js/common/common.js?${nowTime}"></script>

<html>
  <head>
  
  	<head>
		<title>동성교회 주일학교</title>
		
		<style>
		
			/* 폰 */
			@media (max-width: 679px) {
			
				.container {
					text-align: center;
					margin-top: 70px;
				}
				
				.logo1 {
				
				}
				.logo2 {
					margin-top: 10px;
				
				}
				.logo3 {
					margin-top: 10px;
				}
				
				.dsyouth {
					width: 130px;
					cursor: pointer;
				}
				
				.dshigh {
					width: 130px;
					margin-left: 20px;
					cursor: pointer;
				}
				
				.dsmiddle {
					width: 130px;
					cursor: pointer;
				}
				
				.dselementary {
					width: 130px;
					margin-left: 20px;
					cursor: pointer;
				}
				
				.dschildhood {
					width: 130px;
					cursor: pointer;
				}
				
				.dskinder {
					width: 130px;
					margin-left: 20px;
					cursor: pointer;
				}
			}
			
			/* 테블릿 */
			@media (min-width: 680px) and (max-width: 1050px) {
			
				.container {
					text-align: center;
					margin-top: 40px;
				}
				
				.logo1 {
				
				}
				.logo2 {
					margin-top: 10px;
				
				}
				.logo3 {
					margin-top: 10px;
				}
				
				.dsyouth {
					width: 170px;
					cursor: pointer;
				}
				
				.dshigh {
					width: 170px;
					margin-left: 20px;
					cursor: pointer;
				}
				
				.dsmiddle {
					width: 170px;
					cursor: pointer;
				}
				
				.dselementary {
					width: 170px;
					margin-left: 20px;
					cursor: pointer;
				}
				
				.dschildhood {
					width: 170px;
					cursor: pointer;
				}
				
				.dskinder {
					width: 170px;
					margin-left: 20px;
					cursor: pointer;
				}
				
			}
			
			/* pc */
			@media (min-width: 1051px) {
			
				.container {
					text-align: center;
					margin-top: 40px;
				}
				
				.logo1 {
				
				}
				.logo2 {
					margin-top: 10px;
				
				}
				.logo3 {
					margin-top: 10px;
				}
				
				.dsyouth {
					width: 170px;
					cursor: pointer;
				}
				
				.dshigh {
					width: 170px;
					margin-left: 20px;
					cursor: pointer;
				}
				
				.dsmiddle {
					width: 170px;
					cursor: pointer;
				}
				
				.dselementary {
					width: 170px;
					margin-left: 20px;
					cursor: pointer;
				}
				
				.dschildhood {
					width: 170px;
					cursor: pointer;
				}
				
				.dskinder {
					width: 170px;
					margin-left: 20px;
					cursor: pointer;
				}
				
			}
			
		
		</style>
		
		
	</head>


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	<script>
		var contextPath = '${contextPath}';
		var resourcesPath = '${resourcesPath}';
	</script>
    <link href="${resourcesPath}/assets/css/reset.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/common.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/general.css?${nowTime}" rel="stylesheet">
  </head>
  <body>
  	<div class="root">
  		<div class="main-header clearfix">
		</div>
		
	    <div class="container">
	    	<div class="logo1">
	    		<a href="http://keonhee.synology.me/dsyouth"><img src="${resourcesPath}/assets/images/logo/dsyouth_icon.png?${nowTime}" class="dsyouth"></a>
	    		<a href="http://keonhee.synology.me:2/dshigh"><img src="${resourcesPath}/assets/images/logo/dshigh_icon.png?${nowTime}" class="dshigh"></a>
	    	</div>
	    	<div class="logo2">
	    		<a href="http://keonhee.synology.me:2/dsmiddle"><img src="${resourcesPath}/assets/images/logo/dsmiddle_icon.png?${nowTime}" class="dsmiddle"></a>
	    		<a href="http://keonhee.synology.me:2/dselementary"><img src="${resourcesPath}/assets/images/logo/dselementary_icon.png?${nowTime}" class="dselementary"></a>
	    	</div>
	    	<div class="logo3">
	    		<img src="${resourcesPath}/assets/images/logo/dschildhood_icon.png?${nowTime}" class="dschildhood">
	    		<img src="${resourcesPath}/assets/images/logo/dskinder_icon.png?${nowTime}" class="dskinder">
	    	</div>
	    	
	    </div>
	   
	 </div>
	 
  </body>
  
  <script>
  
  </script>
  
</html>
