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

<!-- Style Sheet -->
<link href="${resourcesPath}/assets/css/reset.css?${nowTime}" rel="stylesheet">
<link href="${resourcesPath}/assets/css/common.css?${nowTime}" rel="stylesheet">
<link href="${resourcesPath}/assets/css/general.css?${nowTime}" rel="stylesheet">

<!-- Script -->
<script src= "${resourcesPath}/js/bootstrap.min.js" ></script >
<%-- <script src="${resourcesPath}/assets/js/mypage/main.js?${nowTime}"></script> --%>
<script src= "http://code.jquery.com/jquery-1.11.3.js" /></script >

<script language= "JavaScript">

function noticeCloseWin()
{
      noticeSetCookie( "Notice${popSeq}", "done" , "${closeNoticeDate}"); // 1=하룻동안 공지창 열지 않음
   
    self.close();
}

function noticeSetCookie( name, value, expiredays )
{
    var todayDate = new Date();
    todayDate.setDate( todayDate.getDate() + expiredays );
    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
}

</script>
</head>
<c:set var= "bodyClass" value ="${scrollbarYN eq 'Y' ? 'scroll-visible' : 'scroll-hidden'}" />
<body class= "${bodyClass} ">
<c:if test= "${closeUsableYN == 'Y'} ">
<div class="noticeClose">
      <input type ="checkbox" onclick="noticeCloseWin()">
      <span class ="closeNoticeDate"> ${closeNoticeDate}일 동안 띄우지 않음</span> 　
      <span class ="closeText">CLOSE< button class= "closeButton" type= "button" onclick ="window.close()" ><i class ="fa fa-times" aria-hidden="true" >x </i ></button ></span >
</div>
</c:if>
${content}
</body>

</html>