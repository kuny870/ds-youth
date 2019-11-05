//팀 정보 수정
$("#teamModifyForm").submit(function(e) {

	var form = $(this);
	var url = contextPath + "/rest/team/modify"
   
	$.ajax({
          type: "POST",
          url: url,
          data: form.serialize(), // serializes the form’s elements.
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  alert("팀 정보가 변경 되었습니다.")
            	  location.href = contextPath + "/admin/team";
              }else {
            	  alert(result.message);
              }
          },
   		  fail: function(result) {
   			  alert("팀 정보 변경에 실패 했습니다.");
   		  }
    });

	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});

