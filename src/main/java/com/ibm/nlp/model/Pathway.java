package com.ibm.nlp.model;

import java.io.Serializable;

/**
 * The Class Pathway is the parent of a pathway treatment which .
 *
 * @author henry.feldman@ibm.com
 */
public class Pathway implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Integer id;

	/** The name. */
	private String name;

	/** The cui. */
	private String cui;

	/** The description. */
	private String description;

	/** The activating attribute code. */
	private String activatingAttributeCode;

	/**
	 * Instantiates a new pathway.
	 */
	public Pathway() {
	}

	/**
	 * Instantiates a new pathway.
	 *
	 * @param id                      the id
	 * @param name                    the name
	 * @param description             the description
	 * @param activatingAttributeCode the activating attribute code
	 * @param cui the cui
	 */
	public Pathway(Integer id, String name, String description, String activatingAttributeCode, String cui) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.activatingAttributeCode = activatingAttributeCode;
		this.cui = cui;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the activating attribute code.
	 *
	 * @return the activating attribute code
	 */
	public String getActivatingAttributeCode() {
		return activatingAttributeCode;
	}

	/**
	 * Gets the cui.
	 *
	 * @return the cui
	 */
	public String getCui() {
		return cui;
	}

	/**
	 * Sets the cui.
	 *
	 * @param cui the new cui
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * Sets the activating attribute code.
	 *
	 * @param activatingAttributeCode the new activating attribute code
	 */
	public void setActivatingAttributeCode(String activatingAttributeCode) {
		this.activatingAttributeCode = activatingAttributeCode;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
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
		return "Pathway [id=" + id + ", name=" + name + ", description=" + description + ", activatingAttributeCode="
				+ activatingAttributeCode + "]";
	}

}
