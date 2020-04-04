package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenParm;

@Repository
public class SenParmDaoImpl extends BaseDaoImpl<SenParm> implements SenParmDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenParmDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}