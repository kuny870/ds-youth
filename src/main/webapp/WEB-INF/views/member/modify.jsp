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
            
            	
            	<form id="registProfileImgForm" enctype="multipart/form-data">
            		<input type="hidden" id="memberId" name="memberId" value="${member.id }" />
            		<input type="hidden" id="profileImg" name="profileImg" value="${member.originProfileImg }" />
            		<div class="form-middle2">
            		
	           			<c:choose>
		           			<c:when test="${member.replaceProfileImg ne null and member.replaceProfileImg ne ''}">
		           				<div class="profile-img-wrap">
		           					<label class="member-modify-profileImg-label">
		           						<img src="${resourcesPath}/assets/images/profileImg/${member.replaceProfileImg}" for="profile-img-change" id="member-modify-profileImg" class="member-modify-profileImg">
		           						<img src="${resourcesPath}/assets/images/btn_close.png" id="btn_close" style="width:12px; margin-bottom: 105px; cursor: pointer;" onclick="profileImgReset()">
		           					</label>
		           				</div>
		           			</c:when>
		           			<c:otherwise>
	           					<div class="profile-img-wrap">
									<label id="profile-img-change-label" for="profile-img-change" class="member-modify-originImg-label">
										<img src="${resourcesPath}/assets/images/profile_img.jpg" class="member-modify-originImg">
									</label>
								</div>
	           				</c:otherwise>
	           			</c:choose>
           		
	            		<div class="member-modify-profileImg-regist-div1">
	            			<div class="member-modify-profileImg-regist-div2">
	            				<span class="member-modify-profileImg-regist-span">프로필 사진 : </span>
	            				<input type="file" accept=".jpg, .jpeg, .png" id="originImg" name="originImg" class="member-modify-profileImg-regist-input">
	            			</div>
	            		</div>
	            	</div>
           		</form>
           		
                <form enctype="multipart/form-data" id="memberModifyForm" accept-charset="UTF-8">
                    <div class="form-middle">
                    	<input type="hidden" id="sTeamId" name="sTeamId" value="${memberSearch.teamId }" />
                    	<input type="hidden" id="sGroupId" name="sGroupId" value="${memberSearch.groupId }" />
                    	<input type="hidden" id="sNameKW" name="sNameKW" value="${memberSearch.nameKW }" />
                    	<input type="hidden" id="pageNo" name="pageNo" value="${memberSearch.pageNo }" />
                    	<input type="hidden" id="id" name="id" value="${member.id }" />
                    	<input type="hidden" id="memoInput" name="memoInput" value="${member.memo}" />
                    	
	            		
	            		<%-- <c:choose>
	            			<c:when test="${member.replaceProfileImg ne null }">
	            				<img src="${resourcesPath}/assets/images/profile_img/${member.replace_profile_img}" for="profile-img-change" style="border-radius:5px; width:40px; height:40px;">
	            			</c:when>
	            			<c:otherwise>
	            				<div class="profile-img-wrap" style="text-align: center;">
									<label id="profile-img-change-label" for="profile-img-change" style="width:80%"><div id="output"></div><span><img src="${resourcesPath}/assets/images/profile_img.jpg" style="cursor: pointer;"></span></label>
								</div>
	            			</c:otherwise>
	            		</c:choose> --%>
	            		
	            		<!-- 멤버 상태 값 : 일반 1 , 군인 2 , 해외 3 , 장기결석 4 , 새가족수료 5 , 졸업 6 , 기타 7 -->
                    	<div class="customer-select">
                            <select id="memState" name="memState">
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
                        <%-- <label>
                            <input type="number" pattern="\d*" class="md-input" id="dateOfBirth" name="dateOfBirth" placeholder="생년월일 (ex:870421) (선택)" value="${member.dateOfBirth}" maxlength="6" oninput="numberMaxLength(this);" autocomplete="off">
                        </label> --%>
                        <!-- 휴대폰 번호 -->
                        <label>
                            <input type="number" pattern="\d*" class="md-input" id="htel" name="htel" placeholder='휴대폰 "-" 빼고 입력 (선택)' value="${member.htel}" maxlength="11" oninput="numberMaxLength(this);" autocomplete="off">
                        </label>
                        
                        
                        <c:set var="disabled" value=""/>
                        <c:if test="${login.authId > 2}">
                        	<c:set var="disabled" value="disabled"/>
                        	<input type="hidden" class="departId" name="departId" value="${member.departId }">
                        	<input type="hidden" class="teamId" name="teamId" value="${member.teamId }">
                        </c:if>
                        
                        <!-- 부서 -->
                        <div class="customer-select">
	                            <select class="departId" id="departId" name="departId" onchange="getTeam(this.value);" ${disabled}>
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
	                    <div class="customer-select">
                            <select class="teamId" id="teamId" name="teamId" onchange="getNewMemberDetail(this.value);" ${disabled}>
                            	<c:forEach var="team" items="${teamList }">
									<c:set var="selected" value="" />
									<c:if test="${team.id eq member.teamId}">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${team.id}" ${selected} >${team.tShortName}</option>
								</c:forEach>
                            </select>                        
	                   </div>
	                   
	                   <!-- 동기설정 -->   
                       <div class="customer-select">
                            <select id="samePeriodId" name="samePeriodId">
                            	<option value="" >동기선택</option>
                            	<c:forEach var="sp" items="${samePeriodList}">
                            		<c:set var="selected" value="" />
                            		<c:if test="${sp.id eq member.samePeriodId}">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${sp.id}" ${selected }>${sp.birthYear} 동기</option>
								</c:forEach>
                            </select>                        
	                   </div>
	                   
	                   <p class="jiche-p2">지체 상황</p>
                   	   <label>
                           <textarea class="jiche-textarea" rows="20" id="memo" name="memo" placeholder=""></textarea>
                       </label>
	                   
	                   <!-- 새가족일 경우 인도자 -->
	                   <div id="new-member" style="display: none;">
		                   <label>
	                            <input type="text" class="md-input" id="guider" name="guider" placeholder='인도자 (선택)' value="${member.guider}" autocomplete="off" style="margin-top: 6vw;">                        
	                       </label>
	                       <div class="member-modify-guider-div" style="width: 80%; margin-top: 15px;">
		                       <table class="member-modify-guider-table" style="width: 100%; table-layout: fixed;">
			                       	<tr>
				                       	<td>
				                            <span class="member-modify-guider-text" style="margin-left: 10px;">등록일</span>
				                            <input style="margin-right: 5px;" type="date" class="customer-calendar" id="memberRegDate" name="memberRegDate" value="">
				                        </td>
				                        <td>
				                            <span class="member-modify-guider-text" style="margin-left: 15px;">수료일</span>
				                            <input style="margin-left: 5px;" type="date" class="customer-calendar" id="memberGradDate" name="memberGradDate" value="">                        
				                       </td>
			                       </tr>
		                       </table>
	                       </div>
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
	                        <button type="button" class="btn modify-btn" onclick="memberModify()">적용</button>
	                    </div>
	                </c:if>
                </form>
                
                <!-- 리더 이상 -->
                <c:if test="${login.authId <= 6}">
	                <div class="form-bottom form-bottom-fixed">
	                   	<button type="button" class="btn remove-btn" onclick="memberRemove(${member.id})">삭제</button>
	                </div>
	            </c:if>
                
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/member/modify.js?${nowTime}"></script>
<script>
	$(document).ready(function(){
		$('#memberRegDate').val('${member.memberRegDate}');
		$('#memberGradDate').val('${member.memberGradDate}');
	});
</script>
	
</html>
