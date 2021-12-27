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
            
				<div class="div-container-non-width">
				
				
				<div class="div-container">
					<div class="customer-select-search" style="width: 36%; margin-left: 7px; float: left;">
						 <select class="select-member-list-team" id="memberType" name="memberType" onchange="adminTeamListSearch();">
						 <c:set var="selected" value="" />
						 <c:if test="${memberSearch.memberType eq 'removeMember' }">
						 	<c:set var="selected" value="selected" />
						 </c:if>
						 	<option value="removeMember" ${selected }>삭제팀원</option>
						 <c:set var="selected" value="" />
						 <c:if test="${memberSearch.memberType eq 'graduatedMember' }">
						 	<c:set var="selected" value="selected" />
						 </c:if>
						 	<option value="graduatedMember" ${selected }>졸업팀원</option>
					 	<c:set var="selected" value="" />
						 <c:if test="${memberSearch.memberType eq 'completionMember' }">
						 	<c:set var="selected" value="selected" />
						 </c:if>
						 	<option value="completionMember" ${selected }>새가족수료팀원</option>
	                    </select>   
                    </div>
                    
                    <div class="customer-select-search" style="width: 36%; margin-left: -17px; float: left; visibility: hidden;">
						 <select class="select-member-list-group" id="groupId" name="groupId" onchange="memberSearch();">
						 	<option value="" >순 전체</option>
	                       	<c:forEach var="group" items="${groupList }">
	                       		<c:set var="selected" value="" />
	                       		<c:if test="${group.id eq memberSearch.groupId }">
									<c:set var="selected" value="selected" />
								</c:if>
								<option value="${group.id}" ${selected }>${group.gName}</option>
							</c:forEach>
	                    </select>
                    </div>    
                    
                    <div class="customer-select-search" style="width: 22%; margin-left:-33px; margin-right:-3px; float: left;">
                         <input type="text" class="md-input2" id="nameKW" name="nameKW" onkeyup="if(window.event.keyCode==13){(enterKeyEvent())}" placeholder="이름" value="${memberSearch.nameKW }" style="border: 1px solid #ccc;">
		            </div>
		            <div>
		            	<button class="basic-btn member-list-btn" onclick="adminTeamListSearch();">검색</button>
		            </div>
		            
		            
		            				
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                        	<th class="th-17p0">부서</th>
			                        	<th class="th-14p3">팀</th>
			                            <th>이름</th>
			                            <c:choose>
			                            	<c:when test="${memberSearch.memberType eq 'removeMember' }">
			                            		<th class="th-30p4">삭제시간</th>
			                            	</c:when>
			                            	<c:when test="${memberSearch.memberType eq 'graduatedMember' }">
			                            		<th class="th-30p4">졸업시간</th>
			                            	</c:when>
			                            	<c:otherwise>
			                            		<th class="th-30p4">수료시간</th>
			                            	</c:otherwise>
			                            </c:choose>
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
										    	<button class="basic-btn admin-restore-btn" onclick="restore(${member.id}, ${memberSearch.memberType})">복구</button>
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
