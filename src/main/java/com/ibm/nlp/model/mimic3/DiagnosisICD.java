package com.ibm.nlp.model.mimic3;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

@Entity
@Table(name = "DIAGNOSES_ICD")
@JsonRootName(value = "DiagnosisICD")
public class DiagnosisICD extends BaseEntity implements Serializable, HasValue {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private java.lang.Integer subjectId;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	@Column(name = "SEQ_NUM")
	private Integer sequenceNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ICD9_CODE", referencedColumnName = "ICD9_CODE", nullable = false)
	private DIcdDiagnosis dICDDiagnosis;

	public DiagnosisICD() {
		super();
	}

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
	public java.lang.Integer getSubjectId() {
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

	@JsonProperty("sequenceNumber")
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	@JsonProperty("dICDDiagnosis")
	public DIcdDiagnosis getdICDDiagnosis() {
		return dICDDiagnosis;
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
	public void setSubjectId(java.lang.Integer subjectId) {
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

	@JsonSetter("sequenceNumber")
	public void setSequenceNumber(Integer sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	@JsonSetter("dICDDiagnosis")
	public void setdICDDiagnosis(DIcdDiagnosis dICDDiagnosis) {
		this.dICDDiagnosis = dICDDiagnosis;
	}

	@Transient
	@Override
	public String getValueAsString() {
		return dICDDiagnosis.getShortTitle();
	}

	@Transient
	@Override
	public String getValueWithDetailAsString() {
		return dICDDiagnosis.getLongTitle();
	}

}
