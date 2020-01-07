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
	                <button type="button" class="btn-tab" onclick="addressList()">내 주소</button>
	            </li>
	            <li>
	                <button type="button" class="btn-tab selected" onclick="window.location.reload()">새 주소 등록</button>
	            </li>                
	        </ul>
	        <div class="form">
	            <form enctype="application/x-www-form-urlencoded" id="registAddressForm">
	           		<input type="hidden" name="userId" value="${login.loginId}"/>
	           		<div class="form-middle">
	                   <label>
	                       <input type="text" class="md-input" id="name" name="name" placeholder="주소지명  ex) 집,학교">
	                   </label>
	                   
	                   <div class="form-postal clearfix">
	                       <input class="input-postal md-input" type="text" id="postcode" name="zipcode" placeholder="우편번호" readonly="readonly">
	                       <button class="btn-postal" type="button" onclick="execDaumPostcode()" >우편번호 찾기</button>
	                   </div>
	                   <label>
	                       <input type="text" class="md-input" id="address" name="addr1" placeholder="도로명 주소" readonly="readonly">                        
	                   </label>                        
	                   <label>
	                       <input type="text" class="md-input" id="detailAddress" name="addr2" placeholder="상세주소">                        
	                   </label>
	                   <!-- <div id="wrap" style="display:none;border:1px solid;width:310px;height:100%;margin:5px 0;position:relative">
					   		<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
					   </div> -->
	                   <label>
	                       <input type="text" class="md-input" id="memo" name="memo" placeholder='메모'>                        
	                   </label>                                                 
	               </div>
	               <div class="form-bottom">                
	                   <button class="btn" type="submit" style="margin-top: 20px;">주소 저장</button>
	               </div>
	           </form>
	       </div>
	    </div>
   </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/address_regist.js?${nowTime}"></script>
<script>
	var loginId = '${login.loginId}';
</script>
	
</html>
