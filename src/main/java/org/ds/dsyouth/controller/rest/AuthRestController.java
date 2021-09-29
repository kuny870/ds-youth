package org.ds.dsyouth.controller.rest;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.controller.rest.common.ResponseCode;
import org.ds.dsyouth.controller.rest.common.RestResponse;
import org.ds.dsyouth.exception.IdDuplicatedException;
import org.ds.dsyouth.exception.IdNoMatchException;
import org.ds.dsyouth.exception.PasswordNoMatchException;
import org.ds.dsyouth.model.Address;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.User;
import org.ds.dsyouth.model.UserKeepLogin;
import org.ds.dsyouth.service.AdminService;
import org.ds.dsyouth.service.AuthService;
import org.ds.dsyouth.utils.StringHelper;
import org.ds.dsyouth.validator.AddressValidator;
import org.ds.dsyouth.validator.UserJoinValidator;
import org.ds.dsyouth.validator.UserLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@RestController
@RequestMapping("/rest")
public class AuthRestController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AdminService adminService;

	@Autowired
	UserLoginValidator userLoginValidator;
	
	@Autowired
	UserJoinValidator userJoinValidator;

	@Autowired
	AddressValidator addressValidator;

	
	@InitBinder("userLogin")
	public void initRegEquipBinder_userLogin(WebDataBinder dataBinder) {
		dataBinder.addValidators(userLoginValidator);
	}
	@InitBinder("userJoin")
	public void initRegEquipBinder_userJoin(WebDataBinder dataBinder) {
		dataBinder.addValidators(userJoinValidator);
	}
	
	@InitBinder("address")
	public void initRegEquipBinder_address(WebDataBinder dataBinder) {
		dataBinder.addValidators(addressValidator);
	}

	
	
	/**
	 * 로그인
	 * @param user
	 * @param userBindingResult
	 * @param request
	 * @param httpResponse
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * @throws IdNoMatchException
	 * @throws PasswordNoMatchException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	public RestResponse login(
			@ModelAttribute("userLogin") @Valid User user,
			BindingResult userBindingResult,
			HttpServletRequest request,
			HttpServletResponse httpResponse) throws NoSuchAlgorithmException, UnsupportedEncodingException, IdNoMatchException, PasswordNoMatchException {
		
		RestResponse response = userLoginValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {

			authService.getUserCheck(user);
			
			User userTemp = authService.getUserByLoginId(user);
			HttpSession session = request.getSession();
			if(session.getAttribute(Constants.SESSION_USER) != null) {
				session.removeAttribute(Constants.SESSION_USER);
			}
			session.setAttribute(Constants.SESSION_USER, userTemp);
			
		    if(user.getUseCookie().equals("Y")) { 
			  Cookie cookie = new Cookie(Constants.COOKIE_USER, session.getId()); // session.id 값을 cookie에 저장
			  cookie.setPath("/"); // cookie 값 경로 설정 
			  cookie.setMaxAge(60*60*999999999); // 쿠키 유효시간 설정 
			  httpResponse.addCookie(cookie); // 쿠키 값을 response 에 저장
		  
			  //[모바일 or PC 구별하기]
//			  String filter = "iphone|ipod|android|windows ce|blackberry|symbian|windows phone|webos|opera mini|opera mobi|polaris|iemobile|lgtelecom|nokia|sonyericsson|lg|samsung";
//			  String filters[] = filter.split("\\|");
//			  String webType = "";
//						
//			  for(String tmp : filters){
//				if ( request.getHeader("User-Agent").toLowerCase().indexOf(tmp) != -1) {
//					webType = "MOBILE";
//					break;
//				} else {
//				    webType = "PC";
//				}
//			  }
//				
//			  // 사용자의 기기 구분값을 담기.
//			  request.setAttribute("webType", webType);
			
			  UserKeepLogin ukl = new UserKeepLogin();
			  ukl.setLoginId(userTemp.getLoginId());
			  ukl.setSessionId(cookie.getValue());
			  
			  authService.keepLogin(ukl);
		    }
			 
			
		} catch (IdNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.ID_NO_MATCH);
		} catch (PasswordNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.PASSWORD_NO_MATCH);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	

	/**
	 * 회원가입
	 * @param user
	 * @param userBindingResult
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws IdDuplicatedException
	 */
	@RequestMapping(value = "/join", method = RequestMethod.POST, produces = "application/json")
	public RestResponse join(
			@ModelAttribute("userJoin") @Valid User user,
			BindingResult userBindingResult
			) throws UnsupportedEncodingException, NoSuchAlgorithmException, IdDuplicatedException {

		RestResponse response = userJoinValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
	
		try {
			authService.registUser(user);
			
		} catch (IdDuplicatedException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.ID_DUPLICATED);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		response.setData(user.getId());
		
		return response;
	}
	
	
	
	/**
	 * 회원 기본정보 수정
	 * @param user
	 * @param userBindingResult
	 * @return
	 */
	@RequestMapping(value = "/profile/reset", method = RequestMethod.POST, produces = "application/json")
	public RestResponse reset_info(
			@ModelAttribute("user") @Valid User user,
			HttpServletRequest request,
			BindingResult userBindingResult) {

		RestResponse response = userJoinValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			
			authService.modifyUserInfo(user);
			
			user = authService.getUserByLoginId(user);
			
			HttpSession session = request.getSession();
			if(session.getAttribute(Constants.SESSION_USER) != null) {
				session.removeAttribute(Constants.SESSION_USER);
			}
			session.setAttribute(Constants.SESSION_USER, user);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 비밀번호 수정
	 * @param user
	 * @param currentPw
	 * @param request
	 * @param userBindingResult
	 * @return
	 */
	@RequestMapping(value = "/pw/reset", method = RequestMethod.POST, produces = "application/json")
	public RestResponse reset_pw(
			User user, String currentPw,
			HttpServletRequest request,
			BindingResult userBindingResult) {

//		RestResponse response = userJoinValidator.bindingError(userBindingResult);
		
		RestResponse response = new RestResponse();
		
		try {
			
			User userTemp = new User();
			userTemp.setLoginId(user.getLoginId());
			userTemp.setLoginPw(currentPw);
			
			authService.getUserCheck(userTemp);
			
			authService.modifyUserPassword(user);
			
			user = authService.getUserByLoginId(user);
			
			HttpSession session = request.getSession();
			if(session.getAttribute(Constants.SESSION_USER) != null) {
				session.removeAttribute(Constants.SESSION_USER);
			}
			session.setAttribute(Constants.SESSION_USER, user);
			
		} catch (PasswordNoMatchException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.PASSWORD_NO_MATCH);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.NO_SUCH_ALGORITHM_EXCEPTION);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNSUPPORTED_ENCODING_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 비밀번호 수정 - 임시 비밀번호 발급
	 * @param user
	 * @param currentPw
	 * @param request
	 * @param userBindingResult
	 * @return
	 */
	@RequestMapping(value = "/user/pw/reset", method = RequestMethod.POST, produces = "application/json")
	public RestResponse user_pw_reset(User user) {

		RestResponse response = new RestResponse();
		
		try {
			
			authService.modifyUserPwReset(user);
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.NO_SUCH_ALGORITHM_EXCEPTION);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNSUPPORTED_ENCODING_EXCEPTION);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 권한 적용
	 * @param auth_id 변경할 권한
	 * @param arr 변경할 대상의 id값
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authDetail/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse authDetail_regist(
			Integer auth_id,
			String[] arr) {

		RestResponse response = new RestResponse();
		
		try {
			
			for (int i = 0; i < arr.length; i++) {
				User user = new User();
				user.setId(StringHelper.parseInt(arr[i]));
				user.setAuthId(auth_id);
				authService.modifyUserAuth(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 국장 권한 적용
	 * @param auth_exec 변경할 권한
	 * @param arr 변경할 대상의 id값
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/authExecDetail/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse authExecDetail_regist(
			Integer auth_exec,
			String[] arr) {

		RestResponse response = new RestResponse();
		
		try {
			
			for (int i = 0; i < arr.length; i++) {
				User user = new User();
				user.setId(StringHelper.parseInt(arr[i]));
				user.setAuthExec(auth_exec);
				authService.modifyUserAuth(user);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 권한 초기화
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/auth/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse auth_remove(
			@ModelAttribute Auth auth) {

		RestResponse response = new RestResponse();
		
		try {
			authService.removeAuth(auth);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 국장 권한 초기화
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/authExec/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse authExec_remove() {

		RestResponse response = new RestResponse();
		
		try {
			authService.removeAuthExec();
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	
	/**
	 * 실시간 select box 변환 - 부서 선택시 팀 불러오기
	 * @param team
	 * @param request
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping(value = "/select/depart", method = RequestMethod.POST, produces = "application/json")
	public RestResponse select_depart(
			@ModelAttribute("departId") String departId) {
		
		RestResponse response = new RestResponse();
		
		try {
			
			List<Team> teamList = adminService.getTeamByDepart(departId);
			
			response.setData(teamList);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
	/**
	 * 실시간 select box 변환 - 팀 선택시 순 불러오기
	 * @param team
	 * @param request
	 * @param httpResponse
	 * @return
	 */
	@RequestMapping(value = "/select/group", method = RequestMethod.POST, produces = "application/json")
	public RestResponse select_group(Group group) {
		
		RestResponse response = new RestResponse();
		
		try {
			
			List<Group> groupList = adminService.getGroupListByTeam(group);
			
			response.setData(groupList);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 마이페이지 - 주소 등록
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mypage/address/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse address_regist(
			@ModelAttribute("address") @Valid Address address,
			BindingResult addressBindingResult) {
		
		RestResponse response = addressValidator.bindingError(addressBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			
			authService.registAddress(address);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 마이페이지 - 주소 수정
	 * @param address
	 * @param request
	 * @param userBindingResult
	 * @return
	 */
	@RequestMapping(value = "/mypage/address/edit", method = RequestMethod.POST, produces = "application/json")
	public RestResponse address_edit(
			@ModelAttribute Address address,
			BindingResult userBindingResult) {

		RestResponse response = userJoinValidator.bindingError(userBindingResult);
		
		if(response.isSuccess() == false) {
			return response;
		}
		
		try {
			
			authService.modifyAddress(address);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 마이페이지 - 주소 삭제
	 * @param address
	 * @return
	 */
	@RequestMapping(value = "/mypage/address/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse remove(
			@ModelAttribute Address address) {

		RestResponse response = new RestResponse();
		
		try {
			authService.removeAddress(address);
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 회원 로그아웃
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = "application/json")
	public RestResponse logout(
			@ModelAttribute User user,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		HttpSession session = request.getSession();
		session.invalidate();
		
		try {
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if(loginCookie != null) {
				String sessionId = loginCookie.getValue();
				UserKeepLogin ukl = new UserKeepLogin();
				ukl.setLoginId(user.getLoginId());
				ukl.setSessionId(sessionId);
				authService.removeSessionId(ukl);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 회원 탈퇴
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/withdraw", method = RequestMethod.POST, produces = "application/json")
	public RestResponse withdraw(
			@ModelAttribute User user,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			boolean result = authService.removeUser(user);
			if(result) {
				HttpSession session = request.getSession();
				session.invalidate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 회원 강퇴
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/remove", method = RequestMethod.POST, produces = "application/json")
	public RestResponse user_remove(
			@ModelAttribute User user,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			authService.removeUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 회원 복구
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/user/restore", method = RequestMethod.POST, produces = "application/json")
	public RestResponse user_restore(
			@ModelAttribute User user,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			authService.restoreUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
}