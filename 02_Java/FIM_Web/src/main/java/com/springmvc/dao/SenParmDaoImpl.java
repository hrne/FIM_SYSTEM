package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenParm;

/**
 * 感應模組參數的Dao實做
 * 
 * @author hrne
 *
 */
@Repository 
public class SenParmDaoImpl extends BaseDaoImpl<SenParm> implements SenParmDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenParmDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}