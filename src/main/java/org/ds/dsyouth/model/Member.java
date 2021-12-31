package org.ds.dsyouth.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Member {

	private Integer id;
	private String name;
	private String memo;
	private String memoFlag;
	private String guider;
	private String dateOfBirth;
	private Integer departId;
	private Integer teamId;
	private String htel;
	private String gender;
	private String memberRegDate;
	private String memberGradDate;
	private Integer samePeriodId;
	private String originProfileImg;
	private String replaceProfileImg;
	private Integer regUser;
	private Date regDate;
	private Date modDate;
	private String memState;
	private String delYn;
	private String delDate;
	private String memStateDate;
	
	private String memberType;
	
	private String registFlag;
	private String originProfileImg2;
	private String replaceProfileImg2;
	
	private Depart depart;
	private Team team;
	private Group group;
	private SamePeriod samePeriod;
	private Attendance attendance;
	private MemberState memberState;
	

	public String getRegistFlag() {
		return registFlag;
	}
	public void setRegistFlag(String registFlag) {
		this.registFlag = registFlag;
	}
	public String getOriginProfileImg2() {
		return originProfileImg2;
	}
	public void setOriginProfileImg2(String originProfileImg2) {
		this.originProfileImg2 = originProfileImg2;
	}
	public String getReplaceProfileImg2() {
		return replaceProfileImg2;
	}
	public void setReplaceProfileImg2(String replaceProfileImg2) {
		this.replaceProfileImg2 = replaceProfileImg2;
	}
	public String getMemStateDate() {
		return memStateDate;
	}
	public void setMemStateDate(String memStateDate) {
		this.memStateDate = memStateDate;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getMemoFlag() {
		return memoFlag;
	}
	public void setMemoFlag(String memoFlag) {
		this.memoFlag = memoFlag;
	}
	public MemberState getMemberState() {
		return memberState;
	}
	public void setMemberState(MemberState memberState) {
		this.memberState = memberState;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Attendance getAttendance() {
		return attendance;
	}
	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	public String getGuider() {
		return guider;
	}
	public void setGuider(String guider) {
		this.guider = guider;
	}
	public String getMemberRegDate() {
		return memberRegDate;
	}
	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}
	public String getMemberGradDate() {
		return memberGradDate;
	}
	public void setMemberGradDate(String memberGradDate) {
		this.memberGradDate = memberGradDate;
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
	public String getOriginProfileImg() {
		return originProfileImg;
	}
	public void setOriginProfileImg(String originProfileImg) {
		this.originProfileImg = originProfileImg;
	}
	public String getReplaceProfileImg() {
		return replaceProfileImg;
	}
	public void setReplaceProfileImg(String replaceProfileImg) {
		this.replaceProfileImg = replaceProfileImg;
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
	public String getMemState() {
		return memState;
	}
	public void setMemState(String memState) {
		this.memState = memState;
	}
	public String getDelDate() {
		return delDate;
	}
	public void setDelDate(String delDate) {
		this.delDate = delDate;
	}
}
