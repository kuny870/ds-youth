package org.ds.dsyouth.search.type;

/**
 * 모든 게시판에서 검색 유형을 관리하는 Enum 클래스
 * @author keon
 *
 */
public enum SMonthSearchType implements SType {
	// getName() (getVName(), getSValue()) 형태
	January("1", ""),
	Feburary("2", ""),
	March("3", ""),
	April("4", ""),
	May("5", ""),
	June("6", ""),
	July("7", ""),
	August("8", ""),
	September("9", ""),
	October("10", ""),
	November("11", ""),
	December("12", "")
	;
	
	String vName;
	String sValue;

	SMonthSearchType(String vName, String sValue) {
		this.vName = vName;
		this.sValue = sValue;
	}
	
	public String getName() {
		return name(); // CONTENT
	}

	public String getVName() {
		return vName; // "내용"
	}
	
	public String getSValue() {
		return sValue; // "content"
	}
}
