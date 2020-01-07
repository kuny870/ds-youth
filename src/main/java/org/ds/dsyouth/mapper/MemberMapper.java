package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.Member;
import org.ds.dsyouth.search.MemberSearch;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberMapper {

	boolean insertMember(Member member);
	boolean updateMember(Member member);
	boolean deleteMember(Member member);
	Member selectMember(Member member);
	List<Member> selectMemberListByGroupGrade(Group group);	// 순장 data를 가지고 있는 memberList 값 가져오기
	List<Member> selectMemberListBySamePeriod(String sId);	// 전체 멤버 불러오기 by 동기
	List<Member> selectMemberListBySamePeriodPer(String sId);	// 멤버 불러오기 by 동기별
	List<Member> selectMemberListBySearch(MemberSearch memberSearch);	// 검색어에 해당하는 멤버 불러오기
	List<Member> selectMemberListByGroup(Group group);	// group 값으로 팀 멤버 불러오기
	
	int selectGroupCnt(Group group);		// 그룹의 cnt 구하기
	int selectSamePeriodCnt(int id);	// 동기의 cnt 구하기
	
	int selectMemberCount(MemberSearch memberSearch);	// 멤버 인원수 구하기
	
}
