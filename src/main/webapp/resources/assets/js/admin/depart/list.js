//부서등록
$("#registDepartForm").submit(function(e) {

	var $dName = $('#dName');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($dName.val() == "") {
		validateMessage = '부서를 입력해 주세요';
		validateFocus = $dName;
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
	var url = contextPath + "/rest/depart/regist"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
                      text: "부서가 등록 되었습니다",
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
                text: "부서 등록에 실패 했습니다",
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
        title: '부서 초기화',
        html: '정말 삭제 하시겠습니까?<br>' + $targetInputHidden.val() + ' 부서의 데이터가 전부 삭제됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/depart/remove"
    		
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
                        text: "부서 삭제에 실패 했습니다",
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    });
    	   		  }
    	    });
    		
    	}
    });

}
