package com.awesheet.managers;

import com.awesheet.MainFrame;
import com.awesheet.enums.UIMessageType;
import com.awesheet.handlers.MenuHandler;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.messages.UIMessage;
import com.awesheet.enums.OpenResult;
import com.awesheet.enums.SaveResult;
import com.awesheet.models.Sheet;
import com.awesheet.models.Workbook;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;

public class WorkbookManager implements IMessageListener {
    private static WorkbookManager instance = null;

    public static WorkbookManager getInstance() {
        if (instance == null) {
            instance = new WorkbookManager();
        }

        return instance;
    }

    protected Workbook currentWorkbook;

    protected WorkbookManager() {
        // Register ourselves with the message manager.
        UIMessageManager.getInstance().registerListener(this);
    }

    public void init() {
        // Create a new empty workbook.
        currentWorkbook = new Workbook();

        // Enable menu bar items.
        MainFrame.getInstance().getMenuHandler().getFileSaveItem().setEnabled(true);
        MainFrame.getInstance().getMenuHandler().getFileSaveAsItem().setEnabled(true);
        MainFrame.getInstance().getMenuHandler().getFileImportItem().setEnabled(true);
        MainFrame.getInstance().getMenuHandler().getFileExportItem().setEnabled(true);
    }

    public int saveWorkbook() {
        if (currentWorkbook.getPath() == null) {
            FileDialog dialog = new FileDialog(MainFrame.getInstance(), "Save Workbook", FileDialog.SAVE);
            dialog.setFile("*.awew");
            dialog.setVisible(true);
            dialog.setFilenameFilter(new FilenameFilter(){
                public boolean accept(File dir, String name){
                    return (dir.isFile() && name.endsWith(".awew"));
                }
            });

            String filePath = dialog.getFile();
            String directory = dialog.getDirectory();
            dialog.dispose();

            if (directory != null && filePath != null) {
                String absolutePath = new File(directory + filePath).getAbsolutePath();
                byte serializedData[] = currentWorkbook.serialize();

                if (serializedData == null) {
                    return SaveResult.INTERNAL_ERROR;
                }

                if (FileManager.getInstance().saveFile(absolutePath, serializedData)) {
                    currentWorkbook.setPath(absolutePath);
                    return SaveResult.SUCCESS;
                }

                return SaveResult.ACCESS_ERROR;
            }

            // Dialog cancelled.
            return SaveResult.SUCCESS;
        }

        byte serializedData[] = currentWorkbook.serialize();

        if (serializedData == null) {
            return SaveResult.INTERNAL_ERROR;
        }

        if (FileManager.getInstance().saveFile(currentWorkbook.getPath(), serializedData)) {
            return SaveResult.SUCCESS;
        }

        return SaveResult.ACCESS_ERROR;
    }

    public int saveWorkbookAs() {
        FileDialog dialog = new FileDialog(MainFrame.getInstance(), "Save Workbook As", FileDialog.SAVE);
        dialog.setFile("*.awew");
        dialog.setVisible(true);
        dialog.setFilenameFilter(new FilenameFilter(){
            public boolean accept(File dir, String name){
                return (dir.isFile() && name.endsWith(".awew"));
            }
        });

        String filePath = dialog.getFile();
        String directory = dialog.getDirectory();
        dialog.dispose();

        if (directory != null && filePath != null) {
            String absolutePath = new File(directory + filePath).getAbsolutePath();
            byte serializedData[] = currentWorkbook.serialize();

            if (serializedData == null) {
                return SaveResult.INTERNAL_ERROR;
            }

            if (FileManager.getInstance().saveFile(absolutePath, serializedData)) {
                return SaveResult.SUCCESS;
            }

            return SaveResult.ACCESS_ERROR;
        }

        return SaveResult.SUCCESS;
    }

    public int openWorkbook() {
        FileDialog dialog = new FileDialog(MainFrame.getInstance(), "Open Workbook", FileDialog.LOAD);
        dialog.setFile("*.awew");
        dialog.setVisible(true);
        dialog.setFilenameFilter(new FilenameFilter(){
            public boolean accept(File dir, String name){
                return (dir.isFile() && name.endsWith(".awew"));
            }
        });

        String filePath = dialog.getFile();
        String directory = dialog.getDirectory();
        dialog.dispose();

        if (directory != null && filePath != null) {
            String absolutePath = new File(directory + filePath).getAbsolutePath();
            byte fileData[] = FileManager.getInstance().readFile(absolutePath);

            if (fileData == null) {
                return OpenResult.ACCESS_ERROR;
            }

            Workbook newWorkbook = new Workbook(fileData, absolutePath);

            if (!newWorkbook.isValid()) {
                return OpenResult.INVALID_FORMAT;
            }

            currentWorkbook.destroy();
            currentWorkbook = newWorkbook;
            newWorkbook.addSheets();

            return OpenResult.SUCCESS;
        }

        return OpenResult.SUCCESS;
    }

    public Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    public void createWorkbook() {
        currentWorkbook.destroy();
        currentWorkbook = new Workbook();
    }

    @Override
    public void onMessage(UIMessage message) {
        switch (message.getType()) {
            case UIMessageType.SAVE_WORKBOOK: {
                saveWorkbook();
                break;
            }

            case UIMessageType.SAVE_WORKBOOK_AS: {
                saveWorkbookAs();
                break;
            }

            case UIMessageType.OPEN_WORKBOOK: {
                openWorkbook();
                break;
            }

            case UIMessageType.CREATE_WORKBOOK: {
                createWorkbook();
                break;
            }
        }
    }
}
