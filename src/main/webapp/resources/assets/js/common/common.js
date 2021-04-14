$(document).ready(function(){
	
	// IOS Safari에서 Pinch Zoom 막기
	var lastTouchEnd = 0;
	document.documentElement.addEventListener('touchend', function (event) {
	    var now = (new Date()).getTime();
	    if (now - lastTouchEnd <= 300) {
	      event.preventDefault();
	    }
	    lastTouchEnd = now;
	}, false);
	
	// 맨 상단 타이틀 글자크기에 따른 폰트 조절
	var headTitle = $(".head_title").html();
	
	if(headTitle != null){
		if(headTitle.length > 15 && headTitle.length < 22) {
			$(".head_title").addClass('font-size-5vw');
		}else if(headTitle.length >= 22 && headTitle.length < 25){
			$(".head_title").addClass('font-size-4p5vw');
		}else if(headTitle.length >= 25 && headTitle.length < 28){
			$(".head_title").addClass('font-size-4vw');
		}else if(headTitle.length >= 28){
			$(".head_title").addClass('font-size-3p5vw');
		}
	}
	
	// 2020.03.05 모바일 터치 클릭 이벤트 - 음영효과
    $("button").bind("touchstart", function(e){
        $(this).css("filter", "brightness(80%)");
    });
    $("button").bind("touchend", function(e){
        $(this).css("filter", "none");
    });
    $("a").bind("touchstart", function(e){
        $(this).css("filter", "brightness(80%)");
    });
    $("a").bind("touchend", function(e){
        $(this).css("filter", "none");
    });
    $(".shop-link-login").bind("touchstart", function(e){
        $(this).css("filter", "brightness(80%)");
    });
    $(".shop-link-login").bind("touchend", function(e){
        $(this).css("filter", "none");
    });
    $(".attendance-img").bind("touchstart", function(e){
        $(this).css("filter", "brightness(80%)");
    });
    $(".attendance-img").bind("touchend", function(e){
        $(this).css("filter", "none");
    });
    $(".dsyouth-link").bind("touchstart", function(e){
        $(this).css("filter", "brightness(80%)");
    });
    $(".dsyouth-link").bind("touchend", function(e){
        $(this).css("filter", "none");
    });
    
});


/*function excelDown(year, month) {

	var season = "";
	
	if(month < 7) {
		season = "상반기";
	}else {
		season = "하반기";
	}
	
	location.href = contextPath + "/excelDownload?year=" + year + "&season=" + season;
}*/

// input 자리수 제한
function numberMaxLength(e) { if(e.value.length > e.maxLength) { e.value = e.value.slice(0, e.maxLength); }}

// 페이지 이동
function mypage() {location.href = contextPath + "/mypage";}				// mypage
function adminList() {location.href = contextPath + "/admin/list";}			// 관리 list
function user() {location.href = contextPath + "/admin/user";}			// 회원 관리
function auth() {location.href = contextPath + "/admin/auth";}				// 권한 관리
function depart() {location.href = contextPath + "/admin/depart";}			// 부서 관리
function team() {location.href = contextPath + "/admin/team";}				// 팀 관리
function group() {location.href = contextPath + "/admin/group/name";}		// 순 관리

//순 list
function groupList() {
	var year = $('#year').val();
	var seasonFlag = $('#seasonFlag').val();
	location.href = contextPath + "/admin/group/name?year=" + year + "&seasonFlag=" + seasonFlag;
}
//순 list 조회
function groupSearch() {
	var year = $('#year').val();
	var seasonFlag = $('#seasonFlag').val();
	location.href = contextPath + "/admin/group/name?year=" + year + "&seasonFlag=" + seasonFlag;
}

function samePeriod() {location.href = contextPath + "/admin/samePeriod";}	// 동기 관리
function samePeriodList() {location.href = contextPath + "/samePeriod/list";}	// 동기 list 
function leaderInfoList() {location.href = contextPath + "/leaderInfo/list";}	// 리더배포자료 list 
function voteCompleted() {location.href = contextPath + "/mypage/vote";}	// 완료된 투표
function addressList() {location.href = contextPath + "/mypage/address/list/" + loginId;}	// 주소 list
function addressRegist() {location.href = contextPath + "/mypage/address/regist";}	//	주소 등록
function retreat() {location.href = contextPath + "/admin/retreat/list";}	// 수련회 목록
function retreatRegist() {location.href = contextPath + "/admin/retreat/regist";}		// 수련회 등록

function family() {			// 수련회 가족 list (검색 정보 포함)
	var fId = $('#fId').val();
	location.href = contextPath + "/admin/retreat/family/list/" + fId;
}

//출석 조회
function attendanceSearch() {
	var teamId = $('#teamId').val();
	var year = $('#year').val();
	var month = $('#month').val();
	location.href = contextPath + "/attendance/list?teamId=" + teamId + "&year=" + year + "&month=" + month;
}

//팀원 list (검색 정보 포함)
function memberList() {		
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	var name = encodeURI(encodeURI(sNameKW));
	location.href = contextPath + "/member/list?pageNo=" + pageNo + "&teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + name;
}

//팀원 등록 페이지 이동
function memberRegist() {
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	location.href = contextPath + "/member/regist?teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + sNameKW + "&pageNo=" + pageNo;
}

// 팀원 검색
function memberSearch() {
	var teamId = $('#teamId').val();
	var groupId = $('#groupId').val();
	var nameKW = $('#nameKW').val();
	var name = encodeURI(encodeURI(nameKW));
	var param = {pageNo : 1, teamId : teamId, groupId : groupId, nameKW : name};
	pageModule.searchPage(param);
}

// 출석부 엑셀 다운 실제 구현부
function excelDown() {
	var year = $('#yearExcel').val();
	var season = $('#seasonExcel').val();
	season = encodeURI(encodeURI(season));
	
	var month = "";

	if(season == "상반기") {
		month = "1";
	}else if(season == "하반기") {
		month = "7";
	}else if(season == "코로나순") {
		month = "10";
	}
	
	location.href = contextPath + "/attendance/excelDownload?year=" + year + "&month=" + month + "&season=" + season;
	
	$("#mask, .window").hide();
}

// 공통 팝업
function openPopup(str) {
	Swal.fire({
        text: str,
        confirmButtonText: '확인',
        allowOutsideClick: true
    });
}

// 모바일 기기 체크
function isMobile(){

	var UserAgent = navigator.userAgent;

	if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null)
	{
		return true;
	}else{
		return false;
	}

}

/**
 * 중복서브밋 방지
 */
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
}