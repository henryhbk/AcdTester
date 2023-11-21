package com.ibm.nlp.hibernate;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.services.PrescriptionService;

/**
 * The Class PrescriptionDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class PrescriptionDao extends HibernateDao implements PrescriptionService {

	/** The new date. */
	private Date newDate = new Date();

	/**
	 * Instantiates a new prescription dao.
	 */
	public PrescriptionDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * Gets the prescription.
	 *
	 * @param id the id
	 * @return the prescription
	 */
	@Override
	public Prescription getPrescription(Integer id) {
		final Session session = openSession();
		session.get(Prescription.class, id);
		Prescription prescription = null;
		try {
			prescription = session.get(Prescription.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			return null;
		}
		closeSession(session);
		return prescription;
	}

	/**
	 * Gets the all prescriptions.
	 *
	 * @return the all prescriptions
	 */
	@Override
	public List<Prescription> getAllPrescriptions() {
		final Session session = openSession();
		List<Prescription> prescriptionList = HibernateUtil.loadAllData(Prescription.class, session);
		closeSession(session);
		return prescriptionList;
	}

	/**
	 * Gets the all prescriptions for acd study.
	 *
	 * @return the all prescriptions for acd study
	 */
	@Override
	public List<Prescription> getAllPrescriptionsForAcdStudy() {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Prescription> criteriaQuery = builder.createQuery(Prescription.class);
		Root<Prescription> root = criteriaQuery.from(Prescription.class);
		criteriaQuery.select(root).where(builder.equal(root.get("acdStudyMed"), true));
		List<Prescription> prescriptionList = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return prescriptionList;
	}

	/**
	 * Save prescription.
	 *
	 * @param prescription the prescription
	 * @return the prescription
	 */
	@Override
	public Prescription savePrescription(Prescription prescription) {
		final Session session = openSession();
		session.saveOrUpdate(prescription);
		closeSession(session);
		return null;
	}

	/**
	 * Gets the future prescriptions.
	 *
	 * @return the future prescriptions
	 */
	public List<Prescription> getFuturePrescriptions() {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Prescription> criteriaQuery = builder.createQuery(Prescription.class);
		Root<Prescription> root = criteriaQuery.from(Prescription.class);
		criteriaQuery.select(root).where(builder.greaterThanOrEqualTo(root.get("startdate"), newDate));
		List<Prescription> prescriptionList = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return prescriptionList;
	}

	/**
	 * Delete prescription.
	 *
	 * @param id the id
	 */
	@Override
	public void deletePrescription(Integer id) {
		// TODO Auto-generated method stub
		final Session session = openSession();
		try {
			Prescription element = session.get(Prescription.class, id);
			session.delete(element);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
		}
		closeSession(session);
	}

	/**
	 * Gets the all prescriptions for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all prescriptions for patient
	 */
	@Override
	public List<Prescription> getAllPrescriptionsForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Prescription> criteriaQuery = builder.createQuery(Prescription.class);
		Root<Prescription> root = criteriaQuery.from(Prescription.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<Prescription> prescriptions = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return prescriptions;
	}

	@Override
	public List<Prescription> getPrescriptionsForRxNormId(String rxNormId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Prescription> criteriaQuery = builder.createQuery(Prescription.class);
		Root<Prescription> root = criteriaQuery.from(Prescription.class);
		criteriaQuery.select(root).where(builder.equal(root.get("rxNormCui"), rxNormId));
		List<Prescription> prescriptionList = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return prescriptionList;
	}

	@Override
	public List<Prescription> searchPrescriptionForDrugName(String drugName) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Prescription> criteriaQuery = builder.createQuery(Prescription.class);
		Root<Prescription> root = criteriaQuery.from(Prescription.class);
		criteriaQuery.select(root).where(builder.like(root.get("drugName"), "%" + drugName + "%"));
		List<Prescription> prescriptionList = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return prescriptionList;
	}
}
