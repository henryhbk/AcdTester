package com.ibm.nlp.hibernate;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.ibm.nlp.model.mimic3.SocialHistory;
import com.ibm.nlp.services.SocialHistoryService;

/**
 * The Class SocialHistoryDao.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class SocialHistoryDao extends HibernateDao implements SocialHistoryService {

	/**
	 * Gets the all social histories.
	 *
	 * @return the all social histories
	 */
	@Override
	public List<SocialHistory> getAllSocialHistories() {
		final Session session = openSession();
		List<SocialHistory> socialHistories = HibernateUtil.loadAllData(SocialHistory.class, session);
		closeSession(session);
		return socialHistories;
	}

	/**
	 * Gets the social history for patient.
	 *
	 * @param subjectId the subject id
	 * @return the social history for patient
	 */
	@Override
	public List<SocialHistory> getSocialHistoryForPatient(Integer subjectId) {
		final Session session = openSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<SocialHistory> criteriaQuery = builder.createQuery(SocialHistory.class);
		Root<SocialHistory> root = criteriaQuery.from(SocialHistory.class);
		criteriaQuery.select(root).where(builder.equal(root.get("subjectId"), subjectId));
		List<SocialHistory> noteEvents = session.createQuery(criteriaQuery).getResultList();
		closeSession(session);
		return noteEvents;
	}

	/**
	 * Save socialhistory.
	 *
	 * @param socialHistory the social history
	 * @return the social history
	 */
	@Override
	public SocialHistory saveSocialhistory(SocialHistory socialHistory) {
		final Session session = openSession();
		session.saveOrUpdate(socialHistory);
		closeSession(session);
		return null;
	}

}
