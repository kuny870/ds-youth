function enterKeyEvent() {
	attendanceSearch();
}

let sortableArr = [];

$(function() {
	
	if(isMobile()){
		//모바일페이지
		console.log("모바일로 접속하였습니다.");
	}else{
		//PC용 페이지
		console.log("PC로 접속하였습니다.");
		
		var startPoint;
		var stopPoint;
		var max;
		var min;
		var mainId;
		var subId;
		
	    for(var i=1; i<=$("#sortableIndex").val(); i++){
	    	
	        $( ".sortable" + i ).sortable( {
	        	
	            cancel: ".not-sortable",
	            cursor: "move",
	            item: $('table tbody tr'),
	            
	            start: function(event, ui) {
//	                console.log('start point : ' + ui.item.position().top);
	                startPoint = ui.item.position().top;
	                mainId = ui.item.find('td:first').data('id');
	            },	// start
	            
	            update: function( event, ui ) {
		              $(this).find('tr').each(function(idx) {
		              	var tdVal = $(this).find('td').last().html();
		              	if(tdVal.includes('수정')) {
		              		$(this).find('td').last().html('수정<input type="hidden" name="sortableYn" value="Y"><input type="hidden" id="sortAttOrd" name="sortAttOrd" value="' + (idx+1) + '">');
		              	}else {
		              		$(this).find('td').last().html('<input type="hidden" id="sortAttOrd" name="sortAttOrd" value="' + (idx+1) + '">');
		              	}
		              });
	        	},	// update
	        	
		        stop: function(event, ui) {
//		        	console.log('stop point : ' + ui.item.position().top);
		        	stopPoint = ui.item.position().top;
		            
		            max = (startPoint > stopPoint) ? startPoint : stopPoint;
		            min = (startPoint < stopPoint) ? startPoint : stopPoint;
		            
		            if(max - min > 10) {
		            	
		            	var tmp = ui.item.children('td:last').html();
		            	if(tmp.includes('수정')) {
		            		ui.item.children('td:last').html(tmp);
		            	}else {
		            		ui.item.children('td:last').html('수정<input type="hidden" name="sortableYn" value="Y">' + tmp);
		            	}
			            
			            sortableArr.push(mainId);
			            
		            }
		            
		        }	// stop
		        
	        });
	    }
	    
	}   // PC용 페이지 end

});



// 사유 불러오기
function getSayu(val){
	var sayu;
	var id = $("#attId").val();
	var year = $("#thisYear").val();
	
	$.ajax({
        type: "POST",
        url: contextPath + "/rest/att/getOneAtt",
        data:{ 
        	id : id
        	, year : year
        },
        success: function(result)
        {
            if(result.success) {
            	if(val == "1"){
            		sayu = result.data.sayu1;
            	}else if(val == "2"){
            		sayu = result.data.sayu2;
            	}else if(val == "3"){
            		sayu = result.data.sayu3;
            	}else if(val == "4"){
            		sayu = result.data.sayu4;
            	}else {
            		sayu = result.data.sayu5;
            	}
            	
            	$('#sayu').val(sayu);
            	
            }
        },
 		  fail: function(result) {
 			Swal.fire({
 			    text: "불러오는데 실패했습니다.",
 			    confirmButtonText: '확인',
 			    allowOutsideClick: true
 			});
 		  }
    });
	
}


