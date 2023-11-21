package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * The Class HibernateUtil.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class HibernateUtil {

	/** The registry. */
	private static StandardServiceRegistry registry;

	/** The session factory. */
	private static SessionFactory sessionFactory;

	/** The emf. */
	private static EntityManagerFactory emf;

	/** The em. */
	private static EntityManager em;

	/**
	 * Instantiates a new hibernate util.
	 */
	public HibernateUtil() {
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
				// Create registry
				registry = new StandardServiceRegistryBuilder().configure().build();

				// Create MetadataSources
				MetadataSources sources = new MetadataSources(registry);

				// Create Metadata
				Metadata metadata = sources.getMetadataBuilder().build();

				// Create SessionFactory
				sessionFactory = metadata.getSessionFactoryBuilder().build();

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

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		getSessionFactory();
		System.out.println("pause");
	}

}