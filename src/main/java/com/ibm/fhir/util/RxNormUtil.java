package com.ibm.fhir.util;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ibm.fhir.model.RxNormStatus;
import com.ibm.nlp.fhir.Rxnormdata;
import com.ibm.nlp.hibernate.PrescriptionDao;
import com.ibm.nlp.model.IdGroup;
import com.ibm.nlp.model.mimic3.Prescription;
import com.ibm.nlp.services.PrescriptionService;

/**
 * The Class RxNormUtil.
 *
 * @author henry.feldman@ibm.com
 */
public class RxNormUtil {

	/** The match count. */
	private static Integer matchCount = 0;

	/** The prescription dao. */
	private static PrescriptionService prescriptionDao = new PrescriptionDao();

	/**
	 * Instantiates a new rx norm util.
	 */
	public RxNormUtil() {

	}

	/**
	 * Gets the rx norm cui for ndc.
	 *
	 * @param prescription the prescription
	 * @return the rx norm cui for ndc
	 * @throws ClientProtocolException the client protocol exception
	 * @throws IOException             Signals that an I/O exception has occurred.
	 */
	public static List<String> getRxNormCuiForNdc(Prescription prescription)
			throws ClientProtocolException, IOException {
		String ndcCode = prescription.getNdc();
		ObjectMapper mapper = new ObjectMapper();
		if (ndcCode == null) {
			// System.out.println("ERROR: NDC CODE was NULL");

			try {
				return searchRxNormByName(prescription);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		if (ndcCode.length() != 11) {
			// System.out.println("ERROR: NDC CODE was " + ndcCode.length() + " but should
			// be 11 chars - Ignored");
			try {
				return searchRxNormByName(prescription);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		} else {
			RxNormStatus status = null;
			String rxNormApiUrl = "https://rxnav.nlm.nih.gov/REST/rxcui.json?idtype=NDC&id=" + ndcCode;
			System.out.println(rxNormApiUrl);
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpGet request = new HttpGet(rxNormApiUrl);
			CloseableHttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// return it as a String
				String json = EntityUtils.toString(entity);
				status = mapper.readValue(json, RxNormStatus.class);
			}
			response.close();
			httpClient.close();
			if (status.getIdGroup().getRxnormId() != null && !status.getIdGroup().getRxnormId().isEmpty()) {
				prescription.setRxNormCui(status.getIdGroup().getRxnormId().get(0));
			}
			return status.getIdGroup().getRxnormId();
		}
	}

	/**
	 * Search rx norm by FDB.
	 *
	 * @param prescription the prescription
	 * @return the list
	 * @throws Exception the exception
	 */
// /rxcui?idtype=value&id=GCN_SEQNO&allsrc=1
	@SuppressWarnings("unused")
	private static String searchRxNormByFDB(Prescription prescription) throws Exception {
		// https://rxnav.nlm.nih.gov/REST/rxcui.json?name=Advil+200+mg+Tab&search=1
		XmlMapper xmlMapper = new XmlMapper();
		ObjectMapper jsonMapper = new ObjectMapper();
		String gsn;
		// special fix for multi codes in DB
		if (prescription.getGsn().trim().contains(" ")) {
			String[] codes = prescription.getGsn().split(" ");
			prescription.setGsn(codes[0]);
		}
		String rxNormApiUrl = null;
		try {
			rxNormApiUrl = "https://rxnav.nlm.nih.gov/REST/rxcui?idtype=" + prescription.getGsn().trim()
					+ "&id=GCN_SEQNO&allsrc=1";
			System.out.println(rxNormApiUrl);
		} catch (Exception e) {
			System.out.println(prescription);
			e.printStackTrace();
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet request = new HttpGet(rxNormApiUrl);
		CloseableHttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		Rxnormdata rxNormData = null;
		if (entity != null) {
			// return it as a String
			String xml = EntityUtils.toString(entity);
			System.out.println(xml);
			rxNormData = xmlMapper.readValue(xml, Rxnormdata.class);
		}

		response.close();
		httpClient.close();
		prescription.setRxNormCui(rxNormData.getIdGroup().getIdType());
		System.out.println("FOUND: " + rxNormData.getIdGroup().getIdType());
		System.out.println(rxNormData.getIdGroup());
		return prescription.getRxNormCui();

	}

	/**
	 * Search rx norm by name.
	 *
	 * @param prescription the prescription
	 * @return the list
	 * @throws Exception the exception
	 */
	public static List<String> searchRxNormByName(Prescription prescription) throws Exception {
		// https://rxnav.nlm.nih.gov/REST/rxcui.json?name=Advil+200+mg+Tab&search=1
		ObjectMapper mapper = new ObjectMapper();
		String rxNormApiUrl = "https://rxnav.nlm.nih.gov/REST/rxcui.json?name="
				+ URLEncoder.encode(prescription.getSearchStringForm(), StandardCharsets.UTF_8.toString())
				+ "&search=1";
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet request = new HttpGet(rxNormApiUrl);
		CloseableHttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		IdGroup status = null;
		if (entity != null) {
			// return it as a String
			String json = EntityUtils.toString(entity);
			status = mapper.readValue(json, IdGroup.class);
			System.out.println("Found RxNorm by name search Instead for " + prescription.getSearchStringForm());
		}
		response.close();
		httpClient.close();

		return status.getRxnormId();

	}

	/**
	 * Search rx norm by name.
	 *
	 * @param drugName the drug name
	 * @return the string
	 * @throws Exception the exception
	 */
	public static String searchRxNormByName(String drugName) throws Exception {
		// https://rxnav.nlm.nih.gov/REST/rxcui.json?name=Advil+200+mg+Tab&search=1
		ObjectMapper mapper = new ObjectMapper();
		String rxNormApiUrl = "https://rxnav.nlm.nih.gov/REST/rxcui.json?name="
				+ URLEncoder.encode(drugName, StandardCharsets.UTF_8.toString()) + "&search=1";
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet request = new HttpGet(rxNormApiUrl);
		CloseableHttpResponse response = httpClient.execute(request);
		HttpEntity entity = response.getEntity();
		IdGroup status = null;
		if (entity != null) {
			// return it as a String
			String json = EntityUtils.toString(entity);
			status = mapper.readValue(json, IdGroup.class);
		}
		response.close();
		httpClient.close();
		if (status.getRxnormId() == null) {
			return "UNKNOWN";
		} else {
			return status.getRxnormId().get(0);
		}
	}

	/**
	 * Try match rxnorm code dups.
	 *
	 * @param prescriptionList the prescription list
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void tryMatchRxnormCodeDups(List<Prescription> prescriptionList) throws IOException {
		FileWriter writer = new FileWriter("FixDupDrugCodes.sql");
		Map<String, String> drugNameToRxNormMap = new HashMap<String, String>();
		for (Prescription prescription : prescriptionList) {
			if (prescription.getRxNormCui() != null && !prescription.getRxNormCui().isEmpty()
					&& !drugNameToRxNormMap.containsKey(prescription.getSearchStringForm())) {
				drugNameToRxNormMap.put(prescription.getSearchStringForm(), prescription.getRxNormCui());
			} else if (prescription.getRxNormCui() == null || prescription.getRxNormCui().isEmpty()) {
				if (drugNameToRxNormMap.containsKey(prescription.getSearchStringForm())) {
					prescription.setRxNormCui(drugNameToRxNormMap.get(prescription.getSearchStringForm()));
					writer.write("update prescriptions set rxnormid = " + prescription.getRxNormCui()
							+ " where row_id = " + prescription.getRowId() + ", set acd_study_med = true;\n");
				}
			}
		}
		writer.close();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

	}

	/**
	 * Try match rxnormfor FDB.
	 *
	 * @param prescriptionList the prescription list
	 * @throws Exception the exception
	 */
	public static void tryMatchRxnormforFDB(List<Prescription> prescriptionList) throws Exception {
		Integer size = prescriptionList.size();
		System.out.println("Starting FDB matching for RxNorm");
		// append the broken ones at the end
		FileWriter writer = new FileWriter("FixFDBRxNormCodes.sql", true);
		List<String> ndccodes = null;
		for (Prescription prescription : prescriptionList) {
			if (ndccodes != null) {
				ndccodes.clear();
			}
			if (prescription.getGsn() == null || prescription.getGsn().contains("1972")) {
				ndccodes = getRxNormCuiForNdc(prescription);
				if (ndccodes != null && !ndccodes.isEmpty()) {
					prescription.setRxNormCui(ndccodes.get(0));
					System.out.println("NDC matched: " + prescription.getRxNormCui());
					matchCount++;
					System.out.println(matchCount + "/" + size);
					prescriptionDao.savePrescription(prescription);
				}
			} else if ((prescription.getRxNormCui() == null || prescription.getRxNormCui().isEmpty())
					&& (prescription.getGsn() != null && !prescription.getGsn().isEmpty()
							&& !prescription.getGsn().contains("1972"))
					|| (prescription.getRxNormCui() != null && (prescription.getRxNormCui().equals("1972")))) {
				try {
					if (prescription.getGsn() != null) {
						prescription.setRxNormCui(searchRxNormByFDB(prescription));
					}

					if (prescription != null && prescription.getRxNormCui() != null
							&& !prescription.getRxNormCui().isEmpty()) {
						matchCount++;
						System.out.println(matchCount + "/" + size);
						prescriptionDao.savePrescription(prescription);
						System.out.println("FDB matched: " + prescription.getRxNormCui());

					} else {
						System.out.println("NULL ERROR: " + prescription);
						String nameRxNorm = searchRxNormByName(prescription).get(0);
						if (nameRxNorm != null && !nameRxNorm.isEmpty()) {
							prescription.setRxNormCui(nameRxNorm);
							System.out.println("Name Search instead");
							System.out.println("Name matched");
							prescriptionDao.savePrescription(prescription);
						} else {
							size--;
						}
					}
				} catch (Exception e) {
					System.out.println(prescription);
					e.printStackTrace();
					writer.close();
					System.exit(0);
				}
			}
		}
		System.out.println("Completed FDB matching for RxNorm. matched additional: " + matchCount);
	}
}
