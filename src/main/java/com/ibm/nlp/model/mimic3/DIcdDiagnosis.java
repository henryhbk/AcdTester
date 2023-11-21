package com.ibm.nlp.model.mimic3;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class DIcdDiagnosis.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "D_ICD_DIAGNOSIS")
@JsonRootName(value = "DIcdDiagnosis")
public class DIcdDiagnosis extends BaseEntity implements Serializable, HasValue {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The icd 9 code. */
	@Column(name = "ICD9_CODE")
	private Integer icd9Code;

	/** The short title. */
	@Column(name = "SHORT_TITLE")
	private String shortTitle;

	/** The long title. */
	@Column(name = "LONG_TITLE")
	private String longTitle;

	/**
	 * Instantiates a new d icd diagnosis.
	 */
	public DIcdDiagnosis() {
		super();
	}

	/**
	 * Instantiates a new d icd diagnosis.
	 *
	 * @param rowId      the row id
	 * @param icd9Code   the icd 9 code
	 * @param shortTitle the short title
	 * @param longTitle  the long title
	 */
	public DIcdDiagnosis(Integer rowId, Integer icd9Code, String shortTitle, String longTitle) {
		super();
		this.rowId = rowId;
		this.icd9Code = icd9Code;
		this.shortTitle = shortTitle;
		this.longTitle = longTitle;
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
	 * Gets the icd 9 code.
	 *
	 * @return the icd 9 code
	 */
	@JsonProperty("icd9Code")
	public Integer getIcd9Code() {
		return icd9Code;
	}

	/**
	 * Gets the short title.
	 *
	 * @return the short title
	 */
	@JsonProperty("shortTitle")
	public String getShortTitle() {
		return shortTitle;
	}

	/**
	 * Gets the long title.
	 *
	 * @return the long title
	 */
	@JsonProperty("longTitle")
	public String getLongTitle() {
		return longTitle;
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
	 * Sets the icd 9 code.
	 *
	 * @param icd9Code the new icd 9 code
	 */
	@JsonSetter("icd9Code")
	public void setIcd9Code(Integer icd9Code) {
		this.icd9Code = icd9Code;
	}

	/**
	 * Sets the short title.
	 *
	 * @param shortTitle the new short title
	 */
	@JsonSetter("shortTitle")
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	/**
	 * Sets the long title.
	 *
	 * @param longTitle the new long title
	 */
	@JsonSetter("longTitle")
	public void setLongTitle(String longTitle) {
		this.longTitle = longTitle;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	@Override
	public String getValueAsString() {
		return shortTitle;
	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	@Override
	public String getValueWithDetailAsString() {
		return longTitle;
	}

}
