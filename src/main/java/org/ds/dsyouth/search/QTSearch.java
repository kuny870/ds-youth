package org.ds.dsyouth.search;

import org.ds.dsyouth.utils.DateHelper;

public class QTSearch {
	
	private String year;
	private String month;
	private String day;

	public QTSearch() {
		this.year = DateHelper.getYear();
		this.month = DateHelper.getMonth();
		this.day = DateHelper.getDay();
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

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

}
