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
			<span class="shop-link-login" onclick="retreat()">
				<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
			</span>
			<p class="head_title text-center">수련회 등록</p>
			
            <div class="form">
                <form id="retreatRegistForm">
                    <div class="form-middle">
                    	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
                    	
                    	<div class="customer-select selectbox">
                            <select class="select-fix" id="year" name="year">
                            	<c:forEach var="year" items="${yearList}">
                            		<c:set var="selected" value="" />
									<c:if test="${year eq thisYear}">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${year}" ${selected}>${year}년</option>
								</c:forEach>
                            </select>                        
	                    </div>
	                    
	                    <div class="customer-select selectbox">
                            <select class="select-fix" id="season" name="season">
                            	<c:forEach var="ss" items="${season}">
									<option value="${ss}">${ss}</option>
								</c:forEach>
                            </select>                        
	                    </div>
	                    
	                    <label></label>
	                    
	            		<!-- 수련회명 -->
                        <label>
                            <input type="text" class="md-input" id="retreatName" name="retreatName" placeholder="수련회명 (필수)" value="" autocomplete="off">
                        </label>
                        <!-- 대가족장 -->
                        <label>
                            <input type="text" class="md-input" id="headerFirst" name="headerFirst" placeholder="대가족장 (선택)" value="" autocomplete="off">
                        </label>
                        <!-- 시간청지기 -->
                        <label>
                            <input type="text" class="md-input" id="headerSecond" name="headerSecond" placeholder="시간청지기 (선택)" value="" autocomplete="off">
                        </label>   
                       
                    </div>
                    
                    <div class="form-bottom form-bottom-fixed" style="margin-top: 25px;">                
                        <button class="btn" type="submit">확인</button>
                    </div>
                </form>
            </div>
            
            <div class="div-temp1"></div>
            
        </div>
    </div>
   
</body>

<script src="${resourcesPath}/assets/js/retreat/admin/regist.js?${nowTime}"></script>
	
</html>
