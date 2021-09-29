package org.ds.dsyouth.service;

import java.util.List;
import java.util.Map;

import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.page.Paging;
import org.ds.dsyouth.search.MemberSearch;

public interface MemberService {

	String registMember(Member member);
	boolean modifyMember(Member member);
	boolean removeMember(Member member);
	
	Member getMember(Member member);			// id 값으로 member 객체 불러오기
	List<Member> getMemberListByGroupGrade(Group group);	// 순장 data를 가지고 있는 memberList 가져오기
	List<Member> getMemberList(Group group);	// group 값으로 팀 멤버 불러오기
	List<Member> getMemberListByRemove();	// 삭제된 팀 멤버 불러오기
	Paging<Member> getMemberList(MemberSearch memberSearch);	// 팀별 정보 불러오기
	List<Member> getMemberListBySamePeriod(String sId);	// 전체 멤버 불러오기 by 동기
	List<Member> getMemberListBySamePeriodPer(String sId);	// 멤버 불러오기 by 동기별
	
	int getGroupCnt(Group group);	// 그룹의 cnt 구하기
	int getSamePeriodCnt(int id);	// 동기의 cnt 구하기
	
	List<Member> getMemberListForExcel(Map year);	// 팀별 List 불러오기
	
	boolean restoreMember(Member member);	// 멤버 복구
	boolean modifyMemberMemo(Member member, String userAuthId);
	boolean modifyMemberMemoFlag(Member member);
	
}
