package com.ibm.fhir.util;

public class HtmlUtil {

	public static String getHtmlPagehead(String title) {
		return "<!DOCTYPE html> <html> <head> <title>" + title + "</title> </head>\n" + "<style>\n"
				+ "/* Tooltip container */\n" + ".tooltip {\n" + "  position: relative;\n"
				+ "  display: inline-block;\n"
				+ "  border-bottom: 2px dotted red; /* If you want dots under the hoverable text */\n" + "}\n" + "\n"
				+ "/* Tooltip text */\n" + ".tooltip .tooltiptext {\n" + "  visibility: hidden;\n" + "  width: 120px;\n"
				+ "  background-color: black;\n" + "  color: #fff;\n" + "  text-align: center;\n"
				+ "  padding: 5px 0;\n" + "  border-radius: 6px;\n" + " \n"
				+ "  /* Position the tooltip text - see examples below! */\n" + "  position: absolute;\n"
				+ "  z-index: 1;\n" + "}\n" + "\n" + ".like-pre { white-space: pre-wrap; }"
				+ "/* Show the tooltip text when you mouse over the tooltip container */\n"
				+ ".tooltip:hover .tooltiptext {\n" + "  visibility: visible;\n" + "}\n" + "</style>\n" + "\n"
				+ "<body>\n";

	}
}
