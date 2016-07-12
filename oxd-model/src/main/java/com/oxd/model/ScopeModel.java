package com.oxd.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table
@Entity
public class ScopeModel extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 255)
	private String introduction;//内容简介
	@Column(length = 255)
	private String prePictureUrl;//前置图url
	@Lob  
	@Basic(fetch = FetchType.LAZY)
	private String content;//内容
	@ManyToOne
	@JoinColumn(name = "m_id", nullable = false, updatable = false)
	private MenuModel menuModel; // 上层菜单

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MenuModel getMenuModel() {
		return menuModel;
	}
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
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
}
