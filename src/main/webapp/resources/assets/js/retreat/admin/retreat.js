function remove(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	Swal.fire({
        title: '수련회 초기화',
        html: '정말 삭제 하시겠습니까?<br>' + $targetInputHidden.val() + '의 데이터가 전부 삭제됩니다.',
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/retreat/remove";
    		
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
    	   			    text: "수련회 삭제에 실패 했습니다",
    	   			    confirmButtonText: '확인',
    	   			    allowOutsideClick: true
    	   			});
    	   		  }
    	    });
    		
    	}
    	
    });

}