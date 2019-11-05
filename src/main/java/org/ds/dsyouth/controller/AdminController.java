package org.ds.dsyouth.controller;

import java.util.List;
import java.util.Map;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.service.AdminService;
import org.ds.dsyouth.service.AuthService;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.utils.DateHelper;
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
		List<Team> teamList = adminService.getTeamList();
		
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
	@RequestMapping(value = "/team/modify/{tShortName}", method = RequestMethod.GET)
	public ModelAndView team_modify(Team team) {

		team = adminService.getTeam(team);
		
		ModelAndView mav = new ModelAndView("admin/team/modify");
		
		mav.addObject("team", team);
		
		return mav;
	}
	
	
	/**
	 * 순명 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/group/name", method = RequestMethod.GET)
	public ModelAndView admin_group() {

		List<Team> teamList = adminService.getTeamList();
		List<Group> groupList = adminService.getGroupList();
		List<Member> memberList = memberService.getMemberList();
		
		for(int i = 0; i < groupList.size(); i++) {
			int cnt = memberService.getGroupCnt(groupList.get(i).getId());
			groupList.get(i).setCnt(cnt);
		}
		
		ModelAndView mav = new ModelAndView("admin/group/name");
		
		mav.addObject("teamList", teamList);
		mav.addObject("groupList", groupList);
		mav.addObject("memberList", memberList);
		
		return mav;
	}
	
	
	/**
	 * 순명 관리 상세
	 * @return
	 */
	@RequestMapping(value = "/admin/group/detail", method = RequestMethod.GET)
	public ModelAndView admin_group_detail(
			@RequestParam Map<String, String> map)	{

		List<Member> memberList = memberService.getMemberList(map.get("team"));
		
		ModelAndView mav = new ModelAndView("admin/group/detail");
		
		mav.addObject("map", map);
		mav.addObject("memberList", memberList);
		
		return mav;
	}
	
	
	/**
	 * 동기 관리
	 * @return
	 */
	@RequestMapping(value = "/admin/samePeriod", method = RequestMethod.GET)
	public ModelAndView admin_samePeriod() {

		List<SamePeriod> samePeriodList = adminService.getSamePeriodList();
		
		for(int i = 0; i < samePeriodList.size(); i++) {
			int cnt = memberService.getSamePeriodCnt(samePeriodList.get(i).getId());
			samePeriodList.get(i).setCnt(cnt);
		}
		
		String year = DateHelper.getDate().substring(0, 4);
		
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

		List<Member> memberList = memberService.getMemberListBySamePeriod();
		
		ModelAndView mav = new ModelAndView("admin/samePeriod/detail");
		
		mav.addObject("map", map);
		mav.addObject("memberList", memberList);
		
		return mav;
	}
	
	
}
