function remove(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	var conf = confirm('정말 삭제 하시겠습니까?\n' + $targetInputHidden.val() + '의 데이터가 전부 삭제됩니다.');

	if(conf){

		var url = contextPath + "/rest/retreat/remove"
		
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
	            	  alert(result.message);
	              }
	          },
	   		  fail: function(result) {
	   			  alert("수련회 삭제에 실패 했습니다.");
	   		  }
	    });
	}

}