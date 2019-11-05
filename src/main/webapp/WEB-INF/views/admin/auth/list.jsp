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
   				<span class="shop-link-login" onclick="adminList()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="shop_name text-center">
		            	권한 관리               
		        </p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th>권한명</th>
			                            <th class="th-18p0"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="auth" items="${authList}" varStatus="i">
			                    		<c:choose>
			                       			<c:when test="${login.authId == 1}">
		                       					<tr>
						                            <td class="css-a3">
						                            	<a href="${contextPath }/admin/auth/detail/${auth.id}/${auth.name}">${auth.name} (${auth.cnt})</a>
						                            	<input type="hidden" id="${auth.id}-input-hidden" value="${auth.name}" />
						                            </td>
						                            <td>
						                            	<button class="basic-btn admin-reset-btn" onclick="remove('${auth.id}','${auth.name}')">초기화</button>
						                            </td>
						                        </tr>
			                       			</c:when>
			                       			<c:otherwise>
			                       				<c:if test="${auth.no < 8}">
			                       					<tr>
							                            <td class="css-a3">
							                            	<a href="${contextPath }/admin/auth/detail/${auth.id}/${auth.name}">${auth.name} (${auth.cnt})</a>
							                            	<input type="hidden" id="${auth.id}-input-hidden" value="${auth.name}" />
							                            </td>
							                            <td>
							                            	<button class="basic-btn admin-reset-btn" onclick="remove('${auth.id}','${auth.name}')">초기화</button>
							                            </td>
							                        </tr>
			                       				</c:if>
			                       			</c:otherwise>
				                        </c:choose>
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
  
  <script src="${resourcesPath}/assets/js/admin/auth/list.js?${nowTime}"></script>
  
</html>
