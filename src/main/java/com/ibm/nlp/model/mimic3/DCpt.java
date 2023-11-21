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

/**
 * The Class DCpt is the sub-record for the CPT Events to provide detail about
 * the CPT.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "D_CPT")
@JsonRootName(value = "DCpt")
public class DCpt extends BaseEntity implements Serializable, HasValue {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The category. */
	@Column(name = "CATEGORY")
	private Integer category;

	/** The section range. */
	@Column(name = "SECTIONRANGE")
	private String sectionRange;

	/** The sub section range. */
	@Column(name = "SUBSECTIONRANGE")
	private String subSectionRange;

	/** The sub section header. */
	@Column(name = "SECTIONHEADER")
	private String sectionHeader;

	/** The sub section header. */
	@Column(name = "SUBSECTIONHEADER")
	private String subSectionHeader;

	/** The code suffix. */
	@Column(name = "CODESUFFIX")
	private String codeSuffix;

	/** The min code in sub section. */
	@Column(name = "MINCODEINSUBSECTION")
	private Integer minCodeInSubSection;

	/** The max code in sub section. */
	@Column(name = "MAXCODEINSUBSECTION")
	private Integer maxCodeInSubSection;

	/**
	 * Instantiates a new d cpt.
	 */
	public DCpt() {
		super();
	}

	/**
	 * Instantiates a new d cpt.
	 *
	 * @param rowId               the row id
	 * @param category            the category
	 * @param sectionRange        the section range
	 * @param sectionHeader       the section header
	 * @param subSectionRange     the sub section range
	 * @param subSectionHeader    the sub section header
	 * @param codeSuffix          the code suffix
	 * @param minCodeInSubSection the min code in sub section
	 * @param maxCodeInSubSection the max code in sub section
	 */
	public DCpt(Integer rowId, Integer category, String sectionRange, String sectionHeader, String subSectionRange,
			String subSectionHeader, String codeSuffix, Integer minCodeInSubSection, Integer maxCodeInSubSection) {
		super();
		this.rowId = rowId;
		this.category = category;
		this.sectionRange = sectionRange;
		this.subSectionRange = subSectionRange;
		this.subSectionHeader = subSectionHeader;
		this.codeSuffix = codeSuffix;
		this.minCodeInSubSection = minCodeInSubSection;
		this.maxCodeInSubSection = maxCodeInSubSection;
		this.sectionHeader = sectionHeader;
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
	 * Gets the section range.
	 *
	 * @return the section range
	 */
	@JsonProperty("sectionRange")
	public String getSectionRange() {
		return sectionRange;
	}

	/**
	 * Gets the sub section range.
	 *
	 * @return the sub section range
	 */
	@JsonProperty("subSectionRange")
	public String getSubSectionRange() {
		return subSectionRange;
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
	 * Gets the code suffix.
	 *
	 * @return the code suffix
	 */
	@JsonProperty("codeSuffix")
	public String getCodeSuffix() {
		return codeSuffix;
	}

	/**
	 * Gets the min code in sub section.
	 *
	 * @return the min code in sub section
	 */
	@JsonProperty("minCodeInSubSection")
	public Integer getMinCodeInSubSection() {
		return minCodeInSubSection;
	}

	/**
	 * Gets the max code in sub section.
	 *
	 * @return the max code in sub section
	 */
	@JsonProperty("maxCodeInSubSection")
	public Integer getMaxCodeInSubSection() {
		return maxCodeInSubSection;
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
	 * Sets the section header.
	 *
	 * @param sectionHeader the new section header
	 */
	@JsonSetter("sectionHeader")
	public void setSectionHeader(String sectionHeader) {
		this.sectionHeader = sectionHeader;
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
	 * Sets the section range.
	 *
	 * @param sectionRange the new section range
	 */
	@JsonSetter("sectionRange")
	public void setSectionRange(String sectionRange) {
		this.sectionRange = sectionRange;
	}

	/**
	 * Sets the sub section range.
	 *
	 * @param subSectionRange the new sub section range
	 */
	@JsonSetter("subSectionRange")
	public void setSubSectionRange(String subSectionRange) {
		this.subSectionRange = subSectionRange;
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
	 * Sets the code suffix.
	 *
	 * @param codeSuffix the new code suffix
	 */
	@JsonSetter("codeSuffix")
	public void setCodeSuffix(String codeSuffix) {
		this.codeSuffix = codeSuffix;
	}

	/**
	 * Sets the min code in sub section.
	 *
	 * @param minCodeInSubSection the new min code in sub section
	 */
	@JsonSetter("minCodeInSubSection")
	public void setMinCodeInSubSection(Integer minCodeInSubSection) {
		this.minCodeInSubSection = minCodeInSubSection;
	}

	/**
	 * Sets the max code in sub section.
	 *
	 * @param maxCodeInSubSection the new max code in sub section
	 */
	@JsonSetter("maxCodeInSubSection")
	public void setMaxCodeInSubSection(Integer maxCodeInSubSection) {
		this.maxCodeInSubSection = maxCodeInSubSection;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Override
	public String getValueAsString() {
		return "CPT: " + sectionHeader;

	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Override
	public String getValueWithDetailAsString() {
		return "CPT: " + sectionHeader + " range: " + subSectionRange;

	}

}
