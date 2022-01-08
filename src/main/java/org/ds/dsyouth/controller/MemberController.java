package org.ds.dsyouth.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.excel.GenericExcelViewTeamList;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.model.MemberState;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.User;
import org.ds.dsyouth.page.Paging;
import org.ds.dsyouth.search.MemberSearch;
import org.ds.dsyouth.service.AdminService;
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
public class MemberController {

	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private AdminService adminService;

	
	/**
	 * 팀원 list
	 * @param memberSearch
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public ModelAndView member_list(MemberSearch memberSearch) throws UnsupportedEncodingException {

		String nameKW = java.net.URLDecoder.decode(memberSearch.getNameKW(), "UTF-8");
		memberSearch.setNameKW(nameKW);
		
		Paging<Member> paging = memberService.getMemberList(memberSearch);
		
		List<Depart> departList = adminService.getDepartList();
		List<Team> teamList = adminService.getTeamList();
		List<MemberState> memStateList = adminService.getMemberStateList();
		Group group = new Group();
		group.setTeamId(memberSearch.getTeamId());
		List<Group> groupList = adminService.getGroupListByTeam(group);

		String year = DateHelper.getYear();
		
		if("8".equals(memberSearch.getTeamId())) {
			memberSearch.setTeamId("4");
		}
		
		ModelAndView mav = new ModelAndView("member/list");
		
		mav.addObject("paging", paging);
		
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		mav.addObject("groupList", groupList);
		mav.addObject("memStateList", memStateList);
		mav.addObject("memberSearch", memberSearch);
		mav.addObject("year", year);
		
		return mav;
	}
	
	
	/**
	 * 팀원 등록
	 * @param request
	 * @param memberSearch
	 * @return
	 */
	@RequestMapping(value = "/member/regist", method = RequestMethod.GET)
	public ModelAndView member_regist(HttpServletRequest request, MemberSearch memberSearch) {

		HttpSession session = request.getSession();
		Object user_tmp = session.getAttribute(Constants.SESSION_USER);
		User user = (User)user_tmp;
		
		List<Depart> departList = adminService.getDepartList();
		List<MemberState> memberStateList = adminService.getMemberStateList();
		List<SamePeriod> samePeriodList = adminService.getSamePeriodList();
		List<Team> teamList = null;
		
		// departId 값이 '0' 은 admin 관리자 '3' 은 기타 부서 소속 
		if(user.getDepartId() == 0 || user.getDepartId() == 3) {
			teamList = adminService.getTeamByDepart(departList.get(0).getId().toString());
		} else {
			teamList = adminService.getTeamByDepart(user.getDepartId().toString());
		}
			
		ModelAndView mav = new ModelAndView("member/regist");
		
		mav.addObject("user", user);
		mav.addObject("samePeriodList", samePeriodList);
		mav.addObject("memberStateList", memberStateList);
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		mav.addObject("memberSearch", memberSearch);

		return mav;
	}
	
	
	/**
	 * 팀원 수정
	 * @param member
	 * @param memberSearch
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/member/modify", method = RequestMethod.GET)
	public ModelAndView member_modify(Member member, MemberSearch memberSearch) throws UnsupportedEncodingException {

		String nameKW = java.net.URLDecoder.decode(memberSearch.getNameKW(), "UTF-8");
		memberSearch.setNameKW(nameKW);
		
		member = memberService.getMember(member);
		List<Depart> departList = adminService.getDepartList();
		List<Team> teamList = adminService.getTeamByDepart(member.getDepartId().toString());
		List<MemberState> memberStateList = adminService.getMemberStateList();
		List<SamePeriod> samePeriodList = adminService.getSamePeriodList();
		
		ModelAndView mav = new ModelAndView("member/modify");
		
		mav.addObject("samePeriodList", samePeriodList);
		mav.addObject("memberStateList", memberStateList);
		mav.addObject("member", member);
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		mav.addObject("memberSearch", memberSearch);
		
		return mav;
	}
	
	
	
	/**
	 * 동기 list
	 * @return
	 */
	@RequestMapping(value = "/samePeriod/list", method = RequestMethod.GET)
	public ModelAndView samePeriod_list() {

		List<SamePeriod> samePeriodList = adminService.getSamePeriodList();
		
		for(int i = 0; i < samePeriodList.size(); i++) {
			int cnt = memberService.getSamePeriodCnt(samePeriodList.get(i).getId());
			samePeriodList.get(i).setCnt(cnt);
		}
		
		String year = DateHelper.getYear();
		
		ModelAndView mav = new ModelAndView("samePeriod/list");
		
		mav.addObject("samePeriodList", samePeriodList);
		mav.addObject("year", year);
		
		return mav;
	}
	
	
	/**
	 * 동기 상세
	 * @return
	 */
	@RequestMapping(value = "/samePeriod/detail", method = RequestMethod.GET)
	public ModelAndView samePeriod_detail(
			@RequestParam Map<String, String> map)	{

		List<Member> memberList = memberService.getMemberListBySamePeriodPer(map.get("sId"));
		
		String year = DateHelper.getYear();
		
		ModelAndView mav = new ModelAndView("samePeriod/detail");
		
		mav.addObject("map", map);
		mav.addObject("memberList", memberList);
		mav.addObject("year", year);
		
		return mav;
	}
	
	
	/*
     * 팀별관리 엑셀 다운로드
     */
    @RequestMapping(value = "/team/excelDownload", method = RequestMethod.GET)
    public GenericExcelViewTeamList team_excel_download(
    		Map<String, Object> model,
    		HttpServletResponse response) throws IOException {

    	String year = DateHelper.getYear();
    	Integer monthTmp = StringHelper.parseInt(DateHelper.getMonth());
    	String month = monthTmp.toString();
    	
    	Map<String, String> map = new HashMap<String, String>();
    	map.put("year", year);
    	map.put("month", month);
    	
    	List<Member> memberList = memberService.getMemberListForExcel(map);
		
        model.put("memberList", memberList);

        return new GenericExcelViewTeamList();

    }
	
	
}
