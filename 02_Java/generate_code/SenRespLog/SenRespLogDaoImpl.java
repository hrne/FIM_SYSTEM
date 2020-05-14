package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenRespLog;

@Repository
public class SenRespLogDaoImpl extends BaseDaoImpl<SenRespLog> implements SenRespLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenRespLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}