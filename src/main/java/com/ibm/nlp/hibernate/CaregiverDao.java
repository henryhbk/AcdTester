package com.ibm.nlp.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.nlp.model.mimic3.Caregiver;
import com.ibm.nlp.services.CaregiverService;
import com.ibm.nlp.services.ProcedureService;

/**
 * {@link CaregiverService}
 */
/**
 * The Class CaregiverDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class CaregiverDao extends HibernateDao implements CaregiverService {

	private Logger logger = LoggerFactory.getLogger(CaregiverDao.class);

	/**
	 * Instantiates a new caregiver dao.
	 */
	public CaregiverDao() {
		super();
		if (session.getTransaction().isActive()) {
			closeSession(session);
		}
	}

	/**
	 * @see ProcedureService#getCaregiver(Integer)
	 */
	@Override
	public Caregiver getCaregiver(Integer id) {
		final Session session = openSession();
		Caregiver caregiver = null;
		try {
			caregiver = session.get(Caregiver.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("CaregiverDao.getCaregiver(" + id + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return caregiver;
	}

	/**
	 * @see ProcedureService#getAllCaregivers()
	 */
	@Override
	public List<Caregiver> getAllCaregivers() {
		Session session = null;
		List<Caregiver> careGiverList = null;
		try {
			session = openSession();
			careGiverList = HibernateUtil.loadAllData(Caregiver.class, session);
		} catch (HibernateException e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("CaregiverDao.getAllCaregivers()\n" + e.getLocalizedMessage());
		}
		closeSession(session);
		return careGiverList;
	}

	/**
	 * @see ProcedureService#saveCaregiver(Caregiver)
	 */
	@Override
	public Caregiver saveCaregiver(Caregiver caregiver) {
		final Session session = openSession();
		try {
			session.saveOrUpdate(caregiver);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("CaregiverDao.saveCaregiver(" + caregiver + ")\n" + e.getLocalizedMessage());
			return null;
		}
		closeSession(session);
		return caregiver;
	}

	/**
	 * @see ProcedureService#deleteCaregiver(Integer)
	 */
	@Override
	public void deleteCaregiver(Integer id) {
		Session session = null;
		try {
			session = openSession();
			Caregiver careGiver = session.get(Caregiver.class, id);
			session.delete(careGiver);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackSession(session);
			logger.error("CaregiverDao.deleteCaregiver(" + id + ")\n" + e.getLocalizedMessage());
		}
		closeSession(session);
	}

}
