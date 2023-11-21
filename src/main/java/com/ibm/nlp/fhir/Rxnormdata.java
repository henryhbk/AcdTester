package com.ibm.nlp.fhir;

/**
 * The Class Rxnormdata for the FDB XML data conversion.
 *
 * @author henry.feldman@ibm.com
 */
public class Rxnormdata {

	/** The id group. */
	private IdGroup idGroup;

	/**
	 * Instantiates a new rxnormdata.
	 */
	public Rxnormdata() {
		super();
	}

	/**
	 * Instantiates a new rxnormdata.
	 *
	 * @param idGroup the id group
	 */
	public Rxnormdata(IdGroup idGroup) {
		super();
		this.idGroup = idGroup;
	}

	/**
	 * Gets the id group.
	 *
	 * @return the id group
	 */
	public IdGroup getIdGroup() {
		return idGroup;
	}

	/**
	 * Sets the id group.
	 *
	 * @param idGroup the new id group
	 */
	public void setIdGroup(IdGroup idGroup) {
		this.idGroup = idGroup;
	}

	@Override
	public String toString() {
		return "Rxnormdata [idGroup=" + idGroup + "]";
	}

}
