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
			<p class="head_title text-center">기본정보 변경</p>
			
            <div class="form">
                <form enctype="application/x-www-form-urlencoded" id="profileForm">
                    <div class="form-middle">
                    	<input type="hidden" id="id" name="id" value="${login.id }" />
                        <label>
                            <input type="text" class="md-input" id="loginId" name="loginId" value="${login.loginId }" placeholder="아이디" readonly="readonly">
                        </label>
                        <label>
                            <input type="text" class="md-input" id="name" name="name" value="${login.name }" placeholder="이름" autocomplete="off">
                        </label>                        
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="dateOfBirth" name="dateOfBirth" value="${login.dateOfBirth }" placeholder="생년월일 주민번호 앞 6자리" maxlength="6" oninput="numberMaxLength(this);" autocomplete="off">
                        </label>                                                
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="htel" name="htel" value="${login.htel }" placeholder='휴대폰 번호 "-" 빼고 입력' maxlength="11" oninput="numberMaxLength(this);" autocomplete="off">                        
                        </label>
                        
                        <div class="customer-select">
	                            <select id="departId" name="departId" onchange="fnGetCtgSub(this.value);">
	                            	<c:forEach var="depart" items="${departList }">
										<c:set var="selected" value="" />
										<c:if test="${depart.id eq login.departId}">
											<c:set var="selected" value="selected" />
										</c:if>
										<option value="${depart.id}" ${selected} >${depart.dName}</option>
									</c:forEach>
	                            </select>                        
	                    </div>
	                    <div class="customer-select">
	                            <select id="teamId" name="teamId">
	                            	<c:forEach var="team" items="${teamList }">
										<c:set var="selected" value="" />
										<c:if test="${team.id eq login.teamId}">
											<c:set var="selected" value="selected" />
										</c:if>
										<option value="${team.id}" ${selected} >${team.tShortName}</option>
									</c:forEach>
	                            </select>                        
	                   </div>
                        
                    </div>
                    
                    <c:if test="${login.gender == '남'}">
					    <c:set var="genderMale" value="checked"/>
					</c:if>
					<c:if test="${login.gender == '여'}">
					    <c:set var="genderFemale" value="checked"/>
					</c:if>
					
                    <div class="form-bottom-radio">
                        <label class="radio-inline radio-gender"> 
                            <input type="radio" id="raido-male" name="gender" value="남" ${genderMale }>
                            <span class="ico"></span>
                            <span class="text">남자</span>
                        </label>
                        <label class="radio-inline radio-gender"> 
                            <input type="radio" id="raido-female" name="gender" value="여" ${genderFemale } > 
                            <span class="ico"></span>
                            <span class="text">여자</span>
                        </label>
                    </div>
                    <div class="form-bottom form-bottom-fixed">
                        <button class="btn modify-btn" type="submit">적용</button>
                    </div>
					
					<c:if test="${login.authId != 1}">
	                   <div class="form-bottom form-bottom-fixed">
	                        <button type="button" class="btn remove-btn" onclick="withdraw(${login.id})">회원탈퇴</button>
	                    </div>
                	</c:if>
                        
                </form>
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/profile.js?${nowTime}"></script>
	
</html>
