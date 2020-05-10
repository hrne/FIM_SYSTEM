package com.springmvc.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SysRespStatus;

/**
 * 回傳狀態代碼檔Dao實作
 * 
 * @author hrne
 *
 */
@Repository
public class SysRespStatusDaoImpl extends BaseDaoImpl<SysRespStatus> implements SysRespStatusDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SysRespStatusDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public SysRespStatus find_statusCode(String statusCode) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from SysRespStatus where statusCode =:statusCode ");
		query.setParameter("statusCode", statusCode);
		if (!CollectionUtils.isEmpty(query.list())) {
			return (SysRespStatus) query.list().get(0);
		} else {
			return null;
		}
	}

}