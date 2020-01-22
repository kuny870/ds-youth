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
            <div class="header">
                <h1>회원가입</h1>
                <p>이미 가입하셨나요?<a href="${contextPath }/login" class="link">로그인</a></p>
            </div>
            
            <div class="form">
                <form enctype="application/x-www-form-urlencoded" id="joinForm">
                    <div class="form-middle">
                        <label>
                            <input type="text" class="md-input" id="loginId" name="loginId" placeholder="아이디 (필수)" value="" autocomplete="off">
                        </label>
                        <label>
                            <input type="password" class="md-input" id="loginPw" name="loginPw" placeholder="비밀번호 - 6자 이상 (필수)" value="" autocomplete="off">
                        </label>
                        <label>
                            <input type="password" class="md-input" id="loginPwConfirm" name="loginPwConfirm" placeholder="비밀번호 확인 (필수)" value="" autocomplete="off">
                        </label>
                        <label>
                            <input type="text" class="md-input" id="name" name="name" placeholder="이름 (실명 필수)" value="" autocomplete="off">
                        </label>                        
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="dateOfBirth" name="dateOfBirth" placeholder="생년월일 (ex:870421) (선택)" value="" maxlength="6" oninput="numberMaxLength6(this);" autocomplete="off">
                        </label>                                                
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="htel" name="htel" placeholder='휴대폰 "-" 빼고 입력 (선택)' value="" maxlength="11" oninput="numberMaxLength11(this);" autocomplete="off">
                        </label>
                        
                        <div class="customer-select">
                            <select id="departId" name="departId" onchange="fnGetCtgSub(this.value);">
                            	<option value="" >부서선택 (필수)</option>
                            	<c:forEach var="depart" items="${departList}">
									<option value="${depart.id}" >${depart.dName}</option>
								</c:forEach>
                            </select>                        
	                    </div>
	                    <div class="customer-select">
                            <select id="teamId" name="teamId">
                            	<option value="">팀선택 (필수)</option>
                            </select>                        
	                    </div>
	                   
                    </div>
                    <div class="form-bottom-radio">
                        <label class="radio-inline radio-gender"> 
                            <input type="radio" name="gender" value="남">
                            <span class="ico"></span>
                            <span class="text">남자</span>
                        </label>
                        <label class="radio-inline radio-gender"> 
                            <input type="radio" name="gender" value="여"> 
                            <span class="ico"></span>
                            <span class="text">여자</span>
                        </label>
                    </div>
                    <div class="form-bottom form-bottom-fixed">                
                        <button class="btn" type="submit">확인</button>
                    </div>    
                </form>
            </div>
            
        </div>
  </body>
  
  <script src="${resourcesPath}/assets/js/auth/join.js?${nowTime}"></script>
</html>
