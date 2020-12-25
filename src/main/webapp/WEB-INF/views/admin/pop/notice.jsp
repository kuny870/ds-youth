<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>NOTICE POPUP</title>
<style> 
/* 마스크 뛰우기 */
#mask {  
    position:absolute;  
    z-index:9000;  
    background-color:#000;  
    display:none;  
    left:0;
    top:0;
} 
/* 팝업으로 뜨는 윈도우 css  */ 
.window{
    position: fixed;
    width: 60%;
    left: 50%;
    margin-left: -30%; /* half of width */
    /* height: 300px; */
    top: 50%;
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
 
</style>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"> 
//<![CDATA[
    function wrapWindowByMask(){
 
        //화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();  
        var maskWidth = $(window).width();  
 
        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#mask").css({"width":maskWidth,"height":maskHeight});  
 
        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.
 
        $("#mask").fadeIn(0);      
        $("#mask").fadeTo("slow",0.6);    
 
        //윈도우 같은 거 띄운다.
        $(".window").show();
 
    }
 
    $(document).ready(function(){
        //검은 막 띄우기
        /* $(".openMask").click(function(e){
            e.preventDefault();
            wrapWindowByMask();
        }); */
        wrapWindowByMask();
 
        //닫기 버튼을 눌렀을 때
        $(".window .close").click(function (e) {  
            //링크 기본동작은 작동하지 않도록 한다.
            e.preventDefault();  
            $("#mask, .window").hide();  
        });       
 
        //검은 막을 눌렀을 때
        $("#mask").click(function () {  
            $(this).hide();  
            $(".window").hide();  
 
        });
        
        
        cookiedata = document.cookie; 
        if ( cookiedata.indexOf("notice-20200302=done") < 0 ) {
        	$("#mask, .window").show();
        }else {
        	$("#mask, .window").hide(); 
        }
 
    });
 
//]]>
</script>
</head>
<body>
    <div id="wrap"> 
        <div id="container">  
            <div id="mask"></div>
           	<div class="window">
                <div style="width:100%; height:100%; text-align:center; vertical-align:middle;">
                	<div class="notice-title" style="font-size: 15px; font-weight: 700">[ 3월 2일 업데이트 ]</div><br/>
                	<div class="notice-content-1">1. 출석부</div>
                	<div class="notice-content-2">패드용 출석부가</div>
                	<div class="notice-content-3">업데이트 되었습니다.^^</div>
                </div>
				<p style="text-align:center; background:#ffffff; padding:5px; margin-top:20px;">
					<button class="notice-close-btn" onclick="todayClose('notice-20200302',365)" style="margin-right: 3px;">다시 열지 않음</button>
					<button class="notice-close-btn close" style="margin-left: 3px;">닫기</button>
				</p>
            </div>
        </div>
    </div>
</body>


<script> 

function todayClose(name, value) { 
	setCookieAt00(name, 'done', value);
	$("#mask, .window").hide();
}

//00시 기준 으로 쿠키 값을 셋팅
function setCookieAt00( name, value, expiredays ) {  
	var todayDate = new Date(); 
	todayDate.setDate( todayDate.getDate() + expiredays ); 
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toUTCString() + ";";
}
	
</script> 

</html>

