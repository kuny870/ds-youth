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
   				<span class="shop-link-login" onclick="samePeriod()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    
		    	<c:choose>
			    	<c:when test="${map.gubun == 0 || map.gubun == 9}">
                 		<p class="head_title text-center">${map.name}년생</p>
                    </c:when>
                    <c:otherwise>
                    	<p class="head_title text-center">${map.name}학년</p>
                    </c:otherwise>
                </c:choose>
			    
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-20p0">부서</th>
			                        	<th class="th-14p3">팀</th>
			                            <th>이름</th>
			                            <th class="th-20p0">탄생년</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="member" items="${memberList}" varStatus="i">
			                    		<c:choose>
										    <c:when test="${member.samePeriodId == map.sId}">
			                    				<tr class="on-style2">
				                            </c:when>
				                            <c:otherwise>
				                            	<tr>
				                            </c:otherwise>
				                        </c:choose>
					                        		<td>${member.depart.dName}</td>
											    	<td>${member.team.tShortName}</td>
											    	<td>${member.name}</td>
											    	<td>
											    		<c:if test="${member.samePeriod.birthYear != null}" >
											    			${member.samePeriod.birthYear}년생
											    		</c:if>
											    	</td>
						                            <td>
						                            	<input type="checkbox" id="${member.id}" name="chks">
						                            </td>
				        		                </tr>
				                        
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
				<div class="div-temp1"></div>
		        <div class="layer_fixed3">
		        	<form>
				        <div class="form-bottom" style="text-align:center;">
			            	<button class="btn md-button2" type="button" onclick="samePeriodRegist('${map.sId }')">적용</button>
			          	</div>
		          	</form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/admin/samePeriod/detail.js?${nowTime}"></script>
  
</html>
