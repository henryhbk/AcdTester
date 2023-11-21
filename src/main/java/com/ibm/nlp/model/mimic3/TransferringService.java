package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class TransferringService.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "SERVICES")
@NamedQuery(name = "TransferringService.findAll", query = "SELECT s FROM TransferringService s")
@JsonRootName(value = "TransferringService")
public class TransferringService extends BaseEntity implements Serializable {

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

	/** The startdate. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANSFERTIME")
	private Date startdate;

	/** The Previous service. */
	@Column(name = "PREV_SERVICE")
	private Date previousService;

	/** The Current service. */
	@Column(name = "CURR_SERVICE")
	private Date currentService;

	/**
	 * Instantiates a new transferring service.
	 */
	public TransferringService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new transferring service.
	 *
	 * @param rowId           the row id
	 * @param subjectId       the subject id
	 * @param hadmId          the hadm id
	 * @param startdate       the startdate
	 * @param previousService the previous service
	 * @param currentService  the current service
	 */
	public TransferringService(Integer rowId, Integer subjectId, Integer hadmId, Date startdate, Date previousService,
			Date currentService) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.startdate = startdate;
		this.previousService = previousService;
		this.currentService = currentService;
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
	 * Gets the startdate.
	 *
	 * @return the startdate
	 */
	@JsonProperty("startdate")
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * Gets the previous service.
	 *
	 * @return the previous service
	 */
	@JsonProperty("previousService")
	public Date getPreviousService() {
		return previousService;
	}

	/**
	 * Gets the current service.
	 *
	 * @return the current service
	 */
	@JsonProperty("currentService")
	public Date getCurrentService() {
		return currentService;
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
	 * Sets the startdate.
	 *
	 * @param startdate the new startdate
	 */
	@JsonSetter("startdate")
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * Sets the previous service.
	 *
	 * @param previousService the new previous service
	 */
	public void setPreviousService(Date previousService) {
		this.previousService = previousService;
	}

	/**
	 * Sets the current service.
	 *
	 * @param currentService the new current service
	 */
	public void setCurrentService(Date currentService) {
		this.currentService = currentService;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "TransferringService [rowId=" + rowId + ", subjectId=" + subjectId + ", hadmId=" + hadmId
				+ ", startdate=" + startdate + ", PreviousService=" + previousService + ", CurrentService="
				+ currentService + "]";
	}

}
