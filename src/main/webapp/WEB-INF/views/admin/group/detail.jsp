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
   				<span class="shop-link-login" onclick="groupList()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="head_title text-center">${group.team.tShortName } / ${group.gName }</p>
            
				<div class="div-container">
					
					<div style="margin-left: 20px; color: blue; margin-bottom: 10px; font-size: 3.5vw;">
						＊ 순장으로 임명 시 이름을 클릭해 주세요.
					</div>

					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-22p0">이름</th>
			                            <th class="th-20p0">구분</th>
			                            <th>순공부명</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="member" items="${memberList}" varStatus="i">
			                    		<c:choose>
										    <c:when test="${member.group.gName == group.gName}">
			                    				<tr class="on-style2">
				                            </c:when>
				                            <c:otherwise>
				                            	<tr>
				                            </c:otherwise>
				                        </c:choose>
						                            <td>
						                            	<c:choose>
														    <c:when test="${group.gName == member.group.gName}">
							                    				<c:choose>
																    <c:when test="${member.attendance.groupGrade == '순장'}">
									                    				<div class="on-style" onclick="attGroupGrade('${member.id}','${member.name}','${member.attendance.groupGrade}')">
										                            </c:when>
										                            <c:otherwise>
										                            	<div class="non-style" onclick="attGroupGrade('${member.id}','${member.name}','${member.attendance.groupGrade}')">
										                            </c:otherwise>
										                        </c:choose>
								                            </c:when>
								                            <c:otherwise>
								                            	<c:choose>
																    <c:when test="${member.attendance.groupGrade == '순장'}">
									                    				<div class="on-style">
										                            </c:when>
										                            <c:otherwise>
										                            	<div class="non-style">
										                            </c:otherwise>
										                        </c:choose>
								                            </c:otherwise>
								                        </c:choose>
								                        
								                        ${member.name}
								                        
								                        <c:if test="${member.samePeriodId != null }">
				                            				<c:choose>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear < 19}">
						                            				(${fn:substring(member.samePeriod.birthYear,2,4)})
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 19}">
						                            				(1)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 20}">
						                            				(2)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 21}">
						                            				(3)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 22}">
						                            				(4)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 23}">
						                            				(5)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 24}">
						                            				(6)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 25}">
						                            				(7)
						                            			</c:when>
						                            			<c:when test="${thisYear - member.samePeriod.birthYear == 26}">
						                            				(8)
						                            			</c:when>
						                            			<c:otherwise>
						                            				(${fn:substring(member.samePeriod.birthYear,2,4)})
						                            			</c:otherwise>
						                            		</c:choose>
				                            			</c:if>
								                        
								                        </div>
								                        
								                    </td>
								                    <td>
								                    	<c:forEach var="memberState" items="${memberStateList}">
								                    		<c:if test="${member.memState == memberState.id }">
								                    			${memberState.mState }								                    		
								                    		</c:if>	
								                    	</c:forEach>
								                    </td>
						                            <td>${member.group.gName}</td>
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
		        		<input type="hidden" id="gId" name="gId" value="${group.id }" />
		        		<input type="hidden" id="year" name="year" value="${group.year }" />
                    	<input type="hidden" id="season" name="season" value="${group.season }" />
                    	
				        <div class="form-bottom" style="text-align:center;">
			            	<button class="btn md-button2" type="button" onclick="attGroup()">적용</button>
			          	</div>
		          	</form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/admin/group/detail.js?${nowTime}"></script>
  
</html>
