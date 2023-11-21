package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.nlp.model.mimic3.LabEvent;
import com.ibm.nlp.model.mimic3.LabItem;
import com.ibm.nlp.model.mimic3.MicrobiolgyEvent;
import com.ibm.nlp.services.LabEventService;

/**
 * {@link LabEventService}
 */
/**
 * /** The Class LabEventDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@SuppressWarnings("deprecation")
public class LabEventDao extends HibernateDao implements LabEventService {

	private Logger logger = LoggerFactory.getLogger(ChartEventDao.class);

	/**
	 * Instantiates a new lab event dao.
	 */
	public LabEventDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see LabEventService#getLabEvent(Integer)
	 */
	@Override
	public LabEvent getLabEvent(Integer id) {
		final Session session = openSession();
		LabEvent labEvent = null;
		try {
			labEvent = session.get(LabEvent.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getLabEvent(" + id + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return labEvent;
	}

	/**
	 * @see LabEventService#getAllLabEvents()
	 */
	@Override
	public List<LabEvent> getAllLabEvents() {
		Session session = null;
		List<LabEvent> labEventList = null;
		try {
			session = openSession();
			labEventList = HibernateUtil.loadAllData(LabEvent.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getAllLabEvents()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return labEventList;
	}

	/**
	 * @see LabEventService#getAllLabEventsForAcdStudy()
	 */
	@Override
	public List<LabEvent> getAllLabEventsForAcdStudy() {
		Session session = null;
		List<LabEvent> labEventList = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<LabEvent> criteriaQuery = builder.createQuery(LabEvent.class);
			Root<LabEvent> root = criteriaQuery.from(LabEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("acdStudyMed"), true));
			labEventList = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getAllLabEventsForAcdStudy()\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return labEventList;
	}

	/**
	 * @see LabEventService#saveLabEvent(LabEvent)
	 */
	@Override
	public LabEvent saveLabEvent(LabEvent labEvent) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(labEvent);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("LabEventDao.saveLabEvent(" + labEvent + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return labEvent;
	}

	/**
	 * @see LabEventService#deleteLabEvent(Integer)
	 */
	@Override
	public void deleteLabEvent(Integer id) {
		Session session = null;
		try {
			session = openSession();
			LabEvent labEvent = session.get(LabEvent.class, id);
			session.delete(labEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.deleteLabEvent(" + id + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see LabEventService#getNumberOfLabEventRows()
	 */
	public Long getNumberOfLabEventRows() {
		Session session = null;
		Long size = null;
		try {
			session = openSession();
			Criteria criteria = session.createCriteria(LabEvent.class);
			criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
			size = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getNumberOfLabEventRows()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return size;

	}

	/**
	 * @see LabEventService#getLabEventsByPage(Integer, Integer)
	 */
	@Override
	public List<LabEvent> getLabEventsByPage(Integer pageNumber, Integer pageSize) {

		Session session = null;
		List<LabEvent> labEvents = null;
		try {
			session = openSession();

			Query<LabEvent> query = session.createQuery("from LabEvent", LabEvent.class);

			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			labEvents = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error(
					"LabEventDao.getLabEventsByPage(" + pageNumber + ", " + pageSize + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return labEvents;

	}

	/**
	 * @see LabEventService#getAllLabItems()
	 */
	@Override
	public List<LabItem> getAllLabItems() {
		Session session = null;
		List<LabItem> labItemList = null;
		try {
			session = openSession();
			labItemList = HibernateUtil.loadAllData(LabItem.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getAllLabItems()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return labItemList;
	}

	/**
	 * @see LabEventService#getMicrobiologyEvents()
	 */
	@Override
	public List<MicrobiolgyEvent> getMicrobiologyEvents() {
		Session session = null;
		List<MicrobiolgyEvent> labItemList = null;
		try {
			session = openSession();
			labItemList = HibernateUtil.loadAllData(MicrobiolgyEvent.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getMicrobiologyEvents()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return labItemList;
	}

	/**
	 * @see LabEventService#getMicrobiologyEvent(Integer)
	 */
	@Override
	public MicrobiolgyEvent getMicrobiologyEvent(Integer rowId) {
		final Session session = openSession();
		MicrobiolgyEvent microEvent = null;
		try {
			microEvent = session.get(MicrobiolgyEvent.class, rowId);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getMicrobiologyEvent(" + rowId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return microEvent;
	}

	/**
	 * @see LabEventService#getMicrobiologyEventsForPatient(Integer)
	 */
	@Override
	public List<MicrobiolgyEvent> getMicrobiologyEventsForPatient(Integer subjectId) {
		Session session = null;
		List<MicrobiolgyEvent> microEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<MicrobiolgyEvent> criteriaQuery = builder.createQuery(MicrobiolgyEvent.class);
			Root<MicrobiolgyEvent> root = criteriaQuery.from(MicrobiolgyEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			microEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.getMicrobiologyEventsForPatient(" + subjectId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return microEvents;
	}

	/**
	 * @see LabEventService#saveOrUpdateMicrobiologyEvent(MicrobiolgyEvent)
	 */
	@Override
	public MicrobiolgyEvent saveOrUpdateMicrobiologyEvent(MicrobiolgyEvent microbiologyEvent) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(microbiologyEvent);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error(
					"LabEventDao.saveOrUpdateMicrobiologyEvent(" + microbiologyEvent + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return microbiologyEvent;
	}

	/**
	 * @see LabEventService#deleteMicrobiologyEvent(Integer)
	 */
	@Override
	public void deleteMicrobiologyEvent(Integer rowId) {
		final Session session = openSession();
		try {
			MicrobiolgyEvent microEvent = session.get(MicrobiolgyEvent.class, rowId);
			session.delete(microEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("LabEventDao.deleteMicrobiologyEvent(" + rowId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}
}
