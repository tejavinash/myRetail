package com.myretail.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author rnandikolla
 *
 * @param <T>
 */
public abstract class AbstractJpaDAO<T> {
	
	private static final Logger LOG = Logger.getLogger(AbstractJpaDAO.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	protected Session getSession(){
		return (Session) sessionFactory.getCurrentSession();
	}
	
	private Class<T> instance;
	
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		return (List<T>) getSession().createCriteria(getPersistentClass()).list();
	}

	@SuppressWarnings("unchecked")
	public T get(Long id) {
		return (T) getSession().get(getPersistentClass(),id);
	}
	
	public void delete(Long id)  {
		T t = get(id);
		getSession().delete(t);
		flush();
	}
	
	public T update(T t)  {
		getSession().saveOrUpdate(t);
		flush();
		return t;
	}
	
	@SuppressWarnings("unchecked")
	public T create(T t)  {
		t = (T)getSession().merge(t);
		flush();
		return t;
	}
	
	public void flush() {
		getSession().flush();
	}

	public void evict(T t) {
		getSession().evict(t);
	}
	
	/**
	 * Returns the Class implementation of the generic class from the extended Dao. 
	 * 
	 * This saves us from having to initialize the class and pass in the representation. 
	 * 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Class<T> getPersistentClass(){
		if(instance == null) {
			try {
				instance = (Class<T>) ((Class)((ParameterizedType)this.getClass().
					       getGenericSuperclass()).getActualTypeArguments()[0]);
			} catch (Exception e) {
				LOG.error(e);
			}
		}
		return instance;
	}
	
}
