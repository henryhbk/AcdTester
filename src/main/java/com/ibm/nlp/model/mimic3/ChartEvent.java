package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * The Class ChartEvent.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "chartevents")
@JsonRootName(value = "ChartEvent")
public class ChartEvent extends BaseEntity implements Serializable, HasValue {

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

	/** The icu stay id. */
	@Column(name = "ICUSTAY_ID")
	private Integer icuStayId;

	/** The d item. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", nullable = false)
	private DItem dItem;

	/** The chartdate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CHARTTIME")
	private Date chartTime;

	/** The chartdate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STORETIME")
	private Date storeTime;

	/** The cgid. */
	@Column(name = "CGID")
	private Integer cgid;

	/** The value. */
	@Column(name = "VALUE")
	private String value;

	/** The value num. */
	@Column(name = "VALUENUM")
	private BigDecimal valueNum;

	/** The value uom. */
	@Column(name = "VALUEUOM")
	private String valueUom;

	/** The warning. */
	@Column(name = "WARNING")
	private Boolean warning;

	/** The error. */
	@Column(name = "ERROR")
	private Boolean error;

	/** The result status. */
	@Column(name = "RESULTSTATUS")
	private String resultStatus;

	/** The stopped. */
	@Column(name = "STOPPED")
	private String stopped;

	/** The acd study. */
	@Column(name = "acd_study")
	private Boolean acd_study;

	/**
	 * Instantiates a new chart event.
	 */
	public ChartEvent() {
		super();
	}

	/**
	 * Instantiates a new chart event.
	 *
	 * @param rowId        the row id
	 * @param subjectId    the subject id
	 * @param hadmId       the hadm id
	 * @param icuStayId    the icu stay id
	 * @param dItem        the d item
	 * @param itemId       the item id
	 * @param chartTime    the chart time
	 * @param storeTime    the store time
	 * @param cgid         the cgid
	 * @param value        the value
	 * @param valueNum     the value num
	 * @param valueUom     the value uom
	 * @param warning      the warning
	 * @param error        the error
	 * @param resultStatus the result status
	 * @param stopped      the stopped
	 * @param acd_study    the acd study
	 */
	public ChartEvent(int rowId, Integer subjectId, Integer hadmId, Integer icuStayId, DItem dItem, Date chartTime,
			Date storeTime, Integer cgid, String value, BigDecimal valueNum, String valueUom, Boolean warning,
			Boolean error, String resultStatus, String stopped, Boolean acd_study) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.icuStayId = icuStayId;
		this.dItem = dItem;
		this.chartTime = chartTime;
		this.storeTime = storeTime;
		this.cgid = cgid;
		this.value = value;
		this.valueNum = valueNum;
		this.valueUom = valueUom;
		this.warning = warning;
		this.error = error;
		this.resultStatus = resultStatus;
		this.stopped = stopped;
		this.acd_study = acd_study;
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
	 * Gets the icu stay id.
	 *
	 * @return the icu stay id
	 */
	@JsonProperty("icuStayId")
	public Integer getIcuStayId() {
		return icuStayId;
	}

	/**
	 * Gets the d item.
	 *
	 * @return the d item
	 */
	@JsonProperty("dItem")
	public DItem getdItem() {
		return dItem;
	}

	/**
	 * Gets the chart time.
	 *
	 * @return the chart time
	 */
	@JsonProperty("chartTime")
	public Date getChartTime() {
		return chartTime;
	}

	/**
	 * Gets the store time.
	 *
	 * @return the store time
	 */
	@JsonProperty("storeTime")
	public Date getStoreTime() {
		return storeTime;
	}

