package org.ds.dsyouth.controller.rest;

import javax.servlet.http.HttpServletRequest;

import org.ds.dsyouth.controller.rest.common.ResponseCode;
import org.ds.dsyouth.controller.rest.common.RestResponse;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.service.AttendanceService;
import org.ds.dsyouth.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest")
public class AttendanceRestController {

	@Autowired
	private AttendanceService attendanceService; 

	
	/**
	 * 출석부 수정 - group
	 * @param groupStudy
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/attGroup/edit", method = RequestMethod.POST, produces = "application/json")
	public RestResponse attGroup_regist(
			String gId,
			String[] memberArr,
			String year,
			String seasonFlag,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			for (int i = 0; i < memberArr.length; i++) {
				Attendance att = new Attendance();
				att.setMemberId(memberArr[i]);
				att.setGroupId(gId);
				att.setYear(year);
				att.setSeasonFlag(seasonFlag);
				attendanceService.modifyAttendanceGroup(att);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 출석부 수정 - group_grade
	 * @param attendance
	 * @return
	 */
	@RequestMapping(value = "/attGroupGrade/edit", method = RequestMethod.POST, produces = "application/json")
	public RestResponse attGroupGrade_edit(
			Attendance attendance) {

		RestResponse response = new RestResponse();
		
		try {
			
			attendanceService.modifyAttendanceGroupGrade(attendance);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	/**
	 * 출석 적용
	 * @param aId
	 * @param arr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/attDetail/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse attDetail_regist(
			String year,
			String month,
			String[] aId,
			String[] memberId,
			String[] attYn,
			
			String[] lastFirstWeek,
			String[] lastSecondWeek,
			String[] lastThirdWeek,
			String[] lastFourthWeek,
			String[] lastFifthWeek,
			
			String[] firstWeek,
			String[] secondWeek,
			String[] thirdWeek,
			String[] fourthWeek,
			String[] fifthWeek,
			Integer[] sortAttOrd,
			String sortYN,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			// 출석부 순서 적용
			if("Y".equals(sortYN)) {
				
				for (int i = 0; i < aId.length; i++) {
					Attendance att = new Attendance();
					att.setYear(year);
					att.setMonth(month);
					att.setMemberId(memberId[i]);
					att.setAttYn(attYn[i]);
					att.setAttOrd(sortAttOrd[i]);
					attendanceService.modifyAttendanceOrd(att);
				}
				
			// 출석 적용
			}else {
				
				for (int i = 0; i < aId.length; i++) {
					Attendance att = new Attendance();
					att.setYear(year);
					att.setId(StringHelper.parseInt(aId[i]));
					att.setFirstWeek(firstWeek[i]);
					att.setSecondWeek(secondWeek[i]);
					att.setThirdWeek(thirdWeek[i]);
					att.setFourthWeek(fourthWeek[i]);
					if(fifthWeek != null) {
						att.setFifthWeek(fifthWeek[i]);
					}
					attendanceService.modifyAttendanceCheck(att);
				}
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	/**
	 * 출석 id 한개 불러오기
	 * @param aId
	 * @param arr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/att/getOneAtt", method = RequestMethod.POST, produces = "application/json")
	public RestResponse att_getOne(
			Attendance att,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			att = attendanceService.getOneAttendance(att);
			response.setData(att);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	/**
	 * 출석부 수정 - 사유 삭제
	 * @param groupStudy
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/remove/sayu", method = RequestMethod.POST, produces = "application/json")
	public RestResponse remove_sayu(
			Attendance att,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
			attendanceService.removeAttSayu(att);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
	
	
}