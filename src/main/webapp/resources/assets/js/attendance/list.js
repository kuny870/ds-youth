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
            	alert("적용 되었습니다.")
          	  	location.reload();
            }else {
          	  	alert(result.message);
            }
        },
 		  fail: function(result) {
 			  alert("출석 적용에 실패 했습니다.");
 		  }
     });
}


// 출석 조회
function attSearch() {
	
	var team = $('#team').val();
	var year = $('#year').val();
	var month = $('#month').val();
	
	location.href = contextPath + "/attendance/list?team=" + team + "&year=" + year + "&month=" + month;
	
}