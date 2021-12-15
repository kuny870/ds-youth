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
	 * 팀별 출석부 명단 불러오기
	 */
	@Override
	public List<Attendance> getMemberListByAttForExcel(AttendanceSearch attSearch) {
		return attendanceMapper.selectAttendanceForExcel(attSearch);
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
		
		if("3".equals(attendance.getSeasonFlag())) {
			for(Integer i = 10; i < 13; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroup(attendance);
			}
		}else if("1".equals(attendance.getSeasonFlag())) {
			for(Integer i = 1; i < 7; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroup(attendance);
			}
		}else if("2".equals(attendance.getSeasonFlag()) && "2021".equals(attendance.getYear()) ) {
			for(Integer i = 6; i < 13; i++) {
				attendance.setMonth(i.toString());
				result = attendanceMapper.updateAttendanceGroup(attendance);
			}
		}else if("2".equals(attendance.getSeasonFlag())) {
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
		if("1".equals(attendance.getSeasonFlag())) {
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


	/**
	 * 출석부 한개 값 불러오기
	 */
	@Override
	public Attendance getOneAttendance(Attendance att) {
		return attendanceMapper.selectOneAttendance(att);
	}

	/**
	 * 사유 삭제
	 */
	@Override
	public boolean removeAttSayu(Attendance att) {
		return attendanceMapper.deleteAttSayu(att);
	}

	/**
	 * 출석부 순서 변경
	 */
	@Override
	public boolean modifyAttendanceOrd(Attendance att) {
		return attendanceMapper.updateAttendanceOrd(att);
	}

	/**
	 * 상반기 때 상반기 출석률 불러오기
	 */
	@Override
	public Integer getAttPer1(Attendance att) {
		return attendanceMapper.selectAttPer1(att);
	}
	
	/**
	 * 하반기 때 상반기 출석률 불러오기
	 */
	@Override
	public Integer getAttPer2(Attendance att) {
		return attendanceMapper.selectAttPer2(att);
	}
	
	/**
	 * 하반기 때 하반기 출석률 불러오기
	 */
	@Override
	public Integer getAttPer3(Attendance att) {
		return attendanceMapper.selectAttPer3(att);
	}
	
	/**
	 * 년 출석률 불러오기
	 */
	@Override
	public Integer getAttPer4(Attendance att) {
		return attendanceMapper.selectAttPer4(att);
	}

}
