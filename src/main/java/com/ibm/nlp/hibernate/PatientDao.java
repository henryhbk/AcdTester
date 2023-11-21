package com.ibm.nlp.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;

import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.SearchPatient;
import com.ibm.nlp.services.PatientService;

/**
 * {@link PatientService}
 */

/**
 * The Class PatientDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class PatientDao extends HibernateDao implements PatientService {

	/**
	 * Instantiates a new patient dao.
	 */
	public PatientDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see PatientService#getPatient(Integer)
	 */
	@Override
	public Patients getPatient(Integer id) {
		final Session session = openSession();
		session.get(Patients.class, id);
		Patients patient = null;
		try {
			patient = session.get(Patients.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			return null;
		}
		closeSession(session);
		return patient;
	}

	/**
	 * @see PatientService#getAllPatients()
	 */
	@Override
	public List<Patients> getAllPatients() {
		final Session session = openSession();
		List<Patients> patientList = HibernateUtil.loadAllData(Patients.class, session);
		closeSession(session);
		return patientList;
	}

	/**
	 * @see PatientService#savePatient(Patients)
	 */
	@Override
	public Patients savePatient(Patients patient) {
		final Session session = openSession();
		session.saveOrUpdate(patient);
		closeSession(session);
		return null;
	}

	/**
	 * @see PatientService#deletePatient(Integer)
	 */
	@Override
	public void deletePatient(Integer id) {
		final Session session = openSession();
		// TODO Auto-generated method stub
		closeSession(session);
	}

	/**
	 * @see PatientService#getAllPatientsForAcdStudy()
	 */
	@Override
	public List<Patients> getAllPatientsForAcdStudy() {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Patients> criteriaQuery = builder.createQuery(Patients.class);
		Root<Patients> root = criteriaQuery.from(Patients.class);
		criteriaQuery.select(root).where(builder.equal(root.get("acdStudyPatient"), true));
		List<Patients> patients = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return patients;
	}

	/**
	 * @see PatientService#getPatientBySubjectId(Integer)
	 */
	@Override
	public Patients getPatientBySubjectId(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Patients> criteriaQuery = builder.createQuery(Patients.class);
		Root<Patients> root = criteriaQuery.from(Patients.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		Patients patient = session.createQuery(criteriaQuery).uniqueResult();
		closeSession(session);
		return patient;
	}

	/**
	 * @see PatientService#liveOracleMultiFieldsSearchForPatients(List<FieldValueMapper>)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<SearchPatient> liveOracleMultiFieldsSearchForPatients(List<FieldValueMapper> fieldValueMapper)
			throws IllegalArgumentException {

		final Session session = openSession();

		final StringBuilder queryStr = new StringBuilder();
		queryStr.append("from SearchPatient p ");
		int counter = 0;
		final List<String> searchStrArray = new ArrayList<String>();
		final ArrayList<Integer> intArray = new ArrayList<Integer>();

		final List<SearchPatient> hits;

		int intCounter = 0;

		for (final FieldValueMapper valueMapper : fieldValueMapper) {

			if (counter == 0) {
				queryStr.append(" where ");
			} else {
				queryStr.append(" and ");
			}

			if (valueMapper.getFieldName().equals("p.last")) {
				queryStr.append(" p.lastName like :searchStr").append(counter);
				searchStrArray.add("%" + valueMapper.getFieldValue() + "%");
			}

			if (valueMapper.getFieldName().equals("p.first")) {
				queryStr.append(" p.firstName like :searchStr").append(counter);
				searchStrArray.add("%" + valueMapper.getFieldValue() + "%");

			}

			if (valueMapper.getFieldName().equals("p.gender")) {
				queryStr.append(" p.gender like :searchStr").append(counter);
				searchStrArray.add(valueMapper.getFieldValue());
			}

			if (valueMapper.getFieldName().equals("p.dob")) {
				queryStr.append(" cast(aes_decrypt(p.dob, :key) as string) like :searchStr").append(counter);
				searchStrArray.add(valueMapper.getFieldValue());

			}

			if (valueMapper.getFieldName().equals("p.address")) {
				queryStr.append("p.street like :searchStr").append(counter);
				counter++;
				searchStrArray.add("%" + valueMapper.getFieldValue() + "%");
				queryStr.append(" OR p.city like :searchStr").append(counter);
				counter++;
				searchStrArray.add("%" + valueMapper.getFieldValue() + "%");
				queryStr.append(" OR p.zip like :searchStr").append(counter);
				searchStrArray.add("%" + valueMapper.getFieldValue() + "%");
			}

			counter++;
		}

		queryStr.append(" order by p.lastName");

		final Query<SearchPatient> advancedSearchQuery = session.createQuery(queryStr.toString());

		counter = 0;
		for (final String valueStr : searchStrArray) {
			advancedSearchQuery.setString("searchStr" + counter, valueStr);
			counter++;
		}
		intCounter = 0;
		for (final Integer valueInt : intArray) {
			advancedSearchQuery.setInteger("searchInt" + intCounter, valueInt);
			intCounter++;
		}
		hits = advancedSearchQuery.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();

		closeSession(session);

		return hits;

	}

}
