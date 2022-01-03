package org.ds.dsyouth.search;

import org.ds.dsyouth.utils.DateHelper;
import org.ds.dsyouth.utils.StringHelper;

public class MemberSearch {
	
//	public static final int BOARDSIZE = 30; // 글 게시판에 한 페이지 당 보이게 될 게시글 수는 30개로 픽스

	private int pageNo; // 현재 페이지 번호
	private int startRow; // 화면에 보이게 될 게시글이 디비에서 몇번째인지
	private String teamId; // 팀 검색 조건
	private String groupId; // 순 검색 조건
	private String memStateId; // 멤버 상태별 검색 조건
	private String nameKW; // 이름 검색 조건
	private int boardSize; // 한 화면에 보이게 될 게시글 개수
	private String startDate;
	private String endDate;
	private String year;
	private int month;
	private String memberType;
	
	public MemberSearch() {
		this.setPageNo(1);
		this.setTeamId("");
		this.setGroupId("");
		this.setMemStateId("");
		this.setNameKW("");
		this.boardSize = 10;
		this.year = DateHelper.getYear();
		this.month = StringHelper.parseInt(DateHelper.getMonth());
		this.memberType = "removeMember";
		
		setStartRow(pageNo);
	}
	
	public String getMemStateId() {
		return memStateId;
	}

	public void setMemStateId(String memStateId) {
		this.memStateId = memStateId;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
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

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
		setStartRow(pageNo);
	}

	public int getStartRow() {
		return startRow;
	}
	
	public void setStartRow(int pageNo) { // 디비에서 가져올 게시물의 시작 위치, boardSize가 정해질 때 자동으로 정해진다.
		this.startRow = (pageNo-1) * boardSize;
	}
	
	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getNameKW() {
		return nameKW;
	}

	public void setNameKW(String nameKW) {
		this.nameKW = nameKW;
	}

	public int getBoardSize() {
		return boardSize;
	}
	
	public void setBoardSize(int boardSize) {
		this.boardSize = boardSize;
		setStartRow(boardSize); // boardSize가 정해질 때 StartRow도 정해진다.
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate + " 00:00:00";
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate + " 23:59:59";
	}

}
