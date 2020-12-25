package org.ds.dsyouth.mapper;

import java.util.List;

import org.ds.dsyouth.model.Depart;
import org.ds.dsyouth.model.Group;
import org.ds.dsyouth.model.MemberState;
import org.ds.dsyouth.model.SamePeriod;
import org.ds.dsyouth.model.Team;
import org.ds.dsyouth.model.YearSeason;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

	// 부서
	boolean insertDepart(Depart depart);
	boolean updateDepart(Depart depart);
	boolean deleteDepart(Depart depart);
	boolean deleteDepartId(Depart depart);	// 해당 부서의 멤버가 모두 삭제 됨
	Depart selectDepart(Depart depart);
	List<Depart> selectDepartList();
	List<Depart> selectDepartListByJoin();	// 회원가입시 기타 부서 포함해서 불러오기
	Depart selectDepartByDName(Depart depart);	// 같은 부서 있는지 체크
	
	// 팀
	boolean insertTeam(Team team);
	boolean updateTeam(Team team);
	boolean deleteTeam(Team team);
	boolean deleteTeamId(Team team);	// 해당 팀의 멤버가 모두 삭제 됨
	Team selectTeam(Team team);
	Team selectTeamById(String teamId);
	List<Team> selectTeamList();
	List<Team> selectTeamByDepart(String departId);	// 팀리스트 불러오기 (by 부서)
	Team selectTeamByShortTeam(Team team);	// 같은 팀이 있는지 체크
	
	// 순명
	boolean insertGroup(Group group);
	boolean updateGroup(Group group);
	boolean deleteGroup(Group group);
	Group selectGroup(int id);
	List<Group> selectGroupList(Group group);
	List<Group> selectGroupListByTeam(Group group); // 순 리스트 불러오기 (by 팀)
	boolean deleteGroupId(Group group);	// 순명 삭제에 따른 멤버 상태 초기화
	
	// 동기명
	boolean insertSamePeriod(SamePeriod samePeriod);
	List<SamePeriod> selectSamePeriodList();
	boolean deleteSamePeriod(SamePeriod samePeriod);
	boolean deleteSamePeriodId(SamePeriod samePeriod); // 동기 삭제에 따른 멤버 상태 초기화
	SamePeriod selectSamePeriodByBirthYear(SamePeriod samePeriod);	// 동일한 동기 있는지 체크
	
	List<MemberState> selectMemberStateList();
	
	List<YearSeason> selectYearSeasonList(String thisYear);
}
