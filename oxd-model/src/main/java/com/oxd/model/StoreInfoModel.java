package com.oxd.model;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Table
@Entity
public class StoreInfoModel extends AbstractEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length = 30)
	private String title;	//标题
	@Column(length = 255)
	private String address;//地址
	@Column(length = 255)
	private String prePictureUrl;//前置图url
	@Column(nullable = false)
	private int top;// 是否置顶(0: 不置顶，1：置顶)
	@Lob  
	@Basic(fetch = FetchType.LAZY)
	private String content;//内容
	
	
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
	public String getPrePictureUrl() {
		return prePictureUrl;
	}
	public void setPrePictureUrl(String prePictureUrl) {
		this.prePictureUrl = prePictureUrl;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
