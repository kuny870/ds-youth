package org.ds.dsyouth.model;

public class Attendance {

	private Integer id;
	private String groupId;
	private String groupGrade;
	private String memState;
	private String memberId;
	private String attYn;
	private String year;
	private String month;
	private String firstWeek;
	private String secondWeek;
	private String thirdWeek;
	private String fourthWeek;
	private String fifthWeek;
	private String season;
	private Integer totalLastYear;
	
	private Member member;
	private Depart depart;
	private Team team;
	private Group group;
	

	public Integer getTotalLastYear() {
		return totalLastYear;
	}
	public void setTotalLastYear(Integer totalLastYear) {
		this.totalLastYear = totalLastYear;
	}
	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
	}
	public String getGroupGrade() {
		return groupGrade;
	}
	public void setGroupGrade(String groupGrade) {
		this.groupGrade = groupGrade;
	}
	public String getMemState() {
		return memState;
	}
	public void setMemState(String memState) {
		this.memState = memState;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
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
	private SamePeriod samePeriod;
	
	public SamePeriod getSamePeriod() {
		return samePeriod;
	}
	public void setSamePeriod(SamePeriod samePeriod) {
		this.samePeriod = samePeriod;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getAttYn() {
		return attYn;
	}
	public void setAttYn(String attYn) {
		this.attYn = attYn;
	}
	public String getFirstWeek() {
		return firstWeek;
	}
	public void setFirstWeek(String firstWeek) {
		this.firstWeek = firstWeek;
	}
	public String getSecondWeek() {
		return secondWeek;
	}
	public void setSecondWeek(String secondWeek) {
		this.secondWeek = secondWeek;
	}
	public String getThirdWeek() {
		return thirdWeek;
	}
	public void setThirdWeek(String thirdWeek) {
		this.thirdWeek = thirdWeek;
	}
	public String getFourthWeek() {
		return fourthWeek;
	}
	public void setFourthWeek(String fourthWeek) {
		this.fourthWeek = fourthWeek;
	}
	public String getFifthWeek() {
		return fifthWeek;
	}
	public void setFifthWeek(String fifthWeek) {
		this.fifthWeek = fifthWeek;
	}
	
}
