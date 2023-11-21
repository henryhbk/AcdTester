package com.ibm.nlp;

import java.io.FileWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.nlp.hibernate.SocialHistoryDao;
import com.ibm.nlp.model.mimic3.SocialHistory;
import com.ibm.nlp.services.SocialHistoryService;

/**
 * The Class ExportSocialHistories.
 */
public class ExportSocialHistories {

	/** The mapper. */
	private static ObjectMapper mapper = new ObjectMapper();

	/** The social history dao. */
	private static SocialHistoryService socialHistoryDao = new SocialHistoryDao();

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		System.out.println("Fetching List from DB of social histories");
		List<SocialHistory> socialHistoryList = socialHistoryDao.getAllSocialHistories();
		System.out.println("fetched " + socialHistoryList.size() + " records");
		saveToJsonFile(socialHistoryList);
	}

	/**
	 * Save to json file.
	 *
	 * @param socialHistoryList the social history list
	 */
	private static void saveToJsonFile(List<SocialHistory> socialHistoryList) {
		try {
			FileWriter writer = new FileWriter("socialHistory.json");
			for (SocialHistory socialHistory : socialHistoryList) {
				writer.append(mapper.writeValueAsString(socialHistory));
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
