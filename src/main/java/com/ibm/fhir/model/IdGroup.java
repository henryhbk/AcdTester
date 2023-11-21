package com.ibm.fhir.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonPropertyOrder({ "idType", "id", "rxnormId" })
public class IdGroup {

	@JsonProperty("idType")
	private String idType;
	@JsonProperty("id")
	private String id;
	@JsonProperty("rxnormId")
	private List<String> rxnormId = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public IdGroup() {
	}

	/**
	 * 
	 * @param idType
	 * @param rxnormId
	 * @param id
	 */
	public IdGroup(String idType, String id, List<String> rxnormId) {
		super();
		this.idType = idType;
		this.id = id;
		this.rxnormId = rxnormId;
	}

	@JsonProperty("idType")
	public String getIdType() {
		return idType;
	}

	@JsonProperty("idType")
	public void setIdType(String idType) {
		this.idType = idType;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("rxnormId")
	public List<String> getRxnormId() {
		return rxnormId;
	}

	@JsonProperty("rxnormId")
	public void setRxnormId(List<String> rxnormId) {
		this.rxnormId = rxnormId;
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
		return "IdGroup [idType=" + idType + ", id=" + id + ", rxnormId=" + rxnormId + ", additionalProperties="
				+ additionalProperties + "]";
	}

}
