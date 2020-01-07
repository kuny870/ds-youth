package org.ds.dsyouth.search;

import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;

public class RetreatSearch {
	
	private String year;		// 수련회 년도
	private String thisYear;	// 현재 년
	private int thisMonth;		// 현재 월
	private String season;
	private String familyId;
	private String retreatId;
	private String retreatName;
	private String familyName;
	private String name;

	public RetreatSearch() {
		this.thisYear = DateHelper.getYear();
		this.thisMonth = StringHelper.parseInt(DateHelper.getMonth());
		this.season = "";
		this.familyId = "";
		this.retreatId = "";
		this.familyName = "";
		this.name = "";
		this.retreatName = "";
	}

	public String getThisYear() {
		return thisYear;
	}

	public void setThisYear(String thisYear) {
		this.thisYear = thisYear;
	}

	public int getThisMonth() {
		return thisMonth;
	}

	public void setThisMonth(int thisMonth) {
		this.thisMonth = thisMonth;
	}

	public String getRetreatName() {
		return retreatName;
	}

	public void setRetreatName(String retreatName) {
		this.retreatName = retreatName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

}
