<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>EXCEL DOWNLAOD POPUP</title>
<style> 
/* 마스크 뛰우기 */
#excelDownMask {  
    position:absolute;  
    z-index:9000;  
    background-color:#000;  
    display:none;  
    left:0;
    top:0;
} 
/* 팝업으로 뜨는 윈도우 css  */ 
@media (max-width: 679px) {
	.excelDownWindow{
	    position: fixed;
	    width: 70%;
	    left: 50%;
	    margin-left: -35%; /* half of width */
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
}

@media (min-width: 680px) {
	.excelDownWindow{
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
    function excelDownPopup(){

    	//화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();  
        var maskWidth = $(window).width();  
 
        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#excelDownMask").css({"width":maskWidth,"height":maskHeight});  
 
        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.
 
        $("#excelDownMask").fadeIn(0);      
        $("#excelDownMask").fadeTo("slow",0.6);    
 
        //윈도우 같은 거 띄운다.
        $(".excelDownWindow").show();
 
    }
 
    $(document).ready(function(){
 
        //닫기 버튼을 눌렀을 때
        $(".excelDownWindow .excelDownClose").click(function (e) {  
            //링크 기본동작은 작동하지 않도록 한다.
            e.preventDefault();  
            $("#excelDownMask, .excelDownWindow").hide();  
        });       
 
        //검은 막을 눌렀을 때
        $("#excelDownMask").click(function () {  
            $(this).hide();  
            $(".excelDownWindow").hide();  
 
        });
        
    });
    
 
//]]>
</script>
</head>
<body>
    <div id="wrap"> 
        <div id="container">  
            <div id="excelDownMask"></div>
           	<div class="excelDownWindow">
                <div style="width:100%; height:100%; text-align:center; vertical-align:middle;">
                	<div style="font-size: 16px; font-weight: 700; margin-top:15px; margin-bottom: 10px;">엑셀 다운로드</div>
                	
                	<br/>
                	
						<div class="customer-select-search" style="width: 40%; margin-left: 6.5%; float: left;">
							<!-- 년 선택 -->
	                   		<select class="select-admin-pop-excel-year" id="yearExcel" name="yearExcel" onchange="getSeason(this.value);">
		                       	<c:forEach var="year" items="${yearList }">
		                       		<c:set var="selected" value="" />
									<c:if test="${year eq attendanceSearch.year }">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${year}" ${selected} >${year}년</option>
								</c:forEach>
		                    </select>
	                    </div>    
                    
	                    <div class="customer-select-search" style="width: 40%; margin-left: 7%; float: left;">
							<!-- 상반기 / 하반기 선택 -->
	                   		<select class="select-admin-pop-excel-season" id="seasonExcel" name="season">
		                       	<c:forEach var="ss" items="${seasonList }">
		                       		<c:set var="selected" value="" />
									<c:if test="${ss.season eq attendanceSearch.thisSeason }">
										<c:set var="selected" value="selected" />
									</c:if>
									<option value="${ss.season}" ${selected} >${ss.season}</option>
								</c:forEach>
		                    </select>
	                    </div>
	                    <br/>
	                    
                </div>
                
                <br/><br/>
				
				<div style="text-align: center; margin-bottom: 25px;">
					<button class="excel-down-btn" onclick="excelDown()" style="font-size: 14px; width: 87%;">다운로드</button>
				</div>
				
				<!-- <p style="text-align:center; background:#ffffff; padding:5px; margin-top:20px;">
					<button class="excel-close-btn close">닫기</button>
				</p> -->
            </div>
        </div>
    </div>
</body>

</html>

