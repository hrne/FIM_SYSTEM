package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModMain;

/**
 * 感應裝置主檔Dao實作
 * 
 * @author hrne
 *
 */
@Repository
public class ModMainDaoImpl extends BaseDaoImpl<ModMain> implements ModMainDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModMainDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<ModMain> find_modEnabled() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModMain where modEnabled=1");
		return query.list();
	}

}