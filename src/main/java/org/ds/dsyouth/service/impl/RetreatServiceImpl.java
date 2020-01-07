package org.ds.dsyouth.service.impl;

import java.util.List;

import org.ds.dsyouth.exception.CreateRetreatTableException;
import org.ds.dsyouth.mapper.RetreatMapper;
import org.ds.dsyouth.model.Family;
import org.ds.dsyouth.model.FamilyMember;
import org.ds.dsyouth.model.Retreat;
import org.ds.dsyouth.search.RetreatSearch;
import org.ds.dsyouth.service.RetreatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetreatServiceImpl implements RetreatService {

	@Autowired
	private RetreatMapper retreatMapper;
	
	/**
	 * 수련회 등록
	 * @throws CreateRetreatTableException 
	 */
	@Override
	public boolean registRetreat(Retreat retreat) throws CreateRetreatTableException {

		StringBuffer sb1 = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		String s1 = new String();
		String s2 = new String();
		
		sb1.append(retreat.getYear());
		sb1.append(retreat.getSeason());
		sb1.append(retreat.getRetreatName());
		s1 = sb1.toString();
		
		List<Retreat> rList = retreatMapper.selectRetreatList();
		for(int i=0; i<rList.size(); i++) {
			sb2.append(rList.get(i).getYear());
			sb2.append(rList.get(i).getSeason());
			sb2.append(rList.get(i).getRetreatName());
			s2 = sb2.toString();
			
			if(s1.equals(s2)) {
				throw new CreateRetreatTableException();
			}
		}
		
		boolean result = false;
		result = retreatMapper.insertRetreat(retreat);
		if(result) {
			retreatMapper.createFamilyMemberTable(retreat);
		}
		return result;
	}
	
	
	/**
	 * 가족 등록
	 */
	@Override
	public boolean registFamily(Family family) {
		return retreatMapper.insertFamily(family);
	}
	
	/**
	 * 가족원 등록
	 */
	@Override
	public boolean registFamilyMember(FamilyMember fm) {
		boolean result = false;
		result = retreatMapper.insertFamilyMember(fm);
		return result;
	}

	
	/**
	 * 수련회 목록 불러오기
	 */
	@Override
	public List<Retreat> getRetreatList() {
		return retreatMapper.selectRetreatList();
	}

	/**
	 * 가족 목록 불러오기
	 */
	@Override
	public List<Family> getFamilyList(Retreat retreat) {
		return retreatMapper.selectFamilyList(retreat);
	}

	/**
	 * 가족명단 불러오기 by 검색
	 */
	@Override
	public List<FamilyMember> getFamilyMemberList(RetreatSearch retreatSearch) {
		return retreatMapper.selectFamilyMemberList(retreatSearch);
	}

	
	/**
	 * 수련회 삭제
	 */
	@Override
	public boolean removeRetreat(Retreat retreat) {
		return retreatMapper.deleteRetreat(retreat);
	}
	
	/**
	 * 가족 삭제
	 */
	@Override
	public boolean removeFamily(Family family) {
//		boolean result = false;
//		result = retreatMapper.deleteFamily(family);
//		if(result) {
//			result = retreatMapper.deleteFamilyMemberByFamily(family);
//		}
//		return result;
		return retreatMapper.deleteFamily(family);
	}


	/**
	 * 수련회 불러오기
	 */
	@Override
	public Retreat getRetreat(String id) {
		return retreatMapper.selectRetreat(id);
	}


	/**
	 * 가족원 셋팅
	 */
	@Override
	public boolean modifyFamilyMember(FamilyMember fm) {
		
		if(fm.getId() == null) {
			retreatMapper.insertFamilyMember(fm);
		}else {
			retreatMapper.updateFamilyMember(fm);
		}
		
		return false;
	}

	/**
	 * 가족 불러오기
	 */
	@Override
	public Family getFamily(String id) {
		return retreatMapper.selectFamily(id);
	}


}
