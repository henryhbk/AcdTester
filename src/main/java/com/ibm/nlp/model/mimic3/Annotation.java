package com.ibm.nlp.model.mimic3;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class Annotation.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "annotations")
@JsonRootName(value = "Annotation")
public class Annotation extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The span valid. */
	private Boolean spanValid;

	/** The ade valid. */
	private Boolean adeValid;

	/** The reaction valid. */
	private Boolean reactionValid;

	/** The drug valid. */
	private Boolean drugValid;

	/** The comment. */
	private String comment;

	/** The note row id. */
	private Integer noteRowId;

	/** The user id. */
	private Integer userId;

	/**
	 * Instantiates a new annotation.
	 */
	public Annotation() {
		super();
	}

	/**
	 * Instantiates a new annotation.
	 *
	 * @param id the id
	 * @param spanValid the span valid
	 * @param adeValid the ade valid
	 * @param reactionValid the reaction valid
	 * @param drugValid the drug valid
	 * @param comment the comment
	 * @param noteRowId the note row id
	 * @param userId the user id
	 */
	public Annotation(Integer id, Boolean spanValid, Boolean adeValid, Boolean reactionValid, Boolean drugValid,
			String comment, Integer noteRowId, Integer userId) {
		super();
		this.id = id;
		this.spanValid = spanValid;
		this.adeValid = adeValid;
		this.reactionValid = reactionValid;
		this.drugValid = drugValid;
		this.comment = comment;
		this.noteRowId = noteRowId;
		this.userId = userId;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Id
	@Column(name = "ROW_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the span valid.
	 *
	 * @return the span valid
	 */
	@Column(name = "span_valid")
	public Boolean getSpanValid() {
		return spanValid;
	}

	/**
	 * Gets the ade valid.
	 *
	 * @return the ade valid
	 */
	@Column(name = "ade_valid")
	public Boolean getAdeValid() {
		return adeValid;
	}

	/**
	 * Gets the reaction valid.
	 *
	 * @return the reaction valid
	 */
	@Column(name = "reaction_valid")
	public Boolean getReactionValid() {
		return reactionValid;
	}

	/**
	 * Gets the drug valid.
	 *
	 * @return the drug valid
	 */
	@Column(name = "is_valid_drug")
	public Boolean getDrugValid() {
		return drugValid;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	@Column(name = "coment")
	public String getComment() {
		return comment;
	}

	/**
	 * Gets the note row id.
	 *
	 * @return the note row id
	 */
	@Column(name = "noteevent_row_id")
	public Integer getNoteRowId() {
		return noteRowId;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	@Column(name = "user_id")
	public Integer getUserId() {
		return userId;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the span valid.
	 *
	 * @param spanValid the new span valid
	 */
	public void setSpanValid(Boolean spanValid) {
		this.spanValid = spanValid;
	}

	/**
	 * Sets the ade valid.
	 *
	 * @param adeValid the new ade valid
	 */
	public void setAdeValid(Boolean adeValid) {
		this.adeValid = adeValid;
	}

	/**
	 * Sets the reaction valid.
	 *
	 * @param reactionValid the new reaction valid
	 */
	public void setReactionValid(Boolean reactionValid) {
		this.reactionValid = reactionValid;
	}

	/**
	 * Sets the drug valid.
	 *
	 * @param drugValid the new drug valid
	 */
	public void setDrugValid(Boolean drugValid) {
		this.drugValid = drugValid;
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment the new comment
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * Sets the note row id.
	 *
	 * @param noteRowId the new note row id
	 */
	public void setNoteRowId(Integer noteRowId) {
		this.noteRowId = noteRowId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
