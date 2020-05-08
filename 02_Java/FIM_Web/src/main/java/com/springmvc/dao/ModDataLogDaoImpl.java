package com.springmvc.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.ModUpdateLog;

/**
 * 感應裝置更新紀錄的Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class ModDataLogDaoImpl extends BaseDaoImpl<ModUpdateLog> implements ModDataLogDao {

	@Autowired
	private SessionFactory sessionFactory;

	public ModDataLogDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

}