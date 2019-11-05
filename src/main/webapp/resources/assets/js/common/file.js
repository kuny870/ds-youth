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

function formFileUpload() {
    // 사이즈체크
    var maxSize  = 1 * 1024 * 1024; //1MB
    var fileSize = 0;
    
	// 브라우저 확인
	var browser=navigator.appName;    
    
    
    
    var upload = function ( input ) {
        // reset
        input.parents('.component-form-file').removeClass('img-before');
        input.parents('.component-form-file').removeClass('img-loading');
        input.parents('.component-form-file').removeClass('img-after');
        input.parents('.component-form-file').removeClass('img-error');
        
        input.parents('.component-form-file').addClass('img-loading');
        // img-before, img-afte
        var fileUpload = input.get(0);
        var files = fileUpload.files;
        var bid = 0;
        bid = '';
        
        if (files.length != 0) {
            var data = new FormData();
            for (var i = 0; i < files.length ; i++) {
                data.append('upload_file', files[i]);
            }
            $.ajax({
                xhr: function () {
                    var xhr = $.ajaxSettings.xhr();
                    xhr.upload.onprogress = function (e) {
                        console.log(Math.floor(e.loaded / e.total * 100) + '%');
                    };
                    return xhr;
                },
                contentType: false,
                processData: false,
                type: 'POST',
                data: data,
                url: '/api/upload/' + bid,
                success: function (response) {
                    input.parents('.component-form-file').removeClass('img-loading');                    
                    if ( response.status == 200 ) {
                        input.parents('.component-form-file').addClass('img-after');
                        if ( 0 < input.parents('.component-form-file').length ) {                            
                            var component = input.parents('.component-form-file');
                            component.find('.component-form-file-input').val(response.data.out.file_name);
                            component.find('.component-form-file-view').attr('src',response.data.out.file_path);                           
                        }
                    } else {
                        input.parents('.component-form-file').addClass('img-error');
                        alert('재시도 해주세요.');
                    };
                    console.log(response);
                }
            });
        };        
    };
    
    $('input[type=file]').each(function( index, element ) {
        // 파일삭제
        var input = $(this);        
        
        // reset
        input.parents('.component-form-file').removeClass('img-before');
        input.parents('.component-form-file').removeClass('img-loading');
        input.parents('.component-form-file').removeClass('img-after');
        input.parents('.component-form-file').removeClass('img-error');
        
        if ( 0 < input.parents('.component-form-file').length ) {
            var component = input.parents('.component-form-file');
            if ( component.find('.component-form-file-input').val().length == 0 ) {
                // component.find('.component-form-file-del-btn')
                input.parents('.component-form-file').addClass('img-before');
            } else {
                input.parents('.component-form-file').addClass('img-after');                                            
            }
            
            component.find('.component-form-file-del-btn').unbind('click');
            component.find('.component-form-file-del-btn').on('click', function () {
                $(this).parent('.component-form-file').find('.component-form-file-input').val('');
                $(this).parent('.component-form-file').find('.component-form-file-view').attr('src','');
                $(this).parent('.component-form-file').find('input[type=file]').val('');
                
                // reset
                input.parents('.component-form-file').removeClass('img-before');
                input.parents('.component-form-file').removeClass('img-loading');
                input.parents('.component-form-file').removeClass('img-after');
                input.parents('.component-form-file').removeClass('img-error');
                
                input.parents('.component-form-file').addClass('img-before');                
            });            
            
        }
    });
    
    $('input[type=file]').unbind('change');
    $('input[type=file]').on('change', function () {
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
            if($.inArray(ext, ['mp4']) == -1) {
                alert('mp4 파일만 업로드 할수 있습니다.');
                return;
            }            
        } else {
            if($.inArray(ext, ['gif','png','jpg','jpeg']) == -1) {
                alert('png,jpg,jpeg 파일만 업로드 할수 있습니다.');
                return;
            }            
        };
        
        if ( fileSize > maxSize ) {
            alert("첨부파일 사이즈는 1MB 이내로 등록 가능합니다.");
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
};