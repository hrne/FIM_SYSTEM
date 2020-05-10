package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModParmData;

/**
 * 模組參數資料Dao實作
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

	public List<ModParmData> find_modSenId_show(Integer modSenId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from ModParmData where modSen.id =:modSenId and showEnabled=1");
		query.setParameter("modSenId", modSenId);
		return query.list();
	}

}