package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenMach;

@Repository
public class SenMachDaoImpl extends BaseDaoImpl<SenMach> implements SenMachDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenMachDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}