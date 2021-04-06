<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<title>MEMBER_MEMO POPUP</title>
<style>
/* 마스크 뛰우기 */
#memberMemoMask {
    position:absolute;
    z-index:9000;
    background-color:#000;
    display:none;
    left:0;
    top:0;
}
/* 팝업으로 뜨는 윈도우 css  */
@media (max-width: 679px) {
	.memberMemoWindow{
	    position: fixed;
	    width: 80%;
	    left: 50%;
	    margin-left: -40%; /* half of width */
	    /* height: 300px; */
	    top: 50%;
	    margin-top: -190px; /* half of height */
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
	.memberMemoWindow{
    position: fixed;
    width: 55%;
    left: 57%;
    margin-left: -35%; /* half of width */
    /* height: 300px; */
    top: 45%;
    margin-top: -170px; /* half of height */
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
    function memberMemoPopup(attId, memberId, mName, mMemo){

    	var memMemo = mMemo;
    	memMemo = memMemo.split('<br/>').join("\r\n");
    	memMemo = memMemo.split('#001').join("'");
    	memMemo = memMemo.split('#002').join('"');
    	
    	$('#mName').text('▷ ' + mName);
    	$('#memo').val(memMemo);
    	$('#attId').val(attId);
    	$('#memoPopId').val(memberId);
    	$('#memoPopName').val(mName);
    	
        //화면의 높이와 너비를 구한다.
        var maskHeight = $(document).height();
        var maskWidth = $(window).width();

        //마스크의 높이와 너비를 화면 것으로 만들어 전체 화면을 채운다.
        $("#memberMemoMask").css({"width":maskWidth,"height":maskHeight});

        //애니메이션 효과 - 일단 0초동안 까맣게 됐다가 60% 불투명도로 간다.

        $("#memberMemoMask").fadeIn(0);
        $("#memberMemoMask").fadeTo("slow",0.6);

        //윈도우 같은 거 띄운다.
        $(".memberMemoWindow").show();
        
        getSayu($("#sundays").val());
    }

    $(document).ready(function(){

        //닫기 버튼을 눌렀을 때
        $(".memberMemoWindow .memberMemoClose").click(function (e) {
            //링크 기본동작은 작동하지 않도록 한다.
            e.preventDefault();
            $("#memberMemoMask, .memberMemoWindow").hide();
        });

        //검은 막을 눌렀을 때
        $("#memberMemoMask").click(function () {
            $(this).hide();
            $(".memberMemoWindow").hide();

        });

    });

//]]>
</script>
</head>
<body>
    <div id="memo-wrap">
        <div id="memo-container">
            <div id="memberMemoMask"></div>
           	<div class="memberMemoWindow">
           		<div id="mName" style="font-size: 20px; margin-bottom: 10px;"></div>
	            <div class="form">
	                <form enctype="application/x-www-form-urlencoded" id="memberPopModifyForm">
	                    <div class="form-middle">
	                    	<input type="hidden" id="attId" name="attId" value="" />
	                    	<input type="hidden" id="memoPopId" name="memoPopId" value="" />
	                    	<input type="hidden" id="memoPopName" name="memoPopName" value="" />
	                        <!-- <label>
	                            <div style="font-size: 12px; margin-bottom: 5px;">최근 3개월 출석률 : </div>
	                        </label>
	                        <label>
	                            <div style="font-size: 12px; margin-bottom: 5px;">최근 6개월 출석률 : </div>
	                        </label>
	                        <label>
	                            <div style="font-size: 12px; margin-bottom: 5px;">최근 1년간 출석률 : </div>
	                        </label> -->
	                        <p style="margin-bottom: 15px; font-size: 15px;">지체 상황</p>
	                        <label>
	                            <textarea style="margin-top:-10px; margin-bottom:10px; height:120px; width:100%;" rows="20" id="memo" name="memo" placeholder=""></textarea>
	                        </label>
	                        
	                        <p style="margin-top: 5px; margin-bottom: 10px; font-size: 15px;">예배 불참 사유</p>
	                        
	                        <div class="sayu-month">
	                        	${attendanceSearch.month}월
	                        </div>
		                    
		                    <div style="text-align: center;">
				        		<select class="basic-select sayu-date-select" id="sundays" name="sundays" onchange="getSayu(this.value)">
				        			<c:forEach var="sun" items="${sunday }" varStatus="i">
										<option value="${i.index+1}">${sun}일</option>
									</c:forEach>
				        		</select>
				        		<input type="text" class="basic-input sayu-input" id="sayu" name="sayu" placeholder="사유" value="" autocomplete="off">
				 	        </div>
	                    </div>
	                </form>
	            </div>
	            <div>
					<p style="text-align:center; background:#ffffff; padding:5px; margin-top:15px;">
						<button class="memberMemoRegist" onclick="memberMemoAndSayuRegist()">저장</button>
						<button class="memberMemoClose">닫기</button>
					</p>
				</div>
            </div>
        </div>
    </div>
</body>


<script>

</script>

</html>

