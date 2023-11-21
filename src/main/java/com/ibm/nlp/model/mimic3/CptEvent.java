package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
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
 * The Class CptEvent.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "CPTEVENTS")
@JsonRootName(value = "CptEvent")
public class CptEvent extends BaseEntity implements Serializable, HasValue {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The category. */
	@Column(name = "CATEGORY")
	private Integer category;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	/** The cost center. */
	@Column(name = "COSTCENTER")
	private String costCenter;

	/** The chartdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CHARTDATE")
	private Date chartDate;

	/** The cpt code. */
	@Column(name = "CPT_CD")
	private String cptCode;

	/** The cpt number. */
	@Column(name = "CPT_NUMBNER")
	private Integer cptNumber;

	/** The cpt suffix. */
	@Column(name = "CPT_SUFFIX")
	private String cptSuffix;

	/** The ticket id sequence. */
	@Column(name = "TICKET_ID_SEQ")
	private Integer ticketIdSequence;

	/** The sub section header. */
	@Column(name = "SECTIONHEADER")
	private String sectionHeader;

	/** The sub section header. */
	@Column(name = "SUBSECTIONHEADER")
	private String subSectionHeader;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/**
	 * Instantiates a new cpt event.
	 */
	public CptEvent() {
		super();
	}

	/**
	 * Instantiates a new cpt event.
	 *
	 * @param rowId            the row id
	 * @param category         the category
	 * @param subjectId        the subject id
	 * @param hadmId           the hadm id
	 * @param costCenter       the cost center
	 * @param chartDate        the chart date
	 * @param cptCode          the cpt code
	 * @param cptNumber        the cpt number
	 * @param cptSuffix        the cpt suffix
	 * @param ticketIdSequence the ticket id sequence
	 * @param sectionHeader    the section header
	 * @param subSectionHeader the sub section header
	 * @param description      the description
	 */
	public CptEvent(Integer rowId, Integer category, Integer subjectId, Integer hadmId, String costCenter,
			Date chartDate, String cptCode, Integer cptNumber, String cptSuffix, Integer ticketIdSequence,
			String sectionHeader, String subSectionHeader, String description) {
		super();
		this.rowId = rowId;
		this.category = category;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.costCenter = costCenter;
		this.chartDate = chartDate;
		this.cptCode = cptCode;
		this.cptNumber = cptNumber;
		this.cptSuffix = cptSuffix;
		this.ticketIdSequence = ticketIdSequence;
		this.sectionHeader = sectionHeader;
		this.subSectionHeader = subSectionHeader;
		this.description = description;
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
	 * Gets the category.
	 *
	 * @return the category
	 */
	@JsonProperty("category")
	public Integer getCategory() {
		return category;
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
	 * Gets the cost center.
	 *
	 * @return the cost center
	 */
	@JsonProperty("costCenter")
	public String getCostCenter() {
		return costCenter;
	}

	/**
	 * Gets the chart date.
	 *
	 * @return the chart date
	 */
	@JsonProperty("chartDate")
	public Date getChartDate() {
		return chartDate;
	}

	/**
	 * Gets the cpt code.
	 *
	 * @return the cpt code
	 */
	@JsonProperty("cptCode")
	public String getCptCode() {
		return cptCode;
	}

	/**
	 * Gets the cpt number.
	 *
	 * @return the cpt number
	 */
	@JsonProperty("cptNumber")
	public Integer getCptNumber() {
		return cptNumber;
	}

	/**
	 * Gets the cpt suffix.
	 *
	 * @return the cpt suffix
	 */
	@JsonProperty("cptSuffix")
	public String getCptSuffix() {
		return cptSuffix;
	}

	/**
	 * Gets the ticket id sequence.
	 *
	 * @return the ticket id sequence
	 */
	@JsonProperty("ticketIdSequence")
	public Integer getTicketIdSequence() {
		return ticketIdSequence;
	}

	/**
	 * Gets the section header.
	 *
	 * @return the section header
	 */
	@JsonProperty("sectionHeader")
	public String getSectionHeader() {
		return sectionHeader;
	}

	/**
	 * Gets the sub section header.
	 *
	 * @return the sub section header
	 */
	@JsonProperty("subSectionHeader")
	public String getSubSectionHeader() {
		return subSectionHeader;
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
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	@JsonSetter("rowId")
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	@JsonSetter("category")
	public void setCategory(Integer category) {
		this.category = category;
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
	 * Sets the cost center.
	 *
	 * @param costCenter the new cost center
	 */
	@JsonSetter("costCenter")
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	/**
	 * Sets the chart date.
	 *
	 * @param chartDate the new chart date
	 */
	@JsonSetter("chartDate")
	public void setChartDate(Date chartDate) {
		this.chartDate = chartDate;
	}

	/**
	 * Sets the cpt code.
	 *
	 * @param cptCode the new cpt code
	 */
	@JsonSetter("cptCode")
	public void setCptCode(String cptCode) {
		this.cptCode = cptCode;
	}

	/**
	 * Sets the cpt number.
	 *
	 * @param cptNumber the new cpt number
	 */
	@JsonSetter("cptNumber")
	public void setCptNumber(Integer cptNumber) {
		this.cptNumber = cptNumber;
	}

	/**
	 * Sets the cpt suffix.
	 *
	 * @param cptSuffix the new cpt suffix
	 */
	@JsonSetter("cptSuffix")
	public void setCptSuffix(String cptSuffix) {
		this.cptSuffix = cptSuffix;
	}

	/**
	 * Sets the ticket id sequence.
	 *
	 * @param ticketIdSequence the new ticket id sequence
	 */
	@JsonSetter("ticketIdSequence")
	public void setTicketIdSequence(Integer ticketIdSequence) {
		this.ticketIdSequence = ticketIdSequence;
	}

	/**
	 * Sets the section header.
	 *
	 * @param sectionHeader the new section header
	 */
	@JsonSetter("sectionHeader")
	public void setSectionHeader(String sectionHeader) {
		this.sectionHeader = sectionHeader;
	}

	/**
	 * Sets the sub section header.
	 *
	 * @param subSectionHeader the new sub section header
	 */
	@JsonSetter("subSectionHeader")
	public void setSubSectionHeader(String subSectionHeader) {
		this.subSectionHeader = subSectionHeader;
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
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	@Override
	public String getValueAsString() {
		return cptCode;
	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	@Override
	public String getValueWithDetailAsString() {
		// TODO Auto-generated method stub
		return cptCode + " " + sectionHeader + " of " + subSectionHeader;
	}

}
