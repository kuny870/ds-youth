$(document).ready(function(){
	var team = document.getElementById('teamId');
	if(team.value == 4 || team.value == 8) {
		var newMember = document.getElementById('new-member');
		newMember.style.display = 'block';
	}
	
});


function getTeam(sParam){
	var newMember = document.getElementById('new-member');
	newMember.style.display = 'none';
	
    var $target = $("select[name='teamId']");
    $target.empty();
    if(sParam == ""){
    	$target.append("<option value=''>팀선택</option>");
        return;
    } else if (sParam != "") {
    	
	    $.ajax({
	        type: "POST",
	        url: contextPath + "/rest/select/depart",
	        async: false,
	        data:{ departId : sParam },
	        dataType: "json",
	        success: function(result) {
	            if(result.data.length == 0){
	                $target.append("<option value=''>팀선택</option>");
	            }else{
	                $(result.data).each(function(i){
	                    $target.append("<option value=" + result.data[i].id + ">"+ result.data[i].tShortName +"</option>");
	                });
	                
	                // 새가족 전용 데이터 초기화
                	var guider = document.getElementById('guider');
                	guider.value = '';
                	var memberRegDate = document.getElementById('memberRegDate');
                	memberRegDate.value = '';
                	var memberGradDate = document.getElementById('memberGradDate');
                	memberGradDate.value = '';
                	
	            }
	        }, error:function(xhr){
	            console.log(xhr.responseText);
	            Swal.fire({
	                text: "팀 정보를 불러오는데 실패 했습니다",
	                confirmButtonText: '확인',
	                allowOutsideClick: true
	            });
	            return;
	        }
	    });
	    
    }
    
}


function getNewMemberDetail(val){
	var newMember = document.getElementById('new-member');
	var guider = document.getElementById('guider');
	var memberRegDate = document.getElementById('memberRegDate');
	var memberGradDate = document.getElementById('memberGradDate');
	
	 if(val == 4 || val == 8) {
		newMember.style.display = 'block';
     }else {
    	newMember.style.display = 'none';
    	
    	// 새가족 전용 데이터 초기화
     	guider.value = '';
     	memberRegDate.value = '';
     	memberGradDate.value = '';
     }
}

//멤버 수정
function memberModify() {
	
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	
	var nameKW = encodeURI(encodeURI(sNameKW));
	
	var dateOfBirthRegex=/^[0-9]{6}$/;
	var htelRegex=/^[0-9]{10,11}$/;
	
	var $name = $('#name');
	var $dateOfBirth = $('#dateOfBirth');
	var $htel = $('#htel');

	var validateMessage = null;
	var validateFocus = null;
	
	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($name.val() == "") {
		validateMessage = '이름을 입력해 주세요';
		validateFocus = $name;
	}/* else if ($dateOfBirth.val() != "" && dateOfBirthRegex.test($dateOfBirth.val())===false) {
		validateMessage = '생년월일을 다시 확인해 주세요';
		validateFocus = $dateOfBirth;
	} */else if ($htel.val() != "" && htelRegex.test($htel.val())===false) {
		validateMessage = '휴대폰 번호를 다시 확인해 주세요';
		validateFocus = $htel;
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
		
	Swal.fire({
	    title: '팀원 정보 수정',
	    html: "수정 하시겠습니까?",
	    showCancelButton: true,
	    cancelButtonText: '취소',
	    confirmButtonText: '확인',
	    allowOutsideClick: true,
	    reverseButtons: true
	}).then(function (result) {
		
		if(result.value){
			
			var url = contextPath + "/rest/member/edit"

			$.ajax({
		          type: "POST",
		          url: url,
		          data: {
		        	  sTeamId : sTeamId
		        	  ,sGroupId : sGroupId
		        	  ,sNameKW :  sNameKW
		        	  ,pageNo : pageNo
		              ,id : $('#id').val()
		              ,memState : $('#memState').val()
		              ,name : $('#name').val()
		              ,samePeriodId : $('#samePeriodId').val()
		              /*,dateOfBirth : $('#dateOfBirth').val()*/
		              ,htel : $('#htel').val()
		              ,departId : $('.departId').val()
		              ,teamId : $('.teamId').val()
		              ,guider : $('#guider').val()
		              ,memberRegDate : $('#memberRegDate').val()
		              ,memberGradDate : $('#memberGradDate').val()
		              ,gender : $('input[name="gender"]:checked').val()
		          },
		          success: function(result)
		          {
		              if(result.success) { // show response from the php script.
		            	  Swal.fire({
		                      text: $name.val() + " 정보가 수정 되었습니다",
		                      confirmButtonText: '확인',
		                      allowOutsideClick: true
		                  }).then(function() {
		                	  location.href = contextPath + "/member/list?teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + nameKW + "&pageNo=" + pageNo;
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
		   			    text: $name.val() + " 정보 수정에 실패 했습니다",
		   			    confirmButtonText: '확인',
		   			    allowOutsideClick: true
		   			});
		   		  }
		    });
			
			e.preventDefault(); // avoid to execute the actual submit of the form.
			
		}
	});
	
}


function memberRemove(id) {
	
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	var $name = $('#name');
	
	Swal.fire({
        title: '팀원 정보 삭제',
        html: "정말 삭제 하시겠습니까?",
        showCancelButton: true,
        cancelButtonText: '취소',
        confirmButtonText: '확인',
        allowOutsideClick: true,
        reverseButtons: true
    }).then(function (result) {
    	
    	if(result.value){
    		
    		var url = contextPath + "/rest/member/remove"
 		   
    		$.ajax({
    	          type: "POST",
    	          url: url,
    	          data: {
    	        	  'id' : id
    	          },
    	          success: function(result)
    	          {
    	              if(result.success) { // show response from the php script.
    	            	  Swal.fire({
    	                      text: $name.val() + " 정보가 삭제 되었습니다",
    	                      confirmButtonText: '확인',
    	                      allowOutsideClick: true
    	                  }).then(function() {
    	                	  location.href = contextPath + "/member/list?teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + sNameKW + "&pageNo=" + pageNo;
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
    	   			    text: $name.val() + " 정보 삭제에 실패 했습니다",
    	   			    confirmButtonText: '확인',
    	   			    allowOutsideClick: true
    	   			});
    	   		  }
    	    });
    	
    		e.preventDefault(); // avoid to execute the actual submit of the form.
    		
    	}
    	
    });
	
}