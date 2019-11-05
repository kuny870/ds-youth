package org.ds.dsyouth.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoardFree {

	private Integer id;
	private Integer regUser;
	private String content;
	private Date regDate;
	private Date modDate;
	private String delYn;
	private User user;

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRegUser() {
		return regUser;
	}
	public void setRegUser(Integer regUser) {
		this.regUser = regUser;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getModDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
	    return sdf.format(modDate);
	}
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getRegDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
	    return sdf.format(regDate);
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
