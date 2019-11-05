package org.ds.dsyouth.service.impl;

import java.util.List;

import org.ds.dsyouth.exception.BirthYearDuplicatedException;
import org.ds.dsyouth.exception.DepartDuplicatedException;
import org.ds.dsyouth.exception.TeamDuplicatedException;
import org.ds.dsyouth.mapper.AdminMapper;
import org.ds.dsyouth.mapper.AuthMapper;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

	public static final int PAGESIZE = 10; // 게시판 하단에 보이게 될 페이지 개수 ==> (1 2 3 다음) 이런식으로, 글 게시판이든 사진 게시판이든 똑같이 10개로 고정
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private AuthMapper authMapper;


	/**
	 * 부서 등록
	 * @throws DepartDuplicatedException 
	 */
	@Override
	public boolean registDepart(Depart depart) throws DepartDuplicatedException {
		Depart departConfirm = adminMapper.selectDepartByDName(depart);
		if(departConfirm != null) {
			throw new DepartDuplicatedException();
		}
		return adminMapper.insertDepart(depart);
	}

	/**
	 * 부서 수정
	 */
	@Override
	public boolean modifyDepart(Depart depart) {
		return adminMapper.updateDepart(depart);
	}

	/**
	 * 부서 삭제
	 */
	@Override
	public boolean removeDepart(Depart depart) {
		boolean result = adminMapper.deleteDepart(depart);
		if(result) {
			adminMapper.deleteDepartId(depart);		// 해당 부서의 멤버가 모두 삭제 됨
		}
		return result;
	}


	/**
	 * 부서 리스트 불러오기
	 */
	@Override
	public List<Depart> getDepartList() {
		return adminMapper.selectDepartList();
	}
	
	
	/**
	 * 회원가입시 기타 부서 포함해서 불러오기
	 */
	@Override
	public List<Depart> getDepartListByJoin() {
		return adminMapper.selectDepartListByJoin();
	}
	
	
	/**
	 * 순명 등록
	 */
	@Override
	public boolean registGroup(Group group) {
		return adminMapper.insertGroup(group);
	}

	/**
	 * 순명 수정
	 */
	@Override
	public boolean modifyGroup(Group group) {
		return adminMapper.updateGroup(group);
	}

	/**
	 * 순명 삭제
	 */
	@Override
	public boolean removeGroup(Group group) {
		boolean result = adminMapper.deleteGroup(group);
		if(result) {
			adminMapper.deleteGroupId(group);
		}
		return result;
	}


	/**
	 * 순명 리스트 불러오기
	 */
	@Override
	public List<Group> getGroupList() {
		return adminMapper.selectGroupList();
	}
	
	
	/**
	 * 팀 등록
	 * @throws TeamDuplicatedException 
	 */
	@Override
	public boolean registTeam(Team team) throws TeamDuplicatedException {
		Team teamConfirm = adminMapper.selectTeamByShortTeam(team);
		if(teamConfirm != null) {
			throw new TeamDuplicatedException();
		}
		return adminMapper.insertTeam(team);
	}

	
	/**
	 * 팀 정보 수정
	 * @throws TeamDuplicatedException 
	 */
	@Override
	public boolean modifyTeam(Team team) throws TeamDuplicatedException {
		Team teamConfirm = adminMapper.selectTeamByShortTeam(team);
		if(teamConfirm != null) {
			throw new TeamDuplicatedException();
		}
		return adminMapper.updateTeam(team);
	}


	/**
	 * 팀 삭제
	 */
	@Override
	public boolean removeTeam(Team team) {
		boolean result = adminMapper.deleteTeam(team);
		if(result) {
			adminMapper.deleteTeamId(team);	// 해당 팀의 멤버 모두 삭제 됨
		}
		return result;
	}


	/**
	 * 팀 불러오기
	 */
	@Override
	public Team getTeam(Team team) {
		return adminMapper.selectTeam(team);
	}

	/**
	 * 팀 불러오기 by teamId
	 */
	@Override
	public Team getTeamById(int teamId) {
		return adminMapper.selectTeamById(teamId);
	}
	

	/**
	 * 팀 리스트 불러오기
	 */
	@Override
	public List<Team> getTeamList() {
		return adminMapper.selectTeamList();
	}

	/**
	 * 부서 선택시 팀 불러오기
	 */
	@Override
	public List<Team> getTeamByDepart(String departId) {
		return adminMapper.selectTeamByDepart(departId);
	}

	/**
	 * 권한 List 불러오기
	 */
	@Override
	public List<Auth> getAuthList() {
		return authMapper.selectAuthList();
	}

	/**
	 * 동기 List 불러오기
	 */
	@Override
	public List<SamePeriod> getSamePeriodList() {
		return adminMapper.selectSamePeriodList();
	}

	/**
	 * 동기 삭제
	 */
	@Override
	public boolean removeSamePeriod(SamePeriod samePeriod) {
		boolean result = adminMapper.deleteSamePeriod(samePeriod);
		if(result) {
			adminMapper.deleteSamePeriodId(samePeriod);
		}
		return result;
	}

	/**
	 * 동기 등록
	 * @throws BirthYearDuplicatedException 
	 */
	@Override
	public boolean registSamePeriod(SamePeriod samePeriod) throws BirthYearDuplicatedException {
		SamePeriod samePeriodConfirm = adminMapper.selectSamePeriodByBirthYear(samePeriod);
		if(samePeriodConfirm != null) {
			throw new BirthYearDuplicatedException();
		}
		
		return adminMapper.insertSamePeriod(samePeriod);
	}

	@Override
	public List<Group> getGroupListByTeam(String teamId) {
		return adminMapper.selectGroupListByTeam(teamId);
	}


}
