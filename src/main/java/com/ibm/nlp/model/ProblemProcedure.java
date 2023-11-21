 package com.ibm.nlp.model;

import java.io.Serializable;

/**
 * The Class ProblemProcedure.
 *
 * @author henry.feldman@ibm.com
 */
public class ProblemProcedure implements Serializable, ProblemTreatment {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The description. */
	private String description;

	/** The active. */
	private boolean active;

	/**
	 * The Constant START is used to indicate a procedure is planned to be started.
	 */
	public static final String START = "start";

	/** The Constant DC is used to indicate a procedure is planned to be Stopped. */
	public static final String DC = "Discontinue";

	/**
	 * The Constant MODIFY is used to indicate a procedure is planned to be changed
	 * in some way.
	 */
	public static final String MODIFY = "Modify";

	/**
	 * The Constant MODIFY is used to indicate a procedure is planned to be
	 * Discussed (e.g proposed).
	 */
	public static final String DISCUSS = "Discuss";

	/**
	 * The Constant CONSIDER. is used to indicate a procedure is being considered
	 * for use (likely a PRN)
	 */
	public static final String CONSIDER = "Consider";

	/** The Constant PERFORMED. */
	public static final String PERFORMED = "PERFORMED";

	/** The Constant UNKNOWN. */
	public static final String UNKNOWN = "Unknown";

	/** The cui. */
	private String cui; 

	/**
	 * Instantiates a new problem procedure.
	 */
	public ProblemProcedure() {
	}

	/**
	 * Instantiates a new problem procedure.
	 *
	 * @param id          the id
	 * @param name        the name
	 * @param description the description
	 * @param active      the active
	 * @param cui         the cui
	 */
	public ProblemProcedure(String id, String name, String description, boolean active, String cui) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.active = active;
		this.cui = cui;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	@Override
	public String getName() {
		return name;
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
	 * Gets the cui.
	 *
	 * @return the cui
	 */
	@Override
	public String getCui() {
		return cui;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	@Override
	public void setName(String name) {
		this.name = name;
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
	@Override
	public String toString() {
		return "ProblemProcedure [id=" + id + ", name=" + name + ", description=" + description + ", active=" + active
				+ ", cui=" + cui + "]";
	}

}
