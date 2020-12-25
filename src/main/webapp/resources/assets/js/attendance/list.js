function getSeason(sParam){
    var $target = $("select[name='season']");
     
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
	                    $target.append("<option value=" + result.data[i].season + ">"+ result.data[i].season +"</option>");
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

// 출석 적용
function attendanceRegist() {
	var year = $('#year').val();
	var aId = document.getElementsByName("aId");
	var firstWeek = document.getElementsByName("firstWeek");
	var secondWeek = document.getElementsByName("secondWeek");
	var thirdWeek = document.getElementsByName("thirdWeek");
	var fourthWeek = document.getElementsByName("fourthWeek");
	var fifthWeek = document.getElementsByName("fifthWeek");
	var aIdArray = new Array();
	var firstArray = new Array();
	var secondArray = new Array();
	var thirdArray = new Array();
	var fourthArray = new Array();
	var fifthArray = new Array();
	
	// 이름
	for (var i = 0; i < aId.length; i++) {
		aIdArray.push(aId[i].id);
	}
	// 첫째주
    for (var i = 0; i < firstWeek.length; i++) {
    	if(firstWeek[i].checked) {
    		firstArray.push("Y");
        }else{
        	firstArray.push("N");
        }
    }
    // 둘째주
    for (var i = 0; i < secondWeek.length; i++) {
    	if(secondWeek[i].checked) {
    		secondArray.push("Y");
        }else{
        	secondArray.push("N");
        }
    }
    // 셋째주
    for (var i = 0; i < thirdWeek.length; i++) {
    	if(thirdWeek[i].checked) {
    		thirdArray.push("Y");
        }else{
        	thirdArray.push("N");
        }
    }
    // 넷째주
    for (var i = 0; i < fourthWeek.length; i++) {
    	if(fourthWeek[i].checked) {
    		fourthArray.push("Y");
        }else{
        	fourthArray.push("N");
        }
    }
    // 다섯째주
    for (var i = 0; i < fifthWeek.length; i++) {
    	if(fifthWeek[i].checked) {
    		fifthArray.push("Y");
        }else{
        	fifthArray.push("N");
        }
    }


    var url = contextPath + "/rest/attDetail/regist"
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'year' : year,
        	'aId' : aIdArray,
        	'firstWeek' : firstArray,
        	'secondWeek' : secondArray,
        	'thirdWeek' : thirdArray,
        	'fourthWeek' : fourthArray,
        	'fifthWeek' : fifthArray,
        }, // serializes the form’s elements.
        success: function(result)
        {
            if(result.success) { // show response from the php script.
            	Swal.fire({
                    text: '적용 되었습니다',
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
                 text: '출석 적용에 실패 했습니다',
                 confirmButtonText: '확인',
                 allowOutsideClick: true
             });
 		  }
     });
}


