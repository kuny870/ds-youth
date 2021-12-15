package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceMapper {

	List<Attendance> selectAttendance(AttendanceSearch attendanceSearch);	// 팀별 출석명단 불러오기
	List<Attendance> selectAttendanceForExcel(AttendanceSearch attendanceSearch);	// 엑셀 다운로드용 - 팀별 출석명단 불러오기
	boolean updateAttendanceCheck(Attendance attendance);					// 출석부 체크
	boolean updateAttendanceOrd(Attendance attendance);						// 출석부 순서 변경
	boolean updateAttendanceGroup(Attendance attendance);					// 출석부 group 수정
	boolean updateAttendanceGroupGrade(Attendance attendance);				// 출석부 group_grade 수정
	boolean insertAttendance(Attendance attendance);
	
	boolean updateAttendanceMemState(Attendance attendance);				// 출석부의 회원 상태값 변경
	
	Attendance selectOneAttendance(Attendance att);	// 출석부 id 하나 불러오기
	
	boolean deleteAttSayu(Attendance att);	// 사유 삭제
	
	Integer selectAttPer1(Attendance att); // 상반기 때 상반기 출석률 불러오기
	Integer selectAttPer2(Attendance att); // 하반기 때 상반기 출석률 불러오기
	Integer selectAttPer3(Attendance att); // 하반기 때 하반기 출석률 불러오기
	Integer selectAttPer4(Attendance att); // 년 개인 출석률 불러오기
}
