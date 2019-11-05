<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            <p class="shop_name text-center" style="background: white;">
                 <img src="${resourcesPath }/assets/images/dsyouth_logo.png" class="main-img"> <span class="main-title"> 청년부</span>
            </p>
            <div class="mypage-wrap">
            
                <!-- 리더 이상 -->
                <c:if test="${login.authId <= 6}">
                	<h3>관리자</h3>
                	<div class="mypage-rows">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/admin/list">청년부 관리</a>
	                    </div>
	                </div>
	            
                	<h3>팀</h3>
	                <div class="mypage-rows">
	                    <div class="mypage-row">
	                    	<c:choose>
                       			<c:when test="${departList.size() == 0 || teamList.size() == 0}">
                       				<a href="#" onclick="alert('부서와 팀을 먼저 셋팅해주세요!')">출석 관리</a>
                       			</c:when>
                       			<c:otherwise>
                       				<a href="${contextPath }/attendance/list?team=${login.team.tShortName}">출석 관리</a>
                       			</c:otherwise>
	                        </c:choose>
	                    </div>
	                    <div class="mypage-row">
	                    	<c:choose>
                       			<c:when test="${departList.size() == 0 || teamList.size() == 0}">
                       				<a href="#" onclick="alert('부서와 팀을 먼저 셋팅해주세요!')">팀원 관리</a>
                       			</c:when>
                       			<c:otherwise>
                       				<a href="${contextPath }/member/list?teamId=${login.teamId}">팀원 관리</a>
                       			</c:otherwise>
	                        </c:choose>
	                    </div>
	                </div>
				
					<h3>동기</h3>
					<div class="mypage-rows">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/samePeriod/list">동기 모임</a>
	                    </div>
	                </div>
				
					<h3>리더 자료</h3>
	                <div class="mypage-rows">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/leaderInfo/list">리더배포자료</a>
	                    </div>
	                </div>
              	</c:if>
	            
	            <%-- <h3>말씀</h3>
	            <div class="mypage-rows">
	            	<div class="mypage-row">
                        <a href="${contextPath }/bible/qt">오늘의 QT</a>
                    </div>
                    <div class="mypage-row">
                        <a href="${contextPath }/board/sermon">청년부 설교</a>
                    </div>
                </div> --%>
	            
               <%--  <h3>투표 & 설문</h3>
                <div class="mypage-rows">
                    <div class="mypage-row">
                        <a href="${contextPath }/vote">투표 만들기</a>
                        <a href="javascript:dev()">투표 만들기</a>
                    </div>
                    <div class="mypage-row">
                        <a href="${contextPath }/survey">설문 만들기</a>
                        <a href="javascript:dev()">설문 만들기</a>
                    </div>
                </div> --%>
                
                <h3>게시판</h3>
                <div class="mypage-rows">
                    <div class="mypage-row">
                        <a href="${contextPath }/board/opinion">익명 게시판</a>
                    </div>
                    <div class="mypage-row">
                        <a href="${contextPath }/board/free">자유 게시판</a>
                    </div>
                </div>
                
                <h3>설정</h3>
                <div class="mypage-rows">
                	<c:if test="${login.authId != 1}">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/mypage/profile">기본정보 변경</a>
	                    </div>
	                </c:if>
                    <%-- <div class="mypage-row">
                        <a href="${contextPath }/mypage/profile/detail">상세정보 관리</a>
                    </div>
                    <div class="mypage-row">
                        <a href="${contextPath }/mypage/address/list/${login.loginId}">내 주소 관리</a>
                    </div> --%>
                    <div class="mypage-row">
                        <a href="${contextPath }/mypage/password/edit">비밀번호 변경</a>
                    </div>
                </div>
                <h3>&nbsp;</h3>
                <div class="mypage-rows">
                    <div class="mypage-row">
                        <a href="javascript:logout(${login.id})">로그아웃</a>  
                    </div>
                    <c:if test="${login.authId != 1}">
	                    <div class="mypage-row">
	                        <a href="javascript:withdraw(${login.id})">회원탈퇴</a>        
	                    </div>
	                </c:if>
                </div>
            </div>
        </div>
     </div>
  </body>
  
  <script src="${resourcesPath}/assets/js/mypage/main.js?${nowTime}" ></script>
  
</html>