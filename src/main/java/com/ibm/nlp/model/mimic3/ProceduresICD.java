package com.ibm.nlp.model.mimic3;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

@Entity
@Table(name = "PROCEDURES_ICD")
@JsonRootName(value = "ProceduresICD")
public class ProceduresICD extends BaseEntity implements Serializable, HasValue {

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

	@Column(name = "SEQ_NUM")
	private Integer SequenceNumber;

	@Column(name = "ICD9_CODE")
	private String icd9Code;

	public ProceduresICD() {
		super();
	}

	public ProceduresICD(Integer rowId, Integer subjectId, Integer hadmId, Integer sequenceNumber, String icd9Code) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		SequenceNumber = sequenceNumber;
		this.icd9Code = icd9Code;
	}

	@JsonProperty("rowId")
	public Integer getRowId() {
		return rowId;
	}

	@JsonProperty("subjectId")
	public Integer getSubjectId() {
		return subjectId;
	}

	@JsonProperty("hadmId")
	public Integer getHadmId() {
		return hadmId;
	}

	@JsonProperty("SequenceNumber")
	public Integer getSequenceNumber() {
		return SequenceNumber;
	}

	@JsonProperty("icd9Code")
	public String getIcd9Code() {
		return icd9Code;
	}

	@JsonSetter("rowId")
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	@JsonSetter("subjectId")
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@JsonSetter("hadmId")
	public void setHadmId(Integer hadmId) {
		this.hadmId = hadmId;
	}

	@JsonSetter("SequenceNumber")
	public void setSequenceNumber(Integer sequenceNumber) {
		SequenceNumber = sequenceNumber;
	}

	@JsonSetter("icd9Code")
	public void setIcd9Code(String icd9Code) {
		this.icd9Code = icd9Code;
	}

	@Override
	public String getValueAsString() {
		return icd9Code;
	}

	@Override
	public String getValueWithDetailAsString() {
		return "ICD-9: " + icd9Code;
	}

}
