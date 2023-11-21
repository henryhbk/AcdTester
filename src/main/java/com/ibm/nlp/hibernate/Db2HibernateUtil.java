package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * The Class Db2HibernateUtil is the DB2 version of the hibernate Util.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class Db2HibernateUtil {

	/** The registry. */
	private static StandardServiceRegistry registry;

	/** The session factory. */
	private static SessionFactory sessionFactory;

	/** The emf. */
	private static EntityManagerFactory emf;

	/** The em. */
	private static EntityManager em;

	/**
	 * Instantiates a new db 2 hibernate util.
	 */
	public Db2HibernateUtil() {
		super();
		emf = Persistence.createEntityManagerFactory("PERSISTENCE");
		em = emf.createEntityManager();
	}

	/**
	 * Gets the session factory.
	 *
	 * @return the session factory
	 */
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration config = new Configuration().configure("/hibernate-db2.cfg.xml");
				sessionFactory = config.buildSessionFactory();
			} catch (Exception e) {
				e.printStackTrace();
				if (registry != null) {
					StandardServiceRegistryBuilder.destroy(registry);
				}
			}
		}
		return sessionFactory;
	}

	/**
	 * Shutdown.
	 */
	public static void shutdown() {
		if (registry != null) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	public static Session getSession() {
		return HibernateUtil.getSessionFactory().openSession();
	}

	/**
	 * Gets the entity manager factory.
	 *
	 * @return the entity manager factory
	 */
	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	/**
	 * Gets the entity manager.
	 *
	 * @return the entity manager
	 */
	public static EntityManager getEntityManager() {
		return em;
	}

	/**
	 * Load all data.
	 *
	 * @param <T>     the generic type
	 * @param type    the type
	 * @param session the session
	 * @return the list
	 */
	public static <T> List<T> loadAllData(Class<T> type, Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> criteria = builder.createQuery(type);
		criteria.from(type);
		List<T> data = session.createQuery(criteria).getResultList();
		return data;
	}

}
