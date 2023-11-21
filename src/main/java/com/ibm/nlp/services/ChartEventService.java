package com.ibm.nlp.services;

import java.util.List;

import com.ibm.nlp.model.mimic3.ChartEvent;
import com.ibm.nlp.model.mimic3.DItem;

/**
 * The Interface ChartEventServices.
 *
 * @author Henry Feldman
 * @(C) IBM Watson Health 2021
 */
public interface ChartEventService {

	/**
	 * Gets the all chart events. <b>NEVER EVER CALL THIS!!!</b> The returned data
	 * set is well over <u>50gb</u> in size and requires a join for 322m records!
	 * Java will throw an exception. There will be woe and bad tidings for all; it's
	 * a big twinkie! <b>You have been warned!</b> Use getChartEventsByPage to
	 * safely scroll through this data!
	 *
	 * @return the all chart events
	 */
	List<ChartEvent> getAllChartEvents();

	/**
	 * Gets the chart event.
	 *
	 * @param rowId the row id
	 * @return the chart event
	 */
	ChartEvent getChartEvent(Integer rowId);

	/**
	 * Gets the chart event for subject.
	 *
	 * @param subjectId the subject id
	 * @return the chart event for subject
	 */
	List<ChartEvent> getChartEventForSubject(Integer subjectId);

	/**
	 * Gets the chart event for admission.
	 *
	 * @param hadmId the hadm id
	 * @return the chart event for admission
	 */
	List<ChartEvent> getChartEventForAdmission(Integer hadmId);

	/**
	 * Ge allt D items.
	 *
	 * @return the list
	 */
	List<DItem> geAlltDItems();

	/**
	 * Save or update chart event.
	 *
	 * @param chartEvent the chart event
	 * @return the chart event
	 */
	ChartEvent saveOrUpdateChartEvent(ChartEvent chartEvent);

	/**
	 * Save or update D item.
	 *
	 * @param item the item
	 * @return the chart event
	 */
	DItem saveOrUpdateDItem(DItem item);

	/**
	 * Delete chart event.
	 *
	 * @param rowId the row id
	 */
	void deleteChartEvent(Integer rowId);

	/**
	 * Delete D item.
	 *
	 * @param rowId the row id
	 */
	void deleteDItem(Integer rowId);

	/**
	 * Gets the chart events for item.
	 *
	 * @param itemId the item id
	 * @return the chart events for item
	 */
	List<ChartEvent> getChartEventsForItem(Integer itemId);

	/**
	 * Gets the chart events by page.
	 *
	 * @param pageNumber the page number
	 * @param pageSize   the page size
	 * @return the chart events by page
	 */
	public List<ChartEvent> getChartEventsByPage(Integer pageNumber, Integer pageSize);

	/**
	 * Gets the number of chart event rows for use in calculating the page count
	 *
	 * @return the number of chart event rows
	 */
	Long getNumberOfChartEventRows();

}
