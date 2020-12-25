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
            	  Swal.fire({
	                    text: result.message,
	                    confirmButtonText: '확인',
	                    allowOutsideClick: true
	                });

              }
          },
   		  fail: function(result) {
   			Swal.fire({
                text: "로그아웃에 실패 했습니다",
                confirmButtonText: '확인',
                allowOutsideClick: true
            });
   		  }
    });
}

// 회원 탈퇴
function withdraw(val) {
	
	Swal.fire({
        title: '회원 탈퇴',
        html: '정말 탈퇴 하시겠습니까?',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
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
    	            	  Swal.fire({
    		                    text: result.message,
    		                    confirmButtonText: '확인',
    		                    allowOutsideClick: true
    		                });

    	              }
    	          },
    	   		  fail: function(result) {
    	   			Swal.fire({
                        text: "회원탈퇴에 실패 했습니다",
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    });
    	   		  }
    	    });
    		
    	}
    	
    });

}