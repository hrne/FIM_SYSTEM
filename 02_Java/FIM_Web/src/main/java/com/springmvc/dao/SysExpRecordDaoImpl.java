package com.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.modle.dao.BaseDaoImpl;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SysExpRecord;

/**
 * 實驗紀錄資料Dao實做
 * 
 * @author hrne
 *
 */
@Repository
public class SysExpRecordDaoImpl extends BaseDaoImpl<SysExpRecord> implements SysExpRecordDao {

	@Autowired
	private SessionFactory sessionFactory;

	public SysExpRecordDaoImpl(SessionFactory sessionFactory) {
		super(sessionFactory);
	}


}