package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModUpdateLog;

/**
 * 感應裝置更新紀錄Dao實作
 * 
 * @author hrne
 *
 */
@Repository
public class ModUpdateLogDaoImpl extends BaseDaoImpl<ModUpdateLog> implements ModUpdateLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModUpdateLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}