package com.ibm.nlp;

import static com.ibm.fhir.model.type.String.string;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.ibm.fhir.client.FHIRClient;
import com.ibm.fhir.client.FHIRClientFactory;
import com.ibm.fhir.client.FHIRParameters;
import com.ibm.fhir.client.FHIRResponse;
import com.ibm.fhir.model.resource.Bundle;
import com.ibm.fhir.model.resource.DocumentReference;
import com.ibm.fhir.model.resource.Flag;
import com.ibm.fhir.model.resource.MedicationRequest;
import com.ibm.fhir.model.type.Code;
import com.ibm.fhir.model.type.CodeableConcept;
import com.ibm.fhir.model.type.Coding;
import com.ibm.fhir.model.type.Dosage;
import com.ibm.fhir.model.type.Dosage.DoseAndRate;
import com.ibm.fhir.model.type.Reference;
import com.ibm.fhir.model.type.SimpleQuantity;
import com.ibm.fhir.model.type.code.DocumentReferenceStatus;
import com.ibm.fhir.model.type.code.FlagStatus;
import com.ibm.fhir.model.type.code.MedicationRequestStatus;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.model.AAPProblem;
import com.ibm.nlp.model.DataDictionaryTerms;
import com.ibm.nlp.model.Medication;
import com.ibm.nlp.model.NoteText;
import com.ibm.nlp.model.Patient;
import com.ibm.nlp.model.mimic3.Patients;

/**
 * The Class AAPUtil is a utility class for doing a lot of the data processing
 * for the Assessment and Plan of the note.
 *
 * @author henry.feldman@ibm.com
 */
public class AAPUtil {

	private static final Properties applicationProperties = new Properties();

	private static FHIRClient client = null;

	public static java.lang.String PATIENTID = "c8dc6e6a-9a20-445d-9e9b-d8e7a0158205";

