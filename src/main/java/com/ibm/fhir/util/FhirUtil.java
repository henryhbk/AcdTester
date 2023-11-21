package com.ibm.fhir.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.text.StringEscapeUtils;

import com.ibm.fhir.client.FHIRClient;
import com.ibm.fhir.client.FHIRClientFactory;
import com.ibm.fhir.client.FHIRResponse;
import com.ibm.fhir.model.resource.DiagnosticReport;
import com.ibm.fhir.model.resource.DocumentReference;
import com.ibm.fhir.model.resource.DocumentReference.Content;
import com.ibm.fhir.model.resource.MedicationRequest;
import com.ibm.fhir.model.resource.Observation;
import com.ibm.fhir.model.resource.Patient;
import com.ibm.fhir.model.resource.Practitioner;
import com.ibm.fhir.model.type.Address;
import com.ibm.fhir.model.type.Attachment;
import com.ibm.fhir.model.type.Base64Binary;
import com.ibm.fhir.model.type.Code;
import com.ibm.fhir.model.type.CodeableConcept;
import com.ibm.fhir.model.type.Coding;
import com.ibm.fhir.model.type.Date;
import com.ibm.fhir.model.type.Decimal;
import com.ibm.fhir.model.type.Dosage.DoseAndRate;
import com.ibm.fhir.model.type.Extension;
import com.ibm.fhir.model.type.HumanName;
import com.ibm.fhir.model.type.Identifier;
import com.ibm.fhir.model.type.Narrative;
import com.ibm.fhir.model.type.Quantity;
import com.ibm.fhir.model.type.Reference;
import com.ibm.fhir.model.type.SimpleQuantity;
import com.ibm.fhir.model.type.String;
import com.ibm.fhir.model.type.Timing;
import com.ibm.fhir.model.type.Uri;
import com.ibm.fhir.model.type.code.AddressType;
import com.ibm.fhir.model.type.code.AddressUse;
import com.ibm.fhir.model.type.code.AdministrativeGender;
import com.ibm.fhir.model.type.code.DiagnosticReportStatus;
import com.ibm.fhir.model.type.code.DocumentReferenceStatus;
import com.ibm.fhir.model.type.code.IdentifierUse;
import com.ibm.fhir.model.type.code.MedicationRequestIntent;
import com.ibm.fhir.model.type.code.MedicationRequestStatus;
import com.ibm.fhir.model.type.code.NarrativeStatus;
import com.ibm.fhir.model.type.code.ObservationStatus;
import com.ibm.nlp.fhir.PlaintextNarrativeBuilder;
import com.ibm.nlp.model.DataDictionaryTerms;
import com.ibm.nlp.model.mimic3.Caregiver;
import com.ibm.nlp.model.mimic3.EKGReport;
import com.ibm.nlp.model.mimic3.EchoReport;
import com.ibm.nlp.model.mimic3.LabEvent;
import com.ibm.nlp.model.mimic3.LabItem;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.model.mimic3.RadiologyReport;

/**
 * The Class FhirUtil.
 *
 * @author henry.feldman@ibm.com
 */
public class FhirUtil {
	/** The client. */
	private static FHIRClient client = null;

	/** The Constant OK. */
	public final static int OK = 200;

	/** The Constant CREATED. */
	public final static int CREATED = 201;

	/**
	 * Instantiates a new fhir util.
	 */
	public FhirUtil() {

	}

	/**
	 * Setup fhir client.
	 *
	 * @return the FHIR client
	 * @throws Exception the exception
	 */
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

