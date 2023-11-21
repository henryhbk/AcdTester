package com.ibm.nlp.model;

import java.io.Serializable;
import java.util.Set;

/**
 * The Class Medicationinformation.
 *
 * @author henry.feldman@ibm.com
 */
public class MedicationInformation implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The rx norm id. */
	private String rxNormId;

	/** The name. */
	private String name;

	/** The indications. */
	private Set<String> indications;

	/** The adverse effects. */
	private Set<String> adverseEffects;

	/**
	 * Instantiates a new medicationinformation.
	 */
	public MedicationInformation() {
		super();
	}

	/**
	 * Instantiates a new medicationinformation.
	 *
	 * @param rxNormId       the rx norm id
	 * @param name           the name
	 * @param indications    the indications
	 * @param adverseEffects the adverse effects
	 */
	public MedicationInformation(String rxNormId, String name, Set<String> indications, Set<String> adverseEffects) {
		super();
		this.rxNormId = rxNormId;
		this.name = name;
		this.indications = indications;
		this.adverseEffects = adverseEffects;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the indications.
	 *
	 * @return the indications
	 */
	public Set<String> getIndications() {
		return indications;
	}

	/**
	 * Gets the adverse effects.
	 *
	 * @return the adverse effects
	 */
	public Set<String> getAdverseEffects() {
		return adverseEffects;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the indications.
	 *
	 * @param indications the new indications
	 */
	public void setIndications(Set<String> indications) {
		this.indications = indications;
	}

	/**
	 * Sets the adverse effects.
	 *
	 * @param adverseEffects the new adverse effects
	 */
	public void setAdverseEffects(Set<String> adverseEffects) {
		this.adverseEffects = adverseEffects;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Medicationinformation [rxNormId=" + rxNormId + ", name=" + name + ", indications=" + indications
				+ ", adverseEffects=" + adverseEffects + "]";
	}

}