	/**
	 * Gets the cgid.
	 *
	 * @return the cgid
	 */
	@JsonProperty("cgid")
	public Integer getCgid() {
		return cgid;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	/**
	 * Gets the value num.
	 *
	 * @return the value num
	 */
	@JsonProperty("valueNum")
	public BigDecimal getValueNum() {
		return valueNum;
	}

	/**
	 * Gets the value uom.
	 *
	 * @return the value uom
	 */
	@JsonProperty("valueUom")
	public String getValueUom() {
		return valueUom;
	}

	/**
	 * Gets the warning.
	 *
	 * @return the warning
	 */
	@JsonProperty("warning")
	public Boolean getWarning() {
		return warning;
	}

	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	@JsonProperty("error")
	public Boolean getError() {
		return error;
	}

	/**
	 * Gets the result status.
	 *
	 * @return the result status
	 */
	@JsonProperty("resultStatus")
	public String getResultStatus() {
		return resultStatus;
	}

	/**
	 * Gets the stopped.
	 *
	 * @return the stopped
	 */
	@JsonProperty("stopped")
	public String getStopped() {
		return stopped;
	}

	/**
	 * Gets the acd study.
	 *
	 * @return the acd study
	 */
	@JsonProperty("acd_study")
	public Boolean getAcd_study() {
		return acd_study;
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
	 * Sets the icu stay id.
	 *
	 * @param icuStayId the new icu stay id
	 */
	@JsonSetter("icuStayId")
	public void setIcuStayId(Integer icuStayId) {
		this.icuStayId = icuStayId;
	}

	/**
	 * Sets the d item.
	 *
	 * @param dItem the new d item
	 */
	@JsonSetter("dItem")
	public void setdItem(DItem dItem) {
		this.dItem = dItem;
	}

	/**
	 * Sets the chart time.
	 *
	 * @param chartTime the new chart time
	 */
	@JsonSetter("chartTime")
	public void setChartTime(Date chartTime) {
		this.chartTime = chartTime;
	}

	/**
	 * Sets the store time.
	 *
	 * @param storeTime the new store time
	 */
	@JsonSetter("storeTime")
	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}

	/**
	 * Sets the cgid.
	 *
	 * @param cgid the new cgid
	 */
	@JsonSetter("cgid")
	public void setCgid(Integer cgid) {
		this.cgid = cgid;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	@JsonSetter("value")
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Sets the value num.
	 *
	 * @param valueNum the new value num
	 */
	@JsonSetter("valueNum")
	public void setValueNum(BigDecimal valueNum) {
		this.valueNum = valueNum;
	}

	/**
	 * Sets the value uom.
	 *
	 * @param valueUom the new value uom
	 */
	@JsonSetter("valueUom")
	public void setValueUom(String valueUom) {
		this.valueUom = valueUom;
	}

	/**
	 * Sets the warning.
	 *
	 * @param warning the new warning
	 */
	@JsonSetter("warning")
	public void setWarning(Boolean warning) {
		this.warning = warning;
	}

	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	@JsonSetter("error")
	public void setError(Boolean error) {
		this.error = error;
	}

	/**
	 * Sets the result status.
	 *
	 * @param resultStatus the new result status
	 */
	@JsonSetter("resultStatus")
	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}

	/**
	 * Sets the stopped.
	 *
	 * @param stopped the new stopped
	 */
	@JsonSetter("stopped")
	public void setStopped(String stopped) {
		this.stopped = stopped;
	}

	/**
	 * Sets the acd study.
	 *
	 * @param acd_study the new acd study
	 */
	@JsonSetter("acd_study")
	public void setAcd_study(Boolean acd_study) {
		this.acd_study = acd_study;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Override
	@Transient
	public String getValueAsString() {
		return value + valueUom;
	}

	@Override
	@Transient
	public String getValueWithDetailAsString() {
		return getValueAsString() + " from " + dItem.getCategory();
	}

	@Override
	public String toString() {
		return "ChartEvent [rowId=" + rowId + ", subjectId=" + subjectId + ", hadmId=" + hadmId + ", icuStayId="
				+ icuStayId + ", dItem=" + dItem + ", chartTime=" + chartTime + ", storeTime=" + storeTime + ", cgid="
				+ cgid + ", value=" + value + ", valueNum=" + valueNum + ", valueUom=" + valueUom + ", warning="
				+ warning + ", error=" + error + ", resultStatus=" + resultStatus + ", stopped=" + stopped
				+ ", acd_study=" + acd_study + "]";
	}

}
