package org.ds.dsyouth.search;

import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;

public class AttendanceSearch {
	
	private String team;
	private String year;
	private String lastYear;
	private int month;

	public AttendanceSearch() {
		this.team = "동산";
		this.year = DateHelper.getYear();
		this.lastYear = DateHelper.getLastYear();
		this.month = StringHelper.parseInt(DateHelper.getMonth());
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getTeam() {
		if("".equals(this.team) || "기타".equals(this.team)) {
			this.team = "동산";
		}
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}
	
}
