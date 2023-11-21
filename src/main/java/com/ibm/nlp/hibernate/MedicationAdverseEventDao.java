package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.nlp.model.mimic3.MedicationAdverseEvent;
import com.ibm.nlp.services.MedicationAdverseEventService;

/**
 * The Class MedicationAdverseEventDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class MedicationAdverseEventDao extends HibernateDao implements MedicationAdverseEventService {

	private Logger logger = LoggerFactory.getLogger(MedicationAdverseEventDao.class);

	/**
	 * Instantiates a new medication adverse event dao.
	 */
	public MedicationAdverseEventDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * Gets the medication adverse event.
	 *
	 * @param id the id
	 * @return the medication adverse event
	 */
	@Override
	public MedicationAdverseEvent getMedicationAdverseEvent(Integer id) {
		final Session session = openSession();
		MedicationAdverseEvent medicationAdverseEvent = null;
		try {
			medicationAdverseEvent = session.get(MedicationAdverseEvent.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("MedicationAdverseEventDao.getMedicationAdverseEvent(" + id + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return medicationAdverseEvent;
	}

	/**
	 * Gets the medication adverse events.
	 *
	 * @return the medication adverse events
	 */
	@Override
	public List<MedicationAdverseEvent> getMedicationAdverseEvents() {
		Session session = null;
		List<MedicationAdverseEvent> adverseEventList = null;
		try {
			session = openSession();
			adverseEventList = HibernateUtil.loadAllData(MedicationAdverseEvent.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("MedicationAdverseEventDao.getMedicationAdverseEvents()\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return adverseEventList;
	}

	/**
	 * Save medication adverse event.
	 *
	 * @param medicationAdverseEvent the medication adverse event
	 * @return the medication adverse event
	 */
	@Override
	public MedicationAdverseEvent saveMedicationAdverseEvent(MedicationAdverseEvent medicationAdverseEvent) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(medicationAdverseEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("MedicationAdverseEventDao.saveMedicationAdverseEvent(" + medicationAdverseEvent + ")\n"
					+ e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return medicationAdverseEvent;
	}

	/**
	 * Delete medication adverse event.
	 *
	 * @param id the id
	 */
	@Override
	public void deleteMedicationAdverseEvent(Integer id) {
		Session session = null;
		try {
			session = openSession();
			MedicationAdverseEvent adverseEvent = session.get(MedicationAdverseEvent.class, id);
			session.delete(adverseEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.deleteMedicationAdverseEvent(" + id + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * Truncate medication adverse events.
	 */
	@Override
	public void truncateMedicationAdverseEvents() {
		final Session session = openSession();
		try {
			session.createSQLQuery("truncate table medication_adverse_event").executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.truncateMedicationAdverseEvents()\n" + e.getLocalizedMessage());
		}
		closeSession(session);

	}

	/**
	 * Search medication adverse events for text.
	 *
	 * @param searchString the search string
	 * @return the list
	 */
	@Override
	public List<MedicationAdverseEvent> searchMedicationAdverseEventsForText(String searchString) {
		Session session = null;
		List<MedicationAdverseEvent> noteEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MedicationAdverseEvent> criteriaQuery = builder.createQuery(MedicationAdverseEvent.class);
			Root<MedicationAdverseEvent> root = criteriaQuery.from(MedicationAdverseEvent.class);
			criteriaQuery.select(root).where(builder.like(root.get("adeSpan"), "%" + searchString + "%"));
			noteEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.searchMedicationAdverseEventsForText(" + searchString + ")\n"
					+ e.getLocalizedMessage());
		}
		closeSession(session);
		return noteEvents;
	}

	/**
	 * Gets the ade medication names.
	 *
	 * @return the ade medication names
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<String> getAdeMedicationNames() {
		Session session = null;
		List<String> medications = null;
		try {
			session = openSession();
			String hqlString = "select distinct adeDrugName from MedicationAdverseEvent order by adeDrugName";

			Query<String> distinctQuery = session.createQuery(hqlString);

			medications = distinctQuery.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getAdeMedicationNames()\n" + e.getLocalizedMessage());
		}
		closeSession(session);

		return medications;
	}
}
