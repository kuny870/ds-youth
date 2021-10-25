<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>

    <jsp:include page="/WEB-INF/views/layouts/header.jsp" flush="false" />
    <jsp:include page="/WEB-INF/views/admin/pop/member_picture_pop.jsp" flush="false" />

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
   				<span class="shop-link-login" onclick="samePeriodList()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    
		    	<c:choose>
		    		<c:when test="${map.gubun == 0}">
                 		<p class="head_title text-center">예비1학년</p>
                    </c:when>
			    	<c:when test="${map.gubun == 9}">
                 		<p class="head_title text-center">${map.name}년생</p>
                    </c:when>
                    <c:otherwise>
                    	<p class="head_title text-center">${map.name}학년</p>
                    </c:otherwise>
                </c:choose>

            
				<div class="div-container-non-width">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-20p0">부서</th>
			                        	<th class="th-20p0">팀</th>
			                            <th class="th-26p3">이름</th>
			                            <th>연락처</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="member" items="${memberList}" varStatus="i">
		                            	<tr>
			                        		<td>
			                        			<c:choose>
												    <c:when test="${member.depart.dName == '제1청년부'}">
					                    				1청
						                            </c:when>
						                            <c:when test="${member.depart.dName == '제2청년부'}">
					                    				2청
						                            </c:when>
						                            <c:otherwise>
						                            	${member.depart.dName}
						                            </c:otherwise>
						                        </c:choose>
			                        		</td>
									    	<td>${member.team.tShortName}</td>
									    	
									    	<c:set var="onClick" value=""/>
									    	<c:set var="borderRightOutset" value=""/>
									    	<c:if test="${member.replaceProfileImg != null and member.replaceProfileImg != ''}">
									    		<c:set var="onClick" value="memberPicturePop('${member.name}', '${member.replaceProfileImg }')"/>
                           						<c:set var="borderRightOutset" value="border-right: outset; cursor: pointer;"/>
                           					</c:if>
							                            					
									    	<td onclick="${onClick}" style="${borderRightOutset}">${member.name}</td>
									    	<td>
									    		<c:if test="${member.htel != ''}">
				                            		<a href="tel:${member.htel}">
				                            			<img src="${resourcesPath}/assets/images/phone_img.png" class="phone-img">
				                            		</a>
				                            	</c:if>
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
  
</html>
