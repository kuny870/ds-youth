package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;

public interface AttendanceService {

	List<Attendance> getMemberListByAtt(AttendanceSearch attendanceSearch);	// 팀별 출석부 불러오기
	boolean modifyAttendance(Attendance attendance);
	
}
