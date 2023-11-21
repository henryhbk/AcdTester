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

import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.IcuStay;
import com.ibm.nlp.model.mimic3.TransferringService;
import com.ibm.nlp.services.AdmissionService;

/**
 * The Class AdmissionDb2Dao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class AdmissionDb2Dao extends HibernateDB2Dao implements AdmissionService {

	/**
	 * Instantiates a new admission dao for DB2.
	 */
	public AdmissionDb2Dao() {
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
		}
		closeSession(session);
	}

	/**
	 * @see AdmissionService#getAllAdmissionForPatient(Integer)
	 */
	@Override
	public List<Admission> getAllAdmissionForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Admission> criteriaQuery = builder.createQuery(Admission.class);
		Root<Admission> root = criteriaQuery.from(Admission.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<Admission> admissions = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return admissions;
	}

	/**
	 * @see AdmissionService#getTransferringServicesForPatient(Integer)
	 */
	@Override
	public List<TransferringService> getTransferringServicesForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
		Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<TransferringService> services = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getTransferringServicesForAdmission(Integer)
	 */
	@Override
	public List<TransferringService> getTransferringServicesForAdmission(Integer hadmId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
		Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
		criteriaQuery.select(root).where(builder.equal(root.get("hadmId"), hadmId));
		List<TransferringService> services = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getTransferringFromServicesForService(String)
	 */
	@Override
	public List<TransferringService> getTransferringFromServicesForService(String serviceName) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
		Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
		criteriaQuery.select(root).where(builder.equal(root.get("PreviousService"), serviceName));
		List<TransferringService> services = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getTransferringToServicesForService(String)
	 */
	@Override
	public List<TransferringService> getTransferringToServicesForService(String serviceName) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<TransferringService> criteriaQuery = builder.createQuery(TransferringService.class);
		Root<TransferringService> root = criteriaQuery.from(TransferringService.class);
		criteriaQuery.select(root).where(builder.equal(root.get("CurrentService"), serviceName));
		List<TransferringService> services = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return services;
	}

	/**
	 * @see AdmissionService#getListOfServiceNames()
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public Set<String> getListOfServiceNames() {
		Criteria criteria = session.createCriteria(TransferringService.class);
		criteria.setProjection(Projections.distinct(Projections.property("previousService")));
		List<String> serviceList = criteria.list();

		Set<String> serviceNames = new HashSet<String>();

		serviceNames.addAll(serviceList);
		/*
		 * OK this is somewhat goofy but the two lists of services are not the same
		 * (logically some poeple only take patients from the ICU or only put people
		 * into the ICU (like the ED would never take someone out of the ICU
		 */
		criteria.setProjection(Projections.distinct(Projections.property("currentService")));
		List<String> currServiceList = criteria.list();
		// in theory this works as addAll removes duplicates in a HashSet (since by
		// definition all hashset entries are unique)
		serviceNames.addAll(currServiceList);
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
		}
		closeSession(session);
	}

	/**
	 * @see AdmissionService#getAllIcuStayForPatient(Integer)
	 */
	@Override
	public List<IcuStay> getAllIcuStayForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<IcuStay> criteriaQuery = builder.createQuery(IcuStay.class);
		Root<IcuStay> root = criteriaQuery.from(IcuStay.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<IcuStay> stays = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return stays;
	}

}
