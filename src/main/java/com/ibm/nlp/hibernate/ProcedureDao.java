package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;

import com.ibm.nlp.model.mimic3.ProcedureEvent;
import com.ibm.nlp.services.ProcedureService;

/**
 * {@link ProcedureService}
 */
/**
 * The Class ProcedureDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */

@SuppressWarnings("deprecation")
public class ProcedureDao extends HibernateDao implements ProcedureService {

	public ProcedureDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see ProcedureService#getAllProcedureEvent()
	 */
	@Override
	public List<ProcedureEvent> getAllProcedureEvent() {
		final Session session = openSession();
		List<ProcedureEvent> procedures = HibernateUtil.loadAllData(ProcedureEvent.class, session);
		closeSession(session);
		return procedures;
	}

	/**
	 * @see ProcedureService#getAllProcedureEventForPatient(Integer)
	 */
	@Override
	public List<ProcedureEvent> getAllProcedureEventForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProcedureEvent> criteriaQuery = builder.createQuery(ProcedureEvent.class);
		Root<ProcedureEvent> root = criteriaQuery.from(ProcedureEvent.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<ProcedureEvent> procedures = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return procedures;
	}

	/**
	 * @see ProcedureService#getAllProcedureEventForAdmission(Integer)
	 */
	@Override
	public List<ProcedureEvent> getAllProcedureEventForAdmission(Integer hadmId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProcedureEvent> criteriaQuery = builder.createQuery(ProcedureEvent.class);
		Root<ProcedureEvent> root = criteriaQuery.from(ProcedureEvent.class);
		criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
		List<ProcedureEvent> procedures = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return procedures;
	}

	/**
	 * @see ProcedureService#searchProcedureEventForTerm(String)
	 */
	@Override
	public List<ProcedureEvent> searchProcedureEventForTerm(String searchTerm) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<ProcedureEvent> criteriaQuery = builder.createQuery(ProcedureEvent.class);
		Root<ProcedureEvent> root = criteriaQuery.from(ProcedureEvent.class);
		criteriaQuery.select(root).where(builder.like(root.get("description"), "%" + searchTerm + "%"));
		List<ProcedureEvent> procedures = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return procedures;
	}

	/**
	 * @see ProcedureService#getAllProcedureEventByPage(Integer, Integer)
	 */
	@SuppressWarnings("deprecation")
	@Override
	public List<ProcedureEvent> getAllProcedureEventByPage(Integer pageNumber, Integer pageSize) {

		final Session session = openSession();

		Query<ProcedureEvent> query = session.createQuery("from ProcedureEvent", ProcedureEvent.class);

		query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);

		List<ProcedureEvent> procedures = query.list();
		closeSession(session);
		return procedures;

	}

	/**
	 * @see ProcedureService#getProcedureEvent(Integer)
	 */
	@Override
	public ProcedureEvent getProcedureEvent(Integer rowId) {
		final Session session = openSession();
		ProcedureEvent procedure = null;
		try {
			procedure = session.get(ProcedureEvent.class, rowId);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			return null;
		}
		closeSession(session);
		return procedure;
	}

	/**
	 * @see ProcedureService#saveOrUpdateProcedureEvent(ProcedureEvent)
	 */
	@Override
	public ProcedureEvent saveOrUpdateProcedureEvent(ProcedureEvent cptEvent) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(cptEvent);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
		}
		closeSession(session);
		return cptEvent;
	}

	/**
	 * @see ProcedureService#deleteProcedureEvent(Integer)
	 */
	@Override
	public void deleteProcedureEvent(Integer rowId) {
		final Session session = openSession();
		try {
			ProcedureEvent procedure = session.get(ProcedureEvent.class, rowId);
			session.delete(procedure);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
		}
		closeSession(session);
	}

}
