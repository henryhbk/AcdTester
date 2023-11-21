package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class MicrobiolgyEvent.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "MICROBIOLOGYEVENTS")
@JsonRootName(value = "ChartEvent")
public class MicrobiolgyEvent extends BaseEntity implements Serializable, HasValue {

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

	/** The d item. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SPEC_ITEMID", referencedColumnName = "ITEMID", nullable = false)
	private DItem dItem;

	/** The specimen type. */
	@Column(name = "SPEC_TYPE_DESC")
	private String specimenType;

	/** The org item id. */
	@Column(name = "ORG_ITEMID")
	private Integer orgItemId;

	/** The organism name. */
	@Column(name = "ORG_NAME")
	private String organismName;

	/** The isolate number. */
	@Column(name = "ISOLATE_NUM")
	private Integer isolateNumber;

	/** The antibiotic item id. */
	@Column(name = "AB_ITEMID")
	private Integer antibioticItemId;

	/** The antibiotic NAME. */
	@Column(name = "AB_NAME")
	private String antibioticName;

	/** The dilution text. */
	@Column(name = "DILUTION_TEXT")
	private String dilutionText;

	/** The dilution value. */
	@Column(name = "DILUTION_VALUE")
	private Integer dilutionValue;

	/** The sensitvity. */
	@Column(name = "INTERPRETATION")
	private String sensitvity;

	/** The chartdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CHARTDATE")
	private Date chartdate;

	/**
	 * Instantiates a new microbiolgy event.
	 */
	public MicrobiolgyEvent() {
	}

	/**
	 * Instantiates a new microbiolgy event.
	 *
	 * @param rowId            the row id
	 * @param subjectId        the subject id
	 * @param hadmId           the hadm id
	 * @param dItem            the d item
	 * @param specimenType     the specimen type
	 * @param orgItemId        the org item id
	 * @param organismName     the organism name
	 * @param isolateNumber    the isolate number
	 * @param antibioticItemId the antibiotic item id
	 * @param antibioticName   the antibiotic name
	 * @param dilutionText     the dilution text
	 * @param dilutionValue    the dilution value
	 * @param sensitvity       the sensitvity
	 */
	public MicrobiolgyEvent(Integer rowId, Integer subjectId, Integer hadmId, DItem dItem, String specimenType,
			Integer orgItemId, String organismName, Integer isolateNumber, Integer antibioticItemId,
			String antibioticName, String dilutionText, Integer dilutionValue, String sensitvity) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.specimenType = specimenType;
		this.orgItemId = orgItemId;
		this.organismName = organismName;
		this.isolateNumber = isolateNumber;
		this.antibioticItemId = antibioticItemId;
		this.antibioticName = antibioticName;
		this.dilutionText = dilutionText;
		this.dilutionValue = dilutionValue;
		this.sensitvity = sensitvity;
		this.dItem = dItem;
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

	/**
	 * Gets the spec item id.
	 *
	 * @return the spec item id
	 */
	@JsonProperty("dItem")
	public DItem getDItem() {
		return dItem;
	}

	/**
	 * Gets the specimen type.
	 *
	 * @return the specimen type
	 */
	@JsonProperty("specimenType")
	public String getSpecimenType() {
		return specimenType;
	}

	/**
	 * Gets the org item id.
	 *
	 * @return the org item id
	 */
	@JsonProperty("orgItemId")
	public Integer getOrgItemId() {
		return orgItemId;
	}

	/**
	 * Gets the organism name.
	 *
	 * @return the organism name
	 */
	@JsonProperty("organismName")
	public String getOrganismName() {
		return organismName;
	}

	/**
	 * Gets the isolate number.
	 *
	 * @return the isolate number
	 */
	@JsonProperty("isolateNumber")
	public Integer getIsolateNumber() {
		return isolateNumber;
	}

	/**
	 * Gets the antibiotic item id.
	 *
	 * @return the antibiotic item id
	 */
	@JsonProperty("antibioticItemId")
	public Integer getAntibioticItemId() {
		return antibioticItemId;
	}

	/**
	 * Gets the antibiotic NAME.
	 *
	 * @return the antibiotic NAME
	 */
	@JsonProperty("antibioticItemId")
	public String getantibioticName() {
		return antibioticName;
	}

	/**
	 * Gets the dilution text.
	 *
	 * @return the dilution text
	 */
	@JsonProperty("dilutionText")
	public String getDilutionText() {
		return dilutionText;
	}

