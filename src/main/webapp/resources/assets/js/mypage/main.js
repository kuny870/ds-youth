// 로그아웃
function logout(val) {
	
	if(doubleSubmitCheck()) return;
	
	var url = contextPath + "/rest/logout";
	
	$.ajax({
          type: "POST",
          url: url,
          data: {
        	  loginId : val
          }, // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  location.href = contextPath + "/dsyouth/login";
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

