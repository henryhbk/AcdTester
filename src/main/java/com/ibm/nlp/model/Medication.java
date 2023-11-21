package com.ibm.nlp.model;

import java.io.Serializable;

/**
 * The Class Medication holds basic medication information from the "EMR" in
 * this demo (i.e. from the FHIR server)
 *
 * @author henry.feldman@ibm.com
 */
public class Medication implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The cui. */
	private String cui;

	/** The rx norm. */
	private String rxNorm;

	/** The dose. */
	private Integer dose;

	/** The dose units. */
	private String doseUnits;

	/** The route and frequency. */
	private String routeAndFrequency;

	/** The active. */
	private boolean active;

	/** The version. */
	private Integer version;

	/** The Constant FAILED_TO_STOP. */
	public static final int FAILED_TO_STOP = 0;

	/** The Constant FAILED_TO_START. */
	public static final int FAILED_TO_START = 1;

	/** The Constant FAILED_TO_MOD. */
	public static final int FAILED_TO_MOD = 2;

	/**
	 * Instantiates a new medication.
	 */
	public Medication() {
	}

	/**
	 * Instantiates a new medication.
	 *
	 * @param id                the id
	 * @param name              the name
	 * @param cui               the cui
	 * @param rxNorm            the rx norm
	 * @param dose              the dose
	 * @param doseUnits         the dose units
	 * @param active            the active
	 * @param version           the version
	 * @param routeAndFrequency the route and frequency
	 */
	public Medication(String id, String name, String cui, String rxNorm, Integer dose, String doseUnits, boolean active,
			Integer version, String routeAndFrequency) {
		super();
		this.id = id;
		this.name = name;
		this.cui = cui;
		this.rxNorm = rxNorm;
		this.dose = dose;
		this.doseUnits = doseUnits;
		this.active = active;
		this.version = version;
		this.routeAndFrequency = routeAndFrequency;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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
	 * Gets the rx norm.
	 *
	 * @return the rx norm
	 */
	public String getRxNorm() {
		return rxNorm;
	}

	/**
	 * Gets the dose.
	 *
	 * @return the dose
	 */
	public Integer getDose() {
		return dose;
	}

	/**
	 * Gets the dose units.
	 *
	 * @return the dose units
	 */
	public String getDoseUnits() {
		return doseUnits;
	}

	/**
	 * Checks if is active.
	 *
	 * @return true, if is active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * Gets the route and frequency as a string such as "PO DAILY".
	 *
	 * @return the route and frequency
	 */
	public String getRouteAndFrequency() {
		return routeAndFrequency;
	}

	/**
	 * Sets the route and frequency.
	 *
	 * @param routeAndFrequency the new route and frequency
	 */
	public void setRouteAndFrequency(String routeAndFrequency) {
		this.routeAndFrequency = routeAndFrequency;
	}

	/**
	 * Sets the version.
	 *
	 * @param version the new version
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
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
	 * Sets the cui.
	 *
	 * @param cui the new cui
	 */
	public void setCui(String cui) {
		this.cui = cui;
	}

	/**
	 * Sets the rx norm.
	 *
	 * @param rxNorm the new rx norm
	 */
	public void setRxNorm(String rxNorm) {
		this.rxNorm = rxNorm;
	}

	/**
	 * Sets the dose.
	 *
	 * @param dose the new dose
	 */
	public void setDose(Integer dose) {
		this.dose = dose;
	}

	/**
	 * Sets the dose units.
	 *
	 * @param doseUnits the new dose units
	 */
	public void setDoseUnits(String doseUnits) {
		this.doseUnits = doseUnits;
	}

	/**
	 * Sets the active.
	 *
	 * @param active the new active
	 */
	public void setActive(boolean active) {
		this.active = active;
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
		return "Medication [id=" + id + ", name=" + name + ", cui=" + cui + ", rxNorm=" + rxNorm + ", dose=" + dose
				+ ", doseUnits=" + doseUnits + ", active=" + active + ", version=" + version + "]";
	}

	/**
	 * To pretty string.
	 *
	 * @return the string
	 */
	public String toPrettyString() {
		return name + " " + dose.toString() + doseUnits + " " + routeAndFrequency;
	}

	/**
	 * Gets the pretty rx.
	 *
	 * @return the pretty rx
	 */
	public String getPrettyRx() {
		if (routeAndFrequency != null) {
			return dose.toString() + doseUnits + " " + routeAndFrequency;
		} else {
			return dose.toString() + doseUnits;
		}
	}
}
