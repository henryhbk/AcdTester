package com.ibm.nlp.model;

/**
 * The Interface ProblemTreatment.
 *
 * @author henry.feldman@ibm.com
 */
public interface ProblemTreatment {

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId();

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription();

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive();

	/**
	 * Gets the cui.
	 *
	 * @return the cui
	 */
	public String getCui();

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id);

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name);

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description);

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active);

	/**
	 * Sets the cui.
	 *
	 * @param cui the new cui
	 */
	public void setCui(String cui);
}
