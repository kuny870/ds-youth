package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;

public interface AttendanceService {

	List<Attendance> getMemberListByAtt(AttendanceSearch attendanceSearch);	// 팀별 출석부 불러오기
	List<Attendance> getMemberListByAttForExcel(AttendanceSearch attendanceSearch);	// 팀별 출석부 불러오기
	boolean modifyAttendanceCheck(Attendance attendance);					// 출석체크
	boolean modifyAttendanceOrd(Attendance attendance);						// 출석 순서 변경
	boolean modifyAttendanceGroup(Attendance attendance);					// 출석부 group 수정
	boolean modifyAttendanceGroupGrade(Attendance attendance);				// 출석부 group_grade 수정
	
	Attendance getOneAttendance(Attendance att);	// 출석부 id 하나 불러오기
	
	boolean	removeAttSayu(Attendance att);	// 사유 삭제
	
	Integer getAttPer1(Attendance att);	// 상반기 때 상반기 출석률 불러오기
	Integer getAttPer2(Attendance att);	// 하반기 때 상반기 출석률 불러오기
	Integer getAttPer3(Attendance att);	// 하반기 때 하반기 출석률 불러오기
	Integer getAttPer4(Attendance att);	// 년 출석률 불러오기
	
}
