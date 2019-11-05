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
	 * 출석 적용
	 */
	@Override
	public boolean modifyAttendance(Attendance attendance) {
		return attendanceMapper.updateAttendance(attendance);
	}

}
