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
			    <p class="head_title text-center">순 관리</p>
            
				<div class="div-container">
					
					<div class="customer-select2 selectbox" style="width: 25%; margin-left: 10%; float: left;">
						<!-- 년 선택 -->
                   		<select class="select-fix" id="year" name="year" style="height:27px; font-size: 13px;">
	                       	<c:forEach var="year" items="${yearList }">
	                       		<c:set var="selected" value="" />
								<c:if test="${year eq group.year }">
									<c:set var="selected" value="selected" />
								</c:if>
								<option value="${year}" ${selected} >${year}년</option>
							</c:forEach>
	                    </select>
                    </div>    
                    
                    <div class="customer-select2 selectbox" style="width: 23%; margin-left: 7%; float: left;">
						<!-- 상반기 / 하반기 선택 -->
                   		<select class="select-fix" id="season" name="season" style="height:27px; font-size: 11px;">
	                       	<c:forEach var="ss" items="${season }">
	                       		<c:set var="selected" value="" />
								<c:if test="${ss eq group.getSeason() }">
									<c:set var="selected" value="selected" />
								</c:if>
								<option value="${ss}" ${selected} >${ss}</option>
							</c:forEach>
	                    </select>
                    </div>    
                    
                    
                    <div>
		            	<button class="basic-btn attendance-list-btn" onclick="groupSearch()">조회</button>
		            </div>
                    
                    
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-14p3">팀</th>
			                            <th>순공부명</th>
			                            <th class="th-22p0">순장</th>
			                            <th class="th-20p0"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="group" items="${groupList}" varStatus="i">
			                    		<tr>
				                            <td>
				                            	<c:choose>
												    <c:when test="${group.team.tShortName == '1새가족'}">
					                    				1새
						                            </c:when>
						                            <c:when test="${group.team.tShortName == '2새가족'}">
					                    				2새
						                            </c:when>
						                            <c:otherwise>
						                            	${group.team.tShortName}
						                            </c:otherwise>
						                        </c:choose>
				                            </td>
				                            <td class="css-group-name-a">
				                            	<input type="hidden" id="${group.id}-input-hidden" value="${group.gName}" />
				                            	<a id="${group.id}-a" href="${contextPath }/admin/group/detail?year=${group.year}&season=${group.season}&id=${group.id}">${group.gName} (${group.cnt})</a>
				                            	<input type="text" class="admin-group-input" id="${group.id}-input" value="${group.gName}" style="display: none;"/>
				                            </td>
				                            <td>
				                            	<c:forEach var="member" items="${memberList}" varStatus="j">
				                            		<c:if test="${member.attendance.groupId == group.id}">
														${member.name}
				                            		</c:if>
				                            	</c:forEach>
				                            </td>
				                            <td>
				                            	<!-- 리더 이상 -->
    											<c:if test="${login.teamId == group.teamId || login.authId <= 2}">
    												<button class="basic-btn admin-modify-btn" id="${group.id}-modify-btn" onclick="modify(${group.id})" >수정</button>
				                            		<button class="basic-btn admin-remove-btn" id="${group.id}-remove-btn" onclick="remove(${group.id})" >삭제</button>
				                            	</c:if>
				                            </td>
				                        </tr>
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
				
				<!-- 리더 이상 -->
				<c:choose>
           			<c:when test="${login.authId <= 6}">
			    		<div class="div-temp2"></div>
				        <div class="layer_fixed">
					        <form id="registGroupForm">
					        	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
					        	<input type="hidden" id="year" name="year" value="${group.year }" />
					        	<input type="hidden" id="season" name="season" value="${group.season }" />
					        
					        	<div style="text-align: center;">
					        		<select class="basic-select admin-team-select" id="teamId" name="teamId">
					        			<option>팀선택</option>
					        			<c:forEach var="team" items="${teamList }">
					        				<c:set var="selected" value="" />
											<c:if test="${team.id eq login.teamId}">
												<c:set var="selected" value="selected" />
											</c:if>
											<option value="${team.id}" ${selected} >${team.tShortName}</option>
										</c:forEach>
					        		</select>
					 	        	<input class="basic-input admin-team-input fs-13" id="gName" name="gName" placeholder="ex) 기본진리"></input>
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
  
  <script src="${resourcesPath}/assets/js/admin/group/name.js?${nowTime}"></script>
  
</html>