	public static FHIRClient setupFhirClient() throws Exception {
		if (client == null) {
			Properties clientProperties = new Properties();

			FileInputStream propStream = null;
			try {
				propStream = new FileInputStream(new File("client.properties"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			// load the FHIR properties
			clientProperties.load(propStream);

			// Retrieve an instance of the FHIRClient interface.
			client = FHIRClientFactory.getClient(clientProperties);

			return client;
		} else {
			return client;
		}
	}

	static {
		try {
			applicationProperties.load(ClassLoader.getSystemResourceAsStream("application.properties"));
		} catch (IOException ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}

	/** The parameters. */
	private static FHIRParameters parameters = new FHIRParameters().count(5);

	/** The patient med map. */
	private static Map<String, Medication> patientMedMap = new HashMap<String, Medication>();

	/**
	 * Gets the list of problems in AAP by parsing the assessment and plan and
	 * parsing those into AAPProblems.
	 *
	 * @param noteText the note text
	 * @return the list of problems in AAP
	 */
	public static List<AAPProblem> getListOfProblemsInAAP(String noteText) {
		List<AAPProblem> problemList = new ArrayList<AAPProblem>();
		// break NoteText into lines in an array (in Java11 this could use the .lines()
		// method)
		String[] lines = noteText.split("\n");
		Integer counter = 0;
		boolean empty = false;
		StringBuilder sb = new StringBuilder();
		String firstLine = null;
		// if a line is empty we assume that is a break between problems
		for (String line : lines) {
			if (line.length() <= 1) {
				empty = true;
				// now if sb has text in it then create a aapproblem and add it to the array
				AAPProblem problem = new AAPProblem();
				problem.setPlan(sb.toString());
				problem.setProblemTitle(firstLine);
				problem.setSevere(false); // we don't know yet, but we will!
				problemList.add(problem);
			} else {
				// so we now are in the first text after a blank line, let's assume this is a
				// problem paragraph
				if (empty) {
					empty = false;
					sb = new StringBuilder();
					// preserve the first line so we don't have to reparse to extract the first line
					// for the AAPProblem
					firstLine = removeListItem(line);
					// we need to remove bullet marks from the first line so we can use it for
					// searching text, so we will use a regex to find leading numbers with a
					// terminator:

				} else {
					sb.append(line + "\n");
				}
			}
			counter++;
		}
		return problemList;
	}

	/**
	 * Removes the list item numerator. In other words detect if the string begins
	 * with 1. and return the text after the "1."
	 *
	 * @param stringToTest the string to test
	 * @return the string
	 */
	public static String removeListItem(String stringToTest) {

		boolean foundDigits = false;
		for (int x = 0; x < stringToTest.length(); x++) {
			Character charToTest = stringToTest.charAt(x);
			if (Character.isDigit(charToTest)) {
				foundDigits = true;
			} else if (foundDigits) {
				// now we are post number so let's check if we are at a seperator (.:,)
				if (charToTest == '.' || charToTest == ':' || charToTest == ',') {
					return stringToTest.substring(x + 1).trim();
				}
			}
		}
		return stringToTest;

	}

	/**
	 * Gets the patient from EMR.
	 *
	 * @param patientId the patient id
	 * @return the patient from EMR
	 * @throws Exception the exception
	 */
	public static Patients getPatientFromEMR(String patientId) throws Exception {
		// Retrieve an instance of the FHIRClient interface.
		if (client == null) {
			setupFhirClient();
		}
		FHIRResponse response = client.read("Patient", patientId);
		if (response.getStatus() == FhirUtil.OK) {
			com.ibm.fhir.model.resource.Patient fhirPatient = response
					.getResource(com.ibm.fhir.model.resource.Patient.class);

			// Create the patient class
			DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
			Date dob = df1.parse(fhirPatient.getBirthDate().getValue().toString());

			String gender = fhirPatient.getGender() == null ? null : fhirPatient.getGender().getValue();
			Patients patient = new Patients(Integer.parseInt(fhirPatient.getId()), fhirPatient.getId(),
					fhirPatient.getName().get(0).getGiven().get(0).getValue(),
					fhirPatient.getName().get(0).getFamily().getValue(), dob, gender);
			// we are going to assume the address has 1 line of street since in this demo it
			// does
			patient.setStreet(fhirPatient.getAddress().get(0).getLine().get(0).getValue().toString());
			patient.setCity(fhirPatient.getAddress().get(0).getCity().getValue().toString());
			patient.setState(fhirPatient.getAddress().get(0).getState().getValue().toString());
			patient.setPostal(fhirPatient.getAddress().get(0).getPostalCode().getValue().toString());
			return patient;
		} else {
			System.out.println("Error reading Patient From FHIR Server " + response.getStatus());

			System.out.println("FHIR SERVER: Response " + response.getResponse().getEntity());
			System.out.println("Going to Backup MySql MIMIC III Demo Mode");
			return null;
		}

	}

	/**
	 * Gets the patient meds from EMR.
	 *
	 * @param patientId the patient id
	 * @return the patient meds from EMR
	 * @throws Exception the exception
	 */
	public static List<Medication> getPatientMedsFromEMR(String patientId) throws Exception {
		List<Medication> patientMedications = new ArrayList<Medication>();
		// Retrieve an instance of the FHIRClient interface.
		if (client == null) {
			setupFhirClient();
		}

		parameters = new FHIRParameters().searchParam("patient", PATIENTID).searchParam(FHIRParameters.COUNT, "50");
		FHIRResponse response = client.search("MedicationRequest", parameters);
		Bundle results = response.getResource(Bundle.class);
		for (Bundle.Entry entry : results.getEntry()) {
			Medication medication = new Medication();
			MedicationRequest order = entry.getResource().as(MedicationRequest.class);
			// System.out.println(entry);
			// System.out.println(order);
			// get the active status of this med order
			if (order.getStatus().getValue().equals(MedicationRequestStatus.ACTIVE.getValue())) {
				medication.setActive(true);
			} else {
				medication.setActive(false);
			}
			CodeableConcept code = order.getMedication().as(CodeableConcept.class);
			// we had some early med entries without the name, so let's ignore them
			if (code.getText() != null) {
				medication.setName(code.getText().getValue());
				for (Dosage dosage : order.getDosageInstruction()) {
					if (dosage.getText() != null) {
						medication.setRouteAndFrequency(dosage.getText().getValue());
					} else {
						medication.setRouteAndFrequency("");
					}
					for (DoseAndRate doseAndRate : dosage.getDoseAndRate()) {
						medication.setDoseUnits(doseAndRate.getDose().as(SimpleQuantity.class).getUnit().getValue());
						medication.setDose(
								doseAndRate.getDose().as(SimpleQuantity.class).getValue().getValue().intValue());
					}
				}
				for (Coding coding : code.getCoding()) {
					if (coding.getSystem().getValue().equals(DataDictionaryTerms.RXNORMURI)) {
						medication.setRxNorm(coding.getCode().getValue());
					} else if (coding.getSystem().getValue().equals(DataDictionaryTerms.SNOMEDURI)) {
						medication.setCui(coding.getCode().getValue());
					}
					medication.setId(order.getId());
					medication.setVersion(Integer.parseInt(order.getMeta().getVersionId().getValue()));
					if (medication.getName() != null) {
						patientMedications.add(medication);

					} else {
						System.out.println("************************** REJECTED: ");
					}
				}

			}
		}

		// now do the depulication
		// Now make the map and deduplicate based on versions
		for (Medication medication : patientMedications) {
			if (patientMedMap.containsKey(medication.getCui())) {
				// check if the one in the map is older if so replace it
				if (patientMedMap.get(medication.getCui()).getVersion() < medication.getVersion()) {
					patientMedMap.put(medication.getCui(), medication);
				}
			} else {
				// we dont have this med in the map so add it
				patientMedMap.put(medication.getCui(), medication);
			}
		}
		patientMedications.clear();
		patientMedications.addAll(patientMedMap.values());
		return patientMedications;

	}

	/**
	 * Gets the patient notes from EMR.
	 *
	 * @param patientId the patient id
	 * @return the patient notes from EMR
	 * @throws Exception the exception
	 */
	public static List<NoteText> getPatientNotesFromEMR(String patientId) throws Exception {
		List<NoteText> noteSourceList = new ArrayList<NoteText>();
		Map<String, NoteText> noteIdMap = new HashMap<String, NoteText>();

		// Retrieve an instance of the FHIRClient interface.
		if (client == null) {
			setupFhirClient();
		}

		parameters = new FHIRParameters().searchParam("patient", PATIENTID).searchParam(FHIRParameters.COUNT, "50");
		FHIRResponse response = client.search("DocumentReference", parameters);
		Bundle results = response.getResource(Bundle.class);
		for (Bundle.Entry entry : results.getEntry()) {
			String title = "HMED Attending Admit Note";
			DocumentReference doc = entry.getResource().as(DocumentReference.class);
			doc = doc.toBuilder()
					.type(CodeableConcept.builder().text(string(title))
							.coding(Coding.builder().code(Code.code(String.valueOf("47039-3"))).build()).build())
					.status(DocumentReferenceStatus.CURRENT).build();
			response = client.update(doc);

			String noteContent = doc.getText().getDiv().getValue()
					.replace("<div xmlns=\"http://www.w3.org/1999/xhtml\">", "");

			NoteText noteText = new NoteText(noteContent.replace("</div>", ""), null, noteContent.replace("</div>", ""),
					0L, null, Integer.parseInt(doc.getMeta().getVersionId().getValue()), title, null, new Date());
			noteSourceList.add(noteText);
		}

		// now we need to dedup the list of notes as there can be many versions
		for (NoteText noteText : noteSourceList) {
			// check if the note is in the map, if so then do a version compare
			if (noteIdMap.containsKey(noteText.getId())) {
				if (noteIdMap.get(noteText.getId()).getVersion() < noteText.getVersion()) {
					noteIdMap.put(noteText.getId(), noteText);
				}
			} else {
				noteIdMap.put(noteText.getId(), noteText);
			}
		}
		// now make the returned de-duped list (yeah it's wasteful but simpler this way)
		List<NoteText> noteCleanList = new ArrayList<NoteText>();
		noteCleanList.addAll(noteIdMap.values());
		return noteCleanList;

	}

	/**
	 * Gets the SHA256 hash as a HEX string for a given text block.
	 *
	 * @param text the text
	 * @return the hash
	 */
	public static String getHash(String text) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
		// convert the byte to hex format method 1
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < hash.length; i++) {
			sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
		}

		return sb.toString();
	}

	/**
	 * Send alert as a flag via FHIR to the EHR.
	 *
	 * @param patient the patient
	 * @param alert   the alert
	 * @throws Exception the exception
	 */
	public static String sendAlert(final Patient patient, String alert) throws Exception {
		// Retrieve an instance of the FHIRClient interface.
		if (client == null) {
			setupFhirClient();
		}

		System.out.println("ALERT TEXT: " + alert + " for patient " + patient.getId());

		Flag alertFlag = null;
		try {
			alertFlag = Flag.builder().status(FlagStatus.ACTIVE)
					.code(CodeableConcept.builder().text(string(alert)).build())
					.subject(Reference.builder().reference(string("Patient/" + patient.getId())).build()).build();
		} catch (Exception e) {
			System.err.println("FAILED TO CREATE A FLAG DUE TO AN ERROR: ");
			e.printStackTrace();

		}

		FHIRResponse response = client.create(alertFlag);
		if (response.getStatus() == FhirUtil.CREATED || response.getStatus() == FhirUtil.OK) {
			System.out.println("Patient clinical alert was persisted, location = " + response.getLocation());
		} else {
			System.err.println("Error persisting clinical alert, status code = " + response.getStatus());
		}
		return (alertFlag.toString());

	}

	public static FHIRClient getClient() {
		return client;
	}

}
