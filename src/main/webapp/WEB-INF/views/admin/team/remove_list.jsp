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
			    <p class="head_title text-center">팀원 관리</p>
            
				<div class="div-container">
				
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                        	<th class="th-17p0">부서</th>
			                        	<th class="th-14p3">팀</th>
			                            <th>이름</th>
			                            <th class="th-30p4">삭제시간</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="member" items="${memberList}" varStatus="i">
		                            	<tr>
									    	<td>${member.depart.dName}</td>
									    	<td>${member.team.tShortName}</td>
									    	<td>${member.name}</td>
				                            <td>${member.delDate}</td>
				                            <td>
				                            	<input type="hidden" id="${member.id}-input-hidden" value="${member.name}" />
										    	<button class="basic-btn admin-restore-btn" onclick="restore(${member.id})">복구</button>
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
  
  <script src="${resourcesPath}/assets/js/admin/team/remove_list.js?${nowTime}"></script>
  
</html>
