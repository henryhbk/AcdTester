package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.MedicationAdverseEvent;

/**
 * The Interface MedicationAdverseEventService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface MedicationAdverseEventService {

	/**
	 * Gets the medication adverse event.
	 *
	 * @param id the id
	 * @return the medication adverse event
	 */
	MedicationAdverseEvent getMedicationAdverseEvent(Integer id);

	/**
	 * Gets the medication adverse events.
	 *
	 * @return the medication adverse events
	 */
	List<MedicationAdverseEvent> getMedicationAdverseEvents();

	/**
	 * Save medication adverse event.
	 *
	 * @param medicationAdverseEvent the medication adverse event
	 * @return the medication adverse event
	 */
	MedicationAdverseEvent saveMedicationAdverseEvent(MedicationAdverseEvent medicationAdverseEvent);

	/**
	 * Delete medication adverse event.
	 *
	 * @param id the id
	 */
	void deleteMedicationAdverseEvent(Integer id);

	/**
	 * Truncate medication adverse events table for a new run.
	 */
	void truncateMedicationAdverseEvents();

	/**
	 * Search medication adverse events for text.
	 *
	 * @param searchString the search string
	 * @return the list
	 */
	List<MedicationAdverseEvent> searchMedicationAdverseEventsForText(String searchString);

	/**
	 * Gets the ade medication names.
	 *
	 * @return the ade medication names
	 */
	List<String> getAdeMedicationNames();
}
