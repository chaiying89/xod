package com.oxd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	@CreatedDate
	@Column(length = 11, updatable=false)
	private Date createTime;
	@LastModifiedDate
	@Column(length = 11)
	private Date updateTime;
	@CreatedBy
	@ManyToOne
	@JoinColumn(updatable=false)
	private UserModel createUser;//创建用户
	@LastModifiedBy
	@ManyToOne
	private UserModel updateUser;//更新用户
	
	
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
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
}
