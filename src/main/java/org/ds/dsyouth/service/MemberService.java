package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.page.Paging;
import org.ds.dsyouth.search.MemberSearch;

public interface MemberService {

	boolean registMember(Member member);
	boolean modifyMember(Member member);
	boolean removeMember(Member member);
	
	Member getMember(Member member);	// id 값으로 member 객체 불러오기
	List<Member> getMemberList();	// 전체 멤버 불러오기
	List<Member> getMemberList(String team);	// 해당 팀 멤버만 불러오기
	Paging<Member> getMemberList(MemberSearch memberSearch);	// 팀별 정보 불러오기
	List<Member> getMemberListBySamePeriod();	// 전체 멤버 불러오기 by 동기
	List<Member> getMemberListBySamePeriodPer(String sId);	// 멤버 불러오기 by 동기별
	
	int getGroupCnt(int id);	// 그룹의 cnt 구하기
	int getSamePeriodCnt(int id);	// 동기의 cnt 구하기
	
}
