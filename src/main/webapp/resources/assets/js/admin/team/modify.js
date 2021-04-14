//팀 정보 수정
$("#teamModifyForm").submit(function(e) {

	var form = $(this);
	var url = contextPath + "/rest/team/modify";
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
                      text: "팀 정보가 변경 되었습니다",
                      confirmButtonText: '확인',
                      allowOutsideClick: true
                  }).then(function() {
                	  location.href = contextPath + "/admin/team";
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
                text: "팀 정보 변경에 실패 했습니다",
                confirmButtonText: '확인',
                allowOutsideClick: true
            });
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

