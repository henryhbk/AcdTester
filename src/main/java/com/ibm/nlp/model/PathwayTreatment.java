package com.ibm.nlp.model;

import java.io.Serializable;

/**
 * The Class PathwayTreatment.
 *
 * @author henry.feldman@ibm.com
 */
public class PathwayTreatment implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The type lab. */
	public final static String TYPE_LAB = "umls.LaboratoryProcedure";

	/** The type medication. */
	public final static String TYPE_MEDICATION = "aci.MedicationInd";

	/** The type diagnostic. */
	public final static String TYPE_DIAGNOSTIC = "DiagnosticProcedure";

	/** The type antibiotic. */
	public final static String TYPE_ANTIBIOTIC = "umls.Antibiotic";

	/** The id. */
	private Integer id;

	/** The type. */
	private String type;

	/** The description. */
	private String description;

	/** The action to do. */
	private String actionToDo;

	/** The concept id. */
	private String conceptId;

	/** The parent pathway id. */
	private Integer parentPathwayId;

	/**
	 * Instantiates a new pathway treatment.
	 */
	public PathwayTreatment() {
	}

	/**
	 * Instantiates a new pathway treatment.
	 *
	 * @param id          the id
	 * @param type        the type
	 * @param description the description
	 * @param actionToDo  the action to do
	 * @param conceptId   the concept id
	 */
	public PathwayTreatment(Integer id, String type, String description, String actionToDo, String conceptId) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.actionToDo = actionToDo;
		this.conceptId = conceptId;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * 
	 * /** Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the action to do.
	 *
	 * @return the action to do
	 */
	public String getActionToDo() {
		return actionToDo;
	}

	/**
	 * Gets the concept id.
	 *
	 * @return the concept id
	 */
	public String getConceptId() {
		return conceptId;
	}

	/**
	 * Gets the parent pathway id.
	 *
	 * @return the parent pathway id
	 */
	public Integer getParentPathwayId() {
		return parentPathwayId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the parent pathway id.
	 *
	 * @param parentPathwayId the new parent pathway id
	 */
	public void setParentPathwayId(Integer parentPathwayId) {
		this.parentPathwayId = parentPathwayId;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the action to do.
	 *
	 * @param actionToDo the new action to do
	 */
	public void setActionToDo(String actionToDo) {
		this.actionToDo = actionToDo;
	}

	/**
	 * Sets the concept id.
	 *
	 * @param conceptId the new concept id
	 */
	public void setConceptId(String conceptId) {
		this.conceptId = conceptId;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "PathwayTreatment [type=" + type + ", description=" + description + ", actionToDo=" + actionToDo
				+ ", conceptId=" + conceptId + ", parentPathwayId=" + parentPathwayId + "]";
	}

}
