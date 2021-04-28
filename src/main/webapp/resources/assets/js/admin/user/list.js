// 임시 비밀번호 발급
function pwReset(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	Swal.fire({
        title: '임시 비밀번호 발급',
        html: '임시 비밀번호를 발급하시겠습니까?<br>' + $targetInputHidden.val() + ' 의 비밀번호가 0000 으로 변경됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/user/pw/reset";
    		
    		$.ajax({
    	          type: "POST",
    	          url: url,
    	          data: {
    	        	  id : id
    	          }, // serializes the form’s elements.
    	          success: function(result)
    	          {
    	              if(result.success) { // show response from the php script.
    	            	  Swal.fire({
    		                    text: "변경 되었습니다",
    		                    confirmButtonText: '확인',
    		                    allowOutsideClick: true
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
    	   			    text: "임시 비밀번호 발급에 실패 했습니다",
    	   			    confirmButtonText: '확인',
    	   			    allowOutsideClick: true
    	   			});
    	   		  }
    	    });
    		
    	}

    });

}


// 회원 강퇴
function remove(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	Swal.fire({
        title: '회원 탈퇴',
        html: '정말 탈퇴 시키시겠습니까?<br>' + $targetInputHidden.val() + ' 의 정보가 전부 삭제됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/user/remove";
    		
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
                        text: "회원 탈퇴에 실패 했습니다",
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    });
    	   		  }
    	    });
    		
    	}
    	
    });

}


//회원 복구
function restore(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	Swal.fire({
        title: '회원 복구',
        html: '정말 복구 시키시겠습니까?\n' + $targetInputHidden.val() + ' 의 정보가 복구 됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/user/restore";
    		
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
                        text: "회원 복구에 실패 했습니다",
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    });
    	   		  }
    	    });
    		
    	}
    	
    });

}
