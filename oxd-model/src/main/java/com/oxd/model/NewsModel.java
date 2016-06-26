package com.oxd.model;

import java.io.Serializable;
import java.util.Date;

public class NewsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private Date createTime;
	private String createUser;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}
