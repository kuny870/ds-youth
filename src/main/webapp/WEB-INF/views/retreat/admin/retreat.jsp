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
			    <p class="head_title text-center">수련회 관리</p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th>수련회명</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="retreat" items="${retreatList}" varStatus="i">
			                    		<tr>
				                            <td class="css-a3">
				                            	<a href="${contextPath }/admin/retreat/family/list/${retreat.id}">${retreat.year} ${retreat.season} ${retreat.retreatName}</a>
				                            	<input type="hidden" id="${retreat.id}-input-hidden" value="${retreat.year} ${retreat.season} ${retreat.retreatName}" />
				                            </td>
			                            	<td>
			                            		<button class="basic-btn admin-remove-btn" onclick="remove(${retreat.id})">삭제</button>
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
			            	<button class="btn md-button2" type="button" onclick="retreatRegist()">수련회 등록</button>
			          	</div>
		          	</form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/retreat/admin/retreat.js?${nowTime}"></script>
  
</html>
