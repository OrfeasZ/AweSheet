package com.awesheet.managers;

import com.awesheet.models.Sheet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class CSVManager {
    private static CSVManager instance = null;

    public static CSVManager getInstance() {
        if (instance == null) {
            instance = new CSVManager();
        }

        return instance;
    }

    protected CSVManager() {
    }

    public Sheet importSheet(String path) {
        File csvData = new File(path);

        // Parse the CSV file.
        CSVParser parser;

        try {
            parser = CSVParser.parse(csvData, Charset.defaultCharset(), CSVFormat.RFC4180);
        } catch (IOException e) {
            return null;
        }

        // Create our new sheet.
        Sheet sheet = new Sheet("Imported Sheet");

        // Populate its cells.
        for (CSVRecord record : parser) {
            for (int x = 0; x < record.size(); ++x) {
                System.out.println(record.getRecordNumber());
                sheet.setCellValue(x, (int) record.getRecordNumber(), record.get(x));
            }
        }

        return sheet;
    }

    public boolean exportSheet(Sheet sheet, String path) {
        return false;
    }
}
