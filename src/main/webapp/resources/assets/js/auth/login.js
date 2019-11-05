/*
(function () {
  const root = document.querySelector('.root');
  const navigation = document.getElementById('loginBtn');

  function render(data) {
    const json = JSON.parse(data);
    root.innerHTML = `<h1>${json.title}</h1><p>${json.content}</p>`;
  }

  function renderHtml(html) {
    root.innerHTML = html;
  }

  function get(url) {
    return new Promise((resolve, reject) => {
      const req = new XMLHttpRequest();
      req.open('GET', url);
      req.send();

      req.onreadystatechange = function () {
        if (req.readyState === XMLHttpRequest.DONE) {
          if (req.status === 200) resolve(req.response);
          else reject(req.statusText);
        }
      };
    });
  }

  const routes = {
    '/': function () {
      get('/data/home.json')
        .then(res => render(res));
    },
    '/service': function () {
      get('/data/service.json')
        .then(res => render(res));
    },
    '/about': function () {
      get('/dsyouth/join')
        .then(res => renderHtml(res));
    },
    otherwise(path) {
      root.innerHTML = `${path} Not Found`;
    }
  };

  function router(path) {
    (routes[path] || routes.otherwise)(path);
  }

  // history entry가 변경되면 발생하는 이벤트
  // PJAX 방식은 hash를 사용하지 않으므로 hashchange 이벤트를 사용할 수 없다.
  // popstate event는 pushState에 의해 발생시키지 않는다.
  // 이전페이지 / 다음페이지 button 또는 history.back() / history.go(n)에 의해 발생한다.
  window.addEventListener('popstate', e => {
    // e.state는 pushState 메소드의 첫번째 인수
    console.log('[popstate]', e.state);
    // 이전페이지 / 다음페이지 button이 클릭되면 router를 호출
    router(e.state.path);
  });

  // 내비게이션을 클릭하면 주소창의 url이 변경되므로 서버로 요청이 전송된다.
  // preventDefault를 사용하여 이를 방지하고 history 관리를 위한 처리를 실시
  navigation.addEventListener('click', e => {
    if (!e.target || e.target.nodeName !== 'A') return;
    e.preventDefault();

    // 이동 페이지
    const path = e.target.getAttribute('href');

    // pushState 메소드는 주소창의 URL은 변경하지만 요청하지는 않는다.
    history.pushState({ path }, null, path);

    // path에 의한 AJAX 요청
    router(path);
  });

  // 웹페이지가 처음 로딩되었을 때
  router('/');

  // DOMContentLoaded은 HTML과 script가 로드된 시점에 발생하는 이벤트로 load 이벤트보다 먼저 발생한다. (IE 9 이상 지원)
  // window.addEventListener('DOMContentLoaded', router);

  // 새로고침이 클릭되었을 때, 현 페이지(예를들어 loclahost:5004/service)가 서버에 요청된다.
  // 서버측에는 이에 응답하는 기능이 추가되어야 한다.
  // ex) app.get('/service', (req, res) => res.sendFile(path.join(__dirname + '/public/data/service.html')));
}());




//Client
function get(url) {
  return new Promise((resolve, reject) => {
    const req = new XMLHttpRequest();
    req.open('GET', url);
    // 서버에 JSON을 요청한다.
    req.setRequestHeader('Accept', 'application/json');
    req.send();

    req.onreadystatechange = function () {
      if (req.readyState === XMLHttpRequest.DONE) {
        if (req.status === 200) resolve(req.response);
        else reject(req.statusText);
      }
    };
  });
}

get('/service').then(res => render(res));




//Server
const express = require('express');
const app = express();
const fs = require('fs');

app.get(contextPath + '/mypage', (req, res) => {
  res.format({
    // 새로고침에 의한 브라우저 요청
    'text/html': function(){
      res.sendFile(path.join(__dirname + contextPath + '/mypage'));
    },
    // AJAX 요청
    'application/json': function(){
      res.send(JSON.parse(fs.readFileSync(contextPath + '/mypage', 'utf8')));
    },
    'default': function() {
      // log the request and respond with 406
      res.status(406).send('Not Acceptable');
    }
  });
});

app.listen(3000, function () {
  console.log('listening on http//localhost:3000');
});


*/



$(document).ready(function(){
	
	//모바일 기종 체크
	var varUA = navigator.userAgent.toLowerCase(); //userAgent 값 얻기
	 
	if (varUA.match('android') != null) { 
		//안드로이드 일때 처리
		$("#linkCopy").show();
		$("#clipURL").hide();
	} else if (varUA.indexOf("iphone")>-1||varUA.indexOf("ipad")>-1||varUA.indexOf("ipod")>-1) { 
	    //IOS 일때 처리
		$("#linkCopy").hide();
		$("#clipURL").show();
	} else {
	    //아이폰, 안드로이드 외 처리
		$("#linkCopy").show();
		$("#clipURL").hide();
	}
	
	
	// 웹페이지가 처음 로딩되었을 때
    router('/');
    
     // history entry가 변경되면 발생하는 이벤트
	  // PJAX 방식은 hash를 사용하지 않으므로 hashchange 이벤트를 사용할 수 없다.
	  // popstate event는 pushState에 의해 발생시키지 않는다.
	  // 이전페이지 / 다음페이지 button 또는 history.back() / history.go(n)에 의해 발생한다.
	  window.addEventListener('popstate', e => {
	    // e.state는 pushState 메소드의 첫번째 인수
	    console.log('[popstate]', e.state);
	    // 이전페이지 / 다음페이지 button이 클릭되면 router를 호출
	    router(e.state.path);
	  });
	  
});

