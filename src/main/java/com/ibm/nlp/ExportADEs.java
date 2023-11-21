package com.ibm.nlp;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.nlp.hibernate.MedicationAdverseEventDao;
import com.ibm.nlp.model.mimic3.MedicationAdverseEvent;
import com.ibm.nlp.services.MedicationAdverseEventService;

public class ExportADEs {

	private static MedicationAdverseEventService medicationAdverseEventSDao = new MedicationAdverseEventDao();

	public static void main(String[] args) {
		try {
			List<MedicationAdverseEvent> adeEventList = medicationAdverseEventSDao.getMedicationAdverseEvents();
			System.out.println("NoteEventList: " + adeEventList.size());
			Workbook workBook = new XSSFWorkbook();
			Sheet adeSheet = workBook.createSheet("ACD ADEs");

			CellStyle boldStyle = workBook.createCellStyle();
			Font font = workBook.createFont();
			font.setBold(true);
			boldStyle.setFont(font);

			CellStyle pctStyle = workBook.createCellStyle();
			pctStyle.setDataFormat(workBook.createDataFormat().getFormat("0.000%"));

			Row firstRow = adeSheet.createRow(0);
			firstRow.createCell(0).setCellValue("Note Id");
			firstRow.getCell(0).setCellStyle(boldStyle);
			firstRow.createCell(1).setCellValue("ADE Reaction");
			firstRow.getCell(1).setCellStyle(boldStyle);
			firstRow.createCell(2).setCellValue("ADE Drug/Agent");
			firstRow.getCell(2).setCellStyle(boldStyle);
			firstRow.createCell(3).setCellValue("Span +/- 100chars");
			firstRow.getCell(3).setCellStyle(boldStyle);
			firstRow.createCell(4).setCellValue("Score");
			firstRow.getCell(4).setCellStyle(boldStyle);
			firstRow.createCell(5).setCellValue("isValid");
			firstRow.getCell(5).setCellStyle(boldStyle);
			firstRow.createCell(5).setCellValue("May Be Ambiguous");
			firstRow.getCell(5).setCellStyle(boldStyle);

			int rowCount = 0;
			for (MedicationAdverseEvent adverseEvent : adeEventList) {
				Row row = adeSheet.createRow(++rowCount);
				row.createCell(0).setCellValue(adverseEvent.getNoteEvent().getRowId());
				row.createCell(1).setCellValue(adverseEvent.getAdeReaction());
				row.createCell(2).setCellValue(adverseEvent.getAdeDrugName());
				if (adverseEvent.getAdeSpan() != null) {
					row.createCell(3).setCellValue(adverseEvent.getAdeSpan());
				} else {
					row.createCell(3).setCellValue("No Span Available");
				}
				row.createCell(4).setCellValue(adverseEvent.getAdeScore());
				row.getCell(4).setCellStyle(pctStyle);

				row.createCell(5).setCellValue(adverseEvent.getValid());
				row.createCell(6).setCellValue(adverseEvent.getAmbiguousPhrase());

			}
			adeSheet.autoSizeColumn(0);
			adeSheet.autoSizeColumn(1);
			adeSheet.autoSizeColumn(2);
			adeSheet.autoSizeColumn(3);
			adeSheet.autoSizeColumn(4);
			adeSheet.autoSizeColumn(5);
			adeSheet.autoSizeColumn(6);

			FileOutputStream outputStream = new FileOutputStream("ACD ADE.xlsx");
			workBook.write(outputStream);
			workBook.close();
			System.out.println("Wrote Excel File: ACD ADE.xlsx");
		} catch (Exception e) {
			System.out.println();
			e.printStackTrace();
		}

	}

}
