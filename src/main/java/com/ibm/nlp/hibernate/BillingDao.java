package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.nlp.model.mimic3.CptEvent;
import com.ibm.nlp.model.mimic3.DrgCode;
import com.ibm.nlp.model.mimic3.ProceduresICD;
import com.ibm.nlp.services.BillingService;

/**
 * {@link BillingService}
 */
/**
 * /** The Class BillingDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class BillingDao extends HibernateDao implements BillingService {

	private Logger logger = LoggerFactory.getLogger(BillingDao.class);

	public BillingDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see BillingService#getAllCptEvents()
	 */
	@Override
	public List<CptEvent> getAllCptEvents() {
		Session session = null;
		List<CptEvent> eventList = null;
		try {
			session = openSession();
			eventList = HibernateUtil.loadAllData(CptEvent.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.loadAllData()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return eventList;
	}

	/**
	 * @see BillingService#getAllCptEventsForPatient(Integer)
	 */
	@Override
	public List<CptEvent> getAllCptEventsForPatient(Integer subjectId) {
		Session session = null;
		List<CptEvent> cptEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CptEvent> criteriaQuery = builder.createQuery(CptEvent.class);
			Root<CptEvent> root = criteriaQuery.from(CptEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			cptEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllCptEventsForPatient(" + subjectId + ")\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return cptEvents;
	}

	/**
	 * @see BillingService#getAllCptEventsForAdmission(Integer)
	 */
	@Override
	public List<CptEvent> getAllCptEventsForAdmission(Integer hadmId) {
		Session session = null;
		List<CptEvent> cptEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CptEvent> criteriaQuery = builder.createQuery(CptEvent.class);
			Root<CptEvent> root = criteriaQuery.from(CptEvent.class);
			criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
			cptEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllCptEventsForAdmission(" + hadmId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return cptEvents;
	}

	/**
	 * @see BillingService#searchCptEventsForTerm(String)
	 */
	@Override
	public List<CptEvent> searchCptEventsForTerm(String searchTerm) {
		Session session = null;
		List<CptEvent> cptEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<CptEvent> criteriaQuery = builder.createQuery(CptEvent.class);
			Root<CptEvent> root = criteriaQuery.from(CptEvent.class);
			criteriaQuery.select(root).where(builder.like(root.get("description"), "%" + searchTerm + "%"));
			cptEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.searchCptEventsForTerm(" + searchTerm + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return cptEvents;
	}

	/**
	 * @see BillingService#getCptEvent(Integer)
	 */
	@Override
	public CptEvent getCptEvent(Integer rowId) {
		final Session session = openSession();
		CptEvent billingEvent = null;
		try {
			billingEvent = session.get(CptEvent.class, rowId);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getCptEvent(" + rowId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return billingEvent;
	}

	/**
	 * @see BillingService#saveOrUpdateCptEvent(CptEvent)
	 */
	@Override
	public CptEvent saveOrUpdateCptEvent(CptEvent cptEvent) {
		Session session = null;
		try {
			session = openSession();
			session.saveOrUpdate(cptEvent);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("BillingDao.saveOrUpdateCptEvent(" + cptEvent + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return cptEvent;
	}

	/**
	 * @see BillingService#deleteCptEvent(Integer)
	 */
	@Override
	public void deleteCptEvent(Integer rowId) {
		Session session = null;
		try {
			session = openSession();
			CptEvent billingEvent = session.get(CptEvent.class, rowId);
			session.delete(billingEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.deleteCptEvent(" + rowId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see BillingService#getAllDrgCodes()
	 */
	@Override
	public List<DrgCode> getAllDrgCodes() {
		Session session = null;
		List<DrgCode> eventList = null;
		try {
			session = openSession();
			eventList = HibernateUtil.loadAllData(DrgCode.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllDrgCodes()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return eventList;
	}

	/**
	 * @see BillingService#getAllDrgCodesByDrgType(String)
	 */
	@Override
	public List<DrgCode> getAllDrgCodesByDrgType(String drgType) {
		Session session = null;
		List<DrgCode> drgEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<DrgCode> criteriaQuery = builder.createQuery(DrgCode.class);
			Root<DrgCode> root = criteriaQuery.from(DrgCode.class);
			criteriaQuery.select(root).where(builder.equal(root.get("drgType"), drgType));
			drgEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllDrgCodesByDrgType(" + drgType + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return drgEvents;
	}

	/**
	 * @see BillingService#getAllDrgCodesForPatient(Integer)
	 */
	@Override
	public List<DrgCode> getAllDrgCodesForPatient(Integer subjectId) {
		Session session = null;
		List<DrgCode> drgEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<DrgCode> criteriaQuery = builder.createQuery(DrgCode.class);
			Root<DrgCode> root = criteriaQuery.from(DrgCode.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			drgEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllDrgCodesForPatient(" + subjectId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return drgEvents;
	}

	/**
	 * @see BillingService#getAllDrgCodesForAdmission(Integer)
	 */
	@Override
	public List<DrgCode> getAllDrgCodesForAdmission(Integer hadmId) {
		Session session = null;
		List<DrgCode> drgEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<DrgCode> criteriaQuery = builder.createQuery(DrgCode.class);
			Root<DrgCode> root = criteriaQuery.from(DrgCode.class);
			criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
			drgEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllDrgCodesForAdmission(" + hadmId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return drgEvents;
	}

	/**
	 * @see BillingService#getDrgCode(Integer)
	 */
	@Override
	public DrgCode getDrgCode(Integer rowId) {
		final Session session = openSession();
		DrgCode billingEvent = null;
		try {
			billingEvent = session.get(DrgCode.class, rowId);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getDrgCode(" + rowId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return billingEvent;
	}

	/**
	 * @see BillingService#saveOrUpdateDrgCode(DrgCode)
	 */
	@Override
	public DrgCode saveOrUpdateDrgCode(DrgCode drgEvent) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(drgEvent);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("BillingDao.saveOrUpdateDrgCode(" + drgEvent + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);

		return drgEvent;
	}

	/**
	 * @see BillingService#deleteDrgCode(Integer)
	 */
	@Override
	public void deleteDrgCode(Integer rowId) {
		final Session session = openSession();
		try {
			DrgCode billingEvent = session.get(DrgCode.class, rowId);
			session.delete(billingEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.deleteDrgCode(" + rowId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see BillingService#getAllProceduresICDs()
	 */
	@Override
	public List<ProceduresICD> getAllProceduresICDs() {
		Session session = null;
		List<ProceduresICD> eventList = null;
		try {
			session = openSession();
			eventList = HibernateUtil.loadAllData(ProceduresICD.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllProceduresICDs()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return eventList;
	}

	/**
	 * @see BillingService#getAllProceduresICDsForPatient(Integer)
	 */
	@Override
	public List<ProceduresICD> getAllProceduresICDsForPatient(Integer subjectId) {
		Session session = null;
		List<ProceduresICD> drgEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ProceduresICD> criteriaQuery = builder.createQuery(ProceduresICD.class);
			Root<ProceduresICD> root = criteriaQuery.from(ProceduresICD.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			drgEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllProceduresICDsForPatient(" + subjectId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return drgEvents;
	}

	/**
	 * @see BillingService#getAllProceduresICDsForAdmission(Integer)
	 */
	@Override
	public List<ProceduresICD> getAllProceduresICDsForAdmission(Integer hadmId) {
		Session session = null;
		List<ProceduresICD> drgEvents = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<ProceduresICD> criteriaQuery = builder.createQuery(ProceduresICD.class);
			Root<ProceduresICD> root = criteriaQuery.from(ProceduresICD.class);
			criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
			drgEvents = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getAllProceduresICDsForAdmission(" + hadmId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return drgEvents;
	}

	/**
	 * @see BillingService#getProceduresICD(Integer)
	 */
	@Override
	public ProceduresICD getProceduresICD(Integer rowId) {
		final Session session = openSession();
		ProceduresICD billingEvent = null;
		try {
			billingEvent = session.get(ProceduresICD.class, rowId);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.getProceduresICD(" + rowId + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return billingEvent;
	}

	/**
	 * @see BillingService#getProceduresICD(ProceduresICD)
	 */
	@Override
	public ProceduresICD saveOrUpdateProceduresICD(ProceduresICD procedureIcd) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(procedureIcd);
		} catch (Exception e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("BillingDao.saveOrUpdateProceduresICD(" + procedureIcd + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);

		return procedureIcd;
	}

	/**
	 * @see BillingService#deleteProceduresICD(Integer)
	 */
	@Override
	public void deleteProceduresICD(Integer rowId) {
		final Session session = openSession();
		try {
			ProceduresICD billingEvent = session.get(ProceduresICD.class, rowId);
			session.delete(billingEvent);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("BillingDao.deleteProceduresICD(" + rowId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

}
