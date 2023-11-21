package com.ibm.nlp.model;

import java.io.Serializable;

/**
 * The Class CareGiver.
 *
 * @author henry.feldman@ibm.com
 */
public class CareGiver implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	private Integer rowId;

	/** The cg id. */
	private Integer cgId;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The gender. */
	private String gender;

	/** The label. */
	private String label;

	/** The Description. */
	private String Description;

	/** The fhir json. */
	private String fhirJson;

	/**
	 * Instantiates a new care giver.
	 */
	public CareGiver() {
		super();
	}

	/**
	 * Instantiates a new care giver.
	 *
	 * @param rowId       the row id
	 * @param cgId        the cg id
	 * @param firstName   the first name
	 * @param lastName    the last name
	 * @param gender      the gender
	 * @param label       the label
	 * @param description the description
	 * @param fhirJson    the fhir json
	 */
	public CareGiver(Integer rowId, Integer cgId, String firstName, String lastName, String gender, String label,
			String description, String fhirJson) {
		super();
		this.rowId = rowId;
		this.cgId = cgId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.label = label;
		Description = description;
		this.fhirJson = fhirJson;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	public Integer getRowId() {
		return rowId;
	}

	/**
	 * Gets the cg id.
	 *
	 * @return the cg id
	 */
	public Integer getCgId() {
		return cgId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}

	/**
	 * Gets the fhir json.
	 *
	 * @return the fhir json
	 */
	public String getFhirJson() {
		return fhirJson;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the cg id.
	 *
	 * @param cgId the new cg id
	 */
	public void setCgId(Integer cgId) {
		this.cgId = cgId;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		Description = description;
	}

	/**
	 * Sets the fhir json.
	 *
	 * @param fhirJson the new fhir json
	 */
	public void setFhirJson(String fhirJson) {
		this.fhirJson = fhirJson;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "CareGiver [rowId=" + rowId + ", cgId=" + cgId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", gender=" + gender + ", label=" + label + ", Description=" + Description + ", fhirJson=" + fhirJson
				+ "]";
	}

}
