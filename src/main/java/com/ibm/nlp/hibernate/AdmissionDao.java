package com.ibm.nlp.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.IcuStay;
import com.ibm.nlp.model.mimic3.TransferringService;
import com.ibm.nlp.services.AdmissionService;

/**
 * {@link AdmissionService}
 */
/**
 * The Class AdmissionDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class AdmissionDao extends HibernateDao implements AdmissionService {

	private Logger logger = LoggerFactory.getLogger(AdmissionDao.class);

	/**
	 * Instantiates a new admission dao.
	 */
	public AdmissionDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see AdmissionService#getAdmission(Integer)
	 */
	@Override
	public Admission getAdmission(Integer id) {
		final Session session = openSession();
		Admission admission = null;
		try {
			admission = session.get(Admission.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getAdmission(" + id + ")\n" + e.getLocalizedMessage());
			return null;

		}
		closeSession(session);
		return admission;

	}

	/**
	 * @see AdmissionService#getAllAdmission()
	 */
	@Override
	public List<Admission> getAllAdmission() {
		final Session session = openSession();
		List<Admission> admissionList = null;
		try {
			admissionList = HibernateUtil.loadAllData(Admission.class, session);
		} catch (HibernateException e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("AdmissionDao.getAllAdmission()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return admissionList;
	}

	/**
	 * @see AdmissionService#saveAdmission(Admission)
	 */
	@Override
	public Admission saveAdmission(Admission admission) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(admission);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getAdmission(" + admission + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);

		return admission;
	}

	/**
	 * @see AdmissionService#deleteAdmission(Integer)
	 */
	@Override
	public void deleteAdmission(Integer id) {
		final Session session = openSession();
		try {
			Admission admission = session.get(Admission.class, id);
			session.delete(admission);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.deleteAdmission(" + id + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see AdmissionService#getAllAdmissionForPatient(Integer)
	 */
	@Override
	public List<Admission> getAllAdmissionForPatient(Integer subjectId) {
		final Session session = openSession();
		List<Admission> admissions = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Admission> criteriaQuery = builder.createQuery(Admission.class);
			Root<Admission> root = criteriaQuery.from(Admission.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			admissions = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getAllAdmissionForPatient(" + subjectId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return admissions;
	}

	/**
	 * @see AdmissionService#getTransferringServicesForPatient(Integer)
	 */
	@Override
	public List<TransferringService> getTransferringServicesForPatient(Integer subjectId) {
		final Session session = openSession();
		List<TransferringService> services = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
			Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			services = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error(
					"AdmissionDao.getTransferringServicesForPatient(" + subjectId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getTransferringServicesForAdmission(Integer)
	 */
	@Override
	public List<TransferringService> getTransferringServicesForAdmission(Integer hadmId) {
		final Session session = openSession();
		List<TransferringService> services = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
			Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
			criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
			services = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getTransferringServicesForPatient(" + hadmId + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getTransferringFromServicesForService(String)
	 */
	@Override
	public List<TransferringService> getTransferringFromServicesForService(String serviceName) {
		final Session session = openSession();
		List<TransferringService> services = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
			Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
			criteriaQuery.select(root).where(builder.equal(root.get("previousService"), serviceName));
			services = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getTransferringFromServicesForService(" + serviceName + ")\n"
					+ e.getLocalizedMessage());
		}
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getTransferringToServicesForService(String)
	 */
	@Override
	public List<TransferringService> getTransferringToServicesForService(String serviceName) {
		final Session session = openSession();
		List<TransferringService> services = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
			Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
			criteriaQuery.select(root).where(builder.equal(root.get("currentService"), serviceName));
			services = session.createQuery(criteriaQuery).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getTransferringToServicesForService(" + serviceName + ")\n"
					+ e.getLocalizedMessage());
		}
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getListOfServiceNames()
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Set<String> getListOfServiceNames() {
		List<String> currServiceList = null;
		Set<String> serviceNames = null;
		try {
			Criteria criteria = session.createCriteria(TransferringService.class);
			criteria.setProjection(Projections.distinct(Projections.property("previousService")));
			List<String> serviceList = criteria.list();

			serviceNames = new HashSet<String>();

			serviceNames.addAll(serviceList);
			/*
			 * OK this is somewhat goofy but the two lists of services are not the same
			 * (logically some poeple only take patients from the ICU or only put people
			 * into the ICU (like the ED would never take someone out of the ICU
			 */
			criteria.setProjection(Projections.distinct(Projections.property("currentService")));
			currServiceList = criteria.list();
			// in theory this works as addAll removes duplicates in a HashSet (since by
			// definition all hashset entries are unique)
			serviceNames.addAll(currServiceList);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getListOfServiceNames()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return serviceNames;
	}

	/**
	 * @see AdmissionService#getIcuStay(Integer)
	 */
	@Override
	public IcuStay getIcuStay(Integer id) {
		final Session session = openSession();
		IcuStay stay = null;
		try {
			stay = session.get(IcuStay.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getIcuStay((\"" + id + "\")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return stay;

	}

	/**
	 * @see AdmissionService#getAllIcuStay()
	 */
	@Override
	public List<IcuStay> getAllIcuStay() {
		final Session session = openSession();
		List<IcuStay> stays = null;
		try {
			stays = HibernateUtil.loadAllData(IcuStay.class, session);
		} catch (HibernateException e) {
			rollbackSession(session);
			e.printStackTrace();
			logger.error("AdmissionDao.getAllIcuStay()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return stays;
	}

	/**
	 * @see AdmissionService#saveIcuStay(IcuStay)
	 */
	@Override
	public IcuStay saveIcuStay(IcuStay icuStay) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(icuStay);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.saveIcuStay((\"" + icuStay + "\")\n" + e.getLocalizedMessage());
		}
		closeSession(session);

		return icuStay;
	}

	/**
	 * @see AdmissionService#deleteIcuStay(Integer)
	 */
	@Override
	public void deleteIcuStay(Integer id) {
		final Session session = openSession();
		try {
			IcuStay stay = session.get(IcuStay.class, id);
			session.delete(stay);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.deleteIcuStay(\"" + id + "\")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

	/**
	 * @see AdmissionService#getAllIcuStayForPatient(Integer)
	 */
	@Override
	public List<IcuStay> getAllIcuStayForPatient(Integer subjectId) {
		Session session = null;
		List<IcuStay> stays = null;
		try {
			session = openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<IcuStay> criteriaQuery = builder.createQuery(IcuStay.class);
			Root<IcuStay> root = criteriaQuery.from(IcuStay.class);
			criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
			stays = session.createQuery(criteriaQuery).getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("AdmissionDao.getAllIcuStayForPatient(\"" + subjectId + "\")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return stays;
	}

}
