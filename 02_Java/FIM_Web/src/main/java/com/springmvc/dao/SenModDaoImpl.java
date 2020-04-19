package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenMod;

/**
 * 感應模組的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class SenModDaoImpl extends BaseDaoImpl<SenMod> implements SenModDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenModDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}