function fnGetCtgSub(sParam){
    var $target = $("select[name='groupId']");
     
    $target.empty();
    if(sParam == ""){
    	$target.append("<option value=''>순 전체</option>");
        return;
    } else if (sParam != "") {
    	
	    $.ajax({
	        type: "POST",
	        url: contextPath + "/rest/select/group",
	        async: false,
	        data:{ teamId : sParam },
	        dataType: "json",
	        success: function(result) {
	            if(result.data.length == 0){
	                $target.append("<option value=''>순 전체</option>");
	            }else{
	            	$target.append("<option value=''>순 전체</option>");
	                $(result.data).each(function(i){
	                    $target.append("<option value=" + result.data[i].id + ">"+ result.data[i].gName +"</option>");
	                });
	            }
	        }, error:function(xhr){
	            console.log(xhr.responseText);
	            alert("순 정보를 불러오는데 실패 했습니다.");
	            return;
	        }
	    });
	    
    }
    
}


//페이지 버튼 클릭 시
var pageModule = (function () {
     function movePage(param) {
            // location.pathname ===> 현재 페이지의 URL경로 반환 ===> http://localhost:8080/flower/pop/sender/list
           location.href = location.pathname + "?pageNo=" + param.pageNo + "&teamId=" + param.teamId + "&groupId=" + param.groupId + "&nameKW=" + param.nameKW;
     }
     
     return {
           movePage : function(pageNo) {
        	   	 var teamId = $('#teamId').val();
        		 var groupId = $('#groupId').val();
        		 var nameKW = $('#nameKW').val();
                 // 페이지 번호를 JSON형태로 저장해서 파라미터로 보낸다.
                 var param = {
                           pageNo : pageNo,
                           teamId : teamId,
                           groupId : groupId,
                           nameKW : nameKW
                     };
                movePage(param);
           },
           
           searchPage : function(param) {
                movePage(param);
           }
     }
})();