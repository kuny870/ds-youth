$(document).ready(function(){
	var team = document.getElementById('teamId');
	if(team.value == 4 || team.value == 8) {
		var guider = document.getElementById('guider');
		guider.style.display = 'block';
	}
});


function getTeam(sParam){
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
	                
	                var guider = document.getElementById('guider');
                	guider.style.display = 'none';
                	guider.value = '';
                	
	            }
	        }, error:function(xhr){
	            console.log(xhr.responseText);
	            alert("팀 정보를 불러오는데 실패 했습니다.");
	            return;
	        }
	    });
	    
    }
    
}


function getGuider(val){
	var guider = document.getElementById('guider');
	
	 if(val == 4 || val == 8) {
     	guider.style.display = 'block';
     }else {
     	guider.style.display = 'none';
     	guider.value = '';
     }
}


// 멤버 수정
$("#memberModifyForm").submit(function(e) {
	
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	
	var conf = confirm("수정 하시겠습니까?");
	if(conf) {
		
		var dateOfBirthRegex=/^[0-9]{6}$/;
		var htelRegex=/^[0-9]{10,11}$/;
		
		var $name = $('#name');
		var $dateOfBirth = $('#dateOfBirth');
		var $htel = $('#htel');

		/*var absentChk = $(longAbsentChk).prop("checked");
		if(absentChk){
			$('#longAbsent').val("Y");
		}else {
			$('#longAbsent').val("N");
		}*/
		
		var validateMessage = null;
		var validateFocus = null;
		
		// input 데이터 체크 및 팝업text 입력, 포커스 입력
		if ($name.val() == "") {
			validateMessage = '이름을 입력해 주세요.';
			validateFocus = $name;
		} else if ($dateOfBirth.val() != "" && dateOfBirthRegex.test($dateOfBirth.val())===false) {
			validateMessage = '생년월일을 다시 확인해 주세요.';
			validateFocus = $dateOfBirth;
		} else if ($htel.val() != "" && htelRegex.test($htel.val())===false) {
			validateMessage = '휴대폰 번호를 다시 확인해 주세요.';
			validateFocus = $htel;
		}

		// input 데이터 체크 및 팝업창 띄워주고 포커스
		if(validateMessage != null) {
			validateFocus.focus();
			alert(validateMessage);
			return false;
		}
		
		var form = $(this);
		var url = contextPath + "/rest/member/edit"
	   
		$.ajax({
	          type: "POST",
	          url: url,
	          data: form.serialize(), // serializes the form’s elements.
	          success: function(result)
	          {
	              if(result.success) { // show response from the php script.
	            	  alert($name.val() + " 정보가 수정 되었습니다.")
	            	  location.href = contextPath + "/member/list?teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + sNameKW + "&pageNo=" + pageNo;
	              }else {
	            	  alert(result.message);
	              }
	          },
	   		  fail: function(result) {
	   			  alert($name.val() + " 정보 수정에 실패 했습니다.");
	   		  }
	    });

		e.preventDefault(); // avoid to execute the actual submit of the form.
		
	}
   
});


function memberRemove(id) {
	
	var sTeamId = $('#sTeamId').val();
	var sGroupId = $('#sGroupId').val();
	var sNameKW = $('#sNameKW').val();
	var pageNo = $('#pageNo').val();
	var $name = $('#name');
	
	var conf = confirm("정말 삭제 하시겠습니까?");
	if(conf) {
	
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
	            	  alert($name.val() + " 정보가 삭제 되었습니다.");
	            	  location.href = contextPath + "/member/list?teamId=" + sTeamId + "&groupId=" + sGroupId + "&nameKW=" + sNameKW + "&pageNo=" + pageNo;
	              }else {
	            	  alert(result.message);
	              }
	          },
	   		  fail: function(result) {
	   			  alert($name.val() + " 정보 삭제에 실패 했습니다.");
	   		  }
	    });
	
		e.preventDefault(); // avoid to execute the actual submit of the form.
	}
   
}