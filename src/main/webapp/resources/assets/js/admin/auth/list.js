function remove(id, name) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	if(name == '국장'){
		
		Swal.fire({
            title: '국장 권한 초기화',
            html: '정말 초기화 하시겠습니까?<br>' + $targetInputHidden.val() + ' 권한의 데이터가 초기화 됩니다',
            showCancelButton: true,
            cancelButtonText: '취소',
            confirmButtonText: '확인',
            allowOutsideClick: true,
            reverseButtons: true
        }).then(function (result) {
            if(result.value) {
            	
            	var url = contextPath + "/rest/authExec/remove";
    			
    			$.ajax({
    		          type: "POST",
    		          url: url,
    		          success: function(result)
    		          {
    		              if(result.success) { // show response from the php script.
    		            	  Swal.fire({
    		                      text: '초기화 되었습니다',
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
    	                      text: '초기화에 실패 했습니다',
    	                      confirmButtonText: '확인',
    	                      allowOutsideClick: true
    	                  });
    		   		  }
    		    });
            	
            }
        });
		
	}else {
		
		Swal.fire({
            title: $targetInputHidden.val() + ' 권한 초기화',
            html: '정말 초기화 하시겠습니까?<br>' + $targetInputHidden.val() + ' 권한의 데이터가 순원으로 초기화 됩니다',
            showCancelButton: true,
            cancelButtonText: '취소',
            confirmButtonText: '확인',
            allowOutsideClick: true,
            reverseButtons: true
        }).then(function (result) {
            if(result.value) {
            	
            	var url = contextPath + "/rest/auth/remove";
    			
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
    		                      text: '초기화 되었습니다',
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
    	                      text: '초기화에 실패 했습니다',
    	                      confirmButtonText: '확인',
    	                      allowOutsideClick: true
    	                  });
    		   		  }
    		    });
            	
            }
        });

	}
	
}

