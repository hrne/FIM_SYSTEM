package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModData;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenSwitch;

/**
 * 電源開關感應資料資料的Dao實做
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

	public List<SenSwitch> findSwitchOrderData(ModData modData) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenSwitch where modData.id =:modData_id order by updateDate desc");
		query.setParameter("modData_id", modData.getId());
		return query.list();
	}
}