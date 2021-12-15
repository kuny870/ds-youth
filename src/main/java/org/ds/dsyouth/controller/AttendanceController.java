package org.ds.dsyouth.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.controller.rest.common.ResponseCode;
import org.ds.dsyouth.controller.rest.common.RestResponse;
import org.ds.dsyouth.excel.GenericExcelViewAttendance;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.YearSeason;
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
	
	
	/**
	 * 출석 관리 리스트
	 * @param attSearch
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = {"/attendance/list", "/dsyouth/attendance/list"}, method = RequestMethod.GET)
	public ModelAndView attendance_list(
			HttpServletRequest request,
			@ModelAttribute AttendanceSearch attendanceSearch) throws UnsupportedEncodingException {

		String nameKW = java.net.URLDecoder.decode(attendanceSearch.getNameKW(), "UTF-8");
		attendanceSearch.setNameKW(nameKW);
		
		// 이번년도 구하기
		String year = DateHelper.getYear();
				
		List<Attendance> attendanceList = attendanceService.getMemberListByAtt(attendanceSearch);
		List<Team> teamList = adminService.getTeamList();
		List<Depart> departList = adminService.getDepartList();
		List<YearSeason> seasonList = adminService.getYearSeasonList(year);
		
		// 선택한 달의 일요일 날짜 구하기
		List lastSunday = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + String.format("%02d", (attendanceSearch.getMonth())-1));
		List sunday = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + String.format("%02d", attendanceSearch.getMonth()));
				
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
		mav.addObject("seasonList", seasonList);
		mav.addObject("attendanceSearch", attendanceSearch);
		mav.addObject("yearList", yearList);
		mav.addObject("year", year);
		mav.addObject("sunday", sunday);
		mav.addObject("lastSunday", lastSunday);
		
		return mav;
	}
	
	
	/*
     * 출석부 엑셀 다운로드
     */
    @RequestMapping(value = "/attendance/excelDownload", method = RequestMethod.GET)
    public GenericExcelViewAttendance attendance_excel_download(
    		AttendanceSearch attendanceSearch,
    		Map<String, Object> model,
    		HttpServletResponse response) throws IOException {
        
    	String season = java.net.URLDecoder.decode(attendanceSearch.getSeason(), "UTF-8");
    	attendanceSearch.setSeason(season);
    	
    	List<Depart> departList = adminService.getDepartList();
    	List<Team> teamList = adminService.getTeamList();
		
    	List<List<Attendance>> attendanceExcel = new ArrayList<List<Attendance>>();
    	
    	for(int i=0; i<teamList.size(); i++) {
    		attendanceSearch.setTeamId(teamList.get(i).getId().toString());
    		List<Attendance> attendanceList = attendanceService.getMemberListByAttForExcel(attendanceSearch);
    		// 순편성 전일 경우 엑셀 다운로드 막기 ( TODO 향후 기능개선 필요 )
//    		if(attendanceList.size() == 0) {
//    			PrintWriter out = response.getWriter(); 
//    			out.println("<script>alert('Excel DownLoad Fail'); history.back();</script>"); 
//    			out.flush(); 
//    			out.close();
//    		}
    		attendanceExcel.add(attendanceList);
    	}
		
        model.put("attendanceExcel", attendanceExcel);
        model.put("departList", departList);
        model.put("teamList", teamList);
        model.put("attendanceSearch", attendanceSearch);

        return new GenericExcelViewAttendance();

    }
    
    
}
