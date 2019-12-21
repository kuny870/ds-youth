package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Family;
import org.ds.dsyouth.model.FamilyMember;
import org.ds.dsyouth.model.Retreat;
import org.ds.dsyouth.search.RetreatSearch;
import org.springframework.stereotype.Repository;

@Repository
public interface RetreatMapper {

	boolean insertRetreat(Retreat retreat);
	boolean deleteRetreat(Retreat retreat);
	
	boolean insertFamily(Family family);
	boolean deleteFamily(Family family);
	
	boolean insertFamilyMember(FamilyMember famMember);
	
	List<Retreat> selectRetreatList();
	List<Family> selectFamilyList(Retreat retreat);
	List<FamilyMember> selectFamilyMemberList(RetreatSearch retreatSearch);
	
	
}
