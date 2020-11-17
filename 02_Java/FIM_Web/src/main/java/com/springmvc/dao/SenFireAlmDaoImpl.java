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
import com.springmvc.entity.SenFireAlm;

/**
 * 火災警報感應資料Dao實作
 * 
 * @author hrne
 *
 */
@Repository
public class SenFireAlmDaoImpl extends BaseDaoImpl<SenFireAlm> implements SenFireAlmDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenFireAlmDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<SenFireAlm> find_modMainId_desc(Integer modMainId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenFireAlm where modMain.id =:modMainId order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		return query.list();
	}
	
	public List<SenFireAlm> find_modMainId_date_desc(Integer modMainId, Date startDate, Date endDate){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenFireAlm where modMain.id =:modMainId and updateDate>=:startDate and updateDate<=:endDate order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return query.list();		
	}
}