package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenAcs712;

/**
 * 電流acs712感應資料的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class SenAcs712DaoImpl extends BaseDaoImpl<SenAcs712> implements SenAcs712Dao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenAcs712DaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}