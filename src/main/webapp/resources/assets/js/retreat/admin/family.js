//가족 등록
$("#registFamilyForm").submit(function(e) {

	var $famName = $('#famName');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($famName.val() == "") {
		validateMessage = '가족명을 입력해 주세요.';
		validateFocus = $famName;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		alert(validateMessage);
		return false;
	}
	
	var form = $(this);
	var url = contextPath + "/rest/family/regist"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  alert("가족명이 등록 되었습니다.")
            	  location.reload();
              }else {
            	  alert(result.message);
              }
          },
   		  fail: function(result) {
   			  alert("가족명 등록에 실패 했습니다.");
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});


function remove(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	var conf = confirm('정말 삭제 하시겠습니까?\n' + $targetInputHidden.val() + ' 가족의 데이터가 전부 삭제됩니다.');

	if(conf){

		var url = contextPath + "/rest/family/remove"
		
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
	   			  alert("가족 삭제에 실패 했습니다.");
	   		  }
	    });
	}

}

