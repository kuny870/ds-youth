// 권한 적용
function authRegist(id) {

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

    var url = contextPath + "/rest/authDetail/regist"
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'auth_id' : id,
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
 			  alert("권한 적용에 실패 했습니다.");
 		  }
     });
}

function authExecRegist() {
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

    var url = contextPath + "/rest/authExecDetail/regist"
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'auth_exec' : 1,
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
 			  alert("권한 적용에 실패 했습니다.");
 		  }
     });
}
