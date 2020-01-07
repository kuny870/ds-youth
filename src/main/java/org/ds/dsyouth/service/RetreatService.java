package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.exception.CreateRetreatTableException;
import org.ds.dsyouth.model.Family;
import org.ds.dsyouth.model.FamilyMember;
import org.ds.dsyouth.model.Retreat;
import org.ds.dsyouth.search.RetreatSearch;

public interface RetreatService {

	// 관리자
	Retreat getRetreat(String id);		// id 값으로 수련회 불러오기
	Family getFamily(String id);		// id 값으로 가족 불러오기
	List<Retreat> getRetreatList();		// 역대 수련회 List 불러오기
	List<Family> getFamilyList(Retreat retreat);		// 해당 수련회의 가족 List 불러오기
	boolean modifyFamilyMember(FamilyMember fm);		// 가족원 편성
	
	// 사용자
	List<FamilyMember> getFamilyMemberList(RetreatSearch retreatSearch);
	
	// 수련회 등록
	boolean registRetreat(Retreat retreat) throws CreateRetreatTableException;		// 수련회명 등록
	boolean registFamily(Family family);		// 가족 등록
	boolean registFamilyMember(FamilyMember fm);	// 가족원 등록
	
	// 수련회 삭제
	boolean removeRetreat(Retreat retreat);		// 수련회명 삭제
	boolean removeFamily(Family family);		// 가족 삭제
}
