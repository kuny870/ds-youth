<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="resourcesPath" value="${contextPath}/resources" />
	
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
	    top: 45%;
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
@media (min-width: 680px) {
	.memberMemoWindow{
    position: fixed;
    width: 55%;
    left: 57%;
    margin-left: -35%; /* half of width */
    /* height: 300px; */
    top: 45%;
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
    function memberMemoPopup(attId, memberId, mName, mMemo, profileImg, memoFlag, authId){

    	var memMemo = mMemo;
    	memMemo = memMemo.split('<br/>').join("\r\n");
    	memMemo = memMemo.split('#001').join("'");
    	memMemo = memMemo.split('#002').join('"');
    	
    	$('#mName').text('▷ ' + mName);
    	$('#memo').val(memMemo);
    	$('#attId').val(attId);
    	$('#memoPopId').val(memberId);
    	$('#memoPopName').val(mName);
    	
    	$('#originImgDiv').css('display', 'block');
    	$('#profileImgDiv').css('display', 'block');
    	
    	if(profileImg != "" && profileImg != null) {
    		document.getElementById('profileImgId').src= resourcesPath + '/assets/images/profileImg/' + profileImg;
    		$('#originImgDiv').css('display', 'none');
    	}else {
    		document.getElementById('originImgId').src= resourcesPath + '/assets/images/profile_img.jpg';
    		$('#profileImgDiv').css('display', 'none');
    	}
    	
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
        
        // 목사님이 지체상황 팝업 오픈 시 빨간 점 표시 없앰 memo_flag = 0
        if(memoFlag == "1" && authId == "2") {
        	
        	var url = contextPath + "/rest/member/memoFlag";
            $.ajax({
                type: "POST",
                url: url,
                traditional : true,
                data: {
                	id : memberId
                }, // serializes the form’s elements.
                success: function(result)
                {
                    if(result.success) { // show response from the php script.
                    	
                    	var jeomImg = document.getElementById(attId + '-jeom-img');
                    	jeomImg.style.display = 'none';
                    	
                    }else {

                    }
                },
         		  fail: function(result) {
         			  
         		  }
             });
        	
        }
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
           	
           		<div id="mName" style="font-size: 20px; margin-bottom: 5px;"></div>
           		<c:choose>
           			<c:when test="${ (((attendanceSearch.teamId == 4 || attendanceSearch.teamId == 8) && (login.teamId == 4 || login.teamId == 8)) && login.authId == 3) || 
           			(attendanceSearch.teamId == login.teamId && login.authId == 3) || 
           			login.authId < 3}">
           			
	           		<!-- <input style="display: none;" type="file" accept=".jpg, .heic" id="profile-img-change" name="profileImage"> -->
						<div id="originImgDiv" class="profile-img-wrap" style="text-align:right; margin-top:-32px; margin-right: 1px;">
							<img id="originImgId" src="" style="border-radius:7px; height:40px;">
						</div>
						<div id="profileImgDiv" class="profile-img-wrap" style="text-align:right; margin-top:-32px; margin-right: 1px;">
							<img id="profileImgId" src="" style="border-radius:7px; height:130px; max-width: 68%;">
						</div>
	           		
			            <div class="form">
			                <form enctype="application/x-www-form-urlencoded" id="memberPopModifyForm">
			                    <div class="form-middle">
			                    	<input type="hidden" id="attId" name="attId" value="" />
			                    	<input type="hidden" id="memoPopId" name="memoPopId" value="" />
			                    	<input type="hidden" id="memoPopName" name="memoPopName" value="" />
			                    	<input type="hidden" id="userAuthId" name="userAuthId" value="${login.authId }" />
			                    	
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
			
					</c:when>
					<c:otherwise>
						
						<div id="originImgDiv" style="">
							<img id="originImgId" src="" style="border-radius:7px; height:130px; margin-left: 15px; margin-bottom: 10px;">
						</div>
						<div id="profileImgDiv" style="">
							<img id="profileImgId" src="" style="border-radius:7px; height:200px; max-width:90%; margin-left: 15px; margin-bottom: 10px;">
						</div>
						
					</c:otherwise>
				</c:choose>
				
				
				
				
            </div>
        </div>
    </div>
</body>


<script>

</script>

</html>

