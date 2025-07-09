package com.qa.util;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class BulkUploadCsvCreator {
	
	private static final String Path_Template = (System.getProperty("user.dir")+ "/src/main/java/com/qa/testdata/Users.csv");

	public static void createTemplate(String email, String userName, String firstName, String lastName, String platform) throws IOException {
        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(Path_Template));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        ) {
        	csvPrinter.printRecord("Must be valid Email Eg: abc@example.com", "Maximum of 50 characters",
    				"", "","Must be valid platform Eg: Authoring or Administration or Analytics or Authoring;Administration;Analytics");
            csvPrinter.printRecord("email", "username", "firstname", "lastname", "platform");
            csvPrinter.printRecord(email, userName, firstName, lastName, platform);
            csvPrinter.flush();            
        }
    }
    

}
