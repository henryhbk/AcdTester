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
 * The Class ProcedureEvent.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "PROCEDUREEVENTS_MV")
@JsonRootName(value = "ProcedureEvent")
public class ProcedureEvent extends BaseEntity implements Serializable, HasValue {

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

	/** The start time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STARTTIME")
	private Date startTime;

	/** The end time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ENDTIME")
	private Date endTime;

	/** The d item. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ITEMID", referencedColumnName = "ITEMID", nullable = false)
	private DItem dItem;

	/** The value. */
	@Column(name = "value")
	private BigDecimal value;

	/** The value uom. */
	@Column(name = "VALUEUOM")
	private String valueUom;

	/** The location. */
	@Column(name = "LOCATION")
	private String location;

	/** The location category. */
	@Column(name = "LOCATIONCATEGORY")
	private String locationCategory;

	/** The store time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "STORETIME")
	private Date storeTime;

	/** The cgid. */
	@Column(name = "CGID")
	private Integer cgid;

	/** The order id. */
	@Column(name = "ORDERID")
	private Integer orderId;

	/** The link order id. */
	@Column(name = "LINKORDERID")
	private Integer linkOrderId;

	/** The order category name. */
	@Column(name = "ORDERCATEGORYNAME")
	private String orderCategoryName;

	/** The secondary order category name. */
	@Column(name = "SECONDARYORDERCATEGORYNAME")
	private String secondaryOrderCategoryName;

	/** The order category description. */
	@Column(name = "ORDERCATEGORYDESCRIPTION")
	private String orderCategoryDescription;

	/** The is open bag. */
	@Column(name = "ISOPENBAG")
	private Boolean isOpenBag;

	/** The contninue in next department. */
	@Column(name = "CONTINUEINNEXTDEPT")
	private Boolean contninueInNextDepartment;

	/** The cancel reason. */
	@Column(name = "CANCELREASON")
	private Integer cancelReason;

	/** The status description. */
	@Column(name = "STATUSDESCRIPTION")
	private String statusDescription;

	/** The coments edited by. */
	@Column(name = "COMENTS_EDITEDBY")
	private String comentsEditedBy;

	/** The coments canceled by. */
	@Column(name = "COMENTS_CANCELEDDBY")
	private String comentsCanceledBy;

	/** The comments date. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "COMMENTS_DATE")
	private Date commentsDate;

	/**
	 * Instantiates a new procedure event.
	 */
	public ProcedureEvent() {
		super();
	}

