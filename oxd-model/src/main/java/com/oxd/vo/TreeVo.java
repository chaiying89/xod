package com.oxd.vo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class TreeVo implements Serializable {
	private Integer id;
	private String text;
	private String parentName;
	private Integer pid;
	private String name;
	private String url;
	private Integer level;
	private boolean hasChild;
	private boolean editable;
	private Integer orderBy;
	private String state;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public boolean isHasChild() {
		return hasChild;
	}
	public void setHasChild(String hasChild) {
		this.hasChild = new Boolean(hasChild);
	}
	public boolean isEditable() {
		return editable;
	}
	public void setEditable(String editable) {
		this.editable = new Boolean(editable);
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public String getState() {
		this.state = isHasChild()?"closed":"open";
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
