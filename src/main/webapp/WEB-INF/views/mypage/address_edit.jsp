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
    <link href="${resourcesPath}/assets/css/reset.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/common.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/general.css?${nowTime}" rel="stylesheet">
  </head>

<body>

	<div class="container">
		<div class="header-product">
			 <span class="shop-link-login" onclick="mypage()" style="width:8%; height:53%;">
			 	<img src="${resourcesPath}/assets/images/back_btn.png" style="width: 100%; height:83%;">
			 </span>
			 <p class="head_title text-center">주소 관리</p>
			 
	         <ul class="tab x2 clearfix">
	              <li>
		              <button type="button" class="btn-tab selected" onclick="addressList()">내 주소</button>
	              </li>
	              <li>
	                  <button type="button" class="btn-tab" onclick="addressRegist()">새 주소 등록</button>
	              </li>                  
	         </ul>
	         <div class="form">
	             <form enctype="application/x-www-form-urlencoded" id="addressEditForm">
	             	<input type="hidden" name="id" value="${address.id}">
	             	<input type="hidden" name="loginId" value="${login.id}">
	                 <div class="form-middle">
	                     <label>
	                         <input type="text" class="md-input" id="name" name="name" value="${address.name }" placeholder="주소지명  ex) 집,학교">                        
	                     </label>
	                     
	                     <div class="form-postal clearfix">
	                         <input class="input-postal md-input" type="text" id="zipcode" name="zipcode" value="${address.zipcode }" placeholder="우편번호" readonly="readonly">
	                         <button class="btn-postal" type="button" onclick="execDaumPostcode()">우편번호 찾기</button>
	                     </div>
	                     
	                     <label>
	                         <input type="text" class="md-input" id="addr1" name="addr1" value="${address.addr1 }" placeholder="도로명 주소" readonly="readonly">
	                     </label>                        
	                     <label>
	                         <input type="text" class="md-input" id="addr2" name="addr2" value="${address.addr2 }" placeholder="상세주소">                        
	                     </label>                                                
	                     <label>
	                     	<input type="text" class="md-input" id="memo" name="memo" placeholder='메모' value="${address.memo }"/>
	                     </label>
	                 </div>
	                 <div class="form-bottom" style="margin-top: 20px;">                
	                     <button class="btn" type="submit">수정완료</button>
	                 </div>    
	             </form>
	         </div>
         </div>
     </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/address_edit.js?${nowTime}"></script>
<script>
	var loginId = '${login.loginId}';
</script>
	
</html>
