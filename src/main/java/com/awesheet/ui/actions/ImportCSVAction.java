package com.awesheet.ui.actions;

import com.awesheet.MainFrame;
import com.awesheet.managers.CSVManager;
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
                // TODO: Notify UI of error.
            } else {
                WorkbookManager.getInstance().getCurrentWorkbook().replaceSheet(currentSheet.getID(), importedSheet);
            }
        }
    }
}
