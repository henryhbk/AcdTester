package com.ibm.nlp.model.mimic3;

/**
 * The Interface HasImpression is for text reports that have an impression
 * section.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface HasImpression {

	/**
	 * Gets the impression.
	 *
	 * @return the impression
	 */
	String getImpression();

	/**
	 * Sets the impression.
	 *
	 * @param impression the new impression
	 */
	void setImpression(String impression);

}
