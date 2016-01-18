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

package com.awesheet.ui.actions;

import com.awesheet.MainFrame;
import com.awesheet.managers.CSVManager;
import com.awesheet.managers.WorkbookManager;
import com.awesheet.models.Sheet;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ExportCSVAction extends AbstractAction {
    public ExportCSVAction() {
        super("Export...");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (WorkbookManager.getInstance().getCurrentWorkbook() == null) {
            return;
        }

        Sheet currentSheet = WorkbookManager.getInstance().getCurrentWorkbook().getSelectedSheet();

        if (currentSheet == null) {
            return;
        }

        if (currentSheet.getCells().size() == 0) {
            return;
        }

        FileDialog dialog = new FileDialog(MainFrame.getInstance(), "Export as CSV", FileDialog.SAVE);
        dialog.setFile(currentSheet.getName() + ".csv");
        dialog.setVisible(true);

        String filePath = dialog.getFile();
        String directory = dialog.getDirectory();
        dialog.dispose();

        if (directory != null && filePath != null) {
            String absolutePath = new File(directory + filePath).getAbsolutePath();
            CSVManager.getInstance().exportSheet(currentSheet, absolutePath);
        }
    }
}
