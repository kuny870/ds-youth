// 순공부 적용
function groupRegist(gId) {

	var chks = document.getElementsByName("chks");  //컨트롤 name이 chks인 컨트롤 컬렉션을 가지고 옵니다.
	var arr = new Array();
	
    for (var i = 0; i < chks.length; i++) {
    	if(chks[i].checked) //checkbox가 체크 됐는지 안됐는지 체크..
        {
            arr.push(chks[i].id);
        }
    }

    if(arr == "") {
    	alert("적용할 대상을 선택해 주세요.");
    	return false;
    }

    var url = contextPath + "/rest/groupDetail/regist"
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'gId' : gId,
        	'arr' : arr
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

// 순장 적용
function groupLeader(id, name, grade, gName, mGName, gId) {
	if(grade != "순장" || gName != mGName){
		var conf = confirm(name+ " 을(를) 순장으로 적용 하시겠습니까?");
		if(conf){
			var url = contextPath + "/rest/member/edit"
		    $.ajax({
		        type: "POST",
		        url: url,
		        traditional : true,
		        data: {
		        	'id' : id,
		        	'groupId' : gId,
		        	'groupGrade' : '순장'
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
		 			  alert("순장 적용에 실패 했습니다.");
		 		  }
		     });
		}
		
	}else {
		var conf = confirm(name+ " 을(를) 순원으로 적용 하시겠습니까?");
		if(conf){
			var url = contextPath + "/rest/member/edit"
		    $.ajax({
		        type: "POST",
		        url: url,
		        traditional : true,
		        data: {
		        	'id' : id,
		        	'groupName' : gId,
		        	'groupGrade' : '순원'
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
		 			  alert("순원 적용에 실패 했습니다.");
		 		  }
		     });
		}
	}
}

