//순명 등록
$("#registGroupForm").submit(function(e) {

	var $team = $('#teamId');
	var $gName = $('#gName');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($team.val() == "팀선택") {
		validateMessage = '팀을 선택해 주세요.';
		validateFocus = $team;
	} else if ($gName.val() == "") {
		validateMessage = '순명을 입력해 주세요.';
		validateFocus = $gName;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		alert(validateMessage);
		return false;
	} 
	
	var form = $(this);
	var url = contextPath + "/rest/group/regist"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  alert("순명이 등록 되었습니다.")
            	  location.reload();
              }else {
            	  alert(result.message);
              }
          },
   		  fail: function(result) {
   			  alert("순명 등록에 실패 했습니다.");
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

function modify(id) {
	
	var gNameATag = document.getElementById(id + '-a');
	var gNameInput = document.getElementById(id + '-input');
	var $targetModifyBtn = $("button[id="+ id + "-modify-btn]");
	var $targetRemoveBtn = $("button[id="+ id + "-remove-btn]");
	
	var year = $('#year').val();
	var season = $('#season').val();
	
	// aTag 와 input 태그 크로스
	if(gNameATag.style.display != 'none'){
		gNameATag.style.display = 'none';
    	gNameInput.style.display = 'block';
    }
	
	// 수정버튼과 취소버튼 크로스
    if($targetModifyBtn.html() === '수정'){
    	$targetModifyBtn.html('확인');
    	$targetRemoveBtn.html('취소');
    }else {
    	
    	// 순명 수정 프로세스
    	var conf = confirm('정말 수정 하시겠습니까?');

    	if(conf){
    		
    		var url = contextPath + "/rest/group/modify"
    		
    		$.ajax({
    	          type: "POST",
    	          url: url,
    	          data: {
    	        	  id : id,
    	        	  gName : gNameInput.value,
    	        	  year : year,
    	        	  season : season
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
    	   			  alert("순명 수정에 실패했습니다.");
    	   		  }
    	    });
    	}
    	
    }
    
    
}

function remove(id) {
	
	var gNameATag = document.getElementById(id + '-a');
	var gNameInput = document.getElementById(id + '-input');
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	var $targetModifyBtn = $("button[id="+ id + "-modify-btn]");
	var $targetRemoveBtn = $("button[id="+ id + "-remove-btn]");
	
	// 취소버튼과 수정버튼 크로스
	if($targetRemoveBtn.html() === '취소'){
		gNameATag.style.display = 'block';
		gNameInput.style.display = 'none';
		gNameInput.value = $targetInputHidden.val();
		$targetModifyBtn.html('수정');
		$targetRemoveBtn.html('삭제');
    }else {
    	
    	// 순명 삭제 프로세스
    	var conf = confirm('정말 삭제 하시겠습니까?\n' + $targetInputHidden.val() + ' 그룹의 데이터가 초기화 됩니다.');

    	if(conf){

    		var url = contextPath + "/rest/group/remove"
    		
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
    	   			  alert("순명 삭제에 실패했습니다.");
    	   		  }
    	    });
    	}
    	
    }

}


