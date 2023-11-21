package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.ibm.nlp.hibernate.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the LABEVENTS database table.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "LABEVENTS")
@NamedQuery(name = "Labevent.findAll", query = "SELECT l FROM LabEvent l")
@JsonRootName(value = "LabEvent")
public class LabEvent extends BaseEntity implements Serializable, HasFhir {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The acd study. */
	@Column(name = "acd_study")
	private byte acdStudy;

	/** The charttime. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date charttime;

	/** The fhir json. */
	@Column(name = "fhir_json", nullable = true)
	private String fhirJson;

	/** The flag. */
	private String flag;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	/** The itemid. */
	private int itemid;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

	/** The value. */
	private String value;

	/** The valuenum. */
	private BigDecimal valuenum;

	/** The valueuom. */
	private String valueuom;

	/**
	 * Instantiates a new labevent.
	 */
	public LabEvent() {
	}

	/**
	 * Instantiates a new labevent.
	 *
	 * @param rowId     the row id
	 * @param acdStudy  the acd study
	 * @param charttime the charttime
	 * @param fhirJson  the fhir json
	 * @param flag      the flag
	 * @param hadmId    the hadm id
	 * @param itemid    the itemid
	 * @param subjectId the subject id
	 * @param value     the value
	 * @param valuenum  the valuenum
	 * @param valueuom  the valueuom
	 */
	public LabEvent(int rowId, byte acdStudy, Date charttime, String fhirJson, String flag, Integer hadmId,
			Integer itemid, Integer subjectId, String value, BigDecimal valuenum, String valueuom) {
		super();
		this.rowId = rowId;
		this.acdStudy = acdStudy;
		this.charttime = charttime;
		this.fhirJson = fhirJson;
		this.flag = flag;
		this.hadmId = hadmId;
		this.itemid = itemid;
		this.subjectId = subjectId;
		this.value = value;
		this.valuenum = valuenum;
		this.valueuom = valueuom;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	public Integer getRowId() {
		return rowId;
	}

	/**
	 * Gets the acd study.
	 *
	 * @return the acd study
	 */
	public byte getAcdStudy() {
		return acdStudy;
	}

	/**
	 * Gets the charttime.
	 *
	 * @return the charttime
	 */
	public Date getCharttime() {
		return charttime;
	}

	/**
	 * Gets the fhir json.
	 *
	 * @return the fhir json
	 */
	public String getFhirJson() {
		return fhirJson;
	}

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * Gets the hadm id.
	 *
	 * @return the hadm id
	 */
	public Integer getHadmId() {
		return hadmId;
	}

	/**
	 * Gets the itemid.
	 *
	 * @return the itemid
	 */
	public Integer getItemid() {
		return itemid;
	}

	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Gets the valuenum.
	 *
	 * @return the valuenum
	 */
	public BigDecimal getValuenum() {
		return valuenum;
	}

	/**
	 * Gets the valueuom.
	 *
	 * @return the valueuom
	 */
	public String getValueuom() {
		return valueuom;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the acd study.
	 *
	 * @param acdStudy the new acd study
	 */
	public void setAcdStudy(byte acdStudy) {
		this.acdStudy = acdStudy;
	}

	/**
	 * Sets the charttime.
	 *
	 * @param charttime the new charttime
	 */
	public void setCharttime(Date charttime) {
		this.charttime = charttime;
	}

	/**
	 * Sets the fhir json.
	 *
	 * @param fhirJson the new fhir json
	 */
	public void setFhirJson(String fhirJson) {
		this.fhirJson = fhirJson;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Sets the hadm id.
	 *
	 * @param hadmId the new hadm id
	 */
	public void setHadmId(Integer hadmId) {
		this.hadmId = hadmId;
	}

	/**
	 * Sets the itemid.
	 *
	 * @param itemid the new itemid
	 */
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Sets the valuenum.
	 *
	 * @param valuenum the new valuenum
	 */
	public void setValuenum(BigDecimal valuenum) {
		this.valuenum = valuenum;
	}

	/**
	 * Sets the valueuom.
	 *
	 * @param valueuom the new valueuom
	 */
	public void setValueuom(String valueuom) {
		this.valueuom = valueuom;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Labevent [rowId=" + rowId + ", acdStudy=" + acdStudy + ", charttime=" + charttime + ", fhirJson="
				+ fhirJson + ", flag=" + flag + ", hadmId=" + hadmId + ", itemid=" + itemid + ", subjectId=" + subjectId
				+ ", value=" + value + ", valuenum=" + valuenum + ", valueuom=" + valueuom + "]";
	}

}