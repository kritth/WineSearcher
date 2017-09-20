package com.kritth.klf.wine.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class to support hibernate transaction to make it easier to expan the code
 * 
 * @author Kritth
 *
 */
public class HibernateSupport {
	private static final Logger logger = LoggerFactory.getLogger(HibernateSupport.class);
	
	@Autowired
	private SessionFactory sf;

	/**
	 * Return current session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sf.getCurrentSession();
	}
	
	/**
	 * Find object from database by id
	 * 
	 * @param c Class to find
	 * @param id ID of the object
	 * @return the Object found from the database
	 */
	@SuppressWarnings("unchecked")
	protected <T> T findById(Class<T> c, Serializable id) {
		try {
			Object obj = getSession().load(c, id);
			Hibernate.initialize(obj);
			return (T) obj;
		} catch (ObjectNotFoundException ex) { // In case the data base cannot find object
			logger.error("Object from " + c.getName() + " with ID " + id + " cannot be found.");
			logger.debug(ex.toString(), ex);
			return null;
		}
	}
	
	/**
	 * Find result from class starting from specified index for specified amount
	 * 
	 * @param c
	 * @param from
	 * @param amount
	 * @return List of object from class c
	 */
	@SuppressWarnings("unchecked")
	protected <T> List<T> findResultFromTo(Class<T> c, int from, int amount) {
		Criteria criteria = getSession().createCriteria(c);
		criteria.setFirstResult(from);
		criteria.setMaxResults(amount);
		return (List<T>) criteria.list();
	}
}