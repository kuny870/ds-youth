<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>

    <jsp:include page="/WEB-INF/views/layouts/header.jsp" flush="false" />
    
    <!-- TODO : 공지 레이어 팝업 -->
    <%-- <jsp:include page="/WEB-INF/views/admin/pop/notice.jsp" flush="false" /> --%>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	<script>
		var contextPath = '${contextPath}';
		var resourcesPath = '${resourcesPath}';
	</script>
    <link href="${resourcesPath}/assets/css/reset.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/common.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/general.css?${nowTime}" rel="stylesheet">
	<link href="${resourcesPath}/assets/css/modalLayer.css?${nowTime}" />
	
  </head>
  <body>
    <div class="container">
   		<div class="header-product">
            <p class="main_title text-center" style="background: white;">
                 <img src="${resourcesPath }/assets/images/dsyouth_icon.png?${nowTime}" class="main-img">
                 <span class="main-title cursor-pointer" onclick="window.open('http://gofile.me/6NANj/xsaXoR5gx', '_blanck'); return false;"> 청년부</span>
            </p>
            <div class="mypage-wrap">
            
                <!-- 엘더 이상 -->
                <c:if test="${login.authId <= 6}">
                	<h3>관리자</h3>
                	<div class="mypage-rows">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/admin/list">청년부 관리</a>
	                    </div>
	                </div>
	             </c:if>
	             
	             <!-- 리더 이상 -->
	             <c:if test="${login.authId <= 4}">
                	<h3>팀</h3>
	                <div class="mypage-rows">
	                    <div class="mypage-row">
	                    	<c:choose>
                       			<c:when test="${departList.size() == 0 || teamList.size() == 0}">
                       				<a href="#" onclick="openPopup('부서와 팀을 먼저 셋팅해주세요!')">출석부</a>
                       			</c:when>
                       			<c:otherwise>
                       				<a href="${contextPath }/attendance/list?teamId=${login.teamId}" onclick="loadingbar()">출석부</a>
                       			</c:otherwise>
	                        </c:choose>
	                    </div>
	                    <div class="mypage-row">
	                    	<c:choose>
                       			<c:when test="${departList.size() == 0 || teamList.size() == 0}">
                       				<a href="#" onclick="openPopup('부서와 팀을 먼저 셋팅해주세요!')">팀별 관리</a>
                       			</c:when>
                       			<c:otherwise>
                       				<a href="${contextPath }/member/list?teamId=${login.teamId}" onclick="loadingbar()">팀별 관리</a>
                       			</c:otherwise>
	                        </c:choose>
	                    </div>
	                </div>
	                
	                <!-- 동기 -->
	                <h3>동기</h3>
					<div class="mypage-rows">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/samePeriod/list">동기별 보기</a>
	                    </div>
	                </div>
	                
	                <!-- 수련회 -->
	                <%-- <h3>수련회</h3>
	                <div class="mypage-rows">
	                	<div class="mypage-row">
               				<a href="${contextPath }/retreat/list">가족 편성표</a>
               				<a href="#" onclick="openPopup('준비중 입니다.')">가족 편성표</a>
	                    </div>
	                	<c:if test="${login.loginId == 'admin' 
	                					|| login.name == '문시은'
	                					|| login.name == '유호연'
	                					|| login.name == '이지선'
	                					|| login.name == '최민호'
	                					|| login.name == '맹주옥'
	                					|| login.name == '서유리'
	                					|| login.name == '김재달'}">
		                    <div class="mypage-row">
	                      		<a href="${contextPath }/admin/retreat/list">수련회 관리</a>
		                    </div>
	                    </c:if>
	                </div> --%>
				
					<%-- <h3>리더 자료</h3>
	                <div class="mypage-rows">
	                    <div class="mypage-row">
	                        <a href="${contextPath }/leaderInfo/list">리더배포자료</a>
	                    </div>
	                </div> --%>
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
                
                <!-- 
                <h3>게시판</h3>
                <div class="mypage-rows">
                    <div class="mypage-row">
                        <a href="${contextPath }/board/opinion">익명 게시판</a>
                    </div>
                    <div class="mypage-row">
                        <a href="${contextPath }/board/free">자유 게시판</a>
                    </div>
                </div>
                 -->
                 
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
                        <a href="javascript:logout('${login.loginId}')">로그아웃</a>  
                    </div>
                </div>
            </div>
        </div>
     </div>
  </body>
  
  <script src="${resourcesPath}/assets/js/mypage/main.js?${nowTime}" ></script>
  
</html>