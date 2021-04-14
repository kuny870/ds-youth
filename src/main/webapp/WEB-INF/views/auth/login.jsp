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
  	<div class="root">
  		<div class="main-header clearfix">
		    <nav>
		        <ul>
		            <li>
		                <!-- <span class="dsyouth-link" id="linkCopy">링크복사</span>
		            	<a class="dsyouth-link" id="clipURL" href="http://keonhee.synology.me/dsyouth">링크복사</a> -->
		            	<a href="http://gofile.me/6NANj/vkCQtOUIt" onclick="window.open(this.href, '_blanck'); return false;">사용가이드</a>
		            </li>
		        </ul>
		    </nav>
		</div>
		
	    <div class="container">
	      <div class="header">
	        <h1>동성교회 청년부</h1>
	        <p>가입 안하셨나요?<a href="${contextPath }/join" class="link">회원가입</a></p>
	      </div>
	      <div class="form">
	        <form id="loginForm">
	          <div class="form-middle-login">
	            <label>
	              <input type="text" class="md-input" id="loginId" name="loginId" placeholder="아이디" autocomplete="off">
	            </label>
	            <label>
	              <input type="password" class="md-input" id="loginPw" name="loginPw" placeholder="비밀번호" autocomplete="off">
	            </label>
	          </div>
	          <div class="form-bottom-link">
	            <label class="radio-inline">
	            <input type="checkbox" id="useCookieCheckBox" name="useCookieCheckBox">
	            <input type="hidden" id="useCookie" name="useCookie" value="">
                	<span>로그인 상태 유지</span>
	            </label>
	          </div>
	        
	          <div class="form-bottom">
	            <button class="btn" type="submit">로그인</button>
	          </div>
	        </form>
	      </div>
	    </div>
	   
	 </div>
	 
	 <%-- <jsp:include page="/WEB-INF/views/layouts/footer.jsp" flush="false" /> --%>
	 
  </body>
  
  <script src="${resourcesPath}/assets/js/auth/login.js?${nowTime}"></script>
  
</html>
