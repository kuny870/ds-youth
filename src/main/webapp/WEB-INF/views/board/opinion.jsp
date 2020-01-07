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
                <p class="head_title text-center">익명 게시판</p>
            </div>
            
			<div class="div-container">
				
				<c:forEach var="boardOp" items="${boardOpList}" varStatus="i">
					<div class="div-css">${boardOp.content }　　${boardOp.regDate }</div>

					<c:if test="${boardOpList.size() != i.index + 1}">
					<hr>
					</c:if>
					
				</c:forEach>

			</div>
			
			
<%-- 			 <div class="div-container">
					
				<div class="sales-table">
		            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

		                <table>
		                    <thead>
		                        <tr>
		                            <th>내용</th>
		                            <th class="th-20p0">작성일</th>
		                        </tr>
		                    </thead>
		                    <tbody>
		                    	<c:forEach var="boardOp" items="${boardOpList}" varStatus="i">
		                    		<tr>
			                            <td>${boardOp.content }</td>
		                            	<td>${boardOp.regDate }</td>
			                        </tr>
								</c:forEach>
                                   
		                    </tbody>
		                </table>
		                
		            </div>
		        </div>

			</div> --%>
			
   			<div class="div-temp2"></div>
	        <div class="layer_fixed">
		        <form id="registBoardOpForm">
		        
		        	<div style="text-align: center;">
		 	        	<input class="md-input" id="content" name="content" placeholder="다양한 의견을 남겨주세요"></input>
		 	        </div>
			        <div class="form-bottom" style="text-align:center;">
		            	<button class="btn md-button" type="submit">확인</button>
		          	</div>
		        </form>
			</div>
    
    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/board/opinion.js?${nowTime}"></script>
  
</html>
