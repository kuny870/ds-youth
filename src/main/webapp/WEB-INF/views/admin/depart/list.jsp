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
			    <p class="head_title text-center">부서 관리</p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th>부서명</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="depart" items="${departList}" varStatus="i">
			                    		<tr>
				                            <td>
				                            	${depart.dName}
				                            	<input type="hidden" id="${depart.id}-input-hidden" value="${depart.dName}" />
				                            </td>
			                            	<td>
			                            		<button class="basic-btn admin-remove-btn" onclick="remove(${depart.id})">삭제</button>
			                            	</td>
				                        </tr>
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
	    
		    	<div class="div-temp2"></div>
		        <div class="layer_fixed">
			        <form id="registDepartForm">
			        	<input type="hidden" id="regUser" name="regUser" value="${login.id }" />
			        
			        	<div style="text-align: center;">
			 	        	<input class="md-input" id="dName" name="dName" placeholder="부서" autocomplete="off"></input>
			 	        </div>
				        <div class="form-bottom" style="text-align:center;">
			            	<button class="btn md-button" type="submit">추가</button>
			          	</div>
			        </form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/admin/depart/list.js?${nowTime}"></script>
  
</html>
