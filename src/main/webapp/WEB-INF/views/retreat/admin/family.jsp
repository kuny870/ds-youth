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
   				<span class="shop-link-login" onclick="retreat()">
   					<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
   				</span>
			    <p class="head_title text-center">${retreat.year } ${retreat.season } ${retreat.retreatName }</p>
            
				<div class="div-container">
					
					<div class="sales-table">
			            <div class="table-wrap" style="padding: 0.466667vw 0 0 0;">

			                <table>
			                    <thead>
			                        <tr>
			                            <th>가족명</th>
			                            <th class="th-14p3"></th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach var="family" items="${familyList}" varStatus="i">
			                    		<c:if test="${family.id != null }">
				                    		<tr>
					                            <td class="css-a3">
					                            	<a href="${contextPath }/admin/retreat/family/member/list?retreatId=${family.retreatId}&familyId=${family.id}">${family.famName} (${family.cnt})</a>
					                            	<input type="hidden" id="${family.id}-input-hidden" value="${family.famName}" />
					                            </td>
				                            	<td>
				                            		<button class="basic-btn admin-remove-btn" onclick="remove(${family.id})">삭제</button>
				                            	</td> 
					                        </tr>
					                    </c:if>
									</c:forEach>
                                    
			                    </tbody>
			                </table>
			                
			            </div>
			        </div>
	
				</div>
				
	    
		    	<div class="div-temp2"></div>
		        <div class="layer_fixed">
			        <form id="registFamilyForm">
			        	<input type="hidden" id="retreatId" name="retreatId" value="${retreat.id }" />
			        
			        	<div style="text-align: center;">
			 	        	<input class="md-input" id="famName" name="famName" placeholder="가족명" autocomplete="off"></input>
			 	        </div>
				        <div class="form-bottom" style="text-align:center;">
			            	<button class="btn md-button" type="submit">추가</button>
			          	</div>
			        </form>
				</div>
				
	    	</div>
	    </div>    

  </body>
  
  <script src="${resourcesPath}/assets/js/retreat/admin/family.js?${nowTime}"></script>
  
</html>
