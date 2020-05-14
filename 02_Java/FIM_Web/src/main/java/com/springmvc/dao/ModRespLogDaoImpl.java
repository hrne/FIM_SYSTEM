package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.SenDht11;

/**
 * 感應紀錄Dao實作
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
	
	public List<ModRespLog> find_modMainId_desc(Integer modMainId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModRespLog where modMain.id =:modMainId order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		return query.list();
	}
	
	public List<ModRespLog> find_modMainId_modSenId_desc(Integer modMainId,Integer modSenId){
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModRespLog where modMain.id =:modMainId and modSen.id =:modSenId order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		query.setParameter("modSenId", modSenId);
		return query.list();
	}

}