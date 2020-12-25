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
			    <span class="shop-link-login" onclick="mypage()">
			    	<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
			    </span>
                <p class="head_title text-center">리더배포자료</p>
            
				<div class="div-container">
				
					<div class="sales-table">
				            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">
	
				                <table>
				                    <thead>
				                        <tr>
				                            <th>제목</th>
				                            <!-- 국장만 삭제 가능 -->
				                            <c:if test="${login.authExec == 1}">
			                            		<th class="th-14p3"></th>
			                            	</c:if>
				                        </tr>
				                    </thead>
				                    <tbody>
				                    	<c:forEach var="leaderInfo" items="${leaderInfoList}" varStatus="i">
	                       					<tr>
					                            <td>
					                            	<div class="css-leader-list-a">
					                            		<a href="${contextPath }/leaderInfo/detail/${leaderInfo.id}">${leaderInfo.title}</a>
					                            	</div>
					                            </td>
					                            <!-- 국장만 삭제 가능 -->
					                            <c:if test="${login.authExec == 1}">
					                            	<td>
					                            		<button class="basic-btn admin-remove-btn" onclick="remove(${leaderInfo.id})">삭제</button>
					                            	</td>
					                            </c:if>
					                        </tr>
										</c:forEach>
				                    </tbody>
				                </table>
				                
				            </div>
				        </div>
					
				   </div>
				   
    
	    		<!-- 등록 버튼 -->
	    		<!-- 국장만 등록 가능 -->
	    		<c:choose>
	       			<c:when test="${login.authExec == 1}">
	       				<div class="div-temp2"></div>
				        <div class="layer_fixed">
					        <form id="registLeaderInfoForm" enctype="multipart/form-data">
					        	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
					        
					        	<div style="text-align: center;">
					        		<input type="number" pattern="\d*" class="md-input3" id="month" name="month" placeholder="월" maxlength="2" oninput="numberMaxLength(this);" autocomplete="off"/>
					        		<input type="number" pattern="\d*" class="md-input3" id="day" name="day" placeholder="일" maxlength="2" oninput="numberMaxLength(this);" autocomplete="off"/>
			        				<input type="file" id="originImg" name="originImg" style="width: 57%; margin-left: 10px;"/>
					 	        </div>
						        <div class="form-bottom" style="text-align:center;">
					            	<button class="btn md-button" type="submit">확인</button>
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
  	
	<script src="${resourcesPath}/assets/js/leaderInfo/list.js?${nowTime}"></script>
	
</html>
