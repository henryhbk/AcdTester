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
 * The Class DItem.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "D_ITEMS")
@JsonRootName(value = "DItem")
public class DItem extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private int rowId;

	/** The Item id. */
	@Column(name = "ITEMID")
	private java.lang.Integer ItemId;

	/** The Item label. */
	@Column(name = "LABEL")
	private java.lang.String label;

	/** The Abbreviation. */
	@Column(name = "ABBREVIATION")
	private java.lang.String abbreviation;

	/** The Abbreviation. */
	@Column(name = "DBSOURCE")
	private java.lang.String dbSource;

	/** Links To. */
	@Column(name = "LINKSTO")
	private java.lang.String linksTo;

	/** Category. */
	@Column(name = "CATEGORY")
	private java.lang.String category;

	/** the Unit Name. */
	@Column(name = "UNITNAME")
	private java.lang.String unitName;

	/** the Parameter Type. */
	@Column(name = "PARAM_TYPE")
	private java.lang.String paramType;

	/** The concept id. */
	@Column(name = "CONCEPTID")
	private java.lang.Integer conceptId;

	/**
	 * Instantiates a new d item.
	 */
	public DItem() {
	}

	/**
	 * Instantiates a new d item.
	 *
	 * @param rowId        the row id
	 * @param itemId       the item id
	 * @param label        the label
	 * @param abbreviation the abbreviation
	 * @param dbSource     the db source
	 * @param linksTo      the links to
	 * @param category     the category
	 * @param unitName     the unit name
	 * @param paramType    the param type
	 * @param conceptId    the concept id
	 */
	public DItem(int rowId, Integer itemId, String label, String abbreviation, String dbSource, String linksTo,
			String category, String unitName, String paramType, Integer conceptId) {
		super();
		this.rowId = rowId;
		ItemId = itemId;
		this.label = label;
		this.abbreviation = abbreviation;
		this.dbSource = dbSource;
		this.linksTo = linksTo;
		this.category = category;
		this.unitName = unitName;
		this.paramType = paramType;
		this.conceptId = conceptId;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	@JsonProperty("rowId")
	public int getRowId() {
		return rowId;
	}

	/**
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	@JsonProperty("ItemId")
	public java.lang.Integer getItemId() {
		return ItemId;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	@JsonProperty("label")
	public java.lang.String getLabel() {
		return label;
	}

	/**
	 * Gets the abbreviation.
	 *
	 * @return the abbreviation
	 */
	@JsonProperty("abbreviation")
	public java.lang.String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * Gets the db source.
	 *
	 * @return the db source
	 */
	@JsonProperty("dbSource")
	public java.lang.String getDbSource() {
		return dbSource;
	}

	/**
	 * Gets the links to.
	 *
	 * @return the links to
	 */
	@JsonProperty("linksTo")
	public java.lang.String getLinksTo() {
		return linksTo;
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	@JsonProperty("category")
	public java.lang.String getCategory() {
		return category;
	}

	/**
	 * Gets the unit name.
	 *
	 * @return the unit name
	 */
	@JsonProperty("unitName")
	public java.lang.String getUnitName() {
		return unitName;
	}

	/**
	 * Gets the param type.
	 *
	 * @return the param type
	 */
	@JsonProperty("paramType")
	public java.lang.String getParamType() {
		return paramType;
	}

	/**
	 * Gets the concept id.
	 *
	 * @return the concept id
	 */
	@JsonProperty("conceptId")
	public java.lang.Integer getConceptId() {
		return conceptId;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	@JsonSetter("rowId")
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	@JsonSetter("ItemId")
	public void setItemId(java.lang.Integer itemId) {
		ItemId = itemId;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	@JsonSetter("label")
	public void setLabel(java.lang.String label) {
		this.label = label;
	}

	/**
	 * Sets the abbreviation.
	 *
	 * @param abbreviation the new abbreviation
	 */
	@JsonSetter("abbreviation")
	public void setAbbreviation(java.lang.String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * Sets the db source.
	 *
	 * @param dbSource the new db source
	 */
	@JsonSetter("dbSource")
	public void setDbSource(java.lang.String dbSource) {
		this.dbSource = dbSource;
	}

	/**
	 * Sets the links to.
	 *
	 * @param linksTo the new links to
	 */
	@JsonSetter("linksTo")
	public void setLinksTo(java.lang.String linksTo) {
		this.linksTo = linksTo;
	}

	/**
	 * Sets the category.
	 *
	 * @param category the new category
	 */
	@JsonSetter("category")
	public void setCategory(java.lang.String category) {
		this.category = category;
	}

	/**
	 * Sets the unit name.
	 *
	 * @param unitName the new unit name
	 */
	@JsonSetter("unitName")
	public void setUnitName(java.lang.String unitName) {
		this.unitName = unitName;
	}

	/**
	 * Sets the param type.
	 *
	 * @param paramType the new param type
	 */
	@JsonSetter("paramType")
	public void setParamType(java.lang.String paramType) {
		this.paramType = paramType;
	}

	/**
	 * Sets the concept id.
	 *
	 * @param conceptId the new concept id
	 */
	@JsonSetter("conceptId")
	public void setConceptId(java.lang.Integer conceptId) {
		this.conceptId = conceptId;
	}

	@Override
	public String toString() {
		return "DItem [rowId=" + rowId + ", ItemId=" + ItemId + ", label=" + label + ", abbreviation=" + abbreviation
				+ ", dbSource=" + dbSource + ", linksTo=" + linksTo + ", category=" + category + ", unitName="
				+ unitName + ", paramType=" + paramType + ", conceptId=" + conceptId + "]";
	}

}
