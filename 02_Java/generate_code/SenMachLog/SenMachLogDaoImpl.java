package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenMachLog;

@Repository
public class SenMachLogDaoImpl extends BaseDaoImpl<SenMachLog> implements SenMachLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenMachLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}