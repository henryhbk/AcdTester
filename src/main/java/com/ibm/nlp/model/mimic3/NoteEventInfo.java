package com.ibm.nlp.model.mimic3;

import java.io.Serializable;
import java.text.DecimalFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

// TODO: Auto-generated Javadoc
/**
 * The persistent class for the NOTEEVENTS database table but in somewhat
 * lighter weight form to save space.
 *
 * @author henry.feldman@ibm.com
 */
@Entity
@Table(name = "note_events")
@JsonRootName(value = "NoteeventInfo")
public class NoteEventInfo extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@Column(name = "ROW_ID")
	private int rowId;

	/** The subject id. */
	@Column(name = "SUBJECT_ID")
	private String subjectId;

	/** The text. */
	@Column(name = "TEXT")
	private String text;

	/** The df. */
	@Transient
	private final DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * Instantiates a new noteevent.
	 */
	public NoteEventInfo() {
	}

	// {"ROW_ID":"401597","SUBJECT_ID":"23657","HADM_ID":"125544","CHARTDATE":"2145-02-24","CHARTTIME":"2145-02-24
	// 16:41:00","STORETIME":"2145-02-24 16:42:09","CATEGORY":"Physician
	// ","DESCRIPTION":"Admission
	// Note","CGID":"18991","ISERROR":null,"TEXT":"TITLE:\n"},

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	@JsonProperty("ROW_ID")
	public int getRowId() {
		return rowId;
	}

	/**
	 * Instantiates a new note event info.
	 *
	 * @param rowId                  the row id
	 * @param subjectId              the subject id
	 * @param text                   the text
	 */
	public NoteEventInfo(int rowId, String subjectId, String text) {
		super();
		this.rowId = rowId;
		this.subjectId = subjectId;
		this.text = text;
	}

	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	@JsonProperty("subjectId")
	public String getSubjectId() {
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
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	@JsonSetter("rowId")
	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	@JsonSetter("subjectId")
	public void setSubjectId(String subjectId) {
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
		return "NoteEventInfo [rowId=" + rowId + ", subjectId=" + subjectId + ", text=" + text + ", df=" + df + "]";
	}
}