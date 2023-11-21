package com.ibm.nlp.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * The Class HibernateDB2Dao is the DB2 varian of the standard hibernate DAO
 * class.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class HibernateDB2Dao {

	/** The session. */
	final Session session = openSession();

	/**
	 * Close session.
	 *
	 * @param session the session
	 * @throws HibernateException the hibernate exception
	 */
	protected static void closeSession(final Session session) throws HibernateException {
		session.getTransaction().commit();
	}

	/**
	 * Open session.
	 *
	 * @return the session
	 * @throws HibernateException the hibernate exception
	 */
	protected static Session openSession() throws HibernateException {
		final Session session = Db2HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		return session;
	}

	/**
	 * Rollback session.
	 *
	 * @param session the session
	 * @throws HibernateException the hibernate exception
	 */
	protected void rollbackSession(final Session session) throws HibernateException {
		session.getTransaction().rollback();
	}

}
