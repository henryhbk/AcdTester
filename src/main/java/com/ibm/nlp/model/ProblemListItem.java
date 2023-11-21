package com.ibm.nlp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ibm.watson.health.acd.v1.model.Concept;

/**
 * The Class ProblemListItem.
 *
 * @author henry.feldman@ibm.com
 */
public class ProblemListItem implements Serializable {

	/** default serial ID. */
	private static final long serialVersionUID = 1L;

	/** The name. */
	private String name;

	/** The id. */
	private Long id;

	/** The icd code. */
	private String icdCode;

	/** The cui. */
	private String cui;

	/** The concept list. */
	private List<Concept> conceptList;

	/** The medications. */
	private List<ProblemMedication> medications;

	/** The cuis to stop. */
	private List<String> cuisToStop = new ArrayList<String>();

	/** The alerts. */
	private List<String> alerts = new ArrayList<String>();

	/** The cuis to change. */
	private List<String> cuisToChange = new ArrayList<String>();

	/** The cuis to start. */
	private List<String> cuisToStart = new ArrayList<String>();

	/** The procedures. */
	private List<ProblemProcedure> procedures;

	/** The admission id. */
	private Integer admissionId;

	/**
	 * Instantiates a new problem list item.
	 */
	public ProblemListItem() {
		conceptList = new ArrayList<Concept>();
		medications = new ArrayList<ProblemMedication>();
		procedures = new ArrayList<ProblemProcedure>();
	}

