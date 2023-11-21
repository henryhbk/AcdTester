package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * The Class SearchPatient is a lighter weight class for the return via the live
 * search method
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "PATIENTS")
@JsonRootName(value = "SearchPatient")
public class SearchPatient implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new search patient.
	 */
	public SearchPatient() {
	}

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private int rowId;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	/** The subject id. */
	private java.lang.Integer subjectId;

	/** The first name. */
	@Column(name = "first_name")
	private java.lang.String firstName;

	/** The last name. */
	@Column(name = "last_name")
	private java.lang.String lastName;

	/** The gender. */
	@Column(name = "gender")
	private java.lang.String gender;

	/** The dob. */
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;

	/** The street. */
	@Column(name = "street")
	private java.lang.String street;

	/** The city. */
	@Column(name = "city")
	private java.lang.String city;

	/** The state. */
	@Column(name = "state")
	private java.lang.String state;

	/** The postal. */
	@Column(name = "zip")
	private java.lang.String postal;

	/** The expire flag. */
	@Column(name = "EXPIRE_FLAG")
	private java.lang.Boolean expireFlag;

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	public int getRowId() {
		return rowId;
	}

	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public java.lang.Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public java.lang.String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public java.lang.String getLastName() {
		return lastName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public java.lang.String getGender() {
		return gender;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	public java.lang.String getStreet() {
		return street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public java.lang.String getCity() {
		return city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public java.lang.String getState() {
		return state;
	}

	/**
	 * Gets the postal.
	 *
	 * @return the postal
	 */
	public java.lang.String getPostal() {
		return postal;
	}

	/**
	 * Gets the expire flag.
	 *
	 * @return the expire flag
	 */
	public java.lang.Boolean getExpireFlag() {
		return expireFlag;
	}

	/**
	 * Sets the expire flag.
	 *
	 * @param expireFlag the new expire flag
	 */
	public void setExpireFlag(java.lang.Boolean expireFlag) {
		this.expireFlag = expireFlag;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(java.lang.Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	public void setStreet(java.lang.String street) {
		this.street = street;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * Sets the postal.
	 *
	 * @param postal the new postal
	 */
	public void setPostal(java.lang.String postal) {
		this.postal = postal;
	}

}
