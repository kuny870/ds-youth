package org.ds.dsyouth.service.impl;

import java.util.List;

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
	 */
	@Override
	public boolean registRetreat(Retreat retreat) {
		return retreatMapper.insertRetreat(retreat);
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
	public boolean registFamilyMember(FamilyMember famMember) {
		boolean result = false;
		result = retreatMapper.insertFamilyMember(famMember);
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
		return retreatMapper.deleteFamily(family);
	}


}
