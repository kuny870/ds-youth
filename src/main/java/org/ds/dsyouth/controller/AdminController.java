package org.ds.dsyouth.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.model.MemberState;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.User;
import org.ds.dsyouth.model.YearSeason;
import org.ds.dsyouth.service.AdminService;
import org.ds.dsyouth.service.AuthService;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({Constants.SESSION_USER})
public class AdminController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private MemberService memberService;
	
	
	/**
	 * 청년부 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/list", method = RequestMethod.GET)
	public ModelAndView admin_list() {

		List<Depart> departList = adminService.getDepartList();
		List<Team> teamList = adminService.getTeamList();
		
		ModelAndView mav = new ModelAndView("admin/list");
		
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		
		return mav;
	}
	
	/**
	 * 권한 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/auth", method = RequestMethod.GET)
	public ModelAndView admin_auth() {

		List<Auth> authList = adminService.getAuthList();
		
		// 각 권한에 대한 인원 카운트
		for(int i = 0; i < authList.size() - 1; i++) {
			int authCnt = authService.getAuthCnt(authList.get(i).getId());
			authList.get(i).setCnt(authCnt);
		}
		
		int authExecCnt = authService.getAuthExecCnt();
		authList.get(authList.size() - 1).setCnt(authExecCnt);
		
		ModelAndView mav = new ModelAndView("admin/auth/list");
		
		mav.addObject("authList", authList);
		
		return mav;
	}

	
	/**
	 * 부서 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/depart", method = RequestMethod.GET)
	public ModelAndView admin_depart() {

		List<Depart> departList = adminService.getDepartList();
		
		ModelAndView mav = new ModelAndView("admin/depart/list");
		
		mav.addObject("departList", departList);
		
		return mav;
	}
	
	
	/**
	 * 팀 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/team", method = RequestMethod.GET)
	public ModelAndView admin_team() {

		List<Depart> departList = adminService.getDepartList();
		List<Team> teamList = adminService.getTeamListByAdmin();
		
		ModelAndView mav = new ModelAndView("admin/team/list");
		
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		
		return mav;
	}
	
	
	/**
	 * 팀 관리 수정
	 * @param team
	 * @return
	 */
	@RequestMapping(value = "/team/modify/{id}", method = RequestMethod.GET)
	public ModelAndView team_modify(Team team) {

		team = adminService.getTeam(team);
		
		ModelAndView mav = new ModelAndView("admin/team/modify");
		
		mav.addObject("team", team);
		
		return mav;
	}
	
	/**
	 * 순명 관리
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/admin/group/name", method = RequestMethod.GET)
	public ModelAndView admin_group(
			HttpServletRequest request, 
			Group group) {

		String thisYear = "";
		String thisMonth = DateHelper.getMonth();
		
		if(group.getYear() == null) {
			thisYear = DateHelper.getYear();
		}else {
			thisYear = group.getYear();
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute(Constants.SESSION_USER);
		group.setTeamId(user.getTeamId().toString());
		
		List<Team> teamList = adminService.getTeamList();
		List<Group> groupList = adminService.getGroupList(group);
		List<Member> memberList = memberService.getMemberListByGroupGrade(group);
		List<YearSeason> seasonList = adminService.getYearSeasonList(thisYear);
		
		// 각 순에 대한 인원 카운트
		for(int i = 0; i < groupList.size(); i++) {
			int cnt = memberService.getGroupCnt(groupList.get(i));
			groupList.get(i).setCnt(cnt);
		}
		
		thisYear = DateHelper.getYear();
		
		int yearInt = StringHelper.parseIntAndArrayRange(thisYear);
		
		// 12월에 다음해 순명 등록 가능
		if("12".equals(thisMonth)) {
			yearInt++;
		}
		
		// 이번년도 부터 이전년도의 출석부 존재하는 모든 년도 구하기
		List yearList = new ArrayList();
		for(int i = 2019; i <= yearInt; i++) {
			yearList.add(i);
			if(yearList.size() == 5) {
				break;
			}
		}
		
		ModelAndView mav = new ModelAndView("admin/group/name");
		
		mav.addObject("teamList", teamList);
		mav.addObject("groupList", groupList);
		mav.addObject("memberList", memberList);
		mav.addObject("seasonList", seasonList);
		mav.addObject("yearList", yearList);
		mav.addObject("thisYear", thisYear);
		mav.addObject("group", group);
		
		return mav;
	}
	
	
	/**
	 * 순명 관리 상세
	 * @return
	 */
	@RequestMapping(value = "/admin/group/detail", method = RequestMethod.GET)
	public ModelAndView admin_group_detail(Group group)	{
		
		group = adminService.getGroup(group.getId());
		String thisYear = DateHelper.getYear();
		
		List<Member> memberList = memberService.getMemberList(group);
		List<MemberState> memberStateList = adminService.getMemberStateList();
		
		ModelAndView mav = new ModelAndView("admin/group/detail");
		
		mav.addObject("group", group);
		mav.addObject("memberList", memberList);
		mav.addObject("thisYear", thisYear);
		mav.addObject("memberStateList", memberStateList);
		
		return mav;
	}
	
	
	/**
	 * 동기 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/samePeriod", method = RequestMethod.GET)
	public ModelAndView admin_samePeriod() {

		List<SamePeriod> samePeriodList = adminService.getSamePeriodList();
		
		// 각 동기에 대한 인원 카운트
		for(int i = 0; i < samePeriodList.size(); i++) {
			int cnt = memberService.getSamePeriodCnt(samePeriodList.get(i).getId());
			samePeriodList.get(i).setCnt(cnt);
		}
		
		String year = DateHelper.getYear();
		
		ModelAndView mav = new ModelAndView("admin/samePeriod/list");
		
		mav.addObject("samePeriodList", samePeriodList);
		mav.addObject("year", year);
		
		return mav;
	}
	
	
	/**
	 * 동기 관리 상세
	 * @return
	 */
	@RequestMapping(value = "/admin/samePeriod/detail", method = RequestMethod.GET)
	public ModelAndView admin_samePeriod_detail(
			@RequestParam Map<String, String> map)	{

		List<Member> memberList = memberService.getMemberListBySamePeriod(map.get("sId"));
		
		ModelAndView mav = new ModelAndView("admin/samePeriod/detail");
		
		mav.addObject("map", map);
		mav.addObject("memberList", memberList);
		
		return mav;
	}
	
	
	/**
	 * 팝업 띄우기
	 * @param content
	 * @param popSeq
	 * @param closeUsableYN
	 * @param closeNoticeDate
	 * @param scrollbarYN
	 * @return
	 */
	@RequestMapping(value = "/admin/pop/notice", method = RequestMethod.GET)
	public ModelAndView admin_pop(
			String content, String popSeq, String closeUsableYN, 
			String closeNoticeDate, String scrollbarYN)	{

		ModelAndView mav = new ModelAndView("admin/pop/notice");
		
		mav.addObject("content", content);
		mav.addObject("popSeq", popSeq);
		mav.addObject("closeUsableYN", closeUsableYN);
		mav.addObject("closeNoticeDate", closeNoticeDate);
		mav.addObject("scrollbarYN", scrollbarYN);
		
		return mav;
	}
	
	
}
