//비밀번호 수정
$("#pwEditForm").submit(function(e) {

	if(doubleSubmitCheck()) return;
	
	var $currentPw = $('#currentPw');
	var $loginPw = $('#loginPw');
	var $loginPwConfirm = $('#loginPwConfirm');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($currentPw.val() == "") {
		validateMessage = '현재 비밀번호를 입력해 주세요';
		validateFocus = $currentPw;
	} else if ($loginPw.val() == "") {
		validateMessage = '변경할 비밀번호를 입력해 주세요';
		validateFocus = $loginPw;
	} else if ($loginPw.val().length < 4) {
		validateMessage = '변경할 비밀번호를 4자리 이상 입력해 주세요';
		validateFocus = $loginPw;
	} else if ($loginPw.val() != $loginPwConfirm.val()) {
		validateMessage = '변경할 비밀번호가 일치하지 않습니다';
		validateFocus = $loginPwConfirm;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		Swal.fire({
            text: validateMessage,
            confirmButtonText: '확인',
            allowOutsideClick: true
        });
		doubleSubmitFlag = false;
		return false;
	}
	
	var form = $(this);
	var url = contextPath + "/rest/pw/reset";
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
                      text: "비밀번호가 변경 되었습니다",
                      confirmButtonText: '확인',
                      allowOutsideClick: true
                  }).then(function() {
                	  location.href = contextPath + "/mypage";
                  });
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
   			    text: "비밀번호 변경에 실패 했습니다",
   			    confirmButtonText: '확인',
   			    allowOutsideClick: true
   			});
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});