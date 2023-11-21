package com.ibm.nlp.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Patient holds the basic data from the FHIR server acting as EMR
 * surrogate.
 *
 * @author henry.feldman@ibm.com
 */
public class Patient implements Serializable {

	/** default serial ID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The fhir ID. */
	private String fhirID;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The dob. */
	private Date dob;

	/** The gender. */
	private String gender;

	/**
	 * Instantiates a new patient.
	 */
	public Patient() {
	}

	/**
	 * Instantiates a new patient.
	 *
	 * @param id        the id
	 * @param fhirID    the fhir ID
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param dob       the dob
	 * @param gender    the gender
	 */
	public Patient(String id, String fhirID, String firstName, String lastName, Date dob, String gender) {
		super();
		this.id = id;
		this.fhirID = fhirID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Gets the fhir ID.
	 *
	 * @return the fhir ID
	 */
	public String getFhirID() {
		return fhirID;
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
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public Date getDob() {
		return dob;
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
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Sets the fhir ID.
	 *
	 * @param fhirID the new fhir ID
	 */
	@JsonProperty("fhirid")
	public void setFhirID(String fhirID) {
		this.fhirID = fhirID;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	@JsonProperty("firstname")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	@JsonProperty("lastname")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	@JsonProperty("dob")
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the dob as string.
	 *
	 * @return the dob as string
	 */
	public String getDobAsString() {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(dob);
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
		return "Patient: id: " + id + "\nFirst: " + firstName + "\nLast: " + lastName + "\ndob: " + getDobAsString()
				+ " (" + getAgeAsString() + ") (" + gender + ")\n";
	}

	/**
	 * Gets the age in months.
	 *
	 * @return the age in months
	 */
	@SuppressWarnings("deprecation")
	public int getAgeInMonths() {
		if (dob == null) {
			return 0;
		}

		final Date now = new Date();

		final int yearDiff = now.getYear() - dob.getYear();
		int monthDiff = now.getMonth() - dob.getMonth();
		final int dayDiff = now.getDate() - dob.getDate();

		if (dayDiff < 0) {
			monthDiff -= 1;
		}

		final int ageInMonths = yearDiff * 12 + monthDiff;

		return ageInMonths;
	}

	/**
	 * Gets the age in years, derived from dob.
	 * 
	 * @return the age in years
	 */
	public int getAgeInYears() {
		return getAgeInMonths() / 12;
	}

	/**
	 * Handle all the various combinations of age. For <18 we show Y+M and if <1y
	 * show M and if <1mo show "<1mo"
	 * 
	 * @return the age as string
	 */
	public String getAgeAsString() {
		// Handle all the various combinations of age. For <18 we show Y+M and if
		// <1y show M and if <1mo show "<1mo"
		String age = "";
		if (getAgeInYears() >= 18) {
			age = getAgeInYears() + "y";
		} else {
			if (getAgeInYears() == 0) {
				if (getAgeInMonths() == 0) {
					age = " < 1 mos";
				} else {
					age = getAgeInMonths() + "mo";
				}
			} else {
				age = getAgeInYears() + "y " + getAgeInMonths() % 12 + "mo";
			}
		}
		return age;
	}

}
