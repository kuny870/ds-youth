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
			<span class="shop-link-login" onclick="team()">
				<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
			</span>
			<p class="shop_name text-center">
                 	팀 설정               
            </p>
            <div class="form">
                <form enctype="application/x-www-form-urlencoded" id="teamModifyForm">
                    <div class="form-middle">
                    	<input type="hidden" id="id" name="id" value="${team.id }" />
                        <label>
                            <input type="text" class="md-input" id="tShortName" name="tShortName" value="${team.tShortName }" placeholder="팀명 (short name)">
                        </label>
                        <label>
                            <input type="text" class="md-input" id="tFullName" name="tFullName" value="${team.tFullName}" placeholder="팀명 (full name)">
                        </label>  
                        <label>
                            <textarea style="margin-top:10px; margin-bottom:10px; height:60px;" rows="10" id="tTheme" name="tTheme" placeholder="팀 주제 말씀">${team.tTheme}</textarea>
                        </label>                      
                    </div>
                    
                    <!-- 팀장 이상만 수정 가능 -->
                    <c:if test="${login.authId <= 3}">
	                    <div class="form-bottom form-bottom-fixed" style="margin-bottom: 60px;">
	                        <button class="btn" type="submit">수정</button>
	                    </div>
                    </c:if>
                        
                </form>
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/admin/team/modify.js?${nowTime}"></script>
	
</html>
