package org.ds.dsyouth.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Member {

	private Integer id;
	private String name;
	private String guider;
	private String dateOfBirth;
	private Integer departId;
	private Integer teamId;
	private Integer groupId;
	private String groupGrade;
	private String htel;
	private String gender;
	private Integer samePeriodId;
	private String profileImg;
	private Integer regUser;
	private Date regDate;
	private Date modDate;
	private String longAbsent;
	private String delYn;
	private Depart depart;
	private Team team;
	private Group group;
	private SamePeriod samePeriod;
	
	public String getGuider() {
		return guider;
	}
	public void setGuider(String guider) {
		this.guider = guider;
	}
	public Depart getDepart() {
		return depart;
	}
	public Team getTeam() {
		return team;
	}
	public SamePeriod getSamePeriod() {
		return samePeriod;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public void setSamePeriod(SamePeriod samePeriod) {
		this.samePeriod = samePeriod;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDepartId() {
		if("부서선택".equals(this.departId) ) {
			return 0;
		} else {
			return departId;
		}
	}
	public void setDepartId(Integer departId) {
		this.departId = departId;
	}
	public Integer getTeamId() {
		if("팀선택".equals(this.teamId) ) {
			return 0;
		} else {
			return teamId;
		}
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHtel() {
		return htel;
	}
	public void setHtel(String htel) {
		this.htel = htel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getRegDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
	    return sdf.format(regDate);
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getModDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
	    return sdf.format(modDate);
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public Integer getRegUser() {
		return regUser;
	}
	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}
	public Integer getSamePeriodId() {
		if("동기선택".equals(this.samePeriodId) ) {
			return 0;
		} else {
			return samePeriodId;
		}
	}
	public void setSamePeriodId(Integer samePeriodId) {
		this.samePeriodId = samePeriodId;
	}
	public String getGroupGrade() {
		return groupGrade;
	}
	public void setGroupGrade(String groupGrade) {
		this.groupGrade = groupGrade;
	}
	public String getLongAbsent() {
		return longAbsent;
	}
	public void setLongAbsent(String longAbsent) {
		this.longAbsent = longAbsent;
	}

}
