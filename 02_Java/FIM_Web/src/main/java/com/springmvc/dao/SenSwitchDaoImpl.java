package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料資料Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class SenSwitchDaoImpl extends BaseDaoImpl<SenSwitch> implements SenSwitchDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenSwitchDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<SenSwitch> find_modMainId_desc(Integer modMainId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenSwitch where modMain.id =:modMainId order by updateDate desc");
		query.setParameter("modMainId", modMainId);
		return query.list();
	}
}