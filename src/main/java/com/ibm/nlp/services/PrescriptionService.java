package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.Prescription;

/**
 * The Interface PrescriptionService; is the interface for either webservice or
 * database access. Not prescriptions are really mis-named (by mimic) as they
 * are really medication orders
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface PrescriptionService {

	/**
	 * Gets the annotation.
	 *
	 * @param id the id
	 * @return the annotation
	 */
	Prescription getPrescription(Integer id);

	/**
	 * Gets the all annotations.
	 *
	 * @return the all annotations
	 */
	List<Prescription> getAllPrescriptions();

	/**
	 * Gets the all Prescriptions for Acd study (.e. the acd_study_Prescription ==
	 * true)
	 *
	 * @return the all Prescriptions for Acd study
	 */
	List<Prescription> getAllPrescriptionsForAcdStudy();

	/**
	 * Save annotation.
	 *
	 * @param prescription the prescription
	 * @return the annotation
	 */
	Prescription savePrescription(Prescription prescription);

	/**
	 * Delete annotation.
	 *
	 * @param id the id
	 */
	void deletePrescription(Integer id);

	/**
	 * Gets the all prescriptions for a patient.
	 *
	 * @param subjectId the subject id
	 * @return the all prescriptions for patient
	 */
	List<Prescription> getAllPrescriptionsForPatient(Integer subjectId);

	/**
	 * Gets the prescriptions for rx norm id.
	 *
	 * @param rxNormId the rx norm id
	 * @return the prescriptions for rx norm id
	 */
	List<Prescription> getPrescriptionsForRxNormId(String rxNormId);

	/**
	 * Search prescription for drug name. Note this is a fuzzy search so "lis" will
	 * get LISinopril.
	 *
	 * @param drugName the drug name
	 * @return the list
	 */
	List<Prescription> searchPrescriptionForDrugName(String drugName);
}
