package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.math.BigDecimal;
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
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class IcuStay.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "ICUSTAYS")
@JsonRootName(value = "IcuStay")
public class IcuStay extends BaseEntity implements Serializable, HasValue {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	/** The icu stay id. */
	@Column(name = "ICUSTAY_ID")
	private Integer icuStayId;

	/** The db source. */
	@Column(name = "DBSOURCE")
	private String dbSource;

	/** The first care unit. */
	@Column(name = "FIRST_CAREUNIT")
	private String firstCareUnit;

	/** The last care unit. */
	@Column(name = "LAST_CAREUNIT")
	private String lastCareUnit;

	/** The first ward id. */
	@Column(name = "FIRST_WARDID")
	private Integer firstWardId;

	/** The last ward id. */
	@Column(name = "LAST_WARDID")
	private Integer lastWardId;

	/** The chartdate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INTIME")
	private Date inTime;

	/** The chartdate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "OUTTIME")
	private Date outTime;

	/** The length of stay. */
	@Column(name = "LOS")
	private BigDecimal lengthOfStay;

	/**
	 * Instantiates a new icu stay.
	 */
	public IcuStay() {
		super();
	}

	/**
	 * Instantiates a new icu stay.
	 *
	 * @param rowId         the row id
	 * @param subjectId     the subject id
	 * @param hadmId        the hadm id
	 * @param icuStayId     the icu stay id
	 * @param dbSource      the db source
	 * @param firstCareUnit the first care unit
	 * @param lastCareUnit  the last care unit
	 * @param firstWardId   the first ward id
	 * @param lastWardId    the last ward id
	 * @param inTime        the in time
	 * @param outTime       the out time
	 * @param lengthOfStay  the length of stay
	 */
	public IcuStay(Integer rowId, Integer subjectId, Integer hadmId, Integer icuStayId, String dbSource,
			String firstCareUnit, String lastCareUnit, Integer firstWardId, Integer lastWardId, Date inTime,
			Date outTime, BigDecimal lengthOfStay) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.icuStayId = icuStayId;
		this.dbSource = dbSource;
		this.firstCareUnit = firstCareUnit;
		this.lastCareUnit = lastCareUnit;
		this.firstWardId = firstWardId;
		this.lastWardId = lastWardId;
		this.inTime = inTime;
		this.outTime = outTime;
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
	 * Gets the icu stay id.
	 *
	 * @return the icu stay id
	 */
	@JsonProperty("icuStayId")
	public Integer getIcuStayId() {
		return icuStayId;
	}

	/**
	 * Gets the db source. This field is mostly irrelevant to non-BIDMC staff as
	 * historically we had 2 different ICU EMRs (currently is Metavision) rather
	 * than our main EMR called OMR.
	 *
	 * @return the db source
	 */
	@JsonProperty("dbSource")
	public String getDbSource() {
		return dbSource;
	}

	/**
	 * Gets the first care unit.
	 *
	 * @return the first care unit
	 */
	@JsonProperty("firstCareUnit")
	public String getFirstCareUnit() {
		return firstCareUnit;
	}

	/**
	 * Gets the last care unit.
	 *
	 * @return the last care unit
	 */
	@JsonProperty("lastCareUnit")
	public String getLastCareUnit() {
		return lastCareUnit;
	}

	/**
	 * Gets the first ward id.
	 *
	 * @return the first ward id
	 */
	@JsonProperty("firstWardId")
	public Integer getFirstWardId() {
		return firstWardId;
	}

	/**
	 * Gets the last ward id.
	 *
	 * @return the last ward id
	 */
	@JsonProperty("lastWardId")
	public Integer getLastWardId() {
		return lastWardId;
	}

	/**
	 * Gets the in time.
	 *
	 * @return the in time
	 */
	@JsonProperty("inTime")
	public Date getInTime() {
		return inTime;
	}

	/**
	 * Gets the out time.
	 *
	 * @return the out time
	 */
	@JsonProperty("outTime")
	public Date getOutTime() {
		return outTime;
	}

	/**
	 * Gets the length of stay.
	 *
	 * @return the length of stay
	 */
	@JsonProperty("lengthOfStay")
	public BigDecimal getLengthOfStay() {
		return lengthOfStay;
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
	 * Sets the icu stay id.
	 *
	 * @param icuStayId the new icu stay id
	 */
	@JsonSetter("icuStayId")
	public void setIcuStayId(Integer icuStayId) {
		this.icuStayId = icuStayId;
	}

	/**
	 * Sets the db source.
	 *
	 * @param dbSource the new db source
	 */
	@JsonSetter("dbSource")
	public void setDbSource(String dbSource) {
		this.dbSource = dbSource;
	}

	/**
	 * Sets the first care unit.
	 *
	 * @param firstCareUnit the new first care unit
	 */
	@JsonSetter("firstCareUnit")
	public void setFirstCareUnit(String firstCareUnit) {
		this.firstCareUnit = firstCareUnit;
	}

	/**
	 * Sets the last care unit.
	 *
	 * @param lastCareUnit the new last care unit
	 */
	@JsonSetter("lastCareUnit")
	public void setLastCareUnit(String lastCareUnit) {
		this.lastCareUnit = lastCareUnit;
	}

	/**
	 * Sets the first ward id.
	 *
	 * @param firstWardId the new first ward id
	 */
	@JsonSetter("firstWardId")
	public void setFirstWardId(Integer firstWardId) {
		this.firstWardId = firstWardId;
	}

	/**
	 * Sets the last ward id.
	 *
	 * @param lastWardId the new last ward id
	 */
	@JsonSetter("lastWardId")
	public void setLastWardId(Integer lastWardId) {
		this.lastWardId = lastWardId;
	}

	/**
	 * Sets the in time.
	 *
	 * @param inTime the new in time
	 */
	@JsonSetter("inTime")
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	/**
	 * Sets the out time.
	 *
	 * @param outTime the new out time
	 */
	@JsonSetter("outTime")
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	/**
	 * Sets the length of stay.
	 *
	 * @param lengthOfStay the new length of stay
	 */
	@JsonSetter("lengthOfStay")
	public void setLengthOfStay(BigDecimal lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	@Override
	public String getValueAsString() {
		return lastCareUnit + " for " + lengthOfStay + " days";
	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	@Override
	public String getValueWithDetailAsString() {
		return firstCareUnit + " to " + lastCareUnit + " for " + lengthOfStay + " days";
	}

}
