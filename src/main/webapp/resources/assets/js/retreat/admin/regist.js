// 수련회 등록
$("#retreatRegistForm").submit(function(e) {

	if(doubleSubmitCheck()) return;
	
	var $retreatName = $('#retreatName');
	
	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($retreatName.val() == "") {
		validateMessage = '수련회명을 입력해 주세요';
		validateFocus = $retreatName;
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
	var url = contextPath + "/rest/retreat/regist";
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
                      text: "수련회가 등록 되었습니다",
                      confirmButtonText: '확인',
                      allowOutsideClick: true
                  }).then(function() {
                	  retreat();
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
   			    text: "수련회 등록에 실패 했습니다",
   			    confirmButtonText: '확인',
   			    allowOutsideClick: true
   			});
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

