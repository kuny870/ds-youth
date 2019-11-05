package org.ds.dsyouth.model;

import java.util.Date;

import org.ds.dsyouth.utils.DateHelper;

public class LeaderInfo {

	private Integer id;
	private String title;
	private String originImg;
	private String replaceImg;
	private Integer regUser;
	private Date regDate;
	private String delYn;
	private User user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOriginImg() {
		return originImg;
	}
	public void setOriginImg(String originImg) {
		this.originImg = originImg;
	}
	public String getReplaceImg() {
		return replaceImg;
	}
	public void setReplaceImg(String replaceImg) {
		this.replaceImg = replaceImg;
	}
	public Integer getRegUser() {
		return regUser;
	}
	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}
	public String getRegDate() {
		return DateHelper.getDate2(regDate);
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
