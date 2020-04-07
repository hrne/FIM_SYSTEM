package com.modle.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Data Access Object的基礎實做
 * 
 * @author hrne
 *
 * @param <T>
 */
@Repository
@Transactional
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	// 實體類型
	private Class<T> entityClass;

	private SessionFactory sessionFactory;

	@Autowired
	public BaseDaoImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		// 通過反射獲取泛型傳過來的類型
		this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	@Override
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from " + entityClass.getSimpleName());
	}

	@Override
	public T findByPK(int id) {
		return (T) this.getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public void saveOrUpdate(T entity) {
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate(entity);
	}

	@Override
	public void create(T entity) {
		Session currentSession = this.getHibernateTemplate().getSessionFactory().openSession();
		Transaction tran = currentSession.beginTransaction();
		currentSession.save(entity);
		tran.commit();
		currentSession.close();

	}

	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void delete(T entity) {
		this.getHibernateTemplate().delete(entity);
	}

}
