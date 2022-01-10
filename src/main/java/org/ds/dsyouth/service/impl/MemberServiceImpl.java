package org.ds.dsyouth.service.impl;

import java.util.List;
import java.util.Map;

import org.ds.dsyouth.mapper.AttendanceMapper;
import org.ds.dsyouth.mapper.MemberMapper;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.page.Paging;
import org.ds.dsyouth.search.MemberSearch;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	public static final int PAGESIZE = 10; // 게시판 하단에 보이게 될 페이지 개수 ==> (1 2 3 다음) 이런식으로, 글 게시판이든 사진 게시판이든 똑같이 10개로 고정
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private AttendanceMapper attendanceMapper;


	/**
	 * 멤버 등록 & 출석부 등록
	 */
	@Override
	public String registMember(Member member) {
		
		boolean result = false;
		
		result = memberMapper.insertMember(member);
		
		if(result) {
			String year = DateHelper.getYear();
			String month = DateHelper.getMonth();
			Attendance att = new Attendance();
			
			for(Integer i = 1; i < 13; i++) {
				att.setMemberId(member.getId().toString());
				att.setYear(year);
				att.setMonth(i.toString());
				att.setMemState(member.getMemState());
				
				att.setAttYn("Y");
				attendanceMapper.insertAttendance(att);	// 예배 출석부 추가
				
				att.setAttYn("N");
				attendanceMapper.insertAttendance(att);	// 2부 모임 출석부 추가
			}
			
			// 만약 12월에 멤버를 등록하면 다음년도 출석부에도 추가해 준다.
			if("12".equals(month)) {
				Integer newYear = StringHelper.parseInt(year)+1;
				for(Integer i = 1; i < 13; i++) {
					att.setMemberId(member.getId().toString());
					att.setYear(newYear.toString());
					att.setMonth(i.toString());
					
					att.setAttYn("Y");
					attendanceMapper.insertAttendance(att);
					att.setAttYn("N");
					attendanceMapper.insertAttendance(att);
				}
			}
		}else {
			return "";
		}
		
		return member.getId().toString();
	}

	
	/**
	 * 멤버 기본정보 수정
	 */
	@Override
	public boolean modifyMember(Member member) {
		
		boolean result = false;
		
		// 기본정보(상태값) 변경 전
		Member m_before = memberMapper.selectMember(member);
		
		// 기본정보 변경
		result = memberMapper.updateMember(member);
		
		// 기본정보(상태값) 변경 후
		Member m_after = memberMapper.selectMember(member);
		
		// 졸업 or 수료시 날짜 입력
		if("5".equals(m_after.getMemState()) || "6".equals(m_after.getMemState())) {
			memberMapper.updateMemberStateDate(member);
		}
		
		// 멤버 상태값 변경 전, 후 값이 다를 경우 출석부에도 변경
		if(! m_before.getMemState().equals(m_after.getMemState())) {
			
			Attendance att = new Attendance();
			att.setMemberId(m_after.getId().toString());
			att.setMemState(m_after.getMemState());
			att.setYear(DateHelper.getYear());
			
			Integer currnetMonth = Integer.parseInt(DateHelper.getMonth());
			
			for(Integer i = currnetMonth; i < 13; i++) {
				att.setMonth(i.toString());
				attendanceMapper.updateAttendanceMemState(att);
			}
		}
		
		return result;
	}
	
	
	/**
	 * 멤버 메모 수정
	 */
	@Override
	public boolean modifyMemberMemo(Member member, String userAuthId) {
		
		Member m_before = memberMapper.selectMember(member);
		
		if( ! member.getMemo().equals( m_before.getMemo() ) && ! userAuthId.equals("2") ) {
			member.setMemoFlag("1");
		}else {
			member.setMemoFlag("0");
		}
		
		return memberMapper.updateMemberMemo(member);
	}
	
	/**
	 * 멤버 메모Flag 수정
	 */
	@Override
	public boolean modifyMemberMemoFlag(Member member) {
		return memberMapper.updateMemberMemoFlag(member);
	}


	/**
	 * 멤버 삭제
	 */
	@Override
	public boolean removeMember(Member member) {
		return memberMapper.deleteMember(member);
	}


	/**
	 * 멤버 불러오기 by id
	 */
	@Override
	public Member getMember(Member member) {
		return memberMapper.selectMember(member);
	}
	
	
	/**
	 * 멤버 불러오기 by name
	 */
	@Override
	public List<Member> getMemberByName(Member member) {
		return memberMapper.selectMemberByName(member);
	}
	


	/**
	 * 검색어에 해당하는 멤버 리스트 불러오기
	 */
	@Override
	public Paging<Member> getMemberList(MemberSearch memberSearch) {
		
		int memberCnt = memberMapper.selectMemberCount(memberSearch);
		List<Member> memberList = memberMapper.selectMemberListBySearch(memberSearch);
		
		Paging<Member> paging = new Paging<Member>(PAGESIZE, memberSearch.getBoardSize(), memberSearch.getPageNo(), memberCnt, memberList);
		
		return paging;
	}
	
	/**
	 * 해당 팀 멤버 리스트 불러오기
	 */
	@Override
	public List<Member> getMemberList(Group group) {
		return memberMapper.selectMemberListByGroup(group);
	}

	/**
	 * 그룹의 cnt 구하기
	 */
	@Override
	public int getGroupCnt(Group group) {
		return memberMapper.selectGroupCnt(group);
	}


	/**
	 * 순장 data를 가지고 있는 memberList 값 가져오기
	 */
	@Override
	public List<Member> getMemberListByGroupGrade(Group group) {
		return memberMapper.selectMemberListByGroupGrade(group);
	}
	
	/**
	 * 전체 멤버 불러오기 by 동기
	 */
	@Override
	public List<Member> getMemberListBySamePeriod(String sId) {
		return memberMapper.selectMemberListBySamePeriod(sId);
	}

	/**
	 * 동기별 cnt
	 */
	@Override
	public int getSamePeriodCnt(int id) {
		return memberMapper.selectSamePeriodCnt(id);
	}

	/**
	 * 동기별 멤버 불러오기
	 */
	@Override
	public List<Member> getMemberListBySamePeriodPer(String sId) {
		return memberMapper.selectMemberListBySamePeriodPer(sId);
	}
	
	/**
	 * 팀별 명단 불러오기
	 */
	@Override
	public List<Member> getMemberListForExcel(Map map) {
		return memberMapper.selectMemberListForExcel(map);
	}


	@Override
	public List<Member> getMemberListByRemove(MemberSearch memberSearch) {
		return memberMapper.selectMemberListByRemove(memberSearch);
	}
	
	/**
	 * 멤버 복구
	 */
	@Override
	public boolean restoreMember(Member member) {
		
		boolean result = memberMapper.restoreMember(member);
		
		if(!"removeMember".equals(member.getMemberType())) {
			Integer currnetMonth = Integer.parseInt(DateHelper.getMonth());
			
			Attendance att = new Attendance();
			att.setMemberId(member.getId().toString());
			att.setYear(DateHelper.getYear());
			att.setMemState("1");
			
			for(Integer i = currnetMonth; i < 13; i++) {
				att.setMonth(i.toString());
				attendanceMapper.updateAttendanceMemState(att);
			}
		}
		
		return result;
	}

}
