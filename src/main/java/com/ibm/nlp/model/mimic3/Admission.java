package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class Admission.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "ADMISSIONS")
public class Admission extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	/** The subject id. */
	private Integer subjectId;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	/** The admit time. */
	@Column(name = "ADMITTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date admitTime;

	/** The discharge time. */
	@Column(name = "DISCHTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dischargeTime;

	/** The death time. */
	@Column(name = "DEATHTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date deathTime;

	/** The length of stay. */
	@Column(name = "length_of_stay")
	private Integer lengthOfStay;

	/** The admission type. */
	@Column(name = "ADMISSION_TYPE")
	private String admissionType;

	/** The admission location. */
	@Column(name = "ADMISSION_LOCATION")
	private String admissionLocation;

	/** The discharge location. */
	@Column(name = "DISCHARGE_LOCATION")
	private String dischargeLocation;

	/** The ed registration time. */
	@Column(name = "EDREGTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date edRegistrationTime;

	/** The ed out time. */
	@Column(name = "EDOUTTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date edOutTime;

	/** The diagnosis. */
	@Column(name = "DIAGNOSIS")
	private String diagnosis;

	/** The hospital expire flag. */
	@Column(name = "HOSPITAL_EXPIRE_FLAG")
	private Boolean hospitalExpireFlag;

	/** The has chart events. */
	@Column(name = "HAS_CHARTEVENTS_DATA")
	private Boolean hasChartEvents;

	/** The date formatter. */
	@Transient
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");

	/**
	 * Instantiates a new admission.
	 */
	public Admission() {
		super();
	}

	/**
	 * Instantiates a new admission.
	 *
	 * @param rowId              the row id
	 * @param subjectId          the subject id
	 * @param hadmId             the hadm id
	 * @param admitTime          the admit time
	 * @param dischargeTime      the discharge time
	 * @param deathTime          the death time
	 * @param lengthOfStay       the length of stay
	 * @param admissionType      the admission type
	 * @param admissionLocation  the admission location
	 * @param dischargeLocation  the discharge location
	 * @param edRegistrationTime the ed registration time
	 * @param edOutTime          the ed out time
	 * @param diagnosis          the diagnosis
	 * @param hospitalExpireFlag the hospital expire flag
	 * @param hasChartEvents     the has chart events
	 */
	public Admission(Integer rowId, Integer subjectId, Integer hadmId, Date admitTime, Date dischargeTime,
			Date deathTime, Integer lengthOfStay, String admissionType, String admissionLocation,
			String dischargeLocation, Date edRegistrationTime, Date edOutTime, String diagnosis,
			Boolean hospitalExpireFlag, Boolean hasChartEvents) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.admitTime = admitTime;
		this.dischargeTime = dischargeTime;
		this.deathTime = deathTime;
		this.admissionType = admissionType;
		this.admissionLocation = admissionLocation;
		this.dischargeLocation = dischargeLocation;
		this.edRegistrationTime = edRegistrationTime;
		this.edOutTime = edOutTime;
		this.diagnosis = diagnosis;
		this.hospitalExpireFlag = hospitalExpireFlag;
		this.hasChartEvents = hasChartEvents;
		this.lengthOfStay = lengthOfStay;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	@JsonProperty("rowId")
	public Integer getRowId() {
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
	 * Gets the hadm id.
	 *
	 * @return the hadm id
	 */
	@JsonProperty("hadmId")
	public Integer getHadmId() {
		return hadmId;
	}

	/**
	 * Gets the admit time.
	 *
	 * @return the admit time
	 */
	@JsonProperty("admitTime")
	public Date getAdmitTime() {
		return admitTime;
	}

	/**
	 * Gets the discharge time.
	 *
	 * @return the discharge time
	 */
	@JsonProperty("dischargeTime")
	public Date getDischargeTime() {
		return dischargeTime;
	}

	/**
	 * Gets the death time.
	 *
	 * @return the death time
	 */
	@JsonProperty("deathTime")
	public Date getDeathTime() {
		return deathTime;
	}

	/**
	 * Gets the admission type.
	 *
	 * @return the admission type
	 */
	@JsonProperty("admissionType")
	public String getAdmissionType() {
		return admissionType;
	}

	/**
	 * Gets the admission location.
	 *
	 * @return the admission location
	 */
	@JsonProperty("admissionLocation")
	public String getAdmissionLocation() {
		return admissionLocation;
	}

	/**
	 * Gets the discharge location.
	 *
	 * @return the discharge location
	 */
	@JsonProperty("dischargeLocation")
	public String getDischargeLocation() {
		return dischargeLocation;
	}

	/**
	 * Gets the ed registration time.
	 *
	 * @return the ed registration time
	 */
	@JsonProperty("edRegistrationTime")
	public Date getEdRegistrationTime() {
		return edRegistrationTime;
	}

	/**
	 * Gets the ed out time.
	 *
	 * @return the ed out time
	 */
	@JsonProperty("edOutTime")
	public Date getEdOutTime() {
		return edOutTime;
	}

	/**
	 * Gets the diagnosis.
	 *
	 * @return the diagnosis
	 */
	@JsonProperty("diagnosis")
	public String getDiagnosis() {
		return diagnosis;
	}

	/**
	 * Gets the hospital expire flag.
	 *
	 * @return the hospital expire flag
	 */
	@JsonProperty("hospitalExpireFlag")
	public Boolean getHospitalExpireFlag() {
		return hospitalExpireFlag;
	}

	/**
	 * Gets the checks for chart events.
	 *
	 * @return the checks for chart events
	 */
	@JsonProperty("hasChartEvents")
	public Boolean getHasChartEvents() {
		return hasChartEvents;
	}

	/**
	 * Gets the length of stay.
	 *
	 * @return the length of stay
	 */
	@JsonProperty("lengthOfStay")
	public Integer getLengthOfStay() {
		return lengthOfStay;
	}

	/**
	 * Sets the length of stay.
	 *
	 * @param lengthOfStay the new length of stay
	 */
	@JsonSetter("lengthOfStay")
	public void setLengthOfStay(Integer lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	@JsonSetter("rowId")
	public void setRowId(Integer rowId) {
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
	 * Sets the hadm id.
	 *
	 * @param hadmId the new hadm id
	 */
	@JsonSetter("hadmId")
	public void setHadmId(Integer hadmId) {
		this.hadmId = hadmId;
	}

	/**
	 * Sets the admit time.
	 *
	 * @param admitTime the new admit time
	 */
	@JsonSetter("admitTime")
	public void setAdmitTime(Date admitTime) {
		this.admitTime = admitTime;
	}

	/**
	 * Sets the discharge time.
	 *
	 * @param dischargeTime the new discharge time
	 */
	@JsonSetter("dischargeTime")
	public void setDischargeTime(Date dischargeTime) {
		this.dischargeTime = dischargeTime;
	}

	/**
	 * Sets the death time.
	 *
	 * @param deathTime the new death time
	 */
	@JsonSetter("deathTime")
	public void setDeathTime(Date deathTime) {
		this.deathTime = deathTime;
	}

	/**
	 * Sets the admission type.
	 *
	 * @param admissionType the new admission type
	 */
	@JsonSetter("admissionType")
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	/**
	 * Sets the admission location.
	 *
	 * @param admissionLocation the new admission location
	 */
	@JsonSetter("admissionLocation")
	public void setAdmissionLocation(String admissionLocation) {
		this.admissionLocation = admissionLocation;
	}

	/**
	 * Sets the discharge location.
	 *
	 * @param dischargeLocation the new discharge location
	 */
	@JsonSetter("dischargeLocation")
	public void setDischargeLocation(String dischargeLocation) {
		this.dischargeLocation = dischargeLocation;
	}

	/**
	 * Sets the ed registration time.
	 *
	 * @param edRegistrationTime the new ed registration time
	 */
	@JsonSetter("edRegistrationTime")
	public void setEdRegistrationTime(Date edRegistrationTime) {
		this.edRegistrationTime = edRegistrationTime;
	}

	/**
	 * Sets the ed out time.
	 *
	 * @param edOutTime the new ed out time
	 */
	@JsonSetter("edOutTime")
	public void setEdOutTime(Date edOutTime) {
		this.edOutTime = edOutTime;
	}

	/**
	 * Sets the diagnosis.
	 *
	 * @param diagnosis the new diagnosis
	 */
	@JsonSetter("diagnosis")
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	/**
	 * Sets the hospital expire flag.
	 *
	 * @param hospitalExpireFlag the new hospital expire flag
	 */
	@JsonSetter("hospitalExpireFlag")
	public void setHospitalExpireFlag(Boolean hospitalExpireFlag) {
		this.hospitalExpireFlag = hospitalExpireFlag;
	}

	/**
	 * Sets the checks for chart events.
	 *
	 * @param hasChartEvents the new checks for chart events
	 */
	@JsonSetter("hasChartEvents")
	public void setHasChartEvents(Boolean hasChartEvents) {
		this.hasChartEvents = hasChartEvents;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Admission [rowId=" + rowId + ", subjectId=" + subjectId + ", hadmId=" + hadmId + ", admitTime="
				+ admitTime + ", dischargeTime=" + dischargeTime + ", deathTime=" + deathTime + ", admissionType="
				+ admissionType + ", admissionLocation=" + admissionLocation + ", dischargeLocation="
				+ dischargeLocation + ", edRegistrationTime=" + edRegistrationTime + ", edOutTime=" + edOutTime
				+ ", diagnosis=" + diagnosis + ", hospitalExpireFlag=" + hospitalExpireFlag + ", hasChartEvents="
				+ hasChartEvents + "]";
	}

}
