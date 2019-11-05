// 리더배포자료 등록
$("#registLeaderInfoForm").submit(function(e) {
	
	var dateRegex=/^[0-9]{1,2}$/;
	
	var $month = $('#month');
	var $day = $('#day');
	var $originImg = $('#originImg');

	var validateMessage = null;
	var validateFocus = null;

	// input 데이터 체크 및 팝업text 입력, 포커스 입력
	if ($month.val() == "") {
		validateMessage = '월을 입력해 주세요.';
		validateFocus = $month;
	} else if($month.val() != "" && dateRegex.test($month.val())===false) {
		validateMessage = '숫자만 입력해 주세요';
		validateFocus = $month;
	} else if ($day.val() == "") {
		validateMessage = '일을 입력해 주세요.';
		validateFocus = $day;
	} else if ($day.val() != "" && dateRegex.test($day.val())===false) {
		validateMessage = '숫자만 입력해 주세요';
		validateFocus = $day;
	} else if ($originImg.val() == "") {
		validateMessage = '이미지를 첨부해 주세요.';
		validateFocus = $originImg;
	}

	// input 데이터 체크 및 팝업창 띄워주고 포커스
	if(validateMessage != null) {
		validateFocus.focus();
		alert(validateMessage);
		return false;
	}
	
	var form = $(this);
	var form = $('#registLeaderInfoForm')[0];
    var formData = new FormData(form);
    
	var url = contextPath + "/rest/leaderInfo/regist"
	
	$.ajax({
        type: "post",
        url: url,
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        data: formData, // serializes the form’s elements.
        success: function(result)
        {
            if(result == "SUCCESS") { // show response from the php script.
          	  alert("배포자료가 등록 되었습니다.")
          	  location.reload();
            }
        },
 		  fail: function(result) {
 			  alert("배포자료 등록에 실패 했습니다.");
 		  }
	});

	e.preventDefault();
	
});


function remove(val) {
	
	var conf = confirm('정말 삭제하시겠습니까?');

	if(conf){

		var url = contextPath + "/rest/leaderInfo/remove"
		
		$.ajax({
	          type: "POST",
	          url: url,
	          data: {
	        	  id : val
	          }, // serializes the form’s elements.
	          success: function(result)
	          {
	              if(result.success) { // show response from the php script.
	            	  location.reload();
	              }else {
	            	  alert(result.message);
	              }
	          },
	   		  fail: function(result) {
	   			  alert("리더배포자료 삭제에 실패했습니다.");
	   		  }
	    });
	}

}


function readFileImage(file, callback) {
    var reader = new FileReader();
    var image  = new Image();
  
    reader.readAsDataURL(file);  
    reader.onload = function(_file) {
        image.src    = _file.target.result;
        image.onload = function() {
            var w = this.width,
                h = this.height,
                t = file.type,
                n = file.name,
                s = ~~(file.size/1024) +'KB';
            callback(w,h,t,n,s);
        };
        image.onerror= function() {
            alert('Invalid file type: '+ file.type);
        };      
    };
    
}

/*
$('input[type=file]').on('change', function () {
	
	// 사이즈체크
    var maxSize  = 10 * 1024 * 1024; //1MB
    var fileSize = 0;
    
	// 브라우저 확인
	var browser=navigator.appName; 
	
    var input = $(this);
    var ext = input.val().split('.').pop().toLowerCase();
    var file = this;
    
    // file size check
    if ( browser == "Microsoft Internet Explorer" ) {
        // 익스플로러일 경우
        var oas = new ActiveXObject("Scripting.FileSystemObject");
        fileSize = oas.getFile( file.value ).size;
    } else {
        // 익스플로러가 아닐경우
        fileSize = file.files[0].size;
    };
    
    if ( input.hasClass('upload_video') ) {
    	return;
    } else {
        if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
            alert('png,jpg,jpeg 파일만 업로드 할수 있습니다.');
            return;
        }            
    };
    
    if ( fileSize > maxSize ) {
        alert("첨부파일 사이즈는 10MB 이내로 등록 가능합니다.");
        return;
    };
    
    if ( typeof input.attr("data-size-width") != 'undefined' && typeof input.attr("data-size-height") != 'undefined' ) {
        var width = Number(input.attr("data-size-width"));
        var height = Number(input.attr("data-size-height"));  
        
        var F = this.files;
        if ( F && F[0] ) {
            for ( var i=0; i<F.length; i++ ) {
                readFileImage( F[i] , function (w,h,t,n,s) {
                    w = Number(w);
                    h = Number(h);
                    if ( width == w && height == h ) {
                        upload(input);
                    } else {
                        alert(width+'x'+height+'로 업로드 해주세요.');
                    }
                });
            };
        };
    } else {
        upload(input);            
    };
});    
*/

