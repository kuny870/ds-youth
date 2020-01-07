$(document).ready(function(){
	
	// 공지 팝업창
	var popSeq = '1';
    var closeUsableYN = 'Y';
    var scrollbarYN = 'Y';
    var closeNoticeDate = '다시 열지 않음';
    var title = '공지';
    var content = '앱으로 이용이 불가하여 웹 링크로 사용 부탁드립니다.\n http://dsyouth-env.jzz59thtne.ap-northeast-2.elasticbeanstalk.com/login';
    var widthSize = '300';
    var heightSize = '150';
    var widthLocation = '50';
    var heightLocation = '50';
    var url = contextPath + '/admin/pop/notice?content='+content+'&popSeq=' +popSeq+'&closeUsableYN=' +closeUsableYN+'&closeNoticeDate=' +closeNoticeDate+'&scrollbarYN=' +scrollbarYN;
    var winOption = "width=" + widthSize + ", height=" + heightSize + ",top=" + heightLocation + ",left=" + widthLocation;
    
    if(getCookie("Notice"+popSeq) == "done"){
    }else{
//    	window.open(url, title, winOption);
    }
    
});


// 로그아웃
function logout(val) {
	
	var url = contextPath + "/rest/logout"
	
	$.ajax({
          type: "POST",
          url: url,
          data: {
        	  id : val
          }, // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  location.href = contextPath + "/login";
              }else {
            	  alert(result.message);
              }
          },
   		  fail: function(result) {
   			  alert("로그아웃에 실패 했습니다.");
   		  }
    });
}

// 회원 탈퇴
function withdraw(val) {
	
	var conf = confirm('정말 탈퇴 하시겠습니까?');

	if(conf){

		var url = contextPath + "/rest/withdraw"
		
		$.ajax({
	          type: "POST",
	          url: url,
	          data: {
	        	  id : val
	          }, // serializes the form’s elements.
	          success: function(result)
	          {
	              if(result.success) { // show response from the php script.
	            	  location.href = contextPath + "/login";
	              }else {
	            	  alert(result.message);
	              }
	          },
	   		  fail: function(result) {
	   			  alert("회원탈퇴에 실패 했습니다.");
	   		  }
	    });
	}

}