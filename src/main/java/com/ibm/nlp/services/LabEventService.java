package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.LabEvent;
import com.ibm.nlp.model.mimic3.LabItem;
import com.ibm.nlp.model.mimic3.MicrobiolgyEvent;

/**
 * The Interface LabEventService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface LabEventService {

	/**
	 * Gets the annotation.
	 *
	 * @param id the id
	 * @return the annotation
	 */
	LabEvent getLabEvent(Integer id);

	/**
	 * Gets the all annotations.
	 *
	 * @return the all annotations
	 */
	List<LabEvent> getAllLabEvents();

	/**
	 * Gets the all LabEvents for Acd study (.e. the acd_study == true)
	 *
	 * @return the all LabEvents for Acd study
	 */
	List<LabEvent> getAllLabEventsForAcdStudy();

	/**
	 * Save annotation.
	 *
	 * @param labEvent the lab event
	 * @return the annotation
	 */
	LabEvent saveLabEvent(LabEvent labEvent);

	/**
	 * Delete annotation.
	 *
	 * @param id the id
	 */
	void deleteLabEvent(Integer id);

	/**
	 * Gets the all lab items (definitions, where the name/LOINC code are).
	 *
	 * @return the all lab items
	 */
	List<LabItem> getAllLabItems();

	/**
	 * Gets the lab events by page as the table is 27m records long.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the lab events by page
	 */
	List<LabEvent> getLabEventsByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the microbiology events.
	 *
	 * @return the microbiology events
	 */
	List<MicrobiolgyEvent> getMicrobiologyEvents();

	/**
	 * Gets the microbiology events.
	 *
	 * @param rowId the row id
	 * @return the microbiology events
	 */
	MicrobiolgyEvent getMicrobiologyEvent(Integer rowId);

	/**
	 * Gets the microbiology events for patient.
	 *
	 * @param subjectId the subject id
	 * @return the microbiology events for patient
	 */
	List<MicrobiolgyEvent> getMicrobiologyEventsForPatient(Integer subjectId);

	/**
	 * Save or update microbiology event.
	 *
	 * @param microbiologyEvent the microbiology event
	 * @return the microbiolgy event
	 */
	MicrobiolgyEvent saveOrUpdateMicrobiologyEvent(MicrobiolgyEvent microbiologyEvent);

	/**
	 * Delete microbiology event.
	 *
	 * @param rowId the row id
	 */
	void deleteMicrobiologyEvent(Integer rowId);

}
