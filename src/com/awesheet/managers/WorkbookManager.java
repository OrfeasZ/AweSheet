package com.awesheet.managers;

import com.awesheet.interfaces.IMessageListener;
import com.awesheet.messages.UIMessage;
import com.awesheet.enums.OpenResult;
import com.awesheet.enums.SaveResult;
import com.awesheet.models.Workbook;

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
        currentWorkbook = null;

        UIMessageManager.getInstance().registerListener(this);
    }

    public SaveResult saveWorkbook() {
        return SaveResult.ACCESS_ERROR;
    }

    public OpenResult openWorkbook(String path) {
        return OpenResult.ACCESS_ERROR;
    }

    public Workbook getCurrentWorkbook() {
        return currentWorkbook;
    }

    public void createWorkbook() {

    }

    @Override
    public void onMessage(UIMessage message) {

    }
}
