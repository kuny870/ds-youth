<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
   				<span class="shop-link-login" onclick="adminList()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="head_title text-center">동기 관리</p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">
							
			                <table>
			                    <thead>
			                        <tr>
			                        	<th class="th-20p0">부서</th>
			                        	<th class="th-20p0">탄생년</th>
			                            <th>동기명</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="samePeriod" items="${samePeriodList}" varStatus="i">
			                    		<tr>
			                    			<td>
			                    				<c:choose>
												    <c:when test="${year - samePeriod.birthYear < 27}">
					                    				제1청년부
						                            </c:when>
						                            <c:otherwise>
						                            	제2청년부
						                            </c:otherwise>
						                        </c:choose>
			                    			</td>
			                    			
			                    			<td>
			                    				${samePeriod.birthYear}
			                    				<input type="hidden" id="${samePeriod.id}-input-hidden" value="${samePeriod.birthYear}" />
			                    			</td>
			                    			
				                            <td class="css-a">
				                            	<c:choose>
				                            		<c:when test="${year - samePeriod.birthYear < 19}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=${fn:substring(samePeriod.birthYear,2,4)}년생">${fn:substring(samePeriod.birthYear,2,4)}년생 (${samePeriod.cnt})</a>
						                            </c:when>
												    <c:when test="${year - samePeriod.birthYear == 19}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=1학년">1학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 20}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=2학년">2학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 21}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=3학년">3학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 22}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=4학년">4학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 23}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=5학년">5학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 24}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=6학년">6학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 25}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=7학년">7학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 26}">
					                    				<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=8학년">8학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:otherwise>
						                            	<a href="${contextPath }/admin/samePeriod/detail?sId=${samePeriod.id}&name=${fn:substring(samePeriod.birthYear,2,4)}년생">${fn:substring(samePeriod.birthYear,2,4)}년생 (${samePeriod.cnt})</a>
						                            </c:otherwise>
						                        </c:choose>
				                            </td>
				                            <td>
				                            	<!-- 엘더 이상만 삭제 가능 -->
	    										<c:if test="${login.authId <= 4}">
				                            		<button class="basic-btn admin-remove-btn" onclick="remove(${samePeriod.id})">삭제</button>
				                            	</c:if>
				                            </td>
				                        </tr>
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
	    
	    		<!-- 엘더 이상만 추가 가능 -->
	    		<c:choose>
           			<c:when test="${login.authId <= 4}">
           				<div class="div-temp2"></div>
				        <div class="layer_fixed">
					        <form id="registSamePeriodForm">
					        	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
					        
					        	<div style="text-align: center;">
					 	        	<input type="number" pattern="\d*" class="md-input" id="birthYear" name="birthYear" placeholder="ex) 1987" maxlength="4" oninput="numberMaxLength4(this);" autocomplete="off"></input>
					 	        </div>
						        <div class="form-bottom" style="text-align:center;">
					            	<button class="btn md-button" type="submit">추가</button>
					          	</div>
					        </form>
						</div>
					</c:when>
					<c:otherwise>
						<div class="div-temp1"></div>
					</c:otherwise>
				</c:choose>
			
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/admin/samePeriod/list.js?${nowTime}"></script>
  
  <script>
  	var year = ${year};
  </script>
  
</html>
