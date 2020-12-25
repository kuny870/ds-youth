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
   				<span class="shop-link-login" onclick="family()">
   					<input type="hidden" id="fId" name="fId" value="${family.retreatId }" />
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="head_title text-center">${family.famName }</p>
            
				<div class="div-container">
					
					<div style="margin-left: 20px; color: blue; margin-bottom: 5px; font-size: 3.5vw;">＊ 리더(또는 가족장)로 임명 시 이름을 클릭해 주세요</div>
					<div style="margin-left: 20px; margin-bottom: 10px; font-size: 3.5vw;">
						＊ 가족장 : <span style="background: #ff0080; display: inline-block; width: 50px; height: 17px; border-radius: 4.666667px; margin-right: 20px;">　</span>
						＊ 리더 : <span style="background: blue; display: inline-block; width: 50px; height: 17px; border-radius: 4.666667px; margin-right: 20px;">　</span> </div>
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-14p3">팀</th>
			                            <th class="th-26p3">순</th>
			                            <th class="th-20p0">이름</th>
			                            <th>가족명</th>
			                            <th class="th-9p0"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="fm" items="${fmList}" varStatus="i">
			                    		<c:choose>
										    <c:when test="${fm.familyId == retreatSearch.familyId}">
			                    				<tr class="on-style2">
				                            </c:when>
				                            <c:otherwise>
				                            	<tr>
				                            </c:otherwise>
				                        </c:choose>
				                        			<td>${fm.team.tShortName}</td>
				                        			<td>${fm.group.gName}</td>
						                            <td>
						                            	<c:choose>
														    <c:when test="${fm.familyId == family.id}">
							                    				<c:choose>
																    <c:when test="${fm.groupGrade == '0'}">
									                    				<div class="non-style" onclick="fGroupGrade('${fm.id}','${fm.member.name}','${fm.groupGrade}', '${family.retreatId }')">
										                            </c:when>
										                            <c:when test="${fm.groupGrade == '1'}">
									                    				<div class="on-style" onclick="fGroupGrade('${fm.id}','${fm.member.name}','${fm.groupGrade}', '${family.retreatId }')">
										                            </c:when>
										                            <c:otherwise>
										                            	<div class="on-style3" onclick="fGroupGrade('${fm.id}','${fm.member.name}','${fm.groupGrade}', '${family.retreatId }')">
										                            </c:otherwise>
										                        </c:choose>
								                            </c:when>
								                            <c:otherwise>
								                            	<c:choose>
																    <c:when test="${fm.groupGrade == '0'}">
									                    				<div class="non-style">
										                            </c:when>
										                            <c:when test="${fm.groupGrade == '1'}">
									                    				<div class="on-style">
										                            </c:when>
										                            <c:when test="${fm.groupGrade == '9'}">
									                    				<div class="on-style3">
										                            </c:when>
										                            <c:otherwise>
										                            	<div class="non-style">
										                            </c:otherwise>
										                        </c:choose>
								                            </c:otherwise>
								                        </c:choose>
						                            			${fm.member.name}
						                            			<c:if test="${fm.samePeriod.birthYear != null }">
						                            				<c:choose>
								                            			<c:when test="${year - fm.samePeriod.birthYear < 19}">
								                            				(${fn:substring(fm.samePeriod.birthYear,2,4)})
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 19}">
								                            				(1)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 20}">
								                            				(2)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 21}">
								                            				(3)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 22}">
								                            				(4)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 23}">
								                            				(5)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 24}">
								                            				(6)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 25}">
								                            				(7)
								                            			</c:when>
								                            			<c:when test="${year - fm.samePeriod.birthYear == 26}">
								                            				(8)
								                            			</c:when>
								                            			<c:otherwise>
								                            				(${fn:substring(fm.samePeriod.birthYear,2,4)})
								                            			</c:otherwise>
								                            		</c:choose>
						                            			</c:if>
						                            			
						                            		</div>
						                            	
						                            </td>
						                            <td>${fm.family.famName}</td>
						                            <td>
						                            	<input type="hidden" id="${fm.depart.id}" name="departId">
						                            	<input type="hidden" id="${fm.team.id}" name="teamId">
						                            	<input type="hidden" id="${fm.group.id}" name="groupId">
						                            	<c:set var="fmId" value=""/>
						                            	<c:choose>
														    <c:when test="${fm.id == null}">
														    	<c:set var="fmId" value="0"/>
								                            </c:when>
								                            <c:otherwise>
								                            	<c:set var="fmId" value="${fm.id}"/>
								                            </c:otherwise>
								                        </c:choose>
						                            	<input type="hidden" id="${fmId}" name="fmId">
						                            	<input type="checkbox" id="${fm.member.id}" name="chks">
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
			            	<button class="btn md-button2" type="button" onclick="familyRegist('${family.id }')">적용</button>
			          	</div>
		          	</form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/retreat/admin/family_member.js?${nowTime}"></script>
  
</html>
