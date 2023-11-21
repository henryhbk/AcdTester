package com.ibm.nlp.fhir;

/**
 * The Class IdGroup for the FDB search XML conversion.
 *
 * @author henry.feldman@ibm.com
 */
public class IdGroup {

	/** The all sources. */
	private String allSources;

	/** The id type. */
	private String idType;

	/** The id. */
	private String id;

	/**
	 * Instantiates a new id group.
	 */
	public IdGroup() {
		super();
	}

	/**
	 * Instantiates a new id group.
	 *
	 * @param allSources the all sources
	 * @param idType     the id type
	 * @param id         the id
	 */
	public IdGroup(String allSources, String idType, String id) {
		super();
		this.allSources = allSources;
		this.idType = idType;
		this.id = id;
	}

	/**
	 * Gets the all sources.
	 *
	 * @return the all sources
	 */
	public String getAllSources() {
		return allSources;
	}

	/**
	 * Sets the all sources.
	 *
	 * @param allSources the new all sources
	 */
	public void setAllSources(String allSources) {
		this.allSources = allSources;
	}

	/**
	 * Gets the id type.
	 *
	 * @return the id type
	 */
	public String getIdType() {
		return idType;
	}

	/**
	 * Sets the id type.
	 *
	 * @param idType the new id type
	 */
	public void setIdType(String idType) {
		this.idType = idType;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdGroup [allSources=" + allSources + ", idType=" + idType + ", id=" + id + "]";
	}

}
