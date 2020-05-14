package com.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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

	public ModSen find_senCode(String senCode) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModSen where senCode =:senCode ");
		query.setParameter("senCode", senCode);
		if (!CollectionUtils.isEmpty(query.list())) {
			return (ModSen) query.list().get(0);
		} else {
			return null;
		}
	}
}