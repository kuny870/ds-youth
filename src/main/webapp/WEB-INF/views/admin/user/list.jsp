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
		            	회원 관리          
		        </p>
            
				<div class="div-container">
				
					<div style="margin-left: 20px; color: blue; margin-bottom: 10px;">＊ 임시 비밀번호('000000') 발급 시 아이디를 클릭해 주세요.</div>
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                        	<th class="th-17p0">부서</th>
			                        	<th class="th-14p3">팀</th>
			                            <th class="th-14p3">이름</th>
			                            <th>아이디</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="user" items="${userList}" varStatus="i">
		                            	<tr>
									    	<td>${user.depart.dName}</td>
									    	<td>${user.team.tShortName}</td>
									    	<td>${user.name}</td>
									    	<td class="on-style">
									    		<span onclick="pwReset(${user.id});">${user.loginId}</span>
									    	</td>
				                            <td>
				                            	<input type="hidden" id="${user.id}-input-hidden" value="${user.loginId}" />
				                            	
				                            	<c:choose>
												    <c:when test="${user.delYn == 'N'}">
						                            	<button class="basic-btn admin-remove-btn" onclick="remove(${user.id})">탈퇴</button>
						                            </c:when>
						                            <c:otherwise>
												    	<button class="basic-btn admin-restore-btn" onclick="restore(${user.id})">복구</button>
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
  
  <script src="${resourcesPath}/assets/js/admin/user/list.js?${nowTime}"></script>
  
</html>
