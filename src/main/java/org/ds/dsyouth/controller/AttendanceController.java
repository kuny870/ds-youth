package org.ds.dsyouth.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.ds.dsyouth.common.Constants;
import org.ds.dsyouth.excel.GenericExcelView;
import org.ds.dsyouth.exception.ExcelDownFail;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Depart;
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
	 */
	@RequestMapping(value = "/attendance/list", method = RequestMethod.GET)
	public ModelAndView attendance_list(
			@ModelAttribute AttendanceSearch attendanceSearch) {

		// 이번년도 구하기
		String year = DateHelper.getYear();
				
		List<Attendance> attendanceList = attendanceService.getMemberListByAtt(attendanceSearch);
		List<Team> teamList = adminService.getTeamList();
		List<Depart> departList = adminService.getDepartList();
		List<YearSeason> seasonList = adminService.getYearSeasonList(year);
		
		// 선택한 달의 일요일 날짜 구하기
		List sunday = DateHelper.getDayOfWeekByMonth(attendanceSearch.getYear() + String.format("%02d", attendanceSearch.getMonth()));
				
		// 이번년도 부터 이전년도의 출석부 존재하는 모든 년도 구하기
		List yearList = new ArrayList();
		int yearInt = StringHelper.parseIntAndArrayRange(year);
		for(int i = 2019; i <= yearInt; i++) {
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
		
		
		return mav;
	}
	
	
	/*
     * 출석부 엑셀 다운로드
     */
    @RequestMapping(value = "/attendance/excelDownload", method = RequestMethod.GET)
    public GenericExcelView attendance_excel_download(
    		AttendanceSearch attendanceSearch,
    		Map<String, Object> model,
    		HttpServletResponse response) throws IOException {
        
    	List<Depart> departList = adminService.getDepartList();
    	List<Team> teamList = adminService.getTeamList();
		
    	List<List<Attendance>> attendanceExcel = new ArrayList<List<Attendance>>();
    	
    	for(int i=0; i<teamList.size(); i++) {
    		attendanceSearch.setTeamId(teamList.get(i).getId().toString());
    		List<Attendance> attendanceList = attendanceService.getMemberListByAtt(attendanceSearch);
    		// 순편성 전일 경우 엑셀 다운로드 막기 ( TODO 향후 기능개선 필요 )
    		if(attendanceList.size() == 0) {
    			PrintWriter out = response.getWriter(); 
    			out.println("<script>alert('Excel DownLoad Fail'); history.back();</script>"); 
    			out.flush(); 
    			out.close();
    		}
    		attendanceExcel.add(attendanceList);
    	}
		
        model.put("attendanceExcel", attendanceExcel);
        model.put("departList", departList);
        model.put("teamList", teamList);
        model.put("attendanceSearch", attendanceSearch);

        return new GenericExcelView();

    }
	
	
}
