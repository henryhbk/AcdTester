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

import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.NoteEventInfo;
import com.ibm.nlp.services.NoteEventService;
import com.ibm.nlp.services.ProcedureService;

/**
 * {@link NoteEventService}
 */
/**
 * The Class NoteEventDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@SuppressWarnings("deprecation")
public class NoteEventDao extends HibernateDao implements NoteEventService {

	private Logger logger = LoggerFactory.getLogger(NoteEventDao.class);

	/**
	 * Instantiates a new note event dao.
	 */
	public NoteEventDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see ProcedureService#getNoteEvent(Integer)
	 */
	@Override
	public NoteEvent getNoteEvent(Integer id) {
		final Session session = openSession();
		session.get(NoteEvent.class, id);
		NoteEvent noteEvent = null;
		try {
			noteEvent = session.get(NoteEvent.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getNoteEvent(" + id + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return noteEvent;
	}

	/**
	 * @see ProcedureService#getAllNoteEvents()
	 */
	@Override
	public List<NoteEvent> getAllNoteEvents() {
		Session session = null;
		List<NoteEvent> noteEvents = null;
		try {
			session = openSession();
			noteEvents = HibernateUtil.loadAllData(NoteEvent.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getAllNoteEvents()\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ProcedureService#saveNoteEvent(NoteEvent)
	 */
	@Override
	public NoteEvent saveNoteEvent(NoteEvent noteEvent) {
		Session session = null;
		try {
			session = openSession();
			session.saveOrUpdate(noteEvent);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.saveNoteEvent(" + noteEvent + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return noteEvent;
	}

	/**
	 * @see ProcedureService#deleteNoteEvent(Integer)
	 */
	@Override
	public void deleteNoteEvent(Integer id) {
		Session session = null;
		try {
			session = openSession();
			NoteEvent noteEvent = session.get(NoteEvent.class, id);
			session.delete(noteEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.deleteNoteEvent(" + id + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see ProcedureService#getNoteEventInfo(Integer)
	 */
	@Override
	public NoteEventInfo getNoteEventInfo(Integer id) {
		final Session session = openSession();
		session.get(NoteEventInfo.class, id);
		NoteEventInfo noteEvent = null;
		try {
			noteEvent = session.get(NoteEventInfo.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getNoteEventInfo(" + id + ")\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return noteEvent;

	}

	/**
	 * @see ProcedureService#getAllNoteEventInfos()
	 */
	@Override
	public List<NoteEventInfo> getAllNoteEventInfos() {
		Session session = null;
		List<NoteEventInfo> noteEvents = null;
		try {
			session = openSession();
			noteEvents = HibernateUtil.loadAllData(NoteEventInfo.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getAllNoteEventInfos()\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ProcedureService#getAllNoteEventInfosWithAde()
	 */
	@Override
	public List<NoteEventInfo> getAllNoteEventInfosWithAde() {
		Session session = null;
		List<NoteEventInfo> noteEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<NoteEventInfo> criteriaQuery = builder.createQuery(NoteEventInfo.class);
			Root<NoteEventInfo> root = criteriaQuery.from(NoteEventInfo.class);
			criteriaQuery.select(root).where(builder.equal(root.get("medicationAdverseEvent"), true));
			noteEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getAllNoteEventInfosWithAde()\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ProcedureService#getAllNoteEventsForMimicStudy()
	 */
	@Override
	public List<NoteEvent> getAllNoteEventsForMimicStudy() {
		Session session = null;
		List<NoteEvent> noteEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<NoteEvent> criteriaQuery = builder.createQuery(NoteEvent.class);
			Root<NoteEvent> root = criteriaQuery.from(NoteEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("acdStudyNote"), true));
			noteEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getAllNoteEventsForMimicStudy()\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ProcedureService#searchNotesForText(String)
	 */
	@Override
	public List<NoteEventInfo> searchNotesForText(String text) {
		Session session = null;
		List<NoteEventInfo> noteEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<NoteEventInfo> criteriaQuery = builder.createQuery(NoteEventInfo.class);
			Root<NoteEventInfo> root = criteriaQuery.from(NoteEventInfo.class);
			criteriaQuery.select(root).where(builder.like(root.get("text"), "%" + text + "%"));
			noteEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.searchNotesForText(" + text + ")\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return noteEvents;
	}

	/**
	 * @see ProcedureService#getNoteEventsByPage(Integer, Integer)
	 */
	@Override
	public List<NoteEvent> getNoteEventsByPage(Integer pageNumber, Integer pageSize) {

		Session session = null;
		List<NoteEvent> noteEvents = null;
		try {
			session = openSession();

			Query<NoteEvent> query = session.createQuery("from NoteEvent", NoteEvent.class);

			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);

			noteEvents = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getNoteEventsByPage(" + pageNumber + ", " + pageSize + ")\n"
					+ e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return noteEvents;

	}

	/**
	 * @see ProcedureService#getNumberOfNoteEventRows()
	 */
	@Override
	public Long getNumberOfNoteEventRows() {
		Session session = null;
		Long size = null;
		try {
			session = openSession();
			Criteria criteria = session.createCriteria(NoteEvent.class);
			criteria.setProjection(Projections.projectionList().add(Projections.rowCount()));
			size = (Long) criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("NoteEventDao.getNumberOfNoteEventRows()\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return size;

	}

}
