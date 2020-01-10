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
			<span class="shop-link-login" onclick="memberList()">
				<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
			</span>
			<p class="head_title text-center">팀원 정보</p>
			
            <div class="form">
                <form enctype="application/x-www-form-urlencoded" id="memberModifyForm">
                    <div class="form-middle">
                    	<input type="hidden" id="sTeamId" name="sTeamId" value="${memberSearch.teamId }" />
                    	<input type="hidden" id="sGroupId" name="sGroupId" value="${memberSearch.groupId }" />
                    	<input type="hidden" id="sNameKW" name="sNameKW" value="${memberSearch.nameKW }" />
                    	<input type="hidden" id="pageNo" name="pageNo" value="${memberSearch.pageNo }" />
                    	<input type="hidden" id="id" name="id" value="${member.id }" />
                    	
	            		
	            		<!-- 멤버 상태 값 : 일반 1 , 군인 2 , 해외 3 , 장기결석 4 , 새가족수료 5 , 졸업 6 , 기타 7 -->
                    	<div class="customer-select selectbox">
                            <select class="select-fix" id="memState" name="memState">
                            	<c:forEach var="memberState" items="${memberStateList }">
                            		<c:set var="selected" value="" />
									<c:if test="${memberState.id eq member.memState}">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${memberState.id}" ${selected }>${memberState.mState}</option>
								</c:forEach>
                            </select>                        
	                    </div>
	                   	
	                   	<label></label>
	            		 
	            		<!-- 이름 -->
                        <label>
                            <input type="text" class="md-input" id="name" name="name" placeholder="이름 (필수)" value="${member.name}">
                        </label>   
                        <!-- 생년월일 -->                     
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="dateOfBirth" name="dateOfBirth" placeholder="생년월일 (ex:870421) (선택)" value="${member.dateOfBirth}" maxlength="6" oninput="numberMaxLength6(this);" autocomplete="off">
                        </label>
                        <!-- 휴대폰 번호 -->
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="htel" name="htel" placeholder='휴대폰 "-" 빼고 입력 (선택)' value="${member.htel}" maxlength="11" oninput="numberMaxLength11(this);" autocomplete="off">
                        </label>
                        
                        
                        <c:set var="disabled" value=""/>
                        <c:if test="${login.authId > 2}">
                        	<c:set var="disabled" value="disabled"/>
                        	<input type="hidden" name="departId" value="${member.departId }">
                        	<input type="hidden" name="teamId" value="${member.teamId }">
                        </c:if>
                        
                        <!-- 부서 -->
                        <div class="customer-select selectbox">
	                            <select class="select-fix" id="departId" name="departId" onchange="getTeam(this.value);" ${disabled}>
	                            	<c:forEach var="depart" items="${departList }">
										<c:set var="selected" value="" />
										<c:if test="${depart.id eq member.departId}">
											<c:set var="selected" value="selected" />
										</c:if>
										<option value="${depart.id}" ${selected} >${depart.dName}</option>
									</c:forEach>
	                            </select>                        
	                    </div>
	                    
	                    <!-- 팀 -->
	                    <div class="customer-select selectbox">
                            <select class="select-fix" id="teamId" name="teamId" onchange="getGuider(this.value);" ${disabled}>
                            	<c:forEach var="team" items="${teamList }">
									<c:set var="selected" value="" />
									<c:if test="${team.id eq member.teamId}">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${team.id}" ${selected} >${team.tShortName}</option>
								</c:forEach>
                            </select>                        
	                   </div>
	                   
	                   <!-- 새가족일 경우 인도자 -->
	                   <div id="new-member" style="display: none;">
		                   <label>
	                            <input type="text" class="md-input" id="guider" name="guider" placeholder='인도자 (선택)' value="${member.guider}" autocomplete="off" style="margin-top: 6vw;">                        
	                       </label>
	                       <label>
	                            <span>등록일</span><input type="date" class="customer-calendar" id="memberRegDate" name="memberRegDate" value="${member.memberRegDate}">                        
	                       </label>
	                       <label>
	                            <span>수료일</span><input type="date" class="customer-calendar" id="memberGradDate" name="memberGradDate" value="${member.memberGradDate}">                        
	                       </label>
	                   </div>
                        
                        
                    </div>
                    
                    <!-- 성별 -->
                    <c:if test="${member.gender == '남'}">
					    <c:set var="genderMale" value="checked"/>
					</c:if>
					<c:if test="${member.gender == '여'}">
					    <c:set var="genderFemale" value="checked"/>
					</c:if>
					
                    <div class="form-bottom-radio">
                        <label class="radio-inline radio-gender"> 
                            <input type="radio" name="gender" value="남" ${genderMale }>
                            <span class="ico"></span>
                            <span class="text">남자</span>
                        </label>
                        <label class="radio-inline radio-gender"> 
                            <input type="radio" name="gender" value="여" ${genderFemale }> 
                            <span class="ico"></span>
                            <span class="text">여자</span>
                        </label>
                    </div>
                    
                    <!-- 리더 이상 -->
	    			<c:if test="${login.authId <= 6}">
	                    <div class="form-bottom form-bottom-fixed">                
	                        <button class="btn modify-btn" type="submit">수정</button>
	                    </div>
	                </c:if>
                </form>
                
                <!-- 리더 이상 -->
                <c:if test="${login.authId <= 6}">
	                <div class="form-bottom form-bottom-fixed">
	                   	<button class="btn remove-btn" onclick="memberRemove(${member.id})">삭제</button>
	                </div>
	            </c:if>
                
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/member/modify.js?${nowTime}"></script>
	
</html>
