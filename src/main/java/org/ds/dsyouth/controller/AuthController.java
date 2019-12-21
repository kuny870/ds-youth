package org.ds.dsyouth.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.model.Address;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.User;
import org.ds.dsyouth.service.AdminService;
import org.ds.dsyouth.service.AuthService;
import org.ds.dsyouth.validator.UserLoginValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({Constants.SESSION_USER})
public class AuthController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	UserLoginValidator userLoginValidator;
	
	@InitBinder("userLogin")
	public void initRegEquipBinder_userLogin(WebDataBinder dataBinder) {
		dataBinder.addValidators(userLoginValidator);
	}
	
	@Autowired
	private AuthService authService;

	
	/**
	 * 로그인 화면
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login2(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		// login 체크
		HttpSession session = request.getSession();
		
		if(session.getAttribute(Constants.SESSION_USER) != null) {
			// 로그인 상태 유지 시
			mav = new ModelAndView("mypage/main");
		} else {
			// 세션 값 없을 시
			mav = new ModelAndView("auth/login");
		}
		
		return mav;
	}
	
	/**
	 * 로그인 화면
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		
//		System.out.print("test!!!!!!!!!!!!!!!!!!!!");
//		ApplicationContext context = new ClassPathXmlApplicationContext("jobScheduler.xml");
		
		ModelAndView mav = new ModelAndView();
		
		// login 체크
		HttpSession session = request.getSession();
		
		if(session.getAttribute(Constants.SESSION_USER) != null) {
			// 로그인 상태 유지 시
			mav = new ModelAndView("mypage/main");
		} else {
			// 세션 값 없을 시
			mav = new ModelAndView("auth/login");
		}
		
		return mav;
	}
	
	
	/**
	 * 회원가입 화면
	 * @return
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join() {
		
		List<Depart> departList = adminService.getDepartListByJoin();
		
		ModelAndView mav = new ModelAndView("auth/join");
		
		mav.addObject("departList", departList);
		
		return mav;
	}
	

	/**
	 * 회원 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public ModelAndView admin_user()	{

		List<User> userList = authService.getUserListByUser();
		
		ModelAndView mav = new ModelAndView("admin/user/list");
		
		mav.addObject("userList", userList);
		
		return mav;
	}
	
	
	
	/**
	 * 권한별 관리 상세
	 * @return
	 */
	@RequestMapping(value = "/admin/auth/detail/{id}/{name}", method = RequestMethod.GET)
	public ModelAndView auth_detail(
			Auth auth)	{

		List<User> userList = authService.getUserListByAuth();
		
		ModelAndView mav = new ModelAndView("admin/auth/detail");
		
		mav.addObject("auth", auth);
		mav.addObject("userList", userList);
		
		return mav;
	}
	
	
	// ************************** mypage ******************************* //
	
	
	/**
	 * 마이페이지 화면
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public ModelAndView mypage_main() {

		List<Depart> departList = adminService.getDepartList();
		List<Team> teamList = adminService.getTeamList();
		
		ModelAndView mav = new ModelAndView("mypage/main");
		
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		
		return mav;
	}
	
	
	
	/**
	 * 마이페이지 - 기본정보 수정 화면
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/mypage/profile", method = RequestMethod.GET)
	public ModelAndView setting_info(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Object user_tmp = session.getAttribute(Constants.SESSION_USER);
		User user = (User)user_tmp;
		
		List<Depart> departList = adminService.getDepartList();
		List<Team> teamList = adminService.getTeamByDepart(user.getDepartId().toString());
		
		ModelAndView mav = new ModelAndView("mypage/profile");
		
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		
		return mav;
	}
	
	
	/**
	 * 마이페이지 - 상세정보 수정 화면
	 * @return
	 */
	@RequestMapping(value = "/mypage/profile/detail", method = RequestMethod.GET)
	public ModelAndView setting_detail() {

		ModelAndView mav = new ModelAndView("mypage/profile_detail");
		
		return mav;
	}
	
	
	/**
	 * 마이페이지 - 비밀번호 변경 화면
	 * @return
	 */
	@RequestMapping(value = "/mypage/password/edit", method = RequestMethod.GET)
	public ModelAndView password_edit() {

		ModelAndView mav = new ModelAndView("mypage/pw_edit");
		
		return mav;
	}
	
	
	/**
	 * 마이페이지 - 새 주소
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/mypage/address/regist", method = RequestMethod.GET)
	public ModelAndView address_regist(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView("mypage/address_regist");
		
		return mav;
	}
	
	
	/**
	 * 마이페이지 - 주소 list 화면
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/mypage/address/list/{loginId}", method = RequestMethod.GET)
	public ModelAndView address_list(User user) {

		List<Address> addressList = authService.getAddressList(user);
		
		ModelAndView mav = new ModelAndView("mypage/address_list");
		
		mav.addObject("addressList", addressList);
		
		return mav;
	}
	
	
	/**
	 * 마이페이지 - 주소 수정 화면
	 * @param address
	 * @return
	 */
	@RequestMapping(value = "/mypage/address/edit/{id}", method = RequestMethod.GET)
	public ModelAndView address_edit(Address address) {

		address = authService.getAddress(address);
		
		ModelAndView mav = new ModelAndView("mypage/address_edit");
		
		mav.addObject("address", address);
		
		return mav;
	}
	

	/**
	 * 로그아웃
	 * @param locale
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET, produces = "application/json")
	public ModelAndView logout(Locale locale,
                               HttpServletRequest request) {

		ModelAndView mav = new ModelAndView("auth/login");
		
		mav.addObject("local", locale.getLanguage());
		
		return mav;
	}
}
