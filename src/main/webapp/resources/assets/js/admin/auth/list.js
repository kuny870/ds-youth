function remove(id, name) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	if(name == '국장'){
		
		var conf = confirm('정말 초기화 하시겠습니까?\n' + $targetInputHidden.val() + ' 권한의 데이터가 초기화 됩니다.');

		if(conf){

			var url = contextPath + "/rest/authExec/remove"
			
			$.ajax({
		          type: "POST",
		          url: url,
		          success: function(result)
		          {
		              if(result.success) { // show response from the php script.
		            	  location.reload();
		              }else {
		            	  alert(result.message);
		              }
		          },
		   		  fail: function(result) {
		   			  alert("국장 권한 초기화에 실패 했습니다.");
		   		  }
		    });
		}
		
	}else {
		
		var conf = confirm('정말 초기화 하시겠습니까?\n' + $targetInputHidden.val() + ' 권한의 데이터가 순원으로 초기화 됩니다.');

		if(conf){

			var url = contextPath + "/rest/auth/remove"
			
			$.ajax({
		          type: "POST",
		          url: url,
		          data: {
		        	  id : val
		          }, // serializes the form’s elements.
		          success: function(result)
		          {
		              if(result.success) { // show response from the php script.
		            	  location.reload();
		              }else {
		            	  alert(result.message);
		              }
		          },
		   		  fail: function(result) {
		   			  alert("권한 초기화에 실패 했습니다.");
		   		  }
		    });
		}
		
	}
	
}

