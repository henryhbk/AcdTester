package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The persistent class for the PATIENTS database table.
 *
 * @author henry.feldman@ibm.com
 */
@Entity
@Table(name = "PATIENTS")
@JsonRootName(value = "Patients")
public class Patients extends BaseEntity implements Serializable, HasFhir {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private int rowId;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private java.lang.Integer subjectId;

	/** The first name. */
	@Column(name = "first_name")
	private java.lang.String firstName;

	/** The last name. */
	@Column(name = "last_name")
	private java.lang.String lastName;

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

	/** The gender. */
	@Column(name = "gender")
	private java.lang.String gender;

	/** The race. */
	@Column(name = "race")
	private java.lang.String race;

	/** The language. */
	@Column(name = "language")
	private java.lang.String language;

	/** The ethnicity. */
	@Column(name = "ethnicity")
	private java.lang.String ethnicity;

	/** The religion. */
	@Column(name = "religion")
	private java.lang.String religion;

	/** The insurance. */
	@Column(name = "insurance")
	private java.lang.String insurance;

	/** The marital status. */
	@Column(name = "marital_status")
	private String maritalStatus;

	/** The dob. */
	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;

	/** The Dod. */
	@Column(name = "DOD")
	@Temporal(TemporalType.DATE)
	private Date dod;

	/** The dod hosp. */
	@Column(name = "DOD_HOSP")
	private Date dodHosp;

	/** The expire flag. */
	@Column(name = "EXPIRE_FLAG")
	private java.lang.Boolean expireFlag;

	/** The fhir ID. */
	@Transient
	private java.lang.String fhirID;

	/** The fhir json. */
	@Column(name = "fhir_json")
	private String fhirJson;

	/** The via FHIR. */
	@Transient
	private java.lang.Boolean viaFHIR = true;

	/** The date formatter. */
	@Transient
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

	/** The acd study patient. */
	@Column(name = "acd_study_patient")
	private Boolean acdStudyPatient;

	/**
	 * Dateiates a new patients.
	 */
	public Patients() {
		super();
	}

	/**
	 * Dateiates a new patients.
	 *
	 * @param subjectId     the subject id
	 * @param firstName     the first name
	 * @param lastName      the last name
	 * @param street        the street
	 * @param city          the city
	 * @param state         the state
	 * @param postal        the postal
	 * @param gender        the gender
	 * @param race          the race
	 * @param language      the language
	 * @param ethnicity     the ethnicity
	 * @param religion      the religion
	 * @param maritalStatus the marital status
	 * @param insurance     the insurance
	 * @param dob           the dob
	 * @param dod           the dod
	 * @param dod_hosp      the dod hosp
	 * @param expireFlag    the expire flag
	 * @param fhirID        the fhir ID
	 * @param fhirJson      the fhir json
	 * @param viaFHIR       the via FHIR
	 */
	public Patients(Integer subjectId, String firstName, String lastName, String street, String city, String state,
			String postal, String gender, String race, String language, String ethnicity, String religion,
			String maritalStatus, String insurance, Date dob, Date dod, Date dod_hosp, Boolean expireFlag,
			String fhirID, String fhirJson, Boolean viaFHIR) {
		super();
		this.subjectId = subjectId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.street = street;
		this.city = city;
		this.state = state;
		this.postal = postal;
		this.gender = gender;
		this.race = race;
		this.language = language;
		this.dob = dob;
		this.dod = dod;
		this.dodHosp = dod_hosp;
		this.expireFlag = expireFlag;
		this.fhirID = fhirID;
		this.fhirJson = fhirJson;
		this.viaFHIR = viaFHIR;
		this.religion = religion;
		this.maritalStatus = maritalStatus;
		this.insurance = insurance;
		this.ethnicity = ethnicity;
	}

	/**
	 * Dateiates a new patients.
	 *
	 * @param id        the id
	 * @param fhirID    the fhir ID
	 * @param firstName the first name
	 * @param lastName  the last name
	 * @param dob       the dob
	 * @param gender    the gender
	 */
	public Patients(Integer id, java.lang.String fhirID, java.lang.String firstName, java.lang.String lastName,
			Date dob, java.lang.String gender) {
		super();
		this.subjectId = id;
		this.fhirID = fhirID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
	}