/*
// 게시글 등록
$("#registLeaderInfoForm").submit(function(e) {

//	var form = $(this);
	var form = $('#registLeaderInfoForm')[0];
    var formData = new FormData(form);

    var url = contextPath + "/rest/leaderInfo/regist"
    
    $.ajax({
    	xhr: function () {
            var xhr = $.ajaxSettings.xhr();
            xhr.upload.onprogress = function (e) {
                console.log(Math.floor(e.loaded / e.total * 100) + '%');
            };
            return xhr;
        },
        type: "POST",
        enctype: "multipart/form-data",
        contentType: false,
        processData: false,
        url: url,
        data: formData, // serializes the form’s elements.
//        data: form.serialize(),
        success: function(result)
        {
            if(result.success) { // show response from the php script.
          	  alert("멤버가 등록 되었습니다.")
          	  location.reload();
            }else {
          	  alert(result.message);
            }
        },
 		  fail: function(result) {
 			  alert("멤버 등록에 실패했습니다.");
 		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
	
//    $.ajax({
//    	type: 'POST',
//        url: url,
//        data: formData,
//        processData: false,
//        success: function(result) {
//        	if(result.success) { // show response from the php script.
//        		location.reload();
//        		alert(2);
//        	}else {
//        		alert(result.message);
//        		alert(3);
//        	}
//        },
//		fail: function(result) {
//			alert("리더배포자료 등록에 실패했습니다.");
//		}
//    });

});
	*/
	
	
	
	// img-before, img-after
//    var fileUpload = input.get(0);
//    var files = fileUpload.files;
//    
//    if (files.length != 0) {
//        var data = new FormData();
//        for (var i = 0; i < files.length ; i++) {
//            data.append('upload_file', files[i]);
//        }
//        $.ajax({
//            xhr: function () {
//                var xhr = $.ajaxSettings.xhr();
//                xhr.upload.onprogress = function (e) {
//                    console.log(Math.floor(e.loaded / e.total * 100) + '%');
//                };
//                return xhr;
//            },
//            contentType: false,
//            processData: false,
//            type: 'POST',
//            data: data,
//            url: '/rest/leaderInfo/',
//            success: function(result)
//            {
//                if(result.success) { // show response from the php script.
//              	  location.reload();
//                }else {
//                    alert(result.message);
//                }
//            },
//            fail: function(result) {
//                alert("리더배포자료 등록에 실패했습니다.");
//            }
//        });
//    };

    
    
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/////////////////////
//	
//	
//	var $img = $('#img');
//
//    var validateMessage = null;
//    var validateFocus = null;
//    
//    // input 데이터 체크 및 팝업text 입력, 포커스 입력
//    if ($img.val() == "") {
//         validateMessage = '파일을 선택해 주세요.';
//         validateFocus = $img;
//    }
//    
//    // input 데이터 체크 및 팝업창 띄워주고 포커스
//    if(validateMessage != null) {
//         validateFocus.focus();
//         alert(validateMessage);
//         return false;
//    }
//    
//    var form = $(this);
//    var url = contextPath + "/rest/leaderInfo"
//    
//    $.ajax({
//          type: "POST",
//          contentType: false,
//          processData: false,
//          url: url,
//          data: form.serialize(),
//          success: function(result)
//          {
//              if(result.success) { // show response from the php script.
//            	  location.reload();
//              }else {
//                  alert(result.message);
//              }
//          },
//          fail: function(result) {
//              alert("리더배포자료 등록에 실패했습니다.");
//          }
//    });
//    e.preventDefault(); // avoid to execute the actual submit of the form.
//  
//});

