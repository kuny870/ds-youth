package org.ds.dsyouth.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.search.AttendanceSearch;
import org.ds.dsyouth.search.type.SMonthSearchType;
import org.ds.dsyouth.service.AdminService;
import org.ds.dsyouth.service.AttendanceService;
import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({Constants.SESSION_USER})
public class AttendanceController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AttendanceService attendanceService;
	
	
	
	@RequestMapping(value = "/attendance/regist", method = RequestMethod.GET)
	public ModelAndView attendance_regist(Locale locale) {

		ModelAndView mav = new ModelAndView("attendance/regist");
		
		return mav;
	}
	
	
	
	/**
	 * 출석 관리 리스트
	 * @param attSearch
	 * @return
	 */
	@RequestMapping(value = "/attendance/list", method = RequestMethod.GET)
	public ModelAndView attendance_list(
			@ModelAttribute AttendanceSearch attSearch) {

		List<Attendance> attendanceList = attendanceService.getMemberListByAtt(attSearch);
		List<Team> teamList = adminService.getTeamList();
		List<Depart> departList = adminService.getDepartList();
		
		// 선택한 달의 일요일 날짜 구하기
		List sunday = DateHelper.getDayOfWeekByMonth(attSearch.getYear() + String.format("%02d", Integer.parseInt(attSearch.getMonth())));
		// 이번년도 구하기
		String year = DateHelper.getDate().substring(0, 4);
		
		// 이번년도 부터 이전년도의 출석부 존재하는 모든 년도 구하기
		List yearList = new ArrayList();
		int yearInt = StringHelper.parseIntAndArrayRange(year);
		for(int i = yearInt; i >= 2019; i--) {
			yearList.add(i);
			if(yearList.size() == 5) {
				break;
			}
		}
		
		ModelAndView mav = new ModelAndView("attendance/list");
		
		mav.addObject("attendanceList", attendanceList);
		
		mav.addObject("departList", departList);
		mav.addObject("teamList", teamList);
		mav.addObject("SMonthSearchType", SMonthSearchType.values());
		mav.addObject("attSearch", attSearch);
		mav.addObject("yearList", yearList);
		mav.addObject("year", year);
		mav.addObject("sunday", sunday);
		
		
		return mav;
	}
	
	
	
	/**
	 * 출석 관리 리스트 (검색어 포함)
	 * @param calendarSearch
	 * @return
	 */
//	@RequestMapping(value = "/attendance/list/{team}/{month}", method = RequestMethod.GET)
//	public ModelAndView attendance_list_search(AttendanceSearch attendanceSearch) {
//
//		List<Member> memberList = memberService.getMemberList();
//		
//		
//		// 선택한 달의 일요일 날짜 구하기
//		int yyyy = Integer.parseInt(attendanceSearch.getYear());
//		int mm = Integer.parseInt(attendanceSearch.getMonth());
//		String sunday = "";
//		Calendar cal = Calendar.getInstance();
//	  
//		cal.set(yyyy, mm-1, 1);
//		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//
//		for(int i=1; i<maxDate+1; i++){
//			cal.clear();
//	   
//			cal.set(yyyy, mm-1, i);
//			
//			//System.out.println(yyyy+"-"+mm+"-"+i+"_"+cal.get(cal.DAY_OF_WEEK));
//			
//			switch(cal.get(cal.DAY_OF_WEEK)){
//			case java.util.Calendar.SUNDAY:
//				if(!sunday.equals("")){
//					sunday += "|";
//				}
//
//				if(i<10){
//					sunday += attendanceSearch.getMonth()+"0"+i; 
//				}else{
//					sunday += attendanceSearch.getMonth()+""+i;
//				}
//			}
//	   
//			cal.clear();
//		}
//
//		
//		
//		ModelAndView mav = new ModelAndView("attendance/list");
//		
//		mav.addObject("memberList", memberList);
//		
//		mav.addObject("category", EUserCategoryType.values());
//		mav.addObject("team", EUserTeamType.values());
//		mav.addObject("group", EUserGroupType.values());
//		mav.addObject("SMonthSearchType", SMonthSearchType.values());
//		mav.addObject("nMonth", attendanceSearch.getMonth());
//		
//		return mav;
//	}
	
	
}
