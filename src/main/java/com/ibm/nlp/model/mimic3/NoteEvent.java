package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the NOTEEVENTS database table.
 *
 * @author henry.feldman@ibm.com
 */
@Entity
@Table(name = "NOTEEVENTS")
@JsonRootName(value = "NoteEvent")
public class NoteEvent extends BaseEntity implements Serializable, HasText, HasFhir {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private Integer rowId;

	/** The category. */
	@Column(name = "category")
	private String category;

	/** The cgid. */
	@JoinColumn(name = "CGID")
	private Integer cgid;

	/** The chartdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "CHARTDATE")
	private Date chartdate;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The hadm id. */
	@Column(name = "HADM_ID")
	private Integer hadmId;

	/** The acd study note. */
	@Column(name = "acd_study_note")
	private Boolean acdStudyNote;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private Integer subjectId;

	/** The text. */
	@Column(name = "TEXT")
	private String text;

	/** The fhir json. */
	@Column(name = "fhir_json")
	private String fhirJson;

	/** The df. */
	@Transient
	private DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Instantiates a new noteevent.
	 */
	public NoteEvent() {
	}

	// {"ROW_ID":"401597","SUBJECT_ID":"23657","HADM_ID":"125544","CHARTDATE":"2145-02-24","CHARTTIME":"2145-02-24
	// 16:41:00","STORETIME":"2145-02-24 16:42:09","CATEGORY":"Physician
	// ","DESCRIPTION":"Admission
	// Note","CGID":"18991","ISERROR":null,"TEXT":"TITLE:\n"},

	/**
	 * Instantiates a new note event.
	 *
	 * @param rowId        the row id
	 * @param category     the category
	 * @param cgid         the cgid
	 * @param chartdate    the chartdate
	 * @param description  the description
	 * @param hadmId       the hadm id
	 * @param acdStudyNote the acd study note
	 * @param subjectId    the subject id
	 * @param text         the text
	 * @param df           the df
	 */
	public NoteEvent(Integer rowId, String category, Integer cgid, Date chartdate, String description, Integer hadmId,
			Boolean acdStudyNote, Integer subjectId, String text, DecimalFormat df) {
		super();
		this.rowId = rowId;
		this.category = category;
		this.cgid = cgid;
		this.chartdate = chartdate;
		this.description = description;
		this.hadmId = hadmId;
		this.acdStudyNote = acdStudyNote;
		this.subjectId = subjectId;
		this.text = text;
		this.df = df;
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
	public String getCategory() {
		return category;
	}

	/**
	 * Gets the caregiver.
	 *
	 * @return the caregiver
	 */
	@JsonProperty("caregiver")
	public Integer getCgid() {
		return cgid;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	@JsonProperty("description")
	public String getDescription() {
		return description;
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
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	@JsonProperty("subjectId")
	public Integer getSubjectId() {
		return subjectId;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	@JsonProperty("text")
	public String getText() {
		return text;
	}

	/**
	 * Gets the acd study note.
	 *
	 * @return the acd study note
	 */
	@JsonProperty("acdStudyNote")
	public Boolean getAcdStudyNote() {
		return acdStudyNote;
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
	 * Sets the fhir json.
	 *
	 * @param fhirJson the new fhir json
	 */
	public void setFhirJson(String fhirJson) {
		this.fhirJson = fhirJson;
	}

	/**
	 * Sets the acd study note.
	 *
	 * @param acdStudyNote the new acd study note
	 */
	@JsonSetter("acdStudyNote")
	public void setAcdStudyNote(Boolean acdStudyNote) {
		this.acdStudyNote = acdStudyNote;
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
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Sets the caregiver.
	 *
	 * @param caregiver the new caregiver
	 */
	@JsonSetter("caregiver")
	public void setCgid(Integer cgid) {
		this.cgid = cgid;
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
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	@JsonSetter("description")
	public void setDescription(String description) {
		this.description = description;
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
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	@JsonSetter("subjectId")
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	@JsonSetter("text")
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "NoteEvent [rowId=" + rowId + ", category=" + category + ", cgid=" + cgid + ", chartdate=" + chartdate
				+ ", description=" + description + ", hadmId=" + hadmId + ", acdStudyNote=" + acdStudyNote
				+ ", subjectId=" + subjectId + ", text=" + text + ", fhirJson=" + fhirJson + ", df=" + df + "]";
	}

	/**
	 * To brat annotation.
	 *
	 * @return the string
	 */
//	public String ToBratAnnotation() {
//		return "T1\tMedicationAdverseEvent " + adeStartIndex + " " + adeStopIndex + "\t"
//				+ text.substring(adeStartIndex, adeStopIndex);
//
//	}

}