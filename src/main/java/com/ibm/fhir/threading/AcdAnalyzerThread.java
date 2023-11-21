package com.ibm.fhir.threading;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ibm.cloud.sdk.core.service.exception.BadRequestException;
import com.ibm.fhir.util.FhirUtil;
import com.ibm.fhir.util.RxNormUtil;
import com.ibm.nlp.model.Medication;
import com.ibm.nlp.model.MedicationActionPotential;
import com.ibm.nlp.model.ProblemListItem;
import com.ibm.nlp.model.ProblemMedication;
import com.ibm.nlp.model.ProblemProcedure;
import com.ibm.nlp.model.mimic3.MedicationAdverseEvent;
import com.ibm.nlp.model.mimic3.NoteEvent;
import com.ibm.nlp.model.mimic3.Patients;
import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.services.MedicationAdverseEventService;
import com.ibm.watson.health.acd.v1.AnnotatorForClinicalData;
import com.ibm.watson.health.acd.v1.model.AttributeValueAnnotation;
import com.ibm.watson.health.acd.v1.model.Concept;
import com.ibm.watson.health.acd.v1.model.ContainerGroup;
import com.ibm.watson.health.acd.v1.model.MedicationAnnotation;
import com.ibm.watson.health.acd.v1.model.SectionAnnotation;

/**
 * The Class AcdAnalyzerThread.
 *
 * @author henry.feldman@ibm.com
 */
public class AcdAnalyzerThread implements Runnable {

	/** The note event. */
	private NoteEvent noteEvent;

	/** The prescription list. */
	private List<Prescription> prescriptionList;

	/** The patient med list. */
	private List<Medication> patientMedList = new ArrayList<Medication>();

	/** The patient med map. */
	private Map<String, Medication> patientMedMap = new HashMap<String, Medication>();

	/** The concept map. */
	private Map<Long, List<Concept>> conceptMap = new HashMap<Long, List<Concept>>();

	/** The med dups. */
	private java.util.Set<String> medDups = new HashSet<String>();

	/** The problem item list. */
	private List<ProblemListItem> problemItemList = new ArrayList<ProblemListItem>();

	/** The alert map. */
	private Map<Integer, String> alertMap = new HashMap<Integer, String>();

	/** The patient. */
	private Patients patient;

	/** The prescribed meds map. */
	private Map<String, ProblemMedication> prescribedMedsMap = new HashMap<String, ProblemMedication>();

	/** The thread name. */
	private final String threadName;

	/** The service. */
	private final AnnotatorForClinicalData service;

	/** The next admission id. */
	private final Integer nextAdmissionId;

	private final MedicationAdverseEventService medicationAdverseEventDao;

	Logger logger = LoggerFactory.getLogger(AcdAnalyzerThread.class);

	/**
	 * The un associated problem list item is a special patch for bad note
	 * formatting preventing list detection.
	 */
	private ProblemListItem unAssociatedProblemListItem;

	/**
	 * Instantiates a new acd analyzer thread.
	 *
	 * @param noteEvent             the note event
	 * @param prescriptionList      the prescription list
	 * @param patient               the patient
	 * @param counterMessage        the counter message
	 * @param threadName            the thread name
	 * @param service               the service
	 * @param mappedPathwayConcepts the mapped pathway concepts
	 * @param connection            the connection
	 * @param nextAdmissionId       the next admission id
	 */
	public AcdAnalyzerThread(NoteEvent noteEvent, List<Prescription> prescriptionList, Patients patient,
			String counterMessage, String threadName, AnnotatorForClinicalData service,
			java.util.Set<String> mappedPathwayConcepts, Integer nextAdmissionId,
			MedicationAdverseEventService medicationAdverseEventDao) {
		super();
		this.noteEvent = noteEvent;
		this.prescriptionList = prescriptionList;
		this.patient = patient;
		this.threadName = threadName;
		this.service = service;
		this.nextAdmissionId = nextAdmissionId;
		this.medicationAdverseEventDao = medicationAdverseEventDao;
	}

	/**
	 * Run.
	 */
	@Override
	public void run() {
		System.out.println(threadName + " thread starting");
		try {
			doNLPViaACD(noteEvent, patient);
		} catch (Exception e) {
			System.out.println(threadName + " crashed");
			e.printStackTrace();
		}
		System.out.println(threadName + " exiting");
	}

