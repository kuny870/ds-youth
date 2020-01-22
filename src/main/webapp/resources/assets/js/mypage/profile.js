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
	            alert("팀 정보를 불러오는데 실패 했습니다.");
	            return;
	        }
	    });
	    
    }
    
}

//기본정보 수정
$("#profileForm").submit(function(e) {

	var dateOfBirthRegex=/^[0-9]{6}$/;
	var htelRegex=/^[0-9]{10,11}$/;
	
	var $loginId = $('#loginId');
	var $name = $('#name');
	var $dateOfBirth = $('#dateOfBirth');
	var $htel = $('#htel');
	var $depart = $('#depart');
	var $team = $('#team');
	var $gender = $('input[name="gender"]:checked');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($loginId.val() == "") {
		validateMessage = '아이디를 입력해 주세요.';
		validateFocus = $loginId;
	} else if ($name.val() == "") {
		validateMessage = '이름을 입력해 주세요.';
		validateFocus = $name;
	} else if ($dateOfBirth.val() != "" && dateOfBirthRegex.test($dateOfBirth.val())===false) {
		validateMessage = '생년월일을 다시 확인해 주세요.';
		validateFocus = $dateOfBirth;
	} else if ($htel.val() != "" && htelRegex.test($htel.val())===false) {
		validateMessage = '휴대폰 번호를 다시 확인해 주세요.';
		validateFocus = $htel;
	} else if ($depart.val() == "선택") {
		validateMessage = '부서를 선택해 주세요.';
		validateFocus = $depart;
	} else if ($team.val() == "선택") {
		validateMessage = '팀을 선택해 주세요.';
		validateFocus = $team;
	} else if ($gender.val() == null) {
		validateMessage = '성별을 선택해 주세요.';
		validateFocus = $gender;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		alert(validateMessage);
		return false;
	}
	
	var form = $(this);
	var url = contextPath + "/rest/profile/reset"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  alert("기본정보가 변경 되었습니다.")
            	  location.href = contextPath + "/mypage";
              }else {
            	  alert(result.message);
              }
          },
   		  fail: function(result) {
   			  alert("기본정보 수정에 실패 했습니다.");
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

