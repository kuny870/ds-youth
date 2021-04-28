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
			<p class="head_title text-center">비밀번호 변경</p>
			
            <div class="form">
                <form enctype="application/x-www-form-urlencoded" id="pwEditForm">
                    <div class="form-middle">
                    	<input type="hidden" id="id" name="id" value="${login.id }" />
                    	<input type="hidden" id="loginId" name="loginId" value="${login.loginId }" />
                    	<label>
                            <input type="password" class="md-input" id="currentPw" name="currentPw" placeholder="현재 비밀번호">
                        </label>
                        <label>
                            <input type="password" class="md-input" id="loginPw" name="loginPw" placeholder="변경 비밀번호 - 4자 이상">
                        </label>
                        <label>
                            <input type="password" class="md-input" id="loginPwConfirm" name="loginPwConfirm" placeholder="변경 비밀번호 확인">
                        </label>
                    </div>
                    <div class="form-bottom form-bottom-fixed" style="margin-bottom: 60px; margin-top:30px;">
                        <button class="btn" type="submit">확인</button>
                    </div>    
                </form>
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/pw_edit.js?${nowTime}"></script>
	
</html>
