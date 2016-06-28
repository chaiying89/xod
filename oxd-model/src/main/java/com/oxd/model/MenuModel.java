package com.oxd.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class MenuModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30, unique = true)
	private String name; //菜单名称
	private String url; //网页菜单url地址
	@Column(columnDefinition = "enum('1','2','3')")
	private String level;
	private boolean editable;//是否可编辑
	private boolean hashChild;//能否有孩子（子集）
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_id")
	private MenuModel parent; // 父节点
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "parent", fetch = FetchType.EAGER)
	private Set<MenuModel> child = new LinkedHashSet<MenuModel>(); //子节点
	
	
	private Date createTime;//创建时间
	private Date updateTime;//更新时间
	@ManyToOne
	private UserModel createUser;//创建用户
	@ManyToOne
	private UserModel updateUser;//更新用户
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	public boolean isHashChild() {
		return hashChild;
	}
	public void setHashChild(boolean hashChild) {
		this.hashChild = hashChild;
	}
	public MenuModel getParent() {
		return parent;
	}
	public void setParent(MenuModel parent) {
		this.parent = parent;
	}
	public Set<MenuModel> getChild() {
		return child;
	}
	public void setChild(Set<MenuModel> child) {
		this.child = child;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
}