	/**
	 * Save patients to server.
	 *
	 * @param patientList the patient list
	 * @throws Exception the exception
	 */
	public static void savePatientsToServer(List<Patients> patientList) throws Exception {
		if (patientList == null || patientList.isEmpty()) {
			throw new IllegalArgumentException("Patient list cannot be empty or null");
		}
		if (client == null) {
			setupFhirClient();
		}
		Instant startTime = Instant.now();
		for (Patients patient : patientList) {
			AdministrativeGender gender = null;
			if (patient.getGender() == "M") {
				gender = AdministrativeGender.MALE;
			} else {
				gender = AdministrativeGender.FEMALE;
			}
			java.lang.String isoDateDOB = toIsoDate(patient.getDob(), false);
			Patient fhirPatient = Patient.builder()
					.name(HumanName.builder().given(string(patient.getFirstName()))
							.family(string(patient.getLastName())).build())
					.gender(gender).birthDate(Date.of(isoDateDOB))
					.address(Address.builder().use(AddressUse.HOME).type(AddressType.POSTAL)
							.line(String.of(patient.getStreet())).state(string(patient.getState()))
							.city(string(patient.getCity())).postalCode(string(patient.getPostal()))
							.country(string("USA")).build())
					.id(patient.getSubjectId().toString()).build();
			FHIRResponse response = client.create(fhirPatient);
			if (response.getStatus() == CREATED || response.getStatus() == OK) {
				System.out.println("Patient resource was persisted, location = " + response.getLocation());
			} else {
				System.err.println("Error persisting patient, status code = " + response.getStatus());
			}
		}
		Instant endTime = Instant.now();
		Duration duration = Duration.between(startTime, endTime);
		System.out.println(patientList.size() + " patients loaded to fhir server in " + duration);

	}

	/**
	 * Save patients to server.
	 *
	 * @param patientList the patient list
	 * @throws Exception the exception
	 */
	public static java.lang.String patientToFhir(Patients patient) throws Exception {
		if (patient == null) {
			throw new IllegalArgumentException("Patient cannot be empty or null");
		}
		if (client == null) {
//			setupFhirClient();
		}

		AdministrativeGender gender = null;
		if (patient.getGender() == "M") {
			gender = AdministrativeGender.MALE;
		} else {
			gender = AdministrativeGender.FEMALE;
		}
		java.lang.String isoDateDOB = toIsoDate(patient.getDob(), false);
		Patient fhirPatient = Patient.builder()
				.name(HumanName
						.builder().given(string(patient.getFirstName())).family(string(patient.getLastName())).build())
				.gender(gender).birthDate(Date.of(isoDateDOB))
				.address(Address.builder().use(AddressUse.HOME).type(AddressType.POSTAL)
						.line(string(patient.getStreet())).state(string(patient.getState()))
						.city(string(patient.getCity())).postalCode(string(patient.getPostal())).country(string("USA"))
						.build())
				.id(patient.getSubjectId().toString()).build();
		return fhirPatient.toString();
	}

	/**
	 * To iso date.
	 *
	 * @param date the date
	 * @return the java.lang. string
	 */
	public static java.lang.String toIsoDate(java.util.Date date, boolean addTime) {
		DateTimeFormatter formatter;
		Calendar cal = DateUtils.toCalendar(date);
		Instant instant = cal.toInstant();
		LocalDateTime ldt = instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
		if (addTime) {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

		} else {
			formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		}
		return formatter.format(ldt);
	}

	/**
	 * String.
	 *
	 * @param value the value
	 * @return the string
	 */
	public static String string(java.lang.String value) {
		return String.builder().value(value).build();
	}

