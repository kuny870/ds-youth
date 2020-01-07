package org.ds.dsyouth.controller;

import java.util.ArrayList;
import java.util.List;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.model.Family;
import org.ds.dsyouth.model.FamilyMember;
import org.ds.dsyouth.model.Retreat;
import org.ds.dsyouth.search.RetreatSearch;
import org.ds.dsyouth.search.type.ERetreatSeason;
import org.ds.dsyouth.service.RetreatService;
import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({Constants.SESSION_USER})
public class RetreatController {

	@Autowired
	private RetreatService retreatService;
	
	
	/**
	 * 관리자 모드 : Retreat List
	 * @return
	 */
	@RequestMapping(value = "/admin/retreat/list", method = RequestMethod.GET)
	public ModelAndView admin_retreat_list() {

		List<Retreat> retreatList = retreatService.getRetreatList();
		
		ModelAndView mav = new ModelAndView("retreat/admin/retreat");
		
		mav.addObject("retreatList", retreatList);
		
		return mav;
	}
	
	/**
	 * 관리자 모드 : 수련회 등록
	 * @return
	 */
	@RequestMapping(value = "/admin/retreat/regist", method = RequestMethod.GET)
	public ModelAndView admin_retreat_regist() {

		String thisYear = DateHelper.getYear();
		
		// 이번년도 부터 이전년도의 출석부 존재하는 모든 년도 구하기
		List yearList = new ArrayList();
		int yearInt = StringHelper.parseIntAndArrayRange(thisYear);
		for(int i = yearInt + 1; i >= 2020; i--) {
			yearList.add(i);
			if(yearList.size() == 5) {
				break;
			}
		}
		
		ModelAndView mav = new ModelAndView("retreat/admin/regist");
		
		mav.addObject("yearList", yearList);
		mav.addObject("thisYear", thisYear);
		mav.addObject("season", ERetreatSeason.values());
		
		return mav;
	}
	
	/**
	 * 관리자 모드 : Family List
	 * @return
	 */
	@RequestMapping(value = "/admin/retreat/family/list/{id}", method = RequestMethod.GET)
	public ModelAndView admin_retreat_family_list(Retreat retreat) {

		retreat = retreatService.getRetreat(retreat.getId().toString());
		
		List<Family> familyList = retreatService.getFamilyList(retreat);
		
		ModelAndView mav = new ModelAndView("retreat/admin/family");
		
		mav.addObject("familyList", familyList);
		mav.addObject("retreat", retreat);
		
		return mav;
	}
	
	/**
	 * 관리자 모드 : FamilyMember List
	 * @return
	 */
	@RequestMapping(value = "/admin/retreat/family/member/list", method = RequestMethod.GET)
	public ModelAndView admin_retreat_family_member_list(RetreatSearch retreatSearch) {

		Retreat retreat = retreatService.getRetreat(retreatSearch.getRetreatId());
		Family family = retreatService.getFamily(retreatSearch.getFamilyId());
		
		retreatSearch.setYear(retreat.getYear());
		retreatSearch.setSeason(retreat.getSeason());
		retreatSearch.setRetreatName(retreat.getRetreatName());
		
		List<FamilyMember> fmList = retreatService.getFamilyMemberList(retreatSearch);
		
		String year = DateHelper.getDate().substring(0, 4);
		
		ModelAndView mav = new ModelAndView("retreat/admin/family_member");

		mav.addObject("fmList", fmList);
		mav.addObject("retreatSearch", retreatSearch);
		mav.addObject("family", family);
		mav.addObject("year", year);
		
		return mav;
	}
	

	
	/**
	 * 수련회 가족편성표
	 * @return
	 */
	@RequestMapping(value = "/retreat/list", method = RequestMethod.GET)
	public ModelAndView retreat_list(RetreatSearch retreatSearch) {

//		List<FamilyMember> fmList = retreatService.getFamilyMemberList(retreatSearch);
		
		String thisYear = DateHelper.getYear();
		
		// 이번년도 부터 이전년도의 수련회 존재하는 모든 년도 구하기
		List yearList = new ArrayList();
		int yearInt = StringHelper.parseIntAndArrayRange(thisYear);
		for(int i = yearInt + 1; i >= 2020; i--) {
			yearList.add(i);
			if(yearList.size() == 10) {
				break;
			}
		}
		
		ModelAndView mav = new ModelAndView("retreat/list");
		
		mav.addObject("retreatSearch", retreatSearch);
//		mav.addObject("fmList", fmList);
		mav.addObject("yearList", yearList);
		mav.addObject("thisYear", thisYear);
		mav.addObject("season", ERetreatSeason.values());
		
		return mav;
	}
	
	
	
}