package org.ds.dsyouth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ds.dsyouth.model.User;
import org.ds.dsyouth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private AuthService authService;
	
	// preHandle() : 컨트롤러보다 먼저 수행되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		HttpSession session = request.getSession();
		Object login_session = session.getAttribute("login");

		User user = new User();
		
		if(login_session == null){	// login session 값이 없을 시
			
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {	// 로그인 상태 유지 시
				String sessionId = loginCookie.getValue();
				user = authService.getSessionId(sessionId);
				
				if(user != null) {
					session.setAttribute("login", user);
					return true;
				}
			}
			
			return true;
		}
		
		return true;
	}
	
	// postHandle() : 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
    		Object handler, ModelAndView modelAndView) throws Exception {
    	
        super.postHandle(request, response, handler, modelAndView);
    }

}
