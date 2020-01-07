// 순공부 적용
function attGroup() {

	var gId = $('#gId').val();
	var year = $('#year').val();
	var season = $('#season').val();
	
	var chks = document.getElementsByName("chks");  //컨트롤 name이 chks인 컨트롤 컬렉션을 가지고 옵니다.
	var memberArr = new Array();
	
    for (var i = 0; i < chks.length; i++) {
    	if(chks[i].checked) //checkbox가 체크 됐는지 안됐는지 체크..
        {
    		memberArr.push(chks[i].id);
        }
    }

    if(memberArr == "") {
    	alert("적용할 대상을 선택해 주세요.");
    	return false;
    }

    var url = contextPath + "/rest/attGroup/edit"
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'gId' : gId,
        	'memberArr' : memberArr,
        	'year' : year,
        	'season' : season
        }, // serializes the form’s elements.
        success: function(result)
        {
            if(result.success) { // show response from the php script.
            	alert("적용 되었습니다.")
          	  	location.reload();
            }else {
          	  	alert(result.message);
            }
        },
 		  fail: function(result) {
 			  alert("순명 적용에 실패 했습니다.");
 		  }
     });
}

// 출석부에 groupGrade (순장/순원) 적용
function attGroupGrade(mId, name, grade) {

	var year = $('#year').val();
	var season = $('#season').val();
	
	if(grade == "순원") {
		grade = "순장";
	}else {
		grade = "순원"
	}
	
	var conf = confirm(name+ " 을(를) " + grade + "으로 적용 하시겠습니까?");
	if(conf){
		var url = contextPath + "/rest/attGroupGrade/edit"
	    $.ajax({
	        type: "POST",
	        url: url,
	        traditional : true,
	        data: {
	        	'memberId' : mId,
	        	'groupGrade' : grade,
	        	'year' : year,
	        	'season' : season
	        }, // serializes the form’s elements.
	        success: function(result)
	        {
	            if(result.success) { // show response from the php script.
	            	alert("적용 되었습니다.")
	          	  	location.reload();
	            }else {
	          	  	alert(result.message);
	            }
	        },
	 		  fail: function(result) {
	 			  alert(groupName + " 적용에 실패 했습니다.");
	 		  }
	     });
	}
		
}

