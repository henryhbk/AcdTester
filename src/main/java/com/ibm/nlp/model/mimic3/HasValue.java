package com.ibm.nlp.model.mimic3;

import javax.persistence.Transient;

/**
 * The Interface HasValue is for various entities that can report their values
 * as string (this is different to the toString() method, as this is just the
 * string representation of the value field such as for a lab).
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface HasValue {

	/**
	 * Gets the value as string.
	 *
	 * @return the value as string
	 */
	@Transient
	public String getValueAsString();

	/**
	 * Gets the value with some extra detail (it will show the value + something
	 * like a source or category.
	 *
	 * @return the value with detail as string
	 */
	@Transient
	public String getValueWithDetailAsString();
}
