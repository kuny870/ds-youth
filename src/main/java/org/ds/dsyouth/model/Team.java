package org.ds.dsyouth.model;

public class Team {

	private Integer id;
	private String departId;
	private String tShortName;
	private String tFullName;
	private String tTheme;
	private Integer regUser;
	private String delYn;
	private Depart depart;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String gettShortName() {
		return tShortName;
	}
	public void settShortName(String tShortName) {
		this.tShortName = tShortName;
	}
	public String gettFullName() {
		return tFullName;
	}
	public void settFullName(String tFullName) {
		this.tFullName = tFullName;
	}
	public String getDepartId() {
		return departId;
	}
	public void setDepartId(String departId) {
		this.departId = departId;
	}
	public Depart getDepart() {
		return depart;
	}
	public void setDepart(Depart depart) {
		this.depart = depart;
	}
	public String gettTheme() {
		return tTheme;
	}
	public void settTheme(String tTheme) {
		this.tTheme = tTheme;
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
