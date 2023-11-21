package com.ibm.nlp.hibernate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Base class for all entities in our system. Implements {@link HasAttributes}
 * for generic attributes support.
 * 
 * @author Henry Feldman
 * 
 */
@JsonRootName(value = "BaseEntity")
public class BaseEntity extends Object implements HasAttributes, Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The attributes. */
	private Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * Gets the attribute.
	 *
	 * @param name the name
	 * @return the attribute
	 */
	@Override
	@JsonProperty("attributes")
	public Object getAttribute(final String name) {
		return attributes.get(name);
	}

	/**
	 * Gets the attributes.
	 *
	 * @return the attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	/**
	 * Sets the attribute.
	 *
	 * @param name  the name
	 * @param value the value
	 */
	@Override
	public void setAttribute(final String name, final Object value) {
		attributes.put(name, value);
	}

	/**
	 * Sets the attributes.
	 *
	 * @param attributes the attributes
	 */
	@JsonSetter("attributes")
	public void setAttributes(final Map<String, Object> attributes) {
		this.attributes = attributes;
	}

}
