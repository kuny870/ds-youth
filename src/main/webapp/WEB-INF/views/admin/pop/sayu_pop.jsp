<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>SAYU POPUP</title>
<style>
/* 마스크 뛰우기 */
#sayuMask {
    position:absolute;
    z-index:9000;
    background-color:#000;
    display:none;
    left:0;
    top:0;
}
/* 팝업으로 뜨는 윈도우 css  */
@media (max-width: 679px) {
	.sayuWindow{
	    position: fixed;
	    width: 80%;
	    left: 50%;
	    margin-left: -40%; /* half of width */
	    /* height: 300px; */
	    top: 50%;
	    margin-top: -130px; /* half of height */
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

@media (min-width: 680px) {
	.sayuWindow{
	    position: fixed;
	    width: 55%;
	    left: 57%;
	    margin-left: -35%; /* half of width */
	    /* height: 300px; */
	    top: 45%;
	    margin-top: -150px; /* half of height */
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
    function sayuPop(id, name, date, sayu, ord, gubun){
	
    	if(gubun == '1'){
    		$('#sayuRemove').show();
    	}
    	
    	$('#attId2').val(id);
    	$('#ord').val(ord);
    	
    	var year = $('#thisYear').val();
    	var month = $('#thisMonth').val();
    	
    	$('#sayuName').text('▷ ' + name);
    	$('#sayuStr').html(month + "/" + date + " 예배 불참 사유 : " + sayu);
    	
        //화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#sayuMask").css({"width":maskWidth,"height":maskHeight});

        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.

        $("#sayuMask").fadeIn(0);
        $("#sayuMask").fadeTo("slow",0.6);

        //윈도우 같은 거 띄운다.
        $(".sayuWindow").show();

    }

    $(document).ready(function(){

        //닫기 버튼을 눌렀을 때
        $(".sayuWindow .sayuClose").click(function (e) {
            //링크 기본동작은 작동하지 않도록 한다.
            e.preventDefault();
            $("#sayuMask, .sayuWindow").hide();
        });

        //검은 막을 눌렀을 때
        $("#sayuMask").click(function () {
            $(this).hide();
            $(".sayuWindow").hide();

        });

    });

//]]>
</script>
</head>
<body>
    <div id="sayu-wrap">
        <div id="sayu-container">
            <div id="sayuMask"></div>
           	<div class="sayuWindow">
           		<div id="sayuName" style="font-size: 20px; margin-bottom: 10px;"></div>
	            <div class="form">
	                <form enctype="application/x-www-form-urlencoded" id="memberPopModifyForm">
	                    <div class="form-middle">
	                    	<input type="hidden" id="attId2" name="attId2" value="" />
	                    	<input type="hidden" id="ord" name="ord" value="" />
	                        <p id="sayuStr" style="margin-top: 5px; margin-bottom: 10px; font-size: 15px; text-align: center;"></p>
	                    </div>
	                </form>
	            </div>
	            <div>
					<p style="text-align:center; background:#ffffff; padding:5px; margin-top:15px;">
						<button class="sayuRemove" id="sayuRemove" style="display: none;" onclick="sayuRemove()">삭제</button>
						<button class="sayuClose">닫기</button>
					</p>
				</div>
            </div>
        </div>
    </div>
</body>


<script>

</script>

</html>

