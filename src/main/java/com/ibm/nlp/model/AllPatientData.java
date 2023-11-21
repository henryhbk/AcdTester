package com.ibm.nlp.model;

import java.util.ArrayList;
import java.util.List;

import com.ibm.nlp.model.mimic3.Patients;

/**
 * The Class AllPatientData.
 *
 * @author henry.feldman@ibm.com
 */
public class AllPatientData {

	/** The patient. */
	private Patients patient;

	/** The problem list. */
	private List<MedicalProblem> problemList = new ArrayList<MedicalProblem>();

	/** The medication list. */
	private List<ProblemMedication> medicationList = new ArrayList<ProblemMedication>();

	/** The procedure list. */
	private List<ProblemProcedure> procedureList = new ArrayList<ProblemProcedure>();

	/**
	 * Instantiates a new all patient data.
	 */
	public AllPatientData() {
	}

	/**
	 * Instantiates a new all patient data.
	 *
	 * @param patient        the patient
	 * @param problemList    the problem list
	 * @param medicationList the medication list
	 * @param procedureList  the procedure list
	 */
	public AllPatientData(Patients patient, List<MedicalProblem> problemList, List<ProblemMedication> medicationList,
			List<ProblemProcedure> procedureList) {
		super();
		this.patient = patient;
		this.problemList = problemList;
		this.medicationList = medicationList;
		this.procedureList = procedureList;
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
	 * Gets the problem list.
	 *
	 * @return the problem list
	 */
	public List<MedicalProblem> getProblemList() {
		return problemList;
	}

	/**
	 * Gets the medication list.
	 *
	 * @return the medication list
	 */
	public List<ProblemMedication> getMedicationList() {
		return medicationList;
	}

	/**
	 * Gets the procedure list.
	 *
	 * @return the procedure list
	 */
	public List<ProblemProcedure> getProcedureList() {
		return procedureList;
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
	 * Sets the problem list.
	 *
	 * @param problemList the new problem list
	 */
	public void setProblemList(List<MedicalProblem> problemList) {
		this.problemList = problemList;
	}

	/**
	 * Sets the medication list.
	 *
	 * @param medicationList the new medication list
	 */
	public void setMedicationList(List<ProblemMedication> medicationList) {
		this.medicationList = medicationList;
	}

	/**
	 * Sets the procedure list.
	 *
	 * @param procedureList the new procedure list
	 */
	public void setProcedureList(List<ProblemProcedure> procedureList) {
		this.procedureList = procedureList;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "AllPatientData [patient=" + patient + ", problemList=" + problemList + ", medicationList="
				+ medicationList + ", procedureList=" + procedureList + "]";
	}

	/**
	 * Gets the subject id as hex.
	 *
	 * @return the subject id as hex
	 */
	public String getSubjectIdAsHex() {
		return "XXXX";
	}

	/**
	 * Gets the full patient name.
	 *
	 * @return the full patient name
	 */
	public String getFullPatientName() {
		return patient.getFirstName() + " " + patient.getLastName();
	}
}
