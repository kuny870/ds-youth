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
	
	if(headTitle.length > 15 && headTitle.length < 22) {
		$(".head_title").addClass('font-size-5vw');
	}else if(headTitle.length >= 22 && headTitle.length < 25){
		$(".head_title").addClass('font-size-4p5vw');
	}else if(headTitle.length >= 25 && headTitle.length < 28){
		$(".head_title").addClass('font-size-4vw');
	}else if(headTitle.length >= 28){
		$(".head_title").addClass('font-size-3p5vw');
	}
	
});

// 팝업창 다시 열지 않음 설정
function getCookie( name )
{
    var nameOfCookie = name + "=";
    var x = 0;
    while ( x <= document.cookie.length )
    {
        var y = (x+nameOfCookie.length);
        if ( document.cookie.substring( x, y ) == nameOfCookie )
        {
            if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )
                endOfCookie = document.cookie.length;
            return unescape( document.cookie.substring( y, endOfCookie ) );
        }
        x = document.cookie.indexOf( " ", x ) + 1;
        if ( x == 0 )
            break;
    }
    return "";
}


function excelDown(year, month) {

	var season = "";
	
	if(month < 7) {
		season = "상반기";
	}else {
		season = "하반기";
	}
	
	location.href = contextPath + "/excelDownload?year=" + year + "&season=" + season;
}

// input 자리수 제한
function numberMaxLength2(e) { if(e.value.length > e.maxLength) { e.value = e.value.slice(0, e.maxLength); }}
function numberMaxLength4(e) { if(e.value.length > e.maxLength) { e.value = e.value.slice(0, e.maxLength); }}
function numberMaxLength6(e) { if(e.value.length > e.maxLength) { e.value = e.value.slice(0, e.maxLength); }}
function numberMaxLength11(e) { if(e.value.length > e.maxLength) { e.value = e.value.slice(0, e.maxLength); }}

// 페이지 이동
function mypage() {location.href = contextPath + "/mypage";}				// mypage
function adminList() {location.href = contextPath + "/admin/list";}			// 관리 list
function user() {location.href = contextPath + "/admin/user";}			// 회원 관리
function auth() {location.href = contextPath + "/admin/auth";}				// 권한 관리
function depart() {location.href = contextPath + "/admin/depart";}			// 부서 관리
function team() {location.href = contextPath + "/admin/team";}				// 팀 관리
function group() {location.href = contextPath + "/admin/group/name";}		// 순 관리

function groupList() {						// 순 list
	var year = $('#year').val();
	var season = $('#season').val();
	location.href = contextPath + "/admin/group/name?year=" + year + "&season=" + season;
}	

function samePeriod() {location.href = contextPath + "/admin/samePeriod";}	// 동기 관리

function memberList() {		// 멤버 list (검색 정보 포함)
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	location.href = contextPath + "/member/list?pageNo=" + pageNo + "&teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + sNameKW;
}
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
