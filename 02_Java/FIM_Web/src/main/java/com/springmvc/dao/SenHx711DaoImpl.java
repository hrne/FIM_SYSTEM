package com.springmvc.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenFireAlm;
import com.springmvc.entity.SenHx711;

/**
 * 重量hx711感應資料Dao實作
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

	public List<SenHx711> find_modMainId_desc(Integer modMainId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenHx711 where modMain.id =:modMainId order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		return query.list();
	}
	
	public List<SenHx711> find_modMainId_date_desc(Integer modMainId, Date startDate, Date endDate){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenHx711 where modMain.id =:modMainId and updateDate>=:startDate and updateDate<=:endDate order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();		
	}
}