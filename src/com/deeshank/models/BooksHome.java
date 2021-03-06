package com.deeshank.models;

// default package
// Generated Apr 27, 2015 7:51:44 PM by Hibernate Tools 3.4.0.CR1

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Books.
 * @see .Books
 * @author Hibernate Tools
 */
@Stateless
public class BooksHome {

	private static final Log log = LogFactory.getLog(BooksHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Books transientInstance) {
		log.debug("persisting Books instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Books persistentInstance) {
		log.debug("removing Books instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Books merge(Books detachedInstance) {
		log.debug("merging Books instance");
		try {
			Books result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Books findById(Integer id) {
		log.debug("getting Books instance with id: " + id);
		try {
			Books instance = entityManager.find(Books.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
