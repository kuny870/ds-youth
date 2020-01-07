// 가족원 적용
function familyRegist(fId) {

	var chks = document.getElementsByName("chks");  //컨트롤 name이 chks인 컨트롤 컬렉션을 가지고 옵니다.
	var departId = document.getElementsByName("departId");
	var teamId = document.getElementsByName("teamId");
	var groupId = document.getElementsByName("groupId");
	var fmId = document.getElementsByName("fmId");
	
	var chksArr = new Array();
	var departIdArr = new Array();
	var teamIdArr = new Array();
	var groupIdArr = new Array();
	var fmIdArr = new Array();

	for (var i = 0; i < chks.length; i++) {
    	if(chks[i].checked) //checkbox가 체크 됐는지 안됐는지 체크..
        {
    		chksArr.push(chks[i].id);
    		departIdArr.push(departId[i].id);
    		teamIdArr.push(teamId[i].id);
    		groupIdArr.push(groupId[i].id);
    		fmIdArr.push(fmId[i].id);
        }
    	
    }
	

    if(chksArr == "") {
    	alert("적용할 대상을 선택해 주세요.");
    	return false;
    }

    var url = contextPath + "/rest/familyMember/regist"
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'fId' : fId,
        	'chksArr' : chksArr,
        	'departIdArr' : departIdArr,
        	'teamIdArr' : teamIdArr,
        	'groupIdArr' : groupIdArr,
        	'fmIdArr' : fmIdArr
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
 			  alert("가족원 적용에 실패 했습니다.");
 		  }
     });
}

// 가족 groupGrade 적용
function fGroupGrade(id, name, grade, retreatId) {
	
	var gradeName = null;
	
	if(grade == "0") {
		gradeName = "가족 리더";
		grade = "1";
	}else if(grade == "1"){
		gradeName = "가족장";
		grade = "9";
	}else {
		gradeName = "가족원";
		grade = "0";
	}
	
	var conf = confirm(name+ " 을(를) " + gradeName + "(으)로 적용 하시겠습니까?");
	if(conf){
		var url = contextPath + "/rest/familyMember/edit"
	    $.ajax({
	        type: "POST",
	        url: url,
	        traditional : true,
	        data: {
	        	'id' : id,
	        	'groupGrade' : grade,
	        	'retreatId' : retreatId
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
	 			  alert(gradeName + " 적용에 실패 했습니다.");
	 		  }
	     });
	}
		
}

