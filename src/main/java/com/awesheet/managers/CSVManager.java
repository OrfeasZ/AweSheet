/*
 * AweSheet - Simple Open-Source Spreadsheet Editor
 * Copyright (c) 2015 - 2016, Orfeas - Ioannis Zafeiris, Nikolaos Fylakis
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.awesheet.managers;

import com.awesheet.models.Cell;
import com.awesheet.models.Sheet;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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
                sheet.setCellValue(x, (int) record.getRecordNumber() - 1, record.get(x), true);
            }
        }

        return sheet;
    }

    public boolean exportSheet(Sheet sheet, String path) {
        FileWriter writer = null;
        CSVPrinter printer = null;

        try {
            writer = new FileWriter(path);
            printer = new CSVPrinter(writer, CSVFormat.RFC4180);

            // Write records.
            for (int y = 0; y < sheet.getMaxRow(); ++y) {
                List<String> values = new ArrayList<String>();

                for (int x = 0; x < sheet.getMaxColumn(); ++x) {
                    Cell cell = sheet.getCell(x, y);
                    values.add(cell == null ? "" : cell.getDisplayValue());
                }

                printer.printRecord(values);
            }
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (writer != null) {
                    writer.flush();
                    writer.close();
                }

                if (printer != null) {
                    printer.close();
                }
            }
            catch (Exception ignored) {
            }
        }

        return true;
    }
}
