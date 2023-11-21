package getting_started;

import com.ibm.nlp.AcdConfugurator;
import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.hibernate.PrescriptionDao;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.services.NoteEventService;
import com.ibm.nlp.services.PatientService;
import com.ibm.nlp.services.PrescriptionService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;
import com.ibm.watson.health.acd.v1.model.AttributeValueAnnotation;
import com.ibm.watson.health.acd.v1.model.ContainerGroup;

/**
 * The Class FirstRunTutorial.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class FirstRunTutorial {

	/** The patient dao. */
	private static PatientService patientDao = new PatientDao();

	/** The prescription dao. */
	private static PrescriptionService prescriptionDao = new PrescriptionDao();

	private static NoteEventService noteEventDao = new NoteEventDao();

	/** The Constant SUBJECT_ID. */
	private static final int SUBJECT_ID = 110; // (demo Kevin Perkins patient id)

	private static AnnotatorForClinicalData service;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		// First thing to note is we have some static DAO classes above (the hibernate
		// DAOs can be static, dynamic and are fully thread-safe), but for this demo
		// class they need to be static since main is a static method. You will also
		// note that there is a service interface that the DAOs all obey, this is so you
		// can easily make a REST implementation of them by just creating a JAX_RS
		// service that implements that service and uses a delegate of the actual DAO.

		// set up ACD for grinding text (set your API key there)
		service = AcdConfugurator.getAcdServiceInstance();

		// create a Patients entity (yes, it's plural so as to not clash with the FHIR
		// Patient class I named it to match the table, sorry, blame the MIT guys for
		// plural table names)
		Patients patient = patientDao.getPatientBySubjectId(SUBJECT_ID);

		// the Patients entity has a pretty print toString() method (and a HTML version
		// of same)
		System.out.println(patient.toString());

		// Now pull the patient's medication order (annoyingly called Prescriptions in
		// MIMIC III)
		Integer counter = 0;
		System.out.println("PATIENT MEDICATION ORDERS:\n");

		// Our prescription entity has a handy getSig utility method that packages the
		// record as a human readble medication order. before you blame me, the original
		// database does not include frequency or duration.
		for (Prescription prescription : prescriptionDao.getAllPrescriptionsForPatient(patient.getSubjectId())) {
			System.out.println(counter++ + ". " + prescription.getSig());
		}

		// So we are going to put out the patient as a FHIR Resource. But wait, how did
		// that happen without talking with the FHIR server? Well it's not magic, I just
		// stored the output of the FHIR client library into the database so you won't
		// have to (it took days for some tables with many cores cranking away). The
		// conversion routiens are all in FhirUtil (make sure it's my FhirUtil not the
		// one in the FHIR client library!)
		System.out.println("Example FHIR resource cache: example Patient:\n");
		System.out.println(patient.getFhirJson());

		NoteEvent noteEvent = noteEventDao.getNoteEvent(1);

		// Now we are going to throw the first note in the database (which happens to be
		// a Discharge Summary) and spew all the found attributes in the note to the
		// screen. Note if your ACD server is the Dallas internal dev server it won't
		// have my cartridge. So you may need to change the flow choice (note it is
		// critical that your flow include ClinicalInsights)
		System.out.println("Analyzing note row_id = 1 with ACD");
		ContainerGroup response = null;
		try {
			response = service.analyzeWithFlow("henry_test_cartridge_v1.0_aap_test_flow", noteEvent.getText());
		} catch (Exception e) {
			e.printStackTrace();
		}

		for (AttributeValueAnnotation attribute : response.getAttributeValues()) {
			System.out.println("attribute: " + attribute.getPreferredName());
		}
	}

}