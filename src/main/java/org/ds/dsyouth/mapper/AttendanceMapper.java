package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceMapper {

	List<Attendance> selectAttendance(AttendanceSearch attendanceSearch);	// 팀별 출석명단 불러오기
	boolean updateAttendance(Attendance attendance);
	boolean insertAttendance(Attendance attendance);
	
}
