package org.ds.dsyouth.model;

public class FamilyMember {

	private Integer id;
	private String departId;
	private String teamId;
	private String groupId;
	private String memberId;
	private String familyId;
	private String retreatId;
	private String groupGrade;		// 0: 가족원, 1:리더, 9:가족장
	private String attendState;
	
	
	private Depart depart;
	private Team team;
	private Member member;
	private Group group;
	private SamePeriod samePeriod;
	private Family family;
	private Retreat retreat;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getFamilyId() {
		return familyId;
	}
	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}
	public String getRetreatId() {
		return retreatId;
	}
	public void setRetreatId(String retreatId) {
		this.retreatId = retreatId;
	}
	public String getGroupGrade() {
		return groupGrade;
	}
	public void setGroupGrade(String groupGrade) {
		this.groupGrade = groupGrade;
	}
	public String getAttendState() {
		return attendState;
	}
	public void setAttendState(String attendState) {
		this.attendState = attendState;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public SamePeriod getSamePeriod() {
		return samePeriod;
	}
	public void setSamePeriod(SamePeriod samePeriod) {
		this.samePeriod = samePeriod;
	}
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	public Retreat getRetreat() {
		return retreat;
	}
	public void setRetreat(Retreat retreat) {
		this.retreat = retreat;
	}
	
}
