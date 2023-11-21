package com.ibm.fhir.model;

import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "idGroup" })
public class RxNormStatus {

	@JsonProperty("idGroup")
	private IdGroup idGroup;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public RxNormStatus() {
	}

	/**
	 * 
	 * @param idGroup
	 */
	public RxNormStatus(IdGroup idGroup) {
		super();
		this.idGroup = idGroup;
	}

	@JsonProperty("idGroup")
	public IdGroup getIdGroup() {
		return idGroup;
	}

	@JsonProperty("idGroup")
	public void setIdGroup(IdGroup idGroup) {
		this.idGroup = idGroup;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "RxNormStatus [idGroup=" + idGroup + ", additionalProperties=" + additionalProperties + "]";
	}

}