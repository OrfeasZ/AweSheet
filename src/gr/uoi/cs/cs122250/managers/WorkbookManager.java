package gr.uoi.cs.cs122250.managers;

import gr.uoi.cs.cs122250.enums.OpenResult;
import gr.uoi.cs.cs122250.enums.SaveResult;
import gr.uoi.cs.cs122250.interfaces.IMessageListener;
import gr.uoi.cs.cs122250.models.Workbook;

public class WorkbookManager implements IMessageListener {
    protected Workbook currentWorkbook;

    WorkbookManager() {
        this.currentWorkbook = null;
    }

    public SaveResult saveWorkbook() {
        return SaveResult.AcessError;
    }

    public OpenResult openWorkbook(String path) {
        return OpenResult.AccessError;
    }

    public Workbook getCurrentWorkbook() {
        return this.currentWorkbook;
    }

    public void createWorkbook() {

    }

    @Override
    public void onMessage(String message, Object data) {

    }
}
