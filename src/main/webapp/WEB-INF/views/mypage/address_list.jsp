<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
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
		    <span class="shop-link-login" onclick="mypage()" style="width:8%; height:53%;">
		   		<img src="${resourcesPath}/assets/images/back_btn.png" style="width: 100%; height:83%;">
		   	</span>
	        <p class="head_title text-center"> 주소 관리</p>
	        
	        <ul class="tab x2 clearfix">
	            <li>
	                <button type="button" class="btn-tab selected" onclick="window.location.reload()">내 주소</button>
	            </li>
	            <li>
	                <button type="button" class="btn-tab" onclick="addressRegist()">새 주소 등록</button>
	            </li>                
	        </ul>
	       
			<div class="address-wrap">
	            	
	           	<c:forEach var="address" items="${addressList}" varStatus="i">
	           	
	                <div class="address-row clearfix">
	                    <div class="btn-wrap">
	                        <a href="${contextPath}/mypage/address/edit/${address.id}">수정</a>
	                        <span></span>
	                        <a href="javascript:addressRemove(${address.id})">삭제</a>
	                    </div>
	                    <p class="address">
	                    	${address.addr1 } ${address.addr2 }
	                        <span class="zipcode">(${address.zipcode })</span>
	                    </p>
	                    <div class="orderer">
	                        <p class="name">${address.name }</p><span class="divbar"></span>
	                        <p class="memo">
	                        	${address.memo }
	                    	</p>
	                    </div>
	                </div>
	                
	            </c:forEach>
	               
	        </div>
        </div>
   </div>
   
</body>

<script src="${resourcesPath}/assets/js/mypage/address_list.js?${nowTime}"></script>

</html>
