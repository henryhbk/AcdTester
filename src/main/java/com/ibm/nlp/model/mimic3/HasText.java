package com.ibm.nlp.model.mimic3;

/**
 * The Interface HasText.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface HasText {

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	Integer getRowId();

	/**
	 * Gets the text.
	 *
	 * @return the text
	 */
	public String getText();

}
