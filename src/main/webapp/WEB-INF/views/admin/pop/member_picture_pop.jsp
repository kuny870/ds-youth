<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	
<title>MEMBER_MEMO POPUP</title>
<style>
/* 마스크 뛰우기 */
#memberPictureMask {
    position:absolute;
    z-index:9000;
    background-color:#000;
    display:none;
    left:0;
    top:0;
}

.originImgId-img2 {
	border-radius:7px;
	height:130px;
	margin-left: 15px;
	margin-bottom: 10px;
}

/* 폰 */
@media (max-width: 679px) {

	.profileImgId-img2 {
		border-radius:7px;
		height:200px;
		max-width:90%;
		margin-left: 15px;
		margin-bottom: 10px;
	}
	
	.memberPictureWindow {
	    position: fixed;
	    width: 80%;
	    left: 50%;
	    margin-left: -40%; /* half of width */
	    /* height: 300px; */
	    top: 42%;
	    margin-top: -200px; /* half of height */
	    overflow: auto;
	
	    /* decoration */
	    border: 1px solid #000;
	    background-color: #eee;
	    padding: 1em;
	    box-sizing: border-box;
	
	    display: none;
	    background-color:#FFF;
	    z-index:10000;
	 }
}

/* pc */
@media (min-width: 680px) {

	.profileImgId-img2 {
		border-radius:7px;
		height:500px;
		max-width:90%;
		margin-left: 15px;
		margin-bottom: 10px;
	}
	
	.memberPictureWindow {
	    position: fixed;
	    width: 55%;
	    left: 57%;
	    margin-left: -35%; /* half of width */
	    /* height: 300px; */
	    top: 38%;
	    margin-top: -200px; /* half of height */
	    overflow: auto;
	
	    /* decoration */
	    border: 1px solid #000;
	    background-color: #eee;
	    padding: 1em;
	    box-sizing: border-box;
	    
	    display: none;
	    background-color:#FFF;
	    z-index:10000;
 	}
}

</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
//<![CDATA[
    function memberPicturePop(mName, profileImg){

    	$('#mName').text('▷ ' + mName);
    	
    	$('#originImgDiv').css('display', 'block');
    	$('#profileImgDiv').css('display', 'block');
    	
   		document.getElementById('profileImgId').src= resourcesPath + '/assets/images/profileImg/' + profileImg;
    	
        //화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#memberPictureMask").css({"width":maskWidth,"height":maskHeight});

        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.

        $("#memberPictureMask").fadeIn(0);
        $("#memberPictureMask").fadeTo("slow",0.6);

        //윈도우 같은 거 띄운다.
        $(".memberPictureWindow").show();
        
    }

    $(document).ready(function(){

        //닫기 버튼을 눌렀을 때
        $(".memberPictureWindow .memberMemoClose").click(function (e) {
            //링크 기본동작은 작동하지 않도록 한다.
            e.preventDefault();
            $("#memberPictureMask, .memberPictureWindow").hide();
        });

        //검은 막을 눌렀을 때
        $("#memberPictureMask").click(function () {
            $(this).hide();
            $(".memberPictureWindow").hide();

        });

    });

//]]>
</script>
</head>
<body>
    <div id="picture-wrap">
        <div id="picture-container">
            <div id="memberPictureMask"></div>
           	<div class="memberPictureWindow">
           	
           		<div id="mName" style="font-size: 20px; margin-bottom: 5px;"></div>
           		
					<div id="profileImgDiv">
						<img id="profileImgId" src="" class="profileImgId-img2">
					</div>
            </div>
        </div>
    </div>
</body>


<script>

</script>

</html>

