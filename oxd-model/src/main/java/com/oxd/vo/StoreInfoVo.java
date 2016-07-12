package com.oxd.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class StoreInfoVo implements Serializable {
	private int id;
	private String title;
	private int top;
	private String address;//引言（简介）
	private String prePictureUrl;//前置图url
	private String content;//内容
	private String createTime;//创建时间
	private String updateTime;//更新时间
	private String createUser;//创建用户
	private String updateUser;//创建用户
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrePictureUrl() {
		return prePictureUrl;
	}
	public void setPrePictureUrl(String prePictureUrl) {
		this.prePictureUrl = prePictureUrl;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
