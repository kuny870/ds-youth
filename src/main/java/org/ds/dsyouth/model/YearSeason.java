package org.ds.dsyouth.model;

public class YearSeason {

	private Integer id;
	private String year;
	private String season;
	private String seasonFlag;
	private String delYn;
	

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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
}
