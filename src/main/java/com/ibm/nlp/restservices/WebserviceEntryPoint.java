package com.ibm.nlp.restservices;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

@ApplicationPath("acdtester/*")
public class WebserviceEntryPoint extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> set = new HashSet<>();
		set.add(MedicationAdverseEventWebService.class);
		set.add(PatientWebService.class);
		return set;
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String index() {
		return ("<h2>MIMIC III system</h2>");
	}

}
