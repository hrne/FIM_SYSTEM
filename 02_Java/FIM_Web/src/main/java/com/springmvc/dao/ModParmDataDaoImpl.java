package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;

/**
 * 感應模組參數的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class ModParmDataDaoImpl extends BaseDaoImpl<ModParmData> implements ModParmDataDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModParmDataDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<ModParmData> findByModSenId(Integer modSenId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModParm where modSen.id =:modSen_id and showEnabled=1");
		query.setParameter("modSen_id", modSen_id);
		return query.list();
	}

}