package org.ds.dsyouth.service.impl;

import java.util.List;

import org.ds.dsyouth.mapper.AttendanceMapper;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.search.AttendanceSearch;
import org.ds.dsyouth.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	public static final int PAGESIZE = 10; // 게시판 하단에 보이게 될 페이지 개수 ==> (1 2 3 다음) 이런식으로, 글 게시판이든 사진 게시판이든 똑같이 10개로 고정
	
	@Autowired
	private AttendanceMapper attendanceMapper;
	

	/**
	 * 팀별 출석부 명단 불러오기
	 */
	@Override
	public List<Attendance> getMemberListByAtt(AttendanceSearch attSearch) {
		return attendanceMapper.selectAttendance(attSearch);
	}

	
	/**
	 * 출석부 체크
	 */
	@Override
	public boolean modifyAttendanceCheck(Attendance attendance) {
		return attendanceMapper.updateAttendanceCheck(attendance);
	}
	
	
	/**
	 * 출석부 수정 - group
	 */
	@Override
	public boolean modifyAttendanceGroup(Attendance attendance) {
		
		boolean result = false;
		
		if("상반기".equals(attendance.getSeason())) {
			for(Integer i = 1; i < 7; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroup(attendance);
			}
		}else if("하반기".equals(attendance.getSeason())) {
			for(Integer i = 7; i < 13; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroup(attendance);
			}
		}
		
		return result;
	}
	
	
	/**
	 * 출석부 수정 - group_grade
	 */
	@Override
	public boolean modifyAttendanceGroupGrade(Attendance attendance) {
		
		boolean result = false;
		
		// thisMonth : 현재달이 상반기인 경우
		if("상반기".equals(attendance.getSeason())) {
			for(Integer i = 1; i < 7; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroupGrade(attendance);
			}
		// thisMonth : 현재달이 하반기인 경우	
		}else {
			for(Integer i = 7; i < 13; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroupGrade(attendance);
			}
		}
		
		return result;
	}

}
