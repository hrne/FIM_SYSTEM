package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModParm;

/**
 * 感應模組參數的Dao實做
 * 
 * @author hrne
 *
 */
@Repository 
public class ModParmDaoImpl extends BaseDaoImpl<ModParm> implements ModParmDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModParmDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}