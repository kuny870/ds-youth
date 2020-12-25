package org.ds.dsyouth.search;

import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;

public class AttendanceSearch {
	
	private String teamId;
	private String year;
	private String lastYear;
	private Integer month;
	private String season;
	private String thisSeason;
	
	public AttendanceSearch() {
		this.teamId = "9";
		this.year = DateHelper.getYear();
		this.lastYear = DateHelper.getLastYear();
		this.month = StringHelper.parseInt(DateHelper.getMonth());
	}
	
	public String setThisSeason() {
		return thisSeason;
	}
	
	public String getThisSeason() {
		if(StringHelper.parseInt(DateHelper.getYear()) == 2020  && month > 8) {
			this.thisSeason = "코로나순";
		}else if(month < 7) {
			this.thisSeason = "상반기";
		}else {
			this.thisSeason = "하반기";
		}
		return thisSeason;
	}
	
	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getLastYear() {
		return lastYear;
	}

	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getTeamId() {
		if("".equals(this.teamId) || "0".equals(this.teamId) || "10".equals(this.teamId)) {
			this.teamId = "9";
		}
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	
}
