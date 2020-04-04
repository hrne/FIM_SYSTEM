package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenDht11;

@Repository
public class SenDht11DaoImpl extends BaseDaoImpl<SenDht11> implements SenDht11Dao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenDht11DaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}