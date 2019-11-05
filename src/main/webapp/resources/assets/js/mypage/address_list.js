function addressRemove(val) {
		
		var conf = confirm('삭제하시겠습니까?');

		if(conf){

			var url = contextPath + "/rest/mypage/address/remove"
			
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
		   			  alert("주소 삭제에 실패했습니다.");
		   		  }
		    });
		}

	}
