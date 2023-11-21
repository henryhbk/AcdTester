package com.ibm.nlp.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * The Class IdGroup.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "rxnormId" })
public class IdGroup {

	/** The name. */
	@JsonProperty("name")
	private String name;
	
	/** The rxnorm id. */
	@JsonProperty("rxnormId")
	private List<String> rxnormId = null;
	
	/** The additional properties. */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	@JsonProperty("name")
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the rxnorm id.
	 *
	 * @return the rxnorm id
	 */
	@JsonProperty("rxnormId")
	public List<String> getRxnormId() {
		return rxnormId;
	}

	/**
	 * Sets the rxnorm id.
	 *
	 * @param rxnormId the new rxnorm id
	 */
	@JsonProperty("rxnormId")
	public void setRxnormId(List<String> rxnormId) {
		this.rxnormId = rxnormId;
	}

	/**
	 * Gets the additional properties.
	 *
	 * @return the additional properties
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * Sets the additional property.
	 *
	 * @param name the name
	 * @param value the value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}