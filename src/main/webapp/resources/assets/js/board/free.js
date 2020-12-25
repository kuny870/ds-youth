// 게시글 등록
$("#registBoardFreeForm").submit(function(e) {
	
	var $content = $('#content');

    var validateMessage = null;
    var validateFocus = null;
    
    // input 데이터 체크 및 팝업text 입력, 포커스 입력
    if ($content.val() == "") {
         validateMessage = '내용을 입력해 주세요';
         validateFocus = $content;
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
    var url = contextPath + "/rest/boardFree"
    
    $.ajax({
          type: "POST",
          url: url,
          data: form.serialize(),
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
                  text: "내용 입력에 실패 했습니다",
                  confirmButtonText: '확인',
                  allowOutsideClick: true
              });
          }
    });
    e.preventDefault(); // avoid to execute the actual submit of the form.
  
});