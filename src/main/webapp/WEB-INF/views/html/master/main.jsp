<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<jsp:include page="/WEB-INF/views/common/common_css.jsp" flush="false" />
</head>
<body>
	<div class="container">
        <div class="main-header clearfix">
            <nav>
                <ul>
                    <li>
                        <a href="${contextPath}/mypage">마이페이지</a>
                    </li>
                    <li>
                        <a href="#!">무료등록</a>
                    </li>
                </ul>
            </nav>
        </div>
        <div class="logo-wrap text-center">
            <img src="${resourcesPath}/assets/images/logo_main.png">
            <p>MARKET</p>
        </div>
        <div class="form">
            <form enctype="application/x-www-form-urlencoded">
                <div class="search-wrap">
                    <div class="search-bar">
                        <input type="text" placeholder="       #해시태그 또는 제품명 검색">
                        <div class="btn-search text-center">
                            <img src="${resourcesPath}/assets/images/ico_search.png">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <jsp:include page="/WEB-INF/views/common/quickview.jsp" flush="false" />
</body>
</html>
