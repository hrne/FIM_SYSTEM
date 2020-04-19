package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenMach;

/**
 * 感應器資料的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class SenMachDaoImpl extends BaseDaoImpl<SenMach> implements SenMachDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SenMachDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	/**
	 * 查詢所有啟用的感應器
	 */
	public List<SenMach> findByMachEnable() {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SenMach WHERE machEnable=1");
		return query.list();
	}

}