	/**
	 * Instantiates a new problem list item.
	 *
	 * @param name         the name
	 * @param id           the id
	 * @param icdCode      the icd code
	 * @param cui          the cui
	 * @param conceptList  the concept list
	 * @param medications  the medications
	 * @param cuisToStop   the cuis to stop
	 * @param alerts       the alerts
	 * @param cuisToChange the cuis to change
	 * @param cuisToStart  the cuis to start
	 * @param procedures   the procedures
	 * @param admissionId  the admission id
	 */
	public ProblemListItem(String name, Long id, String icdCode, String cui, List<Concept> conceptList,
			List<ProblemMedication> medications, List<String> cuisToStop, List<String> alerts,
			List<String> cuisToChange, List<String> cuisToStart, List<ProblemProcedure> procedures,
			Integer admissionId) {
		super();
		this.name = name;
		this.id = id;
		this.icdCode = icdCode;
		this.cui = cui;
		this.conceptList = conceptList;
		this.medications = medications;
		this.cuisToStop = cuisToStop;
		this.alerts = alerts;
		this.cuisToChange = cuisToChange;
		this.cuisToStart = cuisToStart;
		this.procedures = procedures;
		this.admissionId = admissionId;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the icd code.
	 *
	 * @return the icd code
	 */
	public String getIcdCode() {
		return icdCode;
	}

	/**
	 * Gets the cui.
	 *
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}

	/**
	 * Gets the concept list.
	 *
	 * @return the concept list
	 */
	public List<Concept> getConceptList() {
		return conceptList;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the medications.
	 *
	 * @return the medications
	 */
	public List<ProblemMedication> getMedications() {
		return medications;
	}

	/**
	 * Gets the procedures.
	 *
	 * @return the procedures
	 */
	public List<ProblemProcedure> getProcedures() {
		return procedures;
	}

	/**
	 * Gets the cuis to stop.
	 *
	 * @return the cuis to stop
	 */
	public List<String> getCuisToStop() {
		return cuisToStop;
	}

	/**
	 * Gets the cuis to change.
	 *
	 * @return the cuis to change
	 */
	public List<String> getCuisToChange() {
		return cuisToChange;
	}

	/**
	 * Gets the cuis to start.
	 *
	 * @return the cuis to start
	 */
	public List<String> getCuisToStart() {
		return cuisToStart;
	}

	/**
	 * Gets the admission id.
	 *
	 * @return the admission id
	 */
	public Integer getAdmissionId() {
		return admissionId;
	}

	/**
	 * Gets the alerts.
	 *
	 * @return the alerts
	 */
	public List<String> getAlerts() {
		return alerts;
	}

	/**
	 * Sets the alerts.
	 *
	 * @param alerts the new alerts
	 */
	public void setAlerts(List<String> alerts) {
		this.alerts = alerts;
	}

	/**
	 * Sets the admission id.
	 *
	 * @param admissionId the new admission id
	 */
	public void setAdmissionId(Integer admissionId) {
		this.admissionId = admissionId;
	}

	/**
	 * Sets the cuis to stop.
	 *
	 * @param cuisToStop the new cuis to stop
	 */
	public void setCuisToStop(List<String> cuisToStop) {
		this.cuisToStop = cuisToStop;
	}

	/**
	 * Sets the cuis to change.
	 *
	 * @param cuisToChange the new cuis to change
	 */
	public void setCuisToChange(List<String> cuisToChange) {
		this.cuisToChange = cuisToChange;
	}

	/**
	 * Sets the cuis to start.
	 *
	 * @param cuisToStart the new cuis to start
	 */
	public void setCuisToStart(List<String> cuisToStart) {
		this.cuisToStart = cuisToStart;
	}

	/**
	 * Sets the medications.
	 *
	 * @param medications the new medications
	 */
	public void setMedications(List<ProblemMedication> medications) {
		this.medications = medications;
	}

	/**
	 * Sets the procedures.
	 *
	 * @param procedures the new procedures
	 */
	public void setProcedures(List<ProblemProcedure> procedures) {
		this.procedures = procedures;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the icd code.
	 *
	 * @param icdCode the new icd code
	 */
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
	}

	/**
	 * Sets the cui.
	 *
	 * @param cui the new cui
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * Sets the concept list.
	 *
	 * @param conceptList the new concept list
	 */
	public void setConceptList(List<Concept> conceptList) {
		this.conceptList = conceptList;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProblemListItem [name=" + name + ", icdCode=" + icdCode + ", cui=" + cui + ", conceptList="
				+ conceptList + "]";
	}

	/**
	 * Gets the meds/procedures as questions.
	 *
	 * @return the questions
	 */
	public List<String> getQuestions() {
		List<String> questions = new ArrayList<String>();

		for (ProblemMedication med : medications) {
			questions.add("In the setting of " + name + ", " + med.getName() + " is a good medication.");
		}
		for (ProblemProcedure procedure : procedures) {
			questions.add("performing " + procedure.getName() + ", in the setting of " + name + " is a good idea.");
		}

		return questions;
	}

	/**
	 * Gets the as HTML.
	 *
	 * @return the as HTML
	 */
	public String getAsHTML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>\n");
		if (icdCode == null || icdCode.isEmpty()) {
			sb.append("<h3>" + getCui() + " " + name + "</h3>\n");
		} else {
			sb.append("<h3>" + icdCode + " " + name + "</h3>\n");

		}
		sb.append("</div>\n");
		sb.append("<div>\n");

		sb.append("medications:<br>\n");
		if (medications.isEmpty()) {
			sb.append("<i>No Medications Detected for this diagnosis</><br>\n");
		} else {
			sb.append("<ol>\n");
			for (ProblemMedication medication : medications) {
				sb.append("<li>" + medication.toPrettyString() + "</li>\n");
			}
			sb.append("</ol>\n");
		}
		sb.append("</div>\n");
//		sb.append("procedures:<br>\n");
//		sb.append("<ol>\n");
//		for (ProblemProcedure procedure : procedures) {
//			sb.append("<li>" + procedure.getName() + " " + procedure.getCui() + "</li>\n");
//		}
//		sb.append("</ol>\n");
		sb.append("<div>\n");
		sb.append("Alerts:<br>\n");
		Set<String> removeDups = new HashSet<String>();
		if (alerts.isEmpty()) {
			sb.append("<i>No Alerts Detected for this diagnosis.</i><br>\n");
		} else {
			sb.append("<ul>\n");
			for (String alertText : alerts) {
				if (!removeDups.contains(alertText)) {
					sb.append("<li><span  class=\"alertText\">" + alertText + "</span></li>\n");
					removeDups.add(alertText);
				}
			}
			sb.append("</ul>\n");
		}
		sb.append("<br></div>\n");
		return sb.toString();
	}

}
