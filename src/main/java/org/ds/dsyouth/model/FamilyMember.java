package org.ds.dsyouth.model;

public class FamilyMember {

	private Integer id;
	private Integer memberId;
	private Integer familyId;
	private Integer retreatId;
	private Integer groupGrade;		// 0: 가족원, 1:리더, 9:가족장
	
	private Member member;
	private Family family;
	private Retreat retreat;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getFamilyId() {
		return familyId;
	}
	public void setFamilyId(Integer familyId) {
		this.familyId = familyId;
	}
	public Integer getRetreatId() {
		return retreatId;
	}
	public void setRetreatId(Integer retreatId) {
		this.retreatId = retreatId;
	}
	public Integer getGroupGrade() {
		return groupGrade;
	}
	public void setGroupGrade(Integer groupGrade) {
		this.groupGrade = groupGrade;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	public Retreat getRetreat() {
		return retreat;
	}
	public void setRetreat(Retreat retreat) {
		this.retreat = retreat;
	}
	
}
