package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.CptEvent;
import com.ibm.nlp.model.mimic3.DrgCode;
import com.ibm.nlp.model.mimic3.ProceduresICD;

/**
 * The Interface BillingService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface BillingService {

	/**
	 * Gets the all cpt events.
	 *
	 * @return the all cpt events
	 */
	List<CptEvent> getAllCptEvents();

	/**
	 * Gets the all cpt events for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all cpt events for patient
	 */
	List<CptEvent> getAllCptEventsForPatient(Integer subjectId);

	/**
	 * Gets the all cpt events for admission.
	 *
	 * @param hadmId the hadm id
	 * @return the all cpt events for admission
	 */
	List<CptEvent> getAllCptEventsForAdmission(Integer hadmId);

	/**
	 * Search cpt events for term in the description field (for example ventilation
	 * shares one CPT code regardless of whether it is full or non-invasive, so this
	 * is how to search for those). This field has a full text-index so searching is
	 * fast.
	 *
	 * @param searchTerm the search term
	 * @return the list
	 */
	List<CptEvent> searchCptEventsForTerm(String searchTerm);

	/**
	 * Gets the cpt event.
	 *
	 * @param rowId the row id
	 * @return the cpt event
	 */
	CptEvent getCptEvent(Integer rowId);

	/**
	 * Save or update cpt event.
	 *
	 * @param cptEvent the cpt event
	 * @return the cpt event
	 */
	CptEvent saveOrUpdateCptEvent(CptEvent cptEvent);

	/**
	 * Delete cpt event.
	 *
	 * @param rowId the row id
	 */
	void deleteCptEvent(Integer rowId);

	/**
	 * Gets the all drg codes.
	 *
	 * @return the all drg codes
	 */
	List<DrgCode> getAllDrgCodes();

	/**
	 * Gets the all drg codes for a given DRG type (There are two types of DRG codes
	 * in the database which have overlapping ranges but distinct definitions for
	 * the codes. The three types of DRG codes in the MIMIC-III database are ‘HCFA’
	 * (Health Care Financing Administration), ‘MS’ (Medicare), and ‘APR’ (All
	 * Payers Registry).)
	 *
	 * @return the all drg codes
	 */
	List<DrgCode> getAllDrgCodesByDrgType(String drgType);

	/**
	 * Gets the all drg codes for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all drg codes for patient
	 */
	List<DrgCode> getAllDrgCodesForPatient(Integer subjectId);

	/**
	 * Gets the all drg codes for admission.
	 *
	 * @param hadmId the hadm id
	 * @return the all drg codes for admission
	 */
	List<DrgCode> getAllDrgCodesForAdmission(Integer hadmId);

	/**
	 * Gets the drg code.
	 *
	 * @param rowId the row id
	 * @return the drg code
	 */
	DrgCode getDrgCode(Integer rowId);

	/**
	 * Save or update drg code.
	 *
	 * @param cptEvent the cpt event
	 * @return the drg code
	 */
	DrgCode saveOrUpdateDrgCode(DrgCode cptEvent);

	/**
	 * Delete drg code.
	 *
	 * @param rowId the row id
	 */
	void deleteDrgCode(Integer rowId);

	/**
	 * Gets the all procedures IC ds.
	 *
	 * @return the all procedures IC ds
	 */
	List<ProceduresICD> getAllProceduresICDs();

	/**
	 * Gets the all procedures IC ds for patient.
	 *
	 * @param subjectId the subject id
	 * @return the all procedures IC ds for patient
	 */
	List<ProceduresICD> getAllProceduresICDsForPatient(Integer subjectId);

	/**
	 * Gets the all procedures IC ds for admission.
	 *
	 * @param hadmId the hadm id
	 * @return the all procedures IC ds for admission
	 */
	List<ProceduresICD> getAllProceduresICDsForAdmission(Integer hadmId);

	/**
	 * Gets the procedures ICD.
	 *
	 * @param rowId the row id
	 * @return the procedures ICD
	 */
	ProceduresICD getProceduresICD(Integer rowId);

	/**
	 * Save or update procedures ICD.
	 *
	 * @param procedureIcd the procedure icd
	 * @return the procedures ICD
	 */
	ProceduresICD saveOrUpdateProceduresICD(ProceduresICD procedureIcd);

	/**
	 * Delete procedures ICD.
	 *
	 * @param rowId the row id
	 */
	void deleteProceduresICD(Integer rowId);

}
