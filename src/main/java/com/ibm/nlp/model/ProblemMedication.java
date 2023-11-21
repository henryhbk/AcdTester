package com.ibm.nlp.model;

import java.io.Serializable;
import java.text.DecimalFormat;

/**
 * The Class ProblemMedication is used to store medication information in the
 * setting of the problemListItem so we know what medicaiton it is in context.
 *
 * @author henry.feldman@ibm.com
 */
public class ProblemMedication implements Serializable, ProblemTreatment {

	/** Default Serial ID. */
	private static final long serialVersionUID = 1L;

	/** The Constant START is used to indicate a med is planned to be started. */
	public static final String START = "start";

	/** The Constant DC is used to indicate a med is planned to be Stopped. */
	public static final String DC = "Discontinue";

	/**
	 * The Constant MODIFY is used to indicate a med is planned to be changed in
	 * some way.
	 */
	public static final String MODIFY = "Modify";

	/**
	 * The Constant MODIFY is used to indicate a med is planned to be Discussed (e.g
	 * proposed).
	 */
	public static final String DISCUSS = "Discuss";

	/**
	 * The Constant CONSIDER. is used to indicate a med is being considered for use
	 * (likely a PRN)
	 */
	public static final String CONSIDER = "Consider";

	/** The Constant TAKEN. */
	public static final String TAKEN = "Taken";

	/** The Constant UNKNOWN. */
	public static final String UNKNOWN = "Unknown";

	/** The id. */
	private String id;

	/** The active. */
	private Boolean active;

	/** The description. */
	private String description;

	/** The df. */
	private DecimalFormat df = new DecimalFormat("0.00");

	/**
	 * The name.
	 */

	private String name;

	/** The rx norm id. */
	private String rxNormId;

	/** The negated. */
	private Boolean negated;

	/** The cui. */
	private String cui;

	/** The action. */
	private MedicationActionPotential action;

	/**
	 * Instantiates a new problem medication.
	 */
	public ProblemMedication() {
	}

	/**
	 * Instantiates a new problem medication.
	 *
	 * @param name     the name
	 * @param rxNormId the rx norm id
	 * @param negated  the negated
	 * @param cui      the cui
	 * @param action   the action
	 */
	public ProblemMedication(String name, String rxNormId, Boolean negated, String cui,
			MedicationActionPotential action) {
		super();
		this.name = name;
		this.rxNormId = rxNormId;
		this.negated = negated;
		this.cui = cui;
		this.action = action;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gets the rx norm id.
	 *
	 * @return the rx norm id
	 */
	public String getRxNormId() {
		return rxNormId;
	}

	/**
	 * Gets the negated.
	 *
	 * @return the negated
	 */
	public Boolean getNegated() {
		return negated;
	}

	/**
	 * Gets the cui.
	 *
	 * @return the cui
	 */
	@Override
	public String getCui() {
		return cui;
	}

	/**
	 * Gets the action.
	 *
	 * @return the action
	 */
	public MedicationActionPotential getAction() {
		return action;
	}

	/**
	 * Sets the action.
	 *
	 * @param action the new action
	 */
	public void setAction(MedicationActionPotential action) {
		this.action = action;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the rx norm id.
	 *
	 * @param rxNormId the new rx norm id
	 */
	public void setRxNormId(String rxNormId) {
		this.rxNormId = rxNormId;
	}

	/**
	 * Sets the negated.
	 *
	 * @param negated the new negated
	 */
	public void setNegated(Boolean negated) {
		this.negated = negated;
	}

	/**
	 * Sets the cui.
	 *
	 * @param cui the new cui
	 */
	@Override
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProblemMedication [name=" + name + ", rxNormId=" + rxNormId + ", negated=" + negated + ", cui=" + cui
				+ "]";
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	@Override
	public void setId(String id) {
		this.id = id;

	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * To pretty string.
	 *
	 * @return the string
	 */
	public String toPrettyString() {
		return name + " action: " + action.getName() + " " + df.format(action.getProbability() * 100F) + "%";
	}
}
