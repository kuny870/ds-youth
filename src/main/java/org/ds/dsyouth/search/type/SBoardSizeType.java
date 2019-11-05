package org.ds.dsyouth.search.type;

/**
 * @author next
 *
 */
public enum SBoardSizeType implements SType {
	// getName() (getVname(), getSvalue()) 
	이십개씩("20개씩", "20"),
	사십개씩("40개씩", "40"),
	육십개씩("60개씩", "60"),
	팔십개씩("80개씩", "80"),
	백개씩("100개씩", "100")
	;
	
	String vName;
	String sValue;

	SBoardSizeType(String vName, String sValue) {
		this.vName = vName;
		this.sValue = sValue;
	}
	
	public String getName() {
		return name(); // CONTENT
	}

	public String getVName() {
		return vName; // "name"
	}
	
	public String getSValue() {
		return sValue; // "content"
	}
}
