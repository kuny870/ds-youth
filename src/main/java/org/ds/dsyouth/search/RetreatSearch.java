package org.ds.dsyouth.search;

public class RetreatSearch {
	
	private String year;
	private String season;
	private String familyId;
	private String familyName;
	private String name;

	public RetreatSearch() {
		this.year = "전체";
		this.season = "전체";
		this.familyId = "전체";
		this.familyName = "";
		this.name = "";
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

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
