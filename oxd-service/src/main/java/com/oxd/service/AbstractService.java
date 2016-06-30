package com.oxd.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.oxd.model.AbstractEntity;
import com.oxd.model.UserModel;

public class AbstractService {
	
	protected void setScalarsAndParams(SQLQuery query, String[] scalars, List<Object> params) {
		for(String scalar : scalars) {
			query.addScalar(scalar);
		}
		if(params != null) {
			int i = 0;
			while(i < params.size()) {
				query.setParameter(i, params.get(i));
			}
		}
	}
	
	protected void setScalars(SQLQuery query, String[] scalars) {
		setScalarsAndParams(query, scalars, null);
	}
	
	protected int getCount(Session session, String sql) {
		SQLQuery query = session.createSQLQuery(sql);
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	protected void fillEntity(AbstractEntity entity) {
		UserModel user = (UserModel) SecurityUtils.getSubject().getPrincipal();
		entity.setUpdateUser(user);
		if(entity.getCreateUser() == null) {
			entity.setCreateUser(user);
		}
		entity.setUpdateTime(new Date());
		if(entity.getCreateTime() == null) {
			entity.setCreateTime(entity.getUpdateTime());
		}
		
	}
}
