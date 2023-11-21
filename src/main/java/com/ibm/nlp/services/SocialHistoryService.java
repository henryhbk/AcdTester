package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.SocialHistory;

// TODO: Auto-generated Javadoc
/**
 * The Interface SocialHistoryService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface SocialHistoryService {

	/**
	 * Gets the all social histories.
	 *
	 * @return the all social histories
	 */
	public List<SocialHistory> getAllSocialHistories();

	/**
	 * Gets the social history for patient.
	 *
	 * @param subjectId the subject id
	 * @return the social history for patient
	 */
	public List<SocialHistory> getSocialHistoryForPatient(Integer subjectId);

	/**
	 * Save socialhistory.
	 *
	 * @param socialHistory the social history
	 * @return the social history
	 */
	public SocialHistory saveSocialhistory(SocialHistory socialHistory);
}
