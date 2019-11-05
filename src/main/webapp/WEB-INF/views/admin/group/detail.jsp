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
   				<span class="shop-link-login" onclick="group()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="shop_name text-center">
		            	${map.team } / ${map.group }          
		        </p>
            
				<div class="div-container">
					
					<div style="margin-left: 20px; color: blue; margin-bottom: 10px;">＊ 순장으로 임명 시 이름을 클릭해 주세요.</div>
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th class="th-26p3">이름</th>
			                            <th>순공부명</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="member" items="${memberList}" varStatus="i">
			                    		<c:choose>
										    <c:when test="${member.group.gName == map.group}">
			                    				<tr class="on-style2">
				                            </c:when>
				                            <c:otherwise>
				                            	<tr>
				                            </c:otherwise>
				                        </c:choose>
						                            <td>
						                            	<c:if test="${member.groupGrade == '순장' && map.group == member.group.gName}">
						                            		<div class="on-style" onclick="groupLeader(${member.id},'${member.name}','${member.groupGrade}','${map.group }','${member.group.gName}',${map.gId})">
						                            			${member.name}
						                            		</div>
						                            	</c:if>
						                            	<c:if test="${member.groupGrade == '순장' && map.group != member.group.gName}">
						                            		<div class="non-style" onclick="groupLeader(${member.id},'${member.name}','${member.groupGrade}','${map.group }','${member.group.gName}',${map.gId})">
						                            			${member.name}
						                            		</div>
						                            	</c:if>
						                            	<c:if test="${member.groupGrade != '순장' }">
						                            		<div class="non-style" onclick="groupLeader(${member.id},'${member.name}','${member.groupGrade}','${map.group }','${member.group.gName}',${map.gId})">
						                            			${member.name}
						                            		</div>
						                            	</c:if>
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
				        <div class="form-bottom" style="text-align:center;">
			            	<button class="btn md-button2" type="button" onclick="groupRegist('${map.gId }')">적용</button>
			          	</div>
		          	</form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/admin/group/detail.js?${nowTime}"></script>
  
</html>