	/**
	 * Do NLP via ACD.
	 *
	 * @param noteText the note text
	 * @param patient  the patient
	 * @throws Exception the exception
	 */
	private void doNLPViaACD(final NoteEvent noteText, final Patients patient) throws Exception {
		System.out.println(threadName + " starting ACD query");

		// prep for unassociated medication handling:
		unAssociatedProblemListItem = new ProblemListItem("Unassociated Dignosis", null, "", "N/A",
				new ArrayList<Concept>(), new ArrayList<ProblemMedication>(), new ArrayList<String>(),
				new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<ProblemProcedure>(), noteEvent.getHadmId());
		// We are going to start by finding the sections, once we find the assessment
		// and plan, we will substring that.

		// now that we have the patient let's get the medications from the "EMR"

		// we first want the section annotator, so we have that in our cartridge flow
		// Request to ACD
		ContainerGroup response = null;

		// wh_acd.ibm_clinical_insights_v1.0_standard_flow
		try {
			response = service.analyzeWithFlow("henry_test_cartridge_v1.0_aap_test_flow", noteText.getText());
			System.out.println(threadName + " ACD query completed");
		} catch (BadRequestException e) {
			java.lang.System.out.println(e.getMessage() + " " + e.getStatusCode());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Example of iterating through Concept annotations and fetching a single value
		// from each
		List<SectionAnnotation> listSections = response.getSectionAnnotations();

		if (listSections != null) {
			// Example of fetching values from a concept in the list of concepts
			int counter = 0;
			for (SectionAnnotation concept : listSections) {
				// Get the A&P section and get it's text as then we will be parsing that into a
				// list of conditions to then look inside
				if (concept.getTrigger().getSectionNormalizedName().equals("Assessment and plan")) {
					Long begin = concept.getTrigger().getEnd();
					begin = begin + 1L; // offset the : at the end of the name
					// if A&P is the last section then we need to do a different range
					@SuppressWarnings("unused")
					Long end;
					if (listSections.size() == counter) {
						SectionAnnotation nextAnnotation = listSections.get(counter + 1);
						end = nextAnnotation.getTrigger().getEnd();
					} else {
						end = new Long(noteText.getText().length());
					}
				}
				counter++;
			}

			// Map all the concepts for later getting the concept back from a UID code in
			// the attribute list
			Map<Long, Concept> uidConceptMap = new HashMap<Long, Concept>();
			// We need to map concepts by UID for later
			for (Concept concept : response.getConcepts()) {
				if (concept.getUid() != null) {
					if (!uidConceptMap.containsKey(concept.getUid())) {
						uidConceptMap.put(concept.getUid(), concept);
					}
				}
			}
			Map<Long, MedicationAnnotation> uidMedicationAnnotationMap = new HashMap<Long, MedicationAnnotation>();
			for (MedicationAnnotation indication : response.getMedicationInd()) {
				if (indication.getUid() != null) {
					if (!uidMedicationAnnotationMap.containsKey(indication.getUid())) {
						uidMedicationAnnotationMap.put(indication.getUid(), indication);
					}
				}
			}

//			System.out.println("CONCEPT MAP SIZE: " + uidConceptMap.size());
			for (AttributeValueAnnotation attribute : response.getAttributeValues()) {
				// make sure that this is an ADE and that ACD thinks it is valid
				if (attribute.getName() != null && attribute.getName().equals("MedicationAdverseEvent")) {
					if (attribute.getName().equals("MedicationAdverseEvent")) {
						if (attribute.getDerivedFrom() == null || attribute.getDerivedFrom().isEmpty()
								|| !uidConceptMap.containsKey(attribute.getDerivedFrom().get(0).getUid())) {
							logger.error("failed to get the derived from, compensating \n" + attribute);
						}
						String reaction = null;
						if (uidConceptMap.containsKey(attribute.getUid())) {
							reaction = uidConceptMap.get(attribute.getDerivedFrom().get(0).getUid()).getPreferredName();
						} else if (uidMedicationAnnotationMap.containsKey(attribute.getUid())) {
							@SuppressWarnings("unused")
							MedicationAnnotation medicationAnnotation = uidMedicationAnnotationMap
									.get(attribute.getUid());
						} else {
							reaction = "unable to determine";
						}
						String agent;
						if (attribute.getPreferredName() != null) {
							agent = attribute.getPreferredName();
						} else if (uidMedicationAnnotationMap.containsKey(attribute.getUid())) {
							MedicationAnnotation medicationAnnotation = uidMedicationAnnotationMap
									.get(attribute.getUid());
							agent = medicationAnnotation.getCoveredText();
							logger.warn("falling back on ACI for drug name");
							;
						} else {
							agent = "UNKNOWN";
						}

						MedicationAdverseEvent medicationAdverseEvent = new MedicationAdverseEvent();
						medicationAdverseEvent
								.setValid(attribute.getDisambiguationData().getValidity().equals("VALID"));
						if (!medicationAdverseEvent.getValid()) {
							System.out.println("\n\nADE EVENT INVALIDATED ********\n\n");
						}
						// now get a span around the ADE text, we set the begin to min 4 since we look
						// to the left to see if it has on in the phrase
						Integer begin = attribute.getBegin().intValue() - 100;
						if (begin < 4) {
							begin = 4;
						}

						Integer end = attribute.getEnd().intValue() + 100;
						if (end > noteEvent.getText().length()) {
							end = noteEvent.getText().length();
						}
						medicationAdverseEvent.setAmbiguousPhrase(noteEvent.getText()
								.substring(attribute.getBegin().intValue() - 4, attribute.getBegin().intValue())
								.equals(" on "));

						try {
							medicationAdverseEvent.setAdeSpan(noteEvent.getText().substring(begin, end));
						} catch (Exception e) {
							logger.error("Span generation failed with indices: " + "BEGIN: " + begin + " END: " + end
									+ "\n" + e.getLocalizedMessage());
						}
						medicationAdverseEvent.setAdeStartIndex(begin);
						medicationAdverseEvent.setAdeStopIndex(end);

						if (attribute.getRxNormId() != null) {
							medicationAdverseEvent.setRxNormId(attribute.getRxNormId());
						} else {
							medicationAdverseEvent.setRxNormId("unable to obtain");
						}
						medicationAdverseEvent
								.setAdeScore(attribute.getInsightModelData().getMedication().getAdverse().getScore());
						medicationAdverseEvent.setAdeDrugName(agent);
						medicationAdverseEvent.setAdeReaction(reaction);
						medicationAdverseEvent.setNoteEvent(noteEvent);
						medicationAdverseEvent.setHadmId(noteEvent.getHadmId());

						medicationAdverseEventDao.saveMedicationAdverseEvent(medicationAdverseEvent);

					}
				}
			}

			// kill the big analyzer just for the ADE scan
			return;
			// ------------ The rest of this is leftover from the Errors of Omission project
			// ---------------
			// SO now we can have the plan into a hashmap based on their covered texts from
			// the diagnosis list from the cartridge. This is because ACD currently doesn't
			// pull the associated plan with the problems

			// now send the AAP to the util that will break them into text blocks based on
			// blank lines.
//			System.out.println(threadName + " submitting ACD query on A&P section");
//			response = service.analyzeWithFlow("henry_test_cartridge_v1.0_aap_test_flow", assessmentAndPlan);
//			System.out.println(threadName + " completed ACD query on A&P section");

			// now we need to submit this somewhere;
//			checkForErrors(response.getAttributeValues());
		}

	}

	/**
	 * Check for errors of omission by comparing orders to medications.
	 *
	 * @param attributeList the attribute list
	 */
	@SuppressWarnings("unused")
	private void checkForErrors(List<AttributeValueAnnotation> attributeList) {
		System.out.println(threadName + " starting order reconcilliation");
		// fill the map for what meds the patient is on by RxNormId
		for (Medication medication : patientMedList) {
			patientMedMap.put(medication.getRxNorm(), medication);
		}
		ProblemListItem problemListItem = null;
		boolean listItem = false;
		for (AttributeValueAnnotation attribute : attributeList) {
			// if we hit a numbered list item then we are in a list of diagnoses so set the
			// flag, the next diagnosis is that list item's diagnosis
			if (attribute.getName().toUpperCase().equals("LISTITEM") && listItem == false) {
				listItem = true;
			}
			if (attribute.getPreferredName() != null && attribute.getPreferredName().equals("levophed")) {
				System.out.println("FOUND LEVOPHED: " + attribute);
				System.out.println("LISTITEM: " + listItem + " & problemListItem: " + problemListItem);
			}

			if (attribute.getName().equals("Diagnosis") && listItem == true) {
				// Make a new problem, otherwise fall through and work on the one we already
				// have. We don't worry about the cruft at the top of the A&P since there is no
				// diagnosis yet, so that will be ignored since the problemlistitem will be null
				problemListItem = new ProblemListItem();
				// remove the text making up the numbered element
				String name = attribute.getPreferredName().substring(attribute.getCoveredText().indexOf(".") + 1);
				problemListItem.setName(name.replace("&#x27;", "'").replace("&#x2f;", "/"));
				problemListItem.setId(attribute.getUid());
				problemListItem.setCui(attribute.getSnomedConceptId());
				// try and find the coding info to make up for the bug
				if (conceptMap.containsKey(attribute.getUid())) {
					// we iterate as multiple concepts may map in here
					for (Concept concept : conceptMap.get(attribute.getUid())) {
						if (concept.getType().equals("ICDiagnosis")) {
							problemListItem.setCui(concept.getCui());
							problemListItem.setIcdCode(concept.getIcd10Code());
						}
					}
				}
				problemItemList.add(problemListItem);
				listItem = false;
				// we are in a new problem so kill the dups list
				medDups.clear();
			} else if (attribute.getName().equals("PrescribedMedication") && problemListItem != null) {
				if (attribute.getPreferredName() != null
						&& !attribute.getPreferredName().equals("Pharmacologic Substance")
						&& !attribute.getPreferredName().equals("Pharmaceutical Preparations")) {
					// So we often discuss the same medication repeatedly in the same section for
					// different reasons so don't duplicate it.

					if (attribute.getRxNormId() != null) {
						// if (!medDups.contains(attribute.getPreferredName())) {
						// this is just for the demo to make sure that Creon is listed as a start (and
						// we don't have it on the med list, so that should fire a failed-to-start alert

						ProblemMedication problemMedication = new ProblemMedication(attribute.getPreferredName(),
								attribute.getRxNormId(), attribute.isNegated(), attribute.getSnomedConceptId(),
								getActionForMedicationConcept(attribute));
						problemListItem.getMedications().add(problemMedication);
						// }
						medDups.add(attribute.getPreferredName());
					} else {
						ProblemMedication problemMedication = new ProblemMedication(attribute.getPreferredName(),
								attribute.getRxNormId(), attribute.isNegated(), attribute.getSnomedConceptId(),
								getActionForMedicationConcept(attribute));
						try {
							problemMedication.setRxNormId(RxNormUtil.searchRxNormByName(problemMedication.getName()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						problemListItem.getMedications().add(problemMedication);
					}
				}

				listItem = false;
			} else if (attribute.getName().equals("PrescribedMedication")
					&& (problemListItem == null || listItem == false)) {
				// special version to get medications not associated with a problem (usualy due
				// to note formatting
				System.out.println("UNASSOCIATED PM");
				if (attribute.getPreferredName() != null
						&& !attribute.getPreferredName().equals("Pharmacologic Substance")
						&& !attribute.getPreferredName().equals("Pharmaceutical Preparations")) {
					// So we often discuss the same medication repeatedly in the same section for
					// different reasons so don't duplicate it.

					if (attribute.getRxNormId() != null) {
						// if (!medDups.contains(attribute.getPreferredName())) {
						// this is just for the demo to make sure that Creon is listed as a start (and
						// we don't have it on the med list, so that should fire a failed-to-start alert

						ProblemMedication problemMedication = new ProblemMedication(attribute.getPreferredName(),
								attribute.getRxNormId(), attribute.isNegated(), attribute.getSnomedConceptId(),
								getActionForMedicationConcept(attribute));
						prescribedMedsMap.put(problemMedication.getRxNormId(), problemMedication);
						unAssociatedProblemListItem.getMedications().add(problemMedication);
						// }
						medDups.add(attribute.getPreferredName());
					} else {
						ProblemMedication problemMedication = new ProblemMedication(attribute.getPreferredName(),
								attribute.getRxNormId(), attribute.isNegated(), attribute.getSnomedConceptId(),
								getActionForMedicationConcept(attribute));
						try {
							problemMedication.setRxNormId(RxNormUtil.searchRxNormByName(problemMedication.getName()));
						} catch (Exception e) {
							e.printStackTrace();
						}
						prescribedMedsMap.put(problemMedication.getRxNormId(), problemMedication);
						unAssociatedProblemListItem.getMedications().add(problemMedication);
					}
				}

				listItem = false;
			} else if (attribute.getName().equals("ICProcedure") && problemListItem != null) {
				ProblemProcedure procedure = new ProblemProcedure();
				procedure.setName(attribute.getPreferredName());
				procedure.setCui(attribute.getSnomedConceptId());
				problemListItem.getProcedures().add(procedure);
				listItem = false;
			}
			// first check the problemItemList's medications, then do it for the global List
			for (ProblemListItem problemItem : problemItemList) {
				int row = 0;
				for (ProblemMedication med : problemItem.getMedications()) {
					// first we need to do the plan-pathway validation (i.e. is it even a valid plan
					// to then reconcile with actions

					// now do the plan validation
					if (med.getAction().getName().equals(ProblemMedication.DC)
							&& patientMedMap.containsKey(med.getRxNormId())
							&& patientMedMap.get(med.getRxNormId()).isActive()) {
						// make sure we only alert once per med per problem
						// this is a failed to stop condition
						String alertText = clinicalAlert(Medication.FAILED_TO_STOP, med.getName(), patient);
						alertMap.put(row, alertText);
//						writeAlertToDb(noteEvent.getSubjectId(), noteEvent.getHadmId(), noteEvent.getRowId(), alertText,
//								med.getRxNormId(), problemItem.getName(), noteEvent.getAdeSpan());
						problemItem.getAlerts().add(alertText);
					} else if (med.getAction().getName().equals(ProblemMedication.START)
							&& !patientMedMap.containsKey(med.getRxNormId())) {
						// make sure we only alert once per med per problem
						String alertText = clinicalAlert(Medication.FAILED_TO_START, med.getName(), patient);

						alertMap.put(row, alertText);
//						writeAlertToDb(noteEvent.getSubjectId(), noteEvent.getHadmId(), noteEvent.getRowId(), alertText,
//								med.getRxNormId(), problemItem.getName(), noteEvent.getAdeSpan());
						problemItem.getAlerts().add(alertText);

					}

					row++;
				}
			}
			int row = 0;
			for (String rxNormId : prescribedMedsMap.keySet()) {
				ProblemMedication med = prescribedMedsMap.get(rxNormId);
				// first we need to do the plan-pathway validation (i.e. is it even a valid plan
				// to then reconcile with actions

				// now do the plan validation
				if (med.getAction().getName().equals(ProblemMedication.DC)
						&& patientMedMap.containsKey(med.getRxNormId())
						&& patientMedMap.get(med.getRxNormId()).isActive()) {
					// make sure we only alert once per med per problem
					// this is a failed to stop condition
					String alertText = clinicalAlert(Medication.FAILED_TO_STOP, med.getName(), patient);
					alertMap.put(row, alertText);
//					writeAlertToDb(noteEvent.getSubjectId(), noteEvent.getHadmId(), noteEvent.getRowId(), alertText,
//							med.getRxNormId(), "Unassociated", noteEvent.getAdeSpan());
					unAssociatedProblemListItem.getAlerts().add(alertText);
				} else if (med.getAction().getName().equals(ProblemMedication.START)
						&& !patientMedMap.containsKey(med.getRxNormId())) {
					// make sure we only alert once per med per problem
					String alertText = clinicalAlert(Medication.FAILED_TO_START, med.getName(), patient);

					alertMap.put(row, alertText);
//					writeAlertToDb(noteEvent.getSubjectId(), noteEvent.getHadmId(), noteEvent.getRowId(), alertText,
//							med.getRxNormId(), unAssociatedProblemListItem.getName(), noteEvent.getAdeSpan());
					unAssociatedProblemListItem.getAlerts().add(alertText);

				}

				row++;
			}

			if (attribute.getName().equals("Diagnosis") && listItem == false) {
				System.out.println("Diagnosis outside ListItem: " + attribute.getPreferredName());
			}

		}
		// handler for unassociated problems
		problemItemList.add(unAssociatedProblemListItem);
		try {
			FileWriter writer = new FileWriter("html/" + noteEvent.getRowId() + ".html");
			writer.write(getHTML());
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(threadName + " completed order reconcilliation");
	}

	/**
	 * Clinical alert.
	 *
	 * @param code        the code
	 * @param alertOnName the alert on name
	 * @param patient     the patient
	 * @return the string
	 */
	private String clinicalAlert(Integer code, String alertOnName, Patients patient) {
		String alertText;

		switch (code) {
		case Medication.FAILED_TO_STOP:
			alertText = alertOnName
					+ " seem to have been planned to be stopped, but there is still an active order! for "
					+ alertOnName;
			break;
		case Medication.FAILED_TO_START:
			alertText = alertOnName
					+ " seem to have been planned to be started, but there is no active order in the EMR for "
					+ alertOnName;
			break;
		case Medication.FAILED_TO_MOD:
			alertText = alertOnName
					+ " seem to have been planned to be adjusted, but there is no adjustment order in the EMR "
					+ alertOnName;
			break;
		default:
			alertText = alertOnName
					+ " seem to have a plan/EMR mismatch please check that the orders in the EMR are correct";
			break;
		}

		return alertText;
	}

	/**
	 * Setup treatment pathways.
	 *
	 * @param concept the concept
	 * @return the action for medication concept
	 */

	/**
	 * Gets the action for medication concept.
	 *
	 * @param concept the concept
	 * @return the action for medication concept
	 */
	private MedicationActionPotential getActionForMedicationConcept(AttributeValueAnnotation concept) {
		List<MedicationActionPotential> actionPotentials = new ArrayList<MedicationActionPotential>();
		if (concept.getInsightModelData() != null && concept.getInsightModelData().getMedication() != null) {
			try {
				// We now put the potenial actions into a list so we can sort them to get the
				// most likely

				concept.getInsightModelData().getMedication().getStarted().getScore();
				actionPotentials.add(new MedicationActionPotential(ProblemMedication.START,
						concept.getInsightModelData().getMedication().getStarted().getScore()));
				actionPotentials.add(new MedicationActionPotential(ProblemMedication.DC,
						concept.getInsightModelData().getMedication().getStopped().getScore()));
				concept.getInsightModelData().getMedication().getUsage().getTakenScore();
				actionPotentials.add(new MedicationActionPotential(ProblemMedication.MODIFY,
						concept.getInsightModelData().getMedication().getDoseChanged().getScore()));
				// we sort to get the highest potential action (i.e. the last one). This may be
				// a low probability, but it is the most likely thing we can determine even if
				// we are unsure.
				java.util.Collections.sort(actionPotentials);
				java.util.Collections.reverse(actionPotentials);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		// get the first item (i.e. the highest priority)
		if (!actionPotentials.isEmpty() && actionPotentials.get(actionPotentials.size() - 1) != null) {
			return actionPotentials.get(0);
		} else {
			return new MedicationActionPotential(ProblemMedication.UNKNOWN, 0F);
		}
	}

	/**
	 * Gets the html.
	 *
	 * @return the html
	 */
	private String getHTML() {
		StringBuilder sb = new StringBuilder();
		sb.append(
				"<!DOCTYPE html> <html> <head> <title>" + "Admission: " + noteEvent.getHadmId() + "</title> </head>\n");
		sb.append("<style>.alertText\n" + "{\n" + "	color: #FF2600;\n" + "	font-style: italic;\n"
				+ "	font-weight: bolder;\n" + "}\n" + "</style>\n");
		sb.append("<body>\n");
		sb.append("<a href=\"" + "html/" + nextAdmissionId + ".html\">Next>></a>");
		sb.append(patient.getAsHTML());
		sb.append("<b>Note Signed: </b>" + FhirUtil.toIsoDate(noteEvent.getChartdate(), false) + "<br>\n");
		sb.append("<h3>" + noteEvent.getDescription() + " admission id: " + noteEvent.getHadmId() + "<h3>\n");
		sb.append("<pre>" + noteEvent.getText() + "<pre><br>\n");
		sb.append("<h3>POE Medication Orders</h3>");
		sb.append("<ol>\n");
		for (Prescription prescription : prescriptionList) {
			sb.append("<li>" + prescription.toHTML() + "</li>\n");
		}
		sb.append("</ol>\n");
		sb.append("<h2>Detected Diagnoses</h2>");
		for (ProblemListItem problemListItem : problemItemList) {
			sb.append(problemListItem.getAsHTML() + "\n");
		}
		sb.append("</body></html>");
		return sb.toString();
	}
}
