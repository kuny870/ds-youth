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
   				<span class="shop-link-login" onclick="mypage()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="head_title text-center">동기별 보기</p>
            
				<div class="div-container-non-width">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">
							
			                <table>
			                    <thead>
			                        <tr>
			                        	<th class="th-18p0">부서</th>
			                        	<th class="th-20p0">탄생년</th>
			                            <th>동기명</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="samePeriod" items="${samePeriodList}" varStatus="i">
			                    		<tr>
			                    			<td>
			                    				<c:choose>
												    <c:when test="${year - samePeriod.birthYear < 27}">
					                    				1청
						                            </c:when>
						                            <c:otherwise>
						                            	2청
						                            </c:otherwise>
						                        </c:choose>
			                    			</td>
			                    			
			                    			<td>${samePeriod.birthYear}</td>
			                    			
				                            <td class="css-a">
				                            	<c:choose>
				                            		<c:when test="${year - samePeriod.birthYear < 18}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=${fn:substring(samePeriod.birthYear,2,4)}&gubun=0">고등부 (${samePeriod.cnt})</a>
						                            </c:when>
				                            		<c:when test="${year - samePeriod.birthYear == 18}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=0&gubun=0">예비1학년 (${samePeriod.cnt})</a>
						                            </c:when>
												    <c:when test="${year - samePeriod.birthYear == 19}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=1&gubun=1">1학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 20}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=2&gubun=2">2학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 21}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=3&gubun=3">3학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 22}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=4&gubun=4">4학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 23}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=5&gubun=5">5학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 24}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=6&gubun=6">6학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 25}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=7&gubun=7">7학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:when test="${year - samePeriod.birthYear == 26}">
					                    				<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=8&gubun=8">8학년 (${samePeriod.cnt})</a>
						                            </c:when>
						                            <c:otherwise>
						                            	<a href="${contextPath }/samePeriod/detail?sId=${samePeriod.id}&name=${fn:substring(samePeriod.birthYear,2,4)}&gubun=9">${fn:substring(samePeriod.birthYear,2,4)}년생 (${samePeriod.cnt})</a>
						                            </c:otherwise>
						                        </c:choose>
				                            </td>
				                        </tr>
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
				<div class="div-temp1"></div>
				
    	</div>
    </div>    

  </body>
  
</html>
