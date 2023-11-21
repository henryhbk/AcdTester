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
 * The Class LabItem.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "D_LABITEMS")
@JsonRootName(value = "LabItem")
public class LabItem extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The item id. */
	@Column(name = "itemid")
	private Integer itemId;

	/** The label. */
	@Column(name = "label")
	private String label;

	/** The fluid. */
	@Column(name = "fluid")
	private String fluid;

	/** The loinc code. */
	@Column(name = "loinc_code")
	private String loincCode;

	/**
	 * Instantiates a new lab item.
	 */
	public LabItem() {
		super();
	}

	/**
	 * Instantiates a new lab item.
	 *
	 * @param rowId     the row id
	 * @param itemId    the item id
	 * @param label     the label
	 * @param fluid     the fluid
	 * @param loincCode the loinc code
	 */
	public LabItem(Integer rowId, Integer itemId, String label, String fluid, String loincCode) {
		super();
		this.rowId = rowId;
		this.itemId = itemId;
		this.label = label;
		this.fluid = fluid;
		this.loincCode = loincCode;
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
	 * Gets the item id.
	 *
	 * @return the item id
	 */
	@JsonProperty("itemId")
	public Integer getItemId() {
		return itemId;
	}

	/**
	 * Gets the label.
	 *
	 * @return the label
	 */
	@JsonProperty("label")
	public String getLabel() {
		return label;
	}

	/**
	 * Gets the fluid.
	 *
	 * @return the fluid
	 */
	@JsonProperty("fluid")
	public String getFluid() {
		return fluid;
	}

	/**
	 * Gets the loinc code.
	 *
	 * @return the loinc code
	 */
	@JsonProperty("loincCode")
	public String getLoincCode() {
		return loincCode;
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
	 * Sets the item id.
	 *
	 * @param itemId the new item id
	 */
	@JsonSetter("itemId")
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	/**
	 * Sets the label.
	 *
	 * @param label the new label
	 */
	@JsonSetter("label")
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Sets the fluid.
	 *
	 * @param fluid the new fluid
	 */
	@JsonSetter("fluid")
	public void setFluid(String fluid) {
		this.fluid = fluid;
	}

	/**
	 * Sets the loinc code.
	 *
	 * @param loincCode the new loinc code
	 */
	@JsonSetter("loincCode")
	public void setLoincCode(String loincCode) {
		this.loincCode = loincCode;
	}

}
