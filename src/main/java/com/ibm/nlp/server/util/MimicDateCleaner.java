package com.ibm.nlp.server.util;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.ibm.fhir.threading.ChartEventDateFixerThread;
import com.ibm.fhir.threading.ThreadPool;
import com.ibm.nlp.AcdConfugurator;
import com.ibm.nlp.hibernate.AdmissionDao;
import com.ibm.nlp.hibernate.CaregiverDao;
import com.ibm.nlp.hibernate.ChartEventDao;
import com.ibm.nlp.hibernate.LabEventDao;
import com.ibm.nlp.hibernate.NoteEventDao;
import com.ibm.nlp.hibernate.PatientDao;
import com.ibm.nlp.hibernate.PrescriptionDao;
import com.ibm.nlp.hibernate.ReportsDao;
import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.LabItem;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.services.AdmissionService;
import com.ibm.nlp.services.ChartEventService;
import com.ibm.nlp.services.LabEventService;
import com.ibm.nlp.services.NoteEventService;
import com.ibm.nlp.services.PatientService;
import com.ibm.nlp.services.ReportService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;

/**
 * The Class MimicDateCleaner. will go through and fuzz all the dates in mimic
 * III to be A) in the past and B) not the real dates
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public class MimicDateCleaner {

	/** The Constant patientDao. */
	private final static PatientService patientDao = new PatientDao();

	/** The Constant admissionDao. */
	private final static AdmissionService admissionDao = new AdmissionDao();

	/** The Constant DAY. */
	private final static Long DAY = 60000L * 60L * 24L;

	/** The Constant YEAR. */
	@SuppressWarnings("unused")
	private final static Long YEAR = DAY * 365;

	/** The patient list. */
	private static List<Patients> patientList;

	/** The random. */
	Random random = new Random();

	/** The patient map. */
	private static Map<Integer, Patients> patientMap = new HashMap<Integer, Patients>();

	/** The admission year map. */
	private static Map<Integer, Integer> admissionYearMap = new HashMap<Integer, Integer>();

	/** The admission map. */
	private static Map<Integer, Admission> admissionMap = new HashMap<Integer, Admission>();

	/** The lab item map. */
	private static Map<Integer, LabItem> labItemMap = new HashMap<Integer, LabItem>();

	/** The lab dao. */
	private static LabEventDao labDao = new LabEventDao();

	/** The note event dao. */
	@SuppressWarnings("unused")
	private static NoteEventService noteEventDao = new NoteEventDao();

	/** The prescription dao. */
	public static PrescriptionDao prescriptionDao = new PrescriptionDao();

	/** The caregiver dao. */
	public static CaregiverDao caregiverDao = new CaregiverDao();

	/** The reports dao. */
	private static ReportService reportsDao = new ReportsDao();

	private static final LabEventService labEventDao = new LabEventDao();

	private static final ChartEventService chartEventDao = new ChartEventDao();
	/** The now. */
	@SuppressWarnings("unused")
	private static Date now = new Date();

	/** The thread pool. */
	private static ThreadPool threadPool;

	/** The Constant PAGESIZE. */
	public static final Integer PAGESIZE = 5000;

	private static AnnotatorForClinicalData service;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		System.out.println("setting up ACD");
		service = AcdConfugurator.getAcdServiceInstance();
		System.out.println("Making patient map");
		patientList = patientDao.getAllPatients();
		for (Patients patient : patientList) {
			patientMap.put(patient.getSubjectId(), patient);
		}

		System.out.println("making lab def map");

		for (LabItem labItem : labDao.getAllLabItems()) {
			labItemMap.put(labItem.getItemId(), labItem);
		}

		System.out.println("Making admission map");
		List<Admission> admissionList = admissionDao.getAllAdmission();
		System.out.println(admissionList.size() + " admissions");
		for (Admission admission : admissionList) {
//			System.out.println(++admitCounter + "/" + admissionList.size());
			admissionYearMap.put(admission.getHadmId(), admission.getAdmitTime().getYear());
			admissionMap.put(admission.getHadmId(), admission);
		}

		Long numberOfRows = chartEventDao.getNumberOfChartEventRows();
		Long pageCount = numberOfRows / PAGESIZE;

		threadPool = new ThreadPool(pageCount.intValue(), 16);

		System.out.println("TOTAL THREADS: " + pageCount.intValue());

		for (int page = 181; page < 32000; page++) {
			threadPool.submitTask(new ChartEventDateFixerThread("Page-" + page, admissionYearMap, admissionMap,
					labItemMap, page, chartEventDao));
		}
	}

}
