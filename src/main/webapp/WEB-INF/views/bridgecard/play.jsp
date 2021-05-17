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
			table tr td img{
				width: 14vw;
			}
			#mainCardDiv img {
				width: 300px;
			}
			#title {
				font-size: 17px;
				text-align: center;
				margin-top: 15px
			}
		}
		
		@media (min-width: 680px) {
			table tr td img{
				width: 110px;
			}
			#mainCardDiv img {
				width: 300px;
				margin-top:35px !important;
			}
			#title {
				font-size: 30px;
				text-align: center;
				margin-top: 15px
			}
		}
		
	</style>
	
  </head>

<body id="playBody">
	<div id="title">
		<h3>BRIDGE</h3>
	</div>
	
	<div id="mainCardDiv" style="width:100%; height:630px; display: none; position: absolute; cursor: pointer; z-index:1" onclick="hide()">
	    <img id="mainCard" src="${resourcesPath}/assets/images/bridgecard/back_card1.png" style="display: block; margin:auto;">
	</div>


	<div id="tableWrap" style="width: 700px; margin:auto; margin-top:10px; z-index:9000">
		<table border="1">
		    <tr>
		        <td>
		            <img id="card1" src="${resourcesPath}/assets/images/bridgecard/back_card1.png" style="cursor: pointer;" onclick="next('card1')">
		        </td>
		        <td>
		            <img id="card2" src="${resourcesPath}/assets/images/bridgecard/back_card2.png" style="cursor: pointer;" onclick="next('card2')">
		        </td>
		        <td>
		            <img id="card3" src="${resourcesPath}/assets/images/bridgecard/back_card3.png" style="cursor: pointer;" onclick="next('card3')">
		        </td>
		        <td>
		            <img id="card4" src="${resourcesPath}/assets/images/bridgecard/back_card4.png" style="cursor: pointer;" onclick="next('card4')">
		        </td>
		        <td>
		            <img id="card5" src="${resourcesPath}/assets/images/bridgecard/back_card5.png" style="cursor: pointer;" onclick="next('card5')">
		        </td>
		        <td>
		            <img id="card6" src="${resourcesPath}/assets/images/bridgecard/back_card6.png" style="cursor: pointer;" onclick="next('card6')">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <img id="card7" src="${resourcesPath}/assets/images/bridgecard/back_card7.png" style="cursor: pointer;" onclick="next('card7')">
		        </td>
		        <td>
		            <img id="card8" src="${resourcesPath}/assets/images/bridgecard/back_card8.png" style="cursor: pointer;" onclick="next('card8')">
		        </td>
		        <td>
		            <img id="card9" src="${resourcesPath}/assets/images/bridgecard/back_card9.png" style="cursor: pointer;" onclick="next('card9')">
		        </td>
		        <td>
		            <img id="card10" src="${resourcesPath}/assets/images/bridgecard/back_card10.png" style="cursor: pointer;" onclick="next('card10')">
		        </td>
		        <td>
		            <img id="card11" src="${resourcesPath}/assets/images/bridgecard/back_card11.png" style="cursor: pointer;" onclick="next('card11')">
		        </td>
		        <td>
		            <img id="card12" src="${resourcesPath}/assets/images/bridgecard/back_card12.png" style="cursor: pointer;" onclick="next('card12')">
		        </td>
		    </tr>
		    <tr>
		        <td>
		            <img id="card13" src="${resourcesPath}/assets/images/bridgecard/back_card13.png" style="cursor: pointer;" onclick="next('card13')">
		        </td>
		        <td>
		            <img id="card14" src="${resourcesPath}/assets/images/bridgecard/back_card14.png" style="cursor: pointer;" onclick="next('card14')">
		        </td>
		        <td>
		            <img id="card15" src="${resourcesPath}/assets/images/bridgecard/back_card15.png" style="cursor: pointer;" onclick="next('card15')">
		        </td>
		        <td>
		            <img id="card16" src="${resourcesPath}/assets/images/bridgecard/back_card16.png" style="cursor: pointer;" onclick="next('card16')">
		        </td>
		        <td>
		            <img id="card17" src="${resourcesPath}/assets/images/bridgecard/back_card17.png" style="cursor: pointer;" onclick="next('card17')">
		        </td>
		        <td>
		        </td>
		    </tr>
		</table>
	</div>

</body>

<script>

	function next(cardId) {

		document.getElementById('mainCard').src = resourcesPath + "/assets/images/bridgecard/front_" + cardId + ".jpg";
		
	    $("#tableWrap").fadeIn(0);      
        $("#tableWrap").fadeTo("slow",0.6);
        
	    $("#mainCardDiv").show(700);
	    
	    document.getElementById(cardId).src = resourcesPath + "/assets/images/bridgecard/front_" + cardId + ".jpg";
	}
	
	function hide() {
	    document.getElementById('mainCardDiv').style.display = 'none';
	    
	    $("#tableWrap").fadeTo("fast",1);
	}

</script>

</html>
