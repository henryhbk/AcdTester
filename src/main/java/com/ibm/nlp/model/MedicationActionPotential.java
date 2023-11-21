package com.ibm.nlp.model;

/**
 * The Class MedicationActionPotential holds the probability of a given action
 * having occured for a problemmedication instance. Problems Names are constants
 * from ProblemMeEdication;
 *
 * @author henry.feldman@ibm.com
 */
public class MedicationActionPotential implements Comparable<MedicationActionPotential> {

	/** The name. */
	private String name;

	/** The probability. */
	private Float probability;

	/**
	 * Instantiates a new medication action potential.
	 */
	public MedicationActionPotential() {
	}

	/**
	 * Instantiates a new medication action potential.
	 *
	 * @param name
	 *            the name
	 * @param probability
	 *            the probability of the action as a float from ACD
	 */
	public MedicationActionPotential(String name, Float probability) {
		super();
		this.name = name;
		this.probability = probability;
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
	 * Gets the probability.
	 *
	 * @return the probability
	 */
	public Float getProbability() {
		return probability;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the probability.
	 *
	 * @param probability
	 *            the new probability
	 */
	public void setProbability(Float probability) {
		this.probability = probability;
	}

	/**
	 * Compare to.
	 *
	 * @param o the o
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(MedicationActionPotential o) {
		return probability.compareTo(o.getProbability());
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
		return "MedicationActionPotential [name=" + name + ", probability=" + probability + "]";
	}

}