	/**
	 * Dateiates a new patients object when given a FHIR patient.
	 *
	 * @param fhirPatient the fhir patient
	 */
	public Patients(com.ibm.fhir.model.resource.Patient fhirPatient) {
		super();
		this.subjectId = Integer.parseInt(fhirPatient.getId());
		this.firstName = fhirPatient.getName().get(0).getGiven().get(0).getValue();
		this.lastName = fhirPatient.getName().get(0).getFamily().getValue();
		java.lang.StringBuilder street = new java.lang.StringBuilder();
		for (com.ibm.fhir.model.type.String address : fhirPatient.getAddress().get(0).getLine()) {
			street.append(address.toString());
		}
		this.street = street.toString();
		this.city = fhirPatient.getAddress().get(0).getCity().getValue().toString();
		this.state = fhirPatient.getAddress().get(0).getState().getValue().toString();
		this.gender = fhirPatient.getGender().getValue();
		if (!fhirPatient.getBirthDate().isPartial()) {
			fhirPatient.getBirthDate().getValue();
			ZoneId.systemDefault();
		}
		this.fhirJson = fhirPatient.toString();
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
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	@JsonProperty("subjectId")
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	@JsonProperty("firstName")
	public java.lang.String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	@JsonProperty("lastName")
	public java.lang.String getLastName() {
		return lastName;
	}

	/**
	 * Gets the street.
	 *
	 * @return the street
	 */
	@JsonProperty("street")
	public java.lang.String getStreet() {
		return street;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	@JsonProperty("city")
	public java.lang.String getCity() {
		return city;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	@JsonProperty("state")
	public java.lang.String getState() {
		return state;
	}

	/**
	 * Gets the postal.
	 *
	 * @return the postal
	 */
	@JsonProperty("street")
	public java.lang.String getPostal() {
		return postal;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	@JsonProperty("gender")
	public java.lang.String getGender() {
		return gender;
	}

	/**
	 * Gets the race.
	 *
	 * @return the race
	 */
	@JsonProperty("race")
	public java.lang.String getRace() {
		return race;
	}

	/**
	 * Gets the language.
	 *
	 * @return the language
	 */
	@JsonProperty("language")
	public java.lang.String getLanguage() {
		return language;
	}

	/**
	 * Gets the religion.
	 *
	 * @return the religion
	 */
	@JsonProperty("religion")
	public java.lang.String getReligion() {
		return religion;
	}

	/**
	 * Gets the marital status.
	 *
	 * @return the marital status
	 */
	@JsonProperty("maritalStatus")
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	@JsonProperty("dob")
	public Date getDob() {
		return dob;
	}

	/**
	 * Gets the dod.
	 *
	 * @return the dod
	 */
	@JsonProperty("dod")
	public Date getDod() {
		return dod;
	}

	/**
	 * Gets the dod hosp.
	 *
	 * @return the dod hosp
	 */
	@JsonProperty("dodHosp")
	public Date getDod_hosp() {
		return dodHosp;
	}

	/**
	 * Gets the expire flag.
	 *
	 * @return the expire flag
	 */
	@JsonProperty("expireFlag")
	public java.lang.Boolean getExpireFlag() {
		return expireFlag;
	}

	/**
	 * Gets the fhir ID.
	 *
	 * @return the fhir ID
	 */
	@JsonProperty("fhirID")
	public java.lang.String getFhirID() {
		return fhirID;
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
	 * Gets the via FHIR.
	 *
	 * @return the via FHIR
	 */
	@JsonProperty("viaFHIR")
	public java.lang.Boolean getViaFHIR() {
		return viaFHIR;
	}

	/**
	 * Gets the acd study patient.
	 *
	 * @return the acd study patient
	 */
	@JsonProperty("acdStudyPatient")
	public Boolean getAcdStudyPatient() {
		return acdStudyPatient;
	}

	/**
	 * Gets the insurance.
	 *
	 * @return the insurance
	 */
	@JsonProperty("insurance")
	public java.lang.String getInsurance() {
		return insurance;
	}

	/**
	 * Gets the ethnicity.
	 *
	 * @return the ethnicity
	 */
	@JsonProperty("ethnicity")
	public java.lang.String getEthnicity() {
		return ethnicity;
	}

	/**
	 * Sets the ethnicity.
	 *
	 * @param ethnicity the new ethnicity
	 */
	@JsonSetter("ethnicity")
	public void setEthnicity(java.lang.String ethnicity) {
		this.ethnicity = ethnicity;
	}

	/**
	 * Sets the insurance.
	 *
	 * @param insurance the new insurance
	 */
	@JsonSetter("insurance")
	public void setInsurance(java.lang.String insurance) {
		this.insurance = insurance;
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
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	@JsonSetter("subjectId")
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	@JsonSetter("firstName")
	public void setFirstName(java.lang.String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	@JsonSetter("lastName")
	public void setLastName(java.lang.String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the street.
	 *
	 * @param street the new street
	 */
	@JsonSetter("street")
	public void setStreet(java.lang.String street) {
		this.street = street;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	@JsonSetter("city")
	public void setCity(java.lang.String city) {
		this.city = city;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	@JsonSetter("state")
	public void setState(java.lang.String state) {
		this.state = state;
	}

	/**
	 * Sets the postal.
	 *
	 * @param postal the new postal
	 */
	@JsonSetter("postal")
	public void setPostal(java.lang.String postal) {
		this.postal = postal;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	@JsonSetter("gender")
	public void setGender(java.lang.String gender) {
		this.gender = gender;
	}

	/**
	 * Sets the race.
	 *
	 * @param race the new race
	 */
	@JsonSetter("race")
	public void setRace(java.lang.String race) {
		this.race = race;
	}

	/**
	 * Sets the language.
	 *
	 * @param language the new language
	 */
	@JsonSetter("language")
	public void setLanguage(java.lang.String language) {
		this.language = language;
	}

	/**
	 * Sets the religion.
	 *
	 * @param religion the new religion
	 */
	@JsonSetter("religion")
	public void setReligion(java.lang.String religion) {
		this.religion = religion;
	}

	/**
	 * Sets the marital status.
	 *
	 * @param maritalStatus the new marital status
	 */
	@JsonSetter("maritalStatus")
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob the new dob
	 */
	@JsonSetter("dob")
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Sets the dod.
	 *
	 * @param dod the new dod
	 */
	@JsonSetter("dod")
	public void setDod(Date dod) {
		this.dod = dod;
	}

	/**
	 * Sets the dod hosp.
	 *
	 * @param dodHosp the new dod hosp
	 */
	@JsonSetter("dodHosp")
	public void setDod_hosp(Date dodHosp) {
		this.dodHosp = dodHosp;
	}

	/**
	 * Sets the expire flag.
	 *
	 * @param expireFlag the new expire flag
	 */
	@JsonSetter("expireFlag")
	public void setExpireFlag(java.lang.Boolean expireFlag) {
		this.expireFlag = expireFlag;
	}

	/**
	 * Sets the fhir ID.
	 *
	 * @param fhirID the new fhir ID
	 */
	@JsonSetter("fhirID")
	public void setFhirID(java.lang.String fhirID) {
		this.fhirID = fhirID;
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
	 * Sets the via FHIR.
	 *
	 * @param viaFHIR the new via FHIR
	 */
	@JsonSetter("viaFHIR")
	public void setViaFHIR(java.lang.Boolean viaFHIR) {
		this.viaFHIR = viaFHIR;
	}

	/**
	 * Sets the acd study patient.
	 *
	 * @param acdStudyPatient the new acd study patient
	 */
	@JsonSetter("acdStudyPatient")
	public void setAcdStudyPatient(Boolean acdStudyPatient) {
		this.acdStudyPatient = acdStudyPatient;
	}

	/**
	 * To java.lang.String.
	 *
	 * @return the java.lang.String
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public java.lang.String toString() {
		String expire;
		if (expireFlag) {
			expire = "EXPIRED: " + dateFormat.format(dod);
		} else {
			expire = "";
		}
		if (street == null || street.isEmpty()) {
			return "Patient: SubjectId: " + subjectId + "\nFirst: " + firstName + "\nLast: " + lastName + "\ndob: "
					+ dateFormat.format(dob) + " (" + getAgeAsString() + ") (" + gender + ")\n" + expire + "\n";
		} else {
			if (viaFHIR) {
				try {
					return "Patient: SubjectId: " + subjectId + "\nFirst: " + firstName + "\nLast: " + lastName
							+ "\ndob: " + getDobAsString() + " (" + getAgeAsString() + ") (" + gender + ")\n"
							+ "Address: " + street + "\n" + city + ", " + state + " " + postal + "\n" + expire;
				} catch (Exception e) {
					e.printStackTrace();
					return "Patient: SubjectId: " + subjectId + "\nFirst: " + firstName + "\nLast: " + lastName
							+ "\ndob: " + getDobAsString() + " (" + getAgeAsString() + ") (" + gender + ")\n"
							+ "Address: " + street + "\n" + city + ", " + state + " " + postal + "\n" + expire;

				}

			} else {
				return "Patient: SubjectId: " + subjectId + "\nFirst: " + firstName + "\nLast: " + lastName + "\ndob: "
						+ getDobAsString() + " (" + getAgeAsString() + ") (" + gender + ")\n" + "Address: " + street
						+ "\n" + city + ", " + state + " " + postal + "\n" + expire
						+ "\n\nWARNING: Random MIMIC III due to FHIR server unavailble\n";
			}
		}
	}

	/**
	 * Handle all the various combinations of age. For <18 we show Y+M and if <1y
	 * show M and if <1mo show "<1mo"
	 * 
	 * @return the age as string
	 */
	@Transient
	public String getAgeAsString() {
		// Handle all the various combinations of age. For <18 we show Y+M and if
		// <1y show M and if <1mo show "<1mo"
		String age = "";
		if (getAgeInYears() >= 18) {
			age = getAgeInYears() + "y";
		} else {
			if (getAgeInYears() == 0) {
				if (getAgeInMonths() == 0) {
					age = " < 1 mo";
				} else {
					age = getAgeInMonths() + "mo";
				}
			} else {
				age = getAgeInYears() + "y " + getAgeInMonths() % 12 + "mo";
			}
		}
		return age;
	}

	/**
	 * Gets the age in months, derived from dob.
	 * 
	 * @return the age in months
	 */
	@SuppressWarnings("deprecation")
	@Transient
	public int getAgeInMonths() {
		if (dob == null) {
			return 0;
		}

		Date endDate;

		if (expireFlag) {
			endDate = dod;
		} else {
			endDate = new Date();
		}

		final int yearDiff = endDate.getYear() - dob.getYear();
		int monthDiff = endDate.getMonth() - dob.getMonth();
		final int dayDiff = endDate.getDate() - dob.getDate();

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
	@Transient
	public int getAgeInYears() {
		return getAgeInMonths() / 12;
	}

	/**
	 * Gets the dob as java.lang.String.
	 *
	 * @return the dob as java.lang.String
	 */
	@Transient
	public java.lang.String getDobAsString() {
		return FhirUtil.toIsoDate(dob, false);
	}

	/**
	 * Gets the as HTML.
	 *
	 * @return the as HTML
	 */
	@Transient
	public String getAsHTML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<div>");
		sb.append("<b>Name: </b>" + getLastName() + ", " + getFirstName() + "<br>\n");
		sb.append("<b>MRN: </b>" + getSubjectId() + "<br>\n");
		sb.append("<b>DOB: </b><i>" + getDobAsString() + "</i><br>\n");
		sb.append("<table>\n<tr valign= \"top\"><td><b>Address</b></td><td>\n");
		sb.append(getStreet() + "<br>\n");
		sb.append(getCity() + ", " + getState() + " " + getPostal() + "</td></tr></table>\n");
		return sb.toString();
	}
}
