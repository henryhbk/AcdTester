package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.ProcedureEvent;

/**
 * The Interface ProcedureService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface ProcedureService {

	/**
	 * Gets the all procedure event (<b>note this is a massive table</b>, you should
	 * probably use the <i>getAllProcedureEventByPage</i> method).
	 *
	 * @return the all procedure event
	 */
	List<ProcedureEvent> getAllProcedureEvent();

	/**
	 * Gets the all procedure event for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all procedure event for patient
	 */
	List<ProcedureEvent> getAllProcedureEventForPatient(Integer subjectId);

	/**
	 * Gets the all procedure event for admission.
	 *
	 * @param hadmId the hadm id
	 * @return the all procedure event for admission
	 */
	List<ProcedureEvent> getAllProcedureEventForAdmission(Integer hadmId);

	/**
	 * Search procedure event for term.
	 *
	 * @param searchTerm the search term
	 * @return the list
	 */
	List<ProcedureEvent> searchProcedureEventForTerm(String searchTerm);

	/**
	 * Gets the all procedure event by page, so you don't try and pull in gigabytes
	 * of entities. <b>Remember PAGE STARTS AT 1</b>
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the all procedure event by page
	 */
	List<ProcedureEvent> getAllProcedureEventByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the procedure event.
	 *
	 * @param rowId the row id
	 * @return the procedure event
	 */
	ProcedureEvent getProcedureEvent(Integer rowId);

	/**
	 * Save or update procedure event.
	 *
	 * @param cptEvent the cpt event
	 * @return the procedure event
	 */
	ProcedureEvent saveOrUpdateProcedureEvent(ProcedureEvent cptEvent);

	/**
	 * Delete procedure event.
	 *
	 * @param rowId the row id
	 */
	void deleteProcedureEvent(Integer rowId);

}
