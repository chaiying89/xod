package com.oxd.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

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
}
