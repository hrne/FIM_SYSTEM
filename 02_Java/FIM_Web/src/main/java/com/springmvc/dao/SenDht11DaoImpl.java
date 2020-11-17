package com.springmvc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenDht11;

/**
 * 溫濕度dht11感應資料Dao實作
 * 
 * @author hrne
 *
 */
@Repository
public class SenDht11DaoImpl extends BaseDaoImpl<SenDht11> implements SenDht11Dao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenDht11DaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<SenDht11> find_modMainId_desc(Integer modMainId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenDht11 where modMain.id =:modMainId order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		return query.list();
	}
	
	public List<SenDht11> find_modMainId_date_desc(Integer modMainId, Date startDate, Date endDate){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenDht11 where modMain.id =:modMainId and updateDate>=:startDate and updateDate<=:endDate order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();		
	}
}