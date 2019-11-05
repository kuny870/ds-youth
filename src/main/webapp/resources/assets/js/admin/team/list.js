//팀 등록
$("#registTeamForm").submit(function(e) {

	var $departId = $('#departId');
	var $team = $('#team');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($departId.val() == "부서선택") {
		validateMessage = '부서를 선택해 주세요.';
		validateFocus = $departId;
	} else if ($team.val() == "") {
		validateMessage = '팀을 입력해 주세요.';
		validateFocus = $team;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		alert(validateMessage);
		return false;
	}
	
	var form = $(this);
	var url = contextPath + "/rest/team/regist"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  alert("팀이 등록 되었습니다.")
            	  location.reload();
              }else {
            	  alert(result.message);
              }
          },
   		  fail: function(result) {
   			  alert("팀 등록에 실패 했습니다.");
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});


function remove(id) {
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	
	var conf = confirm('정말 삭제 하시겠습니까?\n' + $targetInputHidden.val() + ' 팀의 데이터가 전부 삭제됩니다.');

	if(conf){

		var url = contextPath + "/rest/team/remove"
		
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
	   			  alert("팀 삭제에 실패 했습니다.");
	   		  }
	    });
	}

}

