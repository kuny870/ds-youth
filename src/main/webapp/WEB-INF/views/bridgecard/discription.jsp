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
	
	<title>브릿지카드</title>
	
	<style type="text/css">

		@media (max-width: 679px) {
			#mainDiv img {
				width: 80vw;
				cursor: pointer;
				margin-top: 40px;
			}
		}
		
		@media (min-width: 680px) {
			#mainDiv img {
				width: 350px;
				cursor: pointer;
				margin-top: 70px;
			}
		}
		
	</style>
	
  </head>

<body>

	<div id="mainDiv" style="width:100%; text-align:center;">
		<img src="${resourcesPath}/assets/images/bridgecard/discription_card.jpg" onclick="next()">
	</div>

</body>

<script>

    function next() {
        location.href = contextPath + "/bridgecard/play";
    }

</script>

</html>
