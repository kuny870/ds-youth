package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;

public interface AttendanceService {

	List<Attendance> getMemberListByAtt(AttendanceSearch attendanceSearch);	// 팀별 출석부 불러오기
	boolean modifyAttendanceCheck(Attendance attendance);					// 출석체크
	boolean modifyAttendanceGroup(Attendance attendance);					// 출석부 group 수정
	boolean modifyAttendanceGroupGrade(Attendance attendance);				// 출석부 group_grade 수정
	
}