// 사유 삭제
function sayuRemove() {
	
	if(doubleSubmitCheck()) return;
	
	var id = $('#attId2').val();
	var year = $('#thisYear').val();
	var ord = $('#ord').val();
	var removeFlag;
	
	if(ord == "1") {
		removeFlag = "sayu1"
	}else if(ord == "2") {
		removeFlag = "sayu2"
	}else if(ord == "3") {
		removeFlag = "sayu3"
	}else if(ord == "4") {
		removeFlag = "sayu4"
	}else {
		removeFlag = "sayu5"
	}
	
	$.ajax({
        type: "POST",
        url: contextPath + "/rest/remove/sayu",
        data:{ 
        	id : id
        	, year : year
        	, removeFlag : removeFlag
        },
        success: function(result)
        {
            if(result.success) {
            	
                $("#sayuMask, .sayuWindow").hide();
                
          	  Swal.fire({
                    text: "사유가 삭제되었습니다.",
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
 			    text: "사유 삭제에 실패했습니다.",
 			    confirmButtonText: '확인',
 			    allowOutsideClick: true
 			});
 		  }
    });
		
}

// 멤버 메모 등록
function memberMemoAndSayuRegist() {
	
	if(doubleSubmitCheck()) return;
	
	// 지체상황 data
	var memberId = $("#memoPopId").val();
	var memo = $("#memo").val();
	memo = memo.replace(/(?:\r\n|\r|\n)/g, '<br/>');
	memo = memo.split("'").join('#001');
	memo = memo.split('"').join('#002');
	var name = $("#memoPopName").val();
	
	// 예배 불참 사유 data
	var attId = $("#attId").val();
	var sundays = $("#sundays").val();
	var sayu = $("#sayu").val();
	sayu = sayu.split('"').join('');
	sayu = sayu.split("'").join('');
	sayu = sayu.trim();
	var thisYear = $("#thisYear").val();
	
	$.ajax({
        type: "POST",
        url: contextPath + "/rest/member/memo",
        data:{ 
        	id : memberId
        	, memo : memo
        	, sundays : sundays
        	, sayu : sayu
        	, attId : attId
        	, thisYear : thisYear
        },
        success: function(result)
        {
            if(result.success) {
            	
                $("#memberMemoMask, .memberMemoWindow").hide();
                
          	  Swal.fire({
                    text: name + "의 지체상황이 등록되었습니다.",
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
 			    text: "등록에 실패했습니다.",
 			    confirmButtonText: '확인',
 			    allowOutsideClick: true
 			});
 		  }
    });
	
}

//멤버 메모 팝업 호출
function memberMemoPop(gubun, attId, mId, mName, mMemo) {
	if(gubun == "open" ) {
		memberMemoPopup(attId, mId, mName, mMemo);
	}
}

// 출석부 오른쪽 화살표 클릭 이벤트
function secondOpen(){
	var val = document.querySelector('#updown-img').dataset['value'];
	if(val == 'down'){
		document.getElementById('updown-img').setAttribute('data-value','up');
		document.getElementById('updown-img').setAttribute('src', resourcesPath + '/assets/images/ico_accordion_up.png');
		
		$(".secondYN").show(400);
		
	}else {
		document.getElementById('updown-img').setAttribute('data-value','down');
		document.getElementById('updown-img').setAttribute('src', resourcesPath + '/assets/images/ico_accordion_down.png');
		
		$(".secondYN").hide(300);
	}
}

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
	
	if(doubleSubmitCheck()) return;

//	console.log("sortables: " + sortableArr);
	
	if(sortableArr.length > 0) {
		var attYMemberId = document.getElementsByName("attYMemberId");
		for(var i=0; i<attYMemberId.length; i++){
			var targetMemberId = $('.' + attYMemberId[i].value).html();
//			var mainValue = mainTmp.replace('<input type="hidden" id="attYn" name="attYn" value="Y">', '<input type="hidden" id="attYn" name="attYn" value="N">');
			$('td[name=' + attYMemberId[i].value + ']').html(targetMemberId);
		}
	}
	
	var year = $('#year').val();
	var month = $('#month').val();
	var memberId = document.getElementsByName("memberId");
	var attYn = document.getElementsByName("attYn");
	var aId = document.getElementsByName("aId");
	var firstWeek = document.getElementsByName("firstWeek");
	var secondWeek = document.getElementsByName("secondWeek");
	var thirdWeek = document.getElementsByName("thirdWeek");
	var fourthWeek = document.getElementsByName("fourthWeek");
	var fifthWeek = document.getElementsByName("fifthWeek");
	var sortAttOrd = document.getElementsByName("sortAttOrd");
	var aIdArray = new Array();
	var memberIdArray = new Array();
	var attYnArray = new Array();
	var firstArray = new Array();
	var secondArray = new Array();
	var thirdArray = new Array();
	var fourthArray = new Array();
	var fifthArray = new Array();
	
	var sortAttOrdArray = new Array();
	
	// 이름
	for (var i = 0; i < aId.length; i++) {
		aIdArray.push(aId[i].id);
	}
	
	// memberId
	for (var i = 0; i < memberId.length; i++) {
		memberIdArray.push(memberId[i].value);
	}
	
	// attYn
	for (var i = 0; i < attYn.length; i++) {
		attYnArray.push(attYn[i].value);
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
    
    // 출석부 순서
	for (var i = 0; i < sortAttOrd.length; i++) {
		sortAttOrdArray.push(sortAttOrd[i].value);
	}
	
	// 출석부 순서 변경 여부
    var sortYN = "N";
    var sortableYn = document.getElementsByName("sortableYn");
    if(sortableYn.length > 0) {
    	sortYN = "Y";
    }
    
    var url = contextPath + "/rest/attDetail/regist";
    
    $.ajax({
        type: "POST",
        url: url,
        traditional : true,
        data: {
        	'year' : year
        	, 'month' : month
        	, 'aId' : aIdArray
        	, 'memberId' : memberIdArray
        	, 'attYn' : attYnArray
        	, 'firstWeek' : firstArray
        	, 'secondWeek' : secondArray
        	, 'thirdWeek' : thirdArray
        	, 'fourthWeek' : fourthArray
        	, 'fifthWeek' : fifthArray
        	, 'sortAttOrd' : sortAttOrdArray
        	, 'sortYN' : sortYN
        }, // serializes the form’s elements.
        success: function(result)
        {
            if(result.success) { // show response from the php script.
            	
            	if(sortYN == 'Y') {
            		Swal.fire({
                        text: '출석부 순서가 변경 되었습니다',
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    }).then(function() {
                    	location.reload();
                    });
            	}else {
            		Swal.fire({
                        text: '출석 적용 되었습니다',
                        confirmButtonText: '확인',
                        allowOutsideClick: true
                    }).then(function() {
                    	location.reload();
                    });
            	}
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


