package com.ibm.fhir.threading;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.ibm.fhir.util.FhirUtil;
import com.ibm.nlp.model.mimic3.Admission;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.server.util.MimicDateCleaner;
import com.ibm.nlp.services.NoteEventService;

public class NoteEventTextDateCleanerThread implements Runnable {

	private final NoteEventService noteEventDao;

	private final Map<Integer, Patients> patientMap;

	/** The admission year map. */
	private final Map<Integer, Integer> admissionYearMap;

	/** The admission map. */
	private final Map<Integer, Admission> admissionMap;

	/** The thread name. */
	private final String threadName;

	/** The page. */
	private final Integer page;
	private static String beginningDOB = "Date of Birth:  [**";
	private static String endSTRING = "**]";

	private static String beginningADMIT = "Admission Date:  [**";

	private static String beginningDC = "Discharge Date:   [**";

	private static Integer startValue, endValue;

	private static SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/YYYY");

	private List<NoteEvent> noteEventList;

	public NoteEventTextDateCleanerThread(NoteEventService noteEventDao, Map<Integer, Patients> patientMap,
			String threadName, Integer page, Map<Integer, Admission> admissionMap,
			Map<Integer, Integer> admissionYearMap) {
		super();
		this.noteEventDao = noteEventDao;
		this.patientMap = patientMap;
		this.threadName = threadName;
		this.page = page;
		this.admissionMap = admissionMap;
		this.admissionYearMap = admissionYearMap;
	}

	@Override
	public void run() {
		// ScanForBirthDates();
		fixIdFlaw();
	}

	private synchronized void fixIdFlaw() {
		System.out.println("STARTING THREAD for page: " + page);
		noteEventList = noteEventDao.getNoteEventsByPage(page, MimicDateCleaner.PAGESIZE);
		for (NoteEvent noteEvent : noteEventList) {
			if (noteEvent.getFhirJson() != null && !noteEvent.getFhirJson().isEmpty()) {
				noteEvent.getFhirJson().replace("12345", noteEvent.getSubjectId().toString());
			} else {
				FhirUtil.getNoteAsFhir(noteEvent);
			}
			ScanForDischargeDates(noteEvent);
			noteEventDao.saveNoteEvent(noteEvent);
		}
	}

	private synchronized void ScanForBirthDates() {
		System.out.println("STARTING THREAD for page: " + page);
		noteEventList = noteEventDao.getNoteEventsByPage(page, MimicDateCleaner.PAGESIZE);
		for (NoteEvent noteEvent : noteEventList) {
			if (noteEvent.getText().indexOf(beginningDOB) >= 0 && noteEvent.getText().indexOf(endSTRING) >= 0) {
				startValue = noteEvent.getText().indexOf(beginningDOB);
				endValue = noteEvent.getText().indexOf(endSTRING);

				try {
					noteEvent.setText(noteEvent.getText().substring(0, noteEvent.getText().indexOf(beginningDOB))
							+ "Date Of Birth: " + formatter.format(patientMap.get(noteEvent.getSubjectId()).getDob())
							+ noteEvent.getText().substring(endValue + 3));
				} catch (Exception e) {
					System.out.println("NOTE ROW_ID: " + noteEvent.getRowId());
					System.out.println("DOB: " + patientMap.get(noteEvent.getSubjectId()).getDob());
					e.printStackTrace();
				}
			}
			scanForAdmitDates(noteEvent);
			ScanForDischargeDates(noteEvent);
			fixFhir(noteEvent);
			fixChartDate(noteEvent);
			noteEventDao.saveNoteEvent(noteEvent);

		}
		System.out.println("COMPLETED THREAD for page: " + page);

	}

	private synchronized void scanForAdmitDates(NoteEvent noteEvent) {
		if (noteEvent.getText().indexOf(beginningADMIT) >= 0 && noteEvent.getText().indexOf(endSTRING) >= 0) {
			startValue = noteEvent.getText().indexOf(beginningADMIT);
			endValue = noteEvent.getText().indexOf(endSTRING);

			try {
				noteEvent.setText(noteEvent.getText().substring(0, noteEvent.getText().indexOf(beginningADMIT))
						+ "Admission Date: " + formatter.format(admissionMap.get(noteEvent.getHadmId()).getAdmitTime())
						+ noteEvent.getText().substring(endValue + 3));
			} catch (Exception e) {
				System.out.println("NOTE ROW_ID: " + noteEvent.getRowId());
				System.out.println("DOB: " + patientMap.get(noteEvent.getSubjectId()).getDob());
				e.printStackTrace();
			}
		}
	}

	private synchronized void ScanForDischargeDates(NoteEvent noteEvent) {
		if (noteEvent.getText().indexOf(beginningDC) >= 0 && noteEvent.getText().indexOf(endSTRING) >= 0) {
			startValue = noteEvent.getText().indexOf(beginningDC);
			endValue = noteEvent.getText().indexOf(endSTRING);

			try {
				noteEvent.setText(
						noteEvent.getText().substring(0, noteEvent.getText().indexOf(beginningDC)) + "Discharge Date: "
								+ formatter.format(admissionMap.get(noteEvent.getHadmId()).getDischargeTime())
								+ noteEvent.getText().substring(endValue + 3));
			} catch (Exception e) {
				System.out.println("NOTE ROW_ID: " + noteEvent.getRowId());
				System.out.println("DOB: " + patientMap.get(noteEvent.getSubjectId()).getDob());
				e.printStackTrace();
			}
		}
	}

	private synchronized void fixFhir(NoteEvent noteEvent) {
		FhirUtil.getNoteAsFhir(noteEvent);
	}

	@SuppressWarnings("deprecation")
	private synchronized void fixChartDate(NoteEvent noteEvent) {
		noteEvent.getChartdate().setYear(admissionYearMap.get(noteEvent.getHadmId()));
	}

}
