package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.hibernate.FieldValueMapper;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.SearchPatient;

/**
 * The Interface PatientService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface PatientService {

	/**
	 * Gets the patient.
	 *
	 * @param id the id
	 * @return the patient
	 */
	Patients getPatient(Integer id);

	/**
	 * Gets the all patients.
	 *
	 * @return the all patients
	 */
	List<Patients> getAllPatients();

	/**
	 * Gets the all patients for acd study, which are patients who had notes that
	 * were used in the ACD adverse drug event project. By definition those notes
	 * are resident admission notes, for patients admitted directly from the
	 * emergency room. These notes are the cleanest for a lot of doctor note tasks.
	 * It comprises around 11,500 notes.
	 *
	 * @return the all patients for acd study
	 */
	List<Patients> getAllPatientsForAcdStudy();

	/**
	 * Gets the patient by subject id. Note: the SubjectId is <b>not</b> the table
	 * primary key, which is actually Row_ID. This sort of makes sense as that is
	 * how EMRs work in general having a second unique key for patients other than
	 * the DB PK to allow records to merge/unmerge etc all under a MPID. So all the
	 * related objects (labs etc) all use subjectId, not rowId)
	 *
	 * @param subjectId the subject id
	 * @return the patient by subject id
	 */
	Patients getPatientBySubjectId(Integer subjectId);

	/**
	 * Save patient.
	 *
	 * @param patient the patient
	 * @return the patients
	 */
	Patients savePatient(Patients patient);

	/**
	 * Delete patient.
	 *
	 * @param id the id
	 */
	void deletePatient(Integer id);

	/**
	 * Live oracle multi fields search for patients; very cool for building live web
	 * demos. I used this in the EMR I built. It is actually remarkably fast on
	 * pre-indexed fields and similar to google when you start typing it kicks in
	 * and starts searching patients by the specified fields and returns patients.
	 * This technique in the EMR I built even allowed simultaneous multi-lingual
	 * searching (so you could find me by the arabic spelling of my first name and
	 * my english last name). For performance safety it doesn't kick in until the
	 * search term is at least 3 characters long. Obviously as the programmer you
	 * need to get the field names correct from the Patients class
	 *
	 * @param fieldValueMapper the field value mapper
	 * @return the list
	 */
	List<SearchPatient> liveOracleMultiFieldsSearchForPatients(List<FieldValueMapper> fieldValueMapper)
			throws IllegalArgumentException;

}
