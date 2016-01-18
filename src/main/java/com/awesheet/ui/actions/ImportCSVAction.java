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
import com.awesheet.actions.ShowPopupAction;
import com.awesheet.actions.popups.MessagePopup;
import com.awesheet.enums.UIPopupType;
import com.awesheet.managers.CSVManager;
import com.awesheet.managers.UIMessageManager;
import com.awesheet.managers.WorkbookManager;
import com.awesheet.models.Sheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ImportCSVAction extends AbstractAction {
    public ImportCSVAction() {
        super("Import...");
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

        FileDialog dialog = new FileDialog(MainFrame.getInstance(), "Import from CSV", FileDialog.LOAD);
        dialog.setFile(currentSheet.getName() + ".csv");
        dialog.setVisible(true);

        String filePath = dialog.getFile();
        String directory = dialog.getDirectory();
        dialog.dispose();

        if (directory != null && filePath != null) {
            String absolutePath = new File(directory + filePath).getAbsolutePath();
            Sheet importedSheet = CSVManager.getInstance().importSheet(absolutePath);

            if (importedSheet == null) {
                UIMessageManager.getInstance().dispatchAction(
                        new ShowPopupAction<MessagePopup>(UIPopupType.MESSAGE_POPUP,
                                new MessagePopup("Import Error", "Could not import CSV. Please make sure that the file you're trying to import is in the right format.")));
            } else {
                WorkbookManager.getInstance().getCurrentWorkbook().replaceSheet(currentSheet.getID(), importedSheet);
            }
        }
    }
}
