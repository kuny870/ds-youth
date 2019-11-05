<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
  <head>

    <jsp:include page="/WEB-INF/views/layouts/header.jsp" flush="false" />

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	<script>
		var contextPath = '${contextPath}';
		var resourcesPath = '${resourcesPath}';
	</script>
    <link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700&amp;subset=korean" rel="stylesheet">
    <link href="${resourcesPath}/assets/css/reset.css" rel="stylesheet">
    <link href="${resourcesPath}/assets/css/common.css" rel="stylesheet">
    <link href="${resourcesPath}/assets/css/general.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  </head>

<body>

	<div class="container">
           <div class="header">
           <h1>투표 관리</h1>
       </div>
       <ul class="tab x2 clearfix">
           <li>
               <button type="button" class="btn-tab selected" onclick="window.location.reload()">진행중인 투표</button>
           </li>
           <li>
               <button type="button" class="btn-tab" onclick="voteCompleted()">완료된 투표</button>
           </li>                
       </ul>
       <div class="form">
           <%-- <form enctype="application/x-www-form-urlencoded" id="addressForm">
           		<input type="hidden" name="loginId" value="${login.loginId}"/>
           		<div class="form-middle">
                   <label>
                       <input type="text" id="name" name="name" placeholder="주소명  ex) 집,학교">
                   </label>
                   
                   <div class="form-postal clearfix">
                       <input class="input-postal" type="text" id="postcode" name="zipcode" placeholder="우편번호" readonly="readonly">
                       <button class="btn-postal" type="button" onclick="execDaumPostcode()" >우편번호 찾기</button>
                   </div>
                   
                   <label>
                       <input type="text" id="address" name="addr1" placeholder="도로명 주소" readonly="readonly">                        
                   </label>                        
                   <label>
                       <input type="text" id="detailAddress" name="addr2" placeholder="상세주소">                        
                   </label>                                                
               </div>
               <div class="form-bottom">                
                   <button class="btn" type="submit">주소 저장</button>
               </div>
           </form> --%>
       </div>
   </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/vote.js?${nowTime}"></script>

</html>
