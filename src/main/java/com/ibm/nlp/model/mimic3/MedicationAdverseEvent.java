package com.ibm.nlp.model.mimic3;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.ibm.nlp.hibernate.BaseEntity;

/**
 * The Class MedicationAdverseEvent is the table to externalize the ADEs from
 * the NoteEvents table. it pulls in the note in its entirety
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@Entity
@Table(name = "medication_adverse_event")
@JsonRootName(value = "MedicationAdverseEvent")
public class MedicationAdverseEvent extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The row id. */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private Integer id;

	/** The note row id. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "note_row_id", referencedColumnName = "row_id", nullable = false)
	private NoteEvent noteEvent;

	/** The hadm id. */
	@Column(name = "hadm_id")
	private Integer hadmId;

	/** The ade drug name. */
	@Column(name = "ADE_drug_name")
	private String adeDrugName;

	/** The rx norm id. */
	@Column(name = "rx_norm_id")
	private String rxNormId;

	/** The ade reaction. */
	@Column(name = "ADE_reaction")
	private String adeReaction;

	/** The ade start index. */
	@Column(name = "start_index")
	private Integer adeStartIndex;

	/** The ade stop index. */
	@Column(name = "end_index")
	private Integer adeStopIndex;

	/** The ade span. */
	@Column(name = "ade_span")
	private String adeSpan;

	/** The ade score. */
	@Column(name = "ade_score")
	private Float adeScore;

	/** The valid. */
	@Column(name = "ade_valid")
	private Boolean valid;

	/**
	 * whether an ambiguous phrase appears present (i.e. is " on " to the left of
	 * the start?
	 */
	@Column(name = "ambiguous_phrase")
	private Boolean ambiguousPhrase;

	/**
	 * Instantiates a new medication adverse event.
	 */
	public MedicationAdverseEvent() {
		super();
	}

	/**
	 * Instantiates a new medication adverse event.
	 *
	 * @param id              the id
	 * @param noteRowId       the note row id
	 * @param hadmId          the hadm id
	 * @param adeDrugName     the ade drug name
	 * @param adeReaction     the ade reaction
	 * @param adeStartIndex   the ade start index
	 * @param adeStopIndex    the ade stop index
	 * @param adeSpan         the ade span
	 * @param adeScore        the ade score
	 * @param rxNormId        the rx norm id
	 * @param ambiguousPhrase the ambiguous phrase
	 */
	public MedicationAdverseEvent(Integer id, NoteEvent noteEvent, Integer hadmId, String adeDrugName,
			String adeReaction, Integer adeStartIndex, Integer adeStopIndex, String adeSpan, Float adeScore,
			String rxNormId, Boolean ambiguousPhrase) {
		super();
		this.id = id;
		this.noteEvent = noteEvent;
		this.hadmId = hadmId;
		this.adeDrugName = adeDrugName;
		this.adeReaction = adeReaction;
		this.adeStartIndex = adeStartIndex;
		this.adeStopIndex = adeStopIndex;
		this.adeSpan = adeSpan;
		this.adeScore = adeScore;
		this.rxNormId = rxNormId;
		this.ambiguousPhrase = ambiguousPhrase;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@JsonProperty("id")
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the note row id.
	 *
	 * @return the note row id
	 */
	@JsonProperty("noteRowId")
	public NoteEvent getNoteEvent() {
		return noteEvent;
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
	 * Gets the ade drug name.
	 *
	 * @return the ade drug name
	 */
	@JsonProperty("adeDrugName")
	public String getAdeDrugName() {
		return adeDrugName;
	}

	/**
	 * Gets the ade reaction.
	 *
	 * @return the ade reaction
	 */
	@JsonProperty("adeReaction")
	public String getAdeReaction() {
		return adeReaction;
	}

	/**
	 * Gets the ade start index.
	 *
	 * @return the ade start index
	 */
	@JsonProperty("adeStartIndex")
	public Integer getAdeStartIndex() {
		return adeStartIndex;
	}

	/**
	 * Gets the ade stop index.
	 *
	 * @return the ade stop index
	 */
	@JsonProperty("adeStopIndex")
	public Integer getAdeStopIndex() {
		return adeStopIndex;
	}

	/**
	 * Gets the ade span.
	 *
	 * @return the ade span
	 */
	@JsonProperty("adeSpan")
	public String getAdeSpan() {
		return adeSpan;
	}

	/**
	 * Gets the ade score.
	 *
	 * @return the ade score
	 */
	@JsonProperty("adeScore")
	public Float getAdeScore() {
		return adeScore;
	}

	/**
	 * Gets the valid.
	 *
	 * @return the valid
	 */
	@JsonProperty("valid")
	public Boolean getValid() {
		return valid;
	}

	/**
	 * Gets the rx norm id.
	 *
	 * @return the rx norm id
	 */
	@JsonProperty("rxNormId")
	public String getRxNormId() {
		return rxNormId;
	}

	/**
	 * Gets the ambiguous phrase.
	 *
	 * @return the ambiguous phrase
	 */
	@JsonProperty("ambiguousPhrase")
	public Boolean getAmbiguousPhrase() {
		return ambiguousPhrase;
	}

	/**
	 * Sets the ambiguous phrase.
	 *
	 * @param ambiguousPhrase the new ambiguous phrase
	 */
	@JsonSetter("ambiguousPhrase")
	public void setAmbiguousPhrase(Boolean ambiguousPhrase) {
		this.ambiguousPhrase = ambiguousPhrase;
	}

	/**
	 * Sets the rx norm id.
	 *
	 * @param rxNormId the new rx norm id
	 */
	@JsonSetter("rxNormId")
	public void setRxNormId(String rxNormId) {
		this.rxNormId = rxNormId;
	}

	/**
	 * Sets the valid.
	 *
	 * @param valid the new valid
	 */
	@JsonSetter("valid")
	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@JsonSetter("id")
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Sets the note row id.
	 *
	 * @param noteRowId the new note row id
	 */
	@JsonSetter("noteRowId")
	public void setNoteEvent(NoteEvent noteEvent) {
		this.noteEvent = noteEvent;
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
	 * Sets the ade drug name.
	 *
	 * @param adeDrugName the new ade drug name
	 */
	@JsonSetter("adeDrugName")
	public void setAdeDrugName(String adeDrugName) {
		this.adeDrugName = adeDrugName;
	}

	/**
	 * Sets the ade reaction.
	 *
	 * @param adeReaction the new ade reaction
	 */
	@JsonSetter("adeReaction")
	public void setAdeReaction(String adeReaction) {
		this.adeReaction = adeReaction;
	}

	/**
	 * Sets the ade start index.
	 *
	 * @param adeStartIndex the new ade start index
	 */
	@JsonSetter("adeStartIndex")
	public void setAdeStartIndex(Integer adeStartIndex) {
		this.adeStartIndex = adeStartIndex;
	}

	/**
	 * Sets the ade stop index.
	 *
	 * @param adeStopIndex the new ade stop index
	 */
	@JsonSetter("adeStopIndex")
	public void setAdeStopIndex(Integer adeStopIndex) {
		this.adeStopIndex = adeStopIndex;
	}

	/**
	 * Sets the ade span.
	 *
	 * @param adeSpan the new ade span
	 */
	@JsonSetter("adeSpan")
	public void setAdeSpan(String adeSpan) {
		this.adeSpan = adeSpan;
	}

	/**
	 * Sets the ade score.
	 *
	 * @param adeScore the new ade score
	 */
	@JsonSetter("adeScore")
	public void setAdeScore(Float adeScore) {
		this.adeScore = adeScore;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "MedicationAdverseEvent [id=" + id + ", noteEvent=" + noteEvent + ", hadmId=" + hadmId + ", adeDrugName="
				+ adeDrugName + ", adeReaction=" + adeReaction + ", adeStartIndex=" + adeStartIndex + ", adeStopIndex="
				+ adeStopIndex + ", adeSpan=" + adeSpan + ", adeScore=" + adeScore + "]";
	}

}
