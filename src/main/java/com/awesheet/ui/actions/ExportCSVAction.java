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
