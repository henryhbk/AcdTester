package com.ibm.nlp.hibernate;

/**
 * The Interface HasAttributes.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface HasAttributes {

	/**
	 * Get attribute with the given name.
	 * 
	 * @param name name of the attribute
	 * @return the attribute value if it exists, null if it doesn't.
	 */
	Object getAttribute(String name);

	/**
	 * Set the attribute with the given name and value.
	 *
	 * @param name  name of attribute
	 * @param value value of attribute
	 */
	void setAttribute(String name, Object value);

}
