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

import com.ibm.nlp.model.mimic3.ChartEvent;
import com.ibm.nlp.model.mimic3.DItem;
import com.ibm.nlp.services.ChartEventService;

/**
 * {@link ChartEventService}
 */
/**
 * 
 * The Class ChartEventDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@SuppressWarnings("deprecation")
public class ChartEventDao extends HibernateDao implements ChartEventService {

	private Logger logger = LoggerFactory.getLogger(ChartEventDao.class);

	/**
	 * Instantiates a new chart event dao.
	 */
	public ChartEventDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see ChartEventService#getAllChartEvents()
	 */
	@Override
	public List<ChartEvent> getAllChartEvents() {
		Session session = null;
		List<ChartEvent> chartEventList = null;
		try {
			session = openSession();
			chartEventList = HibernateUtil.loadAllData(ChartEvent.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getAllChartEvents()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return chartEventList;
	}

	/**
	 * @see ChartEventService#getChartEvent(Integer)
	 */
	@Override
	public ChartEvent getChartEvent(Integer rowId) {
		final Session session = openSession();
		ChartEvent chartEvent = null;
		try {
			chartEvent = session.get(ChartEvent.class, rowId);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getChartEvent(" + rowId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return chartEvent;
	}

	/**
	 * @see ChartEventService#getChartEventForSubject(Integer)
	 */
	@Override
	public List<ChartEvent> getChartEventForSubject(Integer subjectId) {
		Session session = null;
		List<ChartEvent> chartEventList = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ChartEvent> criteriaQuery = builder.createQuery(ChartEvent.class);
			Root<ChartEvent> root = criteriaQuery.from(ChartEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			chartEventList = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getChartEvent(\"" + subjectId + "\")\\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return chartEventList;
	}

	/**
	 * @see ChartEventService#getChartEventForAdmission(Integer)
	 */
	@Override
	public List<ChartEvent> getChartEventForAdmission(Integer hadmId) {
		Session session = null;
		List<ChartEvent> chartEventList = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ChartEvent> criteriaQuery = builder.createQuery(ChartEvent.class);
			Root<ChartEvent> root = criteriaQuery.from(ChartEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
			chartEventList = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getChartEventForAdmission(\"" + hadmId + "\")\\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return chartEventList;
	}

	/**
	 * @see ChartEventService#geAlltDItems()
	 */
	@Override
	public List<DItem> geAlltDItems() {
		Session session = null;
		List<DItem> itemList = null;
		try {
			session = openSession();
			itemList = HibernateUtil.loadAllData(DItem.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.geAlltDItems()\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return itemList;
	}

	/**
	 * @see ChartEventService#saveOrUpdateChartEvent(ChartEvent)
	 */
	@Override
	public ChartEvent saveOrUpdateChartEvent(ChartEvent chartEvent) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(chartEvent);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("ChartEventDao.saveOrUpdateChartEvent(\"" + chartEvent + "\")\\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return chartEvent;
	}

	/**
	 * @see ChartEventService#saveOrUpdateDItem(DItem)
	 */
	@Override
	public DItem saveOrUpdateDItem(DItem item) {
		Session session = null;
		try {
			session = openSession();
			session.saveOrUpdate(item);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("ChartEventDao.saveOrUpdateDItem(\"" + item + "\")\\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return item;
	}

	/**
	 * @see ChartEventService#deleteChartEvent(Integer)
	 */
	@Override
	public void deleteChartEvent(Integer rowId) {
		Session session = null;
		try {
			session = openSession();
			ChartEvent event = session.get(ChartEvent.class, rowId);
			session.delete(event);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.deleteChartEvent(\"" + rowId + "\")\\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see ChartEventService#deleteDItem(Integer)
	 */
	@Override
	public void deleteDItem(Integer rowId) {
		Session session = null;
		try {
			session = openSession();
			DItem event = session.get(DItem.class, rowId);
			session.delete(event);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.deleteDItem(\"" + rowId + "\")\\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see ChartEventService#getChartEventsForItem(Integer)
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ChartEvent> getChartEventsForItem(Integer itemId) {
		// we will use a query by example here as Hibernate has deprecated alias based
		// queries

		List<ChartEvent> events = null;
		try {
			ChartEvent exampleEvent = new ChartEvent();
			DItem itemExample = new DItem();
			itemExample.setItemId(itemId);
			exampleEvent.setdItem(itemExample);

			Criteria criteria = session.createCriteria(ChartEvent.class);
			events = criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getChartEventsForItem(\"" + itemId + "\")\\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return events;
	}

	/**
	 * @see ChartEventService#getChartEventsByPage(Integer, Integer)
	 */
	@Override
	public List<ChartEvent> getChartEventsByPage(Integer pageNumber, Integer pageSize) {

		Session session = null;
		List<ChartEvent> events = null;
		try {
			session = openSession();

			Query<ChartEvent> query = session.createQuery("from ChartEvent", ChartEvent.class);

			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			events = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getChartEventsByPage(\"" + pageNumber + ", " + pageSize + "\")\\n"
					+ e.getLocalizedMessage());
		}
		closeSession(session);
		return events;

	}

	@Override
	public Long getNumberOfChartEventRows() {
		Session session = null;
		Long size = null;
		try {
			session = openSession();
			Criteria criteria = session.createCriteria(ChartEvent.class);
			criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
			size = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("ChartEventDao.getNumberOfChartEventRows()\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return size;

	}

}
