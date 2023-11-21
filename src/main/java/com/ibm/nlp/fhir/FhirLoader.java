package com.ibm.nlp.fhir;

import java.util.List;

import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.services.PatientService;

/**
 * The Class FhirLoader sends the data when retrieved from MIMIC III to the FHIR
 * server optionally.
 *
 * @author henry.feldman@ibm.com
 */
public class FhirLoader {

	private static PatientService patientDao = new PatientDao();

	/**
	 * Instantiates a new fhir loader.
	 */
	public FhirLoader() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Getting patients from MIMIC III");
		List<Patients> patientList = patientDao.getAllPatients();
		System.out.println("Fetched " + patientList.size() + " Patients");
		try {
			System.out.println("Saving to FHIR SERVER");
			FhirUtil.savePatientsToServer(patientList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
