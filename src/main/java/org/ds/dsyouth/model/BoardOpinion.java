package org.ds.dsyouth.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BoardOpinion {

	private Integer id;
	private String content;
	private Date regDate;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.KOREA);
	    return sdf.format(regDate);
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
}
