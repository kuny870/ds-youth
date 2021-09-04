//멤버 복구
function restore(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	Swal.fire({
        title: '팀원 복구',
        html: '정말 복구 시키시겠습니까?<br>' + $targetInputHidden.val() + ' 의 정보가 복구 됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/member/restore";
    		
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
                        text: "멤버 복구에 실패 했습니다",
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    });
    	   		  }
    	    });
    		
    	}
    	
    });

}