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
			    <span class="shop-link-login" onclick="leaderInfoList()">
			    	<img src="${resourcesPath}/assets/images/back_btn.png" class="back-img">
			    </span>
                <p class="head_title text-center">${leaderInfo.title }</p>
            </div>
            
			<div class="div-container">
				<img src="${resourcesPath}/assets/images/leaderInfo/${leaderInfo.replaceImg}" style="width:100%; height:100%;">
			</div>
    </div>    

  </body>

</html>
