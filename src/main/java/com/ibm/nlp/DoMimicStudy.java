package com.ibm.nlp;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.ibm.fhir.threading.AcdAnalyzerThread;
import com.ibm.fhir.threading.ThreadPool;
import com.ibm.nlp.hibernate.MedicationAdverseEventDao;
import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.hibernate.PrescriptionDao;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.services.MedicationAdverseEventService;
import com.ibm.nlp.services.NoteEventService;
import com.ibm.nlp.services.PatientService;
import com.ibm.nlp.services.PrescriptionService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;

/**
 * The Class DoMimicStudy.
 *
 * @author henry.feldman@ibm.com
 */
public class DoMimicStudy {

	/** The patient list. */
	private static List<Patients> patientList;

	/** The patient map. */
	private static Map<Integer, Patients> patientMap = new HashMap<Integer, Patients>();

	/** The note list. */
	private static List<NoteEvent> noteList;

	/** The note event admission list. */
	private static Map<Integer, NoteEvent> noteEventAdmissionMap = new HashMap<Integer, NoteEvent>();

	/** The prescription list. */
	private static List<Prescription> prescriptionList = new ArrayList<Prescription>();

	/** The admission prescription map. */
	private static Map<Integer, List<Prescription>> admissionPrescriptionMap = new HashMap<Integer, List<Prescription>>();

	/** The thread pool. */
	static ThreadPool threadPool;

	private static java.util.Set<String> mappedPathwayConcepts = new HashSet<String>();

	private final static NoteEventService noteEventDao = new NoteEventDao();

	private final static PatientService patientDao = new PatientDao();

	private final static PrescriptionService prescriptionDao = new PrescriptionDao();

	private final static MedicationAdverseEventService medicationAdverseEventDao = new MedicationAdverseEventDao();

	/**
	 * Instantiates a new do mimic study.
	 */
	public DoMimicStudy() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Starting Data Load of MIMIC ACD Study data");
		loadStudyData();
	}

	/**
	 * Load study data.
	 */
	private static void loadStudyData() {
		Instant startTime = Instant.now();
		try {
			System.out.println("Clearing alerts table");

			medicationAdverseEventDao.truncateMedicationAdverseEvents();

			patientList = patientDao.getAllPatientsForAcdStudy();

			// MimicDao.addlanguageRaceToPatients(patientList);
//			List<CareGiver> careGiverList = MimicDao.getCareGiverList();
//			MimicDao.addNamesToCareGivers(careGiverList);
			// do the fhir update
//			MimicDao.addFhirToPatients(patientList);
//			System.out.println("UPDATED THE PATIENTS WITH FHIR");
			// System.exit(0);

			noteList = noteEventDao.getAllNoteEventsForMimicStudy();
			// tmporary go to brat files
			// BratUtil.exportADEToBrat(noteList);
//			System.exit(0);
//			for (Noteevent note : noteList) {
//				admissionIdSet.add(note.getHadmId());
//				noteEventAdmissionMap.put(note.getHadmId(), note);
//			}
			prescriptionList = prescriptionDao.getAllPrescriptionsForAcdStudy();

//			MimicDao.addFhirToLabs();
//			System.out.println("ADDED FHIR TO Labs");
//			System.exit(0);

//			MimicDao.fillFhirServer();
			// ***************************** REMOVE *******************
//			System.exit(0);
			// ***************************** REMOVE *******************

			System.out.println("Making maps for admission for ACD processing");
			// put the prescriptions into a map by admission to tie them to notes
			Integer prescriptionCount = 0;
			for (Prescription prescription : prescriptionList) {
				// create a filter date which is 30 minutes after the note is entered
				if (!noteEventAdmissionMap.containsKey(prescription.getHadmId())
						|| noteEventAdmissionMap.get(prescription.getHadmId()).getChartdate() == null) {
					admissionPrescriptionMap.put(prescription.getHadmId(), new ArrayList<Prescription>());
					admissionPrescriptionMap.get(prescription.getHadmId()).add(prescription);
					prescriptionCount++;
				} else {
					Date filterStartTime = new Date(
							noteEventAdmissionMap.get(prescription.getHadmId()).getChartdate().getTime() + 1000L
									+ 60 * 30);
					// so make sure the prescription is written within 1 hour of the note and is not
					// ended within the same window
					if (prescription.getStartdate() != null && prescription.getEnddate() != null
							&& prescription.getStartdate().before(filterStartTime)
							&& prescription.getEnddate().after(filterStartTime)) {
						if (!admissionPrescriptionMap.containsKey(prescription.getHadmId())) {
							admissionPrescriptionMap.put(prescription.getHadmId(), new ArrayList<Prescription>());
						}
						admissionPrescriptionMap.get(prescription.getHadmId()).add(prescription);
						prescriptionCount++;
					}
				}
			}
			System.out.println("After time filtering " + prescriptionCount + " prescriptions remained of "
					+ prescriptionList.size());
			for (Patients patient : patientList) {
				patientMap.put(patient.getSubjectId(), patient);
			}

			System.out.println("Creating the Thread Pool");
			threadPool = new ThreadPool(noteList.size(), 10);
			System.out.println("processing admission notes to the Thread Pool");
			Integer counter = 0;
			// create the ACD service to send to each thread rather than creating one in
			// each thread

			AnnotatorForClinicalData service = AcdConfugurator.getAcdServiceInstance();

			Integer index = 0;
			for (NoteEvent noteEvent : noteList) {
				// we only process admission that have medicaitons or else there would be
				// nothing to measure against

				// do a test runnable to see if this is all working
//				for (int i = 0; i < 100; i++) {
//					threadPool.submitTask(new TestThread("Thread-" + i));
//				}
				if (admissionPrescriptionMap.containsKey(noteEvent.getHadmId())) {
					if (noteList.size() < index + 1) {
						threadPool.submitTask(new AcdAnalyzerThread(noteEvent,
								admissionPrescriptionMap.get(noteEvent.getHadmId()),
								patientMap.get(noteEvent.getSubjectId()), "doing: " + counter++ + "/" + noteList.size(),
								"Thread-" + counter, service, mappedPathwayConcepts,
								noteList.get(index + 1).getHadmId(), medicationAdverseEventDao));
					} else {
						// if we get to the end of the list, then wrap the HTML link back to the first
						// file.
						threadPool.submitTask(
								new AcdAnalyzerThread(noteEvent, admissionPrescriptionMap.get(noteEvent.getHadmId()),
										patientMap.get(noteEvent.getSubjectId()),
										"doing: " + counter++ + "/" + noteList.size(), "Thread-" + counter, service,
										mappedPathwayConcepts, noteList.get(0).getHadmId(), medicationAdverseEventDao));
					}
				}
				index++;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Instant endTime = Instant.now();

		System.out.println(patientList.size() + " study patients loaded");
		System.out.println(noteList.size() + " study notes loaded");
		System.out.println(prescriptionList.size() + " Study medication orders loaded");
		Long seconds = Duration.between(startTime, endTime).getSeconds();

		System.out.println("Query Time " + seconds + " seconds");

	}

}
