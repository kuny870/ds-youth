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
			    <p class="head_title text-center">자유 게시판</p>
            
				<div class="div-container">
					
					<c:forEach var="boardFree" items="${boardFreeList}" varStatus="i">
						<div class="div-css">${boardFree.content }　　${boardFree.user.name }　　${boardFree.regDate }</div>
	
						<c:if test="${boardFreeList.size() != i.index + 1}">
						<hr>
						</c:if>
						
					</c:forEach>
	
				</div>
				
				<div class="div-temp2"></div>
		        <div class="layer_fixed">
			        <form id="registBoardFreeForm">
			        	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
			        
			        	<div style="text-align: center;">
			 	        	<input class="md-input" id="content" name="content" placeholder="자유롭게 나눠주세요"></input>
			 	        </div>
				        <div class="form-bottom" style="text-align:center;">
			            	<button class="btn md-button" type="submit">확인</button>
			          	</div>
			        </form>
				</div>
    		</div>
    	</div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/board/free.js?${nowTime}"></script>
  
</html>
