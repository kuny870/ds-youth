package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceMapper {

	List<Attendance> selectAttendance(AttendanceSearch attendanceSearch);	// 팀별 출석명단 불러오기
	boolean updateAttendanceCheck(Attendance attendance);					// 출석부 체크
	boolean updateAttendanceGroup(Attendance attendance);					// 출석부 group 수정
	boolean updateAttendanceGroupGrade(Attendance attendance);				// 출석부 group_grade 수정
	boolean insertAttendance(Attendance attendance);
	
	boolean updateAttendanceMemState(Attendance attendance);				// 출석부의 회원 상태값 변경
	
}
