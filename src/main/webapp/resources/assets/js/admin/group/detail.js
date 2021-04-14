// 순공부 적용
function attGroup() {

	if(doubleSubmitCheck()) return;
	
	var gId = $('#gId').val();
	var year = $('#year').val();
	var seasonFlag = $('#seasonFlag').val();
	
	var chks = document.getElementsByName("chks");  //컨트롤 name이 chks인 컨트롤 컬렉션을 가지고 옵니다.
	var memberArr = new Array();
	
    for (var i = 0; i < chks.length; i++) {
    	if(chks[i].checked) //checkbox가 체크 됐는지 안됐는지 체크..
        {
    		memberArr.push(chks[i].id);
        }
    }

    if(memberArr == "") {
    	Swal.fire({
            text: "적용할 대상을 선택해 주세요",
            confirmButtonText: '확인',
            allowOutsideClick: true
        });
    	doubleSubmitFlag = false;
    	return false;
    }

    var url = contextPath + "/rest/attGroup/edit";
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'gId' : gId,
        	'memberArr' : memberArr,
        	'year' : year,
        	'seasonFlag' : seasonFlag
        }, // serializes the form’s elements.
        success: function(result)
        {
            if(result.success) { // show response from the php script.
            	Swal.fire({
                    text: "적용 되었습니다",
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
 	            text: "순명 적용에 실패 했습니다",
 	            confirmButtonText: '확인',
 	            allowOutsideClick: true
 	        });
 		  }
     });
}

// 출석부에 groupGrade (순장/순원) 적용
function attGroupGrade(mId, name, grade) {

	var year = $('#year').val();
	var seasonFlag = $('#seasonFlag').val();
	
	if(grade == "순원") {
		grade = "순장";
	}else {
		grade = "순원"
	}
	
	Swal.fire({
        title: grade + ' 적용',
        html: name+ " 을(를) " + grade + "으로 적용 하시겠습니까?",
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/attGroupGrade/edit";
        	
    	    $.ajax({
    	        type: "POST",
    	        url: url,
    	        traditional : true,
    	        data: {
    	        	'memberId' : mId,
    	        	'groupGrade' : grade,
    	        	'year' : year,
    	        	'seasonFlag' : seasonFlag
    	        }, // serializes the form’s elements.
    	        success: function(result)
    	        {
    	            if(result.success) { // show response from the php script.
    	            	Swal.fire({
    	                    text: "적용 되었습니다",
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
    	 	            text: groupName + " 적용에 실패 했습니다",
    	 	            confirmButtonText: '확인',
    	 	            allowOutsideClick: true
    	 	        });
    	 		  }
    	     });
    		
    	}
    	
    });
		
}

