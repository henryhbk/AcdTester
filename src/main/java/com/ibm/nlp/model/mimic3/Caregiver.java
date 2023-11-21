package com.ibm.nlp.model.mimic3;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class Caregiver represents someone who might put in orders or notes (note
 * only LIP positions (Attending Physician, NP/PA or Fellow could have a NPI
 * number otherwise it is null).
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "CAREGIVERS")
@NamedQuery(name = "Caregiver.findAll", query = "SELECT c FROM Caregiver c")
@JsonRootName(value = "Caregiver")
public class Caregiver extends BaseEntity implements Serializable, HasFhir {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROW_ID")
	private int rowId;

	/** The cgid. */
	@Column(name = "CGID")
	private int cgid;

	/** The first name. */
	@Column(name = "first_name")
	private String firstName;

	/** The last name. */
	@Column(name = "last_name")
	private String lastName;

	/** The npi number. */
	@Column(name = "npi_number")
	private String npiNumber;

	/** The gender. */
	@Column(name = "gender")
	private String gender;

	/** The label. */
	@Column(name = "LABEL")
	private String label;

	/** The description. */
	@Column(name = "description")
	private String description;

	/** The fhir json. */
	@Column(name = "fhir_json")
	private String fhirJson;

	/**
	 * Instantiates a new caregiver.
	 */
	public Caregiver() {
	}

	/**
	 * Instantiates a new caregiver.
	 *
	 * @param rowId       the row id
	 * @param cgid        the cgid
	 * @param description the description
	 * @param fhirJson    the fhir json
	 * @param firstName   the first name
	 * @param lastName    the last name
	 * @param npiNumber   the npi number
	 * @param gender      the gender
	 * @param label       the label
	 */
	public Caregiver(int rowId, int cgid, String description, String fhirJson, String firstName, String lastName,
			String npiNumber, String gender, String label) {
		super();
		this.rowId = rowId;
		this.cgid = cgid;
		this.description = description;
		this.fhirJson = fhirJson;
		this.firstName = firstName;
		this.lastName = lastName;
		this.npiNumber = npiNumber;
		this.gender = gender;
		this.label = label;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	@JsonProperty("rowId")
	public int getRowId() {
		return rowId;
	}

	/**
	 * Gets the cgid.
	 *
	 * @return the cgid
	 */
	@JsonProperty("cgid")
	public int getCgid() {
		return cgid;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the fhir json.
	 *
	 * @return the fhir json
	 */
	@JsonProperty("fhirJson")
	public String getFhirJson() {
		return fhirJson;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the npi number. note only LIP positions (Attending Physician, NP/PA or
	 * Fellow could have a NPI number otherwise it is null)
	 *
	 * @return the npi number
	 */
	@JsonProperty("npiNumber")
	public String getNpiNumber() {
		return npiNumber;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	@JsonProperty("gender")
	public String getGender() {
		return gender;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	@JsonSetter("rowId")
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the cgid.
	 *
	 * @param cgid the new cgid
	 */
	@JsonSetter("cgid")
	public void setCgid(int cgid) {
		this.cgid = cgid;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	@JsonSetter("description")
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the fhir json.
	 *
	 * @param fhirJson the new fhir json
	 */
	@JsonSetter("fhirJson")
	public void setFhirJson(String fhirJson) {
		this.fhirJson = fhirJson;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	@JsonSetter("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	@JsonSetter("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the npi number.
	 *
	 * @param npiNumber the new npi number
	 */
	@JsonSetter("npiNumber")
	public void setNpiNumber(String npiNumber) {
		this.npiNumber = npiNumber;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	@JsonSetter("gender")
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	@JsonSetter("label")
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Caregiver [rowId=" + rowId + ", cgid=" + cgid + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", npiNumber=" + npiNumber + ", gender=" + gender + ", label=" + label + ", description="
				+ description + ", fhirJson=" + fhirJson + "]";
	}

}