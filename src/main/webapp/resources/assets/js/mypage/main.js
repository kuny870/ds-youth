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