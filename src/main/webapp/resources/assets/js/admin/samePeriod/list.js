//동기 등록
$("#registSamePeriodForm").submit(function(e) {

	var $birthYear = $('#birthYear');
	
	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($birthYear.val() == "") {
		validateMessage = '탄생년을 입력해 주세요';
		validateFocus = $birthYear;
	} else if (year - $birthYear.val() < 19 ) {
		validateMessage = '올해 청년부는\n' + (year - 19) + '년생부터 입니다.';
		$('#birthYear').val('');
		validateFocus = $birthYear;
	} else if (year - $birthYear.val() > 59 ) {
		validateMessage = '60세 이상 동기는\n등록 불가합니다.';
		$('#birthYear').val('');
		validateFocus = $birthYear;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		Swal.fire({
            text: validateMessage,
            confirmButtonText: '확인',
            allowOutsideClick: true
        });
		return false;
	}
	
	var form = $(this);
	var url = contextPath + "/rest/samePeriod/regist"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
                      text: "새로운 동기가 등록 되었습니다",
                      confirmButtonText: '확인',
                      allowOutsideClick: true
                  }).then(function() {
                	  location.reload();
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
                text: "동기 등록에 실패 했습니다",
                confirmButtonText: '확인',
                allowOutsideClick: true
            });
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});


function remove(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	Swal.fire({
        title: '동기 초기화',
        html: '정말 삭제 하시겠습니까?<br>' + $targetInputHidden.val() + '년생 동기의 데이터가 전부 삭제됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/samePeriod/remove"
    		
    		$.ajax({
    	          type: "POST",
    	          url: url,
    	          data: {
    	        	  id : id
    	          }, // serializes the form’s elements.
    	          success: function(result)
    	          {
    	              if(result.success) { // show response from the php script.
    	            	  location.reload();
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
                        text: "동기 삭제에 실패 했습니다",
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    });
    	   		  }
    	    });
    		
    	}
    	
    });

}