	/**
	 * Instantiates a new procedure event.
	 *
	 * @param rowId                      the row id
	 * @param subjectId                  the subject id
	 * @param hadmId                     the hadm id
	 * @param icuStayId                  the icu stay id
	 * @param startTime                  the start time
	 * @param endTime                    the end time
	 * @param dItem                      the d item
	 * @param value                      the value
	 * @param valueUom                   the value uom
	 * @param location                   the location
	 * @param locationCategory           the location category
	 * @param storeTime                  the store time
	 * @param cgid                       the cgid
	 * @param orderId                    the order id
	 * @param linkOrderId                the link order id
	 * @param orderCategoryName          the order category name
	 * @param secondaryOrderCategoryName the secondary order category name
	 * @param orderCategoryDESCRIPTION   the order category DESCRIPTION
	 * @param isOpenBag                  the is open bag
	 * @param contninueInNextDepartment  the contninue in next department
	 * @param cancelReason               the cancel reason
	 * @param statusDescription          the status description
	 * @param comentsEditedBy            the coments edited by
	 * @param comentsCanceledBy          the coments canceled by
	 * @param commentsDate               the comments date
	 */
	public ProcedureEvent(Integer rowId, Integer subjectId, Integer hadmId, Integer icuStayId, Date startTime,
			Date endTime, DItem dItem, BigDecimal value, String valueUom, String location, String locationCategory,
			Date storeTime, Integer cgid, Integer orderId, Integer linkOrderId, String orderCategoryName,
			String secondaryOrderCategoryName, String orderCategoryDESCRIPTION, Boolean isOpenBag,
			Boolean contninueInNextDepartment, Integer cancelReason, String statusDescription, String comentsEditedBy,
			String comentsCanceledBy, Date commentsDate) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.hadmId = hadmId;
		this.icuStayId = icuStayId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.dItem = dItem;
		this.value = value;
		this.valueUom = valueUom;
		this.location = location;
		this.locationCategory = locationCategory;
		this.storeTime = storeTime;
		this.cgid = cgid;
		this.orderId = orderId;
		this.linkOrderId = linkOrderId;
		this.orderCategoryName = orderCategoryName;
		secondaryOrderCategoryName = secondaryOrderCategoryName;
		this.orderCategoryDescription = orderCategoryDESCRIPTION;
		this.isOpenBag = isOpenBag;
		this.contninueInNextDepartment = contninueInNextDepartment;
		this.cancelReason = cancelReason;
		this.statusDescription = statusDescription;
		this.comentsEditedBy = comentsEditedBy;
		this.comentsCanceledBy = comentsCanceledBy;
		this.commentsDate = commentsDate;
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
	 * Gets the start time.
	 *
	 * @return the start time
	 */
	@JsonProperty("startTime")
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * Gets the end time.
	 *
	 * @return the end time
	 */
	@JsonProperty("endTime")
	public Date getEndTime() {
		return endTime;
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
	 * Gets the value.
	 *
	 * @return the value
	 */
	@JsonProperty("value")
	public BigDecimal getValue() {
		return value;
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
	 * Gets the location.
	 *
	 * @return the location
	 */
	@JsonProperty("location")
	public String getLocation() {
		return location;
	}

	/**
	 * Gets the location category.
	 *
	 * @return the location category
	 */
	@JsonProperty("locationCategory")
	public String getLocationCategory() {
		return locationCategory;
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
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	@JsonProperty("orderId")
	public Integer getOrderId() {
		return orderId;
	}

	/**
	 * Gets the link order id.
	 *
	 * @return the link order id
	 */
	@JsonProperty("linkOrderId")
	public Integer getLinkOrderId() {
		return linkOrderId;
	}

	/**
	 * Gets the order category name.
	 *
	 * @return the order category name
	 */
	@JsonProperty("orderCategoryName")
	public String getOrderCategoryName() {
		return orderCategoryName;
	}

	/**
	 * Gets the secondary order category name.
	 *
	 * @return the secondary order category name
	 */
	@JsonProperty("SecondaryOrderCategoryName")
	public String getSecondaryOrderCategoryName() {
		return secondaryOrderCategoryName;
	}

	/**
	 * Gets the order category DESCRIPTION.
	 *
	 * @return the order category DESCRIPTION
	 */
	@JsonProperty("orderCategoryDescription")
	public String getOrderCategoryDESCRIPTION() {
		return orderCategoryDescription;
	}

	/**
	 * Gets the checks if is open bag.
	 *
	 * @return the checks if is open bag
	 */
	@JsonProperty("isOpenBag")
	public Boolean getIsOpenBag() {
		return isOpenBag;
	}

	/**
	 * Gets the contninue in next department.
	 *
	 * @return the contninue in next department
	 */
	@JsonProperty("contninueInNextDepartment")
	public Boolean getContninueInNextDepartment() {
		return contninueInNextDepartment;
	}

	/**
	 * Gets the cancel reason.
	 *
	 * @return the cancel reason
	 */
	@JsonProperty("cancelReason")
	public Integer getCancelReason() {
		return cancelReason;
	}

	/**
	 * Gets the status description.
	 *
	 * @return the status description
	 */
	@JsonProperty("statusDescription")
	public String getStatusDescription() {
		return statusDescription;
	}

	/**
	 * Gets the coments edited by.
	 *
	 * @return the coments edited by
	 */
	@JsonProperty("comentsEditedBy")
	public String getComentsEditedBy() {
		return comentsEditedBy;
	}

	/**
	 * Gets the coments canceled by.
	 *
	 * @return the coments canceled by
	 */
	@JsonProperty("comentsCanceledBy")
	public String getComentsCanceledBy() {
		return comentsCanceledBy;
	}

	/**
	 * Gets the comments date.
	 *
	 * @return the comments date
	 */
	@JsonProperty("commentsDate")
	public Date getCommentsDate() {
		return commentsDate;
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
	 * Sets the start time.
	 *
	 * @param startTime the new start time
	 */
	@JsonSetter("startTime")
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * Sets the end time.
	 *
	 * @param endTime the new end time
	 */
	@JsonSetter("endTime")
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	@JsonSetter("value")
	public void setValue(BigDecimal value) {
		this.value = value;
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
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	@JsonSetter("location")
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Sets the location category.
	 *
	 * @param locationCategory the new location category
	 */
	@JsonSetter("locationCategory")
	public void setLocationCategory(String locationCategory) {
		this.locationCategory = locationCategory;
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
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	@JsonSetter("orderId")
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	/**
	 * Sets the link order id.
	 *
	 * @param linkOrderId the new link order id
	 */
	@JsonSetter("linkOrderId")
	public void setLinkOrderId(Integer linkOrderId) {
		this.linkOrderId = linkOrderId;
	}

	/**
	 * Sets the order category name.
	 *
	 * @param orderCategoryName the new order category name
	 */
	@JsonSetter("orderCategoryName")
	public void setOrderCategoryName(String orderCategoryName) {
		this.orderCategoryName = orderCategoryName;
	}

	/**
	 * Sets the secondary order category name.
	 *
	 * @param secondaryOrderCategoryName the new secondary order category name
	 */
	@JsonSetter("orderCategoryName")
	public void setSecondaryOrderCategoryName(String secondaryOrderCategoryName) {
		this.secondaryOrderCategoryName = secondaryOrderCategoryName;
	}

	/**
	 * Sets the order category DESCRIPTION.
	 *
	 * @param orderCategoryDescription the new order category DESCRIPTION
	 */
	@JsonSetter("orderCategoryDescription")
	public void setOrderCategoryDESCRIPTION(String orderCategoryDescription) {
		this.orderCategoryDescription = orderCategoryDescription;
	}

	/**
	 * Sets the checks if is open bag.
	 *
	 * @param isOpenBag the new checks if is open bag
	 */
	@JsonSetter("isOpenBag")
	public void setIsOpenBag(Boolean isOpenBag) {
		this.isOpenBag = isOpenBag;
	}

	/**
	 * Sets the contninue in next department.
	 *
	 * @param contninueInNextDepartment the new contninue in next department
	 */
	@JsonSetter("contninueInNextDepartment")
	public void setContninueInNextDepartment(Boolean contninueInNextDepartment) {
		this.contninueInNextDepartment = contninueInNextDepartment;
	}

	/**
	 * Sets the cancel reason.
	 *
	 * @param cancelReason the new cancel reason
	 */
	@JsonSetter("cancelReason")
	public void setCancelReason(Integer cancelReason) {
		this.cancelReason = cancelReason;
	}

	/**
	 * Sets the status description.
	 *
	 * @param statusDescription the new status description
	 */
	@JsonSetter("statusDescription")
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	/**
	 * Sets the coments edited by.
	 *
	 * @param comentsEditedBy the new coments edited by
	 */
	@JsonSetter("comentsEditedBy")
	public void setComentsEditedBy(String comentsEditedBy) {
		this.comentsEditedBy = comentsEditedBy;
	}

	/**
	 * Sets the coments canceled by.
	 *
	 * @param comentsCanceledBy the new coments canceled by
	 */
	@JsonSetter("comentsCanceledBy")
	public void setComentsCanceledBy(String comentsCanceledBy) {
		this.comentsCanceledBy = comentsCanceledBy;
	}

	/**
	 * Sets the comments date.
	 *
	 * @param commentsDate the new comments date
	 */
	@JsonSetter("commentsDate")
	public void setCommentsDate(Date commentsDate) {
		this.commentsDate = commentsDate;
	}

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	@Override
	public String getValueAsString() {
		return orderCategoryName;
	}

	/**
	 * Gets the value with detail as string.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	@Override
	public String getValueWithDetailAsString() {
		return orderCategoryName + " " + orderCategoryDescription + " " + value + " " + valueUom + " "
				+ statusDescription;
	}

}
