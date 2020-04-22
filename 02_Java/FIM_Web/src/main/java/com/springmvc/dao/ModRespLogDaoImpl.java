package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModRespLog;

/**
 * 感應紀錄的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class ModRespLogDaoImpl extends BaseDaoImpl<ModRespLog> implements ModRespLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModRespLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}