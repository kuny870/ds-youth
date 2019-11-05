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
			<span class="shop-link-login" onclick="mypage()">
				<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
			</span>
			<p class="shop_name text-center">
                 	상세정보 관리               
            </p>
            
            
            <div class="form">
                <form enctype="application/x-www-form-urlencoded" id="profileDetailForm">
                    <div class="form-middle">
                    	<input type="hidden" id="id" name="id" value="${login.id }" />
					
					<h1>기본 사역팀</h1>
					<label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	미디어팀
		            </label>
		            
		            <label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	선교팀
		            </label>
		            
		            <label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	워쉽팀
		            </label>
		            
		            <label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	전도팀
		            </label>
		            
		            <label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	찬양팀
		            </label>
		            <br/>

					<h3>선교 사역팀</h3>
					
                     <label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	주의나라팀
		            </label>
		            
		             <label class="radio-inline">
		            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
		            <input type="hidden" id="useCookie" name="useCookie" value="">
		            	섬실팀
		            </label>
		            
                    </div>
                    
                    <div class="form-bottom form-bottom-fixed" style="margin-bottom: 60px;">
                        <button class="btn" type="submit">확인</button>
                    </div>    
                </form>
            </div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/profile.js?${nowTime}"></script>
	
</html>
