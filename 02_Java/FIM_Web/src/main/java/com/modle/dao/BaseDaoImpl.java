package com.modle.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
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

	public BaseDaoImpl(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
		// 通過反射獲取泛型傳過來的類型
		Type type = this.getClass().getGenericSuperclass(); // generic 泛型
		if (type instanceof ParameterizedType) {
			// 强制轉化“参數化類型”
			ParameterizedType parameterizedType = (ParameterizedType) type;
			// 参數化類型中可能有多個泛型参數
			Type[] types = parameterizedType.getActualTypeArguments();
			// 獲取資料的第一個元素(User.class)
			entityClass = (Class<T>) types[0]; // com.oa.shore.entity.User.class
		}
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
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public void create(T entity) {
		this.getHibernateTemplate().save(entity);
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
