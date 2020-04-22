package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModData;

/**
 * 感應裝置的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class ModDataDaoImpl extends BaseDaoImpl<ModData> implements ModDataDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModDataDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public List<ModData> findByModEnable() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModData where modEnable=1");
		return query.list();
	}

}