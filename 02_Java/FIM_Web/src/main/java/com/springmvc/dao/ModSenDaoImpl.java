package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModSen;

/**
 * 感應模組的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class ModSenDaoImpl extends BaseDaoImpl<ModSen> implements ModSenDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModSenDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}