package org.ds.dsyouth.model;

public class Family {

	private Integer id;
	private String retreatId;
	private String famName;
	private int cnt;
	private String delYn;
	
	private Retreat retreat;
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRetreatId() {
		return retreatId;
	}
	public void setRetreatId(String retreatId) {
		this.retreatId = retreatId;
	}
	public String getFamName() {
		return famName;
	}
	public void setFamName(String famName) {
		this.famName = famName;
	}
	public Retreat getRetreat() {
		return retreat;
	}
	public void setRetreat(Retreat retreat) {
		this.retreat = retreat;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
}
