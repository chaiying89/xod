package com.oxd.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table
@Entity
public class NewsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30)
	private String title;	//标题
	@Column(length = 255)
	private String introduction;//引言（简介）
	@Column(length = 255)
	private String prePictureUrl;//前置图url
	@ManyToOne
	private MenuModel type;//类型
	@Lob  
	@Basic(fetch = FetchType.LAZY)
	private String content;//内容
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	@ManyToOne
	private UserModel createUser;//创建用户
	@ManyToOne
	private UserModel updateUser;//更新用户
	
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
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPrePictureUrl() {
		return prePictureUrl;
	}
	public void setPrePictureUrl(String prePictureUrl) {
		this.prePictureUrl = prePictureUrl;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public UserModel getCreateUser() {
		return createUser;
	}
	public void setCreateUser(UserModel createUser) {
		this.createUser = createUser;
	}
	public UserModel getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(UserModel updateUser) {
		this.updateUser = updateUser;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MenuModel getType() {
		return type;
	}
	public void setType(MenuModel type) {
		this.type = type;
	}
	
}
