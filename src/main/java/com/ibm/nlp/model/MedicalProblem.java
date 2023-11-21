package com.ibm.nlp.model;

/**
 * The Class MedicalProblem.
 *
 * @author henry.feldman@ibm.com
 */
public class MedicalProblem {
	/** The name. */
	private String name;

	/** The id. */
	private Long id;

	/** The icd code. */
	private String icdCode;

	/** The cui. */
	private String cui;

	/**
	 * Instantiates a new medical problem.
	 */
	public MedicalProblem() {
	}

	/**
	 * Instantiates a new medical problem.
	 *
	 * @param name    the name
	 * @param id      the id
	 * @param icdCode the icd code
	 * @param cui     the cui
	 */
	public MedicalProblem(String name, Long id, String icdCode, String cui) {
		super();
		this.name = name;
		this.id = id;
		this.icdCode = icdCode;
		this.cui = cui;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Gets the icd code.
	 *
	 * @return the icd code
	 */
	public String getIcdCode() {
		return icdCode;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Sets the icd code.
	 *
	 * @param icdCode the new icd code
	 */
	public void setIcdCode(String icdCode) {
		this.icdCode = icdCode;
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
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "MedicalProblem [name=" + name + ", id=" + id + ", icdCode=" + icdCode + ", cui=" + cui + "]";
	}

}
