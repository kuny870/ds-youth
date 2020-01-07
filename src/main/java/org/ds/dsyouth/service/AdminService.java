package org.ds.dsyouth.service;

import java.util.List;

import org.ds.dsyouth.exception.BirthYearDuplicatedException;
import org.ds.dsyouth.exception.DepartDuplicatedException;
import org.ds.dsyouth.exception.TeamDuplicatedException;
import org.ds.dsyouth.model.Auth;
import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;

public interface AdminService {

	// 권한
	List<Auth> getAuthList();
	
	// 팀
	boolean registTeam(Team team) throws TeamDuplicatedException;
	boolean modifyTeam(Team team) throws TeamDuplicatedException;
	boolean removeTeam(Team team);
	Team getTeam(Team team);
	Team getTeamById(String teamId);
	List<Team> getTeamList();
	List<Team> getTeamByDepart(String departId);	// 팀리스트 불러오기 (by 부서)
	
	// 부서
	boolean registDepart(Depart depart) throws DepartDuplicatedException;
	boolean modifyDepart(Depart depart);
	boolean removeDepart(Depart depart);
	List<Depart> getDepartList();
	List<Depart> getDepartListByJoin();	// 회원가입 시 기타 부서 포함해서 불러오기
	
	// 순명
	boolean registGroup(Group group);
	boolean modifyGroup(Group group);
	boolean removeGroup(Group group);
	Group getGroup(int id);
	List<Group> getGroupList(Group group);
	List<Group> getGroupListByTeam(Group group);
	
	// 동기명
	boolean registSamePeriod(SamePeriod samePeriod) throws BirthYearDuplicatedException;
	List<SamePeriod> getSamePeriodList();
	boolean removeSamePeriod(SamePeriod samePeriod);
}
