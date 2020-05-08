package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class SenHx711DaoImpl extends BaseDaoImpl<SenHx711> implements SenHx711Dao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenHx711DaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<SenHx711> findHx711OrderData(ModMain modData) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenHx711 where modData.id =:modData_id order by updateDate desc");
		query.setParameter("modData_id", modData.getId());
		return query.list();
	}
}