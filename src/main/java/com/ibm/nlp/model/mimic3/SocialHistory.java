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

// TODO: Auto-generated Javadoc
/**
 * The Class SocialHistory.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "social_history")
@JsonRootName(value = "SocialHistory")
public class SocialHistory extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The subject id. */
	@Id
	@Column(name = "subject_id")
	private Integer subjectId;

	/** The text. */
	@Column(name = "text")
	private String text;

	/** The hadm id. */
	@Column(name = "hadm_id")
	private Integer hadmId;

	/** The description. */
	@Column(name = "description")
	private String description;

	/**
	 * Instantiates a new social history.
	 */
	public SocialHistory() {
		super();
	}

	/**
	 * Instantiates a new social history.
	 *
	 * @param subjectId   the subject id
	 * @param text        the text
	 * @param hadmId      the hadm id
	 * @param description the description
	 */
	public SocialHistory(Integer subjectId, String text, Integer hadmId, String description) {
		super();
		this.subjectId = subjectId;
		this.text = text;
		this.hadmId = hadmId;
		this.description = description;
	}

	/**
	 * Gets the subect id.
	 *
	 * @return the subect id
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
	 * Gets the hadm id.
	 *
	 * @return the hadm id
	 */
	@JsonProperty("hadmId")
	public Integer getHadmId() {
		return hadmId;
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
	 * Sets the subect id.
	 *
	 * @param subjectId the new subject id
	 */
	@JsonProperty("subjectId")
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
	 * Sets the hadm id.
	 *
	 * @param hadmId the new hadm id
	 */
	@JsonSetter("hadmId")
	public void setHadmId(Integer hadmId) {
		this.hadmId = hadmId;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "SocialHistory [subjectId=" + subjectId + ", text=" + text + ", hadmId=" + hadmId + ", description="
				+ description + "]";
	}

}