	/**
	 * Gets the dilution value.
	 *
	 * @return the dilution value
	 */
	@JsonProperty("dilutionValue")
	public Integer getDilutionValue() {
		return dilutionValue;
	}

	/**
	 * Gets the sensitvity.
	 *
	 * @return the sensitvity
	 */
	@JsonProperty("sensitvity")
	public String getSensitvity() {
		return sensitvity;
	}

	/**
	 * Gets the chartdate.
	 *
	 * @return the chartdate
	 */
	@JsonProperty("chartdate")
	public Date getChartdate() {
		return chartdate;
	}

	/**
	 * Sets the chartdate.
	 *
	 * @param chartdate the new chartdate
	 */
	@JsonSetter("chartdate")
	public void setChartdate(Date chartdate) {
		this.chartdate = chartdate;
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

	/**
	 * Sets the spec item id.
	 *
	 * @param dItem the new d item
	 */
	@JsonSetter("hadmId")
	public void setDItem(DItem dItem) {
		this.dItem = dItem;
	}

	/**
	 * Sets the specimen type.
	 *
	 * @param specimenType the new specimen type
	 */
	@JsonSetter("specimenType")
	public void setSpecimenType(String specimenType) {
		this.specimenType = specimenType;
	}

	/**
	 * Sets the org item id.
	 *
	 * @param orgItemId the new org item id
	 */
	@JsonSetter("orgItemId")
	public void setOrgItemId(Integer orgItemId) {
		this.orgItemId = orgItemId;
	}

	/**
	 * Sets the organism name.
	 *
	 * @param organismName the new organism name
	 */
	@JsonSetter("organismName")
	public void setOrganismName(String organismName) {
		this.organismName = organismName;
	}

	/**
	 * Sets the isolate number.
	 *
	 * @param isolateNumber the new isolate number
	 */
	@JsonSetter("isolateNumber")
	public void setIsolateNumber(Integer isolateNumber) {
		this.isolateNumber = isolateNumber;
	}

	/**
	 * Sets the antibiotic item id.
	 *
	 * @param antibioticItemId the new antibiotic item id
	 */
	@JsonSetter("antibioticItemId")
	public void setAntibioticItemId(Integer antibioticItemId) {
		this.antibioticItemId = antibioticItemId;
	}

	/**
	 * Sets the antibiotic NAME.
	 *
	 * @param antibioticNAME the new antibiotic NAME
	 */
	@JsonSetter("antibioticName")
	public void setantibioticName(String antibioticNAME) {
		this.antibioticName = antibioticNAME;
	}

	/**
	 * Sets the dilution text.
	 *
	 * @param dilutionText the new dilution text
	 */
	@JsonSetter("dilutionText")
	public void setDilutionText(String dilutionText) {
		this.dilutionText = dilutionText;
	}

	/**
	 * Sets the dilution value.
	 *
	 * @param dilutionValue the new dilution value
	 */
	@JsonSetter("dilutionValue")
	public void setDilutionValue(Integer dilutionValue) {
		this.dilutionValue = dilutionValue;
	}

	/**
	 * Sets the sensitvity.
	 *
	 * @param sensitvity the new sensitvity
	 */
	@JsonSetter("sensitvity")
	public void setSensitvity(String sensitvity) {
		this.sensitvity = sensitvity;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	@Override
	public String getValueAsString() {
		return organismName;
	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	@Override
	public String getValueWithDetailAsString() {
		if (organismName == null) {
			return "Final - No Growth";
		}
		if (sensitvity != null) {
			return "\t" + antibioticName + "   " + dilutionText + "  " + dilutionValue + ": " + sensitvity + "\n";
		} else {
			return "\t" + antibioticName + "   " + dilutionText + "  " + dilutionValue + ": NA" + "\n";
		}
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "MicrobiolgyEvent [rowId=" + rowId + ", subjectId=" + subjectId + ", hadmId=" + hadmId + ", dItem="
				+ dItem + ", specimenType=" + specimenType + ", orgItemId=" + orgItemId + ", organismName="
				+ organismName + ", isolateNumber=" + isolateNumber + ", antibioticItemId=" + antibioticItemId
				+ ", antibioticNAME=" + antibioticName + ", dilutionText=" + dilutionText + ", dilutionValue="
				+ dilutionValue + ", sensitvity=" + sensitvity + ", chartdate=" + chartdate + "]";
	}

}
