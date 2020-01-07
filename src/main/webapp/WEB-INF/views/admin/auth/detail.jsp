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
   				<span class="shop-link-login" onclick="auth()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="head_title text-center">${auth.name }</p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                        	<th class="th-20p0">부서</th>
			                        	<th class="th-14p3">팀</th>
			                            <th>이름</th>
			                            <th class="th-20p0">권한</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="user" items="${userList}" varStatus="i">
		                    			<c:choose>
										    <c:when test="${user.auth.name == auth.name || (auth.name == '국장' && user.authExec == 1)}">
			                    				<tr class="on-style2">
				                            </c:when>
				                            <c:otherwise>
				                            	<tr>
				                            </c:otherwise>
				                        </c:choose>
											    	<td>${user.depart.dName}</td>
											    	<td>${user.team.tShortName}</td>
											    	<td>${user.name}</td>
											    	<td>
											    		${user.auth.name}
											    		<c:if test="${user.authExec == 1}">
											    			/ 국장
											    		</c:if>
											    	</td>
						                            <td>
						                            	<input type="checkbox" id="${user.id}" name="chks">
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
				        	<c:choose>
								<c:when test="${auth.name == '국장'}">
									<button class="btn md-button2" type="button" onclick="authExecRegist()">적용</button>
								</c:when>
								<c:otherwise>
					            	<button class="btn md-button2" type="button" onclick="authRegist(${auth.id})">적용</button>
								</c:otherwise>
							</c:choose>
			          	</div>
		          	</form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/admin/auth/detail.js?${nowTime}"></script>
  
</html>
