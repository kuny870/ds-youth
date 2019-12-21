package org.ds.dsyouth.rest;

import javax.servlet.http.HttpServletRequest;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.rest.common.ResponseCode;
import org.ds.dsyouth.rest.common.RestResponse;
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
	 * 출석 적용
	 * @param aId
	 * @param arr
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/attDetail/regist", method = RequestMethod.POST, produces = "application/json")
	public RestResponse attDetail_regist(
			String year,
			String[] aId,
			String[] firstWeek,
			String[] secondWeek,
			String[] thirdWeek,
			String[] fourthWeek,
			String[] fifthWeek,
			HttpServletRequest request) {

		RestResponse response = new RestResponse();
		
		try {
			
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
				attendanceService.modifyAttendance(att);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			response.setResCode(ResponseCode.UNKOWN);
		}
		
		return response;
	}
	
}