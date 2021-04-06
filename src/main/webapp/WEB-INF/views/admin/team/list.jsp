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
			    <p class="head_title text-center">팀 관리</p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-30p4">부서</th>
			                            <th>팀</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="team" items="${teamList}" varStatus="i">
			                    		<tr>
			                    			<td>${team.depart.dName}</td>
				                            <td class="css-a">
				                            	 <a href="${contextPath }/team/modify/${team.id}">${team.tShortName}</a>
				                            	 <input type="hidden" id="${team.id}-input-hidden" value="${team.tShortName}" />
				                           	</td>
				                            <td>
				                            	<!-- 목사 이상만 삭제 가능 -->
				                            	<c:if test="${login.authId <= 2}">
				                            		<button class="basic-btn admin-remove-btn" onclick="remove(${team.id})">삭제</button>
				                            	</c:if>
				                            </td>
				                        </tr>
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
	    		
	    		
	    		<!-- 목사 이상만 추가 가능 -->
	    		<c:choose>
           			<c:when test="${login.authId <= 2}">
           				<div class="div-temp2"></div>
			    		<div class="layer_fixed">
					        <form id="registTeamForm">
					        	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
					        
					        	<div style="text-align: center;">
					        		<select class="basic-select admin-team-select" id="departId" name="departId">
					        			<option>부서선택</option>
					        			<c:forEach var="depart" items="${departList }">
											<option value="${depart.id}" >${depart.dName}</option>
										</c:forEach>
					        		</select>
					 	        	<input class="basic-input admin-team-input" id="tShortName" name="tShortName" placeholder="팀명" autocomplete="off"></input>
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
  
  <script src="${resourcesPath}/assets/js/admin/team/list.js?${nowTime}"></script>
  
</html>