	/**
	 * Check fhir server status.
	 *
	 * @return true, if successful
	 */
	@SuppressWarnings("unused")
	public static boolean checkFhirServerStatus() {
		if (client == null) {
			try {
				setupFhirClient();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			String op = String.of("$healthcheck");
// OperationOutcome outcome = client.invoke(op);
			return true;
		}
		return true;

	}

	/**
	 * Gets the patient as fhir.
	 *
	 * @param patient the patient
	 * @return the patient as fhir
	 */
	public static Patient getPatientAsFhir(Patients patient) {
		List<String> addresses = new ArrayList<String>();
		Code language = null;
		java.lang.String isoDateDOB = toIsoDate(patient.getDob(), false);
		AdministrativeGender gender = null;
		if (patient.getGender() == "M") {
			gender = AdministrativeGender.MALE;
		} else {
			gender = AdministrativeGender.FEMALE;
		}

		switch (patient.getLanguage()) {
		case "English":
			language = Code.of("en-US");
			break;
		case "Spanish":
			language = Code.of("es");
			break;
		case "Chinese":
			language = Code.of("zh");
			break;
		case "French":
			language = Code.of("fr");
			break;
		case "Creole":
			language = Code.of("fr");
			break;
		case "Italian":
			language = Code.of("it");
			break;
		case "Russian":
			language = Code.of("ru");
			break;
		case "Arabic":
			language = Code.of("ar");
			break;
		default:
			language = Code.of("en-US");
			break;

		}

		Code race = null;
		;
		switch (patient.getRace()) {
		case "White (Non-Hispanic)":
			race = Code.of("2106-3");
			break;
		case "Hispanic":
			race = Code.of("2135-2");
			break;
		case "Black":
			race = Code.of("2054-5");
			break;
		case "Hawaiaan":
			race = Code.of("2079-2");
			break;
		case "Pacific Islander":
			race = Code.of("2078-4");
			break;
		case "Native American":
			race = Code.of("1004-1");
			break;
		}
		// race adds as an Extension in FHIR R4
		Extension extension = Extension.builder()
				.extension(Extension.builder().url("ombCategory")
						.value(Coding.builder().system(Uri.of("urn:oid:2.16.840.1.113883.6.238")).code(race)
								.display(string(patient.getRace())).build())
						.build())
				.extension(Extension.builder().url("detailed")
						.value(Coding.builder().system(Uri.of("urn:oid:2.16.840.1.113883.6.238")).code(race)
								.display(string(patient.getRace())).build())
						.build())
				.extension(Extension.builder().url("text").value(string(patient.getRace())).build())
				.url("http://hl7.org/fhir/us/core/StructureDefinition/us-core-race").build();

		Patient fhirPatient = Patient.builder()
				.name(HumanName
						.builder().given(string(patient.getFirstName())).family(string(patient.getLastName())).build())
				.gender(gender).birthDate(Date.of(isoDateDOB))
				.address(Address.builder().use(AddressUse.HOME).type(AddressType.POSTAL).line(addresses)
						.state(string(patient.getState())).city(string(patient.getCity()))
						.postalCode(string(patient.getPostal())).country(string("USA")).build())
				.language(language).id(patient.getSubjectId().toString()).extension(extension).build();

		return fhirPatient;
	}

	/**
	 * Gets the prescription as fhir.
	 *
	 * @param prescription the prescription
	 * @return the prescription as fhir
	 */
	public static Prescription getPrescriptionAsFhir(Prescription prescription) {
		com.ibm.fhir.model.type.Dosage[] doseList1 = new com.ibm.fhir.model.type.Dosage[1];
		SimpleQuantity quantity = null;
		try {
			if (prescription.getDoseValRx() == null || prescription.getFormUnitDisp() == null) {

				if (prescription.getDoseValRx() != null && prescription.getDoseValRx().contains(".")) {
					quantity = SimpleQuantity.builder().value(Decimal.of(Float.parseFloat(prescription.getDoseValRx())))
							.unit(com.ibm.fhir.model.type.String.of(prescription.getDoseUnitRx())).build();
				} else {
					if (prescription.getDoseValRx() == null) {
						quantity = SimpleQuantity.builder().value(Decimal.of(0))
								.unit(com.ibm.fhir.model.type.String.of(prescription.getDoseUnitRx())).build();
					} else {
						quantity = SimpleQuantity.builder()
								.value(Decimal.of(Integer.parseInt(prescription.getDoseValRx())))
								.unit(com.ibm.fhir.model.type.String.of(prescription.getDoseUnitRx())).build();
					}
				}
			} else {
				if (prescription.getFormValDisp() != null && prescription.getFormValDisp().contains(".")) {
					quantity = SimpleQuantity.builder()
							.value(Decimal.of(Float.parseFloat(prescription.getFormValDisp())))
							.unit(com.ibm.fhir.model.type.String.of(prescription.getFormUnitDisp())).build();
				} else {
					if (prescription.getFormValDisp() == null) {
						quantity = SimpleQuantity.builder().value(Decimal.of(0))
								.unit(com.ibm.fhir.model.type.String.of(prescription.getFormUnitDisp())).build();
					} else {
						quantity = SimpleQuantity.builder()
								.value(Decimal.of(Integer.parseInt(prescription.getFormValDisp())))
								.unit(com.ibm.fhir.model.type.String.of(prescription.getFormUnitDisp())).build();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("QUANTITY VALUE = " + prescription);
			e.printStackTrace();
		}
		// C1739768
		DoseAndRate doseAndRate = DoseAndRate.builder().dose(quantity).build();

		Timing timing = Timing.builder().code(CodeableConcept.builder()
				.coding(Coding.builder().code(Code.builder().value("DAILY").build()).build()).build()).build();

		CodeableConcept routeConcept = CodeableConcept.builder()
				.coding(Coding.builder().code(Code.builder().value(prescription.getRoute()).build()).build()).build();

		doseList1[0] = com.ibm.fhir.model.type.Dosage.builder().text(string(prescription.getRoute()))
				.route(routeConcept).timing(timing).doseAndRate(doseAndRate).build();
		MedicationRequest med1 = null;
		java.lang.String drugName = null;
		if (prescription.getDrugNameGeneric() == null) {
			if (prescription.getDrug() != null) {
				drugName = prescription.getDrug();
			}
		} else {
			drugName = prescription.getDrugNameGeneric();
		}
		try {
			med1 = MedicationRequest.builder().id(prescription.getRowId() + "")
					.subject(Reference.builder().reference(string(prescription.getSubjectId() + "")).build())
					.status(MedicationRequestStatus.ACTIVE).dosageInstruction(doseList1)
					.medication(CodeableConcept.builder()
							.coding(Coding.builder().system(Uri.of(DataDictionaryTerms.RXNORMURI))
									.code(Code.of(prescription.getRxNormCui())).build())
							.text(string(drugName)).build())
					.intent(MedicationRequestIntent.ORDER).build();
			prescription.setFhirJson(med1.toString());
		} catch (Exception e) {
			System.out.println("VALUE =" + prescription);
			e.printStackTrace();
			prescription.setFhirJson("");
		}
		return prescription;
	}

	/**
	 * Sets the lab as fhir in the fhirJson field for storage into the DB.
	 *
	 * @param labItem       the lab item
	 * @param labDefinition the lab definition
	 * @return the lab item
	 */
	public static java.lang.String getLabAsFhir(LabEvent labEvent, LabItem labDefinition) {
		Observation labObservation = null;
		java.lang.String interpretation;
		if (labEvent.getFlag() != null) {
			interpretation = labEvent.getFlag();
		} else {
			interpretation = "normal";
		}

		String uom;
		if (labEvent.getValueuom() == null || labEvent.getValueuom().trim().length() == 0) {
			uom = string("N/A");
		} else {
			uom = string(labEvent.getValueuom());
		}

		String loinc;
		if (labDefinition.getLoincCode() == null || labDefinition.getLoincCode().trim().length() < 2) {
			loinc = string("N/A");
		} else {
			loinc = string(labDefinition.getLoincCode());
		}

		try {
			if (labEvent.getValuenum() != null && Pattern.matches("[a-zA-Z]+", labEvent.getValue()) == false
					&& !labEvent.getValue().isEmpty() && labEvent.getValueuom() != null) {
				labObservation = Observation.builder()
						.value(Quantity.builder().value(Decimal.of(labEvent.getValue())).unit(uom).build())
						.identifier(Identifier.builder().use(IdentifierUse.USUAL).build())
						.status(ObservationStatus.FINAL)
						.category(CodeableConcept.builder()
								.coding(Coding.builder()
										.system(Uri.of("http://hl7.org/fhir/ValueSet/observation-category"))
										.code(Code.of("laboratory")).build())
								.build())
						.subject(Reference.builder().reference(string(labEvent.getSubjectId() + "")).build())
						.encounter(Reference.builder().reference(string(labEvent.getHadmId() + "")).build())
						.code(CodeableConcept.builder().id(loinc.toString()).text(string(labDefinition.getLabel()))
								.build())
						.interpretation(CodeableConcept.builder().text(String.of(interpretation)).build()).build();
				labEvent.setFhirJson(labObservation.toString());

			} else {
				labObservation = Observation.builder()
						.value(CodeableConcept.builder().text(String.of(labEvent.getValue())).build())
						.identifier(Identifier.builder().use(IdentifierUse.USUAL).build())
						.status(ObservationStatus.FINAL)
						.category(CodeableConcept.builder()
								.coding(Coding.builder()
										.system(Uri.of("http://hl7.org/fhir/ValueSet/observation-category"))
										.code(Code.of("laboratory")).build())
								.build())
						.subject(Reference.builder().reference(string(labEvent.getSubjectId() + "")).build())
						.encounter(Reference.builder().reference(string(labEvent.getHadmId() + "")).build())
						.code(CodeableConcept.builder().id(loinc.toString()).text(string(labDefinition.getLabel()))
								.build())
						.interpretation(CodeableConcept.builder().text(String.of(interpretation)).build()).build();
				labEvent.setFhirJson(labObservation.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(labEvent);
		}

		if (labObservation != null) {

			return labObservation.toString();
		} else {
			return null;
		}
	}

	public static java.lang.String getCareGiverAsFhir(Caregiver careGiver) {

		try {
			AdministrativeGender gender = null;
			Practitioner practitioner = null;
			if (careGiver.getGender() == "M") {
				gender = AdministrativeGender.MALE;
			} else {
				gender = AdministrativeGender.FEMALE;
			}
			if (careGiver.getNpiNumber() != null) {

				practitioner = Practitioner.builder()
						.name(HumanName.builder().given(string(careGiver.getFirstName()))
								.family(string(careGiver.getLastName())).build())
						.gender(gender).identifier(Identifier.builder().system(Uri.of("http://hl7.org/fhir/sid/us-npi"))
								.value(string(careGiver.getNpiNumber())).build())
						.build();
			} else {
				practitioner = Practitioner.builder()
						.name(HumanName.builder().given(string(careGiver.getFirstName().trim()))
								.family(string(careGiver.getLastName().trim())).build())
						.gender(gender).build();
			}
			careGiver.setFhirJson(practitioner.toString());
			return careGiver.getFhirJson();
		} catch (Exception e) {
			System.out.println(careGiver);
			e.printStackTrace();
			return null;
		}
	}

	public static java.lang.String getNoteAsFhir(NoteEvent noteEvent) {

		DocumentReference note = null;
		java.lang.String noteContents = null;
		try {
			Narrative narrative = new PlaintextNarrativeBuilder()
					.text(StringEscapeUtils.escapeHtml4(noteEvent.getText())).status(NarrativeStatus.GENERATED).build();
			Attachment noteAttachment = Attachment.builder().contentType(Code.of("text/plain"))
					.data(Base64Binary.builder().value(noteEvent.getText().getBytes("UTF-8")).build()).build();
			note = DocumentReference.builder().status(DocumentReferenceStatus.CURRENT)
					.id(noteEvent.getRowId().toString())
					.subject(Reference.builder()
							.reference(com.ibm.fhir.model.type.String.of(noteEvent.getSubjectId().toString())).build())
					.content(Content.builder().attachment(noteAttachment).build()).text(narrative)
					.type(CodeableConcept.builder()
							.coding(Coding.builder().code(Code.code(java.lang.String.valueOf("47039-3"))).build())
							.build())
					.build();
			noteEvent.setFhirJson(note.toString());
			return note.toString();
		} catch (Exception e) {
			System.out.println(noteContents);
			e.printStackTrace();
		}
		return null;
	}

	public static synchronized DocumentReference getNoteAsFhirResource(NoteEvent noteEvent) {

		DocumentReference note = null;
		java.lang.String noteContents = null;
		try {
			Narrative narrative = new PlaintextNarrativeBuilder()
					.text(StringEscapeUtils.escapeHtml4(noteEvent.getText())).status(NarrativeStatus.GENERATED).build();
			Attachment noteAttachment = Attachment.builder().contentType(Code.of("text/plain"))
					.data(Base64Binary.builder().value(noteEvent.getText().getBytes("UTF-8")).build()).build();
			note = DocumentReference.builder().status(DocumentReferenceStatus.CURRENT)
					.id(noteEvent.getRowId().toString())
					.subject(Reference.builder()
							.reference(com.ibm.fhir.model.type.String.of(noteEvent.getSubjectId().toString())).build())
					.content(Content.builder().attachment(noteAttachment).build()).text(narrative)
					.type(CodeableConcept.builder()
							.coding(Coding.builder().code(Code.code(java.lang.String.valueOf("47039-3"))).build())
							.build())
					.build();
			noteEvent.setFhirJson(note.toString());
			return note;
		} catch (Exception e) {
			System.out.println(noteContents);
			e.printStackTrace();
		}
		return null;
	}

	public static synchronized java.lang.String getRadiologyReportAsFhir(RadiologyReport report) {
		DiagnosticReport diagnosticReport;

		com.ibm.fhir.model.type.Instant chartTime = com.ibm.fhir.model.type.Instant.builder()
				.value(toIsoDate(report.getChartdate(), true)).build();
		Narrative narrative = new PlaintextNarrativeBuilder().text(report.getText()).status(NarrativeStatus.GENERATED)
				.build();
		diagnosticReport = DiagnosticReport.builder().status(DiagnosticReportStatus.FINAL)
				.id(report.getRowId().toString())
				.code(CodeableConcept.builder()
						.coding(Coding.builder().display(string(report.getDescription()))
								.system(Uri.of("http://loinc.org")).code(Code.of("68604-8")).build())
						.build())
				.conclusion(string(report.getImpression()))
				.category(CodeableConcept.builder().text(string("Radiology")).build()).id(report.getRowId().toString())
				.issued(chartTime).text(narrative).build();
		report.setFhirJson(diagnosticReport.toString());
		return diagnosticReport.toString();
	}

	public static synchronized java.lang.String getEchoReportAsFhir(EchoReport report) {
		DiagnosticReport diagnosticReport;

		if (report.getImpression() != null && !report.getImpression().isEmpty()
				&& !report.getImpression().trim().isEmpty()) {
			System.out.println("IMPRESSION:[" + report.getImpression() + "]");
			com.ibm.fhir.model.type.Instant chartTime = com.ibm.fhir.model.type.Instant.builder()
					.value(toIsoDate(report.getChartdate(), true)).build();
			Narrative narrative = new PlaintextNarrativeBuilder().text(report.getText())
					.status(NarrativeStatus.GENERATED).build();
			diagnosticReport = DiagnosticReport.builder().status(DiagnosticReportStatus.FINAL)
					.code(CodeableConcept.builder()
							.coding(Coding.builder().display(string(report.getDescription()))
									.system(Uri.of("http://loinc.org")).code(Code.of("34552-0")).build())
							.build())
					.conclusion(string(report.getImpression())).id(report.getRowId().toString())
					.category(CodeableConcept.builder().text(string("Cardiac Ultrasound")).build())
					.id(report.getRowId().toString()).issued(chartTime).text(narrative).build();
			report.setFhirJson(diagnosticReport.toString());
		} else {
			com.ibm.fhir.model.type.Instant chartTime = com.ibm.fhir.model.type.Instant.builder()
					.value(toIsoDate(report.getChartdate(), true)).build();
			Narrative narrative = new PlaintextNarrativeBuilder().text(report.getText())
					.status(NarrativeStatus.GENERATED).build();
			diagnosticReport = DiagnosticReport.builder().status(DiagnosticReportStatus.FINAL)
					.code(CodeableConcept.builder()
							.coding(Coding.builder().display(string(report.getDescription()))
									.system(Uri.of("http://loinc.org")).code(Code.of("34552-0")).build())
							.build())
					.category(CodeableConcept.builder().text(string("Cardiac Ultrasound")).build())
					.id(report.getRowId().toString()).issued(chartTime).text(narrative).build();
			report.setFhirJson(diagnosticReport.toString());
		}
		return diagnosticReport.toString();
	}

	public static synchronized java.lang.String getEKGReportAsFhir(EKGReport report) {
		DiagnosticReport diagnosticReport;

		com.ibm.fhir.model.type.Instant chartTime = com.ibm.fhir.model.type.Instant.builder()
				.value(toIsoDate(report.getChartdate(), true)).build();

		Narrative narrative = new PlaintextNarrativeBuilder().text(report.getText()).status(NarrativeStatus.GENERATED)
				.build();
		diagnosticReport = DiagnosticReport.builder().status(DiagnosticReportStatus.FINAL)
				.id(report.getRowId().toString())
				.code(CodeableConcept.builder()
						.coding(Coding.builder().display(string(report.getDescription()))
								.system(Uri.of("http://hl7.org/fhir/")).code(Code.of("28010-7")).build())
						.build())
				.category(CodeableConcept.builder().text(string("Electrocardiac")).build())
				.id(report.getRowId().toString()).issued(chartTime).text(narrative).build();
		report.setFhirJson(diagnosticReport.toString());
		return diagnosticReport.toString();
	}

}
