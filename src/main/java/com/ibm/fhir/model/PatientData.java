package com.ibm.fhir.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.Prescription;

/**
 * The Class PatientData.
 *
 * @author henry.feldman@ibm.com
 */
public class PatientData {

	/** The patient. */
	private Patients patient;

	/** The note event list. */
	private List<NoteEvent> noteEventList = new ArrayList<NoteEvent>();

	/** The admission note map. */
	private Map<Integer, NoteEvent> admissionNoteMap = new HashMap<Integer, NoteEvent>();

	/** The admission medication map. */
	private Map<Integer, List<Prescription>> admissionMedicationMap = new HashMap<Integer, List<Prescription>>();

	/**
	 * Instantiates a new patient data.
	 */
	public PatientData() {
	}

	/**
	 * Instantiates a new patient data.
	 *
	 * @param patient                the patient
	 * @param noteEventList          the note event list
	 * @param admissionNoteMap       the admission note map
	 * @param admissionMedicationMap the admission medication map
	 */
	public PatientData(Patients patient, List<NoteEvent> noteEventList, Map<Integer, NoteEvent> admissionNoteMap,
			Map<Integer, List<Prescription>> admissionMedicationMap) {
		super();
		this.patient = patient;
		this.noteEventList = noteEventList;
		this.admissionNoteMap = admissionNoteMap;
		this.admissionMedicationMap = admissionMedicationMap;
	}

	/**
	 * Gets the patient.
	 *
	 * @return the patient
	 */
	public Patients getPatient() {
		return patient;
	}

	/**
	 * Gets the note event list.
	 *
	 * @return the note event list
	 */
	public List<NoteEvent> getNoteEventList() {
		return noteEventList;
	}

	/**
	 * Gets the admission medication map which allows you to get all the med orders
	 * for a given admission (HADM_ID in MIMIC III).
	 *
	 * @return the admission medication map
	 */
	public Map<Integer, List<Prescription>> getAdmissionMedicationMap() {
		return admissionMedicationMap;
	}

	/**
	 * Gets the admission note map.
	 *
	 * @return the admission note map
	 */
	public Map<Integer, NoteEvent> getAdmissionNoteMap() {
		return admissionNoteMap;
	}

	/**
	 * Sets the admission note map.
	 *
	 * @param admissionNoteMap the admission note map
	 */
	public void setAdmissionNoteMap(Map<Integer, NoteEvent> admissionNoteMap) {
		this.admissionNoteMap = admissionNoteMap;
	}

	/**
	 * Sets the patient.
	 *
	 * @param patient the new patient
	 */
	public void setPatient(Patients patient) {
		this.patient = patient;
	}

	/**
	 * Sets the note event list.
	 *
	 * @param noteEventList the new note event list
	 */
	public void setNoteEventList(List<NoteEvent> noteEventList) {
		this.noteEventList = noteEventList;
	}

	/**
	 * Sets the admission medication map.
	 *
	 * @param admissionMedicationMap the admission medication map
	 */
	public void setAdmissionMedicationMap(Map<Integer, List<Prescription>> admissionMedicationMap) {
		this.admissionMedicationMap = admissionMedicationMap;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PatientData [patient=" + patient + ", noteEventList=" + noteEventList + ", admissionMedicationMap="
				+ admissionMedicationMap + "]";
	}

}
