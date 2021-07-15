package org.ds.dsyouth.model;

import org.ds.dsyouth.utils.DateHelper;

public class Group {

	private Integer id;
	private String year;
	private String season;
	private String seasonFlag;
	private String departId;
	private String teamId;
	private String gName;
	private Integer ord;
	private Integer cnt;
	private Integer regUser;
	private String delYn;
	
	private Depart depart;
	private Team team;
	
	public Group() {
		this.year = DateHelper.getYear();
		if(Integer.parseInt(DateHelper.getYear()) == 2020 && Integer.parseInt(DateHelper.getMonth()) > 8) {
			this.seasonFlag = "3";
		}else if( (Integer.parseInt(DateHelper.getYear()) == 2021 && Integer.parseInt(DateHelper.getMonth()) > 5) || (Integer.parseInt(DateHelper.getYear()) != 2021 && Integer.parseInt(DateHelper.getMonth()) > 6) ) {
			this.seasonFlag = "2";
		}else {
			this.seasonFlag = "1";
		}
	}
	
	public String getSeasonFlag() {
		return seasonFlag;
	}

	public void setSeasonFlag(String seasonFlag) {
		this.seasonFlag = seasonFlag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSeason() {
		return season;
	}
	public void setSeason(String season) {
		this.season = season;
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public Integer getOrd() {
		return ord;
	}
	public void setOrd(Integer ord) {
		this.ord = ord;
	}
	public Integer getCnt() {
		return cnt;
	}
	public void setCnt(Integer cnt) {
		this.cnt = cnt;
	}
	public Integer getRegUser() {
		return regUser;
	}
	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
}
