function fnGetCtgSub(sParam){
    var $target = $("select[name='teamId']");
     
    $target.empty();
    if(sParam == ""){
    	$target.append("<option value=''>팀선택</option>");
        return;
    } else if (sParam != "") {
    	
	    $.ajax({
	        type: "POST",
	        url: contextPath + "/rest/select/depart",
	        async: false,
	        data:{ departId : sParam },
	        dataType: "json",
	        success: function(result) {
	            if(result.data.length == 0){
	                $target.append("<option value=''>팀선택</option>");
	            }else{
	                $(result.data).each(function(i){
	                    $target.append("<option value=" + result.data[i].id + ">"+ result.data[i].tShortName +"</option>");
	                });
	            }
	        }, error:function(xhr){
	            console.log(xhr.responseText);
	            Swal.fire({
	                text: "팀 정보를 불러오는데 실패 했습니다",
	                confirmButtonText: '확인',
	                allowOutsideClick: true
	            });
	            return;
	        }
	    });
	    
    }
    
}



//회원가입
$("#joinForm").submit(function(e) {

	if(doubleSubmitCheck()) return;
	
	var idRegex = /^[a-z]{4,15}/g;
	var dateOfBirthRegex=/^[0-9]{6}$/;
	var htelRegex=/^[0-9]{10,11}$/;
	
	var $loginId = $('#loginId');
	var $loginPw = $('#loginPw');
	var $loginPwConfirm = $('#loginPwConfirm');
	var $name = $('#name');
	var $dateOfBirth = $('#dateOfBirth');
	var $htel = $('#htel');
	var $departId = $('#departId');
	var $teamId = $('#teamId');
	var $gender = $('input[name="gender"]:checked');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($loginId.val() == "") {
		validateMessage = '아이디를 입력해 주세요';
		validateFocus = $loginId;
	} else if (idRegex.test($loginId.val())===false) {
		validateMessage = '아이디는 소문자 영문으로 시작해야 하며 4-15자리이여야 합니다.';
		validateFocus = $loginId;
	} else if ($loginPw.val() == "") {
		validateMessage = '비밀번호를 입력해 주세요';
		validateFocus = $loginPw;
	} else if ($loginPw.val().length < 4) {
		validateMessage = '비밀번호를 4자리 이상 입력해 주세요';
		validateFocus = $loginPw;
	} else if ($loginPw.val() != $loginPwConfirm.val()) {
		validateMessage = '비밀번호가 일치하지 않습니다';
		validateFocus = $loginPwConfirm;
	} else if ($name.val() == "") {
		validateMessage = '이름을 입력해 주세요';
		validateFocus = $name;
	} else if ($dateOfBirth.val() != "" && dateOfBirthRegex.test($dateOfBirth.val())===false) {
		validateMessage = '생년월일을 다시 확인해 주세요';
		validateFocus = $dateOfBirth;
	} else if ($htel.val() != "" && htelRegex.test($htel.val())===false) {
		validateMessage = '휴대폰 번호를 다시 확인해 주세요';
		validateFocus = $htel;
	} else if ($departId.val() == null || $departId.val() == "") {
		validateMessage = '부서를 선택해 주세요';
		validateFocus = $departId;
	} else if ($teamId.val() == null || $teamId.val() == "") {
		validateMessage = '팀을 선택해 주세요';
		validateFocus = $teamId;
	} else if ($gender.val() == null) {
		validateMessage = '성별을 선택해 주세요';
		validateFocus = $gender;
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
	var url = contextPath + "/rest/join";
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
                      text: "회원가입 되었습니다",
                      confirmButtonText: '확인',
                      allowOutsideClick: true
                  }).then(function() {
                	  location.href = contextPath + "/login";
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
                text: "회원가입에 실패 했습니다",
                confirmButtonText: '확인',
                allowOutsideClick: true
            });
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

