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
			<p class="head_title text-center">청년부 관리</p>
			
            <div class="form">
                    <div class="form-middle">
                    	
                    	<!-- 관리자만 -->
                    	<c:if test="${login.authId == 1}">
							<div class="admin-list-div">
		                		<!-- <label class="admin-list-label">회원</label> -->
		                		<button class="basic-btn admin-list-btn" onclick="user()">회원 관리</button>
		                	</div>
					    </c:if>
					    
                    	<!-- 목사 이상 -->
					    <c:if test="${login.authId <= 2}">
							<div class="admin-list-div">
		                		<!-- <label class="admin-list-label">권한</label> -->
		                		<button class="basic-btn admin-list-btn" onclick="auth()">권한 관리</button>
		                	</div>
	                    	<div class="admin-list-div">
		                		<!-- <label class="admin-list-label">부서</label> -->
		                		<button class="basic-btn admin-list-btn" onclick="depart()">부서 관리</button>
		                	</div>
					    </c:if>
						
						<!-- 팀장 이상 -->
						<c:if test="${login.authId <= 3}">
							<div class="admin-list-div">
			                	<!-- <label class="admin-list-label">팀</label> -->
			                	<c:choose>
	                       			<c:when test="${departList.size() == 0}">
	                       				<button class="basic-btn admin-list-btn" onclick="openPopup('먼저 부서를 셋팅해주세요!')">팀 관리</button>
	                       			</c:when>
	                       			<c:otherwise>
	                       				<button class="basic-btn admin-list-btn" onclick="team()">팀 관리</button>
	                       			</c:otherwise>
		                        </c:choose>
			                </div>
		                </c:if>
		                
		                <!-- 목사 이상 -->
					    <c:if test="${login.authId <= 2}">
	                    	<div class="admin-list-div">
		                		<!-- <label class="admin-list-label">부서</label> -->
		                		<button class="basic-btn admin-list-btn" onclick="teamList()">팀원 관리</button>
		                	</div>
					    </c:if>
		                
		                <div class="admin-list-div">
		                	<!-- <label class="admin-list-label">순</label> -->
		                	<c:choose>
                       			<c:when test="${teamList.size() == 0}">
                       				<button class="basic-btn admin-list-btn" onclick="openPopup('먼저 팀을 셋팅해주세요!')">순 관리</button>
                       			</c:when>
                       			<c:otherwise>
                       				<button class="basic-btn admin-list-btn" onclick="group()">순 관리</button>
                       			</c:otherwise>
	                        </c:choose>
		                </div>
		                <div class="admin-list-div">
	                		<!-- <label class="admin-list-label">동기</label> -->
	                		<c:choose>
                       			<c:when test="${departList.size() == 0}">
                       				<button class="basic-btn admin-list-btn" onclick="openPopup('먼저 부서를 셋팅해주세요!')">동기 관리</button>
                       			</c:when>
                       			<c:otherwise>
                       				<button class="basic-btn admin-list-btn" onclick="samePeriod()">동기 관리</button>
                       			</c:otherwise>
	                        </c:choose>
	                	</div>
		            </div>
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

</html>
