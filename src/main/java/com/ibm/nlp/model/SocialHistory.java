package com.ibm.nlp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class SocialHistory.
 *
 * @author henry.feldman@ibm.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "hadmId" })
public class SocialHistory {

	/** The patient. */
	@JsonProperty("subjectId")
	private String subjectId;

	/** The text. */
	@JsonProperty("text")
	private String text;

	/** The hadm id. */
	@JsonProperty("hadmId")
	private Integer hadmId;

	/** The note description. */
	@JsonProperty("noteDescription")
	private String noteDescription;

	/**
	 * Instantiates a new social history.
	 */
	public SocialHistory() {
		super();
	}

	/**
	 * Instantiates a new social history.
	 *
	 * @param subjectId the subject id
	 * @param text            the text
	 * @param hadmId          the hadm id
	 * @param noteDescription the note description
	 */
	public SocialHistory(String subjectId, String text, Integer hadmId, String noteDescription) {
		super();
		this.subjectId = subjectId;
		this.text = text;
		this.hadmId = hadmId;
		this.noteDescription = noteDescription;
	}

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Gets the hadm id.
	 *
	 * @return the hadm id
	 */
	public Integer getHadmId() {
		return hadmId;
	}

	/**
	 * Gets the note description.
	 *
	 * @return the note description
	 */
	public String getNoteDescription() {
		return noteDescription;
	}

	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public String getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Sets the text.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Sets the hadm id.
	 *
	 * @param hadmId the new hadm id
	 */
	public void setHadmId(Integer hadmId) {
		this.hadmId = hadmId;
	}

	/**
	 * Sets the note description.
	 *
	 * @param noteDescription the new note description
	 */
	public void setNoteDescription(String noteDescription) {
		this.noteDescription = noteDescription;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SocialHistory [subjectId=" + subjectId + ", text=" + text + ", hadmId=" + hadmId + ", noteDescription="
				+ noteDescription + "]";
	}

}
