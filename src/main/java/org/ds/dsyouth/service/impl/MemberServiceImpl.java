package org.ds.dsyouth.service.impl;

import java.util.List;

import org.ds.dsyouth.mapper.AttendanceMapper;
import org.ds.dsyouth.mapper.MemberMapper;
import org.ds.dsyouth.model.Attendance;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.page.Paging;
import org.ds.dsyouth.search.MemberSearch;
import org.ds.dsyouth.service.MemberService;
import org.ds.dsyouth.utils.DateHelper;
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
	public boolean registMember(Member member) {
		
		boolean result = false;
		
		result = memberMapper.insertMember(member);
		
		if(member.getId() != 0) {
			String year = DateHelper.getDate().substring(0, 4);
			Attendance att = new Attendance();
			
			for(int i = 1; i < 13; i++) {
				att.setMemberId(member.getId());
				att.setYear(Integer.parseInt(year));
				att.setMonth(i);
				att.setAttYn("Y");
				if(member.getTeamId() == 4 || member.getTeamId() == 8) {
					attendanceMapper.insertAttendance(att);
					att.setAttYn("N");
					attendanceMapper.insertAttendance(att);
				}else {
					attendanceMapper.insertAttendance(att);
				}
			}
		}
		
		return result;
	}

	
	/**
	 * 멤버 기본정보 수정
	 */
	@Override
	public boolean modifyMember(Member member) {
		return memberMapper.updateMember(member);
	}


	/**
	 * 멤버 삭제
	 */
	@Override
	public boolean removeMember(Member member) {
		return memberMapper.deleteMember(member);
	}


	/**
	 * 멤버 불러오기
	 */
	@Override
	public Member getMember(Member member) {
		return memberMapper.selectMember(member);
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
	public List<Member> getMemberList(String team) {
		return memberMapper.selectMemberListByTeam(team);
	}

	/**
	 * 그룹의 cnt 구하기
	 */
	@Override
	public int getGroupCnt(int id) {
		return memberMapper.selectGroupCnt(id);
	}


	/**
	 * 전체 멤버 불러오기
	 */
	@Override
	public List<Member> getMemberList() {
		return memberMapper.selectMemberList();
	}
	
	/**
	 * 전체 멤버 불러오기 by 동기
	 */
	@Override
	public List<Member> getMemberListBySamePeriod() {
		return memberMapper.selectMemberListBySamePeriod();
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

}
