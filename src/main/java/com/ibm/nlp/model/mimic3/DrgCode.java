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
 * The Class DrgCode.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "DRGCODES")
@JsonRootName(value = "DrgCode")
public class DrgCode extends BaseEntity implements Serializable, HasValue {
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

	/** The drg type. */
	@Column(name = "DRG_TYPE")
	private String drgType;

	/** The drg code. */
	@Column(name = "DRG_CODE")
	private String drgCode;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The drg severity. */
	@Column(name = "DRG_SEVERITY")
	private Integer drgSeverity;

	/** The drg mortality. */
	@Column(name = "DRG_MORTALITY")
	private Integer drgMortality;

	/**
	 * Instantiates a new drg code.
	 */
	public DrgCode() {
		super();
	}

	/**
	 * Instantiates a new drg code.
	 *
	 * @param rowId        the row id
	 * @param subjectId    the subject id
	 * @param hadmId       the hadm id
	 * @param drgType      the drg type
	 * @param drgCode      the drg code
	 * @param description  the description
	 * @param drgSeverity  the drg severity
	 * @param drgMortality the drg mortality
	 */
	public DrgCode(Integer rowId, Integer subjectId, Integer hadmId, String drgType, String drgCode, String description,
			Integer drgSeverity, Integer drgMortality) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.drgType = drgType;
		this.drgCode = drgCode;
		this.description = description;
		this.drgSeverity = drgSeverity;
		this.drgMortality = drgMortality;
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
	 * Gets the drg type. There are two types of DRG codes in the database which
	 * have overlapping ranges but distinct definitions for the codes. The three
	 * types of DRG codes in the MIMIC-III database are ‘HCFA’ (Health Care
	 * Financing Administration), ‘MS’ (Medicare), and ‘APR’ (All Payers Registry).
	 *
	 * @return the drg type
	 */
	@JsonProperty("drgType")
	public String getDrgType() {
		return drgType;
	}

	/**
	 * Gets the drg code.
	 *
	 * @return the drg code
	 */
	@JsonProperty("drgCode")
	public String getDrgCode() {
		return drgCode;
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
	 * Gets the drg severity.
	 *
	 * @return the drg severity
	 */
	@JsonProperty("drgSeverity")
	public Integer getDrgSeverity() {
		return drgSeverity;
	}

	/**
	 * Gets the drg mortality.
	 *
	 * @return the drg mortality
	 */
	@JsonProperty("drgMortality")
	public Integer getDrgMortality() {
		return drgMortality;
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
	 * Sets the drg type.
	 *
	 * @param drgType the new drg type
	 */
	@JsonSetter("drgType")
	public void setDrgType(String drgType) {
		this.drgType = drgType;
	}

	/**
	 * Sets the drg code.
	 *
	 * @param drgCode the new drg code
	 */
	@JsonSetter("drgCode")
	public void setDrgCode(String drgCode) {
		this.drgCode = drgCode;
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
	 * Sets the drg severity.
	 *
	 * @param drgSeverity the new drg severity
	 */
	@JsonSetter("drgSeverity")
	public void setDrgSeverity(Integer drgSeverity) {
		this.drgSeverity = drgSeverity;
	}

	/**
	 * Sets the drg mortality.
	 *
	 * @param drgMortality the new drg mortality
	 */
	@JsonSetter("drgMortality")
	public void setDrgMortality(Integer drgMortality) {
		this.drgMortality = drgMortality;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	@Override
	public String getValueAsString() {
		return null;
	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	@Override
	public String getValueWithDetailAsString() {
		String info = "";
		if (drgSeverity != null) {
			info += ", Severity Score: " + drgSeverity;
		}
		if (drgMortality != null) {
			info += ",  Mortality Score: " + drgMortality;
		}
		return "Type: " + drgType + " " + description + info;
	}

	@Override
	public String toString() {
		return "DrgCode [rowId=" + rowId + ", subjectId=" + subjectId + ", hadmId=" + hadmId + ", drgType=" + drgType
				+ ", drgCode=" + drgCode + ", description=" + description + ", drgSeverity=" + drgSeverity
				+ ", drgMortality=" + drgMortality + "]";
	}

}