function router(path) {
    (routes[path] || routes.otherwise)(path);
  }


function get(url) {
    return new Promise((resolve, reject) => {
      const req = new XMLHttpRequest();
      req.open('GET', url);
      req.send();

      req.onreadystatechange = function () {
        if (req.readyState === XMLHttpRequest.DONE) {
          if (req.status === 200) resolve(req.response);
          else reject(req.statusText);
        }
      };
    });
  }


// 로그인
$("#loginForm").submit(function(e) {
	
	var $loginId = $('#loginId');
	var $loginPw = $('#loginPw');
	var useCookie = $('#useCookieCheckBox').is(":checked");
	
	if(useCookie == true){
		useCookie = 'Y';
	}else {
		useCookie = 'N';
	}

    var validateMessage = null;
    var validateFocus = null;
    
    // input 데이터 체크 및 팝업text 입력, 포커스 입력
    if ($loginId.val() == "") {
    	validateMessage = '아이디를 입력해 주세요.';
        validateFocus = $loginId;
    }else if($loginPw.val() == "") {
    	validateMessage = '비밀번호를 입력해 주세요.';
        validateFocus = $loginPw;
    }
    
    // input 데이터 체크 및 팝업창 띄워주고 포커스
    if(validateMessage != null) {
         validateFocus.focus();
         alert(validateMessage);
         return false;
    }
    
    
    var form = $(this);
    var url = contextPath + "/rest/login"
	
    $.ajax({
          type: "POST",
          url: url,
          data: {
        	  "loginId" : $loginId.val(),
        	  "loginPw" : $loginPw.val(),
        	  "useCookie" : useCookie
          },
          success: function(result)
          {
              if(result.success) { // show response from the php script.
            	  location.href= contextPath + "/mypage";
            	  
            	  
            	  // SPA - PJAX 방식
            	  (function () {
            		  const root = document.querySelector('.root');

            		  function render(data) {
            		    const json = JSON.parse(data);
            		    root.innerHTML = `<h1>${json.title}</h1><p>${json.content}</p>`;
            		  }

            		  function renderHtml(html) {
            		    root.innerHTML = html;
            		  }

            		  function get(url) {
            		    return new Promise((resolve, reject) => {
            		      const req = new XMLHttpRequest();
            		      req.open('GET', url);
            		      req.send();

            		      req.onreadystatechange = function () {
            		        if (req.readyState === XMLHttpRequest.DONE) {
            		          if (req.status === 200) resolve(req.response);
            		          else reject(req.statusText);
            		        }
            		      };
            		    });
            		  }
            		  
            		  get(contextPath + '/mypage').then(res => renderHtml(res));
            		  
            		  history.pushState(null, null, contextPath + '/mypage');

            		}());



            	  //Server
            	  const express = require('express');
               	  const app = express();
               	  const fs = require('fs');

               	  app.get(contextPath + '/mypage', (req, res) => {
               	    res.format({
               	      // 새로고침에 의한 브라우저 요청
               	      'text/html': function(){
               	        res.sendFile(path.join(__dirname + contextPath + '/mypage'));
               	      },
               	      // AJAX 요청
               	      'application/json': function(){
               	        res.send(JSON.parse(fs.readFileSync(contextPath + '/mypage', 'utf8')));
               	      },
               	      'default': function() {
               	        // log the request and respond with 406
               	        res.status(406).send('Not Acceptable');
               	      }
               	    });
               	  });

               	  app.listen(3000, function () {
               	    console.log('listening on http//localhost:3000');
               	  });
            	  
            	  
            	  
              }else {
                  alert(result.message);
              }
          },
          fail: function(result) {
              alert("로그인에 실패 했습니다.");
          }
    });
    e.preventDefault(); // avoid to execute the actual submit of the form.
    
  
});



// 로그아웃
function logout(val) {
	
	var url = contextPath + "/rest/logout"
	
	$.ajax({
      type: "POST",
      url: url,
      data: {
    	  id : val
      }, // serializes the form’s elements.
      success: function(result)
      {
          if(result.success) { // show response from the php script.
        	  location.href = contextPath + "/login";
          }else {
        	  alert(result.message);
          }
      },
	  fail: function(result) {
		  alert("로그아웃에 실패 했습니다.");
	  }
	});
}
	

// 회원 탈퇴
function withdraw(val) {
	
	var conf = confirm('정말 탈퇴 하시겠습니까?');

	if(conf){

		var url = contextPath + "/rest/withdraw"
		
		$.ajax({
	          type: "POST",
	          url: url,
	          data: {
	        	  id : val
	          }, // serializes the form’s elements.
	          success: function(result)
	          {
	              if(result.success) { // show response from the php script.
	            	  location.href = contextPath + "/login";
	              }else {
	            	  alert(result.message);
	              }
	          },
	   		  fail: function(result) {
	   			  alert("회원탈퇴에 실패 했습니다.");
	   		  }
	    });
	}

}


//링크 카피
$("#linkCopy").click(function(e) {
	
	copyToClipboard(document.location.origin + contextPath + "/login");
	alert("링크가 복사 되었습니다.");
	
	e.preventDefault(); // avoid to execute the actual submit of the form.
   
});


function copyToClipboard(val) {
	var t = document.createElement("textarea");
	document.body.appendChild(t);
	t.value = val;
	t.select();
	document.execCommand('copy');
	document.body.removeChild(t);
}