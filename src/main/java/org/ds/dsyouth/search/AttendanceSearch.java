package org.ds.dsyouth.search;

import org.ds.dsyouth.utils.DateHelper;

public class AttendanceSearch {
	
	private String team;
	private String year;
	private String month;

	public AttendanceSearch() {
		this.team = "기쁨";
		this.year = DateHelper.getYear();
		this.month = DateHelper.getMonth();
	}

	public String getYear() {
		return DateHelper.getYear();
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

	public String getTeam() {
		if("".equals(this.team) || "기타".equals(this.team)) {
			this.team = "기쁨";
		}
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
}
