function enterKeyEvent(groupId) {
	modify(groupId);
}

function getSeason(sParam){
    var $target = $("select[name='seasonFlag']");
     
    $target.empty();
    if(sParam == ""){
    	$target.append("<option value=''>-</option>");
        return;
    } else if (sParam != "") {
    	
	    $.ajax({
	        type: "POST",
	        url: contextPath + "/rest/select/season",
	        async: false,
	        data:{ year : sParam },
	        dataType: "json",
	        success: function(result) {
	            if(result.data.length == 0){
	                $target.append("<option value=''>-</option>");
	            }else{
	                $(result.data).each(function(i){
	                    $target.append("<option value=" + result.data[i].seasonFlag + ">"+ result.data[i].season +"</option>");
	                });
	            }
	        }, error:function(xhr){
	            console.log(xhr.responseText);
	            alert("시즌 정보를 불러오는데 실패 했습니다.");
	            return;
	        }
	    });
	    
    }
    
}


//순명 등록
$("#registGroupForm").submit(function(e) {

	if(doubleSubmitCheck()) return;
	
	var $team = $('#teamId');
	var $gName = $('#gName');
	var $gOrd = $('#ord');
	
	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($team.val() == "팀선택") {
		validateMessage = '팀을 선택해 주세요';
		validateFocus = $team;
	} else if ($gOrd.val() == "") {
		validateMessage = '순서를 입력해 주세요';
		validateFocus = $gOrd;
	} else if ($gName.val() == "") {
		validateMessage = '순명을 입력해 주세요';
		validateFocus = $gName;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		Swal.fire({
            text: validateMessage,
            confirmButtonText: '확인',
            allowOutsideClick: true
        });
		doubleSubmitFlag = false;
		return false;
	} 
	
	var form = $(this);
	var url = contextPath + "/rest/group/regist";
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  Swal.fire({
            		  text: "순명이 등록 되었습니다",
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
	       		  text: "순명 등록에 실패 했습니다",
	       		  confirmButtonText: '확인',
	       		  allowOutsideClick: true
	       	  });
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

function modify(id) {
	
	var gNameATag = document.getElementById(id + '-a');
	var gNameInput = document.getElementById(id + '-gName-input');
	
	var gOrdDiv = document.getElementById(id + '-ord-div');
	var gOrdInput = document.getElementById(id + '-ord-input');
	
	var $targetModifyBtn = $("button[id="+ id + "-modify-btn]");
	var $targetRemoveBtn = $("button[id="+ id + "-remove-btn]");
	
	var year = $('#year').val();
	var seasonFlag = $('#seasonFlag').val();
	
	// aTag 와 input 태그 크로스
	if(gNameATag.style.display != 'none'){
		gNameATag.style.display = 'none';
    	gNameInput.style.display = 'block';
    	
    	gOrdDiv.style.display = 'none';
    	gOrdInput.style.display = 'block';
    }
	
	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if (gOrdInput.value == "") {
		validateMessage = '순서를 입력해 주세요';
		validateFocus = gOrdInput;
	} else if (gNameInput.value == "") {
		validateMessage = '순명을 입력해 주세요';
		validateFocus = gNameInput;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		Swal.fire({
            text: validateMessage,
            confirmButtonText: '확인',
            allowOutsideClick: true
        });
		return false;
	} 
	
	// 수정버튼과 취소버튼 크로스
    if($targetModifyBtn.html() === '수정'){
    	$targetModifyBtn.html('확인');
    	$targetRemoveBtn.html('취소');
    	$('#' + id + '-gName-input').focus();
    }else {
    	
    	// 순명 수정 프로세스 TO-BE
    	var url = contextPath + "/rest/group/modify";
		
		$.ajax({
	          type: "POST",
	          url: url,
	          data: {
	        	  id : id
	        	  , gName : gNameInput.value
	        	  , ord : gOrdInput.value
	        	  , year : year
	        	  , seasonFlag : seasonFlag
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
                    text: "순명 수정에 실패했습니다",
                    confirmButtonText: '확인',
                    allowOutsideClick: true
                });
	   		  }
	    });
		
		
    	
    	// 순명 수정 프로세스 AS-IS
//    	Swal.fire({
//            title: '순명 수정',
//            html: '정말 수정 하시겠습니까?',
//            showCancelButton: true,
//            cancelButtonText: '취소',
//            confirmButtonText: '확인',
//            allowOutsideClick: true,
//            reverseButtons: true
//        }).then(function (result) {
//        		
//        	if(result.value){
//        		
//        		var url = contextPath + "/rest/group/modify";
//        		
//        		$.ajax({
//        	          type: "POST",
//        	          url: url,
//        	          data: {
//        	        	  id : id
//        	        	  , gName : gNameInput.value
//        	        	  , ord : gOrdInput.value
//        	        	  , year : year
//        	        	  , seasonFlag : seasonFlag
//        	          }, // serializes the form’s elements.
//        	          success: function(result)
//        	          {
//        	              if(result.success) { // show response from the php script.
//        	            	  location.reload();
//        	              }else {
//        	            	  Swal.fire({
//        		                    text: result.message,
//        		                    confirmButtonText: '확인',
//        		                    allowOutsideClick: true
//        		                });
//
//        	              }
//        	          },
//        	   		  fail: function(result) {
//        	   			Swal.fire({
//    	                    text: "순명 수정에 실패했습니다",
//    	                    confirmButtonText: '확인',
//    	                    allowOutsideClick: true
//    	                });
//        	   		  }
//        	    });
//        		
//        	}
//        	
//        });

    }
    
    
}

function remove(id) {

	var gNameATag = document.getElementById(id + '-a');
	var gNameInput = document.getElementById(id + '-gName-input');
	
	var gOrdDiv = document.getElementById(id + '-ord-div');
	var gOrdInput = document.getElementById(id + '-ord-input');
	
	var $targetInputHidden = $("input[id="+ id + "-input-hidden]");
	var $targetModifyBtn = $("button[id="+ id + "-modify-btn]");
	var $targetRemoveBtn = $("button[id="+ id + "-remove-btn]");
	
	// 취소버튼과 수정버튼 크로스
	if($targetRemoveBtn.html() === '취소'){
		gNameATag.style.display = 'block';
		gNameInput.style.display = 'none';
		
		gOrdDiv.style.display = 'block';
		gOrdInput.style.display = 'none';
		
		gNameInput.value = $targetInputHidden.val();
		$targetModifyBtn.html('수정');
		$targetRemoveBtn.html('삭제');
    }else {
    	
    	// 순명 삭제 프로세스
    	Swal.fire({
            title: '순명 삭제',
            html: '정말 삭제 하시겠습니까?<br>' + $targetInputHidden.val() + ' 그룹의 데이터가 초기화 됩니다.',
            showCancelButton: true,
            cancelButtonText: '취소',
            confirmButtonText: '확인',
            allowOutsideClick: true,
            reverseButtons: true
        }).then(function (result) {
        	
        	if(result.value){
        		
        		var url = contextPath + "/rest/group/remove";
        		
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
    	                    text: "순명 삭제에 실패했습니다",
    	                    confirmButtonText: '확인',
    	                    allowOutsideClick: true
    	                });
        	   		  }
        	    });
        		
        	}
        	
        });

    }

}


