package com.awesheet.managers;

import com.awesheet.MainFrame;
import com.awesheet.enums.UIMessageType;
import com.awesheet.handlers.MenuHandler;
import com.awesheet.interfaces.IMessageListener;
import com.awesheet.messages.UIMessage;
import com.awesheet.enums.OpenResult;
import com.awesheet.enums.SaveResult;
import com.awesheet.models.Workbook;

import javax.swing.*;

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
        // TODO
        return SaveResult.ACCESS_ERROR;
    }

    public int openWorkbook(String path) {
        // TODO
        return OpenResult.ACCESS_ERROR;
    }

    public Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    public void createWorkbook() {
        // TODO
    }

    @Override
    public void onMessage(UIMessage message) {

    }
}
